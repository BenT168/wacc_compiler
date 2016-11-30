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
		this.decNode = func; /* Not sure if needed */
	}

	public boolean check( SymbolTable st, ParserRuleContext ctx ) {
		if (!st.isRecursive(ident)) {
			throw new SemanticErrorException("Function " + ident + " has not been delcared", ctx);
		}
		ParamList params = decNode.getParams();
		if(!(args.size() == params.size())) {
			throw new SemanticErrorException("The function call to " + ident +
					" has an incorrect number of arguments", ctx);

		}
		if(!(args.compareToParamList(params))) {
			throw new SemanticErrorException("Arguments in call to "
					+ ident + " have incorrect types.", ctx);
		}
		return true;
	}

	public BaseType getType() {
		return retType;
	}

	@Override
	public TokSeq assemblyCodeGenerating(Register r) {
		TokSeq seq = new TokSeq();
		Iterator<ExprNode> argExprs = args.reverseIterator();
		int stackOffset = 0;
		while (argExprs.hasNext()) {
			ExprNode expr = argExprs.next();
			seq.appendAll(expr.assemblyCodeGenerating(r));
			seq.append(new StorePreIndexToken(Register.sp, r));
			stackOffset += 4;
		}
		seq.append(new PushToken(Register.R3));
		seq.append(new BranchLinkToken("f_" + ident));
		seq.append(new PopToken(Register.R3));
		seq.append(new AddImmToken(Register.sp, Register.sp, Integer.toString(stackOffset)));
		seq.append(new MovRegToken(r, Register.R0));
		return seq;
	}
}
