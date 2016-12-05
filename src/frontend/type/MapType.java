package frontend.type;


import backend.Register;
import backend.StackPos;
import backend.TokSeq;
import backend.Token;
import backend.tokens.load.LoadAddressToken;
import backend.tokens.print.PrintReferenceToken;
import backend.tokens.store.StorePreIndexToken;
import backend.tokens.store.StoreToken;
import frontend.exception.SemanticErrorException;

public class MapType extends BaseType {

    private BaseType key;
    private BaseType value;

    protected final int VAR_SIZE;

    public MapType(BaseType key, BaseType value) {
        this.key = key;
        this.value = value;
        VAR_SIZE = key.getVarSize() + value.getVarSize();
    }


    @Override
    public boolean isCompatible(BaseType other) {
        // NULL cannot be assigned to maps - they have to be initialised
        if (other == BaseType.NULL) {
            throw new SemanticErrorException("Cannot assign a declaration of a Map to " +
                    "a null object");
        }

        if (!(other instanceof MapType)) {
            return false;
        }

        MapType otherMap = (MapType) other;
        return 	checkType(this.getKey(), otherMap.getKey())
                && checkType(this.getValue(), otherMap.getValue());
    }

    // utility method for checking map types
    private static boolean checkType(BaseType lhs, BaseType rhs) {
        boolean compatible = lhs.isCompatible(rhs);
        if (!compatible) {
            throw new SemanticErrorException("The types " + lhs.toString() +
                    " and " + rhs.toString() + "are incompatible.");
        }
        return compatible;
    }


    @Override
    public String toString() {
        return "map-("+key.toString()+", "+value.toString()+")";
    }

    public BaseType getKey() {
        return key;
    }

    public BaseType getValue() {
        return value;
    }


    @Override
    public Token passAsArg(Register r) {
        return  new StorePreIndexToken(Register.sp, r, -VAR_SIZE  );
    }

    @Override
    public int getVarSize() {
        return VAR_SIZE;
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
