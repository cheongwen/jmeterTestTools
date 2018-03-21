package com.kanmenzhu;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * xml解析工具
 */
public class XmlUtil {


    public static String test_plan;
    public static String test_name;
    public static String test_dir;

    public static void writeTestFile(Element root) {
        try {
            test_plan = root.element("hashTree").element("TestPlan").attribute("testname").getValue();
            test_dir = test_plan + "_" + System.currentTimeMillis();
            File testDir = new File(test_dir);
            testDir.mkdir();
            System.out.println("生成测试jmx路径：" + testDir.getAbsolutePath());
            XMLWriter writer = new XMLWriter(new FileWriter(new File(testDir.getAbsolutePath() + File.separator + ShellUtil.JMETER_TEST_FILE)));
            writer.write(root);
            writer.close();
            ShellUtil.createShell(testDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void readJmxFile(File file) {
        // 创建saxReader对象
        SAXReader reader = new SAXReader();
        try {
            // 通过read方法读取一个文件 转换成Document对象
            Document document = reader.read(file);
            Element root = document.getRootElement();
            Element threadGroup = root.element("hashTree").element("hashTree").element("ThreadGroup");
            test_name = threadGroup.attribute("testname").getValue();
            System.out.println("testname：" + test_name);
            if (Config.get("run.mode").equals("cycle")) {
                System.out.println("按次执行");
                //TODO 按次
                runByCycle(threadGroup);
                writeTestFile(root);
            } else if (Config.get("run.mode").equals("time")) {
                System.out.println("按时间执行");
                //TODO 按时间
                runByTime(threadGroup);
                writeTestFile(root);
            } else {
                System.out.println("Err：运行方式错误，run.mode=" + Config.get("run.mode") + "，目前只支持cycle&time");
            }

        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println(File.separator);
        System.out.println(File.pathSeparator);
    }

    public static void runByTime(Element threadGroup) {
        //按时间运行必须开启调度器
        List<Element> boolprops = threadGroup.elements("boolProp");
        for (Element prop : boolprops) {
            if (Jmx.SCHEDULER.equals(prop.attribute("name").getValue())) {
                prop.setText("true");
                System.out.println("设置调度器成功");
            }
        }
        //首先判断是否存在循环次数节点，按时间执行，则需要设置用于循环
        Element elementProp = threadGroup.element("elementProp");
        Element loopNum = elementProp.element("stringProp");
        if (loopNum != null) {
            elementProp.remove(loopNum);
            elementProp.addElement("intProp").addAttribute("name", Jmx.LOOPS).addText("-1");
        }
        //分别根据配置文件设置运行参数
        List<Element> props = threadGroup.elements("stringProp");
        for (Element prop : props) {
            Attribute name = prop.attribute("name");
            //设置并发数
            if (name.getValue().equals(Jmx.NUM_THREADS)) {
                prop.setText(Config.get(Jmx.NUM_THREADS));
            }
            //设置运行时间
            if (name.getValue().equals(Jmx.DURATION)) {
                prop.setText(Config.get(Jmx.DURATION));
            }
            //设置间隔启动时间
            if (name.getValue().equals(Jmx.RAMP_TIME)) {
                prop.setText(Config.get(Jmx.RAMP_TIME));
            }
        }
    }

    public static void runByCycle(Element threadGroup) {
        //按时间运行必须开启调度器
        List<Element> boolprops = threadGroup.elements("boolProp");
        for (Element prop : boolprops) {
            if (Jmx.SCHEDULER.equals(prop.attribute("name").getValue())) {
                prop.setText("false");
                System.out.println("设置调度器成功");
            }
        }
        List<Element> props = threadGroup.elements("stringProp");
        if (props != null) {
            for (Element prop : props) {
                Attribute name = prop.attribute("name");
                //设置并发数
                if (name.getValue().equals(Jmx.NUM_THREADS)) {
                    prop.setText(Config.get(Jmx.NUM_THREADS));
                }
                //设置间隔启动时间
                if (name.getValue().equals(Jmx.RAMP_TIME)) {
                    prop.setText(Config.get(Jmx.RAMP_TIME));
                }
            }
        }
        //设置运行次数
        String loop = Config.get(Jmx.LOOPS);
        //首先判断是否存在循环次数节点，如果没有则添加
        Element elementProp = threadGroup.element("elementProp");
        Element loopNum = elementProp.element("stringProp");
        if (loopNum != null) {
            loopNum.setText(loop);
        } else {
            Element intProp = elementProp.element("intProp");
            if (intProp != null) {
                elementProp.addElement("stringProp").addAttribute("name", Jmx.LOOPS).addText(loop);
                elementProp.remove(intProp);
            }

        }
    }


}
