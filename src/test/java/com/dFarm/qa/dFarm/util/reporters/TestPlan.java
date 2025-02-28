package com.dFarm.qa.dFarm.util.reporters;

import com.dFarm.qa.dFarm.util.execution.GlobalHelpers;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.xml.XmlTest;

import java.util.List;

public class TestPlan {

    private int testCaseCount = 0;
    private String testPlanStartDateTime = "";
    private String testPlanEndDateTime = "";
    private String testPlanResultId = null;
    private String executionStatus = "3";


    public void setTestPlanName (String testPlanName){
        String testPlanName1 = testPlanName;
    }

    public void setBrowser (String browser){
        String browser1 = browser;
    }
    public int getTestCaseCount() {
        return testCaseCount;
    }

    public void setTestCaseCount(int testCaseCount) {
        this.testCaseCount = testCaseCount;
    }

    public String getTestPlanStartDateTime() {
        return testPlanStartDateTime;
    }

    public void setTestPlanStartDateTime(String testPlanStartDateTime) {
        this.testPlanStartDateTime = testPlanStartDateTime;
    }

    public String getTestPlanEndDateTime() {
        return testPlanEndDateTime;
    }

    public void setTestPlanEndDateTime(String testPlanEndDateTime) {
        this.testPlanEndDateTime = testPlanEndDateTime;
    }

    public String getTestPlanResultId() {
        return testPlanResultId;
    }

    public void setTestPlanResultId(String testPlanResultId) {
        this.testPlanResultId = testPlanResultId;
    }

    public String getTestPlanExecutionStatus() {
        return executionStatus;
    }

    public void setTestPLanExecutionStatus(String executionStatus) {
        this.executionStatus = executionStatus;
    }

    public void setTestClassesInPLan(List<?> testClassesInPLan) {
        List<?> testClassesInPLan1 = testClassesInPLan;
    }

    public static TestPlan setTestPlaDetails(ITestContext ctx, String testPlanName){
        TestPlan plan = new TestPlan();
        XmlTest test = ctx.getCurrentXmlTest();
        ITestNGMethod[] testNGMethods = ctx.getAllTestMethods();

        plan.setTestPlanName(testPlanName);
        plan.setTestPlanStartDateTime(GlobalHelpers.getCurentCstReportTime());
        plan.setBrowser(test.getParameter("browser"));
        plan.setTestClassesInPLan(test.getXmlClasses());
        plan.setTestCaseCount(testNGMethods.length);

        return plan;
    }
}
