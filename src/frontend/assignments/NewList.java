package frontend.assignments;

import backend.Register;
import backend.TokSeq;
import backend.tokens.move.MovRegToken;
import frontend.expressions.ExprNode;
import frontend.type.BaseType;
import frontend.type.ListType;
import org.antlr.v4.runtime.ParserRuleContext;
import symboltable.SymbolTable;

import java.util.LinkedList;

public class NewList extends Assignable {

    private LinkedList<ExprNode> elems;
    private BaseType type;
    private final int LIST_SIZE = 4;

    public NewList(BaseType type) {
        this.type = type;
    }

    @Override
    public boolean check(SymbolTable st, ParserRuleContext ctx) {
        return true;
    }

    @Override
    public BaseType getType() {
        return new ListType(type);
    }

    @Override
    public TokSeq assemblyCodeGenerating(Register dest) {
        TokSeq firstAlloc = mallocSequence(2, LIST_SIZE);
        MovRegToken movReg1 = new MovRegToken(dest, Register.R0);
        firstAlloc
                .append(movReg1);
        return firstAlloc;
    }
}
