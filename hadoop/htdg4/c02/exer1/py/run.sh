#!/usr/bin/env bash
__dirname__=$(dirname "$0")
hadoop dfs -rm -r /output
hadoop jar "$hadoop_home/share/hadoop/tools/lib/hadoop-streaming-2.7.3.jar" \
-input "/input/input_data.txt" \
-output "/output" \
-mapper "$__dirname__/map.py" \
-reducer "$__dirname__/reduce.py"
hadoop dfs -cat /output/part*
