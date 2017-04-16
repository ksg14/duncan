# from textblob.classifiers import NaiveBayesClassifier
# from textblob import TextBlob
# import sys

# def classifier(String query):
#     file_name = 'trainingSet.txt'
#     # train = [
#     #     ('play a song', 'PLAY'),
#     #     ('set an alarm for', 'ALARM'),
#     #     ('remind me to', 'ALARM'),
#     #     ('open fb twitter', 'SOCIAL'),
#     #     ('display today', 'WEATHER'),
#     #     ('see headlines', 'NEWS')
#     # ]
#     train = []

#     with open(file_name,'r') as f:
#         for line in f.readlines():
#             x = [i.strip() for i in line.split('\t')]
#             train.append( ( x[1] , x[0]) )
#             #print(x)
#     cl = NaiveBayesClassifier(train)
#     with open('./nltk/classificationLabel.txt','w') as f:
#         f.writelines(c1)

#     # Classify some text
#     # i = 5
#     # while(i > 0):
#     #     inp = raw_input("Enter here: ")
#     #     print(cl.classify(inp))


# if __name__ == '__main__':

#     x = [sys.argv[i] for i in range(1,len(sys.argv))]
#     query = ' '.join(x)
#     classifier(query)
    

        
