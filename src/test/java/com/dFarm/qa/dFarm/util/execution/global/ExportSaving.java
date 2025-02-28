package com.dFarm.qa.dFarm.util.execution.global;

import com.dFarm.qa.dFarm.util.execution.AllDataHolder;
import com.dFarm.qa.dFarm.util.execution.FlexFrameWorkRunTimeException;
import com.dFarm.qa.dFarm.util.execution.GlobalHelpers;
import org.apache.commons.io.filefilter.FileFileFilter;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ExportSaving {
    private static final Logger Logger = LoggerFactory.getLogger(ExportSaving.class);
    public static void getDriverFactoryforexporting(WebElement elementtoclickon){
        try {
            WebDriver driver = AllDataHolder.getDriver();
            String browser = driver.toString();
            Robot robot = new Robot();
            if (browser.contains("FirefoxDriver")){
                JavascriptExecutor exe = (JavascriptExecutor) driver;
                exe.executeScript("arguments[0].click()", elementtoclickon);

                Thread.sleep(1000);

                robot.keyPress(KeyEvent.VK_DOWN);
                robot.keyRelease(KeyEvent.VK_DOWN);
                GlobalHelpers.robotEnter();
            }else if (browser.contains("InternetExplorerDriver")){
                JavascriptExecutor exe = (JavascriptExecutor) driver;
                exe.executeScript("arguments[0].click()", elementtoclickon);
                Thread.sleep(1000);
                robot.keyPress(KeyEvent.VK_F6);
                robot.keyRelease(KeyEvent.VK_F6);
                robot.keyPress(KeyEvent.VK_TAB);
                robot.keyRelease(KeyEvent.VK_TAB);
                GlobalHelpers.robotEnter();
            }else if (browser.contains("ChromeDriver")){
                JavascriptExecutor exe = (JavascriptExecutor) driver;
                exe.executeScript("arguments[0].click()", elementtoclickon);
            }
        }catch (Exception e){
            Logger.error(e.getMessage());
        }
    }

    public static List<String> excelReaderFile(String filePath){
        List<String > list = new ArrayList<>();
        try {
            BufferedReader bufRdr;
            bufRdr = new BufferedReader(new FileReader(filePath));
            String line = null;
            int col;
            int row = 0;

            while ((line = bufRdr.readLine()) != null){
                StringTokenizer st = new StringTokenizer(line, ",");
                col = 0;
                String lineCsv = "";
                while (st.hasMoreTokens()){
                    //Following storing the data of csv

                    lineCsv += st.nextToken() + ",";
                    col++;
                }
                list.add(lineCsv);
                row++;
            }
            bufRdr.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return list;
    }

    /**
     * @param filePath
     * @param expectedContents
     * @throws FlexFrameWorkRunTimeException
     * @author Gangarapu.Ganesh
     * @ImplNotes This method is written to verify the Excel contents with expected
     *            List of values and then delete the file
     * @throws IOException
     */
    public static void checkExcelContentAndDeleteFile(String filePath, List<String> expectedContents) throws FlexFrameWorkRunTimeException, IOException {
        //Read Excel File content
        String excelContent = excelReaderFile(filePath).toString();
        //Verify the Excel contents
        for (String content : expectedContents){
            AllDataHolder.getUtil().subStringCheck(excelContent, content);
            //Delete the file
            AllDataHolder.getUtil().deleteFile(filePath);
        }
    }

    public static String lastexcelFileModified(String fileFolderPath) throws IOException {
        File fileLocation;
        fileLocation = new File(fileFolderPath);
        String  reportLocation= fileLocation.getAbsolutePath();
        File f1 = new File(reportLocation);
        File[] files = f1.listFiles((FileFilter) new FileFileFilter(){
            @Override
            public boolean accept(File file) {
                return super.accept(file);
            }
        });
        long lastMod = Long.MIN_VALUE;
        File choice = null;
        for (File file : files){
            if (file.lastModified() > lastMod){
                choice = file;
                lastMod = file.lastModified();
            }
        }
        return  choice.toString();
    }

}
