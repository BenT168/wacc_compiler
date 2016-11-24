package main;

import backend.Register;
import backend.TokenCollector;
import backend.TokenSequence;

public class WACCCompiler {
	
	private frontend.tree tree;
	private TokenSequence progSequence;
	
	public WACCCompiler(frontend.tree t){
		this.tree = t;
	}
	
	public void init() {
		TokenSequence bodySequence = tree.toAssembly(new Register());
		TokenCollector tc = new TokenCollector(bodySequence);
		progSequence = tc.collect();
	}
	
	@Override
	public String toString() {
		return progSequence.toString();
	}



}
