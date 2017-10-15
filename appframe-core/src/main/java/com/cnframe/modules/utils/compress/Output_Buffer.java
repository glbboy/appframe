package com.cnframe.modules.utils.compress;

public class Output_Buffer {
	
	private int OutCnt;
	private byte[] OutBuff;

	public Output_Buffer(byte[] b) {
		OutCnt = 0;
		OutBuff = b;
	}

	public int count() {
		return OutCnt;
	}

	public void putbyte(byte c) {
		OutBuff[OutCnt++] = c;
	}

	public void writebytes(byte[] buf, int n) {
		int i;
		for (i = 0; i < n; i++)
			OutBuff[OutCnt++] = buf[i];
	}
}
