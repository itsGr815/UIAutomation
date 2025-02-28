package com.dFarm.qa.dFarm.pageobjects.Prod;

import com.dFarm.qa.dFarm.objectrepository.Prod.ADMINPACKHOUSE;
import com.dFarm.qa.dFarm.util.execution.AllDataHolder;
import com.dFarm.qa.dFarm.util.execution.FlexFrameWorkRunTimeException;
import org.testng.Assert;

public class AdminPackhouse {



    /**
     * @param
     * @throws FlexFrameWorkRunTimeException
     * @implNote Method to verify the navigation to PackHouse Tab in FPO admin application
     * @author Gangarapu.Ganesh
     */
    public void verifyPackHouseModule() throws FlexFrameWorkRunTimeException {
        try {
            navigateToPHTab();
            verifyPHListTab();
            verifyPHStockMasterTab();
            verifyPHSortingAndGradingTab();
            verifyPHSalesOrderTab();
            verifyPHDispatchTab();
            verifyPHClientsTab();
        } catch (Exception e) {
            throw new FlexFrameWorkRunTimeException(e.getMessage());
        }
    }
    /**
     * @param
     * @throws FlexFrameWorkRunTimeException
     * @implNote Method to verify the navigation to PackHouse Tab in FPO admin application
     * @author Gangarapu.Ganesh
     */
    public void navigateToPHTab() throws FlexFrameWorkRunTimeException {
        try {
            AllDataHolder.getFarmer().navigateToAllTabs("Pack House");
        } catch (Exception e) {
            throw new FlexFrameWorkRunTimeException(e.getMessage());
        }
    }

    /**
     * @param subTabName
     * @throws FlexFrameWorkRunTimeException
     * @implNote Method to verify the navigation to PackHouse Sub Tab's in FPO admin application
     * @author Gangarapu.Ganesh
     */
    public void navigateToPHSubTabs(String subTabName) throws FlexFrameWorkRunTimeException {
        try {
            AllDataHolder.getUtil().clickElement(ADMINPACKHOUSE.clickPHSubTab(subTabName));
            AllDataHolder.getUtil().driverWait();
        } catch (Exception e) {
            throw new FlexFrameWorkRunTimeException(e.getMessage());
        }
    }

    /**
     * @param
     * @throws FlexFrameWorkRunTimeException
     * @implNote Method to verify the PackHouse List Sub Tab in FPO admin application
     * @author Gangarapu.Ganesh
     */
    public void verifyPHListTab() throws FlexFrameWorkRunTimeException {
        try {
            AllDataHolder.getUtil().driverWait();
            if (AllDataHolder.getUtil().isContentDisplayed(ADMINPACKHOUSE.INPUT_SEARCH_PH_LIST)) {
                AllDataHolder.getUtil().contentDisplayed(ADMINPACKHOUSE.LABEL_PH_LIST);
                int tableCount = AllDataHolder.getUtil().sizeOfWebElement(ADMINPACKHOUSE.TABLE_PH_LIST_DATA);
                String tableData = null;
                String phMainID = null;
                String phName = null;
                if (tableCount > 0) {
                    if (AllDataHolder.getUtil().isContentDisplayed(ADMINPACKHOUSE.TABLE_PH_LIST_NO_RESULTS)) {
                        tableData = AllDataHolder.getUtil().getText(ADMINPACKHOUSE.TABLE_PH_LIST_NO_RESULTS);
                    } else {
                        tableData = AllDataHolder.getUtil().getText(ADMINPACKHOUSE.TABLE_PH_LIST_DATA);
                        phMainID = AllDataHolder.getUtil().getText(ADMINPACKHOUSE.LABEL_PH_MAIN_ID);
                        phName = AllDataHolder.getUtil().getText(ADMINPACKHOUSE.LABEL_PH_NAME);
                    }
                    if (!tableData.trim().equals("No results")) {
                        AllDataHolder.getUtil().report(1, "<mark>User Navigated to </mark> " +
                                        " <b> PH List </b>", "PH List",
                                "<mark><br>PH MAIN ID IS : " + phMainID + "</br></mark>" +
                                        "<mark><br>PH NAME IS : " + phName + "</br></mark>");
                    } else {
                        AllDataHolder.getUtil().contentDisplayed(ADMINPACKHOUSE.TABLE_PH_LIST_NO_RESULTS);
                        AllDataHolder.getUtil().report(1, "<mark>Pack house is not created </mark> " +
                                " <b> PH LIST</b>", "", "");
                    }
                }
            } else {
                AllDataHolder.getUtil().report(1, "Clicking on Pack house List Sub Tab",
                        "Clicking on Pack house List Sub Tab",
                        "<mark>Pack house List Sub Tab is not displayed, please check the Permission </mark>");
                Assert.fail();
            }
        } catch (Exception e) {
            throw new FlexFrameWorkRunTimeException(e.getMessage());
        }

    }

