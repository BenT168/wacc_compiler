import antlr.BasicParser;

public class Type {

    private BasicParser bp;
    private String type;
    private boolean isArray = false;
    private int arrayDepth = 0;
    private boolean isPair = false;


    public Type(String type) {
        this.type = type;
    }

    String getType() {
        String kindOfType = bp.type().toString();
        if(kindOfType.compareTo("baseType") == 0) {
            return  bp.type().baseType().toString();
        }
        if(kindOfType.compareTo("arrayType") == 0) {
            isArray = true;
            return  bp.type().arrayType().toString();
        }
        if(kindOfType.compareTo("pairType") == 0) {
            return  bp.type().baseType().toString();
        }
        return "";
        //TODO
    }

}