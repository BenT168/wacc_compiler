package backend.generators;

import antlr.WACCParser;
import backend.CodegenInfo;
import backend.data.*;
import backend.label.Label;
import backend.label.LabelType;
import backend.label.NumberedLabelFactory;
import backend.register.Register;
import main.WACC;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.HashMap;
import java.util.Map;

class StatementGenerator extends CodeGenerator {

    StatementGenerator(CodeGenerator parent) {
        super(parent);
    }

    void generate(WACCParser.StatContext ctx) {
        this.visit(ctx);
    }

    @Override
    public CodegenInfo visitSkip(@NotNull WACCParser.SkipContext ctx) {
        Variable v1 = newVarFactory.createNewVar();
        Operand operand1 = new OperandBuilderImpl().insertStringInput(v1.toString()).build();
        Operand operand2 = new OperandBuilderImpl().
                insertStringInput(String.valueOf(0)).
                insertType(OperandType.DATA_TRANSFER_OPERAND).build();
        Instruction i1 = new InstructionBuilderImpl().
                insertOpCode(OpCode.LDR).
                insertOperand(operand1).
                insertOperand(operand2).build();
        emit(i1);
        return codegenInfo;
    }

    @Override
    public CodegenInfo visitDeclare(@NotNull WACCParser.DeclareContext ctx) {
        Variable place = newVarFactory.createNewVar();
        if (ctx.assignRHS().CALL() != null) {

        } else if (ctx.assignRHS().NEWPAIR() != null) {
            newPairGenerator(ctx.assignRHS(), place);
        } else if (ctx.assignRHS().pairElem() != null) {

        } else if (ctx.assignRHS().arrayLiter() != null) {
            arrayLiterGenerator(ctx.assignRHS().arrayLiter(), place);
        } else {
            ExpressionGenerator expressionGenerator = new ExpressionGenerator(this);
            expressionGenerator.generate(ctx.assignRHS().expr(0), place);
        }
        // Rather than maintain a symbol table mapping identifiers in the source language
        // to addresses on the stack, we simply store the values (if they are primitives) or
        // addresses (otherwise) in registers, and use register allocation to ensure that
        // we always have enough registers.
        // If we do not have enough registers, we will perform register spilling during the register allocation stage.
        String identifier = ctx.ident().getText();
        symTabStack.getFirst().put(identifier, place);
        return codegenInfo;
    }

    @Override
    public CodegenInfo visitAssign(@NotNull WACCParser.AssignContext ctx) {
        Variable place;
        String identifier;
        if (ctx.assignLHS().ident() != null) {
            identifier = ctx.assignLHS().ident().getText();
            place = symTabStack.getFirst().get(identifier);
        } else if (ctx.assignLHS().arrayElem() != null) {
            place = newVarFactory.createNewVar();
            generateArrayElem(ctx.assignLHS().arrayElem(), place);
        } else if (ctx.assignLHS().pairElem() != null) {

        }
        return codegenInfo;
    }

    private void generateArrayElem(@NotNull WACCParser.ArrayElemContext ctx, Variable place) {
        // Access array element at stated index and load into new variable.
        Variable v1 = newVarFactory.createNewVar();
        int offset = 0;
        for (int i = ctx.expr().size(); i >= 0; i--) {
            // Assuming only integer literal expressions are allowed.

        }
        Operand operand1 = new OperandBuilderImpl().insertStringInput(v1.toString()).build();
        Operand operand2 = new OperandBuilderImpl().insertStringInput(place.toString()).build();
    }

