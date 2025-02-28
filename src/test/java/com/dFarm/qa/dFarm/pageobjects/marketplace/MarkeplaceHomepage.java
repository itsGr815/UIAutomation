package com.dFarm.qa.dFarm.pageobjects.marketplace;

import com.dFarm.qa.dFarm.constants.DFarmConstants;
import com.dFarm.qa.dFarm.constants.MarketPlaceConstants;
import com.dFarm.qa.dFarm.objectrepository.MarketPlace.MPHOMEPAGE;
import com.dFarm.qa.dFarm.util.execution.AllDataHolder;
import com.dFarm.qa.dFarm.util.execution.FlexFrameWorkRunTimeException;

import java.util.Arrays;
import java.util.List;

public class MarkeplaceHomepage implements MarketPlaceConstants {


    /**
     * @implNote Method to Click on Dynamic Marketplace tab & Verify the UI & Categories list
     * @author Gangarapu.Ganesh
     */
    public void navigateDynamicMarketplace() throws FlexFrameWorkRunTimeException{
        try {
            AllDataHolder.getUtil().clickElement(MPHOMEPAGE.LINK_DYNAMIC_MARKET_PLACE);
            AllDataHolder.getUtil().switchToNewWindow();
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e.getMessage());
        }
    }

    /**
     * @implNote Method to verify the UI elements in Marketplace home page before login
     * @author Gangarapu.Ganesh
     */
    public void validateMPHeaderUI() throws FlexFrameWorkRunTimeException {
        try {
            AllDataHolder.getUtil().contentDisplayed(MPHOMEPAGE.TEXT_GETTING_STARTED);
            AllDataHolder.getUtil().contentDisplayed(MPHOMEPAGE.HEADER_NAVIGATION);
            AllDataHolder.getUtil().contentDisplayed(MPHOMEPAGE.IamBuyerOrSellerLink(MarketPlaceConstants.BUYER));
            AllDataHolder.getUtil().contentDisplayed(MPHOMEPAGE.IamBuyerOrSellerLink(MarketPlaceConstants.SELLER));
            AllDataHolder.getUtil().checkElementIsLink(MPHOMEPAGE.LINK_BUYER);
            AllDataHolder.getUtil().checkElementIsLink(MPHOMEPAGE.LINK_SELLER);
            AllDataHolder.getUtil().contentDisplayed(MPHOMEPAGE.LINK_SIGN_IN);
            verifyCategoriesList();
        }catch (Exception e) {
            throw new FlexFrameWorkRunTimeException(e.getMessage());
        }
    }

    /**
     * @implNote Method to verify the list of categories Marketplace tab
     * @author Gangarapu.Ganesh
     */
    public void verifyCategoriesList() throws FlexFrameWorkRunTimeException {
        try {
            AllDataHolder.getUtil().report(DFarmConstants.INFO, "<br><mark>Validating marketplace categories list</br></mark>",
                    "Validating marketplace categories list",
                    "Validating marketplace categories list");

            List<String> actualMainCategoriesList = AllDataHolder.getUtil().getTextFromWebelements(MPHOMEPAGE.HEADERS_MP_MAIN_CATEGORIES);
            AllDataHolder.getUtil().listOfstringComparision(Arrays.asList(MarketPlaceConstants.getListOfMainCategories), actualMainCategoriesList);
            //Validating all Column Headers to Products List
            AllDataHolder.getUtil().mouseHover(MPHOMEPAGE.AGRICULTURE_PRODUCE);
            List<String> actualAgricultureCategoriesList = AllDataHolder.getUtil().getTextFromWebelements(MPHOMEPAGE.AGRICULTURE_MP_CATEGORIES);
            AllDataHolder.getUtil().listOfstringComparision(Arrays.asList(MarketPlaceConstants.listOfAgriCategories), actualAgricultureCategoriesList);
            AllDataHolder.getUtil().mouseHover(MPHOMEPAGE.POULTRY);
            List<String> actualPoultryCategoriesList = AllDataHolder.getUtil().getTextFromWebelements(MPHOMEPAGE.POULTRY_MP_CATEGORIES);
            AllDataHolder.getUtil().listOfstringComparision(Arrays.asList(MarketPlaceConstants.listOfPoultryMainCategories), actualPoultryCategoriesList);
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e.getMessage());
        }
    }

    /**
     * @implNote Method to verify the list of categories Marketplace tab
     * @author Gangarapu.Ganesh
     */
    public void verifyBuyerContactForm(String buyerOrSeller) throws FlexFrameWorkRunTimeException {
        try {
            AllDataHolder.getUtil().report(DFarmConstants.INFO, "<br><mark>Validating marketplace Buyer Contact Form Overlay UI</br></mark>",
                    "Buyer Contact Form Overlay",
                      "Buyer Contact Form Overlay");
            openContactForm(buyerOrSeller);

        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e.getMessage());
        }
    }

    /**
     * @implNote Method to Click on I am Buyer or Seller link & open the respective Contact form
     * @author Gangarapu.Ganesh
     */
    public void verifyContactFormUI(String buyerOrSeller) throws FlexFrameWorkRunTimeException {
        try {


        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e.getMessage());
        }
    }

    /**
     * @implNote Method to Click on I am Buyer or Seller link & open the respective Contact form
     * @author Gangarapu.Ganesh
     */
    public void openContactForm(String buyerOrSeller) throws FlexFrameWorkRunTimeException {
        try {
            AllDataHolder.getUtil().clickElement(MPHOMEPAGE.IamBuyerOrSellerLink(buyerOrSeller));
            AllDataHolder.getUtil().report(DFarmConstants.INFO, "<br><mark>"+buyerOrSeller+" Overlay Opened</br></mark>",
                    buyerOrSeller,
                    buyerOrSeller);
        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e.getMessage());
        }
    }
}
