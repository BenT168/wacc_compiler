package backend.data;

/**
 * Created by MarkAduol on 07-Dec-16.
 */
public enum SystemCode {
    SYSTEM_EXIT(0);

    private int systemCode;

    SystemCode(int systemCode) {
        this.systemCode = systemCode;
    }

    @Override
    public String toString() {
        return String.valueOf(systemCode);
    }
}
