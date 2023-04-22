package com.DataDrivenTesting;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class ExcelDriven {

    public ArrayList getData(String testCaseName) throws IOException {

        // Create XSSFWorkBook class
        // It requires the access to read a file rather than the file path itself
        String path = System.getProperty("user.dir") + "\\TestData.xlsx";
        ArrayList data = new ArrayList<>();
        FileInputStream fis = new FileInputStream(path);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        System.out.println(workbook.getNumberOfSheets());

        // Go to the desired sheet
        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            if (workbook.getSheetName(i).equalsIgnoreCase("Sheet1")) {
                XSSFSheet sheet = workbook.getSheetAt(i);

                // Go to Test Case Row
                Iterator<Row> rows = sheet.iterator();
                Row firstRow = rows.next();     // now at 1st row
                Iterator<Cell> cells = firstRow.cellIterator();
                int k = 0;
                int testCaseColumn = 0;     // to capture the required Test Case column
                while (cells.hasNext()) {
                    Cell cell = cells.next();
                    if (cell.getStringCellValue().equalsIgnoreCase("Test Case")) {
                        // we have reached the "Test Case" cell and column
                        testCaseColumn = k;
                        break;
                    }
                    k++;
                }

                // Go to required Test Case Row
                while (rows.hasNext()) {        // starting from second row onwards
                    Row row = rows.next();
                    if (row.getCell(testCaseColumn).getStringCellValue().equalsIgnoreCase("Recovery Login")) {

                        // Pull all test data from the required row
                        Iterator<Cell> requiredCells = row.cellIterator();
                        requiredCells.next(); // move to where the test data starts (at cell 2)
                        while (requiredCells.hasNext()) {
                            Cell cell = requiredCells.next();
                            if (cell.getCellType() == CellType.STRING)
                                data.add(cell.getStringCellValue());
                            else
                                // method of apache poi to convert number to string
                                data.add(NumberToTextConverter.toText(cell.getNumericCellValue()));
                        }
                        break; // we don't need to check other rows
                    }
                }
                break; // we don't need to check other sheets

            }
        }
        return data;
    }

}
