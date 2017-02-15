## 内嵌模式-metastore存储在Derby中
1.配额环境变量
```
export HIVE_HOME=/usr/local/hive
export PATH=$HIVE_HOME/bin:$PATH:.
```
2.配置hive
```
cp ${HIVE_HOME}/lib/hive-log4j.properties.template ${HIVE_HOME}/lib/hive-log4j.properties
cp ${HIVE_HOME}/lib/hive-exec-log4j.properties.template ${HIVE_HOME}/lib/hive-exec-log4j.properties
cp ${HIVE_HOME}/lib/hive-default.xml.template ${HIVE_HOME}/lib/hive-site.xml
```
3.初始化Derby数据库
```
${HIVE_HOME}/bin/schematool -initSchema -dbType derby
```
上述语句会根据`hive-site.xml`中databaseName的路径生成数据库
4.启动hadoop创建目录
```
${HADOOP_HOME}/sbin/start-all.sh
hadoop fs -mkdir /tmp
hadoop fs -chmod g+w /tmp
```
5. 启动hive
```
${HIVE_HOME}/bin/hive
```

## 参考
[http://www.cnblogs.com/machong/p/5633346.html](http://www.cnblogs.com/machong/p/5633346.html)
