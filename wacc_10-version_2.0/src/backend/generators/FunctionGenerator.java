package backend.generators;

import antlr.WACCParser;
import backend.data.Variable;

import java.util.HashMap;
import java.util.Map;

class FunctionGenerator extends CodeGenerator {
    private Map<String, Variable> symTab;

    FunctionGenerator(CodeGenerator parent) {
        super(parent);
        this.symTab = new HashMap<>();
        symTabStack.addFirst(this.symTab);
    }

    void generate(WACCParser.FuncContext ctx) {

    }
}
