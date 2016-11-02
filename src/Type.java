import antlr.BasicParser;

/**
 * Created by aa14415 on 01/11/16.
 */
public class Type {

    private BasicParser bp;
    private String type;
    private boolean isArray = false;
    private int arrayDepth = 0;
    private boolean isPair = false;


    public Type(String type) {
        this.type = type;
    }

    public String getType() {
        String kindoftype = bp.type().toString();
        if(kindoftype.compareTo("baseType") == 0) {
            return  bp.type().baseType().toString();
        }
        if(kindoftype.compareTo("arrayType") == 0) {
            isArray = true;
            return  bp.type().arrayType().toString();
        }
        if(kindoftype.compareTo("pairType") == 0) {
            return  bp.type().baseType().toString();
        }
        return bp.type().baseType().toString();
    }

}