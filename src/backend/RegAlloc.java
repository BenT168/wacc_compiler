package backend;

import java.util.HashMap;
import java.util.Map;

public class RegAlloc {
	
	private int currReg;
	private Map<Register, Integer> regMap;
	
	public RegAlloc() {
		this.currReg = 4;
		regMap = new HashMap<>();
	}

	private int getNewReg() {
		return currReg++;
	}

	public void allocate(Register r) {
		if( !regMap.containsKey(r) && !(r.isPreAlloc())) {
			int regIndex = getNewReg();
			r.setReg(regIndex);
			regMap.put(r, regIndex);
		}
	}
	
}
