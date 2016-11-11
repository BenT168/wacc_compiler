lexer grammar WaccLexer;



COMMA: ',' ;

TRUE: 'true' ;
FALSE: 'false' ;

//operators
MULTIPLY: '*' ;
DIVIDE: '/' ;
MOD: '%' ;
PLUS: '+' ;
MINUS: '-' ;
GREATER: '>' ;
GREATER_EQUAL: '>=' ;
LESS: '<' ;
LESS_EQUAL: '<=' ;
IS_EQUAL: '==' ;
NOT_EQUAL: '!=' ;
AND: '&&' ;
OR: '||' ;

NOT: '!' ;
LEN: 'len' ;
ORD: 'ord' ;
CHR: 'chr' ;


//brackets
OPEN_PARENTHESES : '(' ;
CLOSE_PARENTHESES : ')' ;
OPEN_SQUARE_BRACKET : '[' ;
CLOSE_SQUARE_BRACKET : ']' ;

//quotations
SINGLE_QUOTATION: '\'' ;
DOUBLE_QUOTATION: '\"' ;

//pair
PAIR: 'pair' ;
FST: 'fst' ;
SND: 'snd' ;
NEWPAIR: 'newpair' ;

//base-type
INT: 'int';
BOOL: 'bool';
CHAR: 'char';
STRING: 'string';

//stats
CALL: 'call' ;
SKIP: 'skip' ;
EQUAL_ASSIGN: '=' ;
READ: 'read' ;
FREE: 'free' ;
RETURN: 'return' ;
EXIT: 'exit' ;
PRINT: 'print' ;
PRINTLN: 'println' ;
IF: 'if' ;
THEN: 'then' ;
ELSE: 'else' ;
FI: 'fi' ;
WHILE: 'while' ;
DO: 'do' ;
DONE: 'done' ;
BEGIN: 'begin' ;
END: 'end';
SEMI_COLON: ';' ;

IS: 'is' ;




//numbers
fragment DIGIT : '0'..'9' ; 
INTEGER: DIGIT+ ;
VARIABLE: ('_' | 'a'..'z' | 'A'..'Z') ('_' | 'a'..'z' | 'A'..'Z' | DIGIT)* ;


WS: [ \r\n\t]+ -> skip ;


ESCAPED_CHAR: '0' | 'b' | 't' | 'n' | 'f' | 'r' | SINGLE_QUOTATION | DOUBLE_QUOTATION | BACK_SLASH ;

//comment
fragment HASH : '#';
COMMENT: HASH ~[\r\n]* EOL -> skip ;
fragment EOL: (CR)? LF | CR;

//escaped characters
NUL: ''; // <------------------------------------ how?
BS: '\b' ;  // backspace
HT: '\t' ;  // horizontal tab
LF: '\n' ;  // newline
FF: '\f' ;  // newpage 
CR: '\r' ;  // carriage return


EMPTY: '';

BACK_SLASH: '\\';


CHARACTER:
SINGLE_QUOTATION (WS | 'a'..'z' | 'A'..'Z' | ~[BACK_SLASH ''' '"' SINGLE_QUOTATION DOUBLE_QUOTATION]
| BACK_SLASH ESCAPED_CHAR) SINGLE_QUOTATION
;

STR:
DOUBLE_QUOTATION (WS | 'a'..'z' | 'A'..'Z' | ~[BACK_SLASH ''' '"' SINGLE_QUOTATION DOUBLE_QUOTATION]
| BACK_SLASH ESCAPED_CHAR)* DOUBLE_QUOTATION
;
