package com.dFarm.qa.dFarm.pageobjects.CorporateSite;

import com.dFarm.qa.dFarm.constants.CorporateSite;
import com.dFarm.qa.dFarm.constants.ReportingConstants;
import com.dFarm.qa.dFarm.objectrepository.CorporateSite.CORPORATESITE_HOME;
import com.dFarm.qa.dFarm.util.execution.AllDataHolder;
import com.dFarm.qa.dFarm.util.execution.FlexFrameWorkRunTimeException;


public class CorporateSiteHome extends ReportingConstants implements CorporateSite {

    /**
     * @implNote Method to verify the dFarm Inc Home Page
     * @author Gangarapu.Ganesh
     */
    public void verifyDFarmIncHomePage() throws FlexFrameWorkRunTimeException{
        try {
            verifyDFarmLogo();
            verifyHeaderNavigation();
            verifyThinkBeyondBanner();
            verifyFoodSystemSection();
            verifyRealTimeWholeChainSection();
            verifyRealTimeWholeChainVisualization();
            verifyGlobalDigitalMP();
            verifyPrecisionTracing();
            verifyDFarmIncFooterSection();
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e.getMessage());
        }
    }

    /**
     * @implNote Method to verify the Header & footer dFarm Logo
     * @author Gangarapu.Ganesh
     */
    public void verifyDFarmLogo() throws FlexFrameWorkRunTimeException{

        try {
            AllDataHolder.getUtil().contentDisplayed(CORPORATESITE_HOME.IMG_HEADER_dFARM_LOGO);
            Thread.sleep(2000);
            AllDataHolder.getUtil().scrollForSpecificElement(CORPORATESITE_HOME.IMG_FOOTER_dFARM_LOGO);
            AllDataHolder.getUtil().contentDisplayed(CORPORATESITE_HOME.IMG_FOOTER_dFARM_LOGO);
            AllDataHolder.getUtil().scrollForSpecificElement(CORPORATESITE_HOME.IMG_HEADER_dFARM_LOGO);
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e.getMessage());
        }
    }

    /**
     * @implNote Method to verify the Header section options
     * @author Gangarapu.Ganesh
     */
    public void verifyHeaderNavigation() throws FlexFrameWorkRunTimeException {
        try {
            AllDataHolder.getUtil().report(1, "<mark>Verifying dFarm Inc Corporate Website Headers</mark>",
                    "<mark>Verifying dFarm Inc Corporate Website Headers</mark>", AllDataHolder.getDriver().getCurrentUrl());

            AllDataHolder.getUtil().contentDisplayed(CORPORATESITE_HOME.LINK_SUPPLY_CHAIN_OPTIMIZATION);
            AllDataHolder.getUtil().contentDisplayed(CORPORATESITE_HOME.LINK_DYNAMIC_MARKETPLACE);
            AllDataHolder.getUtil().contentDisplayed(CORPORATESITE_HOME.LINK_SCM_FINANCE);
            AllDataHolder.getUtil().contentDisplayed(CORPORATESITE_HOME.LINK_PRECISION_TRACING);
            AllDataHolder.getUtil().contentDisplayed(CORPORATESITE_HOME.LINK_FSMA_204);
            AllDataHolder.getUtil().contentDisplayed(CORPORATESITE_HOME.LINK_TECHNOLOGIES);
            AllDataHolder.getUtil().contentDisplayed(CORPORATESITE_HOME.LINK_GET_DEMO);
            AllDataHolder.getUtil().contentDisplayed(CORPORATESITE_HOME.LINK_SUPPLY_CHAIN_OPTIMIZATION);

        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e.getMessage());
        }
    }

    /**
     * @implNote Method to verify the THINK BEYOND FSMA 204 Poster
     * @author Gangarapu.Ganesh
     */
    public void verifyThinkBeyondBanner() throws FlexFrameWorkRunTimeException{
       try {
           AllDataHolder.getUtil().report(1, "<mark>Verifying dFarm Inc Corporate Website THINK BEYOND FSMA 204 Poster</mark>",
                   "<mark>Verifying dFarm Inc Corporate Website THINK BEYOND FSMA 204 Poster</mark>", AllDataHolder.getDriver().getCurrentUrl());
           AllDataHolder.getUtil().contentDisplayed(CORPORATESITE_HOME.LABEL_THINK_BEYOND_FSMA_204);
           AllDataHolder.getUtil().contentDisplayed(CORPORATESITE_HOME.CONTENT_THINK_BEYOND_FSMA_204);
           AllDataHolder.getUtil().contentDisplayed(CORPORATESITE_HOME.BUTTON_GET_A_CONSULTATION);
           AllDataHolder.getUtil().contentDisplayed(CORPORATESITE_HOME.BUTTON_LEARN_MORE);
       }catch (Exception e){
           throw new FlexFrameWorkRunTimeException(e.getMessage());
       }
    }

    /**
     * @implNote Method to verify the Food Safety – Intelligence – Dynamic Markets
     * @author Gangarapu.Ganesh
     */
    public void verifyFoodSystemSection() throws FlexFrameWorkRunTimeException{
        try {
            AllDataHolder.getUtil().report(1, "<mark>Verifying dFarm Inc Corporate Website Food Safety – Intelligence – Dynamic Markets</mark>",
                    "<mark>Verifying dFarm Inc Corporate Website Food Safety – Intelligence – Dynamic Markets</mark>", AllDataHolder.getDriver().getCurrentUrl());
            AllDataHolder.getUtil().scrollForSpecificElement(CORPORATESITE_HOME.SECTION_FOOD_SAFETY);
            AllDataHolder.getUtil().contentDisplayed(CORPORATESITE_HOME.SECTION_FOOD_SAFETY);
            AllDataHolder.getUtil().contentDisplayed(CORPORATESITE_HOME.BUTTON_WATCH_PRODUCT_TOUR);
            AllDataHolder.getUtil().contentDisplayed(CORPORATESITE_HOME.LABEL_FOOD_SAFETY);
            verifyFoodSystemSectionProductTour();
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e.getMessage());
        }
    }

    /**
     * @implNote Method to verify the Product tour button functionality
     * @author Gangarapu.Ganesh
     */
    public void verifyFoodSystemSectionProductTour() throws FlexFrameWorkRunTimeException{
        String currentYoutubeURL;
        try {
            AllDataHolder.getUtil().report(1, "<mark>Verifying dFarm Inc Corporate Website Product tour button functionality</mark>",
                    "<mark>Verifying dFarm Inc Corporate Website Product tour button functionality</mark>", AllDataHolder.getDriver().getCurrentUrl());
            AllDataHolder.getUtil().clickElement(CORPORATESITE_HOME.BUTTON_WATCH_PRODUCT_TOUR);
            AllDataHolder.getUtil().switchToNewWindow();
            currentYoutubeURL = AllDataHolder.getDriver().getCurrentUrl();
            AllDataHolder.getUtil().stringCompare(dFarm_YT_AIMS__PROCESS_FLOW, currentYoutubeURL);
            AllDataHolder.getUtil().closeAllChildWindowsOrTabs();
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e.getMessage());
        }
    }

    /**
     * @implNote Method to verify the REAL-TIME WHOLE CHAIN DISTRIBUTED ERP
     * @author Gangarapu.Ganesh
     */
    public void verifyRealTimeWholeChainSection() throws FlexFrameWorkRunTimeException{
        try {
            AllDataHolder.getUtil().report(1, "<mark>Verifying dFarm Inc Corporate Website REAL-TIME WHOLE CHAIN DISTRIBUTED ERP</mark>",
                    "<mark>Verifying dFarm Inc Corporate Website REAL-TIME WHOLE CHAIN DISTRIBUTED ERP</mark>", AllDataHolder.getDriver().getCurrentUrl());
            AllDataHolder.getUtil().scrollForSpecificElement(CORPORATESITE_HOME.LABEL_REAL_TIME_WHOLE_CHAIN);
            AllDataHolder.getUtil().contentDisplayed(CORPORATESITE_HOME.LABEL_REAL_TIME_WHOLE_CHAIN);
            AllDataHolder.getUtil().contentDisplayed(CORPORATESITE_HOME.LABEL_REAL_TIME_WHOLE_CHAIN_CONTENT);
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e.getMessage());
        }
    }

    /**
     * @implNote Method to verify the Whole Chain Visualization
     * @author Gangarapu.Ganesh
     */
    public void verifyRealTimeWholeChainVisualization() throws FlexFrameWorkRunTimeException{
        try {
            AllDataHolder.getUtil().report(1, "<mark>Verifying dFarm Inc Corporate Website Whole Chain Visualization</mark>",
                    "<mark>Verifying dFarm Inc Corporate Website Whole Chain Visualization</mark>", AllDataHolder.getDriver().getCurrentUrl());
            AllDataHolder.getUtil().scrollForSpecificElement(CORPORATESITE_HOME.LABEL_WHILE_CHAIN_VISUALIZATION);
            AllDataHolder.getUtil().contentDisplayed(CORPORATESITE_HOME.LABEL_WHILE_CHAIN_VISUALIZATION);
            AllDataHolder.getUtil().contentDisplayed(CORPORATESITE_HOME.CONTENT_WHOLE_CHAIN_PARAGRAPH1);
            AllDataHolder.getUtil().contentDisplayed(CORPORATESITE_HOME.CONTENT_WHOLE_CHAIN_PARAGRAPH2);
            AllDataHolder.getUtil().contentDisplayed(CORPORATESITE_HOME.BUTTON_WHOLE_CHAIN_LEARN_MORE);
            realTimeWholeChainVisualizationLearnMore();
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e.getMessage());
        }
    }

    /**
     * @implNote Method to verify the Product tour button functionality
     * @author Gangarapu.Ganesh
     */
    public void realTimeWholeChainVisualizationLearnMore() throws FlexFrameWorkRunTimeException{
        String currentYoutubeURL;
        try {
            AllDataHolder.getUtil().report(1, "<mark>Verifying dFarm Inc Corporate Website Website Whole Chain Visualization Learn More Button</mark>",
                    "<mark>Verifying dFarm Inc Corporate Website Website Whole Chain Visualization Learn More Button</mark>", AllDataHolder.getDriver().getCurrentUrl());
            AllDataHolder.getUtil().clickElement(CORPORATESITE_HOME.BUTTON_WHOLE_CHAIN_LEARN_MORE);
            currentYoutubeURL = AllDataHolder.getDriver().getCurrentUrl();
            AllDataHolder.getUtil().stringCompare(dFarm_supply_chain_optimization_url2, currentYoutubeURL);
            AllDataHolder.getDriver().get(AllDataHolder.getUrl());
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e.getMessage());
        }
    }

    /**
     * @implNote Method to verify the Global Digital Marketplace
     * @author Gangarapu.Ganesh
     */
    public void verifyGlobalDigitalMP() throws FlexFrameWorkRunTimeException{
        try {
            AllDataHolder.getUtil().report(1, "<mark>Verifying dFarm Inc Corporate Website Global Digital Marketplace</mark>",
                    "<mark>Verifying dFarm Inc Corporate Website Global Digital Marketplace</mark>", AllDataHolder.getDriver().getCurrentUrl());
            AllDataHolder.getUtil().scrollForSpecificElement(CORPORATESITE_HOME.LABEL_GLOBAL_DIGITAL_MARKETPLACE);
            AllDataHolder.getUtil().contentDisplayed(CORPORATESITE_HOME.CONTENT_GDM_PARAGRAPH1);
            AllDataHolder.getUtil().contentDisplayed(CORPORATESITE_HOME.CONTENT_GDM_PARAGRAPH2);
            AllDataHolder.getUtil().contentDisplayed(CORPORATESITE_HOME.BUTTON_GDM_LEARN_MORE);
            verifyGDMLearnMore();
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e.getMessage());
        }
    }


    /**
     * @implNote Method to verify the Global Digital Marketplace Learn More
     * @author Gangarapu.Ganesh
     */
    public void verifyGDMLearnMore() throws FlexFrameWorkRunTimeException{
        String currentYoutubeURL;
        try {
            AllDataHolder.getUtil().report(1, "<mark>Verifying dFarm Inc Corporate Website GDM Learn More button functionality</mark>",
                    "<mark>Verifying dFarm Inc Corporate Website GDM Learn More button functionality</mark>", AllDataHolder.getDriver().getCurrentUrl());
            AllDataHolder.getUtil().clickElement(CORPORATESITE_HOME.BUTTON_GDM_LEARN_MORE);
            AllDataHolder.getUtil().switchToNewWindow();
            currentYoutubeURL = AllDataHolder.getDriver().getCurrentUrl();
            AllDataHolder.getUtil().stringCompare(GDM_URL, currentYoutubeURL);
            AllDataHolder.getUtil().closeAllChildWindowsOrTabs();
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e.getMessage());
        }
    }

    /**
     * @implNote Method to verify the Precision Tracing
     * @author Gangarapu.Ganesh
     */
    public void verifyPrecisionTracing() throws FlexFrameWorkRunTimeException{
        try {
            AllDataHolder.getUtil().report(1, "<mark>Verifying dFarm Inc Corporate Website Precision Tracing</mark>",
                    "<mark>Verifying dFarm Inc Corporate Website Precision Tracing</mark>", "");
            AllDataHolder.getUtil().scrollForSpecificElement(CORPORATESITE_HOME.LABEL_PRECISION_TRACING);
            AllDataHolder.getUtil().contentDisplayed(CORPORATESITE_HOME.CONTENT_PT_PARAGRAPH1);
            AllDataHolder.getUtil().contentDisplayed(CORPORATESITE_HOME.CONTENT_PT_PARAGRAPH2);
            AllDataHolder.getUtil().contentDisplayed(CORPORATESITE_HOME.BUTTON_PT_LEARN_MORE);
            verifyPTLearnMore();
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e.getMessage());
        }
    }

    /**
     * @implNote Method to verify the Global Digital Marketplace Learn More
     * @author Gangarapu.Ganesh
     */
    public void verifyPTLearnMore() throws FlexFrameWorkRunTimeException{
        String currentYoutubeURL;
        try {
            AllDataHolder.getUtil().report(1, "<mark>Verifying dFarm Inc Corporate Website Precision Tracing Learn More functionality</mark>",
                    "<mark>Verifying dFarm Inc Corporate Website Product tour button functionality</mark>", AllDataHolder.getDriver().getCurrentUrl());
            AllDataHolder.getUtil().clickElement(CORPORATESITE_HOME.BUTTON_PT_LEARN_MORE);
            currentYoutubeURL = AllDataHolder.getDriver().getCurrentUrl();
            AllDataHolder.getUtil().stringCompare(Precision_Tracing_url2, currentYoutubeURL);
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e.getMessage());
        }
    }

    /**
     * @implNote Method to verify the Footer Section
     * @author Gangarapu.Ganesh
     */
    public void verifyDFarmIncFooterSection() throws FlexFrameWorkRunTimeException{
        try {
            AllDataHolder.getUtil().report(1, "<mark>Verifying dFarm Inc Corporate Website Footer Section</mark>",
                    "<mark>Verifying dFarm Inc Corporate Website Footer Section</mark>", "");
            Thread.sleep(5000);
            AllDataHolder.getUtil().scrollForSpecificElement(CORPORATESITE_HOME.SECTION_FOOTER);
            AllDataHolder.getUtil().contentDisplayed(CORPORATESITE_HOME.SECTION_FOOTER);

            //Verifying the dFarm Inc footer Section links
            AllDataHolder.getUtil().contentDisplayed(CORPORATESITE_HOME.LINK_HOME);
            AllDataHolder.getUtil().contentDisplayed(CORPORATESITE_HOME.LINK_ABOUT_US);
            AllDataHolder.getUtil().contentDisplayed(CORPORATESITE_HOME.LINK_BLOG);

            AllDataHolder.getUtil().contentDisplayed(CORPORATESITE_HOME.LINK_CASE_STUDY);
            AllDataHolder.getUtil().contentDisplayed(CORPORATESITE_HOME.LINK_NEWS);
            AllDataHolder.getUtil().contentDisplayed(CORPORATESITE_HOME.LINK_SOCIAL);
            AllDataHolder.getUtil().contentDisplayed(CORPORATESITE_HOME.LINK_SOCIAL_LINKEDIN);
            AllDataHolder.getUtil().contentDisplayed(CORPORATESITE_HOME.LINK_SOCIAL_TWITTER);
//            AllDataHolder.getUtil().contentDisplayed(CORPORATESITE_HOME.LINK_FOOTER_SUPPLY_CHAIN_OPTIMIZATION);
//            AllDataHolder.getUtil().contentDisplayed(CORPORATESITE_HOME.LINK_FOOTER_DYNAMIC_MARKETPLACE);
//            AllDataHolder.getUtil().contentDisplayed(CORPORATESITE_HOME.LINK_FOOTER_SCM_FINANCE);
//            AllDataHolder.getUtil().contentDisplayed(CORPORATESITE_HOME.LINK_FOOTER_PRECISION_TRACING);

//            AllDataHolder.getUtil().contentDisplayed(CORPORATESITE_HOME.LINK_FOOTER_FSMA_204);
//            AllDataHolder.getUtil().contentDisplayed(CORPORATESITE_HOME.LINK_FOOTER_TECHNOLOGIES);
            AllDataHolder.getUtil().contentDisplayed(CORPORATESITE_HOME.LINK_CAREERS);
            AllDataHolder.getUtil().contentDisplayed(CORPORATESITE_HOME.TEXT_GLOBAL_HEADQUARTERS);

            AllDataHolder.getUtil().contentDisplayed(CORPORATESITE_HOME.TEXT_GLOBAL_HEADQUARTERS_ADDRESS1);
            AllDataHolder.getUtil().contentDisplayed(CORPORATESITE_HOME.TEXT_GLOBAL_HEADQUARTERS_ADDRESS1_PHONE);
            AllDataHolder.getUtil().contentDisplayed(CORPORATESITE_HOME.TEXT_REGIONAL_OFFICE);
            AllDataHolder.getUtil().contentDisplayed(CORPORATESITE_HOME.TEXT_REGIONAL_OFFICE_LIST);

            AllDataHolder.getUtil().report(1, "<mark>Verifying dFarm Inc Corporate Global Address</mark>",
                    "<mark>"+AllDataHolder.getUtil().getText(CORPORATESITE_HOME.TEXT_GLOBAL_HEADQUARTERS_ADDRESS1)+"</mark>", "");
            AllDataHolder.getUtil().report(1, "<mark>Verifying dFarm Inc Corporate Regional Office</mark>",
                    "<mark>"+AllDataHolder.getUtil().getText(CORPORATESITE_HOME.TEXT_REGIONAL_OFFICE_LIST)+"</mark>", "");

        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e.getMessage());
        }
    }


}

