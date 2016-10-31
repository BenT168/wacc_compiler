parser grammar BasicParser;

options {
  tokenVocab=BasicLexer;
}

program: BEGIN (func)* stat END ;

func : type IDENTITY OPEN_PARENTHESES (paramList)? CLOSE_PARENTHESES IS stat END ;

paramList : param (COMMA param)* ;

param : type IDENTITY ;

stat : SKIP
| type IDENTITY EQUALS assignRHS
| assignLHS EQUALS assignRHS
| READ assignLHS
| FREE expr
| RETURN expr
| EXIT expr
| PRINT expr
| PRINTLN expr
| IF expr THEN stat ELSE stat ENDIF
| WHILE expr DO stat DONE
| BEGIN stat END
| stat SEMI_COLON stat
;

assignLHS : IDENTITY | arrayElem | pairElem ;

assignRHS : expr
| arrayLiter
| NEWPAIR OPEN_PARENTHESES expr COMMA expr CLOSE_PARENTHESES
| pairElem
| CALL IDENTITY OPEN_PARENTHESES (argList)? CLOSE_PARENTHESES
;

argList : expr (COMMA expr)* ;

pairElem : FST expr | SND expr ;

type : baseType | arrayType | pairType ;

baseType : INT | BOOL | CHAR | STRING ;

arrayType : baseType OPEN_SQUARE CLOSE_SQUARE
| arrayType OPEN_SQUARE CLOSE_SQUARE
| pairType OPEN_SQUARE CLOSE_SQUARE
;

pairType : PAIR OPEN_PARENTHESES pairElemType COMMA pairElemType CLOSE_PARENTHESES ;

pairElemType : baseType | arrayType | PAIR ;

expr: expr binaryOper expr
| intLiter
| boolLiter
| charLiter
| STRING_LITER
| pairLiter
| IDENTITY
| arrayElem
| unaryOper expr
| OPEN_PARENTHESES expr CLOSE_PARENTHESES
;

unaryOper : LEN | ORD | CHR | NOT | INC | DEC ;

binaryOper : PLUS | MINUS | MULTI | DIV | MOD | LT | GT | LTE | GTE | EQ | AND | NEQ | OR ;

arrayElem : IDENTITY (OPEN_SQUARE expr CLOSE_SQUARE)+ ;

intLiter : (intSign)? INTEGER ;

intSign : POS | NEG ;

boolLiter : TRUE | FALSE ;

charLiter : CHAR_LITER ;

arrayLiter : OPEN_SQUARE (expr (COMMA expr)* )? CLOSE_SQUARE ;

pairLiter : NULL ;

// EOF indicates that the program must consume to the end of the input.
comment : COMMENT;
