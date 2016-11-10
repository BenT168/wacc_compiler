package frontEnd.visitor;

import antlr.WACCParser;
import antlr.WACCParserBaseVisitor;
import frontEnd.ErrorHandling.Exception;
import frontEnd.ErrorHandling.UndeclaredIdentifierException;
import frontEnd.semanticCheck.SemanticError;
import frontEnd.tree.ASTTree;
import frontEnd.tree.Assignment.*;
import frontEnd.tree.Expr.*;
import frontEnd.tree.Function.FunctionDec;
import frontEnd.tree.Function.VariableDec;
import frontEnd.tree.Parameter.ParamList;
import frontEnd.tree.Parameter.Parameter;
import frontEnd.tree.Program;
import frontEnd.tree.Stat.*;
import frontEnd.tree.Type.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTree;
import symbolTable.SymbolTable;

import java.util.ArrayList;
import java.util.List;

public class myVisitor extends WACCParserBaseVisitor<ASTTree> {

    /* For Storing Variables and its information
     */
    private ParseTree parseTree;
    private SymbolTable currentSymbolTable;

    /* For calling semantic error     */
    private SemanticError semanticError = new SemanticError();

    public myVisitor(ParseTree pTree) {
        this.parseTree = pTree;
        this.currentSymbolTable = new SymbolTable();
    }

    public void init() {
        
        semanticError.printV("Checking sematic integrity...");
        ASTTree tree = parseTree.accept(this);

        //Debugging: prints the WACC tree after semantic checking
        //XStream xstream = new XStream(new JsonHierarchicalStreamDriver());
        //xstream.setMode(xstream.ID_REFERENCES);
        //xstream.alias("WACCTree", BaseType.class);
        //semanticError.printD(xstream.toXML(tree));
    }


    public boolean terminate() {
        return Exception.ERROR_LISTENER.finished();
    }


    //................................PROGRAM.........................................
    @Override
    public ASTTree visitProgram(@NotNull WACCParser.ProgramContext ctx) {
        for (WACCParser.FuncContext fctx : ctx.func()) {
            registerFunction(fctx);
        }

        ArrayList<FunctionDec> functions = new ArrayList<>();
        // We visit all the functions and create full nodes
        for (WACCParser.FuncContext fctx : ctx.func()) {
            FunctionDec fdec = (FunctionDec) visit(fctx);
            fdec.check(currentSymbolTable, ctx);
            functions.add(fdec);
        }

        // Then we visit the statement
        Stat progBody = (Stat) visit(ctx.stat());

        // Finally, we return the program node
        return new Program(functions, progBody);
    }

    private void registerFunction(WACCParser.FuncContext ctx) {

        String funcName = ctx.ident().getText();
        if (currentSymbolTable.containsRecursive(funcName)) {
            throw new UndeclaredIdentifierException("Function has already been defined", ctx);
        }
        String typeEval = ctx.type().getText();
        BaseType returnType = BaseType.evalType(typeEval);
        WACCParser.ParamListContext paramCtx = ctx.paramList();
        ParamList params = null;
        if (paramCtx != null) {
            params = (ParamList) visit(ctx.paramList());
        } else {
            params = new ParamList();
        }
        FunctionDec func = new FunctionDec(returnType, funcName, params);
        currentSymbolTable.add(funcName, func);
    }


    //....................................FUNCTION......................................

    @Override
    public ASTTree visitFunc(WACCParser.FuncContext ctx) {
        String funcName = ctx.ident().getText();
        assert (currentSymbolTable.containsCurrent(funcName));
        FunctionDec func = (FunctionDec) currentSymbolTable.lookUp(funcName);

        // Create an inner scope Symbol Table for the function body.
        currentSymbolTable = new SymbolTable(currentSymbolTable, func.getType());

        // Add params to current SymbolTable
        ParamList paramList = (ParamList) func.getParams();
        registerParams(currentSymbolTable, paramList);

        // Create the functionBody node
        Stat funcBody = (Stat) visit(ctx.stat());

        // finalise the current symbolTable and restore the parent scope
        currentSymbolTable.finaliseScope(funcName);
        currentSymbolTable = currentSymbolTable.getEncSymbolTable();

        // Add function body statement to the function node
        func.add(funcBody);
        func.check(currentSymbolTable, ctx);
        return func;
    }

    private void registerParams(SymbolTable st, ParamList paramList) {
        for (Parameter param : paramList) {
            st.add(param.getIdent(), param);
        }
    }

    /* type IDENTITY EQUALS assignRHS */
    @Override
    public ASTTree visitDeclare(@NotNull WACCParser.DeclareContext ctx) {
        Assignment rhsTree = (Assignment) visit(ctx.assignRHS());
        BaseType varType = BaseType.evalType(ctx.type());
        String ident = ctx.ident().getText();
        VariableDec vcd = new VariableDec(varType, ident, rhsTree);
        vcd.check(currentSymbolTable, ctx);

        return vcd;
    }

