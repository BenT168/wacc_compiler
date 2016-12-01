package backend.tokens.general;

import backend.Token;

public class JumpToken extends Token {
    private String label;
    private String condition;

    public JumpToken(String condition, String label) {
        this.label = label;
        this.condition = condition;
    }

    public JumpToken(String label) {
        this.label = label;
        this.condition = "";
    }

    @Override
    public String toString() {
        return "JMP" + condition +  " " + label;
    }
}

