package com.herry.code;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author herry
 */
public class Test {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, IOException {
        // 指定路径生成文件
        File file = new File("C:\\Users\\Administrator\\Desktop\\666\\tmp\\POI\\table01.xlsx.xls");
        OutputStream outputStream = new FileOutputStream(file);
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Sheet1");
        HSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue("id");
        row.createCell(1).setCellValue("订单号");
        row.createCell(2).setCellValue("下单时间");
        row.createCell(3).setCellValue("个数");
        row.createCell(4).setCellValue("单价");
        row.createCell(5).setCellValue("订单金额");
        // 设置行的高度
        row.setHeightInPoints(30);

        HSSFRow row1 = sheet.createRow(1);
        row1.createCell(0).setCellValue("1");
        row1.createCell(1).setCellValue("NO00001");

        // 创建单元格样式
        HSSFCellStyle cellStyle2 = workbook.createCellStyle();
        HSSFCreationHelper creationHelper = workbook.getCreationHelper();
        // 设置单元格的日期格式
        cellStyle2.setDataFormat(creationHelper.createDataFormat().getFormat("yyyy-MM-dd HH:mm:ss"));
        // 设置列的宽度
        sheet.setColumnWidth(2, 20 * 256);
        // 在行中创建单元格
        HSSFCell cell2 = row1.createCell(2);
        // 设置单元格样式
        cell2.setCellStyle(cellStyle2);
        // 给单元格赋值日期
        cell2.setCellValue(new Date());
        // 行中的索引为3的列设置值
        row1.createCell(3).setCellValue(2);

        // 单元格设置保留两位小数
        HSSFCellStyle cellStyle3 = workbook.createCellStyle();
        cellStyle3.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00"));
        HSSFCell cell4 = row1.createCell(4);
        cell4.setCellStyle(cellStyle3);
        cell4.setCellValue(29.5);

        // 货币格式化
        HSSFCellStyle cellStyle4 = workbook.createCellStyle();
        HSSFFont font = workbook.createFont();
        font.setFontName("华文行楷");
        font.setFontHeightInPoints((short)15);
        font.setColor(HSSFColor.HSSFColorPredefined.RED.getIndex());
        cellStyle4.setFont(font);

        HSSFCell cell5 = row1.createCell(5);
        // 设置计算公式
        cell5.setCellFormula("D2*E2");

        // 获取计算公式的值
        HSSFFormulaEvaluator e = new HSSFFormulaEvaluator(workbook);
        cell5 = e.evaluateInCell(cell5);
        System.out.println(cell5.getNumericCellValue());

        // 设置默认选中的工作表
        workbook.setActiveSheet(0);
        workbook.write(outputStream);
        outputStream.close();

    }
}


