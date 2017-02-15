## 查询语句格式
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
1. `select select_expr,select_expr`等同于oracle的`select select_expr,select_expr from dual`
2. 注意：当表达式中存在NULL，则整个表达式结果为NULL，通过`nvl()`函数将NULL转换为其他有效值．
3. ???????`[DISTRIBUTE BY col_list]`，指定分发器(partitioner)，多Reducer可用．???????

### 联表查询
```
-- 笛卡尔积
select *
from <table_a> a, <table_b> b
where a.col = b.col;

select *
from <table_a> a, <table_b> b
where a.col between b.col1 and b.col2;
```
```
-- 左外连接
select *
from <table_a> a left outer join <table_b> b on a.col = b.col
-- 右外连接
select *
from <table_a> a right outer join <table_b> b on a.col = b.col
-- 外连接
select *
from <table_a> a full outer join <table_b> b on a.col = b.col
```

## 关系运算符
`=`,`<>`,`>`,`<`,`>=`,`<=`,`is null`, `is not null`, `in`, `not in`<br>
`like`,模糊查询,`%`表示匹配0~1个任意字符，`_`表示匹配1个任意字符，转义字符`\\_`和`\\%`表示下划线和百分号本身<br>
'rlike'和`regexp`,java正则表达式,例如`rlike '^d4?'`<br>

## 算数运算符
`+`,`-`, `*`, `/`, `%`, `&`, `|`, `~`, `^`

## 逻辑运算符
`and`, `or`, `not`

## 子查询
只支持`from`和`where`子句下的子查询．并且子查询需要用括号包裹．
```
where column_name in (select ... )
```
若子查询结果含`NULL`,那么不能使用`not in`．
