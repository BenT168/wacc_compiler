package frontEnd.visitor;

import antlr.BasicParser;
import antlr.BasicParserBaseVisitor;
import frontEnd.ErrorHandling.Exception;
import frontEnd.ErrorHandling.IncompatibleTypesException;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.gui.SystemFontMetrics;

import javax.net.ssl.SSLContext;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class TypeCheckVisitor extends BasicParserBaseVisitor<Type> {

    // Holds symbol table information needed by our type checker
    private static class TypeEnv {

        // Identifiers are strings
        private LinkedList<HashMap<String, Type>> vTableScopes = new LinkedList<>();
        private HashMap<String, Type> fTable = new HashMap<>();

        private Type varLookup(String key, LinkedList<HashMap<String, Type>> symTabScopes) {
            Type res;
            for (HashMap<String, Type> scope: vTableScopes) {
                res = scope.get(key);
                if (res != null) {
                    return res;
                }
            }
            // TODO: throw error, identifier unbound in current scope or any enclosing scopes
            System.err.println("Variable identifier: " + key + " unbound in current scope or any enclosing scopes");
            System.exit(200);
            return null;
        }
        
        private Type funcLookup(String key) {
            if (!(fTable.containsKey(key))) {
                System.err.println("Function " + key + " doesn't exist in symbol table");
                System.exit(200);
            }
            return fTable.get(key);
        }

        // Function symbol table insert method
        private void fTableInsert(String key, Type value) {
            if (fTable.containsKey(key)) {
                // TODO: Throw error, function already bound
                System.err.println("Function identifier: " + key + " already in scope");
                System.exit(200);
            }
            fTable.put(key, value);
        }

        // Variable symbol table insert method
        private void vTableInsert(String key, Type value) {
            // We cannot insert an identifier twice. This is a semantic error
            // in the program.
            if (vTableScopes.getFirst().containsKey(key)) {
                System.err.println("Variable identifier: " + key + " already in scope");
                System.exit(200);
            }
            vTableScopes.getFirst().put(key, value);
        }

        private void enterScope() {
            vTableScopes.addFirst(new HashMap<>());
        }

        private void removeScope() {
            vTableScopes.removeFirst();
        }
    }

    private TypeEnv typeEnv;

    public TypeCheckVisitor() {
        this.typeEnv = new TypeEnv();
    }

    @Override
    public Type visitProgram(@NotNull BasicParser.ProgramContext ctx) {

        // We use two passes: one for added the function identifiers to the symbol table, another for evaluating the
        // function bodies.

        for (BasicParser.FuncContext funcCtx: ctx.func()) {
            String i = funcCtx.ident().IDENTITY().getText();

            if (typeEnv.fTable.containsKey(i)) {
                System.err.println("Function already contains key: " + i);
                System.exit(200);
            }

            Type t = visitType(funcCtx.type());
            typeEnv.fTableInsert(i, t);
        }

        for (BasicParser.FuncContext funcCtx: ctx.func()) {
            typeEnv.enterScope();
            visitFunc(funcCtx);
            typeEnv.removeScope();
        }

        // Evaluate "main function" body
        typeEnv.enterScope();
        Type t = visit(ctx.stat());
        typeEnv.removeScope();
        
        return null;
    }

    // Function identifiers and types should already be added to ftable by time we call this method.
    @Override
    public Type visitFunc(@NotNull BasicParser.FuncContext ctx) {

        // Function return type
        Type t0 = visitType(ctx.type());

        if (ctx.paramList() != null) {
            for (BasicParser.ParamContext ctxParam: ctx.paramList().param()) {
                String i = ctxParam.ident().IDENTITY().getText();
                Type t = visitParam(ctxParam);

                if (!(typeEnv.varLookup(i, typeEnv.vTableScopes) == null)) {
                    System.err.println("Identifier: " + i + " already bound");
                    System.exit(200);
                }

                typeEnv.vTableInsert(i, t);
            }
        }

        typeEnv.enterScope();
        Type t1 = visit(ctx.stat());
        typeEnv.removeScope();

        if (!(t0.equals(t1))) {
            System.err.println("Type mismatch:\nExpected: " + t0.toString() + "\nActual: " + t1.toString());
            System.exit(200);
        }
        return null;
    }

    @Deprecated
    @Override
    public Type visitParamList(@NotNull BasicParser.ParamListContext ctx) {
        return super.visitParamList(ctx);
    }

    @Override
    public Type visitParam(@NotNull BasicParser.ParamContext ctx) {
        return visitType(ctx.type());
    }

    @Override
    public Type visitSkip(@NotNull BasicParser.SkipContext ctx) {
        return null;
    }

    @Override
    public Type visitDeclare(@NotNull BasicParser.DeclareContext ctx) {
        Type t1 = visitType(ctx.type());
        String i = ctx.ident().IDENTITY().getText();

        typeEnv.vTableInsert(i, t1);

        Type t2 = visitAssignRHS(ctx.assignRHS());

        if (!(t1.equals(t2))) {
            System.err.println("Type mismatch error:\nExpected: " + t1.toString() + "\nActual: " + t2.toString());
            System.exit(200);
        }
        return null;
    }

    @Override
    public Type visitAssign(@NotNull BasicParser.AssignContext ctx) {
        Type t1 = visitAssignLHS(ctx.assignLHS());
        Type t2 = visitAssignRHS(ctx.assignRHS());

        if (!(t1.equals(t2))) {
            System.err.println("Type mismatch error:\nExpected: " + t1.toString() + "\nActual: " + t2.toString());
            System.exit(200);
        }
        return null;
    }

    @Override
    public Type visitRead(@NotNull BasicParser.ReadContext ctx) {
        Type t = visitAssignLHS(ctx.assignLHS());

        if (!(t.equals(new BaseType(BaseTypeCode.CHAR)) || t.equals(new BaseType(BaseTypeCode.INT)))) {
            System.err.println("In 'Read' statement:\nExpecting type: 'INT' or 'CHAR' in expression\nActual type: " + t.toString());
            System.exit(200);
        }
        return null;
    }

    @Override
    public Type visitFree(@NotNull BasicParser.FreeContext ctx) {
        Type t = visitExpr(ctx.expr());

        // Nulls signify generic arrays/pairs.
        if (!(t instanceof PairType || t instanceof ArrayType)) {
            System.err.println("In 'Free' statement:\nExpecting type: PAIR or ARRAY\nActual type: " + t.toString());
            System.exit(200);
        }
        return null;
    }

    @Override
    public Type visitReturn(@NotNull BasicParser.ReturnContext ctx) {
        // Knows nothing about enclosing function and hence leaves type checking to enclosing function visitor method
        return visitExpr(ctx.expr());
    }

    @Override
    public Type visitExit(@NotNull BasicParser.ExitContext ctx) {
        Type t = visitExpr(ctx.expr());
        Type temp = new BaseType(BaseTypeCode.INT);
        if (!(t.equals(temp))) {
            System.err.println("In 'Exit' statement:\nExpecting type: " + temp.toString() + "\nActual: " + t.toString());
            System.exit(200);
        }
        return null;
    }

    @Override
    public Type visitPrint(@NotNull BasicParser.PrintContext ctx) {
        return visitExpr(ctx.expr());
    }

    @Override
    public Type visitPrintln(@NotNull BasicParser.PrintlnContext ctx) {
        return visitExpr(ctx.expr());
    }

    @Override
    public Type visitIfElse(@NotNull BasicParser.IfElseContext ctx) {
        Type t = visitExpr(ctx.expr());
        Type temp = new BaseType(BaseTypeCode.BOOL);
        if (!(t.equals(temp))) {
            System.err.println("In expression: " + ctx.expr().getText() + "\nExpected type: " + temp.toString() + "\nActual type: " + t.toString());
            System.exit(200);
        }
        // Visit branches in conditional. If Statement conditional only has 2
        // branches
        for (int i = 0; i < 2; i++) {
            typeEnv.enterScope();
            visit(ctx.stat(i));
            typeEnv.removeScope();
        }
        return null;
    }

    @Override
    public Type visitWhile(@NotNull BasicParser.WhileContext ctx) {
        Type t = visitExpr(ctx.expr());
        Type temp = new BaseType(BaseTypeCode.BOOL);
        if (!(t.equals(temp))) {
            System.err.println("In expression: " + ctx.expr().getText() + "\nExpected type: " + temp.toString() + "\nActual type: " + t.toString());
            System.exit(200);
        }
        typeEnv.enterScope();
        visit(ctx.stat());
        typeEnv.removeScope();
        return null;
    }

    @Override
    public Type visitBegin(@NotNull BasicParser.BeginContext ctx) {
        typeEnv.enterScope();
        visit(ctx.stat());
        typeEnv.removeScope();
        return null;
    }

    @Override
    public Type visitMultipleStat(@NotNull BasicParser.MultipleStatContext ctx) {
        ctx.stat().forEach(this::visit);
        return null;
    }

    @Override
    public Type visitAssignLHS(@NotNull BasicParser.AssignLHSContext ctx) {
        Type t = null;
        if (ctx.ident() != null) {
            t = typeEnv.varLookup(ctx.ident().IDENTITY().getText(), typeEnv.vTableScopes);
        } else if (ctx.arrayElem() != null) {
            t = visitArrayElem(ctx.arrayElem());
        } else if (ctx.pairElem() != null) {
            t = visitPairElem(ctx.pairElem());
        } else {
            System.exit(200);
        }
        return t;
    }

    @Override
    public Type visitAssignRHS(@NotNull BasicParser.AssignRHSContext ctx) {
        Type t = null;
        if (ctx.NEWPAIR() != null) {
            Type t1 = visitExpr(ctx.expr(0));
            Type t2 = visitExpr(ctx.expr(1));
            t = new PairType(t1, t2);
        } else if (ctx.CALL() != null) {
            String i = ctx.ident().IDENTITY().getText();
            t = typeEnv.funcLookup(i);

            if (ctx.argList() != null) {
                for (BasicParser.ExprContext exprCtx: ctx.argList().expr()) {
                    // TODO: Refactor ftable 'Type' value to 'List<Type>': we need to know param types.
                }
            }
        } else if (ctx.getChildCount() == 1 && ctx.expr() != null) {
            t = visitExpr(ctx.expr(0));
        } else if (ctx.arrayLiter() != null) {
            t = visitArrayLiter(ctx.arrayLiter());
        } else if (ctx.pairElem() != null) {
            t = visitPairElem(ctx.pairElem());
        } else {
            System.err.println("Error in 'visitAssignRHS' method.");
            System.exit(200);
        }
        return t;
    }

    @Override
    public Type visitArgList(@NotNull BasicParser.ArgListContext ctx) {
        return super.visitArgList(ctx);
    }

    @Override
    public Type visitPairElem(@NotNull BasicParser.PairElemContext ctx) {
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
    public Type visitType(@NotNull BasicParser.TypeContext ctx) {
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
    public Type visitBaseType(@NotNull BasicParser.BaseTypeContext ctx) {
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
    public Type visitArrayType(@NotNull BasicParser.ArrayTypeContext ctx) {
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
    public Type visitPairType(@NotNull BasicParser.PairTypeContext ctx) {
        //List<BasicParser.PairElemContext> pairElemContexts = ctx.p
        if (ctx.pairElemType().size() != 2) {
            System.err.println("Invalid number of parameter types in pair type constructor: " + ctx.pairElemType().size());
        }
        return new PairType(null, null);
    }

    @Override
    public Type visitPairElemType(@NotNull BasicParser.PairElemTypeContext ctx) {
        return super.visitPairElemType(ctx);
    }

    @Override
    public Type visitExpr(@NotNull BasicParser.ExprContext ctx) {
        return super.visitExpr(ctx);
    }

    // We redefine the identifier visit method
    public Type visitIdent(@NotNull BasicParser.IdentContext ctx, LinkedList<HashMap<String, Type>> symTabScopes) {
        return typeEnv.varLookup(ctx.IDENTITY().getText(), symTabScopes);
    }

    @Override
    public Type visitArrayElem(@NotNull BasicParser.ArrayElemContext ctx) {
        Type t1 = visitIdent(ctx.ident(), typeEnv.vTableScopes);
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
    public Type visitBoolLiter(@NotNull BasicParser.BoolLiterContext ctx) {
        return new BaseType(BaseTypeCode.BOOL);
    }

    @Override
    public Type visitCharLiter(@NotNull BasicParser.CharLiterContext ctx) {
        return new BaseType(BaseTypeCode.CHAR);
    }

    @Override
    public Type visitStringLiter(@NotNull BasicParser.StringLiterContext ctx) {
        return new BaseType(BaseTypeCode.STRING);
    }

    @Override
    public Type visitIntLiter(@NotNull BasicParser.IntLiterContext ctx) {
        return new BaseType(BaseTypeCode.INT);
    }

    @Override
    public Type visitArrayLiter(@NotNull BasicParser.ArrayLiterContext ctx) {
        // The array literal's type is determined by the type of the first
        // expression.
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
    public Type visitPairLiter(@NotNull BasicParser.PairLiterContext ctx) {
        return new PairType(null, null);
    }
}
