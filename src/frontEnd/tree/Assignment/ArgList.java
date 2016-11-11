package frontEnd.tree.Assignment;

import frontEnd.tree.ASTTree;
import frontEnd.tree.Expr.Expr;
import frontEnd.tree.Parameter.ParamList;
import frontEnd.tree.Parameter.Parameter;
import frontEnd.tree.Type.BaseType;
import org.antlr.v4.runtime.ParserRuleContext;
import symbolTable.SymbolTable;

import java.util.ArrayList;
import java.util.Iterator;

/* Represents a list of arguments for the call to a function
 * Used to perform checks on provided arguments with the parameter list
 * declared for the given functions
 */

public class ArgList extends ASTTree implements Iterable<Expr>{

	ArrayList<Expr> args;
	
	public ArgList() {
		this.args = new ArrayList<>();
	}
	
	public void add(Expr expr) {
		args.add(expr);
	}
	
	public Expr get(int i) {
		return args.get(i);
	}
	
	public int size() {
		return args.size();
	}
	
	//For comparisons during function calls
	public boolean compareToParamList(ParamList params) {
		Iterator<Expr> argIter = iterator();
		Iterator<Parameter> paramIter = params.iterator();
		while(argIter.hasNext()) {
			BaseType argType = argIter.next().getType();
			BaseType paramType = paramIter.next().getType();
			if (!argType.isCompatible(paramType)) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public boolean equals(Object other) {
		//Checks that types of arg lists are the same
		if(!(other instanceof ArgList)) {
			return false;
		}
			ArgList aln = (ArgList) other;
			if(aln.size() != args.size()) {
				return false;
			}
			for (int i = 0; i < this.size(); i++) {
				BaseType otherType = aln.get(i).getType();
				if ( !this.get(i).getType().isCompatible((otherType)) ) {
					return false;
				}
			}
			return true;
	}

	@Override
	public Iterator<Expr> iterator() {
		return args.iterator();
	}
        @Override
	public boolean check(SymbolTable st, ParserRuleContext ctx) {
		return true;
	}

	@Override
	public BaseType getType() {
		throw new UnsupportedOperationException("ArgListNode has no type.");
	}



}
