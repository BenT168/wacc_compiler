package frontend.assignments;

import backend.Register;
import backend.TokenSequence;


public interface AssignLHS {
	TokenSequence toStoreAssembly(Register dest);
	TokenSequence loadAddress(Register dest);
}
