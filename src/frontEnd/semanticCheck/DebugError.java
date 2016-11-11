package frontEnd.semanticCheck;

//import org.apache.commons.cli.CommandLine;

public class DebugError {
    private boolean testEnvironment = false;
    private boolean debuggingEnvironment = false;

    public DebugError() {
        this.testEnvironment = false;
        this.debuggingEnvironment = false;
        }

    public void printV(String s) {
                if(testEnvironment)
                        System.out.println(s);
        }

        public void printD(String s) {
                if(debuggingEnvironment)
                        System.out.println(s);
        }

/*        public void setOption(CommandLine cmd){
                if(cmd.hasOption("v")){
                        this.testEnvironment = true;
                }
                if(cmd.hasOption("d")){
                        this.debuggingEnvironment = true;
                }
        }

  */      public void printD(int indentation, String text) {
                String res = "";
                for (int i = 0; i < indentation; i++) {
                        res += '\t';
                }
                printD(res + text);
        }
}

