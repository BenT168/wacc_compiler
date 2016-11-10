package frontEnd.tree.Assignment;

import frontEnd.tree.Expr.Expr;
import frontEnd.tree.Function.Function;
import frontEnd.tree.ASTTree;
import frontEnd.tree.Function.FunctionDec;
import frontEnd.tree.Type.BaseType;
import org.antlr.v4.runtime.ParserRuleContext;
import symbolTable.SymbolTable;

import java.util.List;

public class CallFunc extends ASTTree {

    private final FunctionDec func;
    private final ArgList arg;
    private String funcName;
    private List<Expr> actuals;
    private Function functBody;

    public CallFunc(FunctionDec func, ArgList arg) {
        this.func = func;
        this.arg = arg;

    }

    @Override
    public boolean check(SymbolTable symbolTable, ParserRuleContext ctx) {
        ASTTree id = symbolTable.lookUpAll(funcName);
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
            functBody = (Function) id;
        }
        return true;
    }

}
