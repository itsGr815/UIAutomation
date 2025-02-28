package com.dFarm.qa.dFarm.objectrepository.Prod.Balaji;

import com.dFarm.qa.dFarm.util.datahandler.ISuppllyLocatorInfo;
import org.openqa.selenium.By;

/**
 * @author G Ganes
 */
public enum MASTERADMIN implements ISuppllyLocatorInfo {



    LABEL_PARENT_BIRD_BATCH(By.xpath("//h4[text()='Parent Bird Batch']"), "Parent Bird Batch"),
    BUTTON_ADD_PARENT_BATCH(By.xpath("//button//span[text()='Add Parent Batch ']"), " Add Parent Batch Button"),
    BUTTON_RESET(By.xpath("//button//span[text()='Reset']"), "Reset Button"),
    GLOBAL_SEARCH(By.id("globalSearch"), " Global Search "),
    TABLE_TITLE_ADD_PARENT_BATCH(By.xpath("//span[text()='Parent Batch List']"), "Parent Batch List Table Title"),


    PARENT_BATCH_DETAILS_INNER_TABLE_HEADER(By.xpath("(//table[@id='table']//thead)[2]//th"), "Parent Batch List Table Inner Headers"),
    PARENT_BATCH_DETAILS_INNER_TABLE_DATA(By.xpath("((//table[@id='table']//tbody))[2]"), "Parent Batch List Table Inner table Data"),


    PARENT_BATCH_TABLE_FIRST_EXPAND(By.xpath("((//table[@id='table']//tbody)//tr)[1]/td[12]"), "Parent Batch List Table 1st Expand"),


    //Parent Batch Allocation
    LABEL_PARENT_BATCH_ALLOCATION(By.xpath("//h4[text()='Parent Batch Allocation']"), "Parent Batch Allocation"),
    TABLE_PARENT_BATCH_ALLOCATION_LIST(By.xpath("//span[text()='Allocated Batch List']"), "Allocated Batch List"),
    BUTTON_ADD_LOCATION(By.xpath("//button//span[text()=' Add Location ']"), "Button - Add Location"),
    BUTTON_SEARCH(By.xpath("//button//span[text()='Search ']"), "Button - Search"),

    //Location Details
    LABEL_LOCATION_DETAILS(By.xpath("//h4[text()='Location Details']"), "Location Details"),




    //Add Location Overlay
    LABEL_ADD_LOCATION_OVERLAY(By.xpath("//h4[@class='addClient' and contains(.,'Add Location')]"), "Add Location Overlay Title"),
    INPUT_LOCATION_DETAILS(By.xpath("(//div[@class='farmerreg-page'])[2]//form/div"), "Input Fields - Add Location Overlay"),
    BUTTON_SUBMIT(By.xpath("//button//span[text()='Submit ']"), "Button - Submit"),
    ICON_SVG_CLOSE(By.xpath("//*[name()='svg']"), "SVG Close Icon"),

    //Shed Details
    LABEL_SHED_DETAILS(By.xpath("//h4[text()='Shed Details']"), "Shed Details"),
    BUTTON_ADD_SHED(By.xpath("//button//span[text()=' Add Shed ']"), "Button - Add Shed"),
    LABEL_ADD_SHED_OVERLAY(By.xpath("//h4[@class='addClient' and contains(.,'Add Shed')]"), "Add Shed Overlay"),
    TABLE_SHED_DETAILS_TITLE(By.xpath("//span[text()='Shed Details']"), "Shed Details Table Title"),


    //Hatchery Building Details
    LABEL_HATCHERY_BUILDING_DETAILS(By.xpath("//h4[text()='Hatchery Building Details']"), "Hatchery Building Details"),
    BUTTON_ADD_HB_LOCATION(By.xpath("//button//span[text()=' Add H.B Location ']"), "Button - Add H.B Location"),
    TABLE_TITLE_HATCHERY_BUILDING_DETAILS(By.xpath("//span[text()='Hatchery Building Details']"), "Table title - Hatchery Building Details"),

