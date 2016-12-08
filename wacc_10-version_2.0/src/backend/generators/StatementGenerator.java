package backend.generators;

import antlr.WACCParser;
import backend.CodegenInfo;
import backend.data.*;
import backend.label.Label;
import backend.label.LabelType;
import backend.register.Register;
import backend.symtab.Attribute;
import frontend.TypeCheckVisitor;
import frontend.type.Type;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.ArrayList;
import java.util.List;

class StatementGenerator extends CodeGenerator {

    private static TypeCheckVisitor typeCheckVisitor = new TypeCheckVisitor();

    StatementGenerator(CodeGenerator parent) {
        super(parent);
    }

    void generate(WACCParser.StatContext ctx) {
        this.visit(ctx);
    }

    @Override
    public CodegenInfo visitSkip(@NotNull WACCParser.SkipContext ctx) {
        Variable v1      = newVarFactory.createNewVar();
        Operand operand1 = buildOperand(v1.toString());
        Operand operand2 = buildOperand(SystemCode.SYSTEM_EXIT.toString(), OperandType.DATA_TRANSFER_OPERAND);
        Instruction i1   = buildInstruction(OpCode.LDR, operand1, operand2);
        emit(i1);

        return codegenInfo;
    }

    @Override
    public CodegenInfo visitDeclare(@NotNull WACCParser.DeclareContext ctx) {
        Variable place = newVarFactory.createNewVar();
        generateAssignRHS(ctx.assignRHS(), place);

        // Rather than maintain a symbol table mapping identifiers in the source language
        // to addresses on the stack, we simply store the values (if they are primitives) or
        // addresses (otherwise) in registers, and use register allocation to ensure that
        // we always have enough registers.
        // If we do not have enough registers, we will perform register spilling during the register allocation stage.
        String identifier = ctx.ident().getText();

        Type type = typeCheckVisitor.visitType(ctx.type());

        Attribute attribute = new Attribute(place, true, type);
        symTabStack.getFirst().put(identifier, attribute);

        return codegenInfo;
    }

    @Override
    public CodegenInfo visitAssign(@NotNull WACCParser.AssignContext ctx) {
        Variable rhsVar = newVarFactory.createNewVar();
        generateAssignRHS(ctx.assignRHS(), rhsVar);
        generateAssignLHS(ctx.assignLHS(), rhsVar, true);

        return codegenInfo;
    }

    private void generateAssignLHS(@NotNull WACCParser.AssignLHSContext ctx, Variable place, boolean isWrite) {
        Instruction instruction = null;

        if (ctx.ident() != null) {
            String identifier   = ctx.ident().getText();
            Attribute attribute = symTabStack.getFirst().get(identifier);
            Variable stored     = attribute.getVariable();
            Operand operand1    = buildOperand(stored.toString());
            Operand operand2    = buildOperand(place.toString());

            if (isWrite) {
                // LDR stored, place
                instruction = buildInstruction(OpCode.LDR, operand1, operand2);
            } else {
                // LDR place, stored
                instruction = buildInstruction(OpCode.LDR, operand2, operand1);
            }
            emit(instruction);
        } else if (ctx.arrayElem() != null) {
            // 'v1' will hold address of indexed array element.
            Variable v1 = newVarFactory.createNewVar();
            generateArrayElem(ctx.arrayElem(), v1);

            Operand operand1 = buildOperand(place.toString());
            Operand operand2 = buildOperand(v1.toString(), OperandType.MEM_ADDR_OPERAND);

            if (isWrite) {
                // STR place, [v1]
                instruction = buildInstruction(OpCode.STR, operand1, operand2);
            } else {
                // LDR place [v1]
                instruction = buildInstruction(OpCode.LDR, operand1, operand2);
            }
            emit(instruction);
        } else if (ctx.pairElem() != null) {
            generatePairElem(ctx.pairElem(), place, isWrite);
        }
    }

