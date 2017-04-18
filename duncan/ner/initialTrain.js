var https = require('https');

var getTrainingSet = function () {
  var trainingSet = require("./trainingFile.json");
  // console.log (trainingSet);
  return trainingSet;
}

var initialTrain = function() {

var sendWitExpression = function (jsonBody, headers, method, host, path)
{
  var body = JSON.stringify(jsonBody);

  var options = {
      host: host,
      path: path,
      method: method,
      headers: headers
    };

  var req_callback = function(response)
  {
        var resStr = "";
        response.on('data', function (chunk)
        {
          resStr += chunk;
        });
        response.on('end', function ()
        {
            console.log("Wit res - " + resStr);
            // resStr = JSON.parse(resStr);
       });
        response.on('error', function(err)
        {
            console.log('problem with request: '+ e);
            context.fail(err,{});
        });
  }

  var req = https.request(options, req_callback);

  req.on('error', function(e)
  {
    console.log('problem with request: '+ e);
    context.fail(e,{});
  });

  req.write(body);//send json
  req.end();
};

//Get JSON object
var train = getTrainingSet ();

train.id = "entity";
train.lookups = ["trait"];
// console.log(jsonBody);

var witToken = "Bearer " + "OU5YMP53HCXJTM7CWWLQPFXX57YYCPAO";
var hostUrl = "api.wit.ai";
var urlPath = "/entities?v=17/04/2017";
var headers = {
  "Authorization": witToken,
  "Content-Type": "application/json"
};
var method = "POST";

  sendWitExpression (train, headers, method, hostUrl, urlPath);
};

initialTrain ();
