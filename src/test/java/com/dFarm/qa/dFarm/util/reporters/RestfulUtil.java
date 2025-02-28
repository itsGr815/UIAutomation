package com.dFarm.qa.dFarm.util.reporters;

import com.dFarm.qa.dFarm.initializer.ConfigInitializer;
import com.dFarm.qa.dFarm.util.execution.AllDataHolder;
import com.dFarm.qa.dFarm.util.execution.CommonUtil;
import com.dFarm.qa.dFarm.util.execution.FlexFrameWorkRunTimeException;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class RestfulUtil {

    //URI format: http://dFarm.com:3000
    private static final String dbWriterUrl = ConfigInitializer.getProp().getProperty("ReportDBServerURL") + ":" + ConfigInitializer.getProp().getProperty("ReportDBServicePort");

    //To get all global IDs in suite level - Before Suite call
    public static String getIDs(String strJson){
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject(dbWriterUrl + "/getIDs", strJson, String.class);
    }

    //To get group id for each test - Before Method call
    public String getGroupId(String strJson){
        String response = null;
        StringBuffer uri = new StringBuffer();
        uri.append(dbWriterUrl).append("/getGroupId");
        WSCaller wsCaller = new WSCaller();
        try {
            response = postRequest(uri.toString(), new HashMap<>(), strJson);
        }catch (WSException | FlexFrameWorkRunTimeException wsException){
            wsException.printStackTrace();
        }
        return response;
    }

    //To get test plan id - Before Class/Test call
    public String getTestPlnIdTestPlan(String testPlan) throws FlexFrameWorkRunTimeException{
        String response = null;
        StringBuilder uri = new StringBuilder();
        uri.append(dbWriterUrl).append("/getTestPlanIdByTestPlan");
        WSCaller wsCaller = new WSCaller();
        try {
            response = postRequest(uri.toString(), new HashMap<>(), testPlan);
        } catch (WSException wsException){
            wsException.printStackTrace();
        }
        return response;
    }

    //To get test case id for each test - Before method call
    public String getTestCaseIdTestCase(String testCase) throws FlexFrameWorkRunTimeException{
        String response = null;
        StringBuilder uri = new StringBuilder();
        uri.append(dbWriterUrl).append("/getTestCaseIdByName");
        WSCaller wsCaller = new WSCaller();
        try {
            response = postRequest(uri.toString(), new HashMap<>(), testCase);
        } catch (WSException wsException){
            wsException.printStackTrace();
        }
        return response;
    }

    //To get test case details in DB - Before method call
    public String saveTestCase(String testCaseJson) throws FlexFrameWorkRunTimeException{
        String response = null;
        StringBuilder uri = new StringBuilder();
        uri.append(dbWriterUrl).append("/saveTestCaseResults");
        WSCaller wsCaller = new WSCaller();
        try {
            response = postRequest(uri.toString(), new HashMap<>(), testCaseJson);
            Logger.logFordFarm("** Saving Test Case " + AllDataHolder.getCurrentTestCaseId() +
                    "Test Results " + AllDataHolder.getTestCaseStatus() + "response = " + response);
        } catch (WSException wsException){
            Logger.logFordFarm("error saving test case exception " + wsException);
            wsException.printStackTrace();
        }
        return response;
    }

    //To get test plan results in DB - Before method call
    public String saveTestPlanResult(String strJson) throws FlexFrameWorkRunTimeException{
        String response = null;
        StringBuilder uri = new StringBuilder();
        uri.append(dbWriterUrl).append("/saveTestPlanResult");
        WSCaller wsCaller = new WSCaller();
        try {
            response = postRequest(uri.toString(), new HashMap<>(), strJson);
        } catch (WSException wsException){
            wsException.printStackTrace();
        }
        return response;
    }


    //To get test suite results in DB - Before method call
    public String saveTestSuiteResult(String strJson) throws FlexFrameWorkRunTimeException{
        String response = null;
        StringBuilder uri = new StringBuilder();
        uri.append(dbWriterUrl).append("/saveTestSuiteResult");
        WSCaller wsCaller = new WSCaller();
        try {
            response = postRequest(uri.toString(), new HashMap<>(), strJson);
        } catch (WSException wsException){
            wsException.printStackTrace();
        }
        return response;
    }


    private static String postRequest(String endPoint, Map<String, String> headers, String postData) throws WSException, FlexFrameWorkRunTimeException {
        String response;
        try {
            RequestSpecification httpPostRequest = RestAssured.given();
            if (headers != null){
                Set<String> keys = headers.keySet();
                for (String key : keys){
                    httpPostRequest.header(key, headers.get(key));
                }
            }
            httpPostRequest.body(postData);
            CommonUtil util = new CommonUtil();
            response = util.getResponse(httpPostRequest.post(endPoint));
        }catch (Exception e){
            throw new WSException(e);
        }
        return response;
    }

}