    private void generateAssignRHS(@NotNull WACCParser.AssignRHSContext ctx, Variable place) {
        if (ctx.CALL() != null) {
            callStatementGenerator(ctx, place);
        } else if (ctx.NEWPAIR() != null) {
            newPairGenerator(ctx, place);
        } else if (ctx.pairElem() != null) {
            generatePairElem(ctx.pairElem(), place, false);
        } else if (ctx.arrayLiter() != null) {
            arrayLiterGenerator(ctx.arrayLiter(), place);
        } else {
            ExpressionGenerator expressionGenerator = new ExpressionGenerator(this);
            expressionGenerator.generate(ctx.expr(0), place);
        }
    }

    private void callStatementGenerator(@NotNull WACCParser.AssignRHSContext ctx, Variable place) {

        int stackSpaceUsed = 0;
        int memoryPerParam = 4;
        ExpressionGenerator expressionGenerator = new ExpressionGenerator(this);

        if (ctx.argList() != null) {
            for (WACCParser.ExprContext exprCtx : ctx.argList().expr()) {
                expressionGenerator.generate(exprCtx, place);
                Operand operand1 = buildOperand(place.toString());
                Operand operand2 = buildOperand(Register.SP_REG.toString(), OperandType.CALL_OPERAND, -memoryPerParam);
                Instruction i1   = buildInstruction(OpCode.STR, operand1, operand2);
                stackSpaceUsed += memoryPerParam;
            }
        }

        LabelType labelType = LabelType.FUNCTION_LABEL;
        labelType.setLabelTypeText(ctx.ident().getText());
        Label branchLabel = pLabelFactory.createLabel(labelType);
        Instruction branchInstr = buildInstruction(OpCode.BL, branchLabel);
        emit(branchInstr);

        Operand operand1 = buildOperand(Register.SP_REG.toString());
        Operand operand3 = buildOperand(String.valueOf(stackSpaceUsed));
        Instruction i2   = buildInstruction(OpCode.ADD, operand1, operand1, operand3);
        emit(i2);

        // Function results are returned in 'r0'
        operand1         = buildOperand(place.toString());
        Operand operand2 = buildOperand(Register.R0_REG.toString());
        Instruction i3   = buildInstruction(OpCode.MOV, operand1, operand2);
        emit(i3);
    }


    /**
     * Special handling needed for 'pairelem', as it can be used on both the
     * right-hand side and left-hand side of an declaration and assignment.
     * @param ctx Pair element context.
     * @param place Variable in which to load or store the result, depending on
     *              whether we are reading or writing.
     * @param isWrite True if we are writing to the pair element; false if we are
     *                reading from it.
     */
    private void generatePairElem(@NotNull WACCParser.PairElemContext ctx, Variable place, boolean isWrite) {
        Instruction instruction;
        WACCParser.ExprContext pairElemExpr = ctx.expr();

        if (pairElemExpr.ident() == null) {
            System.err.println("Error: Target of pair element expression: '"
                    + pairElemExpr.getText() + "' not an identifier");
            System.exit(-1);
        }

        String identifier   = pairElemExpr.ident().getText();
        Attribute attribute = symTabStack.getFirst().get(identifier);
        Variable stored     = attribute.getVariable();

        // Load into new variable, address of pair; 'LDR v1 stored'
        Variable v1      = newVarFactory.createNewVar();
        Operand operand1 = buildOperand(v1.toString());
        Operand operand2 = buildOperand(stored.toString());
        Instruction i1   = buildInstruction(OpCode.LDR, operand1, operand2);
        emit(i1);

        // Check null pointer
        // MOV r0, v1
        operand2       = operand1;
        operand1       = buildOperand(Register.R0_REG.toString());
        Instruction i2 = buildInstruction(OpCode.MOV, operand1, operand2);
        emit(i2);

        Label branchLabel       = pLabelFactory.createLabel(LabelType.CHECK_NULL_POINTER);
        Instruction branchInstr = buildInstruction(OpCode.BL, branchLabel);
        emit(branchInstr);

        // Dereference pair address with offset to get elem address.
        int offset = 4;
        if (ctx.FST() != null) {
            offset = 0;
        } else if (ctx.SND() != null) {
            offset = 4; // as we have 32-bit addresses
        } else {
            System.err.println("Error: This should never happen!\nPair elem " +
                    "context incorrect: " + ctx.getText());
        }

        // LDR v1, [v1] or LDR v1, [v1, #4]
        operand1       = operand2;
        operand2       = buildOperand(v1.toString(), offset, true);
        Instruction i3 = buildInstruction(OpCode.LDR, operand1, operand2);
        emit(i3);

        operand1       = buildOperand(place.toString());
        operand2       = buildOperand(v1.toString(), OperandType.MEM_ADDR_OPERAND);

        if (isWrite) {
            instruction = buildInstruction(OpCode.STR, operand1, operand2);
        } else {
            instruction = buildInstruction(OpCode.LDR, operand1, operand2);
        }
        emit(instruction); 
    }

