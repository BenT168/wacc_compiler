// Generated from ./WACCParser.g4 by ANTLR 4.4
package antlr;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class WACCParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.4", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		PRINT=40, HASH_KEY=46, APOSTROPHE=68, LT=7, NEWPAIR=54, DO=53, FORM_FEED=64, 
		CHR=17, MINUS=2, END_OF_STRING=60, BACKSLASH=66, WHITESPACE=67, ELSE=49, 
		IF=47, INTEGER=30, DONE=52, NULL=41, MUL=3, FST=55, NEWLINE=61, STRING_LITER=58, 
		TRUE=19, IS=33, EQ=11, READ=35, IDENTITY=69, NOT=18, AND=13, END=32, SEMI_COLON=43, 
		THEN=48, LTE=9, EXIT=38, PLUS=1, CLOSE_PARENTHESES=27, ORD=16, CALL=57, 
		PRINTLN=39, OPEN_PARENTHESES=26, SND=56, CHAR=23, BEGIN=31, FREE=36, INT=21, 
		COMMENT=42, CARRIAGE_RETURN=63, RETURN=37, SKIP=34, WS=59, DOUBLE_QUOTES=65, 
		COMMA=45, MOD=5, OR=14, EQUALS=6, ENDIF=50, COLON=44, CHAR_LITER=70, GT=8, 
		DIV=4, OPEN_SQUARE=28, LEN=15, TAB=62, BOOL=22, GTE=10, STRING=24, CLOSE_SQUARE=29, 
		FALSE=20, WHILE=51, NEQ=12, PAIR=25;
	public static final String[] tokenNames = {
		"<INVALID>", "'+'", "'-'", "'*'", "'/'", "'%'", "'='", "'<'", "'>'", "'<='", 
		"'>='", "'=='", "'!='", "'&&'", "'||'", "'len'", "'ord'", "'chr'", "'!'", 
		"'true'", "'false'", "'int'", "'bool'", "'char'", "'string'", "'pair'", 
		"'('", "')'", "'['", "']'", "INTEGER", "'begin'", "'end'", "'is'", "'skip'", 
		"'read'", "'free'", "'return'", "'exit'", "'println'", "'print'", "'null'", 
		"COMMENT", "';'", "':'", "','", "'#'", "'if'", "'then'", "'else'", "'fi'", 
		"'while'", "'done'", "'do'", "'newpair'", "'fst'", "'snd'", "'call'", 
		"STRING_LITER", "WS", "'\\0'", "'\\n'", "'\\t'", "'\\r'", "'\\f'", "'\\\"'", 
		"'\\'", "' '", "'''", "IDENTITY", "CHAR_LITER"
	};
	public static final int
		RULE_program = 0, RULE_end = 1, RULE_func = 2, RULE_paramList = 3, RULE_param = 4, 
		RULE_stat = 5, RULE_assignLHS = 6, RULE_assignRHS = 7, RULE_argList = 8, 
		RULE_pairElem = 9, RULE_type = 10, RULE_baseType = 11, RULE_arrayType = 12, 
		RULE_pairType = 13, RULE_pairElemType = 14, RULE_expr = 15, RULE_intLiter = 16, 
		RULE_arrayElem = 17, RULE_boolLiter = 18, RULE_charLiter = 19, RULE_stringLiter = 20, 
		RULE_arrayLiter = 21, RULE_pairLiter = 22, RULE_call = 23, RULE_ident = 24;
	public static final String[] ruleNames = {
		"program", "end", "func", "paramList", "param", "stat", "assignLHS", "assignRHS", 
		"argList", "pairElem", "type", "baseType", "arrayType", "pairType", "pairElemType", 
		"expr", "intLiter", "arrayElem", "boolLiter", "charLiter", "stringLiter", 
		"arrayLiter", "pairLiter", "call", "ident"
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
			setState(50); match(BEGIN);
			setState(54);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(51); func();
					}
					} 
				}
				setState(56);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			}
			setState(57); stat(0);
			setState(58); match(END);
			setState(59); match(EOF);
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

	public static class EndContext extends ParserRuleContext {
		public TerminalNode END() { return getToken(WACCParser.END, 0); }
		public EndContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_end; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitEnd(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EndContext end() throws RecognitionException {
		EndContext _localctx = new EndContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_end);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(61); match(END);
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
		enterRule(_localctx, 4, RULE_func);
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
		enterRule(_localctx, 6, RULE_paramList);
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
		enterRule(_localctx, 8, RULE_param);
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
		public TerminalNode EQUALS() { return getToken(WACCParser.EQUALS, 0); }
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
		public TerminalNode EQUALS() { return getToken(WACCParser.EQUALS, 0); }
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
		int _startState = 10;
		enterRecursionRule(_localctx, 10, RULE_stat, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(126);
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
				setState(89); match(EQUALS);
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
				setState(93); match(EQUALS);
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
			case BEGIN:
				{
				_localctx = new BeginContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(122); match(BEGIN);
				setState(123); stat(0);
				setState(124); match(END);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(133);
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
					setState(128);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(129); match(SEMI_COLON);
					setState(130); stat(2);
					}
					} 
				}
				setState(135);
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
		enterRule(_localctx, 12, RULE_assignLHS);
		try {
			setState(139);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(136); ident();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(137); arrayElem();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(138); pairElem();
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
		public ArgListContext argList() {
			return getRuleContext(ArgListContext.class,0);
		}
		public TerminalNode NEWPAIR() { return getToken(WACCParser.NEWPAIR, 0); }
		public TerminalNode CLOSE_PARENTHESES() { return getToken(WACCParser.CLOSE_PARENTHESES, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public ArrayLiterContext arrayLiter() {
			return getRuleContext(ArrayLiterContext.class,0);
		}
		public TerminalNode COMMA() { return getToken(WACCParser.COMMA, 0); }
		public TerminalNode CALL() { return getToken(WACCParser.CALL, 0); }
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public TerminalNode OPEN_PARENTHESES() { return getToken(WACCParser.OPEN_PARENTHESES, 0); }
		public PairElemContext pairElem() {
			return getRuleContext(PairElemContext.class,0);
		}
		public AssignRHSContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignRHS; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitAssignRHS(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignRHSContext assignRHS() throws RecognitionException {
		AssignRHSContext _localctx = new AssignRHSContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_assignRHS);
		int _la;
		try {
			setState(159);
			switch (_input.LA(1)) {
			case PLUS:
			case MINUS:
			case LEN:
			case ORD:
			case CHR:
			case NOT:
			case TRUE:
			case FALSE:
			case OPEN_PARENTHESES:
			case INTEGER:
			case NULL:
			case STRING_LITER:
			case IDENTITY:
			case CHAR_LITER:
				enterOuterAlt(_localctx, 1);
				{
				setState(141); expr(0);
				}
				break;
			case OPEN_SQUARE:
				enterOuterAlt(_localctx, 2);
				{
				setState(142); arrayLiter();
				}
				break;
			case NEWPAIR:
				enterOuterAlt(_localctx, 3);
				{
				setState(143); match(NEWPAIR);
				setState(144); match(OPEN_PARENTHESES);
				setState(145); expr(0);
				setState(146); match(COMMA);
				setState(147); expr(0);
				setState(148); match(CLOSE_PARENTHESES);
				}
				break;
			case FST:
			case SND:
				enterOuterAlt(_localctx, 4);
				{
				setState(150); pairElem();
				}
				break;
			case CALL:
				enterOuterAlt(_localctx, 5);
				{
				setState(151); match(CALL);
				setState(152); ident();
				setState(153); match(OPEN_PARENTHESES);
				setState(155);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PLUS) | (1L << MINUS) | (1L << LEN) | (1L << ORD) | (1L << CHR) | (1L << NOT) | (1L << TRUE) | (1L << FALSE) | (1L << OPEN_PARENTHESES) | (1L << INTEGER) | (1L << NULL) | (1L << STRING_LITER))) != 0) || _la==IDENTITY || _la==CHAR_LITER) {
					{
					setState(154); argList();
					}
				}

				setState(157); match(CLOSE_PARENTHESES);
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
		enterRule(_localctx, 16, RULE_argList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(161); expr(0);
			setState(166);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(162); match(COMMA);
				setState(163); expr(0);
				}
				}
				setState(168);
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
			setState(173);
			switch (_input.LA(1)) {
			case FST:
				enterOuterAlt(_localctx, 1);
				{
				setState(169); match(FST);
				setState(170); expr(0);
				}
				break;
			case SND:
				enterOuterAlt(_localctx, 2);
				{
				setState(171); match(SND);
				setState(172); expr(0);
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
		enterRule(_localctx, 20, RULE_type);
		try {
			setState(178);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(175); baseType();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(176); arrayType();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(177); pairType();
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
		enterRule(_localctx, 22, RULE_baseType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(180);
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
		enterRule(_localctx, 24, RULE_arrayType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(184);
			switch (_input.LA(1)) {
			case INT:
			case BOOL:
			case CHAR:
			case STRING:
				{
				setState(182); baseType();
				}
				break;
			case PAIR:
				{
				setState(183); pairType();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(188); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(186); match(OPEN_SQUARE);
				setState(187); match(CLOSE_SQUARE);
				}
				}
				setState(190); 
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
		enterRule(_localctx, 26, RULE_pairType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(192); match(PAIR);
			setState(193); match(OPEN_PARENTHESES);
			setState(194); pairElemType();
			setState(195); match(COMMA);
			setState(196); pairElemType();
			setState(197); match(CLOSE_PARENTHESES);
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
		enterRule(_localctx, 28, RULE_pairElemType);
		try {
			setState(202);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(199); baseType();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(200); arrayType();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(201); match(PAIR);
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

	public static class ExprContext extends ParserRuleContext {
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode GTE() { return getToken(WACCParser.GTE, 0); }
		public IntLiterContext intLiter() {
			return getRuleContext(IntLiterContext.class,0);
		}
		public StringLiterContext stringLiter() {
			return getRuleContext(StringLiterContext.class,0);
		}
		public TerminalNode LTE() { return getToken(WACCParser.LTE, 0); }
		public TerminalNode OPEN_PARENTHESES() { return getToken(WACCParser.OPEN_PARENTHESES, 0); }
		public TerminalNode MUL() { return getToken(WACCParser.MUL, 0); }
		public TerminalNode NOT() { return getToken(WACCParser.NOT, 0); }
		public CharLiterContext charLiter() {
			return getRuleContext(CharLiterContext.class,0);
		}
		public TerminalNode CLOSE_PARENTHESES() { return getToken(WACCParser.CLOSE_PARENTHESES, 0); }
		public ArrayElemContext arrayElem() {
			return getRuleContext(ArrayElemContext.class,0);
		}
		public PairLiterContext pairLiter() {
			return getRuleContext(PairLiterContext.class,0);
		}
		public BoolLiterContext boolLiter() {
			return getRuleContext(BoolLiterContext.class,0);
		}
		public TerminalNode AND() { return getToken(WACCParser.AND, 0); }
		public TerminalNode DIV() { return getToken(WACCParser.DIV, 0); }
		public TerminalNode ORD() { return getToken(WACCParser.ORD, 0); }
		public TerminalNode NEQ() { return getToken(WACCParser.NEQ, 0); }
		public TerminalNode LT() { return getToken(WACCParser.LT, 0); }
		public TerminalNode GT() { return getToken(WACCParser.GT, 0); }
		public TerminalNode LEN() { return getToken(WACCParser.LEN, 0); }
		public TerminalNode MOD() { return getToken(WACCParser.MOD, 0); }
		public TerminalNode OR() { return getToken(WACCParser.OR, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public TerminalNode MINUS() { return getToken(WACCParser.MINUS, 0); }
		public TerminalNode PLUS() { return getToken(WACCParser.PLUS, 0); }
		public TerminalNode EQ() { return getToken(WACCParser.EQ, 0); }
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
		int _startState = 30;
		enterRecursionRule(_localctx, 30, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(220);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				{
				setState(205);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MINUS) | (1L << LEN) | (1L << ORD) | (1L << CHR) | (1L << NOT))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				setState(206); expr(11);
				}
				break;
			case 2:
				{
				setState(207); match(OPEN_PARENTHESES);
				setState(208); expr(0);
				setState(209); match(CLOSE_PARENTHESES);
				}
				break;
			case 3:
				{
				setState(211); arrayElem();
				}
				break;
			case 4:
				{
				setState(217);
				switch (_input.LA(1)) {
				case PLUS:
				case MINUS:
				case INTEGER:
					{
					setState(212); intLiter();
					}
					break;
				case TRUE:
				case FALSE:
					{
					setState(213); boolLiter();
					}
					break;
				case CHAR_LITER:
					{
					setState(214); charLiter();
					}
					break;
				case STRING_LITER:
					{
					setState(215); stringLiter();
					}
					break;
				case NULL:
					{
					setState(216); pairLiter();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			case 5:
				{
				setState(219); ident();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(242);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(240);
					switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
					case 1:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(222);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(223);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MUL) | (1L << DIV) | (1L << MOD))) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						consume();
						setState(224); expr(11);
						}
						break;
					case 2:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(225);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(226);
						_la = _input.LA(1);
						if ( !(_la==PLUS || _la==MINUS) ) {
						_errHandler.recoverInline(this);
						}
						consume();
						setState(227); expr(10);
						}
						break;
					case 3:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(228);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(229);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LT) | (1L << GT) | (1L << LTE) | (1L << GTE))) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						consume();
						setState(230); expr(9);
						}
						break;
					case 4:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(231);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(232);
						_la = _input.LA(1);
						if ( !(_la==EQ || _la==NEQ) ) {
						_errHandler.recoverInline(this);
						}
						consume();
						setState(233); expr(8);
						}
						break;
					case 5:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(234);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(235); match(AND);
						setState(236); expr(7);
						}
						break;
					case 6:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(237);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(238); match(OR);
						setState(239); expr(6);
						}
						break;
					}
					} 
				}
				setState(244);
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
			setState(248);
			switch (_input.LA(1)) {
			case PLUS:
			case MINUS:
				enterOuterAlt(_localctx, 1);
				{
				setState(245);
				_la = _input.LA(1);
				if ( !(_la==PLUS || _la==MINUS) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				setState(246); match(INTEGER);
				}
				break;
			case INTEGER:
				enterOuterAlt(_localctx, 2);
				{
				setState(247); match(INTEGER);
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
		enterRule(_localctx, 34, RULE_arrayElem);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(250); ident();
			setState(255); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(251); match(OPEN_SQUARE);
					setState(252); expr(0);
					setState(253); match(CLOSE_SQUARE);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(257); 
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
		enterRule(_localctx, 36, RULE_boolLiter);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(259);
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
		enterRule(_localctx, 38, RULE_charLiter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(261); match(CHAR_LITER);
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
		enterRule(_localctx, 40, RULE_stringLiter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(263); match(STRING_LITER);
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
		enterRule(_localctx, 42, RULE_arrayLiter);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(265); match(OPEN_SQUARE);
			setState(274);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PLUS) | (1L << MINUS) | (1L << LEN) | (1L << ORD) | (1L << CHR) | (1L << NOT) | (1L << TRUE) | (1L << FALSE) | (1L << OPEN_PARENTHESES) | (1L << INTEGER) | (1L << NULL) | (1L << STRING_LITER))) != 0) || _la==IDENTITY || _la==CHAR_LITER) {
				{
				setState(266); expr(0);
				setState(271);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(267); match(COMMA);
					{
					setState(268); expr(0);
					}
					}
					}
					setState(273);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(276); match(CLOSE_SQUARE);
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
		enterRule(_localctx, 44, RULE_pairLiter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(278); match(NULL);
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

	public static class CallContext extends ParserRuleContext {
		public TerminalNode CALL() { return getToken(WACCParser.CALL, 0); }
		public CallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_call; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitCall(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CallContext call() throws RecognitionException {
		CallContext _localctx = new CallContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_call);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(280); match(CALL);
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
		enterRule(_localctx, 48, RULE_ident);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(282); match(IDENTITY);
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
		case 5: return stat_sempred((StatContext)_localctx, predIndex);
		case 15: return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean stat_sempred(StatContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0: return precpred(_ctx, 1);
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
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3H\u011f\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\3\2\3\2\7\2\67\n\2\f\2\16\2:\13\2\3\2\3\2\3\2\3\2\3\3\3\3\3"+
		"\4\3\4\3\4\3\4\5\4F\n\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\7\5P\n\5\f\5\16"+
		"\5S\13\5\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3"+
		"\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\5\7\u0081\n\7\3\7\3\7\3\7"+
		"\7\7\u0086\n\7\f\7\16\7\u0089\13\7\3\b\3\b\3\b\5\b\u008e\n\b\3\t\3\t\3"+
		"\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\t\u009e\n\t\3\t\3\t\5"+
		"\t\u00a2\n\t\3\n\3\n\3\n\7\n\u00a7\n\n\f\n\16\n\u00aa\13\n\3\13\3\13\3"+
		"\13\3\13\5\13\u00b0\n\13\3\f\3\f\3\f\5\f\u00b5\n\f\3\r\3\r\3\16\3\16\5"+
		"\16\u00bb\n\16\3\16\3\16\6\16\u00bf\n\16\r\16\16\16\u00c0\3\17\3\17\3"+
		"\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\5\20\u00cd\n\20\3\21\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\5\21\u00dc\n\21\3\21"+
		"\5\21\u00df\n\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\3\21\7\21\u00f3\n\21\f\21\16\21\u00f6\13"+
		"\21\3\22\3\22\3\22\5\22\u00fb\n\22\3\23\3\23\3\23\3\23\3\23\6\23\u0102"+
		"\n\23\r\23\16\23\u0103\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27\3\27\3"+
		"\27\7\27\u0110\n\27\f\27\16\27\u0113\13\27\5\27\u0115\n\27\3\27\3\27\3"+
		"\30\3\30\3\31\3\31\3\32\3\32\3\32\2\4\f \33\2\4\6\b\n\f\16\20\22\24\26"+
		"\30\32\34\36 \"$&(*,.\60\62\2\t\3\2\27\32\4\2\4\4\21\24\3\2\5\7\3\2\3"+
		"\4\3\2\t\f\3\2\r\16\3\2\25\26\u0135\2\64\3\2\2\2\4?\3\2\2\2\6A\3\2\2\2"+
		"\bL\3\2\2\2\nT\3\2\2\2\f\u0080\3\2\2\2\16\u008d\3\2\2\2\20\u00a1\3\2\2"+
		"\2\22\u00a3\3\2\2\2\24\u00af\3\2\2\2\26\u00b4\3\2\2\2\30\u00b6\3\2\2\2"+
		"\32\u00ba\3\2\2\2\34\u00c2\3\2\2\2\36\u00cc\3\2\2\2 \u00de\3\2\2\2\"\u00fa"+
		"\3\2\2\2$\u00fc\3\2\2\2&\u0105\3\2\2\2(\u0107\3\2\2\2*\u0109\3\2\2\2,"+
		"\u010b\3\2\2\2.\u0118\3\2\2\2\60\u011a\3\2\2\2\62\u011c\3\2\2\2\648\7"+
		"!\2\2\65\67\5\6\4\2\66\65\3\2\2\2\67:\3\2\2\28\66\3\2\2\289\3\2\2\29;"+
		"\3\2\2\2:8\3\2\2\2;<\5\f\7\2<=\7\"\2\2=>\7\2\2\3>\3\3\2\2\2?@\7\"\2\2"+
		"@\5\3\2\2\2AB\5\26\f\2BC\5\62\32\2CE\7\34\2\2DF\5\b\5\2ED\3\2\2\2EF\3"+
		"\2\2\2FG\3\2\2\2GH\7\35\2\2HI\7#\2\2IJ\5\f\7\2JK\7\"\2\2K\7\3\2\2\2LQ"+
		"\5\n\6\2MN\7/\2\2NP\5\n\6\2OM\3\2\2\2PS\3\2\2\2QO\3\2\2\2QR\3\2\2\2R\t"+
		"\3\2\2\2SQ\3\2\2\2TU\5\26\f\2UV\5\62\32\2V\13\3\2\2\2WX\b\7\1\2X\u0081"+
		"\7$\2\2YZ\5\26\f\2Z[\5\62\32\2[\\\7\b\2\2\\]\5\20\t\2]\u0081\3\2\2\2^"+
		"_\5\16\b\2_`\7\b\2\2`a\5\20\t\2a\u0081\3\2\2\2bc\7%\2\2c\u0081\5\16\b"+
		"\2de\7&\2\2e\u0081\5 \21\2fg\7\'\2\2g\u0081\5 \21\2hi\7(\2\2i\u0081\5"+
		" \21\2jk\7*\2\2k\u0081\5 \21\2lm\7)\2\2m\u0081\5 \21\2no\7\61\2\2op\5"+
		" \21\2pq\7\62\2\2qr\5\f\7\2rs\7\63\2\2st\5\f\7\2tu\7\64\2\2u\u0081\3\2"+
		"\2\2vw\7\65\2\2wx\5 \21\2xy\7\67\2\2yz\5\f\7\2z{\7\66\2\2{\u0081\3\2\2"+
		"\2|}\7!\2\2}~\5\f\7\2~\177\7\"\2\2\177\u0081\3\2\2\2\u0080W\3\2\2\2\u0080"+
		"Y\3\2\2\2\u0080^\3\2\2\2\u0080b\3\2\2\2\u0080d\3\2\2\2\u0080f\3\2\2\2"+
		"\u0080h\3\2\2\2\u0080j\3\2\2\2\u0080l\3\2\2\2\u0080n\3\2\2\2\u0080v\3"+
		"\2\2\2\u0080|\3\2\2\2\u0081\u0087\3\2\2\2\u0082\u0083\f\3\2\2\u0083\u0084"+
		"\7-\2\2\u0084\u0086\5\f\7\4\u0085\u0082\3\2\2\2\u0086\u0089\3\2\2\2\u0087"+
		"\u0085\3\2\2\2\u0087\u0088\3\2\2\2\u0088\r\3\2\2\2\u0089\u0087\3\2\2\2"+
		"\u008a\u008e\5\62\32\2\u008b\u008e\5$\23\2\u008c\u008e\5\24\13\2\u008d"+
		"\u008a\3\2\2\2\u008d\u008b\3\2\2\2\u008d\u008c\3\2\2\2\u008e\17\3\2\2"+
		"\2\u008f\u00a2\5 \21\2\u0090\u00a2\5,\27\2\u0091\u0092\78\2\2\u0092\u0093"+
		"\7\34\2\2\u0093\u0094\5 \21\2\u0094\u0095\7/\2\2\u0095\u0096\5 \21\2\u0096"+
		"\u0097\7\35\2\2\u0097\u00a2\3\2\2\2\u0098\u00a2\5\24\13\2\u0099\u009a"+
		"\7;\2\2\u009a\u009b\5\62\32\2\u009b\u009d\7\34\2\2\u009c\u009e\5\22\n"+
		"\2\u009d\u009c\3\2\2\2\u009d\u009e\3\2\2\2\u009e\u009f\3\2\2\2\u009f\u00a0"+
		"\7\35\2\2\u00a0\u00a2\3\2\2\2\u00a1\u008f\3\2\2\2\u00a1\u0090\3\2\2\2"+
		"\u00a1\u0091\3\2\2\2\u00a1\u0098\3\2\2\2\u00a1\u0099\3\2\2\2\u00a2\21"+
		"\3\2\2\2\u00a3\u00a8\5 \21\2\u00a4\u00a5\7/\2\2\u00a5\u00a7\5 \21\2\u00a6"+
		"\u00a4\3\2\2\2\u00a7\u00aa\3\2\2\2\u00a8\u00a6\3\2\2\2\u00a8\u00a9\3\2"+
		"\2\2\u00a9\23\3\2\2\2\u00aa\u00a8\3\2\2\2\u00ab\u00ac\79\2\2\u00ac\u00b0"+
		"\5 \21\2\u00ad\u00ae\7:\2\2\u00ae\u00b0\5 \21\2\u00af\u00ab\3\2\2\2\u00af"+
		"\u00ad\3\2\2\2\u00b0\25\3\2\2\2\u00b1\u00b5\5\30\r\2\u00b2\u00b5\5\32"+
		"\16\2\u00b3\u00b5\5\34\17\2\u00b4\u00b1\3\2\2\2\u00b4\u00b2\3\2\2\2\u00b4"+
		"\u00b3\3\2\2\2\u00b5\27\3\2\2\2\u00b6\u00b7\t\2\2\2\u00b7\31\3\2\2\2\u00b8"+
		"\u00bb\5\30\r\2\u00b9\u00bb\5\34\17\2\u00ba\u00b8\3\2\2\2\u00ba\u00b9"+
		"\3\2\2\2\u00bb\u00be\3\2\2\2\u00bc\u00bd\7\36\2\2\u00bd\u00bf\7\37\2\2"+
		"\u00be\u00bc\3\2\2\2\u00bf\u00c0\3\2\2\2\u00c0\u00be\3\2\2\2\u00c0\u00c1"+
		"\3\2\2\2\u00c1\33\3\2\2\2\u00c2\u00c3\7\33\2\2\u00c3\u00c4\7\34\2\2\u00c4"+
		"\u00c5\5\36\20\2\u00c5\u00c6\7/\2\2\u00c6\u00c7\5\36\20\2\u00c7\u00c8"+
		"\7\35\2\2\u00c8\35\3\2\2\2\u00c9\u00cd\5\30\r\2\u00ca\u00cd\5\32\16\2"+
		"\u00cb\u00cd\7\33\2\2\u00cc\u00c9\3\2\2\2\u00cc\u00ca\3\2\2\2\u00cc\u00cb"+
		"\3\2\2\2\u00cd\37\3\2\2\2\u00ce\u00cf\b\21\1\2\u00cf\u00d0\t\3\2\2\u00d0"+
		"\u00df\5 \21\r\u00d1\u00d2\7\34\2\2\u00d2\u00d3\5 \21\2\u00d3\u00d4\7"+
		"\35\2\2\u00d4\u00df\3\2\2\2\u00d5\u00df\5$\23\2\u00d6\u00dc\5\"\22\2\u00d7"+
		"\u00dc\5&\24\2\u00d8\u00dc\5(\25\2\u00d9\u00dc\5*\26\2\u00da\u00dc\5."+
		"\30\2\u00db\u00d6\3\2\2\2\u00db\u00d7\3\2\2\2\u00db\u00d8\3\2\2\2\u00db"+
		"\u00d9\3\2\2\2\u00db\u00da\3\2\2\2\u00dc\u00df\3\2\2\2\u00dd\u00df\5\62"+
		"\32\2\u00de\u00ce\3\2\2\2\u00de\u00d1\3\2\2\2\u00de\u00d5\3\2\2\2\u00de"+
		"\u00db\3\2\2\2\u00de\u00dd\3\2\2\2\u00df\u00f4\3\2\2\2\u00e0\u00e1\f\f"+
		"\2\2\u00e1\u00e2\t\4\2\2\u00e2\u00f3\5 \21\r\u00e3\u00e4\f\13\2\2\u00e4"+
		"\u00e5\t\5\2\2\u00e5\u00f3\5 \21\f\u00e6\u00e7\f\n\2\2\u00e7\u00e8\t\6"+
		"\2\2\u00e8\u00f3\5 \21\13\u00e9\u00ea\f\t\2\2\u00ea\u00eb\t\7\2\2\u00eb"+
		"\u00f3\5 \21\n\u00ec\u00ed\f\b\2\2\u00ed\u00ee\7\17\2\2\u00ee\u00f3\5"+
		" \21\t\u00ef\u00f0\f\7\2\2\u00f0\u00f1\7\20\2\2\u00f1\u00f3\5 \21\b\u00f2"+
		"\u00e0\3\2\2\2\u00f2\u00e3\3\2\2\2\u00f2\u00e6\3\2\2\2\u00f2\u00e9\3\2"+
		"\2\2\u00f2\u00ec\3\2\2\2\u00f2\u00ef\3\2\2\2\u00f3\u00f6\3\2\2\2\u00f4"+
		"\u00f2\3\2\2\2\u00f4\u00f5\3\2\2\2\u00f5!\3\2\2\2\u00f6\u00f4\3\2\2\2"+
		"\u00f7\u00f8\t\5\2\2\u00f8\u00fb\7 \2\2\u00f9\u00fb\7 \2\2\u00fa\u00f7"+
		"\3\2\2\2\u00fa\u00f9\3\2\2\2\u00fb#\3\2\2\2\u00fc\u0101\5\62\32\2\u00fd"+
		"\u00fe\7\36\2\2\u00fe\u00ff\5 \21\2\u00ff\u0100\7\37\2\2\u0100\u0102\3"+
		"\2\2\2\u0101\u00fd\3\2\2\2\u0102\u0103\3\2\2\2\u0103\u0101\3\2\2\2\u0103"+
		"\u0104\3\2\2\2\u0104%\3\2\2\2\u0105\u0106\t\b\2\2\u0106\'\3\2\2\2\u0107"+
		"\u0108\7H\2\2\u0108)\3\2\2\2\u0109\u010a\7<\2\2\u010a+\3\2\2\2\u010b\u0114"+
		"\7\36\2\2\u010c\u0111\5 \21\2\u010d\u010e\7/\2\2\u010e\u0110\5 \21\2\u010f"+
		"\u010d\3\2\2\2\u0110\u0113\3\2\2\2\u0111\u010f\3\2\2\2\u0111\u0112\3\2"+
		"\2\2\u0112\u0115\3\2\2\2\u0113\u0111\3\2\2\2\u0114\u010c\3\2\2\2\u0114"+
		"\u0115\3\2\2\2\u0115\u0116\3\2\2\2\u0116\u0117\7\37\2\2\u0117-\3\2\2\2"+
		"\u0118\u0119\7+\2\2\u0119/\3\2\2\2\u011a\u011b\7;\2\2\u011b\61\3\2\2\2"+
		"\u011c\u011d\7G\2\2\u011d\63\3\2\2\2\308EQ\u0080\u0087\u008d\u009d\u00a1"+
		"\u00a8\u00af\u00b4\u00ba\u00c0\u00cc\u00db\u00de\u00f2\u00f4\u00fa\u0103"+
		"\u0111\u0114";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}