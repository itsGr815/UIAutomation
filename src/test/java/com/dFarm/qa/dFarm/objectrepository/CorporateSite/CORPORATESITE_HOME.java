package com.dFarm.qa.dFarm.objectrepository.CorporateSite;

import com.dFarm.qa.dFarm.constants.CorporateSite;
import com.dFarm.qa.dFarm.util.datahandler.ISuppllyLocatorInfo;
import org.openqa.selenium.By;

public enum CORPORATESITE_HOME implements ISuppllyLocatorInfo, CorporateSite {

    //Header Link
    LINK_SUPPLY_CHAIN_OPTIMIZATION(By.xpath("//a[text()[normalize-space() = 'Supply Chain Optimization']]"), SUPPLY_CHAIN_OPTI),
    LINK_DYNAMIC_MARKETPLACE(By.xpath("//a[text()[normalize-space() = 'Dynamic Marketplace']]"), DYNAMIC_MP),
    LINK_SCM_FINANCE(By.xpath("//a[text()[normalize-space() = 'SCM Finance']]"), "SCM Finance"),
    LINK_PRECISION_TRACING(By.xpath("//a[text()[normalize-space() = 'Precision Tracing']]"), "Precision Tracing"),
    LINK_FSMA_204(By.xpath("//a[text()[normalize-space() = 'FSMA 204']]"), "FSMA 204"),
    LINK_TECHNOLOGIES(By.xpath("//a[text()[normalize-space() = 'Technologies']]"), "Technologies"),
    LINK_GET_DEMO(By.xpath("//a[text()[normalize-space() = 'Get a demo']]"), GET_A_DEMO),
    LINK_HEADER_TEXT(By.xpath("//a[text()[normalize-space() = 'Supply Chain Optimization']]/../..//li/a"), "dFarm inc home page headers"),

    //Food System Section
    SECTION_FOOD_SAFETY(By.xpath("//h1[text()='A Food System Powered by Hyperdata']/../../.."), "A Food System Section"),
    BUTTON_WATCH_PRODUCT_TOUR(By.xpath("(//a//span[contains(.,'Watch Product Tour ')])[1]/.."), "Watch Product Tour  "),
    ////h1[text()='A Food System Powered by Hyperdata']/../../following-sibling::div
    LABEL_FOOD_SAFETY(By.xpath("//h1[text()='A Food System Powered by Hyperdata']"), "A Food System Powered by Hyperdata"),

    //THINK BEYOND FSMA 204 Section
    LABEL_THINK_BEYOND_FSMA_204(By.xpath("(//div/h4[contains(.,'THINK BEYOND FSMA 204')])[1]"), "THINK BEYOND FSMA 204"),
    CONTENT_THINK_BEYOND_FSMA_204(By.xpath("(//div/h4[contains(.,'THINK BEYOND FSMA 204')])[1]/../../../.."), "Content - THINK BEYOND FSMA 204"),
    CONTENT_LIST_FSMA_204(By.xpath("//div[text()='\t\t\t\tFSMA 204 Compliance and much, much more\t\t\t\t\t\t']/../following-sibling::div"), "FSMA 204 Content List"),
    BUTTON_GET_A_CONSULTATION(By.xpath("(//a[contains(.,'Get a Consultation')])[1]"), "Get a Consultation"),
    BUTTON_LEARN_MORE(By.xpath("(//a[contains(.,'Learn More')])[1]"), "Learn More"),


    //REAL-TIME WHOLE CHAIN DISTRIBUTED ERP
    LABEL_REAL_TIME_WHOLE_CHAIN(By.xpath("//h1[text()='REAL-TIME WHOLE CHAIN DISTRIBUTED ERP']"), "REAL-TIME WHOLE CHAIN DISTRIBUTED ERP"),
    LABEL_REAL_TIME_WHOLE_CHAIN_CONTENT(By.xpath("(//h1[text()='REAL-TIME WHOLE CHAIN DISTRIBUTED ERP']/../../following::section)[1]"), "REAL-TIME WHOLE CHAIN DISTRIBUTED ERP Content"),

    //Whole Chain Visualization
    LABEL_WHILE_CHAIN_VISUALIZATION(By.xpath("//h1[text()='Whole Chain Visualization']"), "Whole Chain Visualization"),
    ////h1[text()='Whole Chain Visualization']/../../following-sibling::div
    CONTENT_WHOLE_CHAIN_PARAGRAPH1(By.xpath("(//h1[text()='Whole Chain Visualization']/../../following-sibling::div)[1]"), "Whole Chain Visualization Paragraph 1"),
    CONTENT_WHOLE_CHAIN_PARAGRAPH2(By.xpath("(//h1[text()='Whole Chain Visualization']/../../following-sibling::div)[2]"), "Whole Chain Visualization Paragraph 1"),
    BUTTON_WHOLE_CHAIN_LEARN_MORE(By.xpath("//span[text()='Learn More ']/../../../a[@href='/supply-chain-optimization']"), "Learn More button - Whole Chain Visualization"),


    //Global Digital Marketplace
    LABEL_GLOBAL_DIGITAL_MARKETPLACE(By.xpath("//h1[text()='Global Digital Dynamic Marketplace']"), "Global Digital Marketplace"),
    ////h1[text()='Global Digital Marketplace']/../../../div
    CONTENT_GDM_PARAGRAPH1(By.xpath("(//h1[text()='Global Digital Dynamic Marketplace']/../../../div)[2]"), "Why settle for a chat-based trading community that’s slow and inefficient" +
            "when you can have a fully digital interactive marketplace with real-time quotes."),
    CONTENT_GDM_PARAGRAPH2(By.xpath("(//h1[text()='Global Digital Dynamic Marketplace']/../../../div)[3]"), "Introducing the first AI-powered digital interactive marketplace that instantly " +
            "connects buyers and sellers to new domestic and global markets."),
    BUTTON_GDM_LEARN_MORE(By.xpath("//span[text()='Learn More ']/../../../a[@href='https://marketplace.dfarmhub.com/marketplace/home']"), "Learn More Button - Global Digital Marketplace"),

