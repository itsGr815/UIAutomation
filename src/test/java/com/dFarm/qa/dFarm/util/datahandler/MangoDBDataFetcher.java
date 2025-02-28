package com.dFarm.qa.dFarm.util.datahandler;

import com.dFarm.qa.dFarm.util.execution.FlexFrameWorkRunTimeException;
import com.dFarm.qa.dFarm.util.execution.GlobalHelpers;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.BasicBSONObject;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class MangoDBDataFetcher {

    /**
     * @implNote This method is created to connect with the MongoDB database collection and the desired query
     * @param uriValue
     * @param queryValue
     * @return
     * @throws Exception
     */
    public static FindIterable<Document> MongoDbConnection(String uriValue, Map<String, Object> queryValue) throws Exception{
        FindIterable<Document> queryResult;
        try {
            BasicBSONObject query = new BasicBSONObject();
            query.putAll(queryValue);
            queryResult = getDBCollection(uriValue).find((Bson) query);
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e);
        }
        return queryResult;
    }

    /**
     * @implNote This method is created to connect with the MongoDB with the database,
     *           collection and return db collection
     * @param uriValue
     * @return
     * @throws Exception
     */
    public static MongoCollection<Document> getDBCollection(String uriValue) throws Exception{
        MongoCollection<Document> dbCollection;
     try {
         GlobalHelpers.setupSSL();
         //Creating a Mongo client
         MongoClientURI uri = new MongoClientURI(uriValue);
         MongoClient clinet = new MongoClient(uriValue);
         MongoDatabase db = clinet.getDatabase(uri.getDatabase());
         // Retrieving a collection
         dbCollection = db.getCollection(uri.getCollection());
     }catch (Exception e){
         throw  new FlexFrameWorkRunTimeException(e);
     }
     return dbCollection;
    }

    public static List<JSONObject> mongoDbConnection(String uriValue,Bson dbQuery, Bson sortQuery) throws Exception{
        MongoClientURI uri = new MongoClientURI(uriValue);
        MongoClient client = new MongoClient(uri);
        MongoDatabase db = client.getDatabase(uri.getDatabase());
        //Retrieving a Collection
        MongoCollection<Document> collection = db.getCollection(uri.getCollection());
        List<JSONObject> response = new ArrayList<>();

        Block<Document> storeBlock = document -> {
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(document.toJson());
            }catch (JSONException e){
                e.printStackTrace();
            }
        };
        try {
            FindIterable<Document> results = collection.find(dbQuery);
            if (sortQuery !=  null){
                results.sort(sortQuery);
            }
            results.forEach(storeBlock);
        }catch (Exception e){
            throw new Exception(e);
        }finally {
            client.close();
        }
        return response;
    }

    /**
     * @implNote Method to perform update operation on Mongo DB
     * @param dbName
     * @param collection
     * @param uriValue
     * @param filter
     * @param date
     * @return
     * @throws Exception
     */
    public static String updateMongoDb(String dbName, String collection, String uriValue, Bson filter, Date date) throws Exception {
        Document myDoc = new Document();
        try {
            GlobalHelpers.setupSSL();
            // Creating a Mongo Client
            MongoClientURI uri = new MongoClientURI(uriValue);
            MongoClient client = new MongoClient(uri);
            MongoDatabase db = client.getDatabase(dbName);
            System.out.println(db.toString());
            // Retrieving a Collection
            MongoCollection<Document> dbCollection = db.getCollection(collection);
            Bson newValue = new Document("closeDate", date);
            Bson updateOpearationDocument = new Document("$set", newValue);
            dbCollection.updateOne(filter, updateOpearationDocument);
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e);
        }
        return myDoc.toJson();
    }



}
