package com.dFarm.qa.dFarm.util.datahandler;

import com.dFarm.qa.dFarm.util.execution.AllDataHolder;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MangoDBManager {


    //Connection String
    //hostname ex: 10.87.51.18.27017
    private static final String uriValue = "mangodb://user.name:password@hotname/tablename";
    //Xls collection name
    private static final String collectionName = "excelData";
    //MangoDB document unique key
    private static final String xlsKey = "Test Case Name";
    private static final Logger logger = LoggerFactory.getLogger(MongoDatabase.class);
    private static final MongoDatabase db;
    private static final MongoCollection<Document> collection;


    static {
        db = getDbInstance();
        collection = db.getCollection(collectionName);
    }

    private static MongoDatabase getDbInstance(){
        MongoClientURI uri = new MongoClientURI(uriValue);
        MongoClient client = new MongoClient(uri);
        //return database
        return client.getDatabase(uri.getDatabase());
    }

    public static void moveXLsData(Map<String, Map<String, String>> testData){
        //iterating through testData map to read into document object
        List<Document> documents = new ArrayList<>();
        for (Map.Entry<String, Map<String, String>> entry : testData.entrySet()){
            Document document = new Document();
            Map<String, String> internalMap = entry.getValue();
            //Inner iteration for extraData logic inside xls
            for (Map.Entry<String,String> internalEntry : internalMap.entrySet()){
                //added check to see if there are any values in extra data before adding them to the document object
                if (internalEntry.getKey().equalsIgnoreCase("extraData") && internalEntry.getValue().length() > 1){
                    String[] extraData = internalEntry.getValue().replaceAll("extraData=", "").split(";");
                    for (String extraDatum : extraData){
                        if (extraDatum.contains("=")){
                            //split and append if there is a value key value pair
                            String[] sub = extraDatum.split("=");
                            if (sub.length > 1){
                                document.append(sub[0], sub[1]);
                            }
                        }
                    }
                }
                //For any key-val other than extraData col
                else{
                    if (internalEntry.getValue().trim().length() > 0){
                        document.append(internalEntry.getKey(), internalEntry.getValue());
                    }
                }
            }
            //adding the final document into documents list
            documents.add(document);
        }
        //Performing one time insertion using insertMany function
        collection.insertMany(documents);
    }

    /**
     * @implNote Fetch the specific field value from a test data document
     * @param keys
     * @return
     */
    public String getField(String... keys){
        String value = "";
        try {
            //TODO :Hardcode index - needs change. POC fix with assumption of fixed length array size for keys.
            FindIterable<Document> cursor = collection.find(new BasicDBObject(xlsKey, keys[0]));
            value = cursor.iterator().next().getString(keys[1]);
        }catch (Exception e){
            logger.error("Unable to find " + keys[0] + " fields inside " + AllDataHolder.getCurrentTestCaseId() +
                    " mongo db document, continue to check on local test data.....");
        }
        return value;
    }

    public Map<String, Object> getDocumentMap(String key){
        FindIterable<Document> cursor = collection.find(new BasicDBObject(xlsKey, key));
        Map<String, Object> testCaseData = new HashMap<>();
        for (Map.Entry<String, Object> internalEntry : cursor.iterator().next().entrySet()){
            if (!"_id".equalsIgnoreCase(internalEntry.getKey())){
                testCaseData.put(internalEntry.getKey(), internalEntry.getValue());
            }
        }
        return testCaseData;
    }

}
