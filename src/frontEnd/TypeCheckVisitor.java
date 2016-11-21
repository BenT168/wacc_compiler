package frontEnd;

import antlr.WACCParser;
import antlr.WACCParserBaseVisitor;
import frontEnd.exception.SemanticException;
import frontEnd.exception.SyntaxException;
import frontEnd.expr.BinaryExprNode;
import frontEnd.expr.UnaryExprNode;
import frontEnd.stat.*;
import frontEnd.type.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.ArrayList;
import java.util.List;

public class TypeCheckVisitor extends WACCParserBaseVisitor<Type> {

    /* For Storing Variables and its information */
    private SymbolTable typeEnv;
    private boolean inFunction = false;
    private boolean isMultipleStat = false;
    private boolean returnCheck = true;

    public TypeCheckVisitor() {
        this.typeEnv = new SymbolTable();
    }


    //................................PROGRAM.........................................
    @Override
    public Type visitProgram(@NotNull WACCParser.ProgramContext ctx) {

        // We use two passes: one for adding the function identifiers to the symbol table,
        // another for evaluating the function bodies.

        if(ctx.func() != null) {
            for (WACCParser.FuncContext funcCtx : ctx.func()) {
                String i = funcCtx.ident().IDENTITY().getText();

                if (typeEnv.fTableContainsKey(i)) {
                    throw new SemanticException("Function already contains key: " + i);
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
                returnCheck = false;
                visitFunc(funcCtx);
                typeEnv.removeScope();
                if (!returnCheck && inFunction) {
                    throw new SyntaxException("No return statement");
                }
            }

        }

        // Evaluate "main function" body
        typeEnv.enterScope();
        visit(ctx.stat());
        typeEnv.removeScope();

        return null;
    }

    //....................................FUNCTION......................................

    // Function identifiers and types should already be added to ftable by time we call this method.
    @Override
    public Type visitFunc(@NotNull WACCParser.FuncContext ctx) {
        inFunction = true; // start of function declaration

        Type defined = visitType(ctx.type()); // function return type according to definition

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
            throw new NullPointerException("Null pointer");
        }
        if(actual != null) {
            if(!(defined.equals(actual))) {
                throw new SemanticException("Function: " + ctx.ident().getText()
                        + " \nExpected return type: " + defined.toString() +
                        " \nActual return type: " + actual.toString());
            }
        }
        typeEnv.removeScope(); // end of new scope

        return null;
    }


    //...................................PARAMETER......................................

    @Override
    public Type visitParamList(@NotNull WACCParser.ParamListContext ctx) {
        return super.visitParamList(ctx);
    }

    @Override
    public Type visitParam(@NotNull WACCParser.ParamContext ctx) {
        return visitType(ctx.type());
    }


    //....................................STAT........................................

    /*SKIP*/
    @Override
    public Type visitSkip(@NotNull WACCParser.SkipContext ctx) {
        return null;
    }

    /* type IDENTITY EQUALS assignRHS */
    @Override
    public Type visitDeclare(@NotNull WACCParser.DeclareContext ctx) {
        Type type1 = visitType(ctx.type());
        String name = ctx.ident().IDENTITY().getText();
        typeEnv.vTableInsert(name, type1);
        Type type2 = visitAssignRHS(ctx.assignRHS());
        new VarDecNode(type1, type2).check();
        return null;
    }

    /* assignLHS EQUALS assignRHS  */
    @Override
    public Type visitAssign(@NotNull WACCParser.AssignContext ctx) {
        Type t1 = visitAssignLHS(ctx.assignLHS());
        Type t2 = visitAssignRHS(ctx.assignRHS());
        new Assignment(t1, t2).check();
        return null;
    }

    /*PRINT expr*/
    @Override
    public Type visitPrint(@NotNull WACCParser.PrintContext ctx) {
        return visitExpr(ctx.expr());
    }

    /*PRINTLN expr*/
    @Override
    public Type visitPrintln(@NotNull WACCParser.PrintlnContext ctx) {
        return visitExpr(ctx.expr());
    }


