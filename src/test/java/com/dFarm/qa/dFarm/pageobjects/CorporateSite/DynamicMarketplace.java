package com.dFarm.qa.dFarm.pageobjects.CorporateSite;

import com.dFarm.qa.dFarm.constants.CorporateSite;
import com.dFarm.qa.dFarm.constants.ReportingConstants;
import com.dFarm.qa.dFarm.objectrepository.CorporateSite.CORPORATESITE_HOME;
import com.dFarm.qa.dFarm.util.execution.AllDataHolder;
import com.dFarm.qa.dFarm.util.execution.FlexFrameWorkRunTimeException;
import org.apache.xmlbeans.impl.xb.xsdschema.All;

/**
 * @author G Ganesh
 */
public class DynamicMarketplace  extends ReportingConstants implements CorporateSite {

    private static String currentPageURL = "NA";


    public void verifyLoadAndUIGDM() throws FlexFrameWorkRunTimeException{
        try {
            verifyAndClickHeaderDynamicMarketplaceLink();
            AllDataHolder.getMarkeplaceHomepage().validateMPHeaderUI();
            AllDataHolder.getMarkeplaceHomepage().verifyCategoriesList();
            AllDataHolder.getUtil().closeAllChildWindowsOrTabs();
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e.getMessage());
        }
    }


    /**
     * @implNote Method to verify the Footer Link Dynamic Marketplace
     * @author Gangarapu.Ganesh
     */
    public void verifyAndClickFooterDynamicMarketplaceLink() throws FlexFrameWorkRunTimeException {
        try {
            AllDataHolder.getUtil().report(1, "<mark>Verifying dFarm Inc Corporate Website Footer Section Link Navigation - Dynamic Marketplace</mark>",
                    "<mark>Verifying dFarm Inc Corporate Website Footer Section</mark>", "");
            AllDataHolder.getUtil().scrollForSpecificElement(CORPORATESITE_HOME.SECTION_FOOTER);
            if(AllDataHolder.getUtil().isContentDisplayed(CORPORATESITE_HOME.SECTION_FOOTER)){
                AllDataHolder.getUtil().clickElement(CORPORATESITE_HOME.LINK_FOOTER_DYNAMIC_MARKETPLACE);
                AllDataHolder.getUtil().switchToNewWindow();
                AllDataHolder.getUtil().stringCompare(GDM_URL, AllDataHolder.getDriver().getCurrentUrl());
                currentPageURL = AllDataHolder.getDriver().getCurrentUrl();
            }else {
                AllDataHolder.getUtil().report(1, "<mark>Unable to Find - Dynamic Marketplace Footer Link. Please Refreshing the page & Checking again</mark>",
                        "<mark>Re-Attempting to verify the News footer link verification</mark>", "");
                currentPageURL= AllDataHolder.getDriver().getCurrentUrl();
                AllDataHolder.getDriver().get(currentPageURL);
                AllDataHolder.getUtil().scrollForSpecificElement(CORPORATESITE_HOME.SECTION_FOOTER);
                if(AllDataHolder.getUtil().isContentDisplayed(CORPORATESITE_HOME.SECTION_FOOTER)){
                    AllDataHolder.getUtil().clickElement(CORPORATESITE_HOME.LINK_FOOTER_DYNAMIC_MARKETPLACE);
                    AllDataHolder.getUtil().switchToNewWindow();
                    AllDataHolder.getUtil().stringCompare(GDM_URL, AllDataHolder.getDriver().getCurrentUrl());
                }else {
                    AllDataHolder.getUtil().report(4, "<mark>Unable to Find - Dynamic Marketplace Footer Link. Please Check With Your Site Admin</mark>",
                            "<mark>Dynamic Marketplace - Footer Link Verification is Failed</mark>", "");
                }
            }
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e.getMessage());
        }
    }

    /**
     * @implNote Method to verify the Header Link Dynamic Marketplace
     * @author Gangarapu.Ganesh
     */
    public void verifyAndClickHeaderDynamicMarketplaceLink() throws FlexFrameWorkRunTimeException {
        try {
            AllDataHolder.getUtil().report(1, "<mark>Verifying dFarm Inc Corporate Website Header Section Link Navigation - Dynamic Marketplace</mark>",
                    "<mark>Verifying dFarm Inc Corporate Website Header Section</mark>", "");
            AllDataHolder.getUtil().scrollForSpecificElement(CORPORATESITE_HOME.SECTION_HEADER);
            if(AllDataHolder.getUtil().isContentDisplayed(CORPORATESITE_HOME.SECTION_HEADER)){
                AllDataHolder.getUtil().clickElement(CORPORATESITE_HOME.LINK_DYNAMIC_MARKETPLACE);
                AllDataHolder.getUtil().switchToNewWindow();
                AllDataHolder.getUtil().stringCompare(GDM_URL, AllDataHolder.getDriver().getCurrentUrl());
                currentPageURL = AllDataHolder.getDriver().getCurrentUrl();
            }else {
                AllDataHolder.getUtil().report(1, "<mark>Unable to Find - Dynamic Marketplace Header Link. Please Refreshing the page & Checking again</mark>",
                        "<mark>Re-Attempting to verify the News Header link verification</mark>", "");
                currentPageURL= AllDataHolder.getDriver().getCurrentUrl();
                AllDataHolder.getDriver().get(currentPageURL);
                AllDataHolder.getUtil().scrollForSpecificElement(CORPORATESITE_HOME.SECTION_HEADER);
                if(AllDataHolder.getUtil().isContentDisplayed(CORPORATESITE_HOME.SECTION_HEADER)){
                    AllDataHolder.getUtil().clickElement(CORPORATESITE_HOME.LINK_DYNAMIC_MARKETPLACE);
                    AllDataHolder.getUtil().switchToNewWindow();
                    AllDataHolder.getUtil().stringCompare(GDM_URL, AllDataHolder.getDriver().getCurrentUrl());
                }else {
                    AllDataHolder.getUtil().report(4, "<mark>Unable to Find - Dynamic Marketplace Header Link. Please Check With Your Site Admin</mark>",
                            "<mark>Dynamic Marketplace - Header Link Verification is Failed</mark>", "");
                }
            }
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e.getMessage());
        }
    }


}
