package com.cnframe.modules.utils.compress;

public class Decompressor extends Comp_Base {
	
	private int size;
	private Code_Table tab_prefix;
	private Suffix_Table tab_suffix;
	private De_Stack de_stack;
	
	public Decompressor(Input_Buffer in, Output_Buffer out) {
		super(in, out);
		/* Check the magic number */
		if (((Input.getbyte() & 0xFF) != (Compress.magic_header[0] & 0xFF))
		|| ((Input.getbyte() & 0xFF) != (Compress.magic_header[1] & 0xFF))) {
			System.err.println("stdin: not in compressed format");
			// System.exit(1);
		}
		maxbits = Input.getbyte(); /* set -b from file */
		block_compress = maxbits & Compress.BLOCK_MASK;
		maxbits &= Compress.BIT_MASK;
		maxmaxcode = 1 << maxbits;
		if (maxbits > Compress.BITS) {
			System.err.println("stdin: compressed with " + maxbits+ " bits, can only handle " + Compress.BITS + " bits");
			// System.exit(1);
		}
		n_bits = Compress.INIT_BITS;
		maxcode = MAXCODE();
		offset = 0;
		size = 0;
		clear_flg = 0;
		free_ent = ((block_compress != 0) ? Compress.FIRST : 256);
		tab_prefix = new Code_Table();
		tab_suffix = new Suffix_Table();
		de_stack = new De_Stack();
		/*
		 * 
		 * As above, initialize the first 256 entries in the table.
		 */
		tab_prefix.clear(256);
		tab_suffix.init(256);
	}

	public void decompress() {
		int finchar;
		int code, oldcode, incode;
		finchar = oldcode = getcode();
		if (oldcode == -1) /* EOF already? */
			return; /* Get out of here */
		Output.putbyte((byte) finchar); /* first code must be 8 bits = byte */
		while ((code = getcode()) > -1) {
			if ((code == Compress.CLEAR) && (block_compress != 0)) {
				tab_prefix.clear(256);
				clear_flg = 1;
				free_ent = Compress.FIRST - 1;
				if ((code = getcode()) == -1) /* O, untimely death! */
					break;
			}
			incode = code;
			/*
			 * 
			 * Special case for KwKwK string.
			 */
			if (code >= free_ent) {
				de_stack.push((byte) finchar);
				code = oldcode;
			}
			/*
			 * 
			 * Generate output characters in reverse order
			 */
			while (code >= 256) {
				de_stack.push(tab_suffix.of(code));
				code = tab_prefix.of(code);
			}
			de_stack.push((byte) (finchar = tab_suffix.of(code)));
			/*
			 * 
			 * And put them out in forward order
			 */
			do
				Output.putbyte(de_stack.pop());
			while (!de_stack.is_empty());
			/*
			 * 
			 * Generate the new entry.
			 */
			if ((code = free_ent) < maxmaxcode) {
				tab_prefix.set(code, oldcode);
				tab_suffix.set(code, (byte) finchar);
				free_ent = code + 1;
			}
			/*
			 * 
			 * Remember previous code.
			 */
			oldcode = incode;
		}
	}

	/*
	 * 
	 * Read one code from the standard input. If EOF, return -1. Inputs: stdin
	 * 
	 * Outputs: code or -1 is returned.
	 */
	private int getcode() {
		int code;
		int r_off, bits;
		int bp = 0;
		if (clear_flg > 0 || offset >= size || free_ent > maxcode) {
			/*
			 * 
			 * If the next entry will be too big for the current code size, then
			 * 
			 * we must increase the size. This implies reading a new buffer
			 * 
			 * full, too.
			 */
			if (free_ent > maxcode) {
				n_bits++;
				if (n_bits == maxbits)
					maxcode = maxmaxcode; /* won't get any bigger now */
				else
					maxcode = MAXCODE();
			}
			if (clear_flg > 0) {
				n_bits = Compress.INIT_BITS;
				maxcode = MAXCODE();
				clear_flg = 0;
			}
			size = Input.readbytes(buf, n_bits);
			if (size <= 0)
				return -1; /* end of file */
			offset = 0;
			/* Round size down to integral number of codes */
			size = (size << 3) - (n_bits - 1);
		}
		r_off = offset;
		bits = n_bits;
		/*
		 * 
		 * Get to the first byte.
		 */
		bp += (r_off >> 3);
		r_off &= 7;
		/* Get first part (low order bits) */
		code = ((buf[bp++] >> r_off) & Compress.rmask[8 - r_off]) & 0xff;
		bits -= (8 - r_off);
		r_off = 8 - r_off; /* now, offset into code word */
		/* Get any 8 bit parts in the middle (<=1 for up to 16 bits). */
		if (bits >= 8) {
			code |= (buf[bp++] & 0xff) << r_off;
			r_off += 8;
			bits -= 8;
		}
		/* high order bits. */
		// code |= (buf[bp] & Compress.rmask[bits]) << r_off; // kmd
		// Don McCauley/kmd - IBM 02/26/98
		if (bits > 0)
			code |= (buf[bp] & Compress.rmask[bits]) << r_off;
		offset += n_bits;
		return code;
	}

	/** ************************************************************** */

	final class De_Stack { // moved 4/15/98 dm/kmd

		/*
		 * 
		 * Use protected instead of private to allow access by parent class of
		 * 
		 * inner class. wnb 4/17/98
		 */
		protected byte tab[];
		protected int index;
		public De_Stack() {
			tab = new byte[Compress.STACK_SZ];
			index = 0;
		}

		public void push(byte c) {
			tab[index++] = c;
		}

		public byte pop() {
			index--;
			return tab[index];
		}

		public boolean is_empty() {
			return (index == 0);
		}

	};

	/** ************************************************************** */

	final class Suffix_Table { // moved 4/15/98 dm/kmd
		/*
		 * 
		 * Use protected instead of private to allow access by parent class of
		 * 
		 * inner class. wnb 4/17/98
		 */

		protected byte tab[];

		public Suffix_Table() {
			tab = new byte[Compress.SUFFIX_TAB_SZ];
		}

		public byte of(int i) {
			return tab[i];
		}

		public void set(int i, byte v) {
			tab[i] = v;
		}

		public void init(int size) {
			int code;
			for (code = 0; code < size; code++) {
				tab[code] = (byte) code;
			}
		}
	};
}
