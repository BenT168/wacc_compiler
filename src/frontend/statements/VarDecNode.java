package frontend.statements;

import frontend.exception.SemanticErrorException;
import frontend.type.BaseType;
import org.antlr.v4.runtime.ParserRuleContext;

import symboltable.SymbolTable;
import frontend.assignments.Assignable;
import frontend.expressions.Variable;
import frontend.function.FuncDec;
import backend.Register;
import backend.StackAllocator;
import backend.StackLocation;
import backend.TokenSequence;

/**
 * Class to represent variable declaration statements
 * Rule: type ident '=' assign-rhs
 */

public class VarDecNode extends StatNode {

	private Assignable rhsTree;
	private Variable var;

	public VarDecNode(Variable var, Assignable rhsTree) {
		this.var = var;
		this.rhsTree = rhsTree;
	}

	@Override
	public boolean check( SymbolTable st, ParserRuleContext ctx ) {

		// First we check the identifier is unique and it is not a function
		if ( st.containsCurrent(var.getIdent()) && !(st.get(var.getIdent()) instanceof FuncDec) ) {
			throw new SemanticErrorException(
					"A variable with identifier " + var.getIdent() + " was already declared", ctx);
		}

		// We add the current var to the SymbolTable
		st.add(var.getIdent(), var);

		if ( !var.getType().isCompatible(rhsTree.getType()) ) {
			throw new SemanticErrorException(
					"The types of the rhs and lhs of the variable declaration do not match.\n"
							+ "LHS: " + var.getType().toString() + ",\n"
							+ "RHS: " + rhsTree.getType().toString(), ctx);
		}
		return true;
	}

	@Override
	public BaseType getType() {
		return this.var.getType();
	}

	@Override
	public TokenSequence toAssembly(Register register) {
		StackLocation pos = StackAllocator.stackAllocator.allocateOnStack();
		var.setPos(pos);

		TokenSequence rhsSeq = rhsTree.toAssembly(register);
		TokenSequence storeInVariable = var.getType().storeAssembly(register, pos);

		return rhsSeq.appendAll(storeInVariable);
	}


}
