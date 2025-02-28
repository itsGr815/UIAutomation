package com.dFarm.qa.dFarm.objectrepository.CorporateSite;

import com.dFarm.qa.dFarm.constants.CorporateSite;
import com.dFarm.qa.dFarm.util.datahandler.ISuppllyLocatorInfo;
import org.openqa.selenium.By;

public enum FSMA_204 implements ISuppllyLocatorInfo, CorporateSite {

    //What is FSMA 204?
    LABEL_WHAT_IS_FSMA_204(By.xpath("//h1[contains(text(),'What is FSMA 204?')]"), "What is FSMA 204?"),
    FSMA204_PARAGRAPH(By.xpath("//div[@data-id='2eff434']"), "What is FSMA 204? Paragraph"),
    VIDEO_FSMA_204(By.xpath("//video[@src='https://upload-files-prod.s3.us-east-2.amazonaws.com/dfarm_banner_video.mp4']"), "What is FSMA 204? Video"),
    LINK_FDA(By.xpath("//a[contains(text(),'https://www.fda.gov/food/food-safety-modernization')]"), FSMA_FDA_URL),
    BUTTON_GET_CONSULTATION(By.xpath("(//a[contains(text(),'Get a Consultation')])[1]"), "Get a Consultation Button"),


    //Six FSMA 204 Facts to Know
    LABEL_SIX_FSMA(By.xpath("//h1[contains(text(),'Six FSMA 204 Facts to Know')]"), "Six FSMA 204 Facts to Know"),
    PARAGRAPH_SIZ_FSMA(By.xpath("//section[@data-id='6438044']"), "Six FSMA 204 Facts to Know"),


    //What FSMA 204 Does Not Do
    LABEL_WHAT_FSMA(By.xpath("//h1[contains(text(),'What FSMA 204 Does Not Do')]"), "What FSMA 204 Does Not Do"),
    SECTION_FDA_COMMISSIONER(By.xpath("//div[@data-id='b6a2040']"), "FDA Commissioner Section"),
    SECTION_WHAT_FSMA(By.xpath("//div[@data-id='8515037']"), "What FSMA 204  Section"),
    LABEL_THE_BUSINESS_RICK(By.xpath("//section[@data-id='f1c4b18']//h5[contains(text(),'The Business Risks Associated with Food Safety ')]"), "The Business Risks Associated with Food Safety"),
    LABEL_YOUR_BUSINESS_PRIORITIES(By.xpath("//section[@data-id='f1c4b18']//h5[contains(text(),'Your Business Priorities Should be Focused on Mitigating Risk ')]"), "Your Business Priorities Should be Focused on Mitigating Risk"),
    LABEL_AS_A_FOOD_PRODUCER(By.xpath("//section[@data-id='f1c4b18']//h5[contains(text(),'As a Food Producer or Supplier, What You Must Do ')]"), "As a Food Producer or Supplier, What You Must Do"),
    BUTTON_GET_CONSULTATION_BOTTOM(By.xpath("(//a[contains(text(),'Get a Consultation')])[2]"), "Get a Consultation Button"),


    PARAMETERIZE_OBJECT(By.xpath(""), "");

    public static ISuppllyLocatorInfo setLocator(By locator, String label) {
        FSMA_204.PARAMETERIZE_OBJECT.locator = locator;
        FSMA_204.PARAMETERIZE_OBJECT.lable = label;
        return FSMA_204.PARAMETERIZE_OBJECT;

    }

    FSMA_204(Object locator, String lable) {
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
