package com.dFarm.qa.dFarm.objectrepository.CorporateSite;

import com.dFarm.qa.dFarm.constants.CorporateSite;
import com.dFarm.qa.dFarm.util.datahandler.ISuppllyLocatorInfo;
import org.openqa.selenium.By;

public enum PRECISION_TRACING implements ISuppllyLocatorInfo, CorporateSite {


    //Real-Time Whole Chain Tracing
    LABEL_REAL_TIME(By.xpath("//h1[text()='Real-Time Whole Chain Tracing']"), "Real-Time Whole Chain Tracing"),
    PARAGRAPH_REAL_TIME(By.xpath("//p[contains(text(),'Risk is unmanageable without visibility.')]"), REAL_TIME_WHOLE_CHAIN_TRACING),
    IMG_REAL_TIME_TRANSITION(By.xpath("//div[@data-id='f4a9c82']"), REAL_TIME_WHOLE_CHAIN_TRACING),

    //With Precision Trace
    IFRAME_PT(By.xpath("//iframe[@src='https://marketplace.dfarmhub.com/wp-animation/precision-tracing-precision-trace']"), "iFame PT"),
    IFRAME_PT2(By.xpath("//iframe[@nitro-lazy-src='https://marketplace.dfarmhub.com/wp-animation/precision-tracing-precision-trace']"), "iFame PT 2"),
    LABEL_WITH_PRECISION_TRACE(By.xpath("//h2[text()='With Precision Trace']"), "With Precision Trace"),
    PARAGRAPH_WITH_PRECISION_TRACE(By.xpath("//p[contains(text(),'Manage the track & trace process for your ag supply chain.')]"), PRECISION_PARAGRAPH),
    PRECISION_TRACING_P1(By.xpath("//span[contains(text(),'Visualize your entire supply chain and the role of all participants in it.')]"), "Visualize your entire supply chain and " +
            "the role of all participants in it."),
    IMG_AIMS_CIRCLE(By.xpath("//div[@class='aims-circle-section']"), "Image Precision Trace"),

    //Delivering Food Safety and Quality
    LABEL_DELIVERING_FOOD_SAFETY(By.xpath("//h2[contains(text(),'Delivering Food Safety and Quality')]"), "Delivering Food Safety and Quality"),
    PARAGRAPH1_DELIVERING_FOOD_SAFETY(By.xpath("//p[contains(text(),'BRCGS, Fair Trade, FSSAI, GAP, Global GAP, HALAL, HACCP, Organic and more.')]"), DELIVERING_FOOD_SAFETY1),
    PARAGRAPH2_DELIVERING_FOOD_SAFETY(By.xpath("//div[contains(text(),'Administration (FDA) Food Safety and Modernization Act (FSMA) rule 204.')]"), DELIVERING_FOOD_SAFETY2),
    PARAGRAPH3_DELIVERING_FOOD_SAFETY(By.xpath("//div[contains(text(),'This is possible because we store all data centrally in a cloud database where it is immediately and reliably accessible')]"), DELIVERING_FOOD_SAFETY3),

    //Certification Section
    SECTION_CERTIFICATIONS(By.xpath("//section[@data-id='c8de1ca']"), "Certification Section"),

    //Certificate Images
    CERT_BRCGS(By.xpath("//img[@src='https://cdn-jogah.nitrocdn.com/awnLFzHRrtRPrQiqsAWbUsBnVpkBIzoG/assets/images/optimized/rev-d27d87d/dfarminc.com/wp-content/uploads/2023/07/1-1.png']"), "BRCGS - Food Safety Certificate"),
    CERT_FSSAI(By.xpath("//img[@src='https://cdn-jogah.nitrocdn.com/awnLFzHRrtRPrQiqsAWbUsBnVpkBIzoG/assets/images/optimized/rev-d27d87d/dfarminc.com/wp-content/uploads/2023/07/2-1.png']"), "FSSAI Certificate"),
    CERT_GLOBALGAP(By.xpath("//img[@src='https://cdn-jogah.nitrocdn.com/awnLFzHRrtRPrQiqsAWbUsBnVpkBIzoG/assets/images/optimized/rev-d27d87d/dfarminc.com/wp-content/uploads/2023/07/3-1.png']"), "Global GAP Certificate"),
    CERT_HACCP(By.xpath("//img[@src='https://cdn-jogah.nitrocdn.com/awnLFzHRrtRPrQiqsAWbUsBnVpkBIzoG/assets/images/optimized/rev-d27d87d/dfarminc.com/wp-content/uploads/2023/07/4-1.png']"), "HACCP Certificate"),

    PARAMETERIZE_OBJECT(By.xpath(""), "");

    public static ISuppllyLocatorInfo setLocator(By locator, String label) {
        PRECISION_TRACING.PARAMETERIZE_OBJECT.locator = locator;
        PRECISION_TRACING.PARAMETERIZE_OBJECT.lable = label;
        return PRECISION_TRACING.PARAMETERIZE_OBJECT;

    }

    PRECISION_TRACING(Object locator, String lable) {
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
