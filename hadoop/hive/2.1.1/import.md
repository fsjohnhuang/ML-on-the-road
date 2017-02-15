## 导入文件数据
```
load data [local] inpath '<path>' [overwrite]
into table <table_name> [partition(column_name1=val1,column_name2=val2 ...)];
```
可选项`local`，表示从OS的目录或文件导入数据，否则则从HDFS的目录或文件导入．<br>
可选项`overwrite`，表示覆盖表中原来的数据，否则则是追加到目标表中．<br>
`<path>`，可以是目录或文件路径．<br>
对于分区表而言，必须写`partition`语句，并且后面的值`val1,val2`会覆盖源数据的值．<br>
