package com.github.brelok;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static com.github.brelok.CheckTheMinimumValue.checkTheMinimumValue;
import static com.github.brelok.Connector.getMap;
import static com.github.brelok.CreatorSheet.addValueinCells;
import static com.github.brelok.CreatorSheet.getPricesList;

public class UpdateSheet {

    public static Workbook updateSheet(String url, int numberOfSheet, Workbook workbook) throws IOException {

            Sheet sheet = workbook.getSheetAt(numberOfSheet);

            Map<String, String> map = getMap(url);

            List<String> prices = getPricesList(map);

            Row row1 = sheet.createRow(sheet.getLastRowNum() + 1);

            addValueinCells(row1, prices);

            checkTheMinimumValue(workbook, sheet);

            return workbook;

    }
}
