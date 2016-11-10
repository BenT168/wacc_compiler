package frontEnd.tree.Type;

import frontEnd.tree.IdentifierAST;
import org.antlr.v4.runtime.ParserRuleContext;
import symbolTable.SymbolTable;

public abstract class BaseType extends IdentifierAST {

    //private final String type;

    public abstract boolean isCompatible(BaseType type);
    public abstract String toString();

//    public Type(String type) {
//        if(type.compareTo("bool") == 0 || type.compareTo("int") == 0 || type.compareTo("char") == 0 ||
//                type.compareTo("string") == 0) {
//            this.type = type;
//        } else {
//            System.err.println("Error: " + type + " is not a valid type.");
//            System.exit(-1);
//        }
//    }

    public static final BaseType BOOL = new BaseType() {

        @Override
        public boolean check(SymbolTable symbolTable, ParserRuleContext ctx) {
            return true;
        }

        @Override
        public String toString() {
            return "bool";
        }

        @Override
        public boolean isCompatible(BaseType type) {
            return type == BOOL;
        }

    } ;

    public static final BaseType INT = new BaseType() {

        @Override
        public boolean check(SymbolTable symbolTable, ParserRuleContext ctx) {
            return true;
        }

        @Override
        public String toString() {
            return "int";
        }

        @Override
        public boolean isCompatible(BaseType type) {
            return type == INT;
        }

    } ;

    public static final BaseType CHAR = new BaseType() {

        @Override
        public boolean check(SymbolTable symbolTable, ParserRuleContext ctx) {
            return true;
        }

        @Override
        public String toString() {
            return "char";
        }

        @Override
        public boolean isCompatible(BaseType type) {
            return type == CHAR;
        }

    } ;

    public static final BaseType STRING = new BaseType() {

        @Override
        public boolean check(SymbolTable symbolTable, ParserRuleContext ctx) {
            return true;
        }

        @Override
        public String toString() {
            return "string";
        }

        @Override
        public boolean isCompatible(BaseType type) {
            return type == STRING;
        }

    } ;

    public static final BaseType NULL = new BaseType() {

        @Override
        public boolean check(SymbolTable symbolTable, ParserRuleContext ctx) {
            return true;
        }

        @Override
        public String toString() {
            return "null";
        }

        @Override
        public boolean isCompatible(BaseType type) {
            return true;
        }

    } ;
}

