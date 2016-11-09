package frontEnd.visitor;

import frontEnd.antlr.BasicParser;
import frontEnd.antlr.BasicParserBaseVisitor;
import org.antlr.v4.runtime.misc.NotNull;

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
    public TypeCode visitIntLiter(@NotNull BasicParser.IntLiterContext ctx) {
        return TypeCode.INT;
    }

    @Override
    public TypeCode visitCharLiter(@NotNull BasicParser.CharLiterContext ctx) {
        return TypeCode.CHAR;
    }

    @Override
    public TypeCode visitExpr(@NotNull BasicParser.ExprContext ctx) {
        TypeCode t;
        if (ctx.intLiter() != null) {
            t = visitIntLiter(ctx.intLiter());
        } else if (ctx.charLiter() != null) {
            t = visitCharLiter(ctx.charLiter());
        } else if (ctx.boolLiter() != null) {
            t = visitBoolLiter(ctx.boolLiter());
        } else if (ctx.STRING_LITER() != null){
            t = visitTerminal(ctx.STRING_LITER());
        } else if (ctx.pairLiter() != null) {
            t = visitPairLiter(ctx.pairLiter());
        } else if (ctx.IDENTITY() != null) {
            t = visitTerminal(ctx.IDENTITY());
        } else if (ctx.arrayElem() != null) {
            t = visitArrayElem(ctx.arrayElem());
        } else if (ctx.unaryOper() != null) {
            t = visitUnaryOper(ctx.unaryOper());
        } else if (ctx.binaryOper() != null) {
            t = visitBinaryOper(ctx.binaryOper());
        } else {
            //throw error "Unrecognized expression context"
            System.out.println(ctx.getText());
            return null;
        }
        return t;
    }

    @Override
    public TypeCode visitStat(@NotNull BasicParser.StatContext ctx) {
        TypeCode t;
        if (ctx.assignLHS() != null) {
            t = visitAssignLHS(ctx.assignLHS());
        } else {
            // throw error "Unrecognized statement context"
            System.out.println(ctx.getText());
            return null;
        }
        return t;
    }

    @Override
    public TypeCode visitAssignLHS(@NotNull BasicParser.AssignLHSContext ctx) {
        TypeCode t;
        if (ctx.IDENTITY() != null) {
            // TODO: will 'ctx.IDENTITY.getText()' return the raw identifier
            // TODO: string?
            t = typeEnv.lookup(ctx.IDENTITY().getText());
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
    public TypeCode visitBinaryOper(@NotNull BasicParser.BinaryOperContext ctx) {
        if (ctx.getChildCount() != 2) {
            // We should throw an exception here for invalid child count
            return null;
        }
        TypeCode t1 = ctx.getChild(0).accept(this);
        TypeCode t2 = ctx.getChild(1).accept(this);

        if (t1 != t2) {
            // throw type mismatch exception
            return null;
        }
        return TypeCode.INT;
    }
}
