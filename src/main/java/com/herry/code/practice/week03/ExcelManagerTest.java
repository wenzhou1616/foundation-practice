package com.herry.code.practice.week03;

import java.util.List;

/**
 * 测试表格操作
 * @author herry
 */
public class ExcelManagerTest {

    public static void main(String[] args) {
        ExcelManager excelManager = new ExcelManager();
        excelManager.writeExcel("table01.xlsx","dddd");

        // 直接读取表格数据
//        List<TableManager.RowData> rowData = tableManager.readExcel("table01.xlsx");

        // 用动态代理读取表格数据
        MyMethodInterceptor myMethodInterceptor = new MyMethodInterceptor(excelManager);
        ExcelManager proxyExcelManager = (ExcelManager)myMethodInterceptor.getProxy();
        List<ExcelManager.RowData> rowData = proxyExcelManager.readExcel("table01.xlsx");

        rowData.forEach(System.out::println);

    }
}