    private void newPairGenerator(@NotNull WACCParser.AssignRHSContext ctx, Variable place) {
        int mallocSize = 8;

        Operand operand1 = new OperandBuilderImpl().insertStringInput(Register.R0_REG.toString()).build();
        Operand operand2 = new OperandBuilderImpl().
                insertStringInput(String.valueOf(mallocSize)).
                insertType(OperandType.DATA_TRANSFER_OPERAND).build();
        Instruction i1 = new InstructionBuilderImpl().
                insertOpCode(OpCode.LDR).insertOperand(operand1).insertOperand(operand2).build();
        emit(i1);

        // BL malloc
        Operand label_operand = new OperandBuilderImpl().insertStringInput(LabelType.MALLOC.toString()).build();
        Instruction malloc_instr = new InstructionBuilderImpl().insertOpCode(OpCode.BL).insertOperand(label_operand).build();
        emit(malloc_instr);

        // Move allocated heap address into pair reference variable - 'place'
        operand1 = new OperandBuilderImpl().insertStringInput(place.toString()).build();
        operand2 = new OperandBuilderImpl().insertStringInput(Register.R0_REG.toString()).build();
        Instruction i3 = new InstructionBuilderImpl().
                insertOpCode(OpCode.MOV).insertOperand(operand1).insertOperand(operand2).build();
        emit(i3);

        ExpressionGenerator expressionGenerator = new ExpressionGenerator(this);
        Variable v1 = newVarFactory.createNewVar();
        expressionGenerator.generate(ctx.expr(0), v1);

        mallocSize = 4;
        operand1 = new OperandBuilderImpl().insertStringInput(Register.R0_REG.toString()).build();
        operand2 = new OperandBuilderImpl().
                insertStringInput(String.valueOf(mallocSize)).
                insertType(OperandType.DATA_TRANSFER_OPERAND).build();
        Instruction i4 = new InstructionBuilderImpl().
                insertOpCode(OpCode.LDR).insertOperand(operand1).insertOperand(operand2).build();
        emit(i4);

        emit(malloc_instr);

        // Store variable 'v1' at allocated heap address
        operand1 = new OperandBuilderImpl().insertStringInput(v1.toString()).build();
        operand2 = new OperandBuilderImpl().
                insertStringInput(Register.R0_REG.toString()).insertType(OperandType.MEM_ADDR_OPERAND).build();
        Instruction i5 = new InstructionBuilderImpl().
                insertOpCode(OpCode.STR).insertOperand(operand1).insertOperand(operand2).build();
        emit(i5);

        // Store address of first expression at address in pair reference with offset 0
        operand1 = new OperandBuilderImpl().insertStringInput(Register.R0_REG.toString()).build();
        operand2 = new OperandBuilderImpl().
                insertStringInput(place.toString()).insertType(OperandType.MEM_ADDR_OPERAND).build();
        Instruction i6 = new InstructionBuilderImpl().
                insertOpCode(OpCode.STR).insertOperand(operand1).insertOperand(operand2).build();
        emit(i6);

        Variable v2 = newVarFactory.createNewVar();
        expressionGenerator.generate(ctx.expr(1), v2);

        operand1 = new OperandBuilderImpl().insertStringInput(Register.R0_REG.toString()).build();
        operand2 = new OperandBuilderImpl().
                insertStringInput(String.valueOf(mallocSize)).
                insertType(OperandType.DATA_TRANSFER_OPERAND).build();
        Instruction i7 = new InstructionBuilderImpl().
                insertOpCode(OpCode.LDR).insertOperand(operand1).insertOperand(operand2).build();
        emit(i7);

        emit(malloc_instr);

        // Store variable 'v2' at allocated heap address
        operand1 = new OperandBuilderImpl().insertStringInput(v2.toString()).build();
        operand2 = new OperandBuilderImpl().
                insertStringInput(Register.R0_REG.toString()).insertType(OperandType.MEM_ADDR_OPERAND).build();
        Instruction i8 = new InstructionBuilderImpl().
                insertOpCode(OpCode.STR).insertOperand(operand1).insertOperand(operand2).build();
        emit(i8);

        // Store address of first expression at address in pair reference with offset 4
        operand1 = new OperandBuilderImpl().insertStringInput(Register.R0_REG.toString()).build();
        operand2 = new OperandBuilderImpl().
                insertStringInput(place.toString() + ", #4").insertType(OperandType.MEM_ADDR_OPERAND).build();
        Instruction i9 = new InstructionBuilderImpl().
                insertOpCode(OpCode.STR).insertOperand(operand1).insertOperand(operand2).build();
        emit(i9);
    }

