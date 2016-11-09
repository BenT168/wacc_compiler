package frontEnd.tree.Parameter;

import frontEnd.tree.AST.AST;
import org.antlr.v4.runtime.ParserRuleContext;
import symbolTable.SymbolTable;

public class ParamAST extends AST {

    private Parameter param;

    @Override
    public boolean check(SymbolTable symbolTable, ParserRuleContext ctx) {
        return true;
    }

    public Parameter getParam() {
        return param;
    }
}
