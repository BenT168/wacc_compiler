package frontend.stat;

import frontend.tree;
import org.antlr.v4.runtime.ParserRuleContext;

import symboltable.SymbolTable;
import frontend.assignments.AssignLhsNode;
import frontend.type.WACCType;
import WACCExceptions.IncompatibleTypesException;
import backend.Register;
import backend.TokenSequence;
import backend.tokens.move.MovRegToken;
import backend.tokens.general.ReadToken;

/**
 * Class to represent read statements  
 * Rule: 'read' assign-lhs
 */

public class ReadStatNode extends StatNode {

	private tree lhs;
	
	public ReadStatNode(AssignLhsNode lhs) {
		this.lhs = (tree) lhs;
	}
	
	@Override
	public boolean check( SymbolTable st, ParserRuleContext ctx) {
		WACCType type = lhs.getType();
		if (type == WACCType.INT || type == WACCType.CHAR || type == WACCType.STRING) {
			return true;
		}
		throw new IncompatibleTypesException("Variable cannot be read into.", ctx);
	}
	
	public TokenSequence toAssembly(Register register) {
		AssignLhsNode lhsNode = (AssignLhsNode) lhs;
		return lhsNode.loadAddress(register)
				.append(new MovRegToken(Register.R0, register))
				.append(new ReadToken(lhs.getType()));
	}
}
