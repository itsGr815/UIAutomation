package com.dFarm.qa.dFarm.util.reporters;

import com.dFarm.qa.dFarm.constants.ReportingConstants;
import com.dFarm.qa.dFarm.util.execution.AllDataHolder;
import com.dFarm.qa.dFarm.util.execution.FlexFrameWorkRunTimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.rmi.UnmarshalException;
import java.sql.SQLException;

public class WSException extends Exception{

    private static final long serialVersionUID = 1L;
    private static final Logger Logger = LoggerFactory.getLogger(WSException.class);
    private final String message;


    public WSException(Throwable cause) throws FlexFrameWorkRunTimeException {
        super(cause);
        if (cause instanceof UnmarshalException){
            message = "Service returned HTTP 200, but expected Data not found in response. Please check the Error l=Log for Detailed Response";
        }else if (cause instanceof SQLException){
            message = "Check DB connection";
        }else if (cause instanceof NullPointerException){
            message = "Exception: Nu;; Pointer exception";
        }else {
            message = cause.getMessage();
        }
        Logger.error("Unhandled Exception " + message);
        AllDataHolder.getUtil().report(ReportingConstants.FAIL, "Perform exception check for " + AllDataHolder.getCurrentTestCaseId(), "Test passed with no exception", "Test step failed for " + message);
    }

    @Override
    public String getMessage(){
        return message;
    }
}
