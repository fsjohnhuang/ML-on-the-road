#!/usr/bin/env bash
dir_name=$(dirname "$0")

cat "$dir_name/../input_data.txt" | \
"$dir_name/map.py" | \
sort | \
"$dir_name/reduce.py"
