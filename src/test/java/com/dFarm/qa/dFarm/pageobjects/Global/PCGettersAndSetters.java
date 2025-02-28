package com.dFarm.qa.dFarm.pageobjects.Global;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

public class PCGettersAndSetters {

    Map<Object, String> CCId = new HashMap<>();
    Map<Object, String> farmerId = new HashMap<>();

    Map<Object, String> produce = new HashMap<>();
    Map<Object, String> variety = new HashMap<>();
    Map<Object, String> type = new HashMap<>();
    Map<Object, String> CCCollectionDat = new HashMap<>();
    Map<Object, String> CCCount = new HashMap<>();
    Map<Object, String> CCWeight = new HashMap<>();

    Map<Object, String> mpWeightUnit = new HashMap<>();
    Map<Object, String> mpPriceStatus = new HashMap<>();
    Map<Object, String> mpGrade = new HashMap<>();
    Map<Object, String> mpPrice = new HashMap<>();
    Map<Object, String> mpOtherPrice = new HashMap<>();
    Map<Object, String> mpTotalPrice = new HashMap<>();
    Map<Object, String> mpSize = new HashMap<>();



    public String getCCId() {
        return CCId.get(Thread.currentThread().getId());
    }

    public void setCCId(String CCIdDetails) {
        CCId.put(Thread.currentThread().getId(), CCIdDetails);
    }

    public String getFarmerId() {
        return farmerId.get(Thread.currentThread().getId());
    }

    public void setFarmerId(String farmerIdDetails) {
        farmerId.put(Thread.currentThread().getId(), farmerIdDetails);
    }

    public String getProduce() {
        return produce.get(Thread.currentThread().getId());
    }

    public void setProduce(String produceDetails) {
        produce.put(Thread.currentThread().getId(), produceDetails);
    }

    public String getVariety() {
        return variety.get(Thread.currentThread().getId());
    }

    public void setVariety(String varietyDetails) {
        variety.put(Thread.currentThread().getId(), varietyDetails);
    }

    public String getType() {
        return type.get(Thread.currentThread().getId());
    }

    public void setType(String typeDetails) {
        type.put(Thread.currentThread().getId(), typeDetails);
    }

    public String getCCCollectionDat() {
        return CCCollectionDat.get(Thread.currentThread().getId());
    }

    public void setCCCollectionDat(String CCCollectionDetails) {
        CCCollectionDat.put(Thread.currentThread().getId(), CCCollectionDetails);
    }

    public String getCCCount() {
        return CCCount.get(Thread.currentThread().getId());
    }

    public void setCCCount(String CCCountDetails) {
        CCCount.put(Thread.currentThread().getId(), CCCountDetails);
    }

    public String getCCWeight() {
        return CCWeight.get(Thread.currentThread().getId());
    }

    public void setCCWeight(String CCWeightDetails) {
        CCWeight.put(Thread.currentThread().getId(), CCWeightDetails);
    }

    public Map<Object, String> getMpWeightUnit() {
        return mpWeightUnit;
    }

    public void setMpWeightUnit(String weightUnit) {
        mpWeightUnit.put(Thread.currentThread().getId(), weightUnit);
    }

    public String  getMpPriceStatus() {
        return mpPriceStatus.get(Thread.currentThread().getId());
    }

    public void setMpPriceStatus(String PriceStatus) {
        this.mpPriceStatus.put(Thread.currentThread().getId(), PriceStatus);
    }

    public String  getMpGrade() {
        return mpGrade.get(Thread.currentThread().getId());
    }

    public void setMpGrade(String Grade) {
        this.mpGrade.put(Thread.currentThread().getId(), Grade);
    }

    public String getMpPrice() {
        return mpPrice.get(Thread.currentThread().getId());
    }

    public void setMpPrice(String Price) {
        this.mpPrice.put(Thread.currentThread().getId(), Price);
    }

    public String getMpOtherPrice() {
        return mpOtherPrice.get(Thread.currentThread().getId());
    }

    public void setMpOtherPrice(String OtherPrice) {
        this.mpOtherPrice.get(Thread.currentThread().getId());
    }

    public String getMpTotalPrice() {
        return mpTotalPrice.get(Thread.currentThread().getId());
    }

    public void setMpTotalPrice(String TotalPrice) {
        this.mpTotalPrice.put(Thread.currentThread().getId(), TotalPrice);
    }

    public String getMpSize() {
        return mpSize.get(Thread.currentThread().getId());
    }

    public void setMpSize(String Size) {
        this.mpSize.put(Thread.currentThread().getId(), Size);
    }
}
