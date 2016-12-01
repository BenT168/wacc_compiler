package main;

import antlr.WACCParser;
import frontend.Tree;
import frontend.exception.Exception;
import antlr.WACCParser.*;
import antlr.WACCParserBaseVisitor;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;
import frontend.Program;
import frontend.exception.SemanticErrorException;
import frontend.expressions.StringLeaf;
import frontend.function.ParamList;
import frontend.assignments.*;
import frontend.expressions.*;
import frontend.function.FuncDec;
import frontend.function.Param;
import frontend.statements.*;
import frontend.type.BaseType;
import frontend.type.BinaryOperators;
import frontend.type.UnaryOperators;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTree;
import symboltable.SymbolTable;

import java.util.ArrayList;
import java.util.List;


public class TypeCheckVisitor extends WACCParserBaseVisitor<Tree> {

	/* For the Usage of Debugging */
	public static final Debugger dbh = new Debugger();
	/* For Storing Variables and its information */
	private SymbolTable currentSymbolTable;
	private ParseTree parseTree;
	private Tree progTree;

	public TypeCheckVisitor(ParseTree t) {
		this.parseTree = t;
		this.currentSymbolTable = new SymbolTable();
	}

	//................................PROGRAM............................................

	@Override
	public Tree visitProgram(ProgramContext ctx) {
		// First we visit all functions and register a stub of its return type
		for (FuncContext fctx : ctx.func()) {
			registerFunction(fctx);
		}

		ArrayList<FuncDec> functions = new ArrayList<>();
		// We visit all the functions and create full nodes
		for (FuncContext fctx : ctx.func()) {
			FuncDec fdec = (FuncDec) visit(fctx);
			fdec.check(currentSymbolTable, ctx);
			functions.add(fdec);
		}

		// Then we visit the statement
		StatNode progBody = (StatNode) visit(ctx.stat());

		// Finally, we return the program node
		return new Program(functions, progBody);
	}

	public void init() {
		dbh.printV("Checking sematic integrity...");
		progTree = parseTree.accept(this);

		//Debugging: prints the WACC Tree after semantic checking
		XStream xstream = new XStream(new JsonHierarchicalStreamDriver());
		xstream.setMode(XStream.ID_REFERENCES);
		xstream.alias("Tree", Tree.class);
		dbh.printD(xstream.toXML(progTree));
	}

	/**
	 * @return Returns true iff there were no semantic error in the compiler.
	 */
	public boolean terminate() {
		return Exception.ERROR_LISTENER.complete();
	}

	public Tree getProgTree() {
		return progTree;
	}

	//....................................FUNCTION......................................

	// Function identifiers and types should already be added to ftable by time we call this method.

	@Override
	public Tree visitFunc(FuncContext ctx) {
		java.lang.String funcName = ctx.ident().getText();
		assert (currentSymbolTable.isContained(funcName));
		FuncDec func = (FuncDec) currentSymbolTable.get(funcName);

		// Create an inner scope Symbol Table for the function body.
		currentSymbolTable = new SymbolTable(currentSymbolTable, func.getType());

		// Add params to current SymbolTable
		ParamList paramList = func.getParams();
		registerParams(currentSymbolTable, paramList);

		// Create the functionBody node
		StatNode funcBody = (StatNode) visit(ctx.stat());

		// finalise the current symbolTable and restore the parent scope
		currentSymbolTable.finaliseScope(funcName);
		currentSymbolTable = currentSymbolTable.getParent();

		// Add function body statement to the function node
		func.addFuncBody(funcBody);
		func.check(currentSymbolTable, ctx);
		return func;
	}

	private void registerParams(SymbolTable st, ParamList paramList) {
		for (Param param : paramList) {
			st.add(param.getIdent(), param);
		}
	}

	//...................................PARAMETER......................................


	@Override
	public Tree visitParam(ParamContext ctx) {
		BaseType paramType = BaseType.evalType(ctx.type());
		java.lang.String ident = ctx.ident().getText();
		Param param = new Param(paramType, ident);
		param.check(currentSymbolTable, ctx);
		return param;
	}

	@Override
	public Tree visitParamList(ParamListContext ctx) {
		ParamList params = new ParamList();
		for (ParamContext p : ctx.param()) {
			Param pn = (Param) visit(p);
			params.add(pn);
		}
		params.check(currentSymbolTable, ctx);
		return params;
	}

	//....................................STAT........................................

	/*SKIP*/
	@Override
	public Tree visitSkip(SkipContext ctx) {
		return new SkipNode();
	}

