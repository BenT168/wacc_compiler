package frontEnd.tree.Expr;

import frontEnd.tree.AST.AstLHSNode;
import frontEnd.ErrorHandling.UndeclaredIdentifierException;
import frontEnd.tree.Type.Type;
import org.antlr.v4.runtime.ParserRuleContext;
import symbolTable.SymbolTable;

import java.lang.reflect.UndeclaredThrowableException;

public class Ident extends Expr implements AstLHSNode {

    private String ident;
    private Type type;

    public Ident(Type type, String ident){
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
    public Type getType(){
        return type;
    }

    public String getIdent(){
        return ident;
    }

}
