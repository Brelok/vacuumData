package com.github.brelok;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.github.brelok.Connector.getMap;
import static com.github.brelok.GetTime.getTime;

public class CreatorSheet {

    public static void createSheet(XSSFWorkbook workbook, String url, String name) {

        Map<String, String> map = getMap(url);

        List<String> namesShop = new ArrayList<>(map.keySet());

        XSSFSheet sheet = workbook.createSheet(name);

        Row firstRow = sheet.createRow(0);

        //first cell in first row
        Cell cellFirst = firstRow.createCell(0);
        cellFirst.setCellValue("Time/Shop");

        //other cells in first row
        for (int i = 1; i <= namesShop.size(); i++) {
            Cell cell = firstRow.createCell(i);
            cell.setCellValue(namesShop.get(i - 1));
        }

        //create list with prices
        List<String> prices = getPricesList(map);

        //add next row with current time
        Row row = sheet.createRow(1);

        addValueinCells(row, prices);

    }

    public static List<String> getPricesList(Map map) {
        Set<Map.Entry<String, String>> keyAndValueFromDevice = map.entrySet();
        List<String> prices = new ArrayList<>();
        for (Map.Entry<String, String> entry : keyAndValueFromDevice) {
            prices.add(entry.getValue());
        }
        return prices;
    }

    public static void addValueinCells(Row row, List<String> prices) {
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
