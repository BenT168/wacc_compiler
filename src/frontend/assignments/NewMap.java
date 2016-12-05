package frontend.assignments;

import backend.Register;
import backend.TokSeq;
import backend.tokens.move.MovRegToken;
import frontend.expressions.ExprNode;
import frontend.type.BaseType;
import frontend.type.MapType;
import org.antlr.v4.runtime.ParserRuleContext;
import symboltable.SymbolTable;

import java.util.LinkedList;

public class NewMap extends Assignable {

    private LinkedList<ExprNode> elems;
    private BaseType type1;
    private BaseType type2;
    private final int MAP_SIZE = 8;

    public NewMap(BaseType type1, BaseType type2) {
        this.type1 = type1;
        this.type2 = type2;

    }

    @Override
    public boolean check(SymbolTable st, ParserRuleContext ctx) {
        return true;
    }

    @Override
    public BaseType getType() {
        return new MapType(type1, type2);
    }

    @Override
    public TokSeq assemblyCodeGenerating(Register dest) {
        TokSeq firstAlloc = mallocSequence(2, MAP_SIZE);
        MovRegToken movReg1 = new MovRegToken(dest, Register.R0);
        firstAlloc
                .append(movReg1);
        return firstAlloc;
    }
}
