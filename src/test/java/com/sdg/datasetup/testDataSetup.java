package com.sdg.datasetup;

import org.apache.poi.ss.usermodel.*;
import java.io.File;
import java.io.FileInputStream;
import java.util.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class testDataSetup {

    public static String testDataFilePath=System.getProperty("user.dir")+"/src/test/resources/testdata/testdata.xlsx";
    public static String workSheetName="data";
    public static HashMap<String, String> testdata = new HashMap<>();
    public static Set<String> testDataColumnNames = new LinkedHashSet<>();

    @Test
    public static  HashMap<String, String>retrieveTestData(String currentTestCase) {
        List<HashMap<String, String>> mapList = getTestData(testDataFilePath, workSheetName);
        for (HashMap<String, String> rowMap : mapList) {
            if (rowMap.get("Test Name").trim().equalsIgnoreCase(currentTestCase)) {
                testdata = rowMap;
                break;
            }
        }
        return testdata;
    }

    public static List<HashMap<String, String>> getTestData(String testDataFilePath, String workSheetName)
    {
        List<HashMap<String, String>> mapList = new ArrayList<>();
        HashMap<String, String> map;
        try {
            FileInputStream file = new FileInputStream(new File(testDataFilePath));
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheet(workSheetName);
            Row row0 = sheet.getRow(0);
            for (int r = 1; r <= sheet.getLastRowNum(); r++) {
                Row row = sheet.getRow(r);
                if (row != null) {
                    map = new HashMap<>();
                    for (int c = 0; c < row.getLastCellNum(); c++) {
                        Cell cell0 = row0.getCell(c);
                        if (cell0 != null)
                            cell0.setCellType(CellType.STRING);
                        Cell cell = row.getCell(c);
                        if (cell != null) {
                            cell.setCellType(CellType.STRING);
                            map.put(cell0.getStringCellValue(), cell.getStringCellValue());
                            if (r == 1 && !workSheetName.equalsIgnoreCase("Config")) {
                                if (workSheetName.equalsIgnoreCase("TestData")) {
                                    testDataColumnNames.add(cell0.getStringCellValue());
                                }
                            }
                        }
                    }
                    mapList.add(map);
                }
            }
            workbook.close();
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapList;
    }
}
