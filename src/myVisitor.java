import antlr.BasicParser;
import antlr.BasicParserBaseVisitor;
import org.antlr.v4.runtime.misc.NotNull;


public class myVisitor extends BasicParserBaseVisitor<String> {

    /* Maps Types and Variable
     */
    private SymbolTable symbolTable = new SymbolTable();

    /* For Storing Variables and its information
     */
    private Assignments assignments = new Assignments();

    /* For calling semantic error     */
    private SemanticError semanticError = new SemanticError();



    //@Override
    public String visitOneStat(@NotNull BasicParser.StatContext ctx) {
        int exitValue; // in case of exit we have the exit code

        // Check first four letters of stat and match
        switch(ctx.getText().substring(0,4)) {
            //STAT of form: SKIP
            case "skip" : break;

            //STAT of form: EXIT expr
            case "exit" :
                String value = visitExpr(ctx.expr());
                if(!isParsable(value)) {
                    System.out.println(ctx.type());
                    assignments.exitCount = 1;
                    Type type = assignments.lookUpType(value);
                    if(!type.isInt()) {
                        semanticError.semanticErrorCase(value, "exit");
                    }
                    exitValue = assignments.lookUpInt(value);
                } else {
                    exitValue = Integer.parseInt(value);
                }
                System.exit(exitValue);
                break;

            //STAT of form: FREE
            case "free" : //TODO
                break;

            //STAT of form: READ
            case "read" : //TODO
                break;
        }

        /* Check if current token is a type
         */
        Boolean isBool   = ctx.getText().substring(0,4).compareTo("bool")   == 0;
        Boolean isInt    = ctx.getText().substring(0,3).compareTo("int")    == 0;
        Boolean isChar   = ctx.getText().substring(0,4).compareTo("char")   == 0;
        Boolean isString = ctx.getText().substring(0,6).compareTo("string") == 0;

        Boolean isType = isBool | isInt | isChar | isString;

        if(isType) {
            /* STAT of form: type IDENTITY EQUALS assignRHS
             */

            //Get Type of statement
            String sType = visitType(ctx.type());
            Type type = new Type(sType);

            //Get Variable name e.g x
            String varName = ctx.IDENTITY().getText();

            // Get expression
            String value = visitExpr(ctx.assignRHS().expr(0));

            //Initialise Variable with name and type
            Variable variable = new Variable(varName, type);

            //Check what value expression is

            // Check if integer
            if (isParsable(value)) {
                int intValue;
                intValue = Integer.parseInt(value);
                variable.setIntValue(intValue);

                // Check if boolean
            } else if (value.compareTo("true") == 0 || value.compareTo("false") == 0) {
                boolean boolValue = true;
                if (value.compareTo("false") == 0) {
                    boolValue = false;
                }
                variable.setBoolValue(boolValue);

                //Check if char
            } else if(value.length() == 3) { //Chars are of form 'x'
                char test = "'".charAt(0);
                if(value.charAt(0) == test && value.charAt(2) == test) {
                    char charValue = value.charAt(1);
                    variable.setCharValue(charValue);
                }

                //Otherwise it is a String
            } else {
                variable.setStringValue(value);
            }

            //Add variable to Assignments
            assignments.add(variable);
            //Add type and varName to symboltable
            symbolTable.add(varName, type);
        }

    /*    if(ctx.getText().substring(0,6).compareTo("return") == 0) {

        }

        switch(ctx.getText().substring(0,6)) {
            case "return" :
        }

        switch(ctx.getText().substring(0,9)) {
            case "assignLHS" :
        }
        switch(ctx.getText().substring(0,6)) {
            case "return" :
        }*/
        //System.out.println(ctx.SEMI_COLON());
        return visitChildren(ctx);
    }


    @Override
    public String visitStat(@NotNull BasicParser.StatContext ctx) {
        int numberOfStats = ctx.stat().size();
        //System.out.println(numberOfStats);

        // For just one stat
        if(numberOfStats == 0) {
            visitOneStat(ctx);
        }

        //For multiple stats
        //System.out.println(ctx.stat(0).getText());
       // for(int i = 0; i < numberOfStats; i++) {
            //System.out.println("in  here");
            //System.out.println(ctx.stat(0).getText());
           // visitOneStat(ctx.stat(i));
        //}
        return visitChildren(ctx);
    }


    @Override
    public String visitExpr(@NotNull BasicParser.ExprContext ctx) {
        //System.out.println("expr " + ctx.getText());
        return ctx.getText();
    }

    @Override
    public String visitType(@NotNull BasicParser.TypeContext ctx) {
        //System.out.println("type " + ctx.getText());
        return ctx.getText();
    }

    //Helper Method to check if string given can be converted to an integer
    private boolean isParsable(String input) {
        Boolean parsable = true;
        try {
            Integer.parseInt(input);
        } catch(NumberFormatException e) {
            parsable = false;
        }
        return parsable;
    }


    public void checkVariablesAreAdded() {
        assignments.printList();
    }






}
