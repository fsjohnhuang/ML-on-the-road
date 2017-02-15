## 从HDFS导出数据到数据库
```
./sqoop export --connect jdbc:oracle:thin:@129.23.12.12:1521:orcl --username scottt --password pww -m 1 --table <oracle_table_name> --export-dir '<hdfs_dir_path>'
```
