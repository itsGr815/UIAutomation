package com.dFarm.qa.dFarm.constants;

public interface DFarmConstants {

    String TEST_FAILED = "Test Failed";
    String TEST_PASSED = "Test Passed";
    String REPORT_EXPECTED = "<b>Expected value</b>";
    String REPORT_ACTUAL = "<b>Actual value</b>";
    String REPORT_CLICKED = "Element is Clicked";
    String ATTRIBUTEVALUE = "value";
    String RESULTSPATH = "results\\";
    String SCREENSHOTPATH = "/screenshots/";
    String[] SUPPORTEDBROWSERS = {"firefox", "chrome", "ie", "microsoftedge"};
    String SUPPORTEDBROWSERS_ERROR = " Browser name is Incorrect: Acceptable list(firefox, chrome, ie, microsoftedge)";
    String DRIVER = "Driver";
    String USERNAME = "Username";
    String PASSWORD = "Password@123";
    String PASSWORD_EXCEL = "Password";
    String DFARM_HOMEPAGE = "/login";
    String FASTFAIL = "fastFail";
    String RETRYCOUNT = "retryCount";
    String DECODE_KEY = "Decode_Key";

    String MMDDYYYYDATEFORMAT = "MM/dd/yyyy";
    String DATEFORMAT2 = "MM/dd/yyyy hh:mm aa";
    String HTML_BREAK = "</br>";
    String ASCENDING = "ascending";
    String DESCENDING = "descending";


    String OVERLAY_LOGOFF_CONFIRMATION_CONTENT_LINE1 = "Please note once you logoff, always you can login and get back. ";
    String OVERLAY_LOGOFF_CONFIRMATION_CONTENT_LINE2 = "Are you sure you want to logoff ?";
    String ARE_YOU_SURE = "Are you sure?";

    String WEIGHT_UNIT = "WeightUnit";
    String WEIGHT_UNIT_KG = "kg";
    String WEIGHT_UNIT_TONS = "tons";


    int SUCCESS = 1;
    int SKIP = 4;
    int FAIL = 0;
    int WARNING = 3;
    int ERROR = 5;
    int FATAL =6;
    int INFO = 2;
}
