package frontend.assignments;

import backend.Register;
import backend.TokSeq;

public interface AssignLHS {

	/*
	Interface that deals with assembly generation specifically for assignRHS nodes
	 */

	TokSeq assemblyCodeStoring(Register dest);
	TokSeq loadAddr(Register dest);
}
