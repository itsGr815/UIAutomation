package com.dFarm.qa.dFarm.pageobjects.CorporateSite;

import com.dFarm.qa.dFarm.constants.CorporateSite;
import com.dFarm.qa.dFarm.constants.ReportingConstants;
import com.dFarm.qa.dFarm.objectrepository.CorporateSite.CORPORATESITE_HOME;
import com.dFarm.qa.dFarm.objectrepository.CorporateSite.TECHNOLOGIES;
import com.dFarm.qa.dFarm.util.execution.AllDataHolder;
import com.dFarm.qa.dFarm.util.execution.FlexFrameWorkRunTimeException;
import org.apache.xmlbeans.impl.xb.xsdschema.All;

/**
 * @author G Ganesh
 */
public class Technologies  extends ReportingConstants implements CorporateSite {

    private static String currentPageURL = "NA";

    /**
     * @implNote Method to verify the Footer Link Technologies
     * @author Gangarapu.Ganesh
     */
    public void verifyTechnologiesPage() throws FlexFrameWorkRunTimeException {
        try {
            verifyAndClickHeaderNewsLink();
            verifyTransformativeTechnologies();
            verifyHowdFarmWorks();
            verifyAdvancedTechnologies();
            verifyAFlexibleIntuitiveUserExperience();
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e.getMessage());
        }
    }


    /**
     * @implNote Method to verify the Footer Link Technologies
     * @author Gangarapu.Ganesh
     */
    public void verifyAndClickFooterNewsLink() throws FlexFrameWorkRunTimeException {
        try {
            AllDataHolder.getUtil().report(1, "<mark>Verifying dFarm Inc Corporate Website Footer Section Link Navigation - Technologies</mark>",
                    "<mark>Verifying dFarm Inc Corporate Website Footer Section</mark>", "");
            AllDataHolder.getUtil().scrollForSpecificElement(CORPORATESITE_HOME.SECTION_FOOTER);
            if(AllDataHolder.getUtil().isContentDisplayed(CORPORATESITE_HOME.SECTION_FOOTER)){
                AllDataHolder.getUtil().clickElement(CORPORATESITE_HOME.LINK_FOOTER_TECHNOLOGIES);
                AllDataHolder.getUtil().stringCompare(Technologies_Url, AllDataHolder.getDriver().getCurrentUrl());
                currentPageURL = AllDataHolder.getDriver().getCurrentUrl();
            }else {
                AllDataHolder.getUtil().report(1, "<mark>Unable to Find - Technologies Footer Link. Please Refreshing the page & Checking again</mark>",
                        "<mark>Re-Attempting to verify the Technologies footer link verification</mark>", "");
                currentPageURL= AllDataHolder.getDriver().getCurrentUrl();
                AllDataHolder.getDriver().get(currentPageURL);
                AllDataHolder.getUtil().scrollForSpecificElement(CORPORATESITE_HOME.SECTION_FOOTER);
                if(AllDataHolder.getUtil().isContentDisplayed(CORPORATESITE_HOME.SECTION_FOOTER)){
                    AllDataHolder.getUtil().clickElement(CORPORATESITE_HOME.LINK_FOOTER_TECHNOLOGIES);
                    AllDataHolder.getUtil().stringCompare(Technologies_Url, AllDataHolder.getDriver().getCurrentUrl());
                }else {
                    AllDataHolder.getUtil().report(4, "<mark>Unable to Find - Technologies Footer Link. Please Check With Your Site Admin</mark>",
                            "<mark>News - Footer Link Verification is Failed</mark>", "");
                }
            }
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e.getMessage());
        }
    }

    /**
     * @implNote Method to verify the Header Link Technologies
     * @author Gangarapu.Ganesh
     */
    public void verifyAndClickHeaderNewsLink() throws FlexFrameWorkRunTimeException {
        try {
            AllDataHolder.getUtil().report(1, "<mark>Verifying dFarm Inc Corporate Website Header Section Link Navigation - Technologies</mark>",
                    "<mark>Verifying dFarm Inc Corporate Website Header Section</mark>", "");
            AllDataHolder.getUtil().scrollForSpecificElement(CORPORATESITE_HOME.SECTION_HEADER);
            if(AllDataHolder.getUtil().isContentDisplayed(CORPORATESITE_HOME.SECTION_HEADER)){
                AllDataHolder.getUtil().clickElement(CORPORATESITE_HOME.LINK_TECHNOLOGIES);
                AllDataHolder.getUtil().stringCompare(Technologies_Url, AllDataHolder.getDriver().getCurrentUrl());
                currentPageURL = AllDataHolder.getDriver().getCurrentUrl();
            }else {
                AllDataHolder.getUtil().report(1, "<mark>Unable to Find - Technologies Header Link. Please Refreshing the page & Checking again</mark>",
                        "<mark>Re-Attempting to verify the Technologies Header link verification</mark>", "");
                currentPageURL= AllDataHolder.getDriver().getCurrentUrl();
                AllDataHolder.getDriver().get(currentPageURL);
                AllDataHolder.getUtil().scrollForSpecificElement(CORPORATESITE_HOME.SECTION_HEADER);
                if(AllDataHolder.getUtil().isContentDisplayed(CORPORATESITE_HOME.SECTION_HEADER)){
                    AllDataHolder.getUtil().clickElement(CORPORATESITE_HOME.LINK_TECHNOLOGIES);
                    AllDataHolder.getUtil().stringCompare(Technologies_Url, AllDataHolder.getDriver().getCurrentUrl());
                }else {
                    AllDataHolder.getUtil().report(4, "<mark>Unable to Find - Technologies Header Link. Please Check With Your Site Admin</mark>",
                            "<mark>News - Header Link Verification is Failed</mark>", "");
                }
            }
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e.getMessage());
        }
    }

    /**
     * @implNote Method to verify the Footer  Technologies - Transformative Technologies
     * @author Gangarapu.Ganesh
     */
    public void verifyTransformativeTechnologies() throws FlexFrameWorkRunTimeException{
        try {
            AllDataHolder.getUtil().contentDisplayed(TECHNOLOGIES.LABEL_TRANSFORMATIVE_TECH);
            AllDataHolder.getUtil().contentDisplayed(TECHNOLOGIES.PARAGRAPH_TRANSFORMATIVE_TECH);
            AllDataHolder.getUtil().contentDisplayed(TECHNOLOGIES.IMG_TRANSFORMATIVE_TECH);
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e.getMessage());
        }
    }

    /**
     * @implNote Method to verify the Footer  Technologies - How dFarm works
     * @author Gangarapu.Ganesh
     */
    public void verifyHowdFarmWorks() throws FlexFrameWorkRunTimeException{
        try {
            AllDataHolder.getUtil().scrollForSpecificElement(TECHNOLOGIES.LABEL_HOW_DFARM_WORKS);
            AllDataHolder.getUtil().contentDisplayed(TECHNOLOGIES.LABEL_HOW_DFARM_WORKS);
            AllDataHolder.getUtil().contentDisplayed(TECHNOLOGIES.CARD_WHOLE_CHAIN_VISIBILITY);
            AllDataHolder.getUtil().contentDisplayed(TECHNOLOGIES.CARD_REAL_TIME_AVAILABILITY);
            AllDataHolder.getUtil().contentDisplayed(TECHNOLOGIES.CARD_INSIGHTS);
            AllDataHolder.getUtil().contentDisplayed(TECHNOLOGIES.CARD_SOLUTIONS);
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e.getMessage());
        }
    }

    /**
     * @implNote Method to verify the Footer  Technologies -  Advanced Technologies Power Our Advanced Capabilities
     * @author Gangarapu.Ganesh
     */
    public void verifyAdvancedTechnologies() throws FlexFrameWorkRunTimeException{
        try {
            if(AllDataHolder.getUtil().isContentDisplayed(TECHNOLOGIES.IFRAME)){
                AllDataHolder.getUtil().switchToFrame(TECHNOLOGIES.IFRAME);
            }else if(AllDataHolder.getUtil().isContentDisplayed(TECHNOLOGIES.IFRAME2)){
                AllDataHolder.getUtil().switchToFrame(TECHNOLOGIES.IFRAME2);
            }
            AllDataHolder.getUtil().scrollForSpecificElement(TECHNOLOGIES.LABEL_ADVANCED_TECH);
            Thread.sleep(10000);
            AllDataHolder.getUtil().contentDisplayed(TECHNOLOGIES.LABEL_ADVANCED_TECH);
            AllDataHolder.getUtil().contentDisplayed(TECHNOLOGIES.DIAGRAM_AIMS);
            AllDataHolder.getUtil().switchToDefaultContent(TECHNOLOGIES.LABEL_A_FLEXIBLE);
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e.getMessage());
        }
    }

    /**
     * @implNote Method to verify the Footer  Technologies -  A Flexible, Intuitive User Experience
     * @author Gangarapu.Ganesh
     */
    public void verifyAFlexibleIntuitiveUserExperience() throws FlexFrameWorkRunTimeException{
        try {
            //Removing code since the image names are changing
//            AllDataHolder.getUtil().scrollForSpecificElement(TECHNOLOGIES.LABEL_A_FLEXIBLE);
//            AllDataHolder.getUtil().contentDisplayed(TECHNOLOGIES.LABEL_A_FLEXIBLE);
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e.getMessage());
        }
    }
}
