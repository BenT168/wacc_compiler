package frontend.type;


import backend.Register;
import backend.StackPos;
import backend.TokSeq;
import backend.Token;
import backend.tokens.load.LoadAddressToken;
import backend.tokens.print.PrintReferenceToken;
import backend.tokens.store.StorePreIndexToken;
import backend.tokens.store.StoreToken;

public class MapType extends BaseType {

    private BaseType key;
    private BaseType value;

    protected final int VAR_SIZE = key.getVarSize() + value.getVarSize();

    public MapType(BaseType key, BaseType value) {
        this.key = key;
        this.value = value;
    }


    @Override
    public boolean isCompatible(BaseType other) {
        return false;
    }

    public boolean isCompatible(BaseType otherKey, BaseType otherValue) {

        //Check if otherKey and otherValue are instances of Maps
        if (!(otherKey instanceof MapType)) {
            if(!key.isCompatible(otherKey)) {
                return false;
            }
        }
        if (!(otherValue instanceof MapType)) {
            if(!value.isCompatible(otherValue)) {
                return false;
            }
            return true;
        }

        //Otherwise check that otherKey and otherValue compatible with key and value
        if (!(key == BaseType.NULL) ) {
            MapType otherMap = (MapType) otherKey;
            if (otherMap.getKey() == BaseType.NULL) {
                return true;
            } else if (!key.isCompatible(otherMap.getKey())) {
                return false;
            }
        }
        if (!(value == BaseType.NULL) ) {
            MapType otherMap = (MapType) otherValue;
            if (otherMap.getValue() == BaseType.NULL) {
                return true;
            } else if (!value.isCompatible(otherMap.getValue())) {
                return false;
            }
            return true;
        }

        return true;
    }

    @Override
    public String toString() {
        return "map-"+key.toString()+", "+value.toString();
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
