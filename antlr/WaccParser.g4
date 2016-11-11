parser grammar WaccParser;
@parser::header{
  import SemanticAnalyser.*;
 }


options {
  tokenVocab=WaccLexer;
}

ident
locals[TYPE typename]
: VARIABLE;

pair_liter: NUL;

array_liter
locals[TYPE typename]
: OPEN_SQUARE_BRACKET ((expr (COMMA expr)*))? CLOSE_SQUARE_BRACKET;

str_liter: STR ;

char_liter: CHARACTER ;

bool_liter: TRUE | FALSE ;

int_sign: PLUS | MINUS ;

int_liter: (int_sign)? (INTEGER)+ ;

array_elem
locals[TYPE typename]
: ident (OPEN_SQUARE_BRACKET expr CLOSE_SQUARE_BRACKET)+ ;

binary_oper
: MULTIPLY
| DIVIDE
| MOD
| PLUS
| MINUS
| GREATER
| GREATER_EQUAL
| LESS
| LESS_EQUAL
| IS_EQUAL
| NOT_EQUAL
| AND
| OR
;

atom
locals[TYPE typename]
: int_liter #atom_int
| bool_liter #atom_bool
| char_liter #atom_char
| ident #atom_ident
| OPEN_PARENTHESES expr CLOSE_PARENTHESES #atom_brackets
;

math
locals[TYPE argtype, TYPE returntype]
: math (MULTIPLY | DIVIDE | MOD | PLUS | MINUS) math #expr_bin_math_math
| atom (MULTIPLY | DIVIDE | MOD | PLUS | MINUS) atom #expr_bin_math_atom
| atom #expr_bin_atom
;

bin_bool
locals[TYPE argtype, TYPE returntype]
: bin_bool (AND | OR) bin_bool #expr_bin_bool_bool
| math (IS_EQUAL | NOT_EQUAL) math #expr_bin_bool_math_eq
| math (GREATER | GREATER_EQUAL| LESS | LESS_EQUAL) math #expr_bin_bool_math_moreless
| math #expr_bin_math
;

unary_oper
locals[TYPE argtype, TYPE returntype]
: NOT #unary_not
| MINUS #unary_minus
| LEN #unary_len
| ORD #unary_ord
| CHR #unary_chr
;

expr
locals[TYPE typename]
: int_liter #expr_int
| bool_liter #expr_bool
| char_liter #expr_char
| str_liter #expr_str
| pair_liter #expr_pair
| ident #expr_ident
| array_elem #expr_array_elem
| unary_oper expr #expr_unary
| bin_bool #expr_binary
| OPEN_PARENTHESES expr CLOSE_PARENTHESES #expr_brackets
;

pair_elem_type
locals[TYPE typename]
: base_type   #pair_elem_base_type
| array_type  #pair_elem_array_type
| PAIR        #pair
;

pair_type
locals[TYPE typename]
: PAIR OPEN_PARENTHESES pair_elem_type COMMA pair_elem_type CLOSE_PARENTHESES
;

//array_type:
//| type OPEN_SQUARE_BRACKET CLOSE_SQUARE_BRACKET
//;
// fixing mutually recursive (this below is 'direct left recursive')
array_type
locals[TYPE typename]
: array_type OPEN_SQUARE_BRACKET CLOSE_SQUARE_BRACKET #array_type_array
| base_type OPEN_SQUARE_BRACKET CLOSE_SQUARE_BRACKET #array_type_base 
| pair_type OPEN_SQUARE_BRACKET CLOSE_SQUARE_BRACKET #array_type_pair
;

base_type
locals[TYPE typename]
: INT 	#base_type_int
| BOOL	#base_type_bool
| CHAR 	#base_type_char
| STRING	#base_type_string
;

type
locals[TYPE typename]
: base_type	#type_basetype
| array_type	#type_arraytype
| pair_type	#type_pairtype
;

pair_elem
locals[TYPE typename]
: FST expr	#pair_elem_fst
| SND expr	#pair_elem_snd
;

arg_list: expr (COMMA expr)* ;

assign_rhs
locals[TYPE typename]
: expr	                                                        #assign_rhs_expr
| array_liter	                                                #assign_rhs_ar_liter
| NEWPAIR OPEN_PARENTHESES expr COMMA expr CLOSE_PARENTHESES	#assign_rhs_newpair
| pair_elem		                                                #assign_rhs_pair_elem
| CALL ident OPEN_PARENTHESES arg_list CLOSE_PARENTHESES	    #assign_rhs_call
| CALL ident OPEN_PARENTHESES CLOSE_PARENTHESES				    #assign_rhs_call_empty
;

assign_lhs
locals[TYPE typename]
: ident #assign_lhs_ident
| array_elem	#assign_lhs_array
| pair_elem		#assign_lhs_pair
;

stat
locals[TYPE typename]
: SKIP				#stat_skip
| type ident EQUAL_ASSIGN assign_rhs	#stat_declare
| assign_lhs EQUAL_ASSIGN assign_rhs	#stat_assign
| READ assign_lhs 			#stat_read
| FREE expr				#stat_free
| EXIT expr 				#stat_exit
| PRINT expr				#stat_print
| PRINTLN expr 				#stat_println
| IF expr THEN stat ELSE stat FI	#stat_if
| WHILE expr DO stat DONE 		#stat_while
| BEGIN stat END 			#stat_begin_end
| stat SEMI_COLON stat			#stat_stat
| stat_return				#stat_return_middle
;

stat_return
locals[TYPE typename]
: RETURN expr
| EXIT expr
;


param
locals[PARAM paramObj]
: type ident ;

param_list: param (COMMA param)* ;

if_layers
locals[TYPE typename]
: (IF expr THEN (stat SEMI_COLON)? stat_return ELSE (stat SEMI_COLON)? stat_return FI) #layer_s_s
| (IF expr THEN (stat SEMI_COLON)? if_layers ELSE (stat SEMI_COLON)? if_layers FI) #layer_i_i
| (IF expr THEN (stat SEMI_COLON)? stat_return ELSE (stat SEMI_COLON)? if_layers FI) #layer_s_i
| (IF expr THEN (stat SEMI_COLON)? if_layers ELSE (stat SEMI_COLON)? stat_return FI) #layer_s_i
;

func
locals[FUNCTION funObj]
: type ident OPEN_PARENTHESES (param_list)? CLOSE_PARENTHESES IS (stat SEMI_COLON)? stat_return END #func_standard
| type ident OPEN_PARENTHESES (param_list)? CLOSE_PARENTHESES IS (stat SEMI_COLON)? if_layers END #func_if;

// EOF indicates that the program must consume to the end of the input.
program: BEGIN (func)* stat END EOF;
