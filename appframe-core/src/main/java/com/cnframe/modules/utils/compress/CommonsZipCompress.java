package com.cnframe.modules.utils.compress;
import java.io.BufferedInputStream;  
import java.io.BufferedOutputStream;  
import java.io.File;  
import java.io.FileInputStream;  
import java.io.FileOutputStream;  
import java.io.IOException;  
import java.io.InputStream;  
import java.io.OutputStream;  
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;  
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;  
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;  
import org.apache.commons.io.IOUtils;  
//Apache commons compress ZIP压缩
public class CommonsZipCompress extends ZCompress {  
    /**用于单文件压缩*/  
    @Override  
    public void doCompress(File srcFile, File destFile) throws IOException {  
        ZipArchiveOutputStream out = null;  
        InputStream is = null;  
        try {  
            is = new BufferedInputStream(new FileInputStream(srcFile), bufferLen);  
            out = new ZipArchiveOutputStream(new BufferedOutputStream(new FileOutputStream(destFile), bufferLen));  
            ZipArchiveEntry entry = new ZipArchiveEntry(srcFile.getName());  
            entry.setSize(srcFile.length());  
            out.putArchiveEntry(entry);  
            IOUtils.copy(is, out);  
            out.closeArchiveEntry();  
        } finally {  
            IOUtils.closeQuietly(is);  
            IOUtils.closeQuietly(out);  
        }  
    }  
    @Override  
    public void doDecompress(File srcFile, File destDir) throws IOException {  
        ZipArchiveInputStream is = null;  
        try {  
            is = new ZipArchiveInputStream(new BufferedInputStream(new FileInputStream(srcFile), bufferLen));  
            ZipArchiveEntry entry = null;  
            while ((entry = is.getNextZipEntry()) != null) {  
                if (entry.isDirectory()) {  
                    File directory = new File(destDir, entry.getName());  
                    directory.mkdirs();  
                } else {  
                    OutputStream os = null;  
                    try {  
                        os = new BufferedOutputStream(  
                                new FileOutputStream(new File(destDir, entry.getName())), bufferLen);  
                        IOUtils.copy(is, os);  
                    } finally {  
                        IOUtils.closeQuietly(os);  
                    }  
                }  
            }  
        } finally {  
            IOUtils.closeQuietly(is);  
        }  
    }  
    public final static void main(String[] args) throws Exception{
		String srcFile = "D:\\quartzftp\\C_B_36_BSDM_NPI_09_20140101_0001_A_1.XML.zip";
		String destDir = "D:\\quartzftp\\";
		CommonsZipCompress compress = new CommonsZipCompress();
		compress.doDecompress(new File(srcFile),new File(destDir));
	}
}  
