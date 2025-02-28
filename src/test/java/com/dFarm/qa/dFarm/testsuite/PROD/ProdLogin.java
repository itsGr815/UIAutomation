package com.dFarm.qa.dFarm.testsuite.PROD;

import com.dFarm.qa.dFarm.constants.TestDataConstants;
import com.dFarm.qa.dFarm.constants.groups.PRIORITY;
import com.dFarm.qa.dFarm.util.execution.AllDataHolder;
import com.dFarm.qa.dFarm.util.execution.BaseTest;
import com.dFarm.qa.dFarm.util.execution.FlexFrameWorkRunTimeException;
import org.testng.annotations.Test;

public class ProdLogin extends BaseTest {


//    /**
//     * @param
//     * @throws FlexFrameWorkRunTimeException
//     * @implNote Test Method to verify the End To End Functionality of KFPCL
//     * @author Gangarapu.Ganesh
//     */
//    @Test(testName = "TC01_Login_KFPCL_E2E_Test", groups = {PRIORITY.P1})
//    public void verifyKFPCLLogin()throws FlexFrameWorkRunTimeException {
//        String tabName = AllDataHolder.getTestData().get(AllDataHolder.getCurrentTestCaseId(), TestDataConstants.TAB_NAME);
//        String subTabName = AllDataHolder.getTestData().get(AllDataHolder.getCurrentTestCaseId(), TestDataConstants.SUB_TAB_NAME);
//        try {
//            AllDataHolder.getFarmer().navigateToAllTabs(tabName);
//            AllDataHolder.getFarmer().verifyEnrollFarmerTab(subTabName);
//            AllDataHolder.getFarmer().verifyProduceTab("Produce");
//            AllDataHolder.getFarmer().verifyFinancialTab("Financials");
//            AllDataHolder.getFarmer().verifyProductsTab("Products");
//            AllDataHolder.getFarmer().verifyFarmerPricingTab("Pricing");
//            AllDataHolder.getPackhouse().verifyPackHouseModule();
//
//        }catch (Exception e){
//            throw new FlexFrameWorkRunTimeException(e);
//        }
//    }

//    /**
//     * @param
//     * @throws FlexFrameWorkRunTimeException
//     * @implNote Test Method to verify the End To End Functionality of EVEZON
//     * @author Gangarapu.Ganesh
//     */
//    @Test(testName = "TC02_Login_Evezon_E2E_Test", groups = {PRIORITY.P1})
//    public void verifyEvezonLogin()throws FlexFrameWorkRunTimeException {
//        String tabName = AllDataHolder.getTestData().get(AllDataHolder.getCurrentTestCaseId(), TestDataConstants.TAB_NAME);
//        String subTabName = AllDataHolder.getTestData().get(AllDataHolder.getCurrentTestCaseId(), TestDataConstants.SUB_TAB_NAME);
//        try {
//            AllDataHolder.getFarmer().navigateToAllTabs(tabName);
//            AllDataHolder.getFarmer().verifyEnrollFarmerTab(subTabName);
//            AllDataHolder.getFarmer().verifyProduceTab("Produce");
//            AllDataHolder.getFarmer().verifyFinancialTab("Financials");
//            AllDataHolder.getFarmer().verifyProductsTab("Products");
//            AllDataHolder.getFarmer().verifyFarmerPricingTab("Pricing");
//            AllDataHolder.getPackhouse().verifyPackHouseModule();
//        }catch (Exception e){
//            throw new FlexFrameWorkRunTimeException(e);
//        }
//    }

    /**
     * @param
     * @throws FlexFrameWorkRunTimeException
     * @implNote Test Method to verify the End To End Functionality of dFarm USA
     * @author Gangarapu.Ganesh
     */
//    @Test(testName = "TC03_Login_dFarm_USA_E2E_Test", groups = {PRIORITY.P1})
//    public void verifydFarmUSALogin()throws FlexFrameWorkRunTimeException {
//        String tabName = AllDataHolder.getTestData().get(AllDataHolder.getCurrentTestCaseId(), TestDataConstants.TAB_NAME);
//        String subTabName = AllDataHolder.getTestData().get(AllDataHolder.getCurrentTestCaseId(), TestDataConstants.SUB_TAB_NAME);
//        try {
//            AllDataHolder.getFarmer().navigateToAllTabs(tabName);
//            AllDataHolder.getFarmer().verifyEnrollFarmerTab(subTabName);
//            AllDataHolder.getFarmer().verifyProduceTab("Produce");
//            AllDataHolder.getFarmer().verifyFinancialTab("Financials");
//            AllDataHolder.getFarmer().verifyProductsTab("Products");
//            AllDataHolder.getFarmer().verifyFarmerPricingTab("Pricing");
//            AllDataHolder.getPackhouse().verifyPackHouseModule();
//        }catch (Exception e){
//            throw new FlexFrameWorkRunTimeException(e);
//        }
//    }

