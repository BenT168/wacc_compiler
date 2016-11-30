package frontend.expressions;

import backend.StackPos;
import backend.TokSeq;
import frontend.exception.SemanticErrorException;
import org.antlr.v4.runtime.ParserRuleContext;

import symboltable.SymbolTable;
import frontend.assignments.AssignLHS;
import frontend.type.BaseType;
import backend.Register;
import backend.tokens.operator.AddImmToken;
import backend.tokens.load.LoadAddressToken;

public class Variable extends ExprNode implements AssignLHS {

	protected String ident;
	protected BaseType type;
	protected StackPos position;

	public Variable(BaseType type, String ident) {
		this.ident = ident;
		this.type = type;
	}

	@Override
	public boolean check( SymbolTable st, ParserRuleContext ctx ) {
		if (!st.isRecursive(ident)) {
			throw new SemanticErrorException(ident + " hasn't been defined", ctx);
		}
		return true;
	}

	@Override
	public BaseType getType() {
		return type;
	}

	public String getIdent() {
		return ident;
	}

	@Override
	public int weight() {
		return 1;
	}

	public void setPos(StackPos pos) {
		this.position = pos;
	}

	@Override
	public TokSeq assemblyCodeGenerating(Register r) {
		return new TokSeq(
				new LoadAddressToken(r, position.getBaseReg(), position.getStackIndex()));
	}

	public StackPos getPosition() {
		return position;
	}

	@Override
	public TokSeq assemblyCodeStoring(Register dest) {
		return position.toStoreAssembly(dest);
	}

	@Override
	public TokSeq loadAddr(Register dest) {
		return new TokSeq(
				new AddImmToken(dest, position.getBaseReg(), position.getStackIndex()));
	}



}
