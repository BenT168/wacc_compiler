package backend;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Iterator;

public class TokSeq implements Iterable<Token> {
	public static enum MODE {
		ALL, UNIQUE
	}

	public static final TokSeq EMPTY_SEQUENCE = new TokSeq();
	
	private Deque<Token> tokens;
	private MODE m;

	/* constructor */
	public TokSeq() {
		tokens = new ArrayDeque<>();
		this.m = MODE.ALL;
	}

	/* constructor with set of tokens */
	public TokSeq(Token... tokens) {
		this.tokens = new ArrayDeque<>(Arrays.asList(tokens));
	}

	/* constructor with set of token sequences */
	public TokSeq(TokSeq... sequences) {
		tokens = new ArrayDeque<>();
		
		for (TokSeq seq:sequences) {
			for (Token t:seq) {
				this.append(t);
			}
		}
	}

	/* sets mode as 'unique' */
	public TokSeq setUnique() {
		this.m = MODE.UNIQUE;
		return this;
	}

	/* sets mode as 'all' */
	public TokSeq setAcceptAll() {
		this.m = MODE.ALL;
		return this;
	}

	/* returns token sequence with token passed as argument added in at the end */
	public TokSeq append(Token t) {
		if ( t == null || 
				this.m == MODE.UNIQUE 
				&& tokens.contains(t))
			return this;
		
		tokens.addLast(t);
		return this;
	}

	/* returns token sequence with token passed as argument added in at the start */
	public TokSeq prepend(Token t) {
		if ( t == null ||
				this.m == MODE.UNIQUE 
				&& tokens.contains(t))
			return this;
			
		tokens.addFirst(t);
		return this;
	}

	/* returns token sequence with token sequence passed as argument added in at the end */
	public TokSeq appendAll(TokSeq ts) {
		if (ts == null)
			return this;
		for (Token t:ts) {
			this.append(t);
		}
		
		return this;
	}

	/* returns token sequence with set of tokens passed as argument added in at the end */
	public TokSeq appendAll(Token... ts) {
		for (Token t:ts) {
			this.append(t);
		}
		return this;
	}

	/* returns token sequence with token sequence passed as argument added in at the start */
	public TokSeq prependAll(TokSeq ts) {
		if (ts == null)
			return this;
		Iterator<Token> iter = ts.reverseIterator();
		while(iter.hasNext()) {
			this.prepend(iter.next());
		}
		
		return this;
	}

	/* returns token sequence with set of tokens passed as argument added in at the start */
	public TokSeq prependAll(Token... ts) {
		return prependAll(new TokSeq(ts));
	}
	
	public Iterator<Token> reverseIterator() {
		return tokens.descendingIterator();
	}

	@Override
	public Iterator<Token> iterator() {
		return tokens.iterator();
	}

	/* returns output assembly instruction */
	@Override
	public String toString() {
		String result = "";
		for (Token t:tokens) {
			result += (t.requiresTab()? "\t" : "") + "\t" + t.toString() + "\n";
		}
		return result;
	}

	/* size getter */
	public Integer size() {
		return tokens.size();
	}
}
