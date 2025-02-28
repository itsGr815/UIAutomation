package com.dFarm.qa.dFarm.objectrepository.Prod;

import com.dFarm.qa.dFarm.util.datahandler.ISuppllyLocatorInfo;
import org.openqa.selenium.By;

public enum ADMINPACKHOUSE implements ISuppllyLocatorInfo{


    //PH List
    LABEL_PH_LIST(By.xpath("//span[normalize-space()='Pack Houses']"), "Pack House"),
    INPUT_SEARCH_PH_LIST(By.xpath("//input[@id='globalSearch' and @placeholder[normalize-space()='Search by Company ID or Company Name or State']]"),
            "PH List - Search by Company ID or Company Name or State"),
    TABLE_PH_LIST_NO_RESULTS(By.xpath("//span[normalize-space()='Pack Houses']/../..//ngx-table//tbody/tr//div"), "PH List Table - No Results"),
    LABEL_PH_NAME(By.xpath("//span[normalize-space()='Pack Houses']/../..//ngx-table//tbody/tr//td[2]"), "Pack House Name"),
    LABEL_PH_MAIN_ID(By.xpath("//span[normalize-space()='Pack Houses']/../..//ngx-table//tbody/tr//td[1]"), "PH MAIN ID"),
    TABLE_PH_LIST_DATA(By.xpath("//span[normalize-space()='Pack Houses']/../..//ngx-table//tbody/tr"), "Pack Houses Table Data"),

    //Stock Master
    DROPDOWN_PRODUCE(By.xpath("//mat-select[@formcontrolname='produce']"), "Produce"),
    DROPDOWN_VARIETY(By.xpath("//mat-select[@formcontrolname='variety']"), "Variety"),
    BUTTON_SEARCH(By.xpath("//button[normalize-space()='Search']"), "Search Button"),
    BUTTON_RESET(By.xpath("//button[normalize-space()='Reset']"), "Reset Button"),
    TAB_COLLECTED(By.xpath("//div[@role='tab']/div[normalize-space()='Collected']"), " Stock Master -  Collected"),
    TAB_ACCEPTED(By.xpath("//div[@role='tab']/div[normalize-space()='Accepted']"), " Stock Master -  Accepted"),
    LABEL_AVAILABLE_STOCK(By.xpath("//span[normalize-space()='Available Stock']"), "Available Stock"),
    TABLE_AVAILABLE_STOCK_NO_RESULTS(By.xpath("//span[normalize-space()='Available Stock']/../../..//ngx-table//tbody/tr//div"), "Available Stock"),
    TABLE_AVAILABLE_STOCK_LIST(By.xpath("//span[normalize-space()='Available Stock']/../../..//ngx-table//tbody/tr"), "Available Stock Data"),
    VALUE_PR_ID(By.xpath("(//span[normalize-space()='Available Stock']/../../..//ngx-table//tbody/tr/td[3])[1]"), "PR-ID Value"),
    LABEL_TOTAL_AVAILABLE_WEIGHT(By.xpath("//b[contains(.,'Total Available Weight')]"), "Total Available Weight"),

    //Sorting and Grading Report
    DROPDOWN_GRADE(By.xpath("//mat-select[@formcontrolname='grade']"), "Grade"),
    LABEL_GRADING_LIST(By.xpath("//span[normalize-space()='Grading List']"), "Grading List"),
    TABLE_GRADING_LIST_DATA(By.xpath("//span[normalize-space()='Grading List']/../../../ngx-table//tbody/tr"), "Grading List table data"),
    LABEL_GRADING_LIST_NO_RESULTS(By.xpath("//span[normalize-space()='Grading List']/../../../ngx-table//tbody/tr//div"), "No Results - Grading List"),
    TABLE_GRADING_LIST_1ST_ROW(By.xpath("//span[normalize-space()='Grading List']/../../../ngx-table//tbody/tr[1]"), "Grading List table data"),

    //Sales Order
    INPUT_SEARCH_ORDER_DETAILS(By.xpath("//input[@id='globalSearch']/..//mat-label[normalize-space()='Search Order Details']"), "Search Order Details"),
    BUTTON_SALES_ORDER_RESET(By.xpath("//div[@id='pf-confirm' and normalize-space()='Reset']"), "Reset Button"),
    LABEL_ORDER_DETAILS(By.xpath("//span[normalize-space()='Order Details']"), "Order Details"),
    TABLE_SALES_ORDER_LIS(By.xpath("//span[normalize-space()='Order Details']/../..//ngx-table//tbody/tr"), "Sales Order List"),
    VALUE_SALES_ORDER_NUMBER(By.xpath("(//span[normalize-space()='Order Details']/../..//ngx-table//tbody/tr/td[2])[1]"), "Sales Order Number"),
    LABEL_SALES_ORDER_NO_RESULTS(By.xpath("//span[normalize-space()='Order Details']/../..//ngx-table//tbody/tr//div"), "Order Details - No Results"),


