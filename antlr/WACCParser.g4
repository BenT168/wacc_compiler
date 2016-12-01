parser grammar WACCParser;

options {
  tokenVocab=WACCLexer;
}

// Program
program : BEGIN func* stat END EOF;

// functions
func    : type ident OPEN_PARENTHESES (paramList)? CLOSE_PARENTHESES IS stat END;

paramList
        : param (COMMA param)*;

param   : type ident;

// statements
stat    : SKIP							    # skip
        | type ident EQUAL assignRHS		# declare
        | assignLHS EQUAL assignRHS 		# assign
        | READ assignLHS					# read
        | FREE expr							# free
        | RETURN expr						# return
        | EXIT expr							# exit
        | PRINT expr						# print
        | PRINTLN expr 						# println
        | IF expr THEN stat ELSE stat ENDIF	# ifElse
        | WHILE expr DO stat DONE			# while
        | DO stat WHILE expr DONE           # doWhile
        | FOR stat SEMI_COLON expr SEMI_COLON expr DO stat DONE # forLoop
        | BEGIN stat END 					# begin
        | stat SEMI_COLON stat 				# multipleStat
        | BREAK                             # break
        | CONTINUE                          # continue
        ;

// Assignments
assignLHS
        : ident | arrayElem | pairElem ;

assignRHS
        : expr                                                        # expr_assignRHS
        | arrayLiter                                                  # arrayLiter_assignRHS
        | NEWPAIR OPEN_PARENTHESES expr COMMA expr CLOSE_PARENTHESES  # newPair_assignRHS
        | pairElem                                                    # pairElem_assignRHS
        | CALL ident OPEN_PARENTHESES (argList)? CLOSE_PARENTHESES    # funcCall_assignRHS
        ;

argList : expr (COMMA (expr))* ;

// Pairs. There can be pairs of pairs but when nested the inside type is not declared.
pairType: PAIR OPEN_PARENTHESES pairElemType COMMA pairElemType CLOSE_PARENTHESES ;

pairElem
        : FST expr
        | SND expr
        ;

pairElemType
        : baseType
        | arrayType
        | PAIR
        ;

// Arrays
arrayType
        :( baseType | pairType ) (OPEN_SQUARE CLOSE_SQUARE)+ ;

// WACC Types
type    : baseType
        | arrayType
        | pairType
        ;

baseType: INT
        | BOOL
        | CHAR
        | STRING
        ;

// Expressions.
        // Unary expressions bind the tightest.
expr    : (NOT | MINUS | LEN | ORD | CHR) expr
        //Loops
        | ident PLUSPLUS
        | ident MINUSMINUS
        // binary expressions with order of precedence
        | expr (MUL | DIV | MOD) expr
        | expr (PLUS | MINUS) expr
        | expr (GREATER | GREATER_EQUAL | LESS | LESS_EQUAL) expr
        | expr (DOUBLE_EQUALS | NOT_EQUAL) expr
        // binary boolean expressions
        | expr AND expr
        | expr OR expr
        | expr PLUSEQUAL expr
        | expr MINUSEQUAL expr
        // atomic expressions
        | OPEN_PARENTHESES expr CLOSE_PARENTHESES
        // expression literals
        | (intLiter | boolLiter | charLiter | stringLiter | pairLiter | ident | arrayElem )
;


arrayElem
        : ident (OPEN_SQUARE expr CLOSE_SQUARE)+ ;

intLiter: (PLUS | MINUS) INTEGER
        | INTEGER
        ;

boolLiter
        : TRUE
        | FALSE
        ;

charLiter
        : CHAR_LITER ;

stringLiter
        : STRING_LITER ;

arrayLiter
        : OPEN_SQUARE (expr (COMMA (expr))*)? CLOSE_SQUARE ;

// There is no such thing as a pair literal
pairLiter
        : NULL ;

// This is here for legacy purposes
ident   : IDENTITY;


