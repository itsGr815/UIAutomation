package com.dFarm.qa.dFarm.objectrepository.CorporateSite;

import com.dFarm.qa.dFarm.constants.CorporateSite;
import com.dFarm.qa.dFarm.util.datahandler.ISuppllyLocatorInfo;
import org.openqa.selenium.By;

public enum TECHNOLOGIES implements ISuppllyLocatorInfo, CorporateSite {

    //Transformative Technologies
    LABEL_TRANSFORMATIVE_TECH(By.xpath("//h1[contains(text(),'Transformative Technologies')]"), "Transformative Technologies"),
    PARAGRAPH_TRANSFORMATIVE_TECH(By.xpath("//p[contains(text(),'dFarm’s patented data collection solutions utilize powerful')]"), TRANSFORMATIVE_TECH),
    IMG_TRANSFORMATIVE_TECH(By.xpath("//div[@data-id='08ca704']"), TRANSFORMATIVE_TECH),

    //How dFarm works
    LABEL_HOW_DFARM_WORKS(By.xpath("//h2[contains(text(),'How dFarm works')]"), "How dFarm works"),
    CARD_WHOLE_CHAIN_VISIBILITY(By.xpath("//div[@data-id='35f5e39']"), "Whole Chain Visibility"),
    CARD_REAL_TIME_AVAILABILITY(By.xpath("//div[@data-id='430e0b0']"), "Real-Time Availability"),
    CARD_INSIGHTS(By.xpath("//div[@data-id='2ee3090']"), "Insights"),
    CARD_SOLUTIONS(By.xpath("//div[@data-id='7c2c180']"), " Solution"),

    //Advanced Technologies Power Our Advanced Capabilities
    IFRAME(By.xpath("//iframe[@src='https://marketplace.dfarmhub.com/wp-animation/technologies-aims']"), "iFrame"),
    IFRAME2(By.xpath("//iframe[@nitro-lazy-src='https://marketplace.dfarmhub.com/wp-animation/technologies-aims']"),"Technoligies iFrame2"),
    LABEL_ADVANCED_TECH(By.xpath("//h2[text()='Advanced Technologies Power Our Advanced Capabilities']"), "Advanced Technologies Power Our Advanced Capabilities"),
    DIAGRAM_AIMS(By.xpath("//div[@class='row align-items-center']"), "AIMS Diagram"),

    //A Flexible, Intuitive User Experience
    LABEL_A_FLEXIBLE(By.xpath("//h1[text()='A Flexible, Intuitive User Experience']"), "A Flexible, Intuitive User Experience"),
    //IMG_A_FLEXIBLE(By.xpath("//img[@src='https://dfarminc.com/wp-content/uploads/2023/06/group-product.png']"), "IMG - A Flexible, Intuitive User Experience"),
    IMG_A_FLEXIBLE(By.xpath("//img[@src='https://cdn-jogah.nitrocdn.com/awnLFzHRrtRPrQiqsAWbUsBnVpkBIzoG/assets/images/optimized/rev-d27d87d/dfarminc.com/wp-content/uploads/2023/06/group-product.png']"),
            "IMG - A Flexible, Intuitive User Experience"),


    PARAMETERIZE_OBJECT(By.xpath(""), "");

    public static ISuppllyLocatorInfo setLocator(By locator, String label) {
        TECHNOLOGIES.PARAMETERIZE_OBJECT.locator = locator;
        TECHNOLOGIES.PARAMETERIZE_OBJECT.lable = label;
        return TECHNOLOGIES.PARAMETERIZE_OBJECT;

    }

    TECHNOLOGIES(Object locator, String lable) {
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
