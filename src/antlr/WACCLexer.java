// Generated from ./WACCLexer.g4 by ANTLR 4.4
package antlr;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class WACCLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.4", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		PLUS=1, MINUS=2, MUL=3, DIV=4, MOD=5, EQUAL=6, GREATER_EQUAL=7, GREATER=8, 
		LESS_EQUAL=9, LESS=10, DOUBLE_EQUALS=11, NOT_EQUAL=12, AND=13, OR=14, 
		NOT=15, LEN=16, ORD=17, CHR=18, TRUE=19, FALSE=20, INT=21, BOOL=22, CHAR=23, 
		STRING=24, PAIR=25, OPEN_PARENTHESES=26, CLOSE_PARENTHESES=27, OPEN_SQUARE=28, 
		CLOSE_SQUARE=29, INTEGER=30, BEGIN=31, END=32, IS=33, SKIP=34, READ=35, 
		FREE=36, RETURN=37, EXIT=38, PRINTLN=39, PRINT=40, NULL=41, COMMENT=42, 
		SEMI_COLON=43, COLON=44, COMMA=45, HASH_KEY=46, IF=47, THEN=48, ELSE=49, 
		ENDIF=50, WHILE=51, DONE=52, DO=53, FOR=54, BREAK=55, CONTINUE=56, NEWPAIR=57, 
		FST=58, SND=59, CALL=60, STRING_LITER=61, WS=62, END_OF_STRING=63, NEWLINE=64, 
		TAB=65, CARRIAGE_RETURN=66, FORM_FEED=67, DOUBLE_QUOTES=68, BACKSLASH=69, 
		WHITESPACE=70, APOSTROPHE=71, IDENTITY=72, CHAR_LITER=73;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"'\\u0000'", "'\\u0001'", "'\\u0002'", "'\\u0003'", "'\\u0004'", "'\\u0005'", 
		"'\\u0006'", "'\\u0007'", "'\b'", "'\t'", "'\n'", "'\\u000B'", "'\f'", 
		"'\r'", "'\\u000E'", "'\\u000F'", "'\\u0010'", "'\\u0011'", "'\\u0012'", 
		"'\\u0013'", "'\\u0014'", "'\\u0015'", "'\\u0016'", "'\\u0017'", "'\\u0018'", 
		"'\\u0019'", "'\\u001A'", "'\\u001B'", "'\\u001C'", "'\\u001D'", "'\\u001E'", 
		"'\\u001F'", "' '", "'!'", "'\"'", "'#'", "'$'", "'%'", "'&'", "'''", 
		"'('", "')'", "'*'", "'+'", "','", "'-'", "'.'", "'/'", "'0'", "'1'", 
		"'2'", "'3'", "'4'", "'5'", "'6'", "'7'", "'8'", "'9'", "':'", "';'", 
		"'<'", "'='", "'>'", "'?'", "'@'", "'A'", "'B'", "'C'", "'D'", "'E'", 
		"'F'", "'G'", "'H'", "'I'"
	};
	public static final String[] ruleNames = {
		"PLUS", "MINUS", "MUL", "DIV", "MOD", "EQUAL", "GREATER_EQUAL", "GREATER", 
		"LESS_EQUAL", "LESS", "DOUBLE_EQUALS", "NOT_EQUAL", "AND", "OR", "NOT", 
		"LEN", "ORD", "CHR", "TRUE", "FALSE", "INT", "BOOL", "CHAR", "STRING", 
		"PAIR", "OPEN_PARENTHESES", "CLOSE_PARENTHESES", "OPEN_SQUARE", "CLOSE_SQUARE", 
		"DIGIT", "INTEGER", "BEGIN", "END", "IS", "SKIP", "READ", "FREE", "RETURN", 
		"EXIT", "PRINTLN", "PRINT", "NULL", "COMMENT", "SEMI_COLON", "COLON", 
		"COMMA", "HASH_KEY", "IF", "THEN", "ELSE", "ENDIF", "WHILE", "DONE", "DO", 
		"FOR", "BREAK", "CONTINUE", "NEWPAIR", "FST", "SND", "CALL", "STRING_LITER", 
		"WS", "END_OF_STRING", "NEWLINE", "TAB", "CARRIAGE_RETURN", "FORM_FEED", 
		"DOUBLE_QUOTES", "BACKSLASH", "WHITESPACE", "APOSTROPHE", "ANY_CHAR", 
		"ID_BEGIN_CHAR", "ID_CHAR", "IDENTITY", "CHAR_LITER"
	};


	public WACCLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "WACCLexer.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2K\u01e3\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\4G\tG\4H\tH\4I"+
		"\tI\4J\tJ\4K\tK\4L\tL\4M\tM\4N\tN\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6"+
		"\3\6\3\7\3\7\3\b\3\b\3\b\3\t\3\t\3\n\3\n\3\n\3\13\3\13\3\f\3\f\3\f\3\r"+
		"\3\r\3\r\3\16\3\16\3\16\3\17\3\17\3\17\3\20\3\20\3\21\3\21\3\21\3\21\3"+
		"\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\25\3"+
		"\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3"+
		"\30\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3"+
		"\32\3\32\3\32\3\33\3\33\3\34\3\34\3\35\3\35\3\36\3\36\3\37\3\37\3 \6 "+
		"\u00fe\n \r \16 \u00ff\3!\3!\3!\3!\3!\3!\3\"\3\"\3\"\3\"\3#\3#\3#\3$\3"+
		"$\3$\3$\3$\3%\3%\3%\3%\3%\3&\3&\3&\3&\3&\3\'\3\'\3\'\3\'\3\'\3\'\3\'\3"+
		"(\3(\3(\3(\3(\3)\3)\3)\3)\3)\3)\3)\3)\3*\3*\3*\3*\3*\3*\3+\3+\3+\3+\3"+
		"+\3,\3,\7,\u013f\n,\f,\16,\u0142\13,\3,\3,\3,\3,\3-\3-\3.\3.\3/\3/\3\60"+
		"\3\60\3\61\3\61\3\61\3\62\3\62\3\62\3\62\3\62\3\63\3\63\3\63\3\63\3\63"+
		"\3\64\3\64\3\64\3\65\3\65\3\65\3\65\3\65\3\65\3\66\3\66\3\66\3\66\3\66"+
		"\3\67\3\67\3\67\38\38\38\38\39\39\39\39\39\39\3:\3:\3:\3:\3:\3:\3:\3:"+
		"\3:\3;\3;\3;\3;\3;\3;\3;\3;\3<\3<\3<\3<\3=\3=\3=\3=\3>\3>\3>\3>\3>\3?"+
		"\3?\7?\u0198\n?\f?\16?\u019b\13?\3?\7?\u019e\n?\f?\16?\u01a1\13?\5?\u01a3"+
		"\n?\3?\3?\3@\6@\u01a8\n@\r@\16@\u01a9\3@\3@\3A\3A\3A\3B\3B\3B\3C\3C\3"+
		"C\3D\3D\3D\3E\3E\3E\3F\3F\3F\3G\3G\3H\3H\3I\3I\3J\3J\3J\3J\3J\3J\3J\3"+
		"J\3J\3J\3J\5J\u01d1\nJ\3K\3K\3L\3L\5L\u01d7\nL\3M\3M\7M\u01db\nM\fM\16"+
		"M\u01de\13M\3N\3N\3N\3N\4\u0140\u0199\2O\3\3\5\4\7\5\t\6\13\7\r\b\17\t"+
		"\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27"+
		"-\30/\31\61\32\63\33\65\34\67\359\36;\37=\2? A!C\"E#G$I%K&M\'O(Q)S*U+"+
		"W,Y-[.]/_\60a\61c\62e\63g\64i\65k\66m\67o8q9s:u;w<y={>}?\177@\u0081A\u0083"+
		"B\u0085C\u0087D\u0089E\u008bF\u008dG\u008fH\u0091I\u0093\2\u0095\2\u0097"+
		"\2\u0099J\u009bK\3\2\5\5\2\13\f\17\17\"\"\5\2$$))^^\5\2C\\aac|\u01ef\2"+
		"\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2"+
		"\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2"+
		"\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2"+
		"\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2"+
		"\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2"+
		"\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2"+
		"\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W"+
		"\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2"+
		"\2\2\2e\3\2\2\2\2g\3\2\2\2\2i\3\2\2\2\2k\3\2\2\2\2m\3\2\2\2\2o\3\2\2\2"+
		"\2q\3\2\2\2\2s\3\2\2\2\2u\3\2\2\2\2w\3\2\2\2\2y\3\2\2\2\2{\3\2\2\2\2}"+
		"\3\2\2\2\2\177\3\2\2\2\2\u0081\3\2\2\2\2\u0083\3\2\2\2\2\u0085\3\2\2\2"+
		"\2\u0087\3\2\2\2\2\u0089\3\2\2\2\2\u008b\3\2\2\2\2\u008d\3\2\2\2\2\u008f"+
		"\3\2\2\2\2\u0091\3\2\2\2\2\u0099\3\2\2\2\2\u009b\3\2\2\2\3\u009d\3\2\2"+
		"\2\5\u009f\3\2\2\2\7\u00a1\3\2\2\2\t\u00a3\3\2\2\2\13\u00a5\3\2\2\2\r"+
		"\u00a7\3\2\2\2\17\u00a9\3\2\2\2\21\u00ac\3\2\2\2\23\u00ae\3\2\2\2\25\u00b1"+
		"\3\2\2\2\27\u00b3\3\2\2\2\31\u00b6\3\2\2\2\33\u00b9\3\2\2\2\35\u00bc\3"+
		"\2\2\2\37\u00bf\3\2\2\2!\u00c1\3\2\2\2#\u00c5\3\2\2\2%\u00c9\3\2\2\2\'"+
		"\u00cd\3\2\2\2)\u00d2\3\2\2\2+\u00d8\3\2\2\2-\u00dc\3\2\2\2/\u00e1\3\2"+
		"\2\2\61\u00e6\3\2\2\2\63\u00ed\3\2\2\2\65\u00f2\3\2\2\2\67\u00f4\3\2\2"+
		"\29\u00f6\3\2\2\2;\u00f8\3\2\2\2=\u00fa\3\2\2\2?\u00fd\3\2\2\2A\u0101"+
		"\3\2\2\2C\u0107\3\2\2\2E\u010b\3\2\2\2G\u010e\3\2\2\2I\u0113\3\2\2\2K"+
		"\u0118\3\2\2\2M\u011d\3\2\2\2O\u0124\3\2\2\2Q\u0129\3\2\2\2S\u0131\3\2"+
		"\2\2U\u0137\3\2\2\2W\u013c\3\2\2\2Y\u0147\3\2\2\2[\u0149\3\2\2\2]\u014b"+
		"\3\2\2\2_\u014d\3\2\2\2a\u014f\3\2\2\2c\u0152\3\2\2\2e\u0157\3\2\2\2g"+
		"\u015c\3\2\2\2i\u015f\3\2\2\2k\u0165\3\2\2\2m\u016a\3\2\2\2o\u016d\3\2"+
		"\2\2q\u0171\3\2\2\2s\u0177\3\2\2\2u\u0180\3\2\2\2w\u0188\3\2\2\2y\u018c"+
		"\3\2\2\2{\u0190\3\2\2\2}\u0195\3\2\2\2\177\u01a7\3\2\2\2\u0081\u01ad\3"+
		"\2\2\2\u0083\u01b0\3\2\2\2\u0085\u01b3\3\2\2\2\u0087\u01b6\3\2\2\2\u0089"+
		"\u01b9\3\2\2\2\u008b\u01bc\3\2\2\2\u008d\u01bf\3\2\2\2\u008f\u01c1\3\2"+
		"\2\2\u0091\u01c3\3\2\2\2\u0093\u01d0\3\2\2\2\u0095\u01d2\3\2\2\2\u0097"+
		"\u01d6\3\2\2\2\u0099\u01d8\3\2\2\2\u009b\u01df\3\2\2\2\u009d\u009e\7-"+
		"\2\2\u009e\4\3\2\2\2\u009f\u00a0\7/\2\2\u00a0\6\3\2\2\2\u00a1\u00a2\7"+
		",\2\2\u00a2\b\3\2\2\2\u00a3\u00a4\7\61\2\2\u00a4\n\3\2\2\2\u00a5\u00a6"+
		"\7\'\2\2\u00a6\f\3\2\2\2\u00a7\u00a8\7?\2\2\u00a8\16\3\2\2\2\u00a9\u00aa"+
		"\7@\2\2\u00aa\u00ab\7?\2\2\u00ab\20\3\2\2\2\u00ac\u00ad\7@\2\2\u00ad\22"+
		"\3\2\2\2\u00ae\u00af\7>\2\2\u00af\u00b0\7?\2\2\u00b0\24\3\2\2\2\u00b1"+
		"\u00b2\7>\2\2\u00b2\26\3\2\2\2\u00b3\u00b4\7?\2\2\u00b4\u00b5\7?\2\2\u00b5"+
		"\30\3\2\2\2\u00b6\u00b7\7#\2\2\u00b7\u00b8\7?\2\2\u00b8\32\3\2\2\2\u00b9"+
		"\u00ba\7(\2\2\u00ba\u00bb\7(\2\2\u00bb\34\3\2\2\2\u00bc\u00bd\7~\2\2\u00bd"+
		"\u00be\7~\2\2\u00be\36\3\2\2\2\u00bf\u00c0\7#\2\2\u00c0 \3\2\2\2\u00c1"+
		"\u00c2\7n\2\2\u00c2\u00c3\7g\2\2\u00c3\u00c4\7p\2\2\u00c4\"\3\2\2\2\u00c5"+
		"\u00c6\7q\2\2\u00c6\u00c7\7t\2\2\u00c7\u00c8\7f\2\2\u00c8$\3\2\2\2\u00c9"+
		"\u00ca\7e\2\2\u00ca\u00cb\7j\2\2\u00cb\u00cc\7t\2\2\u00cc&\3\2\2\2\u00cd"+
		"\u00ce\7v\2\2\u00ce\u00cf\7t\2\2\u00cf\u00d0\7w\2\2\u00d0\u00d1\7g\2\2"+
		"\u00d1(\3\2\2\2\u00d2\u00d3\7h\2\2\u00d3\u00d4\7c\2\2\u00d4\u00d5\7n\2"+
		"\2\u00d5\u00d6\7u\2\2\u00d6\u00d7\7g\2\2\u00d7*\3\2\2\2\u00d8\u00d9\7"+
		"k\2\2\u00d9\u00da\7p\2\2\u00da\u00db\7v\2\2\u00db,\3\2\2\2\u00dc\u00dd"+
		"\7d\2\2\u00dd\u00de\7q\2\2\u00de\u00df\7q\2\2\u00df\u00e0\7n\2\2\u00e0"+
		".\3\2\2\2\u00e1\u00e2\7e\2\2\u00e2\u00e3\7j\2\2\u00e3\u00e4\7c\2\2\u00e4"+
		"\u00e5\7t\2\2\u00e5\60\3\2\2\2\u00e6\u00e7\7u\2\2\u00e7\u00e8\7v\2\2\u00e8"+
		"\u00e9\7t\2\2\u00e9\u00ea\7k\2\2\u00ea\u00eb\7p\2\2\u00eb\u00ec\7i\2\2"+
		"\u00ec\62\3\2\2\2\u00ed\u00ee\7r\2\2\u00ee\u00ef\7c\2\2\u00ef\u00f0\7"+
		"k\2\2\u00f0\u00f1\7t\2\2\u00f1\64\3\2\2\2\u00f2\u00f3\7*\2\2\u00f3\66"+
		"\3\2\2\2\u00f4\u00f5\7+\2\2\u00f58\3\2\2\2\u00f6\u00f7\7]\2\2\u00f7:\3"+
		"\2\2\2\u00f8\u00f9\7_\2\2\u00f9<\3\2\2\2\u00fa\u00fb\4\62;\2\u00fb>\3"+
		"\2\2\2\u00fc\u00fe\5=\37\2\u00fd\u00fc\3\2\2\2\u00fe\u00ff\3\2\2\2\u00ff"+
		"\u00fd\3\2\2\2\u00ff\u0100\3\2\2\2\u0100@\3\2\2\2\u0101\u0102\7d\2\2\u0102"+
		"\u0103\7g\2\2\u0103\u0104\7i\2\2\u0104\u0105\7k\2\2\u0105\u0106\7p\2\2"+
		"\u0106B\3\2\2\2\u0107\u0108\7g\2\2\u0108\u0109\7p\2\2\u0109\u010a\7f\2"+
		"\2\u010aD\3\2\2\2\u010b\u010c\7k\2\2\u010c\u010d\7u\2\2\u010dF\3\2\2\2"+
		"\u010e\u010f\7u\2\2\u010f\u0110\7m\2\2\u0110\u0111\7k\2\2\u0111\u0112"+
		"\7r\2\2\u0112H\3\2\2\2\u0113\u0114\7t\2\2\u0114\u0115\7g\2\2\u0115\u0116"+
		"\7c\2\2\u0116\u0117\7f\2\2\u0117J\3\2\2\2\u0118\u0119\7h\2\2\u0119\u011a"+
		"\7t\2\2\u011a\u011b\7g\2\2\u011b\u011c\7g\2\2\u011cL\3\2\2\2\u011d\u011e"+
		"\7t\2\2\u011e\u011f\7g\2\2\u011f\u0120\7v\2\2\u0120\u0121\7w\2\2\u0121"+
		"\u0122\7t\2\2\u0122\u0123\7p\2\2\u0123N\3\2\2\2\u0124\u0125\7g\2\2\u0125"+
		"\u0126\7z\2\2\u0126\u0127\7k\2\2\u0127\u0128\7v\2\2\u0128P\3\2\2\2\u0129"+
		"\u012a\7r\2\2\u012a\u012b\7t\2\2\u012b\u012c\7k\2\2\u012c\u012d\7p\2\2"+
		"\u012d\u012e\7v\2\2\u012e\u012f\7n\2\2\u012f\u0130\7p\2\2\u0130R\3\2\2"+
		"\2\u0131\u0132\7r\2\2\u0132\u0133\7t\2\2\u0133\u0134\7k\2\2\u0134\u0135"+
		"\7p\2\2\u0135\u0136\7v\2\2\u0136T\3\2\2\2\u0137\u0138\7p\2\2\u0138\u0139"+
		"\7w\2\2\u0139\u013a\7n\2\2\u013a\u013b\7n\2\2\u013bV\3\2\2\2\u013c\u0140"+
		"\7%\2\2\u013d\u013f\13\2\2\2\u013e\u013d\3\2\2\2\u013f\u0142\3\2\2\2\u0140"+
		"\u0141\3\2\2\2\u0140\u013e\3\2\2\2\u0141\u0143\3\2\2\2\u0142\u0140\3\2"+
		"\2\2\u0143\u0144\7\f\2\2\u0144\u0145\3\2\2\2\u0145\u0146\b,\2\2\u0146"+
		"X\3\2\2\2\u0147\u0148\7=\2\2\u0148Z\3\2\2\2\u0149\u014a\7<\2\2\u014a\\"+
		"\3\2\2\2\u014b\u014c\7.\2\2\u014c^\3\2\2\2\u014d\u014e\7%\2\2\u014e`\3"+
		"\2\2\2\u014f\u0150\7k\2\2\u0150\u0151\7h\2\2\u0151b\3\2\2\2\u0152\u0153"+
		"\7v\2\2\u0153\u0154\7j\2\2\u0154\u0155\7g\2\2\u0155\u0156\7p\2\2\u0156"+
		"d\3\2\2\2\u0157\u0158\7g\2\2\u0158\u0159\7n\2\2\u0159\u015a\7u\2\2\u015a"+
		"\u015b\7g\2\2\u015bf\3\2\2\2\u015c\u015d\7h\2\2\u015d\u015e\7k\2\2\u015e"+
		"h\3\2\2\2\u015f\u0160\7y\2\2\u0160\u0161\7j\2\2\u0161\u0162\7k\2\2\u0162"+
		"\u0163\7n\2\2\u0163\u0164\7g\2\2\u0164j\3\2\2\2\u0165\u0166\7f\2\2\u0166"+
		"\u0167\7q\2\2\u0167\u0168\7p\2\2\u0168\u0169\7g\2\2\u0169l\3\2\2\2\u016a"+
		"\u016b\7f\2\2\u016b\u016c\7q\2\2\u016cn\3\2\2\2\u016d\u016e\7h\2\2\u016e"+
		"\u016f\7q\2\2\u016f\u0170\7t\2\2\u0170p\3\2\2\2\u0171\u0172\7d\2\2\u0172"+
		"\u0173\7t\2\2\u0173\u0174\7g\2\2\u0174\u0175\7c\2\2\u0175\u0176\7m\2\2"+
		"\u0176r\3\2\2\2\u0177\u0178\7e\2\2\u0178\u0179\7q\2\2\u0179\u017a\7p\2"+
		"\2\u017a\u017b\7v\2\2\u017b\u017c\7k\2\2\u017c\u017d\7p\2\2\u017d\u017e"+
		"\7w\2\2\u017e\u017f\7g\2\2\u017ft\3\2\2\2\u0180\u0181\7p\2\2\u0181\u0182"+
		"\7g\2\2\u0182\u0183\7y\2\2\u0183\u0184\7r\2\2\u0184\u0185\7c\2\2\u0185"+
		"\u0186\7k\2\2\u0186\u0187\7t\2\2\u0187v\3\2\2\2\u0188\u0189\7h\2\2\u0189"+
		"\u018a\7u\2\2\u018a\u018b\7v\2\2\u018bx\3\2\2\2\u018c\u018d\7u\2\2\u018d"+
		"\u018e\7p\2\2\u018e\u018f\7f\2\2\u018fz\3\2\2\2\u0190\u0191\7e\2\2\u0191"+
		"\u0192\7c\2\2\u0192\u0193\7n\2\2\u0193\u0194\7n\2\2\u0194|\3\2\2\2\u0195"+
		"\u01a2\7$\2\2\u0196\u0198\13\2\2\2\u0197\u0196\3\2\2\2\u0198\u019b\3\2"+
		"\2\2\u0199\u019a\3\2\2\2\u0199\u0197\3\2\2\2\u019a\u01a3\3\2\2\2\u019b"+
		"\u0199\3\2\2\2\u019c\u019e\5\u0093J\2\u019d\u019c\3\2\2\2\u019e\u01a1"+
		"\3\2\2\2\u019f\u019d\3\2\2\2\u019f\u01a0\3\2\2\2\u01a0\u01a3\3\2\2\2\u01a1"+
		"\u019f\3\2\2\2\u01a2\u0199\3\2\2\2\u01a2\u019f\3\2\2\2\u01a3\u01a4\3\2"+
		"\2\2\u01a4\u01a5\7$\2\2\u01a5~\3\2\2\2\u01a6\u01a8\t\2\2\2\u01a7\u01a6"+
		"\3\2\2\2\u01a8\u01a9\3\2\2\2\u01a9\u01a7\3\2\2\2\u01a9\u01aa\3\2\2\2\u01aa"+
		"\u01ab\3\2\2\2\u01ab\u01ac\b@\2\2\u01ac\u0080\3\2\2\2\u01ad\u01ae\7^\2"+
		"\2\u01ae\u01af\7\62\2\2\u01af\u0082\3\2\2\2\u01b0\u01b1\7^\2\2\u01b1\u01b2"+
		"\7p\2\2\u01b2\u0084\3\2\2\2\u01b3\u01b4\7^\2\2\u01b4\u01b5\7v\2\2\u01b5"+
		"\u0086\3\2\2\2\u01b6\u01b7\7^\2\2\u01b7\u01b8\7t\2\2\u01b8\u0088\3\2\2"+
		"\2\u01b9\u01ba\7^\2\2\u01ba\u01bb\7h\2\2\u01bb\u008a\3\2\2\2\u01bc\u01bd"+
		"\7^\2\2\u01bd\u01be\7$\2\2\u01be\u008c\3\2\2\2\u01bf\u01c0\7^\2\2\u01c0"+
		"\u008e\3\2\2\2\u01c1\u01c2\7\"\2\2\u01c2\u0090\3\2\2\2\u01c3\u01c4\7)"+
		"\2\2\u01c4\u0092\3\2\2\2\u01c5\u01d1\n\3\2\2\u01c6\u01d1\5\u0081A\2\u01c7"+
		"\u01d1\5\u0083B\2\u01c8\u01d1\5\u0085C\2\u01c9\u01d1\5\u0087D\2\u01ca"+
		"\u01d1\5\u0089E\2\u01cb\u01d1\5\u008bF\2\u01cc\u01d1\5\u008dG\2\u01cd"+
		"\u01d1\5\u008fH\2\u01ce\u01cf\7^\2\2\u01cf\u01d1\7)\2\2\u01d0\u01c5\3"+
		"\2\2\2\u01d0\u01c6\3\2\2\2\u01d0\u01c7\3\2\2\2\u01d0\u01c8\3\2\2\2\u01d0"+
		"\u01c9\3\2\2\2\u01d0\u01ca\3\2\2\2\u01d0\u01cb\3\2\2\2\u01d0\u01cc\3\2"+
		"\2\2\u01d0\u01cd\3\2\2\2\u01d0\u01ce\3\2\2\2\u01d1\u0094\3\2\2\2\u01d2"+
		"\u01d3\t\4\2\2\u01d3\u0096\3\2\2\2\u01d4\u01d7\5\u0095K\2\u01d5\u01d7"+
		"\4\62;\2\u01d6\u01d4\3\2\2\2\u01d6\u01d5\3\2\2\2\u01d7\u0098\3\2\2\2\u01d8"+
		"\u01dc\5\u0095K\2\u01d9\u01db\5\u0097L\2\u01da\u01d9\3\2\2\2\u01db\u01de"+
		"\3\2\2\2\u01dc\u01da\3\2\2\2\u01dc\u01dd\3\2\2\2\u01dd\u009a\3\2\2\2\u01de"+
		"\u01dc\3\2\2\2\u01df\u01e0\5\u0091I\2\u01e0\u01e1\5\u0093J\2\u01e1\u01e2"+
		"\5\u0091I\2\u01e2\u009c\3\2\2\2\f\2\u00ff\u0140\u0199\u019f\u01a2\u01a9"+
		"\u01d0\u01d6\u01dc\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}