package com.github.brelok;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.github.brelok.CreatorSheet.*;
import static com.github.brelok.ReadWorkbook.readWorkbook;
import static com.github.brelok.UpdateSheet.updateSheet;
import static com.github.brelok.WriteToExcel.writeToExcel;

public class Main {

    final static String AME5500 = "https://www.ceneo.pl/51503793";
    final static String AME7000 = "https://www.ceneo.pl/51503794";
    final static String MBOT500 = "https://www.ceneo.pl/73788935";
    final static String MBOT900 = "https://www.ceneo.pl/76005783";
    final static String MBOT950 = "https://www.ceneo.pl/92682776";

    public static void main(String[] args) {

        XSSFWorkbook workbook = new XSSFWorkbook();

        createSheet(workbook, AME5500, "AME5500");
        createSheet(workbook, AME7000, "AME7000");
        createSheet(workbook, MBOT500, "MBOT500");
        createSheet(workbook, MBOT900, "MBOT900");
        createSheet(workbook, MBOT950, "MBOT950");

        try {
            writeToExcel(workbook);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Thread.sleep(3 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            Workbook readingWorkbook = readWorkbook();
            updateSheet(AME5500, 0, readingWorkbook);
            updateSheet(AME7000, 1, readingWorkbook);
            updateSheet(MBOT500, 2, readingWorkbook);
            updateSheet(MBOT900, 3, readingWorkbook);
            updateSheet(MBOT950, 4, readingWorkbook);

            FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
            DataFormatter formatter = new DataFormatter();

            Sheet sheet1 = readingWorkbook.getSheetAt(0);
            CellStyle background = sheet1.getWorkbook().createCellStyle();
            background.setFillBackgroundColor(IndexedColors.RED.getIndex());

            double checkValue = 100000;

            for (Sheet sheet : workbook) {

                for (int i = 1; i <= sheet.getLastRowNum(); i++) {

                    Row row = sheet.getRow(i);
                    List<Double> listPrices = new ArrayList<>();

                    for (int j = 1; j <= row.getLastCellNum() - 1; j++) {

                        Cell cell = row.getCell(j);
                        String stringData = formatter.formatCellValue(cell, evaluator);
                        Double integerData = Double.parseDouble(stringData);
                        listPrices.add(integerData);
                    }

                    for (int j = 0; j < listPrices.size(); j++) {
                        if (listPrices.get(j) < checkValue) {
                            checkValue = listPrices.get(j);
                        }
                    }

                    for (int j = 1; j <= row.getLastCellNum() - 1; j++) {

                        Cell cell = row.getCell(j);
                        String stringData = formatter.formatCellValue(cell, evaluator);
                        Double integerData = Double.parseDouble(stringData);
                        if(integerData == checkValue){
                            cell.setCellStyle(background);
                        }
                    }

                }
            }

            writeToExcel(readingWorkbook);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}


