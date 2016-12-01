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
		PRINT=43, HASH_KEY=49, APOSTROPHE=74, NEWPAIR=60, DO=56, FORM_FEED=70, 
		CHR=18, MINUS=2, END_OF_STRING=66, GREATER_EQUAL=7, BACKSLASH=72, WHITESPACE=73, 
		BREAK=58, ELSE=52, LESS_EQUAL=9, IF=50, INTEGER=30, HEX_LITER=33, DONE=55, 
		NULL=44, MUL=3, DOUBLE_EQUALS=11, FST=61, NEWLINE=67, STRING_LITER=64, 
		FOR=57, TRUE=19, IS=36, READ=38, IDENTITY=75, NOT=15, AND=13, LESS=10, 
		END=35, SEMI_COLON=46, THEN=51, EXIT=41, PLUS=1, CLOSE_PARENTHESES=27, 
		ORD=17, CALL=63, PRINTLN=42, OPEN_PARENTHESES=26, SND=62, CHAR=23, BEGIN=34, 
		FREE=39, INT=21, COMMENT=45, CARRIAGE_RETURN=69, RETURN=40, SKIP=37, WS=65, 
		DOUBLE_QUOTES=71, COMMA=48, BIN_LITER=31, MOD=5, OR=14, OCT_LITER=32, 
		EQUAL=6, GREATER=8, ENDIF=53, COLON=47, CHAR_LITER=76, DIV=4, CONTINUE=59, 
		OPEN_SQUARE=28, LEN=16, TAB=68, BOOL=22, NOT_EQUAL=12, STRING=24, CLOSE_SQUARE=29, 
		FALSE=20, WHILE=54, PAIR=25;
	public static final String[] tokenNames = {
		"<INVALID>", "'+'", "'-'", "'*'", "'/'", "'%'", "'='", "'>='", "'>'", 
		"'<='", "'<'", "'=='", "'!='", "'&&'", "'||'", "'!'", "'len'", "'ord'", 
		"'chr'", "'true'", "'false'", "'int'", "'bool'", "'char'", "'string'", 
		"'pair'", "'('", "')'", "'['", "']'", "INTEGER", "BIN_LITER", "OCT_LITER", 
		"HEX_LITER", "'begin'", "'end'", "'is'", "'skip'", "'read'", "'free'", 
		"'return'", "'exit'", "'println'", "'print'", "'null'", "COMMENT", "';'", 
		"':'", "','", "'#'", "'if'", "'then'", "'else'", "'fi'", "'while'", "'done'", 
		"'do'", "'for'", "'break'", "'continues'", "'newpair'", "'fst'", "'snd'", 
		"'call'", "STRING_LITER", "WS", "'\\0'", "'\\n'", "'\\t'", "'\\r'", "'\\f'", 
		"'\\\"'", "'\\'", "' '", "'''", "IDENTITY", "CHAR_LITER"
	};
	public static final int
		RULE_program = 0, RULE_func = 1, RULE_paramList = 2, RULE_param = 3, RULE_stat = 4, 
		RULE_assignLHS = 5, RULE_assignRHS = 6, RULE_argList = 7, RULE_pairType = 8, 
		RULE_pairElem = 9, RULE_pairElemType = 10, RULE_arrayType = 11, RULE_type = 12, 
		RULE_baseType = 13, RULE_expr = 14, RULE_arrayElem = 15, RULE_intLiter = 16, 
		RULE_boolLiter = 17, RULE_charLiter = 18, RULE_stringLiter = 19, RULE_binLiter = 20, 
		RULE_hexLiter = 21, RULE_octLiter = 22, RULE_arrayLiter = 23, RULE_pairLiter = 24, 
		RULE_ident = 25;
	public static final String[] ruleNames = {
		"program", "func", "paramList", "param", "stat", "assignLHS", "assignRHS", 
		"argList", "pairType", "pairElem", "pairElemType", "arrayType", "type", 
		"baseType", "expr", "arrayElem", "intLiter", "boolLiter", "charLiter", 
		"stringLiter", "binLiter", "hexLiter", "octLiter", "arrayLiter", "pairLiter", 
		"ident"
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
			setState(52); match(BEGIN);
			setState(56);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(53); func();
					}
					} 
				}
				setState(58);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			}
			setState(59); stat(0);
			setState(60); match(END);
			setState(61); match(EOF);
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
			setState(63); type();
			setState(64); ident();
			setState(65); match(OPEN_PARENTHESES);
			setState(67);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INT) | (1L << BOOL) | (1L << CHAR) | (1L << STRING) | (1L << PAIR))) != 0)) {
				{
				setState(66); paramList();
				}
			}

			setState(69); match(CLOSE_PARENTHESES);
			setState(70); match(IS);
			setState(71); stat(0);
			setState(72); match(END);
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
			setState(74); param();
			setState(79);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(75); match(COMMA);
				setState(76); param();
				}
				}
				setState(81);
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
			setState(82); type();
			setState(83); ident();
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
	public static class ContinuesContext extends StatContext {
		public TerminalNode CONTINUE() { return getToken(WACCParser.CONTINUE, 0); }
		public ContinuesContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitContinues(this);
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
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> SEMI_COLON() { return getTokens(WACCParser.SEMI_COLON); }
		public TerminalNode SEMI_COLON(int i) {
			return getToken(WACCParser.SEMI_COLON, i);
		}
		public TerminalNode FOR() { return getToken(WACCParser.FOR, 0); }
		public StatContext stat() {
			return getRuleContext(StatContext.class,0);
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
			setState(141);
			switch (_input.LA(1)) {
			case SKIP:
				{
				_localctx = new SkipContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(86); match(SKIP);
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
				setState(87); type();
				setState(88); ident();
				setState(89); match(EQUAL);
				setState(90); assignRHS();
				}
				break;
			case FST:
			case SND:
			case IDENTITY:
				{
				_localctx = new AssignContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(92); assignLHS();
				setState(93); match(EQUAL);
				setState(94); assignRHS();
				}
				break;
			case READ:
				{
				_localctx = new ReadContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(96); match(READ);
				setState(97); assignLHS();
				}
				break;
			case FREE:
				{
				_localctx = new FreeContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(98); match(FREE);
				setState(99); expr(0);
				}
				break;
			case RETURN:
				{
				_localctx = new ReturnContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(100); match(RETURN);
				setState(101); expr(0);
				}
				break;
			case EXIT:
				{
				_localctx = new ExitContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(102); match(EXIT);
				setState(103); expr(0);
				}
				break;
			case PRINT:
				{
				_localctx = new PrintContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(104); match(PRINT);
				setState(105); expr(0);
				}
				break;
			case PRINTLN:
				{
				_localctx = new PrintlnContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(106); match(PRINTLN);
				setState(107); expr(0);
				}
				break;
			case IF:
				{
				_localctx = new IfElseContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(108); match(IF);
				setState(109); expr(0);
				setState(110); match(THEN);
				setState(111); stat(0);
				setState(112); match(ELSE);
				setState(113); stat(0);
				setState(114); match(ENDIF);
				}
				break;
			case WHILE:
				{
				_localctx = new WhileContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(116); match(WHILE);
				setState(117); expr(0);
				setState(118); match(DO);
				setState(119); stat(0);
				setState(120); match(DONE);
				}
				break;
			case DO:
				{
				_localctx = new DoWhileContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(122); match(DO);
				setState(123); stat(0);
				setState(124); match(WHILE);
				setState(125); expr(0);
				setState(126); match(DONE);
				}
				break;
			case FOR:
				{
				_localctx = new ForLoopContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(128); match(FOR);
				setState(129); stat(0);
				setState(130); match(SEMI_COLON);
				setState(131); expr(0);
				setState(132); match(SEMI_COLON);
				setState(133); expr(0);
				}
				break;
			case BEGIN:
				{
				_localctx = new BeginContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(135); match(BEGIN);
				setState(136); stat(0);
				setState(137); match(END);
				}
				break;
			case BREAK:
				{
				_localctx = new BreakContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(139); match(BREAK);
				}
				break;
			case CONTINUE:
				{
				_localctx = new ContinuesContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(140); match(CONTINUE);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(148);
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
					setState(143);
					if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
					setState(144); match(SEMI_COLON);
					setState(145); stat(4);
					}
					} 
				}
				setState(150);
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
			setState(154);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(151); ident();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(152); arrayElem();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(153); pairElem();
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
			setState(174);
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
			case BIN_LITER:
			case OCT_LITER:
			case HEX_LITER:
			case NULL:
			case STRING_LITER:
			case IDENTITY:
			case CHAR_LITER:
				_localctx = new Expr_assignRHSContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(156); expr(0);
				}
				break;
			case OPEN_SQUARE:
				_localctx = new ArrayLiter_assignRHSContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(157); arrayLiter();
				}
				break;
			case NEWPAIR:
				_localctx = new NewPair_assignRHSContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(158); match(NEWPAIR);
				setState(159); match(OPEN_PARENTHESES);
				setState(160); expr(0);
				setState(161); match(COMMA);
				setState(162); expr(0);
				setState(163); match(CLOSE_PARENTHESES);
				}
				break;
			case FST:
			case SND:
				_localctx = new PairElem_assignRHSContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(165); pairElem();
				}
				break;
			case CALL:
				_localctx = new FuncCall_assignRHSContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(166); match(CALL);
				setState(167); ident();
				setState(168); match(OPEN_PARENTHESES);
				setState(170);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PLUS) | (1L << MINUS) | (1L << NOT) | (1L << LEN) | (1L << ORD) | (1L << CHR) | (1L << TRUE) | (1L << FALSE) | (1L << OPEN_PARENTHESES) | (1L << INTEGER) | (1L << BIN_LITER) | (1L << OCT_LITER) | (1L << HEX_LITER) | (1L << NULL))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (STRING_LITER - 64)) | (1L << (IDENTITY - 64)) | (1L << (CHAR_LITER - 64)))) != 0)) {
					{
					setState(169); argList();
					}
				}

				setState(172); match(CLOSE_PARENTHESES);
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
			setState(176); expr(0);
			setState(181);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(177); match(COMMA);
				{
				setState(178); expr(0);
				}
				}
				}
				setState(183);
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
			setState(184); match(PAIR);
			setState(185); match(OPEN_PARENTHESES);
			setState(186); pairElemType();
			setState(187); match(COMMA);
			setState(188); pairElemType();
			setState(189); match(CLOSE_PARENTHESES);
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
			setState(195);
			switch (_input.LA(1)) {
			case FST:
				enterOuterAlt(_localctx, 1);
				{
				setState(191); match(FST);
				setState(192); expr(0);
				}
				break;
			case SND:
				enterOuterAlt(_localctx, 2);
				{
				setState(193); match(SND);
				setState(194); expr(0);
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
			setState(200);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(197); baseType();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(198); arrayType();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(199); match(PAIR);
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
			setState(204);
			switch (_input.LA(1)) {
			case INT:
			case BOOL:
			case CHAR:
			case STRING:
				{
				setState(202); baseType();
				}
				break;
			case PAIR:
				{
				setState(203); pairType();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(208); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(206); match(OPEN_SQUARE);
				setState(207); match(CLOSE_SQUARE);
				}
				}
				setState(210); 
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
			setState(215);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(212); baseType();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(213); arrayType();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(214); pairType();
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
			setState(217);
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
		public TerminalNode MINUS(int i) {
			return getToken(WACCParser.MINUS, i);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode NOT_EQUAL() { return getToken(WACCParser.NOT_EQUAL, 0); }
		public TerminalNode GREATER_EQUAL() { return getToken(WACCParser.GREATER_EQUAL, 0); }
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
		public PairLiterContext pairLiter() {
			return getRuleContext(PairLiterContext.class,0);
		}
		public BoolLiterContext boolLiter() {
			return getRuleContext(BoolLiterContext.class,0);
		}
		public List<TerminalNode> AND() { return getTokens(WACCParser.AND); }
		public TerminalNode DIV() { return getToken(WACCParser.DIV, 0); }
		public TerminalNode ORD() { return getToken(WACCParser.ORD, 0); }
		public TerminalNode AND(int i) {
			return getToken(WACCParser.AND, i);
		}
		public TerminalNode LEN() { return getToken(WACCParser.LEN, 0); }
		public TerminalNode MOD() { return getToken(WACCParser.MOD, 0); }
		public TerminalNode OR() { return getToken(WACCParser.OR, 0); }
		public TerminalNode GREATER() { return getToken(WACCParser.GREATER, 0); }
		public BinLiterContext binLiter() {
			return getRuleContext(BinLiterContext.class,0);
		}
		public OctLiterContext octLiter() {
			return getRuleContext(OctLiterContext.class,0);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public HexLiterContext hexLiter() {
			return getRuleContext(HexLiterContext.class,0);
		}
		public List<TerminalNode> MINUS() { return getTokens(WACCParser.MINUS); }
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
			setState(248);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				{
				setState(220);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MINUS) | (1L << NOT) | (1L << LEN) | (1L << ORD) | (1L << CHR))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				setState(221); expr(12);
				}
				break;
			case 2:
				{
				setState(222); ident();
				setState(223); match(AND);
				setState(224); match(AND);
				}
				break;
			case 3:
				{
				setState(226); ident();
				setState(227); match(MINUS);
				setState(228); match(MINUS);
				}
				break;
			case 4:
				{
				setState(230); match(OPEN_PARENTHESES);
				setState(231); expr(0);
				setState(232); match(CLOSE_PARENTHESES);
				}
				break;
			case 5:
				{
				setState(241);
				switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
				case 1:
					{
					setState(234); intLiter();
					}
					break;
				case 2:
					{
					setState(235); boolLiter();
					}
					break;
				case 3:
					{
					setState(236); charLiter();
					}
					break;
				case 4:
					{
					setState(237); stringLiter();
					}
					break;
				case 5:
					{
					setState(238); pairLiter();
					}
					break;
				case 6:
					{
					setState(239); ident();
					}
					break;
				case 7:
					{
					setState(240); arrayElem();
					}
					break;
				}
				}
				break;
			case 6:
				{
				setState(246);
				switch (_input.LA(1)) {
				case BIN_LITER:
					{
					setState(243); binLiter();
					}
					break;
				case HEX_LITER:
					{
					setState(244); hexLiter();
					}
					break;
				case OCT_LITER:
					{
					setState(245); octLiter();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(270);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(268);
					switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
					case 1:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(250);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(251);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MUL) | (1L << DIV) | (1L << MOD))) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						consume();
						setState(252); expr(12);
						}
						break;
					case 2:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(253);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(254);
						_la = _input.LA(1);
						if ( !(_la==PLUS || _la==MINUS) ) {
						_errHandler.recoverInline(this);
						}
						consume();
						setState(255); expr(11);
						}
						break;
					case 3:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(256);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(257);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << GREATER_EQUAL) | (1L << GREATER) | (1L << LESS_EQUAL) | (1L << LESS))) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						consume();
						setState(258); expr(10);
						}
						break;
					case 4:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(259);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(260);
						_la = _input.LA(1);
						if ( !(_la==DOUBLE_EQUALS || _la==NOT_EQUAL) ) {
						_errHandler.recoverInline(this);
						}
						consume();
						setState(261); expr(9);
						}
						break;
					case 5:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(262);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(263); match(AND);
						setState(264); expr(8);
						}
						break;
					case 6:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(265);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(266); match(OR);
						setState(267); expr(7);
						}
						break;
					}
					} 
				}
				setState(272);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
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
			setState(273); ident();
			setState(278); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(274); match(OPEN_SQUARE);
					setState(275); expr(0);
					setState(276); match(CLOSE_SQUARE);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(280); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
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
			setState(285);
			switch (_input.LA(1)) {
			case PLUS:
			case MINUS:
				enterOuterAlt(_localctx, 1);
				{
				setState(282);
				_la = _input.LA(1);
				if ( !(_la==PLUS || _la==MINUS) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				setState(283); match(INTEGER);
				}
				break;
			case INTEGER:
				enterOuterAlt(_localctx, 2);
				{
				setState(284); match(INTEGER);
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
			setState(287);
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
			setState(289); match(CHAR_LITER);
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
			setState(291); match(STRING_LITER);
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

	public static class BinLiterContext extends ParserRuleContext {
		public TerminalNode BIN_LITER() { return getToken(WACCParser.BIN_LITER, 0); }
		public BinLiterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_binLiter; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitBinLiter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BinLiterContext binLiter() throws RecognitionException {
		BinLiterContext _localctx = new BinLiterContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_binLiter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(293); match(BIN_LITER);
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

	public static class HexLiterContext extends ParserRuleContext {
		public TerminalNode HEX_LITER() { return getToken(WACCParser.HEX_LITER, 0); }
		public HexLiterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_hexLiter; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitHexLiter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final HexLiterContext hexLiter() throws RecognitionException {
		HexLiterContext _localctx = new HexLiterContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_hexLiter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(295); match(HEX_LITER);
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

	public static class OctLiterContext extends ParserRuleContext {
		public TerminalNode OCT_LITER() { return getToken(WACCParser.OCT_LITER, 0); }
		public OctLiterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_octLiter; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitOctLiter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OctLiterContext octLiter() throws RecognitionException {
		OctLiterContext _localctx = new OctLiterContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_octLiter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(297); match(OCT_LITER);
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
		enterRule(_localctx, 46, RULE_arrayLiter);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(299); match(OPEN_SQUARE);
			setState(308);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PLUS) | (1L << MINUS) | (1L << NOT) | (1L << LEN) | (1L << ORD) | (1L << CHR) | (1L << TRUE) | (1L << FALSE) | (1L << OPEN_PARENTHESES) | (1L << INTEGER) | (1L << BIN_LITER) | (1L << OCT_LITER) | (1L << HEX_LITER) | (1L << NULL))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (STRING_LITER - 64)) | (1L << (IDENTITY - 64)) | (1L << (CHAR_LITER - 64)))) != 0)) {
				{
				setState(300); expr(0);
				setState(305);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(301); match(COMMA);
					{
					setState(302); expr(0);
					}
					}
					}
					setState(307);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(310); match(CLOSE_SQUARE);
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
		enterRule(_localctx, 48, RULE_pairLiter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(312); match(NULL);
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
		enterRule(_localctx, 50, RULE_ident);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(314); match(IDENTITY);
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
		case 1: return precpred(_ctx, 11);
		case 2: return precpred(_ctx, 10);
		case 3: return precpred(_ctx, 9);
		case 4: return precpred(_ctx, 8);
		case 5: return precpred(_ctx, 7);
		case 6: return precpred(_ctx, 6);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3N\u013f\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\3\2\3\2\7\29\n\2\f\2\16\2<\13\2\3\2\3\2\3\2\3\2\3"+
		"\3\3\3\3\3\3\3\5\3F\n\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\7\4P\n\4\f\4\16"+
		"\4S\13\4\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6\u0090\n\6\3\6\3\6\3\6\7\6\u0095"+
		"\n\6\f\6\16\6\u0098\13\6\3\7\3\7\3\7\5\7\u009d\n\7\3\b\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\b\u00ad\n\b\3\b\3\b\5\b\u00b1\n"+
		"\b\3\t\3\t\3\t\7\t\u00b6\n\t\f\t\16\t\u00b9\13\t\3\n\3\n\3\n\3\n\3\n\3"+
		"\n\3\n\3\13\3\13\3\13\3\13\5\13\u00c6\n\13\3\f\3\f\3\f\5\f\u00cb\n\f\3"+
		"\r\3\r\5\r\u00cf\n\r\3\r\3\r\6\r\u00d3\n\r\r\r\16\r\u00d4\3\16\3\16\3"+
		"\16\5\16\u00da\n\16\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\5\20\u00f4\n\20\3\20\3\20\3\20\5\20\u00f9\n\20\5\20\u00fb\n\20\3\20\3"+
		"\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3"+
		"\20\3\20\3\20\7\20\u010f\n\20\f\20\16\20\u0112\13\20\3\21\3\21\3\21\3"+
		"\21\3\21\6\21\u0119\n\21\r\21\16\21\u011a\3\22\3\22\3\22\5\22\u0120\n"+
		"\22\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27\3\30\3\30\3\31\3"+
		"\31\3\31\3\31\7\31\u0132\n\31\f\31\16\31\u0135\13\31\5\31\u0137\n\31\3"+
		"\31\3\31\3\32\3\32\3\33\3\33\3\33\2\4\n\36\34\2\4\6\b\n\f\16\20\22\24"+
		"\26\30\32\34\36 \"$&(*,.\60\62\64\2\t\3\2\27\32\4\2\4\4\21\24\3\2\5\7"+
		"\3\2\3\4\3\2\t\f\3\2\r\16\3\2\25\26\u015d\2\66\3\2\2\2\4A\3\2\2\2\6L\3"+
		"\2\2\2\bT\3\2\2\2\n\u008f\3\2\2\2\f\u009c\3\2\2\2\16\u00b0\3\2\2\2\20"+
		"\u00b2\3\2\2\2\22\u00ba\3\2\2\2\24\u00c5\3\2\2\2\26\u00ca\3\2\2\2\30\u00ce"+
		"\3\2\2\2\32\u00d9\3\2\2\2\34\u00db\3\2\2\2\36\u00fa\3\2\2\2 \u0113\3\2"+
		"\2\2\"\u011f\3\2\2\2$\u0121\3\2\2\2&\u0123\3\2\2\2(\u0125\3\2\2\2*\u0127"+
		"\3\2\2\2,\u0129\3\2\2\2.\u012b\3\2\2\2\60\u012d\3\2\2\2\62\u013a\3\2\2"+
		"\2\64\u013c\3\2\2\2\66:\7$\2\2\679\5\4\3\28\67\3\2\2\29<\3\2\2\2:8\3\2"+
		"\2\2:;\3\2\2\2;=\3\2\2\2<:\3\2\2\2=>\5\n\6\2>?\7%\2\2?@\7\2\2\3@\3\3\2"+
		"\2\2AB\5\32\16\2BC\5\64\33\2CE\7\34\2\2DF\5\6\4\2ED\3\2\2\2EF\3\2\2\2"+
		"FG\3\2\2\2GH\7\35\2\2HI\7&\2\2IJ\5\n\6\2JK\7%\2\2K\5\3\2\2\2LQ\5\b\5\2"+
		"MN\7\62\2\2NP\5\b\5\2OM\3\2\2\2PS\3\2\2\2QO\3\2\2\2QR\3\2\2\2R\7\3\2\2"+
		"\2SQ\3\2\2\2TU\5\32\16\2UV\5\64\33\2V\t\3\2\2\2WX\b\6\1\2X\u0090\7\'\2"+
		"\2YZ\5\32\16\2Z[\5\64\33\2[\\\7\b\2\2\\]\5\16\b\2]\u0090\3\2\2\2^_\5\f"+
		"\7\2_`\7\b\2\2`a\5\16\b\2a\u0090\3\2\2\2bc\7(\2\2c\u0090\5\f\7\2de\7)"+
		"\2\2e\u0090\5\36\20\2fg\7*\2\2g\u0090\5\36\20\2hi\7+\2\2i\u0090\5\36\20"+
		"\2jk\7-\2\2k\u0090\5\36\20\2lm\7,\2\2m\u0090\5\36\20\2no\7\64\2\2op\5"+
		"\36\20\2pq\7\65\2\2qr\5\n\6\2rs\7\66\2\2st\5\n\6\2tu\7\67\2\2u\u0090\3"+
		"\2\2\2vw\78\2\2wx\5\36\20\2xy\7:\2\2yz\5\n\6\2z{\79\2\2{\u0090\3\2\2\2"+
		"|}\7:\2\2}~\5\n\6\2~\177\78\2\2\177\u0080\5\36\20\2\u0080\u0081\79\2\2"+
		"\u0081\u0090\3\2\2\2\u0082\u0083\7;\2\2\u0083\u0084\5\n\6\2\u0084\u0085"+
		"\7\60\2\2\u0085\u0086\5\36\20\2\u0086\u0087\7\60\2\2\u0087\u0088\5\36"+
		"\20\2\u0088\u0090\3\2\2\2\u0089\u008a\7$\2\2\u008a\u008b\5\n\6\2\u008b"+
		"\u008c\7%\2\2\u008c\u0090\3\2\2\2\u008d\u0090\7<\2\2\u008e\u0090\7=\2"+
		"\2\u008fW\3\2\2\2\u008fY\3\2\2\2\u008f^\3\2\2\2\u008fb\3\2\2\2\u008fd"+
		"\3\2\2\2\u008ff\3\2\2\2\u008fh\3\2\2\2\u008fj\3\2\2\2\u008fl\3\2\2\2\u008f"+
		"n\3\2\2\2\u008fv\3\2\2\2\u008f|\3\2\2\2\u008f\u0082\3\2\2\2\u008f\u0089"+
		"\3\2\2\2\u008f\u008d\3\2\2\2\u008f\u008e\3\2\2\2\u0090\u0096\3\2\2\2\u0091"+
		"\u0092\f\5\2\2\u0092\u0093\7\60\2\2\u0093\u0095\5\n\6\6\u0094\u0091\3"+
		"\2\2\2\u0095\u0098\3\2\2\2\u0096\u0094\3\2\2\2\u0096\u0097\3\2\2\2\u0097"+
		"\13\3\2\2\2\u0098\u0096\3\2\2\2\u0099\u009d\5\64\33\2\u009a\u009d\5 \21"+
		"\2\u009b\u009d\5\24\13\2\u009c\u0099\3\2\2\2\u009c\u009a\3\2\2\2\u009c"+
		"\u009b\3\2\2\2\u009d\r\3\2\2\2\u009e\u00b1\5\36\20\2\u009f\u00b1\5\60"+
		"\31\2\u00a0\u00a1\7>\2\2\u00a1\u00a2\7\34\2\2\u00a2\u00a3\5\36\20\2\u00a3"+
		"\u00a4\7\62\2\2\u00a4\u00a5\5\36\20\2\u00a5\u00a6\7\35\2\2\u00a6\u00b1"+
		"\3\2\2\2\u00a7\u00b1\5\24\13\2\u00a8\u00a9\7A\2\2\u00a9\u00aa\5\64\33"+
		"\2\u00aa\u00ac\7\34\2\2\u00ab\u00ad\5\20\t\2\u00ac\u00ab\3\2\2\2\u00ac"+
		"\u00ad\3\2\2\2\u00ad\u00ae\3\2\2\2\u00ae\u00af\7\35\2\2\u00af\u00b1\3"+
		"\2\2\2\u00b0\u009e\3\2\2\2\u00b0\u009f\3\2\2\2\u00b0\u00a0\3\2\2\2\u00b0"+
		"\u00a7\3\2\2\2\u00b0\u00a8\3\2\2\2\u00b1\17\3\2\2\2\u00b2\u00b7\5\36\20"+
		"\2\u00b3\u00b4\7\62\2\2\u00b4\u00b6\5\36\20\2\u00b5\u00b3\3\2\2\2\u00b6"+
		"\u00b9\3\2\2\2\u00b7\u00b5\3\2\2\2\u00b7\u00b8\3\2\2\2\u00b8\21\3\2\2"+
		"\2\u00b9\u00b7\3\2\2\2\u00ba\u00bb\7\33\2\2\u00bb\u00bc\7\34\2\2\u00bc"+
		"\u00bd\5\26\f\2\u00bd\u00be\7\62\2\2\u00be\u00bf\5\26\f\2\u00bf\u00c0"+
		"\7\35\2\2\u00c0\23\3\2\2\2\u00c1\u00c2\7?\2\2\u00c2\u00c6\5\36\20\2\u00c3"+
		"\u00c4\7@\2\2\u00c4\u00c6\5\36\20\2\u00c5\u00c1\3\2\2\2\u00c5\u00c3\3"+
		"\2\2\2\u00c6\25\3\2\2\2\u00c7\u00cb\5\34\17\2\u00c8\u00cb\5\30\r\2\u00c9"+
		"\u00cb\7\33\2\2\u00ca\u00c7\3\2\2\2\u00ca\u00c8\3\2\2\2\u00ca\u00c9\3"+
		"\2\2\2\u00cb\27\3\2\2\2\u00cc\u00cf\5\34\17\2\u00cd\u00cf\5\22\n\2\u00ce"+
		"\u00cc\3\2\2\2\u00ce\u00cd\3\2\2\2\u00cf\u00d2\3\2\2\2\u00d0\u00d1\7\36"+
		"\2\2\u00d1\u00d3\7\37\2\2\u00d2\u00d0\3\2\2\2\u00d3\u00d4\3\2\2\2\u00d4"+
		"\u00d2\3\2\2\2\u00d4\u00d5\3\2\2\2\u00d5\31\3\2\2\2\u00d6\u00da\5\34\17"+
		"\2\u00d7\u00da\5\30\r\2\u00d8\u00da\5\22\n\2\u00d9\u00d6\3\2\2\2\u00d9"+
		"\u00d7\3\2\2\2\u00d9\u00d8\3\2\2\2\u00da\33\3\2\2\2\u00db\u00dc\t\2\2"+
		"\2\u00dc\35\3\2\2\2\u00dd\u00de\b\20\1\2\u00de\u00df\t\3\2\2\u00df\u00fb"+
		"\5\36\20\16\u00e0\u00e1\5\64\33\2\u00e1\u00e2\7\17\2\2\u00e2\u00e3\7\17"+
		"\2\2\u00e3\u00fb\3\2\2\2\u00e4\u00e5\5\64\33\2\u00e5\u00e6\7\4\2\2\u00e6"+
		"\u00e7\7\4\2\2\u00e7\u00fb\3\2\2\2\u00e8\u00e9\7\34\2\2\u00e9\u00ea\5"+
		"\36\20\2\u00ea\u00eb\7\35\2\2\u00eb\u00fb\3\2\2\2\u00ec\u00f4\5\"\22\2"+
		"\u00ed\u00f4\5$\23\2\u00ee\u00f4\5&\24\2\u00ef\u00f4\5(\25\2\u00f0\u00f4"+
		"\5\62\32\2\u00f1\u00f4\5\64\33\2\u00f2\u00f4\5 \21\2\u00f3\u00ec\3\2\2"+
		"\2\u00f3\u00ed\3\2\2\2\u00f3\u00ee\3\2\2\2\u00f3\u00ef\3\2\2\2\u00f3\u00f0"+
		"\3\2\2\2\u00f3\u00f1\3\2\2\2\u00f3\u00f2\3\2\2\2\u00f4\u00fb\3\2\2\2\u00f5"+
		"\u00f9\5*\26\2\u00f6\u00f9\5,\27\2\u00f7\u00f9\5.\30\2\u00f8\u00f5\3\2"+
		"\2\2\u00f8\u00f6\3\2\2\2\u00f8\u00f7\3\2\2\2\u00f9\u00fb\3\2\2\2\u00fa"+
		"\u00dd\3\2\2\2\u00fa\u00e0\3\2\2\2\u00fa\u00e4\3\2\2\2\u00fa\u00e8\3\2"+
		"\2\2\u00fa\u00f3\3\2\2\2\u00fa\u00f8\3\2\2\2\u00fb\u0110\3\2\2\2\u00fc"+
		"\u00fd\f\r\2\2\u00fd\u00fe\t\4\2\2\u00fe\u010f\5\36\20\16\u00ff\u0100"+
		"\f\f\2\2\u0100\u0101\t\5\2\2\u0101\u010f\5\36\20\r\u0102\u0103\f\13\2"+
		"\2\u0103\u0104\t\6\2\2\u0104\u010f\5\36\20\f\u0105\u0106\f\n\2\2\u0106"+
		"\u0107\t\7\2\2\u0107\u010f\5\36\20\13\u0108\u0109\f\t\2\2\u0109\u010a"+
		"\7\17\2\2\u010a\u010f\5\36\20\n\u010b\u010c\f\b\2\2\u010c\u010d\7\20\2"+
		"\2\u010d\u010f\5\36\20\t\u010e\u00fc\3\2\2\2\u010e\u00ff\3\2\2\2\u010e"+
		"\u0102\3\2\2\2\u010e\u0105\3\2\2\2\u010e\u0108\3\2\2\2\u010e\u010b\3\2"+
		"\2\2\u010f\u0112\3\2\2\2\u0110\u010e\3\2\2\2\u0110\u0111\3\2\2\2\u0111"+
		"\37\3\2\2\2\u0112\u0110\3\2\2\2\u0113\u0118\5\64\33\2\u0114\u0115\7\36"+
		"\2\2\u0115\u0116\5\36\20\2\u0116\u0117\7\37\2\2\u0117\u0119\3\2\2\2\u0118"+
		"\u0114\3\2\2\2\u0119\u011a\3\2\2\2\u011a\u0118\3\2\2\2\u011a\u011b\3\2"+
		"\2\2\u011b!\3\2\2\2\u011c\u011d\t\5\2\2\u011d\u0120\7 \2\2\u011e\u0120"+
		"\7 \2\2\u011f\u011c\3\2\2\2\u011f\u011e\3\2\2\2\u0120#\3\2\2\2\u0121\u0122"+
		"\t\b\2\2\u0122%\3\2\2\2\u0123\u0124\7N\2\2\u0124\'\3\2\2\2\u0125\u0126"+
		"\7B\2\2\u0126)\3\2\2\2\u0127\u0128\7!\2\2\u0128+\3\2\2\2\u0129\u012a\7"+
		"#\2\2\u012a-\3\2\2\2\u012b\u012c\7\"\2\2\u012c/\3\2\2\2\u012d\u0136\7"+
		"\36\2\2\u012e\u0133\5\36\20\2\u012f\u0130\7\62\2\2\u0130\u0132\5\36\20"+
		"\2\u0131\u012f\3\2\2\2\u0132\u0135\3\2\2\2\u0133\u0131\3\2\2\2\u0133\u0134"+
		"\3\2\2\2\u0134\u0137\3\2\2\2\u0135\u0133\3\2\2\2\u0136\u012e\3\2\2\2\u0136"+
		"\u0137\3\2\2\2\u0137\u0138\3\2\2\2\u0138\u0139\7\37\2\2\u0139\61\3\2\2"+
		"\2\u013a\u013b\7.\2\2\u013b\63\3\2\2\2\u013c\u013d\7M\2\2\u013d\65\3\2"+
		"\2\2\31:EQ\u008f\u0096\u009c\u00ac\u00b0\u00b7\u00c5\u00ca\u00ce\u00d4"+
		"\u00d9\u00f3\u00f8\u00fa\u010e\u0110\u011a\u011f\u0133\u0136";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}