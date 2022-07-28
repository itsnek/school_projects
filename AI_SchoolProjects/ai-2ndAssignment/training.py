import math
import imp
import functions
import os.path
import csv
import pandas  as pd
import numpy as np


def calculateEntropy(data):

    entropy = 0

    rows = data.shape[0]
    columns = data.shape[1]

    decisions = data["Decision"].value_counts().keys().tolist()
    
    for i in range(0, len(decisions)):

        decision = decisions[i]

        num_of_decisions = data['Decision'].value_counts().tolist()[i]
        
        class_probability = num_of_decisions/rows
        
        entropy = entropy - class_probability*math.log(class_probability, 2)
        
    return entropy


def findDecision(data) :
    
    #entropy = 0

    entropy = calculateEntropy(data)

    rows = data.shape[0]
    columns = data.shape[1]

    gains = []

    for i in range(0, columns-1):

        column_name = data.columns[i]

        classes = data[column_name].value_counts()

        gain = entropy

        for j in range(0, len(classes)):

            current_class = classes.keys().tolist()[j]
			
            subdataset = data[data[column_name] == current_class]
			
            subset_rows = len(subdataset)
            class_probability = subset_rows/rows

            subset_entropy = calculateEntropy(subdataset)
            gain = gain - class_probability * subset_entropy
   		
    
    gains.append(gain)

    best_gain_index = gains.index(max(gains))
    best_choice = data.columns[best_gain_index]

    return best_choice


def buildDecisionTree(data,root,file):
    data_copy = list(data)

    #data_copy = data.copy()

    best_choice = findDecision(data)

    columns = data.shape[1]

    j = 0

    for i in range(0, columns-1) :

        #Finding again best_gain_index,due to dropped columns
        if i == best_choice :
            best_gain_index == j
        j = j + 1 

        #Restore columns
        column_name = data.columns[i]
        if column_name != winner_name:
            data[column_name] = data_copy[column_name]
	
    classes = data[best_choice].value_counts().keys().tolist()
    
    for i in range(0,len(classes)):
        
        current_class = classes[i]
        subdataset = data[data[best_choice] == current_class]
        subdataset = subdataset.drop(columns=[winner_name])

        finalBuilding = False

        if len(subdataset['Decision'].value_counts().tolist()) == 1:
            final_decision = subdataset['Decision'].value_counts().keys().tolist()[0] #all items are equal in this case
            terminateBuilding = True
        if subdataset.shape[1] == 1: #if decision cannot be made even though all columns dropped
            final_decision = subdataset['Decision'].value_counts().idxmax() #get the most frequent one
            terminateBuilding = True

        #-----------------------------------------------------------------------------------------------------------------

        if terminateBuilding == True: #check decision is made
            functions.storeRule(file,(functions.formatRule(root+1,str(final_decision))))
			
        else: #decision is not made, continue to create branch and leafs
            root = root + 1 #the following rule will be included by this rule. increase root
            buildDecisionTree(subdataset,root,file)

    return file 