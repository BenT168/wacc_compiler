package frontEnd.tree.Type;

import frontEnd.semanticCheck.SemanticError;

public class Variable<T> extends Identifier {

    public Type type;

    //Unitialised Variable
    public Variable(Identifier Id) {
        this.type = (Type) Id;
    }

    // Checks that valueType (actual type) is the same as expected type
    private void variableTypeCheck(String valueType) {
      SemanticError semanticError = new SemanticError();
      if(type.getType().compareTo(valueType) != 0) {
          semanticError.semanticType(type.getType(), valueType);
      }
    }

    public Type getType() {
        return type;
    }


}
