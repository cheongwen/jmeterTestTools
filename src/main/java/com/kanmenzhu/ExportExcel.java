package com.kanmenzhu;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xmlbeans.impl.jam.JSourcePosition;
import org.openxmlformats.schemas.drawingml.x2006.main.STAdjCoordinate;

import javax.xml.crypto.Data;

public class ExportExcel {

    private final static String REQUEST = "Request";
    private final static String EXECUTIONS = "Executions";
    private final static String RESPONSETIME = "Response Time(ms)";
    private final static String NETWORK = "Network(KB/sec)";
    private final static String THREADS = "并发数_执行时间";
    private final static String LABEL = "Label";
    private final static String SAMPLES = "#Samples";
    private final static String KO = "错误数";
    private final static String ERROR = "错误率%";
    private final static String AVERAGE = "平均响应时间";
    private final static String MIN = "Min";
    private final static String MAX = "Max";
    private final static String PCT90 = "90th pct";
    private final static String PCT95 = "95th pct";
    private final static String PCT99 = "99th pct";
    private final static String THROUGHPUT = "吞吐量";
    private final static String RECEIVED = "Received";
    private final static String SENT = "Sent";


    /**
     * 导出excel
     *
     * @param excel_name 导出的excel路径（需要带.xlsx)
     * @param datamap    excel数据
     * @throws Exception
     */

