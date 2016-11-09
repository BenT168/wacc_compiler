package frontEnd.tree.Parameter;

import frontEnd.tree.Identifier;
import frontEnd.tree.Type.BaseType;
import org.antlr.v4.runtime.ParserRuleContext;
import symbolTable.SymbolTable;

public class Parameter extends Identifier {

    private BaseType type;

    public Parameter(BaseType type) {
        this.type = type;
    }

    public BaseType getType() {
        return type;
    }

    public void check(SymbolTable symbolTable, ParserRuleContext ctx) {
    }
}