    /**
     * @param
     * @throws FlexFrameWorkRunTimeException
     * @implNote Method to verify the PackHouse Stock Master Sub Tab in FPO admin application
     * @author Gangarapu.Ganesh
     */
    public void verifyPHStockMasterTab() throws FlexFrameWorkRunTimeException {
        try {
            if (AllDataHolder.getUtil().isContentDisplayed(ADMINPACKHOUSE.clickPHSubTab("Stock Master"))) {
                navigateToPHSubTabs("Stock Master");
                AllDataHolder.getUtil().driverWait();
                AllDataHolder.getUtil().contentDisplayed(ADMINPACKHOUSE.DROPDOWN_PRODUCE);
                AllDataHolder.getUtil().contentDisplayed(ADMINPACKHOUSE.DROPDOWN_VARIETY);
                AllDataHolder.getUtil().contentDisplayed(ADMINPACKHOUSE.BUTTON_SEARCH);
                AllDataHolder.getUtil().contentDisplayed(ADMINPACKHOUSE.BUTTON_RESET);
                AllDataHolder.getUtil().contentDisplayed(ADMINPACKHOUSE.TAB_COLLECTED);
                AllDataHolder.getUtil().contentDisplayed(ADMINPACKHOUSE.TAB_ACCEPTED);
                AllDataHolder.getUtil().contentDisplayed(ADMINPACKHOUSE.LABEL_AVAILABLE_STOCK);
                AllDataHolder.getUtil().report(1, "Available Stock", "Available Stock",
                      "<br><mark>" + AllDataHolder.getUtil().getText(ADMINPACKHOUSE.LABEL_TOTAL_AVAILABLE_WEIGHT) + "</br></mark>");
                int tableCount = AllDataHolder.getUtil().sizeOfWebElement(ADMINPACKHOUSE.TABLE_AVAILABLE_STOCK_LIST);
                String tableData = null;
                String prID = null;
                if (tableCount > 0) {
                    if (AllDataHolder.getUtil().isContentDisplayed(ADMINPACKHOUSE.TABLE_AVAILABLE_STOCK_NO_RESULTS)) {
                        tableData = AllDataHolder.getUtil().getText(ADMINPACKHOUSE.TABLE_AVAILABLE_STOCK_NO_RESULTS);
                    } else {
                        tableData = AllDataHolder.getUtil().getText(ADMINPACKHOUSE.TABLE_AVAILABLE_STOCK_LIST);
                        prID = AllDataHolder.getUtil().getText(ADMINPACKHOUSE.VALUE_PR_ID);
                    }
                    if (!tableData.trim().equals("No results")) {
                        AllDataHolder.getUtil().report(1, "<mark>User Navigated to </mark> " +
                                        " <b> Stock Master </b>", "Stock Master",
                                "<mark><br>Produce ID is : " + prID + "</br></mark>");
                    } else {
                        AllDataHolder.getUtil().contentDisplayed(ADMINPACKHOUSE.TABLE_AVAILABLE_STOCK_NO_RESULTS);
                        AllDataHolder.getUtil().report(1, "<mark>Pack house Stock Master data is not created </mark> " +
                                " <b> Stock Master</b>", "", "");
                    }
                }
            } else {
                AllDataHolder.getUtil().report(1, "Clicking on Pack house Stock Master Sub Tab",
                        "Clicking on Pack house Stock Master Sub Tab",
                        "<mark>Pack house Stock Master Sub Tab is not displayed, please check the Permission </mark>");
                Assert.fail();
            }

        } catch (Exception e) {
            throw new FlexFrameWorkRunTimeException(e.getMessage());
        }
    }

