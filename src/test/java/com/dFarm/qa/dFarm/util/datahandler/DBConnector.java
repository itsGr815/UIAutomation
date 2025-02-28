package com.dFarm.qa.dFarm.util.datahandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.dFarm.qa.dFarm.constants.DBConstants;
import com.dFarm.qa.dFarm.initializer.ConfigInitializer;
import com.dFarm.qa.dFarm.util.execution.GlobalHelpers;
//import okhttp3.Connection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class DBConnector implements DBConstants {

    private static final Logger logger = LoggerFactory.getLogger(DBConnector.class);
    private static Connection connection;

    public static Connection getConnectionInstance(String region) {
        Properties dbProperties = ConfigInitializer.prop;
        String connectionString = "";
        String dbUserName = "";
        String dbPassKey;
        try {
            connectionString = dbProperties.getProperty(region);
            dbUserName = GlobalHelpers.getDecodedValue(dbProperties.getProperty(DBConstants.USERNAME));
            dbPassKey = GlobalHelpers.getDecodedValue(dbProperties.getProperty(DBConstants.PASSWORD));
            logger.debug(
                    "Connecting to DB :\n Connection String : " + connectionString + "\n DB User Name :" + dbUserName);
            Class.forName(JDBCCLASS).newInstance();
            connection = DriverManager.getConnection(connectionString, dbUserName, dbPassKey);
            } catch (ClassNotFoundException cnfe) {
              logger.error("Unable to find JDBC jar in CLASSPATH i.e. " + JDBCCLASS, cnfe);
            } catch(SQLException e) {
              logger.error("Unable to connect to DB with \n Connection String : " + connectionString + "\n DB User Name:" + dbUserName, e);
            } catch(Exception e) {
             logger.error("Unable to connect to DB", e);
        }
        return connection;
    }
}
