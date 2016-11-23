package backend.tokens.general;

import backend.InstrToken;

public class CommentToken extends InstrToken {
	
	private String comment;
	
	public CommentToken(String comment) {
		this.comment = comment;
	}
	
	@Override
	public String toString() {
		return "@" + comment;
	}
}
