package com.DataDrivenTesting;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;

public class DataProviderDriven {

    // Each test data in data provider (each array) is used to run a separate TC.

    // However, for Excel test data using for loops and iterating through rows and columns, we can only run
    // the whole thing as a single test.

    // Hence, we need to integrate the Data Provider and Excel API

    @DataProvider(name = "DataDriven")
    public Object[][] getData() {

        // We are using Object array so that all types of variables can be accepted
        Object[][] data = {
                {"Hello", "Run", "23"},
                {"Bye", "Walk", "46"},
                {"Hey", "Jump", "73"}
        };
        return data;
    }

    private DataFormatter dataFormatter = new DataFormatter();

    @DataProvider(name = "DataDrivenWithExcel")
    public Object[][] getDataFromExcel() throws IOException {

        String path = System.getProperty("user.dir") + "\\DataProviderTestData.xlsx";
        FileInputStream fis = new FileInputStream(path);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);

        // indexing starts from 0 for sheets, rows, columns
        XSSFSheet sheet = workbook.getSheetAt(0);

        // physical number of rows is the number of rows you can see
        int rowCount = sheet.getPhysicalNumberOfRows();
        int columnCount = sheet.getRow(0).getLastCellNum();

        // rowCount - 1 for the header
        Object[][] data = new Object[rowCount - 1][columnCount];

        for (int i = 0; i < rowCount - 1; i++) {
            XSSFRow row = sheet.getRow(i + 1);
            for (int j = 0; j < columnCount; j++) {
                XSSFCell cell = row.getCell(j);

                // We need to store as String, hence DatFormatter class
                data[i][j] = dataFormatter.formatCellValue(cell);
            }
        }

        return data;
    }

    @Test(dataProvider = "DataDrivenWithExcel")
    public void testCase(String greeting, String action, String number) {
        System.out.println(greeting + " " + action + " " + number);
    }

}