    private void arrayLiterGenerator(WACCParser.ArrayLiterContext ctx, Variable place) {
        // 4 extra bytes for storing length
        int mallocSize = ctx.expr().size() + 4;

        Operand operand1 = new OperandBuilderImpl().insertStringInput(Register.R0_REG.toString()).build();
        Operand operand2 = new OperandBuilderImpl().
                insertStringInput(String.valueOf(mallocSize)).
                insertType(OperandType.DATA_TRANSFER_OPERAND).build();
        Instruction i1 = new InstructionBuilderImpl().
                insertOpCode(OpCode.LDR).insertOperand(operand1).insertOperand(operand2).build();
        emit(i1);

        // BL malloc
        Operand label_operand = new OperandBuilderImpl().insertStringInput(LabelType.MALLOC.toString()).build();
        Instruction malloc_instr = new InstructionBuilderImpl().insertOpCode(OpCode.BL).insertOperand(label_operand).build();
        emit(malloc_instr);

        // Move allocated heap address into array reference variable - 'place'
        operand1 = new OperandBuilderImpl().insertStringInput(place.toString()).build();
        operand2 = new OperandBuilderImpl().insertStringInput(Register.R0_REG.toString()).build();
        Instruction i3 = new InstructionBuilderImpl().
                insertOpCode(OpCode.MOV).insertOperand(operand1).insertOperand(operand2).build();
        emit(i3);


        Variable v1 = newVarFactory.createNewVar();
        ExpressionGenerator expressionGenerator = new ExpressionGenerator(this);
        for (int i = 0; i < ctx.expr().size(); i++) {
            WACCParser.ExprContext exprCtx = ctx.expr(i);
            expressionGenerator.generate(ctx.expr(i), v1);
            // Store variable for first expression at address of array reference with offset '(i+1)*4'
            String memAddrStr = place.toString() + ", #" + String.valueOf((i+1)*4);
            operand1 = new OperandBuilderImpl().insertStringInput(v1.toString()).build();
            operand2 = new OperandBuilderImpl().
                    insertStringInput(memAddrStr).insertType(OperandType.MEM_ADDR_OPERAND).build();
            Instruction i4 = new InstructionBuilderImpl().
                    insertOpCode(OpCode.STR).insertOperand(operand1).insertOperand(operand2).build();
            emit(i4);
        }

        operand1 = new OperandBuilderImpl().insertStringInput(v1.toString()).build();
        int arrayLength = ctx.expr().size();
        operand2 = new OperandBuilderImpl().
                insertStringInput(String.valueOf(arrayLength)).
                insertType(OperandType.DATA_TRANSFER_OPERAND).build();
        Instruction i5 = new InstructionBuilderImpl().
                insertOpCode(OpCode.LDR).
                insertOperand(operand1).
                insertOperand(operand2).
                build();
        emit(i5);

        // Store length at address of array reference with offset 0
        operand2 = new OperandBuilderImpl().
                insertStringInput(place.toString()).
                insertType(OperandType.MEM_ADDR_OPERAND).build();
        Instruction i6 = new InstructionBuilderImpl().
                insertOpCode(OpCode.STR).
                insertOperand(operand1).
                insertOperand(operand2).build();
        emit(i6);
    }

    @Override
    public CodegenInfo visitRead(@NotNull WACCParser.ReadContext ctx) {
        return super.visitRead(ctx);
    }

    @Override
    public CodegenInfo visitFree(@NotNull WACCParser.FreeContext ctx) {
        return super.visitFree(ctx);
    }

    @Override
    public CodegenInfo visitReturn(@NotNull WACCParser.ReturnContext ctx) {
        return super.visitReturn(ctx);
    }

    @Override
    public CodegenInfo visitExit(@NotNull WACCParser.ExitContext ctx) {
        return super.visitExit(ctx);
    }

