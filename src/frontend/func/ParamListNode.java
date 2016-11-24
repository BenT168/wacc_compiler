package frontend.func;

import WACCExceptions.NotUniqueIdentifierException;
import backend.Register;
import backend.StackPosition;
import backend.TokenSequence;
import frontend.tree;
import frontend.type.WACCType;
import org.antlr.v4.runtime.ParserRuleContext;
import symboltable.SymbolTable;

import java.util.ArrayList;
import java.util.Iterator;

/* Represents a list of parameter that can be compared to check equality
 * and checks for functionality 
 */

public class ParamListNode extends tree implements Iterable<ParamNode>{
	ArrayList<ParamNode> params;
	
	public ParamListNode() {
		this.params = new ArrayList<>();
	}
	
	public void add(ParamNode pn) {
		params.add(pn);
	}
	
	public ParamNode get(int i) {
		return params.get(i);
	}
	
	public int size() {
		return params.size();
	}
	
	@Override
	public boolean equals(Object other) {
		if(!(other instanceof ParamListNode))
			return false;
		
		ParamListNode pln = (ParamListNode) other;
		
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
	public Iterator<ParamNode> iterator() {
		return params.iterator();
	}

	@Override
	public boolean check(SymbolTable st, ParserRuleContext ctx) {
		ArrayList<String> paramIdents = new ArrayList<String>();
		
		//Check whether there are duplicate arguments
		for (ParamNode param : params) {
			if (paramIdents.contains(param.getIdent())) {
				new NotUniqueIdentifierException(
						"A variable with identifier " + param.getIdent() + " was already declared", ctx);
				return false;
			}	
		}
		return true;
	}

	@Override
	public WACCType getType() {
		throw new UnsupportedOperationException("ParamListNode has no type.");
	}

	public void allocateParamsOnStack() {
		for (int i = 0; i < this.size(); i++) {
			this.get(i).setPos(new StackPosition(i + 1, Register.R3));
		}
	}

	@Override
	public TokenSequence toAssembly(Register register) {
		throw new UnsupportedOperationException(
				"ParamListNode does not implement toAssembly."
				+ "It only gives parameters a stack position through "
				+ "its allocatePatamsOnStack() method");
	}

}
