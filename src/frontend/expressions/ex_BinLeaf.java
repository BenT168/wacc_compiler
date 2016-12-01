package frontend.expressions;

import backend.Register;
import backend.TokSeq;
import backend.tokens.load.LoadToken;
import frontend.exception.SyntaxErrorException;
import frontend.type.BaseType;
import org.antlr.v4.runtime.ParserRuleContext;
import symboltable.SymbolTable;

public class ex_BinLeaf extends ExprNode {

    private String value;

    public ex_BinLeaf(String val) {
        this.value = trim(val);
    }

    public static String trim(String val) {
        String res = (val.charAt(0) == '0' && val.charAt(1) == 'b')?
                val.replaceFirst("0b*", "")
                : val;

        return (res.isEmpty()? "0b" : res);
    }

    @Override
    public boolean check( SymbolTable st, ParserRuleContext ctx ) {
        long integer = Long.valueOf(value);
        if (integer < - (Math.pow(2, 31)) || integer > (Math.pow(2, 31) + 1)) {
            throw new SyntaxErrorException("The absolute value, " + value + " is too large", ctx);
        }
        return true;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public BaseType getType() {
        return BaseType.BIN;
    }

    @Override
    public TokSeq assemblyCodeGenerating(Register r) {
        return new TokSeq( new LoadToken(r, value) );
    }

    @Override
    public int weight() {
        return 1;
    }

}
