package com.cnframe.modules.utils.compress;

public class Code_Table {
	private short tab[];
	public Code_Table() {
		tab = new short[Compress.HSIZE];
	}

	public int of(int i) {
		return ((int) tab[i] << 16 >>> 16);
	}

	public void set(int i, int v) {
		tab[i] = (short) v;
	}

	public void clear(int size) {
		int code;
		for (code = 0; code < size; code++) {
			tab[code] = 0;
		}
	}
}
