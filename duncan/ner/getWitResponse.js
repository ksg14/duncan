var https = require('https');

var getResponse = function() {
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
            // console.log(resStr.entities.entity[0].confidence);
            // console.log(resStr.entities.entity[0].metadata);//label
            // console.log(resStr.entities.entity[0].value);//entity
            if(resStr.entities.social) {
              var obj = resStr.entities.social;
              console.log(obj[0].confidence);
              console.log("social");
              console.log(obj[0].value);
            }
            else if(resStr.entities.url) {
              var obj = resStr.entities.url;
              console.log(obj[0].confidence);
              console.log("url");
              console.log(obj[0].value);
            }
            // else if(resStr.entities.news && resStr.entities.intent[0].value == "news") {
            else if(resStr.entities.news) {
              var obj = resStr.entities.news;
              console.log(obj[0].confidence);
              console.log("news");
              console.log(obj[0].value);
            }
            else if(resStr.entities.message_body) {
              var obj = resStr.entities.message_body;
              console.log(obj[0].confidence);
              console.log("media");
              console.log(obj[0].value);
            }
            else if(resStr.entities.intent) {
              var obj = resStr.entities.intent;
              console.log(obj[0].confidence);
              console.log(obj[0].value);
              console.log(obj[0].value);
            }
        });
        response.on('error', function(err)
        {
            console.log('problem with request: '+ e);
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

var queryMsg = process.argv[2];

// console.log(queryMsg);
var witToken = "Bearer " + "OU5YMP53HCXJTM7CWWLQPFXX57YYCPAO";
var hostUrl = "api.wit.ai";
var urlPath = "/message?v=17/04/2017&q=" + queryMsg;
var headers = {
  "Authorization": witToken
};
var method = "GET";

  sendWitExpression (headers, method, hostUrl, urlPath);
};

getResponse ();
