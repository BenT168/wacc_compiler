package frontEnd.tree.Type;


import frontEnd.tree.AST.ParamAST;

public class Parameter extends Identifier {
    private Type type;

    public Object getParam() {
        return param;
    }

    private ParamAST param;

    @Override
    public void check() {

    }

    public ParamAST getParam() {
        return param;
    }
}
}
