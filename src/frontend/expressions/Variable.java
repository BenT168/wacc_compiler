package frontend.expressions;

import backend.StackLocation;
import frontend.exception.SemanticErrorException;
import org.antlr.v4.runtime.ParserRuleContext;

import symboltable.SymbolTable;
import frontend.assignments.AssignLHS;
import frontend.type.BaseType;
import backend.Register;
import backend.TokenSequence;
import backend.tokens.operator.AddImmToken;
import backend.tokens.load.LoadAddressToken;

/* Represents an Identifier and its declared type
 * Constructed with and type and string (e.g BOOL, "myBool")
 * Rule: (' '|'a'-'z'|'A'-'Z')(' '|'a'-'z'|'A'-'Z'|'0'-'9')*
 */

public class Variable extends ExprNode implements AssignLHS {

	protected String ident;
	protected BaseType type;
	protected StackLocation position;

	public Variable(BaseType type, String ident) {
		this.ident = ident;
		this.type = type;
	}

	@Override
	public boolean check( SymbolTable st, ParserRuleContext ctx ) {
		if (!st.containsRecursive(ident)) {
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

	public void setPos(StackLocation pos) {
		this.position = pos;
	}

	@Override
	public TokenSequence toAssembly(Register r) {
		return new TokenSequence(
				new LoadAddressToken(r, position.getBaseReg(), position.getStackIndex()));
	}

	public StackLocation getPosition() {
		return position;
	}

	@Override
	public TokenSequence toStoreAssembly(Register dest) {
		return position.toStoreAssembly(dest);
	}

	@Override
	public TokenSequence loadAddress(Register dest) {
		return new TokenSequence(
				new AddImmToken(dest, position.getBaseReg(), position.getStackIndex()));
	}



}
