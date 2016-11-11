package frontEnd.tree.Type;

import frontEnd.tree.Expr.Expr;

public abstract class BinaryOp {
   
    public BinaryOp() {}
    public abstract boolean check(Expr lhs, Expr rhs);
    public abstract BaseType getType();

    private static final BinaryOp MUL = new ArithBinOp();
    private static final BinaryOp DIV = new ArithBinOp();
    private static final BinaryOp MOD = new ArithBinOp();
    private static final BinaryOp PLUS = new ArithBinOp();
    private static final BinaryOp MINUS = new ArithBinOp();

    private static final BinaryOp GT = new CompareBinOp();
    private static final BinaryOp GTE = new CompareBinOp();
    private static final BinaryOp LT = new CompareBinOp();
    private static final BinaryOp LTE = new CompareBinOp();

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
                return PLUS;
            case "-":
                return MINUS;
            case ">":
                return GT;
            case ">=":
                return GTE;
            case "<":
                return LT;
            case "<=":
                return LTE;
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
