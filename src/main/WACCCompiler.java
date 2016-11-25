package main;

import backend.Register;
import backend.TokenCollector;
import backend.TokenSequence;
import frontend.Tree;

public class WACCCompiler {
	
	private Tree Tree;
	private TokenSequence progSequence;
	
	public WACCCompiler(Tree t){
		this.Tree = t;
	}
	
	public void init() {
		TokenSequence bodySequence = Tree.toAssembly(new Register());
		TokenCollector tc = new TokenCollector(bodySequence);
		progSequence = tc.collect();
	}
	
	@Override
	public String toString() {
		return progSequence.toString();
	}



}
