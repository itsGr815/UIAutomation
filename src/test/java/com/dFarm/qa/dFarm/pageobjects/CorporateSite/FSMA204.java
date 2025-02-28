package com.dFarm.qa.dFarm.pageobjects.CorporateSite;

import com.dFarm.qa.dFarm.constants.CorporateSite;
import com.dFarm.qa.dFarm.constants.ReportingConstants;
import com.dFarm.qa.dFarm.objectrepository.CorporateSite.CORPORATESITE_HOME;
import com.dFarm.qa.dFarm.objectrepository.CorporateSite.FSMA_204;
import com.dFarm.qa.dFarm.util.execution.AllDataHolder;
import com.dFarm.qa.dFarm.util.execution.FlexFrameWorkRunTimeException;
import org.apache.xmlbeans.impl.xb.xsdschema.All;

/**
 * @author G Ganesh
 */
public class FSMA204  extends ReportingConstants implements CorporateSite {

    private static String currentPageURL = "NA";


    /**
     * @implNote Method to verify the Footer Link FSMA 204
     * @author Gangarapu.Ganesh
     */
    public void verifySFSMA204Page() throws FlexFrameWorkRunTimeException {
        try {
            verifyAndClickHeaderFSMA204Link();
            navigateAndVerifyWhatIsFSMA204();
            verifySixFSMA204FactsToKnowSection();
            verifyWhatFSMA204DoesNotDoSection();
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e.getMessage());
        }
    }

    /**
     * @implNote Method to verify the Footer Link FSMA 204
     * @author Gangarapu.Ganesh
     */
    public void verifyAndClickFooterFSMA204Link() throws FlexFrameWorkRunTimeException {
        try {
            AllDataHolder.getUtil().report(1, "<mark>Verifying dFarm Inc Corporate Website Footer Section Link Navigation - FSMA 204</mark>",
                    "<mark>Verifying dFarm Inc Corporate Website Footer Section</mark>", "");
            AllDataHolder.getUtil().scrollForSpecificElement(CORPORATESITE_HOME.SECTION_FOOTER);
            if(AllDataHolder.getUtil().isContentDisplayed(CORPORATESITE_HOME.SECTION_FOOTER)){
                AllDataHolder.getUtil().clickElement(CORPORATESITE_HOME.LINK_FOOTER_FSMA_204);
                AllDataHolder.getUtil().stringCompare(FSMA_204_Url, AllDataHolder.getDriver().getCurrentUrl());
                currentPageURL = AllDataHolder.getDriver().getCurrentUrl();
            }else {
                AllDataHolder.getUtil().report(1, "<mark>Unable to Find - FSMA 204 Footer Link. Please Refreshing the page & Checking again</mark>",
                        "<mark>Re-Attempting to verify the FSMA 204 footer link verification</mark>", "");
                currentPageURL= AllDataHolder.getDriver().getCurrentUrl();
                AllDataHolder.getDriver().get(currentPageURL);
                AllDataHolder.getUtil().scrollForSpecificElement(CORPORATESITE_HOME.SECTION_FOOTER);
                if(AllDataHolder.getUtil().isContentDisplayed(CORPORATESITE_HOME.SECTION_FOOTER)){
                    AllDataHolder.getUtil().clickElement(CORPORATESITE_HOME.LINK_FOOTER_FSMA_204);
                    AllDataHolder.getUtil().stringCompare(FSMA_204_Url, AllDataHolder.getDriver().getCurrentUrl());
                }else {
                    AllDataHolder.getUtil().report(4, "<mark>Unable to Find - FSMA 204 Footer Link. Please Check With Your Site Admin</mark>",
                            "<mark>FSMA 204 - Footer Link Verification is Failed</mark>", "");
                }
            }
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e.getMessage());
        }
    }

    /**
     * @implNote Method to verify the Header Link FSMA 204
     * @author Gangarapu.Ganesh
     */
    public void verifyAndClickHeaderFSMA204Link() throws FlexFrameWorkRunTimeException {
        try {
            AllDataHolder.getUtil().report(1, "<mark>Verifying dFarm Inc Corporate Website Header Section Link Navigation - FSMA 204</mark>",
                    "<mark>Verifying dFarm Inc Corporate Website Header Section</mark>", "");
            AllDataHolder.getUtil().scrollForSpecificElement(CORPORATESITE_HOME.SECTION_HEADER);
            if(AllDataHolder.getUtil().isContentDisplayed(CORPORATESITE_HOME.SECTION_HEADER)){
                AllDataHolder.getUtil().clickElement(CORPORATESITE_HOME.LINK_FSMA_204);
                AllDataHolder.getUtil().stringCompare(FSMA_204_Url, AllDataHolder.getDriver().getCurrentUrl());
                currentPageURL = AllDataHolder.getDriver().getCurrentUrl();
            }else {
                AllDataHolder.getUtil().report(1, "<mark>Unable to Find - FSMA 204 Header Link. Please Refreshing the page & Checking again</mark>",
                        "<mark>Re-Attempting to verify the FSMA 204 Header link verification</mark>", "");
                currentPageURL= AllDataHolder.getDriver().getCurrentUrl();
                AllDataHolder.getDriver().get(currentPageURL);
                AllDataHolder.getUtil().scrollForSpecificElement(CORPORATESITE_HOME.SECTION_HEADER);
                if(AllDataHolder.getUtil().isContentDisplayed(CORPORATESITE_HOME.SECTION_HEADER)){
                    AllDataHolder.getUtil().clickElement(CORPORATESITE_HOME.LINK_FSMA_204);
                    AllDataHolder.getUtil().stringCompare(FSMA_204_Url, AllDataHolder.getDriver().getCurrentUrl());
                }else {
                    AllDataHolder.getUtil().report(4, "<mark>Unable to Find - FSMA 204 Header Link. Please Check With Your Site Admin</mark>",
                            "<mark>FSMA 204 - Header Link Verification is Failed</mark>", "");
                }
            }
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e.getMessage());
        }
    }

    /**
     * @implNote Method to verify the Footer Link FSMA 204 - What is FSMA 204?
     * @author Gangarapu.Ganesh
     */
    public void navigateAndVerifyWhatIsFSMA204() throws FlexFrameWorkRunTimeException {
        try {
            AllDataHolder.getUtil().contentDisplayed(FSMA_204.LABEL_WHAT_IS_FSMA_204);
            AllDataHolder.getUtil().contentDisplayed(FSMA_204.FSMA204_PARAGRAPH);
            AllDataHolder.getUtil().contentDisplayed(FSMA_204.VIDEO_FSMA_204);
            AllDataHolder.getUtil().contentDisplayed(FSMA_204.LINK_FDA);
            AllDataHolder.getUtil().contentDisplayed(FSMA_204.BUTTON_GET_CONSULTATION);


            //Click on FDA Article Link & verify the navigation
            AllDataHolder.getUtil().clickElementJS(FSMA_204.LINK_FDA);
            AllDataHolder.getUtil().switchToNewWindow();
            AllDataHolder.getUtil().stringCompare(FSMA_FDA_URL, AllDataHolder.getDriver().getCurrentUrl());
            AllDataHolder.getUtil().closeAllChildWindowsOrTabs();
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e.getMessage());
        }
    }


    /**
     * @implNote Method to verify the Footer Link FSMA 204 - Six FSMA 204 Facts to Know
     * @author Gangarapu.Ganesh
     */
    public void verifySixFSMA204FactsToKnowSection() throws FlexFrameWorkRunTimeException {
        try {
            AllDataHolder.getUtil().contentDisplayed(FSMA_204.LABEL_SIX_FSMA);
            AllDataHolder.getUtil().contentDisplayed(FSMA_204.PARAGRAPH_SIZ_FSMA);
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e.getMessage());
        }
    }

    /**
     * @implNote Method to verify the Footer Link FSMA 204 - What FSMA 204 Does Not Do
     * @author Gangarapu.Ganesh
     */
    public void verifyWhatFSMA204DoesNotDoSection() throws FlexFrameWorkRunTimeException {
        try {
            AllDataHolder.getUtil().scrollForSpecificElement(FSMA_204.LABEL_WHAT_FSMA);
            AllDataHolder.getUtil().contentDisplayed(FSMA_204.LABEL_WHAT_FSMA);

            AllDataHolder.getUtil().contentDisplayed(FSMA_204.SECTION_FDA_COMMISSIONER);
            AllDataHolder.getUtil().contentDisplayed(FSMA_204.SECTION_WHAT_FSMA);
            AllDataHolder.getUtil().contentDisplayed(FSMA_204.BUTTON_GET_CONSULTATION_BOTTOM);
            AllDataHolder.getUtil().contentDisplayed(FSMA_204.LABEL_THE_BUSINESS_RICK);
            AllDataHolder.getUtil().contentDisplayed(FSMA_204.LABEL_YOUR_BUSINESS_PRIORITIES);
            AllDataHolder.getUtil().contentDisplayed(FSMA_204.LABEL_AS_A_FOOD_PRODUCER);

            AllDataHolder.getUtil().contentDisplayed(FSMA_204.LABEL_AS_A_FOOD_PRODUCER);
            AllDataHolder.getUtil().contentDisplayed(FSMA_204.BUTTON_GET_CONSULTATION_BOTTOM);
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e.getMessage());
        }
    }
}
