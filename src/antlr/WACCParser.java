// Generated from ./WACCParser.g4 by ANTLR 4.4
package antlr;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class WACCParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.4", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		PRINT=44, HASH_KEY=50, APOSTROPHE=75, NEWPAIR=61, DO=57, FORM_FEED=71, 
		CHR=20, MINUS=2, END_OF_STRING=67, GREATER_EQUAL=9, BACKSLASH=73, WHITESPACE=74, 
		BREAK=59, ELSE=53, LESS_EQUAL=11, IF=51, INTEGER=34, DONE=56, NULL=45, 
		MUL=3, DOUBLE_EQUALS=13, FST=62, NEWLINE=68, STRING_LITER=65, FOR=58, 
		MINUSMINUS=22, TRUE=23, IS=37, READ=39, IDENTITY=76, NOT=17, AND=15, LESS=12, 
		END=36, SEMI_COLON=47, THEN=52, EXIT=42, PLUS=1, CLOSE_PARENTHESES=31, 
		ORD=19, CALL=64, PRINTLN=43, OPEN_PARENTHESES=30, SND=63, CHAR=27, BEGIN=35, 
		FREE=40, PLUSPLUS=21, INT=25, COMMENT=46, CARRIAGE_RETURN=70, RETURN=41, 
		SKIP=38, WS=66, DOUBLE_QUOTES=72, COMMA=49, MOD=5, OR=16, EQUAL=6, GREATER=10, 
		ENDIF=54, COLON=48, CHAR_LITER=77, DIV=4, CONTINUE=60, OPEN_SQUARE=32, 
		LEN=18, TAB=69, BOOL=26, PLUSEQUAL=7, MINUSEQUAL=8, NOT_EQUAL=14, STRING=28, 
		CLOSE_SQUARE=33, FALSE=24, WHILE=55, PAIR=29;
	public static final String[] tokenNames = {
		"<INVALID>", "'+'", "'-'", "'*'", "'/'", "'%'", "'='", "'+='", "'-='", 
		"'>='", "'>'", "'<='", "'<'", "'=='", "'!='", "'&&'", "'||'", "'!'", "'len'", 
		"'ord'", "'chr'", "'++'", "'--'", "'true'", "'false'", "'int'", "'bool'", 
		"'char'", "'string'", "'pair'", "'('", "')'", "'['", "']'", "INTEGER", 
		"'begin'", "'end'", "'is'", "'skip'", "'read'", "'free'", "'return'", 
		"'exit'", "'println'", "'print'", "'null'", "COMMENT", "';'", "':'", "','", 
		"'#'", "'if'", "'then'", "'else'", "'fi'", "'while'", "'done'", "'do'", 
		"'for'", "'break'", "'continue'", "'newpair'", "'fst'", "'snd'", "'call'", 
		"STRING_LITER", "WS", "'\\0'", "'\\n'", "'\\t'", "'\\r'", "'\\f'", "'\\\"'", 
		"'\\'", "' '", "'''", "IDENTITY", "CHAR_LITER"
	};
	public static final int
		RULE_program = 0, RULE_func = 1, RULE_paramList = 2, RULE_param = 3, RULE_stat = 4, 
		RULE_assignLHS = 5, RULE_assignRHS = 6, RULE_argList = 7, RULE_pairType = 8, 
		RULE_pairElem = 9, RULE_pairElemType = 10, RULE_arrayType = 11, RULE_type = 12, 
		RULE_baseType = 13, RULE_expr = 14, RULE_arrayElem = 15, RULE_intLiter = 16, 
		RULE_boolLiter = 17, RULE_charLiter = 18, RULE_stringLiter = 19, RULE_arrayLiter = 20, 
		RULE_pairLiter = 21, RULE_ident = 22;
	public static final String[] ruleNames = {
		"program", "func", "paramList", "param", "stat", "assignLHS", "assignRHS", 
		"argList", "pairType", "pairElem", "pairElemType", "arrayType", "type", 
		"baseType", "expr", "arrayElem", "intLiter", "boolLiter", "charLiter", 
		"stringLiter", "arrayLiter", "pairLiter", "ident"
	};

	@Override
	public String getGrammarFileName() { return "WACCParser.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public WACCParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ProgramContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(WACCParser.EOF, 0); }
		public List<FuncContext> func() {
			return getRuleContexts(FuncContext.class);
		}
		public FuncContext func(int i) {
			return getRuleContext(FuncContext.class,i);
		}
		public TerminalNode BEGIN() { return getToken(WACCParser.BEGIN, 0); }
		public TerminalNode END() { return getToken(WACCParser.END, 0); }
		public StatContext stat() {
			return getRuleContext(StatContext.class,0);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(46); match(BEGIN);
			setState(50);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(47); func();
					}
					} 
				}
				setState(52);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			}
			setState(53); stat(0);
			setState(54); match(END);
			setState(55); match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FuncContext extends ParserRuleContext {
		public TerminalNode CLOSE_PARENTHESES() { return getToken(WACCParser.CLOSE_PARENTHESES, 0); }
		public TerminalNode IS() { return getToken(WACCParser.IS, 0); }
		public ParamListContext paramList() {
			return getRuleContext(ParamListContext.class,0);
		}
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public TerminalNode END() { return getToken(WACCParser.END, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public StatContext stat() {
			return getRuleContext(StatContext.class,0);
		}
		public TerminalNode OPEN_PARENTHESES() { return getToken(WACCParser.OPEN_PARENTHESES, 0); }
		public FuncContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_func; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitFunc(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncContext func() throws RecognitionException {
		FuncContext _localctx = new FuncContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_func);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(57); type();
			setState(58); ident();
			setState(59); match(OPEN_PARENTHESES);
			setState(61);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INT) | (1L << BOOL) | (1L << CHAR) | (1L << STRING) | (1L << PAIR))) != 0)) {
				{
				setState(60); paramList();
				}
			}

			setState(63); match(CLOSE_PARENTHESES);
			setState(64); match(IS);
			setState(65); stat(0);
			setState(66); match(END);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParamListContext extends ParserRuleContext {
		public List<ParamContext> param() {
			return getRuleContexts(ParamContext.class);
		}
		public List<TerminalNode> COMMA() { return getTokens(WACCParser.COMMA); }
		public ParamContext param(int i) {
			return getRuleContext(ParamContext.class,i);
		}
		public TerminalNode COMMA(int i) {
			return getToken(WACCParser.COMMA, i);
		}
		public ParamListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_paramList; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitParamList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParamListContext paramList() throws RecognitionException {
		ParamListContext _localctx = new ParamListContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_paramList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(68); param();
			setState(73);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(69); match(COMMA);
				setState(70); param();
				}
				}
				setState(75);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParamContext extends ParserRuleContext {
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public ParamContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_param; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitParam(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParamContext param() throws RecognitionException {
		ParamContext _localctx = new ParamContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_param);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(76); type();
			setState(77); ident();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatContext extends ParserRuleContext {
		public StatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stat; }
	 
		public StatContext() { }
		public void copyFrom(StatContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ReadContext extends StatContext {
		public TerminalNode READ() { return getToken(WACCParser.READ, 0); }
		public AssignLHSContext assignLHS() {
			return getRuleContext(AssignLHSContext.class,0);
		}
		public ReadContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitRead(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class DeclareContext extends StatContext {
		public TerminalNode EQUAL() { return getToken(WACCParser.EQUAL, 0); }
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public AssignRHSContext assignRHS() {
			return getRuleContext(AssignRHSContext.class,0);
		}
		public DeclareContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitDeclare(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BreakContext extends StatContext {
		public TerminalNode BREAK() { return getToken(WACCParser.BREAK, 0); }
		public BreakContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitBreak(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SkipContext extends StatContext {
		public TerminalNode SKIP() { return getToken(WACCParser.SKIP, 0); }
		public SkipContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitSkip(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class WhileContext extends StatContext {
		public TerminalNode DONE() { return getToken(WACCParser.DONE, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode DO() { return getToken(WACCParser.DO, 0); }
		public StatContext stat() {
			return getRuleContext(StatContext.class,0);
		}
		public TerminalNode WHILE() { return getToken(WACCParser.WHILE, 0); }
		public WhileContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitWhile(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExitContext extends StatContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode EXIT() { return getToken(WACCParser.EXIT, 0); }
		public ExitContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitExit(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PrintContext extends StatContext {
		public TerminalNode PRINT() { return getToken(WACCParser.PRINT, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public PrintContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitPrint(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PrintlnContext extends StatContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode PRINTLN() { return getToken(WACCParser.PRINTLN, 0); }
		public PrintlnContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitPrintln(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class DoWhileContext extends StatContext {
		public TerminalNode DONE() { return getToken(WACCParser.DONE, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode DO() { return getToken(WACCParser.DO, 0); }
		public StatContext stat() {
			return getRuleContext(StatContext.class,0);
		}
		public TerminalNode WHILE() { return getToken(WACCParser.WHILE, 0); }
		public DoWhileContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitDoWhile(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ContinueContext extends StatContext {
		public TerminalNode CONTINUE() { return getToken(WACCParser.CONTINUE, 0); }
		public ContinueContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitContinue(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MultipleStatContext extends StatContext {
		public TerminalNode SEMI_COLON() { return getToken(WACCParser.SEMI_COLON, 0); }
		public StatContext stat(int i) {
			return getRuleContext(StatContext.class,i);
		}
		public List<StatContext> stat() {
			return getRuleContexts(StatContext.class);
		}
		public MultipleStatContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitMultipleStat(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FreeContext extends StatContext {
		public TerminalNode FREE() { return getToken(WACCParser.FREE, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public FreeContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitFree(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IfElseContext extends StatContext {
		public TerminalNode ELSE() { return getToken(WACCParser.ELSE, 0); }
		public TerminalNode IF() { return getToken(WACCParser.IF, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode THEN() { return getToken(WACCParser.THEN, 0); }
		public TerminalNode ENDIF() { return getToken(WACCParser.ENDIF, 0); }
		public StatContext stat(int i) {
			return getRuleContext(StatContext.class,i);
		}
		public List<StatContext> stat() {
			return getRuleContexts(StatContext.class);
		}
		public IfElseContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitIfElse(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ForLoopContext extends StatContext {
		public TerminalNode DONE() { return getToken(WACCParser.DONE, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public TerminalNode DO() { return getToken(WACCParser.DO, 0); }
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> SEMI_COLON() { return getTokens(WACCParser.SEMI_COLON); }
		public TerminalNode SEMI_COLON(int i) {
			return getToken(WACCParser.SEMI_COLON, i);
		}
		public TerminalNode FOR() { return getToken(WACCParser.FOR, 0); }
		public StatContext stat(int i) {
			return getRuleContext(StatContext.class,i);
		}
		public List<StatContext> stat() {
			return getRuleContexts(StatContext.class);
		}
		public ForLoopContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitForLoop(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BeginContext extends StatContext {
		public TerminalNode BEGIN() { return getToken(WACCParser.BEGIN, 0); }
		public TerminalNode END() { return getToken(WACCParser.END, 0); }
		public StatContext stat() {
			return getRuleContext(StatContext.class,0);
		}
		public BeginContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitBegin(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ReturnContext extends StatContext {
		public TerminalNode RETURN() { return getToken(WACCParser.RETURN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ReturnContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitReturn(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AssignContext extends StatContext {
		public TerminalNode EQUAL() { return getToken(WACCParser.EQUAL, 0); }
		public AssignRHSContext assignRHS() {
			return getRuleContext(AssignRHSContext.class,0);
		}
		public AssignLHSContext assignLHS() {
			return getRuleContext(AssignLHSContext.class,0);
		}
		public AssignContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitAssign(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatContext stat() throws RecognitionException {
		return stat(0);
	}

	private StatContext stat(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		StatContext _localctx = new StatContext(_ctx, _parentState);
		StatContext _prevctx = _localctx;
		int _startState = 8;
		enterRecursionRule(_localctx, 8, RULE_stat, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(138);
			switch (_input.LA(1)) {
			case SKIP:
				{
				_localctx = new SkipContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(80); match(SKIP);
				}
				break;
			case INT:
			case BOOL:
			case CHAR:
			case STRING:
			case PAIR:
				{
				_localctx = new DeclareContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(81); type();
				setState(82); ident();
				setState(83); match(EQUAL);
				setState(84); assignRHS();
				}
				break;
			case FST:
			case SND:
			case IDENTITY:
				{
				_localctx = new AssignContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(86); assignLHS();
				setState(87); match(EQUAL);
				setState(88); assignRHS();
				}
				break;
			case READ:
				{
				_localctx = new ReadContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(90); match(READ);
				setState(91); assignLHS();
				}
				break;
			case FREE:
				{
				_localctx = new FreeContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(92); match(FREE);
				setState(93); expr(0);
				}
				break;
			case RETURN:
				{
				_localctx = new ReturnContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(94); match(RETURN);
				setState(95); expr(0);
				}
				break;
			case EXIT:
				{
				_localctx = new ExitContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(96); match(EXIT);
				setState(97); expr(0);
				}
				break;
			case PRINT:
				{
				_localctx = new PrintContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(98); match(PRINT);
				setState(99); expr(0);
				}
				break;
			case PRINTLN:
				{
				_localctx = new PrintlnContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(100); match(PRINTLN);
				setState(101); expr(0);
				}
				break;
			case IF:
				{
				_localctx = new IfElseContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(102); match(IF);
				setState(103); expr(0);
				setState(104); match(THEN);
				setState(105); stat(0);
				setState(106); match(ELSE);
				setState(107); stat(0);
				setState(108); match(ENDIF);
				}
				break;
			case WHILE:
				{
				_localctx = new WhileContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(110); match(WHILE);
				setState(111); expr(0);
				setState(112); match(DO);
				setState(113); stat(0);
				setState(114); match(DONE);
				}
				break;
			case DO:
				{
				_localctx = new DoWhileContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(116); match(DO);
				setState(117); stat(0);
				setState(118); match(WHILE);
				setState(119); expr(0);
				setState(120); match(DONE);
				}
				break;
			case FOR:
				{
				_localctx = new ForLoopContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(122); match(FOR);
				setState(123); stat(0);
				setState(124); match(SEMI_COLON);
				setState(125); expr(0);
				setState(126); match(SEMI_COLON);
				setState(127); expr(0);
				setState(128); match(DO);
				setState(129); stat(0);
				setState(130); match(DONE);
				}
				break;
			case BEGIN:
				{
				_localctx = new BeginContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(132); match(BEGIN);
				setState(133); stat(0);
				setState(134); match(END);
				}
				break;
			case BREAK:
				{
				_localctx = new BreakContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(136); match(BREAK);
				}
				break;
			case CONTINUE:
				{
				_localctx = new ContinueContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(137); match(CONTINUE);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(145);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new MultipleStatContext(new StatContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_stat);
					setState(140);
					if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
					setState(141); match(SEMI_COLON);
					setState(142); stat(4);
					}
					} 
				}
				setState(147);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class AssignLHSContext extends ParserRuleContext {
		public ArrayElemContext arrayElem() {
			return getRuleContext(ArrayElemContext.class,0);
		}
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public PairElemContext pairElem() {
			return getRuleContext(PairElemContext.class,0);
		}
		public AssignLHSContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignLHS; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitAssignLHS(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignLHSContext assignLHS() throws RecognitionException {
		AssignLHSContext _localctx = new AssignLHSContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_assignLHS);
		try {
			setState(151);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(148); ident();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(149); arrayElem();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(150); pairElem();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AssignRHSContext extends ParserRuleContext {
		public AssignRHSContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignRHS; }
	 
		public AssignRHSContext() { }
		public void copyFrom(AssignRHSContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class PairElem_assignRHSContext extends AssignRHSContext {
		public PairElemContext pairElem() {
			return getRuleContext(PairElemContext.class,0);
		}
		public PairElem_assignRHSContext(AssignRHSContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitPairElem_assignRHS(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NewPair_assignRHSContext extends AssignRHSContext {
		public TerminalNode NEWPAIR() { return getToken(WACCParser.NEWPAIR, 0); }
		public TerminalNode CLOSE_PARENTHESES() { return getToken(WACCParser.CLOSE_PARENTHESES, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode COMMA() { return getToken(WACCParser.COMMA, 0); }
		public TerminalNode OPEN_PARENTHESES() { return getToken(WACCParser.OPEN_PARENTHESES, 0); }
		public NewPair_assignRHSContext(AssignRHSContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitNewPair_assignRHS(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ArrayLiter_assignRHSContext extends AssignRHSContext {
		public ArrayLiterContext arrayLiter() {
			return getRuleContext(ArrayLiterContext.class,0);
		}
		public ArrayLiter_assignRHSContext(AssignRHSContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitArrayLiter_assignRHS(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FuncCall_assignRHSContext extends AssignRHSContext {
		public ArgListContext argList() {
			return getRuleContext(ArgListContext.class,0);
		}
		public TerminalNode CLOSE_PARENTHESES() { return getToken(WACCParser.CLOSE_PARENTHESES, 0); }
		public TerminalNode CALL() { return getToken(WACCParser.CALL, 0); }
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public TerminalNode OPEN_PARENTHESES() { return getToken(WACCParser.OPEN_PARENTHESES, 0); }
		public FuncCall_assignRHSContext(AssignRHSContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitFuncCall_assignRHS(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Expr_assignRHSContext extends AssignRHSContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Expr_assignRHSContext(AssignRHSContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitExpr_assignRHS(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignRHSContext assignRHS() throws RecognitionException {
		AssignRHSContext _localctx = new AssignRHSContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_assignRHS);
		int _la;
		try {
			setState(171);
			switch (_input.LA(1)) {
			case PLUS:
			case MINUS:
			case NOT:
			case LEN:
			case ORD:
			case CHR:
			case TRUE:
			case FALSE:
			case OPEN_PARENTHESES:
			case INTEGER:
			case NULL:
			case STRING_LITER:
			case IDENTITY:
			case CHAR_LITER:
				_localctx = new Expr_assignRHSContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(153); expr(0);
				}
				break;
			case OPEN_SQUARE:
				_localctx = new ArrayLiter_assignRHSContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(154); arrayLiter();
				}
				break;
			case NEWPAIR:
				_localctx = new NewPair_assignRHSContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(155); match(NEWPAIR);
				setState(156); match(OPEN_PARENTHESES);
				setState(157); expr(0);
				setState(158); match(COMMA);
				setState(159); expr(0);
				setState(160); match(CLOSE_PARENTHESES);
				}
				break;
			case FST:
			case SND:
				_localctx = new PairElem_assignRHSContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(162); pairElem();
				}
				break;
			case CALL:
				_localctx = new FuncCall_assignRHSContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(163); match(CALL);
				setState(164); ident();
				setState(165); match(OPEN_PARENTHESES);
				setState(167);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PLUS) | (1L << MINUS) | (1L << NOT) | (1L << LEN) | (1L << ORD) | (1L << CHR) | (1L << TRUE) | (1L << FALSE) | (1L << OPEN_PARENTHESES) | (1L << INTEGER) | (1L << NULL))) != 0) || ((((_la - 65)) & ~0x3f) == 0 && ((1L << (_la - 65)) & ((1L << (STRING_LITER - 65)) | (1L << (IDENTITY - 65)) | (1L << (CHAR_LITER - 65)))) != 0)) {
					{
					setState(166); argList();
					}
				}

				setState(169); match(CLOSE_PARENTHESES);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArgListContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(WACCParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(WACCParser.COMMA, i);
		}
		public ArgListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argList; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitArgList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgListContext argList() throws RecognitionException {
		ArgListContext _localctx = new ArgListContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_argList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(173); expr(0);
			setState(178);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(174); match(COMMA);
				{
				setState(175); expr(0);
				}
				}
				}
				setState(180);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PairTypeContext extends ParserRuleContext {
		public TerminalNode CLOSE_PARENTHESES() { return getToken(WACCParser.CLOSE_PARENTHESES, 0); }
		public PairElemTypeContext pairElemType(int i) {
			return getRuleContext(PairElemTypeContext.class,i);
		}
		public List<PairElemTypeContext> pairElemType() {
			return getRuleContexts(PairElemTypeContext.class);
		}
		public TerminalNode PAIR() { return getToken(WACCParser.PAIR, 0); }
		public TerminalNode COMMA() { return getToken(WACCParser.COMMA, 0); }
		public TerminalNode OPEN_PARENTHESES() { return getToken(WACCParser.OPEN_PARENTHESES, 0); }
		public PairTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pairType; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitPairType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PairTypeContext pairType() throws RecognitionException {
		PairTypeContext _localctx = new PairTypeContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_pairType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(181); match(PAIR);
			setState(182); match(OPEN_PARENTHESES);
			setState(183); pairElemType();
			setState(184); match(COMMA);
			setState(185); pairElemType();
			setState(186); match(CLOSE_PARENTHESES);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PairElemContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode SND() { return getToken(WACCParser.SND, 0); }
		public TerminalNode FST() { return getToken(WACCParser.FST, 0); }
		public PairElemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pairElem; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitPairElem(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PairElemContext pairElem() throws RecognitionException {
		PairElemContext _localctx = new PairElemContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_pairElem);
		try {
			setState(192);
			switch (_input.LA(1)) {
			case FST:
				enterOuterAlt(_localctx, 1);
				{
				setState(188); match(FST);
				setState(189); expr(0);
				}
				break;
			case SND:
				enterOuterAlt(_localctx, 2);
				{
				setState(190); match(SND);
				setState(191); expr(0);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PairElemTypeContext extends ParserRuleContext {
		public ArrayTypeContext arrayType() {
			return getRuleContext(ArrayTypeContext.class,0);
		}
		public TerminalNode PAIR() { return getToken(WACCParser.PAIR, 0); }
		public BaseTypeContext baseType() {
			return getRuleContext(BaseTypeContext.class,0);
		}
		public PairElemTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pairElemType; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitPairElemType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PairElemTypeContext pairElemType() throws RecognitionException {
		PairElemTypeContext _localctx = new PairElemTypeContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_pairElemType);
		try {
			setState(197);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(194); baseType();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(195); arrayType();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(196); match(PAIR);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArrayTypeContext extends ParserRuleContext {
		public List<TerminalNode> CLOSE_SQUARE() { return getTokens(WACCParser.CLOSE_SQUARE); }
		public List<TerminalNode> OPEN_SQUARE() { return getTokens(WACCParser.OPEN_SQUARE); }
		public TerminalNode OPEN_SQUARE(int i) {
			return getToken(WACCParser.OPEN_SQUARE, i);
		}
		public TerminalNode CLOSE_SQUARE(int i) {
			return getToken(WACCParser.CLOSE_SQUARE, i);
		}
		public BaseTypeContext baseType() {
			return getRuleContext(BaseTypeContext.class,0);
		}
		public PairTypeContext pairType() {
			return getRuleContext(PairTypeContext.class,0);
		}
		public ArrayTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayType; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitArrayType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayTypeContext arrayType() throws RecognitionException {
		ArrayTypeContext _localctx = new ArrayTypeContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_arrayType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(201);
			switch (_input.LA(1)) {
			case INT:
			case BOOL:
			case CHAR:
			case STRING:
				{
				setState(199); baseType();
				}
				break;
			case PAIR:
				{
				setState(200); pairType();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(205); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(203); match(OPEN_SQUARE);
				setState(204); match(CLOSE_SQUARE);
				}
				}
				setState(207); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==OPEN_SQUARE );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeContext extends ParserRuleContext {
		public ArrayTypeContext arrayType() {
			return getRuleContext(ArrayTypeContext.class,0);
		}
		public BaseTypeContext baseType() {
			return getRuleContext(BaseTypeContext.class,0);
		}
		public PairTypeContext pairType() {
			return getRuleContext(PairTypeContext.class,0);
		}
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_type);
		try {
			setState(212);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(209); baseType();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(210); arrayType();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(211); pairType();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BaseTypeContext extends ParserRuleContext {
		public TerminalNode BOOL() { return getToken(WACCParser.BOOL, 0); }
		public TerminalNode STRING() { return getToken(WACCParser.STRING, 0); }
		public TerminalNode CHAR() { return getToken(WACCParser.CHAR, 0); }
		public TerminalNode INT() { return getToken(WACCParser.INT, 0); }
		public BaseTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_baseType; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitBaseType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BaseTypeContext baseType() throws RecognitionException {
		BaseTypeContext _localctx = new BaseTypeContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_baseType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(214);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INT) | (1L << BOOL) | (1L << CHAR) | (1L << STRING))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprContext extends ParserRuleContext {
		public TerminalNode LESS() { return getToken(WACCParser.LESS, 0); }
		public TerminalNode PLUSEQUAL() { return getToken(WACCParser.PLUSEQUAL, 0); }
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode NOT_EQUAL() { return getToken(WACCParser.NOT_EQUAL, 0); }
		public TerminalNode GREATER_EQUAL() { return getToken(WACCParser.GREATER_EQUAL, 0); }
		public TerminalNode MINUSMINUS() { return getToken(WACCParser.MINUSMINUS, 0); }
		public IntLiterContext intLiter() {
			return getRuleContext(IntLiterContext.class,0);
		}
		public StringLiterContext stringLiter() {
			return getRuleContext(StringLiterContext.class,0);
		}
		public TerminalNode DOUBLE_EQUALS() { return getToken(WACCParser.DOUBLE_EQUALS, 0); }
		public TerminalNode OPEN_PARENTHESES() { return getToken(WACCParser.OPEN_PARENTHESES, 0); }
		public TerminalNode MUL() { return getToken(WACCParser.MUL, 0); }
		public TerminalNode NOT() { return getToken(WACCParser.NOT, 0); }
		public CharLiterContext charLiter() {
			return getRuleContext(CharLiterContext.class,0);
		}
		public TerminalNode CLOSE_PARENTHESES() { return getToken(WACCParser.CLOSE_PARENTHESES, 0); }
		public TerminalNode LESS_EQUAL() { return getToken(WACCParser.LESS_EQUAL, 0); }
		public ArrayElemContext arrayElem() {
			return getRuleContext(ArrayElemContext.class,0);
		}
		public TerminalNode PLUSPLUS() { return getToken(WACCParser.PLUSPLUS, 0); }
		public PairLiterContext pairLiter() {
			return getRuleContext(PairLiterContext.class,0);
		}
		public BoolLiterContext boolLiter() {
			return getRuleContext(BoolLiterContext.class,0);
		}
		public TerminalNode AND() { return getToken(WACCParser.AND, 0); }
		public TerminalNode DIV() { return getToken(WACCParser.DIV, 0); }
		public TerminalNode ORD() { return getToken(WACCParser.ORD, 0); }
		public TerminalNode MINUSEQUAL() { return getToken(WACCParser.MINUSEQUAL, 0); }
		public TerminalNode LEN() { return getToken(WACCParser.LEN, 0); }
		public TerminalNode MOD() { return getToken(WACCParser.MOD, 0); }
		public TerminalNode OR() { return getToken(WACCParser.OR, 0); }
		public TerminalNode GREATER() { return getToken(WACCParser.GREATER, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public TerminalNode MINUS() { return getToken(WACCParser.MINUS, 0); }
		public TerminalNode PLUS() { return getToken(WACCParser.PLUS, 0); }
		public TerminalNode CHR() { return getToken(WACCParser.CHR, 0); }
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 28;
		enterRecursionRule(_localctx, 28, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(238);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				{
				setState(217);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MINUS) | (1L << NOT) | (1L << LEN) | (1L << ORD) | (1L << CHR))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				setState(218); expr(13);
				}
				break;
			case 2:
				{
				setState(219); ident();
				setState(220); match(PLUSPLUS);
				}
				break;
			case 3:
				{
				setState(222); ident();
				setState(223); match(MINUSMINUS);
				}
				break;
			case 4:
				{
				setState(225); match(OPEN_PARENTHESES);
				setState(226); expr(0);
				setState(227); match(CLOSE_PARENTHESES);
				}
				break;
			case 5:
				{
				setState(236);
				switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
				case 1:
					{
					setState(229); intLiter();
					}
					break;
				case 2:
					{
					setState(230); boolLiter();
					}
					break;
				case 3:
					{
					setState(231); charLiter();
					}
					break;
				case 4:
					{
					setState(232); stringLiter();
					}
					break;
				case 5:
					{
					setState(233); pairLiter();
					}
					break;
				case 6:
					{
					setState(234); ident();
					}
					break;
				case 7:
					{
					setState(235); arrayElem();
					}
					break;
				}
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(266);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(264);
					switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
					case 1:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(240);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(241);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MUL) | (1L << DIV) | (1L << MOD))) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						consume();
						setState(242); expr(11);
						}
						break;
					case 2:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(243);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(244);
						_la = _input.LA(1);
						if ( !(_la==PLUS || _la==MINUS) ) {
						_errHandler.recoverInline(this);
						}
						consume();
						setState(245); expr(10);
						}
						break;
					case 3:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(246);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(247);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << GREATER_EQUAL) | (1L << GREATER) | (1L << LESS_EQUAL) | (1L << LESS))) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						consume();
						setState(248); expr(9);
						}
						break;
					case 4:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(249);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(250);
						_la = _input.LA(1);
						if ( !(_la==DOUBLE_EQUALS || _la==NOT_EQUAL) ) {
						_errHandler.recoverInline(this);
						}
						consume();
						setState(251); expr(8);
						}
						break;
					case 5:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(252);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(253); match(AND);
						setState(254); expr(7);
						}
						break;
					case 6:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(255);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(256); match(OR);
						setState(257); expr(6);
						}
						break;
					case 7:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(258);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(259); match(PLUSEQUAL);
						setState(260); expr(5);
						}
						break;
					case 8:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(261);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(262); match(MINUSEQUAL);
						setState(263); expr(4);
						}
						break;
					}
					} 
				}
				setState(268);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class ArrayElemContext extends ParserRuleContext {
		public List<TerminalNode> CLOSE_SQUARE() { return getTokens(WACCParser.CLOSE_SQUARE); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public List<TerminalNode> OPEN_SQUARE() { return getTokens(WACCParser.OPEN_SQUARE); }
		public TerminalNode OPEN_SQUARE(int i) {
			return getToken(WACCParser.OPEN_SQUARE, i);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode CLOSE_SQUARE(int i) {
			return getToken(WACCParser.CLOSE_SQUARE, i);
		}
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public ArrayElemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayElem; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitArrayElem(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayElemContext arrayElem() throws RecognitionException {
		ArrayElemContext _localctx = new ArrayElemContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_arrayElem);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(269); ident();
			setState(274); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(270); match(OPEN_SQUARE);
					setState(271); expr(0);
					setState(272); match(CLOSE_SQUARE);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(276); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IntLiterContext extends ParserRuleContext {
		public TerminalNode INTEGER() { return getToken(WACCParser.INTEGER, 0); }
		public TerminalNode PLUS() { return getToken(WACCParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(WACCParser.MINUS, 0); }
		public IntLiterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_intLiter; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitIntLiter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IntLiterContext intLiter() throws RecognitionException {
		IntLiterContext _localctx = new IntLiterContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_intLiter);
		int _la;
		try {
			setState(281);
			switch (_input.LA(1)) {
			case PLUS:
			case MINUS:
				enterOuterAlt(_localctx, 1);
				{
				setState(278);
				_la = _input.LA(1);
				if ( !(_la==PLUS || _la==MINUS) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				setState(279); match(INTEGER);
				}
				break;
			case INTEGER:
				enterOuterAlt(_localctx, 2);
				{
				setState(280); match(INTEGER);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BoolLiterContext extends ParserRuleContext {
		public TerminalNode FALSE() { return getToken(WACCParser.FALSE, 0); }
		public TerminalNode TRUE() { return getToken(WACCParser.TRUE, 0); }
		public BoolLiterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_boolLiter; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitBoolLiter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BoolLiterContext boolLiter() throws RecognitionException {
		BoolLiterContext _localctx = new BoolLiterContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_boolLiter);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(283);
			_la = _input.LA(1);
			if ( !(_la==TRUE || _la==FALSE) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CharLiterContext extends ParserRuleContext {
		public TerminalNode CHAR_LITER() { return getToken(WACCParser.CHAR_LITER, 0); }
		public CharLiterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_charLiter; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitCharLiter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CharLiterContext charLiter() throws RecognitionException {
		CharLiterContext _localctx = new CharLiterContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_charLiter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(285); match(CHAR_LITER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StringLiterContext extends ParserRuleContext {
		public TerminalNode STRING_LITER() { return getToken(WACCParser.STRING_LITER, 0); }
		public StringLiterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stringLiter; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitStringLiter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StringLiterContext stringLiter() throws RecognitionException {
		StringLiterContext _localctx = new StringLiterContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_stringLiter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(287); match(STRING_LITER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArrayLiterContext extends ParserRuleContext {
		public TerminalNode CLOSE_SQUARE() { return getToken(WACCParser.CLOSE_SQUARE, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public TerminalNode OPEN_SQUARE() { return getToken(WACCParser.OPEN_SQUARE, 0); }
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(WACCParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(WACCParser.COMMA, i);
		}
		public ArrayLiterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayLiter; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitArrayLiter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayLiterContext arrayLiter() throws RecognitionException {
		ArrayLiterContext _localctx = new ArrayLiterContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_arrayLiter);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(289); match(OPEN_SQUARE);
			setState(298);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PLUS) | (1L << MINUS) | (1L << NOT) | (1L << LEN) | (1L << ORD) | (1L << CHR) | (1L << TRUE) | (1L << FALSE) | (1L << OPEN_PARENTHESES) | (1L << INTEGER) | (1L << NULL))) != 0) || ((((_la - 65)) & ~0x3f) == 0 && ((1L << (_la - 65)) & ((1L << (STRING_LITER - 65)) | (1L << (IDENTITY - 65)) | (1L << (CHAR_LITER - 65)))) != 0)) {
				{
				setState(290); expr(0);
				setState(295);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(291); match(COMMA);
					{
					setState(292); expr(0);
					}
					}
					}
					setState(297);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(300); match(CLOSE_SQUARE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PairLiterContext extends ParserRuleContext {
		public TerminalNode NULL() { return getToken(WACCParser.NULL, 0); }
		public PairLiterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pairLiter; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitPairLiter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PairLiterContext pairLiter() throws RecognitionException {
		PairLiterContext _localctx = new PairLiterContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_pairLiter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(302); match(NULL);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IdentContext extends ParserRuleContext {
		public TerminalNode IDENTITY() { return getToken(WACCParser.IDENTITY, 0); }
		public IdentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ident; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitIdent(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdentContext ident() throws RecognitionException {
		IdentContext _localctx = new IdentContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_ident);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(304); match(IDENTITY);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 4: return stat_sempred((StatContext)_localctx, predIndex);
		case 14: return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean stat_sempred(StatContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0: return precpred(_ctx, 3);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1: return precpred(_ctx, 10);
		case 2: return precpred(_ctx, 9);
		case 3: return precpred(_ctx, 8);
		case 4: return precpred(_ctx, 7);
		case 5: return precpred(_ctx, 6);
		case 6: return precpred(_ctx, 5);
		case 7: return precpred(_ctx, 4);
		case 8: return precpred(_ctx, 3);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3O\u0135\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\3\2\3\2\7"+
		"\2\63\n\2\f\2\16\2\66\13\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\5\3@\n\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\7\4J\n\4\f\4\16\4M\13\4\3\5\3\5\3\5\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\5\6\u008d\n\6\3\6\3\6\3\6\7\6\u0092\n\6\f\6\16"+
		"\6\u0095\13\6\3\7\3\7\3\7\5\7\u009a\n\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\3\b\3\b\5\b\u00aa\n\b\3\b\3\b\5\b\u00ae\n\b\3\t\3\t"+
		"\3\t\7\t\u00b3\n\t\f\t\16\t\u00b6\13\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13"+
		"\3\13\3\13\3\13\5\13\u00c3\n\13\3\f\3\f\3\f\5\f\u00c8\n\f\3\r\3\r\5\r"+
		"\u00cc\n\r\3\r\3\r\6\r\u00d0\n\r\r\r\16\r\u00d1\3\16\3\16\3\16\5\16\u00d7"+
		"\n\16\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\5\20\u00ef\n\20\5\20\u00f1"+
		"\n\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\7\20\u010b\n\20"+
		"\f\20\16\20\u010e\13\20\3\21\3\21\3\21\3\21\3\21\6\21\u0115\n\21\r\21"+
		"\16\21\u0116\3\22\3\22\3\22\5\22\u011c\n\22\3\23\3\23\3\24\3\24\3\25\3"+
		"\25\3\26\3\26\3\26\3\26\7\26\u0128\n\26\f\26\16\26\u012b\13\26\5\26\u012d"+
		"\n\26\3\26\3\26\3\27\3\27\3\30\3\30\3\30\2\4\n\36\31\2\4\6\b\n\f\16\20"+
		"\22\24\26\30\32\34\36 \"$&(*,.\2\t\3\2\33\36\4\2\4\4\23\26\3\2\5\7\3\2"+
		"\3\4\3\2\13\16\3\2\17\20\3\2\31\32\u0155\2\60\3\2\2\2\4;\3\2\2\2\6F\3"+
		"\2\2\2\bN\3\2\2\2\n\u008c\3\2\2\2\f\u0099\3\2\2\2\16\u00ad\3\2\2\2\20"+
		"\u00af\3\2\2\2\22\u00b7\3\2\2\2\24\u00c2\3\2\2\2\26\u00c7\3\2\2\2\30\u00cb"+
		"\3\2\2\2\32\u00d6\3\2\2\2\34\u00d8\3\2\2\2\36\u00f0\3\2\2\2 \u010f\3\2"+
		"\2\2\"\u011b\3\2\2\2$\u011d\3\2\2\2&\u011f\3\2\2\2(\u0121\3\2\2\2*\u0123"+
		"\3\2\2\2,\u0130\3\2\2\2.\u0132\3\2\2\2\60\64\7%\2\2\61\63\5\4\3\2\62\61"+
		"\3\2\2\2\63\66\3\2\2\2\64\62\3\2\2\2\64\65\3\2\2\2\65\67\3\2\2\2\66\64"+
		"\3\2\2\2\678\5\n\6\289\7&\2\29:\7\2\2\3:\3\3\2\2\2;<\5\32\16\2<=\5.\30"+
		"\2=?\7 \2\2>@\5\6\4\2?>\3\2\2\2?@\3\2\2\2@A\3\2\2\2AB\7!\2\2BC\7\'\2\2"+
		"CD\5\n\6\2DE\7&\2\2E\5\3\2\2\2FK\5\b\5\2GH\7\63\2\2HJ\5\b\5\2IG\3\2\2"+
		"\2JM\3\2\2\2KI\3\2\2\2KL\3\2\2\2L\7\3\2\2\2MK\3\2\2\2NO\5\32\16\2OP\5"+
		".\30\2P\t\3\2\2\2QR\b\6\1\2R\u008d\7(\2\2ST\5\32\16\2TU\5.\30\2UV\7\b"+
		"\2\2VW\5\16\b\2W\u008d\3\2\2\2XY\5\f\7\2YZ\7\b\2\2Z[\5\16\b\2[\u008d\3"+
		"\2\2\2\\]\7)\2\2]\u008d\5\f\7\2^_\7*\2\2_\u008d\5\36\20\2`a\7+\2\2a\u008d"+
		"\5\36\20\2bc\7,\2\2c\u008d\5\36\20\2de\7.\2\2e\u008d\5\36\20\2fg\7-\2"+
		"\2g\u008d\5\36\20\2hi\7\65\2\2ij\5\36\20\2jk\7\66\2\2kl\5\n\6\2lm\7\67"+
		"\2\2mn\5\n\6\2no\78\2\2o\u008d\3\2\2\2pq\79\2\2qr\5\36\20\2rs\7;\2\2s"+
		"t\5\n\6\2tu\7:\2\2u\u008d\3\2\2\2vw\7;\2\2wx\5\n\6\2xy\79\2\2yz\5\36\20"+
		"\2z{\7:\2\2{\u008d\3\2\2\2|}\7<\2\2}~\5\n\6\2~\177\7\61\2\2\177\u0080"+
		"\5\36\20\2\u0080\u0081\7\61\2\2\u0081\u0082\5\36\20\2\u0082\u0083\7;\2"+
		"\2\u0083\u0084\5\n\6\2\u0084\u0085\7:\2\2\u0085\u008d\3\2\2\2\u0086\u0087"+
		"\7%\2\2\u0087\u0088\5\n\6\2\u0088\u0089\7&\2\2\u0089\u008d\3\2\2\2\u008a"+
		"\u008d\7=\2\2\u008b\u008d\7>\2\2\u008cQ\3\2\2\2\u008cS\3\2\2\2\u008cX"+
		"\3\2\2\2\u008c\\\3\2\2\2\u008c^\3\2\2\2\u008c`\3\2\2\2\u008cb\3\2\2\2"+
		"\u008cd\3\2\2\2\u008cf\3\2\2\2\u008ch\3\2\2\2\u008cp\3\2\2\2\u008cv\3"+
		"\2\2\2\u008c|\3\2\2\2\u008c\u0086\3\2\2\2\u008c\u008a\3\2\2\2\u008c\u008b"+
		"\3\2\2\2\u008d\u0093\3\2\2\2\u008e\u008f\f\5\2\2\u008f\u0090\7\61\2\2"+
		"\u0090\u0092\5\n\6\6\u0091\u008e\3\2\2\2\u0092\u0095\3\2\2\2\u0093\u0091"+
		"\3\2\2\2\u0093\u0094\3\2\2\2\u0094\13\3\2\2\2\u0095\u0093\3\2\2\2\u0096"+
		"\u009a\5.\30\2\u0097\u009a\5 \21\2\u0098\u009a\5\24\13\2\u0099\u0096\3"+
		"\2\2\2\u0099\u0097\3\2\2\2\u0099\u0098\3\2\2\2\u009a\r\3\2\2\2\u009b\u00ae"+
		"\5\36\20\2\u009c\u00ae\5*\26\2\u009d\u009e\7?\2\2\u009e\u009f\7 \2\2\u009f"+
		"\u00a0\5\36\20\2\u00a0\u00a1\7\63\2\2\u00a1\u00a2\5\36\20\2\u00a2\u00a3"+
		"\7!\2\2\u00a3\u00ae\3\2\2\2\u00a4\u00ae\5\24\13\2\u00a5\u00a6\7B\2\2\u00a6"+
		"\u00a7\5.\30\2\u00a7\u00a9\7 \2\2\u00a8\u00aa\5\20\t\2\u00a9\u00a8\3\2"+
		"\2\2\u00a9\u00aa\3\2\2\2\u00aa\u00ab\3\2\2\2\u00ab\u00ac\7!\2\2\u00ac"+
		"\u00ae\3\2\2\2\u00ad\u009b\3\2\2\2\u00ad\u009c\3\2\2\2\u00ad\u009d\3\2"+
		"\2\2\u00ad\u00a4\3\2\2\2\u00ad\u00a5\3\2\2\2\u00ae\17\3\2\2\2\u00af\u00b4"+
		"\5\36\20\2\u00b0\u00b1\7\63\2\2\u00b1\u00b3\5\36\20\2\u00b2\u00b0\3\2"+
		"\2\2\u00b3\u00b6\3\2\2\2\u00b4\u00b2\3\2\2\2\u00b4\u00b5\3\2\2\2\u00b5"+
		"\21\3\2\2\2\u00b6\u00b4\3\2\2\2\u00b7\u00b8\7\37\2\2\u00b8\u00b9\7 \2"+
		"\2\u00b9\u00ba\5\26\f\2\u00ba\u00bb\7\63\2\2\u00bb\u00bc\5\26\f\2\u00bc"+
		"\u00bd\7!\2\2\u00bd\23\3\2\2\2\u00be\u00bf\7@\2\2\u00bf\u00c3\5\36\20"+
		"\2\u00c0\u00c1\7A\2\2\u00c1\u00c3\5\36\20\2\u00c2\u00be\3\2\2\2\u00c2"+
		"\u00c0\3\2\2\2\u00c3\25\3\2\2\2\u00c4\u00c8\5\34\17\2\u00c5\u00c8\5\30"+
		"\r\2\u00c6\u00c8\7\37\2\2\u00c7\u00c4\3\2\2\2\u00c7\u00c5\3\2\2\2\u00c7"+
		"\u00c6\3\2\2\2\u00c8\27\3\2\2\2\u00c9\u00cc\5\34\17\2\u00ca\u00cc\5\22"+
		"\n\2\u00cb\u00c9\3\2\2\2\u00cb\u00ca\3\2\2\2\u00cc\u00cf\3\2\2\2\u00cd"+
		"\u00ce\7\"\2\2\u00ce\u00d0\7#\2\2\u00cf\u00cd\3\2\2\2\u00d0\u00d1\3\2"+
		"\2\2\u00d1\u00cf\3\2\2\2\u00d1\u00d2\3\2\2\2\u00d2\31\3\2\2\2\u00d3\u00d7"+
		"\5\34\17\2\u00d4\u00d7\5\30\r\2\u00d5\u00d7\5\22\n\2\u00d6\u00d3\3\2\2"+
		"\2\u00d6\u00d4\3\2\2\2\u00d6\u00d5\3\2\2\2\u00d7\33\3\2\2\2\u00d8\u00d9"+
		"\t\2\2\2\u00d9\35\3\2\2\2\u00da\u00db\b\20\1\2\u00db\u00dc\t\3\2\2\u00dc"+
		"\u00f1\5\36\20\17\u00dd\u00de\5.\30\2\u00de\u00df\7\27\2\2\u00df\u00f1"+
		"\3\2\2\2\u00e0\u00e1\5.\30\2\u00e1\u00e2\7\30\2\2\u00e2\u00f1\3\2\2\2"+
		"\u00e3\u00e4\7 \2\2\u00e4\u00e5\5\36\20\2\u00e5\u00e6\7!\2\2\u00e6\u00f1"+
		"\3\2\2\2\u00e7\u00ef\5\"\22\2\u00e8\u00ef\5$\23\2\u00e9\u00ef\5&\24\2"+
		"\u00ea\u00ef\5(\25\2\u00eb\u00ef\5,\27\2\u00ec\u00ef\5.\30\2\u00ed\u00ef"+
		"\5 \21\2\u00ee\u00e7\3\2\2\2\u00ee\u00e8\3\2\2\2\u00ee\u00e9\3\2\2\2\u00ee"+
		"\u00ea\3\2\2\2\u00ee\u00eb\3\2\2\2\u00ee\u00ec\3\2\2\2\u00ee\u00ed\3\2"+
		"\2\2\u00ef\u00f1\3\2\2\2\u00f0\u00da\3\2\2\2\u00f0\u00dd\3\2\2\2\u00f0"+
		"\u00e0\3\2\2\2\u00f0\u00e3\3\2\2\2\u00f0\u00ee\3\2\2\2\u00f1\u010c\3\2"+
		"\2\2\u00f2\u00f3\f\f\2\2\u00f3\u00f4\t\4\2\2\u00f4\u010b\5\36\20\r\u00f5"+
		"\u00f6\f\13\2\2\u00f6\u00f7\t\5\2\2\u00f7\u010b\5\36\20\f\u00f8\u00f9"+
		"\f\n\2\2\u00f9\u00fa\t\6\2\2\u00fa\u010b\5\36\20\13\u00fb\u00fc\f\t\2"+
		"\2\u00fc\u00fd\t\7\2\2\u00fd\u010b\5\36\20\n\u00fe\u00ff\f\b\2\2\u00ff"+
		"\u0100\7\21\2\2\u0100\u010b\5\36\20\t\u0101\u0102\f\7\2\2\u0102\u0103"+
		"\7\22\2\2\u0103\u010b\5\36\20\b\u0104\u0105\f\6\2\2\u0105\u0106\7\t\2"+
		"\2\u0106\u010b\5\36\20\7\u0107\u0108\f\5\2\2\u0108\u0109\7\n\2\2\u0109"+
		"\u010b\5\36\20\6\u010a\u00f2\3\2\2\2\u010a\u00f5\3\2\2\2\u010a\u00f8\3"+
		"\2\2\2\u010a\u00fb\3\2\2\2\u010a\u00fe\3\2\2\2\u010a\u0101\3\2\2\2\u010a"+
		"\u0104\3\2\2\2\u010a\u0107\3\2\2\2\u010b\u010e\3\2\2\2\u010c\u010a\3\2"+
		"\2\2\u010c\u010d\3\2\2\2\u010d\37\3\2\2\2\u010e\u010c\3\2\2\2\u010f\u0114"+
		"\5.\30\2\u0110\u0111\7\"\2\2\u0111\u0112\5\36\20\2\u0112\u0113\7#\2\2"+
		"\u0113\u0115\3\2\2\2\u0114\u0110\3\2\2\2\u0115\u0116\3\2\2\2\u0116\u0114"+
		"\3\2\2\2\u0116\u0117\3\2\2\2\u0117!\3\2\2\2\u0118\u0119\t\5\2\2\u0119"+
		"\u011c\7$\2\2\u011a\u011c\7$\2\2\u011b\u0118\3\2\2\2\u011b\u011a\3\2\2"+
		"\2\u011c#\3\2\2\2\u011d\u011e\t\b\2\2\u011e%\3\2\2\2\u011f\u0120\7O\2"+
		"\2\u0120\'\3\2\2\2\u0121\u0122\7C\2\2\u0122)\3\2\2\2\u0123\u012c\7\"\2"+
		"\2\u0124\u0129\5\36\20\2\u0125\u0126\7\63\2\2\u0126\u0128\5\36\20\2\u0127"+
		"\u0125\3\2\2\2\u0128\u012b\3\2\2\2\u0129\u0127\3\2\2\2\u0129\u012a\3\2"+
		"\2\2\u012a\u012d\3\2\2\2\u012b\u0129\3\2\2\2\u012c\u0124\3\2\2\2\u012c"+
		"\u012d\3\2\2\2\u012d\u012e\3\2\2\2\u012e\u012f\7#\2\2\u012f+\3\2\2\2\u0130"+
		"\u0131\7/\2\2\u0131-\3\2\2\2\u0132\u0133\7N\2\2\u0133/\3\2\2\2\30\64?"+
		"K\u008c\u0093\u0099\u00a9\u00ad\u00b4\u00c2\u00c7\u00cb\u00d1\u00d6\u00ee"+
		"\u00f0\u010a\u010c\u0116\u011b\u0129\u012c";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}