package com.dFarm.qa.dFarm.pageobjects.CorporateSite;

import com.aventstack.extentreports.Status;
import com.dFarm.qa.dFarm.constants.CorporateSite;
import com.dFarm.qa.dFarm.constants.ReportingConstants;
import com.dFarm.qa.dFarm.objectrepository.CorporateSite.CORPORATESITE_HOME;
import com.dFarm.qa.dFarm.objectrepository.CorporateSite.NEWS;
import com.dFarm.qa.dFarm.util.execution.AllDataHolder;
import com.dFarm.qa.dFarm.util.execution.FlexFrameWorkRunTimeException;
import com.dFarm.qa.dFarm.util.execution.GlobalHelpers;
import com.dFarm.qa.dFarm.util.reporters.ExtentReporting;

/**
 * @author G Ganesh
 */
public class News extends ReportingConstants implements CorporateSite {

    private static String currentPageURL = "NA";


    public void verifyNewsPageFunctionality() throws FlexFrameWorkRunTimeException{
        try {
            verifyAndClickFooterNewsLink();
            verifyNewPageLoadAndContents();
            clickMainNewsCardHeader();
            clickMainNewsCardHeaderFirstRow();
            clickMainNewsCardHeaderSecondRow();
            clickMainNewsCardHeaderThirdRow();
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e.getMessage());
        }
    }

    /**
     * @implNote Method to verify the Footer Link News
     * @author Gangarapu.Ganesh
     */
    public void verifyAndClickFooterNewsLink() throws FlexFrameWorkRunTimeException {
        try {
            AllDataHolder.getUtil().report(1, "<mark>Verifying dFarm Inc Corporate Website Footer Section Link Navigation - News</mark>",
                    "<mark>Verifying dFarm Inc Corporate Website Footer Section</mark>", "");
            AllDataHolder.getUtil().scrollForSpecificElement(CORPORATESITE_HOME.SECTION_FOOTER);
            if(AllDataHolder.getUtil().isContentDisplayed(CORPORATESITE_HOME.SECTION_FOOTER)){
                AllDataHolder.getUtil().clickElement(CORPORATESITE_HOME.LINK_NEWS);
                AllDataHolder.getUtil().stringCompare(News_Url, AllDataHolder.getDriver().getCurrentUrl());
                currentPageURL = AllDataHolder.getDriver().getCurrentUrl();
            }else {
                AllDataHolder.getUtil().report(1, "<mark>Unable to Find - News Footer Link. Please Refreshing the page & Checking again</mark>",
                        "<mark>Re-Attempting to verify the News footer link verification</mark>", "");
                currentPageURL= AllDataHolder.getDriver().getCurrentUrl();
                AllDataHolder.getDriver().get(currentPageURL);
                AllDataHolder.getUtil().scrollForSpecificElement(CORPORATESITE_HOME.SECTION_FOOTER);
                if(AllDataHolder.getUtil().isContentDisplayed(CORPORATESITE_HOME.SECTION_FOOTER)){
                    AllDataHolder.getUtil().clickElement(CORPORATESITE_HOME.LINK_NEWS);
                    AllDataHolder.getUtil().stringCompare(News_Url, AllDataHolder.getDriver().getCurrentUrl());
                }else {
                    AllDataHolder.getUtil().report(4, "<mark>Unable to Find - News Footer Link. Please Check With Your Site Admin</mark>",
                            "<mark>News - Footer Link Verification is Failed</mark>", "");
                }
            }
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e.getMessage());
        }
    }

    /**
     * @implNote Method to verify the News Page Load And News Cards
     * @author Gangarapu.Ganesh
     */
    public void verifyNewPageLoadAndContents() throws FlexFrameWorkRunTimeException {
        try {
            AllDataHolder.getUtil().contentDisplayed(NEWS.LABEL_NEWS);
            AllDataHolder.getUtil().contentDisplayed(NEWS.NEWS_HEADER_1ST);
            AllDataHolder.getUtil().contentDisplayed(NEWS.NEWS_READ_MORE_1ST_NEWS_CARD);
            AllDataHolder.getUtil().contentDisplayed(NEWS.NEWS_HEADERS_1ST_ROW);
            Thread.sleep(10000);
            AllDataHolder.getUtil().scrollForSpecificElement(NEWS.NEWS_HEADERS_2ND_ROW);
            AllDataHolder.getUtil().contentDisplayed(NEWS.NEWS_HEADERS_2ND_ROW);
            AllDataHolder.getUtil().scrollForSpecificElement(NEWS.NEWS_HEADERS_3RD_ROW);
            AllDataHolder.getUtil().contentDisplayed(NEWS.NEWS_HEADERS_3RD_ROW);
            AllDataHolder.getUtil().scrollForSpecificElement(NEWS.NEWS_READ_MORE_1ST_NEWS_CARD);
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e.getMessage());
        }
    }

    /**
     * @implNote Method to verify the News Page Load for the 1st top header news card
     * @author Gangarapu.Ganesh
     */
    public void clickMainNewsCardHeader() throws FlexFrameWorkRunTimeException {
        AllDataHolder.getUtil().report(2, "<b>Clicking On First News Card Headers Card</b> - <mark> "+AllDataHolder.getUtil().getText(NEWS.NEWS_1ST_NEWS_CARD_HEADER)+"</mark>",
                "", "");
        if(AllDataHolder.getUtil().isContentDisplayed(NEWS.NEWS_HEADER_1ST)){
            AllDataHolder.getUtil().clickElement(NEWS.NEWS_READ_MORE_1ST_NEWS_CARD);
            verifyTheNewsCardURLNavigation();
        }
    }

    /**
     * @implNote Method to verify the News Page Load for the 1st row News Headers Randomly
     * @author Gangarapu.Ganesh
     */
    public void clickMainNewsCardHeaderFirstRow() throws FlexFrameWorkRunTimeException {
        int newsHeader1stRow = GlobalHelpers.randomNumberInt("1", "4");
        AllDataHolder.getUtil().report(2, "<b>Clicking On First Row News Card Headers Of Any One Randomly</b> - <mark> "+AllDataHolder.getUtil().getText(NEWS.readMoreFirstRowNewsCardTitle(newsHeader1stRow))+"</mark>",
                "", "");
        if(AllDataHolder.getUtil().isContentDisplayed(NEWS.NEWS_READ_MORE_1ST_NEWS_CARD)){
            AllDataHolder.getUtil().clickElement(NEWS.clickOnReadMoreFirstRow(newsHeader1stRow));
            verifyTheNewsCardURLNavigation();
        }
    }

    /**
     * @implNote Method to verify the News Page Load for the 2nd row News Headers Randomly
     * @author Gangarapu.Ganesh
     */
    public void clickMainNewsCardHeaderSecondRow() throws FlexFrameWorkRunTimeException {
        int newsHeader1stRow = GlobalHelpers.randomNumberInt("1", "4");
        AllDataHolder.getUtil().report(2, "<b>Clicking On First Row News Card Headers Of Any One Randomly</b> - <mark> "+AllDataHolder.getUtil().getText(NEWS.readMoreSecondRowNewsCardTitle(newsHeader1stRow))+"</mark>",
                "", "");
        if(AllDataHolder.getUtil().isContentDisplayed(NEWS.NEWS_HEADERS_2ND_ROW)){
            AllDataHolder.getUtil().clickElement(NEWS.clickOnReadMoreSecondRow(newsHeader1stRow));
            verifyTheNewsCardURLNavigation();
        }
    }

    /**
     * @implNote Method to verify the News Page Load for the 3rd row News Headers Randomly
     * @author Gangarapu.Ganesh
     */
    public void clickMainNewsCardHeaderThirdRow() throws FlexFrameWorkRunTimeException {
        int newsHeader1stRow = GlobalHelpers.randomNumberInt("1", "4");
        AllDataHolder.getUtil().report(2, "<b>Clicking On First Row News Card Headers Of Any One Randomly</b> - <mark> "+AllDataHolder.getUtil().getText(NEWS.readMoreThirdRowNewsCardTitle(1))+"</mark>",
                "", "");
        if(AllDataHolder.getUtil().isContentDisplayed(NEWS.NEWS_HEADERS_3RD_ROW)){
            AllDataHolder.getUtil().clickElement(NEWS.clickOnReadMoreThirdRow(1));
            verifyTheNewsCardURLNavigation();
        }
    }

    /**
     * @implNote Method to verify the News Link navigation and comparing thr URL and reporting back
     * @author Gangarapu.Ganesh
     */
    public void verifyTheNewsCardURLNavigation() throws FlexFrameWorkRunTimeException{
        String newsPageURL = "NA";
        try {
            AllDataHolder.getUtil().switchToNewWindow();
            newsPageURL = AllDataHolder.getDriver().getCurrentUrl();
            Thread.sleep(5000);
            AllDataHolder.getUtil().stringCompareIgnoreCaseNotEqual(News_Url, newsPageURL);
            AllDataHolder.getUtil().report(16, "<b>User Navigated To Below News Post By Clicking on Read More</b> - <br><mark> "+newsPageURL+"</mark>",
                    "<b>User Navigated To Below News Post By Clicking on Read More</b> - <br><mark> "+newsPageURL+"</mark>", "");

            AllDataHolder.getUtil().closeAllChildWindowsOrTabs();
            AllDataHolder.getUtil().switchToDefaultContent(NEWS.LABEL_NEWS);
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e.getMessage());
        }
    }

}
