package backend.tokens.free;

import backend.Token;

public class EmptyToken extends Token {
	public EmptyToken() {}

	@Override
	public String toString() {
		return "";
	}
}
