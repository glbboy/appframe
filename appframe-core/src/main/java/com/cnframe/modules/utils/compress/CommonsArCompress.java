package com.cnframe.modules.utils.compress;

import java.io.BufferedInputStream;  
import java.io.BufferedOutputStream;  
import java.io.File;  
import java.io.FileInputStream;  
import java.io.FileOutputStream;  
import java.io.IOException;  
import java.io.InputStream;  
import org.apache.commons.compress.archivers.ar.ArArchiveEntry;  
import org.apache.commons.compress.archivers.ar.ArArchiveInputStream;  
import org.apache.commons.compress.archivers.ar.ArArchiveOutputStream;  
import org.apache.commons.io.IOUtils;  
//Apache commons compress AR打包
public class CommonsArCompress extends ZCompress {  
    @Override  
    public void doCompress(File srcFile, File destFile) throws IOException {  
        ArArchiveOutputStream zout = null;  
        InputStream is = null;  
        try {  
            is = new BufferedInputStream(new FileInputStream(srcFile), bufferLen);  
            zout = new ArArchiveOutputStream(new BufferedOutputStream(new FileOutputStream(destFile), bufferLen));  
            zout.putArchiveEntry(new ArArchiveEntry(srcFile, srcFile.getName()));  
            IOUtils.copy(is, zout);  
            zout.closeArchiveEntry();  
        } finally {  
            IOUtils.closeQuietly(is);  
            IOUtils.closeQuietly(zout);  
        }  
    }  
    @Override  
    public void doDecompress(File srcFile, File destDir) throws IOException {  
        ArArchiveInputStream is = null;  
        try {  
            is = new ArArchiveInputStream(new BufferedInputStream(new FileInputStream(srcFile), bufferLen));  
            ArArchiveEntry entry = null;  
            while ((entry = is.getNextArEntry()) != null) {  
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

