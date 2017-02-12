## 事例１－统计各店销售总额
1.数据源
```
$ touch 1.txt
```
1.txt
```
20160201 9:03:01 store1 item1 20.01 30
20160201 9:03:01 store1 item2 21.01 50.2
20160201 9:03:01 store2 item3 20.01 31.24
```
2.导入数据到HDFS
```
# 创建文件夹
$ ${hadoop_home}/bin/hadoop fs -mkdir /input
# 导入数据
$ ${hadoop_home}/bin/hadoop fs -put 1.txt /input
```
3.执行JOB
```
${hadoop_home}/bin/hadoop jar ${hadoop_home}/share/hadoop/tools/lib/hadoop-streaming.2.7.3.jar -mapper mapper.py -reducer reducer.py -file mapper.py -file reducer.py -input myinput -output myoutput
```
<br>
mapper.py
```
import sys
for line in sys.stdin:
	data = line.strip().split(' ')
	if len(data) != 6: continue
	date, time, store, item, cost, payment = data
	print("{0} {1}".format(store, payment))
```
reducer.py
```
import sys
totalSales = 0
oldStore = None
for line in sys.stdin:
	data = line
	if len(data) != 2: continue
	store, payment = data
	if oldStore and oldStore != store:
		print("{0} {1}".format(oldStore, totalSales))
		totalSales = 0
	oldStore = store
	totalSales += float(payment)

if oldStore:
	print("{0} {1}".format(oldStore, totalSales))
```
