package com.cnframe.modules.utils.compress;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class Compress {
	
	final static int BITS = 16; /* always set to 16 for SPEC95 */
	final static int INIT_BITS = 9; /* initial number of bits/code */
	final static int HSIZE = 69001; /* 95% occupancy */
	final static int SUFFIX_TAB_SZ = 65536; /* 2**BITS */
	final static int STACK_SZ = 8000; /* decompression stack size */
	final static byte magic_header[] = { (byte) 037, (byte) 0235 }; /* 1F 9D */
	/* Defines for third byte of header */
	final static int BIT_MASK = 0x1f;
	final static int BLOCK_MASK = 0x80;
	/*
	 * 
	 * Masks 0x40 and 0x20 are free. I think 0x20 should mean that there is a
	 * 
	 * fourth header byte (for expansion).
	 */

	/*
	 * 
	 * the next two codes should not be changed lightly, as they must not lie
	 * 
	 * within the contiguous general code space.
	 */

	final static int FIRST = 257; /* first free entry */
	final static int CLEAR = 256; /* table clear output code */
	final static byte lmask[] = { (byte) 0xff, (byte) 0xfe, (byte) 0xfc,
	(byte) 0xf8, (byte) 0xf0, (byte) 0xe0, (byte) 0xc0, (byte) 0x80,
	(byte) 0x00 };
	final static byte rmask[] = { (byte) 0x00, (byte) 0x01, (byte) 0x03,
	(byte) 0x07, (byte) 0x0f, (byte) 0x1f, (byte) 0x3f, (byte) 0x7f,
	(byte) 0xff };
	public static int spec_select_action(byte[] from_buf, int from_count,int action, byte[] to_buf) {
		Input_Buffer in = new Input_Buffer(from_count, from_buf);
		Output_Buffer out = new Output_Buffer(to_buf);
		if (action == 0) {
			Compressor comp = new Compressor(in, out);
			comp.compress();
		} else {
			Decompressor decomp = new Decompressor(in, out);
			decomp.decompress();
		}
		return (out.count());
	}
	public static byte[] getBytes(String filePath){   
		FileInputStream fis = null;
		ByteArrayOutputStream bos = null;
        byte[] buffer = null;   
        try {   
            File file = new File(filePath);   
            fis = new FileInputStream(file);   
            bos = new ByteArrayOutputStream(1024);   
            byte[] b = new byte[1024];   
            int n;   
            while ((n = fis.read(b)) != -1) {   
                bos.write(b, 0, n);   
            }     
        } catch (FileNotFoundException e) {   
            e.printStackTrace();   
        } catch (IOException e) {   
            e.printStackTrace();   
        } finally{
        	if (bos!=null){
        		try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
        	}
        	if (fis!=null){
        		try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}  
        	}
             
        }
        if (bos!=null){
        	buffer = bos.toByteArray(); 
        }
        bos = null;
        fis = null;
        return buffer;   
    }   
	public final static void doDecompress(String filePath,String fileName){
		//解压缩.Z
		byte[] from_buf = Compress.getBytes(filePath+fileName);
		String tarFile = filePath+fileName.replace(".tar.Z", ".tar");
		File file = new File(tarFile);
		FileOutputStream  fos = null;
		try {
			fos = new FileOutputStream(file);
			byte[] to_buf = new byte[1024*1024*10];
			int len = Compress.spec_select_action(from_buf,from_buf.length,1,to_buf);
			fos.write(to_buf,0,len);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (fos!=null){
				try {
					fos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				fos = null;
			}
		}
	}
	@SuppressWarnings("resource")
	public final static void main(String[] args) throws Exception{
		String filePath = "D:\\quartzftp\\FTP200707271404252174.tar.Z";
		String outPutFilePaht = "D:\\quartzftp\\FTP200707271404252174.tar";
		byte[] from_buf = getBytes(filePath);
		File file = new File(outPutFilePaht);   
		FileOutputStream  fos = new FileOutputStream(file);   
		 //ByteArrayOutputStream  bos = new ByteArrayOutputStream(10000);   
		//ByteArrayOutputStream baos = new ByteArrayOutputStream();  
		byte[] to_buf = new byte[1024*1024*10];
		System.out.println(from_buf.length);
		int len = spec_select_action(from_buf,from_buf.length,1,to_buf);
		fos.write(to_buf,0,len);
	}
}
