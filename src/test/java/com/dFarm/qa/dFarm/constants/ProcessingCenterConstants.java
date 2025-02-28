package com.dFarm.qa.dFarm.constants;

public interface ProcessingCenterConstants {

    String[] expectedArrivalsTableHeaders = {"CC ID Details", "Vehicle Plate No", "Produce ID", "Produce", "Variety",
                                             "Receive Type", "Quantity Sent", "Quantity Accepted"};
    String[] menuTabs = {"Home", "Inventory", "Processing", "CRM", "Reports"};
    String[] stockEntryTableEntry = {"Sr. No.", "CC ID", "Farmer ID", "Produce ID", "Produce", "Variety", "CC Collection Date", "Baggage Type Collected", "Barcode / Manual Tracking code",
                                     "Count", "Weight (in Kgs)", "PC Collection Date", "PC CollectionTime", "Status", "Comments", "Actions"};
    String[] stockAvailabilityAtCC = {"Sr. No.", "CC ID", "Farmer Id", "Produce Id", "Produce", "Variety", "Baggage Type", "Baggage Count", "Total Weight (in Kgs)",
                                      "Availability", "Status"};
    String[] stockEntrySortingPC = {"Sr. No.", "CC ID", "Farmer ID", "Produce ID", "Produce", "Variety", "Product Type", "PC Collection Date", "Batch #", "Allocated Weight (in Kgs)",
                                    "Pre Process Loss (in Kgs)", "Preparation Quantity (in Kgs)", "Process Loss (in Kgs)", "Realised (in Kgs)", "Realisation (%)", "Processing Date",
                                    "Processing Time", "Rating", "Status", "Comments", "Action"};
    String[] packingTable = {"Sr. No.", "Produce ID", "Produce", "Variety", "Product Type", "Batch #", "Packing Type", "Count", "Manual / QR Code",
                             "Weight (in Kgs)", "Packing Date", "Packing Time", "Expiration Date", "Status", "Comments", "Action"};
    String[] PRIDListHeaders = {" 	", "Batch No", "Produce Id", "Produce", "Variety", "Allocated To", "Status", "Created Date"};
    String[] dispatchTableHeaders = {"Sr. No.", "Produce ID", "Produce", "Variety", "Type of End Product", "Batch No", "Packing Type", "Cold Storage / Buyer ID", "Cold Storage / Buyer ID",
                                      "Loaded Quantity", "QR Code", "Dispatch Date", "Expected Delivery Date", "Expiration Date", "Produce Received Date", "Produce Received Time", "Status", "Comments", "Action"};
    String[] dispatchList = {"Sr. No.", "Produce ID", "Produce", "Variety", "Type of End Product", "Packing Type", "Weight (In Kgs)", "QR Code", "Packing Date", "Expiration Date",
                             "Cold Storage / Buyer ID", "Dispatch Date", "Expected Delivery Date", "Status", "Comment"};

    String INVENTORY = "Inventory";
    String PROCESSING =  "Processing";
    String CRM = "CRM";
    String REPORTS = "Reports";

    String FORECASTED = "Forecasted";
    String AVAILABLE = "Available";

    String APPLE = "Apple";
    String CIDER = "Cider";
    String WINE = "Wine";
    String JUICE = "Juice";
    String[] productType = {"Cider", "Wine", "Juice"};

    String PP_BAGS = "PP Bags";
    String HDPE_BAGS = "HDPE Bags";
    String PLASTIC_BAGS = "Plastic Bags";
    String COTTON_BOXES = "Carton Boxes";
    String[] packBackType = {"PP Bags", "HDPE Bags", "Plastic Bags", "Carton Boxes"};

    String  DETROIT_COLD_STORAGE = "Detroit Cold Storage";
    String PRICE_LIST = "Price List";
    String ADD_PRICE = "Add Price";
    String[] ADD_PRICE_TABLE_HEADERS = {"Sl # ", "Grade ", "Size ", "Price ", "Other Price ", "Total "};

}
