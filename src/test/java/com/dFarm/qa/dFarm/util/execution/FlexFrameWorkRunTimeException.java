package com.dFarm.qa.dFarm.util.execution;

import com.dFarm.qa.dFarm.constants.DFarmConstants;
import org.openqa.selenium.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * NOTE: Errors are currently handled as per the requirement is Comprehensive
 * automation, it can be changed as per the project requirement
 *
 * @author Gangarapu.Ganesh
 */

@SuppressWarnings("serial")
public class FlexFrameWorkRunTimeException extends Exception {

    private static final Logger Logger = LoggerFactory.getLogger(FlexFrameWorkRunTimeException.class);

    public FlexFrameWorkRunTimeException(String message){
        try {
          AllDataHolder.getUtil().report(DFarmConstants.FAIL, "Check for Run-Time exception", "No exception should be ignored", message);

        }catch (Exception e){
           try {
               AllDataHolder.getUtil().report(DFarmConstants.FAIL, "Check for Run-Time exception", "No exception should be ignored", e.getMessage());
           }catch (Exception ignored){

           }
        }
    }

    public FlexFrameWorkRunTimeException(Throwable cause) {
        super(cause);
        WebDriver driver = AllDataHolder.getDriver();
        String issue = cause.toString();
        try {
            AllDataHolder.getUtil().report(DFarmConstants.INFO, "Check for exception", "Encountered FlexFrameWorkRunTimeException.", "Method : " + Thread.currentThread().getStackTrace()[2].getMethodName()
                    + " in Class : " + Thread.currentThread().getStackTrace()[2].getClassName());

            if (cause instanceof NoSuchElementException) {
                AllDataHolder.getUtil().report(DFarmConstants.FAIL, "Check for NoSuchElementException",
                        "Exception: No Such element Exception", issue);
            } else if (cause instanceof TimeoutException) {
                AllDataHolder.getUtil().report(DFarmConstants.FAIL, "Check for TimeoutException",
                        "Exception: No time out exception", issue);
            }else if (cause instanceof NoAlertPresentException){
                AllDataHolder.getUtil().report(DFarmConstants.FAIL, "Check for NoAlertPresentException",
                        "Exception: No alert present exception", issue);
            }else if (cause instanceof WebDriverException){
                if (AllDataHolder.getUtil().getStackTrace().contains("BaseTest")){
                    AllDataHolder.setIsLastRun(true);
                }
                StringWriter sw = new StringWriter();
                cause.printStackTrace(new PrintWriter(sw));
                AllDataHolder.getUtil().report(6, "Check for WebDriverException",
                        "Exception: Web driver issue exception", sw.toString());
                if (sw.toString().contains("504 Gateway Time-out") ||
                        sw.toString().contains("Server didn't respond int ime. Command duration or timeout")){
                    AllDataHolder.getUtil().report(6, "Check for WebDriverException",
                            "Error Reported: 504 Gateway Time-out", "The server didn't respond in time");
                }
            }else if (cause instanceof NullPointerException){
                AllDataHolder.getUtil().report(DFarmConstants.FAIL, "Check for NullPointerException",
                        "Exception: Null Pointer Exception", issue);
            }else if (cause instanceof IllegalArgumentException){
                AllDataHolder.getUtil().report(DFarmConstants.FAIL, "Check for IllegalArgumentException",
                        "Exception: Illegal Argument Exception", issue);
            }else if (GlobalHelpers.isAlertPresent()){
                Alert subscriptAlert = driver.switchTo().alert();
                subscriptAlert.accept();
                AllDataHolder.getUtil().report(DFarmConstants.FAIL, "Check for exception",
                        "Unexpected Alert Run-ime exception", issue);
            }else {
                AllDataHolder.getUtil().report(DFarmConstants.FAIL, "Check for exception",
                        "Run-ime exception", issue);
            }
            }catch (Exception e){
            try {
                if (e.getMessage().contains("has already finished, and can't receive durther commands.")
                || e.getMessage().contains("Error communicating with the remote browser") || e.getMessage()
                .contains("Invalid message: Due to a previous error, this job has already finished.")){
                    AllDataHolder.getUtil().report(DFarmConstants.FAIL, "Check for exception",
                            "SL Tunnel might have gone down. Aborting Execution..", e.getMessage());
                }else {
                    AllDataHolder.getUtil().report(DFarmConstants.FAIL, "Check for exception",
                            "Popup doesnt exist exception- Force check.", e.getMessage());
                }
                Logger.error(issue);
            }catch (Exception ignored){

            }
        }


        }
}
