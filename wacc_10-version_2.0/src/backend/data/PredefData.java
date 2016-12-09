package backend.data;

/**
 * Created by MarkAduol on 07-Dec-16.
 */
public enum PredefData {
    NUL_BYTE("\"\\0\""),
    NUL_REF_ERROR("\"NullReferenceError: dereference a null reference\\n\\0\""),
    STRING_MSG("\"%.*s\\0\""),
    INT_MSG("\"%d\\0\""),
    ARRAY_INDEX_OUT_OF_BOUNDS_NEGATIVE("\"ArrayIndexOutOfBoundsError: negative index\\n\\0\""),
    ARRAY_INDEX_OUT_OF_BOUNDS_TOO_LARGE("\"ArrayIndexOutOfBoundsError: index too large\\n\\0\"");

    private String data;

    PredefData(String data) {
        this.data = data;
    }


    @Override
    public String toString() {
        return data;
    }
}
