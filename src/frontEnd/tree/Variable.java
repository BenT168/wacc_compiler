package frontEnd.tree;

import frontEnd.semanticCheck.SemanticError;

public class Variable<T> extends Identifier {

    /*A Variable must have a name and a type
     */
    public String name;
    public Type type;

    /* An initialised Variable should have a value
          - this should match the type */
    /*
    private int intValue;
    private char charValue;
    private String stringValue;
    private boolean boolValue;
    */
    private T value;

    //Unitialised Variable
    public Variable(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    // Checks that valueType (actual type) is the same as expected type
    private void variableTypeCheck(String valueType) {
      SemanticError semanticError = new SemanticError();
      if(type.getType().compareTo(valueType) != 0) {
          semanticError.semanticType(type.getType(), valueType);
      }
    }

   //GETTERS

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }
    /*
    public int getIntValue() {
        return intValue;
    }

    public char getCharValue() {
        return charValue;
    }

    public String getStringValue() {
        return stringValue;
    }

    public boolean getBoolValue() {
        return boolValue;
    }
    */

    public T getValue() {
        return value;
    }

    //SETTERS
    /*
    public void setIntValue(int intValue) {
        variableTypeCheck("int");
        this.intValue = intValue;
    }

    public void setCharValue(char charValue) {
        variableTypeCheck("char");
        this.charValue = charValue;
    }

    public void setStringValue(String stringValue) {
        variableTypeCheck("string");
        this.stringValue = stringValue;
    }

    public void setBoolValue(boolean boolValue) {
        variableTypeCheck("bool");
        this.boolValue = boolValue;
    }
    */

    public void setValue(T value) {
        this.value = value;
    }
}