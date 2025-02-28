package com.dFarm.qa.dFarm.constants;

public interface CollectionCenter {

    //Daily Schedule Table Headers
    String PRODUCE_ID = "Produce Id";
    String FARMER_ID = "Farmer Id";
    String PRODUCE = "Produce";
    String VARIETY = "Variety";
    String TOTAL_WEIGHT = "Total weight";
    String PRODUCE_STATUS = "Produce Status";
    String ACTUAL_QUANTITY = "Actual Qty";
    String HARVEST_DATE = "Harvest Date";
    String productName = "Product Name";

    String[] dailySchedulesTable = {"Farmer Id", "Produce Id", "Produce", "Variety", "Total weight", "Produce Status", "Actual Qty", "Harvest Date"};
    String[] dispatchReportsTableHeaders = {"Produce Id", "Type", "Loading Date", "Loading Status", "Vehicle No", "Status", "Sent to PC ID", "Expected Date"};
    String[] userDetails = {"UserName", "First Name", "Last Name", "Personal Email", "Start Date", "End Date"};
    String[] Measurement = {"Imperial -Pounds", "Metric -Kilogram"};
    String[] Currency = {"USD", "CAD", "INR"};
    String[] employeeOrBusiness = {"Employee Details", "Business Information"};
    String[] employeePrivilegeHeaders = {"Privileges", "Read", "Add", "Edit", "Delete"};
    String[] employeePrivilegeSideHeaders = {"Dashboard", "Collection", "Dispatch", "Transportation", "Employee"};
    String[] employeeList = {"No", "Username", "First Name", "Middle Name", "Last Name", "Department", "Phone", "Email", "Status", "Action"};
    String[] rowsPerPage = {"5", "10", "25", "50", "100"};
    String[] departmentList = {"ROLE ID", "ROLE NAME", "ROLE DESCRIPTION", "STATUS", "Action"};
    String[] allocateFarmerHeaders = {"Sr. No", "Farmer ID", "Country", "State", "County", "Full Name", "Mobile", "Email", "Field Officer", "Status"};
    String[] scheduleListHeaders = {"Sr. No", "Username", "Field Officer Name", "Phone", "Schedule on", "Schedule for", "Schedule summary", "Schedule status",
                                    "Last Updated", "Details"};
    String[] ProduceCollectionStatusTableHeaders = {"No", "Farmer Id", "Produce Id", "Produce", "Variety", "Status", "Type Of Collections", "Expected Quantity",
                                    "Actual Quantity", "Harvested", "CC Received"};
    String[] produceDetails = {"Produce", "Baggage Type", "Total Count", "Total Weight", "Available Quantity", "Loaded Weight", "Loaded Count"};
    String[] produceLoadingDP = {"Sr. No", "Produce ID", "Farmer ID", "Produce", "Variety", "QR Code", "Type Of Dispatch", "Loaded Weight", "Loading Date",
                                "Loading Time", "Loading Status", "Vehicle Plate", "Vehicle Model", "Vehicle Color", "PCID", "PC Arrival", "Comments"};
    String[] produceDispatchTableHeaders = {"No", "Produce ID", "Produce", "Variety", "Total Weight (in Kgs)", "CC Received", "Dispatch Status",
                                           "CC Dispatched Date", "CC Dispatched Time", "Vehicle Plate", "Vehicle Model", "PCID", "PC Arrival", "Comments"};


    // Collect Produce in form of
    String MODE_OF_TRANSPORT = "ModeOFTransport";
    String TRUCK = "Truck";
    String CRATES = "Crates";
    String BINS = "Bins";
    String BAGS = "Bags";

    String ACCEPT = "Accept";
    String REJECT = "Reject";
    String SUBMIT = "Submit";
    String SAVE = "Save";
    String RESET = "Reset";
    String PENDING = "PENDING";
    String ACCEPTED = "Accepted";
    String CANCEL = "Cancel";
    String REJECTED = "Rejected";

    //Truck Data
    String TRUCK_PLATE_NUM = "Truck Plate No";
    String DRIVER_NAME = "Driver Name";
    String LOADED_TRUCK_WEIGHT = "Loaded Truck Weight";
    String EMPTY_TRUCK_WEIGHT = "Empty Truck Weight";
    String REJECT_OR_REDUCE_WEIGHT = "Rejected / Reduce Wgt";
    String PRODUCE_QTY = "Produce Qty";
    String ADD_COMMENT = "Add Comment";
    String WEIGHT = "Weight";
    String ADD_PRODUCE = "Add Produce";
    String VEHICLE_MODEL = "Vehicle Model";
    String VEHICLE_COLOR = "Vehicle Color";
    String MOBILE_NUMBER = "Mobile Number";

    String REASON_FOR_REJECTION = "Reason for Rejection";

    // Tab Names
    String TAB_HOME = "Home";
    String TAB_COLLECTION = "Collection";
    String TAB_DISPATCH = "Dispatch";
    String TAB_TRANSPORTATION = "Transportation";

    //Dispatch
    String ANN_ARBOR_PROCESSING_CENTER = "Ann Arbor Processing Center";
    String[] produceDetailsHeaders = {"Produce", "Baggage Type", "Total Count", "Total Weight",
                                      "Loaded Weight", "Loaded Count"};
    String[] produceLoadingTableHeaders = {"Sr. No", "Produce ID", "Farmer ID", "Produce", "Variety", "Type Of Dispatch", "Total Weight", "PCID"};
    String DISPATCHED = "DISPATCHED";

    //Transportation
    String[] transportAndProduceTrackingHeaders = {"No", "Vehicle Plate", "Bag QR Code", "Bag Weight (in Kgs)", "Produce ID", "Loading Date",
                                                   "Loading Time", "PCID", "Unloading Date", "Unloading Time", "Status", "Comment"};

}
