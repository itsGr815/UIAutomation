package com.dFarm.qa.dFarm.constants.groups;

public interface PROCESSINGCENTER {

    String HOME = "Home";
    String INVENTORY = "Inventory";
    String PROCESSING = "Processing";
    String CRM = "Crm";
    String REPORTS = "Reports";
    String MARKETPLACE = "Marketplace";

    interface  INVENTORY {
        String STOCK_TAKE = "StockTake";
        String STOCK_LIST = "StockList";
    }

    interface PROCESSING {
        String SORTING = "Sorting";
        String PACKING = "Packing";
        String DISPATCH = "Dispatch";
        String PC_STATUS = "PcStatus";
    }

    interface MARKETPLACE {
        String PROFILE = "Profile";
        String INVENTORY = "Inventory";
        String MARKETPLACE_INVENTORY = "Marketplace Inventory";
        String PRICING = "Pricing";
    }
    interface CRM {}

   interface REPORTS {}


}
