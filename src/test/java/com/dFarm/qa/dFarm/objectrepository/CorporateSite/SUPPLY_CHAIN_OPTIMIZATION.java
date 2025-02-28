package com.dFarm.qa.dFarm.objectrepository.CorporateSite;

import com.dFarm.qa.dFarm.constants.CorporateSite;
import com.dFarm.qa.dFarm.util.datahandler.ISuppllyLocatorInfo;
import org.openqa.selenium.By;

public enum SUPPLY_CHAIN_OPTIMIZATION implements ISuppllyLocatorInfo, CorporateSite {

    LABEL_WHOLE_CHAIN(By.xpath("//div//h1[contains(text(), 'Whole Chain Visualization')]"), "Whole Chain Visualization"),
    TEXT_WHOLE_CHAIN_PARAGRAPH(By.xpath("//div//p[contains(text(), 'dFarm’s Agriculture Supply Chain Management')]"),
            "dFarm’s Agriculture Supply Chain Management (Ag SCM) services digitally link the entire Ag supply chain from farmer to retailer."),
    SVG_ICON_WHOLE_CHAIN_IMG(By.xpath("//div[@data-lottie-url='https://dfarminc.com/wp-content/uploads/2023/07/AIMS-Animation-Revised4dots.json']"), "Whole Chain Visualization Image"),

    //Total Supply Chain Visibility
    SECTION_TOTAL_SUPPLY_CHAIN_VISIBILITY(By.xpath("//h3[contains(text(),'Total Supply Chain Visibility')]"), "Section - Total Supply Chain Visibility Paragraph 1"),
    SECTION_TOTAL_SUPPLY_CHAIN_VISIBILITY_PARAGRAPH2(By.xpath("(//h3[contains(text(),'Total Supply Chain Visibility')]/../p)[2]"), "Section - Total Supply Chain Visibility Paragraph 2"),
    SECTION_TOTAL_SUPPLY_CHAIN_VISIBILITY_PARAGRAPH3(By.xpath("(//h3[contains(text(),'Total Supply Chain Visibility')]/../p)[3]"), "Section - Total Supply Chain Visibility Paragraph 3"),
    SECTION_TOTAL_SUPPLY_CHAIN_VISIBILITY_CIRCLE_BOX(By.xpath("//div[@class='circle-box']"), "- Circle Box - Total Supply Chain Visibility"),
    IMG_TOTAL_SUPPLY_CHAIN_VISIBILITY_CIRCLE_BOX_IMAGE(By.xpath("//div[@class='circle-box']/.."), "- Circle Box Behind Image - Total Supply Chain Visibility"),
    IFRAME(By.xpath("//iframe[@src='https://marketplace.dfarmhub.com/wp-animation/total-supply-chain-visibility']"), "iFrame - Total Supply Chain Visibility"),
    IFRAME2(By.xpath("//iframe[@nitro-lazy-src='https://marketplace.dfarmhub.com/wp-animation/total-supply-chain-visibility']"), "iFrame - Total Supply Chain Visibility"),

    //Optimize Your Supply Chain
    SECTION_OPTIMIZE_YSC(By.xpath("//h2[text()='Optimize Your Supply Chain']"), "Optimize Your Supply Chain"),
    SECTION_OPTIMIZE_YSC_FIRST(By.xpath("//section[@data-id='56ac764']//div[@data-id='2817970']"), "Optimize Your Supply Chain - First Row"),
    SECTION_OPTIMIZE_YSC_SECOND(By.xpath("//section[@data-id='56ac764']//div[@data-id='101a123']"), "Optimize Your Supply Chain - Second Row"),
    SECTION_OPTIMIZE_YSC_THIRD(By.xpath("//section[@data-id='56ac764']//div[@data-id='626463a']"), "Optimize Your Supply Chain - Third Row"),

    //Revolutionize Your Supply Chain with IT

    SECTION_REVOLUTIONIZE_YOUR_SUPPLY_CHAIN_WITH_IT(By.xpath("//h2[text()='Revolutionize Your Supply Chain with IT']"), "Revolutionize Your Supply Chain with IT"),
    SECTION_REVOLUTIONIZE_YOUR_SUPPLY_CHAIN_WITH_IT_PARAGRAPH(By.xpath("//div[@data-id='49f574d']"), "Revolutionize Your Supply Chain with IT"),
    IMG_PRO_FOUND_GLOBAL_IMPACT(By.xpath("//div[@data-id='bb89ab5']//div/img"), "Image - A Profound Global Impact"),


    //Delivering a Sustainable Future
    LABEL_DELIVERING_A_SUSTAINABLE_FUTURE(By.xpath("//h2[text()='Delivering a Sustainable Future']"), "Delivering a Sustainable Future"),
    TEXT_dFARM_SUPPORTS(By.xpath("//div[contains(text(), 'dFarm supports the United Nations’ Sustainable Development Goals')]"), CorporateSite.dFARM_SUPPORT_THE_UN),
    SECTION_DELIVERING_ICONS(By.xpath("//section[@data-id='52e3431']"), "Section - Delivering a Sustainable Future"),
    IMG_ZERO_HUNGER(By.xpath("//div[@id='big-image']"), "Image - Zero Hunger"),
    IMAGES_ZERO_HUNGER(By.xpath("//div[@class='small-images']"), "Images - Zero Hunger"),

    PARAMETERIZE_OBJECT(By.xpath(""), "");

    public static ISuppllyLocatorInfo setLocator(By locator, String label) {
        SUPPLY_CHAIN_OPTIMIZATION.PARAMETERIZE_OBJECT.locator = locator;
        SUPPLY_CHAIN_OPTIMIZATION.PARAMETERIZE_OBJECT.lable = label;
        return SUPPLY_CHAIN_OPTIMIZATION.PARAMETERIZE_OBJECT;

    }

    SUPPLY_CHAIN_OPTIMIZATION(Object locator, String lable) {
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
