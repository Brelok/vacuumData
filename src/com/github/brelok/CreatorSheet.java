package com.github.brelok;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CreatorSheet {

    public static void createSheet(XSSFWorkbook workbook, String name) {


        XSSFSheet sheet = workbook.createSheet(name);

        Row firstRow = sheet.createRow(0);

        //first cell in first row
        Cell cellFirst = firstRow.createCell(0);
        cellFirst.setCellValue("Time/Shop");

    }

}