    // Variable context cant be found
    public ASTTree visitVariable(WACCParser.DeclareContext ctx) {
        AssignLHS lhs = (AssignLHS) visit(ctx.assignRHS());
        Assignment rhs = (Assignment) visit(ctx.assignRHS());
        AssignStat assignment = new AssignStat(lhs, rhs);
        assignment.check(currentSymbolTable, ctx);

        return assignment;
    }

    //....................................PARAMETER......................................

    @Override
    public ASTTree visitParam(WACCParser.ParamContext ctx) {
        BaseType paramType = BaseType.evalType(ctx.type());
        String ident = ctx.ident().getText();
        Parameter paramNode = new Parameter(paramType, ident);
        paramNode.check(currentSymbolTable, ctx);
        return paramNode;
    }

    @Override
    public ASTTree visitParamList(WACCParser.ParamListContext ctx) {
        ParamList params = new ParamList();
        for (WACCParser.ParamContext p : ctx.param()) {
            Parameter pn = (Parameter) visit(p);
            params.add(pn);
        }
        params.check(currentSymbolTable, ctx);
        return params;
    }


    //....................................STAT........................................

    /*PRINT expr*/
    @Override
    public ASTTree visitPrint(@NotNull WACCParser.PrintContext ctx) {
        Expr expr = (Expr) visit(ctx.expr());
        PrintStat ps = new PrintStat(expr);
        ps.check(currentSymbolTable, ctx);

        return ps;
    }

    /*PRINTLN expr*/
    @Override
    public ASTTree visitPrintln(@NotNull antlr.WACCParser.PrintlnContext ctx) {
        Expr expr = (Expr) visit(ctx.expr());
        PrintLnStat ps = new PrintLnStat(expr);
        ps.check(currentSymbolTable, ctx);

        return ps;
    }

    /*READ assignLHS */
    @Override
    public ASTTree visitRead(@NotNull WACCParser.ReadContext ctx) {
        Expr lhs = (Expr) visit(ctx.assignLHS());
        ReadStat rhs = new ReadStat(lhs);
        rhs.check(currentSymbolTable, ctx);

        return rhs;
    }

    /*EXIT expr */
    @Override
    public ASTTree visitExit(@NotNull WACCParser.ExitContext ctx) {
        Expr exitVal = (Expr) visit(ctx.expr());
        ExitStat stat = new ExitStat(exitVal);
        stat.check(currentSymbolTable, ctx);

        return stat;
    }

    @Override
    public ASTTree visitSkip(WACCParser.SkipContext ctx) {
        SkipStat ssn = new SkipStat();
        return ssn;
    }

    @Override
    public ASTTree visitReturn(WACCParser.ReturnContext ctx) {
        Expr exprType = (Expr) visit(ctx.expr());
        ReturnStat rst = new ReturnStat(exprType);
        rst.check(currentSymbolTable, ctx);

        return rst;
    }

    @Override
    public ASTTree visitFree(WACCParser.FreeContext ctx) {
        Expr expr = (Expr) visit(ctx.expr());
        FreeStat stat = new FreeStat(expr);
        stat.check(currentSymbolTable, ctx);

        return stat;
    }


    /* assignLHS EQUALS assignRHS  */
    @Override
    public ASTTree visitAssign(@NotNull WACCParser.AssignContext ctx) {
        AssignLHS lhs = (AssignLHS) visitAssignLHS(ctx.assignLHS());
        Expr rhs = (Expr) visitAssignRHS(ctx.assignRHS());
        AssignStat stat = new AssignStat(lhs, rhs);
        stat.check(currentSymbolTable, ctx);

        return stat;
    }

    @Override
    public ASTTree visitWhile(WACCParser.WhileContext ctx) {
        Expr loopCond = (Expr) visit(ctx.expr());
        WhileStat whileStat = new WhileStat(loopCond);
        whileStat.check(currentSymbolTable, ctx);

        return whileStat;
    }

    @Override
    public ASTTree visitIfElse(WACCParser.IfElseContext ctx) {
        Expr ifCond = (Expr) visit(ctx.expr());
        IfElseStat ifelseStat = new IfElseStat(ifCond);
        ifelseStat.check(currentSymbolTable, ctx);

        return ifelseStat;
    }

    @Override
    public ASTTree visitBegin(@NotNull WACCParser.BeginContext ctx) {
        currentSymbolTable = new SymbolTable(currentSymbolTable);
        Stat stat = (Stat) visit(ctx.stat());
        currentSymbolTable.finaliseScope();
        currentSymbolTable = currentSymbolTable.getEncSymbolTable();
        return stat;
    }

    @Override
    public ASTTree visitMultipleStat(@NotNull WACCParser.MultipleStatContext ctx) {
        ctx.stat().forEach(this::visit);
        return null;
    }


//..................................ASSIGNMENT......................................

    // Function Call context cant be found
    public ASTTree visitCallFunc(WACCParser.FuncContext ctx) {
        String ident = ctx.ident().getText();
        FunctionDec funcDef = (FunctionDec) currentSymbolTable.lookUp(ident);
        ArgList args;

        //Here we check that the call has arguments
        //if no arguments are present, a new empty arg_list will be made.
        if (ctx.paramList() == null) {
            args = new ArgList();
        } else {
            args = (ArgList) visit(ctx.paramList());
        }
        CallFunc callStat = new CallFunc(funcDef, args);
        callStat.check(currentSymbolTable, ctx);
        return callStat;
    }