    //Precision Tracing
    LABEL_PRECISION_TRACING(By.xpath("//h1[text()='Precision Tracing']"), "Precision Tracing"),
    ////h1[text()='Precision Tracing']/../../../div
    CONTENT_PT_PARAGRAPH1(By.xpath("(//h1[text()='Precision Tracing']/../../../div)[2]"), "All recalls are bad, but what’s unacceptable are time-consuming recalls that require you discard " +
            "perfectly good food just because you can’t quickly isolate just the offending source."),
    CONTENT_PT_PARAGRAPH2(By.xpath("(//h1[text()='Precision Tracing']/../../../div)[3]"), "Introducing a new whole supply chain trace capability that’s dynamic, precise, visual, " +
            "interactive – and enables tracing back and forward."),
    BUTTON_PT_LEARN_MORE(By.xpath("//span[text()='Learn More ']/../../../a[@href='/precision-tracing']"), "Learn More Button - Precision Tracing"),
    //dFarm Logo
    IMG_HEADER_dFARM_LOGO(By.xpath("(//a[@href='/home'])[1]/img"), "dFarm Header Logo"),
    IMG_FOOTER_dFARM_LOGO(By.xpath("(//a[@href='/home'])[2]/img"), "dFarm Footer Logo"),

    //Header Section
    SECTION_HEADER(By.xpath("//section[@data-id='e584bc1']"), "dFarm Header Section"),

    //Footer Section
    SECTION_FOOTER(By.xpath("//section[@data-id='89d562e']"), "dFarm Footer Section"),

    //Footer Link
    LINK_HOME(By.xpath("//a[@href='/dfarmindia/home']/span[text()='Home']"), "Footer Link Home"),
    LINK_ABOUT_US(By.xpath("//a[@href='/about-us']/span[text()='About Us']"), "Footer Link About Us"),
    LINK_BLOG(By.xpath("//a[@href='/blogs']/span[text()='Blogs']"), "Footer Link Blogs"),
    LINK_CASE_STUDY(By.xpath("//span[text()='Case Studies']"), "Footer Link Case Studies"),
    LINK_NEWS(By.xpath("//a[@href='/news']/span[text()='News']"), "Footer Link News"),
    LINK_SOCIAL(By.xpath("//p[text()='Social']"), "Footer Link Social"),
    LINK_SOCIAL_LINKEDIN(By.xpath("(//p[text()='Social']/a)[1]"), "Footer Link Social Linkedin"),
    LINK_SOCIAL_TWITTER(By.xpath("(//p[text()='Social']/a)[1]"), "Footer Link Social Twitter"),
    LINK_FOOTER_SUPPLY_CHAIN_OPTIMIZATION(By.xpath("//a[@href='/supply-chain-optimization']/span[text()='Supply Chain Optimization']"), "Footer Link Supply Chain Optimization"),
    LINK_FOOTER_DYNAMIC_MARKETPLACE(By.xpath("//span[text()='Dynamic Marketplace']"), "Footer Link Dynamic Marketplace"),
    LINK_FOOTER_SCM_FINANCE(By.xpath("//a[@href='/scm-finance']/span[text()='SCM Finance']"), "Footer Link SCM Finance"),
    LINK_FOOTER_PRECISION_TRACING(By.xpath("//a[@href='/precision-tracing']/span[text()='Precision Tracing']"), "Precision Tracing"),
    LINK_FOOTER_FSMA_204(By.xpath("//a[@href='/fsma-204']/span[text()='FSMA 204']"), "Footer Link FSMA 204"),
    LINK_FOOTER_TECHNOLOGIES(By.xpath("//a[@href='/technologies']/span[text()='Technologies']"), "Footer Link Technologies"),
    LINK_CAREERS(By.xpath("//a[@href='/career']/span[text()='Careers']"), "Footer Link Careers"),
    TEXT_GLOBAL_HEADQUARTERS(By.xpath("(//div[contains(text(), 'Global Headquarters')])[1]"), "Global Headquarters"),
    TEXT_GLOBAL_HEADQUARTERS_ADDRESS1(By.xpath("(//div[contains(text(), 'Global Headquarters')])[1]/../../div[2]"), "Global Headquarters Address 1"),
    TEXT_GLOBAL_HEADQUARTERS_ADDRESS1_PHONE(By.xpath("(//div[contains(text(), 'Global Headquarters')])[1]/../../div[3]"), "Global Headquarters Address 1 Phone"),
    TEXT_REGIONAL_OFFICE(By.xpath("(//div[contains(text(), 'Regional Office')])[2]"), "Regional Office"),
    TEXT_REGIONAL_OFFICE_LIST(By.xpath("(//div[contains(text(), 'Regional Office')])[2]/../../div[2]"), "Regional Office List"),



    PARAMETERIZE_OBJECT(By.xpath(""), "");



    public static ISuppllyLocatorInfo setLocator(By locator, String label) {
        CORPORATESITE_HOME.PARAMETERIZE_OBJECT.locator = locator;
        CORPORATESITE_HOME.PARAMETERIZE_OBJECT.lable = label;
        return CORPORATESITE_HOME.PARAMETERIZE_OBJECT;

    }

    CORPORATESITE_HOME(Object locator, String lable) {
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
