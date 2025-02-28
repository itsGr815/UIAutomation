package com.dFarm.qa.dFarm.util.execution;

import com.dFarm.qa.dFarm.constants.ReportingConstants;
import com.dFarm.qa.dFarm.util.reporters.ExtentReporting;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

    private int counter = 0;

    /**
     * @implSpec this method will decide how many times the failed test case is re-run,
     * Value is set in config.properties
     * @param iTestResult
     * @return
     */
    @Override
    public boolean retry(ITestResult iTestResult) {
        int testStatus = ExtentReporting.getStatus();
        try {
            if (AllDataHolder.getRetryCount() >=0 && testStatus != ReportingConstants.PASS){
                if(counter < AllDataHolder.getRetryCount()){
                    counter++;
                    return true;
                }else {
                    AllDataHolder.setIsLastRun(true);
                }
            }
            return false;
        }catch (Exception e){
            return false;
        }
    }
}
