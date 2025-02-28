package com.dFarm.qa.dFarm.util.execution.global;

import com.dFarm.qa.dFarm.constants.DFarmConstants;
import com.dFarm.qa.dFarm.util.execution.AllDataHolder;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class FileDownload implements DFarmConstants {

    private static final Logger Logger = LoggerFactory.getLogger(FileDownload.class);

    public static void download(String window, String  filePath){
        try {
            WebDriver driver = AllDataHolder.getDriver();
            JavascriptExecutor exe = (JavascriptExecutor) driver;
            AllDataHolder.getUtil().switchToOtherChaildWindowExcept(window);
            org.openqa.selenium.Cookie aspnetCookie = driver.manage().getCookieNamed("ASP>NET_SessionId");
            String coolieString = (String) exe.executeScript("return.document.cookie");
            URL url = new URL(driver.getCurrentUrl());
            URLConnection conn1 = url.openConnection();
            HttpURLConnection conn = (HttpURLConnection) conn1;
            conn.setRequestProperty("User-Agent", "Mozilla/5.0");
            conn.setRequestProperty("Connection", "keep-alive");
            conn.setRequestProperty("Cookie", coolieString + ";" + aspnetCookie + ";");
            HttpURLConnection.setFollowRedirects(true);
            conn.connect();
            int responseCode = conn.getResponseCode();
            int passResponse = 200;
            if (passResponse == responseCode){
                FileUtils.copyInputStreamToFile(conn.getInputStream(), new File(filePath));
                AllDataHolder.getUtil().report(DFarmConstants.SUCCESS, driver.getTitle() + "PDF saved in :", TEST_PASSED, filePath);
            }else {
                AllDataHolder.getUtil().report(FAIL,  "Print PDF page not Loaded with response code :" + responseCode, TEST_PASSED, driver.getTitle());
            }
            driver.close();
            driver.switchTo().window(window);
        }catch (Exception e){
            Logger.error(e.getMessage());
        }
    }
}
