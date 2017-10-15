package com.cnframe.modules.utils.compress;

import java.io.BufferedInputStream;  
import java.io.BufferedOutputStream;  
import java.io.File;  
import java.io.FileInputStream;  
import java.io.FileOutputStream;  
import java.io.IOException;  
import java.io.InputStream;  
import java.io.OutputStream;  
import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorOutputStream;
import org.apache.commons.io.FilenameUtils;  
import org.apache.commons.io.IOUtils;  
//Apache commons compress GZIP压缩
public class CommonsGZIPCompress extends ZCompress {  
	@Override  
    public void doCompress(File srcFile, File destFile) throws IOException {  
        OutputStream out = null;  
        InputStream is = null;  
        try {  
            is = new BufferedInputStream(new FileInputStream(srcFile), bufferLen);  
            out = new GzipCompressorOutputStream(new BufferedOutputStream(new FileOutputStream(destFile), bufferLen));  
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
            is = new GzipCompressorInputStream(new BufferedInputStream(new FileInputStream(srcFile), bufferLen));  
            os = new BufferedOutputStream(new FileOutputStream(destFile), bufferLen);  
            IOUtils.copy(is, os);  
        } finally {  
            IOUtils.closeQuietly(is);  
            IOUtils.closeQuietly(os);  
        }  
    }  
    public final static void main(String[] args) throws Exception{
		String srcFile = "D:\\quartzftp\\bms_refresh.sh.bak.20130624.gz";
		String destDir = "D:\\quartzftp\\";
		CommonsGZIPCompress compress = new CommonsGZIPCompress();
		compress.doDecompress(new File(srcFile),new File(destDir));
	}
}  