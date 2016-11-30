package frontend.assignments;

import backend.Register;
import backend.TokSeq;

public interface AssignLHS {
	TokSeq assemblyCodeStoring(Register dest);
	TokSeq loadAddr(Register dest);
}
