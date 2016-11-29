package backEnd;
import antlr.WACCParser;
import antlr.WACCParserBaseVisitor;
import backEnd.helper.Function;
import backEnd.helper.Registers;
import backEnd.helper.Utils;
import backEnd.helper.Variable;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class SystemReadTokens<Void> extends WACCParserBaseVisitor<Void> {

    private static final String defaultString = "\"%.*s\\0\"";
    private static final String defaultTrue = "\"true\\0\"";
    private static final String defaultFalse = "\"false\\0\"";
    private static final String defaultInt = "\"%d\\0\"";
    private static final String defaultPrint = "\"\\0\"";
    private static final String defaultOverflowError = "\"OverflowError: the result"
            + " is too small/large to store in a 4-byte signed-integer.\\n\"";
    private static final String defaultDivideByZero = "\"DivideByZeroError: divide or modulo by zero\\n\\0\"";
    private static final String defaultArrayNegativeIndex = "\"ArrayIndexOutOfBoundsError: negative index\\n\\0\"";
    private static final String defaultArrayIndexOutOfBounds = "\"ArrayIndexOutOfBoundsError: index too large\\n\\0\"";
    private static final String defaultFree = "\"NullReferenceError: dereference a null reference\\n\\0\"";
    private static final String defaultPrintAddress = "\"%p\\0\"";
    private static final String defaultReadChar = "\" %c\\0\"";
    private static final String defaultReadInt = "\"%d\\0\"";
    private static final String MESSAGE_LABEL = "\tmsg_";
    private static final String WORD = "\t\t.word ";
    private static final String ASCII = "\t\t.ascii\t";

    private ArrayList<String> messages = new ArrayList<>();
    private ArrayList<String> defaultMessages = new ArrayList<>();
    private ArrayList<String> defaultStrings = new ArrayList<>();
    private ArrayList<ArrayList<String>> p_prints = new ArrayList<>();
    private ArrayList<String> stringsInProgram = new ArrayList<>();
    private ArrayList<Function> functions = new ArrayList<>();
    ArrayList<Integer> scopeVariablesOffsetCount = new ArrayList<>();
    private ArrayList<Variable> freeVariables = new ArrayList<>();

    private int beginCount = 0;

    private boolean seenBool = false;
    private boolean seenString = false;
    private boolean seenInt = false;
    private boolean seenPrint = false;
    private boolean seenCalc = false;
    private boolean isident = false;
    private boolean isFirstInString =  true;

    private boolean seenDivorMod = false;
    private boolean seenArrayElem = false;
    private boolean seenPrintAddress = false;
    private boolean seenReadInt = false;
    private boolean seenReadChar = false;
    private boolean seenReadIntBeforeSeenInt = false;
    private boolean seenFree = false; // take care of runtime
    private boolean seenFreeArray = false;
    private boolean seenFreePair = false;
    private boolean seenFreePairBeforeFreeArray = false;
    private boolean seenPairElem = false;

    private LinkedList<HashMap<String, String>> variableValuesList = new LinkedList<>();

    public ArrayList<String> getStringsInProgram() {
        return stringsInProgram;
    }

    public ArrayList<Integer> getBeginOffsets() {
        return scopeVariablesOffsetCount;
    }

    public ArrayList<String> getMessages() {
        if (messages.size() > 1) {
            messages.add(0, "\t");
            messages.add(0, ARMInstructions.DATA_DIRECTIVE);
        }
        return messages;
    }

    public ArrayList<ArrayList<String>> getP_Prints() {
        return p_prints;
    }

    public ArrayList<Function> getListOfFunctions() {
        return functions;
    }

    private void addAllMessages() {
        // nub(stringsInProgram);

        for (int i = 0; i < stringsInProgram.size(); i++) {
            messages.add(MESSAGE_LABEL + i + ":");
            String s = stringsInProgram.get(i);
            s = s.replaceAll("\"", "");
            s = s.replaceAll("(\\\\n|\\\\r)", "1");
            s = s.replaceAll("\\\'", "");
            s = s.replaceAll("\\\0", "");
            messages.add(WORD + s.length());
            messages.add(ASCII + stringsInProgram.get(i));
        }
        if ((seenDivorMod || seenCalc || seenArrayElem || seenFree || seenPairElem) && !seenString) {
            defaultStrings.add(defaultString);
        }
        for (int i = 0; i < defaultStrings.size(); i++) {
            addDefaultMessage(defaultStrings.get(i));
        }
        messages.addAll(defaultMessages);

    }


    /*
     * Method: addDefaultMessage Usage: ?
     */
    private void addDefaultMessage(String s) {
        defaultMessages.add(MESSAGE_LABEL + (stringsInProgram.size() + (defaultMessages.size() / 3)) + ":");
        int length = s.length();
        if (s.equals(defaultDivideByZero) || s.equals(defaultArrayNegativeIndex)
                || s.equals(defaultArrayIndexOutOfBounds) || s.equals(defaultFree))
            length -= 1;
        defaultMessages.add(WORD + (length - 3));
        defaultMessages.add(ASCII + s);
    }


    private void writeP_Prints() {
        for (int i = 2; i < defaultMessages.size(); i += 3) {
            String messageLabel = defaultMessages.get(i - 2);
            messageLabel = messageLabel.substring(1, messageLabel.length() - 1);
            String d = defaultMessages.get(i).substring(ASCII.length());
            switch (d) {
                case defaultInt:
                    if (seenReadIntBeforeSeenInt) { // false
                        p_read_int(messageLabel);
                        seenReadIntBeforeSeenInt = false;
                        seenReadInt = false;
                    } else if (seenReadInt && !seenInt) {
                        p_read_int(messageLabel);
                    } else if (seenInt) {
                        p_print_int(messageLabel);
                        seenInt = false;
                    } else {
                        p_read_int(messageLabel);
                    }
                    break;
                case defaultString: // overflow/dividebyzero/arrayindexoutofbounds/actualstring
                    if ((seenDivorMod || seenCalc || seenArrayElem || seenFree || seenPairElem) && !seenString) {
                        p_print_runtimeError();
                    }
                    p_print_string(messageLabel);
                    break;
                case defaultTrue:
                    String falseMessage = defaultMessages.get(i + 1);
                    falseMessage = falseMessage.substring(1, falseMessage.length() - 1);
                    p_print_bool(messageLabel, falseMessage);
                    break;
                case defaultPrint:
                    p_print_ln(messageLabel);
                    break;
                case defaultOverflowError:
                    p_print_overflowError(messageLabel);
                    break;
                case defaultDivideByZero:
                    p_check_divide_by_zero(messageLabel);
                    break;
                case defaultArrayNegativeIndex:
                    String messageLabel2 = defaultMessages.get(i + 1);
                    messageLabel2 = messageLabel2.substring(1, messageLabel2.length() - 1);
                    p_check_array_bounds(messageLabel, messageLabel2);
                    break;
                case defaultPrintAddress:
                    p_print_reference(messageLabel);
                    break;
                case defaultReadChar:
                    p_read_char(messageLabel);
                    break;
                case defaultFree:
                    if (seenFreePairBeforeFreeArray) { // false
                        p_free_pair(messageLabel);
                        seenFreePairBeforeFreeArray = false;
                        seenFreePair = false;
                    } else if (seenFreePair && !seenFreeArray) {
                        p_free_pair(messageLabel);
                    } else if (seenFreeArray) {
                        p_free_array(messageLabel);
                        seenFreeArray = false;
                    } else if (seenPairElem) {
                        p_check_null_pointer(messageLabel);
                    } else {
                        p_free_array(messageLabel);
                    }
                    break;
            }
        }
        if ((seenDivorMod || seenCalc || seenArrayElem || seenFree || seenPairElem) && seenString) {
            p_print_runtimeError();
        }
    }

    /*
     * Method: p_check_null_pointer Usage: ?
     */
    private void p_check_null_pointer(String messageLabel) {
        ArrayList<String> codeGen = new ArrayList<>();
        codeGen.add("\tp_check_null_pointer:");
        codeGen.add(ARMInstructions.PUSH_LINK_REG);
        codeGen.add(ARMInstructions.CMP.printWithReg("r0", "#0"));
        codeGen.add(ARMInstructions.LDREQ.printWithImm("r0", messageLabel));
        codeGen.add(ARMInstructions.BLEQ.printWithString("p_throw_runtime_error"));
        codeGen.add(ARMInstructions.POP_PC);
        p_prints.add(codeGen);
    }

    /*
     * Method: p_free_array Usage: ?
     */
    private void p_free_array(String messageLabel) {
        ArrayList<String> codeGen = new ArrayList<String>();
        codeGen.add("\tp_free_array:");
        codeGen.add(ARMInstructions.PUSH_LINK_REG);
        codeGen.add(ARMInstructions.CMP.printWithReg("r0", "#0"));
        codeGen.add(ARMInstructions.LDREQ.printWithImm("r0", messageLabel));
        codeGen.add(ARMInstructions.BEQ.printWithString("p_throw_runtime_error"));
        codeGen.add(ARMInstructions.BL.printWithString("free"));
        codeGen.add(ARMInstructions.POP_PC);
        p_prints.add(codeGen);
    }

    /*
     * Method: p_free_pair Usage: ?
     */
    private void p_free_pair(String messageLabel) {
        ArrayList<String> codeGen = new ArrayList<String>();
        codeGen.add("\tp_free_pair:");
        codeGen.add(ARMInstructions.PUSH_LINK_REG);
        codeGen.add(ARMInstructions.CMP.printWithReg("r0", "#0"));
        codeGen.add(ARMInstructions.LDREQ.printWithImm("r0", messageLabel));
        codeGen.add(ARMInstructions.BEQ.printWithString("p_throw_runtime_error"));
        codeGen.add(ARMInstructions.PUSH.printWithReg("r0"));
        codeGen.add(ARMInstructions.LDR.printWithAddrReg("r0", "r0", 0));
        codeGen.add(ARMInstructions.BL.printWithString("free"));
        codeGen.add(ARMInstructions.LDR.printWithAddrReg("r0", Registers.sp.getName(), 0));
        codeGen.add(ARMInstructions.LDR.printWithAddrReg("r0", "r0", 4));
        codeGen.add(ARMInstructions.BL.printWithString("free"));
        codeGen.add(ARMInstructions.POP.printWithReg("r0"));
        codeGen.add(ARMInstructions.BL.printWithString("free"));
        codeGen.add(ARMInstructions.POP_PC);
        p_prints.add(codeGen);
    }

    /*
     * Method: p_read_char Usage: ?
     */
    private void p_read_char(String messageLabel) {
        ArrayList<String> codeGen = new ArrayList<String>();
        codeGen.add("\tp_read_char:");
        codeGen.add(ARMInstructions.PUSH_LINK_REG);
        codeGen.add(ARMInstructions.MOV.printWithReg("r1", "r0"));
        codeGen.add(ARMInstructions.LDR.printWithImm("r0", messageLabel));
        codeGen.add(ARMInstructions.ADD.printWithOffset("r0", 4));
        codeGen.add(ARMInstructions.BL.printWithString("scanf"));
        codeGen.add(ARMInstructions.POP_PC);
        p_prints.add(codeGen);
    }

    /*
     * Method: p_read_int Usage: ?
     */
    private void p_read_int(String messageLabel) {
        ArrayList<String> codeGen = new ArrayList<String>();
        codeGen.add("\tp_read_int:");
        codeGen.add(ARMInstructions.PUSH_LINK_REG);
        codeGen.add(ARMInstructions.MOV.printWithReg("r1", "r0"));
        codeGen.add(ARMInstructions.LDR.printWithImm("r0", messageLabel));
        codeGen.add(ARMInstructions.ADD.printWithOffset("r0", 4));
        codeGen.add(ARMInstructions.BL.printWithString("scanf"));
        codeGen.add(ARMInstructions.POP_PC);
        p_prints.add(codeGen);
    }

    /*
     * Method: p_print_reference Usage: ?
     */
    private void p_print_reference(String messageLabel) {
        ArrayList<String> codeGen = new ArrayList<String>();
        codeGen.add("\tp_print_reference:");
        codeGen.add(ARMInstructions.PUSH_LINK_REG);
        codeGen.add(ARMInstructions.MOV.printWithReg("r1", "r0"));
        codeGen.add(ARMInstructions.LDR.printWithImm("r0", messageLabel));
        codeGen.add(ARMInstructions.ADD.printWithOffset("r0", 4));
        codeGen.add(ARMInstructions.BL.printWithString("printf"));
        codeGen.add(ARMInstructions.MOV.printWithImm("r0", "0"));
        codeGen.add(ARMInstructions.BL.printWithString("fflush"));
        codeGen.add(ARMInstructions.POP_PC);
        p_prints.add(codeGen);
    }


    /*
 * Method: p_check_array_bounds Usage: ?
 */
    private void p_check_array_bounds(String messageLabel1, String messageLabel2) {
        ArrayList<String> codeGen = new ArrayList<String>();
        codeGen.add("\tp_check_array_bounds:");
        codeGen.add(ARMInstructions.PUSH_LINK_REG);
        codeGen.add(ARMInstructions.CMP.printWithImm("r0", "0"));
        codeGen.add(ARMInstructions.LDRLT.printWithImm("r0", messageLabel1));
        codeGen.add(ARMInstructions.BLLT.printWithString("p_throw_runtime_error"));
        codeGen.add(ARMInstructions.LDR.printWithAddrReg("r1", "r1", 0));
        codeGen.add(ARMInstructions.CMP.printWithReg("r0", "r1"));
        codeGen.add(ARMInstructions.LDRCS.printWithImm("r0", messageLabel2));
        codeGen.add(ARMInstructions.BLCS.printWithString("p_throw_runtime_error"));
        codeGen.add(ARMInstructions.POP_PC);
        p_prints.add(codeGen);
    }

    /* Method: p_check_divide_by_zero Usage: ?
            */
    private void p_check_divide_by_zero(String messageLabel) {
        ArrayList<String> codeGen = new ArrayList<String>();
        codeGen.add("\tp_check_divide_by_zero:");
        codeGen.add(ARMInstructions.PUSH_LINK_REG);
        codeGen.add(ARMInstructions.CMP.printWithImm("r1", "0"));
        codeGen.add(ARMInstructions.LDREQ.printWithImm("r0", messageLabel));
        codeGen.add(ARMInstructions.BLEQ.printWithString("p_throw_runtime_error"));
        codeGen.add(ARMInstructions.POP_PC);
        p_prints.add(codeGen);
    }

    /*
     * Method: p_print_runtimeError Usage: ?
     */
    private void p_print_runtimeError() {
        ArrayList<String> codeGen = new ArrayList<>();
        codeGen.add("\tp_throw_runtime_error:");
        codeGen.add(ARMInstructions.BL.printWithString("p_print_string"));
        codeGen.add(ARMInstructions.MOV.printWithImm("r0", "-1"));
        codeGen.add(ARMInstructions.BL.printWithString("exit"));
        p_prints.add(codeGen);
    }

    /*
     * Method: p_print_overflowError Usage: ?
     */
    private void p_print_overflowError(String messageLabel) {
        ArrayList<String> codeGen = new ArrayList<String>();
        codeGen.add("\tp_throw_overflow_error:");
        codeGen.add(ARMInstructions.LDR.printWithImm("r0", messageLabel));
        codeGen.add(ARMInstructions.BL.printWithString("p_throw_runtime_error"));
        p_prints.add(codeGen);
    }

    /*
     * Method: p_print_int Usage: ?
     */
    private void p_print_int(String messageLabel) {
        ArrayList<String> codeGen = new ArrayList<>();
        codeGen.add("\tp_print_int:");
        codeGen.add(ARMInstructions.PUSH_LINK_REG);
        codeGen.add(ARMInstructions.MOV.printWithReg("r1", "r0"));
        codeGen.add(ARMInstructions.LDR.printWithImm("r0", messageLabel));
        codeGen.add(ARMInstructions.ADD.printWithOffset("r0", 4));
        codeGen.add(ARMInstructions.BL.printWithString("printf"));
        codeGen.add(ARMInstructions.MOV.printWithImm("r0", "0"));
        codeGen.add(ARMInstructions.BL.printWithString("fflush"));
        codeGen.add(ARMInstructions.POP_PC);
        p_prints.add(codeGen);
    }

    /*
     * Method: p_print_bool Usage: ?
     */
    private void p_print_bool(String messageLabel, String falseMessage) {
        ArrayList<String> codeGen = new ArrayList<>();
        codeGen.add("\tp_print_bool:");
        codeGen.add(ARMInstructions.PUSH_LINK_REG);
        codeGen.add(ARMInstructions.CMP.printWithImm("r0", "0"));
        codeGen.add(ARMInstructions.LDRNE.printWithImm("r0", messageLabel));
        codeGen.add(ARMInstructions.LDREQ.printWithImm("r0", falseMessage));
        codeGen.add(ARMInstructions.ADD.printWithOffset("r0", 4));
        codeGen.add(ARMInstructions.BL.printWithString("printf"));
        codeGen.add(ARMInstructions.MOV.printWithImm("r0", "0"));
        codeGen.add(ARMInstructions.BL.printWithString("fflush"));
        codeGen.add(ARMInstructions.POP_PC);
        p_prints.add(codeGen);
    }

    /*
     * Method: p_print_ln Usage: ?
     */
    private void p_print_ln(String messageLabel) {
        ArrayList<String> codeGen = new ArrayList<>();
        codeGen.add("\tp_print_ln:");
        codeGen.add(ARMInstructions.PUSH_LINK_REG);
        codeGen.add(ARMInstructions.LDR.printWithImm("r0", messageLabel));
        codeGen.add(ARMInstructions.ADD.printWithOffset("r0", 4));
        codeGen.add(ARMInstructions.BL.printWithString("puts"));
        codeGen.add(ARMInstructions.MOV.printWithImm("r0", "0"));
        codeGen.add(ARMInstructions.BL.printWithString("fflush"));
        codeGen.add(ARMInstructions.POP_PC);
        p_prints.add(codeGen);
    }

    /*
     * Method: p_print_string Usage: ?
     */
    private void p_print_string(String messageLabel) {
        ArrayList<String> codeGen = new ArrayList<>();
        codeGen.add("\tp_print_string:");
        codeGen.add(ARMInstructions.PUSH_LINK_REG);
        codeGen.add(ARMInstructions.LDR.printWithAddrReg("r1", "r0", 0));
        codeGen.add(ARMInstructions.ADD.printWithReg("r2", "r0", "#4"));
        codeGen.add(ARMInstructions.LDR.printWithImm("r0", messageLabel));
        codeGen.add(ARMInstructions.ADD.printWithReg("r0", "r0", "#4"));
        codeGen.add(ARMInstructions.BL.printWithString("printf"));
        codeGen.add(ARMInstructions.MOV.printWithImm("r0", "0"));
        codeGen.add(ARMInstructions.BL.printWithString("fflush"));
        codeGen.add(ARMInstructions.POP_PC);
        p_prints.add(codeGen);
    }

    //----------------------------------------------------------------------------------------------------------------

    @Override
    public Void visitProgram(WACCParser.ProgramContext ctx) {
        Function main = new Function("main");
        if(ctx.func() != null) {
            for (int i = 0; i < ctx.func().size(); i++) {
                visitFunc(ctx.func(i));
            }
            functions.add(main);
        }

        getVariablesForFunction(ctx.stat(), main);
        sortOutBeginVariables(ctx.stat());
        //getVariablesForFunction(ctx.stat(), main);
        visit(ctx.stat());
        addAllMessages();
        writeP_Prints();
        return null;
    }


    public Void visitBegin(WACCParser.BeginContext ctx) {
        beginCount++;
        sortOutBeginVariables(ctx.stat());
        visit(ctx.stat());
        return null;
    }


    /*
     * Method: sortOutBeginVariables Usage: gets variables for the current scope
     * using the stat context
     */
    public void sortOutBeginVariables(WACCParser.StatContext stat) {
        if (stat instanceof WACCParser.DeclareContext) {
            Variable v = Utils.getVariable((WACCParser.DeclareContext) stat);
            int current = 0;
            if (scopeVariablesOffsetCount.size() > beginCount) {
                current = scopeVariablesOffsetCount.get(beginCount);
            }
            current += v.getOffset();
            if (scopeVariablesOffsetCount.size() > beginCount) {
                scopeVariablesOffsetCount.remove(beginCount);
            }
            scopeVariablesOffsetCount.add(beginCount , current);
        } else if (stat instanceof WACCParser.MultipleStatContext) {
            WACCParser.MultipleStatContext stats = (WACCParser.MultipleStatContext) stat;
            for(int i = 0; i < stats.stat().size(); i++) {
                sortOutBeginVariables(stats.stat(i));
            }
        } else {
            // begin
            visit(stat);
        }
    }

    /*
     * Method: getVariablesForFunction Usage: ?
     */
    private void getVariablesForFunction(WACCParser.StatContext stat, Function f) {
        if (stat instanceof WACCParser.DeclareContext) {
            Variable v = Utils.getVariable((WACCParser.DeclareContext) stat);
            f.addVariable(v);
        } else if (stat instanceof WACCParser.MultipleStatContext) {
            WACCParser.MultipleStatContext mul = (WACCParser.MultipleStatContext) stat;
            for(int i = 0; i < mul.stat().size(); i++) {
                getVariablesForFunction(mul.stat(i), f);
            }
        } else if (stat instanceof WACCParser.BeginContext) {
            getVariablesForFunction(((WACCParser.BeginContext) stat).stat(), f);
        }

    }

    @Override
    public Void visitPairElem(WACCParser.PairElemContext ctx) {
        if (!seenPairElem) {
            defaultStrings.add(defaultFree);
            seenPairElem = true;
        }
        return null;
    }

    @Override
    public Void visitDeclare(WACCParser.DeclareContext ctx) {
        HashMap<String, String> currentMap = new HashMap<>();
       /* String variableName = ctx.ident().getText();
        currentMap.put(ctx.)
        variableValuesList.addLast();*/
        return visitChildren(ctx);
    }


    @Override
    public Void visitFree(WACCParser.FreeContext ctx) {
        if (ctx.getChild(1) instanceof WACCParser.IdentContext) {
            Function currentFunction = functions.get(functions.size() - 1);
            Variable v = currentFunction.getVariable(ctx.getChild(1).getText());
            if (freeVariables.contains(v)) {
                System.out.println("#runtime_error");
                System.exit(134);
            } else {
                freeVariables.add(v);
            }
            if (v.getType().getChild(0) instanceof WACCParser.PairTypeContext) {
                if (!seenFreePair && !seenFreePairBeforeFreeArray) {
                    defaultStrings.add(defaultFree);
                    seenFreePairBeforeFreeArray = true;
                    seenFree = true;
                    seenFreePair = true;
                } else if (!seenFreePair) {
                    defaultStrings.add(defaultFree);
                    seenFree = true;
                    seenFreePair = true;
                }
            } else if (!(v.getType().getChild(0) instanceof WACCParser.BaseTypeContext)) {
                if (!seenFreeArray) {
                    defaultStrings.add(defaultFree);
                    seenFree = true;
                    seenFreeArray = true;
                }
            }
        }
        return null;
    }



    @Override
    public Void visitRead(WACCParser.ReadContext ctx) {
        String s = ctx.getChild(1).getText();
        s = s.replaceAll("fst", "");
        s = s.replaceAll("snd", "");
        Variable v = functions.get(functions.size() - 1).getVariable(s);
        if (v.getType().getChild(0).getText().equals("int")) {
            if (!seenReadInt && !seenInt) {
                defaultStrings.add(defaultReadInt);
                seenReadIntBeforeSeenInt = true;
                seenReadInt = true;
            } else if (!seenReadInt) {
                defaultStrings.add(defaultReadInt);
                seenReadInt = true;
            }
        } else if (v.getType().getChild(0).getText().equals("char")) {
            if (!seenReadChar)
                defaultStrings.add(defaultReadChar);
            seenReadChar = true;
        } else if (v.getType().getChild(0) instanceof WACCParser.PairTypeContext) {
            if (!seenPairElem)
                defaultStrings.add(defaultFree);
            seenPairElem = true;
            if (ctx.getChild(1).getText().contains("fst")) {
                String type = v.getType().getChild(0).getChild(2).getText();
                if (type.equals("char")) {
                    if (!seenReadChar)
                        defaultStrings.add(defaultReadChar);
                    seenReadChar = true;
                } else if (type.equals("int")) {
                    if (!seenReadInt && !seenInt) {
                        defaultStrings.add(defaultReadInt);
                        seenReadIntBeforeSeenInt = true;
                        seenReadInt = true;
                    } else if (!seenReadInt) {
                        defaultStrings.add(defaultReadInt);
                        seenReadInt = true;
                    }
                }
            } else if (ctx.getChild(1).getText().contains("snd")) {
                String type = v.getType().getChild(0).getChild(4).getText();
                if (type.equals("char")) {
                    if (!seenReadChar)
                        defaultStrings.add(defaultReadChar);
                    seenReadChar = true;
                } else if (type.equals("int")) {
                    if (!seenReadInt && !seenInt) {
                        defaultStrings.add(defaultReadInt);
                        seenReadIntBeforeSeenInt = true;
                        seenReadInt = true;
                    } else if (!seenReadInt) {
                        defaultStrings.add(defaultReadInt);
                        seenReadInt = true;
                    }
                }
            }
        } else {
            // other assign lhs cases
        }
        return null;
    }

    @Override
    public Void visitIdent(WACCParser.IdentContext ctx) {
        isident = true;
        return null;
    }



    @Override
    public Void visitArrayElem(WACCParser.ArrayElemContext ctx) {
        visit(ctx.getChild(0));
        return null;
    }



   @Override
    public Void visitPrintln(WACCParser.PrintlnContext ctx) {
        visitChildren(ctx);
        visitPrintHelper(ctx.expr());
        if (!seenPrint) {
            defaultStrings.add(defaultPrint);
        }
        seenPrint = true;
        return null;
    }


    @Override
    public Void visitPrint(WACCParser.PrintContext ctx) {
        visitPrintHelper(ctx.expr());
        return null;
    }



    public void visitPrintHelper(WACCParser.ExprContext ctx) {
        if (ctx.stringLiter() != null) {
            stringsInProgram.add(ctx.getChild(0).getText());
            if (!seenString) {
                defaultStrings.add(defaultString);
            }
            seenString = true;
        } else if (ctx.boolLiter() != null) {
            if (!seenBool) {
                defaultStrings.add(defaultTrue);
                defaultStrings.add(defaultFalse);
                seenBool = true;
            }
        } else if (ctx.pairLiter() != null) {
            if (!seenPrintAddress) {
                defaultStrings.add(defaultPrintAddress);
                seenPrintAddress = true;
            }
        } else if (ctx.intLiter() != null) {
            if (!seenInt)
                defaultStrings.add(defaultInt);
            seenInt = true;
        } else if (ctx.AND() != null || ctx.OR() != null ) {
            //visit(ctx.expr(1));
            visit(ctx.expr(0));
            visitPrintHelper(ctx.expr(0));
        } else if (Utils.isBinaryOper(ctx)) {
             visitPrintHelper(ctx.expr(0));

        } else if (ctx.PLUS() != null || ctx.MINUS() != null
                || ctx.MUL() != null || ctx.DIV() != null || ctx.MOD() != null) {
            //visit(ctx.getChild(1));
            visitPrintHelper(ctx.expr(0));
        } else if (isident || ctx.ident() != null) {
            Function currentFunction = functions.get(functions.size() - 1);
            Variable v = currentFunction.getVariable(ctx.getText());
            visitPrintHelperType(v.getType());
        } else if (Utils.isUnaryOper(ctx)) {
            visitPrintUnary(ctx);
        } else if (ctx.arrayElem() != null) {
            Function currentFunction = functions.get(functions.size() - 1);
            Variable v = currentFunction.getVariable(ctx.expr(0).getChild(0).getText());
            WACCParser.TypeContext type = (WACCParser.TypeContext) v.getType().getChild(0);
            visit(ctx);
            int depthCount = (ctx.getChild(0).getChildCount() - 1) / 3;
            if (Utils.getDepth(v.getType()) == depthCount) {
                // the type of array so %d etc
                visitPrintHelperType((WACCParser.TypeContext) Utils.getActualTypeOfArray(v.getType()));
            } else {
                // %p
                visitPrintHelperType(type);
            }
        }
    }

    public Void visitMinus_unary(WACCParser.ExprContext ctx) {
        if (!seenCalc && ctx.getParent().getParent().getParent() instanceof WACCParser.DeclareContext) {
            defaultStrings.add(defaultOverflowError);
            seenCalc = true;
        }
        return null;
    }


    public void visitPrintUnary(ParseTree parseTree) {
        WACCParser.ExprContext ctx = (WACCParser.ExprContext) parseTree;
        if (ctx.ORD() != null|| ctx.LEN() != null) {
            if (!seenInt)
                defaultStrings.add(defaultInt);
            seenInt = true;
        } else if (!Utils.isUnaryOper(ctx)) {
            if (!seenBool) {
                defaultStrings.add(defaultTrue);
                defaultStrings.add(defaultFalse);
                seenBool = true;
            }
        } else if (ctx.MINUS() != null && ctx.getChild(1) == null) {
            if (!seenCalc)
                defaultStrings.add(defaultOverflowError);
            seenCalc = true;
            if (!seenInt)
                defaultStrings.add(defaultInt);
            seenInt = true;
        }
    }


    private void visitPrintHelperType(WACCParser.TypeContext type) {
        if (type.getChild(0) instanceof WACCParser.BaseTypeContext) {
            String baseType = type.getChild(0).getText();
            if (baseType.equals(Utils.INT)) {
                if (!seenInt)
                    defaultStrings.add(defaultInt);
                seenInt = true;
            } else if (baseType.equals(Utils.BOOL)) {
                if (!seenBool) {
                    defaultStrings.add(defaultTrue);
                    defaultStrings.add(defaultFalse);
                    seenBool = true;
                }
            } else if (baseType.equals(Utils.STRING)) {
                if (!seenString) {
                    defaultStrings.add(defaultString);
                }
                seenString = true;
            } else if (baseType.equals(Utils.CHAR)) {

            } else {
                defaultStrings.add("visitPrintHelperType in SystemReadTokens has a bad type");
            }
        } else if (type.getChild(0) instanceof WACCParser.PairTypeContext) {
            if (!seenPrintAddress) {
                defaultStrings.add(defaultPrintAddress);
                seenPrintAddress = true;
            }
        } else {
            // Array type
            if (!seenPrintAddress && !(type.getChild(0).getText().equals("string")))
                defaultStrings.add(defaultPrintAddress);
            seenPrintAddress = true;
        }
    }

    @Override
    public Void visitStringLiter(WACCParser.StringLiterContext ctx) {
        stringsInProgram.add(ctx.getText());
        return null;
    }

    @Override public Void visitExpr(WACCParser.ExprContext ctx) {
        if(ctx.DIV() != null || ctx.MOD() != null) {
            visitDivorMod();
        }
        if(Utils.isBinaryOper(ctx) && ctx.expr().size() == 2) {
            if(ctx.MINUS() != null || ctx.PLUS()!= null  || ctx.MUL() != null ) {
                visitCalcOperator();
            } else if(ctx.DIV() == null && ctx.MOD() == null) {//if div == null then must be a boolean function
                visitBinaryOperOther(ctx);
            }
        } else if(Utils.isUnaryOper(ctx)) {
            if(ctx.MINUS() != null) {
                visitMinus_unary(ctx);
            }
        }
        return visitChildren(ctx);
    }



    public void visitDivorMod() {
        if (!seenDivorMod) {
            defaultStrings.add(defaultDivideByZero);
            seenDivorMod = true;
        }
    }


    public void visitCalcOperator() {
        if (!seenCalc) {
            defaultStrings.add(defaultOverflowError);
            seenCalc = true;
        }
    }



    public void visitBinaryOperOther(ParseTree ctx) {
        if (!seenBool && checkParentIsPrint(ctx)) {
            defaultStrings.add(defaultTrue);
            defaultStrings.add(defaultFalse);
            seenBool = true;
        }
    }


    public boolean checkParentIsPrint(ParseTree ctx) {
        if (ctx.getParent() instanceof WACCParser.PrintContext
                || ctx.getParent() instanceof WACCParser.PrintlnContext) {
            return true;
        } else {
            if (ctx.getParent() != null) {
                return checkParentIsPrint(ctx.getParent());
            } else {
                return false;
            }
        }
    }

    @Override
    public Void visitFunc(WACCParser.FuncContext ctx) {
        Function f = new Function(ctx.ident().getText());
        int numOfParams;
        if (ctx.paramList() == null) {
            numOfParams = 0;
        } else {
            numOfParams = (ctx.paramList().getChildCount() + 1) / 2;
        }
        if (ctx.paramList() != null) {
            for (int i = ctx.paramList().getChildCount(); i >= 0; i -= 2) {
                String name = ctx.paramList().param(i / 2).ident().getText();
                int o = Utils.getNumOfBytesForType((WACCParser.TypeContext) ctx.paramList().param(i / 2).getChild(0));
                Variable v = new Variable(name, o, ctx.paramList().param(i / 2).type());
                f.addVariable(v);
            }
        }
        f.setParamCount(numOfParams);
        getVariablesForFunction(ctx.stat(), f);
        functions.add(f);
        return visitChildren(ctx);
    }





}
