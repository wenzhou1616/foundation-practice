package com.herry.code.practice;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;

/**
 * @author herry
 */
public class TableManagerTest {
    private static final Logger logger = LoggerFactory.getLogger(TableManagerTest.class);

    public static void main(String[] args) {
        TableManager tableManager = new TableManager();
//        List<TableManager.RowData> rowData = tableManager.readExcel("table01.xlsx");
        MyMethodInterceptor myMethodInterceptor = new MyMethodInterceptor(tableManager);
        TableManager proxyTableManager = (TableManager)myMethodInterceptor.getProxy();
        List<TableManager.RowData> rowData = proxyTableManager.readExcel("table01.xlsx");
        for (TableManager.RowData rowDatum : rowData) {
            System.out.println(rowDatum);
        }

    }
}
