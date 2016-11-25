package backend;

import backend.tokens.operator.AddImmToken;
import backend.tokens.operator.SubToken;

import java.util.Stack;


public class StackAllocator {

	public static final StackAllocator stackAllocator = new StackAllocator();

	private static final int WORD_SIZE = 4;
	private static final int MAX_IMM_INT = 1024;

	private Stack<Integer> scopeStack;

	public StackAllocator() {
		scopeStack = new Stack<>();
		scopeStack.push(0);
	}

	public StackLocation allocateOnStack() {
		StackLocation pos = new StackLocation(getCounter());
		incrementCounter();
		return pos;
	}

	private void incrementCounter() {
		scopeStack.push( scopeStack.pop() + 1 );
	}

	public void enterNewScope() {
		scopeStack.push(0);
	}

	public int getCounter() {
		return scopeStack.peek();
	}

	public void exitScope() {
		scopeStack.pop();
	}

	public TokenSequence getInitialisation() {
		return getSequence(new SubToken(null, null, 0));
	}

	public TokenSequence getTermination() {
		return getSequence(new AddImmToken(null, null, null));
	}


	private TokenSequence getSequence(Token sampleToken) {
		TokenSequence seq = new TokenSequence();
		int remaining = getCounter() * WORD_SIZE;
		if (remaining == 0)
			return seq;

		do {
			int current = Math.min(remaining, MAX_IMM_INT);

			if (sampleToken instanceof SubToken)
				seq.append(new SubToken(Register.sp, Register.sp, current));
			else if (sampleToken instanceof AddImmToken)
				seq.append(new AddImmToken(Register.sp, Register.sp, current));

			remaining -= current;
		} while (remaining > 0);

		return seq;
	}
}
