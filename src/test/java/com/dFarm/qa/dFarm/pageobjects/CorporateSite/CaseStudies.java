package com.dFarm.qa.dFarm.pageobjects.CorporateSite;

import com.dFarm.qa.dFarm.constants.CorporateSite;
import com.dFarm.qa.dFarm.constants.ReportingConstants;
import com.dFarm.qa.dFarm.objectrepository.CorporateSite.CASESTUDIES;
import com.dFarm.qa.dFarm.objectrepository.CorporateSite.CORPORATESITE_HOME;
import com.dFarm.qa.dFarm.util.execution.AllDataHolder;
import com.dFarm.qa.dFarm.util.execution.FlexFrameWorkRunTimeException;

/**
 * @author G Ganesh
 */
public class CaseStudies extends ReportingConstants implements CorporateSite {

    private static boolean isCaseStudyPresent = false;

    public void verifyCaseStudiesPage() throws FlexFrameWorkRunTimeException {
        try {
            verifyAndClickFooterCaseStudiesLink();
            verifyCaseStudiesPageLoad();

            if(!isCaseStudyPresent){
                AllDataHolder.getDriver().get(AllDataHolder.getDriver().getCurrentUrl());
                Thread.sleep(10000);
            }
            openMangoCaseStudy();
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e.getMessage());
        }
    }
    /**
     * @implNote Method to verify the Footer Link Case Studies
     * @author Gangarapu.Ganesh
     */
    public void verifyAndClickFooterCaseStudiesLink() throws FlexFrameWorkRunTimeException {
        try {
            AllDataHolder.getUtil().report(1, "<mark>Verifying dFarm Inc Corporate Website Footer Section Link Navigation - Case Studies </mark>",
                    "<mark>Verifying dFarm Inc Corporate Website Footer Section</mark>", "");
            AllDataHolder.getUtil().scrollForSpecificElement(CORPORATESITE_HOME.SECTION_FOOTER);
            if(AllDataHolder.getUtil().isContentDisplayed(CORPORATESITE_HOME.SECTION_FOOTER)){
                AllDataHolder.getUtil().clickElement(CORPORATESITE_HOME.LINK_CASE_STUDY);
                AllDataHolder.getUtil().stringCompare(Case_Studies_Url, AllDataHolder.getDriver().getCurrentUrl());
            }else {
                AllDataHolder.getUtil().report(1, "<mark>Unable to Find - Case Studies Footer Link. Please Refreshing the page & Checking again</mark>",
                        "<mark>Re-Attempting to verify the Careers footer link verification</mark>", "");
                String URL= AllDataHolder.getDriver().getCurrentUrl();
                AllDataHolder.getDriver().get(URL);
                AllDataHolder.getUtil().scrollForSpecificElement(CORPORATESITE_HOME.SECTION_FOOTER);
               if(AllDataHolder.getUtil().isContentDisplayed(CORPORATESITE_HOME.SECTION_FOOTER)){
                    AllDataHolder.getUtil().clickElement(CORPORATESITE_HOME.LINK_CASE_STUDY);
                    AllDataHolder.getUtil().stringCompare(Case_Studies_Url, AllDataHolder.getDriver().getCurrentUrl());
                }else {
                    AllDataHolder.getUtil().report(4, "<mark>Unable to Find - Case Studies Footer Link. Please Check With Your Site Admin</mark>",
                            "<mark>Case Studies - Footer Link Verification is Failed</mark>", "");
                }
            }
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e.getMessage());
        }
    }

    /**
     * @implNote Method to verify the Loading of Case Studies Page and checking components
     * @author Gangarapu.Ganesh
     */
    public boolean verifyCaseStudiesPageLoad() throws FlexFrameWorkRunTimeException {
        try {
            AllDataHolder.getUtil().contentDisplayed(CASESTUDIES.LABEL_CASE_STUDIES);
            AllDataHolder.getUtil().contentDisplayed(CASESTUDIES.ARTICLES_CASE_STUDIES);
            AllDataHolder.getUtil().contentDisplayed(CASESTUDIES.ARTICLE_FIRST);
            if (AllDataHolder.getUtil().isContentDisplayed(CASESTUDIES.ARTICLES_CASE_STUDIES)) {
                isCaseStudyPresent = true;
            }
            return isCaseStudyPresent;
        } catch (Exception e) {
            throw new FlexFrameWorkRunTimeException(e.getMessage());
        }
    }

    /**
     * @implNote Method to verify the Loading of Case Studies Details for Mango and verifying the different sections
     * @author Gangarapu.Ganesh
     */
    public void openMangoCaseStudy() throws FlexFrameWorkRunTimeException{
        try {
            AllDataHolder.getUtil().clickElementJS(CASESTUDIES.ARTICLE_FIRST);
            //Verifying the Mango Case Study
            AllDataHolder.getUtil().contentDisplayed(CASESTUDIES.ARTICLE_MANGO_TITLE);
            AllDataHolder.getUtil().report(2, "Mango Case Study Title - <mark>"+AllDataHolder.getUtil().getText(CASESTUDIES.ARTICLE_MANGO_TITLE)+" </mark>",
                    "", "");
            AllDataHolder.getUtil().contentDisplayed(CASESTUDIES.ARTICLE_MANGO_INTRO);
            AllDataHolder.getUtil().contentDisplayed(CASESTUDIES.ARTICLE_MANGO_INTRO_IMAGE);
            AllDataHolder.getUtil().report(2, "<mark>Mango Case Study Intro Section - "+AllDataHolder.getUtil().getText(CASESTUDIES.ARTICLE_MANGO_INTRO)+" </mark>",
                    "", "");
            //Background
            AllDataHolder.getUtil().contentDisplayed(CASESTUDIES.ARTICLE_MANGO_BACKGROUND);
            AllDataHolder.getUtil().report(2, "<mark>Mango Case Study 2nd Section Title - "+AllDataHolder.getUtil().getText(CASESTUDIES.ARTICLE_MANGO_BACKGROUND)+" </mark>",
                    "", "");
            AllDataHolder.getUtil().scrollForSpecificElement(CASESTUDIES.ARTICLE_MANGO_FARM_MANAGEMENT);
            AllDataHolder.getUtil().contentDisplayed(CASESTUDIES.ARTICLE_MANGO_FARM_MANAGEMENT);
            AllDataHolder.getUtil().report(2, "<mark>Case Study 3rd Section (Farm Management) Title And Content - "+AllDataHolder.getUtil().getText(CASESTUDIES.ARTICLE_MANGO_FARM_MANAGEMENT)+" </mark>",
                    "", "");
            AllDataHolder.getUtil().report(2, "<b>Mango Case Study 3rd Section (Farm Management) Title And Content - </b> <mark>"+AllDataHolder.getUtil().getText(CASESTUDIES.ARTICLE_MANGO_FARM_MANAGEMENT)+" </mark>",
                    "", "");
            AllDataHolder.getUtil().scrollForSpecificElement(CASESTUDIES.ARTICLE_MANGO_FARM_MANAGEMENT_CONTENT);
            AllDataHolder.getUtil().contentDisplayed(CASESTUDIES.ARTICLE_MANGO_FARM_MANAGEMENT_CONTENT);
            AllDataHolder.getUtil().report(2, "<b>Mango Case Study 4th Section (Farm Management) Title And Content - </b><mark>"+AllDataHolder.getUtil().getText(CASESTUDIES.ARTICLE_MANGO_FARM_MANAGEMENT_CONTENT)+" </mark>",
                    "", "");
            AllDataHolder.getUtil().contentDisplayed(CASESTUDIES.ARTICLE_MANGO_FARM_MANAGEMENT_IMAGE);

            //Harvesting Methodologies
            AllDataHolder.getUtil().scrollForSpecificElement(CASESTUDIES.ARTICLE_HARVESTING_METHODS);
            AllDataHolder.getUtil().contentDisplayed(CASESTUDIES.ARTICLE_HARVESTING_METHODS);
            AllDataHolder.getUtil().report(2, "<b>Mango Case Study 5th Section (Harvesting Methodologies) Title And Content - </b> <mark>"+AllDataHolder.getUtil().getText(CASESTUDIES.ARTICLE_HARVESTING_METHODS)+" </mark>",
                    "", "");
            AllDataHolder.getUtil().contentDisplayed(CASESTUDIES.ARTICLE_HARVESTING_METHODS_FIRST_PARAGRAPH);
            AllDataHolder.getUtil().report(2, "<b>Mango Case Study 6th Section (Harvesting Methodologies) 1st Paragraph - </b> <mark>"+AllDataHolder.getUtil().getText(CASESTUDIES.ARTICLE_HARVESTING_METHODS_FIRST_PARAGRAPH)+" </mark>",
                    "", "");
            AllDataHolder.getUtil().contentDisplayed(CASESTUDIES.ARTICLE_HARVESTING_METHODS_SECOND_PARAGRAPH);
            AllDataHolder.getUtil().report(2, "<b>Mango Case Study 7th Section (Harvesting Methodologies) 2nd Paragraph - </b> <mark>"+AllDataHolder.getUtil().getText(CASESTUDIES.ARTICLE_HARVESTING_METHODS_SECOND_PARAGRAPH)+" </mark>",
                    "", "");
            AllDataHolder.getUtil().contentDisplayed(CASESTUDIES.ARTICLE_HARVESTING_METHODS_FIRST_IMG);
            AllDataHolder.getUtil().contentDisplayed(CASESTUDIES.ARTICLE_POST_HARVESTING_IMAGES);

            //Post-Harvest Management
            AllDataHolder.getUtil().contentDisplayed(CASESTUDIES.ARTICLE_POST_HARVESTING_CONTENT);
            AllDataHolder.getUtil().report(2, "<b>Mango Case Study Post-Harvest Management Section - </b><mark>"+AllDataHolder.getUtil().getText(CASESTUDIES.ARTICLE_POST_HARVESTING_CONTENT)+" </mark>",
                    "", "");
            AllDataHolder.getUtil().contentDisplayed(CASESTUDIES.ARTICLE_POST_HARVESTING_IMAGES);
            //Packaging and Transportation
            AllDataHolder.getUtil().scrollForSpecificElement(CASESTUDIES.ARTICLE_PACKING_AND_TRANSPORTATION);
            AllDataHolder.getUtil().contentDisplayed(CASESTUDIES.ARTICLE_PACKING_AND_TRANSPORTATION);
            AllDataHolder.getUtil().report(2, "<b>Mango Case Study Packaging and Transportation Section - </b><mark>"+AllDataHolder.getUtil().getText(CASESTUDIES.ARTICLE_PACKING_AND_TRANSPORTATION)+" </mark>",
                    "", "");
            //Conclusion
            AllDataHolder.getUtil().contentDisplayed(CASESTUDIES.ARTICLE_CONCLUSION);
            AllDataHolder.getUtil().report(2, "<b>Mango Case Study Conclusion Section (Packaging and Transportation) - </b><mark>"+AllDataHolder.getUtil().getText(CASESTUDIES.ARTICLE_PACKING_AND_TRANSPORTATION)+" </mark>",
                    "", "");
            //About dFarm
            AllDataHolder.getUtil().contentDisplayed(CASESTUDIES.ARTICLE_ABOUT_DFARM);
            AllDataHolder.getUtil().report(2, "<b>Mango Case Study About dFarm Section - </b><mark>"+AllDataHolder.getUtil().getText(CASESTUDIES.ARTICLE_ABOUT_DFARM)+" </mark>",
                    "", "");


        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e.getMessage());
        }
    }
}
