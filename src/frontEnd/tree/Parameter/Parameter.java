package frontEnd.tree.Parameter;

import frontEnd.tree.Identifier;
import frontEnd.tree.Type.BaseType;
import org.antlr.v4.runtime.ParserRuleContext;
import symbolTable.SymbolTable;

public class Parameter extends Identifier {

    private Parameter parameter;


    public Parameter getParam() {
        return parameter;
    }

    @Override
    public BaseType getType() {
        return null;
    }

    public void check(SymbolTable symbolTable, ParserRuleContext ctx) {
    }
}

