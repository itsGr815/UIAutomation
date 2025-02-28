package com.dFarm.qa.dFarm.pageobjects.CorporateSite;

import com.dFarm.qa.dFarm.constants.CorporateSite;
import com.dFarm.qa.dFarm.constants.ReportingConstants;
import com.dFarm.qa.dFarm.objectrepository.CorporateSite.CORPORATESITE_HOME;
import com.dFarm.qa.dFarm.util.execution.AllDataHolder;
import com.dFarm.qa.dFarm.util.execution.FlexFrameWorkRunTimeException;

/**
 * @author G Ganesh
 */
public class Careers  extends ReportingConstants implements CorporateSite {


    /**
     * @implNote Method to verify the Footer Link Careers
     * @author Gangarapu.Ganesh
     */
    public void verifyAndClickFooterCareersLink() throws FlexFrameWorkRunTimeException {
        try {
            AllDataHolder.getUtil().report(1, "<mark>Verifying dFarm Inc Corporate Website Footer Section Link Navigation - Careers </mark>",
                    "<mark>Verifying dFarm Inc Corporate Website Footer Section</mark>", "");
            AllDataHolder.getUtil().scrollForSpecificElement(CORPORATESITE_HOME.SECTION_FOOTER);
            if(AllDataHolder.getUtil().isContentDisplayed(CORPORATESITE_HOME.SECTION_FOOTER)){
                AllDataHolder.getUtil().clickElement(CORPORATESITE_HOME.LINK_CAREERS);
                AllDataHolder.getUtil().stringCompare(Career_Url, AllDataHolder.getDriver().getCurrentUrl());
            }else {
                AllDataHolder.getUtil().report(1, "<mark>Unable to Find - Careers Footer Link. Please Refreshing the page & Checking again</mark>",
                        "<mark>Re-Attempting to verify the Careers footer link verification</mark>", "");
                String URL= AllDataHolder.getDriver().getCurrentUrl();
                AllDataHolder.getDriver().get(URL);
                AllDataHolder.getUtil().scrollForSpecificElement(CORPORATESITE_HOME.SECTION_FOOTER);
                if(AllDataHolder.getUtil().isContentDisplayed(CORPORATESITE_HOME.SECTION_FOOTER)){
                    AllDataHolder.getUtil().clickElement(CORPORATESITE_HOME.LINK_CAREERS);
                    AllDataHolder.getUtil().stringCompare(Career_Url, AllDataHolder.getDriver().getCurrentUrl());
                }else {
                    AllDataHolder.getUtil().report(4, "<mark>Unable to Find - Careers Footer Link. Please Check With Your Site Admin</mark>",
                            "<mark>Careers - Footer Link Verification is Failed</mark>", "");
                }
            }
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e.getMessage());
        }
    }
}
