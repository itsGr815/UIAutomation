package com.dFarm.qa.dFarm.testsuite;

import com.dFarm.qa.dFarm.constants.groups.COLDSTORAGE;
import com.dFarm.qa.dFarm.constants.groups.PRIORITY;
import com.dFarm.qa.dFarm.objectrepository.LOGIN;
import com.dFarm.qa.dFarm.util.execution.AllDataHolder;
import com.dFarm.qa.dFarm.util.execution.BaseTest;
import com.dFarm.qa.dFarm.util.execution.FlexFrameWorkRunTimeException;
import org.testng.annotations.Test;

public class ProdSuite extends BaseTest {

    @Test(testName = "LoginHcl", groups = {COLDSTORAGE.HOME, PRIORITY.P1})
    public void loginEVEZON()throws FlexFrameWorkRunTimeException {
        String URL = "https://evezon.dfarm.in/login";
        try {
            AllDataHolder.getUtil().navigateToURL(URL);
           AllDataHolder.getUtil().report(1, "Login to HCL", AllDataHolder.getDriver().getCurrentUrl(), AllDataHolder.getDriver().getCurrentUrl());
           AllDataHolder.getUtil().waitUntill(3000);
           AllDataHolder.getUtil().contentDisplayed(LOGIN.TEXTBOX_LOGINID_INPUT);
           AllDataHolder.getUtil().contentDisplayed(LOGIN.TEXTBOX_PASSWORD_INPUT);
           AllDataHolder.getUtil().contentDisplayed(LOGIN.BUTTON_LOGIN);
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e);
        }
    }

    @Test(testName = "LoginKAAPCO", groups = {COLDSTORAGE.HOME, PRIORITY.P1})
    public void loginKAPPCO()throws FlexFrameWorkRunTimeException {
        String URL = "https://evezon.dfarm.in/login";
        try {
            AllDataHolder.getUtil().navigateToURL(URL);
            AllDataHolder.getUtil().report(1, "Login to HCL", AllDataHolder.getDriver().getCurrentUrl(), AllDataHolder.getDriver().getCurrentUrl());
            AllDataHolder.getUtil().waitUntill(3000);
            AllDataHolder.getUtil().contentDisplayed(LOGIN.TEXTBOX_LOGINID_INPUT);
            AllDataHolder.getUtil().contentDisplayed(LOGIN.TEXTBOX_PASSWORD_INPUT);
            AllDataHolder.getUtil().contentDisplayed(LOGIN.BUTTON_LOGIN);
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e);
        }
    }
}
