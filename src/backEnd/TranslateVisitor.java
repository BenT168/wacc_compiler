package backEnd;

import antlr.WACCParser;
import antlr.WACCParserBaseVisitor;
import backEnd.stat.VisitDeclPairNode;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.LinkedList;

public class TranslateVisitor extends WACCParserBaseVisitor<LinkedList<String>> {

    private LinkedList<String> instructions = new LinkedList<>();

    @Override
    public LinkedList<String> visitDeclare(@NotNull WACCParser.DeclareContext ctx) {
        instructions.add(ARMInstruction.pushNew());

        String type = ctx.type().getText();
        if(type.length() > 4) {
            //Check if pair
            if(type.substring(0, 4).compareTo("pair") == 0) {
                instructions.add(ARMInstruction.sub("sp", "sp", "4")); //move base pointer
                visit(ctx.assignRHS());
            }
        }
        return instructions;
    }

    @Override
    public LinkedList<String> visitAssignRHS(@NotNull WACCParser.AssignRHSContext ctx) {
        //Check if newpair
        if(ctx.NEWPAIR() !=  null) {
            String[] newpairElems = (new VisitDeclPairNode()).getElementsNewpair(ctx.getText());
            instructions.add(ARMInstruction.ldr("r0", newpairElems[0]));
            instructions.add(ARMInstruction.push("r0"));
        }

        return instructions;
    }

    public LinkedList<String> getInstructions() {
        return instructions;
    }




}
