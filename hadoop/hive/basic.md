## Hive是什么？
1. Hive是一个构建于HDFS之上的数据仓库.
2. 提供类SQL的HQL语言，可通过HQL来查询数据仓库中的数据．(HQL会被编译为MapReduce来执行)
3. 通过内嵌的Derby,MySql或其他关系型数据库存储表，列等元数据，在HDFS之上构建数据表，数据列等结构．（数据表对应HDFS的目录，数据列对应HDFS的文件）

## 数据仓库是什么？
数据仓库在技术实现上其实就是一个普通的数据库．但功能上则是有主题，数据不随时间变化，只随时间增多的，用于企业或组织作决策分析的数据库．<br>
也就是**写操作少，读操作多**．

## OLTP和OLAP是什么？
OLTP就是在线事务处理，就是读写操作相当，并且要保证读写操作的一致性等．<br>
OLAP就是在线分析处理，就是写操作少，读操作多，对操作的一致性要求不高．<br>
数据仓库就是OLAP的产品．

## Hive安装
最新版[http://hive.apache.org](http://hive.apache.org)<br>
历史版本[http://archive.apache.org](http://archive.apache.org)<br>

### 运行模式
1. 嵌入模式(Derby)
元信息存储在Derby数据库中，只能创建一个链接．<br>
下载bin目录后，在启用hadoop后，执行`${hive_home}/bin/hive`则会进入嵌入模式了．<br>
2. 本地模式
元信息存储在其他数据库(如MySql)中，Hive和MySql运行在同一台服务器中．
3. 远程模式(生产环境中)
元信息存储在其他数据库(如MySql)中，Hive和MySql运行在不同的服务器中．
下载bin目录后，在启用hadoop后;<br>
在mysql数据库中创建数据库;<br>
将mysql驱动的jar包保存到`${hive_home}/lib`中;<br>
修改`${hive_home}/conf/hive-site.xml`文件;<br>
```
<?xml version="1.0"?>
<?xml-stylesheet type="text/xsl" href="configuration.xsl"?>
<configuration>
	<property>
		<name>javax.jdo.option.ConnectionURL</name><value>jdbc:mysql://192.168.56.101:3306/hive</value>
		<name>javax.jdo.option.ConnectionDriverName</name><value>com.mysql.jdbc.Driver</value>
		<name>javax.jdo.option.ConnectionUserName</name><value>root</value>
		<name>javax.jdo.option.ConnectionPassword</name><value>password</value>
	</property>
</configuration>
```
执行`${hive_home}/bin/hive`<br>

## 管理方式
### CLI
进入：执行`${hive_home}/bin/hive`或`${hive_home}/bin/hive --service cli`<br>
退出：执行`quit`或`exit`<br>
清屏：`C-l`或`!clear`<br>
查看表：`show tables`<br>
查看内置函数：`show functions`<br>
查看表结构：`desc <table_name>`<br>
查看HDFS下的目录和文件：`dfs -ls`<br>
执行操作系统命令：`!<bash>`<br>
执行HQL文件：`source <filepath>`<br>
进入静默模式：`${hive_home}/bin/hive -S`，就是不产生hive的日志信息，直接输出查询结果<br>
不进入交互模式，直接执行：`${hive_home}/bin/hive -e 'show tables';`<br>

### WebConsole
### 远程服务启动方式
以JDBC或ODBC程序登陆到hive作操作，则必须使用该启动方式．底层使用的是Thrift Server<br>
启动：`${hive_home}/bin/hive --service hiveserver &`<br>
默认端口：10000

## HQL
```
create table <table_name>(
  <column_name> <column_type>,
	......
);
```

## 数据类型
### 基本数据类型
tinyint/smallint/bigint:整型
float/double:浮点型
boolean:布尔类型
decimal:实数类型
string:字符串类型
varchar:从hive0.12.0开始支持,`varchar(20)`表示最大长度为20
char:从hive0.13.0开始支持,`char(20)`表示长度为20
### 复杂数据类型
Array:数组类型，由一系列类型相同的元素构成,`array<float>`
Map:集合类型，保存一条key->value键值对，可以通过key来访问元素,`map<string, float>`，因此多个键值对时`array<map<string, float>>`
Struct:结构类型，可以包含不同类型的元素．通过"点语法"来访问元素,`struct<name:string,age:int,sex:string>`
### 时间类型
Timestamp:从Hive0.8.0开始支持，与时区无关，是一个相对于UNIX诞生时间的偏移量．
查看系统的时间偏移量:`select unix_timestamp();`
Date:从Hive0.12.0开始支持，不含时间

## 存储结构
### 表
Table(内部表)
对应HDFS上的目录,删除表同时会删除表的数据．
```
create table <table_name>(
  <column_name> <type>,
	......
  <column_name> <type>
)
[location '<dir_path>']
[row format delimited fields terminated by ',']
;
```
指定表保存到HDFS的哪个目录,可选项`location`;
指定列分隔符，可选项`row format delimited fields terminated by`，默认分隔符为制表符;
```
-- 创建表并插入数据
create table <table_name>
[row format delimited fields terminated by ',']
as
select * from <table_name>;
```
```
alter table <table_name> add columns(<column_name> <type>);
```
```
drop table <table_name>;
```
Partition(分区表)
对应RMDB的partition列的密集索引．<br>
分区表的每个Partition对应与表下的各自的子目录．<br>
```
create table <table_name>(
  <column_name> <type>,
	......
  <column_name> <type>
)
partitioned by (<column_name> <type>)
[row format delimited fields terminated by ',']
;
```
插入数据
```
insert into table <table_name> partition(<column_name>=<value>)
select * from <table_name> where <column_name> = <value>
;
```
External Table(外部表)
指已经在HDFS中存在的数据，可以创建Partition.
外部表只有一个过程，加载数据和创建表同时完成，并不会移动到数据仓库目录中，只是与外部数据建立一个链接．当删除一个外部表时，仅删除该链接而已．
```
create external table <table_name>(
  <column_name> <type>,
	......
  <column_name> <type>
)
row format delimited fields terminated by ','
location '<dir_path>';
```
Bucket Table(桶表)
对数据进行hash后存放在不同文件中．降低热块，提高查询速度．
```
create table <table_name>(
  <column_name> <type>,
	......
  <column_name> <type>
)
clustered by(<column_name> into 5 buckets);
```
### 视图
```
create view <view_name>
as
<select_statement>
```

## 查看执行计划
```
explain <sql_statement>;
```

## 数据导入
### Load导入语句
```
load data [local] inpath 'filepath' [overwrite]
into table <table_name> [partition(column_name1=val1,column_name2=val2 ...)];
```
`local`，表示从操作系统的目录的导入数据，否则从HDFS下的目录或文件导入．
`<filepath>`可以是文件路径，也可以是目录路径．
### 使用Sqoop导入导出
安装：
1. 下载[http://sqoop.apache.org](http://sqoop.apache.org)，然后解压．
2. 在`.bashrc`设置环境变量
```
export HADOOP_COMMON_HOME=$HADOOP_HOME
export HADOOP_MAPRED_HOME=$HADOOP_HOME
```
3. 把驱动程序jar拷贝到`$sqoop_home/lib/`下
使用示例：
1.导入Oracle数据到HDFS中
```
./sqoop import --connect jdbc:oracle:thin:@129.23.12.12:1521:orcl --username scottt --password pww --table emp --columns 'id,name,age' -m 1 --target-dir '/sqoop/emp'
```
2.导入Oracle数据到Hive中
```
./sqoop import --hive-import --connect jdbc:oracle:thin:@129.23.12.12:1521:orcl --username scottt --password pww --table emp --columns 'id,name,age' -m 1
```
3.导入Oracle数据到Hive中，并指定表名
```
./sqoop import --hive-import --connect jdbc:oracle:thin:@129.23.12.12:1521:orcl --username scottt --password pww --table emp --columns 'id,name,age' -m 1 --hive-table <table_name>
```
4.导入Oracle数据到Hive中，并使用where条件
```
./sqoop import --hive-import --connect jdbc:oracle:thin:@129.23.12.12:1521:orcl --username scottt --password pww --table emp --columns 'id,name,age' -m 1 --hive-table <table_name> --where 'column_name1=val1'
```
5.导入Oracle数据到Hive中，并使用查询语句
```
./sqoop import --hive-import --connect jdbc:oracle:thin:@129.23.12.12:1521:orcl --username scottt --password pww -m 1 --hive-table <table_name> --query 'select * from emp where sal<2000 and $CONDITIONS' --target-dir '/sqoop/emp5'
```
6.导入Hive数据到Oracle中
```
./sqoop export --connect jdbc:oracle:thin:@129.23.12.12:1521:orcl --username scottt --password pww -m 1 --table <oracle_table_name> --export-dir '<hdfs_dir_path>'
```

## 数据查询
查询语法：
```
SELECT [ALL|DISTINCT] select_expr,select_expr, ...
FROM table_reference
[WHERE where_condition]
[GROUP BY col_list]
[CLUSTER BY col_list
	| [DISTRIBUTE BY col_list] [SORT BY col_list]
	| [ORDER BY col_list]
]
[LIMIT number]
```
`select select_expr,select_expr`等同于oracle的`select select_expr,select_expr from dual`
`[DISTRIBUTE BY col_list]`，指定分发器(partitioner)，多Reducer可用．
注意：当表达式中存在NULL，则整个表达式结果为NULL，通过`nvl()`函数将NULL转换为其他有效值．
判断是否为NULL，`is null`或`not is null`

### Fetch Task
从Hive0.10.0开始支持.
对于没有排序，函数的简单查询操作，将不生成MapReduce语句，而是采用fetch task．
3种配置方式:
1. 在hive交互环境中，输入`set hive.fetch.task.conversion=more;`
2. 进入hive交互环境时，输入`hive --hiveconf hive.fetch.task.conversion=more`
3. 配置文件hive-site.xml
```
<property>
	<name>hive.fetch.task.conversion</name>
	<value>more</value>
</property>
```

采用字段别名来排序：在hive交互模式下`set hive.groupby.orderby.position.alias=true;`
### 内置函数
数字：round,ceil,floor
字符：lower, upper, length(字符数), concat, substr, trim, lpand, rpad
收集函数：size
`size(map(<key, value>,<key, value>,.....))
```
size(map('k',1,'b',2)) --结果为2
```
转换函数：cast
`cast(<val> as <type>)`

日期函数：
to_date, year, month, day, weekofyear,datediff,date_add,date_sub
条件函数：
`coalesce`，从左到右返回第一个不为null的值
`case...when...`，条件表达式
```
case a when b then c
[when d then e]*
[else f]
end
```
聚合函数:count, sum, min, max, avg
表生成函数:explode,将map的每个键值对生成一行
```
select explode(map(1,'c1', 2 , 'c2', 3, 'c3'));
```
### 自定义函数(UDF, User Defined Function)
创建
1. 创建一个继承`org.apache.hadoop.hive.sql.UDF`的类
2. 重写`evaluate`函数
3. 打成jar包上传到服务器上
4. 添加jar包，在hive cli交互环境中，`add jar <jar_file_path>`
5. 创建临时函数，在hive cli交互环境中，`create temporary function <function_name> as '<java_class_qualified_name>'`
删除
`drop temporary function <function_name>`

## 表连接
支持等值连接，不等值连接，外连接和自连接.
### 等值连接
```
select ....
from emp e, dept d
where e.deptno = d.deptno;
```
### 不等值连接
```
select ....
from emp e, dept d
where e.sal between d.losal and d.hisal;
```
### 外连接(左外连接，右外连接)
```
select ......
from emp e right outer join dept d on(e.deptno = d.deptno)
```
### 自连接

## 子查询
hive只支持`from`和`where`子句中的子查询
```
where col_name in(select ... from ...)
```
```
where col_name not in(select ... from ...)
```
若子查询结果含null，那么不能用`not in`

## 用Java操作Hive
可通过JDBC或Thrift Client的方式连接Hive服务．
### JDBC方式
1. 拷贝${hive_home}/lib/hive-jdbc.jar到java工程中．
```
// 帮助类
public class JDBCUtils{
	private static String driver = "org.apache.hadoop.hive.jdbc.HiveDriver";
	private static String url = "jdbc:hive://12.23.12.31:10000/default";

	// 注册驱动
	static{
		try{
			Class.forName(driver);
		}
		catch(ClassNotFoundException e){
			throw new ExceptionInInitializerError(e);
		}
	}
	// 获取链接
	public static Connection getConnection(){
		try{
			return DriverManager.getConnection(url);
		}
		catch(SQLException e){
			return null;
		}
	}
	// 释放资源
	public static void release(Connection conn, Statement st, ResultSet rs){
		if(rs != null){
			try{
				rs.close();
			} catch(SQLException e){
				e.printStackTrace();
			}
			finally{
				rs = null;
			}
		}
		if(st != null){
			try{
				st.close();
			} catch(SQLException e){
				e.printStackTrace();
			}
			finally{
				st = null;
			}
		}
		if(conn != null){
			try{
				conn.close();
			} catch(SQLException e){
				e.printStackTrace();
			}
			finally{
				conn = null;
			}
		}
	}
}
```
```
public static void main(String[] args){
	Connection conn = null;
	Statemenet st = null;
	ResultSet rs = null;

	String hql = "select * from emp";
	try{
		conn = JDBCUtils.getConnection();
		st = conn.createStatement();
		rs = st.executeQuery(hql);
		while (rs.next()){
			String name = rs.getString(2);
			double sal = rs.getDouble(6);
			System.out.println(name+"\t"+sal);
		}
	}catch(Exception e){
		e.printStackTrace();
	}
	finally{
		JDBCUtils.release(conn, st, rs);
	}
}
```
注意：缺的jar包可以到`${hadoop_home}/lib`或`${hive_home}/lib`下找到．
### Thrift Client
基于Socket
```
public static void main(String[] args) throws Exception{
	final TSocket tSocket = new TSocket();
	final TProtocal tProtocal = new TBinaryProtocol(tSocket);
	final HiveClient client = new HiveClient(tProtocal);

	tProtocal.open();
	client.execute("desc emp");
	List<String> columns = client.fetchAll();
	for (String col:columns){
		System.out.println(col);
	}

	tSocket.close();
}
```

## 参考资料
hive官网下的hive kiwi.
