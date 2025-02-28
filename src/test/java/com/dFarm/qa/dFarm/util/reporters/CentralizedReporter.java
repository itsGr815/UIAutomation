package com.dFarm.qa.dFarm.util.reporters;

import com.dFarm.qa.dFarm.constants.ReportingConstants;
import com.dFarm.qa.dFarm.constants.ServiceAndSauce;
import com.dFarm.qa.dFarm.util.execution.AllDataHolder;
import com.dFarm.qa.dFarm.util.execution.FlexFrameWorkRunTimeException;
import com.dFarm.qa.dFarm.util.execution.GlobalHelpers;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.apache.xmlbeans.impl.xb.xsdschema.All;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;

import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.Optional;

//Class to store the results in DB
public class CentralizedReporter extends ReportingConstants {

    private static final Logger Logger = LoggerFactory.getLogger(CentralizedReporter.class);
    String inUserId;
    Gson gson = new Gson();
    JSONObject json = new JSONObject();
    RestfulUtil restUtil = new RestfulUtil();

    /**
     * @author Ganesh
     * @implSpec Before suite  call to get all primary id's for test suite from the reporting system
     */
    public void getGlobalIds(ITestContext ctx){
        DecimalFormat df = new DecimalFormat("0.00");
        //Record Suite Start time
        ReportingConstants.suiteStartTime = GlobalHelpers.getCurentCstReportTime();
        //Build Json object to fetch ID's
        json.put(BROWSER, Optional.ofNullable(ctx.getCurrentXmlTest().getParameter(BROWSER)).orElse("chrome"));
        json.put(BROWSERVERSION, df.format(Float.parseFloat(AllDataHolder.getBrowserVersion())));
        json.put(APPVERSION, df.format(Float.parseFloat(AllDataHolder.getAppVersion())));
        json.put(APPLICATION, "dFarm");
        json.put(REGION, AllDataHolder.getCentralizedReportRegion());
        json.put(OSVERSION, AllDataHolder.getPlatform().replaceAll("_", " "));
        json.put(TESTSUITE, ctx.getSuite().getName());
        json.put(TESTCASECOUNT, ctx.getSuite().getAllMethods().size());
        try {
            //Post json as string to service
            String ids = RestfulUtil.getIDs(json.toString());
            //Set the response from service to all data holder for later use
            AllDataHolder.setGlobalReportingIds(ids);
        }catch (Exception e){
            Logger.error(e.getMessage());
        }
    }

    /**
     * @author Ganesh
     * @implSpec Before method call to fetch the group id for each test
     */
    public void getGroupId(){
        //Build Json Object to fetch ID's
        //Passing all the list of testng group annotations to centralized reporter separated by |
        try {
            StringBuilder tagList = new StringBuilder();
            for (String tag : AllDataHolder.getGroups()){
                tagList.append(tag).append("|");
            }
            json.put(GROUPNAME, tagList.toString());
        }catch (Exception e){
            Logger.error(e.getMessage());
        }
        try {
            //Post json as string to service
            JSONObject jsonObj = new JSONObject(restUtil.getGroupId(json.toString()));
            //Set the response from service to all data folder for later use
            AllDataHolder.setGroupIdMap(Integer.parseInt(jsonObj.get(GROUPID).toString()));
        }catch (Exception e){
            Logger.error(e.getMessage());
        }
    }

    /**
     * @author Ganesh
     * @implSpec Method created to set the metadata for centralized reporting
     */
    public void setTestMetaData(String testPlanName, Method method) throws FlexFrameWorkRunTimeException {
        JSONObject allIdJson = new JSONObject(AllDataHolder.getGlobalReportingIds());
        try {
            if (allIdJson.length() > 0) {
                AllDataHolder.getTestCase().setTestSuiteId(allIdJson.get(TESTSUITEID).toString());
                AllDataHolder.getTestCase().setBrowserId(allIdJson.get(BROWSERID).toString());
                AllDataHolder.getTestCase().setTestPlanId(AllDataHolder.getTestPlanIdMap(testPlanName));
                AllDataHolder.getTestCase().setTestCaseId(getTestCaseDetails(method));
                AllDataHolder.getTestCase().setGroupId(Integer.toString(AllDataHolder.getGroupIdMap()));
                AllDataHolder.getTestCase().setCreatedBy(AllDataHolder.getCurrentUser());
                AllDataHolder.getTestCase().setTestCaseRetryCount(Integer.toString(AllDataHolder.getTestCaseRetryCount()));
                String type = AllDataHolder.getTestData().get(AllDataHolder.getCurrentTestCaseId(), ServiceAndSauce.TYPE);
                if (AllDataHolder.getIsServiceData() && !StringUtils.isEmpty(type) && !StringUtils.isEmpty(AllDataHolder.getUserId())){
                    JSONObject allData = new JSONObject(AllDataHolder.getLoginData());
                    inUserId = allData.get(ServiceAndSauce.RES_INUSED).toString();
                }else {
                    inUserId = "1";
                }
                AllDataHolder.getTestCase().setInUseId(inUserId);
                AllDataHolder.getTestCase().setRunStartDateStr(AllDataHolder.getTestStartTime());
                AllDataHolder.getTestCase().setRunEndDateStr(AllDataHolder.getTestEndTime());
                AllDataHolder.getTestCase().setStatusId(AllDataHolder.getTestCaseStatus());
            }
        } catch (Exception e) {
            throw new FlexFrameWorkRunTimeException(e);
        }
    }

