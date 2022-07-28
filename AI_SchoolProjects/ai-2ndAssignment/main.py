import imp
import math
import training
import functions
import pandas  as pd


filename = "C:/Users/Nikos/Desktop/οπα/AI/εργασίες/2nd Assignmentdata/enron1.tar/enron1/ham/0001.1999-12-10.farmer.ham.txt"
dataset = pd.read_csv(filename)
header = str("ID3 tree:")

root = 1; file = "rules.py" 

functions.createFile(file,header)
training.buildDecisionTree(dataset,root,file)

print("Finished building tree.")
