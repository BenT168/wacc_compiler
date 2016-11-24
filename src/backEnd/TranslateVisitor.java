package backEnd;

import antlr.WACCParser;
import antlr.WACCParserBaseVisitor;
import backEnd.stat.VisitDeclPairNode;
import frontEnd.SymbolTable;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.Arrays;
import java.util.LinkedList;

public class TranslateVisitor extends WACCParserBaseVisitor<LinkedList<String>> {

    private LinkedList<String> instructions = new LinkedList<>(Arrays.asList(".global main"));
    private String[] reg = {"r0","r1","r2","r3","r4","r5","r6","r7","r8",
                                  "r9","r10","r11","r12","r13","r14","r15","r16"};
    //Number of declarations to set initial store
    private int numberOfDeclare = 0;

    //SymbolTable made in TypeCheckVisitor
    private SymbolTable table;

    //For functions
    private boolean inFunction = false;
    private int posOfnewpair = 0;
    private int mainSeen = 0;
    private int PositionToPop = 0;
    private int countDeclarations = 0;


    @Override
    public LinkedList<String> visitFunc(@NotNull WACCParser.FuncContext ctx) {
        inFunction = true;
        String funcName = ctx.ident().getText();
        instructions.add(funcName+":");
        //push{lr}
        instructions.add(ARMInstruction.pushNew());

        inFunction = false;
        return visitChildren(ctx);
    }


    @Override
    public LinkedList<String> visitDeclare(@NotNull WACCParser.DeclareContext ctx) {
        //If not in funtion then return label main:
        checkMainSeen();

        System.out.println(ctx.getText());

        //Get type in declaration
        String type = ctx.type().getText();

        //Check if pair type
        if(type.length() > 4) {
            //Check if pair
            if(type.substring(0, 4).compareTo("pair") == 0) {
                VisitDeclPairNode vpnode = new VisitDeclPairNode(this);
                if(numberOfDeclare == 1) {
                    visitDeclareNewpair(ctx);
                    //STR
                    instructions.add(ARMInstruction.str(reg[0], "sp"));
                } else if(numberOfDeclare > 1) {
                //Check that assignRHS is newpair
                    countDeclarations++;
                if(ctx.assignRHS().NEWPAIR() != null) {
                    visitDeclareNewpair(ctx);
                } else {
                    String expr = ctx.assignRHS().expr(0).getText();
                    //Check if expression and if variable is in symboltable
                    if (expr != null && table.intotalVTable(expr)) { // assigned to an expression
                        instructions.add(ARMInstruction.str(reg[0], "sp", 4));
                        instructions.add(ARMInstruction.ldr(reg[0], "sp", 4));
                    }
                }
                }
                //Check at end of declaration
                if(countDeclarations == numberOfDeclare) {
                    vpnode.endMalloc(instructions);
                }
            }
        }
        return instructions;
    }


    private void visitDeclareNewpair(@NotNull WACCParser.DeclareContext ctx) {
        VisitDeclPairNode vpnode = new VisitDeclPairNode(this);

        //start popping registers at 1
        int posPop = 1;

        //Update PositionToPop
        PositionToPop = posPop;
        //Popr is first register to pop reg[1]
        String popr = reg[posPop];

        //Malloc the types in pair
        int[] mallocs = vpnode.spaceMalloc(ctx);
        for(int i = 0; i < 2; i++) {
            posOfnewpair = i;
            visit(ctx.assignRHS());
            instructions.add(ARMInstruction.mov(reg[0], mallocs[i]));
            instructions.add(ARMInstruction.branchwlink("malloc"));
            instructions.add(ARMInstruction.pop(popr));
            //malloc byte if necessary
            vpnode.mallocByte(mallocs, i, popr, instructions);
        }
        //malloc pair itself
        vpnode.mallocPair(instructions, popr, posPop);
    }


    @Override
    public LinkedList<String> visitAssignRHS(@NotNull WACCParser.AssignRHSContext ctx) {
        //Check if newpair
        if(ctx.NEWPAIR() !=  null) {
            VisitDeclPairNode vpnode = new VisitDeclPairNode(this);

            //Get elements of newpair (fst, snd)
            String[] newpairElems = vpnode.getElementsNewpair(ctx);

            //If element is an int - then ldr, otherwise use mov instruction
            if(ARMInstruction.isParsable(newpairElems[posOfnewpair].trim())) {
                //LDR
                instructions.add(ARMInstruction.ldr(reg[0], newpairElems[posOfnewpair]));
            } else {
                //MOV
                instructions.add(ARMInstruction.mov(reg[0], newpairElems[posOfnewpair]));
            }
            //PUSH
            instructions.add(ARMInstruction.push(reg[0]));
        }
        return instructions;
    }

    @Override
    public LinkedList<String> visitFree(@NotNull WACCParser.FreeContext ctx) {
        instructions.add(ARMInstruction.ldr(reg[0], "sp"));
        instructions.add(ARMInstruction.branchwlink("p_free_pair"));
        return visitChildren(ctx);
    }





    public LinkedList<String> getInstructions() {
        return instructions;
    }


    /*Function to determine when to have label main: */
    private void checkMainSeen() {
        if(mainSeen == 0 && !inFunction) {
            instructions.add("main:");
            mainSeen++;

            //push{lr}
            instructions.add(ARMInstruction.pushNew());

            //move base pointer -> SUB sp sp #offset
            int offset = 4 * numberOfDeclare; //calculates size of offset
            instructions.add(ARMInstruction.sub("sp", "sp", offset));
        }
    }

    public void setNumberOfDeclare(int numberOfDeclare) {
        this.numberOfDeclare = numberOfDeclare;
    }

    public int getNumberOfDeclare() {
        return numberOfDeclare;
    }

    public void setTable(SymbolTable table) {
        this.table = table;
    }
}
