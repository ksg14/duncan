import sys

f = open("trainingSet.txt")

while(f.readlines()):
    line = f.readlines()
    print line
    
    # for token in tokenized:
    #     # loops over all tokens *except the first two*
    #     print(token)