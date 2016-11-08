package frontEnd.tree.AST;

import frontEnd.tree.Type.Parameter;

public class ParamAST extends AST {

    private Parameter param;

    @Override
    public void check() {

    }

    public Parameter getParam() {
        return param;
    }
}
