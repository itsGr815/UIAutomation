package com.dFarm.qa.dFarm.util.execution;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.IAnnotationTransformer;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;

public class TestListener implements ITestListener, IAnnotationTransformer {
    public static ITestContext ctx;
    public static Map<String , ITestResult> testFailure = new LinkedHashMap<>();

    private static final Logger LogbackLogger = LoggerFactory.getLogger(TestListener.class);


    @Override
    public void onTestStart(ITestResult iTestResult) {
        testFailure.put(AllDataHolder.getCurrentTestCaseId(), null);
        ctx = iTestResult.getTestContext();
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        LogbackLogger.info("PlaceHolder for unimplemented methods");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        testFailure.put(AllDataHolder.getCurrentTestCaseId(), iTestResult);
        LogbackLogger.info(AllDataHolder.getCurrentTestCaseId() + " User for failure reason : " + iTestResult.getMethod().toString());
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        testFailure.put(AllDataHolder.getCurrentTestCaseId(), iTestResult);
        LogbackLogger.info(AllDataHolder.getCurrentTestCaseId() + " User for Skip reason : " + iTestResult.getMethod().toString());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        LogbackLogger.info("PlaceHolder for unimplemented methods");
    }

    @Override
    public void onStart(ITestContext iTestContext) {
    LogbackLogger.info("PlaceHolder for unimplemented methods");
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        System.out.println(ctx.getFailedTests().size());
    }

    @Override
    public void transform(ITestAnnotation iTestAnnotation, Class aClass, Constructor constructor, Method method) {
        iTestAnnotation.setRetryAnalyzer(RetryAnalyzer.class);
    }
}
