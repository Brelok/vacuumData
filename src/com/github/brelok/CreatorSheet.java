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

public class CreatorSheet {

    public static void createSheet (XSSFWorkbook workbook, String url, String name){

        Map<String, String> map = getMap(url);

        List<String> namesShop = new ArrayList<>(map.keySet());

        XSSFSheet sheet = workbook.createSheet(name);

        Row firstRow = sheet.createRow(0);
        for (int i = 1; i <= namesShop.size(); i++) {
            Cell cell = firstRow.createCell(i);
            cell.setCellValue(namesShop.get(i - 1));

        }

        //create list with prices
        Set<Map.Entry<String, String>> keyAndValueFromDevice = map.entrySet();
        List<String> prices = new ArrayList<>();
        for (Map.Entry<String, String> entry : keyAndValueFromDevice) {
            int i = 0;
            prices.add(entry.getValue());
        }

        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formatDateTime = time.format(formatter);

        //add next row with current time
        Row row = sheet.createRow(1);

        //add first cell with current time
        Cell cell = row.createCell(0);
        cell.setCellValue(formatDateTime);

        //add new cells with prices
        for (int i = 1; i <= prices.size() ; i++) {
            Cell cell1 = row.createCell(i);
            cell1.setCellValue(prices.get(i-1));
        }

    }

}
