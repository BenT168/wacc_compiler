package backend.generators;

import antlr.WACCParser;
import backend.data.*;
import backend.label.Label;
import backend.label.LabelType;
import backend.register.Register;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.ArrayList;
import java.util.List;

class ExpressionGenerator extends CodeGenerator {
    ExpressionGenerator(CodeGenerator parent) {
        super(parent);
    }

    void generate(@NotNull WACCParser.ExprContext ctx, Variable place) {
        // Non-strict evaluation in the RHS argument of '&&' ensures we will not
        // get a null pointer exception.
        if (ctx.expr().size() == 2 && isBinaryExprContext(ctx)) {
            generateBinaryExpr(ctx, place);
        } else if (isUnaryExprContext(ctx)) {
            generateUnaryExpr(ctx, place);
        } else if (ctx.intLiter() != null) {
            generateIntLiter(ctx.intLiter(), place);
        } else if (ctx.charLiter() != null) {
            generateCharLiter(ctx.charLiter(), place);
        } else if (ctx.stringLiter() != null) {
            generateStringLiter(ctx.stringLiter(), place);
        } else if (ctx.boolLiter() != null) {
            generateBoolLiter(ctx.boolLiter(), place);
        } else if (ctx.pairLiter() != null) {
            generatePairLiter(ctx.pairLiter(), place);
        } else if (ctx.ident() != null) {
            generateIdent(ctx.ident(), place);
        } else if (ctx.arrayElem() != null) {
            generateArrayElem(ctx.arrayElem(), place);
        } else if (ctx.OPEN_PARENTHESES() != null) {
            generate(ctx.expr(0), place);
        } else {
            //System.err.println("Error: Expression context undecidable\n" + ctx.getText());
        }
    }

    private boolean isBinaryExprContext(@NotNull WACCParser.ExprContext ctx) {
        try {
            return ctx.PLUS() != null || ctx.DIV() != null ||
                    ctx.MUL() != null || ctx.GREATER() != null
                    || ctx.GREATER_EQUAL() != null;
        } catch (NullPointerException e) {
            System.err.println("This should never happen!\n" +
                    "Threw null pointer exception in method 'isBinaryExprContext" +
                    "(WACCParser.ExprContext)'");
            return false;
        }
    }

    private boolean isUnaryExprContext(@NotNull WACCParser.ExprContext ctx) {
        try {
            return (ctx.CHR() != null
                    || ctx.LEN() != null || ctx.NOT() != null
                    || ctx.ORD() != null || ctx.MINUS().size() == 2);
        } catch (NullPointerException e) {
            return false;
        }
    }

