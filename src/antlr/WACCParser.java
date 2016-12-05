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
		PRINT=55, HASH_KEY=61, NEW=33, ADD=35, APOSTROPHE=86, NEWPAIR=72, DO=68, 
		FORM_FEED=82, CHR=20, MINUS=2, END_OF_STRING=78, GREATER_EQUAL=9, BACKSLASH=84, 
		WHITESPACE=85, BREAK=70, ELSE=64, LESS_EQUAL=11, IF=62, INTEGER=42, HEX_LITER=45, 
		DONE=67, NULL=56, MUL=3, DOUBLE_EQUALS=13, FST=73, NEWLINE=79, STRING_LITER=76, 
		FOR=69, MINUSMINUS=22, TRUE=23, IS=48, READ=50, IDENTITY=87, HASHMAP=37, 
		NOT=17, AND=15, LINKEDLIST=31, GET=34, LESS=12, END=47, SEMI_COLON=58, 
		THEN=63, LIST=30, MAP=36, EXIT=53, PLUS=1, CLOSE_PARENTHESES=39, ORD=19, 
		CALL=75, PRINTLN=54, OPEN_PARENTHESES=38, SND=74, CHAR=27, BEGIN=46, FREE=51, 
		PLUSPLUS=21, INT=25, COMMENT=57, CARRIAGE_RETURN=81, RETURN=52, SKIP=49, 
		WS=77, DOUBLE_QUOTES=83, COMMA=60, BIN_LITER=43, MOD=5, OR=16, ARRAYLIST=32, 
		OCT_LITER=44, EQUAL=6, GREATER=10, ENDIF=65, COLON=59, CHAR_LITER=88, 
		DIV=4, CONTINUE=71, OPEN_SQUARE=40, LEN=18, TAB=80, BOOL=26, PLUSEQUAL=7, 
		MINUSEQUAL=8, NOT_EQUAL=14, STRING=28, CLOSE_SQUARE=41, FALSE=24, WHILE=66, 
		PAIR=29;
	public static final String[] tokenNames = {
		"<INVALID>", "'+'", "'-'", "'*'", "'/'", "'%'", "'='", "'+='", "'-='", 
		"'>='", "'>'", "'<='", "'<'", "'=='", "'!='", "'&&'", "'||'", "'!'", "'len'", 
		"'ord'", "'chr'", "'++'", "'--'", "'true'", "'false'", "'int'", "'bool'", 
		"'char'", "'string'", "'pair'", "'list'", "'linkedList'", "'arrayList'", 
		"'new'", "'get'", "'add'", "'map'", "'hashMap'", "'('", "')'", "'['", 
		"']'", "INTEGER", "BIN_LITER", "OCT_LITER", "HEX_LITER", "'begin'", "'end'", 
		"'is'", "'skip'", "'read'", "'free'", "'return'", "'exit'", "'println'", 
		"'print'", "'null'", "COMMENT", "';'", "':'", "','", "'#'", "'if'", "'then'", 
		"'else'", "'fi'", "'while'", "'done'", "'do'", "'for'", "'break'", "'continues'", 
		"'newpair'", "'fst'", "'snd'", "'call'", "STRING_LITER", "WS", "'\\0'", 
		"'\\n'", "'\\t'", "'\\r'", "'\\f'", "'\\\"'", "'\\'", "' '", "'''", "IDENTITY", 
		"CHAR_LITER"
	};
	public static final int
		RULE_program = 0, RULE_func = 1, RULE_paramList = 2, RULE_param = 3, RULE_stat = 4, 
		RULE_assignLHS = 5, RULE_assignRHS = 6, RULE_argList = 7, RULE_pairType = 8, 
		RULE_pairElem = 9, RULE_pairElemType = 10, RULE_arrayType = 11, RULE_listType = 12, 
		RULE_mapType = 13, RULE_type = 14, RULE_baseType = 15, RULE_expr = 16, 
		RULE_arrayElem = 17, RULE_listElem = 18, RULE_intLiter = 19, RULE_boolLiter = 20, 
		RULE_charLiter = 21, RULE_stringLiter = 22, RULE_binLiter = 23, RULE_hexLiter = 24, 
		RULE_octLiter = 25, RULE_arrayLiter = 26, RULE_pairLiter = 27, RULE_ident = 28;
	public static final String[] ruleNames = {
		"program", "func", "paramList", "param", "stat", "assignLHS", "assignRHS", 
		"argList", "pairType", "pairElem", "pairElemType", "arrayType", "listType", 
		"mapType", "type", "baseType", "expr", "arrayElem", "listElem", "intLiter", 
		"boolLiter", "charLiter", "stringLiter", "binLiter", "hexLiter", "octLiter", 
		"arrayLiter", "pairLiter", "ident"
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
			setState(58); match(BEGIN);
			setState(62);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(59); func();
					}
					} 
				}
				setState(64);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			}
			setState(65); stat(0);
			setState(66); match(END);
			setState(67); match(EOF);
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
			setState(69); type();
			setState(70); ident();
			setState(71); match(OPEN_PARENTHESES);
			setState(73);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INT) | (1L << BOOL) | (1L << CHAR) | (1L << STRING) | (1L << PAIR) | (1L << LIST) | (1L << LINKEDLIST) | (1L << ARRAYLIST) | (1L << MAP) | (1L << HASHMAP))) != 0)) {
				{
				setState(72); paramList();
				}
			}

			setState(75); match(CLOSE_PARENTHESES);
			setState(76); match(IS);
			setState(77); stat(0);
			setState(78); match(END);
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
			setState(80); param();
			setState(85);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(81); match(COMMA);
				setState(82); param();
				}
				}
				setState(87);
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
			setState(88); type();
			setState(89); ident();
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
	public static class AddElemContext extends StatContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public TerminalNode ADD() { return getToken(WACCParser.ADD, 0); }
		public AddElemContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitAddElem(this);
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
			setState(154);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				{
				_localctx = new SkipContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(92); match(SKIP);
				}
				break;
			case 2:
				{
				_localctx = new DeclareContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(93); type();
				setState(94); ident();
				setState(95); match(EQUAL);
				setState(96); assignRHS();
				}
				break;
			case 3:
				{
				_localctx = new AssignContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(98); assignLHS();
				setState(99); match(EQUAL);
				setState(100); assignRHS();
				}
				break;
			case 4:
				{
				_localctx = new ReadContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(102); match(READ);
				setState(103); assignLHS();
				}
				break;
			case 5:
				{
				_localctx = new FreeContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(104); match(FREE);
				setState(105); expr(0);
				}
				break;
			case 6:
				{
				_localctx = new ReturnContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(106); match(RETURN);
				setState(107); expr(0);
				}
				break;
			case 7:
				{
				_localctx = new ExitContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(108); match(EXIT);
				setState(109); expr(0);
				}
				break;
			case 8:
				{
				_localctx = new PrintContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(110); match(PRINT);
				setState(111); expr(0);
				}
				break;
			case 9:
				{
				_localctx = new PrintlnContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(112); match(PRINTLN);
				setState(113); expr(0);
				}
				break;
			case 10:
				{
				_localctx = new IfElseContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(114); match(IF);
				setState(115); expr(0);
				setState(116); match(THEN);
				setState(117); stat(0);
				setState(118); match(ELSE);
				setState(119); stat(0);
				setState(120); match(ENDIF);
				}
				break;
			case 11:
				{
				_localctx = new WhileContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(122); match(WHILE);
				setState(123); expr(0);
				setState(124); match(DO);
				setState(125); stat(0);
				setState(126); match(DONE);
				}
				break;
			case 12:
				{
				_localctx = new DoWhileContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(128); match(DO);
				setState(129); stat(0);
				setState(130); match(WHILE);
				setState(131); expr(0);
				setState(132); match(DONE);
				}
				break;
			case 13:
				{
				_localctx = new ForLoopContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(134); match(FOR);
				setState(135); stat(0);
				setState(136); match(SEMI_COLON);
				setState(137); expr(0);
				setState(138); match(SEMI_COLON);
				setState(139); expr(0);
				setState(140); match(DO);
				setState(141); stat(0);
				setState(142); match(DONE);
				}
				break;
			case 14:
				{
				_localctx = new BeginContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(144); match(BEGIN);
				setState(145); stat(0);
				setState(146); match(END);
				}
				break;
			case 15:
				{
				_localctx = new AddElemContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(148); ident();
				setState(149); match(ADD);
				setState(150); expr(0);
				}
				break;
			case 16:
				{
				_localctx = new BreakContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(152); match(BREAK);
				}
				break;
			case 17:
				{
				_localctx = new ContinuesContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(153); match(CONTINUE);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(161);
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
					setState(156);
					if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
					setState(157); match(SEMI_COLON);
					setState(158); stat(5);
					}
					} 
				}
				setState(163);
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
		public ListElemContext listElem() {
			return getRuleContext(ListElemContext.class,0);
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
			setState(168);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(164); ident();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(165); arrayElem();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(166); pairElem();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(167); listElem();
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
	public static class NewList_assignRHSContext extends AssignRHSContext {
		public TerminalNode LESS() { return getToken(WACCParser.LESS, 0); }
		public TerminalNode CLOSE_PARENTHESES() { return getToken(WACCParser.CLOSE_PARENTHESES, 0); }
		public TerminalNode ARRAYLIST() { return getToken(WACCParser.ARRAYLIST, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode GREATER() { return getToken(WACCParser.GREATER, 0); }
		public TerminalNode NEW() { return getToken(WACCParser.NEW, 0); }
		public TerminalNode LINKEDLIST() { return getToken(WACCParser.LINKEDLIST, 0); }
		public TerminalNode OPEN_PARENTHESES() { return getToken(WACCParser.OPEN_PARENTHESES, 0); }
		public NewList_assignRHSContext(AssignRHSContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitNewList_assignRHS(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NewMap_assignRHSContext extends AssignRHSContext {
		public TerminalNode LESS() { return getToken(WACCParser.LESS, 0); }
		public TerminalNode CLOSE_PARENTHESES() { return getToken(WACCParser.CLOSE_PARENTHESES, 0); }
		public TerminalNode HASHMAP() { return getToken(WACCParser.HASHMAP, 0); }
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public TerminalNode COMMA() { return getToken(WACCParser.COMMA, 0); }
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TerminalNode GREATER() { return getToken(WACCParser.GREATER, 0); }
		public TerminalNode NEW() { return getToken(WACCParser.NEW, 0); }
		public TerminalNode OPEN_PARENTHESES() { return getToken(WACCParser.OPEN_PARENTHESES, 0); }
		public NewMap_assignRHSContext(AssignRHSContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitNewMap_assignRHS(this);
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
			setState(206);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				_localctx = new Expr_assignRHSContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(170); expr(0);
				}
				break;
			case 2:
				_localctx = new ArrayLiter_assignRHSContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(171); arrayLiter();
				}
				break;
			case 3:
				_localctx = new NewPair_assignRHSContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(172); match(NEWPAIR);
				setState(173); match(OPEN_PARENTHESES);
				setState(174); expr(0);
				setState(175); match(COMMA);
				setState(176); expr(0);
				setState(177); match(CLOSE_PARENTHESES);
				}
				break;
			case 4:
				_localctx = new NewList_assignRHSContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(179); match(NEW);
				setState(180);
				_la = _input.LA(1);
				if ( !(_la==LINKEDLIST || _la==ARRAYLIST) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				setState(181); match(LESS);
				setState(182); type();
				setState(183); match(GREATER);
				setState(184); match(OPEN_PARENTHESES);
				setState(185); match(CLOSE_PARENTHESES);
				}
				break;
			case 5:
				_localctx = new NewMap_assignRHSContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(187); match(NEW);
				setState(188); match(HASHMAP);
				setState(189); match(LESS);
				setState(190); type();
				setState(191); match(COMMA);
				setState(192); type();
				setState(193); match(GREATER);
				setState(194); match(OPEN_PARENTHESES);
				setState(195); match(CLOSE_PARENTHESES);
				}
				break;
			case 6:
				_localctx = new PairElem_assignRHSContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(197); pairElem();
				}
				break;
			case 7:
				_localctx = new FuncCall_assignRHSContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(198); match(CALL);
				setState(199); ident();
				setState(200); match(OPEN_PARENTHESES);
				setState(202);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PLUS) | (1L << MINUS) | (1L << NOT) | (1L << LEN) | (1L << ORD) | (1L << CHR) | (1L << TRUE) | (1L << FALSE) | (1L << OPEN_PARENTHESES) | (1L << INTEGER) | (1L << BIN_LITER) | (1L << OCT_LITER) | (1L << HEX_LITER) | (1L << NULL))) != 0) || ((((_la - 76)) & ~0x3f) == 0 && ((1L << (_la - 76)) & ((1L << (STRING_LITER - 76)) | (1L << (IDENTITY - 76)) | (1L << (CHAR_LITER - 76)))) != 0)) {
					{
					setState(201); argList();
					}
				}

				setState(204); match(CLOSE_PARENTHESES);
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
			setState(208); expr(0);
			setState(213);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(209); match(COMMA);
				{
				setState(210); expr(0);
				}
				}
				}
				setState(215);
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
			setState(216); match(PAIR);
			setState(217); match(OPEN_PARENTHESES);
			setState(218); pairElemType();
			setState(219); match(COMMA);
			setState(220); pairElemType();
			setState(221); match(CLOSE_PARENTHESES);
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
			setState(227);
			switch (_input.LA(1)) {
			case FST:
				enterOuterAlt(_localctx, 1);
				{
				setState(223); match(FST);
				setState(224); expr(0);
				}
				break;
			case SND:
				enterOuterAlt(_localctx, 2);
				{
				setState(225); match(SND);
				setState(226); expr(0);
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
			setState(232);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(229); baseType();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(230); arrayType();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(231); match(PAIR);
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
			setState(236);
			switch (_input.LA(1)) {
			case INT:
			case BOOL:
			case CHAR:
			case STRING:
				{
				setState(234); baseType();
				}
				break;
			case PAIR:
				{
				setState(235); pairType();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(240); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(238); match(OPEN_SQUARE);
				setState(239); match(CLOSE_SQUARE);
				}
				}
				setState(242); 
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

	public static class ListTypeContext extends ParserRuleContext {
		public TerminalNode LESS() { return getToken(WACCParser.LESS, 0); }
		public TerminalNode LIST() { return getToken(WACCParser.LIST, 0); }
		public TerminalNode ARRAYLIST() { return getToken(WACCParser.ARRAYLIST, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode GREATER() { return getToken(WACCParser.GREATER, 0); }
		public TerminalNode LINKEDLIST() { return getToken(WACCParser.LINKEDLIST, 0); }
		public ListTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_listType; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitListType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ListTypeContext listType() throws RecognitionException {
		ListTypeContext _localctx = new ListTypeContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_listType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(244);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LIST) | (1L << LINKEDLIST) | (1L << ARRAYLIST))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			{
			setState(245); match(LESS);
			setState(246); type();
			setState(247); match(GREATER);
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

	public static class MapTypeContext extends ParserRuleContext {
		public TerminalNode LESS() { return getToken(WACCParser.LESS, 0); }
		public TerminalNode MAP() { return getToken(WACCParser.MAP, 0); }
		public TerminalNode HASHMAP() { return getToken(WACCParser.HASHMAP, 0); }
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public TerminalNode COMMA() { return getToken(WACCParser.COMMA, 0); }
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TerminalNode GREATER() { return getToken(WACCParser.GREATER, 0); }
		public MapTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mapType; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitMapType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MapTypeContext mapType() throws RecognitionException {
		MapTypeContext _localctx = new MapTypeContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_mapType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(249);
			_la = _input.LA(1);
			if ( !(_la==MAP || _la==HASHMAP) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			{
			setState(250); match(LESS);
			setState(251); type();
			setState(252); match(COMMA);
			setState(253); type();
			setState(254); match(GREATER);
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

	public static class TypeContext extends ParserRuleContext {
		public ArrayTypeContext arrayType() {
			return getRuleContext(ArrayTypeContext.class,0);
		}
		public MapTypeContext mapType() {
			return getRuleContext(MapTypeContext.class,0);
		}
		public BaseTypeContext baseType() {
			return getRuleContext(BaseTypeContext.class,0);
		}
		public PairTypeContext pairType() {
			return getRuleContext(PairTypeContext.class,0);
		}
		public ListTypeContext listType() {
			return getRuleContext(ListTypeContext.class,0);
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
		enterRule(_localctx, 28, RULE_type);
		try {
			setState(261);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(256); baseType();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(257); arrayType();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(258); pairType();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(259); listType();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(260); mapType();
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
		enterRule(_localctx, 30, RULE_baseType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(263);
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
		public ListElemContext listElem() {
			return getRuleContext(ListElemContext.class,0);
		}
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
		int _startState = 32;
		enterRecursionRule(_localctx, 32, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(293);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				{
				setState(266);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MINUS) | (1L << NOT) | (1L << LEN) | (1L << ORD) | (1L << CHR))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				setState(267); expr(14);
				}
				break;
			case 2:
				{
				setState(268); ident();
				setState(269); match(PLUSPLUS);
				}
				break;
			case 3:
				{
				setState(271); ident();
				setState(272); match(MINUSMINUS);
				}
				break;
			case 4:
				{
				setState(274); match(OPEN_PARENTHESES);
				setState(275); expr(0);
				setState(276); match(CLOSE_PARENTHESES);
				}
				break;
			case 5:
				{
				setState(286);
				switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
				case 1:
					{
					setState(278); intLiter();
					}
					break;
				case 2:
					{
					setState(279); boolLiter();
					}
					break;
				case 3:
					{
					setState(280); charLiter();
					}
					break;
				case 4:
					{
					setState(281); stringLiter();
					}
					break;
				case 5:
					{
					setState(282); pairLiter();
					}
					break;
				case 6:
					{
					setState(283); ident();
					}
					break;
				case 7:
					{
					setState(284); arrayElem();
					}
					break;
				case 8:
					{
					setState(285); listElem();
					}
					break;
				}
				}
				break;
			case 6:
				{
				setState(291);
				switch (_input.LA(1)) {
				case BIN_LITER:
					{
					setState(288); binLiter();
					}
					break;
				case HEX_LITER:
					{
					setState(289); hexLiter();
					}
					break;
				case OCT_LITER:
					{
					setState(290); octLiter();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(321);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(319);
					switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
					case 1:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(295);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(296);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MUL) | (1L << DIV) | (1L << MOD))) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						consume();
						setState(297); expr(12);
						}
						break;
					case 2:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(298);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(299);
						_la = _input.LA(1);
						if ( !(_la==PLUS || _la==MINUS) ) {
						_errHandler.recoverInline(this);
						}
						consume();
						setState(300); expr(11);
						}
						break;
					case 3:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(301);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(302);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << GREATER_EQUAL) | (1L << GREATER) | (1L << LESS_EQUAL) | (1L << LESS))) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						consume();
						setState(303); expr(10);
						}
						break;
					case 4:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(304);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(305);
						_la = _input.LA(1);
						if ( !(_la==DOUBLE_EQUALS || _la==NOT_EQUAL) ) {
						_errHandler.recoverInline(this);
						}
						consume();
						setState(306); expr(9);
						}
						break;
					case 5:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(307);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(308); match(AND);
						setState(309); expr(8);
						}
						break;
					case 6:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(310);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(311); match(OR);
						setState(312); expr(7);
						}
						break;
					case 7:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(313);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(314); match(PLUSEQUAL);
						setState(315); expr(6);
						}
						break;
					case 8:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(316);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(317); match(MINUSEQUAL);
						setState(318); expr(5);
						}
						break;
					}
					} 
				}
				setState(323);
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
		enterRule(_localctx, 34, RULE_arrayElem);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(324); ident();
			setState(329); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(325); match(OPEN_SQUARE);
					setState(326); expr(0);
					setState(327); match(CLOSE_SQUARE);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(331); 
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

	public static class ListElemContext extends ParserRuleContext {
		public TerminalNode CLOSE_PARENTHESES() { return getToken(WACCParser.CLOSE_PARENTHESES, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public TerminalNode GET() { return getToken(WACCParser.GET, 0); }
		public TerminalNode OPEN_PARENTHESES() { return getToken(WACCParser.OPEN_PARENTHESES, 0); }
		public ListElemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_listElem; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitListElem(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ListElemContext listElem() throws RecognitionException {
		ListElemContext _localctx = new ListElemContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_listElem);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(333); ident();
			setState(334); match(GET);
			setState(335); match(OPEN_PARENTHESES);
			setState(336); expr(0);
			setState(337); match(CLOSE_PARENTHESES);
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
		enterRule(_localctx, 38, RULE_intLiter);
		int _la;
		try {
			setState(342);
			switch (_input.LA(1)) {
			case PLUS:
			case MINUS:
				enterOuterAlt(_localctx, 1);
				{
				setState(339);
				_la = _input.LA(1);
				if ( !(_la==PLUS || _la==MINUS) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				setState(340); match(INTEGER);
				}
				break;
			case INTEGER:
				enterOuterAlt(_localctx, 2);
				{
				setState(341); match(INTEGER);
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
		enterRule(_localctx, 40, RULE_boolLiter);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(344);
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
		enterRule(_localctx, 42, RULE_charLiter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(346); match(CHAR_LITER);
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
		enterRule(_localctx, 44, RULE_stringLiter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(348); match(STRING_LITER);
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
		enterRule(_localctx, 46, RULE_binLiter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(350); match(BIN_LITER);
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
		enterRule(_localctx, 48, RULE_hexLiter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(352); match(HEX_LITER);
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
		enterRule(_localctx, 50, RULE_octLiter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(354); match(OCT_LITER);
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
		enterRule(_localctx, 52, RULE_arrayLiter);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(356); match(OPEN_SQUARE);
			setState(365);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PLUS) | (1L << MINUS) | (1L << NOT) | (1L << LEN) | (1L << ORD) | (1L << CHR) | (1L << TRUE) | (1L << FALSE) | (1L << OPEN_PARENTHESES) | (1L << INTEGER) | (1L << BIN_LITER) | (1L << OCT_LITER) | (1L << HEX_LITER) | (1L << NULL))) != 0) || ((((_la - 76)) & ~0x3f) == 0 && ((1L << (_la - 76)) & ((1L << (STRING_LITER - 76)) | (1L << (IDENTITY - 76)) | (1L << (CHAR_LITER - 76)))) != 0)) {
				{
				setState(357); expr(0);
				setState(362);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(358); match(COMMA);
					{
					setState(359); expr(0);
					}
					}
					}
					setState(364);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(367); match(CLOSE_SQUARE);
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
		enterRule(_localctx, 54, RULE_pairLiter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(369); match(NULL);
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
		enterRule(_localctx, 56, RULE_ident);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(371); match(IDENTITY);
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
		case 16: return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean stat_sempred(StatContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0: return precpred(_ctx, 4);
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
		case 7: return precpred(_ctx, 5);
		case 8: return precpred(_ctx, 4);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3Z\u0178\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\3\2\3\2\7\2?\n\2\f"+
		"\2\16\2B\13\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\5\3L\n\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\4\3\4\3\4\7\4V\n\4\f\4\16\4Y\13\4\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\5\6\u009d\n\6\3\6\3\6\3\6\7\6\u00a2\n\6\f\6\16"+
		"\6\u00a5\13\6\3\7\3\7\3\7\3\7\5\7\u00ab\n\7\3\b\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\b\u00cd\n\b\3\b\3\b\5\b\u00d1\n\b\3"+
		"\t\3\t\3\t\7\t\u00d6\n\t\f\t\16\t\u00d9\13\t\3\n\3\n\3\n\3\n\3\n\3\n\3"+
		"\n\3\13\3\13\3\13\3\13\5\13\u00e6\n\13\3\f\3\f\3\f\5\f\u00eb\n\f\3\r\3"+
		"\r\5\r\u00ef\n\r\3\r\3\r\6\r\u00f3\n\r\r\r\16\r\u00f4\3\16\3\16\3\16\3"+
		"\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\5"+
		"\20\u0108\n\20\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\5\22\u0121"+
		"\n\22\3\22\3\22\3\22\5\22\u0126\n\22\5\22\u0128\n\22\3\22\3\22\3\22\3"+
		"\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3"+
		"\22\3\22\3\22\3\22\3\22\3\22\3\22\7\22\u0142\n\22\f\22\16\22\u0145\13"+
		"\22\3\23\3\23\3\23\3\23\3\23\6\23\u014c\n\23\r\23\16\23\u014d\3\24\3\24"+
		"\3\24\3\24\3\24\3\24\3\25\3\25\3\25\5\25\u0159\n\25\3\26\3\26\3\27\3\27"+
		"\3\30\3\30\3\31\3\31\3\32\3\32\3\33\3\33\3\34\3\34\3\34\3\34\7\34\u016b"+
		"\n\34\f\34\16\34\u016e\13\34\5\34\u0170\n\34\3\34\3\34\3\35\3\35\3\36"+
		"\3\36\3\36\2\4\n\"\37\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,."+
		"\60\62\64\668:\2\f\3\2!\"\3\2 \"\3\2&\'\3\2\33\36\4\2\4\4\23\26\3\2\5"+
		"\7\3\2\3\4\3\2\13\16\3\2\17\20\3\2\31\32\u019c\2<\3\2\2\2\4G\3\2\2\2\6"+
		"R\3\2\2\2\bZ\3\2\2\2\n\u009c\3\2\2\2\f\u00aa\3\2\2\2\16\u00d0\3\2\2\2"+
		"\20\u00d2\3\2\2\2\22\u00da\3\2\2\2\24\u00e5\3\2\2\2\26\u00ea\3\2\2\2\30"+
		"\u00ee\3\2\2\2\32\u00f6\3\2\2\2\34\u00fb\3\2\2\2\36\u0107\3\2\2\2 \u0109"+
		"\3\2\2\2\"\u0127\3\2\2\2$\u0146\3\2\2\2&\u014f\3\2\2\2(\u0158\3\2\2\2"+
		"*\u015a\3\2\2\2,\u015c\3\2\2\2.\u015e\3\2\2\2\60\u0160\3\2\2\2\62\u0162"+
		"\3\2\2\2\64\u0164\3\2\2\2\66\u0166\3\2\2\28\u0173\3\2\2\2:\u0175\3\2\2"+
		"\2<@\7\60\2\2=?\5\4\3\2>=\3\2\2\2?B\3\2\2\2@>\3\2\2\2@A\3\2\2\2AC\3\2"+
		"\2\2B@\3\2\2\2CD\5\n\6\2DE\7\61\2\2EF\7\2\2\3F\3\3\2\2\2GH\5\36\20\2H"+
		"I\5:\36\2IK\7(\2\2JL\5\6\4\2KJ\3\2\2\2KL\3\2\2\2LM\3\2\2\2MN\7)\2\2NO"+
		"\7\62\2\2OP\5\n\6\2PQ\7\61\2\2Q\5\3\2\2\2RW\5\b\5\2ST\7>\2\2TV\5\b\5\2"+
		"US\3\2\2\2VY\3\2\2\2WU\3\2\2\2WX\3\2\2\2X\7\3\2\2\2YW\3\2\2\2Z[\5\36\20"+
		"\2[\\\5:\36\2\\\t\3\2\2\2]^\b\6\1\2^\u009d\7\63\2\2_`\5\36\20\2`a\5:\36"+
		"\2ab\7\b\2\2bc\5\16\b\2c\u009d\3\2\2\2de\5\f\7\2ef\7\b\2\2fg\5\16\b\2"+
		"g\u009d\3\2\2\2hi\7\64\2\2i\u009d\5\f\7\2jk\7\65\2\2k\u009d\5\"\22\2l"+
		"m\7\66\2\2m\u009d\5\"\22\2no\7\67\2\2o\u009d\5\"\22\2pq\79\2\2q\u009d"+
		"\5\"\22\2rs\78\2\2s\u009d\5\"\22\2tu\7@\2\2uv\5\"\22\2vw\7A\2\2wx\5\n"+
		"\6\2xy\7B\2\2yz\5\n\6\2z{\7C\2\2{\u009d\3\2\2\2|}\7D\2\2}~\5\"\22\2~\177"+
		"\7F\2\2\177\u0080\5\n\6\2\u0080\u0081\7E\2\2\u0081\u009d\3\2\2\2\u0082"+
		"\u0083\7F\2\2\u0083\u0084\5\n\6\2\u0084\u0085\7D\2\2\u0085\u0086\5\"\22"+
		"\2\u0086\u0087\7E\2\2\u0087\u009d\3\2\2\2\u0088\u0089\7G\2\2\u0089\u008a"+
		"\5\n\6\2\u008a\u008b\7<\2\2\u008b\u008c\5\"\22\2\u008c\u008d\7<\2\2\u008d"+
		"\u008e\5\"\22\2\u008e\u008f\7F\2\2\u008f\u0090\5\n\6\2\u0090\u0091\7E"+
		"\2\2\u0091\u009d\3\2\2\2\u0092\u0093\7\60\2\2\u0093\u0094\5\n\6\2\u0094"+
		"\u0095\7\61\2\2\u0095\u009d\3\2\2\2\u0096\u0097\5:\36\2\u0097\u0098\7"+
		"%\2\2\u0098\u0099\5\"\22\2\u0099\u009d\3\2\2\2\u009a\u009d\7H\2\2\u009b"+
		"\u009d\7I\2\2\u009c]\3\2\2\2\u009c_\3\2\2\2\u009cd\3\2\2\2\u009ch\3\2"+
		"\2\2\u009cj\3\2\2\2\u009cl\3\2\2\2\u009cn\3\2\2\2\u009cp\3\2\2\2\u009c"+
		"r\3\2\2\2\u009ct\3\2\2\2\u009c|\3\2\2\2\u009c\u0082\3\2\2\2\u009c\u0088"+
		"\3\2\2\2\u009c\u0092\3\2\2\2\u009c\u0096\3\2\2\2\u009c\u009a\3\2\2\2\u009c"+
		"\u009b\3\2\2\2\u009d\u00a3\3\2\2\2\u009e\u009f\f\6\2\2\u009f\u00a0\7<"+
		"\2\2\u00a0\u00a2\5\n\6\7\u00a1\u009e\3\2\2\2\u00a2\u00a5\3\2\2\2\u00a3"+
		"\u00a1\3\2\2\2\u00a3\u00a4\3\2\2\2\u00a4\13\3\2\2\2\u00a5\u00a3\3\2\2"+
		"\2\u00a6\u00ab\5:\36\2\u00a7\u00ab\5$\23\2\u00a8\u00ab\5\24\13\2\u00a9"+
		"\u00ab\5&\24\2\u00aa\u00a6\3\2\2\2\u00aa\u00a7\3\2\2\2\u00aa\u00a8\3\2"+
		"\2\2\u00aa\u00a9\3\2\2\2\u00ab\r\3\2\2\2\u00ac\u00d1\5\"\22\2\u00ad\u00d1"+
		"\5\66\34\2\u00ae\u00af\7J\2\2\u00af\u00b0\7(\2\2\u00b0\u00b1\5\"\22\2"+
		"\u00b1\u00b2\7>\2\2\u00b2\u00b3\5\"\22\2\u00b3\u00b4\7)\2\2\u00b4\u00d1"+
		"\3\2\2\2\u00b5\u00b6\7#\2\2\u00b6\u00b7\t\2\2\2\u00b7\u00b8\7\16\2\2\u00b8"+
		"\u00b9\5\36\20\2\u00b9\u00ba\7\f\2\2\u00ba\u00bb\7(\2\2\u00bb\u00bc\7"+
		")\2\2\u00bc\u00d1\3\2\2\2\u00bd\u00be\7#\2\2\u00be\u00bf\7\'\2\2\u00bf"+
		"\u00c0\7\16\2\2\u00c0\u00c1\5\36\20\2\u00c1\u00c2\7>\2\2\u00c2\u00c3\5"+
		"\36\20\2\u00c3\u00c4\7\f\2\2\u00c4\u00c5\7(\2\2\u00c5\u00c6\7)\2\2\u00c6"+
		"\u00d1\3\2\2\2\u00c7\u00d1\5\24\13\2\u00c8\u00c9\7M\2\2\u00c9\u00ca\5"+
		":\36\2\u00ca\u00cc\7(\2\2\u00cb\u00cd\5\20\t\2\u00cc\u00cb\3\2\2\2\u00cc"+
		"\u00cd\3\2\2\2\u00cd\u00ce\3\2\2\2\u00ce\u00cf\7)\2\2\u00cf\u00d1\3\2"+
		"\2\2\u00d0\u00ac\3\2\2\2\u00d0\u00ad\3\2\2\2\u00d0\u00ae\3\2\2\2\u00d0"+
		"\u00b5\3\2\2\2\u00d0\u00bd\3\2\2\2\u00d0\u00c7\3\2\2\2\u00d0\u00c8\3\2"+
		"\2\2\u00d1\17\3\2\2\2\u00d2\u00d7\5\"\22\2\u00d3\u00d4\7>\2\2\u00d4\u00d6"+
		"\5\"\22\2\u00d5\u00d3\3\2\2\2\u00d6\u00d9\3\2\2\2\u00d7\u00d5\3\2\2\2"+
		"\u00d7\u00d8\3\2\2\2\u00d8\21\3\2\2\2\u00d9\u00d7\3\2\2\2\u00da\u00db"+
		"\7\37\2\2\u00db\u00dc\7(\2\2\u00dc\u00dd\5\26\f\2\u00dd\u00de\7>\2\2\u00de"+
		"\u00df\5\26\f\2\u00df\u00e0\7)\2\2\u00e0\23\3\2\2\2\u00e1\u00e2\7K\2\2"+
		"\u00e2\u00e6\5\"\22\2\u00e3\u00e4\7L\2\2\u00e4\u00e6\5\"\22\2\u00e5\u00e1"+
		"\3\2\2\2\u00e5\u00e3\3\2\2\2\u00e6\25\3\2\2\2\u00e7\u00eb\5 \21\2\u00e8"+
		"\u00eb\5\30\r\2\u00e9\u00eb\7\37\2\2\u00ea\u00e7\3\2\2\2\u00ea\u00e8\3"+
		"\2\2\2\u00ea\u00e9\3\2\2\2\u00eb\27\3\2\2\2\u00ec\u00ef\5 \21\2\u00ed"+
		"\u00ef\5\22\n\2\u00ee\u00ec\3\2\2\2\u00ee\u00ed\3\2\2\2\u00ef\u00f2\3"+
		"\2\2\2\u00f0\u00f1\7*\2\2\u00f1\u00f3\7+\2\2\u00f2\u00f0\3\2\2\2\u00f3"+
		"\u00f4\3\2\2\2\u00f4\u00f2\3\2\2\2\u00f4\u00f5\3\2\2\2\u00f5\31\3\2\2"+
		"\2\u00f6\u00f7\t\3\2\2\u00f7\u00f8\7\16\2\2\u00f8\u00f9\5\36\20\2\u00f9"+
		"\u00fa\7\f\2\2\u00fa\33\3\2\2\2\u00fb\u00fc\t\4\2\2\u00fc\u00fd\7\16\2"+
		"\2\u00fd\u00fe\5\36\20\2\u00fe\u00ff\7>\2\2\u00ff\u0100\5\36\20\2\u0100"+
		"\u0101\7\f\2\2\u0101\35\3\2\2\2\u0102\u0108\5 \21\2\u0103\u0108\5\30\r"+
		"\2\u0104\u0108\5\22\n\2\u0105\u0108\5\32\16\2\u0106\u0108\5\34\17\2\u0107"+
		"\u0102\3\2\2\2\u0107\u0103\3\2\2\2\u0107\u0104\3\2\2\2\u0107\u0105\3\2"+
		"\2\2\u0107\u0106\3\2\2\2\u0108\37\3\2\2\2\u0109\u010a\t\5\2\2\u010a!\3"+
		"\2\2\2\u010b\u010c\b\22\1\2\u010c\u010d\t\6\2\2\u010d\u0128\5\"\22\20"+
		"\u010e\u010f\5:\36\2\u010f\u0110\7\27\2\2\u0110\u0128\3\2\2\2\u0111\u0112"+
		"\5:\36\2\u0112\u0113\7\30\2\2\u0113\u0128\3\2\2\2\u0114\u0115\7(\2\2\u0115"+
		"\u0116\5\"\22\2\u0116\u0117\7)\2\2\u0117\u0128\3\2\2\2\u0118\u0121\5("+
		"\25\2\u0119\u0121\5*\26\2\u011a\u0121\5,\27\2\u011b\u0121\5.\30\2\u011c"+
		"\u0121\58\35\2\u011d\u0121\5:\36\2\u011e\u0121\5$\23\2\u011f\u0121\5&"+
		"\24\2\u0120\u0118\3\2\2\2\u0120\u0119\3\2\2\2\u0120\u011a\3\2\2\2\u0120"+
		"\u011b\3\2\2\2\u0120\u011c\3\2\2\2\u0120\u011d\3\2\2\2\u0120\u011e\3\2"+
		"\2\2\u0120\u011f\3\2\2\2\u0121\u0128\3\2\2\2\u0122\u0126\5\60\31\2\u0123"+
		"\u0126\5\62\32\2\u0124\u0126\5\64\33\2\u0125\u0122\3\2\2\2\u0125\u0123"+
		"\3\2\2\2\u0125\u0124\3\2\2\2\u0126\u0128\3\2\2\2\u0127\u010b\3\2\2\2\u0127"+
		"\u010e\3\2\2\2\u0127\u0111\3\2\2\2\u0127\u0114\3\2\2\2\u0127\u0120\3\2"+
		"\2\2\u0127\u0125\3\2\2\2\u0128\u0143\3\2\2\2\u0129\u012a\f\r\2\2\u012a"+
		"\u012b\t\7\2\2\u012b\u0142\5\"\22\16\u012c\u012d\f\f\2\2\u012d\u012e\t"+
		"\b\2\2\u012e\u0142\5\"\22\r\u012f\u0130\f\13\2\2\u0130\u0131\t\t\2\2\u0131"+
		"\u0142\5\"\22\f\u0132\u0133\f\n\2\2\u0133\u0134\t\n\2\2\u0134\u0142\5"+
		"\"\22\13\u0135\u0136\f\t\2\2\u0136\u0137\7\21\2\2\u0137\u0142\5\"\22\n"+
		"\u0138\u0139\f\b\2\2\u0139\u013a\7\22\2\2\u013a\u0142\5\"\22\t\u013b\u013c"+
		"\f\7\2\2\u013c\u013d\7\t\2\2\u013d\u0142\5\"\22\b\u013e\u013f\f\6\2\2"+
		"\u013f\u0140\7\n\2\2\u0140\u0142\5\"\22\7\u0141\u0129\3\2\2\2\u0141\u012c"+
		"\3\2\2\2\u0141\u012f\3\2\2\2\u0141\u0132\3\2\2\2\u0141\u0135\3\2\2\2\u0141"+
		"\u0138\3\2\2\2\u0141\u013b\3\2\2\2\u0141\u013e\3\2\2\2\u0142\u0145\3\2"+
		"\2\2\u0143\u0141\3\2\2\2\u0143\u0144\3\2\2\2\u0144#\3\2\2\2\u0145\u0143"+
		"\3\2\2\2\u0146\u014b\5:\36\2\u0147\u0148\7*\2\2\u0148\u0149\5\"\22\2\u0149"+
		"\u014a\7+\2\2\u014a\u014c\3\2\2\2\u014b\u0147\3\2\2\2\u014c\u014d\3\2"+
		"\2\2\u014d\u014b\3\2\2\2\u014d\u014e\3\2\2\2\u014e%\3\2\2\2\u014f\u0150"+
		"\5:\36\2\u0150\u0151\7$\2\2\u0151\u0152\7(\2\2\u0152\u0153\5\"\22\2\u0153"+
		"\u0154\7)\2\2\u0154\'\3\2\2\2\u0155\u0156\t\b\2\2\u0156\u0159\7,\2\2\u0157"+
		"\u0159\7,\2\2\u0158\u0155\3\2\2\2\u0158\u0157\3\2\2\2\u0159)\3\2\2\2\u015a"+
		"\u015b\t\13\2\2\u015b+\3\2\2\2\u015c\u015d\7Z\2\2\u015d-\3\2\2\2\u015e"+
		"\u015f\7N\2\2\u015f/\3\2\2\2\u0160\u0161\7-\2\2\u0161\61\3\2\2\2\u0162"+
		"\u0163\7/\2\2\u0163\63\3\2\2\2\u0164\u0165\7.\2\2\u0165\65\3\2\2\2\u0166"+
		"\u016f\7*\2\2\u0167\u016c\5\"\22\2\u0168\u0169\7>\2\2\u0169\u016b\5\""+
		"\22\2\u016a\u0168\3\2\2\2\u016b\u016e\3\2\2\2\u016c\u016a\3\2\2\2\u016c"+
		"\u016d\3\2\2\2\u016d\u0170\3\2\2\2\u016e\u016c\3\2\2\2\u016f\u0167\3\2"+
		"\2\2\u016f\u0170\3\2\2\2\u0170\u0171\3\2\2\2\u0171\u0172\7+\2\2\u0172"+
		"\67\3\2\2\2\u0173\u0174\7:\2\2\u01749\3\2\2\2\u0175\u0176\7Y\2\2\u0176"+
		";\3\2\2\2\31@KW\u009c\u00a3\u00aa\u00cc\u00d0\u00d7\u00e5\u00ea\u00ee"+
		"\u00f4\u0107\u0120\u0125\u0127\u0141\u0143\u014d\u0158\u016c\u016f";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}