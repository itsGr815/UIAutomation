package com.dFarm.qa.dFarm.util.datahandler;


import java.util.LinkedHashMap;
import java.util.Map;

public class ExcelData implements HandleTestData {

    @Override
    public Map<String, Object> readTestData() {
        ExcelMaps excelMaps = new ExcelMaps();
        return new LinkedHashMap<>(excelMaps.readAllExcelData().getTestDataMap());
    }
}
