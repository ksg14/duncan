import urllib2, urllib, json

baseurl = "https://query.yahooapis.com/v1/public/yql?"
yql_query = "select item.condition from weather.forecast where woeid = 2283386"
yql_url = baseurl + urllib.urlencode({'q':yql_query}) + "&format=json"
result = urllib2.urlopen(yql_url).read()
data = json.loads(result)
dic =  data['query']['results']['channel']['item']['condition']

x =  json.dumps(dic, sort_keys=True)
py_obj = json.loads(x)

data = [py_obj['date'] + '\n' , py_obj['text'] + '\n' , str((int(py_obj['temp']) - 32) * (5/9.0)) + '\n']

with open('weather.txt' , 'w') as f:
    f.writelines(data)
