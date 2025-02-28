package com.dFarm.qa.dFarm.testsuite.CorporateSite;

import com.dFarm.qa.dFarm.constants.groups.PRIORITY;
import com.dFarm.qa.dFarm.util.execution.AllDataHolder;
import com.dFarm.qa.dFarm.util.execution.CorporateBaseTest;
import com.dFarm.qa.dFarm.util.execution.FlexFrameWorkRunTimeException;
import org.testng.annotations.Test;

public class CorporateHome extends CorporateBaseTest {

    /**
     * @param
     * @throws FlexFrameWorkRunTimeException
     * @implNote Test Method to verify the Home Page of dFarm Inc Site
     * @author Gangarapu.Ganesh
     */
    @Test(testName = "TC01_dFarmInc_HomePage_Test", groups = {PRIORITY.P1})
    public void verifydFarmIncHomePage()throws FlexFrameWorkRunTimeException {
        try {
            AllDataHolder.getCorporateSiteHome().verifyDFarmIncHomePage();
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e);
        }
    }

    /**
     * @param
     * @throws FlexFrameWorkRunTimeException
     * @implNote Test Method to verify the Blogs Page of dFarm Inc Site
     * @author Gangarapu.Ganesh
     */
    @Test(testName = "TC02_dFarmInc_Footer_Link_BLOGS_Test", groups = {PRIORITY.P1})
    public void verifydFarmIncFooterlinkBlogs()throws FlexFrameWorkRunTimeException {
        try {
            AllDataHolder.getBlogs().verifyBlogsFooterLinkLoading();
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e);
        }
    }

    /**
     * @param
     * @throws FlexFrameWorkRunTimeException
     * @implNote Test Method to verify the Case Studies page of dFarm Inc Site
     * @author Gangarapu.Ganesh
     */
    @Test(testName = "TC03_dFarmInc_Footer_Link_Case_Studies_Test", groups = {PRIORITY.P1})
    public void verifydFarmIncFooterlinkCaseStudies()throws FlexFrameWorkRunTimeException {
        try {
            AllDataHolder.getCaseStudies().verifyCaseStudiesPage();
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e);
        }
    }

    /**
     * @param
     * @throws FlexFrameWorkRunTimeException
     * @implNote Test Method to verify the Case Studies page of dFarm Inc Site
     * @author Gangarapu.Ganesh
     */
    @Test(testName = "TC04_dFarmInc_Footer_Link_News_Test", groups = {PRIORITY.P1})
    public void verifydFarmIncFooterlinkNews()throws FlexFrameWorkRunTimeException {
        try {
            AllDataHolder.getNews().verifyNewsPageFunctionality();
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e);
        }
    }

    /**
     * @param
     * @throws FlexFrameWorkRunTimeException
     * @implNote Test Method to verify the Supply Chain Optimization page of dFarm Inc Site
     * @author Gangarapu.Ganesh
     */
    @Test(testName = "TC05_dFarmInc_Footer_Link_Supply_Chain_Optimization_Test", groups = {PRIORITY.P1})
    public void verifydFarmIncFooterlinkSupplyChainOptimization()throws FlexFrameWorkRunTimeException {
        try {
            AllDataHolder.getSupplyChainOptimization().verifySupplyChainOptimizationPageFunctionality();
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e);
        }
    }

    /**
     * @param
     * @throws FlexFrameWorkRunTimeException
     * @implNote Test Method to verify the GDM of dFarm Inc Site
     * @author Gangarapu.Ganesh
     */
    @Test(testName = "TC06_dFarmInc_Footer_Link_GDM_Test", groups = {PRIORITY.P1})
    public void verifydFarmIncFooterlinkGDM()throws FlexFrameWorkRunTimeException {
        try {
            AllDataHolder.getDynamicMarketplace().verifyLoadAndUIGDM();
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e);
        }
    }

    /**
     * @param
     * @throws FlexFrameWorkRunTimeException
     * @implNote Test Method to verify the SCM Finance of dFarm Inc Site
     * @author Gangarapu.Ganesh
     */
    @Test(testName = "TC07_dFarmInc_Footer_Link_SCM_Finance_Test", groups = {PRIORITY.P1})
    public void verifydFarmIncFooterlinkSCMFinance()throws FlexFrameWorkRunTimeException {
        try {
            AllDataHolder.getScmFinance().verifySCMFinancePageFunctionality();
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e);
        }
    }

    /**
     * @param
     * @throws FlexFrameWorkRunTimeException
     * @implNote Test Method to verify the Precision Tracing of dFarm Inc Site
     * @author Gangarapu.Ganesh
     */
    @Test(testName = "TC08_dFarmInc_Footer_Link_Precision_Tracing_Test", groups = {PRIORITY.P1})
    public void verifydFarmIncFooterlinkPrecisionTracing()throws FlexFrameWorkRunTimeException {
        try {
            AllDataHolder.getPrecisionTracing().verifyPrecisionTracingPage();
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e);
        }
    }



    /**
     * @param
     * @throws FlexFrameWorkRunTimeException
     * @implNote Test Method to verify the FSMA 204 of dFarm Inc Site
     * @author Gangarapu.Ganesh
     */
    @Test(testName = "TC09_dFarmInc_Footer_Link_Technologies_Test", groups = {PRIORITY.P1})
    public void verifydFarmIncFooterlinkTechnologies()throws FlexFrameWorkRunTimeException {
        try {
            AllDataHolder.getTechnologies().verifyTechnologiesPage();
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e);
        }
    }

    /**
     * @param
     * @throws FlexFrameWorkRunTimeException
     * @implNote Test Method to verify the FSMA 204 of dFarm Inc Site
     * @author Gangarapu.Ganesh
     */
    @Test(testName = "TC10_dFarmInc_Footer_Link_FSMA 204_Test", groups = {PRIORITY.P1})
    public void verifydFarmIncFooterlinkFSMA204()throws FlexFrameWorkRunTimeException {
        try {
            AllDataHolder.getFsma204().verifySFSMA204Page();
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e);
        }
    }


}