    @Override
    public ASTTree visitArgList(WACCParser.ArgListContext ctx) {
        int argListLength = ctx.expr().size();
        ArgList args = new ArgList();
        for (int i = 0; i < argListLength; i++) {
            args.add((Expr) visit(ctx.expr(i)));
        }
        args.check(currentSymbolTable, ctx);
        return args;
    }

    @Override
    public ASTTree visitPairElem(@NotNull WACCParser.PairElemContext ctx) {
        String fstOrSnd = ctx.getChild(0).getText();
        Expr expr = (Expr) visit(ctx.expr());
        PairElem pairElem = new PairElem(fstOrSnd, expr);
        pairElem.check(currentSymbolTable, ctx);
        return pairElem;
    }

    @Override
    public ASTTree visitArrayElem(@NotNull WACCParser.ArrayElemContext ctx) {
        String ident = ctx.ident().getText();
        List<WACCParser.ExprContext> exprCtxs = ctx.expr();
        ArrayList<Expr> exprs = new ArrayList<Expr>();
        for (WACCParser.ExprContext ec : exprCtxs) {
            exprs.add((Expr) visit(ec));
        }

        ArrayType t = (ArrayType) currentSymbolTable.lookUp(ident).getType();
        ArrayElem arrayElem = new ArrayElem(exprs, t);
        return arrayElem;
    }

    @Override
    public ASTTree visitArrayLiter(WACCParser.ArrayLiterContext ctx) {
        ArrayList<Expr> elems = new ArrayList<Expr>();

        for (int i = 0; i < ctx.expr().size(); i++) {
            elems.add((Expr) visit(ctx.expr(i)));
        }

        ArrayLiter arrayLiter = new ArrayLiter(elems);
        arrayLiter.check(currentSymbolTable, ctx);
        return arrayLiter;
    }


    //..................................EXPRESSION......................................

    @Override
    public ASTTree visitExpr(WACCParser.ExprContext ctx) {
        // if it's an atomic `( expr )` expression, we just call visit on the
        // inner expr
        if (ctx.OPEN_PARENTHESES() != null) {
            assert (ctx.children.size() == 3);
            return visit(ctx.expr(0));
        }

        switch (ctx.getChildCount()) {
            case 3: // Binary Expression of type `lhs OP rhs`
                Expr lhs = (Expr) visit(ctx.expr(0));
                Expr rhs = (Expr) visit(ctx.expr(1));
                BinaryOp binaryOp = BinaryOp.evalBinOp(ctx.getChild(1).getText());
                BinaryOper binExpr = new BinaryOper(lhs, binaryOp, rhs);
                binExpr.check(currentSymbolTable, ctx);

                return binExpr;

            case 2: // Unary Expression of type `OP expr`
                Expr expr = (Expr) visit(ctx.expr(0));
                UnaryOp unaryOp = UnaryOp.evalUnOp(ctx.getChild(0).getText());
                UnaryOper unaryExpr = new UnaryOper(unaryOp, expr);
                unaryExpr.check(currentSymbolTable, ctx);

                return unaryExpr;

            default: // in this case this is a single rule (i.e. int_liter, char_liter)
                return visit(ctx.getChild(0));
        }
    }

    @Override
    public ASTTree visitCharLiter(WACCParser.CharLiterContext ctx) {
        CharLiter charleaf = new CharLiter(ctx.getText());
        charleaf.check(currentSymbolTable, ctx);
        return charleaf;
    }

    @Override
    public ASTTree visitIntLiter(WACCParser.IntLiterContext ctx) {
        String intValue = ctx.getText();
        IntLiter intLeaf = new IntLiter(Integer.valueOf(intValue));
        intLeaf.check(currentSymbolTable, ctx);
        return intLeaf;
    }

    @Override
    public ASTTree visitBoolLiter(WACCParser.BoolLiterContext ctx) {
        BoolLiter boolLeaf = new BoolLiter(ctx.getText());
        boolLeaf.check(currentSymbolTable, ctx);
        return boolLeaf;
    }

    @Override
    public ASTTree visitStringLiter(WACCParser.StringLiterContext ctx) {
        StrLiter strLeaf = new StrLiter(ctx.getText());
        strLeaf.check(currentSymbolTable, ctx);
        return strLeaf;
    }

    // Pair literals are null by default.
    @Override
    public ASTTree visitPairLiter(WACCParser.PairLiterContext ctx) {
        return new PairLiter();
    }

    //..................................IDENTITY....................................

    @Override
    public ASTTree visitIdent(WACCParser.IdentContext ctx) {
        String ident = ctx.IDENTITY().getText();
        if (currentSymbolTable.containsRecursive(ident)) {
            ASTTree var = currentSymbolTable.lookUp(ident);
            BaseType varType = var.getType();
            Ident id = new Ident(varType, ident);
            return id;
        }
        throw new UndeclaredIdentifierException("The variable " + ident
                + " was undefined", ctx);
    }

}





