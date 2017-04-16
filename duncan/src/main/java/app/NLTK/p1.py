import sys


file = open("trainingSet.txt", "r")

with open('trainingSet.txt') as f:

    tokenized = read_by_tokens(f)

    # read first two tokens separately
    first_token = next(tokenized)
    second_token = next(tokenized)
    print (first_token + "  " + second_token)
    # for token in tokenized:
    #     # loops over all tokens *except the first two*
    #     print(token)