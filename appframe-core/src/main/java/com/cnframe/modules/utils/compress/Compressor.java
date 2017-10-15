package com.cnframe.modules.utils.compress;

public class Compressor extends Comp_Base {
	
	private final static int CHECK_GAP = 10000; /* ratio check interval */
	private int ratio;
	private int checkpoint;
	private int in_count; /* length of input */
	private int bytes_out; /* length of compressed output */
	private Hash_Table htab;
	private Code_Table codetab;

	public Compressor(Input_Buffer in, Output_Buffer out) {
		super(in, out);
		if (maxbits < Compress.INIT_BITS)
			maxbits = Compress.INIT_BITS;
		if (maxbits > Compress.BITS)
			maxbits = Compress.BITS;
		maxmaxcode = 1 << maxbits;
		n_bits = Compress.INIT_BITS;
		maxcode = MAXCODE();
		offset = 0;
		bytes_out = 3; /* includes 3-byte header mojo */
		clear_flg = 0;
		ratio = 0;
		in_count = 1;
		checkpoint = CHECK_GAP;
		free_ent = ((block_compress != 0) ? Compress.FIRST : 256);
		htab = new Hash_Table(); // dm/kmd 4/10/98
		codetab = new Code_Table();
		Output.putbyte(Compress.magic_header[0]);
		Output.putbyte(Compress.magic_header[1]);
		Output.putbyte((byte) (maxbits | block_compress));
	}

	public void compress() {
		int fcode;
		int i = 0;
		int c;
		int ent;
		int disp;
		int hsize_reg;
		int hshift;
		ent = Input.getbyte();
		hshift = 0;
		for (fcode = htab.hsize(); fcode < 65536; fcode *= 2)
			hshift++;
		hshift = 8 - hshift; /* set hash code range bound */
		hsize_reg = htab.hsize();
		htab.clear(); /* clear hash table */
		next_byte: while ((c = Input.getbyte()) != -1) {
			in_count++;
			fcode = (((int) c << maxbits) + ent);
			i = ((c << hshift) ^ ent); /* xor hashing */
			int temphtab = htab.of(i); // dm/kmd 4/15
			// dm kmd if ( htab.of (i) == fcode ) { // dm/kmd 4/15
			if (temphtab == fcode) {
				ent = codetab.of(i);
				continue next_byte;
			}

			// dm kmd 4/15 if ( htab.of (i) >= 0 ) { /* non-empty slot */
			if (temphtab >= 0) { /* non-empty slot dm kmd 4/15 */
				disp = hsize_reg - i; /* secondary hash (after G. Knott) */
				if (i == 0)
					disp = 1;
				do {
					if ((i -= disp) < 0)
						i += hsize_reg;
					temphtab = htab.of(i); // dm/kmd 4/15
					// dm/kmd 4/15 if ( htab.of (i) == fcode ) {
					if (temphtab == fcode) {
						ent = codetab.of(i);
						continue next_byte;
					}
					// dm/kmd 4/15 } while ( htab.of (i) > 0 );
				} while (temphtab > 0); // dm kmd 4/15
			}
			output(ent);
			ent = c;
			if (free_ent < maxmaxcode) {
				codetab.set(i, free_ent++); /* code -> hashtable */
				htab.set(i, fcode);
			} else if ((in_count >= checkpoint) && (block_compress != 0))
				cl_block();
		}

		/*
		 * 
		 * Put out the final code.
		 */
		output(ent);
		output(-1);
		return;
	}

	/*
	 * 
	 * Output the given code. Inputs: code: A n_bits-bit integer. If == -1, then
	 * 
	 * EOF. This assumes that n_bits =< (long)wordsize - 1. Outputs: Outputs
	 * 
	 * code to the file. Assumptions: Chars are 8 bits long. Algorithm: Maintain
	 * 
	 * a BITS character long buffer (so that 8 codes will fit in it exactly).
	 */

	private void output(int code) {
		int r_off = offset, bits = n_bits;
		int bp = 0;
		if (code >= 0) {
			/*
			 * 
			 * Get to the first byte.
			 */
			bp += (r_off >> 3);
			r_off &= 7;
			/*
			 * 
			 * Since code is always >= 8 bits, only need to mask the first hunk
			 * 
			 * on the left.
			 */

			buf[bp] = (byte) ((buf[bp] & Compress.rmask[r_off]) | (code << r_off)

					& Compress.lmask[r_off]);

			bp++;
			bits -= (8 - r_off);
			code >>= 8 - r_off;
			/* Get any 8 bit parts in the middle (<=1 for up to 16 bits). */
			if (bits >= 8) {
				buf[bp++] = (byte) code;
				code >>= 8;
				bits -= 8;
			}
			/* Last bits. */
			if (bits != 0)
				buf[bp] = (byte) code;
			offset += n_bits;
			if (offset == (n_bits << 3)) {
				bp = 0;
				bits = n_bits;
				bytes_out += bits;
				do
					Output.putbyte(buf[bp++]);
				while (--bits != 0);
				offset = 0;
			}

			/*
			 * 
			 * If the next entry is going to be too big for the code size, then
			 * 
			 * increase it, if possible.
			 */
			if (free_ent > maxcode || (clear_flg > 0)) {
				/*
				 * 
				 * Write the whole buffer, because the input side won't discover
				 * 
				 * the size increase until after it has read it.
				 */
				if (offset > 0) {
					Output.writebytes(buf, n_bits);
					bytes_out += n_bits;
				}
				offset = 0;
				if (clear_flg != 0) {
					n_bits = Compress.INIT_BITS;
					maxcode = MAXCODE();
					clear_flg = 0;
				} else {
					n_bits++;
					if (n_bits == maxbits)
						maxcode = maxmaxcode;
					else
						maxcode = MAXCODE();
				}
			}
		} else {
			/*
			 *
			 * At EOF, write the rest of the buffer.
			 */
			if (offset > 0)
				Output.writebytes(buf, ((offset + 7) / 8));
			bytes_out += (offset + 7) / 8;
			offset = 0;
		}
	}
	/* table clear for block compress */
	private void cl_block() {
		int rat;
		checkpoint = in_count + CHECK_GAP;
		if (in_count > 0x007fffff) { /* shift will overflow */
			rat = bytes_out >> 8;
			if (rat == 0) { /* Don't divide by zero */
				rat = 0x7fffffff;
			} else {
				rat = in_count / rat;
			}
		} else {
			rat = (in_count << 8) / bytes_out; /* 8 fractional bits */
		}
		if (rat > ratio) {
			ratio = rat;
		} else {
			ratio = 0;
			htab.clear();
			free_ent = Compress.FIRST;
			clear_flg = 1;
			output((int) Compress.CLEAR);
		}
	}

	final class Hash_Table { // moved 4/15/98 dm/kmd
		/*
		 * 
		 * Use protected instead of private to allow access by parent class of
		 * 
		 * inner class. wnb 4/17/98
		 */
		protected int tab[]; // for dynamic table sizing */
		protected int size;

		public Hash_Table() {
			size = Compress.HSIZE;
			tab = new int[size];
		}

		public int of(int i) {
			return tab[i];
		}

		public void set(int i, int v) {
			tab[i] = v;
		}

		public int hsize() {
			return size;
		}
		
		public void clear() {
			int i;
			for (i = 0; i < size; i++) {
				tab[i] = -1;
			}
		}
	};
}
