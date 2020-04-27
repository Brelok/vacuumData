package com.github.brelok;

import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class WriteToExcel implements GettingTime {

    public static void writeToExcel(Workbook workbook) throws IOException {

        //Write the workbook in file system
        FileOutputStream out = new FileOutputStream(new File("excel.xls"));
        workbook.write(out);
        out.close();
        System.out.println("finished write to file, time: " + GettingTime.getTime());

    }
}
