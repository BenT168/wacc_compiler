package backEnd;


class ARMInstruction {

    static String pushNew() {
        return "PUSH {lr}";
    }

    static String ret() {
        return "POP {pc}";
    }

    static String push(String reg) {
        return "PUSH {" + reg + "}";
    }

    static String pop(String reg) {
        return "POP {" + reg + "}";
    }

    static String branchCond(String string, String condition) {
        return "BL{" + condition + "} " + string;
    }

    static String branch(String string) {
        return "B " + string;
    }

    static String branchwlink(String string) {
        return "BL " + string;
    }

    static String mov(String reg, int imm) {
        return "MOV " + reg + ", #" + imm;
    }

    static String mov(String dstreg, String reg) {
        return "MOV " + dstreg + ", " + reg;
    }

    static String movEQ(String dstreg, String reg) {
        return "MOVEQ " + dstreg + ", " + reg;
    }

    static String movNE(String dstreg, String reg) {
        return "MOVNE " + dstreg + ", " + reg;
    }

    static String sub(String store, String r1, int imm) {
        return "SUB " + store + ", " + r1 + ", #" + imm;
    }

    static String add(String store, String r1, int imm) {
        return "ADD " + store + ", " + r1 + ", #" + imm;
    }

    static String ldr(String dstreg, String val) {
        return "LDR " + dstreg + ", =" + val;
    }

    static String ldr(String dstreg, String reg, int offset) {
        return "LDR " + dstreg + ", [" + reg + ", " + offset + "]";
    }

    static String str(String dstreg, String src) {
        return "STR " + dstreg + " [" + src + "]";
    }

    static String str(String dstreg, String src, int offset) {
        return "STR " + dstreg + " [" + src + ", #" + offset + "]";
    }

    static String cmp(String reg1, String reg2) {
        return "CMP " + reg1 + ", " + reg2;
    }

    static String cmp(String reg1, int imm) {
        return "CMP " + reg1 + ", #" + imm;
    }

}