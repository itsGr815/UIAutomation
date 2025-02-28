package com.dFarm.qa.dFarm.constants.groups;

public interface PACKHOUSE {

    String HOME = "Home";
    String STOCKMASTER = "Stock Master";
    String SORTING = "Sorting";
    String SALESORDER = "Sales Order";
    String PACKING = "Packing";
    String DISPATCH = "Dispatch";
    String CLIENTS = "Clients";

    interface STOCKMASTER {
        String IN_TAKE_ORDER_LIST = "In-Take Order List";
        String IN_TAKE = "In Take";
        String CURRENT_STOCK = "Current Stock";
    }

    interface SORTING {
        String SORTING_AND_GRADING = "Sorting and Grading";
        String SORTING_AND_GRADING_REPORT = "Sorting and Grading Report";
    }

    interface SALESORDER {
        String ADDORDER = "Add Order";
    }

    interface PACKING {
        String ALLOCATION = "Allocation";
        String PACKING_AND_QRCODE = "Packing & QR Code";
        String PACKED_LIST = "Packed List";
        String PALLET_CONVERSION = "Pallet Conversion";
    }

    interface DISPATCH {
        String LOADING = "Loading";
        String TRACKING = "Tracking";
    }

    interface CLIENTS {
        String CLIENT_LIST = "Client List";
    }

}
