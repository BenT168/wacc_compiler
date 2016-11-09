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
    public TypeCode visitStat(@NotNull BasicParser.StatContext ctx) {

        if (ctx.SKIP() != null) {
            return null;
        } else if (ctx.type() != null && ctx.IDENTITY() != null && ctx.EQUALS
                () != null && ctx.assignRHS() != null) {

            TypeCode t1 = visitType(ctx.type());
            typeEnv.insert(ctx.IDENTITY().getText(), t1);
            TypeCode t2 = visitAssignRHS(ctx.assignRHS());

            if (t1 != t2) {
                // Type mismatch error
            }

        } else if (ctx.assignLHS() != null && ctx.EQUALS() != null && ctx
                .assignRHS() != null) {

            TypeCode t1 = visitAssignLHS(ctx.assignLHS());
            TypeCode t2 = visitAssignRHS(ctx.assignRHS());

            if (t1 != t2) {
                // Type mismatch error
            }

        } else if (ctx.READ() != null && ctx.assignLHS() != null) {
            TypeCode t = visitAssignLHS(ctx.assignLHS());

            if (!(t == TypeCode.CHAR || t == TypeCode.INT)) {
                // Type mismatch, read can only handle integers and characters
            }
        } else if (ctx.FREE() != null && ctx.expr() != null) {
            TypeCode t = visitExpr(ctx.expr());

            if (!(t == TypeCode.PAIR || t == TypeCode.ARRAY)) {
                // Error
            }
        } else if (ctx)
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
    public TypeCode visitUnaryOper(@NotNull BasicParser.UnaryOperContext ctx) {
        return super.visitUnaryOper(ctx);
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

    @Override
    public TypeCode visitArrayElem(@NotNull BasicParser.ArrayElemContext ctx) {
        return super.visitArrayElem(ctx);
    }

    @Override
    public TypeCode visitIntSign(@NotNull BasicParser.IntSignContext ctx) {
        return super.visitIntSign(ctx);
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

    @Override
    public TypeCode visitComment(@NotNull BasicParser.CommentContext ctx) {
        return super.visitComment(ctx);
    }
}
