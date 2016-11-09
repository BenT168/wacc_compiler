package frontEnd.visitor;

import antlr.BasicParser;
import antlr.BasicParserBaseVisitor;
import frontEnd.ErrorHandling.InvalidTypeException;
import frontEnd.semanticCheck.SemanticError;
import frontEnd.tree.Identifier;
import frontEnd.tree.Parameter.Scalar;
import frontEnd.tree.Type.ArrayType;
import frontEnd.tree.Type.BaseType;
import frontEnd.tree.Type.PairType;
import frontEnd.tree.Variable;
import org.antlr.v4.runtime.misc.NotNull;
import symbolTable.SymbolTable;

import java.lang.reflect.Type;


public class myVisitor extends BasicParserBaseVisitor<String> {

    /* For Storing Variables and its information
     */
    private SymbolTable TopSymbolTable = new SymbolTable(null);
    private SymbolTable ST = TopSymbolTable;

    /* For calling semantic error     */
    private SemanticError semanticError = new SemanticError();

    private int arrayDepth = 0;
    private int frees = 0;

    //................................PROGRAM.........................................
    @Override
    public String visitProgram(@NotNull BasicParser.ProgramContext ctx) {
        ST.add("int", new Scalar(-2147483648, 2147483647));
        ST.add("char", new Scalar(0, 255));
        ST.add("bool", new Scalar(0, 1));

        //System.out.println(ctx.start.getLine());

        return visitChildren(ctx);
    }

    //....................................STAT........................................
    /*PRINT expr*/
    @Override
    public String visitPrint(@NotNull BasicParser.PrintContext ctx) {
        visit(ctx.expr());
        return visitChildren(ctx);
    }

    /*PRINTLN expr*/
    @Override
    public String visitPrintln(@NotNull antlr.BasicParser.PrintlnContext ctx) {
        visit(ctx.expr());
        return visitChildren(ctx);
    }

    /*READ assignLHS */
    @Override
    public String visitRead(@NotNull BasicParser.ReadContext ctx) {
        visit(ctx.assignLHS());
        return visitChildren(ctx);
    }

    @Override
    public String visitAssignLHS(@NotNull BasicParser.AssignLHSContext ctx) {
        BaseType type;
        try {
            type = ((Variable) ST.lookUpAll(visit(ctx.IDENTITY()))).getType();
       } catch(NullPointerException n) {
           //Check arrayElem
       }

       try {
           type = ((Variable) ST.lookUpAll(visit(ctx.arrayElem()))).getType();
        } catch (NullPointerException n) {
           //Check pairElem
       }

        try {
          type = ((Variable) ST.lookUpAll(visit(ctx.pairElem()))).getType();
        } catch (NullPointerException n) {
            semanticError.semanticErrorCase(ctx.getText(), "notInitialised");
        }

        return visitChildren(ctx);
    }


    @Override
    public String visitArrayElem(@NotNull BasicParser.ArrayElemContext ctx) {
        for(int i = 0; i < ctx.expr().size(); i++) {
            if(!isParsable(ctx.expr(i).getText())) {
                semanticError.semanticErrorCase(ctx.expr(i).getText(), "exit");
             } else if(Integer.parseInt(ctx.expr(i).getText()) < 0) {
                throw new ArrayIndexOutOfBoundsException();
            }
        }
        return ctx.IDENTITY().getText();
    }

    @Override
    public String visitPairElem(@NotNull BasicParser.PairElemContext ctx) {
        String pairExpr = "";
        try {
            pairExpr = ctx.FST().getText();
        } catch(NullPointerException n) {
            //Check snd
        }

        try {
            pairExpr = ctx.SND().getText();
        } catch (NullPointerException n) {

        }
        return pairExpr;
    }



    /*EXIT expr */
    @Override
    public String visitExit(@NotNull BasicParser.ExitContext ctx) {
        String value = visitExpr(ctx.expr());
        //Check if a letter
        if(!isParsable(value)) {
            //Look up to see if letter is a variable in symbolTable
            BaseType type = ((Variable) ST.lookUp(value)).getType();
            if (type != BaseType.INT) {
                //If variable type is not an int, throw an error
                semanticError.semanticErrorCase(value, "exit");
            }
        }
        return visitChildren(ctx);
    }

    /* type IDENTITY EQUALS assignRHS  */
    @Override
    public String visitDeclare(@NotNull BasicParser.DeclareContext ctx) {
        System.out.println(ctx.type().getText());
        String type = visit(ctx.type());
        String name = ctx.IDENTITY().getText();
        String rhsExpr = visit(ctx.assignRHS());

        if(!isArray(type) && !isPair(type)) {
            //Just a base type
            Variable variable = new Variable(fromStringType(type), rhsExpr);
            ST.add(name, variable);
        } else if(isArray(type)) {

            //Array Type
            ArrayType arrayType =
                    recurseinitArray(fromStringType(reduceStringArray(type)));
            Variable variable = new Variable(arrayType, rhsExpr);
            ST.add(name, variable);
        } else if(isPair(type)) {



        }

        //Variable variable = new Variable(baseType, )

        return visitChildren(ctx);
    }

    @Override
    public String visitFree(@NotNull BasicParser.FreeContext ctx) {
        frees++;
        String expr = visit(ctx.expr());
        BaseType type = ((Variable) ST.lookUp(expr)).getType();
        if(!(type instanceof PairType)) {
            semanticError.semanticType("Pair", type.toString());
        }
        if(frees > 1) {
            throw new RuntimeException();
        }
        return visitChildren(ctx);
    }