    /**
     * @param
     * @throws FlexFrameWorkRunTimeException
     * @implNote Method to verify the PackHouse Sorting and Grading Report Sub Tab in FPO admin application
     * @author Gangarapu.Ganesh
     */
    public void verifyPHSortingAndGradingTab() throws FlexFrameWorkRunTimeException {
        try {
            if (AllDataHolder.getUtil().isContentDisplayed(ADMINPACKHOUSE.clickPHSubTab("Sorting and Grading Report"))) {
                navigateToPHSubTabs("Sorting and Grading Report");
                AllDataHolder.getUtil().driverWait();
                AllDataHolder.getUtil().contentDisplayed(ADMINPACKHOUSE.DROPDOWN_PRODUCE);
                AllDataHolder.getUtil().contentDisplayed(ADMINPACKHOUSE.DROPDOWN_VARIETY);
                AllDataHolder.getUtil().contentDisplayed(ADMINPACKHOUSE.DROPDOWN_GRADE);
                AllDataHolder.getUtil().contentDisplayed(ADMINPACKHOUSE.BUTTON_SEARCH);
                AllDataHolder.getUtil().contentDisplayed(ADMINPACKHOUSE.BUTTON_RESET);
                AllDataHolder.getUtil().contentDisplayed(ADMINPACKHOUSE.LABEL_GRADING_LIST);

                int tableCount = AllDataHolder.getUtil().sizeOfWebElement(ADMINPACKHOUSE.TABLE_GRADING_LIST_DATA);
                String tableData = null;
                String gradeData = null;
                if (tableCount > 0) {
                    if (AllDataHolder.getUtil().isContentDisplayed(ADMINPACKHOUSE.LABEL_GRADING_LIST_NO_RESULTS)) {
                        tableData = AllDataHolder.getUtil().getText(ADMINPACKHOUSE.LABEL_GRADING_LIST_NO_RESULTS);
                    } else {
                        tableData = AllDataHolder.getUtil().getText(ADMINPACKHOUSE.TABLE_GRADING_LIST_DATA);
                        gradeData = AllDataHolder.getUtil().getText(ADMINPACKHOUSE.TABLE_GRADING_LIST_1ST_ROW);
                    }
                    if (!tableData.trim().equals("No results")) {
                        AllDataHolder.getUtil().report(1, "<mark>User Navigated to </mark> " +
                                        " <b> Sorting and Grading Report </b>", "Sorting and Grading Report",
                                "<mark><br> 1 st Grading Data is : " + gradeData + "</br></mark>");
                    } else {
                        AllDataHolder.getUtil().contentDisplayed(ADMINPACKHOUSE.LABEL_GRADING_LIST_NO_RESULTS);
                        AllDataHolder.getUtil().report(1, "<mark>Pack house Sorting and Grading Report data is not created </mark> " +
                                " <b> Sorting and Grading Report </b>", "", "");
                    }
                }
            } else {
                AllDataHolder.getUtil().report(1, "Clicking on Pack house Sorting and Grading Report Sub Tab",
                        "Clicking on Pack house Sorting and Grading Report Sub Tab",
                        "<mark>Pack house Sorting and Grading Report Sub Tab is not displayed, please check the Permission </mark>");
                Assert.fail();
            }

        } catch (Exception e) {
            throw new FlexFrameWorkRunTimeException(e.getMessage());
        }
    }

