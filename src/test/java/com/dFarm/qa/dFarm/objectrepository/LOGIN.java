package com.dFarm.qa.dFarm.objectrepository;

import com.dFarm.qa.dFarm.util.datahandler.ISuppllyLocatorInfo;
import org.openqa.selenium.By;

public enum LOGIN implements ISuppllyLocatorInfo {


    TEXTBOX_LOGINID_INPUT(By.xpath("//input[@formcontrolname='login_id']"), "User Name"),
    TEXTBOX_PASSWORD_INPUT(By.xpath("//input[@formcontrolname='password']"), "Password"),
    BUTTON_LOGIN(By.xpath("//button[@class='login-btn']"), "Login Button"),


    ICON_MP_SING_IN(By.xpath("//p[contains(.,'Sign In')]"), "MP - Sign In Icon"),
    BUTTON_MP_SIGN_IN(By.xpath("//button//span[contains(.,'Sign In')]"), "MP - Sign In Button"),


    PARAMETERIZE_OBJECT(By.xpath(""), "");



    public static synchronized ISuppllyLocatorInfo getHomePageTabs(String tabName){
        return setLocator(By.xpath("//div[@class='navigation-list-box']//a[contains(.,'"+tabName+"')]"), "Tab Name: " + tabName);
    }

    public static ISuppllyLocatorInfo setLocator(By locator, String lable) {
        LOGIN.PARAMETERIZE_OBJECT.locator = locator;
        LOGIN.PARAMETERIZE_OBJECT.label = lable;
        return LOGIN.PARAMETERIZE_OBJECT;

    }

    LOGIN(Object locator, String label) {
        if(locator == null || label == null){
            throw  new IllegalArgumentException("Argus must not be null");
        }
        this.locator = locator;
        this.label = label;
    }


    private Object locator;
    private String label;

    public Object getLocator() {
        return locator;
    }

    public String getLabel() {
        return label;
    }
}