	/* type IDENTITY EQUALS assignRHS */
	public Tree visitDeclare(DeclareContext ctx) {
		Assignable rhsTree = (Assignable) visit(ctx.assignRHS());
		BaseType varType = BaseType.evalType(ctx.type());
		java.lang.String ident = ctx.ident().getText();
		Variable var = new Variable(varType, ident);
		DeclareNode vdn = new DeclareNode(var, rhsTree);
		vdn.check(currentSymbolTable, ctx);

		return vdn;
	}

	/* assignLHS EQUALS assignRHS  */
	@Override
	public Tree visitAssign(AssignContext ctx) {
		AssignLHS lhs = (AssignLHS) visit(ctx.assignLHS());
		Assignable rhs = (Assignable) visit(ctx.assignRHS());
		AssignNode assignment = new AssignNode(lhs, rhs);
		assignment.check(currentSymbolTable, ctx);

		return assignment;
	}

	/**
	 * registerFunction adds a stub of the function into the currentSymbolTable
	 * without recursing into the function body. This is to support nested
	 * function calls, where a function body might call a not yet defined
	 * function.
	 *
	 * @param fctx
	 *            The FuncContext generated by the Parser
	 */
	private void registerFunction(FuncContext fctx) {
		java.lang.String funcName = fctx.ident().getText();
		if (currentSymbolTable.isRecursive(funcName)) {
			throw new SemanticErrorException("Function has already been defined", fctx);
		}
		BaseType returnType = BaseType.evalType(fctx.type());
		ParamListContext paramCtx = fctx.paramList();
		ParamList params = null;
		if (paramCtx != null) {
			params = (ParamList) visit(fctx.paramList());
		} else {
			params = new ParamList();
		}
		FuncDec func = new FuncDec(returnType, funcName, params);
		currentSymbolTable.add(funcName, func);
	}

	/*PRINT expressions*/
	@Override
	public Tree visitPrint(PrintContext ctx) {
		ExprNode expr = (ExprNode) visit(ctx.expr());
		PrintNode ps = new PrintNode(expr);
		ps.check(currentSymbolTable, ctx);

		return ps;
	}

	/*PRINTLN expressions*/
	@Override
	public Tree visitPrintln(PrintlnContext ctx) {
		ExprNode expr = (ExprNode) visit(ctx.expr());
		PrintLnNode ps = new PrintLnNode(expr);
		ps.check(currentSymbolTable, ctx);

		return ps;
	}

	/*READ assignLHS */
	@Override
	public Tree visitRead(ReadContext ctx) {
		AssignLHS lhs = (AssignLHS) visit(ctx.assignLHS());
		ReadNode rsn = new ReadNode(lhs);
		rsn.check(currentSymbolTable, ctx);

		return rsn;
	}

	/*FREE expressions */
	@Override
	public Tree visitFree(FreeContext ctx) {
		ExprNode expr = (ExprNode) visit(ctx.expr());
		FreeNode stat = new FreeNode(expr);
		stat.check(currentSymbolTable, ctx);

		return stat;
	}

	/*EXIT expressions */
	@Override
	public Tree visitExit(ExitContext ctx) {
		ExprNode exitVal = (ExprNode) visit(ctx.expr());
		ExitNode stat = new ExitNode(exitVal);
		stat.check(currentSymbolTable, ctx);

		return stat;
	}

	/*RETURN expressions*/
	@Override
	public Tree visitReturn(ReturnContext ctx) {
		ExprNode exprType = (ExprNode) visit(ctx.expr());
		ReturnNode rst = new ReturnNode(exprType);
		rst.check(currentSymbolTable, ctx);

		return rst;
	}

	/*IF ELSE statements*/
	@Override
	public Tree visitIfElse(IfElseContext ctx) {
		ExprNode ifCond = (ExprNode) visit(ctx.expr());
		StatNode thenStat = (StatNode) visit(ctx.stat(0));
		StatNode elseStat = (StatNode) visit(ctx.stat(1));
		IfElseNode ifStat = new IfElseNode(ifCond, thenStat, elseStat);
		ifStat.check(currentSymbolTable, ctx);

		return ifStat;
	}

	/*WHILE expr DO statements DONE*/
	@Override
	public Tree visitWhile(WhileContext ctx) {
		ExprNode loopCond = (ExprNode) visit(ctx.expr());
		StatNode loopBody = (StatNode) visit(ctx.stat());
		WhileNode whileStat = new WhileNode(loopCond, loopBody);
		whileStat.check(currentSymbolTable, ctx);

		return whileStat;
	}

	/*BEGIN statements END*/
	@Override
	public Tree visitBegin(BeginContext ctx) {
		currentSymbolTable = new SymbolTable(currentSymbolTable);
		StatNode stat = (StatNode) visit(ctx.stat());
		currentSymbolTable.finaliseScope();
		currentSymbolTable = currentSymbolTable.getParent();
		return stat;
	}

