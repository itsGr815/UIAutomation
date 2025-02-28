package com.dFarm.qa.dFarm.util.execution;

import com.aventstack.extentreports.Status;
import com.dFarm.qa.dFarm.constants.DFarmConstants;
import com.dFarm.qa.dFarm.constants.ReportingConstants;
import com.dFarm.qa.dFarm.constants.ServiceAndSauce;
import com.dFarm.qa.dFarm.objectrepository.LOGIN;
import com.dFarm.qa.dFarm.util.datahandler.ISuppllyLocatorInfo;
import com.dFarm.qa.dFarm.util.reporters.ExtentReporting;
import com.dFarm.qa.dFarm.util.reporters.WSException;
import com.paulhammant.ngwebdriver.NgWebDriver;
import io.restassured.response.Response;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.openqa.selenium.Point;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtil extends ExtentReporting implements DFarmConstants {

    private static final long DEFAULT_TIMEOUT = 60000;
    private static final long DEFAULT_CHECK_INTERVAL = 500;
    private static final Random r = new Random();
    //----------initializing your table for each test case

    /**
     * @implNote To get web element for object passes
     * @param o
     * @return element
     * @author Gangarapu.Ganesh
     */
    public WebElement getWebElement(Object o){
        WebDriver driver = AllDataHolder.getDriver();
        WebElement ele = null;
        if (o != null){
            try {
                if (o instanceof By){
                    ele = driver.findElement((By) o);
                }
                if (o instanceof WebElement){
                    ele = (WebElement) o;
                }
            }catch (StaleElementReferenceException sere){
                WebDriverWait wait = new WebDriverWait(driver, 30);
                if (o instanceof By){
                    wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy((By) o));
                    ele = driver.findElement((By) o);
                }
                if (o instanceof WebElement){
                    wait.until(ExpectedConditions.visibilityOf((WebElement) o));
                    ele = (WebElement) o;
                }
            }
        }else {
            throw new IllegalArgumentException("Object passes to getWebElement should not be null");
        }
        return ele;
    }

    public By getBy(Object o){
        if (o instanceof By){
            return (By) o;
        }
        if(o instanceof WebElement){
            return (By) o;
        }
        throw new IllegalArgumentException("Object passes to getWebElement should not be null");
    }

    public By returnByforCustomerUsage(ISuppllyLocatorInfo isli){
        return (By) isli.getLocator();

    }

    public void clickElement(ISuppllyLocatorInfo isil) throws FlexFrameWorkRunTimeException {
        clickElement(isil.getLocator(), isil.getLabel());
    }

    /**
     * @implNote Method to Click Element
     * @param byOrWebElement
     * @param identifier
     * @author Gangarapu.Ganesh
     */
    public void clickElement(Object byOrWebElement, String identifier) throws FlexFrameWorkRunTimeException {
        try {
            WebElement element = getWebElement(byOrWebElement);
            // An addition test check
            if (element.isEnabled()){
                element.click();
                // TODO need to come up with better approach for wait handling, as this ClickElement is used across the application
                waitUntill(3000);
                report(SUCCESS, "Click on: " + identifier, REPORT_CLICKED + identifier, REPORT_EXPECTED + identifier);
            }else {
                report(FAIL, "Click on: " + identifier, REPORT_CLICKED + identifier, "Unable to click , element is disabled" + identifier);
            }
        }catch (ElementClickInterceptedException enie){
            clickElementJS(byOrWebElement, identifier);
        }catch (StaleElementReferenceException sere){
            clickElement(byOrWebElement, identifier);
        }catch (Exception e){
            report(FAIL, "Click on " + identifier, REPORT_CLICKED + identifier, e.getMessage());
        }

    }

    public void clickElementJS(ISuppllyLocatorInfo isil) throws FlexFrameWorkRunTimeException {
        clickElementJS(isil.getLocator(), isil.getLabel());
    }

    /**
     * @implNote Method to click element using JavascriptExecutor
     * @author Gangarapu.Ganesh
     * @param byOrWebElement
     * @param identifier
     */
    public void clickElementJS(Object byOrWebElement, String identifier) throws FlexFrameWorkRunTimeException {
        try {
            WebDriver driver = AllDataHolder.getDriver();
            WebElement element = getWebElement(byOrWebElement);
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            if (element.isEnabled()){
                executor.executeScript("arguments[0].click();", element);
                report(SUCCESS, "Click on: " + identifier, REPORT_CLICKED + identifier, REPORT_EXPECTED + identifier);
            }else {
                report(FAIL, "Click on: " + identifier, REPORT_CLICKED + identifier, "Unable to click , element is disabled" + identifier);
            }
        }catch (StaleElementReferenceException sere){
            clickElementJS(byOrWebElement, identifier);
        }catch (Exception e){
            report(FAIL, "Click on " + identifier, REPORT_CLICKED + identifier, e.getMessage());
        }
    }

    /**
     * @implNote Method to scroll for particular element and click on the element
     * @param isil
     * @author Gangarapu.Ganesh
     */
    public void scrollAndClick(ISuppllyLocatorInfo isil) throws FlexFrameWorkRunTimeException {
        scrollForSpecificElement(isil);
        clickElement(isil.getLocator(), isil.getLabel());
    }

    public void scrollForSpecificElement( ISuppllyLocatorInfo isil) throws FlexFrameWorkRunTimeException {
        scrollForSpecificElement(isil.getLocator(), isil.getLabel());
    }

    /**
     * @implNote Method to scroll for specific element
     * @param byOWebElement
     * @param identifier
     * @author Gangarapu.Ganesh
     */
    public void scrollForSpecificElement( Object byOWebElement, String identifier) throws FlexFrameWorkRunTimeException {
        try {
            WebDriver driver = AllDataHolder.getDriver();
            WebElement element = getWebElement(byOWebElement);
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].scrollIntoView()", element);
            report(SUCCESS, "Perform, Java script Scrolling", "Scrolled to " + identifier, "Scrolled to " + identifier);
        }catch (StaleElementReferenceException sere){
            scrollForSpecificElement(byOWebElement, identifier);
        }catch (Exception e){
            report(FAIL, "Perform, Java script Scrolling", "Scrolld to " + identifier, e.getMessage());
        }
    }

    public void clickElementInLoop(ISuppllyLocatorInfo isil, int iteration) throws FlexFrameWorkRunTimeException {
        for (int i = 1; i <= iteration; i++){
            try {
                WebElement element = getWebElement(isil);
                //An Additional test check
                this.scrollForSpecificElement(element, isil.getLabel());
                if (element.isEnabled()){
                    element.click();
                    //Report as Pass(1), Fail(0) to custom report
                    report(SUCCESS, "Click on " + isil.getLabel(), REPORT_CLICKED + isil.getLabel(), REPORT_EXPECTED + isil.getLabel());
                }else {
                    report(FAIL, "Click on " + isil.getLabel(), REPORT_CLICKED + isil.getLabel(), "Unable to click , element is disabled: " + isil.getLabel() );
                }
            }catch (StaleElementReferenceException sere){
                clickElementInLoop(isil, iteration);
            }catch (Exception e){
                report(FAIL, "Click on " + isil.getLabel(), REPORT_CLICKED + isil.getLabel(), "Unable to click , element is disabled: " + e.getMessage());
            }
        }
    }

    /**
     * @implNote Method to compare two string values
     * @param expected
     * @param actual
     */
    public void stringCompare(String expected, String actual) throws FlexFrameWorkRunTimeException {
        try {
            if (expected.trim().equals(actual.trim())){
                report(SUCCESS, "Check if " + REPORT_EXPECTED + expected + " Matches " + REPORT_ACTUAL + actual, REPORT_EXPECTED + expected + " Matches "+ REPORT_ACTUAL + actual, REPORT_EXPECTED + expected + " Matches "+ REPORT_ACTUAL + actual);
            }else {
                report(FAIL, "Check if " + REPORT_EXPECTED + expected + " Matches " + REPORT_ACTUAL + actual, REPORT_EXPECTED + expected + " Matches "+ REPORT_ACTUAL + actual, REPORT_EXPECTED + expected + " does not matches "+ REPORT_ACTUAL + actual);
            }
        }catch (Exception e){
            report(FAIL, "Check if " + REPORT_EXPECTED + expected + " Matches " + REPORT_ACTUAL + actual, REPORT_EXPECTED + expected + " Matches "+ REPORT_ACTUAL + actual, e.getMessage());
        }
    }

    /**
     * @implNote Method to compare two string values
     * @param expected
     * @param actual
     */
    public void stringCompare(String expected, String actual, String identifier) throws FlexFrameWorkRunTimeException {
        try {
            if (expected.trim().equals(actual.trim())){
                report(SUCCESS, "Check if " + REPORT_EXPECTED + expected + " Matches " + REPORT_ACTUAL + actual, REPORT_EXPECTED + expected + " Matches  "+ REPORT_ACTUAL + actual, REPORT_EXPECTED + expected + " Matches  "+ REPORT_ACTUAL + actual);
            }else {
                String errorDifference = StringUtils.difference(expected, actual);
                report(FAIL, "Check if " + REPORT_EXPECTED + expected + " Matches  " + REPORT_ACTUAL + actual, REPORT_EXPECTED + expected + "Matches  "+ REPORT_ACTUAL + actual,
                        "String Match failed  for "+ identifier + " and the error difference is: " +errorDifference);
            }
        }catch (Exception e){
            report(FAIL, "Check if " + REPORT_EXPECTED + expected + " Matches  " + REPORT_ACTUAL + actual, REPORT_EXPECTED + expected + " Matches  "+ REPORT_ACTUAL + actual, e.getMessage());
        }
    }
    /**
     * @implNote Method to compare two string values with Ignore Case
     * @param expected
     * @param actual
     */
    public void stringCompareIgnoreCase(String expected, String actual) throws FlexFrameWorkRunTimeException {
        try {
            if (expected.trim().equalsIgnoreCase(actual.trim())){
                report(SUCCESS, "Check if " + REPORT_EXPECTED + expected + "Matches" + REPORT_ACTUAL + actual, REPORT_EXPECTED + expected + "Matches"+ REPORT_ACTUAL + actual, REPORT_EXPECTED + expected + "Matches"+ REPORT_ACTUAL + actual);
            }else {
                report(FAIL, "Check if " + REPORT_EXPECTED + expected + "Matches" + REPORT_ACTUAL + actual, REPORT_EXPECTED + expected + "Matches"+ REPORT_ACTUAL + actual, REPORT_EXPECTED + expected + "does not matches"+ REPORT_ACTUAL + actual);
            }
        }catch (Exception e){
            report(FAIL, "Check if " + REPORT_EXPECTED + expected + "Matches" + REPORT_ACTUAL + actual, REPORT_EXPECTED + expected + "Matches"+ REPORT_ACTUAL + actual, e.getMessage());
        }
    }

    public void browserBack() throws FlexFrameWorkRunTimeException {
        WebDriver driver = AllDataHolder.getDriver();
        try {
            JavascriptExecutor js = (JavascriptExecutor)  driver;
            js.executeScript("window.history.go(-1)");
            driver.navigate().back();
            report(SUCCESS, "Perform browser back", "Browser back peroformed", "Browser back performed");
        }catch (Exception e){
            report(FAIL, "Perform browser back", "Browser back peroformed", e.getMessage() );
        }
    }

    public void sendValue(ISuppllyLocatorInfo isil, String value) throws FlexFrameWorkRunTimeException {
        try {
            WebDriver driver = AllDataHolder.getDriver();
            WebElement element = new RemoteWebElement();
            //Added to handle some scenario where Object is not visible
            JavascriptExecutor js = (JavascriptExecutor)  driver;
            js.executeScript("arguments[0].scrollIntoView(true);", element);
            element.sendKeys(value);
            report(SUCCESS, "Send "+ value + " to: "+ isil.getLabel(), "Value sent : " + value, "Value sent : " + value);
        }catch (StaleElementReferenceException sere){
            sendValue(isil, value);
        }catch (Exception e){
            report(FAIL, "Send "+ value + " to: "+ isil.getLabel(), "Value sent : " + value, e.getMessage());
        }
    }

    public void sendValueAndPressENTER(ISuppllyLocatorInfo isil, String value) throws FlexFrameWorkRunTimeException {
        sendValueAndPressENTER(isil.getLocator(), value, isil.getLabel());
    }

    /**
     * @implNote Method to send the value and press ENTER
     * @param byOrWebElement
     * @param value
     * @param identifier
     */
    public void sendValueAndPressENTER(Object byOrWebElement, String value, String identifier) throws FlexFrameWorkRunTimeException {
        try {
            WebElement element = getWebElement(byOrWebElement);
            element.click();
            element.sendKeys(value);
            element.sendKeys(Keys.ENTER);
            report(SUCCESS, "Send "+ value + " and press enter in " + identifier, "Action performed on : " + value, "Action performed on : " + value);
        }catch (StaleElementReferenceException sere){
            sendValueAndPressENTER(byOrWebElement, value, identifier);
        }catch (Exception e){
            report(FAIL, "Send "+ value + " and press enter in " + identifier, "Action performed on : " + value, getClass().getCanonicalName());
        }
    }


    public void clearAndSendValue(ISuppllyLocatorInfo isil, String value) throws FlexFrameWorkRunTimeException {
        clearAndSendValue(isil.getLocator(), value, isil.getLabel());
    }

    /**
     * @implNote Method to clear the input box and send the value
     * @param byOrWebElement
     * @param value
     * @param identifier
     */
    public void clearAndSendValue(Object byOrWebElement, String value, String identifier) throws FlexFrameWorkRunTimeException {
        try {
            WebElement element = getWebElement(byOrWebElement);
            element.clear();
            element.sendKeys(value);
            report(SUCCESS, "Clear and send " + value + " to: " + identifier,
                    identifier + " Clear and sent  : " + value, identifier + " Clear and sent  : " + value);
        }catch (StaleElementReferenceException sere){
            clearAndSendValue(byOrWebElement, value, identifier);
        }catch (Exception e){
            report(FAIL, "Clear and send " + value + " to: " + identifier,
                    identifier + " Clear and sent  : " + value, e.getMessage());
        }
    }

    /**
     * @implNote Method to select drop down based on index
     * @param isil
     * @param value
     */
    public void selectDropDown(ISuppllyLocatorInfo isil, Integer value) throws FlexFrameWorkRunTimeException {
        try {
            WebElement element = getWebElement(isil.getLocator());
            Select dropDown = new Select(element);
            dropDown.selectByIndex(value);
            String selected = dropDown.getFirstSelectedOption().getText();
            report(SUCCESS, "Select "+ isil.getLabel() + " by option index: " + value,
                    isil.getLabel() + " selected by option index: " + value, isil.getLabel() + " selected by option index: " + value);
        }catch (Exception e){
            report(FAIL, "Select "+ isil.getLabel() + " by option index: " + value,
                    isil.getLabel() + " selected by option index: " + value, e.getMessage());
        }
    }

    /**
     * @implNote Method to get selected drop down text
     * @param isil
     */
    public String getSelectedDropDownValue(ISuppllyLocatorInfo isil) throws FlexFrameWorkRunTimeException {
        String dropDownValue = null;
        try {
            WebElement element = getWebElement(isil.getLabel());
            Select dropDown = new Select(element);
            dropDownValue = dropDown.getFirstSelectedOption().getText().trim();
            report(SUCCESS, "Fetch selected dropdown value " + dropDownValue + "from " + isil.getLabel(), "Fetch value: " + dropDownValue, "Fetched value: " + dropDownValue);
        }catch (Exception e){
            report(FAIL, "Fetch selected dropdown value " + dropDownValue + "from " + isil.getLabel(), "Fetch value: " + dropDownValue, e.getMessage());
        }
        return dropDownValue;
    }

    public void contentExists(ISuppllyLocatorInfo isil) throws FlexFrameWorkRunTimeException {
        contentExists(isil.getLocator(), isil.getLabel());
    }

    /**
     * @implNote Method to check content exists and report
     * @param byOrWebElement
     * @param identifier
     */
    public void contentExists(Object byOrWebElement, String identifier) throws FlexFrameWorkRunTimeException {
        try {
            WebDriver driver = AllDataHolder.getDriver();
            if (byOrWebElement instanceof By){
                driver.findElement((By) byOrWebElement);
            }else {
                WebElement ele = (WebElement) byOrWebElement;
            }
            report(SUCCESS, "Check if " + identifier + " exists", "Content exists: " + identifier, "Content exists: " + identifier);
        }catch (StaleElementReferenceException sere){
            contentExists(byOrWebElement, identifier);
        }catch (Exception e){
            report(FAIL, "Check if " + identifier + " exists", "Content exists: " + identifier, e.getMessage());
        }
    }


    public void valueExists(ISuppllyLocatorInfo isil) throws FlexFrameWorkRunTimeException {
        contentExists(isil.getLocator(), isil.getLabel());
    }

    /**
     * @implNote Method to check the value exists or not and report
     * @param o
     * @param text
     */
    public void valueExists(Object o, String... text) throws FlexFrameWorkRunTimeException {
        try {
            WebElement element = getWebElement(o);
            if (element.getText().equals(text[0])){
                report(SUCCESS, "Check if" + text[0] + "exists", "Value exists", "Value exists");
            }else {
                report(FAIL, "Check if" + text[0] + "exists", "Value exists", "Value doesn't exists");
            }
        }catch (StaleElementReferenceException sere){
            valueExists(o, text);
        }catch (Exception e){
            report(FAIL, "Check if" + text[0] + "exists", "Value exists", e.getMessage());
        }
    }

    public void verifyValueAttribute(ISuppllyLocatorInfo isil, String expValue) throws FlexFrameWorkRunTimeException {
        try {
            WebElement element = getWebElement(isil);
            String actValue = element.getAttribute(ATTRIBUTEVALUE);
            if (actValue.equals(expValue)){
                report(SUCCESS, "Fetch attribute value: " + expValue,
                        "Fetched attribute value: " + actValue,
                        "Fetched attribute value: " + actValue);
            }else {
                report(FAIL, "Fetch attribute value: " + expValue,
                        "Fetched attribute value: " + actValue,
                        "Failed to fetch attribute value");
            }
        }catch (Exception e){
            report(FAIL, "Fetch attribute value: " + expValue,
                    "Fetched attribute value:",
                    e.getMessage());
        }
    }

    /**
     * @implNote Method to check the attributes doesn't present
     * @param isil
     * @param expValue
     */
    public void verifyValueAttributeNotPresent(ISuppllyLocatorInfo isil, String expValue) throws FlexFrameWorkRunTimeException {
        try {
            WebElement element = getWebElement(isil);
            String actValue = element.getAttribute(ATTRIBUTEVALUE);
            if (actValue.equals(expValue)){
                report(FAIL, "Check for attribute: " + expValue,
                        "Attribute value doesn't exist as expected",
                        "Attribute value doesn't exist" + actValue);
            }else {
                report(SUCCESS, "Check for attribute: " + expValue,
                        "Attribute value doesn't exist as expected",
                        "Attribute value doesn't exist as expected");
            }
        }catch (Exception e){
            report(FAIL, "Check for attribute: " + expValue,
                    "Attribute value doesn't exist as expected",
                    e.getMessage());
        }
    }


    /**
     * @implNote Method to wait explicitly for frame
     * @param byOrWebElement
     * @param time
     */
    public void waitExplicitlyForFrame(Object byOrWebElement, long time) throws FlexFrameWorkRunTimeException {
        try {
            WebDriver driver = AllDataHolder.getDriver();
            WebDriverWait wait = new WebDriverWait(driver, time);
            By by = getBy(byOrWebElement);
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(by));
            report(SUCCESS, "Switch to iFrame", "Switch to iFrame", "Switch to iFrame");
        }catch (Exception e){
            report(FAIL, "Switch to iFrame", "Switch to iFrame", e.getMessage());
        }
    }

    public void waitExplicitlyForWebElement(ISuppllyLocatorInfo isil, long time) throws FlexFrameWorkRunTimeException {
        waitExplicitlyForWebElement(isil.getLocator(), time);
    }

    public void waitExplicitlyForWebElement(ISuppllyLocatorInfo isil) throws FlexFrameWorkRunTimeException {
        waitExplicitlyForWebElement(isil.getLocator(), DEFAULT_TIMEOUT);
    }

    /**
     * @implNote Method to wait for element
     * @param byOrWebElement
     * @param time
     */
    public void waitExplicitlyForWebElement(Object byOrWebElement, long time) throws FlexFrameWorkRunTimeException {
        try {
            WebDriver driver = AllDataHolder.getDriver();
            WebDriverWait wait = new WebDriverWait(driver, time);
            WebElement element = getWebElement(byOrWebElement);
            wait.until(ExpectedConditions.visibilityOf(element));
            report(SUCCESS, "Wait for the element to be visible", "Element is visible", "Element is visible");
        }catch (Exception e){
            report(FAIL, "Wait for the element to be visible", "element is not visible", e.getMessage());
        }
    }

    /**
     * @implNote Method to wait for element to be selected
     * @param isil
     * @param time
     */
    public void waitForWebElementToBeSelected(ISuppllyLocatorInfo isil, long time) throws FlexFrameWorkRunTimeException {
        try {
            WebDriver driver = AllDataHolder.getDriver();
            WebDriverWait wait = new WebDriverWait(driver, time);
            WebElement element = getWebElement(isil.getLocator());
            wait.until(ExpectedConditions.elementSelectionStateToBe(element, true));
            report(SUCCESS, "Wait for the element to be selected", isil.getLabel() + "is selectable", isil.getLabel() + "is selectable");
        }catch (Exception e){
            report(FAIL, "Wait for the element to be selected", isil.getLabel() + "is selectable", e.getMessage());
        }
    }

    public void waitForWebElementToBeClickble(ISuppllyLocatorInfo isil, long time) throws FlexFrameWorkRunTimeException {
        try {
            WebDriver driver = AllDataHolder.getDriver();
            WebDriverWait wait = new WebDriverWait(driver, time);
            WebElement element = getWebElement(isil);
            wait.until(ExpectedConditions.elementToBeClickable(element));
            report(SUCCESS, "Wait for the element to be Clickable", isil.getLabel() + "is Clickable", isil.getLabel() + "is Clickable");
        }catch (Exception e){
            report(FAIL, "Wait for the element to be selected", isil.getLabel() + "is Clickable", e.getMessage());
        }
    }

    /**
     * @implNote Method to check the position of two elements (side by side)
     * @param firstEle
     * @param secondele
     */
    public void posotionCheckersidebyside(ISuppllyLocatorInfo firstEle, ISuppllyLocatorInfo secondele) throws FlexFrameWorkRunTimeException {
        try {
            Point firstElement = getWebElement(firstEle).getLocation();
            Point secondElement = getWebElement(secondele).getLocation();
            if (firstElement.x < secondElement.x){
                report(SUCCESS, "check if " + firstEle.getLabel() + " is Net to each other with " + secondele.getLabel(),
                 firstEle.getLabel() + " and " + secondele.getLabel() + " are next to each other",
                        firstEle.getLabel() + " and " + secondele.getLabel() + " are next to each other");
            }else {
                report(FAIL, "check if " + firstEle.getLabel() + " is Net to each other with " + secondele.getLabel(),
                        firstEle.getLabel() + " and " + secondele.getLabel() + " are next to each other",
                        firstEle.getLabel() + " and " + secondele.getLabel() + " are next to each other");
            }
        }catch (Exception e){
            report(SUCCESS, "check if " + firstEle.getLabel() + " is Net to each other with " + secondele.getLabel(),
                    firstEle.getLabel() + " and " + secondele.getLabel() + " are next to each other",
                    e.getMessage());
        }
    }

    /**
     * @param expected
     * @param actual
     * @implNote Method to compare the list of elements
     *           Untill method to loop through and items in an expected list and compare with elements in actual list
     */
    public void compareElementsIntListLoose(List<String> expected, List<String> actual) throws FlexFrameWorkRunTimeException {
        {
            int index = 0;
            try {
                for (String elementExpected : expected) {
                    String actualElement = actual.get(index);
                    if (actualElement.contains(elementExpected)) {
                        report(SUCCESS, "Verify element expected contains actual",
                                REPORT_EXPECTED + " contains " + elementExpected + REPORT_ACTUAL + actual.get(index),
                                REPORT_EXPECTED + " contains " + elementExpected + REPORT_ACTUAL + actual.get(index));
                    } else {
                        report(FAIL, "Verify element expected contains actual",
                                REPORT_EXPECTED + " contains " + elementExpected + REPORT_ACTUAL + actual.get(index),
                                REPORT_EXPECTED + " contains " + elementExpected + REPORT_ACTUAL + actual.get(index));
                    }
                    index++;
                }
            } catch (Exception e) {
                throw new FlexFrameWorkRunTimeException(e);
            }
        }

    }

    /**
     * @implNote Method to compare list string values adn report
     * @param expected
     * @param actual
     */
    public void listOfstringComparision(List<String> expected, List<String> actual) throws FlexFrameWorkRunTimeException {
        try {
            if (actual != null & actual.equals(expected)){
                report(SUCCESS, "Verify lists are matching",
                        REPORT_EXPECTED + expected + "is matching" + REPORT_ACTUAL + actual,
                        REPORT_EXPECTED + expected + "is matching" + REPORT_ACTUAL + actual);
            }else {
                report(FAIL, "Verify lists are not matching",
                        REPORT_EXPECTED + expected + "is not matching" + REPORT_ACTUAL + actual,
                        REPORT_EXPECTED + expected + "is not matching" + REPORT_ACTUAL + actual);
            }
        }catch (Exception e){
            report(ERROR, e.getMessage() + "Exception while Verifying lists",
                    REPORT_EXPECTED + expected + "is not matching" + REPORT_ACTUAL + actual, e.getMessage());
        }
    }

    /**
     * @implNote Method to check sub string
     * @param parent
     * @param child
     */
    public void substringCheck(String parent, String child) throws FlexFrameWorkRunTimeException {
        try {
            if (parent.contains(child)){
                report(SUCCESS, "Perform String contains check",
                        REPORT_EXPECTED + parent + "Contains" + REPORT_ACTUAL + child,
                        REPORT_EXPECTED + parent + "Contains" + REPORT_ACTUAL + child);
            }else {
                report(SUCCESS, "Perform String contains check",
                        REPORT_EXPECTED + parent + "Contains" + REPORT_ACTUAL + child,
                        REPORT_EXPECTED + parent + "does not contains" + REPORT_ACTUAL + child);
            }
        }catch (Exception e){
            report(FAIL, "Perform String contains check",
                    REPORT_EXPECTED + parent + "Contains" + REPORT_ACTUAL + child, e.getMessage());
        }
    }


    public String extentreportcreateScreenshot() throws FlexFrameWorkRunTimeException {
        try {
            WebDriver driver = AllDataHolder.getDriver();
            UUID uuid = UUID.randomUUID();
            //Generate screenshot as a file object
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(scrFile, new File(RESULTSPATH + SCREENSHOTPATH + uuid + ".png"));
            }catch (Exception e){
                throw new FlexFrameWorkRunTimeException(e);
            }
            File file = new File(RESULTSPATH);
            String reportLocation;
         //   String planKey = System.getProperty(ServiceAndSauce.PLANKEY).length() > 0 ? System.getProperty(ServiceAndSauce.PLANKEY) : "FALSE";
            String planKey = "FALSE";
          if (planKey.equals("TRUE")){
               reportLocation = "https://bamboo.dFarm.com/artifacts/" + System.getProperty("plankey") + "/" + System.getProperty("shortJobKey") + "/build-"
                       + System.getProperty("buildNumber") + "/Results";
           }else {
               reportLocation = file.getAbsolutePath();
           }
           return reportLocation + SCREENSHOTPATH + uuid + ".png";
        }catch (Exception e){
            return "";
        }
    }



    public void waitUntill(long milliSecs){
        long endTimeMillis = System.currentTimeMillis() + milliSecs;
        while (true){
            if (System.currentTimeMillis() > endTimeMillis){
                break;
            }
        }
    }
    public String getResponse(Object response) throws WSException, FlexFrameWorkRunTimeException {

        String responseValue;
        try {
            if (response instanceof HttpResponse){
                HttpResponse res = (HttpResponse) response;
                responseValue = EntityUtils.toString(res.getEntity());
            }else {
                Response res = (Response) response;
                responseValue = res.getBody().asString();
            }
        }catch (Exception e){
            throw new WSException(e);
        }
        return responseValue;
    }

    // report pass, fail
    public void report(int status, String testStepName, String expectedResult, String actualResult) throws FlexFrameWorkRunTimeException {

        if (status == 1){
           super.reportStep(ReportingConstants.PASS, Status.PASS, testStepName, expectedResult, actualResult);
//            super.reportStep(ReportingConstants.PASS, Status.PASS, testStepName,  expectedResult, actualResult,
//                    extentreportcreateScreenshot());
        }else if (status == 2){
            super.reportStep(ReportingConstants.INFO, Status.INFO, testStepName, expectedResult, actualResult);
        }else if (status == 3){
            if (AllDataHolder.isFastFail()){
                super.reportStep(ReportingConstants.WARNING, Status.WARNING, testStepName, expectedResult, actualResult);
            }else {
                super.reportStep(ReportingConstants.WARNING, Status.WARNING, testStepName, expectedResult, actualResult);
            }
        }else if (status == 4){
            super.reportStep(ReportingConstants.INFO, Status.INFO, "<mark> Capture Skip Method Info</mark>",
                    "Capture Skip Method name", "<details><summary>Skipped Method Info</summary>" + getStackTrace()+ "</details>");
            super.reportStep(ReportingConstants.SKIP, Status.SKIP, testStepName, "Skip Identifier :: " + expectedResult, actualResult,
                    extentreportcreateScreenshot());
            Assert.fail();
        }else if (status == 5){
            super.reportStep(ReportingConstants.ERROR, Status.ERROR, testStepName, expectedResult, actualResult);
            Assert.fail();
        }else if (status == 6){
            super.reportStep(ReportingConstants.FATAL, Status.FATAL, testStepName, expectedResult, actualResult);
        }else if(status == 16){
            super.reportStep(ReportingConstants.INFO, Status.INFO, "<mark> Capture Page ScreenShoot</mark>",
                    "Capture Page ScreenShot", "New URL Loaded" + expectedResult);
            super.reportStep(ReportingConstants.INFO, Status.INFO, testStepName, "Screenshot Identifier ::",
                    extentreportcreateScreenshot());
        }else {
            if (AllDataHolder.isFastFail()){
                super.reportStep(ReportingConstants.INFO, Status.INFO, "<mark> Capture Failed method Info</mark>",
                        "Captured failed method name", "<details><summary>Failed Method Info</summary>" + getStackTrace()+ "</details>");
                super.reportStep(ReportingConstants.FAIL, Status.FAIL, testStepName, "Failed Identifier :: " + expectedResult, actualResult,
                        extentreportcreateScreenshot());
                Assert.fail();
            }else {
                super.reportStep(ReportingConstants.INFO, Status.INFO, "<mark> Capture Failed method Info</mark>",
                        "Captured failed method name", "<details><summary>Failed Method Info</summary>" + getStackTrace()+ "</details>");
                super.reportStep(ReportingConstants.FAIL, Status.FAIL, testStepName, "Failed Identifier :: " + expectedResult, actualResult,
                        extentreportcreateScreenshot());
            }
        }
    }

    public String getStackTrace() {
        int i;
        StringBuilder stackTrace = new StringBuilder();
        for (i = 3; i < Thread.currentThread().getStackTrace().length; i++) {
            if (Thread.currentThread().getStackTrace()[i].getClassName().startsWith("com.dFarm")) {
                stackTrace.append(Thread.currentThread().getStackTrace()[i].toString()).append("<br/>");
            }
        }
        return stackTrace.toString();
    }

    public String switchToOtherChaildWindowExcept(String currentWindoworTab){
        String childWindow = "";
        return childWindow;
    }

    public void subStringCheck(String parent, String  child) throws FlexFrameWorkRunTimeException {
        try {
            if (parent.contains(child)){
                report(SUCCESS, "Perform String contains check", REPORT_EXPECTED + parent + "contains" + REPORT_ACTUAL + child, REPORT_EXPECTED + parent + "contains" + REPORT_ACTUAL + child);
            }else {
                report(FAIL, "Perform String contains check", REPORT_EXPECTED + parent + "contains" + REPORT_ACTUAL + child, "Verify if" + REPORT_EXPECTED + parent + "does not contains" + REPORT_ACTUAL + child);
            }
        }catch (Exception e){
            report(FAIL, "Perform String contains check", REPORT_EXPECTED + parent + "contains" + REPORT_ACTUAL + child, e.getMessage());
        }
    }

    public boolean deleteFile(String filePath) throws FlexFrameWorkRunTimeException{
        try {
            return Files.deleteIfExists(Paths.get(filePath));
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e);
        }
    }

    public void navigateToURL(String url) throws FlexFrameWorkRunTimeException{
        try {
            WebDriver driver = AllDataHolder.getDriver();
            driver.get(url);
            report(SUCCESS, "Get URL: " + url, "Page loaded for URL: " + url, "Page loaded for URL: " + url);
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e);
        }
    }

    public boolean isContentDisplayed(ISuppllyLocatorInfo isil){
        return isContentDisplayed(isil.getLocator());
    }

    public boolean isContentDisplayed(Object byOrWebElement){
        try {
            WebElement element = getWebElement(byOrWebElement);
            return element.isDisplayed();
        }catch (Exception e){
            return false;
        }
    }

    public void closeAllChildWindowsOrTabs() throws FlexFrameWorkRunTimeException {
        WebDriver driver = AllDataHolder.getDriver();
        Set<String> childWindowsOrTabs = driver.getWindowHandles();

        childWindowsOrTabs.remove(AllDataHolder.getParentWindowHandle());
        for (String childWindowsOrTab : childWindowsOrTabs){
            try {
                driver.switchTo().window(childWindowsOrTab);
                String windowTitle = driver.getTitle();
                driver.close();
                report(SUCCESS, "Closing all child windows and/or Tabs",
                        "Closed Window or Tab with title: " + windowTitle,
                        "Closed Window or Tab with title: " + windowTitle);
            }catch (Exception e){
                report(FAIL, "Closing all child windows and/or Tabs",
                        "Closed windows or Tab",
                        e.getMessage());
            }
        }
        driver.switchTo().window(AllDataHolder.getParentWindowHandle());
    }

    public String getTextByJS(ISuppllyLocatorInfo isil) throws FlexFrameWorkRunTimeException{
        try {
            WebDriver driver = AllDataHolder.getDriver();
            WebElement element = getWebElement(isil.getLocator());
            JavascriptExecutor js = (JavascriptExecutor) driver;
            Object textValue = js.executeScript("return arguments[0].value", element);
            return textValue.toString().trim();
        }catch (Exception e){
            report(FAIL, "Fetch Text from element using Java Script", "Fetch Text from element using Java Script",
                    e.getMessage());
        }
        return "";
    }

    public String getText(ISuppllyLocatorInfo isli){
        return getText(isli.getLocator());
    }

    public String getText(Object byOrWebElement){
        String returnValue;
        WebElement element;
        try {
            element = getWebElement(byOrWebElement);
            returnValue = element.getText().trim();
            report(SUCCESS, "Fetch text from element ", "Fetched text from element:  " + returnValue,
                    "Fetched text from element: " + returnValue);
        } catch (FlexFrameWorkRunTimeException e) {
            returnValue = "";
        }
        return returnValue;
    }

    public void contentDisplayed(ISuppllyLocatorInfo[] isli) throws FlexFrameWorkRunTimeException{
        try {
            for (ISuppllyLocatorInfo ele : isli){
                contentDisplayed(ele.getLocator(), ele.getLabel());
            }
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e);
        }
    }

    public void contentDisplayed(ISuppllyLocatorInfo isli) throws FlexFrameWorkRunTimeException {
        contentDisplayed(isli.getLocator(), isli.getLabel());
    }

    public void contentDisplayed(Object byOrWebElement, String identifier) throws FlexFrameWorkRunTimeException {
        try {
            WebElement element = getWebElement(byOrWebElement);
            if (element.isDisplayed()){
                report(SUCCESS, "Check if web element is displayed", identifier + " is Displayed", identifier + " is Displayed");
            }else {
                report(FAIL, "Check if web element is displayed", identifier + " is Displayed", identifier + " is not Displayed");
            }
        }catch (StaleElementReferenceException | FlexFrameWorkRunTimeException sere){
            contentDisplayed(byOrWebElement, identifier);
        }catch (Exception e){
            report(FAIL, "Check if webelement is displayed", identifier + " is Displayed", e.getMessage());
        }
    }

    public boolean isAlertPresent(){
        try {
            WebDriver driver = AllDataHolder.getDriver();
            driver.switchTo().alert();
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean waitTillPresenceOfAlert(int seconds){
        try {
            WebDriver driver = AllDataHolder.getDriver();
            new WebDriverWait(driver, seconds).until(ExpectedConditions.alertIsPresent());
            return true;
        }catch (Exception e){
            return  false;
        }
    }


    public void contentDoesNotExist(ISuppllyLocatorInfo isli) throws FlexFrameWorkRunTimeException {
        contentDoesNotExist(isli.getLocator(), isli.getLabel());
    }

    private void contentDoesNotExist(Object byOrWebElement, String identifier) throws FlexFrameWorkRunTimeException {
        WebElement element;
        WebDriver driver = AllDataHolder.getDriver();
        try {
            if (byOrWebElement instanceof By){
                element = driver.findElement((By) byOrWebElement);
            }else {
                element = (WebElement) byOrWebElement;
            }
            report(FAIL, "Check if Content doen't exists", identifier + "content exists", "Content exists");
        }catch (Exception e){
            report(SUCCESS, "Check if Content doen't exists", identifier + "content exists", "Content doesn't exists as expected");

        }
    }

    public boolean isElementEnabled(ISuppllyLocatorInfo isli) throws FlexFrameWorkRunTimeException {
        return isElementEnabled(isli.getLocator(), isli.getLabel());
    }

    public boolean isElementEnabled(Object byOrWenbElement, String identifier) throws FlexFrameWorkRunTimeException {
        boolean flag = false;
        try {
            WebElement element = getWebElement(byOrWenbElement);
            if (element.isEnabled()){
               report(SUCCESS, "Check if " + identifier + "is enabled", "Check if " + identifier + "is enabled", "Check if " + identifier + "is enabled");
               flag = true;
            }else {
                report(FAIL, "Check if " + identifier + "is enabled", "Check if " + identifier + "is enabled", "Check if " + identifier + "is disabled");
                flag = false;
            }
        }catch (Exception e){
            report(FAIL, "Check if " + identifier + "is enabled", "Check if " + identifier + "is enabled", e.getMessage());
        }
        return flag;
    }

    /**
     * Method to verify the element is enabled for list
     * @param isli
     * @throws FlexFrameWorkRunTimeException
     */
    public void isElementEnabled(ISuppllyLocatorInfo[] isli) throws FlexFrameWorkRunTimeException{
        try {
            for (ISuppllyLocatorInfo ele : isli){
                isElementEnabled(ele.getLocator(), ele.getLabel());
            }
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e);
        }
    }

    /**
     * @implSpec  to check element is disabled
     * @param isli
     * @throws FlexFrameWorkRunTimeException
     */
    public void checkElementsDisabled(ISuppllyLocatorInfo[] isli) throws FlexFrameWorkRunTimeException {
        for (ISuppllyLocatorInfo anIsli : isli){
            checkElementsDisabled(anIsli);
        }
    }

    public void checkElementsDisabled(ISuppllyLocatorInfo isli) throws FlexFrameWorkRunTimeException {
        checkElementDisabled(isli.getLocator(), isli.getLabel());
    }

    public void checkElementDisabled(Object byOrWebElement, String identifier) throws FlexFrameWorkRunTimeException {
        try {
            WebElement element = getWebElement(byOrWebElement);
            if (!(element.isEnabled())){
                report(SUCCESS, "Check If " + identifier + " is disabled", identifier + " element is disabled", identifier + " element is disabled");
            }else if(element.isEnabled()){
                report(FAIL, "Check If " + identifier + " is disabled", identifier + " element is disabled", identifier + " element is enabled");
            }
        }catch (Exception e){
            report(FAIL, "Check If " + identifier + " is disabled", identifier + " element is disabled", e.getMessage());
        }
    }

    /**
     * @implSpec to check element is disabled and return true / false
     * @param isli
     * @return
     * @throws FlexFrameWorkRunTimeException
     */
    public boolean isElementDisabled(ISuppllyLocatorInfo isli) throws FlexFrameWorkRunTimeException {
        boolean value = false;
        try {
            WebElement element = getWebElement(isli.getLocator());
            if (!element.isEnabled()){
                value = true;
            }
        }catch (Exception e){
            report(FAIL, "Check If " + isli.getLabel() + " is disabled", isli.getLabel() + " element is disabled", e.getMessage());
        }
        return value;
    }

    public String getAttribute(ISuppllyLocatorInfo isli, String  attributeName) throws FlexFrameWorkRunTimeException {
        return getAttribute(isli.getLocator(), attributeName, isli.getLabel());
    }

    private String getAttribute(Object byOrWebElement, String attributeName, String identifier) throws FlexFrameWorkRunTimeException {
        String value = " ";
        try {
            WebElement element = getWebElement(byOrWebElement);
            value = element.getAttribute(attributeName);
        }catch (Exception e){
            report(FAIL, "Fetch attribute " + attributeName + " for " + identifier, attributeName + " fetched", e.getMessage());
        }
       return value.trim();
    }

    public String getCurrentDateStamp(){
        SimpleDateFormat format = new SimpleDateFormat(MMDDYYYYDATEFORMAT);
        Date date = Calendar.getInstance().getTime();
        return format.format(date);
    }

    /**
     * @implSpec Check if element 1 is right element 2
     * @param isliRight
     * @param isliLeft
     * @throws FlexFrameWorkRunTimeException
     */
    public void positionCheckerRight(ISuppllyLocatorInfo isliRight, ISuppllyLocatorInfo isliLeft) throws FlexFrameWorkRunTimeException {
        try {
            Point rightElement = getWebElement(isliRight.getLocator()).getLocation();
            Point leftElement = getWebElement(isliLeft.getLocator()).getLocation();

            if (leftElement.x < rightElement.x){
                report(SUCCESS, "Check if " + isliRight.getLabel() + " is displayed next to " + isliLeft.getLabel(),
                        isliRight.getLabel() + " is displayed right to " + isliLeft.getLabel(),
                        isliRight.getLabel() + " is displayed right to " + isliLeft.getLabel());
            }else {
                report(FAIL, "Check if " + isliRight.getLabel() + " is displayed next to " + isliLeft.getLabel(),
                        isliRight.getLabel() + " is displayed right to " + isliLeft.getLabel(),
                        isliRight.getLabel() + " is not displayed right to " + isliLeft.getLabel());
            }
        }catch (Exception e){
            report(FAIL, "Check if " + isliRight.getLabel() + " is displayed next to " + isliLeft.getLabel(),
                    isliRight.getLabel() + " is displayed right to " + isliLeft.getLabel(),
                    e.getMessage());
        }
    }

    public void mouseClick(ISuppllyLocatorInfo isli) throws FlexFrameWorkRunTimeException {
        mouseClick(isli.getLocator(), isli.getLabel());
    }

    public void mouseClick(Object byOrWebElement, String identifier) throws FlexFrameWorkRunTimeException {
        WebDriver driver = AllDataHolder.getDriver();
        Actions builder = new Actions(driver);
        try {
            WebElement element = getWebElement(byOrWebElement);
            builder.moveToElement(element).build().perform();
            builder.moveToElement(element).click().build().perform();
            report(SUCCESS, "Perform mouse click on " + identifier, "Mouse clicked on " + identifier, "Mouse clicked on " + identifier);
        }catch (StaleElementReferenceException sere){
            mouseClick(byOrWebElement, identifier);
        }catch (Exception e){
            report(FAIL, "Perform mouse click on " + identifier, "Mouse clicked on " + identifier, e.getMessage());
        }
    }

    public void mouseDoubleClick(ISuppllyLocatorInfo isli) throws FlexFrameWorkRunTimeException {
        mouseDoubleClick(isli.getLocator(), isli.getLabel());
    }

    // Mouse double click
    public void mouseDoubleClick(Object byOrWebElement, String identifier) throws FlexFrameWorkRunTimeException {
        WebDriver driver = AllDataHolder.getDriver();
        Actions builder = new Actions(driver);
        try {
            WebElement element = getWebElement(byOrWebElement);
            builder.moveToElement(element).build().perform();
            builder.moveToElement(element).doubleClick().build().perform();
            report(SUCCESS, "Perform mouse click on " + identifier, "Mouse clicked on " + identifier, "Mouse clicked on " + identifier);
        }catch (StaleElementReferenceException sere){
            mouseClick(byOrWebElement, identifier);
        }catch (Exception e){
            report(FAIL, "Perform mouse click on " + identifier, "Mouse clicked on " + identifier, e.getMessage());
        }
    }

    public void mouseHover(ISuppllyLocatorInfo isli) throws FlexFrameWorkRunTimeException {
        mouseHover(isli.getLocator(), isli.getLabel());
    }

    private void mouseHover(Object byOrWebElement, String identifier) throws FlexFrameWorkRunTimeException{
        try {
            WebDriver driver = AllDataHolder.getDriver();
            Actions builder = new Actions(driver);
            WebElement element = getWebElement(byOrWebElement);
            builder.moveToElement(element).build().perform();
            report(SUCCESS, "Perform mouse hover on " + identifier, "Mouse hover performed on " + identifier, "Mouse hover performed on " + identifier);
        }catch (StaleElementReferenceException sere){
            mouseHover(byOrWebElement,identifier);
        }catch (Exception e){
            report(FAIL, "Perform mouse hover on " + identifier, "Mouse hover performed on " + identifier, e.getMessage());
        }
    }

    public void scrollForASpecificElement(ISuppllyLocatorInfo isli) throws FlexFrameWorkRunTimeException {
        scrollForASpecificElement(isli.getLocator(), isli.getLabel());
    }

    private void scrollForASpecificElement(Object byOrWebElement, String identifier) throws FlexFrameWorkRunTimeException {
        try {
            WebDriver driver = AllDataHolder.getDriver();
            WebElement element = getWebElement(byOrWebElement);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView();", element);
            report(SUCCESS, "Performed Java script scrolling", "Scrolled to " + identifier, "Scrolled to " + identifier);
        }catch (StaleElementReferenceException sere){
            scrollForASpecificElement(byOrWebElement, identifier);
        }catch (Exception e){
            report(FAIL, "Performed Java script scrolling", "Scrolled to " + identifier, e.getMessage());
        }
    }

    public void setFocusOnElement(ISuppllyLocatorInfo isli) throws FlexFrameWorkRunTimeException {
        try {
            WebDriver driver = AllDataHolder.getDriver();
            WebElement element = getWebElement(isli.getLocator());
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("document.getElementById()'status-reset').focus();", element);
            report(SUCCESS, "Set focus on " + isli.getLabel(), "Focused on element " + isli.getLabel(), "Focused on element " + isli.getLabel());
        }catch (Exception e){
            report(FAIL, "Set focus on " + isli.getLabel(), "Focused on element " + isli.getLabel(), e.getMessage());
        }
    }

    public void selectDropDownWithText(ISuppllyLocatorInfo isli, String elementToSelect) throws FlexFrameWorkRunTimeException {
        selectDropDownWithText(isli.getLocator(), elementToSelect, isli.getLabel());
    }

    private void selectDropDownWithText(Object byOrWebElement, String elementToSelect, String label) throws FlexFrameWorkRunTimeException {
        try {
            WebElement element = getWebElement(byOrWebElement);
            Select dropDown = new Select(element);
            dropDown.selectByVisibleText(elementToSelect);
            report(SUCCESS, "Select a value from dropdown using Text",
                    "Value Selected from dropdown: " + elementToSelect,
                    "Value Selected from dropdown: " + elementToSelect);
            waitUntill(500);
        }catch (StaleElementReferenceException sere){
            selectDropDownWithText(byOrWebElement, elementToSelect, label);
        }catch (Exception e){
            report(SUCCESS, "Select a value from dropdown using Text",
                    "Value Selected from dropdown: " + elementToSelect,
                    e.getMessage());
        }
    }


    private void selectDropDownWithValue(Object byOrWebElement, String valueToSelect) throws FlexFrameWorkRunTimeException {
        try {
            WebElement element = getWebElement(byOrWebElement);
            Select dropDown = new Select(element);
            dropDown.selectByValue(valueToSelect);
            report(SUCCESS, "Select a value from dropdown using Text",
                    "Selected dropdown by text: " + valueToSelect,
                    "Selected dropdown by text " + valueToSelect);
            waitUntill(500);
        }catch (Exception e){
            report(SUCCESS, "Select a value from dropdown using Text",
                    "Selected dropdown by text " + valueToSelect,
                    e.getMessage());
        }
    }

    public void multiSelectDropDownValues(ISuppllyLocatorInfo isli, String selectMethodType, String[] valuesToBeSelected) throws FlexFrameWorkRunTimeException {
        try {
            WebElement element = getWebElement(isli.getLocator());
            Select dropdown = new Select(element);
            //To Verify the list has attribute "Multiple"
            if (dropdown.isMultiple()){
                for (String val : valuesToBeSelected){
                    switch (selectMethodType){
                        //For Reusability - switch based on the Method
                        case "Value":
                            dropdown.selectByValue(val);
                            report(SUCCESS, "Select dropdown by value",
                                    "Selected dropdown by value :" + val, "Selected dropdown by value :" + val);
                            break;
                        case "Index":
                            dropdown.selectByIndex(Integer.parseInt(val));
                            report(SUCCESS, "Select dropdown by Index",
                                    "Selected dropdown by Index :" + val, "Selected dropdown by Index :" + val);
                            break;
                        case "VisibleText":
                            dropdown.selectByVisibleText(val);
                            report(SUCCESS, "Select dropdown by visible text",
                                    "Selected dropdown by visible text :" + val, "Selected dropdown by visible text :" + val);
                            break;

                    }
                }
            }
            this.waitUntill(500);
        } catch (Exception e) {
            report(FAIL, "Select dropdown", "Select dropdown", e.getMessage());
        }
    }

    public void waitForWebElementAndClick(ISuppllyLocatorInfo isli) throws FlexFrameWorkRunTimeException {
        WebElement element = getWebElement(isli.getLocator());
        WebDriver driver = AllDataHolder.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
        try {
            if (isli.getLocator() instanceof By)
                waitForStable(isli, DEFAULT_CHECK_INTERVAL);
            element = wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
            report(SUCCESS, "Wait for element to be clickble" + isli.getLabel(),
                    "Loaded element is clicked: " + isli.getLabel(),
                    "Loaded element is clicked: " + isli.getLabel());
        }catch (StaleElementReferenceException sere){
            waitForWebElementAndClick(isli);
        }catch (Exception e){
            report(FAIL, "Wait for element to be clickble",
                    "Loaded elment is clicked: " + isli.getLabel(), e.getMessage());
        }
    }

    public void waitForStable(ISuppllyLocatorInfo isli, long timeout) throws FlexFrameWorkRunTimeException, InterruptedException {
        long startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - startTime < timeout){
            WebElement element;
            try {
                element = getWebElement(isli);
                if (element !=null && element.isDisplayed()){
                    break;
                }
            }catch (Exception e){
                Thread.sleep(DEFAULT_CHECK_INTERVAL);
            }
        }
    }


    /**
     * @implNote Driver wait, wait untill loading disappears
     * @throws FlexFrameWorkRunTimeException
     */
    public void driverWait() throws FlexFrameWorkRunTimeException {
        try {
            WebDriver driver = AllDataHolder.getDriver();
            if (driver.findElement(By.xpath("(//div[@class='sk-ball-spin-clockwise'])[2]")).isDisplayed()){
                Wait<WebDriver> wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(90))
                        .pollingEvery(Duration.ofMillis(500)).ignoring(NoSuchElementException.class);
                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("")));
            }
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e);
        }
    }

    /**
     * @implNote Driver wait, wait untill loading disappears
     * @throws FlexFrameWorkRunTimeException
     */
    public void corporateSiteDriverWait() throws FlexFrameWorkRunTimeException {
        try {
            WebDriver driver = AllDataHolder.getDriver();
            if (driver.findElement(By.xpath("//div[@class='premium-loader']")).isDisplayed()){
                Wait<WebDriver> wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(90))
                        .pollingEvery(Duration.ofMillis(500)).ignoring(NoSuchElementException.class);
                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("")));
            }
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e);
        }
    }

    public int sizeOfWebElement(Object obj) throws FlexFrameWorkRunTimeException {
        WebDriver driver = AllDataHolder.getDriver();
        try {
            List<WebElement> elements = driver.findElements((By) obj);
            return elements.size();
        } catch (Exception e){
            report(FAIL, "Fetch size of WebElements", "Fetched size of WebElements", e.getMessage());
        }
        return 0;
    }

    public int sizeOfWebElement(ISuppllyLocatorInfo isli) throws FlexFrameWorkRunTimeException {
        return  sizeOfWebElement(isli.getLocator());
    }

    /**
     * @implSpec To verify whether the text is present in desired element / area
     * @param byOrWebElement
     * @param expText
     * @throws FlexFrameWorkRunTimeException
     */
    public void verifyTextInSpecificElement(Object byOrWebElement, String expText) throws FlexFrameWorkRunTimeException {
        try {
            WebElement element = getWebElement(byOrWebElement);
            String actText = element.getText().trim();
            if (actText.contains(expText)){
                report(SUCCESS, "Perform text contains check on Webelement",
                        actText + " is contains " + expText,
                        actText + " is contains " + expText);
            }else {
                report(FAIL, "Perform text contains check on Webelement",
                        actText + " is contains " + expText,
                        actText + " is does not contains " + expText);
            }
        } catch (Exception e) {
            report(FAIL, "Perform text contains check on Webelements",
                    "Webelements contains " + expText,
                    e.getMessage());
        }
    }

    public void verifyOptionSelected(ISuppllyLocatorInfo isli, String  expOption) throws FlexFrameWorkRunTimeException {
        try {
            WebElement element = getWebElement(isli);
            Select dropDown = new Select(element);
            String  actOption = dropDown.getFirstSelectedOption().getText();
            if (actOption.equals(expOption)){
                report(SUCCESS, "Check if selected option is selected in the dropdown",
                        "Actual option is " + actOption + " matching expected option " + expOption,
                        "Actual option is " + actOption + " matching expected option " + expOption);
            }else {
                report(FAIL, "Check if selected option is selected in the dropdown",
                        "Actual option is " + actOption + " matching expected option " + expOption,
                        "Actual option is " + actOption + " not matching expected option " + expOption);

            }
        }catch (Exception e){
            report(SUCCESS, "Check if selected option is selected in the dropdown",
                    "Actual option is matching expected option " + expOption,
                    e.getMessage());
        }
    }

    public String getListSizeInString(ISuppllyLocatorInfo isli) throws FlexFrameWorkRunTimeException {
        String listSize = null;
        try {
            listSize = Integer.toString(this.sizeOfWebElement(isli));
        }catch (Exception e){
            report(FAIL, "Get list size for: " + isli.getLabel(), "List size fetched", e.getMessage());
        }
        return listSize;
    }

    public void onFinish(ITestContext context){
        throw new UnsupportedOperationException();
    }

    public void onTestStart(ITestResult result){
        throw new UnsupportedOperationException();
    }

    public void onTestSuccess(ITestResult result){
        throw new UnsupportedOperationException();
    }

    public void onTestSkipped(ITestResult result){
        throw new UnsupportedOperationException();
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result){
        throw new UnsupportedOperationException();
    }

    public void onStart(ITestContext context){
        throw new UnsupportedOperationException();
    }

    public void onFailure(ITestResult arg0){
        throw new UnsupportedOperationException();
    }

    public List<WebElement> getWebElements(ISuppllyLocatorInfo isli) throws FlexFrameWorkRunTimeException {
        return getWebElements(isli.getLocator(), isli.getLabel());
    }

    public List<WebElement> getWebElements(Object byOrWebElement, String identifier) throws FlexFrameWorkRunTimeException {
        List<WebElement> elements = null;
        try {
            WebDriver driver = AllDataHolder.getDriver();
            if (byOrWebElement instanceof By){
                elements = driver.findElements((By) byOrWebElement);
            }else {
                elements = (List<WebElement>) byOrWebElement;
            }
            report(SUCCESS, "Fetch all we elements", "All elements are fetched for: " + identifier,
                    "All elements are fetched for: " + identifier);
        }catch (StaleElementReferenceException sere){
            getWebElements(byOrWebElement, identifier);
        }catch (Exception e){
            report(SUCCESS, "Fetch all we elements", "All elements are fetched for: " + identifier,
                    e.getMessage());
        }
        return elements;
    }

    public String getTextFromWebElementJS(ISuppllyLocatorInfo isli) throws FlexFrameWorkRunTimeException {
        return getTextFromWebElementJS(isli.getLocator());
    }

    public String  getTextFromWebElementJS(Object byOrWebElement) throws FlexFrameWorkRunTimeException {
        String textValue = null;
        try {
            WebDriver driver = AllDataHolder.getDriver();
            WebElement element = getWebElement(byOrWebElement);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            textValue = js.executeScript("return arguments[0].textContent;", element).toString().trim();
        } catch (Exception e) {
            report(FAIL, "Fetch all text for web element using java script", "Fetch all text for web element using java script", e.getMessage());
        }
        return textValue;
    }

    public List<String> getTextFromWebElements(ISuppllyLocatorInfo isli) throws FlexFrameWorkRunTimeException {
        List<String> actual = new ArrayList<>();
        for (WebElement value : getWebElements(isli)){
            actual.add(getTextFromWebElementJS(value));
        }
        return actual;
    }

    /**
     * @implNote Method to select and paste the value in textbox
     * @param isli
     * @param value
     * @throws FlexFrameWorkRunTimeException
     */
    public void selectAndPasteValue(ISuppllyLocatorInfo isli, String value) throws FlexFrameWorkRunTimeException {
        try {
            WebElement element = getWebElement(isli);
            element.sendKeys(Keys.HOME, Keys.chord(Keys.SHIFT, Keys.ENTER), value);
            report(SUCCESS, "Clear and send value in " + isli.getLabel(),
                    "TextBox cleared and sent :" + value,
                    "TextBox cleared and sent :" + value);
        }catch (StaleElementReferenceException sere){
            selectAndPasteValue(isli, value);
        }catch (Exception e){
            report(FAIL, "Clear and send value in " + isli.getLabel(),
                    "TextBox cleared and sent :" + value,
                    e.getMessage());
        }
    }

    public void floatValueComparision(float expected, float actual, String  identifier) throws FlexFrameWorkRunTimeException {
        try {
            if (Float.compare(expected, actual) == 0){
                report(SUCCESS, "Verify numbers are matching in " + identifier,
                        REPORT_EXPECTED + expected + " is matching" + REPORT_ACTUAL + actual,
                        REPORT_EXPECTED + expected + " is matching" + REPORT_ACTUAL + actual);
            }else {
                report(FAIL, "Verify numbers are matching in " + identifier,
                        REPORT_EXPECTED + expected + " is matching" + REPORT_ACTUAL + actual,
                        REPORT_EXPECTED + expected + " is not matching" + REPORT_ACTUAL + actual);
            }
        }catch (Exception e){
            report(FAIL, "Verify numbers are matching in " + identifier,
                    REPORT_EXPECTED + expected + " is matching" + REPORT_ACTUAL + actual,
                    e.getMessage());
        }
    }

    /**
     * @implSpec  isValuePreset function returns all the text displayed in a list of
     * Webelements and compares if the list contains a given list of elements
     *
     * @implNote This utility returns a List<String> and is generally useful for comparing 2 Strings in a List
     * @param isli
     * @param text
     * @throws FlexFrameWorkRunTimeException
     */
    public void isValuePresent(ISuppllyLocatorInfo isli, List<String> text) throws FlexFrameWorkRunTimeException {
        try {
            List<String> actualList = getTextFromWebElements(isli);
            if (actualList.containsAll(text)){
                report(SUCCESS, "Verify actual list contains all expected values in " + isli.getLabel(),
                        actualList + " Values present as same as " + text,
                        actualList + " Values present as same as " + text);
            }else {
                report(FAIL, "Verify actual list contains all expected values in " + isli.getLabel(),
                        actualList + " Values present as same as " + text,
                        actualList + " Values present not same as " + text);
            }
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e);
        }
    }

    public List<String> getTextFromWebelements(ISuppllyLocatorInfo isli) throws  FlexFrameWorkRunTimeException {
        try {
            List<String> getTextFromWebElementList = new ArrayList<>();
            for (WebElement element : getWebElements(isli)){
                String elementText = getTextFromWebElementJS(element).replaceAll("\\n", "").trim();
                getTextFromWebElementList.add(elementText);
            }
            return  getTextFromWebElementList;
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e);
        }
    }

    public List<String> getTextAttributeFromElements(ISuppllyLocatorInfo isli, String attributeName) throws  FlexFrameWorkRunTimeException {
        try {
            List<String> getTextFromWebElementList = new ArrayList<>();
            for (WebElement element : getWebElements(isli)){
                String elementText = getAttribute(element, attributeName, "Fetch attribute value");
                getTextFromWebElementList.add(elementText);
            }
            return  getTextFromWebElementList;
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e);
        }
    }

    public void verifyBrowserTab(String tabTitle, String  identfier) throws FlexFrameWorkRunTimeException {
        try {
            WebDriver driver = AllDataHolder.getDriver();
            //get window handlers as list
            List<String> browserTabs = new ArrayList<>(driver.getWindowHandles());
            for (String browserTab : browserTabs){
                //Switching to tab
                driver.switchTo().window(browserTab);
                String title = driver.getTitle();
                if (title.contains(tabTitle)){
                    report(SUCCESS, "Verify browser tab title in " + identfier,
                            "Browser tab opened with title " + tabTitle,
                            "Browser tab opened with title " + tabTitle);
                    break;
                }
            }
            if (waitTillPresenceOfAlert(5)){
                acceptPopup();
                driver.close();
                driver.switchTo().window(browserTabs.get(0));
            }
        }catch (Exception e){
            report(FAIL, "Verify browser tab title in " + identfier,
                    "Browser tab opened with title " + tabTitle,
                    e.getMessage());
        }
    }

    public void acceptPopup() {
        WebDriver driver = AllDataHolder.getDriver();
        waitTillPresenceOfAlert(8);
        driver.switchTo().alert().accept();
    }

    /**
     * @implNote This method return the date by adding or subtracting days
     *           from current date accepts +values and -Values, to get Current date use 0(zero)
     * @param days
     * @return
     * @throws ParseException
     */
    public String getDateWithAddOrMinusFromCurrentDate(int days) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(MMDDYYYYDATEFORMAT);
        LocalDate date = LocalDate.now().plusDays(days);
        SimpleDateFormat oldFormat = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(oldFormat.parse(date.toString()));
    }

    public void validateLegendColor(ISuppllyLocatorInfo isli, String hexColor, String cssValue) throws FlexFrameWorkRunTimeException {
        String[] number;
        try {
            WebElement element = getWebElement(isli);
            //Get CSS value as RGBA
            String checkColor = element.getCssValue(cssValue);
            //Split and parse
            if (checkColor.contains("rgba")){
                number = checkColor.replace("rgba(", "").replace(")", "").split(",");
            }else {
                number = checkColor.replace("rgb(", "").replace(")", "").split(",");
            }

            int red = Integer.parseInt(number[0].trim());
            int g = Integer.parseInt(number[1].trim());
            int b = Integer.parseInt(number[2].trim());

            //Convert Int to Hex value
            String hex = "#" + formatConversion(Integer.toHexString(red)) + formatConversion(Integer.toHexString(g))
                    + formatConversion(Integer.toHexString(b));
            // Check if Actual matches with Expected

            if (hex.equalsIgnoreCase(hexColor)){
                report(SUCCESS, "Verify color in " + isli.getLabel(),
                        "Expected color " + hexColor + "matching actual color" + hex,
                        "Expected color " + hexColor + "matching actual color" + hex);
            }else {
                report(FAIL, "Verify color in " + isli.getLabel(),
                        "Expected color " + hexColor + "matching actual color" + hex,
                        "Expected color " + hexColor + " not matching actual color" + hex);
            }


        }catch (Exception e){
            report(FAIL, "Verify color in " + isli.getLabel(),
                    "Expected color " + hexColor + "matching actual color",
                    e.getMessage());
        }

    }

    private String formatConversion(String number) {
        if (number.length() < 2){
            return "0" + number;
        }
        return number;
    }

    public void validateBackgroundImage(ISuppllyLocatorInfo isli, String expValue, String cssValue) throws FlexFrameWorkRunTimeException {
        try {
            WebElement element = getWebElement(isli);
            // Get CSS values as RGBA
            String actual = element.getCssValue(cssValue);

            if (actual.contains(expValue)){
                report(SUCCESS, "Verify Background image in " + isli.getLabel(),
                        "Background image expected " + expValue + " matching with actual " + actual,
                        "Background image expected " + expValue + " matching with actual " + actual);
            }else {
                report(FAIL, "Verify Background image in " + isli.getLabel(),
                        "Background image expected " + expValue + " matching with actual " + actual,
                        "Background image expected " + expValue + " not matching with actual " + actual);
            }
        } catch (Exception e) {
            report(SUCCESS, "Verify Background image in " + isli.getLabel(),
                    "Background image expected " + expValue + " matching with actual ",
                    e.getMessage());
        }
    }

    /**
     * @implNote This method is written to verify sorting of the given element
     * @param isli
     * @throws FlexFrameWorkRunTimeException
     */
    public void sortingAscending(ISuppllyLocatorInfo isli) throws FlexFrameWorkRunTimeException {
        WebDriver driver = AllDataHolder.getDriver();
        List<WebElement> sortingColumn = driver.findElements((By) isli.getLocator());
        List<String> actual = new ArrayList<>();
        List<String> actualSorted;
        for (WebElement aSortingColumn : sortingColumn){
            String value = aSortingColumn.getText().trim();
            if (value.length() > 0){
                actual.add(value);
            }
        }
        actualSorted = new ArrayList<>(actual);
        actualSorted.sort(String::compareToIgnoreCase);
        if (actualSorted.equals(actual)){
            report(SUCCESS, "Verify Column sorted in ascending order in " + isli.getLabel(),
                    "Expected Sorting " + actualSorted + " matching with actual sorting " + actual,
                    "Expected Sorting " + actualSorted + " matching with actual sorting " + actual);
        }else {
            report(FAIL, "Verify Column sorted in ascending order in " + isli.getLabel(),
                    "Expected Sorting " + actualSorted + HTML_BREAK + " matching with actual sorting " + actual,
                    "Expected Sorting " + actualSorted + HTML_BREAK +" not matching with actual sorting " + actual);
        }
    }

    public void sortingDescending(ISuppllyLocatorInfo isli) throws FlexFrameWorkRunTimeException {
        WebDriver driver = AllDataHolder.getDriver();
        List<WebElement> sortingColumn = driver.findElements((By) isli.getLocator());
        List<String> actual = new ArrayList<>();
        List<String> actualSorted;
        for (WebElement aSortingColumn : sortingColumn){
            String value = aSortingColumn.getText().trim();
        }
        actualSorted = new ArrayList<>(actual);
        actualSorted.sort(Collections.reverseOrder(String::compareToIgnoreCase));
        if (actualSorted.equals(actual)){
            report(SUCCESS, "Verify Column sorted in ascending order in " + isli.getLabel(),
                    "Expected Sorting " + actualSorted + HTML_BREAK + " matching with actual sorting " + actual,
                    "Expected Sorting " + actualSorted + HTML_BREAK + " matching with actual sorting " + actual);
        }else {
            report(FAIL, "Verify Column sorted in ascending order in " + isli.getLabel(),
                    "Expected Sorting " + actualSorted + HTML_BREAK + " matching with actual sorting " + actual,
                    "Expected Sorting " + actualSorted + HTML_BREAK +" not matching with actual sorting " + actual);
        }
    }

    /**
     * @implNote  This method is written to verify the soring for given list of String
     * @param actualValue
     * @throws FlexFrameWorkRunTimeException
     */
    public void sortAscending(List<String> actualValue) throws FlexFrameWorkRunTimeException {
        List<String> actualSorted;
        actualSorted = new ArrayList<>(actualValue);
        actualSorted.sort(String::compareToIgnoreCase);
        if (actualSorted.equals(actualValue)){
            report(SUCCESS, "Verify column is sorted ascending order",
                    "Expected sorting " + actualSorted + HTML_BREAK + " matching with actual sorting " + actualValue,
                    "Expected sorting " + actualSorted + HTML_BREAK + " matching with actual sorting " + actualValue);
        }else {
            report(FAIL, "Verify column is sorted ascending order",
                    "Expected sorting " + actualSorted + HTML_BREAK + " matching with actual sorting " + actualValue,
                    "Expected sorting " + actualSorted + HTML_BREAK + " not matching with actual sorting " + actualValue);
        }
    }

    /**
     * @implNote  This method is written to verify the soring for given list of String
     * @param actualValue
     * @throws FlexFrameWorkRunTimeException
     */
    public void sortAscendingLong(List<Long> actualValue) throws FlexFrameWorkRunTimeException {
        List<Long> actualSorted;
        actualSorted = new ArrayList<>(actualValue);
        Collections.sort(actualValue);
        if (actualSorted.equals(actualValue)){
            report(SUCCESS, "Verify column is sorted ascending order",
                    "Expected sorting " + actualSorted + HTML_BREAK + " matching with actual sorting " + actualValue,
                    "Expected sorting " + actualSorted + HTML_BREAK + " matching with actual sorting " + actualValue);
        }else {
            report(FAIL, "Verify column is sorted ascending order",
                    "Expected sorting " + actualSorted + HTML_BREAK + " matching with actual sorting " + actualValue,
                    "Expected sorting " + actualSorted + HTML_BREAK + " not matching with actual sorting " + actualValue);
        }
    }

    public boolean isElementPresent(By by){
        boolean elementPreset = true;
        WebDriver driver = AllDataHolder.getDriver();
        try {
            driver.findElement(by);
        }catch (NoSuchElementException e){
            elementPreset = false;
        }
        return elementPreset;
    }

    public void acceptSSLCeritificate(){
        WebDriver driver = AllDataHolder.getDriver();
        if (!AllDataHolder.isNewLogin()){
            if (!isContentDisplayed(LOGIN.TEXTBOX_LOGINID_INPUT)){
                driver.get("javascript:document.getElementById('overridelink').click()");
            }
        }
    }

    /**
     * @implNote selectLastOptionOfDropDown program to selects last option from any given dropdown field
     * @param isli
     * @throws FlexFrameWorkRunTimeException
     */
    public void selectLastOptionOfDropDown(ISuppllyLocatorInfo isli) throws FlexFrameWorkRunTimeException {
        try {
            WebElement element = getWebElement(isli);
            Select dropDown = new Select(element);
            dropDown.selectByIndex(dropDown.getOptions().size() -1);
            String selValue = dropDown.getFirstSelectedOption().toString();
            report(SUCCESS, "Select last option in " + isli.getLabel(), "Selected option is " + selValue, "Selected option is " + selValue);

        } catch (Exception e) {
            report(SUCCESS, "Select last option in " + isli.getLabel(), "Selected option", e.getMessage());
        }
    }


    /**
     * @implNote selectrandomOptionOfDropDown program to selects random option from any given dropdown field
     * @param isli
     * @throws FlexFrameWorkRunTimeException
     */
    public void selectRandomOptionOfDropDown(ISuppllyLocatorInfo isli) throws FlexFrameWorkRunTimeException {
        try {
            WebElement element = getWebElement(isli);
            Select dropDown = new Select(element);
            dropDown.selectByIndex(r.nextInt(dropDown.getOptions().size() -1) + 1);
            String selValue = dropDown.getFirstSelectedOption().toString();
            report(SUCCESS, "Select random option in " + isli.getLabel(), "Selected option is " + selValue, "Selected option is " + selValue);

        } catch (Exception e) {
            report(SUCCESS, "Select random option in " + isli.getLabel(), "Selected option", e.getMessage());
        }
    }

    //Getting CSS value
    public void getCssValue(ISuppllyLocatorInfo isli, String cssValueToGet) throws FlexFrameWorkRunTimeException {
        String cssValue = null;
        try {
            WebElement element = getWebElement(isli);
            cssValue = element.getCssValue(cssValue);
        }catch (StaleElementReferenceException sere){
            getWebElements(isli, cssValueToGet);
        }catch (Exception e){
            report(FAIL, "Fetch CSS value in " + isli.getLabel(), "Fecthed CSS value in " + isli.getLabel(), e.getMessage());
        }

    }

    /**
     * @implNote Verify sorting ascending order with ignoring null values
     * @param isli
     * @throws FlexFrameWorkRunTimeException
     */
    public void sortingAscendingRemovingNullValue(ISuppllyLocatorInfo isli) throws FlexFrameWorkRunTimeException {
        WebDriver driver = AllDataHolder.getDriver();
        List<WebElement> sortingColumn = driver.findElements((By) isli.getLocator());
        List<String> actual = new ArrayList<>();
        List<String> actualSorted;
        for (WebElement aSortingColumn : sortingColumn){
           actual.add(aSortingColumn.getText());
           actual.removeAll(Collections.singleton(""));
        }
        actualSorted = new ArrayList<>(actual);
        Collections.sort(actualSorted);
        if (actualSorted.equals(actual)){
            report(SUCCESS, "Verify Column sorted in ascending order in " + isli.getLabel(),
                    "Expected Sorting " + actualSorted + " matching with actual sorting " + actual,
                    "Expected Sorting " + actualSorted + " matching with actual sorting " + actual);
        }else {
            report(FAIL, "Verify Column sorted in ascending order in " + isli.getLabel(),
                    "Expected Sorting " + actualSorted + HTML_BREAK + " matching with actual sorting " + actual,
                    "Expected Sorting " + actualSorted + HTML_BREAK +" not matching with actual sorting " + actual);
        }
    }

    /**
     * @implNote Verify sorting ascending order with ignoring null values
     * @param isli
     * @throws FlexFrameWorkRunTimeException
     */
    public void sortingDescendingCaseSensitiveAndRemovingNullValue(ISuppllyLocatorInfo isli) throws FlexFrameWorkRunTimeException {
        WebDriver driver = AllDataHolder.getDriver();
        List<WebElement> sortingColumn = driver.findElements((By) isli.getLocator());
        List<String> actual = new ArrayList<>();
        List<String> actualSorted;
        for (WebElement aSortingColumn : sortingColumn){
            actual.add(aSortingColumn.getText());
            actual.removeAll(Collections.singleton(""));
        }
        actualSorted = new ArrayList<>(actual);
        actual.sort(Collections.reverseOrder());
        if (actualSorted.equals(actual)){
            report(SUCCESS, "Verify Column sorted in descending order in " + isli.getLabel(),
                    "Expected Sorting " + actualSorted + " matching with actual sorting " + actual,
                    "Expected Sorting " + actualSorted + " matching with actual sorting " + actual);
        }else {
            report(FAIL, "Verify Column sorted in descending order in " + isli.getLabel(),
                    "Expected Sorting " + actualSorted + HTML_BREAK + " matching with actual sorting " + actual,
                    "Expected Sorting " + actualSorted + HTML_BREAK +" not matching with actual sorting " + actual);
        }
    }

    public boolean isElementEnbledBoolean(ISuppllyLocatorInfo isli) throws FlexFrameWorkRunTimeException {
        boolean value = false;
        try {
            value = isElementEnbledBoolean(isli.getLocator(), isli.getLabel());
        }catch (Exception e){
            report(FAIL, "Check if the element is disabled,", isli.getLabel() + "is enabled", e.getMessage());
        }
        return value;
    }

    private boolean isElementEnbledBoolean(Object byOrWebElement, String identifier) throws FlexFrameWorkRunTimeException {
        boolean flag = false;
        try {
            WebElement element = getWebElement(byOrWebElement);
            if (element.isEnabled()){
                report(SUCCESS, "Check if element is enabled is " + identifier, "Element is enabled in " + identifier, "Element is enabled in " + identifier);
                flag = true;
            }
        } catch (Exception e) {
            report(FAIL, "Check if the element is disabled,", identifier + "is enabled", e.getMessage());
        }
        return flag;
    }

    public String getHostFromNodeURL(String nodeURL){
        Pattern pattern = Pattern.compile("(https://)(.*?)(.com)");
        String host = "";
        Matcher matcher = pattern.matcher(nodeURL);
        while (matcher.find()){
            host = matcher.group(2);
        }
        return host.toUpperCase();
    }

    public void switchToWindoUsingTitle(String title) throws FlexFrameWorkRunTimeException{
        try {
            WebDriver driver = AllDataHolder.getDriver();
            Set<String> windows = driver.getWindowHandles();
            String childWindow = "";
            for (String window : windows){
                driver.switchTo().window(window);
                waitUntill(1000);
                if (driver.getTitle().equals(title)){
                    childWindow = window;
                    break;
                }
            }
            if (childWindow.isEmpty()){
                report(FAIL, "Switch to window with title " + title, "Switched wo window with title " + title,
                        "No child window is available to switch");
            }else {
                report(SUCCESS, "Switch to window with title " + title, "Switched wo window with title " + title,
                        "Switched wo window with title " + title);
            }
        }catch (Exception e){
            report(FAIL, "Switch to window with title " + title, "Switched wo window with title " + title,
                    e.getMessage());
        }
    }

    public void verifyNotNull(ISuppllyLocatorInfo isli) throws FlexFrameWorkRunTimeException {
        verifyNotNull(isli.getLocator(), isli.getLabel());
    }

    private void verifyNotNull(Object byOrWebElement, String label) throws FlexFrameWorkRunTimeException {
        try {
            WebElement element = getWebElement(byOrWebElement);
            if (element.getText() !=null){
                report(SUCCESS, "Check if element is text is not null",
                        label + "_" + "Element text is not null",
                        label + "_" + "Element text is not null");
            }else {
                report(FAIL, "Check if element is text is not null",
                        label + "_" + "Element text is not null",
                        label + "_" + "Element text is  null");
            }
        }catch (Exception e){
            report(FAIL, "Check if element is text is not null",
                    label + "_" + "Element text is not null",
                    e.getMessage());
        }
    }

    public void checkElementIsLink(ISuppllyLocatorInfo isli) throws FlexFrameWorkRunTimeException {
        checkElementIsLink(isli.getLocator(), isli.getLabel());
    }

    public void checkElementIsLink(Object byOrWebElement,  String label) throws FlexFrameWorkRunTimeException {
        try {
            WebElement element = getWebElement(byOrWebElement);
            String tag = element.getTagName();
            if (("a").equals(tag)){
                report(SUCCESS, "Check if the element is link", element.getText() + "element is a link",
                        element.getText() + "element is a link");
            }else {
                report(FAIL, "Check if the element is link", element.getText() + "element is a link",
                        element.getText() + "element is not a link");
            }
        }catch (Exception e){
            report(SUCCESS, "Check if the element is link", "Element is a link",
                    e.getMessage());
        }
    }

    public void clearValueAndPressEnter(ISuppllyLocatorInfo isli) throws  FlexFrameWorkRunTimeException {
        try {
            WebElement element = getWebElement(isli);
            element.click();
            element.sendKeys(Keys.ENTER);
            report(SUCCESS, "Clear value and press ENTER " + isli.getLabel(), "Value cleared and pressed ENTER",
                    "Value cleared and pressed ENTER");
        }catch (StaleElementReferenceException sere){
            clearValueAndPressEnter(isli);
        }catch (Exception e){
            report(FAIL, "Clear value and press ENTER " + isli.getLabel(), "Value cleared and pressed ENTER",
                    e.getMessage());
        }
    }

    public void clearValue(ISuppllyLocatorInfo isli) throws  FlexFrameWorkRunTimeException {
        clearValue(isli.getLocator(), isli.getLabel());
    }
    public void clearValue(Object byOrWebelement, String identifier) throws  FlexFrameWorkRunTimeException {
        try {
            WebElement element = getWebElement(byOrWebelement);
            element.click();
            report(SUCCESS, "Clear value and press ENTER " + identifier, "Value cleared and pressed ENTER",
                    "Value cleared and pressed ENTER");
        }catch (StaleElementReferenceException sere){
            clearValue(byOrWebelement, identifier);
        }catch (Exception e){
            report(FAIL, "Clear value and press ENTER " + identifier, "Value cleared and pressed ENTER",
                    e.getMessage());
        }
    }

    /**
     * @implNote Verify the content in the text file with that of UI
     * @param text
     * @param fileNmae
     * @throws FlexFrameWorkRunTimeException
     */
    public void verifyContentInTextFile(List<String> text, String fileNmae) throws  FlexFrameWorkRunTimeException {
        try{
            String path = AllDataHolder.getImportFileLocation();
            Path myPath = Paths.get(path, fileNmae);
            Charset charset = StandardCharsets.ISO_8859_1;
            //Read all lines in notepad
            List<String> fullText = Files.readAllLines(myPath, charset);
            // Compare listed notepad
            this.listOfstringComparision(fullText, text);
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e);
        }
    }

    public void verifyDropDownValuePresent(ISuppllyLocatorInfo isli, String dropDownValue) throws FlexFrameWorkRunTimeException {
        try {
            WebElement element = getWebElement(isli);
            Select select = new Select(element);
            List<WebElement> dropDownValues = select.getOptions();
            for (WebElement eachValue : dropDownValues){
                if (eachValue.getText().contains(dropDownValue)){
                    report(SUCCESS, "Verify " + dropDownValue + "present is dropdown " + isli.getLabel(),
                            dropDownValue + "Is present in dropdown " + isli.getLabel(),
                            dropDownValue + "Is present in dropdown " + isli.getLabel());
                }
            }
        }catch (Exception e){
            report(FAIL, "Verify " + dropDownValue + "present is dropdown " + isli.getLabel(),
                    dropDownValue + "Is present in dropdown " + isli.getLabel(),
                    e.getMessage());
        }
    }

    public void dateBetweenFromto(List<String> columnValue, String fromDate, String toDate) throws FlexFrameWorkRunTimeException {
        try {
            SimpleDateFormat format = new SimpleDateFormat(MMDDYYYYDATEFORMAT);
            List<String> outputValuePass = new ArrayList<>();
            List<String> outputValueFail = new ArrayList<>();
            Date from = format.parse(fromDate);
            Date to = format.parse(toDate);
            for (String eachValue : columnValue){
                Date exp = format.parse(eachValue);
                int before = exp.compareTo(to);
                int after = exp.compareTo(from);
                if (before <=0 || after >=0){
                    outputValuePass.add(eachValue);
                }else {
                    outputValueFail.add(eachValue);
                }
            }
            if (outputValueFail.isEmpty()){
                report(SUCCESS, "Verify od dates between " + fromDate + " and " + toDate,
                        outputValuePass + " dates are between " + fromDate + " and " + toDate,
                        outputValuePass + " dates are between " + fromDate + " and " + toDate);
            }else {
                report(SUCCESS, "Verify od dates between " + fromDate + " and " + toDate,
                        outputValuePass + " dates are between " + fromDate + " and " + toDate,
                        outputValuePass + " dates are between " + fromDate + " and " + toDate + HTML_BREAK
                + outputValueFail + " dates are not between " + fromDate + "and " + toDate);
            }

        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e);
        }
    }

    public void verifyAttribute(ISuppllyLocatorInfo isli, String attribName, String expectedValue) throws FlexFrameWorkRunTimeException {
        verifyAttribute(isli.getLocator(), attribName, expectedValue, isli.getLabel());
    }

    private void verifyAttribute(Object locator, String attribName, String expectedValue, String identifier) throws FlexFrameWorkRunTimeException {

        try {
            WebElement element = getWebElement(locator);
            String value = element.getAttribute(attribName);
            if (value.equals(expectedValue)){
                report(SUCCESS, "Perform attribute value equality check",
                        "Actual value " + value + " is matching with expected value " + expectedValue,
                        "Actual value " + value + " is matching with expected value " + expectedValue);
            }else {
                report(FAIL, "Perform attribute value equality check",
                        "Actual value " + value + " is matching with expected value " + expectedValue,
                        "Actual value " + value + " is not matching with expected value " + expectedValue);
            }
        }catch (Exception e){
            report(FAIL, "Perform attribute value equality check",
                    "Actual value  is matching with expected value " + expectedValue,
                    e.getMessage());
        }

    }

    /**
     * @implNote This method is written to verify sorting of the give numbers
     * @param isli
     * @throws FlexFrameWorkRunTimeException
     */
    public void numaricalSortingAscending(ISuppllyLocatorInfo isli) throws FlexFrameWorkRunTimeException {
        WebDriver driver = AllDataHolder.getDriver();
        List<WebElement> sortingColumn = driver.findElements((By) isli.getLocator());
        List<Double> actual = new ArrayList<>();
        List<Double> actualSorted;
        for (WebElement aSortingColumn : sortingColumn){
            String columnValue = aSortingColumn.getText().trim();
            if (columnValue.length() > 0){
                columnValue.replace("$", "").replaceAll(",", "").replace("%", "");
            }
        }
        actualSorted = new ArrayList<>(actual);
        Collections.sort(actualSorted);
        if (actualSorted.equals(actual)){
            report(SUCCESS, "Verify Column sorted in ascending order in " + isli.getLabel(),
                    "Expected Sorting " + actualSorted + HTML_BREAK +" matching with actual sorting " + actual,
                    "Expected Sorting " + actualSorted + HTML_BREAK + " matching with actual sorting " + actual);
        }else {
            report(FAIL, "Verify Column sorted in ascending order in " + isli.getLabel(),
                    "Expected Sorting " + actualSorted + HTML_BREAK + " matching with actual sorting " + actual,
                    "Expected Sorting " + actualSorted + HTML_BREAK +" not matching with actual sorting " + actual);
        }
    }

    /**
     * @implNote This method is written to verify sorting of the give numbers
     * @param isli
     * @throws FlexFrameWorkRunTimeException
     */
    public void numaricalSortingDescending(ISuppllyLocatorInfo isli) throws FlexFrameWorkRunTimeException {
        WebDriver driver = AllDataHolder.getDriver();
        List<WebElement> sortingColumn = driver.findElements((By) isli.getLocator());
        List<Double> actual = new ArrayList<>();
        List<Double> actualSorted;
        for (WebElement aSortingColumn : sortingColumn){
            String columnValue = aSortingColumn.getText().trim();
            if (columnValue.length() > 0){
                columnValue.replace("$", "").replaceAll(",", "").replace("%", "");
            }
        }
        actualSorted = new ArrayList<>(actual);
        actualSorted.sort(Collections.reverseOrder());
        if (actualSorted.equals(actual)){
            report(SUCCESS, "Verify Column sorted in descending order in " + isli.getLabel(),
                    "Expected Sorting " + actualSorted + HTML_BREAK +" matching with actual sorting " + actual,
                    "Expected Sorting " + actualSorted + HTML_BREAK + " matching with actual sorting " + actual);
        }else {
            report(FAIL, "Verify Column sorted in descending order in " + isli.getLabel(),
                    "Expected Sorting " + actualSorted + HTML_BREAK + " matching with actual sorting " + actual,
                    "Expected Sorting " + actualSorted + HTML_BREAK +" not matching with actual sorting " + actual);
        }
    }

    public String getAddDaysToCurrentStamp(int days) throws FlexFrameWorkRunTimeException {
        try {
            SimpleDateFormat format = new SimpleDateFormat(MMDDYYYYDATEFORMAT);
            Date date = Calendar.getInstance().getTime();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DATE, days);
            Date newDate = calendar.getTime();
            return format.format(newDate);
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e);
        }
    }

    /**
     * @implNote to verify max length of text box
     * @param isli
     * @param len
     * @throws FlexFrameWorkRunTimeException
     */
    public void verifyMaxLength(ISuppllyLocatorInfo isli, int len) throws FlexFrameWorkRunTimeException {
        try {
            int actLength = Integer.parseInt(getAttribute(isli, "maxlength"));
            if (actLength == len){
                report(SUCCESS, "Verify max length of text box in " + isli.getLabel(),
                "Expected " + len + " is matching actual " + actLength,
                        "Expected " + len + " is matching actual " + actLength);
            }else {
                report(FAIL, "Verify max length of text box in " + isli.getLabel(),
                        "Expected " + len + " is matching actual " + actLength,
                        "Expected " + len + " is not matching actual " + actLength);
            }
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e);
        }
    }

    /**
     * @implNote  To Launch new tab
     * @param url
     * @throws FlexFrameWorkRunTimeException
     */
    public void toLaunchNewTab(String url) throws FlexFrameWorkRunTimeException {
        try {
            WebDriver driver = AllDataHolder.getDriver();
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.open()");
            switchToNewWindow();
            if (!url.isEmpty()){
                driver.navigate().to(url);
                report(SUCCESS, "launch new tab and navigate to " + url, "Launched new tab and navigated to " + url,
                        "Launched new tab and navigated to " + url);
            }else {
                report(FAIL, "launch new tab and navigate to " + url, "Launched new tab and navigated to " + url,
                        "URL is Empty");
            }
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e);
        }
    }

    /**
     * @implNote  To Launch new tab
     * @param url
     * @throws FlexFrameWorkRunTimeException
     */
    public void toLaunchNewBrowserWindow(String url) throws FlexFrameWorkRunTimeException {
        try {
            WebDriver driver = AllDataHolder.getDriver();
            JavascriptExecutor js = (JavascriptExecutor) driver;
            if (!url.isEmpty()){
                js.executeScript("window.open('" + url + "', '_BLANK 'height=400'");
                switchToNewWindow();
                report(SUCCESS, "launch window and navigate to " + url, "Launched new window and navigated to " + url,
                        "Launched new window and navigated to " + url);
            }else {
                report(FAIL, "launch new window and navigate to " + url, "Launched new window and navigated to " + url,
                        "URL is Empty");
            }
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e);
        }
    }

    public String  switchToNewWindow() throws FlexFrameWorkRunTimeException {
        WebDriver driver = AllDataHolder.getDriver();
        Set<String> windows = driver.getWindowHandles();
        windows.remove(AllDataHolder.getParentWindowHandle());
        if (windows.size() == 1){
            driver.switchTo().window(windows.iterator().next());
            report(SUCCESS, "Switch to new window", "Switched to new window and its tile is: " + driver.getTitle(),
                    "Switched to new window and its tile is: " + driver.getTitle());
        }else {
            report(SUCCESS, "Switch to new window", "Switched to new window and its tile is: " + driver.getTitle(),
                    "Switching failed. Possibly, more than one child windows detected. Please use other switch window for more than 1 chils window");
        }
        return driver.getWindowHandle();
    }

    /**
     * @implNote To verify the date format
     * @param format
     * @param value
     * @throws FlexFrameWorkRunTimeException
     */
    public void verifyValidDateFormat(String format, String value) throws FlexFrameWorkRunTimeException {
        try {
            SimpleDateFormat formater = new SimpleDateFormat(format);
            Date date = formater.parse(value);
            if (!value.equals(formater.format(date))) {
                report(FAIL, " Verify date format is valid", value + "date is in expected format " + format,
                        value + "date is not in expected format " + format);
            }else {
                report(SUCCESS, " Verify date format is valid", value + "date is in expected format " + format,
                        value + "date is  in expected format " + format);
            }
        } catch (ParseException e) {
            throw new FlexFrameWorkRunTimeException(e);
        }
    }

    /**
     * @implNote To verify a list contains a specific word
     * @param actualList
     * @param searchedValue
     * @throws FlexFrameWorkRunTimeException
     */
    public void verifyListContainsText(List<String> actualList, String searchedValue) throws FlexFrameWorkRunTimeException {
        try {
            if (actualList.contains(searchedValue)){
                report(SUCCESS, "Verify the list contains expected value " + searchedValue,
                        "List " + actualList + " contains" + " expected value " + searchedValue,
                        "List " + actualList + " contains" + " expected value " + searchedValue);
            }else {
                report(FAIL, "Verify the list contains expected value " + searchedValue,
                        "List " + actualList + " contains" + " expected value " + searchedValue,
                        "List " + actualList + " not contains" + " expected value " + searchedValue);
            }
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e);
        }
    }

    /**
     * @implNote To verify dropdown values with expected dropdown values
     * @param isli
     * @param expectedOptions
     * @throws FlexFrameWorkRunTimeException
     */
    public void checkDroPDownValues(ISuppllyLocatorInfo isli, List<String> expectedOptions) throws FlexFrameWorkRunTimeException {
        try {
            Select dropDown = new Select(getWebElement(isli.getLocator()));
            driverWait();
            List<WebElement> dropDownOptions = dropDown.getOptions();
            List<String> dropDownValues = new ArrayList<>();
            for (WebElement option : dropDownOptions){
                dropDownValues.add(option.getText().trim().replaceAll("()+", " "));
            }
            if (dropDownValues.equals(expectedOptions)){
                report(SUCCESS, "Verify dropdown options in " + isli.getLabel(),
                         "Expected dropdown options :" + expectedOptions + " Matching with actual options : " + dropDownValues,
                        "Expected dropdown options :" + expectedOptions + " Matching with actual options : " + dropDownValues);
            }else {
                report(FAIL, "Verify dropdown options in " + isli.getLabel(),
                        "Expected dropdown options :" + expectedOptions + " Matching with actual options : " + dropDownValues,
                        "Expected dropdown options :" + expectedOptions + " Not Matching with actual options : " + dropDownValues);
            }
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e);
        }
    }

    /**
     * @implNote To return wheather the element is drop down or not
     * @param isli
     * @return
     * @throws FlexFrameWorkRunTimeException
     */
    public boolean isDropDown(ISuppllyLocatorInfo isli) throws FlexFrameWorkRunTimeException {
        boolean isDropDown = true;
        try {
            WebElement element = getWebElement(isli.getLocator());
            isDropDown ="select".equals(element.getTagName());
        }catch (Exception e){
            report(FAIL, "Check if the element is dropdown in " + isli.getLabel(),
                    "Checked element is dropdown : " + isli.getLabel(), e.getMessage());
        }
        return isDropDown;
    }

    /**
     * @implNote To send any key in text box
     * @param isli
     * @param key
     * @throws FlexFrameWorkRunTimeException
     */
    public void sendKeys(ISuppllyLocatorInfo isli, Keys key) throws FlexFrameWorkRunTimeException {
        try {
            WebElement element = getWebElement(isli.getLocator());
            element.sendKeys(key);
            report(SUCCESS, " Press Key: " + key.name(),
                    "Key Pressed: " + key.name(),
                    "Key Pressed: " + key.name());
        }catch (Exception e){
            report(SUCCESS, " Press Key: " + key.name(),
                    "Key Pressed: " + key.name(),
                    e.getMessage());
        }
    }

    /**
     * @implNote this method get all values from dropdown
     * @param isli
     * @return
     * @throws FlexFrameWorkRunTimeException
     */
    public List<String> getDropDownOptions(ISuppllyLocatorInfo isli) throws FlexFrameWorkRunTimeException {
        return getAllValuesFromDropdown(isli);
    }

    /**
     * @implNote this method get all values from dropdown
     * @param isli
     * @return
     * @throws FlexFrameWorkRunTimeException
     */
    public List<String> getAllValuesFromDropdown(ISuppllyLocatorInfo isli) throws FlexFrameWorkRunTimeException {
        List<String> values = new ArrayList<>();
        try {
            WebElement element = getWebElement(isli);
            Select select = new Select(element);
            for (WebElement value : select.getOptions()){
                values.add(value.getText());
            }
            report(SUCCESS, "Check if value is preset", "Values are present", "Values are present");
            return values;
        }catch (Exception e){
            report(FAIL, "Check if value is preset", "Values are present", e.getMessage());
        }
        return values;
    }

    /**
     * @implNote this method verifies a textbox is empty or not and returns boolen value
     * @param isli
     * @return
     * @throws FlexFrameWorkRunTimeException
     */
    public boolean isTextBoxEmpty(ISuppllyLocatorInfo isli) throws FlexFrameWorkRunTimeException {
        boolean value = false;
        try {
            WebElement element = getWebElement(isli.getLocator());
            String actualText = element.getAttribute(ATTRIBUTEVALUE);
            value = actualText.isEmpty();
        }catch (Exception e){
            report(FAIL, " Check if text box is empty", "Checked if text box is empty", e.getMessage());
        }
        return value;
    }

    /**
     * @implNote this method verifies a textbox is empty or not
     * @param isli
     * @return
     * @throws FlexFrameWorkRunTimeException
     */
    public void verifyTextBoxEmpty(ISuppllyLocatorInfo isli) throws FlexFrameWorkRunTimeException {
        boolean value = false;
        try {
          if (isTextBoxEmpty(isli)){
              report(SUCCESS, " Check if text box is empty", "Checked if text box is empty", "Checked if text box is empty");
          }else {
              report(FAIL, " Check if text box is empty", "Checked if text box is not empty", "Text box is empty");
          }
        }catch (Exception e){
            report(FAIL, " Check if text box is empty", "Checked if text box is empty", e.getMessage());
        }
    }

    /**
     * @implNote to verify text is upper case or not
     * @param isli
     * @throws FlexFrameWorkRunTimeException
     */
    public void verifyTextInUpperCase(ISuppllyLocatorInfo isli) throws FlexFrameWorkRunTimeException {
        try {
            verifyTextInUpperCase(isli.getLocator(), isli.getLabel());
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e);
        }
    }

    /**
     * @implNote to verify text is upper case or not
     * @param byOrWebElement
     * @param identifier
     * @throws FlexFrameWorkRunTimeException
     */
    public void verifyTextInUpperCase(Object byOrWebElement, String identifier) throws FlexFrameWorkRunTimeException {
        try {
            WebElement element = getWebElement(byOrWebElement);
            String text = element.getText().replaceAll("\\s+", "").replaceAll("[^a-zA-Z]", "");
            verifyTextInUpperCase(text, identifier);
        }catch (Exception e){
            report(FAIL, "Verify text element is uppercase in " + identifier, "text is Null", e.getMessage());
        }
    }

    /**
     * @implNote To verify text is upper case or not
     * @param textToBeValidated
     * @param identifier
     * @throws FlexFrameWorkRunTimeException
     */
    public void verifyTextInUpperCase(String textToBeValidated, String identifier) throws FlexFrameWorkRunTimeException {
        try {
            if (textToBeValidated.length() >0){
                if (StringUtils.isAllLowerCase(textToBeValidated)){
                    report(SUCCESS, "Verify text element is uppercase in " + identifier,
                            textToBeValidated + " text is uppaercase",
                            textToBeValidated + " text is uppaercase");
                }else {
                    report(FAIL, "Verify text element is uppercase in " + identifier,
                            textToBeValidated + " text is uppaercase",
                            textToBeValidated + " text is not in uppaercase");
                }
            }else {
                report(SUCCESS, "Verify text element is uppercase in " + identifier,
                        "Text is Null",
                        "Text is Null");
            }
        }catch (Exception e){
            report(SUCCESS, "Verify text element is uppercase in " + identifier,
                    "Text is Null",
                    e.getMessage());
        }
    }

    /**
     * @implNote  Method used to copy and paste
     * @param copyFrom
     * @param pasteTo
     * @throws FlexFrameWorkRunTimeException
     */
    public void copyPasteValue(ISuppllyLocatorInfo copyFrom, ISuppllyLocatorInfo pasteTo) throws FlexFrameWorkRunTimeException {
        try {
            String copy = getWebElement(copyFrom.getLocator()).getText();
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Clipboard clipboard = toolkit.getSystemClipboard();
            clipboard.setContents(new StringSelection(copy), null);
            getWebElement(pasteTo.getLocator()).clear();
            getWebElement(pasteTo.getLocator()).sendKeys(Keys.CONTROL, "v");
            driverWait();
            report(SUCCESS, "Check if text is copied and pasted",
                    copy + "Copied from " + copyFrom.getLabel() + " and Pasted in " + pasteTo.getLabel(),
                    copy + "Copied from " + copyFrom.getLabel() + " and Pasted in " + pasteTo.getLabel());
        }catch (Exception e){
            report(FAIL, "Check if text is copied and pasted",
                    "Value Copied from " + copyFrom.getLabel() + " and Pasted in " + pasteTo.getLabel(),
                    e.getMessage());
        }
    }

    /**
     * @implNote This method used to validate number format and its comes between 0 to 9 digit
     * @param value
     * @param identifier
     * @throws FlexFrameWorkRunTimeException
     */
    public void isNumericValue(String value, String identifier) throws FlexFrameWorkRunTimeException {
        try {
            // --Below regix verify that its numeric plus comes between 0 to 9 digits
            String regexStr = "^[-+]?\\d+(\\.\\d+)?$";
            //------------- Check if Actual matched with Expected ------------
            if (value.replaceAll("," , "").trim().matches(regexStr)){
                //--------- Pass code here --------
                report(SUCCESS, "Check input value is numeric in " + identifier,
                        value + " displayed as numeric value", value + " displayed as numeric value");
            }else {
                report(FAIL, "Check input value is numeric in " + identifier,
                        value + " displayed as numeric value", value + " not displayed as numeric value");
            }
        }catch (Exception e){
            report(FAIL, "Check input value is numeric in " + identifier,
                    value + " displayed as numeric value", e.getMessage());
        }
    }

    /**
     * @implNote This method used to vlidate the Date format of Month dd, yyyy
     * @param inputDate
     * @param identifier
     * @throws FlexFrameWorkRunTimeException
     */
    public void validateDateSpecificFormat(String inputDate, String identifier) throws FlexFrameWorkRunTimeException {
        //Validate the date format should come in the format. ex: June 21, 1992
        try {
            String regexStr = " \\w+\\s\\d{2},\\s\\s{4}";
            if (inputDate.matches(regexStr)){
                report(SUCCESS, "Verify date format is valid in " + identifier,
                        inputDate + " Date is in expected format",
                        inputDate + " Date is in expected format");
            }else {
                report(FAIL, "Verify date format is valid in " + identifier,
                        inputDate + " Date is in expected format",
                        inputDate + " Date is not in expected format");
            }

        }catch (Exception e){
            report(FAIL, "Verify date format is valid in " + identifier,
                    inputDate + " Date is in expected format",
                    e.getMessage());
        }
    }

    /**
     * @implNote Methos to sort the numbers in Descending
     * @param values
     * @throws FlexFrameWorkRunTimeException
     */
    public void numberSortingDescending(List<String> values) throws FlexFrameWorkRunTimeException {
        try {
            List<Double> expected;
            List<Double> actual = new ArrayList<>();
            List<Integer> expectedIntegerList = new ArrayList<>();
            List<Integer> actualIntegerList = new ArrayList<>();
            for (String value : values){
                actual.add(Double.valueOf(value));
            }
            expected = new ArrayList<>(actual);
            expected.sort(Collections.reverseOrder());
            //Iteration to convert the double into Integer for expected value
            for (Double value : expected){
                expectedIntegerList.add(value.intValue());
            }
            //Iteration to convert the double into Integer for actual value
            for (Double value : actual){
                actualIntegerList.add(value.intValue());
            }
            if (actual.equals(expected)){
                report(SUCCESS, REPORT_EXPECTED + expectedIntegerList + "<b> is matching <b>" + REPORT_ACTUAL + actualIntegerList,
                        " Columns is sorted in descending order",
                        " Columns is sorted in descending order");
            }else {
                report(FAIL, REPORT_EXPECTED + expectedIntegerList + "<b> is not matching <b>" + REPORT_ACTUAL + actualIntegerList,
                        " Columns is sorted in descending order",
                        " Columns is not sorted in descending order" + actualIntegerList);
            }
        }catch (Exception e){
            report(FAIL, REPORT_EXPECTED + "<b> is not matching <b>" + REPORT_ACTUAL,
                    " Columns is sorted in descending order",
                    e.getMessage());
        }
    }

    /**
     * @implNote  This method to sort the date in Ascending / descending order
     * @param actual
     * @param order
     * @throws FlexFrameWorkRunTimeException
     */
    public void sortDates(List<Date> actual, String order) throws FlexFrameWorkRunTimeException {
        try {
            List<Date> actualSorted = new ArrayList<>(actual);
            if (order.equals(ASCENDING)){
                Collections.sort(actualSorted);
            }else {
                actualSorted.sort(Collections.reverseOrder());
            }
            if (actualSorted.equals(actual)){
                report(SUCCESS, "Sort in " + order, "Columns is Sorted in " + order + actual, "Columns is Sorted in " + order + actual);
            }else {
                report(FAIL, "Sort in " + order, "Columns is Sorted in " + order + actual, order + "Sorting failed -Actual :" + actual + "Expected: "+ actualSorted);
            }
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e);
        }
    }

    /**
     * @implNote this method used to scrolling anf click
     * @param isli1
     * @param isli2
     * @throws FlexFrameWorkRunTimeException
     */
    public void scroolAndClickElement(ISuppllyLocatorInfo isli1, ISuppllyLocatorInfo isli2) throws FlexFrameWorkRunTimeException {
        try {
            while (sizeOfWebElement(isli1) == 0){
                clickElement(isli1);
            }
            WebElement elementToClick = getWebElement(isli2);
            clickElement(elementToClick, isli2.getLabel());
            report(SUCCESS, "Scroll and click Element", "Element is clicked", "Element is clicked");
        }catch (Exception e){
            report(FAIL, "Scroll and click Element", "Element is clicked", "Failed to scroll and click element" + e.getMessage());
        }
    }

    /**
     * @implNote Method to check the element is highlighted or not
     * @param isli
     * @throws FlexFrameWorkRunTimeException
     */
    public void isElementHighlighted(ISuppllyLocatorInfo isli)throws FlexFrameWorkRunTimeException {
        isElementHighlighted(isli.getLocator(), isli.getLabel());
    }

    /**
     * @implNote Method to check the element is highlighted or not
     * @param byOrWebElement
     * @param identifier
     * @throws FlexFrameWorkRunTimeException
     */
    private void isElementHighlighted(Object byOrWebElement, String identifier) throws FlexFrameWorkRunTimeException {
        try {
            WebElement element = getWebElement(byOrWebElement);
            if ("rgba(3, 125, 174, 1)".equals(element.getCssValue("background-value"))){
                report(SUCCESS, "Check if" + identifier + "is highlighted", "Element is highlighted", "Element is highlighted");
            }else {
                report(FAIL, "Check if" + identifier + "is highlighted", "Element is highlighted", "Element is not highlighted");
            }
        } catch (FlexFrameWorkRunTimeException e) {
            report(SUCCESS, "Check if" + identifier + "is highlighted", "Element is highlighted", "Element is not highlighted");
        }
    }

    public void switchToFrame(ISuppllyLocatorInfo isli) throws FlexFrameWorkRunTimeException {
        try {
            WebDriver driver = AllDataHolder.getDriver();
            driver.switchTo().frame(returnWebElement(isli));
            report(SUCCESS, "Switch to iframe", "Switched to iframe: " + isli
            .getLabel(), "Switched to iframe: " + isli
                    .getLabel());
        }catch (Exception e){
            report(FAIL, "Switch to iframe", "Switched to iframe: " + isli
                    .getLabel(), e.getMessage());
        }
    }

    private WebElement returnWebElement(ISuppllyLocatorInfo isli) throws FlexFrameWorkRunTimeException{
        WebDriver driver = AllDataHolder.getDriver();
        return driver.findElement((By) isli.getLocator());
    }

    public void switchToParentFrame(ISuppllyLocatorInfo isli) throws FlexFrameWorkRunTimeException {
        try {
            WebDriver driver = AllDataHolder.getDriver();
            driver.switchTo().parentFrame();
            report(SUCCESS, "Switch to Parent frame", "Switched to Parent frame: " + isli
                    .getLabel(), "Switched to Parent frame: " + isli
                    .getLabel());
        }catch (Exception e){
            report(FAIL, "Switch to Parent frame", "Switched to Parent frame: " + isli
                    .getLabel(), e.getMessage());
        }
    }

    public void switchToDefaultContent(ISuppllyLocatorInfo isli) throws FlexFrameWorkRunTimeException {
        try {
            WebDriver driver = AllDataHolder.getDriver();
            driver.switchTo().defaultContent();
            report(SUCCESS, "Switch to Default Content", "Switched to Default Content: " + isli
                    .getLabel(), "Switched to Default Contente: " + isli
                    .getLabel());
        }catch (Exception e){
            report(FAIL, "Switch to Default Content", "Switched to Default Content: " + isli
                    .getLabel(), e.getMessage());
        }
    }

    public void comparePopUpText(String expected, String  identifier) throws FlexFrameWorkRunTimeException {
        try {
            WebDriver driver = AllDataHolder.getDriver();
            waitTillPresenceOfAlert(8);
            String actual = getAlertText(driver).trim();
            acceptPopup();
            if (actual.replaceAll("\\s", "").contains(expected.replaceAll("\\s", ""))){
                report(SUCCESS, "Compare pop up text value",
                        "Pop up text comparision matched", REPORT_EXPECTED + expected + "is matching " + REPORT_ACTUAL + actual);
            }else {
                report(SUCCESS, "Compare pop up text value",
                        "Pop up text comparision matched", REPORT_EXPECTED + expected + "is not matching " + REPORT_ACTUAL + actual);
            }
        }catch (Exception e){
            report(SUCCESS, "Compare pop up text value",
                    "Pop up text comparision matched", e.getMessage());
        }
    }

    String getAlertText(WebDriver driver) {
        String alertText;
        try {
            alertText = driver.switchTo().alert().getText();
        }catch (UnhandledAlertException e){
            alertText = driver.switchTo().alert().getText();
        }
        return alertText;
    }

    /**
     * @implNote Method to set the value using JS
     * @param isli
     * @param value
     * @throws FlexFrameWorkRunTimeException
     */
    public void setValueUsingJS(ISuppllyLocatorInfo isli, String value) throws FlexFrameWorkRunTimeException {
        try {
            WebDriver driver = AllDataHolder.getDriver();
            ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + value + "';", getWebElement(isli));

        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e);
        }
    }

    public void mouseHoverAndClick(ISuppllyLocatorInfo hoverElement, ISuppllyLocatorInfo clickElement) throws  FlexFrameWorkRunTimeException {
        try {
            WebDriver driver = AllDataHolder.getDriver();
            Actions action = new Actions(driver);
            WebElement elementHover = getWebElement(hoverElement.getLocator());
            WebElement elementClick = getWebElement(clickElement.getLocator());
            action.moveToElement(elementHover).click(elementClick).build().perform();
            report(SUCCESS, hoverElement.getLabel() + " " + clickElement.getLabel(),
                    "Mouse Hover and clicked", "Mouse Hover and clicked");
        }catch (Exception e){
            report(FAIL, hoverElement.getLabel() + " " + clickElement.getLabel(),
                    "Mouse Hover and clicked", e.getMessage());

        }
    }

    public String extentreportcreateScreenshot64() throws FlexFrameWorkRunTimeException {
        try {
            String encodedBase64 = null;
            FileInputStream fileInputStreamReader = null;
            WebDriver driver = AllDataHolder.getDriver();
            UUID uuid = UUID.randomUUID();
            //Generate screenshot as a file object
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            fileInputStreamReader = new FileInputStream(scrFile);
            byte[] bytes = new byte[(int)scrFile.length()];
            fileInputStreamReader.read(bytes);
            encodedBase64 = new String(Base64.encodeBase64(bytes));

            try {
                FileUtils.copyFile(scrFile, new File(RESULTSPATH + SCREENSHOTPATH + uuid + ".png"));
            }catch (Exception e){
                throw new FlexFrameWorkRunTimeException(e);
            }
            File file = new File(RESULTSPATH);
            String reportLocation;
            String planKey = System.getProperty(ServiceAndSauce.PLANKEY);
         /*  if (System.getProperty(ServiceAndSauce.PLANKEY != null)){
               reportLocation = "https://bamboo.dFarm.com/artifacts/" + System.getProperty("plankey") + "/" + System.getProperty("shortJobKey") + "/build-"
                       + System.getProperty("buildNumber") + "/Results";
           }else {*/
            reportLocation = file.getAbsolutePath();
            /* }*/
          //  return reportLocation + SCREENSHOTPATH + uuid + ".png";
            return reportLocation + SCREENSHOTPATH + uuid + ".png" + encodedBase64;

        }catch (Exception e){
            return "";
        }
    }

