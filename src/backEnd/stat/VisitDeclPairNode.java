package backEnd.stat;

import antlr.WACCParser;

public class VisitDeclPairNode {

    private WACCParser.DeclareContext ctx;
    private int[] spaceMalloc = new int[2];

    public String[] getElementsNewpair(String newpair) {
        String[] elements = new String[2];
        char[] fst = new char[newpair.length()];
        char[] snd = new char[newpair.length()];

        int i = 8; //starts at the first element of newpair(fst, snd)
        while(newpair.charAt(i) != ',') {
            fst[i] = newpair.charAt(i);
            i++;
        }

        i = i + 2; // skip the ',' and space
        while(newpair.charAt(i) != ')') {
            snd[i] = newpair.charAt(i);
        }
        String fstStr = String.valueOf(fst);
        String sndStr = String.valueOf(snd);

        elements[0] = fstStr;
        elements[1] = sndStr;

        return elements;
    }
}
