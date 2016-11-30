package main;

import backend.Register;
import backend.TokCollector;
import backend.TokSeq;
import frontend.Tree;

public class CodeGenerator {
	
	private Tree Tree;
	private TokSeq progSeq;
	
	public CodeGenerator(Tree t){
		this.Tree = t;
	}
	
	public void init() {
		TokSeq bodySeq = Tree.assemblyCodeGenerating(new Register());
		TokCollector tc = new TokCollector(bodySeq);
		progSeq = tc.collect();
	}
	
	@Override
	public String toString() {
		return progSeq.toString();
	}



}
