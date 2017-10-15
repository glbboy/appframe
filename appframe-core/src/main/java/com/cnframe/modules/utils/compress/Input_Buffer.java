package com.cnframe.modules.utils.compress;

public class Input_Buffer {
	
	private int InCnt;
	private int Current;
	private byte[] InBuff;
	
	public Input_Buffer(int c, byte[] b) {
		InCnt = c;
		Current = 0;
		InBuff = b;
	}

	// kmd 02/26/98 public byte getbyte() {
	public int getbyte() {
		if (InCnt > 0) {
			InCnt--;
			// return( InBuff[Current++] ); // kmd 02/26/98
			return (InBuff[Current++] & 0x00FF); // kmd 02/26/98
		} else {
			// return( (byte)-1 ); // kmd 02/26/98
			return (-1); // kmd 02/26/98
		}
	}

	public int readbytes(byte[] buf, int n) {
		int i;
		if (InCnt <= 0)
			return (-1);
		if (n > InCnt)
			n = InCnt;
		for (i = 0; i < n; i++) {
			buf[i] = InBuff[Current++];
			InCnt--;
		}
		return (i);
	}
}
