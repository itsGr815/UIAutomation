package com.dFarm.qa.dFarm.objectrepository.Prod.Balaji;

import com.dFarm.qa.dFarm.util.datahandler.ISuppllyLocatorInfo;
import org.openqa.selenium.By;

/**
 * @author G Ganesh
 */
public enum POULTRY implements ISuppllyLocatorInfo {

    //Daily Report
    LABEL_PAGE_TITLE_DR(By.xpath("//h4[text()='Daily Report']"), "Page Title - Daily Report"),
    INPUT_DR_DATE(By.xpath("//input[@formcontrolname='date']"), "Daily Report - Date"),
    INPUT_DR_BATCH_NO(By.xpath("//input[@formcontrolname='batch_no']"), "Daily Report  - Batch No"),
    DROPDOWN_DR_LOCATION_NAME(By.xpath("//mat-select[@formcontrolname='location_id']"), "Daily Report - Location name"),
    DROPDOWN_DR_SHED(By.xpath("//mat-select[@formcontrolname='shed']"), "Daily Report - Shed"),
    TABLE_DR_TITLE(By.xpath("//span[text()='Daily Report']"), " Table title - Daily Report"),
    TABLE_DR_HEADERS(By.xpath("(//table[@id='table']//thead)[1]//th"), "Daily Report Headers"),
    TABLE_DR_FIRST_RECORD(By.xpath("((//table[@id='table']//tbody)//tr)[1]"), "Daily Report First record"),
    TABLE_DR_ALL_RECORD(By.xpath("//table[@id='table']//tbody//tr"), "Daily Report First record"),
    ICON_DR_DOWNLOAD(By.xpath("//button//mat-icon[text()='get_app']"), "Daily Report Download Icon"),

    //Daily Feed Details
    LABEL_DFR_PAGE_TITLE(By.xpath("//h4[text()='Daily Feed Details']"), "Page Title - Daily Feed Details"),
    INPUT_DFR_DATE(By.xpath("//input[@formcontrolname='date']"), "Daily Feed Details - Date"),
    INPUT_DFR_BATCH_NO(By.xpath("//input[@formcontrolname='batch_no']"), "Daily Feed Details  - Batch No"),
    DROPDOWN_DFR_LOCATION_NAME(By.xpath("//mat-select[@formcontrolname='location_id']"), "Daily Feed Details - Location name"),
    DROPDOWN_DFR_SHED(By.xpath("//mat-select[@formcontrolname='shed']"), "Daily Feed Details - Shed"),
    DROPDOWN_DFR_FEED_CATEGORY(By.xpath("//mat-select[@formcontrolname='feed_category']"), "Daily Feed Details - Feed Category"),
    TABLE_DFR_TITLE(By.xpath("//span[text()='Daily Feed Details']"), "Table title - Daily Feed Details"),





    PARAMETERIZE_OBJECT(By.xpath(""), "");

    ;
    public static ISuppllyLocatorInfo setLocator(By locator, String lable) {
        POULTRY.PARAMETERIZE_OBJECT.locator = locator;
        POULTRY.PARAMETERIZE_OBJECT.lable = lable;
        return POULTRY.PARAMETERIZE_OBJECT;

    }


    POULTRY(Object locator, String label) {
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
