package frontend.function;

import backend.Token;
import org.antlr.v4.runtime.ParserRuleContext;

import symboltable.SymbolTable;
import frontend.Tree;
import frontend.statements.StatNode;
import frontend.type.BaseType;
import backend.Register;
import backend.StackAllocator;
import backend.TokenSequence;
import backend.tokens.general.EasyToken;
import backend.tokens.general.LabelToken;
import backend.tokens.move.MovRegToken;
import backend.tokens.general.PushToken;

/* Represents a Function declaration
 * Contains information of the function name, return type, function parameters,
 * function body and a BoolLeaf that becomes true once the function body has been added.
 */

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
	public TokenSequence toAssembly(Register r) {
		Token label = new LabelToken("f_" + funcName);
		Token push = new PushToken(Register.lr);
		TokenSequence startSequence = new TokenSequence(label, push);
		startSequence.append(new MovRegToken(Register.R3, Register.sp));

		params.allocateParamsOnStack();

		StackAllocator.stackAllocator.enterNewScope();

		TokenSequence body = funcBody.toAssembly(r);
		TokenSequence stackAllocSequence = StackAllocator.stackAllocator.getInitialisation();
		TokenSequence middleSequence =
				new TokenSequence(
						stackAllocSequence,
						body);

		StackAllocator.stackAllocator.exitScope();

		Token ltorg = new EasyToken(".ltorg");
		TokenSequence finalSequence = new TokenSequence(ltorg);

		return startSequence
				.appendAll(middleSequence)
				.appendAll(finalSequence);
	}

}
