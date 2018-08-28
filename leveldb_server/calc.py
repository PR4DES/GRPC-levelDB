#!/bin/python

import numpy

f = open("server_log_PM", "r")
lines = f.readlines()
load = []
for i, line in enumerate(lines):
	if(i>2 and i<10):
		print(int(line.split("]")[1]))

f.close()

