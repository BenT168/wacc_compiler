// Generated from ./WaccParser.g4 by ANTLR 4.4
package antlr;

  import SemanticAnalyser.*;
 
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link WaccParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface WaccParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link WaccParser#array_elem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArray_elem(@NotNull WaccParser.Array_elemContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stat_if}
	 * labeled alternative in {@link WaccParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStat_if(@NotNull WaccParser.Stat_ifContext ctx);
	/**
	 * Visit a parse tree produced by the {@code array_type_base}
	 * labeled alternative in {@link WaccParser#array_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArray_type_base(@NotNull WaccParser.Array_type_baseContext ctx);
	/**
	 * Visit a parse tree produced by the {@code array_type_pair}
	 * labeled alternative in {@link WaccParser#array_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArray_type_pair(@NotNull WaccParser.Array_type_pairContext ctx);
	/**
	 * Visit a parse tree produced by {@link WaccParser#ident}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdent(@NotNull WaccParser.IdentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code unary_minus}
	 * labeled alternative in {@link WaccParser#unary_oper}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnary_minus(@NotNull WaccParser.Unary_minusContext ctx);
	/**
	 * Visit a parse tree produced by the {@code assign_rhs_call_empty}
	 * labeled alternative in {@link WaccParser#assign_rhs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssign_rhs_call_empty(@NotNull WaccParser.Assign_rhs_call_emptyContext ctx);
	/**
	 * Visit a parse tree produced by {@link WaccParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(@NotNull WaccParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expr_ident}
	 * labeled alternative in {@link WaccParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_ident(@NotNull WaccParser.Expr_identContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expr_binary}
	 * labeled alternative in {@link WaccParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_binary(@NotNull WaccParser.Expr_binaryContext ctx);
	/**
	 * Visit a parse tree produced by the {@code base_type_char}
	 * labeled alternative in {@link WaccParser#base_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBase_type_char(@NotNull WaccParser.Base_type_charContext ctx);
	/**
	 * Visit a parse tree produced by the {@code type_arraytype}
	 * labeled alternative in {@link WaccParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType_arraytype(@NotNull WaccParser.Type_arraytypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code atom_ident}
	 * labeled alternative in {@link WaccParser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtom_ident(@NotNull WaccParser.Atom_identContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expr_bin_math_atom}
	 * labeled alternative in {@link WaccParser#math}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_bin_math_atom(@NotNull WaccParser.Expr_bin_math_atomContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stat_println}
	 * labeled alternative in {@link WaccParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStat_println(@NotNull WaccParser.Stat_printlnContext ctx);
	/**
	 * Visit a parse tree produced by {@link WaccParser#char_liter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitChar_liter(@NotNull WaccParser.Char_literContext ctx);
	/**
	 * Visit a parse tree produced by {@link WaccParser#binary_oper}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinary_oper(@NotNull WaccParser.Binary_operContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stat_return_middle}
	 * labeled alternative in {@link WaccParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStat_return_middle(@NotNull WaccParser.Stat_return_middleContext ctx);
	/**
	 * Visit a parse tree produced by the {@code pair_elem_array_type}
	 * labeled alternative in {@link WaccParser#pair_elem_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPair_elem_array_type(@NotNull WaccParser.Pair_elem_array_typeContext ctx);
	/**
	 * Visit a parse tree produced by {@link WaccParser#bool_liter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBool_liter(@NotNull WaccParser.Bool_literContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expr_bin_bool_math_eq}
	 * labeled alternative in {@link WaccParser#bin_bool}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_bin_bool_math_eq(@NotNull WaccParser.Expr_bin_bool_math_eqContext ctx);
	/**
	 * Visit a parse tree produced by the {@code base_type_bool}
	 * labeled alternative in {@link WaccParser#base_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBase_type_bool(@NotNull WaccParser.Base_type_boolContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stat_print}
	 * labeled alternative in {@link WaccParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStat_print(@NotNull WaccParser.Stat_printContext ctx);
	/**
	 * Visit a parse tree produced by the {@code atom_brackets}
	 * labeled alternative in {@link WaccParser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtom_brackets(@NotNull WaccParser.Atom_bracketsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code pair_elem_base_type}
	 * labeled alternative in {@link WaccParser#pair_elem_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPair_elem_base_type(@NotNull WaccParser.Pair_elem_base_typeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code assign_rhs_ar_liter}
	 * labeled alternative in {@link WaccParser#assign_rhs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssign_rhs_ar_liter(@NotNull WaccParser.Assign_rhs_ar_literContext ctx);
	/**
	 * Visit a parse tree produced by {@link WaccParser#int_sign}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInt_sign(@NotNull WaccParser.Int_signContext ctx);
	/**
	 * Visit a parse tree produced by {@link WaccParser#array_liter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArray_liter(@NotNull WaccParser.Array_literContext ctx);
	/**
	 * Visit a parse tree produced by the {@code unary_len}
	 * labeled alternative in {@link WaccParser#unary_oper}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnary_len(@NotNull WaccParser.Unary_lenContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stat_free}
	 * labeled alternative in {@link WaccParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStat_free(@NotNull WaccParser.Stat_freeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expr_bin_math}
	 * labeled alternative in {@link WaccParser#bin_bool}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_bin_math(@NotNull WaccParser.Expr_bin_mathContext ctx);
	/**
	 * Visit a parse tree produced by the {@code atom_bool}
	 * labeled alternative in {@link WaccParser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtom_bool(@NotNull WaccParser.Atom_boolContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expr_bin_bool_math_moreless}
	 * labeled alternative in {@link WaccParser#bin_bool}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_bin_bool_math_moreless(@NotNull WaccParser.Expr_bin_bool_math_morelessContext ctx);
	/**
	 * Visit a parse tree produced by the {@code assign_rhs_expr}
	 * labeled alternative in {@link WaccParser#assign_rhs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssign_rhs_expr(@NotNull WaccParser.Assign_rhs_exprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expr_str}
	 * labeled alternative in {@link WaccParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_str(@NotNull WaccParser.Expr_strContext ctx);
	/**
	 * Visit a parse tree produced by the {@code atom_char}
	 * labeled alternative in {@link WaccParser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtom_char(@NotNull WaccParser.Atom_charContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expr_array_elem}
	 * labeled alternative in {@link WaccParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_array_elem(@NotNull WaccParser.Expr_array_elemContext ctx);
	/**
	 * Visit a parse tree produced by the {@code assign_rhs_pair_elem}
	 * labeled alternative in {@link WaccParser#assign_rhs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssign_rhs_pair_elem(@NotNull WaccParser.Assign_rhs_pair_elemContext ctx);
	/**
	 * Visit a parse tree produced by the {@code atom_int}
	 * labeled alternative in {@link WaccParser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtom_int(@NotNull WaccParser.Atom_intContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stat_stat}
	 * labeled alternative in {@link WaccParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStat_stat(@NotNull WaccParser.Stat_statContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expr_unary}
	 * labeled alternative in {@link WaccParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_unary(@NotNull WaccParser.Expr_unaryContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stat_declare}
	 * labeled alternative in {@link WaccParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStat_declare(@NotNull WaccParser.Stat_declareContext ctx);
	/**
	 * Visit a parse tree produced by the {@code unary_chr}
	 * labeled alternative in {@link WaccParser#unary_oper}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnary_chr(@NotNull WaccParser.Unary_chrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stat_assign}
	 * labeled alternative in {@link WaccParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStat_assign(@NotNull WaccParser.Stat_assignContext ctx);
	/**
	 * Visit a parse tree produced by {@link WaccParser#stat_return}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStat_return(@NotNull WaccParser.Stat_returnContext ctx);
	/**
	 * Visit a parse tree produced by the {@code type_basetype}
	 * labeled alternative in {@link WaccParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType_basetype(@NotNull WaccParser.Type_basetypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stat_read}
	 * labeled alternative in {@link WaccParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStat_read(@NotNull WaccParser.Stat_readContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expr_bool}
	 * labeled alternative in {@link WaccParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_bool(@NotNull WaccParser.Expr_boolContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expr_bin_atom}
	 * labeled alternative in {@link WaccParser#math}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_bin_atom(@NotNull WaccParser.Expr_bin_atomContext ctx);
	/**
	 * Visit a parse tree produced by the {@code base_type_int}
	 * labeled alternative in {@link WaccParser#base_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBase_type_int(@NotNull WaccParser.Base_type_intContext ctx);
	/**
	 * Visit a parse tree produced by {@link WaccParser#int_liter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInt_liter(@NotNull WaccParser.Int_literContext ctx);
	/**
	 * Visit a parse tree produced by the {@code type_pairtype}
	 * labeled alternative in {@link WaccParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType_pairtype(@NotNull WaccParser.Type_pairtypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link WaccParser#pair_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPair_type(@NotNull WaccParser.Pair_typeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code base_type_string}
	 * labeled alternative in {@link WaccParser#base_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBase_type_string(@NotNull WaccParser.Base_type_stringContext ctx);
	/**
	 * Visit a parse tree produced by the {@code assign_lhs_ident}
	 * labeled alternative in {@link WaccParser#assign_lhs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssign_lhs_ident(@NotNull WaccParser.Assign_lhs_identContext ctx);
	/**
	 * Visit a parse tree produced by {@link WaccParser#param}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParam(@NotNull WaccParser.ParamContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expr_brackets}
	 * labeled alternative in {@link WaccParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_brackets(@NotNull WaccParser.Expr_bracketsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expr_char}
	 * labeled alternative in {@link WaccParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_char(@NotNull WaccParser.Expr_charContext ctx);
	/**
	 * Visit a parse tree produced by the {@code assign_rhs_call}
	 * labeled alternative in {@link WaccParser#assign_rhs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssign_rhs_call(@NotNull WaccParser.Assign_rhs_callContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expr_pair}
	 * labeled alternative in {@link WaccParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_pair(@NotNull WaccParser.Expr_pairContext ctx);
	/**
	 * Visit a parse tree produced by the {@code assign_lhs_pair}
	 * labeled alternative in {@link WaccParser#assign_lhs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssign_lhs_pair(@NotNull WaccParser.Assign_lhs_pairContext ctx);
	/**
	 * Visit a parse tree produced by the {@code pair_elem_fst}
	 * labeled alternative in {@link WaccParser#pair_elem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPair_elem_fst(@NotNull WaccParser.Pair_elem_fstContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stat_begin_end}
	 * labeled alternative in {@link WaccParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStat_begin_end(@NotNull WaccParser.Stat_begin_endContext ctx);
	/**
	 * Visit a parse tree produced by the {@code array_type_array}
	 * labeled alternative in {@link WaccParser#array_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArray_type_array(@NotNull WaccParser.Array_type_arrayContext ctx);
	/**
	 * Visit a parse tree produced by the {@code layer_s_i}
	 * labeled alternative in {@link WaccParser#if_layers}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLayer_s_i(@NotNull WaccParser.Layer_s_iContext ctx);
	/**
	 * Visit a parse tree produced by the {@code unary_not}
	 * labeled alternative in {@link WaccParser#unary_oper}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnary_not(@NotNull WaccParser.Unary_notContext ctx);
	/**
	 * Visit a parse tree produced by the {@code assign_lhs_array}
	 * labeled alternative in {@link WaccParser#assign_lhs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssign_lhs_array(@NotNull WaccParser.Assign_lhs_arrayContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expr_bin_math_math}
	 * labeled alternative in {@link WaccParser#math}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_bin_math_math(@NotNull WaccParser.Expr_bin_math_mathContext ctx);
	/**
	 * Visit a parse tree produced by the {@code func_if}
	 * labeled alternative in {@link WaccParser#func}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunc_if(@NotNull WaccParser.Func_ifContext ctx);
	/**
	 * Visit a parse tree produced by the {@code layer_i_i}
	 * labeled alternative in {@link WaccParser#if_layers}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLayer_i_i(@NotNull WaccParser.Layer_i_iContext ctx);
	/**
	 * Visit a parse tree produced by the {@code func_standard}
	 * labeled alternative in {@link WaccParser#func}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunc_standard(@NotNull WaccParser.Func_standardContext ctx);
	/**
	 * Visit a parse tree produced by {@link WaccParser#pair_liter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPair_liter(@NotNull WaccParser.Pair_literContext ctx);
	/**
	 * Visit a parse tree produced by the {@code pair}
	 * labeled alternative in {@link WaccParser#pair_elem_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPair(@NotNull WaccParser.PairContext ctx);
	/**
	 * Visit a parse tree produced by {@link WaccParser#arg_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArg_list(@NotNull WaccParser.Arg_listContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stat_skip}
	 * labeled alternative in {@link WaccParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStat_skip(@NotNull WaccParser.Stat_skipContext ctx);
	/**
	 * Visit a parse tree produced by {@link WaccParser#param_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParam_list(@NotNull WaccParser.Param_listContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expr_int}
	 * labeled alternative in {@link WaccParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_int(@NotNull WaccParser.Expr_intContext ctx);
	/**
	 * Visit a parse tree produced by the {@code unary_ord}
	 * labeled alternative in {@link WaccParser#unary_oper}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnary_ord(@NotNull WaccParser.Unary_ordContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stat_exit}
	 * labeled alternative in {@link WaccParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStat_exit(@NotNull WaccParser.Stat_exitContext ctx);
	/**
	 * Visit a parse tree produced by the {@code layer_s_s}
	 * labeled alternative in {@link WaccParser#if_layers}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLayer_s_s(@NotNull WaccParser.Layer_s_sContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expr_bin_bool_bool}
	 * labeled alternative in {@link WaccParser#bin_bool}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_bin_bool_bool(@NotNull WaccParser.Expr_bin_bool_boolContext ctx);
	/**
	 * Visit a parse tree produced by the {@code pair_elem_snd}
	 * labeled alternative in {@link WaccParser#pair_elem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPair_elem_snd(@NotNull WaccParser.Pair_elem_sndContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stat_while}
	 * labeled alternative in {@link WaccParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStat_while(@NotNull WaccParser.Stat_whileContext ctx);
	/**
	 * Visit a parse tree produced by {@link WaccParser#str_liter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStr_liter(@NotNull WaccParser.Str_literContext ctx);
	/**
	 * Visit a parse tree produced by the {@code assign_rhs_newpair}
	 * labeled alternative in {@link WaccParser#assign_rhs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssign_rhs_newpair(@NotNull WaccParser.Assign_rhs_newpairContext ctx);
}