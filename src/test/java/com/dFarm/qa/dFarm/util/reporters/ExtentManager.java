package com.dFarm.qa.dFarm.util.reporters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ResourceCDN;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Protocol;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.dFarm.qa.dFarm.constants.DriverConstants;
import com.dFarm.qa.dFarm.constants.ReportingConstants;
import com.dFarm.qa.dFarm.util.execution.AllDataHolder;


public class ExtentManager {

    static ExtentReports extent;

  public static ExtentReports getInstance(){
      return extent;
  }

    public static synchronized void createInstance(int threadCount, String fileName){
       ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
       htmlReporter.setAppendExisting(true);
       htmlReporter.config().setTheme(Theme.DARK);
       htmlReporter.config().setEncoding("UTF-8");
       htmlReporter.config().setProtocol(Protocol.HTTPS);
       htmlReporter.config().setResourceCDN(ResourceCDN.EXTENTREPORTS);
       htmlReporter.config().setDocumentTitle("Testing Automation dFarm Reports");
       htmlReporter.config().setReportName("Execution reports - dFarm AIMS QA");
       htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
       htmlReporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss a");


       extent = new ExtentReports();

       extent.setSystemInfo(ReportingConstants.CREATEBY, AllDataHolder.getCurrentUser());
       extent.setSystemInfo(DriverConstants.BROWSER, AllDataHolder.getBrowser());
       extent.setSystemInfo(DriverConstants.ENVIRONMENT, System.getProperty(DriverConstants.ENV));
        extent.setSystemInfo(DriverConstants.URL, System.getProperty(DriverConstants.URL));
       extent.setSystemInfo(ReportingConstants.THREADCOUNT, Integer.toString(threadCount));

       extent.attachReporter(htmlReporter);
    }



}
