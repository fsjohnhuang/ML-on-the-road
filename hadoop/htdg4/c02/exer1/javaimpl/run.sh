#!/usr/bin/env bash
__dirname__=$(dirname "$0")

hadoop dfs -rm -r /output
hadoop jar "$__dirname__/out/artifacts/javaimpl_jar/javaimpl.jar" "MaxTemperature"
hadoop dfs -cat /output/part*
