package frontend.statements;

import backend.StackPos;
import backend.TokSeq;
import frontend.exception.SemanticErrorException;
import frontend.type.BaseType;
import org.antlr.v4.runtime.ParserRuleContext;

import symboltable.SymbolTable;
import frontend.assignments.Assignable;
import frontend.expressions.Variable;
import frontend.function.FuncDec;
import backend.Register;
import backend.StackAlloc;


public class VarDecNode extends StatNode {

	private Assignable rhsTree;
	private Variable var;

	public VarDecNode(Variable var, Assignable rhsTree) {
		this.var = var;
		this.rhsTree = rhsTree;
	}

	@Override
	public boolean check( SymbolTable st, ParserRuleContext ctx ) {

		if ( st.isContained(var.getIdent()) && !(st.get(var.getIdent()) instanceof FuncDec) ) {
			throw new SemanticErrorException(
					"A variable with identifier " + var.getIdent() + " was already declared", ctx);
		}

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
	public TokSeq assemblyCodeGenerating(Register register) {
		StackPos pos = StackAlloc.STACK_ALLOC.OnStackAlloc();
		var.setPos(pos);

		TokSeq rhsSeq = rhsTree.assemblyCodeGenerating(register);
		TokSeq storeInVariable = var.getType().storeAssembly(register, pos);

		return rhsSeq.appendAll(storeInVariable);
	}


}
