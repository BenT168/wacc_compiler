package frontend;

import backend.Token;
import backend.Register;
import backend.StackAllocator;
import backend.TokenSequence;
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
	public TokenSequence toAssembly(Register register) {
		// functions are stored at the top of the program
		TokenSequence functionDeclarations = new TokenSequence();
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
			functionDeclarations.appendAll(f.toAssembly(register));
		}

		TokenSequence progSeq = progBody.toAssembly(register);

		//Create main label, push lr, and insert allocation sequence
		progSeq.prependAll(
				new TokenSequence(
						new LabelToken("main"),
						new PushToken(Register.lr))
					.appendAll(StackAllocator.stackAllocator.getInitialisation()));

		// after the progSeq has been visited, we retrieve the eventual StackLocation Allocations
		progSeq.appendAll(
				StackAllocator.stackAllocator.getTermination()
					.appendAll(
						new LoadToken(Register.R0, "0"),
						new PopToken(Register.pc),
						new EasyToken(".ltorg")));

		return functionDeclarations.appendAll(progSeq);
	}
}
