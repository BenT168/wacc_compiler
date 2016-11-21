package backEnd;


public class ARMInstruction {

    public static String pushNew() {
        return "PUSH {lr}";
    }

    public static String ret() {
        return "POP {pc}";
    }

    public static String push(String reg) {
        return "PUSH {"+ reg +"}";
    }

    public static String pop(String reg) {
        return "POP {"+ reg +"}";
    }

    public static String ldr(String reg, String val) {
        return "LDR "+ reg +", ="+val;
    }

    public static String bl(String string) {
        return "BL "+string;
    }

    public static String mov(String reg, int val) {
        return "MOV "+ reg +", #"+val;
    }

    public static String sub(String store, String r1, int val) {
        return "SUB "+ store +", "+r1+", #"+val;
    }

    public static String add(String store, String r1, int val) {
        return "ADD "+ store +", "+r1+", #"+val;
    }

    public static String str(String reg, String reg2) {
        return "STR "+reg + ", ["+reg2+"]";
    }

    public static String str(String reg, String reg2, int offset) {
        return "STR "+reg + ", ["+reg2+", "+ "#"+offset+"]";
    }




}
