package com.dFarm.qa.dFarm.util.reporters;

public class TestSteps {

    String testStepName;
    String expectedResult;
    String actualResult;
    String createdBy;
    String statusId;
    String runStartDateStr;
    String runEndDateStr;


    public String getTestStepName() {
        return testStepName;
    }

    public void setTestStepName(String testStepName) {
        this.testStepName = testStepName;
    }

    public String getExpectedResult() {
        return expectedResult;
    }

    public void setExpectedResult(String expectedResult) {
        this.expectedResult = expectedResult;
    }

    public String getActualResult() {
        return actualResult;
    }

    public void setActualResult(String actualResult) {
        this.actualResult = actualResult;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
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
}
