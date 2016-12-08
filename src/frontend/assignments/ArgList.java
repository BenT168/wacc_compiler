package frontend.assignments;

import backend.Register;
import backend.TokSeq;
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


public class ArgList extends Tree implements Iterable<ExprNode>{

	private Deque<ExprNode> args;

	public ArgList() {
		this.args = new ArrayDeque<>();
	}


	/*
	Adding exprnode to arglist
	*/
	public void add(ExprNode expr) {
		args.add(expr);
	}

	/*
	Method returns arglist size
	 */
	public int size() {
		return args.size();
	}

	/*
	For comparisons during function calls
	 */
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


	/*
	Method checks that the types of the arg lists are the same
	 */
	@Override
	public boolean equals(Object other) {
		//Checks that the arg list is actually of type ArgList
		if(!(other instanceof ArgList))
			return false;

		//Checks that arg lists are the same size
		ArgList aln = (ArgList) other;
		if(aln.size() != args.size())
			return false;

		//Iterates through list of exprnode and checks the type
		Iterator<ExprNode> iter = aln.iterator();
		for (ExprNode e1:this) {
			ExprNode e2 = iter.next();
			if (!e1.getType().isCompatible(e2.getType()))
				return false;
		}
		return true;
	}

	/*
	Methods to generate Iterators for arglist
	 */
	@Override
	public Iterator<ExprNode> iterator() {
		return args.iterator();
	}

	public Iterator<ExprNode> reverseIterator() {
		return args.descendingIterator();
	}

	/*
	Method should never be called on arg list
	 */
	@Override
	public boolean check(SymbolTable st, ParserRuleContext ctx) {
		return true;
	}

	/*
	Arglist has no type
	 */
	@Override
	public BaseType getType() {
		throw new UnsupportedOperationException("ArgList has no type.");
	}

	/*
	Arglist should not generate code for ARM
	 */
	@Override
	public TokSeq assemblyCodeGenerating(Register register) {
		throw new UnsupportedOperationException(
				"ArgList doesn't implement assemblyCodeGenerating directly."
				+ "It is taken care of by CallFunc through this class iterator.");
	}

}
