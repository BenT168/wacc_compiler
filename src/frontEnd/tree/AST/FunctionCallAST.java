package frontEnd.tree.AST;

import frontEnd.tree.Expr.Expr;
import frontEnd.tree.Type.Identifier;
import frontEnd.tree.Type.Type;

import java.util.List;

public class FunctionCallAST extends AST {

    private String funcName;
    private List<Expr> actuals;
    private Function function;


    @Override
    public void check() {
        Identifier id = ST.lookUpAll(funcName);
        if(id == null) {
            semanticError.semanticErrorCase(funcName, "unknownFunction");
        } else if(!(id instanceof Function)) {
            semanticError.semanticErrorCase(funcName, "notAFunction");
        } else if(((Function) id).getFormals().length != actuals.size()) {
            semanticError.semanticErrorCase(funcName, "paramNumber");
        } else {
            for(int i = 0; i < actuals.size(); i++) {
                actuals.get(i).check();
                Type actualType = actuals.get(i).getType();
                Type formalType = ((Function) id).getFormals()[i].getType();
                if(!(assignCompat(formalType,actualType))) {
                    semanticError.semanticErrorCase("", "functionCallType");
                }
            }
            function = (Function) id;
        }

    }
}
