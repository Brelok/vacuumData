package com.github.brelok;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.github.brelok.CheckTheMinimumValue.checkTheMinimumValue;
import static com.github.brelok.Connector.getMap;
import static com.github.brelok.CreatorSheet.getPricesList;
import static com.github.brelok.GetTime.getTime;

public class UpdateSheet {


    public static Workbook updateSheet(String url, int numberOfSheet, Workbook workbook) throws IOException {

        //data
        Map<String, String> map = getMap(url);
        List<String> namesShop = new ArrayList<>(map.keySet());
        List<String> prices = getPricesList(map);

        Sheet sheet = workbook.getSheetAt(numberOfSheet);

        Row firstRow = sheet.getRow(0);


        //create next row
        Row row = sheet.createRow(sheet.getLastRowNum() + 1);

        //first cell inrow
        Cell firstCell = row.createCell(0);
        firstCell.setCellValue(getTime());


        List<String> existingNamesShop = getExistingNamesShop(sheet);

        List<String> uniqueNewNames = getUniqueFromNewNames(namesShop, existingNamesShop);


        //if new names is more then existing
        if (namesShop.size() > existingNamesShop.size()) {

            //add new names
            for (int i = 0; i < uniqueNewNames.size(); i++) {
                Cell cell = firstRow.createCell(firstRow.getLastCellNum());
                cell.setCellValue(uniqueNewNames.get(i));
            }

            //read existing name after add
            List<String> existingNameAfterUpdate = readExistingNameAfterUpdate(firstRow);

            //add price to cells
            addValueInCells(existingNameAfterUpdate, namesShop, prices, row);

        }


        // if new names size is the same like existing
        if (namesShop.size() == existingNamesShop.size()) {

            //check if is new names shop
            if (uniqueNewNames.size() > 0) {
                for (int i = 0; i < uniqueNewNames.size(); i++) {
                    Cell cell = firstRow.createCell(firstRow.getLastCellNum());
                    cell.setCellValue(uniqueNewNames.get(i));
                }
            }

            List<String> existingNameAfterUpdate = readExistingNameAfterUpdate(firstRow);

            addValueInCells(existingNameAfterUpdate, namesShop, prices, row);
        }

        //if new names size is more then existing
        if (namesShop.size() < existingNamesShop.size()) {

            addValueInCells(existingNamesShop, namesShop, prices, row);

        }
        checkTheMinimumValue(workbook,row);

        return workbook;

    }

    public static List<String> getExistingNamesShop(Sheet sheet) {
        List<String> list = new ArrayList<>();
        Row firstRow = sheet.getRow(0);

        for (int i = 1; i < firstRow.getLastCellNum(); i++) {
            list.add(firstRow.getCell(i).toString());
        }
        return list;
    }


    public static List<String> getUniqueFromNewNames(List<String> newNames, List<String> existingNames) {
        List<String> unique = new ArrayList<>(newNames);
        unique.removeAll(existingNames);

        return unique;
    }

    public static List<String> readExistingNameAfterUpdate(Row row) {
        List<String> existingNameAfterUpdate = new ArrayList<>();
        for (Cell cell : row) {
            existingNameAfterUpdate.add(cell.toString());
        }
        existingNameAfterUpdate.remove(existingNameAfterUpdate.get(0));
        return existingNameAfterUpdate;
    }

    public static void addValueInCells(List<String> existingNamesAfterUpdate, List<String> newNames, List<String> newPrices, Row row) {
        for (int i = 0; i < existingNamesAfterUpdate.size(); i++) {
            Cell cell = row.createCell(i + 1);

            for (int j = 0; j < newNames.size(); j++) {
                if (newNames.get(j).equals(existingNamesAfterUpdate.get(i))) {
                    cell.setCellValue(newPrices.get(j));
                    break;
                }
            }

        }
    }

}
