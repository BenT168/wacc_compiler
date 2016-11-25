package backend;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Iterator;

public class TokenSequence implements Iterable<Token> {
	public static enum MODE {
		ALL, UNIQUE
	}

	public static final TokenSequence EMPTY_SEQUENCE = new TokenSequence();
	
	private Deque<Token> tokens;
	private MODE m;

	
	public TokenSequence() {
		tokens = new ArrayDeque<>();
		this.m = MODE.ALL;
	}

	public TokenSequence(Token... tokens) {
		this.tokens = new ArrayDeque<>(Arrays.asList(tokens));
	}
	
	public TokenSequence(TokenSequence... sequences) {
		tokens = new ArrayDeque<>();
		
		for (TokenSequence seq:sequences) {
			for (Token t:seq) {
				this.append(t);
			}
		}
	}
	
	public TokenSequence setUnique() {
		this.m = MODE.UNIQUE;
		return this;
	}
	
	public TokenSequence setAcceptAll() {
		this.m = MODE.ALL;
		return this;
	}
	
	public TokenSequence append(Token t) {
		if ( t == null || 
				this.m == MODE.UNIQUE 
				&& tokens.contains(t))
			return this;
		
		tokens.addLast(t);
		return this;
	}
	
	public TokenSequence prepend(Token t) {
		if ( t == null ||
				this.m == MODE.UNIQUE 
				&& tokens.contains(t))
			return this;
			
		tokens.addFirst(t);
		return this;
	}
	
	public TokenSequence appendAll(TokenSequence ts) {
		if (ts == null)
			return this;
		for (Token t:ts) {
			this.append(t);
		}
		
		return this;
	}
	
	public TokenSequence appendAll(Token... ts) {
		for (Token t:ts) {
			this.append(t);
		}
		return this;
	}
	
	public TokenSequence prependAll(TokenSequence ts) {
		if (ts == null)
			return this;
		Iterator<Token> iter = ts.reverseIterator();
		while(iter.hasNext()) {
			this.prepend(iter.next());
		}
		
		return this;
	}
	
	public TokenSequence prependAll(Token... ts) {
		return prependAll(new TokenSequence(ts));
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