    private void generateArrayElem(@NotNull WACCParser.ArrayElemContext ctx, Variable place) {
        // Load into 'place', the array reference obtained from the symbol table
        // (i.e: the base pointer to the array). Note that the length of the
        // array is stored at [base_pointer] and the first element at [base_pointer, #4]
        // Generate indexes and load into new variables.
        // 'arrayVar' holds the variable that holds the address of the array
        // 'place' holds the variable that holds the address of the particular
        // element in the array that we wish to access.
        int fixedArrOffset = 4;
        Operand fixedArrOffsetOperand = buildOperand(String.valueOf(fixedArrOffset), OperandType.IMM_OPERAND);

        String arrayIdentifier = ctx.ident().getText();
        Attribute attribute    = symTabStack.getFirst().get(arrayIdentifier);
        Variable arrayVar      = attribute.getVariable();

        OpCode opCode            = OpCode.LDR;
        Operand arrayElemOperand = buildOperand(place.toString());
        Operand arrayVarOperand  = buildOperand(arrayVar.toString());
        Instruction i1           = buildInstruction(opCode, arrayElemOperand, arrayVarOperand);
        emit(i1);

        // Accessing address of particular element in array.
        List<Variable> indexes = new ArrayList<>();
        ExpressionGenerator expressionGenerator = new ExpressionGenerator(this);

        for (int i = ctx.expr().size() - 1; i >= 0; i--) {
            WACCParser.ExprContext exprContext = ctx.expr(i);
            Variable var = newVarFactory.createNewVar();
            expressionGenerator.generate(exprContext, var);
            indexes.add(var);

            // Get length of array.
            // To do this, we access the array element at the address of the
            // array, with an offset 0.
            OpCode opCode1 = OpCode.LDR;

            Operand arrayElemMemOperand = buildOperand(place.toString(), OperandType.MEM_ADDR_OPERAND);
            Instruction i2              = buildInstruction(opCode1, arrayElemOperand, arrayElemMemOperand);
            emit(i2);

            // Check array bounds
            opCode1 = OpCode.MOV;

            Operand reg_r0_operand = buildOperand(Register.R0_REG.toString());
            Operand reg_r1_operand = buildOperand(Register.R1_REG.toString());
            Operand exprVarOperand = buildOperand(var.toString());
            Instruction i3 = buildInstruction(opCode1, reg_r0_operand, exprVarOperand);
            Instruction i4 = buildInstruction(opCode1, reg_r1_operand, arrayElemOperand);
            emit(i3);
            emit(i4);

            // Check array bounds: branch instruction
            Label branchLabel = pLabelFactory.createLabel(LabelType.CHECK_ARRAY_BOUNDS);
            Instruction branchInstr = buildInstruction(OpCode.BL, branchLabel);
            emit(branchInstr);

            // Add 4-byte offset to move the array element pointer to the first
            // element of the array.
            opCode = OpCode.ADD;
            Instruction i5 = buildInstruction(opCode, arrayElemOperand, arrayElemOperand, fixedArrOffsetOperand);
            emit(i5);

            // Add index, as specified by result of expression evaluation,
            // multiplied by 4 to account for byte-addressable memory
            // and 4-byte integers.
            int indexShiftValue = 2;
            Operand lsl_2_exprVarOperand = buildOperand(var.toString(), indexShiftValue, false);
            Instruction i6 = buildInstruction(opCode, arrayElemOperand, arrayElemOperand, lsl_2_exprVarOperand);
            emit(i6);
        }
    }

