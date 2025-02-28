package com.dFarm.qa.dFarm.util.datahandler;


import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.dFarm.qa.dFarm.constants.TestDataConstants;
import edu.emory.mathcs.backport.java.util.concurrent.ConcurrentHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class HandleDelimitedDataFromXLS implements IHandleTestData, TestDataConstants {

    private final Map<String, List<String>> sheet2HeaderMap;
    private final Map<String, Map<String, String >> testDataMap;

    private static final Logger logger = LoggerFactory.getLogger(HandleDelimitedDataFromXLS.class);

    private static final HandleDelimitedDataFromXLS INSTANCE = new HandleDelimitedDataFromXLS();

    public static HandleDelimitedDataFromXLS getDataHandler() {
        return INSTANCE;
    }

    public HandleDelimitedDataFromXLS() {
        sheet2HeaderMap = new ConcurrentHashMap();
        testDataMap = new ConcurrentHashMap();
    }

    @Override
    public void handleTestDataListHeaders(String sheetName, List<String> headers) {
        sheetName = sheetName.trim();
        if (sheetName == null || sheetName.length() <1)
            throw new IllegalArgumentException("Sheetname must not be null or zero length");
        if (headers == null|| headers.size() < 1)
            throw new IllegalArgumentException("Headers must not be null or empty");
        if (sheet2HeaderMap.containsKey((sheetName))) {
            logger.warn("WARNING: Overwriting duplicate sheetname. This is likely not what you want");
        }
            sheet2HeaderMap.put(sheetName, headers);
    }


    @Override
    public void handleTestDataAsList(String sheetName, String testCaseID, List<String> dataAsList) {
        if(sheetName == null || sheetName.length() < 1)
            throw new IllegalArgumentException("Sheetname must not be null or zero length");
        if(dataAsList == null || dataAsList.size() < 1)
            throw new IllegalArgumentException("dataAsList must not be null or empty");
        List<String > headers = sheet2HeaderMap.get(sheetName);
        if (headers.size() != dataAsList.size()) {
            logger.warn("Warning: headers size does not match data list size! Potential data loss");
            logger.warn("Headerssize:" + headers.size());
            logger.warn("Data size: " + dataAsList.size());
        }

        Map<String, String> dataMap = new HashMap();
        boolean returnStatus = true;

        int maxHeaders = headers.size();
        int maxData = dataAsList.size();
        int i = 0;
        for (String h : headers) {
            if (i >= maxHeaders || i >= maxData) {
                logger.warn ("Cannot add any more data due to spreadsheet errors: header/data length  mismatch");
                break;
            }
            if (h == null || h.length() < 1) {
                logger.warn("refusing to parse beyond a null or zero length header");
                break;
            }
            String data = dataAsList.get(i++);

            dataMap.put(h.trim(), data);
            if(h.equalsIgnoreCase(COLUMNNAME_STRUCTURED)) {
                handleDelimitedData(dataMap, data);
            }
        }
        if (testCaseID == null) {
            logger.warn("Refusing to add empty testcaseid. Pleas check spreadsheet");
            return;
        }
        if(dataMap.size() < 1) {
            logger.warn("Refusing to add empty data map. Pleas check spreadsheet");
            return;
        }
        testDataMap.put(testCaseID, dataMap);
    }

    private void handleDelimitedData(Map<String, String> dataMap, String data) {
        String delimiter = DEFAULT_DELIMITER;
        if(data.startsWith(DATATYPE_PREFIX_DELIMITED))
            delimiter = data.charAt(DATATYPE_PREFIX_DELIMITED.length()) + "";
        String[] kvPairs = data.split(delimiter + "");
        for(String kv : kvPairs) {
            if (kv.startsWith(DATATYPE_PREFIX_DELIMITED))
                continue;
            if (kv == null || kv.length() < 1) {
                continue;
            }
            if (kv.length() < 1) {
                continue;
            }
            int idxVal = kv.indexOf(KV_SEPARATOR);
            if (idxVal < 1) {

            } else {
                String key = kv.substring(0, idxVal);
                String val = kv.substring(idxVal + 1);
                dataMap.put(key, val);
            }
        }
    }

    @Override
    public String get (String testCaseID, String key) {
        String returnVal = null;
        if (testCaseID == null || key == null)
            throw new IllegalArgumentException("Args must not be null");
        testCaseID = testCaseID.trim();
        key = key.trim();
        Map<String, String> dataMap = testDataMap.get(testCaseID);
        if (dataMap == null) {
            logger.warn("Could not find testCaseID in map: " + testCaseID);
        } else {
            if (dataMap.containsKey(key)) {
                return dataMap.get(key);
            } else {
                logger.warn("Could not find ket: " + key + ", for test caseID: " + testCaseID);
            }
        }
        return returnVal;
    }

    @Override
    public String get(String testCaseID, String key, String defaultReturnValue) {
        String retVal = get (testCaseID, key);
        if (retVal == null)
            return defaultReturnValue;
        return retVal;
    }

    private static void p(String s) {
        logger.debug(s);
    }

    @Override
    public Map<String, Map<String, String>>  getTestDataMap() {
        return this.testDataMap;
    }
}
