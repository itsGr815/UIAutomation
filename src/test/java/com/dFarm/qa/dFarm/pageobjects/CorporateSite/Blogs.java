package com.dFarm.qa.dFarm.pageobjects.CorporateSite;

import com.dFarm.qa.dFarm.constants.CorporateSite;
import com.dFarm.qa.dFarm.constants.ReportingConstants;
import com.dFarm.qa.dFarm.objectrepository.CorporateSite.BLOGS;
import com.dFarm.qa.dFarm.objectrepository.CorporateSite.CORPORATESITE_HOME;
import com.dFarm.qa.dFarm.util.execution.AllDataHolder;
import com.dFarm.qa.dFarm.util.execution.FlexFrameWorkRunTimeException;
import com.dFarm.qa.dFarm.util.execution.GlobalHelpers;
import org.apache.xmlbeans.impl.xb.xsdschema.All;

import java.util.Arrays;

/**
 * @author G Ganesh
 */
public class Blogs extends ReportingConstants implements CorporateSite {

    private static boolean isArticleDisplayed = false;

    /**
     * @implNote Method to verify the Footer Link Blogs and content of the Blogs
     * @author Gangarapu.Ganesh
     */
    public void verifyBlogsFooterLinkLoading() throws FlexFrameWorkRunTimeException{
        try {
            verifyAndClickFooterBlogsLink();
            verifyBlogsHeaderSection();
            verifyBlogsArticlesSection();
            if (!isArticleDisplayed) {
                AllDataHolder.getDriver().get(AllDataHolder.getDriver().getCurrentUrl());
            }
            verifyBlogsFooterNavigation();
            verifyBlogsClickArticle(0);
            AllDataHolder.getDriver().get(Blogs_Url);
            clickOnArticleTopicAndVerifyContent();
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e.getMessage());
        }
    }

    /**
     * @implNote Method to verify the Footer Link Blogs
     * @author Gangarapu.Ganesh
     */
    public void verifyAndClickFooterBlogsLink() throws FlexFrameWorkRunTimeException {
        try {
            AllDataHolder.getUtil().report(1, "<mark>Verifying dFarm Inc Corporate Website Footer Section Link Navigation - Blogs</mark>",
                    "<mark>Verifying dFarm Inc Corporate Website Footer Section</mark>", "");
            AllDataHolder.getUtil().scrollForSpecificElement(CORPORATESITE_HOME.SECTION_FOOTER);
            if(AllDataHolder.getUtil().isContentDisplayed(CORPORATESITE_HOME.SECTION_FOOTER)){
                AllDataHolder.getUtil().clickElement(CORPORATESITE_HOME.LINK_BLOG);
                AllDataHolder.getUtil().stringCompare(Blogs_Url, AllDataHolder.getDriver().getCurrentUrl());
            }else {
                AllDataHolder.getUtil().report(1, "<mark>Unable to Find - Blogs Footer Link. Please Refreshing the page & Checking again</mark>",
                        "<mark>Re-Attempting to verify the Blogs footer link verification</mark>", "");
                String URL= AllDataHolder.getDriver().getCurrentUrl();
                AllDataHolder.getDriver().get(URL);
                AllDataHolder.getUtil().scrollForSpecificElement(CORPORATESITE_HOME.SECTION_FOOTER);
                if(AllDataHolder.getUtil().isContentDisplayed(CORPORATESITE_HOME.SECTION_FOOTER)){
                    AllDataHolder.getUtil().clickElement(CORPORATESITE_HOME.LINK_BLOG);
                    AllDataHolder.getUtil().stringCompare(Blogs_Url, AllDataHolder.getDriver().getCurrentUrl());
                }else {
                    AllDataHolder.getUtil().report(4, "<mark>Unable to Find - Blogs Footer Link. Please Check With Your Site Admin</mark>",
                            "<mark>Blogs - Footer Link Verification is Failed</mark>", "");
                }
            }
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e.getMessage());
        }
    }

    /**
     * @implNote Method to verify the Blogs Header Section
     * @author Gangarapu.Ganesh
     */
    public void verifyBlogsHeaderSection() throws FlexFrameWorkRunTimeException {
        try {
            AllDataHolder.getUtil().contentDisplayed(BLOGS.SECTION_HEADER_BLOGS);
            AllDataHolder.getUtil().contentDisplayed(BLOGS.SECTION_BLOGS_CONTENT);
            AllDataHolder.getUtil().contentDisplayed(BLOGS.BLOG_TOPICS);
            AllDataHolder.getUtil().contentDisplayed(BLOGS.LIST_BLOG_TOPICS);
            AllDataHolder.getUtil().listOfstringComparision(Arrays.asList(CorporateSite.BLOGS_ARTICLES_TOPIC), AllDataHolder.getUtil().getTextFromWebElements(BLOGS.LIST_BLOG_TOPICS));
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e.getMessage());
        }
    }

    /**
     * @implNote Method to verify the Blogs Header Section
     * @author Gangarapu.Ganesh
     */
    public boolean verifyBlogsArticlesSection() throws FlexFrameWorkRunTimeException {
        try {
            if(AllDataHolder.getUtil().isContentDisplayed(BLOGS.LIST_BLOG_ARTICLES)){
                AllDataHolder.getUtil().contentDisplayed(BLOGS.LIST_BLOG_ARTICLES);
                isArticleDisplayed = true;
            }
            return isArticleDisplayed;
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e.getMessage());
        }
    }

    /**
     * @implNote Method to verify the Blogs Header Section
     * @author Gangarapu.Ganesh
     */
    public void verifyBlogsClickArticle(int articleTopic) throws FlexFrameWorkRunTimeException {
        String getCurrentURL;
        try {
        AllDataHolder.getUtil().clickElementJS(BLOGS.clickArticle(articleTopic >0 ? articleTopic : GlobalHelpers.randomNumberInt("1", "10")));
        getCurrentURL = AllDataHolder.getDriver().getCurrentUrl();
        AllDataHolder.getUtil().stringCompareIgnoreCaseNotEqual(Blogs_Url, getCurrentURL);
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e.getMessage());
        }
    }

    /**
     * @implNote Method to verify the Blogs Navigation Section
     * @author Gangarapu.Ganesh
     */
    public void verifyBlogsFooterNavigation() throws FlexFrameWorkRunTimeException {
        try {
            AllDataHolder.getUtil().contentDisplayed(BLOGS.BLOGS_NAVIGATION);
            AllDataHolder.getUtil().contentDisplayed(BLOGS.BLOGS_NAV_PAGE);
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e.getMessage());
        }
    }

    /**
     * @implNote Method to verify the Blogs Articles
     * @author Gangarapu.Ganesh
     */
    public void clickOnArticleTopicAndVerifyContent() throws FlexFrameWorkRunTimeException{
        try {
            String currentPageURL = AllDataHolder.getDriver().getCurrentUrl();
            for (int i = 1; i< CorporateSite.BLOGS_ARTICLES_TOPIC.length; i++){
                Thread.sleep(10000);
                AllDataHolder.getUtil().clickElement(BLOGS.BLOG_TOPICS);
                AllDataHolder.getUtil().clickElementJS(BLOGS.clickArticleTopic(i+1));
                Thread.sleep(10000);
                    if(AllDataHolder.getUtil().isContentDisplayed(BLOGS.LIST_BLOG_ARTICLES)){
                    AllDataHolder.getUtil().report(1, "<mark>Clicked On Article Topic : <mark>"+AllDataHolder.getUtil().getText(BLOGS.clickArticleTopic(i+1))+"</mark></mark>",
                            "<mark>Clicking on Article Topic :/mark>", "");
                    verifyBlogsClickArticle(1);
                    AllDataHolder.getDriver().get(currentPageURL);
                    Thread.sleep(8000);
                    AllDataHolder.getUtil().setFocusOnParticularElement(BLOGS.BLOG_TOPICS);
                    AllDataHolder.getUtil().clickElement(BLOGS.BLOG_TOPICS);
                }else {
                    AllDataHolder.getUtil().report(4, "<mark>Unable to Find - Article details for the Topic : <mark>"+AllDataHolder.getUtil().getText(BLOGS.clickArticleTopic(i+1))+"</mark></mark>",
                            "<mark>Unable to Find - Article details for the Topic</mark>", "");
                }

            }

        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e.getMessage());
        }
    }

}
