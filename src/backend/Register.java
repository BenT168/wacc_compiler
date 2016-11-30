package backend;

public class Register {
	
	private Integer registerNumber;
	private Register next;
	
	public void setReg(int reg) {
		this.registerNumber = reg;
	}
	
	public Register getNext() {
		if (next == null)
			next = new Register();
		return next;
	}

	public boolean isPreAlloc() {
		return false;
	}

	
	@Override
	public String toString() {
		if (registerNumber == null) {
			throw new IllegalStateException("The register for this object was not set.");
		}
		
		return "r" + registerNumber;
	}
	

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
