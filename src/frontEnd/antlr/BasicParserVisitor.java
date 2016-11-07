// Generated from ./BasicParser.g4 by ANTLR 4.4
package frontEnd.antlr;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link BasicParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface BasicParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link BasicParser#argList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgList(@NotNull BasicParser.ArgListContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#arrayLiter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayLiter(@NotNull BasicParser.ArrayLiterContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#arrayElem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayElem(@NotNull BasicParser.ArrayElemContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#assignRHS}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignRHS(@NotNull BasicParser.AssignRHSContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#assignLHS}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignLHS(@NotNull BasicParser.AssignLHSContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#unaryOper}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryOper(@NotNull BasicParser.UnaryOperContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(@NotNull BasicParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(@NotNull BasicParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#intLiter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntLiter(@NotNull BasicParser.IntLiterContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#baseType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBaseType(@NotNull BasicParser.BaseTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#pairLiter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPairLiter(@NotNull BasicParser.PairLiterContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#param}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParam(@NotNull BasicParser.ParamContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#charLiter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCharLiter(@NotNull BasicParser.CharLiterContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(@NotNull BasicParser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#pairElem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPairElem(@NotNull BasicParser.PairElemContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#intSign}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntSign(@NotNull BasicParser.IntSignContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStat(@NotNull BasicParser.StatContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#arrayType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayType(@NotNull BasicParser.ArrayTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#binaryOper}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinaryOper(@NotNull BasicParser.BinaryOperContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#boolLiter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolLiter(@NotNull BasicParser.BoolLiterContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#pairType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPairType(@NotNull BasicParser.PairTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#pairElemType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPairElemType(@NotNull BasicParser.PairElemTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#func}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunc(@NotNull BasicParser.FuncContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#paramList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParamList(@NotNull BasicParser.ParamListContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#comment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComment(@NotNull BasicParser.CommentContext ctx);
}