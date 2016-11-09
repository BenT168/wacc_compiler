parser grammar BasicParser;

options {
  tokenVocab=BasicLexer;
}


program: BEGIN (func)* stat END EOF ;

func : type ident OPEN_PARENTHESES (paramList)? CLOSE_PARENTHESES IS stat END ;

paramList : param (COMMA param)* ;

param : type ident ;


stat : SKIP  # skip
| type ident EQUALS assignRHS  # declare
| assignLHS EQUALS assignRHS  # assign
| READ assignLHS  # read
| FREE expr  # free
| RETURN expr  # return
| EXIT expr  # exit
| PRINT expr  # print
| PRINTLN expr  # println
| IF expr THEN stat ELSE stat ENDIF  # ifElse
| WHILE expr DO stat DONE  # while
| BEGIN stat END  # begin
| stat SEMI_COLON stat  # multipleStat
;

assignLHS : ident | arrayElem | pairElem ;

assignRHS : expr
| arrayLiter
| NEWPAIR OPEN_PARENTHESES expr COMMA expr CLOSE_PARENTHESES
| pairElem
| CALL ident OPEN_PARENTHESES (argList)? CLOSE_PARENTHESES
;

argList : expr (COMMA expr)* ;

pairElem : FST expr | SND expr ;

type : baseType | arrayType | pairType ;

baseType : INT | BOOL | CHAR | STRING ;

arrayType : ( baseType | pairType ) (OPEN_SQUARE CLOSE_SQUARE)+ ;

pairType : PAIR OPEN_PARENTHESES pairElemType COMMA pairElemType CLOSE_PARENTHESES ;

pairElemType : baseType | arrayType | PAIR ;

expr : ( NOT | MINUS | LEN | ORD | CHR ) expr
| expr ( MUL | DIV | MOD ) expr
| expr ( PLUS | MINUS ) expr
| expr ( LT | GT | LTE | GTE ) expr
| expr ( EQ | NEQ ) expr
| expr AND expr
| expr OR expr
| OPEN_PARENTHESES expr CLOSE_PARENTHESES
| (intLiter | boolLiter | charLiter | stringLiter | pairLiter | ident | arrayElem )
;


intLiter :  (PLUS | MINUS) INTEGER | INTEGER ;

arrayElem : ident (OPEN_SQUARE expr CLOSE_SQUARE)+ ;

boolLiter : TRUE | FALSE ;

charLiter : CHAR_LITER ;

stringLiter : STRING_LITER;

arrayLiter : OPEN_SQUARE (expr (COMMA (expr))* )? CLOSE_SQUARE ;

pairLiter : NULL ;

ident : IDENTITY;

