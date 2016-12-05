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
import frontend.type.MapType;
import org.antlr.v4.runtime.ParserRuleContext;
import symboltable.SymbolTable;

import java.util.LinkedList;

public class MapElem extends ExprNode implements AssignLHS {

    private Variable var;
    private LinkedList<ExprNode> locations;
    private MapType mapType;

    public MapElem(LinkedList<ExprNode> locations, Variable var) {
        this.locations = locations;
        this.mapType = (MapType) var.getType();
        this.var = var;
    }

    @Override
    public boolean check(SymbolTable st, ParserRuleContext ctx ) {
        for(ExprNode pos : locations) {
            if(!(pos.getType() == BaseType.INT)) {
                el.addedIntoErrList(new SemanticErrorException("List position can only be found using an IntLeaf", ctx));
                return false;
            }
        }

        return true;
    }

    @Override
    public BaseType getType() {
      return null;
    }

    public BaseType getKey() {
        BaseType type = mapType;
        for (ExprNode location: locations) {
            type = ((MapType) type).getKey();
        }
        return type;
    }

    public BaseType getValue() {
        BaseType type = mapType;
        for (ExprNode location: locations) {
            type = ((MapType) type).getValue();
        }
        return type;
    }



    @Override
    public TokSeq assemblyCodeStoring(Register dest) {
        return mapElemCommonAssembly(dest.getNext())
                .append(mapType.getKey().storeAssembly(dest.getNext(), dest))
                .append(mapType.getValue().storeAssembly(dest.getNext(), dest));

    }


    public TokSeq assemblyCodeGenerating(Register dest) {
        TokSeq out = new TokSeq();
        TokSeq mapAccess = mapElemCommonAssembly(dest);
        LoadAddressToken loadResultOne = mapType.getKey().loadAssembly(dest, dest);
        LoadAddressToken loadResultTwo = mapType.getValue().loadAssembly(dest, dest);
        out
                .appendAll(mapAccess)
                .append(loadResultOne)
                .append(loadResultTwo);


        return out;
    }

    private TokSeq mapElemCommonAssembly(Register dest) {
        int posOnStack = var.getPosition().getStackIndex();

        TokSeq out = new TokSeq();

        AddImmToken addTok = new AddImmToken(dest, Register.sp, Integer.toString(posOnStack));
        out.append(addTok);

        for (ExprNode expr : locations) {
            TokSeq exprSeq = expr.assemblyCodeGenerating(dest.getNext());

            exprSeq.appendAll( new TokSeq(
                    new LoadAddressToken(dest, dest),
                    new MovRegToken(Register.R0, dest.getNext()),
                    new MovRegToken(Register.R1, dest),
                    new CheckArrayBoundsToken(),
                    new AddImmToken(dest, dest, Integer.toString(4))));

            if(mapType.getKey().getVarSize() == BaseType.INT.getVarSize()) {
                exprSeq.append(new AddToken(dest, dest, dest.getNext(), "LSL #2"));
            }
            if(mapType.getValue().getVarSize() == BaseType.INT.getVarSize()) {
                exprSeq.append(new AddToken(dest, dest, dest.getNext(), "LSL #2"));
            } else if(mapType.getKey().getVarSize() != BaseType.INT.getVarSize() &&
                    mapType.getValue().getVarSize() != BaseType.INT.getVarSize()) {
                exprSeq.append(new AddToken(dest, dest, dest.getNext()));
            }
            out.appendAll(exprSeq);
        }

        return out;
    }


    public String getIdent() {
        return var.getIdent();
    }

    @Override
    public int weight() {
        int max = 0;
        for (ExprNode e:locations) {
            int exprWeight = e.weight();
            max = exprWeight > max ? exprWeight : max;
        }

        return max;
    }

    @Override
    public TokSeq loadAddr(Register dest) {
        TokSeq seq = var.assemblyCodeGenerating(dest);
        TokSeq mapIndex = this.locations.get(0).assemblyCodeGenerating(dest.getNext());
        seq.appendAll(mapIndex);
        seq.appendAll(
                new LoadAddressToken(dest, dest),
                new AddToken(dest, dest, dest.getNext()));
        return seq;
    }

}
