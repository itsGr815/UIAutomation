package com.dFarm.qa.dFarm.objectrepository.MarketPlace;

import com.dFarm.qa.dFarm.constants.MarketPlaceConstants;
import com.dFarm.qa.dFarm.util.datahandler.ISuppllyLocatorInfo;
import org.openqa.selenium.By;

public enum MPBUYERCONTACTFORM implements ISuppllyLocatorInfo, MarketPlaceConstants {

    //Buyer Contact Form
    OVERLAY_BUYER_REGISTRATION(By.xpath("//div[@role='dialog']"), "Buyer Contact Form Overlay"),
    TEXT_BUYER_REGISTRATION(By.xpath("//h2[contains(.,'Buyer Contact Form')]"), "Buyer Contact Form"),
    LABEL_FIRST_NAME(By.xpath("//div[text()='First Name ']/../input[@id='first_name']"), FIRST_NAME),

    LABEL_MIDDLE_NAME(By.xpath("//div[text()='Middle Name']/../input[@id='middle_name']"), MIDDLE_NAME),
    LABEL_LAST_NAME(By.xpath("//div[text()='Last Name ']/../input[@id='last_name']"), LAST_NAME),
    LABEL_BUSINESS_NAME(By.xpath("//div[text()='Business Name ']/../input[@id='businessName']"), BUSINESS_NAME),
    LABEL_COUNTRY(By.xpath("//div[text()='Country ']/../p-dropdown[@formcontrolname='country']"), COUNTRY),

    LABEL_EMAIL_ID(By.xpath("//div[text()='Email ID ']/../input[@id='floatingInput']"), EMAIL_ID),
    LABEL_MOBILE_NUMBER(By.xpath("//div[text()='Mobile Number ']/..//input[@formcontrolname='phone']"), MOBILE_NUMBER),
    LABEL_PRODUCE_OF_INTERST(By.xpath("//div[text()='Produce of Interest ']/..//p-chips[@formcontrolname='produce']"), PRODUCE_OF_INTEREST),

    BUTTON_CANCEL(By.xpath("//button[@type='button']/p[normalize-space(.)='Cancel']"), "Cancel Button"),
    BUTTON_SUBMIT(By.xpath("//button[@type='submit']/p[normalize-space(.)='Submit']"), "Submit Button"),




    PARAMETERIZE_OBJECT(By.xpath(""), "");

    public static ISuppllyLocatorInfo setLocator(By locator, String label) {
        MPBUYERCONTACTFORM.PARAMETERIZE_OBJECT.locator = locator;
        MPBUYERCONTACTFORM.PARAMETERIZE_OBJECT.lable = label;
        return MPBUYERCONTACTFORM.PARAMETERIZE_OBJECT;

    }

    MPBUYERCONTACTFORM(Object locator, String lable) {
        if (locator == null || lable == null) {
            throw new IllegalArgumentException("Argus must not be null");
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
