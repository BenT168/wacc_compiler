import java.util.List;

/**
 * Created by aa14415 on 04/11/16.
 */
public interface SymbolTableInterface {


    /*Add a variable to list*/
    void add(Variable variable);

    /*For testing purposes to see if variables are added to list*/
    void printList();


    /*Use lookupInt when value of variable in list is an int*/
    int lookUpInt(String varName);


    /*Use lookupBool when value of variable in list is a boolean*/
    boolean lookUpBool(String varName);


    /*Use lookupString when value of variable in list is a string*/
    String lookUpString(String varName);


    /*Use lookupChar when value of variable in list is a char*/
    char lookUpChar(String varName);


    /*Use lookUpType to check type of name in list if it exits*/
    Type lookUpType(String name);

    /*Setter for parentAymbolTable */
    void setParentSymbolTable(SymbolTable parentSymbolTable);

    /*Removes all items from symbol table. */
    void clear();


    /* Returns a clone of the current symbol table.
     * Useful for child scopes. */
    List<Variable> clone();

}
