package com.dFarm.qa.dFarm.util.reporters;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.dFarm.qa.dFarm.constants.ReportingConstants;
import com.dFarm.qa.dFarm.util.execution.AllDataHolder;
import com.dFarm.qa.dFarm.util.execution.GlobalHelpers;
import com.aventstack.extentreports.Status;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Arrays;

public class ExtentReporting {

    public static HashMap<Long, ExtentTest> extentTestMap = new HashMap<>();

    /** The Status for screen shot **/
    private static final List<Status> statusForScreenShot = Arrays.asList(Status.FAIL, Status.ERROR, Status.FATAL, Status.SKIP);

    public synchronized static ExtentTest getTest(){
        return extentTestMap.get(Thread.currentThread().getId());
    }

    public synchronized static void createTest(String name, Method method, String... category){
        ExtentTest test = ExtentManager.getInstance().createTest(name);

        for (ExtentTest existingFailedTest : extentTestMap.values()){
            if(existingFailedTest.getModel().getName().equals(test.getModel().getName())){
                ExtentManager.getInstance().removeTest(existingFailedTest);
            }
        }

        if(category != null){
            test.assignCategory(category);
            test.info("<b> Method Name : </br> <br>" + method.getName());
            extentTestMap.put(Thread.currentThread().getId(), test);
        }
    }

    public synchronized static void log(String message){
        getTest().info(message);
    }

    public synchronized void reportStep(int status, Status logStatus, String testStepName, String expectedResult, String actualResult, String... screenShotPath){
        try {
            if (statusForScreenShot.contains(logStatus) && screenShotPath.length !=0 && !screenShotPath[0].isEmpty()){
                /* The log format failure. */ /* The log format failure. */
                String logFormatFailure = "<b>%s</b><pre>%s</pre>";
                String logFormatFailureNew = "<details><summary><b>%s</b><br/></summary><pre>%s</pre></details>";
                getTest().log(logStatus, String.format(logFormatFailureNew, testStepName, actualResult),
                        MediaEntityBuilder.createScreenCaptureFromPath(screenShotPath[0]).build());
            }else {
                /* The log format. */ /* The log format. */
                String logFormat = "<b>%s</b><br>%s";
                getTest().log(logStatus, String.format(logFormat, testStepName, actualResult));
            }
            if (!AllDataHolder.getIsDebug()){
                TestSteps steps = new TestSteps();
                steps.setTestStepName(testStepName);
                steps.setExpectedResult(expectedResult);
                steps.setActualResult(actualResult);
                steps.setCreatedBy(AllDataHolder.getCurrentUser());
                steps.setRunStartDateStr(GlobalHelpers.getCurentCstReportTime());
                steps.setRunEndDateStr(GlobalHelpers.getCurentCstReportTime());
                steps.setStatusId(Integer.toString(status));
                AllDataHolder.getTestCase().getTestSteps().add(steps);
            }

        }catch (Exception e){
            getTest().error(e.getMessage());
        }
    }

    /**
     * End Test
     */
    public synchronized static void endTest(){
        //Report as Error if 0 Steps are executed in a test
        if(getTest().getModel().getLogContext().size() == 0){
            getTest().log(Status.ERROR, "No Steps recorded");
        }
        ExtentManager.getInstance().flush();
    }

    /**
     * Gets the status
     * @return the status
     */
    public synchronized static int getStatus(){
        if (getTest().getStatus().equals(Status.PASS)){
            return ReportingConstants.PASS;
        }else if (getTest().getStatus().equals(Status.INFO)){
            return ReportingConstants.INFO;
        }else if(getTest().getStatus().equals(Status.WARNING)){
            return ReportingConstants.WARNING;
        }else if (getTest().getStatus().equals(Status.SKIP)){
            return ReportingConstants.SKIP;
        }else if (getTest().getStatus().equals(Status.ERROR)){
            return ReportingConstants.ERROR;
        }else if (getTest().getStatus().equals(Status.FATAL)){
            return ReportingConstants.FATAL;
        }else {
            return ReportingConstants.FAIL;
        }

    }

}
