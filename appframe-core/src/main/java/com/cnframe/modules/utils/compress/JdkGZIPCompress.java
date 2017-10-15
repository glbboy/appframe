package com.cnframe.modules.utils.compress;

import java.io.BufferedInputStream;  
import java.io.BufferedOutputStream;  
import java.io.File;  
import java.io.FileInputStream;  
import java.io.FileOutputStream;  
import java.io.IOException;  
import java.io.InputStream;  
import java.io.OutputStream;  
import java.util.zip.GZIPInputStream;  
import java.util.zip.GZIPOutputStream;  
import org.apache.commons.io.FilenameUtils;  
import org.apache.commons.io.IOUtils;  
//JDK GZIP压缩
public class JdkGZIPCompress extends ZCompress {  
    @Override  
    public void doCompress(File srcFile, File destFile) throws IOException {  
        OutputStream out = null;  
        InputStream is = null;  
        try {  
            is = new BufferedInputStream(new FileInputStream(srcFile), bufferLen);  
            out = new GZIPOutputStream(new BufferedOutputStream(new FileOutputStream(destFile), bufferLen));  
            IOUtils.copy(is, out);  
        } finally {  
            IOUtils.closeQuietly(is);  
            IOUtils.closeQuietly(out);  
        }  
    }  
    @Override  
    public void doDecompress(File srcFile, File destDir) throws IOException {  
        InputStream is = null;  
        OutputStream os = null;  
        try {  
            File destFile = new File(destDir, FilenameUtils.getBaseName(srcFile.toString()));  
            is = new GZIPInputStream(new BufferedInputStream(new FileInputStream(srcFile), bufferLen));  
            os = new BufferedOutputStream(new FileOutputStream(destFile), bufferLen);  
            IOUtils.copy(is, os);  
        } finally {  
            IOUtils.closeQuietly(is);  
            IOUtils.closeQuietly(os);  
        }  
    }  
}  
