package frontEnd.visitor;

import antlr.BasicParser;
import antlr.BasicParserBaseVisitor;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.HashMap;
import java.util.LinkedList;

public class TypeCheckVisitor extends BasicParserBaseVisitor<Type> {

    // Holds symbol table information needed by our type checker
    private static class TypeEnv {

        // Identifiers are strings
        private LinkedList<HashMap<String, Type>> symTabScopes;

        public Type lookup(String key) {
            Type res = null;
            for (HashMap<String, Type> scope: symTabScopes) {
                res = scope.get(key);
                if (res != null) {
                    return res;
                }
            }
            // throw identifier not found exception here
            return null;
        }

        public void insert(String key, Type value) {
            // We cannot insert an identifier twice. This is a semantic error
            // in the program.
            symTabScopes.getFirst().putIfAbsent(key, value);
        }

        public void enterScope() {
            symTabScopes.addFirst(new HashMap<>());
        }

        // TODO: We might need persistent scopes, so consider making symbol
        // TODO: table a persistent data structure.
        public void removeScope() {
            symTabScopes.removeFirst();
        }
    }

    private TypeEnv typeEnv;

    public TypeCheckVisitor() {
        this.typeEnv = new TypeEnv();
    }

    @Override
    public Type visitProgram(@NotNull BasicParser.ProgramContext ctx) {
        return super.visitProgram(ctx);
    }

    @Override
    public Type visitFunc(@NotNull BasicParser.FuncContext ctx) {
        return super.visitFunc(ctx);
    }

    @Override
    public Type visitParamList(@NotNull BasicParser.ParamListContext ctx) {
        return super.visitParamList(ctx);
    }

    @Override
    public Type visitParam(@NotNull BasicParser.ParamContext ctx) {
        return super.visitParam(ctx);
    }

    @Override
    public Type visitSkip(@NotNull BasicParser.SkipContext ctx) {
        return null;
    }

    @Override
    public Type visitDeclare(@NotNull BasicParser.DeclareContext ctx) {
        Type t1 = visitType(ctx.type());
        typeEnv.insert(ctx.ident().IDENTITY().getText(), t1);
        Type t2 = visitAssignRHS(ctx.assignRHS());

        if (t1 != t2) {
            // TODO: error
        }
        return null;
    }

    @Override
    public Type visitAssign(@NotNull BasicParser.AssignContext ctx) {
        Type t1 = visitAssignLHS(ctx.assignLHS());
        Type t2 = visitAssignRHS(ctx.assignRHS());

        if (t1 != t2) {
            // TODO: error
        }
        return null;
    }

    @Override
    public Type visitRead(@NotNull BasicParser.ReadContext ctx) {
        Type t = visitAssignLHS(ctx.assignLHS());

        if (!(t == Type.CHAR || t == Type.INT)) {
            // TODO: error
        }
        return null;
    }

    @Override
    public Type visitFree(@NotNull BasicParser.FreeContext ctx) {
        Type t = visitExpr(ctx.expr());

        if (!(t == Type.PAIR || t == Type.ARRAY)) {
            // TODO: error
        }
        return null;
    }

    @Override
    public Type visitReturn(@NotNull BasicParser.ReturnContext ctx) {
        ParserRuleContext tmpCtx = ctx;
        while (ctx.getParent() != null && !(ctx.getParent() instanceof
                BasicParser.FuncContext)) {
            tmpCtx = ctx.getParent();
        }
        if (tmpCtx == null) {
            // TODO: error, return statement unbound by function
        }
        Type t1 = visitExpr(ctx.expr());
        Type t2 = visitFunc((BasicParser.FuncContext) tmpCtx);
        if (t1 != t2) {
            // TODO: Mismatch between return statement type and function return
            // TODO: type
        }
        return null;
    }

    @Override
    public Type visitExit(@NotNull BasicParser.ExitContext ctx) {
        Type t = visitExpr(ctx.expr());
        if (t != Type.INT) {
            // TODO: error
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
        }
        if (((BaseType) t).getTypeCode() != BaseTypeCode.BOOL) {
            // TODO: error
        }
        return null;
    }

    @Override
    public Type visitWhile(@NotNull BasicParser.WhileContext ctx) {
        Type t = visitExpr(ctx.expr());
        if (!(t instanceof BaseType)) {
            // TODO: type error
        }
        if (((BaseType) t).getTypeCode() != BaseTypeCode.BOOL) {
            // TODO: error
        }
        return null;
    }

    @Override
    public Type visitBegin(@NotNull BasicParser.BeginContext ctx) {
        return visit(ctx.stat());
    }

    @Override
    public Type visitMultipleStat(@NotNull BasicParser.MultipleStatContext ctx) {
        return super.visitMultipleStat(ctx);
    }

    @Override
    public Type visitAssignLHS(@NotNull BasicParser.AssignLHSContext ctx) {
        Type t;
        if (ctx.ident() != null) {
            t = typeEnv.lookup(ctx.ident().IDENTITY().getText());
        } else if (ctx.arrayElem() != null) {
            // TODO
            return null;
        } else if (ctx.pairElem() != null) {
            // TODO
            return null;
        } else {
            // throw error as in other visit methods
            return null;
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
            return
        }
        return super.visitType(ctx);
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

    @Override
    public Type visitIdent(@NotNull BasicParser.IdentContext ctx) {
        return super.visitIdent(ctx);
    }

    @Override
    public Type visitArrayElem(@NotNull BasicParser.ArrayElemContext ctx) {
        Type t1 = visitIdent(ctx.ident());
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
