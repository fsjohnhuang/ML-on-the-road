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
