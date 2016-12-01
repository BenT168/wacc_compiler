package frontend.expressions;

import backend.Register;
import backend.TokSeq;
import backend.tokens.load.LoadToken;
import frontend.type.BaseType;
import org.antlr.v4.runtime.ParserRuleContext;
import symboltable.SymbolTable;

public class ex_HexLeaf extends ExprNode {

    private String value;

    public ex_HexLeaf(String val) {
        this.value = trim(val);
    }

    public static String trim(String val) {
        String res = (val.charAt(0) == '0' && val.charAt(1) == 'h')?
                val.replaceFirst("0h*", "")
                : val;

        return (res.isEmpty()? "0h" : res);
    }

    @Override
    public boolean check( SymbolTable st, ParserRuleContext ctx ) {
        return true;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public BaseType getType() {
        return BaseType.HEX;
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
