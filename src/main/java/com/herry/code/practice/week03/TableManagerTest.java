package com.herry.code.practice.week03;
import com.google.errorprone.annotations.Var;
import net.sf.cglib.proxy.MethodInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author herry
 */
public class TableManagerTest {
    private static final Logger logger = LoggerFactory.getLogger(TableManagerTest.class);

    public static void main(String[] args) {
        TableManager tableManager = new TableManager();
        tableManager.writeExcel("table02.xlsx","dddd");
//        List<TableManager.RowData> rowData = tableManager.readExcel("table01.xlsx");
        MyMethodInterceptor myMethodInterceptor = new MyMethodInterceptor(tableManager);
        TableManager proxyTableManager = (TableManager)myMethodInterceptor.getProxy();
        List<TableManager.RowData> rowData = proxyTableManager.readExcel("table01.xlsx");
        rowData.forEach(System.out::println);

    }
}
