package com.dFarm.qa.dFarm.pageobjects.CorporateSite;

import com.dFarm.qa.dFarm.constants.CorporateSite;
import com.dFarm.qa.dFarm.constants.ReportingConstants;
import com.dFarm.qa.dFarm.objectrepository.CorporateSite.CORPORATESITE_HOME;
import com.dFarm.qa.dFarm.objectrepository.CorporateSite.SUPPLY_CHAIN_OPTIMIZATION;
import com.dFarm.qa.dFarm.util.execution.AllDataHolder;
import com.dFarm.qa.dFarm.util.execution.FlexFrameWorkRunTimeException;
import org.apache.xmlbeans.impl.xb.xsdschema.All;

/**
 * @author G Ganesh
 */
public class SupplyChainOptimization extends ReportingConstants implements CorporateSite {

    private static String currentPageURL = "NA";

    public void verifySupplyChainOptimizationPageFunctionality() throws FlexFrameWorkRunTimeException{
        try {
            verifyAndClickHeaderSCOLink();
            verifyLoadingAndWholeChainVisualization();
            verifyLoadingAndTotalSupplyChainVisibility();
            verifyLoadingAndOptimizeYourSupplyChain();
            verifyLoadingAndDeliveringASustainableFuture();
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e.getMessage());
        }
    }

    /**
     * @implNote Method to verify the Footer Link Supply Chain Optimization
     * @author Gangarapu.Ganesh
     */
    public void verifyAndClickFooterSCOLink() throws FlexFrameWorkRunTimeException {
        try {
            AllDataHolder.getUtil().report(1, "<mark>Verifying dFarm Inc Corporate Website Footer Section Link Navigation - Supply Chain Optimization</mark>",
                    "<mark>Verifying dFarm Inc Corporate Website Footer Section</mark>", "");
            AllDataHolder.getUtil().scrollForSpecificElement(CORPORATESITE_HOME.SECTION_FOOTER);
            if(AllDataHolder.getUtil().isContentDisplayed(CORPORATESITE_HOME.SECTION_FOOTER)){
                AllDataHolder.getUtil().clickElement(CORPORATESITE_HOME.LINK_FOOTER_SUPPLY_CHAIN_OPTIMIZATION);
                AllDataHolder.getUtil().stringCompare(dFarm_supply_chain_optimization_url, AllDataHolder.getDriver().getCurrentUrl());
                currentPageURL = AllDataHolder.getDriver().getCurrentUrl();
            }else {
                AllDataHolder.getUtil().report(1, "<mark>Unable to Find - Supply Chain Optimization Footer Link. Please Refreshing the page & Checking again</mark>",
                        "<mark>Re-Attempting to verify the Supply Chain Optimization footer link verification</mark>", "");
                currentPageURL= AllDataHolder.getDriver().getCurrentUrl();
                AllDataHolder.getDriver().get(currentPageURL);
                AllDataHolder.getUtil().scrollForSpecificElement(CORPORATESITE_HOME.SECTION_FOOTER);
                if(AllDataHolder.getUtil().isContentDisplayed(CORPORATESITE_HOME.SECTION_FOOTER)){
                    AllDataHolder.getUtil().clickElement(CORPORATESITE_HOME.LINK_FOOTER_SUPPLY_CHAIN_OPTIMIZATION);
                    AllDataHolder.getUtil().stringCompare(dFarm_supply_chain_optimization_url, AllDataHolder.getDriver().getCurrentUrl());
                }else {
                    AllDataHolder.getUtil().report(4, "<mark>Unable to Find - Supply Chain Optimization Footer Link. Please Check With Your Site Admin</mark>",
                            "<mark>Supply Chain Optimization - Footer Link Verification is Failed</mark>", "");
                }
            }
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e.getMessage());
        }
    }

    /**
     * @implNote Method to verify the Header Link Supply Chain Optimization
     * @author Gangarapu.Ganesh
     */
    public void verifyAndClickHeaderSCOLink() throws FlexFrameWorkRunTimeException {
        try {
            AllDataHolder.getUtil().report(1, "<mark>Verifying dFarm Inc Corporate Website Header Section Link Navigation - Supply Chain Optimization</mark>",
                    "<mark>Verifying dFarm Inc Corporate Website Header Section</mark>", "");
            AllDataHolder.getUtil().scrollForSpecificElement(CORPORATESITE_HOME.SECTION_HEADER);
            if(AllDataHolder.getUtil().isContentDisplayed(CORPORATESITE_HOME.SECTION_HEADER)){
                AllDataHolder.getUtil().clickElement(CORPORATESITE_HOME.LINK_SUPPLY_CHAIN_OPTIMIZATION);
                AllDataHolder.getUtil().stringCompare(dFarm_supply_chain_optimization_url, AllDataHolder.getDriver().getCurrentUrl());
                currentPageURL = AllDataHolder.getDriver().getCurrentUrl();
            }else {
                AllDataHolder.getUtil().report(1, "<mark>Unable to Find - Supply Chain Optimization Header Link. Please Refreshing the page & Checking again</mark>",
                        "<mark>Re-Attempting to verify the Supply Chain Optimization Header link verification</mark>", "");
                currentPageURL= AllDataHolder.getDriver().getCurrentUrl();
                AllDataHolder.getDriver().get(currentPageURL);
                AllDataHolder.getUtil().scrollForSpecificElement(CORPORATESITE_HOME.SECTION_HEADER);
                if(AllDataHolder.getUtil().isContentDisplayed(CORPORATESITE_HOME.SECTION_HEADER)){
                    AllDataHolder.getUtil().clickElement(CORPORATESITE_HOME.LINK_SUPPLY_CHAIN_OPTIMIZATION);
                    AllDataHolder.getUtil().stringCompare(dFarm_supply_chain_optimization_url, AllDataHolder.getDriver().getCurrentUrl());
                }else {
                    AllDataHolder.getUtil().report(4, "<mark>Unable to Find - Supply Chain Optimization Header Link. Please Check With Your Site Admin</mark>",
                            "<mark>Supply Chain Optimization - Header Link Verification is Failed</mark>", "");
                }
            }
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e.getMessage());
        }
    }

    /**
     * @implNote Method to verify the Whole Chain Visualization Section
     * @author Gangarapu.Ganesh
     */
    public void verifyLoadingAndWholeChainVisualization() throws FlexFrameWorkRunTimeException {
        try {
            AllDataHolder.getUtil().contentDisplayed(SUPPLY_CHAIN_OPTIMIZATION.LABEL_WHOLE_CHAIN);
            AllDataHolder.getUtil().contentDisplayed(SUPPLY_CHAIN_OPTIMIZATION.TEXT_WHOLE_CHAIN_PARAGRAPH);
            AllDataHolder.getUtil().contentDisplayed(SUPPLY_CHAIN_OPTIMIZATION.SVG_ICON_WHOLE_CHAIN_IMG);
            AllDataHolder.getUtil().clickElementJS(SUPPLY_CHAIN_OPTIMIZATION.LABEL_WHOLE_CHAIN);
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e.getMessage());
        }
    }

    /**
     * @implNote Method to verify the Total Supply Chain Visibility Section
     * @author Gangarapu.Ganesh
     */
    public void verifyLoadingAndTotalSupplyChainVisibility() throws FlexFrameWorkRunTimeException {
        try {
//            AllDataHolder.getUtil().scrollForSpecificElement(SUPPLY_CHAIN_OPTIMIZATION.SECTION_TOTAL_SUPPLY_CHAIN_VISIBILITY);
            if(AllDataHolder.getUtil().isContentDisplayed(SUPPLY_CHAIN_OPTIMIZATION.IFRAME)){
                AllDataHolder.getUtil().switchToFrame(SUPPLY_CHAIN_OPTIMIZATION.IFRAME);
            }else if(AllDataHolder.getUtil().isContentDisplayed(SUPPLY_CHAIN_OPTIMIZATION.IFRAME2)){
                AllDataHolder.getUtil().switchToFrame(SUPPLY_CHAIN_OPTIMIZATION.IFRAME2);
            }
            AllDataHolder.getUtil().contentDisplayed(SUPPLY_CHAIN_OPTIMIZATION.SECTION_TOTAL_SUPPLY_CHAIN_VISIBILITY);
            AllDataHolder.getUtil().contentDisplayed(SUPPLY_CHAIN_OPTIMIZATION.SECTION_TOTAL_SUPPLY_CHAIN_VISIBILITY_PARAGRAPH2);
            AllDataHolder.getUtil().contentDisplayed(SUPPLY_CHAIN_OPTIMIZATION.SECTION_TOTAL_SUPPLY_CHAIN_VISIBILITY_PARAGRAPH3);
            AllDataHolder.getUtil().contentDisplayed(SUPPLY_CHAIN_OPTIMIZATION.SECTION_TOTAL_SUPPLY_CHAIN_VISIBILITY_CIRCLE_BOX);
            AllDataHolder.getUtil().contentDisplayed(SUPPLY_CHAIN_OPTIMIZATION.IMG_TOTAL_SUPPLY_CHAIN_VISIBILITY_CIRCLE_BOX_IMAGE);
            AllDataHolder.getUtil().switchToDefaultContent(SUPPLY_CHAIN_OPTIMIZATION.LABEL_WHOLE_CHAIN);
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e.getMessage());
        }
    }

    /**
     * @implNote Method to verify the Optimize Your Supply Chain Section
     * @author Gangarapu.Ganesh
     */
    public void verifyLoadingAndOptimizeYourSupplyChain() throws FlexFrameWorkRunTimeException {
        try {
            AllDataHolder.getUtil().scrollForSpecificElement(SUPPLY_CHAIN_OPTIMIZATION.SECTION_REVOLUTIONIZE_YOUR_SUPPLY_CHAIN_WITH_IT);
            AllDataHolder.getUtil().contentDisplayed(SUPPLY_CHAIN_OPTIMIZATION.SECTION_REVOLUTIONIZE_YOUR_SUPPLY_CHAIN_WITH_IT);
            AllDataHolder.getUtil().contentDisplayed(SUPPLY_CHAIN_OPTIMIZATION.SECTION_OPTIMIZE_YSC_FIRST);
            AllDataHolder.getUtil().contentDisplayed(SUPPLY_CHAIN_OPTIMIZATION.SECTION_OPTIMIZE_YSC_SECOND);
            AllDataHolder.getUtil().contentDisplayed(SUPPLY_CHAIN_OPTIMIZATION.SECTION_OPTIMIZE_YSC_THIRD);
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e.getMessage());
        }
    }

    /**
     * @implNote Method to verify the Delivering a Sustainable Future Section
     * @author Gangarapu.Ganesh
     */
    public void verifyLoadingAndDeliveringASustainableFuture() throws FlexFrameWorkRunTimeException {
        try {
            AllDataHolder.getUtil().scrollForSpecificElement(SUPPLY_CHAIN_OPTIMIZATION.SECTION_REVOLUTIONIZE_YOUR_SUPPLY_CHAIN_WITH_IT);
            AllDataHolder.getUtil().contentDisplayed(SUPPLY_CHAIN_OPTIMIZATION.SECTION_REVOLUTIONIZE_YOUR_SUPPLY_CHAIN_WITH_IT);
//            AllDataHolder.getUtil().contentDisplayed(SUPPLY_CHAIN_OPTIMIZATION.TEXT_dFARM_SUPPORTS);
//            AllDataHolder.getUtil().contentDisplayed(SUPPLY_CHAIN_OPTIMIZATION.SECTION_DELIVERING_ICONS);
//            AllDataHolder.getUtil().contentDisplayed(SUPPLY_CHAIN_OPTIMIZATION.IMG_ZERO_HUNGER);
//            AllDataHolder.getUtil().contentDisplayed(SUPPLY_CHAIN_OPTIMIZATION.IMAGES_ZERO_HUNGER);

            AllDataHolder.getUtil().contentDisplayed(SUPPLY_CHAIN_OPTIMIZATION.SECTION_REVOLUTIONIZE_YOUR_SUPPLY_CHAIN_WITH_IT_PARAGRAPH);
            AllDataHolder.getUtil().contentDisplayed(SUPPLY_CHAIN_OPTIMIZATION.IMG_PRO_FOUND_GLOBAL_IMPACT);
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e.getMessage());
        }
    }
}
