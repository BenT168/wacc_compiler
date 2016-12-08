package frontend.assignments;

import backend.Register;
import backend.TokSeq;
import backend.tokens.store.StoreToken;
import backend.tokens.load.LoadToken;
import backend.tokens.move.MovRegToken;
import frontend.expressions.ExprNode;
import frontend.type.ArrayType;
import frontend.type.BaseType;
import org.antlr.v4.runtime.ParserRuleContext;
import symboltable.SymbolTable;

import java.util.ArrayList;

public class ArrayLiter extends Assignable {

	private ArrayList<ExprNode> elems;
	private BaseType baseType;

	public ArrayLiter(ArrayList<ExprNode> elems) {
		this.elems = elems;
		if (elems.size() > 0) {
			baseType = elems.get(0).getType();
		} else {
			baseType = BaseType.NULL;
		}
	}

	/*
	Method that checks that the types of all the exprnode in elems matches the basetype
	 */
	@Override
	public boolean check(SymbolTable st, ParserRuleContext ctx) {
		//Iterates through array  and checks all elements are of the same type (i.e. baseType)
		for(int i = 0; i < elems.size(); i++) {
			if (!elems.get(i).getType().isCompatible(baseType)) {
				return false;
			}
		}
		return true;
	}

	/*
	Method returns an array type of the basetype
	 */
	@Override
	public BaseType getType() {
		//Base Type might be null if empty array
		return new ArrayType(baseType);
	}

	/*
	Method that generates the ARM assembly of the array liter
	 */
	@Override
	public TokSeq assemblyCodeGenerating(Register dest) {
		int arrayLength = elems.size();

		//allocate memory for the arraySize and its elements(addresses)
		TokSeq allocateArray = mallocSequence(arrayLength + 1, baseType.getVarSize());
		MovRegToken movReg1 = new MovRegToken(dest, Register.R0);
		allocateArray.append(movReg1);

		//iterates through the list of array elems, and produces their assembly code
		int count = 1;
		for (ExprNode node : elems) {
			TokSeq exprSeq = node.assemblyCodeGenerating(dest.getNext());
			StoreToken storeElem = new StoreToken(dest, dest.getNext(), (count*this.getType().getVarSize()));
			allocateArray
			.appendAll(exprSeq)
			.append(storeElem);

			count++;
		}
		//store the arraySize
		LoadToken loadSize = new LoadToken(dest.getNext(), Integer.toString(arrayLength));
		StoreToken storeSize = new StoreToken(dest, dest.getNext());
		allocateArray.append(loadSize).append(storeSize);
		return allocateArray;
	}

}
