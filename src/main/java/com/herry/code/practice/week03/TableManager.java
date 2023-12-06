package com.herry.code.practice.week03;

import com.herry.code.practice.common.BusinessException;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 操作表格
 * @author herry
 */
public class TableManager {

    private static final Logger logger = LoggerFactory.getLogger(TableManager.class);

    private static final String XLSX_EXTENSION = "xlsx";

    /**
     * 将文本数据写入表格
     */
    public void writeExcel(String filePath, String data) {
        // 参数校验
        if (StringUtils.isBlank(filePath)) {
            throw new BusinessException("表格路径名为空");
        }
        if (StringUtils.isBlank(data)) {
            throw new BusinessException("数据为空");
        }
        if (!FilenameUtils.isExtension(filePath, XLSX_EXTENSION)) {
            throw new BusinessException("不是表格，文件格式有误");
        }

        try (Workbook workbook = new XSSFWorkbook();
             FileOutputStream fos = new FileOutputStream(filePath)) {
            // 创建工作表对象
            Sheet sheet = workbook.createSheet("Sheet1");

            // 写入数据到单元格
            Row row = sheet.createRow(0);
            Cell cell = row.createCell(0);
            cell.setCellValue(data);

            // 保存工作簿到文件
            workbook.write(fos);
            System.out.println("Excel文件写入成功！");
        } catch (IOException e) {
            logger.error("Excel文件写入失败", e);
        }
    }


    /**
     * 从表格读取数据
     */
    public List<RowData> readExcel(String filePath) {
        List<RowData> rows = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                RowData rowData = new RowData();

                for (Cell cell : row) {
                    switch (cell.getCellType()) {
                        case STRING:
                            String stringValue = cell.getStringCellValue();
                            // 处理字符串类型的数据
                            rowData.setString(stringValue);
                            break;
                        case NUMERIC:
                            if (DateUtil.isCellDateFormatted(cell)) {
                                Date dateValue = cell.getDateCellValue();
                                // 处理日期类型的数据
                                rowData.setDate(dateValue);
                            } else {
                                Double numericValue = cell.getNumericCellValue();
                                // 处理数字类型的数据
                                rowData.setNumber(numericValue);
                            }
                            break;
                        default:
                            break;
                    }
                }
                rows.add(rowData);
            }
        } catch (IOException e) {
            logger.error("读取数据出错", e);
        }
        return rows;
    }

    /**
     * 数据行
     */
    public static class RowData {
        private String string;
        private Double number;
        private Date date;

        public String getString() {
            return string;
        }

        public void setString(String string) {
            this.string = string;
        }

        public Double getNumber() {
            return number;
        }

        public void setNumber(Double number) {
            this.number = number;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        @Override
        public String toString() {
            return "RowData{" +
                    "string='" + string + '\'' +
                    ", number=" + number +
                    ", date=" + date +
                    '}';
        }
    }
}







