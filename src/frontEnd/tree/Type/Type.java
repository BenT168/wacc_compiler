package frontEnd.tree.Type;

public abstract class Type extends Identifier {

    //private final String type;

    public abstract boolean isCompatible(Type type);
    public abstract String toString();

//    public Type(String type) {
//        if(type.compareTo("bool") == 0 || type.compareTo("int") == 0 || type.compareTo("char") == 0 ||
//                type.compareTo("string") == 0) {
//            this.type = type;
//        } else {
//            System.err.println("Error: " + type + " is not a valid type.");
//            System.exit(-1);
//        }
//    }

    public static final Type BOOL = new Type() {
        @Override
        public void check() {

        }

        @Override
        public String toString() {
            return "bool";
        }

        @Override
        public boolean isCompatible(Type type) {
            return type == BOOL;
        }

    } ;

    public static final Type INT = new Type() {
        @Override
        public void check() {
            //TODO
        }

        @Override
        public String toString() {
            return "int";
        }

        @Override
        public boolean isCompatible(Type type) {
            return type == INT;
        }

    } ;

    public static final Type CHAR = new Type() {
        @Override
        public void check() {
            //TODO
        }

        @Override
        public String toString() {
            return "char";
        }

        @Override
        public boolean isCompatible(Type type) {
            return type == CHAR;
        }

    } ;

    public static final Type NULL = new Type() {
        @Override
        public void check() {
            //TODO
        }

        @Override
        public String toString() {
            return "null";
        }

        @Override
        public boolean isCompatible(Type type) {
            return true;
        }

    } ;
}

