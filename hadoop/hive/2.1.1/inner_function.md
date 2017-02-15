## 类型转换
```
-- 若转换失败返回NULL
cast(<val> as <type>):<type>

-- 将字符串1转换为bigint的数字类型
cast('1' as bigint)
```

## 聚合函数
```
count
sum
avg
min
max
```

## 默认值
```
-- 若column_name的值为NULL时，返回default_value
nvl(column_name, <any> default_value):<any>
```

## 字符串
```
-- 获取字符串的字符数
length(<string>):<int>
-- 反转字符串
reverse(<string>):<string>
-- 合并字符串
concat(<string>, <string>, ...):<string>
-- 带分隔符合并字符串
concat_ws(<string> separator, <string>, <string>, ...):<string>
-- 转大写
upper(<string>):<string>
-- 转小写
lower(<string>):<string>
-- 删除两端空格
trim(<string>):<string>
ltrim(<string>):<string>
rtrim(<string>):<string>
-- 截断
substr(<string> src, <int> start_idx[, <int> length]):<string>
-- 正则表达式提取子字符串, group_idx为0时则返回匹配的整个字符串，1,2,...则返回对应的匹配组字符串
regexp_extract(<string> src, <string> pattern, <int> group_idx):<string>
-- 分割
split(<string> src, <string> separator):array<string>
-- 重复
repeat(<string> src, <int> n):<string>
```

## 条件
```
-- if...else...语句
if(<boolean> condition, <any> true_value, <any> false_or_null_value)
-- 返回第一个非NULL值，若全为NULL则返回NULL
coalesce(<any> v1, <any> v2, ...)
-- switch语句
case a
	when b then c
	when d then f
	......
	else g
end
```

## 日期
```
-- 将字符合日期格式的字符串'yyyy-MM-dd HH:mm:ss.fffffffff'，转换为date类型
to_date(<string>):<date>
-- 获取年信息
year(<string>):<int>
-- 获取月信息
month(<string>):<int>
-- 获取日信息
day(<string>):<int>
-- 获取时信息
hour(<string>):<int>
-- 获取分信息
minute(<string>):<int>
-- 获取秒信息
second(<string>):<int>
-- 获取所在周数信息
weekofyear(<string>):<int>
-- 返回结束日期-开始日期的天数
datediff(<string> end_date, <string> start_date):<int>
-- 对指定日期增加多少天
date_add(<string>, <int>):<string>
-- 对指定日期减少多少天
date_sub(<string>, <int>):<string>
```

## 时间戳
```
-- 获取当前时区的UNIX时间戳
unix_timestamp():<bigint>
-- 获取当前时区指定日期时间的UNIX时间戳，若转换失败则返回0
unix_timestamp('yyyy-MM-dd HH:mm:ss.fffffffff'):<bigint>
-- 将timestamp转换为指定格式的日期字符串
from_unixtime(<bigint>[, <string> format]):<string>
-- 获取当前日期时间
from_unixtime(unix_timestamp())
```

## 复杂类型函数
```
-- 获取map,struct,array的元素个数
size(<complex_type>):<int>
```

## 特殊函数
```
-- url信息提取函数
parse_url(<string> url, <string> part2extract[, <string> key2extract]):<string>
	part2extract的取值范围是HOST, PATH, QUERY, REF, PROTOCOL, AUTHORITY, FILE, USERINFO
-- json信息提取函数
get_json_object(<string> json_string, <string> path):<string>
```
