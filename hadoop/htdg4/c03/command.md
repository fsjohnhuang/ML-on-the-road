```
# 从FS复制文件到HDFS
hadoop fs -copyFromLocal <path_of_fs> <path_of_hdfs>
hadoop fs -put <path_of_fs> <path_of_hdfs>
# 从HDFS复制文件到FS
hadoop fs -copyToLocal <path_of_hdfs> <path_of_fs>
# 新建目录
hadoop fs -mkdir <path>
# 查看文件列表
hadoop fs -ls <path>
# 查看文件
hadoop fs -cat <path>

# 查看FS的文件列表
hadoop fs -ls file:///<path>
```
