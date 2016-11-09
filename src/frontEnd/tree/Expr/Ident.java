package frontEnd.tree.Expr;

import frontEnd.tree.AST.AssignLHS;
import frontEnd.ErrorHandling.UndeclaredIdentifierException;
import frontEnd.tree.Type.BaseType;
import org.antlr.v4.runtime.ParserRuleContext;
import symbolTable.SymbolTable;

public class Ident extends Expr implements AssignLHS {

    private String ident;
    private BaseType type;

    public Ident(BaseType type, String ident){
        this.ident = ident;
        this.type = type;
    }

    @Override
    public boolean check(SymbolTable symtble, ParserRuleContext ctx){
        if(!symtble.containsRecursive(ident)){
            throw new UndeclaredIdentifierException(ident + " hasn't been defined", ctx);
        }
        return true;
    }

    @Override
    public BaseType getType(){
        return type;
    }

    public String getIdent(){
        return ident;
    }

}
