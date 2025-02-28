package com.dFarm.qa.dFarm.initializer;

import com.dFarm.qa.dFarm.constants.DFarmConstants;
import com.dFarm.qa.dFarm.constants.ServiceAndSauce;
import com.dFarm.qa.dFarm.constants.TestDataConstants;
import com.dFarm.qa.dFarm.util.execution.AllDataHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.Properties;

public class ConfigInitializer {
    //ToDo

    private static final Logger Logger = LoggerFactory.getLogger(ConfigInitializer.class);
    public static final Properties prop = new Properties();
    public static String testDataSvcServer;
    public static String env;

    public static void setConfigProperties() throws IOException {
        getProp().load(new FileInputStream(new File(TestDataConstants.CONFIGPATH).getAbsolutePath()));
        AllDataHolder.setBrowser(Optional.ofNullable(System.getenv(ServiceAndSauce.BROWSER)).orElse(getProp().getProperty(ServiceAndSauce.BROWSER)));
        AllDataHolder.setEnvironment(Optional.ofNullable(System.getProperty(TestDataConstants.ENVIRONMENT)).orElse(getProp().getProperty(TestDataConstants.DEFAULTREGION)));
        AllDataHolder.setUrl(Optional.ofNullable(System.getProperty(TestDataConstants.URL)).orElse(getProp().getProperty(TestDataConstants.DEFAULTURL)));
        AllDataHolder.setIsFastFail(Optional.ofNullable(System.getProperty(DFarmConstants.FASTFAIL)).orElse(getProp().getProperty(DFarmConstants.FASTFAIL)));
        AllDataHolder.setIsDebug(Optional.ofNullable(System.getProperty(ServiceAndSauce.ISDEBUG)).orElse(getProp().getProperty(ServiceAndSauce.ISDEBUG)));
        AllDataHolder.setExelPath(Optional.ofNullable(System.getProperty(TestDataConstants.EXCEL_PATH)).orElse(getProp().getProperty(TestDataConstants.EXCEL_PATH)));
        AllDataHolder.setYamlPath(Optional.ofNullable(System.getProperty(TestDataConstants.YAML_PATH)).orElse(getProp().getProperty(TestDataConstants.YAML_PATH)));
        AllDataHolder.setRetryCount(Optional.ofNullable(System.getProperty(DFarmConstants.RETRYCOUNT)).orElse(getProp().getProperty(DFarmConstants.RETRYCOUNT)));
        AllDataHolder.setWeightUnit(Optional.ofNullable(System.getProperty(DFarmConstants.WEIGHT_UNIT)).orElse(getProp().getProperty(DFarmConstants.WEIGHT_UNIT_KG)));
    }

    @SuppressWarnings("SameReturnValue")
    public static Properties getProp(){
        return prop;
    }
}
