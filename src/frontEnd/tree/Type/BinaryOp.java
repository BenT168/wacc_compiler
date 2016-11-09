package frontEnd.tree.Type;

import frontEnd.tree.Expr.Expr;

public abstract class BinaryOp {

    public abstract boolean check(Expr lhs, Expr rhs);
    public abstract Type getType();

    private static final BinaryOp MUL = new ArithBinOp();
    private static final BinaryOp DIV = new ArithBinOp();
    private static final BinaryOp MOD = new ArithBinOp();
    private static final BinaryOp ADD = new ArithBinOp();
    private static final BinaryOp SUB = new ArithBinOp();

    private static final BinaryOp GRT = new CompareBinOp();
    private static final BinaryOp GRT_EQ = new CompareBinOp();
    private static final BinaryOp LESS = new CompareBinOp();
    private static final BinaryOp LESS_EQ = new CompareBinOp();

    private static final BinaryOp EQ = new EqualBinOp();
    private static final BinaryOp NEQ = new EqualBinOp();

    private static final BinaryOp AND = new BoolBinOp();
    private static final BinaryOp OR = new BoolBinOp();

    public static BinaryOp evalBinOp(String oper){
        switch (oper) {
            case "*":
                return MUL;
            case "/":
                return DIV;
            case "%":
                return MOD;
            case "+":
                return ADD;
            case "-":
                return SUB;
            case ">":
                return GRT;
            case ">=":
                return GRT_EQ;
            case "<":
                return LESS;
            case "<=":
                return LESS_EQ;
            case "==":
                return EQ;
            case "!=":
                return NEQ;
            case "&&":
                return AND;
            case "||":
                return OR;

            default:
                throw new IllegalArgumentException("The string" + oper + " did not match any binary operators");
        }
    }

}