    /*READ assignLHS */
    @Override
    public Type visitRead(@NotNull WACCParser.ReadContext ctx) {
        new ReadStatNode(visitAssignLHS(ctx.assignLHS())).check();
        return null;
    }

    /*FREE expr */
    @Override
    public Type visitFree(@NotNull WACCParser.FreeContext ctx) {
        new FreeStat(visitExpr(ctx.expr())).check();
        return null;
    }

    /*EXIT expr */
    @Override
    public Type visitExit(@NotNull WACCParser.ExitContext ctx) {
        new ExitStat(visitExpr(ctx.expr())).check();
        return null;
    }

    /*RETURN expr*/
    @Override
    public Type visitReturn(@NotNull WACCParser.ReturnContext ctx) {
        // Knows nothing about enclosing function and hence leaves type checking to enclosing function visitor method
        Type ret = visitExpr(ctx.expr());
        typeEnv.removeScope();
        inFunction = false;

        if (typeEnv.getvTableScopes().size() == 1) {
            returnCheck = true;
        }
        return ret;
    }

    /*IF ELSE stat*/
    @Override
    public Type visitIfElse(@NotNull WACCParser.IfElseContext ctx) {
        new IfElseStatNode(visitExpr(ctx.expr()), ctx.expr().getText()).check();
        // Visit branches in conditional. If Statement conditional only has 2 branches
        for (int i = 0; i < 2; i++) {
            typeEnv.enterScope();
            visit(ctx.stat(i));
            typeEnv.removeScope();
        }
        return null;
    }

    /*WHILE stat*/
    @Override
    public Type visitWhile(@NotNull WACCParser.WhileContext ctx) {
        new WhileStatNode(visitExpr(ctx.expr()), ctx.expr().getText()).check();
        // examine body of while
        typeEnv.enterScope();
        visit(ctx.stat());
        typeEnv.removeScope();
        return null;
    }

    /*BEGIN stat END*/
    @Override
    public Type visitBegin(@NotNull WACCParser.BeginContext ctx) {
        typeEnv.enterScope();
        visit(ctx.stat());
        typeEnv.removeScope();
        return null;
    }

    /*stat ; stat*/
    @Override
    public Type visitMultipleStat(@NotNull WACCParser.MultipleStatContext ctx) {
        //Boolean for function to check how many stats is has
        isMultipleStat = true;
        boolean seenReturn = false;
        int pos = 0;
        int lastStatementIndex = ctx.stat().size()-1;

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
            //if in function, then throw syntax error
            if (inFunction) {
                throw new SyntaxException("Function does not have a return statement.");
            }
            throw new SemanticException("Statement after return. Unreachable statement.");
        }

        // visit all statements sequentially
        ctx.stat().forEach(this::visit);
        return null;
    }


//..................................ASSIGNMENT......................................

    /*assign-lhs*/
    @Override
    public Type visitAssignLHS(@NotNull WACCParser.AssignLHSContext ctx) {
        Type t = null;
        if (ctx.ident() != null) {
            t = typeEnv.varLookup(ctx.ident().IDENTITY().getText());
        } else if (ctx.arrayElem() != null) {
            t = visitArrayElem(ctx.arrayElem());
            if(t.equals(new BaseType(BaseTypeEnum.STRING))) {
                //one element of string array is a char
                t = new BaseType(BaseTypeEnum.CHAR);
            } else {
                t = t.reduce();
            }
        } else if (ctx.pairElem() != null) {
            t = visitPairElem(ctx.pairElem());
        } else {
            throw new SemanticException("Error in expression:" +
                    ctx.getText() + "\nin method 'visitAssignLHS");

        }
        return t;
    }

    /*assign-rhs*/
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

            if ((types.size()-1) != sizeOfExprsCxt){
                throw new SemanticException("Invalid number of arguments in call declaration:\n" +
                        "Expecting:" +
                        " " + (types.size()-1) + "\nActual: " + exprCtxs.size());

            }

            for (int j = 1; j < types.size(); j++) {
                Type temp1 = types.get(j);
                Type temp2 = visitExpr(exprCtxs.get(j - 1));

                if (!(temp1.equals(temp2))) {
                    throw new SemanticException("Type mismatch error:\nExpecting: " +
                            temp1.toString() + "\nActual: " + temp2.toString());
                }
            }
        } else if (ctx.arrayLiter() != null) {
            t = visitArrayLiter(ctx.arrayLiter());
        } else if (ctx.pairElem() != null) {
            t = visitPairElem(ctx.pairElem());
        } else if (ctx.expr() != null) {
            t = visitExpr(ctx.expr(0));
        } else {
            throw new SemanticException("Error in 'visitAssignRHS' method.");
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
            System.err.print("In expression: " + ctx.getText() +
                    "\nExpecting type: Pair" + "\nActual type: " + t.toString());
        }
        if (ctx.FST() != null) {
            t = ((PairType) t).getType1();
        } else {
            t = ((PairType) t).getType2();
        }
        return t;
    }

