package com.dFarm.qa.dFarm.util.datahandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.dFarm.qa.dFarm.util.execution.AllDataHolder;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.dFarm.qa.dFarm.constants.TestDataConstants;



public class ExcelMaps implements TestDataConstants {

    private static final Logger logger = LoggerFactory.getLogger(ExcelMaps.class);
    public IHandleTestData hddfx;

    public ExcelMaps() {
        hddfx = new HandleDelimitedDataFromXLS();
    }

    public void readSheet(File path) {
        Map<String, Map<String, ArrayList<String>>> map = new LinkedHashMap<>();
        Map<String, ArrayList<String>> mapSheet;
        List<String> allHeaders;
        List<String> allColumnData;

        try {
            XSSFWorkbook workbook = (XSSFWorkbook) WorkbookFactory.create(new FileInputStream(path));

            for (XSSFSheet aWorkBook : workbook){
                int indexOfTestCaseId = TETSCASEID_INDEX_ERROR;
                mapSheet = new LinkedHashMap<>();
                String key;
                ArrayList<String> list;

                p("Processing sheet: " + aWorkBook.toString());
                Iterator<Row> rows = aWorkBook.rowIterator();
                int rowNum = 0;
                String cellValue;
                List<String> columnHeaders = new ArrayList<>();
                while (rows.hasNext()){
                    allHeaders = new ArrayList<>();
                    allColumnData = new ArrayList<>();
                    String testCaseId = null;
                    key = "";
                    boolean hasValue = false;
                    list = new ArrayList<>();
                    XSSFRow row = (XSSFRow) rows.next();
                    Iterator<Cell> column = row.cellIterator();
                    int colNum = 0;
                    while (column.hasNext()){
                        XSSFCell cell = (XSSFCell) column.next();
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        cellValue = cell.getStringCellValue().trim().replaceAll(NONBREAKABLESPACE, "");
                        if (rowNum !=0){
                            int indexOfKeyColumn = columnHeaders.indexOf(INDEXCOLUMNFORUNIQUEID);
                            allColumnData.add(cellValue);
                            if (colNum == indexOfKeyColumn){
                                key = cellValue;
                                testCaseId = cellValue;
                            }else {
                                list.add(cellValue);
                            }
                            hasValue = true;
                        }else {
                            try {
                                if (cellValue == null || cellValue.isEmpty()){
                                    logger.debug("Column value can't be empty . Remove the formatting for the unused columns.");
                                }else {
                                    allHeaders.add(cellValue);
                                    columnHeaders.add(cellValue);
                                    if (cellValue.equalsIgnoreCase(INDEXCOLUMNFORUNIQUEID)){
                                        indexOfTestCaseId = colNum;
                                    }
                                    if (!(colNum == 0 || colNum == 1)){
                                        key = "headers";
                                        list.add(cellValue);
                                    }
                                }
                            }catch (Exception e){
                                logger.debug("Column value can't be empty . Remove the formatting for the unused columns");
                            }
                            hasValue = true;
                        }
                        colNum++;
                    }
                    if (indexOfTestCaseId == TETSCASEID_INDEX_ERROR){
                        p("EARNING: Could not find a column designated as a test case id: ");
                    }else {
                        if (rowNum == 0){
                            hddfx.handleTestDataListHeaders(aWorkBook.getSheetName(), allHeaders);
                        }else {
                            hddfx.handleTestDataAsList(aWorkBook.getSheetName(), testCaseId, allColumnData);
                        }
                    }
                    if (hasValue){
                        mapSheet.put(key, list);
                        rowNum++;
                    }
                }
                map.put(aWorkBook.getSheetName(), mapSheet);
            }
        }catch (Exception e){
            logger.error("Unable to read the Test Data sheet : " + path);
        }

    }

    public IHandleTestData readAllExcelData() {
        try {
            for (Entry<File, InputStream> is : getAllExcelFile().entrySet()) {
                logger.debug(String.format("Parsing %s to Excel Map", is.getKey()));
                readSheet(is.getKey());
            }
        } catch (Exception e) {
            logger.error("Unable to Parse Data from Excel to Map : " + e);
        }
        return hddfx;
    }

    public Map<File, InputStream> getAllExcelFile() {
        Map<File, InputStream> filenames = new LinkedHashMap<>();
        Resource[] resources;
        try {
            String xlsPath = "exceldata";
            PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            if (AllDataHolder.getExelPath().isEmpty() || AllDataHolder.getExelPath() == null) {
                resources = resolver.getResources("classpath:exceldata/*.xlsx");
            } else if (AllDataHolder.getExelPath().equalsIgnoreCase("NA")) {
                resources = null;
            } else {
                resources = resolver.getResources("classpath:exceldata/" + AllDataHolder.getExelPath());
            }
            for (Resource resource : resources) {
                filenames.put(resource.getFile(), resource.getInputStream());
            }
        } catch (Exception e) {
            logger.error("Unable to find Excel files from folder" + e);
        }
        System.out.println(filenames);
        return filenames;
    }


    private static void p(String s) {
        logger.debug(s);
    }
}
