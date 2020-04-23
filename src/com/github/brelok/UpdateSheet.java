package com.github.brelok;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.github.brelok.CheckTheMinimumValue.checkTheMinimumValue;
import static com.github.brelok.Connector.getMap;
import static com.github.brelok.GetTime.getTime;

public class UpdateSheet {

    public static Workbook updateSheet(String url, int numberOfSheet, Workbook workbook) throws IOException {

        //data
        Map<String, String> map = getMap(url);
        List<String> namesShop = new ArrayList<>(map.keySet());
        List<String> prices = getPricesList(map);


        Sheet sheet = workbook.getSheetAt(numberOfSheet);
        Row firstRow = sheet.getRow(0);

        //other cells in first row
        for (int i = 1; i <= namesShop.size(); i++) {
            Cell cell = firstRow.createCell(i);
            cell.setCellValue(namesShop.get(i - 1));
        }

        //other row
        Row row = sheet.createRow(1);

        addValueInCells(row, prices);

        checkTheMinimumValue(workbook,sheet);

        sheet.shiftRows(1,sheet.getLastRowNum(),1);

        return workbook;
    }

    public static List<String> getPricesList(Map map) {
        Set<Map.Entry<String, String>> keyAndValueFromDevice = map.entrySet();
        List<String> prices = new ArrayList<>();
        for (Map.Entry<String, String> entry : keyAndValueFromDevice) {
            prices.add(entry.getValue());
        }
        return prices;
    }

    public static void addValueInCells(Row row, List<String> prices) {
        //add first cell with current time
        Cell cell = row.createCell(0);
        cell.setCellValue(getTime());

        //add new cells with prices
        for (int i = 1; i <= prices.size(); i++) {
            Cell cell1 = row.createCell(i);
            cell1.setCellValue(prices.get(i - 1));
        }
    }
}
