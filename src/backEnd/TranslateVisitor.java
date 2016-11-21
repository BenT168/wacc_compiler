package backEnd;

import antlr.WACCParser;
import antlr.WACCParserBaseVisitor;
import backEnd.stat.VisitDeclPairNode;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.LinkedList;

public class TranslateVisitor extends WACCParserBaseVisitor<LinkedList<String>> {

    private LinkedList<String> instructions = new LinkedList<>();
    private String[] reg = {"r0", "r1","r2","r3","r4","r5","r6","r7","r8",
                                  "r9","r10","r11","r12","r13","r14","r15","r16"};
    private boolean inFunction = false;
    private int posOfnewpair = 0;
    private int mainSeen = 0;



    @Override
    public LinkedList<String> visitFunc(@NotNull WACCParser.FuncContext ctx) {
        inFunction = true;

        //after everything

        inFunction = false;
        return visitChildren(ctx);
    }


    @Override
    public LinkedList<String> visitDeclare(@NotNull WACCParser.DeclareContext ctx) {

        //If not in funtion then return label main:
        checkMainSeen();

        //push{lr}
        instructions.add(ARMInstruction.pushNew());

        String type = ctx.type().getText();

        //Check if pair type
        if(type.length() > 4) {
            //Check if pair
            if(type.substring(0, 4).compareTo("pair") == 0) {

                //move base pointer
                instructions.add(ARMInstruction.sub("sp", "sp", 4));

                VisitDeclPairNode vpnode = new VisitDeclPairNode();

                //start popping registers at 1
                int posPop = 1;
                //Popr is first register to pop reg[1]
                String popr = reg[posPop];

                //Malloc the types in pair
                int[] mallocs = vpnode.spaceMalloc(type);
                for(int i = 0; i < 2; i++) {
                    posOfnewpair = i;
                    visit(ctx.assignRHS());
                    instructions.add(ARMInstruction.mov(reg[0], mallocs[i]));
                    instructions.add(ARMInstruction.branchwlink("malloc"));
                    instructions.add(ARMInstruction.pop(popr));
                    if(mallocs[i] == 1) {
                        //store in STRB
                        instructions.add(ARMInstruction.strb(popr, reg[0]));
                    } else {
                        instructions.add(ARMInstruction.str(popr, reg[0]));
                    }
                    instructions.add(ARMInstruction.push(reg[0]));
                }

                //malloc pair itself
                vpnode.mallocPair(instructions, popr, posPop);
            }
        }
        return instructions;
    }

    @Override
    public LinkedList<String> visitAssignRHS(@NotNull WACCParser.AssignRHSContext ctx) {
        //Check if newpair
        if(ctx.NEWPAIR() !=  null) {
            VisitDeclPairNode vpnode = new VisitDeclPairNode();

            //Get elements of newpair (fst, snd)
            String[] newpairElems = vpnode.getElementsNewpair(ctx.getText(), 8);

            //If element is an int - then ldr, otherwise use mov instruction
            if(ARMInstruction.isParsable(newpairElems[posOfnewpair].trim())) {
                instructions.add(ARMInstruction.ldr(reg[0], newpairElems[posOfnewpair]));
            } else {
                instructions.add(ARMInstruction.mov(reg[0], newpairElems[posOfnewpair]));
            }
            instructions.add(ARMInstruction.push(reg[0]));
        }

        return instructions;
    }


    public LinkedList<String> getInstructions() {
        return instructions;
    }


    /*Function to determine when to have label main: */
    private void checkMainSeen() {
        if(mainSeen == 0 && !inFunction) {
            instructions.add("main:");
            mainSeen++;
        }
    }




}
