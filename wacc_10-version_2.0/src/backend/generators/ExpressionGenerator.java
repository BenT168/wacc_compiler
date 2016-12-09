package backend.generators;

import antlr.WACCParser;
import backend.data.*;
import backend.label.Label;
import backend.label.LabelType;
import backend.register.Register;
import backend.symtab.Attribute;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.*;

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
            return ctx.PLUS() != null
                    || ctx.DIV() != null
                    || ctx.MINUS().size() == 1 // since '--' is the unary decrement operator.
                    || ctx.MUL() != null
                    || ctx.GREATER() != null
                    || ctx.GREATER_EQUAL() != null
                    || ctx.LESS() != null
                    || ctx.LESS_EQUAL() != null
                    || ctx.DOUBLE_EQUALS() != null
                    || ctx.NOT_EQUAL() != null
                    || ctx.OR() != null
                    || ctx.AND() != null;
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
                    || ctx.LEN() != null
                    || ctx.NOT() != null
                    || ctx.ORD() != null
                    || ctx.MINUS().size() == 2); // Since '-' is the binary subtraction operator,
                                                 // and '--' is the unary decrement operator.
        } catch (NullPointerException e) {
            return false;
        }
    }

    private void generateBinaryExpr(@NotNull WACCParser.ExprContext ctx, Variable place) {

        Variable place1 = newVarFactory.createNewVar();
        Variable place2 = newVarFactory.createNewVar();
        generate(ctx.expr(0), place1);
        generate(ctx.expr(1), place2);

        OpCode opCode;

        Operand operand  = buildOperand(place.toString());
        Operand operand1 = buildOperand(place1.toString());
        Operand operand2 = buildOperand(place2.toString());

        // For comparison expressions
        Instruction cmpInstr = buildInstruction(OpCode.CMP, operand1, operand2);

        if (ctx.MOD() != null) {
            Register r0 = Register.R0_REG;
            Register r1 = Register.R1_REG;

            Operand operand3 = buildOperand(r0.toString());
            Operand operand4 = buildOperand(r1.toString());

            opCode = OpCode.MOV;
            Label label1 = pLabelFactory.createLabel(LabelType.CHECK_DIVIDE_BY_ZERO);
            Label label2 = pLabelFactory.createLabel(LabelType._SPECIAL_MOD);

            Instruction modInstr1        = buildInstruction(opCode, operand3, operand1);
            emit(modInstr1);
            Instruction modInstr2        = buildInstruction(opCode, operand4, operand2);
            emit(modInstr2);
            Instruction branchInstr      = buildInstruction(OpCode.BL, label1);
            emit(branchInstr);
            Instruction special_modInstr = buildInstruction(OpCode.BL, label2);
            emit(special_modInstr);
            Instruction i1               = buildInstruction(OpCode.MOV, operand, operand4);
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
        Operand operand         = buildOperand(place.toString());
        Instruction instruction = buildInstruction(opCode, operand, operand1, operand2);
        emit(instruction);
    }

    private void generateBooleanInstructions(Variable place, OpCode opCode1, OpCode opCode2) {
        int trueVal  = 1;
        int falseVal = 0;

        Operand operand          = buildOperand(place.toString());
        Operand operand1         = buildOperand(String.valueOf(trueVal), OperandType.IMM_OPERAND);
        Operand operand2         = buildOperand(String.valueOf(falseVal), OperandType.IMM_OPERAND);
        Instruction instruction1 = buildInstruction(opCode1, operand, operand1);
        Instruction instruction2 = buildInstruction(opCode2, operand, operand2);
        emit(instruction1);
        emit(instruction2);
    }

    private void generateUnaryExpr(@NotNull WACCParser.ExprContext ctx, Variable place) {
        Variable place1 = newVarFactory.createNewVar();
        generate(ctx.expr(0), place1);
    }

    /**
     * @param ctx Integer node context from 'WACCParser' class.
     * @param place Used to create destination operand in integer load instruction.
     */
    private void generateIntLiter(@NotNull WACCParser.IntLiterContext ctx, Variable place) {
        String intValue = ctx.getText();

        Operand operand1        = buildOperand(place.toString());
        Operand operand2        = buildOperand(intValue, OperandType.DATA_TRANSFER_OPERAND);
        Instruction instruction = buildInstruction(OpCode.LDR, operand1, operand2);
        emit(instruction);
    }

    private void generateCharLiter(@NotNull WACCParser.CharLiterContext ctx, Variable place) {
        String charValue = ctx.getText();

        Operand operand1        = buildOperand(place.toString());
        Operand operand2        = buildOperand(charValue, OperandType.IMM_OPERAND);
        Instruction instruction = buildInstruction(OpCode.MOV,  operand1, operand2);
        emit(instruction);
    }

    private void generateStringLiter(@NotNull WACCParser.StringLiterContext ctx, Variable place) {
        String stringValue = ctx.getText();
        int messageSize    = stringValue.length()-2; // '-2' to account for apostrophes on either side of string.
        Label msg_label    = nLabelFactory.createLabel(LabelType.MESSAGE_LABEL);

        List<Instruction> dataSegmentInstrs = new ArrayList<>();

        Operand dataOperand1   = buildOperand(String.valueOf(messageSize));
        Operand dataOperand2   = buildOperand(stringValue);
        Instruction dataInstr1 = buildInstruction(Directive.WORD, dataOperand1);
        Instruction dataInstr2 = buildInstruction(Directive.ASCII, dataOperand2);

        dataSegmentInstrs.add(dataInstr1);
        dataSegmentInstrs.add(dataInstr2);

        codegenInfo.addToDataSegment(msg_label, dataSegmentInstrs);

        Operand operand1        = buildOperand(place.toString());
        Operand operand2        = buildOperand(msg_label.toString(), OperandType.DATA_TRANSFER_OPERAND);
        Instruction instruction = buildInstruction(OpCode.LDR, operand1, operand2);
        emit(instruction);
    }

    private void generateBoolLiter(@NotNull WACCParser.BoolLiterContext ctx, Variable place) {
        int boolValue = ctx.TRUE() != null ? 1 : 0;

        Operand operand1        = buildOperand(place.toString());
        Operand operand2        = buildOperand(String.valueOf(boolValue), OperandType.IMM_OPERAND);
        Instruction instruction = buildInstruction(OpCode.MOV, operand1, operand2);
        emit(instruction);
    }

    private void generatePairLiter(@NotNull WACCParser.PairLiterContext ctx, Variable place) {
        int pairValue = 0;

        Operand operand1        = buildOperand(place.toString());
        Operand operand2        = buildOperand(String.valueOf(pairValue), OperandType.DATA_TRANSFER_OPERAND);
        Instruction instruction = buildInstruction(OpCode.LDR, operand1, operand2);
        emit(instruction);
    }

    /**
     *
     * @param ctx Identifier node context.
     * @param place Variable into which to load the address of the identifier.
     */
    private void generateIdent(@NotNull WACCParser.IdentContext ctx, Variable place) {
        // Alternatively, we could use registers and map all variables to registers,
        // the do 'place := variableToRegisterMap.getValue(identifier)'.
        // Variable stored = symTabStack.getLast().get(ctx.getText());
        Attribute attribute = symTabStack.getFirst().get(ctx.getText());
        Variable stored     = attribute.getVariable();

        Operand operand1        = buildOperand(place.toString());
        Operand operand2        = buildOperand(stored.toString());
        Instruction instruction = buildInstruction(OpCode.MOV, operand1, operand2);
        emit(instruction);
    }

    private void generateArrayElem(@NotNull WACCParser.ArrayElemContext ctx, Variable place) {
        // Load into 'place', the array reference obtained from the symbol table
        // (i.e: the base pointer to the array). Note that the length of the
        // array is stored at [base_pointer] and the first element at [base_pointer, #4]
        // Generate indexes and load into new variables.
        int fixedArrOffset            = 4;
        Operand fixedArrOffsetOperand = buildOperand(String.valueOf(fixedArrOffset), OperandType.IMM_OPERAND);

        String arrIdentifier = ctx.ident().getText();
        Attribute attribute  = symTabStack.getFirst().get(arrIdentifier);
        Variable arrayRef    = attribute.getVariable();

        OpCode opCode           = OpCode.MOV;
        Operand arrayRefOperand = buildOperand(place.toString());
        Operand operand2        = buildOperand(arrayRef.toString());
        Instruction i1          = buildInstruction(opCode, arrayRefOperand, operand2);
        emit(i1);

        // Holds variables storing values of expressions at the array indexes -
        // from the leftmost index to the rightmost index. We assume that only
        // arithmetic expressions are allowed as indexes. (TODO: should we check
        // this ar runtime, or during the type-checking phase?)
        List<Variable> indexes                  = new ArrayList<>();
        ExpressionGenerator expressionGenerator = new ExpressionGenerator(this);

        for (int i = ctx.expr().size() - 1; i >= 0; i--) {

            Variable var                       = newVarFactory.createNewVar();
            WACCParser.ExprContext exprContext = ctx.expr(i);

            expressionGenerator.generate(exprContext, var);
            indexes.add(var);

            // Checking array bounds

            // Load into the array reference, the value at the array reference
            // Remember, the value at the array reference (i.e: with offset 0)
            // is the length of the array.
            OpCode opCode1             = OpCode.LDR;
            Operand arrayRefMemOperand = buildOperand(place.toString(), OperandType.MEM_ADDR_OPERAND);
            Instruction i2             = buildInstruction(opCode1, arrayRefOperand, arrayRefMemOperand);
            emit(i2);

            OpCode opCode2         = OpCode.MOV;
            Operand reg_r0_operand = buildOperand(Register.R0_REG.toString());
            Operand reg_r1_operand = buildOperand(Register.R1_REG.toString());
            Operand exprVarOperand = buildOperand(var.toString());
            Instruction i3         = buildInstruction(opCode2, reg_r0_operand, exprVarOperand);
            Instruction i4         = buildInstruction(opCode2, reg_r1_operand, arrayRefOperand);
            emit(i3);
            emit(i4);

            // Check array bounds: branch instruction
            Label branchLabel       = pLabelFactory.createLabel(LabelType.CHECK_ARRAY_BOUNDS);
            Instruction branchInstr = buildInstruction(OpCode.BL, branchLabel);

            emit(branchInstr);

            // Add 4-byte offset since array length stored at index 0.
            opCode         = OpCode.ADD;
            Instruction i5 = buildInstruction(opCode, arrayRefOperand, arrayRefMemOperand, fixedArrOffsetOperand);
            emit(i5);

            // Add index multiplied by 4 to account for byte-addressable memory
            // and 4-byte integers.
            int indexShiftValue          = 2;
            Operand lsl_2_exprVarOperand = buildOperand(var.toString(), indexShiftValue, false);
            Instruction i6               = buildInstruction(opCode, arrayRefOperand, arrayRefOperand, lsl_2_exprVarOperand);
            emit(i6);
        }
    }
}
