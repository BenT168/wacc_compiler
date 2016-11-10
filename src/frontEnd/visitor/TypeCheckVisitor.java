package frontEnd.visitor;

import antlr.BasicParser;
import antlr.BasicParserBaseVisitor;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.misc.Pair;

import java.util.HashMap;
import java.util.LinkedList;

public class TypeCheckVisitor extends BasicParserBaseVisitor<TypeCode> {

    // Holds symbol table information needed by our type checker
    private static class TypeEnv {

        // Identifiers are strings
        private LinkedList<HashMap<String, TypeCode>> symTabScopes;

        public TypeCode lookup(String key) {
            TypeCode res = null;
            for (HashMap<String, TypeCode> scope: symTabScopes) {
                res = scope.get(key);
                if (res != null) {
                    return res;
                }
            }
            // throw identifier not found exception here
            return null;
        }

        public void insert(String key, TypeCode value) {
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
    public TypeCode visitProgram(@NotNull BasicParser.ProgramContext ctx) {
        return super.visitProgram(ctx);
    }

    @Override
    public TypeCode visitFunc(@NotNull BasicParser.FuncContext ctx) {
        return super.visitFunc(ctx);
    }

    @Override
    public TypeCode visitParamList(@NotNull BasicParser.ParamListContext ctx) {
        return super.visitParamList(ctx);
    }

    @Override
    public TypeCode visitParam(@NotNull BasicParser.ParamContext ctx) {
        return super.visitParam(ctx);
    }

    @Override
    public TypeCode visitDeclare(@NotNull BasicParser.DeclareContext ctx) {
        TypeCode t1 = visitType(ctx.type());
        typeEnv.insert(ctx.ident().IDENTITY().getText(), t1);
        TypeCode t2 = visitAssignRHS(ctx.assignRHS());

        if (t1 != t2) {
            // TODO: error
        }
        return null;
    }

    @Override
    public TypeCode visitAssign(@NotNull BasicParser.AssignContext ctx) {
        TypeCode t1 = visitAssignLHS(ctx.assignLHS());
        TypeCode t2 = visitAssignRHS(ctx.assignRHS());

        if (t1 != t2) {
            // TODO: error
        }
        return null;
    }

    @Override
    public TypeCode visitRead(@NotNull BasicParser.ReadContext ctx) {
        TypeCode t = visitAssignLHS(ctx.assignLHS());

        if (!(t == TypeCode.CHAR || t == TypeCode.INT)) {
            // TODO: error
        }
        return null;
    }

    @Override
    public TypeCode visitFree(@NotNull BasicParser.FreeContext ctx) {
        TypeCode t = visitExpr(ctx.expr());

        if (!(t == TypeCode.PAIR || t == TypeCode.ARRAY)) {
            // TODO: error
        }
        return null;
    }

    @Override
    public TypeCode visitReturn(@NotNull BasicParser.ReturnContext ctx) {
        ParserRuleContext tmpCtx = ctx;
        while (ctx.getParent() != null && !(ctx.getParent() instanceof
                BasicParser.FuncContext)) {
            tmpCtx = ctx.getParent();
        }
        if (tmpCtx == null) {
            // TODO: error, return statement unbound by function
        }
        TypeCode t1 = visitExpr(ctx.expr());
        TypeCode t2 = visitFunc((BasicParser.FuncContext) tmpCtx);
        if (t1 != t2) {
            // TODO: Mismatch between return statment type and function return
            // TODO: type
        }
        return null;
    }

    @Override
    public TypeCode visitAssignLHS(@NotNull BasicParser.AssignLHSContext ctx) {
        TypeCode t;
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
    public TypeCode visitAssignRHS(@NotNull BasicParser.AssignRHSContext ctx) {
        return super.visitAssignRHS(ctx);
    }

    @Override
    public TypeCode visitArgList(@NotNull BasicParser.ArgListContext ctx) {
        return super.visitArgList(ctx);
    }

    @Override
    public TypeCode visitPairElem(@NotNull BasicParser.PairElemContext ctx) {
        return super.visitPairElem(ctx);
    }

    @Override
    public TypeCode visitType(@NotNull BasicParser.TypeContext ctx) {
        return super.visitType(ctx);
    }

    @Override
    public TypeCode visitBaseType(@NotNull BasicParser.BaseTypeContext ctx) {
        return super.visitBaseType(ctx);
    }

    @Override
    public TypeCode visitArrayType(@NotNull BasicParser.ArrayTypeContext ctx) {
        return super.visitArrayType(ctx);
    }

    @Override
    public TypeCode visitPairType(@NotNull BasicParser.PairTypeContext ctx) {
        return super.visitPairType(ctx);
    }

    @Override
    public TypeCode visitPairElemType(@NotNull BasicParser.PairElemTypeContext ctx) {
        return super.visitPairElemType(ctx);
    }

    @Override
    public TypeCode visitExpr(@NotNull BasicParser.ExprContext ctx) {
        return super.visitExpr(ctx);
    }

    @Override
    public TypeCode visitArrayElem(@NotNull BasicParser.ArrayElemContext ctx) {
        return super.visitArrayElem(ctx);
    }

    @Override
    public TypeCode visitBoolLiter(@NotNull BasicParser.BoolLiterContext ctx) {
        return super.visitBoolLiter(ctx);
    }

    @Override
    public TypeCode visitCharLiter(@NotNull BasicParser.CharLiterContext ctx) {
        return TypeCode.CHAR;
    }

    @Override
    public TypeCode visitIntLiter(@NotNull BasicParser.IntLiterContext ctx) {
        return TypeCode.INT;
    }

    @Override
    public TypeCode visitArrayLiter(@NotNull BasicParser.ArrayLiterContext ctx) {
        return super.visitArrayLiter(ctx);
    }

    @Override
    public TypeCode visitPairLiter(@NotNull BasicParser.PairLiterContext ctx) {
        return super.visitPairLiter(ctx);
    }
}
