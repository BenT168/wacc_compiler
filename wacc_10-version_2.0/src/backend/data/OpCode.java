package backend.data;

/**
 * Opcodes for assembly instructions.
 */
public enum OpCode {
    // Branching
    BEQ, BLEQ, BL, BGE, BG, B, BLLT, BLCS,

    // Data Transfer
    LDR, STR, MOV, MOVGT, MOVGE, MOVEQ, MOVNE, MOVLE, MOVLT, LDREQ, LDRLT, LDRCS,

    // Arithmetic and relational operators
    ADD, SUB, IMUL, IDIV, AND, OR,

    // Comparison
    CMP,

    // Stack manipulation
    PUSH, POP;
}
