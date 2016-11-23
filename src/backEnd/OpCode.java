package backEnd;

public enum OpCode {

    // Arithmetic and logical operators
    ADD, SUB, DIV, MUL, MOD, EQ, NEQ, AND, OR, GT, GTE, LT, LTE,

    // Call and Return
    CALL, RETURN,

    // Data Transfer
    LOAD_VAR, LOAD_IMM, LOAD_ADDR, STR_ADDR,

    // Branching
    JMP,

    // Comparison
    CMP;

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
