package com.dFarm.qa.dFarm.objectrepository.Prod;

import com.dFarm.qa.dFarm.util.datahandler.ISuppllyLocatorInfo;
import org.openqa.selenium.By;


public enum KFPCL implements ISuppllyLocatorInfo , com.dFarm.qa.dFarm.constants.KFPCL {

    LABEL_WELCOME(By.xpath("lp-welcome-text"), WELCOME_TEXT),

    PARAMETERIZE_OBJECT(By.xpath(""), "");
    ;

    public static synchronized ISuppllyLocatorInfo inputDepartmentFields(int count) {
        return setLocator(By.xpath("(//div[@class='manage-employee-box']/form/div[@class='row'][2]/div)["+count+"]"), "Department Department Table");
    }
    public static ISuppllyLocatorInfo setLocator(By locator, String lable) {
        KFPCL.PARAMETERIZE_OBJECT.locator = locator;
        KFPCL.PARAMETERIZE_OBJECT.lable = lable;
        return KFPCL.PARAMETERIZE_OBJECT;

    }


    KFPCL(Object locator, String label) {
        if(locator == null || label == null){
            throw  new IllegalArgumentException("Argus must not be null");
        }
        this.locator = locator;
        this.lable = label;
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
