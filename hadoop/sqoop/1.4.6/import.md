## 现实数据库的表
```
./sqoop list-tables --connect jdbc:oracle:thin:@129.23.12.12:1521:orcl --username scottt --password pww
```
## 从数据库导到HDFS
```
./sqoop import --connect jdbc:oracle:thin:@129.23.12.12:1521:orcl --username scottt --password pww --table emp --columns 'id,name,age' -m 1 --target-dir '/sqoop/emp'
```
## 从数据库导到Hive
```
./sqoop import --hive-import --connect jdbc:oracle:thin:@129.23.12.12:1521:orcl --username scottt --password pww --table emp --columns 'id,name,age' -m 1
```
注意：`--table`后的表名必须为大写，否则会找不到表．
