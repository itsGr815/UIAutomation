package com.dFarm.qa.dFarm.initializer;

import com.dFarm.qa.dFarm.constants.ServiceAndSauce;
import com.dFarm.qa.dFarm.constants.TestDataConstants;
import com.dFarm.qa.dFarm.util.execution.AllDataHolder;
import org.apache.xmlbeans.impl.xb.xsdschema.All;
import org.openqa.selenium.WebDriver;

public class DriverMode implements TestDataConstants {

    private static boolean isWebDriverMode;
    private static boolean isHeadLessMode;
    private static boolean isSauce;

    public DriverMode(){
        isSauce = System.getProperty(ServiceAndSauce.ISSAUCE) != null && Boolean.parseBoolean(System.getProperty(ServiceAndSauce.ISSAUCE));
        isWebDriverMode = true;
        isHeadLessMode = System.getenv(HEADLESSMODE) != null && System.getenv(HEADLESSMODE).equals(FLAGYES);
    }

    public DriverFactory getDriverFactory() {
        if (isSauce){
            DriverFactory driver = new SauceDriver();
            driver.setBrowser(AllDataHolder.getBrowser());
        }
        if(isHeadLessMode){
            return new PhantomDriver();
        }
        if(isWebDriverMode){
            DriverFactory driver = new WebDriverFactory();
           // driver.setBrowser("chrome");
            driver.setBrowser(AllDataHolder.getBrowser());
            return driver;
        }
        return  new WebDriverFactory();
    }

    public static boolean isSauce(){
        return isSauce;
    }
}
