package backEnd.stat;

import antlr.WACCParser;
import backEnd.ARMInstructions;
import backEnd.CodeGenVisitor;

import java.util.LinkedList;

public class VisitDeclPairNode {

    private String[] reg = {"r0", "r1","r2","r3","r4","r5","r6","r7","r8",
            "r9","r10","r11","r12","r13","r14","r15","r16"};
    private CodeGenVisitor visitor;


    public VisitDeclPairNode(CodeGenVisitor visitor) {
        this.visitor = visitor;
    }

    //Instruction of mallocing the pair
    public void mallocPair(LinkedList<String> instructions, String popr, int posPop) {
        //Space for the pair itself
        //MOV
        instructions.add(ARMInstructions.MOV.printWithImm(reg[0], "8"));
        //BL
        instructions.add(ARMInstructions.BL.printWithString("malloc"));
        String popPrev = popr;
        popr = reg[posPop+1];
        //POP
        instructions.add(ARMInstructions.POP.printWithReg(popPrev, popr));
        //STR
        instructions.add(ARMInstructions.STR.printWithReg(popr, reg[0]));

        //move base pointer back
        //STR
        instructions.add(ARMInstructions.STR.printWithAddrReg(popPrev, reg[0], 4));
    }

    public void endMalloc(LinkedList<String> instructions) {
        //ADD
        int offset = 4 * visitor.getNumberOfDeclare();
        instructions.add(ARMInstructions.ADD.printWithOffset("sp", offset));
        //MOV
        instructions.add(ARMInstructions.MOV.printWithImm(reg[0], "0"));
        //POP
        instructions.add(ARMInstructions.POP_PC);
    }


    //Gives instruction of mallocing a byte
    public void mallocByte(int[] mallocs, int i, String popr, LinkedList<String> instructions) {
        if(mallocs[i] == 1) {
            //store in STRB
            instructions.add(ARMInstructions.STRB.printWithReg(popr, reg[0]));
        } else {
            //STR
            instructions.add(ARMInstructions.STR.printWithReg(popr, reg[0]));
        }
        //PUSH
        instructions.add(ARMInstructions.PUSH.printWithReg(reg[0]));
    }



    //Returns int array of space to be allocated by types
    //[int, int] -> [4,4]
    public int[] spaceMalloc(WACCParser.DeclareContext ctx) {
        String[] types = getElementsNewpair(ctx);
        int mallocSize1 = checkMallocSize(types[0]);
        int mallocSize2 = checkMallocSize(types[1]);

        int[] mallocs = new int[2];
        mallocs[0] = mallocSize1;
        mallocs[1] = mallocSize2;

        return mallocs;

    }

    //Check type so that can malloc appropriate size
    //int -> 4 char -> 1
    private int checkMallocSize(String type) {
        switch(type.trim()){
            case "int" :
                return 4; //int has 4 bytes
            case "char" :
                return 1; //char has 1 byte
            case "bool" :
                return 1; //bool has 1 byte
        }
        return 0;
    }


    //Returns array of elements of newpair(fst, snd) -> [fst, snd]
    public String[] getElementsNewpair(WACCParser.AssignRHSContext ctx) {
        String[] elements = new String[2];
        elements[0] = ctx.expr(0).getText();
        elements[1] = ctx.expr(1).getText();
        return elements;
    }

    //Returns array of pairtypes, pair(type1, type2) -> [type1, type2]
    public String[] getElementsNewpair(WACCParser.DeclareContext ctx) {
        String[] typeElements = new String[2];
        typeElements[0] = ctx.type().pairType().pairElemType(0).getText();
        typeElements[1] = ctx.type().pairType().pairElemType(1).getText();
        return typeElements;
    }
}
