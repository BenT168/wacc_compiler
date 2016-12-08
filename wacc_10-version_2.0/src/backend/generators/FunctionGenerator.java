package backend.generators;

import antlr.WACCParser;
import backend.data.*;
import backend.label.Label;
import backend.label.LabelType;
import backend.register.Register;
import backend.symtab.Attribute;
import frontend.TypeCheckVisitor;
import frontend.type.Type;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class FunctionGenerator extends CodeGenerator {
    private Map<String, Attribute> symTab;
    private static TypeCheckVisitor typeCheckVisitor = new TypeCheckVisitor();

    FunctionGenerator(CodeGenerator parent) {
        super(parent);
        this.symTab = new HashMap<>();
    }

    void generate(WACCParser.FuncContext ctx) {
        symTabStack.addFirst(this.symTab);
        // Store params in symbol table
        if (ctx.paramList() != null) {
            for (WACCParser.ParamContext paramContext : ctx.paramList().param()) {
                String identifier = paramContext.ident().getText();
                Variable v1 = newVarFactory.createNewVar();
                Type paramType = typeCheckVisitor.visitType(paramContext.type());
                Attribute attribute = new Attribute(v1, true, paramType);

                symTabStack.getFirst().put(identifier, attribute);
            }

        }

        // Emit function label
        LabelType funcLabelType = LabelType.FUNCTION_LABEL;
        funcLabelType.setLabelTypeText(ctx.ident().getText());
        Label funcLabel = pLabelFactory.createLabel(funcLabelType);
        emitLabel(funcLabel);

        Operand operand1 = buildOperand(Register.LR_REG.toString(), OperandType.STACK_OPERAND);
        Instruction i1 = buildInstruction(OpCode.PUSH, operand1);
        emit(i1);

        StatementGenerator statementGenerator = new StatementGenerator(this);
        statementGenerator.generate(ctx.stat());

        operand1 = buildOperand(Register.PC_REG.toString(), OperandType.STACK_OPERAND);
        Instruction i2 = buildInstruction(OpCode.POP, operand1);
        emit(i2);
        emit(i2);
        emitDirective(Directive.LTORG);

        symTabStack.removeFirst();
    }
}
