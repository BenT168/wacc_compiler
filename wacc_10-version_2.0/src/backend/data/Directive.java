package backend.data;

/**
 * <h1>Directives.</h1>
 * <p>A small subset of ARM assembler directives.</p>
 */
public enum Directive {
    GLOBAL(".global"),
    LTORG(".ltorg"),
    DATA(".data"),
    TEXT(".text"),
    WORD(".word"),
    ASCII(".ascii");

    private String text;

    Directive(String directiveText) {
        this.text = directiveText;
    }

    @Override
    public String toString() {
        return text;
    }
}