    /**
     * @param
     * @throws FlexFrameWorkRunTimeException
     * @implNote Method to verify the PackHouse Sales Order Sub Tab in FPO admin application
     * @author Gangarapu.Ganesh
     */
    public void verifyPHSalesOrderTab() throws FlexFrameWorkRunTimeException {
        try {
            if (AllDataHolder.getUtil().isContentDisplayed(ADMINPACKHOUSE.clickPHSubTab("Sales Order"))) {
                navigateToPHSubTabs("Sales Order");
                AllDataHolder.getUtil().driverWait();
                AllDataHolder.getUtil().contentDisplayed(ADMINPACKHOUSE.INPUT_SEARCH_ORDER_DETAILS);
                AllDataHolder.getUtil().contentDisplayed(ADMINPACKHOUSE.BUTTON_SALES_ORDER_RESET);
                AllDataHolder.getUtil().contentDisplayed(ADMINPACKHOUSE.LABEL_ORDER_DETAILS);

                int tableCount = AllDataHolder.getUtil().sizeOfWebElement(ADMINPACKHOUSE.TABLE_SALES_ORDER_LIS);
                String tableData = null;
                String salesOrder = null;
                if (tableCount > 0) {
                    if (AllDataHolder.getUtil().isContentDisplayed(ADMINPACKHOUSE.LABEL_SALES_ORDER_NO_RESULTS)) {
                        tableData = AllDataHolder.getUtil().getText(ADMINPACKHOUSE.LABEL_SALES_ORDER_NO_RESULTS);
                    } else {
                        tableData = AllDataHolder.getUtil().getText(ADMINPACKHOUSE.TABLE_SALES_ORDER_LIS);
                        salesOrder = AllDataHolder.getUtil().getText(ADMINPACKHOUSE.VALUE_SALES_ORDER_NUMBER);
                    }
                    if (!tableData.trim().equals("No results")) {
                        AllDataHolder.getUtil().report(1, "<mark>User Navigated to </mark> " +
                                        " <b> Sales Order </b>", "Sales Order",
                                "<mark><br>Sales Order # : " + salesOrder + "</br></mark>");
                    } else {
                        AllDataHolder.getUtil().contentDisplayed(ADMINPACKHOUSE.LABEL_SALES_ORDER_NO_RESULTS);
                        AllDataHolder.getUtil().report(1, "<mark>Pack house Sales Order data is not created </mark> " +
                                " <b> Sales Order </b>", "", "");
                    }
                }
            } else {
                AllDataHolder.getUtil().report(1, "Clicking on Pack house Sales Order Sub Tab",
                        "Clicking on Pack house Sales Order Sub Tab",
                        "<mark>Pack house Sales Order Sub Tab is not displayed, please check the Permission </mark>");
                Assert.fail();
            }

        } catch (Exception e) {
            throw new FlexFrameWorkRunTimeException(e.getMessage());
        }
    }

    /**
     * @param
     * @throws FlexFrameWorkRunTimeException
     * @implNote Method to verify the PackHouse Dispatch Sub Tab in FPO admin application
     * @author Gangarapu.Ganesh
     */
    public void verifyPHDispatchTab() throws FlexFrameWorkRunTimeException {
        try {
            if (AllDataHolder.getUtil().isContentDisplayed(ADMINPACKHOUSE.clickPHSubTab("Dispatch"))) {
                navigateToPHSubTabs("Dispatch");
                AllDataHolder.getUtil().driverWait();
                AllDataHolder.getUtil().contentDisplayed(ADMINPACKHOUSE.INPUT_SEARCH_DISPATCH_LIST);
                AllDataHolder.getUtil().contentDisplayed(ADMINPACKHOUSE.LABEL_TRACKING_LIST);
                AllDataHolder.getUtil().contentDisplayed(ADMINPACKHOUSE.BUTTON_DISPATCH_RESET);

                int tableCount = AllDataHolder.getUtil().sizeOfWebElement(ADMINPACKHOUSE.TABLE_DISPATCH_LIST_DATA);
                String tableData = null;
                String salesOrderId = null;
                String deliveryId = null;
                if (tableCount > 0) {
                    if (AllDataHolder.getUtil().isContentDisplayed(ADMINPACKHOUSE.LABEL_DISPATCH_LIST_NO_RESULTS)) {
                        tableData = AllDataHolder.getUtil().getText(ADMINPACKHOUSE.LABEL_DISPATCH_LIST_NO_RESULTS);
                    } else {
                        tableData = AllDataHolder.getUtil().getText(ADMINPACKHOUSE.TABLE_DISPATCH_LIST_DATA);
                        deliveryId = AllDataHolder.getUtil().getText(ADMINPACKHOUSE.LABEL_DELIVERY_ID);
                        salesOrderId = AllDataHolder.getUtil().getText(ADMINPACKHOUSE.LABEL_SALES_ORDER_ID);
                    }
                    if (!tableData.trim().equals("No results")) {
                        AllDataHolder.getUtil().report(1, "<mark>User Navigated to </mark> " +
                                        " <b> Dispatch </b>", "Dispatch",
                                "<mark><br>Delivery #  : " + deliveryId + "</br></mark>" +
                                        "<mark><br>Sales Order #  : " + salesOrderId + "</br></mark>");

                    } else {
                        AllDataHolder.getUtil().contentDisplayed(ADMINPACKHOUSE.LABEL_DISPATCH_LIST_NO_RESULTS);
                        AllDataHolder.getUtil().report(1, "<mark>Pack house Dispatch data is not created </mark> " +
                                " <b> Dispatch </b>", "", "");
                    }
                }
            } else {
                AllDataHolder.getUtil().report(1, "Clicking on Pack house Dispatch Sub Tab",
                        "Clicking on Pack house Dispatch Sub Tab",
                        "<mark>Pack house Dispatch Sub Tab is not displayed, please check the Permission </mark>");
                Assert.fail();
            }

        } catch (Exception e) {
            throw new FlexFrameWorkRunTimeException(e.getMessage());
        }
    }

