lexer grammar WACCLexer;

//binary integer operators
PLUS: '+' ;
MINUS: '-' ;
MUL: '*' ;
DIV: '/' ;
MOD: '%' ;
EQUAL: '=' ;
PLUSEQUAL: '+=' ;
MINUSEQUAL: '-=' ;

//relational-operator
GREATER_EQUAL: '>=' ;
GREATER: '>' ;
LESS_EQUAL: '<=' ;
LESS: '<' ;
DOUBLE_EQUALS: '==' ;
NOT_EQUAL: '!=' ;

//conditional-operators
AND: '&&' ;
OR: '||' ;

//unary-boolean-operators
NOT: '!' ;

//unary-operators
LEN: 'len' ;
ORD: 'ord' ;
CHR: 'chr' ;
PLUSPLUS: '++';
MINUSMINUS: '--';

//boolean-literals
TRUE: 'true' ;
FALSE: 'false' ;

//BASE TYPES
INT: 'int' ;
BOOL: 'bool' ;
CHAR: 'char' ;
STRING: 'string' ;
PAIR: 'pair' ;

//LIST TYPES
LIST: 'list' ;
LINKEDLST: 'linkedList' ;
ARRAYLIST: 'arrayList' ;
NEW: 'new' ;
GET: 'get' ;
ADD: 'add' ;

//MAP TYPES
MAP: 'map' ;
HASHMAP: 'hashMap' ;

//brackets
OPEN_PARENTHESES : '(' ;
CLOSE_PARENTHESES : ')' ;
OPEN_SQUARE: '[' ;
CLOSE_SQUARE: ']' ;

//numbers
fragment DIGIT : '0'..'9' ;
fragment SIGN : ( PLUS | MINUS );
INTEGER: SIGN? DIGIT+ ;

fragment BINARY : [0-1] ;
BIN_LITER: '0b' SIGN? BINARY+ ;

fragment OCTAL : [0-7] ;
OCT_LITER: '0o' SIGN? OCTAL+ ;

fragment HEXADECIMAL : [0-9A-F] ;
HEX_LITER: '0h' SIGN? HEXADECIMAL+ ;

//program keywords
BEGIN : 'begin' ;
END : 'end' ;

//function keywords
IS : 'is' ;

//statement keywords
SKIP : 'skip' ;
READ: 'read' ;
FREE: 'free' ;
RETURN: 'return' ;
EXIT: 'exit' ;
PRINTLN: 'println' ;
PRINT: 'print' ;
NULL: 'null' ;

//comments
COMMENT : '#' .*? '\n' -> skip ;

//punctuation
SEMI_COLON: ';' ;
COLON: ':' ;
COMMA: ',' ;
HASH_KEY: '#' ;

//if then else
IF: 'if' ;
THEN: 'then' ;
ELSE: 'else' ;
ENDIF: 'fi' ;

//while loop
WHILE: 'while' ;
DONE: 'done' ;
DO: 'do' ;

//for loop
FOR: 'for' ;

//break and continue
BREAK: 'break' ;
CONTINUE: 'continues' ;


//pairs
NEWPAIR: 'newpair' ;
FST: 'fst' ;
SND: 'snd' ;

//function call
CALL: 'call' ;

//string literal
STRING_LITER : '"' (.*? | ANY_CHAR*) '"';

//whitespace
WS : [ \t\r\n]+ -> skip ;

//escaped characters
END_OF_STRING : '\\0' ;
NEWLINE : '\\n' ;
TAB : '\\t' ;
CARRIAGE_RETURN : '\\r';
FORM_FEED : '\\f';
DOUBLE_QUOTES : '\\"' ;
BACKSLASH : '\\' ;
WHITESPACE : ' ' ;
APOSTROPHE: '\'' ;

fragment ANY_CHAR : ~('\\' | '\'' | '\"')  | END_OF_STRING |
NEWLINE | TAB | CARRIAGE_RETURN | FORM_FEED | DOUBLE_QUOTES |
BACKSLASH | WHITESPACE | '\\\'';

//identities
fragment ID_BEGIN_CHAR: '_' | 'a'..'z' | 'A'..'Z' ;
fragment ID_CHAR: ID_BEGIN_CHAR | '0'..'9' ;
IDENTITY: ID_BEGIN_CHAR ID_CHAR* ;

//char literal:
CHAR_LITER : APOSTROPHE ANY_CHAR APOSTROPHE ;
