package com.dFarm.qa.dFarm.initializer;

import com.dFarm.qa.dFarm.constants.ServiceAndSauce;
import org.openqa.selenium.WebDriver;

public class SauceDriver extends DriverFactory implements ServiceAndSauce {


    @Override
    public WebDriver getDriverFactory() {
        return null;
    }
}
