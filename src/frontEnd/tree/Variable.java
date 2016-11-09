package frontEnd.tree;

import frontEnd.tree.Type.BaseType;

public class Variable<T> extends Identifier {

    public BaseType type;

    //Unitialised Variable
    public Variable(Identifier Id) {
        this.type = (BaseType) Id;
    }

//    // Checks that valueType (actual type) is the same as expected type
//    private void variableTypeCheck(String valueType) {
//      SemanticError semanticError = new SemanticError();
//      if(type.getType().compareTo(valueType) != 0) {
//          semanticError.semanticType(type.getType(), valueType);
//      }
//    }

    public BaseType getType() {
        return type;
    }

}
