package backend;

import java.util.HashMap;
import java.util.Map;

public class RegAlloc {
	
	private int currReg;
	private Map<Register, Integer> regMap;

	/* constructor */
	public RegAlloc() {
		this.currReg = 4;
		regMap = new HashMap<>();
	}

	/* introduces additional register */
	private int getNewReg() {
		return currReg++;
	}

	/* allocates register if not already allocated nor pre-allocated register */
	public void allocate(Register r) {
		if (!regMap.containsKey(r) && !(r.isPreAlloc())) {
			int regIndex = getNewReg();
			r.setReg(regIndex);
			regMap.put(r, regIndex);
		}
	}
	
}
