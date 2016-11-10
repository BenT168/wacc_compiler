package frontEnd.ErrorHandling;

import java.util.ArrayList;

public class ErrorListener {

    ArrayList<Exception> errorList;

    public ErrorListener(){
        this.errorList = new ArrayList<Exception>();
    }

    public void record(Exception e) {
        errorList.add(e);
    }

    public String printErrors(){
        String errorPrint = "";
        for( Exception e: errorList) {
            errorPrint += e;
        }
        return errorPrint;
    }

    public boolean finished(){
        boolean correctProgram = errorList.isEmpty();

        if (!correctProgram){
            System.err.println(printErrors());
        }

        return correctProgram;
    }
}
