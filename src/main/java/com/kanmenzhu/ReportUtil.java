package com.kanmenzhu;

import com.alibaba.fastjson.JSONObject;
import netscape.javascript.JSObject;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ReportUtil {

    private final static String JS_NAME="dashboard.js";
    private final static String statisticsTable = "statisticsTable";
    private final static String JS="js";
    public static Map<String,String> resultByThreads = new HashMap<>();

    public static void getJs(String filepath){
        File file = new File(filepath);
        if (!file.exists()){
            System.out.println(filepath+"文件不存在");
            return;
        }
        if (!file.isDirectory()){
            System.out.println(filepath+"不是目录");
            return;
        }
        findJs(file);


    }

    public static void findJs(File dir){
        for (File subfile :dir.listFiles()){
            if (subfile.isFile()){
                if (subfile.getName().endsWith(JS)&&subfile.getName().equals(JS_NAME)) {
                    String name = subfile.getParentFile().getParentFile().getParentFile().getName();
                    resultByThreads.put(name, getSummary(subfile));
                    break;
                }
            }else{
                findJs(subfile);
            }


        }
    }

    public static String getSummary(File jsfile){
        String summary = null;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(jsfile)));
            String line = "";
            while ((line = reader.readLine())!=null){
                if (line.contains(statisticsTable)){
                    break;
                }
            }
            summary = subLine(line);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return summary;
        }
    }

    /**
     * 例：createTable($("#statisticsTable"), {"supportsControllersDiscrimination": true, "overall": {"data": ["Total", 85064, 0, 0.0, 40.54640035737762, 8, 9352, 56.0, 92.0, 319.0, 708.9434688757948, 1968.2873847791845, 81.00232993991015], "isController": false}, "titles": ["Label", "#Samples", "KO", "Error %", "Average", "Min", "Max", "90th pct", "95th pct", "99th pct", "Throughput", "Received", "Sent"], "items": [{"data": ["HTTP请求", 85064, 0, 0.0, 40.54640035737762, 8, 9352, 56.0, 92.0, 319.0, 708.9434688757948, 1968.2873847791845, 81.00232993991015], "isController": false}]}, function(index, item){
     * @param line
     * @return
     */
    private static String subLine(String line){
        int start =line.indexOf("{");
        int end = line.lastIndexOf("}");
        String subline = line.substring(start,end+1);
        return subline;
    }

    public static void main(String[] args) {
        getJs("/Users/chang.lu/Documents/dev_workspace/JmeterTestTool/testrun_1521541865273");
    }

}
