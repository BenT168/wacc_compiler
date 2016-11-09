package frontEnd.tree.Function;

import frontEnd.tree.AST.AST;
import frontEnd.tree.Expr.Expr;
import frontEnd.tree.Identifier;
import frontEnd.tree.Type.BaseType;
import org.antlr.v4.runtime.ParserRuleContext;
import symbolTable.SymbolTable;

import java.util.List;

public class FunctionCallAST extends AST {

    private String funcName;
    private List<Expr> actuals;
    private Function function;

    @Override
    public boolean check(SymbolTable symbolTable, ParserRuleContext ctx) {
        Identifier id = ST.lookUpAll(funcName);
        if(id == null) {
            semanticError.semanticErrorCase(funcName, "unknownFunction");
        } else if(!(id instanceof Function)) {
            semanticError.semanticErrorCase(funcName, "notAFunction");
        } else if(((Function) id).getFormals().length != actuals.size()) {
            semanticError.semanticErrorCase(funcName, "paramNumber");
        } else {
            for(int i = 0; i < actuals.size(); i++) {
                actuals.get(i).check(symbolTable, ctx);
                BaseType actualType = actuals.get(i).getType();
                BaseType formalType = ((Function) id).getFormals()[i].getType();
                if(!(assignCompat(formalType,actualType))) {
                    semanticError.semanticErrorCase("", "functionCallType");
                }
            }
            function = (Function) id;
        }
        return true;
    }

}
