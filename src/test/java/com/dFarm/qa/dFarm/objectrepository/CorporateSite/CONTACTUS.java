package com.dFarm.qa.dFarm.objectrepository.CorporateSite;

import com.dFarm.qa.dFarm.constants.CorporateSite;
import com.dFarm.qa.dFarm.util.datahandler.ISuppllyLocatorInfo;
import org.openqa.selenium.By;

public enum CONTACTUS implements ISuppllyLocatorInfo, CorporateSite {


    TEXT_CONTACT_US(By.xpath("//div[@id='pum_popup_title_2475']"), "dFarm Inc - Contact Us Overlay"),
    OVERLAY_CONTACT_US(By.xpath("(//form[@aria-label='Contact form'])[2]"), "dFarm Inc - Contact Us Overlay"),
    BUTTON_CONTACT_US_SUBMIT(By.xpath("(//input[@type='submit'])[2]"), "dFarm Inc - Submit button Contact Us Overlay"),
    BUTTON_CONTACT_US_CLOSE(By.xpath("(//button[@aria-label='Close'])[11]"), "dFarm Inc - Contact Us Overlay Close Button"),



    PARAMETERIZE_OBJECT(By.xpath(""), "");

    public static ISuppllyLocatorInfo setLocator(By locator, String label) {
        CONTACTUS.PARAMETERIZE_OBJECT.locator = locator;
        CONTACTUS.PARAMETERIZE_OBJECT.lable = label;
        return CONTACTUS.PARAMETERIZE_OBJECT;

    }

    CONTACTUS(Object locator, String lable) {
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
