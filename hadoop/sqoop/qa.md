## Found interface org.apache.hadoop.mapreduce.JobContext, but class was expected
在java中调用sqoop接口进行mysql和hdfs直接数据传输时，遇到以下错误：<br>
Found interface org.apache.hadoop.mapreduce.JobContext, but class was expected<br>
这里需要注意，sqoop有两个版本：<br>
sqoop-1.4.4.bin__hadoop-1.0.0.tar.gz（对应hadoop1版本）<br>
sqoop-1.4.4.bin__hadoop-2.0.4-alpha.tar.gz（对应hadoop2版本）<br>
出现上面的错误就是hadoop和对应的sqoop版本不一致，二者保持一致即可解决问题。<br>
