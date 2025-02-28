package com.dFarm.qa.dFarm.initializer;

import com.dFarm.qa.dFarm.constants.DriverConstants;
import org.openqa.selenium.WebDriver;

public abstract class DriverFactory implements DriverConstants {


    private String browser;

    /*
     * Gets the driver factory
     *
     * @return the driver factory
     */
    public abstract WebDriver getDriverFactory();

    /*
     * Gets the version
     *
     * @return the version
     */
    protected String getVersion(String version){
        //return version.equals(LATEST)?
        System.out.println("Browser Version is" + version);
        return version;
    }

    /*
     * Gets the browser
     *
     * @return the browser
     */
    public String getBrowser(){
        return this.browser;
    }

    /*
     * Sets the browser
     */
    public void setBrowser(String browser){
        this.browser = browser;
    }
}



