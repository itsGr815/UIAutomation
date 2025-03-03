package com.dFarm.qa.dFarm;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;

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
        //  Run Configurations
        //-ea -DEnv=Env to Tests -DURL=Website URL
        System.setProperty("webdriver.chrome.driver", "D:/dFarm/dFarm/Automation/chromedriver.exe");

        ChromeOptions chromeOpt = new ChromeOptions();
        chromeOpt.addArguments("--remote-allow-origins=*");
        ChromeDriver chromeDriver = new ChromeDriver(chromeOpt);

        chromeDriver.get("https://distribution.dfarm.in/login");
        chromeDriver.navigate().refresh();
        chromeDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        chromeDriver.manage().window().maximize();
    }


}
