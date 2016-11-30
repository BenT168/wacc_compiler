package frontend.statements;

import frontend.Tree;
import frontend.exception.SemanticErrorException;
import frontend.type.BaseType;
import org.antlr.v4.runtime.ParserRuleContext;

import symboltable.SymbolTable;
import frontend.assignments.AssignLHS;
import backend.Register;
import backend.TokSeq;
import backend.tokens.move.MovRegToken;
import backend.tokens.general.ReadToken;

/**
 * Class to represent read statements
 * Rule: 'read' assign-lhs
 */

public class ReadNode extends StatNode {

	private Tree lhs;

	public ReadNode(AssignLHS lhs) {
		this.lhs = (Tree) lhs;
	}

	@Override
	public boolean check( SymbolTable st, ParserRuleContext ctx) {
		BaseType type = lhs.getType();
		if (type == BaseType.INT || type == BaseType.CHAR || type == BaseType.STRING) {
			return true;
		}
		throw new SemanticErrorException("Variable cannot be read into.", ctx);
	}

	public TokSeq assemblyCodeGenerating(Register register) {
		AssignLHS lhsNode = (AssignLHS) lhs;
		return lhsNode.loadAddr(register)
				.append(new MovRegToken(Register.R0, register))
				.append(new ReadToken(lhs.getType()));
	}
}
