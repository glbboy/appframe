package com.cnframe.modules.utils.compress;
import java.io.BufferedInputStream;  
import java.io.BufferedOutputStream;  
import java.io.File;  
import java.io.FileInputStream;  
import java.io.FileOutputStream;  
import java.io.IOException;  
import java.io.InputStream;  
import java.io.OutputStream;  
import java.util.zip.ZipEntry;  
import java.util.zip.ZipInputStream;  
import java.util.zip.ZipOutputStream;  
import org.apache.commons.io.IOUtils;  
//JDK ZIP压缩
public class JdkZipCompress extends ZCompress {  
    @Override  
    public void doCompress(File srcFile, File destFile) throws IOException {  
        ZipOutputStream zout = null;  
        InputStream is = null;  
        try {  
            is = new BufferedInputStream(new FileInputStream(srcFile), bufferLen);  
            zout = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(destFile), bufferLen));  
            zout.putNextEntry(new ZipEntry(srcFile.getName()));  
            IOUtils.copy(is, zout);  
            zout.closeEntry();  
        } finally {  
            IOUtils.closeQuietly(is);  
            IOUtils.closeQuietly(zout);  
        }  
    }  
    @Override  
    public void doDecompress(File srcFile, File destDir) throws IOException {  
        ZipInputStream is = null;  
        try {  
            is = new ZipInputStream(new BufferedInputStream(new FileInputStream(srcFile), bufferLen));  
            ZipEntry entry = null;  
            while ((entry = is.getNextEntry()) != null) {  
                if (entry.isDirectory()) {  
                    File directory = new File(destDir, entry.getName());  
                    directory.mkdirs();  
                    is.closeEntry();  
                } else {  
                    OutputStream os = null;  
                    try {  
                        os = new BufferedOutputStream(  
                                new FileOutputStream(new File(destDir, entry.getName())), bufferLen);  
                        IOUtils.copy(is, os);  
                    } finally {  
                        IOUtils.closeQuietly(os);  
                    }  
                    is.closeEntry();  
                }  
            }  
        } finally {  
            IOUtils.closeQuietly(is);  
        }  
    }  
}  