    private void newPairGenerator(@NotNull WACCParser.AssignRHSContext ctx, Variable place) {
        List<Instruction> block = new ArrayList<>();
        
        int mallocSize = 8;

        Operand operand1 = buildOperand(Register.R0_REG.toString());
        Operand operand2 = buildOperand(String.valueOf(mallocSize), OperandType.DATA_TRANSFER_OPERAND);
        Instruction i1   = buildInstruction(OpCode.LDR, operand1, operand2);
        emit(i1);

        // BL malloc
        Label malloc_label       = pLabelFactory.createLabel(LabelType.MALLOC);
        Instruction malloc_instr = buildInstruction(OpCode.BL, malloc_label);
        emit(malloc_instr);

        // Move allocated heap address into pair reference variable - 'place'
        operand2       = operand1;
        operand1       = buildOperand(place.toString());
        Instruction i3 = buildInstruction(OpCode.MOV, operand1, operand2);
        emit(i3);

        Variable v1                             = newVarFactory.createNewVar();
        ExpressionGenerator expressionGenerator = new ExpressionGenerator(this);
        expressionGenerator.generate(ctx.expr(0), v1);

        mallocSize = 4;
        operand1       = operand2;
        operand2       = buildOperand(String.valueOf(mallocSize), OperandType.DATA_TRANSFER_OPERAND);
        Instruction i4 = buildInstruction(OpCode.LDR, operand1, operand2);
        emit(i4);

        emit(malloc_instr);

        // Store variable 'v1' at allocated heap address
        operand1       = buildOperand(v1.toString());
        operand2       = buildOperand(Register.R0_REG.toString(), OperandType.MEM_ADDR_OPERAND);
        Instruction i5 = buildInstruction(OpCode.STR, operand1, operand2);
        emit(i5);

        // Store address of first expression at address in pair reference with offset 0
        operand1       = buildOperand(Register.R0_REG.toString());
        operand2       = buildOperand(place.toString(), OperandType.MEM_ADDR_OPERAND);
        Instruction i6 = buildInstruction(OpCode.STR, operand1, operand2);
        emit(i6);

        Variable v2 = newVarFactory.createNewVar();
        expressionGenerator.generate(ctx.expr(1), v2);

        operand2       = buildOperand(String.valueOf(mallocSize), OperandType.DATA_TRANSFER_OPERAND);
        Instruction i7 = buildInstruction(OpCode.LDR, operand1, operand2);
        emit(i7);

        emit(malloc_instr);

        // Store variable 'v2' at allocated heap address
        operand1       = buildOperand(v2.toString());
        operand2       = buildOperand(Register.R0_REG.toString(), OperandType.MEM_ADDR_OPERAND);
        Instruction i8 = buildInstruction(OpCode.STR, operand1, operand2);
        emit(i8);

        // Store address of first expression at address in pair reference with offset 4
        int offset1 = 4;
        operand1       = buildOperand(Register.R0_REG.toString());
        operand2       = buildOperand(place.toString(), offset1, true);
        Instruction i9 = buildInstruction(OpCode.STR, operand1, operand2);
        emit(i9);
    }

