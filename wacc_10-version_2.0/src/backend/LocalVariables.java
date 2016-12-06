package backend;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>Stack Frame's Local Variables</h1>
 * <p>Function calls result in the creation of a new stack frame. This stack
 * frame consists of the function call parameters; the return address for the
 * stack frame base pointer; and the declared, local variables in the function
 * body. This class keeps track of the local variables. </p>
 */
public class LocalVariables {

    // List keeps track of reserved local variables.
    // The ith element in the list is true if the the ith variable declared is
    // still in use. The list size grows only as new variables are declared,
    // hence its size is equivalent to the number of variables declared
    // (including those no longer in use).
    private List<Boolean> reservedVariables;

    /**
     * Constructor
     * @param requestedAmount Number of variables to reserve.
     */
    public LocalVariables(int requestedAmount) {
        reservedVariables = new ArrayList<>(requestedAmount);
        for (int i = 0; i < requestedAmount; i++) {
            reservedVariables.add(true);
        }
    }

    /**
     * Reserve a variable at the lowest free (i.e: false) index or at index
     * 'reservedVariables.size()' if there are no false entries in the list.
     * @return The index of the newly reserved variable.
     */
    public int reserve() {
        for (int i = 0; i < reservedVariables.size(); i++) {
            if (!reservedVariables.get(i)) {
                reservedVariables.add(i, true);
                return i;
            }
        }
        reservedVariables.add(reservedVariables.size(), true);
        return reservedVariables.size();
    }

    /**
     * Release a variable.
     * @param index The index of the variable to be released.
     */
    public void release(int index) {
        reservedVariables.set(index, false);
    }

    /**
     * Note that this method will include those variables no longer in use,
     * so long as they were requested to be reserved during the construction of
     * this instance.
     * @return Number of local variables needed by the function.
     */
    public int size() {
        return reservedVariables.size();
    }
}
