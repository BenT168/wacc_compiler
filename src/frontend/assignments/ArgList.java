package frontend.assignments;

import backend.Register;
import backend.TokenSequence;
import frontend.expressions.ExprNode;
import frontend.function.ParamList;
import frontend.function.Param;
import frontend.Tree;
import frontend.type.BaseType;
import org.antlr.v4.runtime.ParserRuleContext;
import symboltable.SymbolTable;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

/* Represents a list of arguments for the call to a function
 * Used to perform checks on provided arguments with the parameter list
 * declared for the given functions
 */

public class ArgList extends Tree implements Iterable<ExprNode>{
	Deque<ExprNode> args;

	public ArgList() {
		this.args = new ArrayDeque<>();
	}

	public void add(ExprNode expr) {
		args.add(expr);
	}

	public int size() {
		return args.size();
	}

	//For comparisons during function calls
	public boolean compareToParamList(ParamList params) {
		Iterator<ExprNode> argIter = iterator();
		Iterator<Param> paramIter = params.iterator();
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
		if(!(other instanceof ArgList))
			return false;

		ArgList aln = (ArgList) other;
		if(aln.size() != args.size())
			return false;

		Iterator<ExprNode> iter = aln.iterator();
		for (ExprNode e1:this) {
			ExprNode e2 = iter.next();
			if (!e1.getType().isCompatible(e2.getType()))
				return false;
		}

		return true;
	}

	@Override
	public Iterator<ExprNode> iterator() {
		return args.iterator();
	}

	public Iterator<ExprNode> reverseIterator() {
		return args.descendingIterator();
	}

	@Override
	public boolean check(SymbolTable st, ParserRuleContext ctx) {
		return true;
	}

	@Override
	public BaseType getType() {
		throw new UnsupportedOperationException("ArgList has no type.");
	}

	@Override
	public TokenSequence toAssembly(Register register) {
		throw new UnsupportedOperationException(
				"ArgList doesn't implement toAssembly directly."
				+ "It is taken care of by CallFunc through this class iterator.");
	}

}
