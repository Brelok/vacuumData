package com.github.brelok;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.FileInputStream;
import java.io.IOException;

public class ReadingWorkbook {

    public static Workbook readWorkbook() throws IOException {
        FileInputStream inputStream = new FileInputStream("excel.xls");
//        InputStream inputStream = getClass().getResourceAsStream("/excel.xls");
        Workbook workbook = WorkbookFactory.create((inputStream));
        return workbook;
    }
}
