package frontEnd.tree.Type;

import org.antlr.v4.runtime.ParserRuleContext;
import symbolTable.SymbolTable;

public class PairElemType extends BaseType {

    private BaseType elementType;

    public PairElemType(BaseType type) {
        this.elementType = type;
    }

    public BaseType getType() {
        return BaseType.NULL;
    }

    @Override
    public boolean isCompatible(BaseType type) {
        return true;
    }

    @Override
    public String toString() {
        return elementType.toString();
    }

    @Override
    public boolean check(SymbolTable symbolTable, ParserRuleContext ctx) {
        return true;
    }
}
