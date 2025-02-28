package com.dFarm.qa.dFarm.util.execution;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @implSpec To re-run before test and before method critical failures based on the retry count set via config.properties
 */
public class RetryOnException {

    public static final int DEFAULT_RETRIES = 3;
    public static final long DEFAULT_TIME_TO_WAIT_MS = 10000;

    private int numRetries;
    private final long timeToWaitMS;

    //CONSTRUCTOR
    public RetryOnException(int _numRetries,
                            long _timeToWaitMS){
        numRetries = _numRetries;
        timeToWaitMS = _timeToWaitMS;
    }

    public RetryOnException(){
        this(DEFAULT_RETRIES, DEFAULT_TIME_TO_WAIT_MS);
    }

    /**
     * shouldRetry
     * Returns true if a retry can be attempted
     * @return True if retries attempts remain; else false
     */
    public boolean shouldRetry(){
        return (numRetries >=0);
    }

    /**
     * waitUntillNextTry
     * Waits for _timeToWaitMS. Ignores any interrupted exceptions
     */
    public void waitUntillNextTry(){
        try {
            Thread.sleep(timeToWaitMS);
        }catch (Exception ignored){

        }
    }

    /**
     * exceptionOccured
     * Call when an exception has occurred in the block. If the
     * retry limit is exceeded, throws an exception.
     * Else wait for the specified time
     * @param ex
     * @throws FlexFrameWorkRunTimeException
     */
    public void exceptionOccured(Exception ex) throws FlexFrameWorkRunTimeException {
        numRetries--;
        if (!shouldRetry()){
            AllDataHolder.setIsLastRun(true);
            StringWriter sw = new StringWriter();
            ex.printStackTrace(new PrintWriter(sw));
            AllDataHolder.getUtil().report(6, "Exception Check", "No exception should occur", "</br><b>Stacktrace:</b>" + sw);
        }
        waitUntillNextTry();
    }
}