	/*statements ; statements*/
	@Override
	public Tree visitMultipleStat(MultipleStatContext ctx) {
		StatNode lhs = (StatNode) visit(ctx.stat(0));
		StatNode rhs = (StatNode) visit(ctx.stat(1));

		MultiStatNode seqStat = new MultiStatNode(lhs, rhs);
		seqStat.check(currentSymbolTable, ctx);

		return seqStat;
	}



			//....................EXTENSION_STAT...........................


	/*FOR stat SEMI_COLON expr SEMI_COLON expr DO stat DONE*/
	@Override
	public Tree visitForLoop(@NotNull WACCParser.ForLoopContext ctx) {
		StatNode loopCondOne = (StatNode) visit(ctx.stat(0));
		ExprNode loopCondTwo = (ExprNode) visit(ctx.expr(0));
		ExprNode loopCondThree = (ExprNode) visit(ctx.expr(1));
		StatNode loopBody = (StatNode) visit(ctx.stat(1));
		ForLoopNode forLoopStat = new ForLoopNode(loopCondOne, loopCondTwo, loopCondThree, loopBody);
		forLoopStat.check(currentSymbolTable, ctx);

		return forLoopStat;
	}

	/*CONTINUE*/
	@Override
	public Tree visitContinues(@NotNull WACCParser.ContinuesContext ctx) {
		return new ex_ContinueNode();
	}

	/*BREAK*/
	@Override
	public Tree visitBreak(@NotNull WACCParser.BreakContext ctx) {
		return new ex_BreakNode();
	}

	/*DO stat WHILE expr DONE */
	@Override
	public Tree visitDoWhile(@NotNull WACCParser.DoWhileContext ctx) {
		ExprNode loopCond = (ExprNode) visit(ctx.expr());
		StatNode loopBody = (StatNode) visit(ctx.stat());
		DoWhileNode doWhileStat = new DoWhileNode(loopCond, loopBody);
		doWhileStat.check(currentSymbolTable, ctx);

		return doWhileStat;
	}

//..................................ASSIGNMENT......................................

	@Override
	public Tree visitNewPair_assignRHS(NewPair_assignRHSContext ctx) {
		ExprNode fst = (ExprNode) visit(ctx.expr(0));
		ExprNode snd = (ExprNode) visit(ctx.expr(1));
		NewPair pair = new NewPair(fst, snd);
		pair.check(currentSymbolTable, ctx);
		return pair;
	}

	@Override
	public Tree visitFuncCall_assignRHS(FuncCall_assignRHSContext ctx) {
		java.lang.String ident = ctx.ident().getText();
		FuncDec funcDef = (FuncDec) currentSymbolTable.get(ident);
		ArgList args;

		//Here we check that the call has arguments
		//if no arguments are present, a info empty arg_list will be made.
		if (ctx.argList() == null) {
			args = new ArgList();
		} else {
			args = (ArgList) visit(ctx.argList());
		}
		CallFunc callStat = new CallFunc(funcDef, args);
		callStat.check(currentSymbolTable, ctx);
		return callStat;
	}

	@Override
	public Tree visitArgList(ArgListContext ctx) {
		int argListLength = ctx.expr().size();
		ArgList args = new ArgList();
		for (int i = 0; i < argListLength; i++) {
			args.add((ExprNode) visit(ctx.expr(i)));
		}
		args.check(currentSymbolTable, ctx);
		return args;
	}

	/*
	 * Rule:
	 * 		(FST | SND) ident
	 */
	@Override
	public Tree visitPairElem(PairElemContext ctx) {
		java.lang.String fstOrSnd = ctx.getChild(0).getText();
		ExprNode expr = (ExprNode) visit(ctx.expr());
		PairElem pairElem = new PairElem(fstOrSnd, expr);
		pairElem.check(currentSymbolTable, ctx);
		return pairElem;
	}


	@Override
	public Tree visitArrayElem(ArrayElemContext ctx) {
		java.lang.String ident = ctx.ident().getText();
		List<ExprContext> exprCtxs = ctx.expr();
		ArrayList<ExprNode> exprs = new ArrayList<ExprNode>();
		for (ExprContext ec : exprCtxs) {
			exprs.add((ExprNode) visit(ec));
		}

		Variable var = (Variable) currentSymbolTable.get(ident);
		ArrayElem arrayElem = new ArrayElem(exprs, var);
		return arrayElem;
	}

	@Override
	public Tree visitArrayLiter(ArrayLiterContext ctx) {
		ArrayList<ExprNode> elems = new ArrayList<ExprNode>();

		for (int i = 0; i < ctx.expr().size(); i++) {
			elems.add((ExprNode) visit(ctx.expr(i)));
		}

		ArrayLiter arrayLiter = new ArrayLiter(elems);
		arrayLiter.check(currentSymbolTable, ctx);
		return arrayLiter;
	}

