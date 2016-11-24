package main;

import WACCExceptions.NotUniqueIdentifierException;
import WACCExceptions.UndeclaredIdentifierException;
import WACCExceptions.WACCException;
import antlr.WACCParser.*;
import antlr.WACCParserBaseVisitor;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;
import frontend.ProgNode;
import frontend.tree;
import frontend.assignments.*;
import frontend.expr.*;
import frontend.func.FuncDecNode;
import frontend.func.ParamListNode;
import frontend.func.ParamNode;
import frontend.stat.*;
import frontend.type.WACCBinOp;
import frontend.type.WACCType;
import frontend.type.WACCUnOp;
import org.antlr.v4.runtime.tree.ParseTree;
import symboltable.SymbolTable;
import util.Debugger;

import java.util.ArrayList;
import java.util.List;


public class TypeCheckVisitor extends WACCParserBaseVisitor<tree> {

	/* For the Usage of Debugging */
	public static final Debugger dbh = new Debugger();
	/* For Storing Variables and its information */
	private SymbolTable currentSymbolTable;
	private ParseTree parseTree;
	private tree progTree;

	public TypeCheckVisitor(ParseTree t) {
		this.parseTree = t;
		this.currentSymbolTable = new SymbolTable();
	}

	//................................PROGRAM............................................

	@Override
	public tree visitProgram(ProgramContext ctx) {
		// First we visit all functions and register a stub of its return type
		for (FuncContext fctx : ctx.func()) {
			registerFunction(fctx);
		}

		ArrayList<FuncDecNode> functions = new ArrayList<>();
		// We visit all the functions and create full nodes
		for (FuncContext fctx : ctx.func()) {
			FuncDecNode fdec = (FuncDecNode) visit(fctx);
			fdec.check(currentSymbolTable, ctx);
			functions.add(fdec);
		}

		// Then we visit the statement
		StatNode progBody = (StatNode) visit(ctx.stat());

		// Finally, we return the program node
		return new ProgNode(functions, progBody);
	}

	public void init() {
		dbh.printV("Checking sematic integrity...");
		progTree = parseTree.accept(this);
		
		//Debugging: prints the WACC tree after semantic checking
		XStream xstream = new XStream(new JsonHierarchicalStreamDriver());
		xstream.setMode(XStream.ID_REFERENCES);
		xstream.alias("tree", tree.class);
		dbh.printD(xstream.toXML(progTree));
	}

	/**
	 * @return Returns true iff there were no semantic error in the compiler.
	 */
	public boolean terminate() {
		return WACCException.ERROR_LISTENER.finish();
	}
	
	public tree getProgTree() {
		return progTree;
	}

	//....................................FUNCTION......................................

	// Function identifiers and types should already be added to ftable by time we call this method.

