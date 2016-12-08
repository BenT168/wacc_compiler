package frontend.assignments;

import backend.Register;
import backend.TokSeq;
import backend.tokens.branch.BranchLinkToken;
import backend.tokens.general.PopToken;
import backend.tokens.general.PushToken;
import backend.tokens.move.MovRegToken;
import backend.tokens.operator.AddImmToken;
import backend.tokens.store.StorePreIndexToken;
import frontend.exception.SemanticErrorException;
import frontend.expressions.ExprNode;
import frontend.function.FuncDec;
import frontend.function.ParamList;
import frontend.type.BaseType;
import org.antlr.v4.runtime.ParserRuleContext;
import symboltable.SymbolTable;

import java.util.Iterator;


public class CallFunc extends Assignable {

	private String ident;
	private ArgList args;
	private BaseType retType;
	private FuncDec decNode;

	public CallFunc(FuncDec func, ArgList args) {
		this.retType = func.returnType;
		this.ident = func.getFuncName();
		this.args = args;
		this.decNode = func;
	}

	/*
	Method checks that call is semantically correct
	 */
	public boolean check( SymbolTable st, ParserRuleContext ctx ) {
		//Checks that function of call is already declared
		if (!st.isRecursive(ident)) {
			throw new SemanticErrorException("Function " + ident + " has not been declared", ctx);
		}
		ParamList params = decNode.getParams();
		// Checks that the argument size of call is the same as parameter size of funtion called
		if(!(args.size() == params.size())) {
			throw new SemanticErrorException("The function call to " + ident +
					" has an incorrect number of arguments", ctx);
		}
		//Checks that all arguments have the same type as respective parameters
		if(!(args.compareToParamList(params))) {
			throw new SemanticErrorException("Arguments in call to "
					+ ident + " have incorrect types.", ctx);
		}
		return true;
	}


	public BaseType getType() {
		return retType;
	}

	/* Method generates ARM assembly for a call method
	 */
	@Override
	public TokSeq assemblyCodeGenerating(Register r) {
		//Creates Token sequence to add instructions
		TokSeq seq = new TokSeq();
		// Creates a reverse iterator of args
		Iterator<ExprNode> argExprs = args.reverseIterator();
		int stackOffset = 0;
		// Goes through the iterator and generates ARM assemble code for each argument in arg list
		while (argExprs.hasNext()) {
			ExprNode expr = argExprs.next();
			seq.appendAll(expr.assemblyCodeGenerating(r));
			seq.append(new StorePreIndexToken(Register.sp, r));
			stackOffset += 4;
		}
		// Generates ARM code for the function called in call
		seq.append(new PushToken(Register.R3));
		seq.append(new BranchLinkToken("f_" + ident));
		seq.append(new PopToken(Register.R3));
		seq.append(new AddImmToken(Register.sp, Register.sp, Integer.toString(stackOffset)));
		seq.append(new MovRegToken(r, Register.R0));
		return seq;
	}
}
