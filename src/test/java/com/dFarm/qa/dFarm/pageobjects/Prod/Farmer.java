package com.dFarm.qa.dFarm.pageobjects.Prod;

import com.dFarm.qa.dFarm.objectrepository.LOGIN;
import com.dFarm.qa.dFarm.objectrepository.Prod.FARMER;
import com.dFarm.qa.dFarm.util.execution.AllDataHolder;
import com.dFarm.qa.dFarm.util.execution.FlexFrameWorkRunTimeException;
import org.testng.Assert;

import java.util.List;

public class Farmer {

    /**
     * @param tabName
     * @throws FlexFrameWorkRunTimeException
     * @implNote Method to verify the navigation to tabs in FPO admin application based on tabName
     * @author Gangarapu.Ganesh
     */
    public void navigateToAllTabs(String tabName) throws FlexFrameWorkRunTimeException {
        try {
            AllDataHolder.getUtil().clickElement(LOGIN.getHomePageTabs(tabName));
            AllDataHolder.getUtil().report(1, "<mark>User Navigated to </mark> " +
                    " <b>" + tabName + "</b>", "", "");
        } catch (Exception e) {
            throw new FlexFrameWorkRunTimeException(e.getMessage());
        }
    }

    /**
     * @param subTabName
     * @throws FlexFrameWorkRunTimeException
     * @implNote Method to verify the navigation to  Enroll farmer tab
     * @author Gangarapu.Ganesh
     */
    public void verifyEnrollFarmerTab(String subTabName) throws FlexFrameWorkRunTimeException {
        try {
            AllDataHolder.getUtil().contentDisplayed(FARMER.getFarmerSubTabs(subTabName));
            AllDataHolder.getUtil().contentDisplayed(FARMER.LABEL_FARMER_LIST);
            AllDataHolder.getUtil().report(1, "<mark>Farmers List  : </mark> " +
                    " <b>" + subTabName + "</b>", "", "<b><mark>" + AllDataHolder.getUtil().getText(FARMER.LABEL_FARMER_LIST) + "</b></mark>");
            int tableCount = AllDataHolder.getUtil().sizeOfWebElement(FARMER.TABLE_FARMER_LIST);
            if (tableCount > 0) {
                AllDataHolder.getUtil().report(1, "<mark>User Navigated to </mark> " +
                        " <b>" + subTabName + "</b>", "", "");
            } else {
                AllDataHolder.getUtil().contentDisplayed(FARMER.TABLE_FARMER);
                AllDataHolder.getUtil().report(1, "<mark>Farmer are not registered </mark> " +
                        " <b>" + subTabName + "</b>", "", "");
            }
        } catch (Exception e) {
            throw new FlexFrameWorkRunTimeException(e.getMessage());
        }
    }

