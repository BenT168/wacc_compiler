package frontend.assignments;

import java.util.ArrayList;

import frontend.exception.SemanticErrorException;
import frontend.type.BaseType;
import org.antlr.v4.runtime.ParserRuleContext;

import symboltable.SymbolTable;
import frontend.expressions.ExprNode;
import frontend.expressions.Variable;
import frontend.type.ArrayType;
import backend.Register;
import backend.TokenSequence;
import backend.tokens.operator.AddImmToken;
import backend.tokens.operator.AddToken;
import backend.tokens.check.CheckArrayBoundsToken;
import backend.tokens.load.LoadAddressToken;
import backend.tokens.move.MovRegToken;

public class ArrayElem extends ExprNode implements AssignLHS {

	Variable var;
	ArrayList<ExprNode> locations;
	ArrayType arrayType;

	public ArrayElem(ArrayList<ExprNode> locations, Variable var) {
		this.locations = locations;
		this.arrayType = (ArrayType) var.getType();
		this.var = var;
	}

	@Override
	public boolean check( SymbolTable st, ParserRuleContext ctx ) {
		for(ExprNode pos : locations) {
			if(!(pos.getType() == BaseType.INT)) {
				el.record(new SemanticErrorException("Array position can only be found using an IntLeaf", ctx));
				return false;
			}
		}

		return true;
	}

	@Override
	public BaseType getType() {
		BaseType type = arrayType;
		for (ExprNode location: locations) {
			type = ((ArrayType) type).getBaseType();
		}
		return type;
	}

	@Override
	public TokenSequence toStoreAssembly(Register dest) {
		return arrayElemCommonAssembly(dest.getNext())
				.append(arrayType.getBaseType().storeAssembly(dest.getNext(), dest));
	}


	public TokenSequence toAssembly(Register dest) {
		TokenSequence out = new TokenSequence();
		TokenSequence arrayAccess = arrayElemCommonAssembly(dest);
		LoadAddressToken loadResult = arrayType.getBaseType().loadAssembly(dest, dest);
		out
		.appendAll(arrayAccess)
		.append(loadResult);

		return out;
	}

	private TokenSequence arrayElemCommonAssembly(Register dest) {
		int posOnStack = var.getPosition().getStackIndex();

		TokenSequence out = new TokenSequence();

		AddImmToken addTok = new AddImmToken(dest, Register.sp, Integer.toString(posOnStack));
		out.append(addTok);

		for (ExprNode expr : locations) {
			TokenSequence exprSeq = expr.toAssembly(dest.getNext());

			exprSeq.appendAll( new TokenSequence(
					new LoadAddressToken(dest, dest),
					new MovRegToken(Register.R0, dest.getNext()),
					new MovRegToken(Register.R1, dest),
					new CheckArrayBoundsToken(),
					new AddImmToken(dest, dest, Integer.toString(4))));

			if(arrayType.getBaseType().getVarSize() == BaseType.INT.getVarSize()) {
				exprSeq.append(new AddToken(dest, dest, dest.getNext(), "LSL #2"));
			} else {
				exprSeq.append(new AddToken(dest, dest, dest.getNext()));
			}
			out.appendAll(exprSeq);
		}

		return out;
	}


	public String getIdent() {
		return var.getIdent();
	}

	@Override
	public int weight() {
		int max = 0;
		for (ExprNode e:locations) {
			int exprWeight = e.weight();
			max = exprWeight > max ? exprWeight : max;
		}

		return max;
	}

	@Override
	public TokenSequence loadAddress(Register dest) {
		TokenSequence seq = var.toAssembly(dest);
		TokenSequence arrayIndex = this.locations.get(0).toAssembly(dest.getNext());
		seq.appendAll(arrayIndex);
		seq.appendAll(
				new LoadAddressToken(dest, dest),
				new AddToken(dest, dest, dest.getNext()));
		return seq;
	}

}
