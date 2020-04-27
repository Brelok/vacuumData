package com.github.brelok;

import org.apache.poi.ss.usermodel.*;

import java.util.ArrayList;
import java.util.List;

public class CheckTheMinimumValue {

    public static void checkTheMinimumValue(Workbook workbook, Sheet sheet) {


        CellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.RED.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());

        CellStyle styleZero = workbook.createCellStyle();
        styleZero.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
        styleZero.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        styleZero.setBorderBottom(BorderStyle.THIN);
        styleZero.setBorderLeft(BorderStyle.THIN);
        styleZero.setBorderRight(BorderStyle.THIN);
        styleZero.setBorderTop(BorderStyle.THIN);
        styleZero.setBottomBorderColor(IndexedColors.BLACK.getIndex());


        double checkValue = 100000;

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {

            Row row = sheet.getRow(i);
            List<Double> listPrices = new ArrayList<>();

            for (int j = 1; j <= row.getLastCellNum() - 1; j++) {

                Cell cell = row.getCell(j);

                    listPrices.add(getDouble(workbook, cell));

            }

            for (int j = 0; j < listPrices.size(); j++) {
                if (listPrices.get(j) < checkValue) {
                    checkValue = listPrices.get(j);
                }
            }

            for (int j = 1; j <= row.getLastCellNum() - 1; j++) {

                Cell cell = row.getCell(j);
                if (getDouble(workbook, cell) == checkValue) {
                    cell.setCellStyle(style);

                }
            }
        }
    }

    public static Double getDouble(Workbook workbook, Cell cell) {
        FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
        DataFormatter formatter = new DataFormatter();

        String stringData = formatter.formatCellValue(cell, evaluator);
        return Double.parseDouble(stringData);
    }
}