    /**
     * @param
     * @throws FlexFrameWorkRunTimeException
     * @implNote Method to verify the PackHouse Dispatch Sub Tab in FPO admin application
     * @author Gangarapu.Ganesh
     */
    public void verifyPHClientsTab() throws FlexFrameWorkRunTimeException {
        try {
            if (AllDataHolder.getUtil().isContentDisplayed(ADMINPACKHOUSE.clickPHSubTab("Clients"))) {
                navigateToPHSubTabs("Clients");
                AllDataHolder.getUtil().driverWait();
                AllDataHolder.getUtil().contentDisplayed(ADMINPACKHOUSE.LABEL_CLIENT_DETAILS);
                AllDataHolder.getUtil().contentDisplayed(ADMINPACKHOUSE.INPUT_CLIENT_SEARCH);
                AllDataHolder.getUtil().contentDisplayed(ADMINPACKHOUSE.BUTTON_ADD_NEW_CLIENT);
                AllDataHolder.getUtil().contentDisplayed(ADMINPACKHOUSE.BUTTON_BUYERS);
                AllDataHolder.getUtil().contentDisplayed(ADMINPACKHOUSE.BUTTON_SUPPLIERS);

                int tableCount = AllDataHolder.getUtil().sizeOfWebElement(ADMINPACKHOUSE.TABLE_CLIENTS_DETAILS_LIST);
                String tableData = null;
                String salesOrderId = null;
                String companyName = null;
                String companyId = null;
                if (tableCount > 0) {
                    if (AllDataHolder.getUtil().isContentDisplayed(ADMINPACKHOUSE.LABEL_CLIENTS_NO_RESULTS)) {
                        tableData = AllDataHolder.getUtil().getText(ADMINPACKHOUSE.LABEL_CLIENTS_NO_RESULTS);
                    } else {
                        tableData = AllDataHolder.getUtil().getText(ADMINPACKHOUSE.TABLE_CLIENTS_DETAILS_LIST);
                        companyName = AllDataHolder.getUtil().getText(ADMINPACKHOUSE.LABEL_CLIENT_COMPANY_NAME);
                        companyId = AllDataHolder.getUtil().getText(ADMINPACKHOUSE.LABEL_CLIENT_COMPANY_ID);

                    }
                    if (!tableData.trim().equals("No results")) {
                        AllDataHolder.getUtil().report(1, "<mark>User Navigated to </mark> " +
                                        " <b> Clients </b>", "Clients",
                                "<mark><br>Client Company ID #  : " + companyName + "</br></mark>" +
                                        "<mark><br>Client Company Name #  : " + companyId + "</br></mark>");

                    } else {
                        AllDataHolder.getUtil().contentDisplayed(ADMINPACKHOUSE.LABEL_CLIENTS_NO_RESULTS);
                        AllDataHolder.getUtil().report(1, "<mark>Pack house Clients data is not created </mark> " +
                                " <b> Clients </b>", "", "");
                    }
                }
                AllDataHolder.getUtil().clickElement(ADMINPACKHOUSE.BUTTON_ADD_NEW_CLIENT);
                AllDataHolder.getUtil().driverWait();
                if(AllDataHolder.getCurrentTestCaseId().contains("Evezon")){
                    AllDataHolder.getUtil().contentDisplayed(ADMINPACKHOUSE.LABEL_ADD_NEW_SUPPLIER_BUYER);
                }else {
                    AllDataHolder.getUtil().contentDisplayed(ADMINPACKHOUSE.LABEL_ADD_NEW_CLIENT);
                }

                AllDataHolder.getDriver().get(AllDataHolder.getDriver().getCurrentUrl());
            } else {
                AllDataHolder.getUtil().report(1, "Clicking on Pack house Clients Sub Tab",
                        "Clicking on Pack house Clients Sub Tab",
                        "<mark>Pack house Clients Sub Tab is not displayed, please check the Permission </mark>");
                Assert.fail();
            }

        } catch (Exception e) {
            throw new FlexFrameWorkRunTimeException(e.getMessage());
        }
    }
}