    @Override
    public String visitBaseType(@NotNull BasicParser.BaseTypeContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public String visitArrayType(@NotNull BasicParser.ArrayTypeContext ctx) {

        return visitChildren(ctx);
    }

    @Override
    public String visitPairType(@NotNull BasicParser.PairTypeContext ctx) {
        String typeOne = visit(ctx.pairElemType(0));
        String typeTwo = visit(ctx.pairElemType(1));
//        pairTypes[0] = typeOne;
//        pairTypes[1] = typeTwo;

        //Check

        //PairType pair = new PairType(typeOne, typeTwo);



        return visitChildren(ctx);
    }

    private void typeShouldBeInt(@NotNull BasicParser.ExprContext ctx) {
        String ex1 = visit(ctx.expr(0));
        System.out.println(ex1);
        String ex2 = ctx.expr(1).getText();
        if(!isParsable(ex1) || !isParsable(ex2)) {
            semanticError.semanticErrorCase(ex1, "exit");
        }
    }

    private void typeCanBeAny(@NotNull BasicParser.ExprContext ctx) {
        if (ctx.expr(0).intLiter() != null && ctx.expr(1).intLiter() != null) {
            //do nothing
        } else if (ctx.expr(0).boolLiter() != null && ctx.expr(1).boolLiter() != null) {

        } else if (ctx.expr(0).charLiter() != null && ctx.expr(1).charLiter() != null) {

        } else if (ctx.expr(0).STRING_LITER() != null && ctx.expr(1).STRING_LITER() != null) {

        } else {
            //types not equal
            semanticError.semanticErrorCase(ctx.LT().getText(), "exit");
        }
    }

    private void typeShouldbeBool(@NotNull BasicParser.ExprContext ctx) {
        try {
            ctx.expr(0).boolLiter();
        } catch(NullPointerException n) {
            //should be between boolean
            semanticError.semanticErrorCase("bool", "exit");
        }

    }


    @Override
    public String visitExpr(@NotNull BasicParser.ExprContext ctx) {
        try{
            ctx.MULTI();
            typeShouldBeInt(ctx);
        } catch (NullPointerException n) {
            //Check DIV
        }


        try{
            ctx.DIV();
            typeShouldBeInt(ctx);
            if(Integer.parseInt(ctx.expr(1).getText()) == 0) {
                throw new RuntimeException();
            }
        } catch (NullPointerException n) {
            //CHECK MOD
        }


        try{
            ctx.MOD();
            typeShouldBeInt(ctx);
        } catch (NullPointerException n) {
            //CHECK PLUS
        }

        try{
            ctx.PLUS();
            typeShouldBeInt(ctx);
        } catch (NullPointerException n) {
            //CHECK MINUS
        }

        try{
            ctx.MINUS();
            typeShouldBeInt(ctx);
        } catch (NullPointerException n) {
            //CHECK LESS THAN

        }
        try{
            ctx.LT();
            typeCanBeAny(ctx);
        } catch (NullPointerException n) {
            //CHECK GT
        }

        try{
            ctx.GT();
            typeCanBeAny(ctx);
        } catch (NullPointerException n) {
            //CHECK LTE
        }

        try{
            ctx.LTE();
            typeCanBeAny(ctx);
        } catch (NullPointerException n) {

        }try{
            ctx.GTE();
            typeCanBeAny(ctx);
        } catch (NullPointerException n) {

        }try{
            ctx.EQ();
            typeCanBeAny(ctx);
        } catch (NullPointerException n) {

        }try{
            ctx.NEQ();
            typeCanBeAny(ctx);
        } catch (NullPointerException n) {

        }try{
            ctx.AND();
            typeShouldbeBool(ctx);
        } catch (NullPointerException n) {

        }
        try{
            ctx.OR();
            typeShouldbeBool(ctx);
        } catch (NullPointerException n) {

        }
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

     private BaseType fromStringType(String stype) {
         switch(stype) {
             case "int" :
                 return BaseType.INT;
             case "bool" :
                 return BaseType.BOOL;
             case "char" :
                 return BaseType.CHAR;
             case "string" :
                 return BaseType.STRING;
         }
         return null;
     }

     private String  reduceStringArray(String stype) {
         char[] array = new char[6];
         if(isArray(stype)) {
             int i = 0;
             while(stype.charAt(i) != '[') {
                 array[i] = stype.charAt(i);
                 i++;
             }
         }
         return String.valueOf(array);
     }

     private boolean isArray(String type) {
        char bracket = '[';
         boolean array = false;
         int numofbrackets = 0;
         for(int i = 0; i < type.length(); i++) {
             if(type.charAt(i) == bracket) {
                 numofbrackets++;
                 array = true;
             }
         }
         arrayDepth = numofbrackets;
         return array;
     }

     private boolean isPair(String type) {
         if(type.length() >= 4) {
             return type.substring(0,4).compareTo("pair") == 0;
         }
         return false;

     }

     private ArrayType recurseinitArray(BaseType type) {
         if(arrayDepth == 1) {
             return new ArrayType(type);
         } else {
             arrayDepth--;
             return new ArrayType(recurseinitArray(type));
         }
     }




//    public void checkVariablesAreAdded() {
//        symbolTable.printList();
//    }






}
