package frontend.exception;

import java.util.ArrayList;


public class ErrorListener {
	
	ArrayList<Exception> errorList;
	
	public ErrorListener() {
		this.errorList = new ArrayList<>();
	}
	
	public void addedIntoErrList(Exception e) {
		this.errorList.add(e);
	}
	
	public String printErr() {
		String errorPrintout = "";
		for (Exception e : errorList) {
			errorPrintout += e.toString();
		}
		
		return errorPrintout;
	}

	public int errorCount() {
		return errorList.size();
	}

	public boolean complete() {
		boolean correctProgram = errorCount() == 0;
		if (!correctProgram) {
			System.err.println(printErr());
		}
		return correctProgram;
	}
}
