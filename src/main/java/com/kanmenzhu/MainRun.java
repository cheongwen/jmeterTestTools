package com.kanmenzhu;

import java.io.File;

public class MainRun {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("执行参数说明：");
            System.out.println("1.执行jmx文件路径，e.g. /home/runtest/test.jmx");
            System.out.println("2.执行配置方案文件路径，e.g. /home/runtest/config.properties");
            System.out.println("finish.");
            return;
        }
        String runjmx = args[0];
        String config = args[1];
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
    }
}
