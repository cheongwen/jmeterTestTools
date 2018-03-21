# jmeterTestTools
# 功能说明
>1.根据性能测试运行方案配置，生成自动运行脚本。<br/>
>>运行配置文件 runconfig.properties<br/>
>>
>>\### 运行系统配置<br/>
\# 运行系统 linux,windows<br/>
run.jmeter.sys=linux<br/>
\# jmeter安装主目录<br/>
run.jmeter.home=/home/tools/apache-jmeter-4.0<br/>
\# 按时间 time  按次数 cycle<br/>
run.mode=time<br/>
\# 最小的并发数<br/>
run.threads.min=10<br/>
\# 最大的并发数<br/>
run.threads.max=30<br/>
\# 每次递增的并发数<br/>
run.threads.step=10<br/>
\# 启动间隔时间，单位：秒<br/>
run.threads.ramptime=10<br/>
\# 每个线程数的运行时间，单位：秒<br/>
run.time=120<br/>
\# 每个线程数运行次数<br/>
run.cycle=300<br/>
\# 每次运行的间隔时间，单位：秒<br/>
run.sleeptime=60<br/>
>
>>根据上述配置，会自动生成性能测试运行shell脚本，测试方案为10个并发开始依次递增10个并发，最大并发数为30；
>>即10、20、30个并发每次运行120秒，每次运行间隔时间为60秒。
>>

>2.执行自动运行脚本，生成测试报告，根据测试报告数据自动整理合并测试数据。
>
>>自动测试脚本执行完成后，jmeter生成测试报告目录，本工具会汇总每次执行完成后的测试报告，生成总的测试报表，格式为excel。

# 使用说明
>usage：java -jar JmeterTestTool-v1.x.jar  [test | report]  [jmx file | report file]  [config file | report dir]

## 一、生成测试脚本
>example：java -jar JmeterTestTool-v1.x.jar [test] [test.jmx] [config.properties]    
>
	1. 生成测试脚本 usage: test
	2. jmx文件路径，e.g. /home/runtest/test.jmx
	3. 执行配置方案文件路径，e.g. config.properties
>
>生成产物：
>
>1. 以[测试计划+时间戳]命名的测试目录，如testrun_1521541865273<br/>
>2. 目录下生成自动测试脚本 auto\_run\_jmeter.sh<br/>
>3. jmeter执行测试计划文件 jmeter_test.jmx
>
>产物使用：
>
>1. chmod a+x auto\_run\_jmeter.sh<br/>
>2. ./auto\_run\_jmeter.sh &<br/>
>
>执行完毕：
>
>1. 根据方案运行配置生成对应的测试报告文件及目录，命名方式：并发数_执行时间(s)，如10\_120.jtl、10\_120


## 二、合成测试报告
>example：java -jar JmeterTestTool-v1.x.jar [report] [report.xlsx] [/home/testrun_1521541865273]<br/>
>
	1. 合成测试报告 usage: report
	2. 生成报告文件，e.g. report.xlsx
	3. 测试报告集合目录，e.g. /home/testrun_1521541865273
	
>生成产物：
>
>1. 执行目录生成汇总报表文件，如report.xlsx
