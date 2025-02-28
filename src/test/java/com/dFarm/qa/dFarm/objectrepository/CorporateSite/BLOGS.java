package com.dFarm.qa.dFarm.objectrepository.CorporateSite;

import com.dFarm.qa.dFarm.util.datahandler.ISuppllyLocatorInfo;
import org.openqa.selenium.By;

/**
 * @author G Ganesh
 */
public enum BLOGS implements ISuppllyLocatorInfo {

    SECTION_HEADER_BLOGS(By.xpath("//section[@data-id='2ad96b1']"), " Blogs - Header Section"),
    SECTION_BLOGS_CONTENT(By.xpath("//section[@data-id='a0cb18e']"), "Blogs - Content Section"),
    BLOG_TOPICS(By.xpath("//section[@data-id='a0cb18e']//div[@class='premium-blog-filter']/ul"), "Blogs - Topics list"),
    LIST_BLOG_TOPICS(By.xpath("//section[@data-id='a0cb18e']//div[@class='premium-blog-filter']/ul/li"), "Blogs - Topics list "),
    LIST_BLOG_ARTICLES(By.xpath("//section[@data-id='a0cb18e']//div[@class='premium-blog-filter']/../..//article"), "Blogs - Articles"),
    BLOGS_NAVIGATION(By.xpath("//section[@data-id='a0cb18e']//div[@class='premium-blog-filter']/../..//nav"), "Blogs - Navigation"),
    BLOGS_NAV_PAGE(By.xpath("//section[@data-id='a0cb18e']//div[@class='premium-blog-filter']/../..//nav/a"), "Blogs - Navigation List"),

    PARAMETERIZE_OBJECT(By.xpath(""), "");


    public static synchronized ISuppllyLocatorInfo clickArticle(int number){
        //return setLocator(By.xpath("(//section[@data-id='a0cb18e']//div[@class='premium-blog-filter']/../..//article)["+number+"]"), "Clicking On Article");
        return setLocator(By.xpath("(//section[@data-id='a0cb18e']//div[@class='premium-blog-filter']/../..//article)["+number+"]//div//h1/a"), "Clicking On Article");
    }

    public static synchronized ISuppllyLocatorInfo clickArticleTopic(int articleTopic){
        return setLocator(By.xpath("(//section[@data-id='a0cb18e']//div[@class='premium-blog-filter']/ul/li)["+articleTopic+"]/a"), "Clicking On Article Topic");
    }

    public static ISuppllyLocatorInfo setLocator(By locator, String label) {
        BLOGS.PARAMETERIZE_OBJECT.locator = locator;
        BLOGS.PARAMETERIZE_OBJECT.lable = label;
        return BLOGS.PARAMETERIZE_OBJECT;

    }

    BLOGS(Object locator, String lable) {
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
