package com.github.brelok;

import org.apache.poi.ss.usermodel.*;

import java.util.ArrayList;
import java.util.List;

public interface CheckingMinimumValue {

    static void checkTheMinimumValue(Workbook workbook, Row row) {

        CellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.RED.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());

        double checkValue = 1000000;

        List<Double> listPrices = new ArrayList<>();

        for (int i = 1; i < row.getLastCellNum() - 1; i++) {
            Cell cell = row.getCell(i);
            if (cell.getCellType() != CellType.BLANK) {
                listPrices.add(getDouble(workbook, cell));
            }
        }

        for (int i = 0; i < listPrices.size(); i++) {
            if (listPrices.get(i) < checkValue) {
                checkValue = listPrices.get(i);
            }
        }
        for (int i = 1; i < row.getLastCellNum(); i++) {
            Cell cell = row.getCell(i);
            if (cell.getCellType() != CellType.BLANK) {
                if (getDouble(workbook, cell) == checkValue) {
                    cell.setCellStyle(style);
                }
            }
        }
    }

    static Double getDouble(Workbook workbook, Cell cell) {
        FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
        DataFormatter formatter = new DataFormatter();

        String stringData = formatter.formatCellValue(cell, evaluator);
        return Double.parseDouble(stringData);
    }

}
