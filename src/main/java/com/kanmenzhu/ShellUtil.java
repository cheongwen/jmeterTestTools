package com.kanmenzhu;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ShellUtil {

    public final static String JMETER_TEST_FILE = "jmeter_test.jmx";
    public final static String JMETER_TEST_LOG = "runjmeter.log";
    public final static String RUN_TEST_FILE_LINUX = "auto_run_jmeter.sh";
    public final static String RUN_TEST_FILE_WIN = "auto_run_jmeter.bat";
    public final static String SYSTEM_LINUX = "linux";
    public final static String SYSTEM_WINDOWS = "windows";
    public final static String LINE = System.lineSeparator();

    /**
     * 生成测试sh脚本
     * @param testDir
     */
    public static void createShell(File testDir) {
        StringBuffer shfile = new StringBuffer();
        String sys = Config.get("run.jmeter.sys");
        Integer min = Integer.valueOf(Config.get("run.threads.min"));
        Integer max = Integer.valueOf(Config.get("run.threads.max"));
        Integer step = Integer.valueOf(Config.get("run.threads.step"));
        String ramptime = Config.get("run.threads.ramptime");
        //每次运行执行时间
        String time = Config.get("run.time");
        //一次测试结束后等待时间
        String sleeptime = Config.get("run.sleeptime");
        //jmeter应用安装路径
        String jmeterHome = Config.get("run.jmeter.home");
        String jmx = testDir.getAbsolutePath() + File.separator + JMETER_TEST_FILE;
        for (int i = min.intValue(); i <= max.intValue(); i = i + step.intValue()) {
            //生成测试报告名称
            String report = testDir.getAbsolutePath() + File.separator + i + "_" + time;
            //根据运行配置生成自动运行脚本
            String startinfo = "echo \"" + i + "个并发，运行" + time + "s\" >> " + JMETER_TEST_LOG;
            shfile.append(startinfo);
            shfile.append(LINE);
            String cmd = "sh " + jmeterHome + File.separator + "bin" + File.separator + "jmeter.sh -JnumThreads=" + i + " -JrampTime=" + ramptime + " -Jduration=" + time + " -n -t " + jmx + " -l " + report + ".jtl -e -o " + report + " >>" + JMETER_TEST_LOG;
            if (SYSTEM_WINDOWS.equals(sys)) {
                cmd = jmeterHome + File.separator + "bin" + File.separator + "jmeter -JnumThreads=" + i + " -JrampTime=" + ramptime + " -Jduration=" + time + " -n -t " + jmx + " -l " + report + ".jtl -e -o " + report + " >>" + JMETER_TEST_LOG;
            }
            shfile.append(cmd);
            shfile.append(LINE);
            String endinfo = "echo \"=======end=======\" >> " + JMETER_TEST_LOG;
            shfile.append(endinfo);
            shfile.append(LINE);
            String sleep = "sleep " + sleeptime;
            if (SYSTEM_WINDOWS.equals(sys)) {
                sleep = "set SLEEP=ping 127.0.0.1 -n";
                shfile.append(sleep);
                shfile.append(LINE);
                sleep = "::%SLEEP% 60";
            }
            shfile.append(sleep);
            shfile.append(LINE);
        }
        try {
            //生成自动运行脚本,根据设置系统windows & linux
            File file = new File(testDir.getAbsolutePath() + File.separator + RUN_TEST_FILE_LINUX);
            if (SYSTEM_WINDOWS.equals(sys)) {
                file = new File(testDir.getAbsolutePath() + File.separator + RUN_TEST_FILE_WIN);
            }
            FileWriter writer = new FileWriter(file);
            writer.write(shfile.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
