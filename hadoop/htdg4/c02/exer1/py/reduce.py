#!/usr/bin/env python
#-*- coding:utf8 -*-
import sys

highest = 0
oldYear = None

for record in sys.stdin:
    (year, temperature) = record.strip().split('\t')
    if oldYear and oldYear != year:
        print('{0}\t{1}'.format(oldYear, highest))
        highest = 0
    oldYear = year
    highest = max(highest, int(temperature))

if oldYear:
    print('{0}\t{1}'.format(oldYear, highest))
