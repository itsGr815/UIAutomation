package com.dFarm.qa.dFarm.objectrepository.Prod;

import com.dFarm.qa.dFarm.util.datahandler.ISuppllyLocatorInfo;
import org.openqa.selenium.By;

public enum FARMER implements ISuppllyLocatorInfo {


    //Enroll Farmer
    LABEL_ENROLL_FARMER(By.xpath("//span[text()[normalize-space() = 'Enroll Farmer']]"), "Enroll Farmer"),
    LABEL_FARMER_LIST(By.xpath("//span[normalize-space()='Farmers List']/following-sibling::span"), "Farmers List  Count"),
    TABLE_FARMER(By.xpath("//span[text()[normalize-space() = 'Enroll Farmer']]/../../div/ngx-table/div/table/tbody"), "Farmer List table"),
    TABLE_FARMER_LIST(By.xpath("//span[text()[normalize-space() = 'Enroll Farmer']]/../../div/ngx-table/div/table/tbody/tr"), "Farmer List"),

    //Farmer Produce
    LABEL_FARMER_PRODUCE(By.xpath("//span[normalize-space()='Farmer Produce']"), "Farmer Produce"),
    LABEL_FARMER_PRODUCE_COUNT(By.xpath("//span[normalize-space()='Farmer Produce']/following-sibling::span"), "Farmer Produce Count"),
    TABLE_PRODUCE_LIST(By.xpath("//span[text()[normalize-space() = 'Farmer Produce']]/../../div/ngx-table/div/table/tbody/tr"), "Farmer Produce List"),
    TABLE_PRODUCE_LIST_DATE(By.xpath("//span[text()[normalize-space() = 'Farmer Produce']]/../../div/ngx-table/div/table/tbody/tr//div"), "Farmer Produce List table data"),

    //Farmer Financials
    LABEL_FARMER_FINANCIAL(By.xpath("//span[normalize-space()='Farmer Financial']"), "Farmer Financial"),
    TABLE_FARMER_FINANCIAL_LIST(By.xpath("//span[normalize-space()='Farmer Financial']/../../../ngx-table//tbody/tr"), "Farmer Financial List"),
    TABLE_FARMER_FINANCIAL_NO_RESULTS(By.xpath("//span[normalize-space()='Farmer Financial']/../../../ngx-table//tbody/tr//div"), "No Results - Farmer Financial"),
    TABLE_FARMER_FINANCIAL_DATA(By.xpath("(//span[normalize-space()='Farmer Financial']/../../../ngx-table//tbody/tr/td[2])[1]"), "Farmer Financial"),

    //Farmer Products
    LABEL_FARMER_PRODUCTS(By.xpath("//span[normalize-space()='Products']"), "Farmer Products"),
    ICON_ADD_PRODUCT(By.xpath("//span[normalize-space()='Products']/..//button[@mattooltip[normalize-space()='Add Product']]"), "Add Product Icon"),
    ICON_SAVE_CHANGES(By.xpath("//span[normalize-space()='Products']/..//button[@mattooltip[normalize-space()='Save Changes']]"), "Save Changes"),
    ICON_RESET_CHANGES(By.xpath("//span[normalize-space()='Products']/..//button[@mattooltip[normalize-space()='Reset Changes']]"), "Reset Changes"),
    TABLE_PRODUCTS_LIST(By.xpath("//span[normalize-space()='Products']/../..//ngx-table//tbody/tr"), "Products List Table"),
    DROPDOWN_ROWS_PER_PAGE(By.xpath(".//div[@class='ngx-pagination-range-dropdown-button']"), "Rows Per Page"),


    //PRICING
    LABEL_PRICE_LIST(By.xpath("//span[normalize-space()='Price List']"), "Farmer Price List"),
    LABEL_NO_RESULTS_PRICE_LIST(By.xpath("//span[normalize-space()='Price List']/../../..//ngx-table//tbody//tr//div"), "No Results - Price List"),
    TABLE_PRICE_LIST_DATA(By.xpath("//span[normalize-space()='Price List']/../../..//ngx-table//tbody//tr"),"Farmer Price List Details"),

    PARAMETERIZE_OBJECT(By.xpath(""), "");
    ;

    public static ISuppllyLocatorInfo setLocator(By locator, String lable) {
        FARMER.PARAMETERIZE_OBJECT.locator = locator;
        FARMER.PARAMETERIZE_OBJECT.lable = lable;
        return FARMER.PARAMETERIZE_OBJECT;

    }

    public static synchronized ISuppllyLocatorInfo getFarmerSubTabs(String subTabName) {
        return setLocator(By.xpath("//ul[@class='tabview-title-list']//a[contains(.,'"+subTabName+"')]"), subTabName);
    }

    public static synchronized ISuppllyLocatorInfo selectRowsPerPage(String rowsPerPage){
        return setLocator(By.xpath(".//div[@class='ngx-pagination-range-dropdown-button']/../ul/li/span[normalize-space()='"+rowsPerPage+"']"),
                "<mark>" +rowsPerPage + " Records Selected Per Page </mark>");
    }

    public static synchronized ISuppllyLocatorInfo getProductSourceCountry(int coutn) {
        return setLocator(By.xpath("(//span[normalize-space()='Products']/../..//ngx-table//tbody/tr/td[1])["+coutn+"]"), "Products Source Country");
    }

    public static synchronized ISuppllyLocatorInfo getProductName(int coutn) {
        return setLocator(By.xpath("(//span[normalize-space()='Products']/../..//ngx-table//tbody/tr/td[2])["+coutn+"]"), "Products Source Country");
    }

    FARMER(Object locator, String label) {
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
