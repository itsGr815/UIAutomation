package com.dFarm.qa.dFarm.objectrepository;

import com.dFarm.qa.dFarm.constants.DFarmConstants;
import com.dFarm.qa.dFarm.util.datahandler.ISuppllyLocatorInfo;
import org.openqa.selenium.By;

public enum LOGOUT implements ISuppllyLocatorInfo {

    ICON_USER_PROFILE(By.className("hib-user-icon-box"), "Profile Icon - person_outline"),
    LINK_LOGOFF(By.xpath("//div[@class='cdk-overlay-pane']/div//following-sibling::li[@class='logoffbtn']/a[contains(.,'Logoff')]"), "LogOff Link"),
    OVERLAY_LOGOFF_CONFIRMATION(By.xpath("(//div[@class='cdk-overlay-container']/div)[2]/div//mat-dialog-container"), "Logoff Confirmation overlay"),
    OVERLAY_LOGOFF_CONFIRMATION_HEADER(By.xpath("//span[normalize-space()='Are you sure?']"), DFarmConstants.ARE_YOU_SURE),
    OVERLAY_LOGOFF_CONFIRMATION_CONTENT_LINE1(By.xpath("(//div[@class='popup-content']/p)[1]"), DFarmConstants.OVERLAY_LOGOFF_CONFIRMATION_CONTENT_LINE1),
    OVERLAY_LOGOFF_CONFIRMATION_CONTENT_LINE2(By.xpath("(//div[@class='popup-content']/p)[2]"), DFarmConstants.OVERLAY_LOGOFF_CONFIRMATION_CONTENT_LINE2),
    BUTTON_LOGOFF_CANCEL(By.id("pf-cancel"), "Cancel - Logoff"),
    BUTTON_LOGOFF_CONFIRM(By.id("pf-confirm"), "Confirm - Logoff"),




    PARAMETERIZE_OBJECT(By.xpath(""), "");


    LOGOUT(Object locator, String label) {
        if(locator == null || label == null){
            throw  new IllegalArgumentException("Argus must not be null");
        }
        this.locator = locator;
        this.lable = label;
    }

    private final Object locator;
    private final String lable;

    public Object getLocator() {
        return locator;
    }

    public String getLabel() {
        return lable;
    }
}
