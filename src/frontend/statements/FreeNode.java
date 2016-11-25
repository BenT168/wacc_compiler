package frontend.statements;

import frontend.exception.SemanticErrorException;
import org.antlr.v4.runtime.ParserRuleContext;

import symboltable.SymbolTable;
import frontend.expressions.ExprNode;
import frontend.expressions.Variable;
import frontend.type.ArrayType;
import frontend.type.PairType;
import backend.Register;
import backend.TokenSequence;
import backend.tokens.free.FreeArrayToken;
import backend.tokens.free.FreePairToken;
import backend.tokens.load.LoadAddressToken;
import backend.tokens.move.MovRegToken;

/**
 * Class to represent free statements used to free array or pair variables
 * Rule: 'free' expressions
 *
 */

public class FreeNode extends StatNode {

	private ExprNode en;

	public FreeNode(ExprNode en) {
		this.en = en;
	}

	public boolean check( SymbolTable st, ParserRuleContext ctx ) {
		if (en instanceof Variable) {
			Variable identN = (Variable) en;
			String ident = identN.getIdent();
			if (st.containsRecursive(ident)) {
				if (!(st.get(ident).getType() instanceof ArrayType
					|| st.get(ident).getType() instanceof PairType)) {
					throw new SemanticErrorException("Can only free an Array or a PairLeaf", ctx);
				}
			}
			return true;
		}
		throw new SemanticErrorException("'Free' must be passed an identifier to a variable", ctx);
	}

	public TokenSequence toAssembly(Register register) {
		TokenSequence freeStat = new TokenSequence();
		if (en.getType() instanceof ArrayType) {
			freeStat.appendAll(new TokenSequence(
				new LoadAddressToken(register, Register.sp),
				new MovRegToken(Register.R0, register),
				new FreeArrayToken()));
		} else {
			freeStat.appendAll(new TokenSequence(
				new LoadAddressToken(register, Register.sp),
				new MovRegToken(Register.R0, register),
				new FreePairToken()));
		}
		return freeStat;
	}

}