	//..................................EXPRESSION......................................

	@Override
	public Tree visitExpr(ExprContext ctx) {
		// if it's an atomic `( expressions )` expression, we just call visit on the
		// inner expressions
		if (ctx.OPEN_PARENTHESES() != null) {
			assert (ctx.children.size() == 3);
			return visit(ctx.expr(0));
		}

		switch (ctx.getChildCount()) {
			case 3:
				// Binary Expression of type `lhs OP rhs`
				ExprNode lhs = (ExprNode) visit(ctx.expr(0));
				ExprNode rhs = (ExprNode) visit(ctx.expr(1));
				BinaryOperators binaryOp = BinaryOperators.evalBinOp(ctx.getChild(1).getText());
				BinaryExpr binExpr = new BinaryExpr(lhs, binaryOp, rhs);
				binExpr.check(currentSymbolTable, ctx);

				return binExpr;

			case 2:
				// Unary Expression of type 'ident OP'
				if(ctx.ident() != null && (ctx.PLUSPLUS() != null || ctx.MINUSMINUS() != null)) {
					ExprNode expr = (ExprNode) visit(ctx.ident());
					UnaryOperators unaryOp = UnaryOperators.evalUnOp(ctx.getChild(1).getText());
					UnaryExpr unaryExpr = new UnaryExpr(unaryOp, expr);
					unaryExpr.check(currentSymbolTable, ctx);

					return unaryExpr;
				} else {
					// Unary Expression of type `OP expressions`
					ExprNode expr = (ExprNode) visit(ctx.expr(0));
					UnaryOperators unaryOp = UnaryOperators.evalUnOp(ctx.getChild(0).getText());
					UnaryExpr unaryExpr = new UnaryExpr(unaryOp, expr);
					unaryExpr.check(currentSymbolTable, ctx);

					return unaryExpr;

				}
			default: // in this case this is a single rule (i.e. int_liter, char_liter)
				return visit(ctx.getChild(0));
		}
	}

	// PairLeaf literals are null by default.
	@Override
	public Tree visitPairLiter(PairLiterContext ctx) {
		return new PairLiter();
	}

	/*
	 * Rule: `newpair`(expressions, expressions)
	 */

	@Override
	public Tree visitCharLiter(CharLiterContext ctx) {
		CharLeaf charleaf = new CharLeaf(ctx.getText());
		charleaf.check(currentSymbolTable, ctx);
		return charleaf;
	}

	@Override
	public Tree visitIntLiter(IntLiterContext ctx) {
		String intValue = ctx.getText();
		IntLeaf anIntLeaf = new IntLeaf(intValue);
		anIntLeaf.check(currentSymbolTable, ctx);
		return anIntLeaf;
	}

	@Override
	public Tree visitBoolLiter(BoolLiterContext ctx) {
		BoolLeaf boolLeaf = new BoolLeaf(ctx.getText());
		boolLeaf.check(currentSymbolTable, ctx);
		return boolLeaf;
	}

	@Override
	public Tree visitStringLiter(StringLiterContext ctx) {
		StringLeaf strLeaf = new StringLeaf(ctx.getText());
		strLeaf.check(currentSymbolTable, ctx);
		return strLeaf;
	}

				//...............EXTENSION_EXPRESSION...........................

	@Override
	public Tree visitBinLiter(BinLiterContext ctx) {
		ex_BinLeaf exBinLeaf = new ex_BinLeaf(ctx.getText());
		exBinLeaf.check(currentSymbolTable, ctx);
		return exBinLeaf;
	}

	@Override
	public Tree visitOctLiter(OctLiterContext ctx) {
		ex_OctLeaf exOctLeaf = new ex_OctLeaf(ctx.getText());
		exOctLeaf.check(currentSymbolTable, ctx);
		return exOctLeaf;
	}

	@Override
	public Tree visitHexLiter(HexLiterContext ctx) {
		ex_HexLeaf exHexLeaf = new ex_HexLeaf(ctx.getText());
		exHexLeaf.check(currentSymbolTable, ctx);
		return exHexLeaf;
	}

	//..................................IDENTITY......................................

	/*
	 * This method will only be reached in the case of a variable assignment. If
	 * the variable is undeclared we want to throw an exception and stop checking
	 * further. (non-Javadoc)
	 *
	 * @see
	 * WACCParserBaseVisitor#visitIdent(WACCParser.IdentContext)
	 */
	@Override
	public Tree visitIdent(IdentContext ctx) {
		java.lang.String ident = ctx.IDENTITY().getText();
		if (currentSymbolTable.isRecursive(ident)) {
			Tree var = currentSymbolTable.get(ident);
			return var;
		}

		throw new SemanticErrorException("The variable " + ident
				+ " was undefined", ctx);
	}


}
