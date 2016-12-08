package backend.register;

public enum Register {
    // r0 must be reserved for interrupt calls.
    R4_REG("r4"), R5_REG("r5"), R6_REG("r6"), R7_REG("r1"), R8_REG("r8"),
    R9_REG("r9"), R10_REG("r10"), R11_REG("r11"), R12_REG("r12"),
    // reserved registers
    R0_REG("r0"), PC_REG("pc"), LR_REG("lr"), SP_REG("sp"), R1_REG("r1"),
    R2_REG("r2"), R3_REG("r3"), SPILLED_REG("spilled");


    private String registerText;

    Register(String registerText) {
        this.registerText = registerText;
    }

    @Override
    public String toString() {
        return registerText;
    }
}