    /**
     * @param subTabName
     * @throws FlexFrameWorkRunTimeException
     * @implNote Method to verify the navigation to the Produce tab
     * @author Gangarapu.Ganesh
     */
    public void verifyProduceTab(String subTabName) throws FlexFrameWorkRunTimeException {
        try {
            if (AllDataHolder.getUtil().isContentDisplayed(FARMER.getFarmerSubTabs(subTabName))) {
                AllDataHolder.getUtil().clickElement(FARMER.getFarmerSubTabs(subTabName));
                AllDataHolder.getUtil().contentDisplayed(FARMER.LABEL_FARMER_PRODUCE);
                AllDataHolder.getUtil().report(1, "<mark>Farmers Produce List  : </mark> " +
                        " <b>" + subTabName + "</b>", "", "<b><mark>" + AllDataHolder.getUtil().getText(FARMER.LABEL_FARMER_PRODUCE_COUNT) + "</b></mark>");
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
     * @author Gangarapu.Ganesh
     */
    public void verifyFinancialTab(String subTabName) throws FlexFrameWorkRunTimeException {
        try {
            if (AllDataHolder.getUtil().isContentDisplayed(FARMER.getFarmerSubTabs(subTabName))) {
                AllDataHolder.getUtil().clickElement(FARMER.getFarmerSubTabs(subTabName));
                AllDataHolder.getUtil().contentDisplayed(FARMER.LABEL_FARMER_FINANCIAL);
                int tableCount = AllDataHolder.getUtil().sizeOfWebElement(FARMER.TABLE_FARMER_FINANCIAL_LIST);
                String tableData = null;
                if (tableCount > 0) {
                    if (AllDataHolder.getUtil().isContentDisplayed(FARMER.TABLE_FARMER_FINANCIAL_NO_RESULTS)) {
                        tableData = AllDataHolder.getUtil().getText(FARMER.TABLE_FARMER_FINANCIAL_NO_RESULTS);
                    } else {
                        tableData = AllDataHolder.getUtil().getText(FARMER.TABLE_FARMER_FINANCIAL_DATA);
                    }
                    if (!tableData.trim().equals("No results")) {
                        AllDataHolder.getUtil().report(1, "<mark>User Navigated to </mark> " +
                                " <b>" + subTabName + "</b>", "", "<mark>GRN: " + tableData + "</mark>");
                    } else {
                        AllDataHolder.getUtil().contentDisplayed(FARMER.TABLE_FARMER_FINANCIAL_NO_RESULTS);
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
     * @author Gangarapu.Ganesh
     */
    public void verifyProductsTab(String subTabName) throws FlexFrameWorkRunTimeException {
        try {
            if (AllDataHolder.getUtil().isContentDisplayed(FARMER.getFarmerSubTabs(subTabName))) {
                AllDataHolder.getUtil().clickElement(FARMER.getFarmerSubTabs(subTabName));
                Thread.sleep(5000);
                AllDataHolder.getUtil().contentDisplayed(FARMER.LABEL_FARMER_PRODUCTS);
                AllDataHolder.getUtil().contentDisplayed(FARMER.ICON_ADD_PRODUCT);
                AllDataHolder.getUtil().contentDisplayed(FARMER.ICON_SAVE_CHANGES);
                AllDataHolder.getUtil().contentDisplayed(FARMER.ICON_RESET_CHANGES);
                AllDataHolder.getUtil().clickElement(FARMER.DROPDOWN_ROWS_PER_PAGE);
                AllDataHolder.getUtil().clickElement(FARMER.selectRowsPerPage("100"));
                int tableCount = AllDataHolder.getUtil().sizeOfWebElement(FARMER.TABLE_PRODUCTS_LIST);
                if (tableCount > 0) {
                    AllDataHolder.getUtil().report(1, "<mark>User Navigated to </mark> " +
                            " <b>" + subTabName + "</b>", "", "<br><mark>List of Added Products</mark></br>");

                    List <String> productsList = AllDataHolder.getUtil().getTextFromWebElements(FARMER.TABLE_PRODUCTS_LIST);
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

    /**
     * @param subTabName
     * @throws FlexFrameWorkRunTimeException
     * @implNote Method to verify the navigation to the Farmer Pricing tab
     * @author Gangarapu.Ganesh
     */
    public void verifyFarmerPricingTab(String subTabName) throws FlexFrameWorkRunTimeException {
        try {
            if (AllDataHolder.getUtil().isContentDisplayed(FARMER.getFarmerSubTabs(subTabName))) {
                AllDataHolder.getUtil().clickElement(FARMER.getFarmerSubTabs(subTabName));
                AllDataHolder.getUtil().contentDisplayed(FARMER.LABEL_PRICE_LIST);
                int tableCount = AllDataHolder.getUtil().sizeOfWebElement(FARMER.TABLE_PRICE_LIST_DATA);
                String tableData = null;
                if (tableCount > 0) {
                    if (AllDataHolder.getUtil().isContentDisplayed(FARMER.LABEL_NO_RESULTS_PRICE_LIST)) {
                        tableData = AllDataHolder.getUtil().getText(FARMER.LABEL_NO_RESULTS_PRICE_LIST);
                    } else {
                        tableData = AllDataHolder.getUtil().getText(FARMER.LABEL_PRICE_LIST);
                    }
                    if (!tableData.trim().equals("No results")) {
                        AllDataHolder.getUtil().report(1, "<mark>User Navigated to </mark> " +
                                " <b>" + subTabName + "</b>", "", tableData);
                    } else {
                        AllDataHolder.getUtil().contentDisplayed(FARMER.LABEL_NO_RESULTS_PRICE_LIST);
                        AllDataHolder.getUtil().report(1, "<mark>Farmer Pricing are not created </mark> " +
                                " <b>" + subTabName + "</b>", "", "");
                    }
                } else {
                    AllDataHolder.getUtil().report(1, "Clicking on Farmer Pricing Sub Tab",
                            "Clicking on Farmer Pricing Sub Tab",
                            "<mark>Farmer Pricing tab is not displayed, please check the Permission </mark>");
                    Assert.fail();
                }
            }
        } catch (Exception e) {
            throw new FlexFrameWorkRunTimeException(e.getMessage());
        }
    }
}

