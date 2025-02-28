package com.dFarm.qa.dFarm.util.datahandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class TestData {

    protected Map<String, Object> allTestData = new LinkedHashMap<>();
    private static final Logger logger = LoggerFactory.getLogger(TestData.class);
    private final String logUnablleToFetch = "Unable to fetch Data for the key : %s | %s";

    private final List<HandleTestData> allTestDataReader = new ArrayList<>();

    private static TestData testData;

    public static synchronized TestData getTestData(){
        if(testData==null){
            testData = new TestData();
            return testData;
        }else {
            return testData;
        }
    }

    public TestData(){
        allTestDataReader.add(new ExcelData());
        allTestDataReader.add(new YamlData());
        this.readTestData();
    }

    public void readTestData(){
        for (HandleTestData testData : allTestDataReader){
            allTestData.putAll(testData.readTestData());
        }
    }

    public String get(String... keys){
        return getDataAsString(this.allTestData, keys);
    }

    public String get(Map<String, Object> allTestData, String... keys){
        return getDataAsString(this.allTestData, keys);
    }

    public String getDataAsString(String... keys){
        return getDataAsString(this.allTestData, keys);
    }

    @SuppressWarnings("unchecked")
    public String getDataAsString(Map<String, Object> allTestData, String... keys){
        String  data = "";
        Map yamlDataForKey = new LinkedHashMap(allTestData);
        for (String key : keys){
            try {
                Object keyData = yamlDataForKey.get(key);
                if (keyData instanceof String || keyData instanceof Integer || keyData instanceof Double){
                    data = String.valueOf(keyData);
                }else {
                    yamlDataForKey = (Map<String, Object>) keyData;
                }
            }catch (Exception e){
                logger.error(String.format(logUnablleToFetch, Arrays.toString(keys), e));
            }
        }
        return data.trim();
    }

    public Map<String, Object> getDataAsMap(String... keys){
        return getDataAsMap(this.allTestData, keys);
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> getDataAsMap(Map<String, Object> allTestData, String... keys){
        Map yamlDataForKey = new LinkedHashMap();
        for (String key : keys){
            try {
                yamlDataForKey = (Map) yamlDataForKey.get(key);
            }catch (Exception e){
                logger.error(String.format(logUnablleToFetch, Arrays.toString(keys), e));
            }
        }
        return yamlDataForKey;
    }

    public Map<String, String> getDataAsMapStringValue(String... keys){
        return getDataAsMapStringValue(this.allTestData, keys);
    }

    @SuppressWarnings("unchecked")
    public Map<String, String> getDataAsMapStringValue(Map<String, Object> allTestData ,String... keys){
        Map yamlDataForKey = new LinkedHashMap();
        for (String key : keys){
            try {
                yamlDataForKey = (Map) yamlDataForKey.get(key);
            }catch (Exception e){
                logger.error(String.format(logUnablleToFetch, Arrays.toString(keys), e));
            }
        }
        return yamlDataForKey;
    }

    public List<Map<String, Object>> getDataAsList(String... keys){
        return getDataAsList(this.allTestData, keys);
    }

    public List<Map<String, Object>> getDataAsList(Map<String, Object> allTestData, String... keys){
        List<Map<String, Object>> data = new ArrayList<>();
        Map<String, Object> testData = allTestData;
        for (String key : keys){
            try {
                Object keyData = testData.get(key);
                if (keyData instanceof List){
                    data = (List) keyData;
                }else {
                    testData = (Map<String, Object>) keyData;
                }
            }catch (Exception e){
                logger.error(String.format(logUnablleToFetch, Arrays.toString(keys), e));
            }
        }
        return data;
    }

    public List<String> getDataAsListOfString(String... keys){
        return getDataAsListOfString(this.allTestData, keys);
    }

    @SuppressWarnings("unchecked")
    public List<String> getDataAsListOfString(Map<String, Object> allTestData, String... keys){
        List<String> data = new ArrayList<>();
        Map yamlDataForKey = new LinkedHashMap();
        for (String key : keys){
            try {
                Object keyData = yamlDataForKey.get(key);
                if (keyData instanceof List){
                    data = (List) keyData;
                }else {
                    yamlDataForKey = (Map<String, Object>) keyData;
                }
            }catch (Exception e){
                logger.error(String.format(logUnablleToFetch, Arrays.toString(keys), e));
            }
        }
        return data;
    }









}
