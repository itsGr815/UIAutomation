package com.dFarm.qa.dFarm.util.datahandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class OracleDataFetcher {

    private static final Logger logger = LoggerFactory.getLogger(OracleDataFetcher.class);

    /**
     * @implNote This method is created to fetch data from DB using Query(As
     * String parameter)  and column name(primary key as String)
     * @param query
     * @param keyIdentifier
     * @param region
     * @return
     * @throws SQLException
     */
    public static Map<String, Map<String, Object>> fetchData(String query, String keyIdentifier, String region) throws SQLException{
        Connection connect;
        Statement statement = null;
        Map<String, Map<String, Object>> dbData = new LinkedHashMap<>();
        Map<String, Object> columnData;
        connect = DBConnector.getConnectionInstance(region);
        //Creates a Statement object for sending SQL statements to the database
        statement = connect.createStatement();
        logger.debug("Running Query to fetch Data :\n" + query + "\nKey for column :" + keyIdentifier);
        //Executing the SQL query
        try (ResultSet result = statement.executeQuery(query)){
            String key = "";
            //Sorting the column names in an array
            List<String> headers = new ArrayList<>();
            for (int i = 1; i <= result.getMetaData().getColumnCount(); i++){
                String colName = result.getMetaData().getColumnName(i);
                headers.add(colName);
            }
            while (result.next()){
                int i = 1;
                columnData = new HashMap<>();
                while (true){
                    try {
                        Object data = result.getObject(i);
                        if (headers.get(i - 1).equals(keyIdentifier)){
                            key = data.toString();
                        }
                        columnData.put(headers.get(i -1), data);
                        i++;
                    }catch (Exception e){
                        break;
                    }
                }
                dbData.put(key, columnData);
            }
        }catch (Exception e){
            logger.error("Unable to fetch the Data from DB : "+ e);
        }finally {
            if (statement != null){
                statement.close();
            }
        }
        return dbData;
    }

    public static List<Map<String, Object >> fetchAllData(String query, String keyIdentifier, String region) throws SQLException{
        Connection connect;
        Statement statement = null;
        List<Map<String, Object>> dbData = new LinkedList<>();
        Map<String, Object> columnData;
        connect = DBConnector.getConnectionInstance(region);
        //Creates a Statement object for sending SQL statements to the database
        statement = connect.createStatement();
        logger.debug("Running Query to fetch Data :\n" + query + "\nKey for column :" + keyIdentifier);
        //Executing the SQL query
        try (ResultSet result = statement.executeQuery(query)){
            //Sorting the column name in a array
            List<String> headers = new ArrayList<>();
            for (int i = 1; i <= result.getMetaData().getColumnCount(); i++){
                String colName = result.getMetaData().getColumnName(i);
                headers.add(colName);
            }
            int count = 1;
            while (result.next()){
                int i = 1;
                columnData = new LinkedHashMap<>();
                while (true){
                    try {
                        Object data = result.getObject(i);
                        columnData.put(headers.get(i - 1) + count, data);
                        i++;
                    }catch (Exception e){
                        break;
                    }
                    count++;
                }
                dbData.add(columnData);
            }
        }catch (Exception e){
            logger.error("Unable to fetch Data from DB :" + e);
        }finally {
            if (statement != null){
                statement.close();
            }
        }
        return dbData;
    }




















}
