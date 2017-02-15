1.下载[http://sqoop.apache.org](http://sqoop.apache.org)，然后解压<br>
2.在`/etc/profile`下追加`export sqoop_home=/usr/local/sqoop`<br>
3.把驱动程序jar拷贝到`${sqoop_home}/lib/`下<br>
4.修改`${sqoop_home}/conf/sqoop-env.sh`
```
cp ${sqoop_home}/conf/sqoop-env.sh.template ${sqoop_home}/conf/sqoop-env.sh
```
配置如下内容
```
#Set path to where bin/hadoop is available
export HADOOP_COMMON_HOME=/usr/local/hadoop/

#Set path to where hadoop-*-core.jar is available
export HADOOP_MAPRED_HOME=/usr/local/hadoop

#set the path to where bin/hbase is available
export HBASE_HOME=/usr/local/hbase

#Set the path to where bin/hive is available
export HIVE_HOME=/usr/local/hive

#Set the path for where zookeper config dir is
export ZOOCFGDIR=/usr/local/zk
```
