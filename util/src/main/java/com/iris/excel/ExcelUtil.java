package com.iris.excel;


import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author iris
 * @date 2021/8/24
 */
@Slf4j
public class ExcelUtil {


    private static List<Map<String, String>> getMaps(String[] columns, InputStream is) {
        List<Map<String, String>> list = new ArrayList<>();
        Workbook wb;
        Sheet sheet;
        Row row;
        String cellData;
        wb = readExcel(is, "");
        if (wb != null) {
            //用来存放表中数据
            list = new ArrayList<>();
            //获取第一个sheet
            sheet = wb.getSheetAt(0);
            //获取最大行数
            int rowNum = sheet.getPhysicalNumberOfRows();
            //获取第一行
            row = sheet.getRow(0);
            //获取最大列数
            int colNum = row.getPhysicalNumberOfCells();
            for (int i = 1; i < rowNum; i++) {
                Map<String, String> map = new LinkedHashMap<>();
                row = sheet.getRow(i);
                if (row != null) {
                    for (int j = 0; j < colNum; j++) {
                        cellData = (String) getCellFormatValue(row.getCell(j));
                        map.put(columns[j], cellData);
                    }
                } else {
                    break;
                }
                list.add(map);
            }
        }
        return list;
    }

    //读取excel
    public static Workbook readExcel(InputStream is, String fileName) {
        Workbook wb = null;

        try {
            if (fileName.contains(".xls")) {
                wb = new HSSFWorkbook(is);
            } else if (fileName.contains(".xlsx")) {
                wb = new XSSFWorkbook(is);
            } else {
                wb = null;
            }
        } catch (IOException e) {
            log.error("{}", e.getMessage());
        }
        return wb;
    }

    public static Object getCellFormatValue(Cell cell) {
        Object cellValue;
        if (cell != null) {
            //判断cell类型
            switch (cell.getCellType()) {
                case NUMERIC: {
                    cellValue = String.valueOf(cell.getNumericCellValue());
                    break;
                }
                case FORMULA: {
                    //判断cell是否为日期格式
                    if (DateUtil.isCellDateFormatted(cell)) {
                        //转换为日期格式YYYY-mm-dd
                        cellValue = cell.getDateCellValue();
                    } else {
                        //数字
                        cellValue = String.valueOf(cell.getNumericCellValue());
                    }
                    break;
                }
                case STRING: {
                    cellValue = cell.getRichStringCellValue().getString();
                    break;
                }
                default:
                    cellValue = "";
            }
        } else {
            cellValue = "";
        }
        return cellValue;
    }

    private static final Short fontSize = 11;
    //行高
    private static final Integer rowHeight = 16;
    //列宽
    private static final Integer columWidth = 12;

    public static HSSFWorkbook getHSSFWorkbook(List<String> heard, List<List<Object>> content, String titleStr) {
        //创建HSSFWorkbook对象
        HSSFWorkbook wb = new HSSFWorkbook();
        //创建HSSFSheet对象
        HSSFSheet sheet = wb.createSheet("sheet1");
        //设置默认行宽
        sheet.setDefaultColumnWidth(columWidth);

        // 标题样式（加粗，垂直居中）
        HSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);//水平居中
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
        HSSFFont fontStyle = wb.createFont();
        fontStyle.setBold(true);   //加粗
        fontStyle.setFontHeightInPoints((short) 12);  //设置标题字体大小
        cellStyle.setFont(fontStyle);

        //在第0行创建rows  (表标题)
        HSSFRow title = sheet.createRow(0);
        title.setHeightInPoints(27);//行高
        HSSFCell cellValue = title.createCell(0);
        cellValue.setCellValue(titleStr);
        cellValue.setCellStyle(cellStyle);
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, (heard.size() - 1)));
        //设置表头样式，表头居中
        HSSFCellStyle style = wb.createCellStyle();
        //设置单元格样式
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
        //设置字体
        HSSFFont font = wb.createFont();
        font.setFontHeightInPoints(fontSize);
        style.setFont(font);



        //设置单元格样式
        HSSFCellStyle descStyle = wb.createCellStyle();
        descStyle.setWrapText(true); // 自动换行
        descStyle.setAlignment(HorizontalAlignment.LEFT); // 水平对齐
        descStyle.setVerticalAlignment(VerticalAlignment.CENTER); // 垂直对齐
        // 创建描述
        HSSFRow desc = sheet.createRow(1);
        desc.setHeightInPoints(77);//行高
        HSSFCell descCell = desc.createCell(0);
        descCell.setCellStyle(descStyle);
        descCell.setCellValue("填写说明：\n1、该数据模型仅支持新增单行数据导入;\n2、ad;\n3、asd。");
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, (heard.size() - 1)));

        //在第1行创建rows
        HSSFRow row = sheet.createRow(2);
        //设置列头元素
        HSSFCell cellHead;
        for (int i = 0; i < heard.size(); i++) {
            cellHead = row.createCell(i);
            cellHead.setCellValue(heard.get(i));
            cellHead.setCellStyle(style);
        }

        int a = 3;
        for (int i = 0; i < content.size(); i++) {
            HSSFRow roww = sheet.createRow(a);
            List<Object> list = content.get(i);
            HSSFCell cell;
            // 序号
            cell = roww.createCell(0);
            cell.setCellStyle(style);
            cell.setCellValue(a - 2);

            for (int j = 0; j < heard.size() - 1; j++) {
                cell = roww.createCell(j + 1);
                cell.setCellStyle(style);
                Object valueObject = list.get(j);
                String value;
                if (valueObject == null) {
                    valueObject = "";
                }
                if (valueObject instanceof String) {
                    //取出的数据是字符串直接赋值
                    value = (String) list.get(j);
                } else if (valueObject instanceof Integer) {
                    //取出的数据是Integer
                    value = valueObject + "";
                } else {
                    value = valueObject.toString();

                }
                cell.setCellValue(value);
            }
            a++;
        }

        return wb;
    }
}