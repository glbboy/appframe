package com.cnframe.modules.utils.compress;

public class Comp_Base {
	
	protected int n_bits; /* number of bits/code */
	protected int maxbits; /* user settable max # bits/code */
	protected int maxcode; /* maximum code, given n_bits */
	protected int maxmaxcode; /* should NEVER generate this code */
	protected int offset;
	protected int block_compress;
	protected int free_ent; /* first unused entry */
	protected int clear_flg;
	protected Input_Buffer Input;
	protected Output_Buffer Output;
	protected byte buf[];

	public Comp_Base(Input_Buffer in, Output_Buffer out) {
		Input = in;
		Output = out;
		maxbits = Compress.BITS;
		block_compress = Compress.BLOCK_MASK;
		buf = new byte[Compress.BITS];
	}

	public int MAXCODE() {
		return ((1 << (n_bits)) - 1);
	}
}
