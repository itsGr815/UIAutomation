package com.dFarm.qa.dFarm.constants.groups;

public interface COLDSTORAGE {

    String HOME = "Home";
    String FARMER = "Farmer";
    String BUSINESS = "Business";
    String STOCK_TAKE_MASTER = "Stocktake Master";
    String SALES_ORDER = "Sales Order";

    interface HOME {
        String MY_PROFILE = "MY Profile";
        String EMPLOYEES = "Employees";
        String WAREHOUSE = "Warehouse";
        String CONFIGURATION= "Configuration";
        String HELP = "Help";
        String SETTINGS = "Settings";
    }

    interface FARMER {
        String FARMERS_LIST = "Farmers List";
        String PRODUCE_LIST = "Produce List";
        String PRODUCTS = "Products";
    }

    interface BUSINESS {
        String BUSINESS_LIST = "Business List";
        String PRODUCE_LIST = "Produce List";
    }

    interface STOCKTAKEMASTER {
        String STOCK_TAKE_ORDER_LIST = "Stocktake Order List";
        String STOCK_TAKE_ENTRY = "Stocktake Entry";
        String STOCK_TAKE_LIST = "Stocktake List";
    }

    interface SALESORDER {
        String SALES_ORDER_LIST = "Sales Order List";
        String DISPATCH_ORDER = "Dispatch Order";
        String DISPATCH_LIST = "Dispatch List";
    }
}