    private void generateBinaryExpr(@NotNull WACCParser.ExprContext ctx, Variable place) {
        Variable place1 = newVarFactory.createNewVar();
        Variable place2 = newVarFactory.createNewVar();
        generate(ctx.expr(0), place1);
        generate(ctx.expr(1), place2);

        Operand operand = new OperandBuilderImpl().insertStringInput(place.toString()).build();
        Operand operand1 = new OperandBuilderImpl().insertStringInput(place1.toString()).build();
        Operand operand2 = new OperandBuilderImpl().insertStringInput(place2.toString()).build();
        OpCode opCode;

        // For comparison expressions
        Instruction cmpInstr = new InstructionBuilderImpl().
                insertOpCode(OpCode.CMP).insertOperand(operand1).insertOperand(operand2).build();

        if (ctx.MOD() != null) {
            Register r0 = Register.R0_REG;
            Register r1 = Register.R1_REG;
            Operand operand3 = new OperandBuilderImpl().insertStringInput(r0.toString()).build();
            Operand operand4 = new OperandBuilderImpl().insertStringInput(r1.toString()).build();
            opCode = OpCode.MOV;
            Label label1 = pLabelFactory.createLabel(LabelType.CHECK_DIVIDE_BY_ZERO);
            Label label2 = pLabelFactory.createLabel(LabelType._SPECIAL_MOD);
            Instruction modInstr1 = new InstructionBuilderImpl().
                    insertOpCode(opCode).insertOperand(operand3).insertOperand(operand1).build();
            Instruction modInstr2 = new InstructionBuilderImpl().
                    insertOpCode(opCode).insertOperand(operand4).insertOperand(operand2).build();
            Instruction branchInstr = new InstructionBuilderImpl().
                    insertOpCode(OpCode.BL).insertLabel(label1).build();
            Instruction special_modInstr = new InstructionBuilderImpl().
                    insertOpCode(OpCode.BL).insertLabel(label2).build();
            Instruction i1 = new InstructionBuilderImpl().
                    insertOpCode(OpCode.MOV).insertOperand(operand).insertOperand(operand4).build();
            emit(modInstr1);
            emit(modInstr2);
            emit(branchInstr);
            emit(special_modInstr);
            emit(i1);
        } else if (ctx.GREATER() != null) {
            emit(cmpInstr);
            generateBooleanInstructions(place, OpCode.MOVGT, OpCode.MOVLE);
        } else if (ctx.GREATER_EQUAL() != null) {
            emit(cmpInstr);
            generateBooleanInstructions(place, OpCode.MOVGE, OpCode.MOVLT);
        } else if (ctx.LESS() != null) {
            emit(cmpInstr);
            generateBooleanInstructions(place, OpCode.MOVLT, OpCode.MOVGE);
        } else if (ctx.LESS_EQUAL() != null) {
            emit(cmpInstr);
            generateBooleanInstructions(place, OpCode.MOVLE, OpCode.MOVGT);
        } else if (ctx.DOUBLE_EQUALS() != null) {
            emit(cmpInstr);
            generateBooleanInstructions(place, OpCode.MOVEQ, OpCode.MOVNE);
        } else if (ctx.NOT_EQUAL() != null) {
            emit(cmpInstr);
            generateBooleanInstructions(place, OpCode.MOVNE, OpCode.MOVEQ);
        } else if (ctx.AND() != null) {
            opCode = OpCode.AND;
            generateArithmeticExpression(place, opCode, operand1, operand2);
        } else if (ctx.OR() != null) {
            opCode = OpCode.OR;
            generateArithmeticExpression(place, opCode, operand1, operand2);
        } else if (ctx.MUL() != null) {
            opCode = OpCode.IMUL;
            generateArithmeticExpression(place, opCode, operand1, operand2);
        } else if (ctx.DIV() != null) {
            opCode = OpCode.IDIV;
            generateArithmeticExpression(place, opCode, operand1, operand2);
        } else if (ctx.PLUS() != null) {
            opCode = OpCode.ADD;
            generateArithmeticExpression(place, opCode, operand1, operand2);
        } else if (ctx.MINUS() != null) {
            opCode = OpCode.SUB;
            generateArithmeticExpression(place, opCode, operand1, operand2);
        } else {
            System.err.println("Not a binary expression: " + ctx.getText());
        }
    }

    private void generateArithmeticExpression(Variable place, OpCode opCode, Operand operand1, Operand operand2) {
        Operand operand = new OperandBuilderImpl().
                insertStringInput(place.toString()).build();
        Instruction i1 = new InstructionBuilderImpl().
                insertOpCode(opCode).
                insertOperand(operand).
                insertOperand(operand1).
                insertOperand(operand2).build();
        emit(i1);
    }

    // Helper function for 'generateBinaryExpr()'
    private void generateBooleanInstructions(Variable place, OpCode opCode1, OpCode opCode2) {
        Operand operand = new OperandBuilderImpl().
                insertStringInput(place.toString()).build();
        Operand operand1 = new OperandBuilderImpl().
                insertType(OperandType.IMM_OPERAND).
                insertStringInput(String.valueOf(1)).build();
        Operand operand2 = new OperandBuilderImpl().
                insertType(OperandType.IMM_OPERAND).
                insertStringInput(String.valueOf(0)).build();
        Instruction i1 = new InstructionBuilderImpl().
                insertOpCode(opCode1).
                insertOperand(operand).
                insertOperand(operand1).build();
        Instruction i2 = new InstructionBuilderImpl().
                insertOpCode(opCode2).
                insertOperand(operand).
                insertOperand(operand2).build();
        emit(i1);
        emit(i2);
    }

    private void generateUnaryExpr(@NotNull WACCParser.ExprContext ctx, Variable place) {
        Variable place1 = newVarFactory.createNewVar();
        generate(ctx.expr(0), place1);
    }

    /**
     * @param ctx Integer node context from 'WACCParser' class.
     * @param place Used to create destination operand in integer load instruction.
     */
    public void generateIntLiter(@NotNull WACCParser.IntLiterContext ctx, Variable place) {
        String intValue = ctx.getText();

        Operand operand1 = new OperandBuilderImpl().
                insertStringInput(place.toString()).build();
        Operand operand2 = new OperandBuilderImpl().
                insertType(OperandType.DATA_TRANSFER_OPERAND).
                insertStringInput(intValue).build();
        Instruction i1 = new InstructionBuilderImpl().
                insertOpCode(OpCode.LDR).
                insertOperand(operand1).
                insertOperand(operand2).build();
        emit(i1);
    }

