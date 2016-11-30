package symboltable;


import frontend.exception.SemanticErrorException;
import frontend.exception.SyntaxErrorException;
import frontend.type.BaseType;

public class Expectation {
	private BaseType expectedType;
	private boolean hasBeenCalled;
	private boolean resolved;
	private boolean noReturnExpected;


	public Expectation(BaseType expected) {
		this.expectedType = expected;
		this.resolved = false;
		this.hasBeenCalled = false;
		noReturnExpected = false;
	}


	public Expectation() {
		this.expectedType = null;
		this.resolved = true;
		this.hasBeenCalled = false;
		noReturnExpected = true;
	}

	public boolean checkType(BaseType actualType) {
		if (noReturnExpected) {
			hasBeenCalled = true;
			new SemanticErrorException("No return type expected.");
			return false;
		}

		if (!hasBeenCalled) {
			hasBeenCalled = true;
			resolved = expectedType != null
					&& actualType.isCompatible(expectedType);
		} else {
			// after the first call, `resolved` depends on its previous value
			resolved = resolved
					&& expectedType != null
					&& actualType.isCompatible(expectedType);
		}
		return resolved;
	}

	public boolean isResolved() {
		// HACK: if a return statement was called but none was found, the UnresolvedExpectationException is thrown rather that only created.
		if (!hasBeenCalled && !noReturnExpected)
			throw new SyntaxErrorException("No return statement for the function was found", null);
		// only one of hasBeenCalled and noReturnExpected should be true
		return (hasBeenCalled != noReturnExpected) && resolved;
	}

}
