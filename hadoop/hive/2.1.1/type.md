## Primitive Type
### 数值类型
```
tinyint, 占1byte, 字面量 1Y
smallint, 占2byte, 字面量 1S
int, 占4byte, 字面量 1
bigint, 占8byte, 字面量 1L
float, 占4byte
double, 占8byte, 字面量 1.0
decimal, 可表示任意精度的小数, 在DDL中可通过decimal(<digit>[, <precise>=0])指定精度,其中<digit>范围为1~38.
	decimal(5, 2)表示5位精度为2的小数;
	decimal(5)表示5位精度为0的小数.
```
### 文本类型
```
string, 变长字符串，理论上容量为2G，字面量'I am string!'
varchar, 变长字符串，容量为1~65355
char, 定长字符串，容量为1~65355
```
### 二进制类型
```
boolean, 值为true或false
binary, 变长二进制
```
### 时间类型
```
date, 年月日
timestamp, 不含时区信息，距离UNIX纪元(1970-01-01 00:00:00UTC)的偏移量，单位为纳秒．
	转换为整型或浮点型时，单位为秒;
	转换为字符串时，格式为yyyy-MM-dd HH:mm:ss.ffffffff
```

## Complex Type
### 数组
```
array<type>, 由一系列类型相同的元素构成，索引从0开始．
	DDL中create table table_name (column_name array<int>)
	创建时, array(val1,val2,...)
	访问时, column_name[0]
```
创建表时，指定数组元素间的间隔符
```
create table emp(
	name string,
	phones array<string>
)
row format delimited
	fields terminated by '\t'
	collection items terminated by ':';
```

### 键值对
```
map<primitive_type, type>, 键值对集合
	DDL中create table table_name (column_name map<int, string>)
	创建时, map(key1,val1,key2,val2,...)
	访问时, column_name[key1]
```
创建表时，指定键值元素间的间隔符
```
create table emp(
	name string,
	phones array<string>
)
row format delimited 
	fields terminated by '\t'
	collection items terminated by ','
	map keys terminated by ':';
```

### 结构体
```
struct<key1:type1, key2:type2,...>, 可以包含不同类型的元素
	DDL中create table table_name (column_name struct<name:string, age:int>)
	创建时, 匿名结构体struct(val1,val2,...)
					命名结构体named_struct(key1,val1,key2,val2,...)
	访问时, column_name.key1
```
创建表时，指定结构体值间的间隔符
```
create table emp(
	name string,
	phones struct<name:string,age:int>
)
row format delimited
	fields terminated by '\t'
	collection items terminated by ':';
```
### 联合体
```
uniontype<type1,type2,...>, 和C语言的union类似, 通过0,1,...访问元素值
	DDL中create table table_name (column_name uniontype<string, int>)
	创建时, create_union(tag,val0,val1,...), tag为元素下标0,1,..., val0,val1...为元素候选值列表
		create_union(1,'val0', 'val1', 'val2') 返回{1:'val1'}
	访问时, column_name[0]
```
创建表时，指定联合体值间的间隔符
```
create table emp(
	name string,
	interests uniontype<string, char(20)>
)
row format delimited
	fields terminated by '\t'
	collection items terminated by ':';
```