    public static void createExcel(String excel_name, Map<String, String[]> datamap) {
        // 创建新的Excel 工作簿
        XSSFWorkbook workbook = new XSSFWorkbook();
        // 在Excel工作簿中建一工作表，其名为缺省值
        XSSFSheet sheet = workbook.createSheet();
        // 合并单元格
        CellRangeAddress cra00 = new CellRangeAddress(0, 0, 0, 13);
        sheet.addMergedRegion(cra00);
        CellRangeAddress cra10 = new CellRangeAddress(1, 1, 0, 1);
        sheet.addMergedRegion(cra10);
        CellRangeAddress cra11 = new CellRangeAddress(1, 1, 2, 4);
        sheet.addMergedRegion(cra11);
        CellRangeAddress cra12 = new CellRangeAddress(1, 1, 5, 11);
        sheet.addMergedRegion(cra12);
        CellRangeAddress cra13 = new CellRangeAddress(1, 1, 12, 13);
        sheet.addMergedRegion(cra13);

        // 第一行
        // 标题：当前版本数据
        XSSFRow row0 = sheet.createRow(0);
        XSSFCell cell00 = row0.createCell(0);
        // 设置表格样式
        // 设置excel头（第一行）的头名称
        cell00.getCellStyle().setAlignment(HorizontalAlignment.CENTER);
        cell00.setCellValue("结果统计");
        // 第二行
        // Request，Executions,Response Time(ms),Network(KB/sec)
        XSSFRow row1 = sheet.createRow(1);
        XSSFCell row1cell0 = row1.createCell(0);
        row1cell0.setCellType(CellType.STRING);
        row1cell0.getCellStyle().setAlignment(HorizontalAlignment.CENTER);
        row1cell0.setCellValue(REQUEST);
        XSSFCell row1cell2 = row1.createCell(2);
        row1cell2.setCellType(CellType.STRING);
        row1cell2.getCellStyle().setAlignment(HorizontalAlignment.CENTER);
        row1cell2.setCellValue(EXECUTIONS);
        XSSFCell row1cell4 = row1.createCell(5);
        row1cell4.setCellType(CellType.STRING);
        row1cell4.getCellStyle().setAlignment(HorizontalAlignment.CENTER);
        row1cell4.setCellValue(RESPONSETIME);
        XSSFCell row1cell5 = row1.createCell(12);
        row1cell5.setCellType(CellType.STRING);
        row1cell5.getCellStyle().setAlignment(HorizontalAlignment.CENTER);
        row1cell5.setCellValue(NETWORK);
        //第三行
        XSSFRow row3 = sheet.createRow(2);
        XSSFCell row3cell0 = row3.createCell(0);
        row3cell0.setCellType(CellType.STRING);
        row3cell0.getCellStyle().setAlignment(HorizontalAlignment.CENTER);
        row3cell0.setCellValue(THREADS);
        XSSFCell row3cell1 = row3.createCell(1);
        row3cell1.setCellType(CellType.STRING);
        row3cell1.getCellStyle().setAlignment(HorizontalAlignment.CENTER);
        row3cell1.setCellValue(LABEL);
        XSSFCell row3cell2 = row3.createCell(2);
        row3cell2.setCellType(CellType.STRING);
        row3cell2.getCellStyle().setAlignment(HorizontalAlignment.CENTER);
        row3cell2.setCellValue(SAMPLES);
        XSSFCell row3cell3 = row3.createCell(3);
        row3cell3.setCellType(CellType.STRING);
        row3cell3.getCellStyle().setAlignment(HorizontalAlignment.CENTER);
        row3cell3.setCellValue(KO);
        XSSFCell row3cell4 = row3.createCell(4);
        row3cell4.setCellType(CellType.STRING);
        row3cell4.getCellStyle().setAlignment(HorizontalAlignment.CENTER);
        row3cell4.setCellValue(ERROR);
        XSSFCell row3cell5 = row3.createCell(5);
        row3cell5.setCellType(CellType.STRING);
        row3cell5.getCellStyle().setAlignment(HorizontalAlignment.CENTER);
        row3cell5.setCellValue(AVERAGE);
        XSSFCell row3cell6 = row3.createCell(6);
        row3cell6.setCellType(CellType.STRING);
        row3cell6.getCellStyle().setAlignment(HorizontalAlignment.CENTER);
        row3cell6.setCellValue(MIN);
        XSSFCell row3cell7 = row3.createCell(7);
        row3cell7.setCellType(CellType.STRING);
        row3cell7.getCellStyle().setAlignment(HorizontalAlignment.CENTER);
        row3cell7.setCellValue(MAX);
        XSSFCell row3cell8 = row3.createCell(8);
        row3cell8.setCellType(CellType.STRING);
        row3cell8.getCellStyle().setAlignment(HorizontalAlignment.CENTER);
        row3cell8.setCellValue(PCT90);
        XSSFCell row3cell9 = row3.createCell(9);
        row3cell9.setCellType(CellType.STRING);
        row3cell9.getCellStyle().setAlignment(HorizontalAlignment.CENTER);
        row3cell9.setCellValue(PCT95);
        XSSFCell row3cell10 = row3.createCell(10);
        row3cell10.setCellType(CellType.STRING);
        row3cell10.getCellStyle().setAlignment(HorizontalAlignment.CENTER);
        row3cell10.setCellValue(PCT99);
        XSSFCell row3cell11 = row3.createCell(11);
        row3cell11.setCellType(CellType.STRING);
        row3cell11.getCellStyle().setAlignment(HorizontalAlignment.CENTER);
        row3cell11.setCellValue(THROUGHPUT);
        XSSFCell row3cell12 = row3.createCell(12);
        row3cell12.setCellType(CellType.STRING);
        row3cell12.getCellStyle().setAlignment(HorizontalAlignment.CENTER);
        row3cell12.setCellValue(RECEIVED);
        XSSFCell row3cell13 = row3.createCell(13);
        row3cell13.setCellType(CellType.STRING);
        row3cell13.getCellStyle().setAlignment(HorizontalAlignment.CENTER);
        row3cell13.setCellValue(SENT);

        // 添加数据
        // ===============================================================
        Iterator<String> iterator = datamap.keySet().iterator();
        int n = 0;
        while (iterator.hasNext()) {
            // 在索引2的位置创建行
            XSSFRow row_value = sheet.createRow(n + 3);
            String key = iterator.next();
            String[] datalist = datamap.get(key);
            XSSFCell cell = row_value.createCell(0);
            // 定义单元格为字符串类型
            cell.setCellType(CellType.NUMERIC);
            // 在单元格中输入一些内容
            cell.setCellValue(key);
            // ===============================================================
            for (int i = 0; i < datalist.length; i++) {
                // 在索引0的位置创建单元格（左上端）
                XSSFCell cellx = row_value.createCell(i + 1);
                // 定义单元格为字符串类型
                cellx.setCellType(CellType.NUMERIC);
                // 在单元格中输入一些内容
                cellx.setCellValue(datalist[i]);
            }
            // ===============================================================

            n++;
        }
        try {
            // 新建一输出文件流
            FileOutputStream fos = new FileOutputStream(excel_name);
            // 把相应的Excel 工作簿存盘
            workbook.write(fos);
            fos.flush();
            // 操作结束，关闭文件
            fos.close();
            workbook.close();
            System.out.println("已导出文件：" + excel_name);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        String filename = "test.xlsx";
        try {
            createExcel(filename, getData("/Users/chang.lu/Documents/dev_workspace/JmeterTestTool/testrun_1521541865273"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过测试报告集合目录获取报告结果数据
     *
     * @param filepath
     * @return
     */
    public static Map<String, String[]> getData(String filepath) {
        Map<String, String[]> data = new HashMap<>();
        ReportUtil.getJs(filepath);
        Map<String, String> result = ReportUtil.resultByThreads;
        Iterator<String> iterator = result.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            String sumstring = result.get(key);
            List<String[]> sums = parseData(sumstring);
            for (String[] sum : sums) {
                String keynow = key + "_" + sum[0];
                data.put(keynow, sum);
            }
        }
        return data;
    }

    /**
     * 解析报告结果数据json字符串，获取性能测试数据
     *
     * @param jsonstring
     * @return
     */
    public static List<String[]> parseData(String jsonstring) {
        JSONObject json = JSONObject.parseObject(jsonstring);
        JSONArray items = (JSONArray) json.get("items");
        List<String[]> datalist = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            JSONObject item = (JSONObject) items.get(i);
            JSONArray data = (JSONArray) item.get("data");
            Object[] objects = data.toArray();
            String[] dataString = new String[data.size()];
            for (int j = 0; j < data.size(); j++) {
                SummaryReport sum = new SummaryReport(data);
                dataString = sum.getStringData();
            }
            datalist.add(dataString);
        }
        return datalist;
    }
}
