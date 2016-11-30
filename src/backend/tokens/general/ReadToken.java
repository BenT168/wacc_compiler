package backend.tokens.general;

import backend.TokSeq;
import backend.Token;
import backend.system.SystemFormatterHandler;
import backend.system.SystemReadHandler;
import backend.tokens.branch.BranchLinkToken;
import frontend.type.BaseType;

public class ReadToken extends Token {
	private BaseType type;

	public ReadToken(BaseType type) {
		this.type = type;
	}

	@Override
	public TokSeq toPrepend() {
		TokSeq pre = new TokSeq();
		if (type == BaseType.INT)
			return pre.append(SystemFormatterHandler.INT_FORMATTER);
		if (type == BaseType.CHAR)
			return pre.append(SystemFormatterHandler.CHAR_FORMATTER);
		else
			throw new IllegalArgumentException("Can only read ints or chars");
	}

	@Override
	public TokSeq toAppend() {
		TokSeq post = new TokSeq();
		if (type == BaseType.INT)
			return post.append(SystemReadHandler.READ_INT);
		if (type == BaseType.CHAR)
			return post.append(SystemReadHandler.READ_CHAR);
		else
			throw new IllegalArgumentException("Can only read ints or chars");
	}

	@Override
	public String toString() {
		if (type == BaseType.INT)
			return new BranchLinkToken("p_read_int").toString();
		if (type == BaseType.CHAR)
			return new BranchLinkToken("p_read_char").toString();
		else
			throw new IllegalArgumentException("Can only read ints or chars");
	}
}
