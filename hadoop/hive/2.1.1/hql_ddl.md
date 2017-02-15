## 内部表(Table)
表结构，对应的HDFS目录和文件均在Hive的管理下．因此当删除表时，会将表结构，HDFS目录和文件一并删除．
```
-- 创建
create table [if not exists] <table_name>(
	<column_name> <type>,
	......
	<column_name> <type>
)
[location '<dir_path>']
[row format delimited
	[line terminated by '\n']
	[fields terminated by '\001']
	[collection items terminated by '\002']
	[map keys terminated by '\003']
]
[as select_statement];
```
可选项`location '<dir_path>'`，设置表数据保存在指定的HDFS目录．<br>
可选项`row format delimited`用于表示后面的项目将用于设置行，列，元素，键值间的分隔符．<br>
1. `line terminated by`用于设置行分隔符，默认为`\n`(line feed character)．只支持`\n`作为行分隔符
2. `fields terminated by`用于设置列分隔符，默认为`\001`(`Ctrl-A`)
3. `collection items terminated by`用于设置集合元素分隔符，默认为`\002`(`Ctrl-B`)
4. `map keys terminated by`用于设置键值分隔符，默认为`\003`(`Ctrl-C`)
可选项`as select_statement`用于在创建表同时插入数据

```
-- 复制表结构
create table <table_a> like <table_b>;
-- 删除
drop table <table_name>;
-- 增加列
alter table <table_name> add columns(
	<column_name> <type>,
	......
	<column_name> <type>
);
-- 清空表数据(截断表)
truncate table <table_name>;
```

## 外部表(External Table)
仅表结构在Hive的管理下．因此当删除表时，只会删除表结构，而HDFS目录和文件不会删除．
```
-- 创建
create external table [if not exists] <table_name>(
  <column_name> <type>,
	......
  <column_name> <type>
)
location '<dir_path>'
[row format delimited
	[line terminated by '\n']
	[fields terminated by '\001']
	[collection items terminated by '\002']
	[map keys terminated by '\003']
];
```

## 分区表(Partition)
对应RMDB的partition列的密集索引．<br>
分区表分为单分区和多分区表．单分区表就是以一列作为分区依据，一个分区对应表目录下的一个无嵌套子目录(如`tbl1/col1/`)；而多分区表就是以多列作为分区依据，一个分区对应表目录下的一系列嵌套子目录(如`tbl1/col1/col2/col3`)<br>
```
create table [if not exists] <table_name>(
  <column_name> <type>,
	......
  <column_name> <type>
)
partitioned by (<column_name> <type>)
[row format delimited
	[line terminated by '\n']
	[fields terminated by '\001']
	[collection items terminated by '\002']
	[map keys terminated by '\003']
];
```
注意：`partitioned by (<column_name> <type>)`中的`column_name`将和`<table_name>`后的`column_name`一同组成表的列．因此不能存在重复定义．
```
-- 示例
create table if not exists t_test(
	name string,
	age int
)
partitioned by (sex tinyint);
```
```
-- 插入数据
insert into table <table_name> partition(<column_name>=<value>)
select * from <table_name> where <column_name> = <value>;
-- 追加分区
alter table <table_name> add partition(<column_name1>=<value1>,<column_name2>=<value2>,...);
-- 删除分区
alter table <table_name> drop partition(<column_name1>=<value1>,<column_name2>=<value2>,...);
```
```
-- 查看分区情况
show partitions <table_name>;

-- 清空分区数据(截断分区)
truncate table <table_name> partition(<column_name>=<value>);
```
## 桶表(Bucket Table)
对数据进行hash后存放在不同文件中．降低热块，提高查询速度．
```
create table [is not exists] <table_name>(
  <column_name> <type>,
	......
  <column_name> <type>
)
clustered by(<column_name> into 5 buckets);
```

## 视图(View)
```
create view <view_name>
as
<select_statement>
```
