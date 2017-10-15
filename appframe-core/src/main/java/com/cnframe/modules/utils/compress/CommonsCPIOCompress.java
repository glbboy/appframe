package com.cnframe.modules.utils.compress;

import java.io.BufferedInputStream;  
import java.io.BufferedOutputStream;  
import java.io.File;  
import java.io.FileInputStream;  
import java.io.FileOutputStream;  
import java.io.IOException;  
import java.io.InputStream;  
import org.apache.commons.compress.archivers.cpio.CpioArchiveEntry;  
import org.apache.commons.compress.archivers.cpio.CpioArchiveInputStream;  
import org.apache.commons.compress.archivers.cpio.CpioArchiveOutputStream;  
import org.apache.commons.io.IOUtils;  
//Apache commons compress CPIO打包
public class CommonsCPIOCompress extends ZCompress {  
    @Override  
    public void doCompress(File srcFile, File destFile) throws IOException {  
        CpioArchiveOutputStream out = null;  
        InputStream is = null;  
        try {  
            is = new BufferedInputStream(new FileInputStream(srcFile), bufferLen);  
            out = new CpioArchiveOutputStream(new BufferedOutputStream(new FileOutputStream(destFile), bufferLen));  
            out.putArchiveEntry(new CpioArchiveEntry(srcFile, srcFile.getName()));  
            IOUtils.copy(is, out);  
            out.closeArchiveEntry();  
        } finally {  
            IOUtils.closeQuietly(is);  
            IOUtils.closeQuietly(out);  
        }  
    }  
    @Override  
    public void doDecompress(File srcFile, File destDir) throws IOException {  
        CpioArchiveInputStream is = null;  
        try {  
            is = new CpioArchiveInputStream(new BufferedInputStream(new FileInputStream(srcFile), bufferLen));  
            CpioArchiveEntry entry = null;  
            while ((entry = is.getNextCPIOEntry()) != null) {  
                if (entry.isDirectory()) {  
                    File directory = new File(destDir, entry.getName());  
                    directory.mkdirs();  
                } else {  
                    BufferedOutputStream bos = null;  
                    try {  
                        byte[] buffer = new byte[bufferLen];  
                        bos = new BufferedOutputStream(new FileOutputStream(  
                                new File(destDir, entry.getName())), bufferLen);  
                        int len;  
                        long size = entry.getSize();  
                        while (size > 0) {  
                            if (size < bufferLen) {  
                                len = is.read(buffer, 0, (int) size);  
                                size -= len;  
                            } else {  
                                len = is.read(buffer);  
                                size -= len;  
                            }  
                            bos.write(buffer, 0, len);  
                        }  
                    } finally {  
                        IOUtils.closeQuietly(bos);  
                    }  
                }  
            }  
        } finally {  
            IOUtils.closeQuietly(is);  
        }  
    }  
}  
