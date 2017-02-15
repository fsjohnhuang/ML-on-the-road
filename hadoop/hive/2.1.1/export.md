## 导出到文件
```
insert overwrite [local] directory '<dir_path>'
[row format delimited
	[line terminated by '\n']
	[fields terminated by '\001']
	[collection items terminated by '\002']
	[map keys terminated by '\003']
]
select_statement;
```
可选项`local`，表示将数据导出到OS指定目录下，否则则导出到HDFS目录下.
