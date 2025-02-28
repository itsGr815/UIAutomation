package com.dFarm.qa.dFarm.objectrepository.Prod;

import com.dFarm.qa.dFarm.util.datahandler.ISuppllyLocatorInfo;
import org.openqa.selenium.By;

public enum PACKHOUSE implements ISuppllyLocatorInfo {

    //Stock Master
    INPUT_PRODUCE_ID_SEARCH(By.xpath("//input[@placeholder='Produce ID']"), "Produce ID"),
    BUTTON_PRODUCE_ID_SEARCH(By.xpath("//input[@placeholder='Produce ID']/../..//button[@aria-label='Search']"), "Produce ID Button"),
    BUTTON_CREATE_NEW_ORDER(By.xpath("//button/span[normalize-space()='Create New Order']"), "Create New Order"),
    LABEL_INTAKE_ORDER_DETAILS_TABLE_TITLE(By.xpath("//span[normalize-space()='In-Take Order Details']"), "In-Take Order Details Table"),
    TABLE_TAKE_ORDER_DETAILS_DATA(By.xpath("//span[normalize-space()='In-Take Order Details']/../..//ngx-table//tbody/tr"), "In-Take Order Details Table data"),
    TABLE_TAKE_ORDER_DETAILS_NO_RESULTS(By.xpath("//span[normalize-space()='In-Take Order Details']/../..//ngx-table//tbody/tr//div"), "In-Take Order Details No Results"),
    TABLE_TAKE_ORDER_DETAILS_PR_ID(By.xpath("(//span[normalize-space()='In-Take Order Details']/../..//ngx-table//tbody/tr/td[2])[1]"), "PR ID NUmber"),
    TABLE_TAKE_ORDER_DETAILS_FARMER_ID(By.xpath("(//span[normalize-space()='In-Take Order Details']/../..//ngx-table//tbody/tr/td[4])[1]"), " Farmer ID"),

    //In-Take List
    LABEL_IN_TAKE_LIST_TABLE_TITLE(By.xpath("//span[normalize-space()='In-Take List']"), "Table Title - In-Take List"),
    STICKY_NOTE_PRODUCE_INFO(By.xpath("//div[@class='info']/div"), "Produce Info Sticky Notes"),
    DROPDOWN_PRODUCE_ID(By.xpath("//div[@role='listbox']/mat-option"), "Produce List DropDown"),



    PARAMETERIZE_OBJECT(By.xpath(""), "");
    ;

    public static synchronized ISuppllyLocatorInfo navigateToPackhouseSubTabs(String subTabName) {
        return setLocator(By.xpath("//ul[@class='tabview-title-list']//a[normalize-space()='"+subTabName+"']"), subTabName);
    }

    public static synchronized ISuppllyLocatorInfo selectProduceStatusButton(String status) {
        return setLocator(By.xpath("//div[@id='pf-confirm' and normalize-space(.)='"+status+"']"), status);
    }

    public static synchronized ISuppllyLocatorInfo getProduceInfoStickyNote(int infoId) {
        return setLocator(By.xpath("//div[@class='info']/div["+infoId+"]"), "Produce Info Sticky Notes");
    }

    public static synchronized ISuppllyLocatorInfo selectRandomProduceID(int index) {
        return setLocator(By.xpath("//div[@role='listbox']/mat-option["+index+"]"), "Produce ID Selection");
    }

    public static ISuppllyLocatorInfo setLocator(By locator, String lable) {
        PACKHOUSE.PARAMETERIZE_OBJECT.locator = locator;
        PACKHOUSE.PARAMETERIZE_OBJECT.lable = lable;
        return PACKHOUSE.PARAMETERIZE_OBJECT;

    }


    PACKHOUSE(Object locator, String label) {
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
