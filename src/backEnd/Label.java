package backEnd;

public class Label {

    private String text;

    public Label(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text + ":";
    }
}
