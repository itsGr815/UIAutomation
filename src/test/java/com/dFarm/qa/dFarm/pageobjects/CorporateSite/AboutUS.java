package com.dFarm.qa.dFarm.pageobjects.CorporateSite;

import com.dFarm.qa.dFarm.constants.CorporateSite;
import com.dFarm.qa.dFarm.constants.ReportingConstants;
import com.dFarm.qa.dFarm.objectrepository.CorporateSite.ABOUTUS;
import com.dFarm.qa.dFarm.objectrepository.CorporateSite.CORPORATESITE_HOME;
import com.dFarm.qa.dFarm.util.execution.AllDataHolder;
import com.dFarm.qa.dFarm.util.execution.FlexFrameWorkRunTimeException;

/**
 * @author G Ganesh
 */
public class AboutUS extends ReportingConstants implements CorporateSite {

    /**
     * @implNote Method to verify the Footer Link About Us
     * @author Gangarapu.Ganesh
     */
    public void verifyFooterAboutUsLink() throws FlexFrameWorkRunTimeException {
        try {
            AllDataHolder.getUtil().report(1, "<mark>Verifying dFarm Inc Corporate Website Footer Section Link Navigation - About Us</mark>",
                    "<mark>Verifying dFarm Inc Corporate Website Footer Section</mark>", "");
            AllDataHolder.getUtil().scrollForSpecificElement(CORPORATESITE_HOME.SECTION_FOOTER);
            if(AllDataHolder.getUtil().isContentDisplayed(CORPORATESITE_HOME.SECTION_FOOTER)){
                AllDataHolder.getUtil().clickElement(CORPORATESITE_HOME.LINK_ABOUT_US);
                AllDataHolder.getUtil().stringCompare(About_US_Url, AllDataHolder.getDriver().getCurrentUrl());
            }else {
                AllDataHolder.getUtil().report(1, "<mark>Unable to Find - About Us Footer Link. Please Refreshing the page & Checking again</mark>",
                        "<mark>Re-Attempting to verify the About Us footer link verification</mark>", "");
                String URL= AllDataHolder.getDriver().getCurrentUrl();
                AllDataHolder.getDriver().get(URL);
                AllDataHolder.getUtil().scrollForSpecificElement(CORPORATESITE_HOME.SECTION_FOOTER);
                if(AllDataHolder.getUtil().isContentDisplayed(CORPORATESITE_HOME.SECTION_FOOTER)){
                    AllDataHolder.getUtil().clickElement(CORPORATESITE_HOME.LINK_ABOUT_US);
                    AllDataHolder.getUtil().stringCompare(About_US_Url, AllDataHolder.getDriver().getCurrentUrl());
                }else {
                    AllDataHolder.getUtil().report(4, "<mark>Unable to Find - About Us Footer Link. Please Check With Your Site Admin</mark>",
                            "<mark>About Us - Footer Link Verification is Failed</mark>", "");
                }
            }
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e.getMessage());
        }
    }

    /**
     * @implNote Method to verify the About Us Making Difference Section
     * @author Gangarapu.Ganesh
     */
    public void verifyAboutUsMakingDifferenceSection() throws FlexFrameWorkRunTimeException{
        try {
            AllDataHolder.getUtil().contentDisplayed(ABOUTUS.LABEL_MAKING_A_DIFFERENCE);
            AllDataHolder.getUtil().contentDisplayed(ABOUTUS.TEXT_MAKING_DIFFERENCE);
            AllDataHolder.getUtil().report(1, "<mark>Verifying About Us Page Making Difference Section</mark>",
                    "<mark>"+AllDataHolder.getUtil().getText(ABOUTUS.TEXT_MAKING_DIFFERENCE)+"</mark>", "");
            AllDataHolder.getUtil().contentDisplayed(ABOUTUS.IMAGE_MAKING_DIFFERENCE);
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e.getMessage());
        }
    }

    /**
     * @implNote Method to verify the About Us Fundamental Section
     * @author Gangarapu.Ganesh
     */
    public void verifyAboutUsFundamentalSection() throws FlexFrameWorkRunTimeException{
        try {
            AllDataHolder.getUtil().contentDisplayed(ABOUTUS.LABEL_FUNDAMENTAL);
            AllDataHolder.getUtil().contentDisplayed(ABOUTUS.TEXT_FUNDAMENTAL_SECTION_LIST);
            AllDataHolder.getUtil().report(1, "<mark>Verifying About Us Page Fundamental  Section</mark>",
                    "<mark>"+AllDataHolder.getUtil().getText(ABOUTUS.TEXT_FUNDAMENTAL_SECTION_LIST)+"</mark>", "");
            AllDataHolder.getUtil().contentDisplayed(ABOUTUS.TEXT_OUR_TEAM);
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e.getMessage());
        }
    }

    /**
     * @implNote Method to verify the About Us Fundamental Section
     * @author Gangarapu.Ganesh
     */
    public void verifyAboutUsLeadershipSection() throws FlexFrameWorkRunTimeException{
        try {
            AllDataHolder.getUtil().contentDisplayed(ABOUTUS.LABEL_LEADERSHIP);


            AllDataHolder.getUtil().contentDisplayed(ABOUTUS.TEXT_OUR_TEAM);
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e.getMessage());
        }
    }
}