    /**
     * @param
     * @throws FlexFrameWorkRunTimeException
     * @implNote Test Method to verify the End To End Functionality of Green Orbit
     * @author Gangarapu.Ganesh
     */
    @Test(testName = "TC04_Login_Green_Orbit_E2E_Test", groups = {PRIORITY.P1})
    public void verifyGreenOrbitLogin()throws FlexFrameWorkRunTimeException {
        String tabName = AllDataHolder.getTestData().get(AllDataHolder.getCurrentTestCaseId(), TestDataConstants.TAB_NAME);
        String subTabName = AllDataHolder.getTestData().get(AllDataHolder.getCurrentTestCaseId(), TestDataConstants.SUB_TAB_NAME);
        try {
            AllDataHolder.getFarmer().navigateToAllTabs(tabName);
            AllDataHolder.getFarmer().verifyEnrollFarmerTab(subTabName);
            AllDataHolder.getFarmer().verifyProduceTab("Produce");
            AllDataHolder.getFarmer().verifyFinancialTab("Financials");
            AllDataHolder.getFarmer().verifyProductsTab("Products");
            AllDataHolder.getFarmer().verifyFarmerPricingTab("Pricing");
            AllDataHolder.getPackhouse().verifyPackHouseModule();
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e);
        }
    }

//    /**
//     * @param
//     * @throws FlexFrameWorkRunTimeException
//     * @implNote Test Method to verify the End To End Functionality of Vennar Digital Farm
//     * @author Gangarapu.Ganesh
//     */
//    @Test(testName = "TC05_Login_Vennar_E2E_Test", groups = {PRIORITY.P1})
//    public void verifyVennarLogin()throws FlexFrameWorkRunTimeException {
//        String tabName = AllDataHolder.getTestData().get(AllDataHolder.getCurrentTestCaseId(), TestDataConstants.TAB_NAME);
//        String subTabName = AllDataHolder.getTestData().get(AllDataHolder.getCurrentTestCaseId(), TestDataConstants.SUB_TAB_NAME);
//        try {
//            AllDataHolder.getFarmer().navigateToAllTabs(tabName);
//            AllDataHolder.getFarmer().verifyEnrollFarmerTab(subTabName);
//            AllDataHolder.getFarmer().verifyProduceTab("Produce");
//            AllDataHolder.getFarmer().verifyFinancialTab("Financials");
//            AllDataHolder.getFarmer().verifyProductsTab("Products");
//            AllDataHolder.getFarmer().verifyFarmerPricingTab("Pricing");
//            AllDataHolder.getPackhouse().verifyPackHouseModule();
//        }catch (Exception e){
//            throw new FlexFrameWorkRunTimeException(e);
//        }
//    }

    /**
     * @param
     * @throws FlexFrameWorkRunTimeException
     * @implNote Test Method to verify the End To End Functionality of Balaji Hatcheries
     * @author Gangarapu.Ganesh
     */
    @Test(testName = "TC06_Login_Balaji_E2E_Test", groups = {PRIORITY.P1})
    public void verifyBalajiLogin()throws FlexFrameWorkRunTimeException {
        String mainTabName = AllDataHolder.getTestData().get(AllDataHolder.getCurrentTestCaseId(), TestDataConstants.MAIN_TAB_NAME);
        String subTabName = AllDataHolder.getTestData().get(AllDataHolder.getCurrentTestCaseId(), TestDataConstants.SUB_TAB_NAME);
        try {
            //Navigating to Farmer List tab
            AllDataHolder.getBalajiFarmer().navigateBalajiFarmerTab(mainTabName, subTabName);
            //Navigating to Farmer Produce tab
            AllDataHolder.getBalajiFarmer().verifyBalajiProduceTab(mainTabName,"Produce");
            //Navigating to Farmer Financials
            AllDataHolder.getBalajiFarmer().verifyBalajiFinancialTab(mainTabName, "Financials");
          //  Navigating to Products Tab
            AllDataHolder.getBalajiFarmer().verifyBalajiProductsTab(mainTabName, "Products");
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e);
        }
    }

}
