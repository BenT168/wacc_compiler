package frontEnd.tree.Parameter;

import frontEnd.tree.ASTTree;
import frontEnd.tree.Type.BaseType;
import org.antlr.v4.runtime.ParserRuleContext;
import symbolTable.SymbolTable;

public class Parameter extends ASTTree {

    private BaseType type;
    private String ident;

    public Parameter(BaseType type, String ident) {
        this.type = type;
	this.ident = ident;
    }

    @Override
    public BaseType getType() {
        return type;
    }
    
    public String getIdent() {
        return ident;
    }

    public boolean check(SymbolTable symbolTable, ParserRuleContext ctx) {
	return true;    
    }

    @Override
    public boolean equals(Object other) {
	if (other instanceof Parameter) {
		Parameter pn = (Parameter) other;
		if (pn.getType() == type) {
			return true;
		}
	}
	return false;
    }
}

