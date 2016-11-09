package frontEnd.tree.Type;

import frontEnd.tree.Expr.Expr;

public abstract class UnaryOp {

    public abstract boolean check(Expr expr);
    public abstract Type getType();

    public static UnaryOp evalUnOp(String oper){
        switch (oper) {
            case "!":
                return NOT;
            case "-":
                return NEG;
            case "len":
                return LEN;
            case "ord":
                return ORD;
            case "chr":
                return CHR;

            default:
                throw new IllegalArgumentException("The string" + oper + " did not match any unary operators");
        }
    }

    public static final UnaryOp NOT = new UnaryOp() {
        @Override
        public String toString() {
            return "NOT";
        }

        @Override
        public boolean check(Expr expr) {
            return expr.getType().equals(Type.BOOL);
        }

        @Override
        public Type getType(){
            return  Type.BOOL;
        }

    } ;

    public static final UnaryOp NEG = new UnaryOp() {
        @Override
        public String toString() {
            return "NEG";
        }

        @Override
        public boolean check(Expr expr) {
            return expr.getType().equals(Type.INT);
        }

        @Override
        public Type getType(){
            return  Type.INT;
        }

    } ;


    public static final UnaryOp LEN = new UnaryOp() {
        @Override
        public String toString() {
            return "LEN";
        }

        @Override
        public boolean check(Expr expr) {
            return expr.getType() instanceof Array;
        }

        @Override
        public Type getType(){
            return  Type.INT;
        }

    } ;


    public static final UnaryOp ORD = new UnaryOp() {
        @Override
        public String toString() {
            return "ORD";
        }

        @Override
        public boolean check(Expr expr) {
            return expr.getType().equals(Type.CHAR);
        }

        @Override
        public Type getType(){
            return  Type.INT;
        }

    } ;

    public static final UnaryOp CHR = new UnaryOp() {
        @Override
        public String toString() {
            return "CHR";
        }

        @Override
        public boolean check(Expr expr) {
            return expr.getType().equals(Type.INT);
        }

        @Override
        public Type getType(){
            return  Type.CHAR;
        }

    } ;
}
