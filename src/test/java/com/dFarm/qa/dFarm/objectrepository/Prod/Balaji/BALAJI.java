package com.dFarm.qa.dFarm.objectrepository.Prod.Balaji;

import com.dFarm.qa.dFarm.util.datahandler.ISuppllyLocatorInfo;
import org.openqa.selenium.By;

public enum BALAJI implements ISuppllyLocatorInfo {

    ICON_MENU(By.xpath("//div[@class='header-navigation-icon-box']"), "Balaji Menu"),

    BH_DASHBOARD(By.xpath("(//div[@class='farmerreg-page']//form)[2]"), "Balaji Dashboard"),

    //Farmer Produce
    LABEL_PRODUCE_PAGE_TITLE(By.xpath("//h4[normalize-space()='Produce']"), "Produce"),

    //Farmer Financials
    LABEL_FARMER_FINANCIALS_PAGE_TITLE(By.xpath("//h4[normalize-space()='Financials']"), "Financials"),
    LABEL_ADD_PAYMENT(By.xpath("//b[normalize-space()='Add Payment']"), "Add Payment"),
    BUTTON_ADD_PAYMENT(By.xpath("//button//span[normalize-space()='Add Payment']"), "Button - Add Payment"),
    BUTTON_RESET_PAYMENT(By.xpath("//button//span[normalize-space()='Reset']"), "Payment Reset Buttons"),
    LABEL_FARMER_FINANCIALS(By.xpath("//h2[normalize-space()='Farmer Financials']"), "Farmer Financials"),
    LABEL_FARMER_FINANCIALS_NO_RESULTS(By.xpath("//pagination[@id='paginationconfigurationBasic']/..//tbody/tr//div"), "No Results -Farmer Financials"),
    TABLE_FARMER_FINANCIALS_LIST(By.xpath("//pagination[@id='paginationconfigurationBasic']/..//tbody/tr"), "Farmer Financials Table Data"),


    //Farmer Products
    LABEL_FARMER_PRODUCTS_PAGE_TITLE(By.xpath("//h4[normalize-space()='Products']"), " Balaji FarmerProducts"),

    PARAMETERIZE_OBJECT(By.xpath(""), "");


    public static synchronized ISuppllyLocatorInfo clickMainTab(String tabName) {
        return setLocator(By.xpath("//div[@class='left-navigation-box']//mat-panel-title/a[normalize-space()='"+tabName+"']"), tabName);
    }

    public static synchronized ISuppllyLocatorInfo clickSubTab(String mainTabName, String subTabName) {
        return setLocator(By.xpath("//div[@class='left-navigation-box']//mat-panel-title/a[normalize-space()='"+mainTabName+"']/../../../..//div[@role='region']//a[normalize-space()='"+subTabName+"']"),
                "<mark>Clicked on Main Tab" + mainTabName + " and Clicked on Sub Tab " +subTabName +"</mark>");
    }
    public static ISuppllyLocatorInfo setLocator(By locator, String lable) {
        BALAJI.PARAMETERIZE_OBJECT.locator = locator;
        BALAJI.PARAMETERIZE_OBJECT.lable = lable;
        return BALAJI.PARAMETERIZE_OBJECT;

    }


    BALAJI(Object locator, String label) {
        if(locator == null || label == null){
            throw  new IllegalArgumentException("Argus must not be null");
        }
        this.locator = locator;
        this.lable = label;
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
