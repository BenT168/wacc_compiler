package backend;

public class TokCollector {
	private TokSeq top;
	private TokSeq body;
	private TokSeq bottom;
	
	private RegAlloc allocator;

	/* constructor */
	public TokCollector(TokSeq progToken) {
		this.top = new TokSeq().setUnique();
		this.body = progToken;
		this.bottom = new TokSeq().setUnique();
		this.allocator = new RegAlloc();
	}

	/* returns token sequence of all tokens appended */
	public TokSeq collect() {
		
		for (Token t:body) {
			t.setRegs(allocator);
			top.appendAll(t.toPrepend());
			bottom.appendAll(t.toAppend());
		}

		wrapTopSequence(top);
		TokSeq finalSequence = new TokSeq(top, body, bottom);
		
		return finalSequence;
	}

	/* includes top string into token sequence passed as argument
	 * every assembly program needs this top string */
	private static void wrapTopSequence(TokSeq top) {
		top.prepend(new Token() {
			@Override
			public String toString() {
				return ".data\n";
			}
			
			@Override
			public boolean requiresTab() {
				return false;
			}
		});
	}
}
