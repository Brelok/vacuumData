package com.github.brelok;

import com.sun.corba.se.spi.orbutil.threadpool.Work;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

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

            return workbook;

    }
}
