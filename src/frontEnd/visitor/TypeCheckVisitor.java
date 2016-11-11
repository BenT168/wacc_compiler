package frontEnd.visitor;

import antlr.WACCParser;
import antlr.WACCParserBaseVisitor;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class TypeCheckVisitor extends WACCParserBaseVisitor<Type> {

    private SymbolTable typeEnv;
    private boolean returned = false;
    private boolean inFunction = false;

    public TypeCheckVisitor() {
        this.typeEnv = new SymbolTable();
    }

    @Override
    public Type visitProgram(@NotNull WACCParser.ProgramContext ctx) {

        // We use two passes: one for adding the function identifiers to the symbol table,
        // another for evaluating the function bodies.

        if(ctx.func() != null) {
            for (WACCParser.FuncContext funcCtx : ctx.func()) {
                String i = funcCtx.ident().IDENTITY().getText();

                if (typeEnv.fTableContainsKey(i)) {
                    System.err.println("Function already contains key: " + i);
                    System.exit(200);
                }

                Type t = visitType(funcCtx.type());
                List<Type> paramTypes = new ArrayList<>();
                paramTypes.add(t);
                if(funcCtx.paramList() != null) {
                    for (WACCParser.ParamContext pCtx : funcCtx.paramList().param()) {
                        paramTypes.add(visitParam(pCtx));
                    }
                }
                typeEnv.fTableInsert(i, paramTypes);
            }

            for (WACCParser.FuncContext funcCtx : ctx.func()) {
                typeEnv.enterScope();
                visitFunc(funcCtx);
                typeEnv.removeScope();
            }
        }

            // Evaluate "main function" body
            typeEnv.enterScope();
            visit(ctx.stat());
            typeEnv.removeScope();

        return null;
    }

    // Function identifiers and types should already be added to ftable by time we call this method.
    @Override
    public Type visitFunc(@NotNull WACCParser.FuncContext ctx) {
        inFunction = true; // start of function declaration
        Type defined = visitType(ctx.type());

        // loop through and store parameters into Symbol Table
        if (ctx.paramList() != null) {
            for (WACCParser.ParamContext ctxParam: ctx.paramList().param()) {
                String i = ctxParam.ident().IDENTITY().getText();
                Type t = visitParam(ctxParam);
                typeEnv.vTableInsert(i, t);
            }
        }

        typeEnv.enterScope(); // new scope
        Type actual = null;
        try {
            actual = visit(ctx.stat());
        } catch (NullPointerException e) {
            // do nothing
        }
        if(!(defined.equals(actual))) {
            System.err.print("Function: " + ctx.ident().getText()
                + " \nExpected return type: " + defined.toString() + " \nActual return type: " + actual.toString());
            System.exit(200);
        }
        typeEnv.removeScope(); // end of new scope

        inFunction = false; // end of function declaration

        return null;
    }

    @Deprecated
    @Override
    public Type visitParamList(@NotNull WACCParser.ParamListContext ctx) {
        return super.visitParamList(ctx);
    }

    @Override
    public Type visitParam(@NotNull WACCParser.ParamContext ctx) {
        return visitType(ctx.type());
    }

    @Override
    public Type visitSkip(@NotNull WACCParser.SkipContext ctx) {
        return null;
    }

    @Override
    public Type visitDeclare(@NotNull WACCParser.DeclareContext ctx) {
        String name = ctx.ident().IDENTITY().getText();
        Type type1 = visitType(ctx.type());
        Type type2 = visitAssignRHS(ctx.assignRHS());
        new VariableDeclarationAST(type1, name, type2).check();
        typeEnv.vTableInsert(name, type1);
        return null;
    }

    @Override
    public Type visitAssign(@NotNull WACCParser.AssignContext ctx) {
        Type t1 = visitAssignLHS(ctx.assignLHS());
        Type t2 = visitAssignRHS(ctx.assignRHS());
        new AssignmentAST(t1, t2).check();
        return null;
    }

    @Override
    public Type visitRead(@NotNull WACCParser.ReadContext ctx) {
        new ReadAST(visitAssignLHS(ctx.assignLHS())).check();
        return null;
    }

    @Override
    public Type visitFree(@NotNull WACCParser.FreeContext ctx) {
        new FreeAST(visitExpr(ctx.expr())).check();
        return null;
    }

    @Override
    public Type visitReturn(@NotNull WACCParser.ReturnContext ctx) {
        // Knows nothing about enclosing function and hence leaves type checking to enclosing function visitor method
        Type ret = visitExpr(ctx.expr());
        typeEnv.removeScope();
        return ret;
    }

    @Override
    public Type visitExit(@NotNull WACCParser.ExitContext ctx) {
        new ExitAST(visitExpr(ctx.expr())).check();
        return null;
    }

    @Override
    public Type visitPrint(@NotNull WACCParser.PrintContext ctx) {
        return visitExpr(ctx.expr());
    }

    @Override
    public Type visitPrintln(@NotNull WACCParser.PrintlnContext ctx) {
        return visitExpr(ctx.expr());
    }

    @Override
    public Type visitIfElse(@NotNull WACCParser.IfElseContext ctx) {
        new IfElseAST(visitExpr(ctx.expr()), ctx.expr().getText()).check();
        // Visit branches in conditional. If Statement conditional only has 2 branches
        for (int i = 0; i < 2; i++) {
            typeEnv.enterScope();
            visit(ctx.stat(i));
            typeEnv.removeScope();
        }
        return null;
    }

    @Override
    public Type visitWhile(@NotNull WACCParser.WhileContext ctx) {
        new WhileAST(visitExpr(ctx.expr()), ctx.expr().getText()).check();
        // examine body of while
        typeEnv.enterScope();
        visit(ctx.stat());
        typeEnv.removeScope();
        return null;
    }

    @Override
    public Type visitBegin(@NotNull WACCParser.BeginContext ctx) {
        typeEnv.enterScope();
        visit(ctx.stat());
        typeEnv.removeScope();
        return null;
    }

    @Override
    public Type visitMultipleStat(@NotNull WACCParser.MultipleStatContext ctx) {
        boolean seenReturn = false;
        int pos = 0;

        // looking up return statements
        for(int i = 0; i < ctx.stat().size(); i++) {
            if(ctx.stat(i).getText().matches("return(.*)")) {
                seenReturn = true;
                pos = i;
            }
        }

        // if in the top-level scope there is any statement past the return statement
        // then that should cause an error
        if(seenReturn && pos != ctx.stat().size() - 1) {
            System.err.print("Statement after return. Unreachable statement.");
            System.exit(200);
        }

        // visit all statements sequentially
        ctx.stat().forEach(this::visit);
        return null;
    }

    @Override
    public Type visitAssignLHS(@NotNull WACCParser.AssignLHSContext ctx) {
        Type t = null;
        if (ctx.ident() != null) {
            t = typeEnv.varLookup(ctx.ident().IDENTITY().getText(), typeEnv.getvTableScopes());
        } else if (ctx.arrayElem() != null) {
            t = visitArrayElem(ctx.arrayElem());
            if(t.equals(new BaseType(BaseTypeCode.STRING))) {
                //one element of string array is a char
                t = new BaseType(BaseTypeCode.CHAR);
            } else {
                t = t.reduce();
            }
        } else if (ctx.pairElem() != null) {
            t = visitPairElem(ctx.pairElem());
        } else {
            System.err.println("Error in expression:" + ctx.getText() + "\nin method 'visitAssignLHS");
            System.exit(200);
        }
        return t;
    }

    @Override
    public Type visitAssignRHS(@NotNull WACCParser.AssignRHSContext ctx) {
        Type t = null;
        if (ctx.NEWPAIR() != null) {
            Type t1 = visitExpr(ctx.expr(0));
            Type t2 = visitExpr(ctx.expr(1));
            t = new PairType(t1, t2);
        } else if (ctx.CALL() != null) {
            String i = ctx.ident().IDENTITY().getText();
            List<Type> types = typeEnv.funcLookup(i);
            List<WACCParser.ExprContext> exprCtxs = null;

            //t is return type of function
            t = typeEnv.funcLookup(ctx.ident().getText()).get(0);

            int sizeOfExprsCxt = 0;

            if (ctx.argList() != null) {
                exprCtxs = ctx.argList().expr();
                sizeOfExprsCxt = exprCtxs.size();
            }

            // TODO: Could cause null pointer exception
            if ((types.size()-1) != sizeOfExprsCxt){
                System.err.println("Invalid number of arguments in call declaration:\nExpecting: " + (types.size()-1) + "\nActual: " + exprCtxs.size());
                System.exit(200);
            }
            
            for (int j = 1; j < types.size(); j++) {
                Type temp1 = types.get(j);
                Type temp2 = visitExpr(exprCtxs.get(j - 1));

                if (!(temp1.equals(temp2))) {
                    System.err.println("Type mismatch error:\nExpecting: " + temp1.toString() + "\nActual: " + temp2.toString());
                    System.exit(200);
                }
            }
        } else if (ctx.arrayLiter() != null) {
            t = visitArrayLiter(ctx.arrayLiter());
        } else if (ctx.pairElem() != null) {
            t = visitPairElem(ctx.pairElem());
        } else if (ctx.expr() != null) {
            t = visitExpr(ctx.expr(0));
        } else {
            System.err.println("Error in 'visitAssignRHS' method.");
            System.exit(200);
        }
        return t;
    }

    @Override
    public Type visitArgList(@NotNull WACCParser.ArgListContext ctx) {
        return super.visitArgList(ctx);
    }

    @Override
    public Type visitPairElem(@NotNull WACCParser.PairElemContext ctx) {
        Type t = visitExpr(ctx.expr());
        if (!(t instanceof PairType)) {
            System.err.println("In expression: " + ctx.getText() + "\nExpecting type: Pair" + "\nActual type: " + t.toString());
        }
        if (ctx.FST() != null) {
            t = ((PairType) t).getType1();
        } else {
            t = ((PairType) t).getType2();
        }
        return t;
    }

    @Override
    public Type visitType(@NotNull WACCParser.TypeContext ctx) {
        if (ctx.baseType() != null) {
            return visitBaseType(ctx.baseType());
        } else if (ctx.arrayType() != null) {
            return visitArrayType(ctx.arrayType());
        } else if (ctx.pairType() != null) {
            return visitPairType(ctx.pairType());
        } else {
            System.err.println("Error in 'visitType' method");
            System.exit(200);
        }
        return null;
    }

    @Override
    public Type visitBaseType(@NotNull WACCParser.BaseTypeContext ctx) {
        if (ctx.BOOL() != null) {
            return new BaseType(BaseTypeCode.BOOL);
        } else if (ctx.CHAR() != null) {
            return new BaseType(BaseTypeCode.CHAR);
        } else if (ctx.INT() != null) {
            return new BaseType(BaseTypeCode.INT);
        } else if (ctx.STRING() != null) {
            return new BaseType(BaseTypeCode.STRING);
        } else {
            System.err.println("Error in 'visitBaseType' method");
            System.exit(200);
        }
        return null;
    }

    @Override
    public Type visitArrayType(@NotNull WACCParser.ArrayTypeContext ctx) {
        Type t = null;
        if (ctx.baseType() != null) {
            t = visitBaseType(ctx.baseType());
        } else if (ctx.pairType() != null) {
            t = visitPairType(ctx.pairType());
        } else {
            System.err.println("Error in method 'visitArrayType'");
            System.exit(200);
        }
        return new ArrayType(t);
    }

    @Override
    public Type visitPairType(@NotNull WACCParser.PairTypeContext ctx) {
        List<WACCParser.PairElemTypeContext> ctxs = ctx.pairElemType();
        if (ctx.pairElemType().size() != 2) {
            System.err.println("Invalid number of parameter types in pair type constructor: " + ctx.pairElemType().size());
        }
        List<Type> ls = new ArrayList<>();
        Type t = null;
        for (WACCParser.PairElemTypeContext x: ctxs) {
            if (x.baseType() != null) {
                t = visitBaseType(x.baseType());
            } else if (x.arrayType() != null) {
                t = visitArrayType(x.arrayType());
            } else {
                System.err.println("Error in method 'visitPairType'");
                System.exit(200);
            }
            ls.add(t);
        }
        return new PairType(ls.get(0), ls.get(1));
    }

    @Override
    public Type visitPairElemType(@NotNull WACCParser.PairElemTypeContext ctx) {
        return super.visitPairElemType(ctx);
    }

    @Override
    public Type visitExpr(@NotNull WACCParser.ExprContext ctx) {
        Type type = null;
        if(ctx.intLiter()!= null) {
            type = new BaseType(BaseTypeCode.INT);
        } else if(ctx.boolLiter() != null) {
            type = new BaseType(BaseTypeCode.BOOL);
        } else if(ctx.charLiter() != null) {
            type = new BaseType(BaseTypeCode.CHAR);
        } else if(ctx.stringLiter() != null) {
            type = new BaseType(BaseTypeCode.STRING);
        } else if(ctx.CHR() != null || ctx.ORD() != null || ctx.LEN() != null || ctx.NOT() != null
                || ctx.MINUS() != null) {
            type = visitUnaryExpr(ctx);
        } else if (ctx.expr().size() == 2) { //binary operations
            type = visitBinaryExpr(ctx);
        } else if(ctx.arrayElem() != null) {
            type = visitArrayElem(ctx.arrayElem());
        } else if(ctx.ident() != null) {
            type = visitIdent(ctx.ident(), typeEnv.getvTableScopes());
        } else if(ctx.OPEN_PARENTHESES() != null) {
            type = visitExpr(ctx.expr(0));
        }
        return type;
    }

    public Type visitUnaryExpr(@NotNull WACCParser.ExprContext ctx) {
        Type argType = visit(ctx.expr(0));
        return (Type) new UnaryExprAST(argType, ctx).check();
    }

    public Type visitBinaryExpr(@NotNull WACCParser.ExprContext ctx) {
        Type lhs = visitExpr(ctx.expr(0));
        Type rhs = visitExpr(ctx.expr(1));
        return (Type) new BinaryExprAST(lhs, rhs, ctx).check();
    }

        // We redefine the identifier visit method
    public Type visitIdent(@NotNull WACCParser.IdentContext ctx, LinkedList<HashMap<String, Type>> symTabScopes) {
        return typeEnv.varLookup(ctx.IDENTITY().getText(), symTabScopes);
    }

    @Override
    public Type visitArrayElem(@NotNull WACCParser.ArrayElemContext ctx) {
        Type t1 = visitIdent(ctx.ident(), typeEnv.getvTableScopes());
        // Array element's type is determined by the type of the first
        // expression.
        Type t2 = visitExpr(ctx.expr(0));
        Type temp = new BaseType(BaseTypeCode.INT);
        // TODO: must check all indices
        if (!(t2.equals(temp))) {
            System.err.println("In expression: " + ctx.getText() + "\nExpecting type: " + temp.toString() +"\nActual type: " + t2.toString());
            System.exit(200);
        }
        return t1;
    }

    @Override
    public Type visitBoolLiter(@NotNull WACCParser.BoolLiterContext ctx) {
        return new BaseType(BaseTypeCode.BOOL);
    }

    @Override
    public Type visitCharLiter(@NotNull WACCParser.CharLiterContext ctx) {
        return new BaseType(BaseTypeCode.CHAR);
    }

    @Override
    public Type visitStringLiter(@NotNull WACCParser.StringLiterContext ctx) {
        return new BaseType(BaseTypeCode.STRING);
    }

    @Override
    public Type visitIntLiter(@NotNull WACCParser.IntLiterContext ctx) {
        return new BaseType(BaseTypeCode.INT);
    }

    @Override
    public Type visitArrayLiter(@NotNull WACCParser.ArrayLiterContext ctx) {
        // The array literal's type is determined by the type of the first expression.
        Type t = visitExpr(ctx.expr(0));
        for (int i = 1; i < ctx.expr().size(); i++) {
            Type temp = visitExpr(ctx.expr(i));
            if (!(t.equals(temp))) {
                System.err.printf("Conflicting types in array literal:\nAt index %d: %s\nAt index: %d: %s\n", (i-1), t.toString(), i, temp.toString());
            }
            t = temp;
        }
        return new ArrayType(t);
    }

    @Override
    public Type visitPairLiter(@NotNull WACCParser.PairLiterContext ctx) {
        return new PairType(null, null);
    }
}
