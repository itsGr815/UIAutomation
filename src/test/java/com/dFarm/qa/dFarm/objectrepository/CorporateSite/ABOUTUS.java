package com.dFarm.qa.dFarm.objectrepository.CorporateSite;

import com.dFarm.qa.dFarm.util.datahandler.ISuppllyLocatorInfo;
import org.openqa.selenium.By;

/**
 * @author G Ganesh
 */
public enum ABOUTUS implements ISuppllyLocatorInfo {

    LABEL_MAKING_A_DIFFERENCE(By.xpath("(//h1[normalize-space()='Making a Difference'])[1]"), "Making a Difference"),
    TEXT_MAKING_DIFFERENCE(By.xpath("(//section[@data-id='114603d']//section/div/div)[1]"), "Making a Difference Content"),
    IMAGE_MAKING_DIFFERENCE(By.xpath("(//section[@data-id='114603d']//section/div/div)[2]"), "Image - Making a Difference"),
    LABEL_FUNDAMENTAL(By.xpath("//h2[normalize-space()='Fundamentally, We Are a Trusted Data Company']"), "Section - Fundamentally, We Are a Trusted Data Company"),
    TEXT_FUNDAMENTAL_SECTION_LIST(By.xpath("//div[@data-id='b74b4a7']"), "Fundamental Section Pointers"),
    TEXT_OUR_TEAM(By.xpath("//h2[text()='Our team brings deep expertise in agriculture, technology and data management']"), "Our team brings deep expertise in agriculture, " +
            "technology and data management"),
    LABEL_LEADERSHIP(By.xpath("(//h2[text()='Leadership'])[2]"), "Leadership Section"),
    LIST_LEADERSHIP_USA(By.xpath("//section[@id='London']"), ""),



    OVERLAY_LM_TITLE_VV_BABU(By.xpath("(//div[@id='pum_popup_title_816'])[1]"), "Learn More - Overlay  Title"),
    OVERLAY_LM_CONTENT(By.xpath("(//div[@id='pum_popup_title_816'])[1]/..//p"), "Learn More - Overlay Content"),
    OVERLAY_LM_CLOSE_VV_BABU(By.xpath("(//div[@id='pum_popup_title_816'])[1]/..//button"), "Learn More - Overlay Close"),





    PARAMETERIZE_OBJECT(By.xpath(""), "");

    public static synchronized ISuppllyLocatorInfo clickOnLearnMore(String personName){
        return setLocator(By.xpath("//h2[text()='"+personName+"']/../../../div//a[contains(text(),'Learn More')]"), "<mark> Learn More: </mark>" + personName);

    }

    public static synchronized ISuppllyLocatorInfo clickOnLearnMore2(String personName){
        return setLocator(By.xpath("(//h2[text()='"+personName+"']/../../../div//a[contains(text(),'Learn More')])[2]"), "<mark> Learn More: </mark>" + personName);

    }

    public static synchronized ISuppllyLocatorInfo verifyLearnMoreOverlayTitle(String personName){
        return setLocator(By.xpath("//div[contains(text(), '"+personName+"')]"), "<mark> Learn More Overlay: </mark>" + personName);
    }

    public static ISuppllyLocatorInfo setLocator(By locator, String label) {
        ABOUTUS.PARAMETERIZE_OBJECT.locator = locator;
        ABOUTUS.PARAMETERIZE_OBJECT.lable = label;
        return ABOUTUS.PARAMETERIZE_OBJECT;

    }

    ABOUTUS(Object locator, String lable) {
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