    private void arrayLiterGenerator(WACCParser.ArrayLiterContext ctx, Variable place) {
        List<Instruction> block = new ArrayList<>();
        // 4 extra bytes for storing length
        int mallocSize = ctx.expr().size()*4 + 4;

        Operand operand1 = buildOperand(Register.R0_REG.toString());
        Operand operand2 = buildOperand(String.valueOf(mallocSize), OperandType.DATA_TRANSFER_OPERAND);
        Instruction i1   = buildInstruction(OpCode.LDR, operand1, operand2);
        emit(i1);

        // BL malloc
        Label malloc_label       = pLabelFactory.createLabel(LabelType.MALLOC);
        Instruction malloc_instr = buildInstruction(OpCode.BL, malloc_label);
        emit(malloc_instr);

        // Move allocated heap address into array reference - 'place'
        operand2       = operand1;
        operand1       = buildOperand(place.toString());
        Instruction i3 = buildInstruction(OpCode.MOV, operand1, operand2);
        emit(i3);

        Variable v1                             = newVarFactory.createNewVar();
        ExpressionGenerator expressionGenerator = new ExpressionGenerator(this);

        for (int i = 0; i < ctx.expr().size(); i++) {
            expressionGenerator.generate(ctx.expr(i), v1);
            // Store variable for first expression at address of array reference with offset '(i+1)*4'
            int offset1 = (i+1)*4;
            String memAddrStr = place.toString();

            operand1       = buildOperand(v1.toString());
            operand2       = buildOperand(memAddrStr, offset1, true);
            Instruction i4 = buildInstruction(OpCode.STR, operand1, operand2);
            emit(i4);
        }

        // Load array length into variable 'v1'
        int arrayLength = ctx.expr().size();

        operand2       = buildOperand(String.valueOf(arrayLength), OperandType.DATA_TRANSFER_OPERAND);
        Instruction i5 = buildInstruction(OpCode.LDR, operand1, operand2);
        emit(i5);

        // Store length at address of array reference with offset 0
        int offset     = 0;
        operand2       = buildOperand(place.toString(), offset, true);
        Instruction i6 = buildInstruction(OpCode.STR, operand1, operand2);
        emit(i6);
    }

    @Override
    public CodegenInfo visitRead(@NotNull WACCParser.ReadContext ctx) {
        if (ctx.assignLHS() == null) {
            System.err.println("In Read Statement: No target:\n" + ctx.getText());
            System.exit(-1);
        }

        Variable place = newVarFactory.createNewVar();
        generateAssignLHS(ctx.assignLHS(), place, false);

        Operand operand1 = buildOperand(Register.R0_REG.toString());
        Operand operand2 = buildOperand(place.toString());
        Instruction i1   = buildInstruction(OpCode.MOV, operand1, operand2);
        emit(i1);

        Label branchLabel = pLabelFactory.createLabel(LabelType.READ_INT);
        codegenInfo.addPredefLabelRef(branchLabel);

        Instruction i2    = buildInstruction(OpCode.BL, branchLabel);
        emit(i2);

        return codegenInfo;
    }

    @Override
    public CodegenInfo visitFree(@NotNull WACCParser.FreeContext ctx) {
        return super.visitFree(ctx);
    }

    @Override
    public CodegenInfo visitReturn(@NotNull WACCParser.ReturnContext ctx) {
        Variable place = newVarFactory.createNewVar();
        ExpressionGenerator expressionGenerator = new ExpressionGenerator(this);
        expressionGenerator.generate(ctx.expr(), place);

        Operand operand1 = buildOperand(Register.R0_REG.toString());
        Operand operand2 = buildOperand(place.toString());
        Instruction i1   = buildInstruction(OpCode.MOV, operand1, operand2);
        emit(i1);

        operand1       = buildOperand(Register.PC_REG.toString(), OperandType.STACK_OPERAND);
        Instruction i2 = buildInstruction(OpCode.POP, operand1);
        emit(i2);

        return codegenInfo;
    }

