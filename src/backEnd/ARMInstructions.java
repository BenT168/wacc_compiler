package backEnd;

import backEnd.helper.Registers;

public enum ARMInstructions {

    PUSH, POP, MOV, LDR, STR, BR, BL, BLVS, CMP, BEQ, MOVGT, MOVLT, MOVGE,
    MOVLE, MOVEQ, MOVNE, AND, ORR, B, SUB, ADD, LDRNE, LDREQ, STRB, LDRSB,
    EOR, ADDS, SUBS, SMULL, BLNE, BLEQ, RSBS, LDRLT, LDRCS, BLLT, BLCS;

    public static final String PUSH_LINK_REG = PUSH.printWithReg(Registers.lr.getName());
    public static final String POP_PC = POP.printWithReg(Registers.pc.getName());
    public static final String LTORG_DIRECTIVE = "\t \t.ltorg";
    public static final String TEXT_DIRECTIVE = "\t.text";
    public static final String DATA_DIRECTIVE = "\t.data";
    public static final String GLOBAL_MAIN_DIRECTIVE = "\t.global main";

    public String printWithReg(String register) {
        switch(this){
            case PUSH:
            case POP:
            case BR:
            case BL:
                return "\t\t" + this + "{" + register + "}";
            default:
                return "printWithReg(" + register + ")";
        }
    }

    public String printWithReg(String reg1, String reg2) {
        switch(this){
            case MOV:
            case LDR:
            case STR:
            case CMP:
                return "\t\t" + this + " " + reg1 + ", " + reg2 ;
            default:
                return "printWithReg(" +  reg1 + ", " + reg2 + ")";
        }
    }

    public String printWithReg(String reg1, String reg2, String reg3) {
        switch(this){
            case AND:
            case ORR:
            case ADD:
            case EOR:
            case ADDS:
            case SUBS:
            case CMP:
                return "\t\t" + this + " " + reg1 + ", " + reg2 +  ", " + reg3 ;
            default:
                return "printWithReg(" +  reg1 + ", " + reg2 +  ", " + reg3 +")";
        }
    }

    public String printWithImm(String reg, String intLiter) {
        switch(this){
            case LDR:
            case LDRNE:
            case LDRCS:
            case LDREQ:
            case LDRLT:
                return "\t\t" + this + " " + reg + ",  =" + intLiter ;
            case MOV:
            case CMP:
            case MOVEQ:
            case MOVGE:
            case MOVLE:
            case MOVGT:
            case MOVLT:
            case MOVNE:
            case EOR:
                return "\t\t" + this + " " + reg + ",  #" + intLiter ;
            default:
                return "printWithImm(" +  reg + ", " + intLiter + ")";
        }
    }

    public String printWithAddr(String reg, int offset) {
        switch(this){
            case LDR:
            case STR:
            case STRB:
            case LDRSB:
            case ADD:
                if (offset == 0){
                    return "\t\t" + this + " " + reg + ", [sp]" ;
                } else {
                    return "\t\t" + this + " " + reg + ", [sp, #" + offset + "]" ;
                }
            default:
                return "printWithAddr(" +  reg + ", " + Integer.toString(offset) + ")";
        }
    }

    public String printWithOffset(String reg, int offset) {
        switch(this){
            case SUB:
            case ADD:
            case LDR:
            case EOR:
            case RSBS:
            case CMP:
                    return "\t\t" + this + " " + reg + ", " + reg + ", #" + offset;
            default:
                return "printWithOffset(" +  reg + ", " + Integer.toString(offset) + ")";
        }
    }

    public String printWithString(String str) {
        switch(this){
            case B:
            case BEQ:
            case BL:
            case BLCS:
            case BLEQ:
            case BLNE:
            case BLLT:
            case BLVS:
            case ADD:
                return "\t\t" + this + " " + str ;
            default:
                return " ";
        }
    }

    public String printWithAddrReg(String reg1, String reg2, int offset) {
        switch(this){
            case LDR:
            case STR:
            case STRB:
            case LDRSB:
                if (offset == 0){
                    return "\t\t" + this + " " + reg1 + ", [" + reg2 + "]" ;
                } else {
                    return "\t\t" + this + " " + reg1 +
                            ", [" + reg2 + ", #" + offset + "]" ;
                }
            default:
                return "printWithAddrReg(" +  reg1 +
                        ", " + reg2 + ", " + Integer.toString(offset) + ")";
        }
    }

    public String printCalledFunc(String reg) {
        switch(this){
            case STRB:
                return "\t\t" + this + " " + reg + ", [sp, #-1]!" ;
            case STR:
                return "\t\t" + this + " " + reg + ", [sp, #-4]!" ;
            default:
                return "printCalledFunc(" +  reg + ")";
        }
    }

    public String printWith4Regs(String reg1, String reg2) {
        switch(this){
            case SMULL:
                return "\t\t" + this + " " + reg1 + ", " + reg2 + ", "
                        + reg1 + ", " + reg2 ;

            default:
                return "printWith4Regs(" +  reg1 + ", " + reg2 + ")";
        }
    }


    public String printWith4RegsX(String reg1, String reg2, String str) {
        switch(this){
            case ADD:
                return "\t\t" + this + " " + reg1 + ", " + reg1 + ", "
                        + reg2 + ", " + str ;
            default:
                return "printWith4RegsX(" +  reg1 + ", " + reg2 + "," + str + ")";
        }
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