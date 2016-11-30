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

	
	public TokSeq() {
		tokens = new ArrayDeque<>();
		this.m = MODE.ALL;
	}

	public TokSeq(Token... tokens) {
		this.tokens = new ArrayDeque<>(Arrays.asList(tokens));
	}
	
	public TokSeq(TokSeq... sequences) {
		tokens = new ArrayDeque<>();
		
		for (TokSeq seq:sequences) {
			for (Token t:seq) {
				this.append(t);
			}
		}
	}
	
	public TokSeq setUnique() {
		this.m = MODE.UNIQUE;
		return this;
	}
	
	public TokSeq setAcceptAll() {
		this.m = MODE.ALL;
		return this;
	}
	
	public TokSeq append(Token t) {
		if ( t == null || 
				this.m == MODE.UNIQUE 
				&& tokens.contains(t))
			return this;
		
		tokens.addLast(t);
		return this;
	}
	
	public TokSeq prepend(Token t) {
		if ( t == null ||
				this.m == MODE.UNIQUE 
				&& tokens.contains(t))
			return this;
			
		tokens.addFirst(t);
		return this;
	}
	
	public TokSeq appendAll(TokSeq ts) {
		if (ts == null)
			return this;
		for (Token t:ts) {
			this.append(t);
		}
		
		return this;
	}
	
	public TokSeq appendAll(Token... ts) {
		for (Token t:ts) {
			this.append(t);
		}
		return this;
	}
	
	public TokSeq prependAll(TokSeq ts) {
		if (ts == null)
			return this;
		Iterator<Token> iter = ts.reverseIterator();
		while(iter.hasNext()) {
			this.prepend(iter.next());
		}
		
		return this;
	}
	
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
	
	@Override
	public String toString() {
		String result = "";
		for (Token t:tokens) {
			result += (t.requiresTab()? "\t" : "") + "\t" + t.toString() + "\n";
		}
		return result;
	}

	public Integer size() {
		return tokens.size();
	}
}
