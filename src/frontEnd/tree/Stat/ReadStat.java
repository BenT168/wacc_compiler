package frontEnd.tree.Stat;

import frontEnd.ErrorHandling.IncompatibleTypesException;
import frontEnd.tree.ASTTree;
import frontEnd.tree.Assignment.AssignLHS;
import frontEnd.tree.Type.BaseType;
import org.antlr.v4.runtime.ParserRuleContext;
import symbolTable.SymbolTable;

public class ReadStat extends Stat {

    private ASTTree lhs;

    public ReadStat(AssignLHS lhs) {
        this.lhs = (ASTTree) lhs;
    }

    @Override
    public boolean check(SymbolTable symbolTable, ParserRuleContext ctx) {
        BaseType type = lhs.getType();
        if (type == BaseType.INT || type == BaseType.CHAR || type == BaseType.STRING) {
	return true;
		}
		throw new IncompatibleTypesException("Variable cannot be read into.", ctx);
    }

}
