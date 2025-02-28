package com.dFarm.qa.dFarm.pageobjects.CorporateSite;

import com.dFarm.qa.dFarm.constants.CorporateSite;
import com.dFarm.qa.dFarm.constants.ReportingConstants;
import com.dFarm.qa.dFarm.objectrepository.CorporateSite.CORPORATESITE_HOME;
import com.dFarm.qa.dFarm.objectrepository.CorporateSite.SCM_FINANCE;
import com.dFarm.qa.dFarm.util.execution.AllDataHolder;
import com.dFarm.qa.dFarm.util.execution.FlexFrameWorkRunTimeException;

/**
 * @author G Ganesh
 */
public class SCMFinance  extends ReportingConstants implements CorporateSite {

    private static String currentPageURL = "NA";


    public void verifySCMFinancePageFunctionality() throws FlexFrameWorkRunTimeException{
        try {
            verifyAndClickHeaderSCMFinanceLink();
            verifyLoadingSCMFinancePage();
            verifyLoadingSCMFinanceKeyComponentsSection();
            verifyLoadingSCMFinanceSCFWSection();
            verifyLoadingSCMFinanceKeyBenefitsSection();
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e.getMessage());
        }
    }

    /**
     * @implNote Method to verify the Footer Link SCM Finance
     * @author Gangarapu.Ganesh
     */
    public void verifyAndClickFooterSCMFinanceLink() throws FlexFrameWorkRunTimeException {
        try {
            AllDataHolder.getUtil().report(1, "<mark>Verifying dFarm Inc Corporate Website Footer Section Link Navigation - SCM Finance</mark>",
                    "<mark>Verifying dFarm Inc Corporate Website Footer Section</mark>", "");
            AllDataHolder.getUtil().scrollForSpecificElement(CORPORATESITE_HOME.SECTION_FOOTER);
            if(AllDataHolder.getUtil().isContentDisplayed(CORPORATESITE_HOME.SECTION_FOOTER)){
                AllDataHolder.getUtil().clickElement(CORPORATESITE_HOME.LINK_FOOTER_SCM_FINANCE);
                AllDataHolder.getUtil().stringCompare(Scm_Finance, AllDataHolder.getDriver().getCurrentUrl());
                currentPageURL = AllDataHolder.getDriver().getCurrentUrl();
            }else {
                AllDataHolder.getUtil().report(1, "<mark>Unable to Find - SCM Finance Footer Link. Please Refreshing the page & Checking again</mark>",
                        "<mark>Re-Attempting to verify the News footer link verification</mark>", "");
                currentPageURL= AllDataHolder.getDriver().getCurrentUrl();
                AllDataHolder.getDriver().get(currentPageURL);
                AllDataHolder.getUtil().scrollForSpecificElement(CORPORATESITE_HOME.SECTION_FOOTER);
                if(AllDataHolder.getUtil().isContentDisplayed(CORPORATESITE_HOME.SECTION_FOOTER)){
                    AllDataHolder.getUtil().clickElement(CORPORATESITE_HOME.LINK_FOOTER_SCM_FINANCE);
                    AllDataHolder.getUtil().stringCompare(Scm_Finance, AllDataHolder.getDriver().getCurrentUrl());
                }else {
                    AllDataHolder.getUtil().report(4, "<mark>Unable to Find - SCM Finance Footer Link. Please Check With Your Site Admin</mark>",
                            "<mark>SCM Finance - Footer Link Verification is Failed</mark>", "");
                }
            }
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e.getMessage());
        }
    }

    /**
     * @implNote Method to verify the Header Link SCM Finance
     * @author Gangarapu.Ganesh
     */
    public void verifyAndClickHeaderSCMFinanceLink() throws FlexFrameWorkRunTimeException {
        try {
            AllDataHolder.getUtil().report(1, "<mark>Verifying dFarm Inc Corporate Website Header Section Link Navigation - SCM Finance</mark>",
                    "<mark>Verifying dFarm Inc Corporate Website Footer Section</mark>", "");
            AllDataHolder.getUtil().scrollForSpecificElement(CORPORATESITE_HOME.SECTION_HEADER);
            if(AllDataHolder.getUtil().isContentDisplayed(CORPORATESITE_HOME.SECTION_HEADER)){
                AllDataHolder.getUtil().clickElement(CORPORATESITE_HOME.LINK_SCM_FINANCE);
                AllDataHolder.getUtil().stringCompare(Scm_Finance, AllDataHolder.getDriver().getCurrentUrl());
                currentPageURL = AllDataHolder.getDriver().getCurrentUrl();
            }else {
                AllDataHolder.getUtil().report(1, "<mark>Unable to Find - SCM Finance Header Link. Please Refreshing the page & Checking again</mark>",
                        "<mark>Re-Attempting to verify the News footer link verification</mark>", "");
                currentPageURL= AllDataHolder.getDriver().getCurrentUrl();
                AllDataHolder.getDriver().get(currentPageURL);
                AllDataHolder.getUtil().scrollForSpecificElement(CORPORATESITE_HOME.SECTION_HEADER);
                if(AllDataHolder.getUtil().isContentDisplayed(CORPORATESITE_HOME.SECTION_HEADER)){
                    AllDataHolder.getUtil().clickElement(CORPORATESITE_HOME.LINK_SCM_FINANCE);
                    AllDataHolder.getUtil().stringCompare(Scm_Finance, AllDataHolder.getDriver().getCurrentUrl());
                }else {
                    AllDataHolder.getUtil().report(4, "<mark>Unable to Find - SCM Header Footer Link. Please Check With Your Site Admin</mark>",
                            "<mark>SCM Finance - Header Link Verification is Failed</mark>", "");
                }
            }
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e.getMessage());
        }
    }

    /**
     * @implNote Method to verify the SCM Finance Page Load
     * @author Gangarapu.Ganesh
     */
    public void verifyLoadingSCMFinancePage() throws FlexFrameWorkRunTimeException {
        try {
        AllDataHolder.getUtil().contentDisplayed(SCM_FINANCE.LABEL_SCM_FINANCE);
            AllDataHolder.getUtil().contentDisplayed(SCM_FINANCE.TEXT_SCM_FINANCE_PARAGRAPH);
            AllDataHolder.getUtil().contentDisplayed(SCM_FINANCE.IMG_SCM_FINANCE);
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e.getMessage());
        }
    }

    /**
     * @implNote Method to verify the SCM Finance - Key Components of Supply Chain Finance
     * @author Gangarapu.Ganesh
     */
    public void verifyLoadingSCMFinanceKeyComponentsSection() throws FlexFrameWorkRunTimeException {
        try {
            AllDataHolder.getUtil().contentDisplayed(SCM_FINANCE.LABEL_KEY_COMPONENTS);
            AllDataHolder.getUtil().contentDisplayed(SCM_FINANCE.CARDS_KEY_COMPONENTS);
            AllDataHolder.getUtil().contentDisplayed(SCM_FINANCE.IMG_SCM_FINANCE);
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e.getMessage());
        }
    }

    /**
     * @implNote Method to verify the SCM Finance - How Supply Chain Finance Works
     * @author Gangarapu.Ganesh
     */
    public void verifyLoadingSCMFinanceSCFWSection() throws FlexFrameWorkRunTimeException {
        try {
            AllDataHolder.getUtil().scrollForSpecificElement(SCM_FINANCE.LABEL_HOW_SCFW);
            AllDataHolder.getUtil().contentDisplayed(SCM_FINANCE.LABEL_HOW_SCFW);
            for (int i = 1; i< AllDataHolder.getUtil().sizeOfWebElement(SCM_FINANCE.LIST_HOW_SCFW); i++){
                Thread.sleep(10000);

                if(AllDataHolder.getUtil().isContentDisplayed(SCM_FINANCE.selectArticleTopic(i))){
                    AllDataHolder.getUtil().report(1, "<mark>How Supply Chain Finance Works : <mark>"+AllDataHolder.getUtil().getText(SCM_FINANCE.selectArticleTopic(i))+"</mark></mark>",
                            "<mark>How Supply Chain Finance Works :/mark>", "");
                }else {
                    AllDataHolder.getUtil().report(4, "<mark>Unable to Find - How Supply Chain Finance Works  : <mark>"+AllDataHolder.getUtil().getText(SCM_FINANCE.selectArticleTopic(i))+"</mark></mark>",
                            "<mark>Unable to Find - How Supply Chain Finance Works </mark>", "");
                }

            }


        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e.getMessage());
        }
    }

    /**
     * @implNote Method to verify the SCM Finance - Key benefits for buyers and suppliers in supply chain financing
     * @author Gangarapu.Ganesh
     */
    public void verifyLoadingSCMFinanceKeyBenefitsSection() throws FlexFrameWorkRunTimeException {
        try {
            AllDataHolder.getUtil().scrollForSpecificElement(SCM_FINANCE.LABEL_KEY_BENEFITS);
            AllDataHolder.getUtil().contentDisplayed(SCM_FINANCE.LABEL_KEY_BENEFITS);
            AllDataHolder.getUtil().contentDisplayed(SCM_FINANCE.LABEL_FOR_BUYERS);
            AllDataHolder.getUtil().report(1, "<mark>Benefits For Buyers  : <mark>"+AllDataHolder.getUtil().getText(SCM_FINANCE.LIST_BENEFITS_FOR_BUYERS_POINTS)+"</mark></mark>",
                    "<mark>Benefits For Buyers :/mark>", "");

            AllDataHolder.getUtil().contentDisplayed(SCM_FINANCE.LABEL_FOR_SUPPLIERS);
            AllDataHolder.getUtil().report(1, "<mark>Benefits  For Suppliers   : <mark>"+AllDataHolder.getUtil().getText(SCM_FINANCE.LIST_BENEFITS_FOR_SUPPLIERS_POINTS)+"</mark></mark>",
                    "<mark>Benefits  For Suppliers :/mark>", "");

            AllDataHolder.getUtil().scrollForSpecificElement(SCM_FINANCE.NOTE_BENEFITS);
            AllDataHolder.getUtil().contentDisplayed(SCM_FINANCE.NOTE_BENEFITS);
            AllDataHolder.getUtil().stringCompare(SCM_BENEFITS_NOTE.trim(), AllDataHolder.getUtil().getText(SCM_FINANCE.NOTE_BENEFITS).trim());

        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e.getMessage());
        }
    }

}
