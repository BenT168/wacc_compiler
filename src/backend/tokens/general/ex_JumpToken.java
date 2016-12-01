package backend.tokens.general;

import backend.Token;

public class ex_JumpToken extends Token {
    private String label;
    private String condition;

    public ex_JumpToken(String condition, String label) {
        this.label = label;
        this.condition = condition;
    }

    public ex_JumpToken(String label) {
        this.label = label;
        this.condition = "";
    }

    @Override
    public String toString() {
        return "JMP" + condition +  " " + label;
    }
}

