package frontEnd.visitor;

import frontEnd.semanticCheck.SemanticError;
import frontEnd.antlr.BasicParser;
import frontEnd.antlr.BasicParserBaseVisitor;
import frontEnd.tree.Type;
import frontEnd.tree.Variable;
import org.antlr.v4.runtime.misc.NotNull;
import symbolTable.SymbolTable;


public class myVisitor extends BasicParserBaseVisitor<String> {

    /* For Storing Variables and its information
     */
    private SymbolTable symbolTable = new SymbolTable();

    /* For calling semantic error     */
    private SemanticError semanticError = new SemanticError();



    @Override
    public String visitProgram(@NotNull BasicParser.ProgramContext ctx) {
        System.out.println(ctx.start.getLine());
        //if(ctx.BEGIN())
        //assignments = new Assignments();
        return visitChildren(ctx);
    }

    @Override
    public String visitStat(@NotNull BasicParser.StatContext ctx) {
        int numberOfStats = ctx.stat().size();

        // Go through multiple stats
        if(numberOfStats == 0) {
            visitOneStat(ctx);
        }
        return visitChildren(ctx);
    }

    //@Override
    public String visitOneStat(@NotNull BasicParser.StatContext ctx) {
        int exitValue; // in case of exit we have the exit code

        // Check first two letters of stat and match
        switch(ctx.getText().substring(0,2)) {
            case "if" : //TODO
                break;
        }

        if(ctx.getText().length() > 2)

        // Check first four letters of stat and match
        switch(ctx.getText().substring(0,4)) {
            //STAT of form: SKIP
            case "skip" : break;

            //STAT of form: EXIT expr
            case "exit" :
                String value = visitExpr(ctx.expr());
                if(!isParsable(value)) {
                    symbolTable.exitCount() = 1;
                    Type type = symbolTable.lookUpType(value);
                    if(!type.isInt()) {
                        semanticError.semanticErrorCase(value, "exit");
                    }
                    exitValue = symbolTable.lookUpInt(value);
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

        if(ctx.getText().length() > 5)

        // Check first two letters of stat and match
        switch(ctx.getText().substring(0,5)) {
            case "begin" :
                SymbolTable newScope = new SymbolTable();
                SymbolTable tmp = symbolTable;
                newScope.setParentSymbolTable(tmp);
                symbolTable = newScope;
            case "print" : //TODO
                break;
            case "while" : //TODO
        }


        System.out.println(ctx.getText().length());
        System.out.println(ctx.getText());

        /* Check if current token is a type
         */
        Boolean isString = false;
        Boolean isBool   = ctx.getText().substring(0,4).compareTo("bool")   == 0;
        Boolean isInt    = ctx.getText().substring(0,3).compareTo("int")    == 0;
        Boolean isChar   = ctx.getText().substring(0,4).compareTo("char")   == 0;
         if(ctx.getText().length() > 5) {
             isString = ctx.getText().substring(0, 6).compareTo("string") == 0;
         }

        Boolean isType = isBool | isInt | isChar | isString;

        if(isType) {
            //STAT of form: type IDENTITY EQUALS assignRHS

            //Get Type of statement
            String sType = visitType(ctx.type());
            Type type = new Type(sType);

            //Get Variable name e.g x
            String varName = ctx.IDENTITY().getText();

            // Get expression of RHS
            String value = visitExpr(ctx.assignRHS().expr(0));

            //Initialise Variable with name and type
            Variable variable = new Variable(varName, type);

            //Check what value of RHS expression is

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
            symbolTable.add(variable);
        }

        if(ctx.getText().length() >= 6) {
            switch (ctx.getText().substring(0, 6)) {
                case "return": //TODO
                    break;
            }
        }

        if(ctx.getText().length() >= 7) {
            switch (ctx.getText().substring(0, 7)) {
                case "println": //TODO
                    break;
            }
        }

        if (ctx.getText().length() >= 9) {
            switch (ctx.getText().substring(0, 9)) {
                case "assignLHS": //TODO
            }
        }

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
        symbolTable.printList();
    }






}