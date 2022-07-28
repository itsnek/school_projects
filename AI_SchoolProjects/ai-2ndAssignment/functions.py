#import numpy as np
import imp
from csv import reader

# Load a CSV file
def load_csv(filename):
	file = open(filename, "r")
	lines = reader(file)
	dataset = list(lines)
	return dataset

def formatRule(root,content):
	resp = ' '
	add = content

	for i in range(0, root):
		resp = add + '   ' #den einai apoluta swsto.
	
	return resp	

def storeRule(file,content):
	f = open(file, "a+")
	f.writelines(content)
	f.writelines("\n")

def createFile(file,content):
	f = open(file, "w")
	f.write(content)