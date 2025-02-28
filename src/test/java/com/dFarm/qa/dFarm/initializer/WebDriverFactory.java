package com.dFarm.qa.dFarm.initializer;

import com.dFarm.qa.dFarm.constants.DriverConstants;
import com.dFarm.qa.dFarm.util.wdm.DriverManagerConfig;
import io.github.bonigarcia.wdm.*;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class WebDriverFactory extends DriverFactory {

    private static final Logger Logger = LoggerFactory.getLogger(WebDriverFactory.class);
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

    //TodO
    public WebDriver getDriverFactory() {
        try {
            String browser = getBrowser();
            //TODO
            //Write method to set proxy
            if(browser.equalsIgnoreCase(FIREFOXBROWSER)){
               FirefoxDriverManager.getInstance().setup(Architecture.x64, DriverManagerConfig.getProperty(DriverConstants.FIREFOXVERSION));
                final FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.setBinary(firefoxOptions.getBinary());
                //Creating custom profile for auto saving the downloads and use it with desired Capabilities
                FirefoxProfile profile = new FirefoxProfile();
                profile.setPreference("browser.download.folderList", 1);
                profile.setPreference("browser.download.manager.ShowWhenStarting", false);
                profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf, text/csv");
               // firefoxOptions.setCapability(FirefoxDriver.PROFILE, profile);
                driver = ThreadLocal.withInitial(() -> new FirefoxDriver(firefoxOptions));
            }else if (browser.equalsIgnoreCase(IEBROWSER)){
                InternetExplorerOptions ieOpt = new InternetExplorerOptions();
                InternetExplorerDriverManager.getInstance().setup(Architecture.x64, getVersion(DriverManagerConfig.getProperty(IEVERSION)));
                ieOpt.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
                ieOpt.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
                ieOpt.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
                ieOpt.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
                ieOpt.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, false);
                ieOpt.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
                driver = ThreadLocal.withInitial(() -> new InternetExplorerDriver(ieOpt));
            }else if(browser.equalsIgnoreCase(CHROMEBROWSER)){
            // ChromeDriverManager.getInstance().setup(Architecture.x64, DriverManagerConfig.getProperty(CHROMEVERSION));
               System.setProperty("webdriver.chrome.driver", "D:/dFarm/dFarm/Automation/chrome_driver/133/chromedriver.exe");
                HashMap<String, Object> chromePrefs = new HashMap<>();
                chromePrefs.put("profile.default_content.setting_values_automatic_downloads", 1);
                chromePrefs.put("download.prompt_for_download", false);
                chromePrefs.put("plugins,always_open_pdf_externally", false);
                chromePrefs.put("download.default_directory", "D:\\dFarm\\dFarm\\Automation\\fileDownload");
                chromePrefs.put("profile.default_content_setting_values.notifications", 2);
                Map<String, Object> cloudOptions = new HashMap<>();
                ChromeOptions chromeOpt = new ChromeOptions();
                chromeOpt.addArguments("--disable-extensions");
                chromeOpt.addArguments("--disable-notifications");
                chromeOpt.addArguments("--remote-allow-origins=*");
                chromeOpt.setExperimentalOption("prefs", chromePrefs);
                chromeOpt.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
                chromeOpt.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);

                driver = ThreadLocal.withInitial(() -> new ChromeDriver(chromeOpt));
            }else if(browser.equalsIgnoreCase(EDGEBROWSER)){
                EdgeDriverManager.getInstance().setup(Architecture.x64, getVersion(DriverManagerConfig.getProperty(EDGEVERSION)));
                driver = ThreadLocal.withInitial(EdgeDriver::new);
            }else if(browser.equalsIgnoreCase(SAFARIBROWSER)){
                Logger.error("Safari not yet supported");

            }else{
                Logger.error("Unable to instantiate Driver. Check if browser name " + browser + "is splelled correctly");
            }
        }catch (Exception e){
            Logger.error(e.getMessage());
        }
        return driver.get();
    }
}
