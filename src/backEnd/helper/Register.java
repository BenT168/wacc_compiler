package backEnd.helper;

public class Register {

    private String registerName;
    private boolean inUse;
    private boolean arrayAccessed; // reference to array elem when you are indexing regardless of LSL

    public Register(String registerName) {
        this.registerName = registerName;
        inUse = false;
        arrayAccessed = false;
    }

    public String getName() {
        return registerName;
    }

    public void setRegisterToUsed() {
        inUse = true;
    }

    public void setRegisterToUnused() {
        inUse = false;
    }

    public boolean isInUse() {
        return inUse || arrayAccessed;
    }

    public boolean isNotDisabled() {
        return !arrayAccessed;
    }

    public void setRegisterArrayAccessed() {
        arrayAccessed = true;
    }

    public void removeRegiserArrayAccessed() {
        arrayAccessed = false;
    }

    public String printRegister() {
        return "{" + registerName + "}";
    }
}