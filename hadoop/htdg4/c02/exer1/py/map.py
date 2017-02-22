#!/usr/bin/env python
#-*- coding:utf8 -*-
import sys
import re

MISSING = 9999
for record in sys.stdin:
    record = record.strip()
    (year, temperature, quality) = (record[15:19], int(record[87:92]), record[92:93])
    if MISSING != temperature and re.match(r'[01459]',quality):
        print('{0}\t{1}'.format(year, temperature))
