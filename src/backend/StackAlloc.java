package backend;

import backend.tokens.operator.AddImmToken;
import backend.tokens.operator.SubToken;

import java.util.Stack;


public class StackAlloc {

	public static final StackAlloc STACK_ALLOC = new StackAlloc();

	private static final int WORD_SIZE = 4;
	private static final int MAX_IMM_INT = 1024;

	private Stack<Integer> scopeStack;

	public StackAlloc() {
		scopeStack = new Stack<>();
		scopeStack.push(0);
	}

	public StackPos OnStackAlloc() {
		StackPos pos = new StackPos(getCounter());
		incCounter();
		return pos;
	}

	private void incCounter() {
		scopeStack.push( scopeStack.pop() + 1 );
	}

	public int getCounter() {
		return scopeStack.peek();
	}

	public void enterNewScope() {
		scopeStack.push(0);
	}

	public void exitScope() {
		scopeStack.pop();
	}

	public TokSeq getInit() {
		return getSeq(new SubToken(null, null, 0));
	}

	public TokSeq getTerminated() {
		return getSeq(new AddImmToken(null, null, null));
	}


	private TokSeq getSeq(Token sampleToken) {
		TokSeq seq = new TokSeq();
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
