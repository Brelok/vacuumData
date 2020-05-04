package com.github.brelok;

import org.apache.poi.ss.usermodel.*;
import org.quartz.*;


import java.io.*;

import static com.github.brelok.MailSender.mailSender;
import static com.github.brelok.UpdateSheet.updateSheet;
import static com.github.brelok.WriteToExcel.writeToExcel;

public class Job implements ReadingWorkbook, org.quartz.Job {

    final static String AME5500 = "https://www.ceneo.pl/51503793";
    final static String AME7000 = "https://www.ceneo.pl/51503794";
    final static String MBOT500 = "https://www.ceneo.pl/73788935";
    final static String MBOT900 = "https://www.ceneo.pl/76005783";
    final static String MBOT950 = "https://www.ceneo.pl/92682776";


    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

//        XSSFWorkbook workbook = new XSSFWorkbook();
//
//        createSheet(workbook, "AME5500", AME5500);
//        createSheet(workbook, "AME7000", AME7000);
//        createSheet(workbook, "MBOT500", MBOT500);
//        createSheet(workbook, "MBOT900", MBOT900);
//        createSheet(workbook, "MBOT950", MBOT950);
//
//        try {
//            System.out.println("creating new excel file ...");
//            writeToExcel(workbook);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        try {
            Workbook readingWorkbook = ReadingWorkbook.readWorkbook();
            updateSheet(AME5500, 0, readingWorkbook);
            updateSheet(AME7000, 1, readingWorkbook);
            updateSheet(MBOT500, 2, readingWorkbook);
            updateSheet(MBOT900, 3, readingWorkbook);
            updateSheet(MBOT950, 4, readingWorkbook);

            System.out.println("updating excel file ...");
            writeToExcel(readingWorkbook);
        } catch (IOException e) {
            e.printStackTrace();
        }

        mailSender();


    }
}


