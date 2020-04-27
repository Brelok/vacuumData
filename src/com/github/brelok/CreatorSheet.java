package com.github.brelok;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.github.brelok.CheckTheMinimumValue.checkTheMinimumValue;
import static com.github.brelok.Connector.getMap;
import static com.github.brelok.GetTime.getTime;

public class CreatorSheet {

    public static void createSheet(XSSFWorkbook workbook, String name, String url) {

        //data
        Map<String, String> map = getMap(url);
        List<String> namesShop = new ArrayList<>(map.keySet());
        List<String> prices = getPricesList(map);

        XSSFSheet sheet = workbook.createSheet(name);

        Row firstRow = sheet.createRow(0);

        //first cell in first row
        Cell cellFirst = firstRow.createCell(0);
        cellFirst.setCellValue("Time/Shop");

        //other cells in first row
        addNamesShopInFirstRow(namesShop, firstRow);

        Row row = sheet.createRow(1);

        addValueInCellsInFirstRow(row, prices);

        checkTheMinimumValue(workbook, sheet);


    }

    public static List<String> getPricesList(Map map) {
        Set<Map.Entry<String, String>> keyAndValueFromDevice = map.entrySet();
        List<String> prices = new ArrayList<>();
        for (Map.Entry<String, String> entry : keyAndValueFromDevice) {
            prices.add(entry.getValue());
        }
        return prices;
    }

    public static void addNamesShopInFirstRow(List<String> list, Row row) {
        for (int i = 1; i <= list.size(); i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(list.get(i - 1));
        }
    }

    public static void addValueInCellsInFirstRow(Row row, List<String> prices) {
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
