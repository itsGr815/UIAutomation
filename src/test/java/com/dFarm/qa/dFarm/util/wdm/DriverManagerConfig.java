package com.dFarm.qa.dFarm.util.wdm;

import com.dFarm.qa.dFarm.constants.DriverConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class DriverManagerConfig implements DriverConstants {

    private static DriverManagerConfig instance;
    private static Properties prop;
    private static final Logger Logger = LoggerFactory.getLogger(DriverManagerConfig.class);

    private DriverManagerConfig(){
      try {
          Logger.debug("Instantiating DriverManagerConfig to store all data to properties");
          prop = new Properties();
          prop.load(new FileInputStream(new File(DRIVERCONFIGLOCATION)));
      }catch (IOException ioException){
          Logger.error("Unable to given config file"+DRIVERCONFIGLOCATION+" check if specified PATH is correct " +ioException);
      }catch (Exception e){
          Logger.error("Unable to Config file " +e);
      }
    }


   private static DriverManagerConfig getInstance(){
        if(instance == null){
            new DriverManagerConfig();
        }
        return instance;
   }

   public static String getProperty(String key){
        String value = "";
        try {
            Logger.debug("Fetching " + key + " From Driver config");
            value= getInstance().prop.getProperty(key);
        }catch (Exception e){
            Logger.error("Unable to fetch the key " + key + " Make sure specified key is correct");
        }
        return value;
   }

}
