package com.dFarm.qa.dFarm.util.reporters;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class EmailReporter {

    private static final Logger Logger = LoggerFactory.getLogger(EmailReporter.class);

    private static String imagePath;

    public static void emailableEmailReporter(String htmlReportName){
        PrintWriter writer = null;

        /**
         * Declare all required variables
         */
        WebDriver driver = null;
        WebElement elementEnv, elementTests, elementStartTime, elementEndTime, elementTotalTime, elementTotalTest,
                elementSteps;
        String reportHeader, bambooArtifactsPath, sauceLabsBuildPath;
        String envimage, testsimage, starttimeimage, endtimeimage, totaltimeimage, totaltestimage, stesimage;

        /**
         * Create file of extent report
         */
        File generateReportFile =  new File(htmlReportName);
        /**
         * Destination path to store email report
         */
        String destinationPath = "https://bamboo.dfarm.com/artifact/"+System.getProperty("planKey")+ "/"
                + System.getProperty("shortJobKey") + "/build " + System.getProperty("buildNumber");
        String emailReportPath = "results/EmailReporter";
        imagePath = "results/Screenshots";

        String bambooImagePath = destinationPath + "Results/screenshots";

        /**
         * Create the directory for email report and image report
         */
        File destinationReportFile = new File(emailReportPath);
        File imageFile = new File(imagePath);

        /**
         * Check all file location exists
         */
        try{
            if (!generateReportFile.exists()){
                throw new Exception("Report not found at " + htmlReportName);
            }
            if (!destinationReportFile.exists()){
                if (!destinationReportFile.mkdir()){
                    throw new Exception("Destination folder not found for email report at  " + emailReportPath);
                }
            }
            if (!imageFile.exists()){
                throw new Exception("Destination folder not found for image at  " + imagePath);
            }

            driver = getPhantomInstance();

            /**
             * Navigate to created extent report
             */
            driver.get("file:///" + generateReportFile.getAbsolutePath());
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.manage().window().maximize();

            /**
             * Navigate to dashboard
             */
            //Enter the dashboard path
            driver.findElement(By.xpath("//a[@view=dashboard=view]//i")).click();

            /**
             * get required report sections from dashboard(Bamboo -- Extent report Dashboard values)
             */
            //Environment
            elementEnv = driver.findElement(By.xpath("//div[@class='card-panel dashboard-environment']"));
            //Tests
            elementTests = driver.findElement(By.xpath("(//div[@class='card-panel nm-v])[1]"));
            //Start Time
            elementStartTime = driver.findElement(By.xpath("(//div[@class='col s2'])[3]"));
            //End Time
            elementEndTime = driver.findElement(By.xpath("(//div[@class='col s2'])[4]"));
            //Time Taken
            elementTotalTime = driver.findElement(By.xpath("(//div[@class='col s2'])[5]"));
            //Tests
            elementTotalTest = driver.findElement(By.xpath("(//div[@class='col s2'])[1]"));
            //Steps
            elementSteps = driver.findElement(By.xpath("(//div[@class='card-panel nm-v])[2]"));

            /**
             * Take screenshot of the page and get sub image of the required
             * element and store it in a 'png' format.
             */
            takeSnapShot(driver, imagePath + "\\envimage.png", elementEnv);
            takeSnapShot(driver, imagePath + "\\testimage.png", elementTests);
            takeSnapShot(driver, imagePath + "\\totalstepsimage.png", elementSteps);
            takeSnapShot(driver, imagePath + "\\starttimeimage.png", elementStartTime);
            takeSnapShot(driver, imagePath + "\\endtimeimage.png", elementEndTime);
            takeSnapShot(driver, imagePath + "\\totaltimeimage.png", elementTotalTime);
            takeSnapShot(driver, imagePath + "\\totaltestsimage.png", elementTotalTest);

            /**
             * Create email report file
             */
            File resultFile = new File(emailReportPath + "\\emailextentreport.html");
            /**
             * Use PrintWriter to create htmlreport
             */
            writer = new PrintWriter(resultFile);
            envimage = bambooImagePath + "\\subenvimage.png";
            testsimage = bambooImagePath + "\\testsimage.png";
            stesimage = bambooImagePath + "\\totalstepsimage.png";
            starttimeimage = bambooImagePath + "\\starttimeimage.png";
            endtimeimage = bambooImagePath + "\\endtimeimage.png";
            totaltimeimage = bambooImagePath + "\\totaltimeinage.png";
            totaltestimage = bambooImagePath + "\\totaltestsimage.png";
            reportHeader = "dFarm Execution Status";

            /**
             * Prepare the HTML report
             */
            writer.write("<html>\n");
            writer.append("<body>\n");
            writer.append("<table align=\"left\" style=\"boarder:1.5px silver solid;\">");
            writer.append("<tr><td><font color=\"black\" face=\"Arial\"><h4><left>Date/Time & Environment Summary</left></h4></font>");
            writer.append("<div><img src=\"").append(totaltestimage).append("\"/>");
            writer.append("<img sre=\"").append(starttimeimage).append("\"/>");
            writer.append("<img sre=\"").append(endtimeimage).append("\"/>");
            writer.append("<img sre=\"").append(totaltimeimage).append("\"/>");
            writer.append("<img sre=\"").append(envimage).append("\"/></div></td></tr><br><br>");
            writer.append("<tr><td></td></tr>");
            bambooArtifactsPath = destinationPath + "/R" + htmlReportName.substring(1);
            sauceLabsBuildPath = "https://app.saucelabs.com/archives?query=build:%20%22" + System.getProperty("planKey")
                    + System.getProperty("buildNumber") + "%22&sort=Best%20Match";
            writer.append("<tr><td><center><a href=\"").append(bambooArtifactsPath);
            writer.append("\"> Visit details results report</a>&nbsp&nbsp&nbsp");
            writer.append("<a href=\"").append(sauceLabsBuildPath).append("Visit Sauce Lab Builds</a></center></td></tr>");
            writer.append("</table>\n");
            writer.append("</body>\n");
            writer.append("</html>\n");
            writer.close();
        }catch (Exception e){
            Logger.error(e.getMessage());
        }finally {
            if (writer != null){
                writer.close();
                driver.close();
            }
        }
    }

    private static void takeSnapShot(WebDriver webDriver, String destinationImagePath, WebElement webElement){
        try {
            /**
             * Take full page screenshot
             */
            TakesScreenshot scrShot = ((TakesScreenshot) webDriver);
            File srcFile = scrShot.getScreenshotAs(OutputType.FILE);
            BufferedImage fullImage = ImageIO.read(srcFile);

            /**
             * get the required element's section location
             */
            Point point = webElement.getLocation();
            int width = webElement.getSize().getWidth();
            int height = webElement.getSize().getHeight();
            /**
             * Create the sub image from full page
             */
            BufferedImage elementScreenshot = fullImage.getSubimage(point.getX(), point.getY(), width, height);
            /**
             * store it as a png image
             */
            ImageIO.write(elementScreenshot, "png", srcFile);
            File destFile = new File(destinationImagePath);
            FileUtils.copyFile(srcFile, destFile);
            if (destinationImagePath.contains("envimage.png")){
                BufferedImage envImg = ImageIO.read(destFile);
                /**
                 * Create the sub image from sub image for Region and URL
                 */
                BufferedImage subImageScreenshot = envImg.getSubimage(0, 135, 343, 112);
                /**
                 * Store it as a png image
                 */
                ImageIO.write(subImageScreenshot, "png", srcFile);
                destFile = new File(imagePath + "\\subenvimage.png");
                FileUtils.copyFile(srcFile, destFile);
            }
        }catch (WebDriverException | IOException ignored){

        }
    }

    private static WebDriver getPhantomInstance(){
        WebDriver driver;
        ArrayList<String> cliArgsCap = new ArrayList<>();
        cliArgsCap.add("--proxy=proxy.dfarm.com:8080");
        //Enter the service account details
        cliArgsCap.add("--proxy-auth=username:password");
        cliArgsCap.add("--proxy-type=http");
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, cliArgsCap);
        cap.setJavascriptEnabled(true);
        cap.setCapability("takeScreenshot", true);
        cap.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "E:\\dFarm\\Automation\\Phantom\\phantomjs-2.1.1-windows\\" +
                "phantomjs-2.1.1-windows\\bin\\phantomjs.exe");
        try {
            driver = new PhantomJSDriver(cap);
        }catch (Exception e){
            driver = new PhantomJSDriver(cap);
        }
        return driver;
    }


}
