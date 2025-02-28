package com.dFarm.qa.dFarm.pageobjects.Global;

import com.dFarm.qa.dFarm.constants.CollectionCenter;
import com.dFarm.qa.dFarm.util.execution.AllDataHolder;
import com.dFarm.qa.dFarm.util.execution.FlexFrameWorkRunTimeException;
import com.dFarm.qa.dFarm.util.execution.GlobalHelpers;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

public class GettersAndSetters implements CollectionCenter {

   @Getter @Setter Map<Object, String> farmerId = new HashMap<>();
   @Getter @Setter Map<Object, String> produceId = new HashMap<>();
   @Getter @Setter Map<Object, String> businessId = new HashMap<>();
   @Getter @Setter Map<Object, String> variety = new HashMap<>();
   @Getter @Setter Map<Object, String> totalWeight = new HashMap<>();
   @Getter @Setter Map<Object, String> produceStatus = new HashMap<>();
   @Getter @Setter Map<Object, String> ActualQty = new HashMap<>();
   @Getter Map<Object, String> harvestDate = new HashMap<>();
   Map<Object, String> rejectOrAccept = new HashMap<>();
   Map<Object, String> farmerOrBusinessId = new HashMap<>();
   Map<Object, String> produce = new HashMap<>();
   Map<Object, String> salesOrderId = new HashMap<>();
   Map<Object, String> retailerId = new HashMap<>();

    public String getRejectOrAccept() {
        return rejectOrAccept.get(Thread.currentThread().getId());
    }

    public void setRejectOrAccept(String rejectOrAcceptVal) {
       rejectOrAccept.put(Thread.currentThread().getId(), rejectOrAcceptVal);
    }

    public String  getFarmerId() {
        return farmerId.get(Thread.currentThread().getId());
    }

    public void setFarmerId(String farmerIdVal) {
        farmerId.put(Thread.currentThread().getId(), farmerIdVal);
    }

    public String  getProduceId() {
        return produceId.get(Thread.currentThread().getId());
    }

    public void setProduceId(String produceVal) throws FlexFrameWorkRunTimeException {
      /*   produceVal = AllDataHolder.getTestData().get(AllDataHolder.getCurrentTestCaseId(), PRODUCE_ID).length() > 0 ?
                AllDataHolder.getTestData().get(AllDataHolder.getCurrentTestCaseId(), PRODUCE_ID)
                 : AllDataHolder.getHomePage().getProduceIdFromDailySchedule();*/
        produceId.put(Thread.currentThread().getId(), produceVal);
    }

    public String  getVariety() {
        return variety.get(Thread.currentThread().getId());
    }

    public void setVariety(String varietyVal) {
        variety.put(Thread.currentThread().getId(), varietyVal);
    }

    public String  getTotalWeight() {
        return totalWeight.get(Thread.currentThread().getId());
    }

    public void setTotalWeight(String totalWeightVal) {
        totalWeight.put(Thread.currentThread().getId(), totalWeightVal);
    }

    public String getProduceStatus() {
        return produceStatus.get(Thread.currentThread().getId());
    }

    public void setProduceStatus(String produceStatusVal) {
        produceStatus.put(Thread.currentThread().getId(), produceStatusVal);
    }

    public String getActualQty() {
        return ActualQty.get(Thread.currentThread().getId());
    }

    public void setActualQty(String actualQtyVal) {
        ActualQty.put(Thread.currentThread().getId(), actualQtyVal);
    }

    public String getHarvestDate() {
        return harvestDate.get(Thread.currentThread().getId());
    }

    public void setHarvestDate(String harvestDateVal) throws FlexFrameWorkRunTimeException {
        harvestDateVal = GlobalHelpers.addMonthtoCurrentDate(2);
        harvestDate.put(Thread.currentThread().getId(), harvestDateVal);
    }

    public String  getBusinessId() {
        return businessId.get(Thread.currentThread().getId());
    }

    public void setBusinessId(String businessIdVal) {
        businessId.put(Thread.currentThread().getId(), businessIdVal);
    }

    public String getFarmerOrBusinessId() {
        return farmerOrBusinessId.get(Thread.currentThread().getId());
    }

    public void setFarmerOrBusinessId(String farmerOrBusinessIdVal) {
        farmerOrBusinessId.put(Thread.currentThread().getId(), farmerOrBusinessIdVal);
    }

    public String getProduce() {
        return produce.get(Thread.currentThread().getId());
    }

    public void setProduce(String produceVal) {
        produce.put(Thread.currentThread().getId(), produceVal);
    }

    public String getSalesOrderId() {
        return salesOrderId.get(Thread.currentThread().getId());
    }

    public void setSalesOrderId(String  salesOrderIdDetails) {
        salesOrderId.put(Thread.currentThread().getId(), salesOrderIdDetails);
    }

    public String getRetailerId() {
        return retailerId.get(Thread.currentThread().getId());
    }

    public void setRetailerId(String retailerIdDetails) {
        retailerId.put(Thread.currentThread().getId(), retailerIdDetails);
    }
}
