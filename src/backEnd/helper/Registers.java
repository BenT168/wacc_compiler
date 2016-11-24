package backEnd.helper;

import java.util.ArrayList;
import java.util.List;

public class Registers {

    public static final int FIRST_GENERAL_REGISTER = 0;
    public static final int LAST_GENERAL_REGISTER = 12;
    public static final int FIRST_RETURN_REGISTER = 0;
    public static final int LAST_RETURN_REGISTER = 3;

//    // Special Registers
    public static Register sp  = new Register("sp");
    public static Register lr  = new Register("lr");
    public static Register pc  = new Register("pc");

    ArrayList<Register> generalRegisters = new ArrayList<Register>();
    ArrayList<Register> returnRegisters = new ArrayList<Register>();


    public Registers() {
        for (int i = FIRST_GENERAL_REGISTER; i <= LAST_GENERAL_REGISTER; i++) {
            generalRegisters.add(new Register("r" + i));
        }
        for (int i = FIRST_RETURN_REGISTER; i <= LAST_RETURN_REGISTER; i++) {
            returnRegisters.add(generalRegisters.get(i));
        }
    }

    public Register getSpecificRegister(int registerNumber) {
        assert (registerNumber >= FIRST_GENERAL_REGISTER && registerNumber <= LAST_GENERAL_REGISTER);
        return generalRegisters.get(registerNumber);
    }

    public Register getAvailableRegisterFromList(List<Register> regs) {
        for (int i = 0 ; i < regs.size(); i++) {
            if (!(regs.get(i).isInUse())) {
                regs.get(i).setRegisterToUsed();
                return regs.get(i);
            }
        }
        // TODO: If you run out of return registers throw an error, but this
        //       should never be the case in the test suite...
        return null;
    }

    public Register getGeneralRegister() {
        return getAvailableRegisterFromList(generalRegisters);
    }

    public Register getReturnRegister() {
        return getAvailableRegisterFromList(returnRegisters);
    }

    public Register getNonReturnRegister() {
        return getAvailableRegisterFromList((List<Register>)
                generalRegisters.subList(LAST_RETURN_REGISTER+1, LAST_GENERAL_REGISTER+1));
    }

    public void freeRegister(Register r) {
        r.setRegisterToUnused();
    }

    public void freeAllRegisters() {
        for (int i = 0; i < generalRegisters.size(); i++) {
            freeRegister(generalRegisters.get(i));
        }
    }

    public void resetAllRegisters() {
        for (int i = 0; i < generalRegisters.size(); i++) {
            freeRegister(generalRegisters.get(i));
            generalRegisters.get(i).removeRegiserArrayAccessed();
        }
    }

    public ArrayList<Register> freeLastTwoNonReturnRegistersInUse() {
        int count = 0;
        ArrayList<Register> result = new ArrayList<Register>();
        for (int i = LAST_GENERAL_REGISTER; i >= LAST_RETURN_REGISTER+1; i--) {
            Register r = getSpecificRegister(i);
            if (r.isInUse() && r.isNotDisabled()) {
                freeRegister(r);
                result.add(0, r);
                count++;
                if (count == 2) {
                    break;
                }
            }
        }
        return result;
    }

    public void freeLastRegister() {
        // TODO Auto-generated method stub
        for (int i = LAST_GENERAL_REGISTER; i >= LAST_RETURN_REGISTER+1; i--) {
            Register r = getSpecificRegister(i);
            if (r.isInUse() && r.isNotDisabled()) {
                freeRegister(r);
                break;

            }
        }
    }

    public Register getLastDisabled() {
        for (int i = LAST_GENERAL_REGISTER; i >= LAST_RETURN_REGISTER+1; i--) {
            Register r = getSpecificRegister(i);
            if (!r.isNotDisabled()) {
                return r;
            }
        }
        return null;
    }


}