//..................................TYPE......................................

    @Override
    public Type visitType(@NotNull WACCParser.TypeContext ctx) {
        if (ctx.baseType() != null) {
            return visitBaseType(ctx.baseType());
        } else if (ctx.arrayType() != null) {
            return visitArrayType(ctx.arrayType());
        } else if (ctx.pairType() != null) {
            return visitPairType(ctx.pairType());
        } else {
            throw new SemanticException("Error in 'visitType' method");
        }
    }

    @Override
    public Type visitBaseType(@NotNull WACCParser.BaseTypeContext ctx) {
        if (ctx.BOOL() != null) {
            return new BaseType(BaseTypeEnum.BOOL);
        } else if (ctx.CHAR() != null) {
            return new BaseType(BaseTypeEnum.CHAR);
        } else if (ctx.INT() != null) {
            return new BaseType(BaseTypeEnum.INT);
        } else if (ctx.STRING() != null) {
            return new BaseType(BaseTypeEnum.STRING);
        } else {
            throw new SemanticException("Error in 'visitBaseType' method");
        }

    }

    @Override
    public Type visitArrayType(@NotNull WACCParser.ArrayTypeContext ctx) {
        Type t = null;
        if (ctx.baseType() != null) {
            //Check if Array of an array e.g c[][]
            if(ctx.CLOSE_SQUARE().size() > 1) {
                t = new ArrayType(visitBaseType(ctx.baseType()));
            } else {
                t = visitBaseType(ctx.baseType());
            }
        } else if (ctx.pairType() != null) {
            t = visitPairType(ctx.pairType());
        } else {
            throw new SemanticException("Error in method 'visitArrayType'");
        }
        return new ArrayType(t);
    }

    @Override
    public Type visitPairType(@NotNull WACCParser.PairTypeContext ctx) {

        List<WACCParser.PairElemTypeContext> contexts = ctx.pairElemType();

        if (ctx.pairElemType().size() != 2) {
            System.err.println("Invalid number of parameter types in pair type constructor: "
                    + ctx.pairElemType().size());
        }

        List<Type> ls = new ArrayList<>();
        Type t = null;

        for (WACCParser.PairElemTypeContext context : contexts) {
            if (context.baseType() != null) {
                t = visitBaseType(context.baseType());
            } else if (context.arrayType() != null) {
                t = visitArrayType(context.arrayType());
            } else if (context.PAIR() != null) {
                // 'null' arguments signify a nested pair.
                t = new PairType(null, null);
            } else {
                throw new SemanticException("Error in method 'visitPairType'");
            }
            ls.add(t);
        }
        return new PairType(ls.get(0), ls.get(1));
    }

    @Override
    public Type visitPairElemType(@NotNull WACCParser.PairElemTypeContext ctx) {
        return super.visitPairElemType(ctx);
    }



    //..................................EXPRESSION......................................

    @Override
    public Type visitExpr(@NotNull WACCParser.ExprContext ctx) {
        Type type = null;
        if(ctx.intLiter()!= null) {
            long value = Long.parseLong(ctx.intLiter().INTEGER().getText());
            if (ctx.intLiter().PLUS() == null) {
                value = Math.negateExact(value);
            }
            if (!(Scalar.isAcceptableInt(value))) {
                throw new SyntaxException("Integer is outside acceptable range.");
            }
            type = new BaseType(BaseTypeEnum.INT);
        } else if(ctx.boolLiter() != null) {
            type = new BaseType(BaseTypeEnum.BOOL);
        } else if(ctx.charLiter() != null) {
            type = new BaseType(BaseTypeEnum.CHAR);
        } else if(ctx.stringLiter() != null) {
            type = new BaseType(BaseTypeEnum.STRING);
        } else if(ctx.CHR() != null || ctx.ORD() != null || ctx.LEN() != null || ctx.NOT() != null
                || ctx.MINUS() != null) {
            type = visitUnaryExpr(ctx);
        } else if (ctx.expr().size() == 2) { //binary operations
            type = visitBinaryExpr(ctx);
        } else if(ctx.arrayElem() != null) {
            type = visitArrayElem(ctx.arrayElem());
        } else if(ctx.ident() != null) {
            type = visitIdent(ctx.ident());
        } else if(ctx.OPEN_PARENTHESES() != null) {
            type = visitExpr(ctx.expr(0));
        }
        return type;
    }

    public Type visitUnaryExpr(@NotNull WACCParser.ExprContext ctx) {
        Type argType = visit(ctx.expr(0));
        return (Type) new UnaryExprNode(argType, ctx).check();
    }

    public Type visitBinaryExpr(@NotNull WACCParser.ExprContext ctx) {
        Type lhs = visitExpr(ctx.expr(0));
        Type rhs = visitExpr(ctx.expr(1));
        return (Type) new BinaryExprNode(lhs, rhs, ctx).check();
    }

    @Override
    public Type visitIdent(@NotNull WACCParser.IdentContext ctx) {
        return typeEnv.varLookup(ctx.IDENTITY().getText());
    }

    @Override
    public Type visitArrayElem(@NotNull WACCParser.ArrayElemContext ctx) {
        Type t1 = visitIdent(ctx.ident());
        // Array element's type is determined by the type of the first
        // expression.

        //Check if Array and get type of first element
        if(t1 instanceof ArrayType) {
            t1 = t1.reduce();
        }
        Type t2 = visitExpr(ctx.expr(0));
        Type temp = new BaseType(BaseTypeEnum.INT);
        if (!(t2.equals(temp))) {
            throw new SemanticException("In expression: " +
                    ctx.getText() + "\nExpecting type: "
                    + temp.toString() +"\nActual type: " +
                    t2.toString());

        }
        return t1;
    }

    @Override
    public Type visitBoolLiter(@NotNull WACCParser.BoolLiterContext ctx) {
        return new BaseType(BaseTypeEnum.BOOL);
    }

    @Override
    public Type visitCharLiter(@NotNull WACCParser.CharLiterContext ctx) {
        return new BaseType(BaseTypeEnum.CHAR);
    }

    @Override
    public Type visitStringLiter(@NotNull WACCParser.StringLiterContext ctx) {
        return new BaseType(BaseTypeEnum.STRING);
    }

    @Override
    public Type visitIntLiter(@NotNull WACCParser.IntLiterContext ctx) {
        return new BaseType(BaseTypeEnum.INT);
    }

    @Override
    public Type visitArrayLiter(@NotNull WACCParser.ArrayLiterContext ctx) {
        // The array literal's type is determined by the type of the first expression.
        Type t = visitExpr(ctx.expr(0));
        for (int i = 1; i < ctx.expr().size(); i++) {
            Type temp = visitExpr(ctx.expr(i));
            if (!(t.equals(temp))) {
                System.err.printf("Conflicting types in array literal:\nAt index %d: %s\nAt index: %d: %s\n",
                        (i-1), t.toString(), i, temp.toString());
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
