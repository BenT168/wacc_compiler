package frontend.type;

import backend.Register;
import backend.StackPos;
import backend.TokSeq;
import backend.Token;
import backend.tokens.load.LoadAddressToken;
import backend.tokens.print.PrintReferenceToken;
import backend.tokens.store.StorePreIndexToken;
import backend.tokens.store.StoreToken;

public class ListType extends BaseType {

    private BaseType baseType;

    protected final int MEMORY_SIZE = 1000;

    public ListType(BaseType baseType) {
        this.baseType = baseType;
    }


    @Override
    public boolean isCompatible(BaseType other) {

        if (!(other instanceof ListType)) {
            if(!baseType.isCompatible(other)) {
                return false;
            }
            return true;
        }
        if (!(baseType == BaseType.NULL)) {
            ListType otherList = (ListType) other;
            if ( otherList.getBaseType() == BaseType.NULL) {
                return true;
            } else if (!baseType.isCompatible(otherList.getBaseType())) {
                return false;
            }
            return true;
        }
        return true;
    }

    @Override
    public String toString() {
        return "list-"+baseType.toString();
    }

    public BaseType getBaseType() {
        return baseType;
    }


    @Override
    public Token passAsArg(Register r) {
        return  new StorePreIndexToken(Register.sp, r, -MEMORY_SIZE  );
    }

    @Override
    public int getVarSize() {
        return MEMORY_SIZE;
    }

    @Override
    public StoreToken storeAssembly(Register dest, Register source) {
        return new StoreToken(dest, source);
    }

    @Override
    public TokSeq printAssembly(Register register) {
        return new TokSeq(new PrintReferenceToken(register));
    }

    @Override
    public TokSeq storeAssembly(Register source, StackPos pos) {
        int index = pos.getStackIndex();
        return new TokSeq(
                new StoreToken(Register.sp, source, index));
    }

    @Override
    public LoadAddressToken loadAssembly(Register dest, Register source) {
        return new LoadAddressToken(dest, source);
    }
}

