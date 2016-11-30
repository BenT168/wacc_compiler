package frontend.function;

import backend.Register;
import backend.StackPos;
import backend.TokSeq;
import frontend.Tree;
import frontend.exception.SemanticErrorException;
import frontend.type.BaseType;
import org.antlr.v4.runtime.ParserRuleContext;
import symboltable.SymbolTable;

import java.util.ArrayList;
import java.util.Iterator;

public class ParamList extends Tree implements Iterable<Param>{
	ArrayList<Param> params;

	public ParamList() {
		this.params = new ArrayList<>();
	}

	public void add(Param pn) {
		params.add(pn);
	}

	public Param get(int i) {
		return params.get(i);
	}

	public int size() {
		return params.size();
	}

	@Override
	public boolean equals(Object other) {
		if(!(other instanceof ParamList))
			return false;

		ParamList pln = (ParamList) other;

		if(pln.size() != this.size())
			return false;

		for (int i = 0; i < this.size(); i++) {
			if ( !this.get(i).equals(pln.get(i)) ) {
				return false;
			}
		}
		return true;
	}

	@Override
	public Iterator<Param> iterator() {
		return params.iterator();
	}

	@Override
	public boolean check(SymbolTable st, ParserRuleContext ctx) {
		ArrayList<String> paramIdents = new ArrayList<String>();

		//Check whether there are duplicate arguments
		for (Param param : params) {
			if (paramIdents.contains(param.getIdent())) {
				throw new SemanticErrorException(
						"A variable with identifier " + param.getIdent() + " was already declared", ctx);
			}
		}
		return true;
	}

	@Override
	public BaseType getType() {
		throw new UnsupportedOperationException("ParamList has no type.");
	}

	public void allocateParamsOnStack() {
		for (int i = 0; i < this.size(); i++) {
			this.get(i).setPos(new StackPos(i + 1, Register.R3));
		}
	}

	@Override
	public TokSeq assemblyCodeGenerating(Register register) {
		throw new UnsupportedOperationException(
				"ParamList does not implement assemblyCodeGenerating."
				+ "It only gives parameters a stack position through "
				+ "its allocatePatamsOnStack() method");
	}

}
