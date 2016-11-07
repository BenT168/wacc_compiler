package frontEnd;

import antlr.BasicParser;

public class Type implements TypeInterface {

    private BasicParser bp;
    private String type;
    private boolean isArray = false;
    private int arrayDepth = 0;
    private boolean isPair = false;


    public Type(String type) {
        if(type.compareTo("bool") == 0 || type.compareTo("int") == 0 || type.compareTo("char") == 0 ||
        type.compareTo("string") == 0) {
            this.type = type;
        } else {
            System.out.println("Error: " + type + " is not a valid type.");
            System.exit(-1);
        }
    }

    public String getType() {
        return type;
    }

    public boolean isInt() {
        return type.compareTo("int") == 0;
    }

    public boolean isBool() {
        return type.compareTo("bool") == 0;
    }

    public boolean isChar() {
        return type.compareTo("char") == 0;
    }

    public boolean isString() {
        return type.compareTo("string") == 0;
    }

}