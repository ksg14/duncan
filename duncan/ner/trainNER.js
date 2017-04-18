var https = require('https');

var getTrainingSet = function () {
  var trainingSet = require("./ner/trainingFile.json");
  // console.log (trainingSet);
  return trainingSet;
}

var getQuery = function () {
  var i, entity = "", phrase;
  // for(i = 3; i < process.argv.length; i++) {
  //   entity += process.argv[i];
  //   if(i != (process.argv.length-1))
  //     entity += " ";
  // }

  // node trainNER.js media starboy%40play%20the%20song%20starboy

  phrase = process.argv[3].substring (process.argv[3].indexOf("%40")+3);
  entity = process.argv[3].substring (0, process.argv[3].indexOf("%40"));

  phrase = phrase.replace (/%20/g, " ");
  entity = entity.replace (/%20/g, " ");
  var queryObj = {
    "label" : process.argv[2],
    "entity" : entity,
    "phrase" : phrase
  };
  // console.log(queryObj);
  return queryObj;
};

var updateFile = function (train) {
  var fs = require('fs')
  fs.truncate('./trainingFile.json', 0, function(){
    // console.log('cleared');
    fs.writeFile('./trainingFile.json', JSON.stringify(train), function(){
      // console.log('Updated');
    });
  });
}

var updateTrainingSetJson = function (train, query) {
  var i, j;
  for(i = 0; i < train.values.length; i++) {
    if(train.values[i].value == query.entity) {
      for(j = 0; j < train.values[i].expressions.length; j++) {
        if(train.values[i].expressions[j] == query.phrase) {
          // console.log (train);
          return train;
        }
      }
      train.values[i].expressions.push (query.phrase);
      // console.log (train);
      return train;
    }
  }
  train.values.push ({
    "value": query.entity,
    "expressions":[
                  query.phrase
                 ],
    "metadata": query.label
  });
  // console.log (train);
  return train;
}

var updateTrainingSet = function(event, context) {
  var createEntity = function () {
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
                // console.log("Wit res - " + resStr);
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
    var train = updateTrainingSetJson (getTrainingSet(), getQuery());
    updateFile (train);

    train.id = "entity";
    train.lookups = ["trait"];
    //  console.log(jsonBody);

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

var deleteEntity = function () {
  var sendWitExpression = function (headers, method, host, path)
  {
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
             // console.log("fb " + chunk.message_id)
            resStr += chunk;
            //console.log(str); //logs FB's response (recipientid and msg id)
          });
          response.on('end', function ()
          {
              // console.log("Wit res - " + resStr);
              // resStr = JSON.parse(resStr);
              createEntity ();
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

    req.end();
  };

  var witToken = "Bearer " + "OU5YMP53HCXJTM7CWWLQPFXX57YYCPAO";
  var hostUrl = "api.wit.ai";
  var urlPath = "/entities/entity?v=17/04/2017";
  var headers = {
    "Authorization": witToken,
  };
  var method = "DELETE";

     sendWitExpression (headers, method, hostUrl, urlPath);
  };

  var getEntity = function () {
    var sendWitExpression = function (headers, method, host, path)
    {
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
               // console.log("fb " + chunk.message_id)
              resStr += chunk;
              //console.log(str); //logs FB's response (recipientid and msg id)
            });
            response.on('end', function ()
            {
                // console.log("Wit res - " + resStr);
                resStr = JSON.parse(resStr);
                var updateTrainObj = {
                  "id" : resStr.name,
                  "lookups" : resStr.lookups,
                  "values" : resStr.values
                }
                updateFile (updateTrainObj);
                deleteEntity();
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

      req.end();
    };

    var witToken = "Bearer " + "OU5YMP53HCXJTM7CWWLQPFXX57YYCPAO";
    var hostUrl = "api.wit.ai";
    var urlPath = "/entities/entity?v=17/04/2017";
    var headers = {
      "Authorization": witToken,
    };
    var method = "GET";

    sendWitExpression (headers, method, hostUrl, urlPath);
  };

  getEntity ();
};

updateTrainingSet ();
