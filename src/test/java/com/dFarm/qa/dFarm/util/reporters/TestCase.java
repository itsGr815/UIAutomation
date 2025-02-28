package com.dFarm.qa.dFarm.util.reporters;

import java.util.List;

public class TestCase {

    String testSuiteId;
    String browserId;
    String testPlanId;
    String testCaseId;
    String statusId;
    String createdBy;
    String inUseId;
    String groupId;
    String runStartDateStr;
    String runEndDateStr;
    String testCaseRetryCount;

    List<TestSteps> TestSteps;

    public String getTestSuiteId() {
        return testSuiteId;
    }

    public void setTestSuiteId(String testSuiteId) {
        this.testSuiteId = testSuiteId;
    }

    public String getBrowserId() {
        return browserId;
    }

    public void setBrowserId(String browserId) {
        this.browserId = browserId;
    }

    public String getTestPlanId() {
        return testPlanId;
    }

    public void setTestPlanId(String testPlanId) {
        this.testPlanId = testPlanId;
    }

    public String getTestCaseId() {
        return testCaseId;
    }

    public void setTestCaseId(String testCaseId) {
        this.testCaseId = testCaseId;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getInUseId() {
        return inUseId;
    }

    public void setInUseId(String inUseId) {
        this.inUseId = inUseId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getRunStartDateStr() {
        return runStartDateStr;
    }

    public void setRunStartDateStr(String runStartDateStr) {
        this.runStartDateStr = runStartDateStr;
    }

    public String getRunEndDateStr() {
        return runEndDateStr;
    }

    public void setRunEndDateStr(String runEndDateStr) {
        this.runEndDateStr = runEndDateStr;
    }

    public String getTestCaseRetryCount() {
        return testCaseRetryCount;
    }

    public void setTestCaseRetryCount(String testCaseRetryCount) {
        this.testCaseRetryCount = testCaseRetryCount;
    }

    public List<com.dFarm.qa.dFarm.util.reporters.TestSteps> getTestSteps() {
        return TestSteps;
    }

    public void setTestSteps(List<com.dFarm.qa.dFarm.util.reporters.TestSteps> testSteps) {
        TestSteps = testSteps;
    }
}
