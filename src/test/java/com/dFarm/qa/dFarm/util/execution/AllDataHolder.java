package com.dFarm.qa.dFarm.util.execution;

import com.dFarm.qa.dFarm.constants.ReportingConstants;
import com.dFarm.qa.dFarm.initializer.ConfigInitializer;
import com.dFarm.qa.dFarm.pageobjects.CorporateSite.*;
import com.dFarm.qa.dFarm.pageobjects.Global.GettersAndSetters;
import com.dFarm.qa.dFarm.pageobjects.Global.PCGettersAndSetters;
import com.dFarm.qa.dFarm.pageobjects.Prod.AdminPackhouse;
import com.dFarm.qa.dFarm.pageobjects.Prod.BalajiFarmer;
import com.dFarm.qa.dFarm.pageobjects.Prod.Farmer;
import com.dFarm.qa.dFarm.pageobjects.marketplace.MarkeplaceHomepage;
import com.dFarm.qa.dFarm.util.datahandler.TestData;
import com.dFarm.qa.dFarm.util.execution.global.ExportSaving;
import com.dFarm.qa.dFarm.util.reporters.TestCase;
import com.dFarm.qa.dFarm.util.reporters.TestPlan;
import com.dFarm.qa.dFarm.util.reporters.WSCaller;
import com.galenframework.reports.GalenTestInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class AllDataHolder {

    //Variables
    public static HashMap<Long, WebDriver> driverdMap = new HashMap<Long, WebDriver>();
    public static HashMap<Long, String> testCaseIdMap = new HashMap<>();
    public static HashMap<Long, Boolean> isLastRunMap = new HashMap<Long, Boolean>();
    public static HashMap<Long, String> userIdMap = new HashMap<>();
    public static HashMap<Long, String> typeMap = new HashMap<>();
    public static HashMap<Long, Integer> groupIdMap = new HashMap<>();
    public static HashMap<String, String> testPlanIdMap = new HashMap<String, String>();
    public static HashMap<Long, List<String>> groupMap = new HashMap<>();
    public static HashMap<Long, String> testStatusMap = new HashMap<>();
    public static HashMap<Long, String> testStartTimeMap = new HashMap<>();
    public static HashMap<Long, String> testEndTimeMap = new HashMap<>();
    public static HashMap<Long, String> LoginDataMap = new HashMap<>();
    public static HashMap<Long, String> ParentWindowHandleMap = new HashMap<>();
    public static TestCase testCase = new TestCase();
    public static HashMap<String, TestPlan> testPlanMap = new HashMap<>();
    public static HashMap<Long, TestCase> centralizedReportMap = new HashMap<>();
    public static HashMap<Long, Integer> testCaseRetryCountMap = new HashMap<>();
    public static String currentUser;
    public static String browser;
    static String environment, url, getTestDataServer, importFileLocation;
    static boolean isServiceData;
    static boolean isFastFail;
    static boolean isDebug;
    static boolean isSauce;
    static boolean isNewLogin;
    static boolean isExcelCreated;
    static String exelPath;
    static String centralizedReportRegion;
    static String platform;
    static String browserVersion;
    static String maxDuration;
    static String commandTimeOut;
    static String sauseUser;
    static String sauceKey;
    static String idleTimeOut;
    static String screenResolution;
    static TestData testData;
    static String globalReportingIds;
    static String appVersion;
    static String yamlPath;
    static String weightUnit;
    static int retryCount;
    static String galenSpaceCommonPath;
    static String excelFileName;
    static String downloadFileLocationPath;
    private static List<GalenTestInfo> galenTestInfo = new LinkedList<>();
    private static Boolean firstLogin = true;
    private static Boolean loginFailure = false;
    private static CommonUtil util;
    private static GlobalHelpers globalHelpers;
    private static WSCaller wsCaller;
    private static ReportingConstants reportingConstants;
    private static GettersAndSetters gettersAndSetters;
    private static PCGettersAndSetters pcGettersAndSetters;
    private static ExportSaving exportSaving;
    private static MarkeplaceHomepage markeplaceHomepage;
    private static Farmer farmer;
    private static BalajiFarmer balajiFarmer;
    private static AdminPackhouse packhouse;
    private static CorporateSiteHome corporateSiteHome;
    private static AboutUS aboutUS;
    private static Blogs blogs;
    private static Careers careers;
    private static CaseStudies caseStudies;
    private static DynamicMarketplace dynamicMarketplace;
    private static FSMA204 fsma204;
    private static News news;
    private static PrecisionTracing precisionTracing;
    private static SCMFinance scmFinance;
    private static SupplyChainOptimization supplyChainOptimization;
    private static Technologies technologies;


    static {
        try {
            ConfigInitializer.setConfigProperties();
        }catch (Exception e){
            e.printStackTrace();
        }
        setCurrentUser();
        testData = TestData.getTestData();
        setTestData(testData);
        setUtil(new CommonUtil());
        setGlobalHelpers(new GlobalHelpers());
        setWsCaller(new WSCaller());
        setMarkeplaceHomepage(new MarkeplaceHomepage());
        setFarmer(new Farmer());
        setBalajiFarmer(new BalajiFarmer());
        setPackhouse(new AdminPackhouse());
        setCorporateSiteHome(new CorporateSiteHome());
        setAboutUS(new AboutUS());
        setBlogs(new Blogs());
        setCareers(new Careers());
        setCaseStudies(new CaseStudies());
        setDynamicMarketplace(new DynamicMarketplace());
        setFsma204(new FSMA204());
        setNews(new News());
        setPrecisionTracing(new PrecisionTracing());
        setScmFinance(new SCMFinance());
        setSupplyChainOptimization(new SupplyChainOptimization());
        setTechnologies(new Technologies());


    }

    public static GettersAndSetters getGettersAndSetters() {
        return gettersAndSetters;
    }

    public static void setGettersAndSetters(GettersAndSetters gettersAndSetters) {
        AllDataHolder.gettersAndSetters = gettersAndSetters;
    }

    public static ReportingConstants getReportingConstants() {
        return reportingConstants;
    }

    public static void setReportingConstants(ReportingConstants reportingConstants) {
        AllDataHolder.reportingConstants = reportingConstants;
    }

    public static WSCaller getWsCaller() {
        return wsCaller;
    }

    public static void setWsCaller(WSCaller wsCaller) {
        AllDataHolder.wsCaller = wsCaller;
    }

    public static GlobalHelpers getGlobalHelpers() {
        return globalHelpers;
    }

    public static void setGlobalHelpers(GlobalHelpers globalHelpers) {
        AllDataHolder.globalHelpers = globalHelpers;
    }

    public static synchronized WebDriver getDriver() {
        return driverdMap.get(Thread.currentThread().getId());
    }

    public static synchronized void setDriver(WebDriver driver) {
        driverdMap.put(Thread.currentThread().getId(), driver);
    }
    public static synchronized void removeDriver(WebDriver driver) {
        driverdMap.remove(Thread.currentThread().getId(), driver);
    }

    public static synchronized void removeReports(TestCase testCase) {
        driverdMap.remove(Thread.currentThread().getId(), testCase);
    }
    public static synchronized String  getCurrentTestCaseId() {
        return testCaseIdMap.get(Thread.currentThread().getId());
    }

    public static void setCurrentTestCaseId(String testCaseId) {
        testCaseIdMap.put(Thread.currentThread().getId(), testCaseId);
    }

    public static synchronized boolean getIsLastRun() {
        return Optional.ofNullable(isLastRunMap.get(AllDataHolder.getCurrentTestCaseId())).orElse(false);
    }

    public static synchronized void setIsLastRun(Boolean flag) {
        isLastRunMap.put(Thread.currentThread().getId(), flag);
    }

    public static String  getUserId() {
        return userIdMap.get(Thread.currentThread().getId());
    }

    public static synchronized void setUserId(String userId) {
       userIdMap.put(Thread.currentThread().getId(), userId);
    }

    public static String  getType() {
        return typeMap.get(Thread.currentThread().getId());
    }

    public static synchronized void setType(String type) {
        typeMap.put(Thread.currentThread().getId(), type);
    }

    public static int getGroupIdMap() {
        return groupIdMap.get(Thread.currentThread().getId());
    }

    public static void setGroupIdMap(int groupId) {
        groupIdMap.put(Thread.currentThread().getId(), groupId);
    }

    public static String getTestPlanIdMap(String testPlanName) {
        return testPlanIdMap.get(testPlanName);
    }

    public static void setTestPlanMapId(String testPlanName, String testPlanId) {
      testPlanIdMap.put(testPlanName, testPlanId);
    }

    public static List<String> getGroups() {
        return groupMap.get(Thread.currentThread().getId());
    }

    public static void setGroups(List<String> groups) {
        groupMap.put(Thread.currentThread().getId(), groups);
    }

    public static String getTestCaseStatus() {
        return testStatusMap.get(Thread.currentThread());
    }

    public static synchronized void setTestCaseStatus(String testStatus) {
        testStatusMap.put(Thread.currentThread().getId(), testStatus);
    }

    public static String  getTestStartTime() {
        return testStartTimeMap.get(Thread.currentThread().getId());
    }

    public static synchronized void setTestStartTime(String testStartTime) {
        testStartTimeMap.put(Thread.currentThread().getId(), testStartTime);
    }

    public static String  getTestEndTime() {
        return testEndTimeMap.get(Thread.currentThread().getId());
    }

    public static synchronized void setTestEndTime(String  testEndTime) {
        testEndTimeMap.put(Thread.currentThread().getId(), testEndTime);
    }

    public static String getLoginData() {
        return LoginDataMap.get(Thread.currentThread().getId());
    }

    public static synchronized void setLoginData(String loginData) {
       LoginDataMap.put(Thread.currentThread().getId(), loginData);
    }

    public static String getParentWindowHandle() {
        return ParentWindowHandleMap.get(Thread.currentThread().getId());
    }

    public static void setParentWindowHandle(WebDriver driver) {
        ParentWindowHandleMap.put(Thread.currentThread().getId(), driver.getWindowHandle());
    }

    public static TestCase getTestCase() {
        return centralizedReportMap.get(Thread.currentThread().getId());
    }

    public static void setTestCase(TestCase testCase) {
        centralizedReportMap.put(Thread.currentThread().getId(), testCase);
    }

    public static void setTestPlan(String testPlanName, TestPlan testPlan) {
        testPlanMap.put(testPlanName, testPlan);
    }

    public static int getTestCaseRetryCount() {
        return testCaseRetryCountMap.get(Thread.currentThread().getId());
    }

    public static void setTestCaseRetryCount(int retryCount) {
        testCaseRetryCountMap.put(Thread.currentThread().getId(), retryCount);
    }
    public static HashMap getRetryCountMapper() {
        return testCaseRetryCountMap;
    }

    public static String getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser() {
        String currentUser = System.getProperty(ReportingConstants.USERNAME);
        if (currentUser.equalsIgnoreCase("SYSTEM") || currentUser.startsWith("$")){
            AllDataHolder.currentUser = "Bamboo";
        }else {
            AllDataHolder.currentUser = System.getProperty(ReportingConstants.USERNAME);
        }
    }

    public static String getBrowser() {
        return browser;
    }

    public static void setBrowser(String browser) {
        AllDataHolder.browser = browser;
    }

    public static String getEnvironment() {
        return environment;
    }

    public static void setEnvironment(String environment) {
        AllDataHolder.environment = environment;
        ConfigInitializer.env = environment;
    }

    public static String getUrl() {
        return url;
    }

    public static void setUrl(String url) {
        AllDataHolder.url = url;
    }

    public static String getGetTestDataServer() {
        return getTestDataServer;
    }

    public static void setGetTestDataServer(String getTestDataServer) {
        AllDataHolder.getTestDataServer = getTestDataServer;
        ConfigInitializer.testDataSvcServer = getTestDataServer;
    }

    public static String getImportFileLocation() {
        return importFileLocation;
    }

    public static void setImportFileLocation(String importFileLocation) {
        AllDataHolder.importFileLocation = importFileLocation;
    }

    public static boolean getIsServiceData() {
        return isServiceData;
    }

    public static void setIsServiceData(String isServiceData) {
        AllDataHolder.isServiceData = Boolean.parseBoolean(isServiceData);
    }

    public static boolean isFastFail() {
        return isFastFail;
    }

    public static void setIsFastFail(String  isFastFail) {
        AllDataHolder.isFastFail = Boolean.parseBoolean(isFastFail);
    }

    public static boolean getIsDebug() {
        return isDebug;
    }

    public static void setIsDebug(String isDebug) {
        AllDataHolder.isDebug = Boolean.parseBoolean(isDebug);
    }

    public static boolean isIsSauce() {
        return isSauce;
    }

    public static void setIsSauce(boolean isSauce) {
        AllDataHolder.isSauce = isSauce;
    }

    public static boolean isIsNewLogin() {
        return isNewLogin;
    }

    public static void setIsNewLogin(boolean isNewLogin) {
        AllDataHolder.isNewLogin = isNewLogin;
    }

    public static boolean isIsExcelCreated() {
        return isExcelCreated;
    }

    public static void setIsExcelCreated(boolean isExcelCreated) {
        AllDataHolder.isExcelCreated = isExcelCreated;
    }

    public static String getCentralizedReportRegion() {
        return centralizedReportRegion;
    }

    public static void setCentralizedReportRegion(String centralizedReportregion) {
        AllDataHolder.centralizedReportRegion = centralizedReportregion;
    }

    public static String getPlatform() {
        return platform;
    }

    public static void setPlatform(String platform) {
        AllDataHolder.platform = platform;
    }

    public static String getBrowserVersion() {
        return browserVersion;
    }

    public static void setBrowserVersion(String browserVersion) {
        AllDataHolder.browserVersion = browserVersion;
    }

    public static String getMaxDuration() {
        return maxDuration;
    }

    public static void setMaxDuration(String maxDuration) {
        AllDataHolder.maxDuration = maxDuration;
    }

    public static String getCommandTimeOut() {
        return commandTimeOut;
    }

    public static void setCommandTimeOut(String commandTimeOut) {
        AllDataHolder.commandTimeOut = commandTimeOut;
    }

    public static String getSauseUser() {
        return sauseUser;
    }

    public static void setSauseUser(String sauseUser) {
        AllDataHolder.sauseUser = sauseUser;
    }

    public static String getSauceKey() {
        return sauceKey;
    }

    public static void setSauceKey(String sauceKey) {
        AllDataHolder.sauceKey = sauceKey;
    }

    public static String getIdleTimeOut() {
        return idleTimeOut;
    }

    public static void setIdleTimeOut(String idleTimeOut) {
        AllDataHolder.idleTimeOut = idleTimeOut;
    }

    public static String getScreenResolution() {
        return screenResolution;
    }

    public static void setScreenResolution(String screenResolution) {
        AllDataHolder.screenResolution = screenResolution;
    }

    public static TestData getTestData() {
        return testData;
    }

    public static void setTestData(TestData testData) {
        AllDataHolder.testData = testData;
    }

    public static String getGlobalReportingIds() {
        return globalReportingIds;
    }

    public static void setGlobalReportingIds(String globalReportingIds) {
        AllDataHolder.globalReportingIds = globalReportingIds;
    }

    public static String getAppVersion() {
        return appVersion;
    }

    public static void setAppVersion(String appVersion) {
        AllDataHolder.appVersion = appVersion;
    }

    public static String getExelPath() {
        return exelPath;
    }

    public static void setExelPath(String exelPath) {
        AllDataHolder.exelPath = exelPath;
    }

    public static String getYamlPath() {
        return yamlPath;
    }

    public static void setYamlPath(String yamlPath) {
        AllDataHolder.yamlPath = yamlPath;
    }

    public static String getWeightUnit() {
        return weightUnit;
    }

    public static void setWeightUnit(String weightUnit) {
        AllDataHolder.weightUnit = weightUnit;
    }

    public static int getRetryCount() {
        return retryCount;
    }

    public static void setRetryCount(String  retryCount) {
        AllDataHolder.retryCount = StringUtils.isEmpty(retryCount) ? 0 : Integer.parseInt(retryCount);
    }

    public static String getGalenSpaceCommonPath() {
        return galenSpaceCommonPath;
    }

    public static void setGalenSpaceCommonPath(String galenSpaceCommonPath) {
        AllDataHolder.galenSpaceCommonPath = galenSpaceCommonPath;
    }

    public static String getExcelFileName() {
        return excelFileName;
    }

    public static void setExcelFileName(String excelFileName) {
        AllDataHolder.excelFileName = excelFileName;
    }

    public static String getDownloadFileLocationPath() {
        return downloadFileLocationPath;
    }

    public static void setDownloadFileLocationPath(String downloadFileLocationPath) {
        AllDataHolder.downloadFileLocationPath = downloadFileLocationPath;
    }

    public static List<GalenTestInfo> getGalenTestInfo() {
        return galenTestInfo;
    }

    public static void setGalenTestInfo(List<GalenTestInfo> galenTestInfo) {
        AllDataHolder.galenTestInfo = galenTestInfo;
    }

    public static Boolean getFirstLogin() {
        return firstLogin;
    }

    public static void setFirstLogin(Boolean firstLogin) {
        AllDataHolder.firstLogin = firstLogin;
    }

    public static Boolean getLoginFailure() {
        return loginFailure;
    }

    public static void setLoginFailure(Boolean loginFailure) {
        AllDataHolder.loginFailure = loginFailure;
    }

    public static CommonUtil getUtil() {
        return util;
    }

    public static void setUtil(CommonUtil util) {
        AllDataHolder.util = util;
    }

    public static boolean isNewLogin() {
        return isNewLogin;
    }



    public static PCGettersAndSetters getPcGettersAndSetters() {
        return pcGettersAndSetters;
    }

    public static void setPcGettersAndSetters(PCGettersAndSetters pcGettersAndSetters) {
        AllDataHolder.pcGettersAndSetters = pcGettersAndSetters;
    }


    public static ExportSaving getExportSaving() {
        return exportSaving;
    }

    public static void setExportSaving(ExportSaving exportSaving) {
        AllDataHolder.exportSaving = exportSaving;
    }

    public static MarkeplaceHomepage getMarkeplaceHomepage() {
        return markeplaceHomepage;
    }

    public static void setMarkeplaceHomepage(MarkeplaceHomepage markeplaceHomepage) {
        AllDataHolder.markeplaceHomepage = markeplaceHomepage;
    }

    public static Farmer getFarmer() {
        return farmer;
    }

    public static void setFarmer(Farmer farmer1) {
        AllDataHolder.farmer = farmer1;
    }

    public static BalajiFarmer getBalajiFarmer() {
        return balajiFarmer;
    }

    public static void setBalajiFarmer(BalajiFarmer balajiFarmer) {
        AllDataHolder.balajiFarmer = balajiFarmer;
    }

    public static AdminPackhouse getPackhouse() {
        return packhouse;
    }

    public static void setPackhouse(AdminPackhouse packhouse) {
        AllDataHolder.packhouse = packhouse;
    }

    public static CorporateSiteHome getCorporateSiteHome() {
        return corporateSiteHome;
    }

    public static void setCorporateSiteHome(CorporateSiteHome corporateSiteHome) {
        AllDataHolder.corporateSiteHome = corporateSiteHome;
    }

    public static AboutUS getAboutUS() {
        return aboutUS;
    }

    public static void setAboutUS(AboutUS aboutUS) {
        AllDataHolder.aboutUS = aboutUS;
    }

    public static Blogs getBlogs() {
        return blogs;
    }

    public static void setBlogs(Blogs blogs) {
        AllDataHolder.blogs = blogs;
    }

    public static Careers getCareers() {
        return careers;
    }

    public static void setCareers(Careers careers) {
        AllDataHolder.careers = careers;
    }

    public static CaseStudies getCaseStudies() {
        return caseStudies;
    }

    public static void setCaseStudies(CaseStudies caseStudies) {
        AllDataHolder.caseStudies = caseStudies;
    }

    public static DynamicMarketplace getDynamicMarketplace() {
        return dynamicMarketplace;
    }

    public static void setDynamicMarketplace(DynamicMarketplace dynamicMarketplace) {
        AllDataHolder.dynamicMarketplace = dynamicMarketplace;
    }

    public static FSMA204 getFsma204() {
        return fsma204;
    }

    public static void setFsma204(FSMA204 fsma204) {
        AllDataHolder.fsma204 = fsma204;
    }

    public static News getNews() {
        return news;
    }

    public static void setNews(News news) {
        AllDataHolder.news = news;
    }

    public static PrecisionTracing getPrecisionTracing() {
        return precisionTracing;
    }

    public static void setPrecisionTracing(PrecisionTracing precisionTracing) {
        AllDataHolder.precisionTracing = precisionTracing;
    }

    public static SCMFinance getScmFinance() {
        return scmFinance;
    }

    public static void setScmFinance(SCMFinance scmFinance) {
        AllDataHolder.scmFinance = scmFinance;
    }

    public static SupplyChainOptimization getSupplyChainOptimization() {
        return supplyChainOptimization;
    }

    public static void setSupplyChainOptimization(SupplyChainOptimization supplyChainOptimization) {
        AllDataHolder.supplyChainOptimization = supplyChainOptimization;
    }

    public static Technologies getTechnologies() {
        return technologies;
    }

    public static void setTechnologies(Technologies technologies) {
        AllDataHolder.technologies = technologies;
    }
}
