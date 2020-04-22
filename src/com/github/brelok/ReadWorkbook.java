package com.github.brelok;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.FileInputStream;
import java.io.IOException;

public class ReadWorkbook {

    public static Workbook readWorkbook() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("excel.xls");
        Workbook workbook = WorkbookFactory.create((fileInputStream));
        return workbook;
    }
}
