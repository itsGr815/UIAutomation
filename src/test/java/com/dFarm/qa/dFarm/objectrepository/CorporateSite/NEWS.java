package com.dFarm.qa.dFarm.objectrepository.CorporateSite;

import com.dFarm.qa.dFarm.util.datahandler.ISuppllyLocatorInfo;
import org.openqa.selenium.By;

/**
 * @author G Ganesh
 */
public enum NEWS implements ISuppllyLocatorInfo {

    LABEL_NEWS(By.xpath("//section[@data-id='8b5a46d']//h1[text()=' News']"), "Label News"),
    NEWS_HEADER_1ST(By.xpath("//section[@data-id='8b5a46d']/..//section[@data-id='8d0bfff']"), "News Banner - 1st News Card"),
    NEWS_READ_MORE_1ST_NEWS_CARD(By.xpath("//section[@data-id='8b5a46d']/..//section[@data-id='8d0bfff']//a"), "Read More - 1st News Card"),
    NEWS_1ST_NEWS_CARD_HEADER(By.xpath("//section[@data-id='8b5a46d']/..//section[@data-id='8d0bfff']//h3"), "Read More - 1st News Card Title"),
    NEWS_HEADERS_1ST_ROW(By.xpath("//section[@data-id='3b61665']/div/div"), "News Banner - 1st Row"),
    NEWS_HEADERS_2ND_ROW(By.xpath("//section[@data-id='dfb5524']/div/div"), "News Banner - 2nd Row"),
    NEWS_HEADERS_3RD_ROW(By.xpath("//section[@data-id='471de61']/div/div"), "News Banner - 3rd Row"),

    PARAMETERIZE_OBJECT(By.xpath(""), "");

    public static synchronized ISuppllyLocatorInfo clickOnReadMoreFirstRow(int newsTopics) {
        return setLocator(By.xpath("(//section[@data-id='3b61665']/div/div)["+newsTopics+"]//a//span//span[text()='Read More']"), "News Banner - Clicking Random News Card In 1st Row");
    }
    public static synchronized ISuppllyLocatorInfo readMoreFirstRowNewsCardTitle(int newsTopics) {
        return setLocator(By.xpath("(//section[@data-id='3b61665']/div/div)["+newsTopics+"]//h1"), "News Banner - News Card Title In 1st Row");
    }
    public static synchronized ISuppllyLocatorInfo clickOnReadMoreSecondRow(int newsTopics) {
        return setLocator(By.xpath("(//section[@data-id='dfb5524']/div/div)["+newsTopics+"]//a"), "News Banner - Clicking Random News Card In 2nd Row");
    }
    public static synchronized ISuppllyLocatorInfo readMoreSecondRowNewsCardTitle(int newsTopics) {
        return setLocator(By.xpath("(//section[@data-id='dfb5524']/div/div)["+newsTopics+"]//h1"), "News Banner - News Card Title  In 2nd Row");
    }
    public static synchronized ISuppllyLocatorInfo clickOnReadMoreThirdRow(int newsTopics) {
        return setLocator(By.xpath("(//section[@data-id='471de61']/div/div)["+newsTopics+"]//a"), "News Banner - Clicking Random News Card In 3rd Row");
    }
    public static synchronized ISuppllyLocatorInfo readMoreThirdRowNewsCardTitle(int newsTopics) {
        return setLocator(By.xpath("(//section[@data-id='471de61']/div/div)["+newsTopics+"]//h1"), "News Banner - CNews Card Title  In 3rd Row");
    }

    public static ISuppllyLocatorInfo setLocator(By locator, String label) {
        NEWS.PARAMETERIZE_OBJECT.locator = locator;
        NEWS.PARAMETERIZE_OBJECT.lable = label;
        return NEWS.PARAMETERIZE_OBJECT;

    }

    NEWS(Object locator, String lable) {
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
