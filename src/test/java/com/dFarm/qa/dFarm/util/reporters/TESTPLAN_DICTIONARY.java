package com.dFarm.qa.dFarm.util.reporters;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Arrays;

public enum TESTPLAN_DICTIONARY {


    //Add the module / class  names
    PACK_HOUSE("Pack House", new String[]{"HomePage", "StockMaster", "SalesOrder", "Packing"}),
    dFARMADMIN("dFarm Admin", new String[]{"Admin"}),
    ;


    private final String testPlan;
    private final String[] className;

    TESTPLAN_DICTIONARY(String testPlan, String[] className){
        if(testPlan == null || className == null){
            throw new IllegalArgumentException("Args must not be null");
        }
        this.testPlan = testPlan;
        this.className = className;
    }

    public String getTestPlan(){
        return testPlan;
    }

    public String[] getClassName(){
        return className;
    }

    // Reverse-lookup map logci below for getting a key based on partial map value match
    private static final Map<List<String>, String> Lookup = new HashMap<>();

    static {
        for (TESTPLAN_DICTIONARY d : TESTPLAN_DICTIONARY.values()){
        Lookup.put(Arrays.asList(d.getClassName()), d.getTestPlan());
        }
    }

    public static String getTestPlanName(String className){
        String testPlan = null;
        for (Map.Entry<List<String>, String> e : Lookup.entrySet()){
            if (e.getKey().contains(className)){
                testPlan = e.getValue();
                break;
            }
        }
        return testPlan;
    }
}
