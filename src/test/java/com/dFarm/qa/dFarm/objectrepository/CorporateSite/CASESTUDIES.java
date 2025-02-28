package com.dFarm.qa.dFarm.objectrepository.CorporateSite;

import com.dFarm.qa.dFarm.util.datahandler.ISuppllyLocatorInfo;
import org.openqa.selenium.By;

/**
 * @author G Ganesh
 */
public enum CASESTUDIES implements ISuppllyLocatorInfo {

    LABEL_CASE_STUDIES(By.xpath("//section[@data-id='15292b9']//h1[text()='Case Studies']"), "Case Studies Label"),
    ARTICLES_CASE_STUDIES(By.xpath("//section[@data-id='48c7e55']/div//article"), "Case Studies Articles List"),
    ARTICLE_FIRST(By.xpath("(//section[@data-id='48c7e55']/div//article//div//a)[1]"), "Case Studies Articles List"),
    ARTICLE_MANGO_TITLE(By.xpath("//h1[text()='Mango Agriculture Practices with dFarm']"), "Mango Agriculture Practices with dFarm"),
    ARTICLE_MANGO_INTRO(By.xpath("//div[@data-id='294bae0']"), "Mango Introduction Section"),
    ARTICLE_MANGO_INTRO_IMAGE(By.xpath("//div[@data-id='294bae0']/../..//img"), "Mango Introduction Section Image"),
    ARTICLE_MANGO_BACKGROUND(By.xpath("//h2[text()='Background']/../../../div"), "Mango Article Background Section"),
    ARTICLE_MANGO_FARM_MANAGEMENT(By.xpath("//h2[text()='Farm Management']/../../.."), "Article Farm Management Section"),
    ARTICLE_MANGO_FARM_MANAGEMENT_CONTENT(By.xpath("(//section[@data-id='694f0e3']/div/div)[1]"), "Article Farm Management Section Content"),
    ARTICLE_MANGO_FARM_MANAGEMENT_IMAGE(By.xpath("(//section[@data-id='694f0e3']/div/div)[2]"), "Article Farm Management Section Content"),

    ARTICLE_HARVESTING_METHODS(By.xpath("(//h2[text()='Harvesting Methodologies']/../../..)[1]"), " Article Section - Harvesting Methodologies"),
    ARTICLE_HARVESTING_METHODS_FIRST_PARAGRAPH(By.xpath("(//h2[text()='Harvesting Methodologies']/../../..)[1]//p"), "Article Section - Harvesting Methodologies First Section"),
    ARTICLE_HARVESTING_METHODS_SECOND_PARAGRAPH(By.xpath("(//h2[text()='Harvesting Methodologies']/../../..)[1]/../..//div//ul"), "Article Section - Harvesting Methodologies Second Section"),
    ARTICLE_HARVESTING_METHODS_FIRST_IMG(By.xpath("(//h2[text()='Harvesting Methodologies']/../../..)[1]//img"), "Article Section - Harvesting Methodologies First Image"),
    ARTICLE_POST_HARVESTING_IMAGES(By.xpath("(//h2[text()='Post-Harvest Management']/../../..)[1]//img"), "Post-Harvest Management Images"),
    ARTICLE_POST_HARVESTING_CONTENT(By.xpath("(//h2[text()='Post-Harvest Management']/../../..)[1]"), "Post-Harvest Management Content"),
    ARTICLE_PACKING_AND_TRANSPORTATION(By.xpath("(//h2[text()='Packaging and Transportation']/../../..)[1]"), "Packaging and Transportation"),
    ARTICLE_CONCLUSION(By.xpath("(//h2[text()='Conclusion']/../../..)[1]"), "Article Conclusion"),
    ARTICLE_ABOUT_DFARM(By.xpath("(//h2[text()='About dFarm']/../../..)[1]"), "Article About dFarm"),

    PARAMETERIZE_OBJECT(By.xpath(""), "");


    public static ISuppllyLocatorInfo setLocator(By locator, String label) {
        CASESTUDIES.PARAMETERIZE_OBJECT.locator = locator;
        CASESTUDIES.PARAMETERIZE_OBJECT.lable = label;
        return CASESTUDIES.PARAMETERIZE_OBJECT;

    }

    CASESTUDIES(Object locator, String lable) {
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
