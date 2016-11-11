package symbolTable;

import frontEnd.ErrorHandling.IncompatibleTypesException;
import frontEnd.ErrorHandling.UnresolvedExpectationException;
import frontEnd.tree.Type.BaseType;


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
			new IncompatibleTypesException("No return type expected.");
			return false;
		}
		
		if (!hasBeenCalled) {
			hasBeenCalled = true;
			resolved = expectedType != null 
					&& actualType.isCompatible(expectedType);
		} else {
			resolved = resolved && expectedType != null 
					&& actualType.isCompatible(expectedType);
		}
		return resolved;
	}
	
	
	public boolean isResolved() {
		if (!hasBeenCalled && !noReturnExpected)
			throw new UnresolvedExpectationException("No return statement for the function was found");
		return (hasBeenCalled != noReturnExpected) && resolved;
	}
	
}
