package com.kanmenzhu;

import java.io.File;

public class MainRun {
    public static void main(String[] args) {
//        if (args.length < 8) {
//            System.out.println("执行参数说明：");
//            System.out.println("1.请求地址，e.g. cas-cloudphone-test-1");
//            System.out.println("2.请求服务器端口，e.g. 8080");
//            System.out.println("3.请求线程数，e.g. 100");
//            System.out.println("4.启动时间，单位：秒，e.g. 10");
//            System.out.println("5.执行循环次数，e.g. 10");
//            System.out.println("6.执行jmx文件存储地址，e.g. /home/admin/testcast/yunphone.jmx");
//            System.out.println("7.运行日志输出文件路径 e.g. /home/admin/20180120.jtl");
//            System.out.println("8.输出报告目录路径，e.g. /home/admin/output");
//            System.out.println("e.g. sh /home/admin/apache-jmeter-3.3/bin/jmeter.sh -Jurl=cas-cloudphone-test-1 -Jport=8080 -JthreadCount=100 -JRampUp=10 -Jcycle=10 -n -t /home/admin/testcast/yunphone.jmx -l 20180120.jtl -e -o /home/admin/output");
//            System.out.println(" ");
//            System.out.println("执行前准备:");
//            System.out.println("1.执行输出报告路径必须为空");
//            System.out.println("2.参数位置不能为空");
//            System.out.println("finish.");
//        }

        File file = new File("/Users/chang.lu/样例模板空.jmx");
        System.out.println(file.getAbsolutePath()+"   "+file.exists());
        file.exists();

    }
}
