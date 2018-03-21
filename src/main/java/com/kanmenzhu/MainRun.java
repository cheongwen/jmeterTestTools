package com.kanmenzhu;

import java.io.File;
import java.util.Map;

public class MainRun {

    private final static String TEST = "test";
    private final static String REPORT = "report";

    public static void main(String[] args) {
        if (args.length == 3) {
            //执行生成测试脚本
            if (TEST.equals(args[0])) {
                String runjmx = args[1];
                String config = args[2];
                File jmxFile = new File(runjmx);
                File configFile = new File(config);
                if (!jmxFile.exists() || jmxFile.isDirectory()) {
                    System.out.println("测试jmx文件路径错误,请检查");
                    return;
                }
                if (!configFile.exists() || configFile.isDirectory()) {
                    System.out.println("测试config文件路径错误,请检查");
                    return;
                }
                Config.readConfig(configFile);
                XmlUtil.readJmxFile(jmxFile);
                ShellUtil.createShell(new File(XmlUtil.test_dir));
                return;
            }
            if (REPORT.equals(args[0])) {
                String excelfile = args[1];
                String reportdir = args[2];
                File dir = new File(reportdir);
                if (!dir.isDirectory()) {
                    System.out.println("报告集合目录不是一个有效的目录，请检查");
                    return;
                }
                Map<String, String[]> data = ExportExcel.getData(reportdir);
                ExportExcel.createExcel(excelfile, data);
                return;
            }
            printInfo();
        } else {
            printInfo();
        }


    }

    public static void printInfo() {
        System.out.println("执行参数说明：");
        System.out.println("usage：java -jar JmeterTestTool-v1.x.jar [test | report] [jmx file | report file] [config file | report dir]");
        System.out.println("一、生成测试脚本");
        System.out.println("参数1.生成测试脚本 usage: test ");
        System.out.println("参数2.jmx文件路径，e.g. /home/runtest/test.jmx");
        System.out.println("参数3.执行配置方案文件路径，e.g. config.properties");
        System.out.println("example：java -jar JmeterTestTool-v1.x.jar [test] [test.jmx] [config.properties]");
        System.out.println("二、合成测试报告");
        System.out.println("参数1.合成测试报告 usage: report ");
        System.out.println("参数2.生成报告文件，e.g. report.xlsx");
        System.out.println("参数3.测试报告集合目录，e.g. /home/runtest");
        System.out.println("example：java -jar JmeterTestTool-v1.x.jar [report] [report.xlsx] [/home/runtest]");
    }
}
