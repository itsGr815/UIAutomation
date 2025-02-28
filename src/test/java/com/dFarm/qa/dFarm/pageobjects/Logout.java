package com.dFarm.qa.dFarm.pageobjects;

import com.dFarm.qa.dFarm.constants.DFarmConstants;
import com.dFarm.qa.dFarm.constants.ServiceAndSauce;
import com.dFarm.qa.dFarm.objectrepository.LOGOUT;
import com.dFarm.qa.dFarm.util.execution.AllDataHolder;
import com.dFarm.qa.dFarm.util.execution.FlexFrameWorkRunTimeException;
import com.dFarm.qa.dFarm.util.execution.GetTestData;
import org.apache.commons.lang3.StringUtils;
import org.apache.xmlbeans.impl.xb.xsdschema.NamedGroup;
import org.openqa.selenium.WebDriver;

public class Logout {

    public static void logout() throws FlexFrameWorkRunTimeException {
        WebDriver driver = AllDataHolder.getDriver();
       try {
           String type = AllDataHolder.getTestData().get(AllDataHolder.getCurrentTestCaseId(), ServiceAndSauce.TYPE);
           if (AllDataHolder.getIsServiceData() && !StringUtils.isEmpty(type)){
               GetTestData.resetUserID(AllDataHolder.getUserId());
           }
           //close All child windows or tabs and switch back to parent window for logout
           AllDataHolder.getUtil().closeAllChildWindowsOrTabs();
           AllDataHolder.getUtil().waitUntill(3000);
           AllDataHolder.getUtil().clickElement(LOGOUT.ICON_USER_PROFILE);
           AllDataHolder.getUtil().clickElement(LOGOUT.LINK_LOGOFF);
           if (AllDataHolder.getUtil().isContentDisplayed(LOGOUT.OVERLAY_LOGOFF_CONFIRMATION)){
               System.out.println(AllDataHolder.getUtil().getText(LOGOUT.OVERLAY_LOGOFF_CONFIRMATION_HEADER));
               AllDataHolder.getUtil().stringCompare(AllDataHolder.getUtil().getText(LOGOUT.OVERLAY_LOGOFF_CONFIRMATION_HEADER),
                       DFarmConstants.ARE_YOU_SURE);
               AllDataHolder.getUtil().stringCompare(AllDataHolder.getUtil().getText(LOGOUT.OVERLAY_LOGOFF_CONFIRMATION_CONTENT_LINE1), DFarmConstants.OVERLAY_LOGOFF_CONFIRMATION_CONTENT_LINE1);
               AllDataHolder.getUtil().stringCompare(AllDataHolder.getUtil().getText(LOGOUT.OVERLAY_LOGOFF_CONFIRMATION_CONTENT_LINE2), DFarmConstants.OVERLAY_LOGOFF_CONFIRMATION_CONTENT_LINE2);
               LOGOUT[] cancelOrConfirm = {
                       LOGOUT.BUTTON_LOGOFF_CANCEL, LOGOUT.BUTTON_LOGOFF_CONFIRM
               };
               AllDataHolder.getUtil().contentDisplayed(cancelOrConfirm);
               AllDataHolder.getUtil().clickElement(LOGOUT.BUTTON_LOGOFF_CONFIRM);
           }
       }catch (Exception E){
        String currentURL = driver.getCurrentUrl();
        if(!(currentURL == null || currentURL.isEmpty())){
            driver.manage().deleteCookieNamed("__utmb");
            driver.navigate().refresh();
           // driver.switchTo().defaultContent();
            driver.get(AllDataHolder.getUrl());
            AllDataHolder.getUtil().report(DFarmConstants.FAIL, "Click on Logout Link", "User logged out",
                    "Error finding logout link. Logged out forcefully.");
        }
       }
       AllDataHolder.setUrl("NA");
    }
}