    /**
     * @author Ganesh
     * @implSpec To Create a json for getting tets case id from reporting system
     */

    public String getTestCaseDetails(Method method) throws FlexFrameWorkRunTimeException {
        //Building json array
        json.put(ReportingConstants.TETSCASE, AllDataHolder.getCurrentTestCaseId());
        json.put(TESTMETHOD, method.getName());
        //Passing all the list of testng group annotation to centralized reporter separated by |
        try {
            StringBuilder tagList = new StringBuilder();
            for (String tag : AllDataHolder.getGroups()){
                tagList.append(tag).append("|");
            }
            json.put(GROUPS, tagList.toString());
        }catch (Exception e){
            Logger.error(e.getMessage());
        }
        json.put(PRIORITYID, AllDataHolder.getGroups().get(0).replaceAll("p", ""));
        json.put(CREATEDBY, AllDataHolder.getCurrentTestCaseId());
        json.put(CLASSNAME, method.getDeclaringClass().getName());

        JSONObject testCaseidJson = new JSONObject(restUtil.getTestCaseIdTestCase(json.toString()));
        return testCaseidJson.get("testCaseId").toString();
    }

    /**
     * @author Ganesh
     * @implSpec To create a json for getting test plan if from reporting system
     */
    public String getTestPlanDetails(String testPlanName) throws FlexFrameWorkRunTimeException {
        json.put(TESTPLAN, testPlanName);
        json.put(DECSRIPTION, AllDataHolder.getCurrentTestCaseId());
        json.put(CREATEDBY, AllDataHolder.getCurrentUser());
        JSONObject testPlanId = new JSONObject(restUtil.getTestPlnIdTestPlan(json.toString()));
        return testPlanId.get("testPlanId").toString();
    }

    /**
     * @author Ganesh
     * @implSpec To Save test results in centralized reporting system
     */
    public void saveTestTestResults(String testPlanName, Method method) throws FlexFrameWorkRunTimeException{
        try {
            setTestMetaData(testPlanName, method);
            restUtil.saveTestCase(gson.toJson(AllDataHolder.getTestCase()));
            AllDataHolder.removeReports(AllDataHolder.getTestCase());
        }catch (Exception e){
            throw  new FlexFrameWorkRunTimeException(e);
        }
    }

    /**
     * @author Ganesh
     * @implSpec To Save test plan results in centralized reporting system
     */
    public String saveTestPlanResults(String testPlanName) throws FlexFrameWorkRunTimeException{
        JSONObject allIdJson = new JSONObject(AllDataHolder.getGlobalReportingIds());
        //To update the class if the class already exists
        if (Optional.ofNullable(AllDataHolder.testPlanMap.get(testPlanName).getTestPlanResultId()).isPresent()){
            json.put("id", AllDataHolder.getTestPlanIdMap(testPlanName));
        }
        json.put(TESTSUITEID, allIdJson.get(TESTSUITEID));
        json.put(TESTPLANID, AllDataHolder.getTestPlanIdMap(testPlanName));
        json.put(STATUSID, AllDataHolder.testPlanMap.get(testPlanName).getTestPlanExecutionStatus());
        json.put(BROWSERID, allIdJson.get(BROWSERID));
        json.put(CREATEDBY, AllDataHolder.getCurrentUser());
        json.put(RUNSTARTDATESTR, AllDataHolder.testPlanMap.get(testPlanName).getTestPlanStartDateTime());
        json.put(RUNSTARTDATESTR, AllDataHolder.testPlanMap.get(testPlanName).getTestPlanEndDateTime());
        JSONObject testPlanId = new JSONObject(restUtil.saveTestPlanResult(json.toString()));
        return testPlanId.get("testPlanResultId").toString();
    }

    /**
     * @author Ganesh
     * @implSpec To Save test suite results in centralized reporting system
     */
    public void saveTestSuiteResults() throws FlexFrameWorkRunTimeException{
        try {
            //Build testsuite json array
            JSONObject allIdJson = new JSONObject(AllDataHolder.getGlobalReportingIds());
            json.put(TESTSUITEID, allIdJson.get(TESTSUITEID).toString());
            json.put(OSID, allIdJson.get(OSID).toString());
            json.put(APPVERSIONID, allIdJson.get(APPVERSIONID).toString());
            json.put(ENVIRONMENTID, allIdJson.get(ENVIRONMENTID).toString());
            json.put(ISDEBUG, "-1");
            //report suite fail or pass
            json.put(STATUSID, isSuiteFail ? FAIL : PASS);
            json.put(CREATEDBY, AllDataHolder.getCurrentUser());
            json.put(RUNSTARTDATESTR, suiteStartTime);
            json.put(RUNENDDATESTR, GlobalHelpers.getCurentCstReportTime());
            //Post test suite results
            restUtil.saveTestSuiteResult(json.toString());
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e);
        }
    }
}