    @Override
    public CodegenInfo visitExit(@NotNull WACCParser.ExitContext ctx) {
        Variable place = newVarFactory.createNewVar();
        ExpressionGenerator expressionGenerator = new ExpressionGenerator(this);
        expressionGenerator.generate(ctx.expr(), place);

        Operand operand1 = buildOperand(Register.R0_REG.toString());
        Operand operand2 = buildOperand(place.toString());
        Instruction i1   = buildInstruction(OpCode.MOV, operand1, operand2);
        emit(i1);

        Label label = pLabelFactory.createLabel(LabelType.EXIT);
        Instruction i2 = buildInstruction(OpCode.BL, label);
        emit(i2);

        return codegenInfo;
    }

    @Override
    public CodegenInfo visitPrint(@NotNull WACCParser.PrintContext ctx) {
        Variable place                          = newVarFactory.createNewVar();
        ExpressionGenerator expressionGenerator = new ExpressionGenerator(this);
        expressionGenerator.generate(ctx.expr(), place);

        Label targetLabel = pLabelFactory.createLabel(LabelType.PRINT_STRING);
        codegenInfo.addPredefLabelRef(targetLabel);

        Instruction i1 = buildInstruction(OpCode.BL, targetLabel);
        emit(i1);

        return codegenInfo;
    }

    @Override
    public CodegenInfo visitPrintln(@NotNull WACCParser.PrintlnContext ctx) {
        Variable place                          = newVarFactory.createNewVar();
        ExpressionGenerator expressionGenerator = new ExpressionGenerator(this);
        expressionGenerator.generate(ctx.expr(), place);

        Label targetLabel = pLabelFactory.createLabel(LabelType.PRINT_LINE);
        codegenInfo.addPredefLabelRef(targetLabel);

        Instruction i1 = buildInstruction(OpCode.BL, targetLabel);
        emit(i1);

        return codegenInfo;
    }

    @Override
    public CodegenInfo visitIfElse(@NotNull WACCParser.IfElseContext ctx) {
        List<Instruction> block = new ArrayList<>();

        ExpressionGenerator expressionGenerator = new ExpressionGenerator(this);
        StatementGenerator statementGenerator   = new StatementGenerator(this);

        Label newLabel = nLabelFactory.createLabel(LabelType.DEFAULT);

        // Generate code for boolean expression
        Variable place = newVarFactory.createNewVar();
        expressionGenerator.generate(ctx.expr(), place);

        // Generate code for statements.
        Label falseLabel = nLabelFactory.createLabel(LabelType.DEFAULT);
        Instruction i1   = buildInstruction(OpCode.BEQ, falseLabel);
        emit(i1);

        statementGenerator.generate(ctx.stat(0));

        Instruction i2 = buildInstruction(OpCode.B, newLabel);
        emit(i2);

        emitLabel(falseLabel);
        statementGenerator.generate(ctx.stat(1));
        emitLabel(newLabel);

        return codegenInfo;
    }

    @Override
    public CodegenInfo visitWhile(@NotNull WACCParser.WhileContext ctx) {
        // Branch instruction
        Label condLabel          = nLabelFactory.createLabel(LabelType.DEFAULT);
        Instruction branchInstr1 = buildInstruction(OpCode.B, condLabel);
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
        Operand operand1  = buildOperand(v1.toString());
        Operand operand2  = buildOperand(String.valueOf(trueCondValue), OperandType.IMM_OPERAND);
        Instruction i1    = buildInstruction(OpCode.CMP, operand1, operand2);
        emit(i1);

        // Branch to body on equality instruction
        Instruction i2 = buildInstruction(OpCode.BEQ, trueLabel);
        emit(i2);

        return codegenInfo;
    }

    @Override
    public CodegenInfo visitBegin(@NotNull WACCParser.BeginContext ctx) {
        return super.visitBegin(ctx);
    }
}