    //Dispatch
    INPUT_SEARCH_DISPATCH_LIST(By.xpath("//input[@id='globalSearch']/..//mat-label[normalize-space()='Search']"), "Dispatch List Search Input box"),
    LABEL_TRACKING_LIST(By.xpath("//span[normalize-space()='Tracking List']"), "Tracking List"),
    LABEL_DISPATCH_LIST_NO_RESULTS(By.xpath("//span[normalize-space()='Tracking List']/../../..//ngx-table//tbody/tr//div"), "No Results - Tracking List"),
    BUTTON_DISPATCH_RESET(By.xpath("//button[@type='reset' and normalize-space()='Reset']"), "Dispatch - Reset buttons"),
    TABLE_DISPATCH_LIST_DATA(By.xpath("//span[normalize-space()='Tracking List']/../../..//ngx-table//tbody/tr"), "Tracking List Table Data"),
    LABEL_DELIVERY_ID(By.xpath("(//span[normalize-space()='Tracking List']/../../..//ngx-table//tbody/tr//td[2])[1]"), "Delivery ID - Tracking List"),
    LABEL_SALES_ORDER_ID(By.xpath("(//span[normalize-space()='Tracking List']/../../..//ngx-table//tbody/tr//td[3])[1]"), "Sales Order ID - Tracking List"),



    //Clients
    LABEL_CLIENT_DETAILS(By.xpath("//span[normalize-space()='Client Details']"), "Client Details"),
    INPUT_CLIENT_SEARCH(By.xpath("//input[@id='globalSearch' and @placeholder[normalize-space()='Search by Client details']]"), "Search by Client details"),
    BUTTON_ADD_NEW_CLIENT(By.xpath("//button//span[normalize-space()='Add new']"), "Add New Button"),
    BUTTON_BUYERS(By.xpath("//div[@id='pf-confirm' and normalize-space()='Buyers']"), "Buyer Button"),
    BUTTON_SUPPLIERS(By.xpath("//div[@id='pf-confirm' and normalize-space()='Suppliers']"), "Suppliers Button"),
    LABEL_ADD_NEW_CLIENT(By.xpath("//h4[normalize-space()='Add Buyer/Vendor']"), "Add Buyer/Vendor"),
    LABEL_ADD_NEW_SUPPLIER_BUYER(By.xpath("//h4[normalize-space()='Add Supplier/Buyer']"), "Add Supplier/Buyer"),

    LABEL_CLIENTS_NO_RESULTS(By.xpath("//span[normalize-space()='Client Details']/../../..//ngx-table//tbody/tr//div"), "No Results - Client Details"),
    TABLE_CLIENTS_DETAILS_LIST(By.xpath("//span[normalize-space()='Client Details']/../../..//ngx-table//tbody/tr") , "Client Details"),
    LABEL_CLIENT_COMPANY_NAME(By.xpath("(//span[normalize-space()='Client Details']/../../..//ngx-table//tbody/tr/td[4])[1]"), "Client Details - Company Name"),
    LABEL_CLIENT_COMPANY_ID(By.xpath("(//span[normalize-space()='Client Details']/../../..//ngx-table//tbody/tr/td[3])[1]"), "Client Details - Company ID"),


    PARAMETERIZE_OBJECT(By.xpath(""), "");
    ;


    public static synchronized ISuppllyLocatorInfo clickPHSubTab(String subTabName) {
        return setLocator(By.xpath("//ul[@class='tabview-title-list']//a[normalize-space()='"+subTabName+"']"), subTabName );
    }

    public static synchronized ISuppllyLocatorInfo selectProduceOrVariety(String produceOrVariety) {
        return setLocator(By.xpath("//mat-option[@role='option' and normalize-space()='"+produceOrVariety+"']"), "Selected " + produceOrVariety);
    }
    public static ISuppllyLocatorInfo setLocator(By locator, String lable) {
        ADMINPACKHOUSE.PARAMETERIZE_OBJECT.locator = locator;
        ADMINPACKHOUSE.PARAMETERIZE_OBJECT.lable = lable;
        return ADMINPACKHOUSE.PARAMETERIZE_OBJECT;

    }


    ADMINPACKHOUSE(Object locator, String label) {
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