    //Add Hatchery Building Overlay
    LABEL_ADD_BH_OVERLAY(By.xpath("//h4[@class='addClient' and contains(.,'Add Hatchery Building')]"), "Add Hatchery Building Overlay"),
    INPUT_HB_DETAILS(By.xpath("(//div[@class='farmerreg-page'])[2]//form/div"), "Input Fields - Add Location Overlay"),

    //Breed
    LABEL_BREED(By.xpath("//h4[text()='Breed']"), "Breed Tab"),
    DROPDOWN_BIRD_TYPE(By.xpath("//mat-select[@formcontrolname='chick_type']"), "Bird Type"),
    BUTTON_ADD_BREED(By.xpath("//button//span[text()='Add Breed ']"), "Button - Add Breed"),
    TABLE_TITLE_BREED_DETAILS(By.xpath("//span[text()='Breed Details']"), "Table - Breed Details"),
    TABLE_BREED_HEADERS(By.xpath("(//table[@id='table']//thead)[1]//th"), "Breed Details Table Headers"),
    TABLE_BREED_FIRST_RECORD(By.xpath("((//table[@id='table']//tbody)//tr)[1]"), "Breed Details Table First Record"),
    TABLE_BREED_ALL_RECORDS(By.xpath("//table[@id='table']//tbody//tr"), "Breed Details Table First Record"),



    //Standard Production %
    LABEL_STANDARD_PRODUCTION(By.xpath("//h4[text()='Standard Production %']"), " Page Title - Standard Production %"),
    DROPDOWN_BIRD_SP_TYPE(By.xpath("//mat-select[@formcontrolname='bird_type']"), "Standard Production % Bird Type"),
    DROPDOWN_SP_TYPE_OF_LAYING(By.xpath("//mat-select[@formcontrolname='laying_type']"), "Standard Production % - Type Of Laying"),
    DROPDOWN_SP_PS(By.xpath("//mat-select[@formcontrolname='per_stds']"), "Standard Production % - Performance Standards"),
    BUTTON_SP_DOWNLOAD_CSV_TEMPLATE(By.xpath("//button//span[text()='Download CSV Template']"), "Standard Production % - Download CSV Template"),
    BUTTON_SP_UPDATE(By.xpath("//button//span[text()='Upload ']"), "Standard Production % - Update"),
    DROPDOWN_SP_TYPE_OF_STANDARD(By.xpath("//mat-label[text()='Type of Standard ']//../../../mat-select"), "Standard Production % - Type of Standard"),
    TABLE_SP_TITLE(By.xpath("//span[text()='Production Performance Details ']"), "Table Title - Production Performance Details"),
    MAIN_TABLE_SP_HEADERS(By.xpath("(//table[@id='table']//thead)[1]//th"), "Table Headers - Production Performance Details"),
    TABLE_SP_MAIN_FIRST_RECORD(By.xpath("((//table[@id='table']//tbody)//tr)[1]"), "Table Main Data - First record - Production Performance Details"),
    MAIN_SP_TABLE_ALL_RECORDS(By.xpath("//table[@id='table']//tbody//tr"), "Table All Records - Production Performance Details"),

