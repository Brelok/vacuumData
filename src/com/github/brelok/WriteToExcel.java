package com.github.brelok;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;

import static com.github.brelok.Connector.getMap;
import static com.github.brelok.CreatorSheet.createSheet;

public class WriteToExcel {

    final static String AME5500 = "https://www.ceneo.pl/51503793";
    final static String AME7000 = "https://www.ceneo.pl/51503794";
    final static String MBOT500 = "https://www.ceneo.pl/73788935";
    final static String MBOT900 = "https://www.ceneo.pl/76005783";
    final static String MBOT950 = "https://www.ceneo.pl/92682776";

    public static void main(String[] args) {


        XSSFWorkbook workbook = new XSSFWorkbook();

        createSheet(workbook, AME5500, "AME5500");
        createSheet(workbook, AME7000, "AME7000");
        createSheet(workbook, MBOT500, "MBOT500");
        createSheet(workbook, MBOT900, "MBOT900");
        createSheet(workbook, MBOT950, "MBOT950");

        try {
            //Write the workbook in file system
            FileOutputStream out = new FileOutputStream(new File("excel.xls"));
            workbook.write(out);
            out.close();
            System.out.println("it's done");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}


