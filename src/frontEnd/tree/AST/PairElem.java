package frontEnd.tree.AST;

import org.antlr.v4.runtime.ParserRuleContext;
import symbolTable.SymbolTable;

public class PairElem extends AST {

    @Override
    public boolean check(SymbolTable symbolTable, ParserRuleContext ctx) {
        return false;
    }
}
