package com.dFarm.qa.dFarm.constants.groups;

/**
 * This interface promulgatesn collection of static final strings used for TestNg
 * group execution. Below are the list of flows available flows in Collection Center.
 *
 * @author Gangarapu.Ganesh
 * @implNote These constants should not be used within your code. This is strictly
 *           written for TestNG grouping usage.
 */

public interface COLLECTIONCENTER {

    String HOME = "Home";
    String COLLECTION = "Collection";

    interface  COLLECTION {
        String COLLECT_PRODUCE = "CollectProduce";
        String COLLECTION_REPORT = "CollectionReport";
    }
}
