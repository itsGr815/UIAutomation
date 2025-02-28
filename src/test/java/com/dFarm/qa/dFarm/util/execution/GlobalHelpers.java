package com.dFarm.qa.dFarm.util.execution;

import com.dFarm.qa.dFarm.constants.DFarmConstants;
import com.dFarm.qa.dFarm.constants.TestDataConstants;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.apache.commons.io.filefilter.WildcardFilter;
import org.apache.commons.lang3.time.DateUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileFilter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;
import java.util.List;

public class GlobalHelpers implements DFarmConstants {

    private static final String CHAR_LIST = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String SPL_CHAR_LIST = "!@#$%^&*()_+[{]}|;'?/:";
    private static final SecureRandom rnd = new SecureRandom();
    private static final SimpleDateFormat formater = new SimpleDateFormat(DFarmConstants.MMDDYYYYDATEFORMAT);
    private static final Date date = new Date();
    private static Calendar c = new GregorianCalendar();
    private static final Random randomGenerator = new Random();
    private static final DecimalFormat df = new DecimalFormat("0.00");


    public static String acceptPopupsandReturnText() throws FlexFrameWorkRunTimeException {
        try {
            WebDriver driver = AllDataHolder.getDriver();
            Alert alertDialog = driver.switchTo().alert();
            String alertText = alertDialog.getText();
            alertDialog.accept();
            AllDataHolder.getUtil().report(DFarmConstants.SUCCESS, "Alert Pop-up", "Accepted Alert pop-up: " + alertText, "Accepted Alert pop-up" + alertText);
            return alertText;
            } catch (Exception e) {
            throw new FlexFrameWorkRunTimeException(e);
              }
        }

    public static String getRegionFromUrl(String url) {
        String region = null;
        if (url.contains("dfarm.dev")) {
            region = "DEV";
        } else if(url.contains("dfarm.qa")){
            region = "QA";
        }
        return region;
    }


