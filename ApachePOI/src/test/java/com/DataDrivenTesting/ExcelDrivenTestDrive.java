package com.DataDrivenTesting;

import java.io.IOException;

import java.util.ArrayList;

public class ExcelDrivenTestDrive {

    public static void main(String[] args) throws IOException {

        ExcelDriven excelUtility = new ExcelDriven();
        ArrayList testData = excelUtility.getData("Main Login");
        System.out.println(testData.get(0));
        System.out.println(testData.get(1));
        System.out.println(testData.get(2));
    }
}