    //Feed
    LABEL_FEED(By.xpath("//h4[text()='Feed']"), "Page Title - Feed"),
    DROPDOWN_FEED_BIRD_TYPE(By.xpath("//mat-select[@formcontrolname='feed_type']"), "Feed Dropdown - Bird Type"),
    DROPDOWN_FEED_CHICK_TYPE(By.xpath("//mat-select[@formcontrolname='bird_type']"), "Feed Dropdown - chick Type"),
    DROPDOWN_FEED_GENDER(By.xpath("//mat-select[@formcontrolname='gender']"), " Feed Dropdown - Gender"),
    DROPDOWN_FEED_FEED_CATEGORY(By.xpath("//input[@formcontrolname='feed_category']"), "Feed Dropdown - Feed Category"),
    INPUT_FEED_SHORT_NAME(By.xpath("//input[@formcontrolname='short_name']"), "Feed Dropdown - Short Name"),
    DROPDOWN_FEED_STATUS(By.xpath("//mat-select[@formcontrolname='status']"), " Feed Dropdown - Status"),
    BUTTON_FEED_ADD_FEED(By.xpath("//button//span[text()='Add Feed ']"), "Button - Add Feed"),
    TABLE_FEED_TITLE(By.xpath("//span[text()='Feed Details']"), " Table Title - Feed Details"),
    MAIN_TABLE_FEED_HEADERS(By.xpath("(//table[@id='table']//thead)[1]//th"), "Table Headers - Feed Details"),
    TABLE_FEED_MAIN_FIRST_RECORD(By.xpath("((//table[@id='table']//tbody)//tr)[1]"), "Table Main Data - First record - Feed Details"),
    MAIN_FEED_TABLE_ALL_RECORDS(By.xpath("//table[@id='table']//tbody//tr"), "Table All Records - Feed Details"),
    FEED_DETAILS_INNER_TABLE_HEADER(By.xpath("(//table[@id='table']//thead)[2]//th"), "Feed Details Table Inner Headers"),
    FEED_DETAILS_INNER_TABLE_DATA(By.xpath("((//table[@id='table']//tbody))[2]"), "Feed Details Table Inner table Data"),
    FEED_DETAILS_TABLE_FIRST_EXPAND(By.xpath("((//table[@id='table']//tbody)//tr)[1]/td[5]"), "Feed Details Table Expand Icon"),

    //Suppliers
    LABEL_SUPPLIERS(By.xpath("//h4[text()='Suppliers']"), "Page Title - Suppliers"),
    DROPDOWN_SUP_ORG_NAME(By.xpath("//mat-select[@formcontrolname='organization_name']"), "Suppliers - Organization Name"),
    DROPDOWN_SUP_CONTACT_PERSON(By.xpath("//mat-select[@formcontrolname='contact_person']"), "Suppliers - Contact Person"),
    BUTTON_SUP_ADD_SUPPLIER(By.xpath("//button//span[text()=' Add Supplier ']"), "Supplier - Add Supplier"),
    TABLE_SUP_TITLE(By.xpath("//span[text()='Supplier Details']"), "Supplier - Supplier Details"),
    TABLE_SUP_TABLE_HEADERS(By.xpath("(//table[@id='clientList']//thead)[1]//th"), "Supplier Details Headers"),
    TABLE_SUP_TABLE_FIRST_RECORD(By.xpath("((//table[@id='clientList']//tbody)//tr)[1]"), "Supplier Details First Record"),
    TABLE_SUP_TABLE_ALL_RECORD(By.xpath("//table[@id='clientList']//tbody//tr"), "Supplier Details All Record"),

    //Units





    //Common
    MAIN_TABLE_HEADERS(By.xpath("(//table[@id='table']//thead)[1]//th"), "Parent Batch List Table Main Headers"),
    TABLE_MAIN_FIRST_RECORD(By.xpath("((//table[@id='table']//tbody)//tr)[1]"), "Table Main Data - First record"),
    MAIN_TABLE_ALL_RECORDS(By.xpath("//table[@id='table']//tbody//tr"), "Parent Batch List Table Data"),

    FILTER_LOCATION_NAME(By.xpath("//mat-select[@formcontrolname='location_name']"), "Location Name"),
    TABLE_HEADERS(By.xpath("(//table[@id='clientList']//thead)[1]//th"), "Table Headers"),
    TABLE_FIRST_RECORDS(By.xpath("((//table[@id='clientList']//tbody)//tr)[1]"), "Table First Records"),
    TABLE_ALL_RECORDS(By.xpath("//table[@id='clientList']//tbody//tr"),"Table All records"),


    PARAMETERIZE_OBJECT(By.xpath(""), "");

    ;
    public static ISuppllyLocatorInfo setLocator(By locator, String lable) {
        MASTERADMIN.PARAMETERIZE_OBJECT.locator = locator;
        MASTERADMIN.PARAMETERIZE_OBJECT.lable = lable;
        return MASTERADMIN.PARAMETERIZE_OBJECT;

    }


    MASTERADMIN(Object locator, String label) {
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
