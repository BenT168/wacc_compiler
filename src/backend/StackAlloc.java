package backend;

import backend.tokens.operator.AddImmToken;
import backend.tokens.operator.SubToken;

import java.util.Stack;


public class StackAlloc {

	public static final StackAlloc STACK_ALLOC = new StackAlloc();

	private static final int WORD_SIZE = 4;
	private static final int MAX_IMM_INT = 1024;

	private Stack<Integer> scopeStack;

	/* constructor */
	public StackAlloc() {
		scopeStack = new Stack<>();
		scopeStack.push(0);
	}

	/* returns stack position to allocate to */
	public StackPos OnStackAlloc() {
		StackPos pos = new StackPos(getCounter());
		incCounter();
		return pos;
	}

	/* increments counter */
	private void incCounter() {
		scopeStack.push(scopeStack.pop() + 1);
	}

	/* returns current counter */
	public int getCounter() {
		return scopeStack.peek();
	}

	/* to be called every time a new scope is entered */
	public void enterNewScope() {
		scopeStack.push(0);
	}

	/* to be called every time a scope is exited */
	public void exitScope() {
		scopeStack.pop();
	}

	/* returns initialised token sequence */
	public TokSeq getInit() {
		return getSeq(new SubToken(null, null, 0));
	}

	/* returns terminated token sequence */
	public TokSeq getTerminated() {
		return getSeq(new AddImmToken(null, null, null));
	}

	/* returns a sequence created starting from the token passed as argument onwards */
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
