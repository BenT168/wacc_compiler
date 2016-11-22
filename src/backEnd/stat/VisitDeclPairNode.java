package backEnd.stat;

import backEnd.OLDARMInstructions;

import java.util.LinkedList;

public class VisitDeclPairNode {

    private String[] reg = {"r0", "r1","r2","r3","r4","r5","r6","r7","r8",
            "r9","r10","r11","r12","r13","r14","r15","r16"};

    public void mallocPair(LinkedList<String> instructions, String popr, int posPop) {
        instructions.add(OLDARMInstructions.mov(reg[0], 8));
        instructions.add(OLDARMInstructions.branchwlink("malloc"));
        String popPrev = popr;
        popr = reg[posPop+1];
        instructions.add(OLDARMInstructions.pop(popPrev, popr));
        instructions.add(OLDARMInstructions.str(popr, reg[0]));

        //move base pointer back
        instructions.add(OLDARMInstructions.str(popPrev, reg[0], 4));
        instructions.add(OLDARMInstructions.str(reg[0], "sp"));
        instructions.add(OLDARMInstructions.add("sp", "sp",4));
        instructions.add(OLDARMInstructions.mov(reg[0], 0));
        instructions.add(OLDARMInstructions.ret());
    }



    public int[] spaceMalloc(String pairType) {
        String[] types = getElementsNewpair(pairType, 5);
        int mallocSize1 = checkMallocSize(types[0]);
        int mallocSize2 = checkMallocSize(types[1]);

        int[] mallocs = new int[2];
        mallocs[0] = mallocSize1;
        mallocs[1] = mallocSize2;

        return mallocs;

    }

    private int checkMallocSize(String type) {
        switch(type.trim()){
            case "int" :
                return 4; //int has 4 bytes
            case "char" :
                return 1; //char has 1 byte
//            case "string" :
//                return getElementsNewpair(ctx.getText(), 5)[0].length();
//            //string has lengthofString bytes
            case "bool" :
                return 1; //bool has 1 byte
        }
        return 0;
    }

    public String[] getElementsNewpair(String newpair, int posStart) {
        assert(posStart == 5 || posStart == 8);

        String[] elements = new String[2];
        char[] fst = new char[newpair.length()];
        char[] snd = new char[newpair.length()];


        //i = 8 starts at the first element of newpair(fst, snd)
        //i = 5 starts at the first element of pair(type1, type2)
        int i = posStart;
        int j = 0;
        while(newpair.charAt(i) != ',') {
            fst[j] = newpair.charAt(i);
            i++;
            j++;
        }

        i = i + 1; // skip the ',' and space
        j = 0;
        while(newpair.charAt(i) != ')') {
            snd[j] = newpair.charAt(i);
            i++;
            j++;
        }
        String fstStr = String.valueOf(fst);
        String sndStr = String.valueOf(snd);

        elements[0] = fstStr;
        elements[1] = sndStr;

        return elements;
    }
}
