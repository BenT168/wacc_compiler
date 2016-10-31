parser grammar BasicParser;

options {
  tokenVocab=BasicLexer;
}

binaryOper : PLUS | MINUS | MULTI | DIV | MOD | LT | GT | LTE | GTE | EQ | AND | NEQ | OR ;

unaryOper : LEN | ORD | CHR | NOT | INC | DEC ;

expr: expr binaryOper expr
| INTEGER
| OPEN_PARENTHESES expr CLOSE_PARENTHESES
| BOOL
| CHAR
| STR
| PAIR
| IDENTIC
| ARRAY-ELEM
| unaryOper expr
;

// EOF indicates that the program must consume to the end of the input.
prog: (expr)*  EOF ;