    @Override
    public CodegenInfo visitPrint(@NotNull WACCParser.PrintContext ctx) {
        ExpressionGenerator expressionGenerator = new ExpressionGenerator(this);
        Variable place = newVarFactory.createNewVar();
        expressionGenerator.generate(ctx.expr(), place);

        Label targetLabel = pLabelFactory.createLabel(LabelType.PRINT_STRING);
        codegenInfo.addPredefLabelRef(targetLabel);
        Instruction i1 = new InstructionBuilderImpl().insertOpCode(OpCode.BL).insertLabel(targetLabel).build();
        emit(i1);

        return codegenInfo;
    }

    @Override
    public CodegenInfo visitPrintln(@NotNull WACCParser.PrintlnContext ctx) {
        ExpressionGenerator expressionGenerator = new ExpressionGenerator(this);
        Variable place = newVarFactory.createNewVar();
        expressionGenerator.generate(ctx.expr(), place);

        Label targetLabel = pLabelFactory.createLabel(LabelType.PRINT_LINE);
        codegenInfo.addPredefLabelRef(targetLabel);
        Instruction i1 = new InstructionBuilderImpl().insertOpCode(OpCode.BL).insertLabel(targetLabel).build();
        emit(i1);

        return codegenInfo;
    }

    @Override
    public CodegenInfo visitIfElse(@NotNull WACCParser.IfElseContext ctx) {
        ExpressionGenerator expressionGenerator = new ExpressionGenerator(this);
        StatementGenerator statementGenerator = new StatementGenerator(this);

        NumberedLabelFactory nLabelFactory = new NumberedLabelFactory();
        Label newLabel = nLabelFactory.createLabel(LabelType.DEFAULT);

        // Generate code for boolean expression
        Variable place = newVarFactory.createNewVar();
        expressionGenerator.generate(ctx.expr(), place);

        // Generate code for statements.
        Label falseLabel = nLabelFactory.createLabel(LabelType.DEFAULT);
        Instruction i1 = new InstructionBuilderImpl().insertOpCode(OpCode.BEQ).insertLabel(falseLabel).build();
        emit(i1);
        statementGenerator.generate(ctx.stat(0));
        Instruction i2 = new InstructionBuilderImpl().insertOpCode(OpCode.B).insertLabel(newLabel).build();
        emit(i2);
        emitLabel(falseLabel);
        statementGenerator.generate(ctx.stat(1));
        emitLabel(newLabel);

        return codegenInfo;
    }

    @Override
    public CodegenInfo visitWhile(@NotNull WACCParser.WhileContext ctx) {
        // Branch instruction
        Label condLabel = nLabelFactory.createLabel(LabelType.DEFAULT);
        Instruction branchInstr1 = new InstructionBuilderImpl().
                insertOpCode(OpCode.B).
                insertLabel(condLabel).build();
        emit(branchInstr1);

        // True condition label and while body code generation
        Label trueLabel = nLabelFactory.createLabel(LabelType.DEFAULT);
        emitLabel(trueLabel);
        generate(ctx.stat());

        // Boolean condition (i.e: expression) code generation
        emitLabel(condLabel);
        Variable v1 = newVarFactory.createNewVar();
        ExpressionGenerator expressionGenerator = new ExpressionGenerator(this);
        expressionGenerator.generate(ctx.expr(), v1);

        // Comparison instruction
        int trueCondValue = 1;
        Operand operand1 = new OperandBuilderImpl().insertStringInput(v1.toString()).build();
        Operand operand2 = new OperandBuilderImpl().
                insertStringInput(String.valueOf(trueCondValue)).
                insertType(OperandType.IMM_OPERAND).build();
        Instruction i1 = new InstructionBuilderImpl().
                insertOpCode(OpCode.CMP).
                insertOperand(operand1).
                insertOperand(operand2).build();
        emit(i1);

        // Branch to body on equality instruction
        Instruction i2 = new InstructionBuilderImpl().
                insertOpCode(OpCode.B).
                insertLabel(trueLabel).build();
        emit(i2);

        return codegenInfo;
    }

    @Override
    public CodegenInfo visitBegin(@NotNull WACCParser.BeginContext ctx) {
        return super.visitBegin(ctx);
    }
}