	@Override
	public tree visitFunc(FuncContext ctx) {
		String funcName = ctx.ident().getText();
		assert (currentSymbolTable.containsCurrent(funcName));
		FuncDecNode func = (FuncDecNode) currentSymbolTable.get(funcName);

		// Create an inner scope Symbol Table for the function body.
		currentSymbolTable = new SymbolTable(currentSymbolTable, func.getType());

		// Add params to current SymbolTable
		ParamListNode paramList = func.getParams();
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

	private void registerParams(SymbolTable st, ParamListNode paramList) {
		for (ParamNode param : paramList) {
			st.add(param.getIdent(), param);
		}
	}

	//...................................PARAMETER......................................


	@Override
	public tree visitParam(ParamContext ctx) {
		WACCType paramType = WACCType.evalType(ctx.type());
		String ident = ctx.ident().getText();
		ParamNode paramNode = new ParamNode(paramType, ident);
		paramNode.check(currentSymbolTable, ctx);
		return paramNode;
	}

	@Override
	public tree visitParamList(ParamListContext ctx) {
		ParamListNode params = new ParamListNode();
		for (ParamContext p : ctx.param()) {
			ParamNode pn = (ParamNode) visit(p);
			params.add(pn);
		}
		params.check(currentSymbolTable, ctx);
		return params;
	}

	//....................................STAT........................................

	/*SKIP*/
	@Override
	public tree visitSkip(SkipContext ctx) {
		SkipStatNode ssn = new SkipStatNode();

		return ssn;
	}

	/* type IDENTITY EQUALS assignRHS */
	public tree visitDeclare(DeclareContext ctx) {
		Assignable rhsTree = (Assignable) visit(ctx.assignRHS());
		WACCType varType = WACCType.evalType(ctx.type());
		String ident = ctx.ident().getText();
		VarNode var = new VarNode(varType, ident);
		VarDecNode vdn = new VarDecNode(var, rhsTree);
		vdn.check(currentSymbolTable, ctx);

		return vdn;
	}

	/* assignLHS EQUALS assignRHS  */
	@Override
	public tree visitAssign(AssignContext ctx) {
		AssignLhsNode lhs = (AssignLhsNode) visit(ctx.assignLHS());
		Assignable rhs = (Assignable) visit(ctx.assignRHS());
		AssignStatNode assignment = new AssignStatNode(lhs, rhs);
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
		String funcName = fctx.ident().getText();
		if (currentSymbolTable.containsRecursive(funcName)) {
			throw new NotUniqueIdentifierException("Function has already been defined", fctx);
		}
		WACCType returnType = WACCType.evalType(fctx.type());
		ParamListContext paramCtx = fctx.paramList();
		ParamListNode params = null;
		if (paramCtx != null) {
			params = (ParamListNode) visit(fctx.paramList());
		} else {
			params = new ParamListNode();
		}
		FuncDecNode func = new FuncDecNode(returnType, funcName, params);
		currentSymbolTable.add(funcName, func);
	}

	/*PRINT expr*/
	@Override
	public tree visitPrint(PrintContext ctx) {
		ExprNode expr = (ExprNode) visit(ctx.expr());
		PrintStat ps = new PrintStat(expr);
		ps.check(currentSymbolTable, ctx);

		return ps;
	}

	/*PRINTLN expr*/
	@Override
	public tree visitPrintln(PrintlnContext ctx) {
		ExprNode expr = (ExprNode) visit(ctx.expr());
		PrintLnStat ps = new PrintLnStat(expr);
		ps.check(currentSymbolTable, ctx);

		return ps;
	}

	/*READ assignLHS */
	@Override
	public tree visitRead(ReadContext ctx) {
		AssignLhsNode lhs = (AssignLhsNode) visit(ctx.assignLHS());
		ReadStatNode rsn = new ReadStatNode(lhs);
		rsn.check(currentSymbolTable, ctx);

		return rsn;
	}

	/*FREE expr */
	@Override
	public tree visitFree(FreeContext ctx) {
		ExprNode expr = (ExprNode) visit(ctx.expr());
		FreeStat stat = new FreeStat(expr);
		stat.check(currentSymbolTable, ctx);

		return stat;
	}

	/*EXIT expr */
	@Override
	public tree visitExit(ExitContext ctx) {
		ExprNode exitVal = (ExprNode) visit(ctx.expr());
		ExitStat stat = new ExitStat(exitVal);
		stat.check(currentSymbolTable, ctx);

		return stat;
	}

	/*RETURN expr*/
	@Override
	public tree visitReturn(ReturnContext ctx) {
		ExprNode exprType = (ExprNode) visit(ctx.expr());
		ReturnStatNode rst = new ReturnStatNode(exprType);
		rst.check(currentSymbolTable, ctx);

		return rst;
	}

	/*IF ELSE stat*/
	@Override
	public tree visitIfElse(IfElseContext ctx) {
		ExprNode ifCond = (ExprNode) visit(ctx.expr());
		StatNode thenStat = (StatNode) visit(ctx.stat(0));
		StatNode elseStat = (StatNode) visit(ctx.stat(1));
		IfStatNode ifStat = new IfStatNode(ifCond, thenStat, elseStat);
		ifStat.check(currentSymbolTable, ctx);

		return ifStat;
	}


	/*WHILE stat*/
	@Override
	public tree visitWhile(WhileContext ctx) {
		ExprNode loopCond = (ExprNode) visit(ctx.expr());
		StatNode loopBody = (StatNode) visit(ctx.stat());
		WhileStatNode whileStat = new WhileStatNode(loopCond, loopBody);
		whileStat.check(currentSymbolTable, ctx);

		return whileStat;
	}

	/*BEGIN stat END*/
	@Override
	public tree visitBegin(BeginContext ctx) {
		currentSymbolTable = new SymbolTable(currentSymbolTable);
		StatNode stat = (StatNode) visit(ctx.stat());
		currentSymbolTable.finaliseScope();
		currentSymbolTable = currentSymbolTable.getParent();
		return stat;
	}

	/*stat ; stat*/
	@Override
	public tree visitMultipleStat(MultipleStatContext ctx) {
		StatNode lhs = (StatNode) visit(ctx.stat(0));
		StatNode rhs = (StatNode) visit(ctx.stat(1));
		;
		SeqStatNode seqStat = new SeqStatNode(lhs, rhs);
		seqStat.check(currentSymbolTable, ctx);

		return seqStat;
	}


//..................................ASSIGNMENT......................................

	@Override
	public tree visitNewpair_assignment(Newpair_assignmentContext ctx) {
		ExprNode fst = (ExprNode) visit(ctx.expr(0));
		ExprNode snd = (ExprNode) visit(ctx.expr(1));
		NewPairNode pair = new NewPairNode(fst, snd);
		pair.check(currentSymbolTable, ctx);
		return pair;
	}

	@Override
	public tree visitFunc_call_assignment(Func_call_assignmentContext ctx) {
		String ident = ctx.ident().getText();
		FuncDecNode funcDef = (FuncDecNode) currentSymbolTable.get(ident);
		ArgListNode args;
		
		//Here we check that the call has arguments
		//if no arguments are present, a new empty arg_list will be made.
		if (ctx.argList() == null) {
			args = new ArgListNode();
		} else {
			args = (ArgListNode) visit(ctx.argList());
		}
		CallStatNode callStat = new CallStatNode(funcDef, args);
		callStat.check(currentSymbolTable, ctx);
		return callStat;
	}

	@Override
	public tree visitArgList(ArgListContext ctx) {
		int argListLength = ctx.expr().size();
		ArgListNode args = new ArgListNode();
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
	public tree visitPairElem(PairElemContext ctx) {
		String fstOrSnd = ctx.getChild(0).getText();
		ExprNode expr = (ExprNode) visit(ctx.expr());
		PairElemNode pairElem = new PairElemNode(fstOrSnd, expr);
		pairElem.check(currentSymbolTable, ctx);
		return pairElem;
	}


	@Override
	public tree visitArrayElem(ArrayElemContext ctx) {
		String ident = ctx.ident().getText();
		List<ExprContext> exprCtxs = ctx.expr();
		ArrayList<ExprNode> exprs = new ArrayList<ExprNode>();
		for (ExprContext ec : exprCtxs) {
			exprs.add((ExprNode) visit(ec));
		}

		VarNode var = (VarNode) currentSymbolTable.get(ident);
		ArrayElemNode arrayElem = new ArrayElemNode(exprs, var);
		return arrayElem;
	}

	@Override
	public tree visitArrayLiter(ArrayLiterContext ctx) {
		ArrayList<ExprNode> elems = new ArrayList<ExprNode>();

		for (int i = 0; i < ctx.expr().size(); i++) {
			elems.add((ExprNode) visit(ctx.expr(i)));
		}

		ArrayLiterNode arrayLiter = new ArrayLiterNode(elems);
		arrayLiter.check(currentSymbolTable, ctx);
		return arrayLiter;
	}

	//..................................EXPRESSION......................................

	@Override
	public tree visitExpr(ExprContext ctx) {
		// if it's an atomic `( expr )` expression, we just call visit on the
		// inner expr
		if (ctx.OPEN_PARENTHESES() != null) {
			assert (ctx.children.size() == 3);
			return visit(ctx.expr(0));
		}

		switch (ctx.getChildCount()) {
			case 3: // Binary Expression of type `lhs OP rhs`
				ExprNode lhs = (ExprNode) visit(ctx.expr(0));
				ExprNode rhs = (ExprNode) visit(ctx.expr(1));
				WACCBinOp binaryOp = WACCBinOp.evalBinOp(ctx.getChild(1).getText());
				BinExprNode binExpr = new BinExprNode(lhs, binaryOp, rhs);
				binExpr.check(currentSymbolTable, ctx);

				return binExpr;

			case 2: // Unary Expression of type `OP expr`
				ExprNode expr = (ExprNode) visit(ctx.expr(0));
				WACCUnOp unaryOp = WACCUnOp.evalUnOp(ctx.getChild(0).getText());
				UnExprNode unaryExpr = new UnExprNode(unaryOp, expr);
				unaryExpr.check(currentSymbolTable, ctx);

				return unaryExpr;

			default: // in this case this is a single rule (i.e. int_liter, char_liter)
				return visit(ctx.getChild(0));
		}
	}

	// Pair literals are null by default.
	@Override
	public tree visitPairLiter(PairLiterContext ctx) {
		return new PairLiterNode();
	}

	/*
	 * Rule: `newpair`(expr, expr)
	 */

	@Override
	public tree visitCharLiter(CharLiterContext ctx) {
		CharLeaf charleaf = new CharLeaf(ctx.getText());
		charleaf.check(currentSymbolTable, ctx);
		return charleaf;
	}

	@Override
	public tree visitIntLiter(IntLiterContext ctx) {
		String intValue = ctx.getText();
		IntLeaf intLeaf = new IntLeaf(intValue);
		intLeaf.check(currentSymbolTable, ctx);
		return intLeaf;
	}

	@Override
	public tree visitBoolLiter(BoolLiterContext ctx) {
		BoolLeaf boolLeaf = new BoolLeaf(ctx.getText());
		boolLeaf.check(currentSymbolTable, ctx);
		return boolLeaf;
	}

	@Override
	public tree visitStringLiter(StringLiterContext ctx) {
		StringLeaf strLeaf = new StringLeaf(ctx.getText());
		strLeaf.check(currentSymbolTable, ctx);
		return strLeaf;
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
	public tree visitIdent(IdentContext ctx) {
		String ident = ctx.IDENTITY().getText();
		if (currentSymbolTable.containsRecursive(ident)) {
			tree var = currentSymbolTable.get(ident);
			return var;
		}

		throw new UndeclaredIdentifierException("The variable " + ident
				+ " was undefined", ctx);
	}


}
