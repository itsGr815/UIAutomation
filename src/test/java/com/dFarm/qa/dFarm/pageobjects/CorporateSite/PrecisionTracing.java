package com.dFarm.qa.dFarm.pageobjects.CorporateSite;

import com.dFarm.qa.dFarm.constants.CorporateSite;
import com.dFarm.qa.dFarm.constants.ReportingConstants;
import com.dFarm.qa.dFarm.objectrepository.CorporateSite.CORPORATESITE_HOME;
import com.dFarm.qa.dFarm.objectrepository.CorporateSite.PRECISION_TRACING;
import com.dFarm.qa.dFarm.util.execution.AllDataHolder;
import com.dFarm.qa.dFarm.util.execution.FlexFrameWorkRunTimeException;

/**
 * @author G Ganesh
 */
public class PrecisionTracing  extends ReportingConstants implements CorporateSite {

    private static String currentPageURL = "NA";

    /**
     * @implNote Method to verify the Footer Link Precision Tracing Page
     * @author Gangarapu.Ganesh
     */
    public void verifyPrecisionTracingPage() throws FlexFrameWorkRunTimeException {
        try {
            verifyAndClickHeaderPrecisionTracingLink();
            navigateAndVerifyRealTimeWholeChainTracingSection();
            navigateAndVerifyWithPrecisionTraceSection();
            navigateAndVerifyDeliveringFoodSafetyAndQualitySection();
            navigateAndVerifyCertificationSection();
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e.getMessage());
        }
    }



    /**
     * @implNote Method to verify the Footer Link Precision Tracing
     * @author Gangarapu.Ganesh
     */
    public void verifyAndClickFooterPrecisionTracingLink() throws FlexFrameWorkRunTimeException {
        try {
            AllDataHolder.getUtil().report(1, "<mark>Verifying dFarm Inc Corporate Website Footer Section Link Navigation - Precision Tracing</mark>",
                    "<mark>Verifying dFarm Inc Corporate Website Footer Section</mark>", "");
            AllDataHolder.getUtil().scrollForSpecificElement(CORPORATESITE_HOME.SECTION_FOOTER);
            if(AllDataHolder.getUtil().isContentDisplayed(CORPORATESITE_HOME.SECTION_FOOTER)){
                AllDataHolder.getUtil().clickElement(CORPORATESITE_HOME.LINK_FOOTER_PRECISION_TRACING);
                AllDataHolder.getUtil().stringCompare(Precision_Tracing_url, AllDataHolder.getDriver().getCurrentUrl());
                currentPageURL = AllDataHolder.getDriver().getCurrentUrl();
            }else {
                AllDataHolder.getUtil().report(1, "<mark>Unable to Find - Precision Tracing Footer Link. Please Refreshing the page & Checking again</mark>",
                        "<mark>Re-Attempting to verify the Precision Tracing footer link verification</mark>", "");
                currentPageURL= AllDataHolder.getDriver().getCurrentUrl();
                AllDataHolder.getDriver().get(currentPageURL);
                AllDataHolder.getUtil().scrollForSpecificElement(CORPORATESITE_HOME.SECTION_FOOTER);
                if(AllDataHolder.getUtil().isContentDisplayed(CORPORATESITE_HOME.SECTION_FOOTER)){
                    AllDataHolder.getUtil().clickElement(CORPORATESITE_HOME.LINK_FOOTER_PRECISION_TRACING);
                    AllDataHolder.getUtil().stringCompare(Precision_Tracing_url, AllDataHolder.getDriver().getCurrentUrl());
                }else {
                    AllDataHolder.getUtil().report(4, "<mark>Unable to Find - Precision Tracing Footer Link. Please Check With Your Site Admin</mark>",
                            "<mark>Precision Tracing - Footer Link Verification is Failed</mark>", "");
                }
            }
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e.getMessage());
        }
    }

    /**
     * @implNote Method to verify the Header Link Precision Tracing
     * @author Gangarapu.Ganesh
     */
    public void verifyAndClickHeaderPrecisionTracingLink() throws FlexFrameWorkRunTimeException {
        try {
            AllDataHolder.getUtil().report(1, "<mark>Verifying dFarm Inc Corporate Website Header Section Link Navigation - Precision Tracing</mark>",
                    "<mark>Verifying dFarm Inc Corporate Website Header Section</mark>", "");
            AllDataHolder.getUtil().scrollForSpecificElement(CORPORATESITE_HOME.SECTION_HEADER);
            if(AllDataHolder.getUtil().isContentDisplayed(CORPORATESITE_HOME.SECTION_HEADER)){
                AllDataHolder.getUtil().clickElement(CORPORATESITE_HOME.LINK_PRECISION_TRACING);
                AllDataHolder.getUtil().stringCompare(Precision_Tracing_url, AllDataHolder.getDriver().getCurrentUrl());
                currentPageURL = AllDataHolder.getDriver().getCurrentUrl();
            }else {
                AllDataHolder.getUtil().report(1, "<mark>Unable to Find - Precision Tracing Header Link. Please Refreshing the page & Checking again</mark>",
                        "<mark>Re-Attempting to verify the Precision Tracing Header link verification</mark>", "");
                currentPageURL= AllDataHolder.getDriver().getCurrentUrl();
                AllDataHolder.getDriver().get(currentPageURL);
                AllDataHolder.getUtil().scrollForSpecificElement(CORPORATESITE_HOME.SECTION_HEADER);
                if(AllDataHolder.getUtil().isContentDisplayed(CORPORATESITE_HOME.SECTION_HEADER)){
                    AllDataHolder.getUtil().clickElement(CORPORATESITE_HOME.LINK_PRECISION_TRACING);
                    AllDataHolder.getUtil().stringCompare(Precision_Tracing_url, AllDataHolder.getDriver().getCurrentUrl());
                }else {
                    AllDataHolder.getUtil().report(4, "<mark>Unable to Find - Precision Tracing Header Link. Please Check With Your Site Admin</mark>",
                            "<mark>Precision Tracing - Header Link Verification is Failed</mark>", "");
                }
            }
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e.getMessage());
        }
    }

    /**
     * @implNote Method to verify the Footer Link Precision Tracing - Real-Time Whole Chain Tracing
     * @author Gangarapu.Ganesh
     */
    public void navigateAndVerifyRealTimeWholeChainTracingSection() throws FlexFrameWorkRunTimeException {
        try {
            AllDataHolder.getUtil().contentDisplayed(PRECISION_TRACING.LABEL_REAL_TIME);
            AllDataHolder.getUtil().contentDisplayed(PRECISION_TRACING.PARAGRAPH_REAL_TIME);
            AllDataHolder.getUtil().contentDisplayed(PRECISION_TRACING.IMG_REAL_TIME_TRANSITION);
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e.getMessage());
        }
    }

    /**
     * @implNote Method to verify the Footer Link Precision Tracing - With Precision Trace
     * @author Gangarapu.Ganesh
     */
    public void navigateAndVerifyWithPrecisionTraceSection() throws FlexFrameWorkRunTimeException {
        try {
            if(AllDataHolder.getUtil().isContentDisplayed(PRECISION_TRACING.IFRAME_PT)){
                AllDataHolder.getUtil().switchToFrame(PRECISION_TRACING.IFRAME_PT);
            }else if(AllDataHolder.getUtil().isContentDisplayed(PRECISION_TRACING.IFRAME_PT2)){
                AllDataHolder.getUtil().switchToFrame(PRECISION_TRACING.IFRAME_PT2);
            }
            AllDataHolder.getUtil().scrollForSpecificElement(PRECISION_TRACING.PARAGRAPH_WITH_PRECISION_TRACE);
            Thread.sleep(10000);
            AllDataHolder.getUtil().contentDisplayed(PRECISION_TRACING.LABEL_WITH_PRECISION_TRACE);
            AllDataHolder.getUtil().contentDisplayed(PRECISION_TRACING.PARAGRAPH_WITH_PRECISION_TRACE);
            AllDataHolder.getUtil().contentDisplayed(PRECISION_TRACING.PRECISION_TRACING_P1);
            AllDataHolder.getUtil().contentDisplayed(PRECISION_TRACING.IMG_AIMS_CIRCLE);
            AllDataHolder.getUtil().switchToDefaultContent(PRECISION_TRACING.LABEL_DELIVERING_FOOD_SAFETY);
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e.getMessage());
        }
    }

    /**
     * @implNote Method to verify the Footer Link Precision Tracing - Delivering Food Safety and Quality
     * @author Gangarapu.Ganesh
     */
    public void navigateAndVerifyDeliveringFoodSafetyAndQualitySection() throws FlexFrameWorkRunTimeException {
        try {
            AllDataHolder.getUtil().scrollForSpecificElement(PRECISION_TRACING.LABEL_DELIVERING_FOOD_SAFETY);
            AllDataHolder.getUtil().contentDisplayed(PRECISION_TRACING.LABEL_DELIVERING_FOOD_SAFETY);
            AllDataHolder.getUtil().contentDisplayed(PRECISION_TRACING.PARAGRAPH1_DELIVERING_FOOD_SAFETY);
            AllDataHolder.getUtil().contentDisplayed(PRECISION_TRACING.PARAGRAPH2_DELIVERING_FOOD_SAFETY);
            AllDataHolder.getUtil().contentDisplayed(PRECISION_TRACING.PARAGRAPH3_DELIVERING_FOOD_SAFETY);
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e.getMessage());
        }
    }


    /**
     * @implNote Method to verify the Footer Link Precision Tracing - Certification Section
     * @author Gangarapu.Ganesh
     */
    public void navigateAndVerifyCertificationSection() throws FlexFrameWorkRunTimeException {
        try {
            AllDataHolder.getUtil().scrollForSpecificElement(PRECISION_TRACING.SECTION_CERTIFICATIONS);
            AllDataHolder.getUtil().contentDisplayed(PRECISION_TRACING.SECTION_CERTIFICATIONS);
//            AllDataHolder.getUtil().contentDisplayed(PRECISION_TRACING.CERT_BRCGS);
//            AllDataHolder.getUtil().contentDisplayed(PRECISION_TRACING.CERT_FSSAI);
//            AllDataHolder.getUtil().contentDisplayed(PRECISION_TRACING.CERT_GLOBALGAP);
//            AllDataHolder.getUtil().contentDisplayed(PRECISION_TRACING.CERT_HACCP);
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e.getMessage());
        }
    }
}
