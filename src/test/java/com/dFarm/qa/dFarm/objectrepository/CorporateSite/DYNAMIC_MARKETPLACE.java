package com.dFarm.qa.dFarm.objectrepository.CorporateSite;

import com.dFarm.qa.dFarm.constants.CorporateSite;
import com.dFarm.qa.dFarm.util.datahandler.ISuppllyLocatorInfo;
import org.openqa.selenium.By;

public enum DYNAMIC_MARKETPLACE implements ISuppllyLocatorInfo, CorporateSite {


    PARAMETERIZE_OBJECT(By.xpath(""), "");

    public static ISuppllyLocatorInfo setLocator(By locator, String label) {
        DYNAMIC_MARKETPLACE.PARAMETERIZE_OBJECT.locator = locator;
        DYNAMIC_MARKETPLACE.PARAMETERIZE_OBJECT.lable = label;
        return DYNAMIC_MARKETPLACE.PARAMETERIZE_OBJECT;

    }

    DYNAMIC_MARKETPLACE(Object locator, String lable) {
        if(locator == null || lable == null){
            throw  new IllegalArgumentException("Argus must not be null");
        }
        this.locator = locator;
        this.lable = lable;
    }

    private Object locator;
    private String lable;

    public Object getLocator() {
        return locator;
    }

    public String getLabel() {
        return lable;
    }
}
