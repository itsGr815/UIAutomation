package com.dFarm.qa.dFarm.objectrepository.MarketPlace;

import com.dFarm.qa.dFarm.constants.MarketPlaceConstants;
import com.dFarm.qa.dFarm.util.datahandler.ISuppllyLocatorInfo;
import org.openqa.selenium.By;

public enum MPHOMEPAGE implements ISuppllyLocatorInfo, MarketPlaceConstants {

    LINK_DYNAMIC_MARKET_PLACE(By.id("wholesaleTrading"), DYNAMIC_MARKETPLACE),
    LINK_SIGN_IN(By.xpath("//a[@id='navbarDarkDropdownMenuLink' and contains(.,' Hello, Sign in ')]"), "Hello, Sign in"),
    ICON_PROFILE(By.id("navbarDarkDropdownMenuLink"), "MP - Profile"),
    OVERLAY_SIGN_IN(By.xpath("//ul[@class='largeDevice loginPanel ng-star-inserted']"), "Overlay - Hello, Sign in"),
    LOGO_DFARM_SIGN_IN(By.xpath("//ul[@class='largeDevice loginPanel ng-star-inserted']//div[@class='brand-wrapper']"), "dFarm Logo"),
    TEXT_SIGN_IN(By.xpath("//div[@class='login-text-wrapper']/h2"), "Sign-In"),
    TEXT_SIGN_IN_ENTER_EMAIL_PASSWORD(By.xpath("//div[@class='login-text-wrapper']/p"), ENTER_USERNAME_PWD),
    TEXT_EMAIL(By.xpath("//input[@formcontrolname='login_id']/../div[contains(.,'Email')]"), "Email"),
    INPUT_ENTER_EMAIL(By.id("email"), ENTER_EMAIL_ID),
    TEXT_PASSWORD(By.xpath("//input[@formcontrolname='password']/../div[contains(.,'Password')]"), "Password"),
    INPUT_ENTER_PASSWORD(By.id("password"), "Enter Your Password"),
    ICON_PASSWORD_MASK(By.className("pi pi-eye-slash"), "Password Mask"),
    ICON_PASSWORD_UN_MASK(By.className("pi pi-eye"), "Password Mask"),
    REMEMBER_ME(By.xpath("//label[contains(.,' Remember me ')]/../p-checkbox"), "Remember me  "),
    LINK_FORGOT_PASSWORD(By.className("forgot-psw"), FORGOT_PASSWORD),
    BUTTON_SIGN_IN(By.xpath("//button[@class='animate-btn w-auto']/p[contains(.,'Sign In')]"), "Sign In"),

    //Header Section
    HEADER_NAVIGATION(By.id("navbarNav"),"Header Navigation Section"),
    LINK_BUYER(By.xpath("//a[contains(text(),'Buyer')]"), BUYER),
    LINK_SELLER(By.xpath("//a[contains(text(),'Seller')]"), SELLER),
    TEXT_GETTING_STARTED(By.xpath("//span[contains(text(),'Get Started!')]"), "Get Started!"),
    INPUTBOX_SEARCH_PRODUCTS(By.xpath("autoComplete searchProduce"), "Search Products - Input Box"),
    INPUT_SEARCH_PRODUCTS(By.xpath("//input[@role='searchbox']"), "Search Products - Input Box"),
    HEADERS_MP_CATEGORIES(By.xpath("//ul[@class='header-item-wrapper']/li"), "Marketplace Categories"),
    HEADERS_MP_MAIN_CATEGORIES(By.xpath("//div[@class='dropbtn']"), "MP Main Categories Headers"),
    AGRICULTURE_MP_CATEGORIES(By.xpath("(//div[@class='dropbtn'])[1]/..//a"), " Agriculture - Marketplace Categories"),
    POULTRY_MP_CATEGORIES(By.xpath("(//div[@class='dropbtn'])[2]/..//a"), "Poultry - Marketplace Categories"),
    POULTRY(By.xpath("//div[@class='dropbtn' and contains(text(), 'Poultry')]"), "Poultry"),
    AGRICULTURE_PRODUCE(By.xpath("//div[@class='dropbtn' and contains(text(), 'Agriculture Produce ')]"), "Agriculture Produce"),



    MSG_LOGIN_FAIL_INVALID_CREDENTIALS(By.xpath("//span[text()=' Incorrect Email / Password. Please try again. ']"), MarketPlaceConstants.USER_PWD_INCORRECT),


    PARAMETERIZE_OBJECT(By.xpath(""), "");


    public static ISuppllyLocatorInfo setLocator(By locator, String label) {
        MPHOMEPAGE.PARAMETERIZE_OBJECT.locator = locator;
        MPHOMEPAGE.PARAMETERIZE_OBJECT.lable = label;
        return MPHOMEPAGE.PARAMETERIZE_OBJECT;

    }

    //Enter the Collect Produce in form of Truck, Nags, Crates, Bins
    public static synchronized ISuppllyLocatorInfo collectionProduceInForm_Of(String transPort) {
        return setLocator(By.xpath("//span[contains(.,'Collect Produce in form of')]/following-sibling::div/button[contains(.,'" + transPort + "')]"), "Collection came to CC in the form of: " + transPort);
    }
    public static synchronized ISuppllyLocatorInfo IamBuyerOrSellerLink(String buyerOrSeller) {
        return setLocator(By.xpath("//div[@id='navbarNav']//li[contains(.,'I am a')]/span[contains(.,'"+buyerOrSeller+"')]"), "I am a" + buyerOrSeller);
    }

    MPHOMEPAGE(Object locator, String lable) {
        if (locator == null || lable == null) {
            throw new IllegalArgumentException("Argus must not be null");
        }
        this.locator = locator;
        this.lable = lable;
    }

    private Object locator;
    private String lable;

    public Object getLocator() {
        return locator;
    }

    public String getLabel() {
        return lable;
    }

}
