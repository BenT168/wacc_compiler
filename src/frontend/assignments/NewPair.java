package frontend.assignments;

import backend.TokSeq;
import frontend.type.BaseType;
import org.antlr.v4.runtime.ParserRuleContext;

import symboltable.SymbolTable;
import frontend.expressions.ExprNode;
import frontend.type.PairType;
import backend.Register;
import backend.tokens.move.MovRegToken;
import backend.tokens.store.StoreToken;

public class NewPair extends Assignable {

	private ExprNode fst;
	private ExprNode snd;
	private final int PAIR_SIZE = 4;

	public NewPair(ExprNode fst, ExprNode snd) {
		this.fst = fst;
		this.snd = snd;
	}

	@Override
	public boolean check(SymbolTable st, ParserRuleContext ctx) {
		return true;
	}

	@Override
	public BaseType getType() {
		return new PairType(fst.getType(), snd.getType());
	}

	@Override
	public TokSeq assemblyCodeGenerating(Register dest) {

		TokSeq firstAlloc = mallocSequence(2, PAIR_SIZE);
		MovRegToken movReg1 = new MovRegToken(dest, Register.R0);

		TokSeq fstExp = fst.assemblyCodeGenerating(dest.getNext());
		TokSeq secondAlloc = mallocSequence(1, fst.getType().getVarSize());
		TokSeq storeFst = storeValAndAdd(fst, dest, 0);

		TokSeq sndExp = snd.assemblyCodeGenerating(dest.getNext());
		TokSeq thirdAlloc = mallocSequence(1, snd.getType().getVarSize());
		TokSeq storeSnd = storeValAndAdd(snd, dest, 4);

		firstAlloc
			.append(movReg1)
			.appendAll(fstExp)
			.appendAll(secondAlloc)
			.appendAll(storeFst)
			.appendAll(sndExp)
			.appendAll(thirdAlloc)
			.appendAll(storeSnd);

		return firstAlloc;
	}

	private TokSeq storeValAndAdd(ExprNode expr, Register dest, int offset) {
		StoreToken store = expr.getType().storeAssembly(Register.R0, dest.getNext());
		StoreToken storeAdd = new StoreToken(dest, Register.R0,  offset);
		return new TokSeq(store, storeAdd);
	}
}
