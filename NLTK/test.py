from textblob.classifiers import NaiveBayesClassifier
from textblob import TextBlob
import sys

train = [
    ('play a song', 'PLAY'),
    ('set an alarm for', 'ALARM'),
    ('remind me to', 'ALARM'),
    ('open fb twitter', 'SOCIAL'),
    ('display today', 'WEATHER'),
    ('see headlines', 'NEWS')
]


cl = NaiveBayesClassifier(train)

# Classify some text
i = 5
while(i > 0):
    inp = raw_input("Enter here: ")
    print(cl.classify(inp)) 

  # "pos"
#print(cl.classify("I don't like their pizza."))   # "neg"

# Classify a TextBlob

