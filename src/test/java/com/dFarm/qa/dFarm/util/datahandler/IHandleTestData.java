package com.dFarm.qa.dFarm.util.datahandler;

import java.util.List;
import java.util.Map;

public interface IHandleTestData {

    //These are implicitly public static final, just verbose for clarity
    String DATATYPE_PREFIX_DELIMITED = "delimiter=";
    String DEFAULT_DELIMITER = ";";
    String KV_SEPARATOR = "=";

    Map<String, Map<String , String >> getTestDataMap();

    /**
     * @implNote Test writer will care more about this likely should split this out as well
     * @param testCaseID should be unique within the entire map
     * @param key the header value or label user to reference the data value
     * @return the value corresponding to the key
     */
    String get(String testCaseID, String key);

    /**
     * @implNote A convience method to return a default value if missing from data map A
     * slightly less verbose method name for a test with the team
     * @param testCaseID
     * @param key
     * @param defaultReturnValue
     * @return
     */
    String get(String testCaseID, String key, String defaultReturnValue);

    /**
     * @implNote Internal plumbing method to preserve header info for parsing
     * @param sheetName unique sheet name
     * @param headers list of headers for the sheet
     */
    void handleTestDataListHeaders(String sheetName, List<String> headers);

    /**
     * @implNote Internal plumbing method to resolve xlsx data against header guidance
     * @param sheetName
     * @param testCaseId
     * @param dataAsList
     */
    void handleTestDataAsList(String sheetName, String testCaseId, List<String> dataAsList);
}