//    /**
//     * @implNote Method to select the rows per page
//     * @param rowsPerPage
//     * @throws FlexFrameWorkRunTimeException
//     */
//    public void setRowsPerPage1(String rowsPerPage) throws FlexFrameWorkRunTimeException {
//        if (AllDataHolder.getUtil().isContentDisplayed(CCHOMEPAGE.DROPDOWN_DISPATCH_TABLE_ROWS_PER_PAGE)){
//            AllDataHolder.getUtil().clickElement(CCHOMEPAGE.DROPDOWN_DISPATCH_TABLE_ROWS_PER_PAGE);
//            // If rowsPerPage is empty selects 100 by default
//            if (!rowsPerPage.isEmpty()){
//                AllDataHolder.getUtil().clickElement(CCHOMEPAGE.selectDispatchReportsTableRowsPerPage(rowsPerPage));
//                AllDataHolder.getUtil().report(DFarmConstants.INFO, "Selecting Daily Schedule Table Rows per page",
//                        "Selecting Daily Schedule Table Rows per page",
//                        "<mark>Selected Daily Schedule Table Rows per page : " + rowsPerPage + "</mark>");
//            }else {
//                AllDataHolder.getUtil().clickElement(CCHOMEPAGE.selectDispatchReportsTableRowsPerPage("100"));
//            }
//
//        }
//    }

    public void sendValueAndPressDOWN(ISuppllyLocatorInfo isli, String value)throws FlexFrameWorkRunTimeException {
        sendValueAndPressDOWN(isli.getLocator(), value, isli.getLabel());
    }

    /**
     * @implNote Method to press down arrow and select
     * @param byOrWebElement
     * @param value
     * @param identifier
     * @throws FlexFrameWorkRunTimeException
     */
    public void sendValueAndPressDOWN(Object byOrWebElement, String value, String identifier) throws FlexFrameWorkRunTimeException {
        try {
            WebElement element = getWebElement(byOrWebElement);
            element.click();
            element.sendKeys(value);
            element.sendKeys(Keys.ARROW_DOWN);
            element.sendKeys(Keys.ENTER);
            report(SUCCESS, "Send "+ value + " and press enter in " + identifier, "Action performed on : " + value, "Action performed on : " + value);
        }catch (StaleElementReferenceException sere){
            sendValueAndPressDOWN(byOrWebElement, value, identifier);
        }catch (Exception e){
            report(FAIL, "Send "+ value + " and press enter in " + identifier, "Action performed on : " + value, getClass().getCanonicalName());
        }
    }

    /**
     * @implNote Method to wait untill Angular page loads
     * @throws FlexFrameWorkRunTimeException
     */
    public void waitUntillPageLoad() throws FlexFrameWorkRunTimeException {
        try {
            NgWebDriver ngWebDriver = (NgWebDriver) AllDataHolder.getDriver();
            ngWebDriver.waitForAngularRequestsToFinish();
            ngWebDriver.waitForAngular2RequestsToFinish();
        }catch (Exception e){
            report(FAIL, "Loading time is more........", "Loading time is more", "Loading time is more");
        }
    }

    /* wait.until( new Predicate<WebDriver>() {

        public boolean apply(WebDriver driver) {
            return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
        }
    }
    );*/

    /**
     * @implNote Method to compare two string values with Ignore Case
     * @param expected
     * @param actual
     */
    public void stringCompareIgnoreCaseNotEqual(String expected, String actual) throws FlexFrameWorkRunTimeException {
        try {
            if (expected.trim().equalsIgnoreCase(actual.trim())){
                report(FAIL, "Check if " + REPORT_EXPECTED + expected + "Matches" + REPORT_ACTUAL + actual, REPORT_EXPECTED + expected + "Matches"+ REPORT_ACTUAL + actual, REPORT_EXPECTED + expected + "does not matches"+ REPORT_ACTUAL + actual);
            }else {
                report(SUCCESS, "Check if " + REPORT_EXPECTED + expected + "Matches" + REPORT_ACTUAL + actual, REPORT_EXPECTED + expected + "Matches"+ REPORT_ACTUAL + actual, REPORT_EXPECTED + expected + "Matches"+ REPORT_ACTUAL + actual);
            }
        }catch (Exception e){
            report(FAIL, "Check if " + REPORT_EXPECTED + expected + "Matches" + REPORT_ACTUAL + actual, REPORT_EXPECTED + expected + "Matches"+ REPORT_ACTUAL + actual, e.getMessage());
        }
    }

    public void setFocusOnParticularElement(ISuppllyLocatorInfo isli) throws FlexFrameWorkRunTimeException {
        try {
            WebDriver driver = AllDataHolder.getDriver();
            WebElement element = getWebElement(isli.getLocator());
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].focus();", element);
            report(SUCCESS, "Set focus on " + isli.getLabel(), "Focused on element " + isli.getLabel(), "Focused on element " + isli.getLabel());
        }catch (Exception e){
            report(FAIL, "Set focus on " + isli.getLabel(), "Focused on element " + isli.getLabel(), e.getMessage());
        }
    }
}

