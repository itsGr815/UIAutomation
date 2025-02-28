package com.dFarm.qa.dFarm.objectrepository.CorporateSite;

import com.dFarm.qa.dFarm.constants.CorporateSite;
import com.dFarm.qa.dFarm.util.datahandler.ISuppllyLocatorInfo;
import org.openqa.selenium.By;

public enum SCM_FINANCE implements ISuppllyLocatorInfo, CorporateSite {

    LABEL_SCM_FINANCE(By.xpath("//h1[text()='SCM Finance']"), "SCM Finance"),
    TEXT_SCM_FINANCE_PARAGRAPH(By.xpath("//div[contains(text(),'dFarm has integrated Supply Chain Finance (SCF)')]"), SCM_FINANCE_PARAGRAPH1),
    IMG_SCM_FINANCE(By.xpath("//div[@data-id='8ec9227']//img"), "Image - SCM Finance"),

    //Key Components of Supply Chain Finance
    LABEL_KEY_COMPONENTS(By.xpath("//h2[text()='Key Components of Supply Chain Finance']"), "Key Components of Supply Chain Finance"),
    CARDS_KEY_COMPONENTS(By.xpath("//section[@data-id='03c2883']/div/div"), "Cards - Buyer, Supplier, Financier"),


    //How Supply Chain Finance Works
    LABEL_HOW_SCFW(By.xpath("//h2[text()='How Supply Chain Finance Works']"), "How Supply Chain Finance Works"),
    LIST_HOW_SCFW(By.xpath("//section[@data-id='5792964']/div/div"), "How Supply Chain Finance Works"),

    //Key benefits for buyers and suppliers in supply chain financing
    LABEL_KEY_BENEFITS(By.xpath("//h2[text()='Key benefits for buyers and suppliers in supply chain financing']"), "Key benefits for buyers and suppliers in supply chain financing"),
    LABEL_FOR_BUYERS(By.xpath("//section[@data-id='9c869ac']//div[contains(text(), 'For Buyers')]"), " Benefits For Buyers"),
    LIST_BENEFITS_FOR_BUYERS_POINTS(By.xpath("//section[@data-id='9c869ac']/div/div[1]"), "List Of Benefits For Buyers"),
    LABEL_FOR_SUPPLIERS(By.xpath("//section[@data-id='9c869ac']//div[contains(text(), 'For Suppliers')]"), "Benefits For Suppliers"),
    LIST_BENEFITS_FOR_SUPPLIERS_POINTS(By.xpath("//section[@data-id='9c869ac']/div/div[2]"), "List Of Benefits For Buyers"),
    NOTE_BENEFITS(By.xpath("//div[contains(text(),'By implementing supply chain finance, agribusinesses can enhance financial stability')]"), SCM_BENEFITS_NOTE),


    PARAMETERIZE_OBJECT(By.xpath(""), "");

    public static synchronized ISuppllyLocatorInfo selectArticleTopic(int articleTopic){
        return setLocator(By.xpath("(//section[@data-id='5792964']/div/div)["+articleTopic+"]"), "Selecting How SCFW Article Topic");
    }

    public static ISuppllyLocatorInfo setLocator(By locator, String label) {
        SCM_FINANCE.PARAMETERIZE_OBJECT.locator = locator;
        SCM_FINANCE.PARAMETERIZE_OBJECT.lable = label;
        return SCM_FINANCE.PARAMETERIZE_OBJECT;

    }

    SCM_FINANCE(Object locator, String lable) {
        if(locator == null || lable == null){
            throw  new IllegalArgumentException("Argus must not be null");
        }
        this.locator = locator;
        this.lable = lable;
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