    public void generateCharLiter(@NotNull WACCParser.CharLiterContext ctx, Variable place) {
        String charValue = ctx.getText();

        Operand operand1 = new OperandBuilderImpl().
                insertStringInput(place.toString()).build();
        Operand operand2 = new OperandBuilderImpl().
                insertType(OperandType.IMM_OPERAND).
                insertStringInput(charValue).build();
        Instruction i1 = new InstructionBuilderImpl().
                insertOpCode(OpCode.MOV).
                insertOperand(operand1).
                insertOperand(operand2).build();
        emit(i1);
    }

    public void generateStringLiter(@NotNull WACCParser.StringLiterContext ctx, Variable place) {
        String stringValue = ctx.getText();
        Label msg_label = nLabelFactory.createLabel(LabelType.MESSAGE_LABEL);

        List<Instruction> dataSegmentInstrs = new ArrayList<>();
        Operand data_o1 = new OperandBuilderImpl().
                insertStringInput(String.valueOf(stringValue.length())).build();
        Instruction data_i1 = new InstructionBuilderImpl().
                insertDirective(Directive.WORD).insertOperand(data_o1).build();
        data_o1 = new OperandBuilderImpl().insertStringInput(stringValue).build();
        Instruction data_i2 = new InstructionBuilderImpl().
                insertDirective(Directive.ASCII).insertOperand(data_o1).build();
        dataSegmentInstrs.add(data_i1);
        dataSegmentInstrs.add(data_i2);
        codegenInfo.addToDataSegment(msg_label, dataSegmentInstrs);

        Operand operand1 = new OperandBuilderImpl().
                insertStringInput(place.toString()).build();
        Operand operand2 = new OperandBuilderImpl().
                insertType(OperandType.DATA_TRANSFER_OPERAND).
                insertStringInput(msg_label.toString()).build();
        Instruction i1 = new InstructionBuilderImpl().
                insertOpCode(OpCode.LDR).
                insertOperand(operand1).
                insertOperand(operand2).build();
        emit(i1);
    }

    public void generateBoolLiter(@NotNull WACCParser.BoolLiterContext ctx, Variable place) {
        int boolValue = ctx.TRUE() != null ? 1 : 0;

        Operand operand1 = new OperandBuilderImpl().
                insertStringInput(place.toString()).build();
        Operand operand2 = new OperandBuilderImpl().
                insertType(OperandType.IMM_OPERAND).
                insertStringInput(String.valueOf(boolValue)).build();
        Instruction i1 = new InstructionBuilderImpl().
                insertOpCode(OpCode.MOV).
                insertOperand(operand1).
                insertOperand(operand2).build();
        emit(i1);
    }

    public void generatePairLiter(@NotNull WACCParser.PairLiterContext ctx, Variable place) {
        int pairValue = 0;

        Operand operand1 = new OperandBuilderImpl().insertStringInput(place.toString()).build();
        Operand operand2 = new OperandBuilderImpl().insertStringInput(String.valueOf(pairValue)).
                insertType(OperandType.DATA_TRANSFER_OPERAND).build();
        Instruction i1 = new InstructionBuilderImpl().
                insertOpCode(OpCode.LDR).
                insertOperand(operand1).
                insertOperand(operand2).build();
        emit(i1);
    }

    /**
     *
     * @param ctx Identifier node context.
     * @param place Variable into which to load the address of the identifier.
     */
    public void generateIdent(@NotNull WACCParser.IdentContext ctx, Variable place) {
        // Alternatively, we could use registers and map all variables to registers,
        // the do 'place := variableToRegisterMap.getValue(identifier)'.
        // Variable stored = symTabStack.getLast().get(ctx.getText());
        Variable stored = symTabStack.getFirst().get(ctx.getText());
        Operand operand1 = new OperandBuilderImpl().insertStringInput(place.toString()).build();
        Operand operand2 = new OperandBuilderImpl().insertStringInput(stored.toString()).build();
        Instruction i1 = new InstructionBuilderImpl().
                insertOpCode(OpCode.LDR).
                insertOperand(operand1).
                insertOperand(operand2).build();
        emit(i1);
    }

    private void generateArrayElem(@NotNull WACCParser.ArrayElemContext ctx, Variable place) {
    }
}
