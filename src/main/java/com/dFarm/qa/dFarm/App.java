package com.dFarm.qa.dFarm;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.text.DecimalFormat;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
//        final DecimalFormat df = new DecimalFormat("0.00");
//        double temp1  = 254.222222;
//        String t;
//         t = df.format(temp1);
//        System.out.println( "Hello World!" +t);
        App app = new App();

        app.launchChrome();

    }


    public void launchChrome(){
        System.setProperty("webdriver.chrome.driver", "D:/dFarm/dFarm/Automation/chromedriver.exe");

        ChromeOptions chromeOpt = new ChromeOptions();
        chromeOpt.addArguments("--remote-allow-origins=*");
        ChromeDriver chromeDriver = new ChromeDriver(chromeOpt);

        chromeDriver.get("https://distribution.dfarm.in/login");
    }


}
