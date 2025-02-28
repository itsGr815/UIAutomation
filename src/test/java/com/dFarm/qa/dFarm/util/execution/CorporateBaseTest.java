package com.dFarm.qa.dFarm.util.execution;

import com.dFarm.qa.dFarm.constants.*;
import com.dFarm.qa.dFarm.initializer.DriverMode;
import com.dFarm.qa.dFarm.util.reporters.*;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.io.*;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Optional;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class CorporateBaseTest {

    //Todo
    private static final Logger logger = LoggerFactory.getLogger(BaseTest.class);
    private static WebDriver driver;
    public String htmlReportName;
    protected ITestContext ctx;
    DriverMode driverMode = new DriverMode();
    public static HashMap<String, ArrayList<String>> testPlanCaseMapper;

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite(ITestContext ctx) throws FlexFrameWorkRunTimeException, IOException {
        logger.info("Starting new test suite execution for "+ ctx.getSuite());
        if (ctx.getCurrentXmlTest().getParameter(ServiceAndSauce.BROWSER) != null){
            logger.error("Perform browser found in testng XML. this value is ignored and can overridden by either" +
                    "-DBrowser=chrome vm arguments or bu updating config/config.properties");
        }
        // Delete failed and skipped files for fresh run
        deleteSkippedAndFailedFiles();
    }

    @BeforeClass(alwaysRun = true)
    public synchronized void beforeClass(ITestContext ctx) throws FlexFrameWorkRunTimeException {
       /* if (!AllDataHolder.getIsDebug()){
            CentralizedReporter report = new CentralizedReporter();
            // Getting full class name path from testng. Note, getClass is a polymorphic call, so extending should make the reflection still work
            String absoluteClassName = this.getClass().getName();
            // Fetching only the class name from class object
            String className = absoluteClassName.substring(absoluteClassName.lastIndexOf('.') + 1);
            logger.info("Executing class: " + className);
            // checking for the descriptive test case name from enum
            String testPlanName = Optional.ofNullable(TESTPLAN_DICTIONARY.getTestPlanName(className)).orElse(className);
            // To check and if not set if there is any existing reporting id for the class
            if (!Optional.ofNullable(AllDataHolder.getTestPlanIdMap(testPlanName)).isPresent()){
                AllDataHolder.setTestPlan(testPlanName, TestPlan.setTestPlaDetails(ctx, testPlanName));
                AllDataHolder.setTestPlanMapId(testPlanName, report.getTestPlanDetails(testPlanName));
            }
        }*/
    }

    @BeforeMethod(alwaysRun = true)
    public void testSetup(Method method, ITestContext ctx){
        try {
            //Store test case id in Map and create its corresponding
            getTestMethodsAndCreateReport(method, ctx);
            //Set context
            this.ctx = ctx;
            //Initialize driver
            checkDriver();
            //Launch URL
            launchURL();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * @implNote After method to logout and report the test status
     * @param ctx
     * @param method
     */
    @AfterMethod
    public void endTest(ITestContext ctx, Method method){
        logger.info("Ending test execution for " + AllDataHolder.getCurrentTestCaseId());
        //Setting test ebd tine to the report
        AllDataHolder.setTestEndTime(GlobalHelpers.getCurentCstReportTime());
        //End Test reporting
        try {
            //Post report to centralized reporting system
            // if isDebug = false
            if (!AllDataHolder.getIsDebug()){
                //If its a pass, dont retry it again
                if(isTestSuccess()){
                    AllDataHolder.setIsLastRun(true);
                }
                if (AllDataHolder.getIsLastRun()){
                    AllDataHolder.setTestCaseStatus(Integer.toString(ExtentReporting.getStatus()));
                    //Getting full class name path from testng, Note, getClass is a polymorphic call,
                    // so extending make the reflection still work
                    String absoluteClassName = this.getClass().getName();
                    // Fetching only the class name from the class object
                    String className = absoluteClassName.substring(absoluteClassName.lastIndexOf(".") + 1);
                    //Checking the descriptive test case from enum
                    String testPlanName = Optional.ofNullable(TESTPLAN_DICTIONARY.getTestPlanName(className)).orElse(className);
                    if (!isTestSuccess()){
                        //Setting end time
                        AllDataHolder.testPlanMap.get(testPlanName).setTestPLanExecutionStatus(Integer.toString(ExtentReporting.getStatus()));
                    }
                    CentralizedReporter report = new CentralizedReporter();
                    report.saveTestTestResults(testPlanName, method);
                }
            }
            AllDataHolder.userIdMap.remove(Thread.currentThread().getId());
            //Log failed test cases infile "FailedAndSkippedTestCase.txt"
            createForFailedTestCase(method);
            if (!AllDataHolder.getIsDebug()){
                //Report status into Excel file
                writeStatusReportToExcel();
            }

            //Extent report end test reporting
            ExtentReporting.endTest();
            //Set first login to true for login retry
        }catch (Exception e){
            logger.error(e.getMessage());
        }finally {
            AllDataHolder.setFirstLogin(true);
        }

        //Quit driver and report back to saucelabs
        try {
            if (AllDataHolder.getDriver() !=null){
                WebDriver driver = AllDataHolder.getDriver();
                //Report to sauce and quite the driver instance
                if (DriverMode.isSauce()){
                    JavascriptExecutor executor = (JavascriptExecutor) driver;
                    executor.executeScript("Sauce:job-result=" + (isTestSuccess() ? "passed" : "failed"));
                    //Quit driver
                    driver.quit();
                    logger.info("Sauce test status updated for : " + AllDataHolder.getCurrentTestCaseId());
                    AllDataHolder.removeDriver(driver);
                }
            }
        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }

    /**
     * @implSpec  Capture Test Paln end
     * @param ctx
     */
    @AfterClass(alwaysRun = true)
    public synchronized void afterClass(ITestContext ctx){
        if (!AllDataHolder.getIsDebug()){
            // Getting full class name path from testng. Note, getClass is a polymorphic call, so extending should make the reflection still work
            String absoluteClassName = this.getClass().getName();
            // Fetching only the class name from class object
            String className = absoluteClassName.substring(absoluteClassName.lastIndexOf('.') + 1);
            logger.info("Executing class: " + className);
            // checking for the descriptive test case name from enum
            String testPlanName = Optional.ofNullable(TESTPLAN_DICTIONARY.getTestPlanName(className)).orElse(className);
            //Setting end time
            AllDataHolder.testPlanMap.get(testPlanName).setTestPlanEndDateTime(GlobalHelpers.getCurentCstReportTime());
        }
    }

    /**
     * @implSpec To quit all the driver instances once the test run is over
     * @throws FlexFrameWorkRunTimeException
     */
    @AfterSuite(alwaysRun = true)
    public void cleaner() throws FlexFrameWorkRunTimeException {
        //Post Test suite results to Centralized reporting system
        if (!AllDataHolder.getIsDebug()){
            CentralizedReporter saveResults = new CentralizedReporter();
            //Saving test plan results
            for (Map.Entry<String, TestPlan> entry : AllDataHolder.testPlanMap.entrySet()){
                AllDataHolder.testPlanMap.get(entry.getKey()).setTestPlanResultId(saveResults.saveTestPlanResults(entry.getKey()));
            }
            saveResults.saveTestSuiteResults();
        }
        //Quitting all Testng threads
        for (Map.Entry<Long, WebDriver> entry : AllDataHolder.driverdMap.entrySet()){
            entry.getValue().quit();
        }
        logger.info("Ending test suite execution");
    }

    /**
     * @implNote To reuse the existing driver or initialize a new driver for each test in saucelabs.
     */
    public void checkDriver() throws FlexFrameWorkRunTimeException {
        //Check if the required number of drivers has been instantiated based on the testng thread count
        if (!DriverMode.isSauce()){
            if (driver != null && AllDataHolder.driverdMap.keySet().size() == ctx.getSuite().getXmlSuite().getThreadCount()){
                driver = AllDataHolder.getDriver();
                logger.info("Existing driver " + driver + " reused for : " + AllDataHolder.getCurrentTestCaseId());
            }else {
                driver = initializeDriver(ctx);
                logger.info("New Driver " + driver + " allocated for :" + AllDataHolder.getCurrentTestCaseId());
            }
        }else {
            driver = initializeDriver(ctx);
            logger.info("New Driver " + driver + " allocated for :" + AllDataHolder.getCurrentTestCaseId());
        }
    }

    public WebDriver initializeDriver(ITestContext ctx) throws FlexFrameWorkRunTimeException {
        WebDriver driverWeb = null;
        //To check if the browser entered is supported or not
        if (!ArrayUtils.contains(DFarmConstants.SUPPORTEDBROWSERS, AllDataHolder.getBrowser())){
            logger.error(DFarmConstants.SUPPORTEDBROWSERS_ERROR);
        }
        try {
            driverWeb = driverMode.getDriverFactory().getDriverFactory();
        }catch (Exception e){
            AllDataHolder.setIsLastRun(true);
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            AllDataHolder.getUtil().report(3, "Check for any driver exception", "No driver exception found",
                    "Driver Initialization failed, Stacktrace: "+ sw);
        }
        AllDataHolder.setDriver(driverWeb);
        if (driverWeb == null){
            Assert.fail();
        }else {
            ctx.setAttribute(DFarmConstants.DRIVER, driverWeb);
            // Setting global implicit wait time
            driverWeb.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            // Nor maximizing window for firefox browser
            if (!AllDataHolder.getBrowser().equals(DriverConstants.FIREFOXBROWSER)){
                driverWeb.manage().window().maximize();
            }
            //Setting the zoom level to default level for IE browser
            if (AllDataHolder.getBrowser().equals(DriverConstants.IEBROWSER)){
                driverWeb.findElement(By.tagName("html")).sendKeys(Keys.chord(Keys.CONTROL, "0"));
            }
        }
        return driverWeb;
    }

    private synchronized void getTestMethodsAndCreateReport(Method method, ITestContext ctx) throws FlexFrameWorkRunTimeException{
        //Fetching and Setting Module for skipping testcase
        try {
            List<String> modules = new ArrayList<>();
            Object[] userTypeObject = method.getAnnotation(Test.class).groups();
            for (Object obj : userTypeObject){
                modules.add(obj.toString());
            }
            AllDataHolder.setGroups(modules);
        }catch (NullPointerException npe){
            logger.error(npe.getMessage());
        }
        AllDataHolder.setCurrentTestCaseId(method.getAnnotation(Test.class).testName());
        //If test case exists then increase the retry count else insert new record with initial count of 0
        if (AllDataHolder.getRetryCountMapper().containsKey(AllDataHolder.getCurrentTestCaseId())){
            AllDataHolder.setTestCaseRetryCount(AllDataHolder.getTestCaseRetryCount() + 1);
        }else {
            AllDataHolder.setTestCaseRetryCount(0);
        }
        logger.info("Starting to execute method: " + AllDataHolder.getCurrentTestCaseId());
        //Report initialization
        reportInitializer(ctx, method);
        if (!AllDataHolder.getIsDebug()){
            // Creating the excel file
            try {
                excelStatusReport();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public void reportInitializer(ITestContext ctx, Method method) throws  FlexFrameWorkRunTimeException{
        //Initialize Extent report
        initializeExtentReports(ctx);
        //Start Extent report
        ExtentReporting.createTest(AllDataHolder.getCurrentTestCaseId(), method, getGroupsForExecution());
        if (!AllDataHolder.getIsDebug()){
            TestCase testCase = new TestCase();
            testCase.setTestSteps(new ArrayList<>());
            AllDataHolder.setTestCase(testCase);
            CentralizedReporter reporter = new CentralizedReporter();
            reporter.getGroupId();
        }
    }

    public static String[] getGroupsForExecution() throws FlexFrameWorkRunTimeException{
        try {
            List<String> groups = new ArrayList<>(AllDataHolder.getGroups());
            groups.add(AllDataHolder.getBrowser());
            return groups.toArray(new String[groups.size()]);
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e);
        }
    }
    /**
     * @implNote Method to initialize Extent reporting
     * @param ctx
     */
    public void initializeExtentReports(ITestContext ctx){
        int number;
        Random r = new Random();
        number = r.nextInt(9999);
        if (ExtentManager.getInstance() == null){
            int threadCount = (ctx != null) ? ctx.getSuite().getXmlSuite().getThreadCount() : 0;
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yy");
            String formattedDate = formatter.format(date);
            htmlReportName = "results/" + AllDataHolder.getEnvironment() + "_ExecutionReport_"
                    + AllDataHolder.getBrowser() + "_" + formattedDate + "_" + number + ".html";
            ExtentManager.createInstance(threadCount, htmlReportName);
        }
    }

    /**
     * @implNote Method to get Status Code
     * @param statusCode
     * @return
     */
    public String getStatusCode(int statusCode){
        switch (statusCode){
            case 3:
                return "PASS";
            case 2:
                return "FAIL";
            case 9:
                return "SKIP";
            case 10:
                return "WARNING";
            case 8:
                return "ERROR";
            case 11:
                return "FATAL";
            case 7:
                return "INFO";
            default:
                return "";

        }
    }

    /**
     * @implNote Method to Initialize test reporting
     * @param ctx
     * @throws FlexFrameWorkRunTimeException
     */
    public synchronized void getIds(ITestContext ctx) throws FlexFrameWorkRunTimeException{
        try {
            CentralizedReporter report = new CentralizedReporter();
            report.getGlobalIds(ctx);
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e);
        }
    }

    /**
     * @implNote  Method to delete FailedAndSkipped.txt
     * @throws IOException
     */
    public void deleteSkippedAndFailedFiles() throws IOException {
        //Delete the FailedAndSkipped.txt for fresh run
        deleteFile("FailedAndSkipped.txt");
    }

    /**
     * @implNote Method to delete any File
     * @param fileName
     * @throws IOException
     */
    public void deleteFile(String fileName) throws IOException {
        try {
            Files.deleteIfExists(Paths.get(fileName));
        }catch (IOException | SecurityException e){
            System.err.println(e);
        }
    }


    /**
     * @implSpec Method to know the test status
     * @return
     */
    public boolean isTestSuccess(){
        int status = ExtentReporting.getStatus();
        if (status == ReportingConstants.PASS){
            return true;
        }else {
            ReportingConstants.isSuiteFail = true;
            return false;
        }
    }

    /**
     * @implSpec  Method to create a file that hold all failed & skip test cases reported by Extent Report.
     * Note: this is not used to hold TestNG failed tets cases
     * @param method
     * @throws FlexFrameWorkRunTimeException
     * @throws IOException
     */
    private synchronized void createForFailedTestCase(Method method) throws FlexFrameWorkRunTimeException, IOException {
        try {
            int status = ExtentReporting.getStatus();
            String fileName = "FailedAndSkipped.txt";
            if (status !=ReportingConstants.PASS && AllDataHolder.getIsLastRun()){
                try (FileWriter fw = new FileWriter(fileName, true)){
                    fw.write('\n' + ">include name=\"" + method.getName() + "\" />");
                }catch (IOException ioEx){
                    throw new IOException(ioEx.getMessage());
                }
            }
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e);
        }
    }

    /**
     * @implNote Method to generate Excel Status Report
     * @throws FlexFrameWorkRunTimeException
     * @throws IOException
     */
    public synchronized void excelStatusReport() throws FlexFrameWorkRunTimeException, IOException {
        if (!AllDataHolder.isIsExcelCreated()){
            //Create blank work book
            XSSFWorkbook workbook = new XSSFWorkbook();
            // Create a blank sheet
            XSSFSheet writeSpreadSheet = workbook.createSheet("Execution_Status");
            //Writing the workbook int file system
            String fileName = "results/" + AllDataHolder.getEnvironment() + "_ExecutionReport_"
                    + AllDataHolder.getBrowser() + ".xlsx";
            AllDataHolder.setExcelFileName(fileName);
            XSSFRow row;
            row = writeSpreadSheet.createRow(0);
            String [] testCaseDetails = new String[4];
            testCaseDetails[0] = "Test_Case_Name";
            testCaseDetails[1] = "Method";
            testCaseDetails[2] = "Class";
            testCaseDetails[3] = "Status";
            int cellid = 0;
            for (String obj : testCaseDetails){
                XSSFCell cell = row.createCell(cellid++);
                cell.setCellValue(obj);
            }
            FileOutputStream out = new FileOutputStream(new File(fileName));
            workbook.write(out);
            out.close();
            AllDataHolder.setIsExcelCreated(true);
        }
    }

    /**
     * @implNote Method to write steps into Excel file
     * @throws IOException
     */
    public synchronized void writeStatusReportToExcel() throws IOException {
        XSSFRow row;
        FileInputStream file = new FileInputStream(new File(AllDataHolder.getExcelFileName()));
        XSSFWorkbook readWorkBook = new XSSFWorkbook(file);
        XSSFSheet readSpreadsheet = readWorkBook.getSheetAt(0);
        Iterator<Row> rowIterator = readSpreadsheet.iterator();
        int rowid = 0;
        String status = getStatusCode(ExtentReporting.getStatus());
        //Getting full class name from testng. Note, getClass is a polymorphic call, so extending should make the reflection still work
        String absoluteClassName = this.getClass().getName();
        // Fetching only the class name path from class object
        String className = absoluteClassName.substring(absoluteClassName.lastIndexOf(".") + 1);
        boolean cellValueBlank = false;
        while (rowIterator.hasNext() && !cellValueBlank){
            rowid++;
            Iterator<Cell> cellIterator = rowIterator.next().cellIterator();
            while (cellIterator.hasNext()){
                cellValueBlank = true;
                break;
            }
        }
        //Create row object
        row = readSpreadsheet.createRow(rowid);
        String[] testCaseDetails = new String[4];
        testCaseDetails[0] = AllDataHolder.getCurrentTestCaseId();
        testCaseDetails[1] = AllDataHolder.getTestData().get(AllDataHolder.getCurrentTestCaseId(), TestDataConstants.COLUMNNAME_METHOD);
        testCaseDetails[2] = className;
        testCaseDetails[3] = status;
        int cellid = 0;
        for (String obj : testCaseDetails){
            Cell cell = row.createCell(cellid++);
            cell.setCellValue(obj);
        }
        FileOutputStream out = new FileOutputStream(new File(AllDataHolder.getExcelFileName()));
        readWorkBook.write(out);
        out.close();

    }

    public static void launchURL() throws FlexFrameWorkRunTimeException{
        try {
            String url = AllDataHolder.getUrl();
            if(url.equalsIgnoreCase("NA")){
                url = AllDataHolder.getTestData().get(AllDataHolder.getCurrentTestCaseId(), TestDataConstants.APP_URL);
                AllDataHolder.setUrl(url);
            }
            AllDataHolder.getUtil().navigateToURL(url);
            // Set the parent window handle
            AllDataHolder.setParentWindowHandle(AllDataHolder.getDriver());
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e);
        }
    }

}
