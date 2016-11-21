package backEnd;


public class ARMInstruction {

    public static String pushNew() {
        return "PUSH {lr}";
    }

    public static String ret() {
        return "POP {pc}";
    }

    public static String push(String reg) {
        return "PUSH {" + reg + "}";
    }

    public static String pop(String reg) {
        return "POP {" + reg + "}";
    }

    public static String pop(String reg1, String reg2) {
        return "POP {" + reg1 + ", " + reg2 + "}";
    }

    public static String branchCond(String string, String condition) {
        return "BL{" + condition + "} " + string;
    }

    public static String branch(String string) {
        return "B " + string;
    }

    public static String branchwlink(String string) {
        return "BL " + string;
    }

    public static String mov(String reg, int imm) {
        return "MOV " + reg + ", #" + imm;
    }

    public static String mov(String dstreg, String reg) {
        String symb = "'";
        if(!isParsable(reg) && reg.trim().charAt(0) == symb.charAt(0)) {
            // its a char
            return "MOV " + dstreg + ", #" + reg;
        }
        return "MOV " + dstreg + ", " + reg;
    }

    public static String movEQ(String dstreg, String reg) {

        return "MOVEQ " + dstreg + ", " + reg;
    }

    public static String movNE(String dstreg, String reg) {
        return "MOVNE " + dstreg + ", " + reg;
    }

    public static String sub(String store, String r1, int imm) {
        return "SUB " + store + ", " + r1 + ", #" + imm;
    }

    public static String add(String store, String r1, int imm) {
        return "ADD " + store + ", " + r1 + ", #" + imm;
    }

    public static String ldr(String dstreg, String val) {
        String symb = "'";
        if(!isParsable(val) && val.trim().charAt(0) == symb.charAt(0)) {
            // its a char
            return "LDR " + dstreg + ", #" + val;
        }
        return "LDR " + dstreg + ", =" + val;
    }

    public static String ldr(String dstreg, String reg, int offset) {
        return "LDR " + dstreg + ", [" + reg + ", " + offset + "]";
    }

    public static String str(String dstreg, String src) {
        return "STR " + dstreg + ", [" + src + "]";
    }

    public static String strb(String dstreg, String src) {
        return "STRB " + dstreg + ", [" + src + "]";
    }

    public static String str(String dstreg, String src, int offset) {
        return "STR " + dstreg + ", [" + src + ", #" + offset + "]";
    }

    public static String cmp(String reg1, String reg2) {
        return "CMP " + reg1 + ", " + reg2;
    }

    public static String cmp(String reg1, int imm) {
        return "CMP " + reg1 + ", #" + imm;
    }


    public static boolean isParsable(String val) {
        try {
            Integer.parseInt(val);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}