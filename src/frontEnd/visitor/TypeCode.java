package frontEnd.visitor;

public enum TypeCode {

    INT {public String toString() { return "int";}},
    CHAR {public String toString() {return "char";}},
    PAIR {public String toString() {return  "pair";}},
    ARRAY {public String toString() {return  "array";}}
}