    public static String getCurentCstReportTime(){
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("IST"));
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss:ms");
        format.setTimeZone(calendar.getTimeZone());
        return format.format(calendar.getTime());
    }

    public static String getCurrentDate() throws FlexFrameWorkRunTimeException {
        try {
            SimpleDateFormat formater = new SimpleDateFormat(DFarmConstants.MMDDYYYYDATEFORMAT);
             Date date = new Date();
            return formater.format(date);
        } catch (Exception e) {
            throw new FlexFrameWorkRunTimeException(e);
        }
    }

    /**
     * to get future/past year
     * @author Manjunath MG
     * @return alertText
     */
    public static String addYearAlonetoCurrentYear (int yearstoadd) {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        return Integer.toString(year + yearstoadd);
    }

    public static String addYeartoCurrentDate (int yearstoadd) throws FlexFrameWorkRunTimeException {
       String time = formater.format(date);
       Calendar c = Calendar.getInstance();
       try {
           c.setTime(formater.parse(time));
       } catch (Exception e) {
           throw new FlexFrameWorkRunTimeException(e);
       }
       c.add(Calendar.YEAR, yearstoadd);
       return formater.format(c.getTime());
    }

    public static String addDatetoCurrentDate (int datetoadd) throws FlexFrameWorkRunTimeException {
        String time = formater.format(date);
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(formater.parse(time));
        } catch (Exception e) {
            throw new FlexFrameWorkRunTimeException(e);
        }
        c.add(Calendar.DATE, datetoadd);
        return formater.format(c.getTime());
    }

    public static String addMonthtoCurrentDate (int monthtoadd) throws FlexFrameWorkRunTimeException {
        String time = formater.format(date);
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(formater.parse(time));
        } catch (Exception e) {
            throw new FlexFrameWorkRunTimeException(e);
        }
        c.add(Calendar.MONTH, monthtoadd);
        return formater.format(c.getTime());
    }

    public static void switchToPopAndClose() throws FlexFrameWorkRunTimeException {
        try {
            WebDriver driver = AllDataHolder.getDriver();
            AllDataHolder.getUtil().driverWait();
            String winHandleBefore = driver.getWindowHandle();
            //Switch to new window opened
            for (String winHandle : driver.getWindowHandles()) {
                driver.switchTo().window(winHandle);
            }
            Set<String> windowHandles = driver.getWindowHandles();
            int count = windowHandles.size();
            if (count !=1) {
                driver.close();
                AllDataHolder.getUtil().report(DFarmConstants.SUCCESS, "New Window", "New window closed", "New window closed");
            } else {
                AllDataHolder.getUtil().report(DFarmConstants.SUCCESS, "New Window", "New window closed", "No new window to close");
            }
            driver.switchTo().window(winHandleBefore);
        } catch(Exception e) {
            throw new FlexFrameWorkRunTimeException(e);
        }

    }

    public static boolean isAlertPresentandAccept() {
        WebDriver driver = AllDataHolder.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        boolean foundAlert;
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            foundAlert = true;
            Alert subscribeAlert = driver.switchTo().alert();
            String alertText = AllDataHolder.getUtil().getAlertText(driver);
            subscribeAlert.accept();
            AllDataHolder.getUtil().report(DFarmConstants.SUCCESS, "Alert Pop-up", "Acception Alert pop-up: " + alertText, "Acception Alert pop-up: " + alertText);
        } catch (Exception e) {
            foundAlert = false;
        }
        return foundAlert;
    }

    public static String GetCustomDate(int customday, String customformat) {
        Date date1 = DateUtils.addDays(new Date(), customday);
        SimpleDateFormat dateformat = new SimpleDateFormat("" + customformat + "");
        return dateformat.format(date1);
    }

    public static String GetCustomZoneTime(String customzone, String customformat) {
        c = Calendar.getInstance(TimeZone.getTimeZone("" + customzone + ""));
        SimpleDateFormat format = new SimpleDateFormat("" + customformat + "");
        format.setTimeZone(c.getTimeZone());
        return format.format(c.getTime());
    }

    public static String GetCustomZoneDate(String customzone, String customformat) {
        c = Calendar.getInstance(TimeZone.getTimeZone("" + customzone + ""));
        SimpleDateFormat format = new SimpleDateFormat("" + customformat + "");
        format.setTimeZone(c.getTimeZone());
        return format.format(c.getTime());
    }

    public static String generateRandomName(int size) {
        StringBuilder randStr = new StringBuilder();
        for (int i =0; i < size; i++) {
            int number = getRandomNumber();
            char ch = CHAR_LIST.charAt(number);
            randStr.append(ch);
        }
        return randStr.toString();
    }

    public static int getRandomNumber() {
        int ramdomInt = randomGenerator.nextInt(CHAR_LIST.length());
        if (ramdomInt -1 == -1) {
            return ramdomInt;
        } else {
            return ramdomInt -1;
        }
    }

    public static String getLastModifiedFilename(String filelocationofthefile) {
        File filelocation = new File(filelocationofthefile);
        String reportlocation = filelocation.getAbsolutePath();
        File f1 = new File(reportlocation);
        File[] files = f1.listFiles(File::isFile);
        long lastMod = Long.MIN_VALUE;
        File choice = null;
        for (File file : files) {
            if (file.lastModified() > lastMod) {
                choice = file;
                lastMod = file.lastModified();
            }
        }
        return choice.toString();
    }

    public static void validDateFormat(String date, String format) throws FlexFrameWorkRunTimeException {
        try {
            if (validateDateFormatBoolean(date, format)) {
                AllDataHolder.getUtil().report(DFarmConstants.SUCCESS, date, "Expected date " + date + "is matching with format" + format,
                        "Expected date " + date + "is matching with format" + format);
            } else {
                AllDataHolder.getUtil().report(DFarmConstants.FAIL, date, "Expected date " + date + "is matching with format" + format,
                        "Expected date " + date + "is not matching with format" + format);
            }
        } catch (Exception e) {
            throw new FlexFrameWorkRunTimeException(e);
        }
    }

    private static boolean validateDateFormatBoolean(String date, String format) {
        boolean dateValue = true;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            dateFormat.setLenient(false);
            dateFormat.parse(date.trim());
        } catch (ParseException e) {
            dateValue = false;
        }
        return dateValue;
    }


    public static void verifyDateFormat(List<String> list, String format) throws FlexFrameWorkRunTimeException {
        try {
            for (String aList : list) {
                String indexvalue = aList.trim();
                if (!("").equals(indexvalue) && indexvalue != null) {
                    validDateFormat(indexvalue, format);
                } else {
                    AllDataHolder.getUtil().report(DFarmConstants.SUCCESS, "Verify date format of <b>" + indexvalue + "<\b>text", "give Data is null", "given Data is null");
                }
            }
        } catch (Exception e) {
            throw new FlexFrameWorkRunTimeException(e);
        }
    }


    public static void refreshTime(String before, String after) throws FlexFrameWorkRunTimeException {
        try {
            String[] beforeDate = before.replaceAll("Data as of ","").split(",");
            String beforeTime = beforeDate[0].trim().substring(0,7);
            String[] afterDate = after.replaceAll("Data as of ","").split(",");
            String afterTime = afterDate[0].trim().substring(0,7);
            SimpleDateFormat dateFormat = new SimpleDateFormat("h:mma");
            dateFormat.setTimeZone(TimeZone.getTimeZone("ET"));
            Date beforeTimeDate = dateFormat.parse(beforeTime);
            Date afterTimeDate = dateFormat.parse(afterTime);
            if (beforeTimeDate.before(afterTimeDate)) {
                AllDataHolder.getUtil().report(DFarmConstants.SUCCESS, "Before Refresh :" + before + ", After Refresh :" + after, "Time got refreshed", "Time got refreshed");
            } else {
                AllDataHolder.getUtil().report(FAIL, "Before Refresh :" + before + ", After Refresh :" + after, "Time got refreshed", "Time got refreshed");
            }
        } catch (Exception e) {
            throw new FlexFrameWorkRunTimeException(e);
        }
    }


    public static List<String> ColumnText(Object byOrWebElement) {
        WebDriver driver = AllDataHolder.getDriver();
        List<WebElement> Text = driver.findElements((By) byOrWebElement);
        List<String> actual = new ArrayList<>();
        for (WebElement aText : Text) {
            actual.add(aText.getText());
        }
        return actual;
    }

    public static String randomAlphanumericString(int length) {
        // UUID.randomUUID()generates a 36 character string, which after removing "-"
        // becomes a 32 char string
        String randomAlphanumericText = UUID.randomUUID().toString().concat(UUID.randomUUID().toString().replaceAll("-", "")).replaceAll("-", "");
        String inputValue = "dFarm TESTDATA VALUE" + randomAlphanumericText;
        return inputValue.substring(0, length);
    }

    //Generates Alphanumeric String
    public static String randomSalesComments(int length) {
        // UUID.randomUUID()generates a 36 character string, which after removing "-"
        // becomes a 32 char string
        String randomAlphanumericText = UUID.randomUUID().toString().concat(UUID.randomUUID().toString().replaceAll("-", "")).replaceAll("-", "");
        String inputValue = "dFarm Sales Order" + randomAlphanumericText;
        return inputValue.substring(0, length);
    }

    /**
     * to get decoded value
     * @param encodeValue
     * @return decode value
     */
    public static String getDecodedValue(String encodeValue){
        return new String(Base64.decodeBase64(encodeValue));
    }

    public static boolean isAlertPresent(){
        try {
            WebDriver driver = AllDataHolder.getDriver();
            driver.switchTo().alert();
            return true;
        }catch (NoAlertPresentException ex){
            return false;
        }
    }

    public static String randomNumber (float min, float max, int decimalPlaces) {
        float unformattedval = randomGenerator.nextFloat() * (max - min) + min;
        return (String.format("%." + decimalPlaces + "f", unformattedval));
    }

    public static int randomNumberInt(String min, String max) {
        return ((randomGenerator.nextInt(Integer.parseInt(max) - Integer.parseInt(min)) + Integer.parseInt(min)));
    }

    public static double randomNumberDouble(String min, String max) {
        double random = new Random().nextDouble();
        double result;
        double resultValue;
        result = Double.parseDouble(min) + (random * (Double.parseDouble(max) - Double.parseDouble(min)));
        resultValue =  returnTwoDouble(result);
        return resultValue;
    }

    public static double returnTwoDouble(double value) {
        String decimalValue;
        decimalValue = df.format(value);
        return Double.parseDouble(decimalValue);
    }

    public static String randomNumericChars(int numberOfDigits) {
        StringBuilder randStr = new StringBuilder();
        for (int i=0; i < numberOfDigits; i++) {
            randStr.append(randomGenerator.nextInt(9));
        }
        String randomvalue = randStr.toString().replaceAll("-", "");
        if(Long.parseLong(randomvalue) == 0) {
            randomvalue = randomNumbericChars(numberOfDigits);
        }
        return randomvalue;
    }

    private static String randomNumbericChars(int numberOfDigits) {
        StringBuilder randStr = new StringBuilder();
        for (int i = 0; i < numberOfDigits; i++){
            randStr.append(randomGenerator.nextInt(9));
        }
        String randomValue = randStr.toString().replaceAll("-", "");
        //Added below code to handle possibility of "randomGenerator.nextInt()" returning 0
        //Iterate again untill we have value not equal to 0
        if (Long.parseLong(randomValue) == 0){
            randomValue = randomNumbericChars(numberOfDigits);
        }
        return randomValue;
    }


    public static String validZipcodes() {
        String validZip = null;
        String[] validZip_US = {"78759", "78758", "12345-1234","36099"};
        for (int i=0; i < validZip_US.length;) {
            validZip = validZip_US[randomGenerator.nextInt(validZip_US.length)];
            break;
        }
        return validZip;
    }

    public static String dismissPopupsAndReturnText() throws FlexFrameWorkRunTimeException {
        try {
            WebDriver driver = AllDataHolder.getDriver();
            Alert alertDialog = driver.switchTo().alert();
            String alertText = alertDialog.getText();
            alertDialog.dismiss();
            AllDataHolder.getUtil().report(SUCCESS, "Alert Pop-up", "Accepted Alert pop-up: " + alertText, "Accepted Alert pop-up" + alertText);
            return alertText;
        } catch (Exception e) {
            throw new FlexFrameWorkRunTimeException(e);
        }
    }

    public static String getFirstDayOfPreviousMonth() {
        SimpleDateFormat formater = new SimpleDateFormat("M/d/YYYY");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        calendar.add(Calendar.DATE, -1);
        Date firstdate = calendar.getTime();
        return formater.format(firstdate);
    }


    public static String getFirstDayOfPreviousYear() {
        SimpleDateFormat formater = new SimpleDateFormat("M/d/YYYY");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -1);
        calendar.add(Calendar.DATE, -1);
        Date firstdate = calendar.getTime();
        return formater.format(firstdate);
    }

    public static String getFirstDayOfPreviousHalfYear() {
        SimpleDateFormat formater = new SimpleDateFormat("M/d/YYYY");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -6);
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date firstdate = calendar.getTime();
        return formater.format(firstdate);
    }

    public static String getLastDayOfPreviousMonth() {
        SimpleDateFormat formater = new SimpleDateFormat("M/d/YYYY");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date lastday = calendar.getTime();
        return formater.format(lastday);
    }

    public static String randomSpecialCharacterString (int size) {
            StringBuilder sb = new StringBuilder(size);
            for (int i =0; i < size; i++)
                sb.append(SPL_CHAR_LIST.charAt(rnd.nextInt(SPL_CHAR_LIST.length())));
            return sb.toString();
    }

    public static int getRandomNumber (int minumum) {
        Random randomGenerator = new Random();
        int randomInt = minumum + randomGenerator.nextInt(CHAR_LIST.length());
        if (randomInt -1 == -1) {
            return randomInt;
        } else {
            return randomInt - 1;
        }
    }


    public static String getRandomNumberSize( int size) {
            StringBuilder str = new StringBuilder();
            Random r = new Random();
            for (int i =0; i< size; i++) {
                str.append(r.nextInt(9));
            }
            return str.toString();
    }

    public static void pressEnter() throws Exception {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
    }


    public static void copyToClipboard(String str) {
            StringSelection ss = new StringSelection(str);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
    }

    public static void robotEscape( ) throws FlexFrameWorkRunTimeException {
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ESCAPE);
            robot.keyRelease(KeyEvent.VK_ESCAPE);
        } catch (Exception e) {
            throw new FlexFrameWorkRunTimeException(e);
        }
    }


    public static void robotPaste() throws FlexFrameWorkRunTimeException {
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyRelease(KeyEvent.VK_V);
        } catch (Exception e) {
            throw new FlexFrameWorkRunTimeException(e);
        }
    }


    public static void robotSave() throws FlexFrameWorkRunTimeException {
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyRelease(KeyEvent.VK_V);
        } catch (Exception e) {
            throw new FlexFrameWorkRunTimeException(e);
        }
    }

    public static void robotShiftSave() throws FlexFrameWorkRunTimeException {
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_S);
            robot.keyRelease(KeyEvent.VK_SHIFT);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyRelease(KeyEvent.VK_S);
        } catch (Exception e) {
            throw new FlexFrameWorkRunTimeException(e);
        }
    }


    public static void robotEnter( ) throws FlexFrameWorkRunTimeException {
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        } catch (Exception e) {
            throw new FlexFrameWorkRunTimeException(e);
        }
    }

    public static File getTheNewestFile( String filePath, String ext) {
        File theNewstFile = null;
        File dir = new File(filePath);
        FileFilter fileFilter = new WildcardFilter("*." + ext);
        File[] files = dir.listFiles(fileFilter);
        if(files.length > 0) {
            Arrays.sort(files, LastModifiedFileComparator.LASTMODIFIED_REVERSE);
            theNewstFile = files[0];
        }
        return theNewstFile;
    }


    public static File lastFileModified(String filePath) {
        File f1 = new File(filePath);
        File[] files = f1.listFiles(File::isFile);
        long lastMod = Long.MIN_VALUE;
        File choice = null;
        for (File file : files) {
            if (file.lastModified() > lastMod) {
                choice = file;
                lastMod = file.lastModified();
            }
        }
        return choice;
    }

    public static boolean generateRandomBoolean() {
        return randomGenerator.nextBoolean();
    }

    public static List<String> exportCSVData() throws FlexFrameWorkRunTimeException {
        try {
            File file = lastFileModified(TestDataConstants.USERPATH + System.getenv().get(TestDataConstants.EXPORTSUSERNAME) + TestDataConstants.DOWNLOADSPATH);
            return Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new FlexFrameWorkRunTimeException(e);
        }
    }


    public static String getTimeStamp() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm a");
        Calendar cal = Calendar.getInstance();
        dateFormat.setTimeZone(TimeZone.getTimeZone("EST5EDT"));
        return dateFormat.format(cal.getTime());
    }

    public static String getESTDateStamp() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(MMDDYYYYDATEFORMAT);
        Calendar cal = Calendar.getInstance();
        dateFormat.setTimeZone(TimeZone.getTimeZone("EST5EDT"));
        return dateFormat.format(cal.getTime());
    }

    public static String getCurrentCstReportTime(){
        c = Calendar.getInstance(TimeZone.getTimeZone("CST"));
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:sss.ms");
        format.setTimeZone(c.getTimeZone());
        return format.format(c.getTime());
    }

    public static String getDecodeValue(String encodeValue) throws FlexFrameWorkRunTimeException {
        try {
            return new String(Base64.decodeBase64(encodeValue));
        } catch (Exception e) {
            throw new FlexFrameWorkRunTimeException(e);
        }
    }

    public String convertDateIntoString(String format, Date date) {
        DateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }


    public static void setupSSL(){

        String sslJks = TestDataConstants.SSL_CERT_LOCATION;
        String sslJksPwd = TestDataConstants.SSL_JKS_PASSWD;
        System.setProperty("javax.net.ssl.trustStore", sslJks);
        System.setProperty("javax.net.ssl.trustStorePassword", sslJksPwd);
        System.setProperty("javax.net.ssl.KeyStore", sslJks);
        System.setProperty("javax.net.ssl.KeyStorePassword", sslJksPwd);
        System.setProperty("javax.net.ssl.KeyStoreType", TestDataConstants.SSL_KEYSTORE_TYPE);
        //Disabling host name check
        HostnameVerifier allHostsValid = (arg0, arg1) -> true;
        //Install the all-trusting host verifier
        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
    }

}
