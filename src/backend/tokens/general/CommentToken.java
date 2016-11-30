package backend.tokens.general;

import backend.Token;

public class CommentToken extends Token {
	
	private String comment;
	
	public CommentToken(String comment) {
		this.comment = comment;
	}
	
	@Override
	public String toString() {
		return "@" + comment;
	}
}
