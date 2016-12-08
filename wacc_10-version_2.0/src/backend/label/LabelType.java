package backend.label;

public enum LabelType {
    // Dynamically generated labels
    DEFAULT("L"),
    // Some message labels contain special data.
    MESSAGE_LABEL("msg_"),
    // Predefined labels
    MALLOC("malloc"),
    MAIN_FUNCTION_LABEL("main"),
    CHECK_ARRAY_BOUNDS("p_check_array_bounds"),
    THROW_OVERFLOW_ERROR("p_throw_overflow_error"),
    CHECK_NULL_POINTER("p_check_null_pointer"),
    FREE_PAIR("p_free_pair"),
    PRINT_STRING("p_print_string"),
    PRINT_LINE("p_print_ln"),
    CHECK_DIVIDE_BY_ZERO("p_check_divide_by_zero"),
    _SPECIAL_MOD("__aeabi_idivmod"),
    PRINT_INT("p_print_int"),
    READ_CHAR("p_read_char"),
    READ_INT("p_read_int"),
    THROW_RUNTIME_ERROR("p_throw_runtime_error"),
    // System Call labels
    PUTS("puts"),
    F_FLUSH("fflush"),
    EXIT("exit"),
    // Function label
    FUNCTION_LABEL("");

    private String labelTypeText;

    LabelType(String labelTypeText) {
        this.labelTypeText = labelTypeText;
    }

    public void setLabelTypeText(String labelTypeText) {
        this.labelTypeText = labelTypeText;
    }

    @Override
    public String toString() {
        return labelTypeText;
    }
}
