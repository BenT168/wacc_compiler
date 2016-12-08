package backend.data;

/**
 * Created by MarkAduol on 07-Dec-16.
 */
public enum PredefData {
    NUL_BYTE("\"\\0\"");

    private String data;

    PredefData(String data) {
        this.data = data;
    }


    @Override
    public String toString() {
        return data;
    }
}