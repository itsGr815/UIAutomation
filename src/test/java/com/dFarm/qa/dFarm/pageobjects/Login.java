package com.dFarm.qa.dFarm.pageobjects;

import com.dFarm.qa.dFarm.constants.*;
import com.dFarm.qa.dFarm.objectrepository.LOGIN;
import com.dFarm.qa.dFarm.objectrepository.MarketPlace.MPHOMEPAGE;
import com.dFarm.qa.dFarm.util.execution.AllDataHolder;
import com.dFarm.qa.dFarm.util.execution.FlexFrameWorkRunTimeException;
import com.dFarm.qa.dFarm.util.reporters.Status;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Base64;

public class Login {

    public static void loginWithUser(String userName) throws FlexFrameWorkRunTimeException{
        try {
            String password ;
            String deCodeKey ;
            String decodedPwd = null;
            String decodedUserName = null;

            password = AllDataHolder.getTestData().get(AllDataHolder.getCurrentTestCaseId(), DFarmConstants.PASSWORD_EXCEL).length() > 0 ?
                    AllDataHolder.getTestData().get(AllDataHolder.getCurrentTestCaseId(), DFarmConstants.PASSWORD_EXCEL) : DFarmConstants.PASSWORD;
            deCodeKey = AllDataHolder.getTestData().get(AllDataHolder.getCurrentTestCaseId(), DFarmConstants.DECODE_KEY).length() > 0 ?
                    AllDataHolder.getTestData().get(AllDataHolder.getCurrentTestCaseId(), DFarmConstants.DECODE_KEY) : "NA";

            if(deCodeKey.equals("ZEZhcm1BSU1T")){
                decodedUserName = new String(Base64.getDecoder().decode(userName));
                decodedPwd   = new String(Base64.getDecoder().decode(password));
            }else {
                AllDataHolder.getUtil().report(0, "<mark>Password Decoding Failed</mark>", "Password Decoding",
                        "<mark>Incorrect Password Decode Key Passed<mark>");
                Assert.fail();
            }

            dFarmLogin(decodedUserName, decodedPwd);
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e);
        }
    }

    public static void dFarmLogin(String userName, String password) throws FlexFrameWorkRunTimeException{
        try {
            String url = AllDataHolder.getUrl();
            if(url.equalsIgnoreCase("NA")){
                url = AllDataHolder.getTestData().get(AllDataHolder.getCurrentTestCaseId(), TestDataConstants.APP_URL);
                AllDataHolder.setUrl(url);
            }
            AllDataHolder.getUtil().navigateToURL(url);
            // Set the parent window handle
            AllDataHolder.setParentWindowHandle(AllDataHolder.getDriver());
            //Perform login
            if(url.contains("marketplace") || url.contains("mp")){
                loginToMP(userName, password);
            }else{
                login(userName, password);
            }
            // Verify login failed
            // quitFailedLogin();
            // Set the parent window handle
        //  AllDataHolder.setParentWindowHandle(AllDataHolder.getDriver());
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e);
        }
    }

    public static void login(String userName, String password) throws FlexFrameWorkRunTimeException{
        try {
            WebDriver driver = AllDataHolder.getDriver();
            String url = AllDataHolder.getUrl();
            if (driver.getCurrentUrl().contains(DFarmConstants.DFARM_HOMEPAGE)){
                AllDataHolder.getUtil().report(Status.INFO,
                        "Launch DFarm Application in " + System.getProperty(DriverConstants.ENV) + "Environment",
                        "Application launched with following URL - " + System.getProperty(DriverConstants.URL)
                                + System.getProperty(DriverConstants.ENV) + "Environment",
                        "Application launched with following URL - " + System.getProperty(DriverConstants.URL)
                                + System.getProperty(DriverConstants.ENV) + "Environment");
            }
            if (AllDataHolder.getUtil().isContentDisplayed(LOGIN.TEXTBOX_LOGINID_INPUT)){
                AllDataHolder.getUtil().clickElement(LOGIN.TEXTBOX_LOGINID_INPUT);
                AllDataHolder.getUtil().clearAndSendValue(LOGIN.TEXTBOX_LOGINID_INPUT, userName);
                AllDataHolder.getUtil().clickElement(LOGIN.TEXTBOX_PASSWORD_INPUT);
                AllDataHolder.getUtil().clearAndSendValue(LOGIN.TEXTBOX_PASSWORD_INPUT, password);
                AllDataHolder.getUtil().clickElement(LOGIN.BUTTON_LOGIN);
                AllDataHolder.getUtil().waitUntill(3000);
            }else {
                AllDataHolder.getUtil().report(DFarmConstants.ERROR, "Load Login Page", "Login page not found <br>",
                        "Login page not found");
                Assert.fail("Login page not found");
            }
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e);
        }
    }

    public static void loginToMP(String userName, String password) throws FlexFrameWorkRunTimeException{
        try {
            WebDriver driver = AllDataHolder.getDriver();
            String url = AllDataHolder.getUrl();
            if (driver.getCurrentUrl().contains(DFarmConstants.DFARM_HOMEPAGE)){
                AllDataHolder.getUtil().report(Status.INFO,
                        "Launch DFarm Application in " + System.getProperty(DriverConstants.ENV) + "Environment",
                        "Application launched with following URL - " + System.getProperty(DriverConstants.URL)
                                + System.getProperty(DriverConstants.ENV) + "Environment",
                        "Application launched with following URL - " + System.getProperty(DriverConstants.URL)
                                + System.getProperty(DriverConstants.ENV) + "Environment");
            }
            AllDataHolder.getMarkeplaceHomepage().navigateDynamicMarketplace();
            if (AllDataHolder.getUtil().isContentDisplayed(MPHOMEPAGE.LINK_SIGN_IN)){
                AllDataHolder.getUtil().clickElementJS(MPHOMEPAGE.LINK_SIGN_IN);
                AllDataHolder.getUtil().clickElement(MPHOMEPAGE.INPUT_ENTER_EMAIL);
//                AllDataHolder.getUtil().clearAndSendValue(MPHOMEPAGE.INPUT_ENTER_EMAIL, userName);
                AllDataHolder.getUtil().clearAndSendValue(MPHOMEPAGE.INPUT_ENTER_EMAIL, "andy@gmail.com");
                AllDataHolder.getUtil().clickElement(MPHOMEPAGE.INPUT_ENTER_PASSWORD);
                AllDataHolder.getUtil().clearAndSendValue(MPHOMEPAGE.INPUT_ENTER_PASSWORD, password);
                AllDataHolder.getUtil().clickElementJS(MPHOMEPAGE.BUTTON_SIGN_IN);
               // AllDataHolder.getUtil().waitUntill(3000);
                quitFailedLoginMP();
            }else {
                AllDataHolder.getUtil().report(DFarmConstants.ERROR, "Load Login Page", "Login page not found <br>",
                        "Login page not found");
                Assert.fail("Login page not found");
            }
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e);
        }
    }


    /**
     * @implNote Method to verify the Login failure due to incorrect Username or Password for Marketplace
     * @author Gangarapu.Ganesh
     */
    public static void quitFailedLoginMP() throws FlexFrameWorkRunTimeException {
        try {
            if (AllDataHolder.getUtil().isContentDisplayed(MPHOMEPAGE.MSG_LOGIN_FAIL_INVALID_CREDENTIALS)) {
                AllDataHolder.getUtil().report(DFarmConstants.WARNING, "Login Failed",
                        "<br><mark>" +   AllDataHolder.getUtil().getText(MPHOMEPAGE.MSG_LOGIN_FAIL_INVALID_CREDENTIALS)+ "<br><mark>",
                        "<br><mark>" + MarketPlaceConstants.USER_PWD_INCORRECT + "<br><mark>");
                Assert.fail("Login Fail");
            }
        } catch (Exception e) {
            throw new FlexFrameWorkRunTimeException(e);
        }
    }

    /* To-Do */
    public static void quitFailedLogin() throws FlexFrameWorkRunTimeException {
        try {
            if (AllDataHolder.getUtil().isContentDisplayed(MPHOMEPAGE.MSG_LOGIN_FAIL_INVALID_CREDENTIALS)) {
                AllDataHolder.getUtil().report(DFarmConstants.WARNING, "Login Failed",
                        "<br><mark>" +   AllDataHolder.getUtil().getText(MPHOMEPAGE.MSG_LOGIN_FAIL_INVALID_CREDENTIALS)+ "<br><mark>",
                        "<br><mark>" + MarketPlaceConstants.USER_PWD_INCORRECT + "<br><mark>");
                Assert.fail("Login Fail");
            }
        } catch (Exception e) {
            throw new FlexFrameWorkRunTimeException(e);
        }
    }
}
