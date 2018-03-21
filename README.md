# jmeterTestTools

>使用说明：
>usage：java -jar JmeterTestTool-v1.x.jar  [test | report]  [jmx file | report file]  [config file | report dir]

##一、生成测试脚本
>example：java -jar JmeterTestTool-v1.x.jar [test] [test.jmx] [config.properties]    
>
	参数1.生成测试脚本 usage: test
	参数2.jmx文件路径，e.g. /home/runtest/test.jmx
	参数3.执行配置方案文件路径，e.g. config.properties

##二、合成测试报告
>example：java -jar JmeterTestTool-v1.x.jar [report] [report.xlsx] [/home/runtest]
>
	参数1.合成测试报告 usage: report
	参数2.生成报告文件，e.g. report.xlsx
	参数3.测试报告集合目录，e.g. /home/runtest
