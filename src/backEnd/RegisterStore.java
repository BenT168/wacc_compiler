package backEnd;

import frontEnd.SymbolTable;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class RegisterStore {

    //private List<HashMap<String, >> registerTable;
    private List<HashMap<String, String>> registerVals = new LinkedList<>();

    public void add(String reg, String val) {
        HashMap<String, String> addThis = new HashMap<>();
        addThis.put(reg, val);
        registerVals.add(addThis);
    }

    public String getVal(String reg) {
        for(HashMap<String, String> map : registerVals) {
            if(map.containsKey(reg)) {
                return map.get(reg);
            }
        }
        return null;
    }

    public String getReg(String val) {
        for(HashMap<String, String> map : registerVals) {
            if(map.containsValue(val)) {
                return map.get(val);
            }
        }
        return null;
    }
}
