package frontEnd.tree.Type;

import frontEnd.ErrorHandling.*;
import org.antlr.v4.runtime.ParserRuleContext;
import symbolTable.SymbolTable;

public class PairType extends BaseType {

    private BaseType fst;
    private BaseType snd;
    private static PairType nullPairType = new PairType(BaseType.NULL, BaseType.NULL);

    public PairType(BaseType fst, BaseType snd) {
        this.fst = (fst instanceof PairType) ? nullPairType : fst;
        this.snd = (snd instanceof PairType) ? nullPairType : snd;
    }

    public BaseType getFst() {
        return fst;
    }

    public BaseType getSnd() {
        return snd;
    }

    @Override
    public boolean isCompatible(BaseType type) {
        if(type == BaseType.NULL) {
            return true;
        }

        if (!(type instanceof PairType)){
            return false;
        }

        PairType other = (PairType) type;

        return checkType(this.getFst(),other.getFst()) &&
                checkType(this.getSnd(), other.getSnd());
    }

    private boolean checkType(BaseType fst, BaseType snd) {
        boolean compatible = fst == BaseType.NULL || fst.isCompatible((snd));
        if(!compatible){
            throw new IncompatibleTypesException("The types " + fst.toString() + " and " + snd.toString() + " are not compatible.");
        }
        return compatible;
    }

    @Override
    public String toString() {
        return "pair(" + fst.toString() + ", " + snd.toString() + ")";
    }


    @Override
    public boolean check(SymbolTable symbolTable, ParserRuleContext ctx) {
        return true;
    }


}
