package frontend.function;

import backend.TokSeq;
import backend.Token;
import org.antlr.v4.runtime.ParserRuleContext;

import symboltable.SymbolTable;
import frontend.Tree;
import frontend.statements.StatNode;
import frontend.type.BaseType;
import backend.Register;
import backend.StackAlloc;
import backend.tokens.general.EasyToken;
import backend.tokens.general.LabelToken;
import backend.tokens.move.MovRegToken;
import backend.tokens.general.PushToken;

public class FuncDec extends Tree {

	public final BaseType returnType;
	private String funcName;
	private ParamList params;
	private StatNode funcBody;
	private boolean complete;

	public FuncDec(BaseType returnType, String funcName, ParamList params) {
		this(returnType, funcName);
		this.params = params;
		complete = false;
	}

	public FuncDec(BaseType returnType, String funcName) {
		this.returnType = returnType;
		this.funcName = funcName;
		complete = false;
	}

	@Override
	public boolean check(SymbolTable st, ParserRuleContext ctx) {
		return complete;
	}

	@Override
	public BaseType getType() {
		return this.returnType;
	}

	public String getFuncName() {
		return funcName;
	}

	public ParamList getParams() {
		return params;
	}

	public void addFuncBody(StatNode funcBody) {
		this.funcBody = funcBody;
		complete = true;
	}

	@Override
	public TokSeq assemblyCodeGenerating(Register r) {
		Token label = new LabelToken("f_" + funcName);
		Token push = new PushToken(Register.lr);
		TokSeq startSequence = new TokSeq(label, push);
		startSequence.append(new MovRegToken(Register.R3, Register.sp));

		params.allocateParamsOnStack();

		StackAlloc.STACK_ALLOC.enterNewScope();

		TokSeq body = funcBody.assemblyCodeGenerating(r);
		TokSeq stackAllocSequence = StackAlloc.STACK_ALLOC.getInit();
		TokSeq middleSequence =
				new TokSeq(
						stackAllocSequence,
						body);

		StackAlloc.STACK_ALLOC.exitScope();

		Token ltorg = new EasyToken(".ltorg");
		TokSeq finalSequence = new TokSeq(ltorg);

		return startSequence
				.appendAll(middleSequence)
				.appendAll(finalSequence);
	}

}
