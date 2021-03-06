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
        | ident ADD expr                    # addElemList
        | ident ADD expr expr               # addElemMap
        | condAssign                        # conditionalOps
        | BREAK                             # break
        | CONTINUE                          # continues
        ;

// Assignments
assignLHS
        : ident | arrayElem | pairElem | listElem;

assignRHS
        : expr                                                        # expr_assignRHS
        | arrayLiter                                                  # arrayLiter_assignRHS
        | NEWPAIR OPEN_PARENTHESES expr COMMA expr CLOSE_PARENTHESES  # newPair_assignRHS
        | NEW (LINKEDLIST | ARRAYLIST) LESS type GREATER OPEN_PARENTHESES CLOSE_PARENTHESES # newList_assignRHS
        | NEW HASHMAP LESS type COMMA type GREATER OPEN_PARENTHESES CLOSE_PARENTHESES # newMap_assignRHS
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

// Lists
listType: (LIST | LINKEDLIST | ARRAYLIST) (LESS type GREATER) ;

// Maps
mapType: (MAP | HASHMAP) (LESS type COMMA type GREATER) ;

// WACC Types
type    : baseType
        | arrayType
        | pairType
        | listType
        | mapType
        ;

baseType: INT
        | BOOL
        | CHAR
        | STRING
        ;

// Expressions.
        // Unary expressions bind the tightest.
expr    : (NOT | MINUS | LEN | ORD | CHR) expr
        // binary expressions with order of precedence
        | expr (MUL | DIV | MOD) expr
        | expr (PLUS | MINUS) expr
        | expr (GREATER | GREATER_EQUAL | LESS | LESS_EQUAL) expr
        | expr (DOUBLE_EQUALS | NOT_EQUAL) expr
        // binary boolean expressions
        | expr AND expr
        | expr OR expr
        // atomic expressions
        | OPEN_PARENTHESES expr CLOSE_PARENTHESES
        //Ternary expression
        | expr TERNARY expr COLON expr
        //Loops
        | ident PLUS PLUS
        | ident MINUS MINUS
        // expression literals
        | (intLiter | boolLiter | charLiter | stringLiter | pairLiter | ident | arrayElem | listElem )
        | (binLiter | hexLiter | octLiter )
        ;


condAssign : ident PLUSEQUAL expr
           | ident MINUSEQUAL expr
           ;

arrayElem
        : ident (OPEN_SQUARE expr CLOSE_SQUARE)+ ;

listElem
        : ident GETLIST OPEN_PARENTHESES expr CLOSE_PARENTHESES ;

mapElem
        : ident GETMAP OPEN_PARENTHESES expr CLOSE_PARENTHESES ;

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

binLiter
        : BIN_LITER ;

hexLiter
        : HEX_LITER ;

octLiter
        : OCT_LITER ;

arrayLiter
        : OPEN_SQUARE (expr (COMMA (expr))*)? CLOSE_SQUARE ;

// There is no such thing as a pair literal
pairLiter
        : NULL ;

// This is here for legacy purposes
ident   : IDENTITY;


