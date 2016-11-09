package frontEnd.tree;

import frontEnd.tree.Type.BaseType;

public class Variable extends Identifier {

    private BaseType type;


    //Unitialised Variable
    public Variable(BaseType type, String expr) {
        this.type = type;
    }

    public BaseType getType() {
        return type;
    }

}
