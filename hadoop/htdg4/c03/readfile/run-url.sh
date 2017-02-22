#!/usr/bin/env bash
__dirname__=$(dirname "$0")

hadoop jar "$__dirname__/out/artifacts/readfile_jar/readfile.jar" ReadFile "any_value"
