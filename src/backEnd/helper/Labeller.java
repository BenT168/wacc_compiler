package backEnd.helper;

public class Labeller {

    private int labelCount;

    private static final String readIntLabel = "p_read_int";
    private static final String readCharLabel = "p_read_char";

    public Labeller() {
        labelCount = -1;
    }

    public String getNewLabel() {
        labelCount++;
        return "L" + labelCount;
    }

    public String getReadIntLabel() {
        return readIntLabel;
    }

    public String getReadCharLabel() {
        return readCharLabel;
    }

}