package frontend.assignments;

import backend.Register;
import backend.TokenSequence;


public interface AssignLhsNode {
	TokenSequence toStoreAssembly(Register dest);
	TokenSequence loadAddress(Register dest);
}
