package frontend;

import backend.TokSeq;
import backend.Token;
import backend.Register;
import backend.StackAlloc;
import backend.tokens.general.EasyToken;
import backend.tokens.general.LabelToken;
import backend.tokens.general.PopToken;
import backend.tokens.general.PushToken;
import backend.tokens.load.LoadToken;
import frontend.function.FuncDec;
import frontend.statements.StatNode;
import frontend.type.BaseType;
import org.antlr.v4.runtime.ParserRuleContext;
import symboltable.SymbolTable;

import java.util.List;

public class Program extends Tree {

	private List<FuncDec> functions;
	private StatNode progBody;

	public Program(List<FuncDec> functions, StatNode progBody) {
		this.functions = functions;
		this.progBody = progBody;
	}

	@Override
	public boolean check(SymbolTable st, ParserRuleContext ctx) {
		return true;
	}

	@Override
	public BaseType getType() {
		throw new UnsupportedOperationException("getType() is not supported in Program");
	}

	@Override
	public TokSeq assemblyCodeGenerating(Register register) {
		// functions are stored at the top of the program
		TokSeq functionDeclarations = new TokSeq();
		functionDeclarations.append(
				new Token() {
							@Override
							public String toString() { return ".text\n\n\t.global main"; }

							@Override
							public boolean requiresTab() {
								return false;
							}
				}
				);

		for (FuncDec f:functions) {
			functionDeclarations.appendAll(f.assemblyCodeGenerating(register));
		}

		TokSeq progSeq = progBody.assemblyCodeGenerating(register);


		progSeq.prependAll(
				new TokSeq(
						new LabelToken("main"),
						new PushToken(Register.lr))
					.appendAll(StackAlloc.STACK_ALLOC.getInit()));

		progSeq.appendAll(
				StackAlloc.STACK_ALLOC.getTerminated()
					.appendAll(
						new LoadToken(Register.R0, "0"),
						new PopToken(Register.pc),
						new EasyToken(".ltorg")));

		return functionDeclarations.appendAll(progSeq);
	}
}
