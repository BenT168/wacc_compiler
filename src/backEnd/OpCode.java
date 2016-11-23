package backEnd;

public enum OpCode {

    // Arithmetic and logical operators
    ADD, SUB, DIV, MUL, MOD, EQ, NEQ, AND, OR, GT, GTE, LT, LTE,

    // Call and Return
    CALL, RETURN,

    // Data Transfer
    LOAD_VAR, LOAD_IMM, LOAD_ADDR, STR_ADDR,

    // Branching
    JMP, BR,

    // Comparison
    CMP,

    // Stack manipulation
    PUSH, POP;
}
