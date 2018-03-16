package com.kanmenzhu;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.SAXWriter;
import org.xml.sax.SAXException;

import java.io.File;
import java.util.List;

/**
 * xml解析工具
 */
public class XmlUtil {

    /**
     * 设置运行时间，单位：分钟
     */
    public void setRunTime(int runTime) {

    }

    /**
     * 设置循环次数
     */
    public void setCycleTime() {

    }

    public static void readFile(String filePath) {
        // 创建saxReader对象
        SAXReader reader = new SAXReader();
        try {
            // 通过read方法读取一个文件 转换成Document对象
            Document document = reader.read(new File(filePath));
            Element root = document.getRootElement();
            Element threadGroup = root.element("hashTree").element("hashTree").element("ThreadGroup");
            String testname = threadGroup.attribute("testname").getValue();
            System.out.println(testname);
            List<Element> props = threadGroup.elements("stringProp");
            for (Element prop : props){
                Attribute name = prop.attribute("name");
                System.out.println(name);
                //设置并发数
                if (name.getValue().equals(Jmx.NUM_THREADS)){
                    name.setValue("30000");
                }
                //设置是否永久循环
                if (name.getValue().equals(Jmx.CONTINUE_FOREVER)){
                    name.setValue("false");
                }
                //设置运行时间
                if (name.getValue().equals(Jmx.DURATION)){
                    name.setValue("18000");
                }
            }
            SAXWriter writer = new SAXWriter();
            writer.write(root);
            
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
            System.out.println("写文件出错");
        }
    }

    public static void main(String[] args) {
        readFile("/Users/chang.lu/样例模板空.jmx");
    }

}
