// Generated from ./WACCParser.g4 by ANTLR 4.4
package antlr;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link WACCParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface WACCParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link WACCParser#argList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgList(@NotNull WACCParser.ArgListContext ctx);
	/**
	 * Visit a parse tree produced by {@link WACCParser#arrayLiter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayLiter(@NotNull WACCParser.ArrayLiterContext ctx);
	/**
	 * Visit a parse tree produced by {@link WACCParser#arrayElem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayElem(@NotNull WACCParser.ArrayElemContext ctx);
	/**
	 * Visit a parse tree produced by {@link WACCParser#assignRHS}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignRHS(@NotNull WACCParser.AssignRHSContext ctx);
	/**
	 * Visit a parse tree produced by the {@code declare}
	 * labeled alternative in {@link WACCParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclare(@NotNull WACCParser.DeclareContext ctx);
	/**
	 * Visit a parse tree produced by {@link WACCParser#assignLHS}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignLHS(@NotNull WACCParser.AssignLHSContext ctx);
	/**
	 * Visit a parse tree produced by {@link WACCParser#ident}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdent(@NotNull WACCParser.IdentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code skip}
	 * labeled alternative in {@link WACCParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSkip(@NotNull WACCParser.SkipContext ctx);
	/**
	 * Visit a parse tree produced by {@link WACCParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(@NotNull WACCParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by the {@code while}
	 * labeled alternative in {@link WACCParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhile(@NotNull WACCParser.WhileContext ctx);
	/**
	 * Visit a parse tree produced by {@link WACCParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(@NotNull WACCParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link WACCParser#intLiter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntLiter(@NotNull WACCParser.IntLiterContext ctx);
	/**
	 * Visit a parse tree produced by {@link WACCParser#baseType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBaseType(@NotNull WACCParser.BaseTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code println}
	 * labeled alternative in {@link WACCParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintln(@NotNull WACCParser.PrintlnContext ctx);
	/**
	 * Visit a parse tree produced by {@link WACCParser#pairLiter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPairLiter(@NotNull WACCParser.PairLiterContext ctx);
	/**
	 * Visit a parse tree produced by {@link WACCParser#param}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParam(@NotNull WACCParser.ParamContext ctx);
	/**
	 * Visit a parse tree produced by {@link WACCParser#charLiter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCharLiter(@NotNull WACCParser.CharLiterContext ctx);
	/**
	 * Visit a parse tree produced by {@link WACCParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(@NotNull WACCParser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link WACCParser#stringLiter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringLiter(@NotNull WACCParser.StringLiterContext ctx);
	/**
	 * Visit a parse tree produced by the {@code free}
	 * labeled alternative in {@link WACCParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFree(@NotNull WACCParser.FreeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ifElse}
	 * labeled alternative in {@link WACCParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfElse(@NotNull WACCParser.IfElseContext ctx);
	/**
	 * Visit a parse tree produced by {@link WACCParser#pairElem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPairElem(@NotNull WACCParser.PairElemContext ctx);
	/**
	 * Visit a parse tree produced by {@link WACCParser#arrayType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayType(@NotNull WACCParser.ArrayTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code read}
	 * labeled alternative in {@link WACCParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRead(@NotNull WACCParser.ReadContext ctx);
	/**
	 * Visit a parse tree produced by {@link WACCParser#boolLiter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolLiter(@NotNull WACCParser.BoolLiterContext ctx);
	/**
	 * Visit a parse tree produced by {@link WACCParser#pairType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPairType(@NotNull WACCParser.PairTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link WACCParser#pairElemType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPairElemType(@NotNull WACCParser.PairElemTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link WACCParser#call}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCall(@NotNull WACCParser.CallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exit}
	 * labeled alternative in {@link WACCParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExit(@NotNull WACCParser.ExitContext ctx);
	/**
	 * Visit a parse tree produced by the {@code print}
	 * labeled alternative in {@link WACCParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrint(@NotNull WACCParser.PrintContext ctx);
	/**
	 * Visit a parse tree produced by {@link WACCParser#func}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunc(@NotNull WACCParser.FuncContext ctx);
	/**
	 * Visit a parse tree produced by {@link WACCParser#paramList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParamList(@NotNull WACCParser.ParamListContext ctx);
	/**
	 * Visit a parse tree produced by the {@code multipleStat}
	 * labeled alternative in {@link WACCParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultipleStat(@NotNull WACCParser.MultipleStatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code begin}
	 * labeled alternative in {@link WACCParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBegin(@NotNull WACCParser.BeginContext ctx);
	/**
	 * Visit a parse tree produced by the {@code return}
	 * labeled alternative in {@link WACCParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturn(@NotNull WACCParser.ReturnContext ctx);
	/**
	 * Visit a parse tree produced by the {@code assign}
	 * labeled alternative in {@link WACCParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssign(@NotNull WACCParser.AssignContext ctx);
}