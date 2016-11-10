package frontEnd.visitor;

import antlr.BasicParser;
import antlr.BasicParserBaseVisitor;
import frontEnd.ErrorHandling.Exception;
import frontEnd.ErrorHandling.IncompatibleTypesException;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class TypeCheckVisitor extends BasicParserBaseVisitor<Type> {

    // Holds symbol table information needed by our type checker
    private static class TypeEnv {

        // Identifiers are strings
        private LinkedList<HashMap<String, Type>> vTableScopes = new LinkedList<>();
        private HashMap<String, Type> fTable = new HashMap<>();

        private Type lookup(String key, LinkedList<HashMap<String, Type>> symTabScopes) {
            Type res;
            for (HashMap<String, Type> scope: symTabScopes) {
                res = scope.get(key);
                if (res != null) {
                    return res;
                }
            }
            // TODO: throw error, identifier unbound in current scope or any enclosing scopes
            System.exit(200);
        }

        // Function symbol table insert method
        private void fTableInsert(String key, Type value) {
            if (fTable.containsKey(key)) {
                // TODO: Throw error, function already bound
                System.exit(200);
            }
            fTable.put(key, value);
        }

        // Variable symbol table insert method
        private void vTableInsert(String key, Type value) {
            // We cannot insert an identifier twice. This is a semantic error
            // in the program.
            if (vTableScopes.getFirst().containsKey(key)) {
                // TODO: Throw error, variable already defined
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
                // TODO: Throw error, function already bound
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
        return t;
    }

    // Function identifiers and types should already be added to ftable by time we call this method.
    @Override
    public Type visitFunc(@NotNull BasicParser.FuncContext ctx) {

        // Function return type
        Type t0 = visitType(ctx.type());

        for (BasicParser.ParamContext ctxParam: ctx.paramList().param()) {
            String i = ctxParam.ident().IDENTITY().getText();
            Type t = visitParam(ctxParam);

            if (!(typeEnv.lookup(i, typeEnv.vTableScopes) == null)) {
                // TODO: throw error, identifier already bound
                System.exit(200);
            }

            typeEnv.vTableInsert(i, t);
        }

        typeEnv.enterScope();
        Type t1 = visit(ctx.stat());
        typeEnv.removeScope();

        if (!(t0.equals(t1))) {
            // TODO: throw error, type mismatch between expected function return type and actual body return type
            System.exit(200);
        }
        return t0;
    }

    @Override
    public Type visitParamList(@NotNull BasicParser.ParamListContext ctx) {
        return super.visitParamList(ctx);
    }

    @Override
    public Type visitParam(@NotNull BasicParser.ParamContext ctx) {
        Type t = visitType(ctx.type());
        typeEnv.fTableInsert(ctx.ident().IDENTITY().getText(), t);
        return t;
    }

    @Override
    public Type visitSkip(@NotNull BasicParser.SkipContext ctx) {
        return null;
    }

    @Override
    public Type visitDeclare(@NotNull BasicParser.DeclareContext ctx) {
        Type t1 = visitType(ctx.type());
        typeEnv.vTableInsert(ctx.ident().IDENTITY().getText(), t1);
        Type t2 = visitAssignRHS(ctx.assignRHS());

        if (!(t1.equals(t2))) {
            // TODO: error
            System.exit(200);
        }
        return null;
    }

    @Override
    public Type visitAssign(@NotNull BasicParser.AssignContext ctx) {
        Type t1 = visitAssignLHS(ctx.assignLHS());
        Type t2 = visitAssignRHS(ctx.assignRHS());

        if (!(t1.equals(t2))) {
            // TODO: error
            System.exit(200);
        }
        return null;
    }

    @Override
    public Type visitRead(@NotNull BasicParser.ReadContext ctx) {
        Type t = visitAssignLHS(ctx.assignLHS());

        if (!(t.equals(new BaseType(BaseTypeCode.CHAR)) || t.equals(new
                BaseType(BaseTypeCode.INT)))) {
            // TODO: error
            System.exit(200);
        }
        return null;
    }

    @Override
    public Type visitFree(@NotNull BasicParser.FreeContext ctx) {
        Type t = visitExpr(ctx.expr());

        // Nulls signify generic arrays/pairs.
        if (!(t.equals(new PairType(null, null)) || t.equals(new ArrayType(null)
        ))) {
            // TODO: error
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
        if (!(t.equals(new BaseType(BaseTypeCode.INT)))) {
            // TODO: error
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
        if (!(t instanceof BaseType)) {
            // TODO: type error
            System.exit(200);
        }
        if (((BaseType) t).getTypeCode() != BaseTypeCode.BOOL) {
            // TODO: error
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
        if (!(t instanceof BaseType)) {
            // TODO: type error
            System.exit(200);
        }
        if (((BaseType) t).getTypeCode() != BaseTypeCode.BOOL) {
            // TODO: error
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
            t = typeEnv.lookup(ctx.ident().IDENTITY().getText(), typeEnv.vTableScopes);
        } else if (ctx.arrayElem() != null) {
            // TODO
            System.exit(200);
        } else if (ctx.pairElem() != null) {
            // TODO
            System.exit(200);
        } else {
            // throw error as in other visit methods
            System.exit(200);
        }
        return t;
    }

    @Override
    public Type visitAssignRHS(@NotNull BasicParser.AssignRHSContext ctx) {
        return super.visitAssignRHS(ctx);
    }

    @Override
    public Type visitArgList(@NotNull BasicParser.ArgListContext ctx) {
        return super.visitArgList(ctx);
    }

    @Override
    public Type visitPairElem(@NotNull BasicParser.PairElemContext ctx) {
        return super.visitPairElem(ctx);
    }

    @Override
    public Type visitType(@NotNull BasicParser.TypeContext ctx) {
        if (ctx.baseType().BOOL() != null) {
            return new BaseType(BaseTypeCode.BOOL);
        } else if (ctx.baseType().CHAR() != null) {
            return new BaseType(BaseTypeCode.CHAR);
        } else if (ctx.baseType().STRING() != null) {
            return new BaseType(BaseTypeCode.STRING);
        } else if (ctx.baseType().INT() != null) {
            return new BaseType(BaseTypeCode.INT);
        } else if (ctx.arrayType() != null) {
            return new ArrayType(new BaseType(null));
        } else if (ctx.pairType() != null) {
            return new PairType(null, null);
        } else {
            // TODO: throw error
            System.exit(200);
        }
        return null;
    }

    @Override
    public Type visitBaseType(@NotNull BasicParser.BaseTypeContext ctx) {
        return super.visitBaseType(ctx);
    }

    @Override
    public Type visitArrayType(@NotNull BasicParser.ArrayTypeContext ctx) {
        return super.visitArrayType(ctx);
    }

    @Override
    public Type visitPairType(@NotNull BasicParser.PairTypeContext ctx) {
        return super.visitPairType(ctx);
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
        return typeEnv.lookup(ctx.IDENTITY().getText(), symTabScopes);
    }

    @Override
    public Type visitArrayElem(@NotNull BasicParser.ArrayElemContext ctx) {
        Type t1 = visitIdent(ctx.ident(), typeEnv.vTableScopes);
        // Array element's type is determined by the type of the first
        // expression.
        Type t2 = visitExpr(ctx.expr(0));
        if (t2.equals(new BaseType(BaseTypeCode.INT))) {
            // Indexing error
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
        return new ArrayType(t);
    }

    @Override
    public Type visitPairLiter(@NotNull BasicParser.PairLiterContext ctx) {
        return new PairType(null, null);
    }
}
