package com.dFarm.qa.dFarm.pageobjects.Prod;

import com.dFarm.qa.dFarm.objectrepository.Prod.Balaji.BALAJI;
import com.dFarm.qa.dFarm.objectrepository.Prod.FARMER;
import com.dFarm.qa.dFarm.util.execution.AllDataHolder;
import com.dFarm.qa.dFarm.util.execution.FlexFrameWorkRunTimeException;
import org.testng.Assert;

import java.util.List;

public class BalajiFarmer {

    /**
     * @implNote Method to verify the navigation to Main tabs in Balaji admin application based on tabName
     * @param mainTabName
     * @throws FlexFrameWorkRunTimeException
     */

    public void navigateBalajiMainTabs(String mainTabName) throws  FlexFrameWorkRunTimeException {
        try {
            AllDataHolder.getUtil().clickElement(BALAJI.ICON_MENU);
            AllDataHolder.getUtil().clickElement(BALAJI.clickMainTab(mainTabName));
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e.getMessage());
        }
    }

    /**
     * @implNote Method to verify the navigation to Sub tabs in Balaji admin application based on tabName
     * @param mainTabName, subTabName
     * @throws FlexFrameWorkRunTimeException
     */

    public void navigateBalajiSubTabs(String mainTabName, String subTabName) throws  FlexFrameWorkRunTimeException {
        try {
            if (AllDataHolder.getUtil().isContentDisplayed(BALAJI.clickSubTab(mainTabName, subTabName))){
                AllDataHolder.getUtil().clickElement(BALAJI.clickSubTab(mainTabName, subTabName));
            }else {
                AllDataHolder.getUtil().report(1, "Clicking on Sub Tab",
                        "Clicking on Sub Tab",
                        "<mark>Sub tab is not displayed, please check the Permission </mark>");
                Assert.fail();
            }
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e.getMessage());
        }
    }

    /**
     * @implNote Method to verify the navigation to Sub tabs in Balaji admin application based on tabName
     * @param mainTabName, subTabName
     * @throws FlexFrameWorkRunTimeException
     */

    public void navigateBalajiFarmerTab(String mainTabName, String subTabName) throws  FlexFrameWorkRunTimeException {
        try {
            navigateBalajiMainTabs(mainTabName);
            navigateBalajiSubTabs(mainTabName, subTabName);
            if(AllDataHolder.getUtil().isContentDisplayed(FARMER.LABEL_ENROLL_FARMER)){
                int tableCount = AllDataHolder.getUtil().sizeOfWebElement(FARMER.TABLE_FARMER_LIST);
                if(tableCount > 0){
                    AllDataHolder.getUtil().report(1, "<mark>User Navigated to </mark> " +
                            " <b>" + subTabName +"</b>", "", "");
                }else{
                    AllDataHolder.getUtil().contentDisplayed(FARMER.TABLE_FARMER);
                    AllDataHolder.getUtil().report(1, "<mark>Farmer are not registered </mark> " +
                            " <b>" + subTabName +"</b>", "", "");
                }
            }else {
                AllDataHolder.getUtil().report(1, "<mark>Enroll Farmer Tab is not loaded </mark> " +
                        " <b>" + subTabName +"</b>", "", "");
                Assert.fail();
            }
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e.getMessage());
        }
    }

    /**
     * @param subTabName
     * @throws FlexFrameWorkRunTimeException
     * @implNote Method to verify the navigation to the Produce tab
     */
    public void verifyBalajiProduceTab(String mainTabName,String subTabName) throws FlexFrameWorkRunTimeException {
        try {
            AllDataHolder.getUtil().clickElement(BALAJI.ICON_MENU);
            if (AllDataHolder.getUtil().isContentDisplayed(BALAJI.clickSubTab(mainTabName, subTabName))) {
                AllDataHolder.getBalajiFarmer().navigateBalajiSubTabs(mainTabName, "Produce");
                AllDataHolder.getUtil().contentDisplayed(FARMER.LABEL_FARMER_PRODUCE);
                AllDataHolder.getUtil().report(1, "<mark>Farmers Produce List  : </mark> " +
                        " <b>" + subTabName + "</b>", "",
                        "<b><mark> Farmer Produce :" + AllDataHolder.getUtil().getText(FARMER.LABEL_FARMER_PRODUCE_COUNT) + "</b></mark>");
                int tableCount = AllDataHolder.getUtil().sizeOfWebElement(FARMER.TABLE_PRODUCE_LIST);
                if (tableCount > 0) {
                    String tableData = AllDataHolder.getUtil().getText(FARMER.TABLE_PRODUCE_LIST_DATE);
                    if (!tableData.trim().equals("No results")) {
                        AllDataHolder.getUtil().report(1, "<mark>User Navigated to </mark> " +
                                " <b>" + subTabName + "</b>", "", "");
                    } else {
                        AllDataHolder.getUtil().contentDisplayed(FARMER.TABLE_PRODUCE_LIST);
                        AllDataHolder.getUtil().report(1, "<mark>Produces are not created </mark> " +
                                " <b>" + subTabName + "</b>", "", "");
                    }
                } else {
                    AllDataHolder.getUtil().report(1, "<mark>Farmer Produces Page is not Displayed </mark> " +
                            " <b>" + subTabName + "</b>", "", "");
                }
            } else {
                AllDataHolder.getUtil().report(1, "Clicking on Farmer Produce Sub Tab",
                        "Clicking on Farmer Produce Sub Tab",
                        "<mark>Produce tab is not displayed, please check the Permission </mark>");
                Assert.fail();
            }
        } catch (Exception e) {
            throw new FlexFrameWorkRunTimeException(e.getMessage());
        }
    }


    /**
     * @param subTabName
     * @throws FlexFrameWorkRunTimeException
     * @implNote Method to verify the navigation to the Farmer Financials tab
     */
    public void  verifyBalajiFinancialTab(String mainTabName, String subTabName) throws FlexFrameWorkRunTimeException {
        try {
            AllDataHolder.getUtil().clickElement(BALAJI.ICON_MENU);
            if (AllDataHolder.getUtil().isContentDisplayed(BALAJI.clickSubTab(mainTabName, subTabName))) {
                AllDataHolder.getBalajiFarmer().navigateBalajiSubTabs(mainTabName, "Financials");
                AllDataHolder.getUtil().contentDisplayed(BALAJI.LABEL_FARMER_FINANCIALS_PAGE_TITLE);
                AllDataHolder.getUtil().contentDisplayed(BALAJI.LABEL_ADD_PAYMENT);
                AllDataHolder.getUtil().contentDisplayed(BALAJI.BUTTON_ADD_PAYMENT);
                AllDataHolder.getUtil().contentDisplayed(BALAJI.BUTTON_RESET_PAYMENT);
                AllDataHolder.getUtil().contentDisplayed(BALAJI.LABEL_FARMER_FINANCIALS);
                AllDataHolder.getUtil().contentDisplayed(BALAJI.LABEL_FARMER_FINANCIALS);
                int tableCount = AllDataHolder.getUtil().sizeOfWebElement(BALAJI.TABLE_FARMER_FINANCIALS_LIST);
                String tableData = null;
                if (tableCount > 0) {
                    if (AllDataHolder.getUtil().isContentDisplayed(BALAJI.LABEL_FARMER_FINANCIALS_NO_RESULTS)) {
                        tableData = AllDataHolder.getUtil().getText(BALAJI.LABEL_FARMER_FINANCIALS_NO_RESULTS);
                    } else {
                        tableData = AllDataHolder.getUtil().getText(BALAJI.TABLE_FARMER_FINANCIALS_LIST);
                    }
                    if (!tableData.trim().equals("No results")) {
                        AllDataHolder.getUtil().report(1, "<mark>User Navigated to </mark> " +
                                " <b>" + subTabName + "</b>", "", "<mark>GRN: " + tableData + "</mark>");
                    } else {
                        AllDataHolder.getUtil().contentDisplayed(BALAJI.LABEL_FARMER_FINANCIALS_NO_RESULTS);
                        AllDataHolder.getUtil().report(1, "<mark>Farmer Financial are not created </mark> " +
                                " <b>" + subTabName + "</b>", "", "");
                    }
                } else {
                    AllDataHolder.getUtil().report(1, "Clicking on Farmer Financials Sub Tab",
                            "Clicking on Farmer Produce Sub Tab",
                            "<mark>Farmer Financials tab is not displayed, please check the Permission </mark>");
                    Assert.fail();
                }
            }
        } catch (Exception e) {
            throw new FlexFrameWorkRunTimeException(e.getMessage());
        }
    }

    /**
     * @param subTabName
     * @throws FlexFrameWorkRunTimeException
     * @implNote Method to verify the navigation to the Products tab
     */
    public void verifyBalajiProductsTab(String mainTabName, String subTabName) throws FlexFrameWorkRunTimeException {
        try {
            AllDataHolder.getUtil().clickElement(BALAJI.ICON_MENU);
            if (AllDataHolder.getUtil().isContentDisplayed(BALAJI.clickSubTab(mainTabName, subTabName))) {
                AllDataHolder.getBalajiFarmer().navigateBalajiSubTabs(mainTabName, subTabName);
                AllDataHolder.getUtil().contentDisplayed(BALAJI.LABEL_FARMER_PRODUCTS_PAGE_TITLE);
                AllDataHolder.getUtil().contentDisplayed(FARMER.ICON_ADD_PRODUCT);
                AllDataHolder.getUtil().contentDisplayed(FARMER.ICON_RESET_CHANGES);
                AllDataHolder.getUtil().clickElement(FARMER.DROPDOWN_ROWS_PER_PAGE);
                AllDataHolder.getUtil().clickElement(FARMER.selectRowsPerPage("100"));
                int tableCount = AllDataHolder.getUtil().sizeOfWebElement(FARMER.TABLE_PRODUCTS_LIST);
                if (tableCount > 0) {
                    AllDataHolder.getUtil().report(1, "<mark>User Navigated to </mark> " +
                            " <b>" + subTabName + "</b>", "", "<br><mark>List of Added Products</mark></br>");

                    List<String> productsList = AllDataHolder.getUtil().getTextFromWebElements(FARMER.TABLE_PRODUCTS_LIST);
                    AllDataHolder.getUtil().report(1, "Listing All Added Product Details",
                            "List of All Added Products", "List of All Added Products");

                    for (int colCount = 0; colCount < productsList.size() ; colCount++){
                        AllDataHolder.getUtil().report(1, "<u><b>Product Name And Source Country Details</b></u>",
                                "Product Name And Source Country",
                                "<mark>Product Source Country : "
                                        + AllDataHolder.getUtil().getText(FARMER.getProductSourceCountry(colCount + 1)) +
                                        " : <br>" + "Product Name :" +  AllDataHolder.getUtil().getText(FARMER.getProductName(colCount + 1)) + "</br> </mark>"
                        );
                    }

                } else {
                    AllDataHolder.getUtil().contentDisplayed(FARMER.TABLE_PRODUCTS_LIST);
                    AllDataHolder.getUtil().report(1, "<mark>Products are not Created </mark> " +
                            " <b>" + subTabName + "</b>", "", "");
                }
            } else {
                AllDataHolder.getUtil().report(1, "Clicking on Farmer Products Sub Tab",
                        "Clicking on Farmer Produce Sub Tab",
                        "<mark>Products tab is not displayed, please check the Permission </mark>");
                Assert.fail();
            }
        } catch (Exception e) {
            throw new FlexFrameWorkRunTimeException(e.getMessage());
        }
    }
}
