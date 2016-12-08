package backend;

public class Register {
	
	private Integer registerNumber;
	private Register next;

	/* assigns/updates register number */
	public void setReg(int reg) {
		this.registerNumber = reg;
	}

	/* returns next register if any */
	public Register getNext() {
		if (next == null)
			next = new Register();
		return next;
	}

	/* returns true iff register is pre-allocated */
	public boolean isPreAlloc() {
		return false;
	}

	/* returns string of the form "rN" where N is the register number */
	@Override
	public String toString() {
		if (registerNumber == null) {
			throw new IllegalStateException("The register for this object was not set.");
		}
		
		return "r" + registerNumber;
	}


	/* set of pre-allocated registers
	 * i.e. R0, R1, R2, R3, sp, lr, pc */

	public static final Register R0 = new Register() {
		@Override
		public String toString() {
			return "r0";
		}
		
		@Override
		public boolean isPreAlloc() {
			return true;
		}
		
		@Override
		public Register getNext() {
			return R1;
		}
	};

	public static final Register R1 = new Register() {
		@Override
		public String toString() {
			return "r1";
		}
		
		@Override
		public boolean isPreAlloc() {
			return true;
		}
		
		@Override
		public Register getNext() {
			return R2;
		}
	};

	public static final Register R2 = new Register() {
		@Override
		public String toString() {
			return "r2";
		}
		
		@Override
		public boolean isPreAlloc() {
			return true;
		}
		
		@Override
		public Register getNext() {
			return R3;
		}
	};

	public static final Register R3 = new Register() {
		@Override
		public String toString() {
			return "r3";
		}
		
		@Override
		public boolean isPreAlloc() {
			return true;
		}
		
		@Override
		public Register getNext() {
			throw new UnsupportedOperationException("Can't request a register after R3");
		}
	};
	
	public static final Register sp = new Register() {
		@Override
		public String toString() {
			return "sp";
		}
		
		@Override
		public boolean isPreAlloc() {
			return true;
		}
		
		@Override
		public Register getNext() {
			throw new UnsupportedOperationException("Can't request a register after sp");
		}
	};
	
	public static final Register lr = new Register() {
		@Override
		public String toString() {
			return "lr";
		}
		
		@Override
		public boolean isPreAlloc() {
			return true;
		}
		
		@Override
		public Register getNext() {
			throw new UnsupportedOperationException("Can't request a register after lr");
		}
	};
	
	public static final Register pc = new Register() {
		@Override
		public String toString() {
			return "pc";
		}
		
		@Override
		public boolean isPreAlloc() {
			return true;
		}
		
		@Override
		public Register getNext() {
			throw new UnsupportedOperationException("Can't request a register after pc");
		}
	};

}
