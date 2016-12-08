package frontend.assignments;

import backend.Register;
import backend.TokSeq;
import backend.tokens.check.CheckArrayBoundsToken;
import backend.tokens.load.LoadAddressToken;
import backend.tokens.move.MovRegToken;
import backend.tokens.operator.AddImmToken;
import backend.tokens.operator.AddToken;
import frontend.exception.SemanticErrorException;
import frontend.expressions.ExprNode;
import frontend.expressions.Variable;
import frontend.type.BaseType;
import frontend.type.ListType;
import org.antlr.v4.runtime.ParserRuleContext;
import symboltable.SymbolTable;
import java.util.LinkedList;

public class ex_ListElem extends ExprNode implements  AssignLHS {

    private Variable var;
    private ExprNode expr;
    private ListType listType;

    public ex_ListElem(ExprNode expr, Variable var) {
        this.expr = expr;
        this.listType = (ListType) var.getType();
        this.var = var;
    }

    @Override
    public boolean check(SymbolTable st, ParserRuleContext ctx ) {
        if(!(expr.getType() == BaseType.INT)) {
            throw new SemanticErrorException("List position can only be found using an IntLeaf", ctx);
        }
        return true;
    }

    @Override
    public BaseType getType() {
        BaseType type = listType;
        type = ((ListType) type).getBaseType();
        return type;
    }

    @Override
    public TokSeq assemblyCodeStoring(Register dest) {
        return listElemCommonAssembly(dest.getNext())
                .append(listType.getBaseType().storeAssembly(dest.getNext(), dest));
    }


    public TokSeq assemblyCodeGenerating(Register dest) {
        TokSeq out = new TokSeq();
        TokSeq listAccess = listElemCommonAssembly(dest);
        LoadAddressToken loadResult = listType.getBaseType().loadAssembly(dest, dest);
        out
                .appendAll(listAccess)
                .append(loadResult);

        return out;
    }

    private TokSeq listElemCommonAssembly(Register dest) {
        int posOnStack = var.getPosition().getStackIndex();

        TokSeq out = new TokSeq();

        AddImmToken addTok = new AddImmToken(dest, Register.sp, Integer.toString(posOnStack));
        out.append(addTok);

        TokSeq exprSeq = expr.assemblyCodeGenerating(dest.getNext());
        exprSeq.appendAll( new TokSeq(
                new LoadAddressToken(dest, dest),
                new MovRegToken(Register.R0, dest.getNext()),
                new MovRegToken(Register.R1, dest),
                new AddImmToken(dest, dest, Integer.toString(4))));

        if(listType.getBaseType().getVarSize() == BaseType.INT.getVarSize()) {
            exprSeq.append(new AddToken(dest, dest, dest.getNext(), "LSL #2"));
        } else {
            exprSeq.append(new AddToken(dest, dest, dest.getNext()));
        }
        out.appendAll(exprSeq);

        return out;
    }


    public String getIdent() {
        return var.getIdent();
    }

    @Override
    public int weight() {
        int max = 0;
        int exprWeight = expr.weight();
        max = exprWeight > max ? exprWeight : max;
        return max;
    }

    @Override
    public TokSeq loadAddr(Register dest) {
        TokSeq seq = var.assemblyCodeGenerating(dest);
        TokSeq listIndex = expr.assemblyCodeGenerating(dest.getNext());
        seq.appendAll(listIndex);
        seq.appendAll(
                new LoadAddressToken(dest, dest),
                new AddToken(dest, dest, dest.getNext()));
        return seq;
    }

}
