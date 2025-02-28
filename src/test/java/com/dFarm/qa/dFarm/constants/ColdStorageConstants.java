package com.dFarm.qa.dFarm.constants;

public interface ColdStorageConstants {


    String[] dateInRange = {"1D", "5D", "1M", "3M", "6M", "1Y", "2Y", "5Y", "10Y", "Max"};
    String[] csMenuBar = {"Home", "Farmer", "Business", "Stocktake Master", "Sales Order"};
    String[] departmentListTableHeaders = {"ROLE ID", "ROLE NAME", "ROLE DESCRIPTION", "STATUS", "Action"};
    String[] floorTableHeaders = {"Floor code", "Name", "Type", "Capacity", "Temperature", "Storage Type", "Total Capacity Util(%)", "Edit"};
    String[] aisleTableHeaders = {"Floor code", "code", "Name", "Capacity", "Edit"};
    String[] stackTableHeaders = {"Floor code", "Aisle", "code", "Name", "Capacity", "Edit"};
    String[] palletTableHeaders = {"Floor code", "Aisle", "Stack", "code", "Name", "Capacity", "Edit"};
    String[] farmerListTableHeaders = {"Sr. No", "Farmer ID", "First Name", "Middle Name", "Last Name", "Mobile No.", "Email ID", "Action", "Status", "Details"};
    String[] produceListTableHeaders = {"Sr. No", "Farmer ID", "Produce ID", "Produce", "Variety", "Total Quantity", "Stock on Hold", "Open Market", "Marketplace",
                                        "CS Entry Date", "CS Exit Date", "Stored Days", "Stock Availability", "Details"};
    String[] productListTableHeaders = {"Name", "Description", "No of Variety", "Image", "Action"};
    String[] editProductListTableHeaders = {"Name", "Description", "Shelf Life (Days)", "Image", "Action"};
    String[] farmerInfoOverlayHeaders = {"MAINID", "NAME", "Phone", "Email"};
    String[] countryList = {" United States", "Canada", "India"};
    String[] businessListTableHeaders = {"Sr. No", "Business ID", "Business Name", "Name", "Mobile No.", "Email ID", "Status", "Details"};
    String[] businessList = {"Distribution Center", "Buyer", "Pack House", "Collection Center", "Processing Center"};
    String[] businessInfoTableHeaders = {"Person Name", "Phone", "EXT", "FAX", "eMAIL"};
    String[] businessProduceListTableHeaders = {"Sr. No", "Business ID", "Produce ID", "Produce", "Variety", "Total Quantity", "Stock on Hold", "Open Market",
                                                "Marketplace", "CS Entry Date", "CS Exit Date", "Stored Days", "Stock Availability", "Details"};
    String[] businessInfoTable = {"Registration Id", "Name", "Registration #", "Start Year"};
    String[] stockTakeRequestTableHeaders = {"Sr. No", "Client Type", "Client ID", "Order Received", "PR. ID", "Produce", "Variety", "Type of Dispatch",
                                             "Expected Weight (in Kgs)", "Expected Arrival Date", "Expected Exit Date", "Duration in Days", "Order Status",
                                             "Collection Status", "Collection Date"};
    String[] stockTakeEntryProduceCollected = {"Sr. No", "Farmer/Business ID", "PR. ID", "Produce", "Variety", "Pallet No.", "Quantity", "Loaded Weight", "CS Loading Date", "CS Loading Time",
                                               "Floor", "Aisle", "Stack", "Pallet", "Details"};
    String[] stockTakeEntryProduceCollectedArrow = {"Sr. No", "Packing Type", "Weight", "QR Code", "Received Date", "Status", "Expiration Date", "Process Type", "Comments"};
    String[] stockTakeListProduceCollected = {"Sr. No", "Client ID", "PR. ID", "Produce", "Variety", "Pallet", "Total Quantity Loaded", "Total Quantity Dispatch",
                                              "Total Quantity Available", "CS Loading Date", "CS Exit Date", "Stored Days", "Status"};
    String[] salesOrderListHeaders = {"Sr. No", "Sales Order ID", "Order From PH ID / Retailers ID", "Order Placed Date", "Quantity", "Dispatched Quantity", "Dispatch Request Date",
                                      "Order Status", "Comment", "Dispatch Status", "Dispatch Date", "Dispatch Details"};
    String[] newSalesOrderTableHeaders = {"Sr. No", "PRODUCE", "VARIETY", "Order Req Date", "Quantity", "Status"};
    String[] existingStockTableHeaders = {"Sr. No", "Farmer/Business ID", "PR. ID", "Produce", "Variety", "Pallet No.", "Quantity", "Loaded Weight", "Available Weight",
                                          "Received Date", "Floor", "Aisle", "Stack", "Pallet", "Quantity", "Status"};
    String[] orderedTableHeaders = {"Sr. No", "Farmer/Business ID", "PR. ID", "Produce", "Variety", "Pallet No.", "Quantity", "Received Date", "Floor", "Aisle",
                                    "Stack", "Pallet"};

    String INDIA = "India";
    String CANADA = "Canada";


    String WAREHOUSE = "Warehouse";
    String FARMER = "Farmer";
    String BUSINESS = "Business";
    String STOCK_TAKE_MASTER = "Stocktake Master";
    String SALES_ORDER = "Sales Order";

    String ACTIVE = "ACTIVE";
    String INACTIVE = " INACTIVE";

    String METRIC_KILOGRAM = "Metric -Kilogram";
    String IMPERIAL_POUNDS = "Imperial -Pounds";

    String AISLE_AND_PALLET_STRUCTURE = "Aisle and Pallet Structure";
    String LOT_LEVEL_SYSTEM = "Lot Level System";

    String FARMER_LIST = "Farmer List";
    String PRODUCE_LIST = "Produce List";
    String PRODUCT_LIST = "Product List";

    String BUSINESS_TYPE = "Business Type";
    String BUSINESS_ID = "Business Id";

    String[] domainNames = {"@gmail.com", "@Yahoo.com", "@hotmail.com", "@dfarminc.com", "@org.com"};
}
