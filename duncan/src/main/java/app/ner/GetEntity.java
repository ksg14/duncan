package ner;

import java.io.*;
import java.net.*;
import java.nio.channels.FileChannel;
import java.io.FileInputStream;
import java.nio.ByteBuffer;

public class GetEntity {
  private static String extractMessage (String jsonString) {
    String entity = "", entityConfidenceStr, label = "", labelConfidenceStr;

    String textDelimToken = "\"confidence\" : ";
    int messageStart = jsonString.indexOf (textDelimToken) + textDelimToken.length();
    int messageStop = jsonString.indexOf (",", messageStart);
    entityConfidenceStr = jsonString.substring (messageStart, messageStop);

    textDelimToken = "\"value\" : \"";
    messageStart = jsonString.indexOf (textDelimToken) + textDelimToken.length();
    messageStop = jsonString.indexOf ("\"", messageStart);
    entity = jsonString.substring (messageStart, messageStop);

    textDelimToken = "\"confidence\" : ";
    messageStart = jsonString.indexOf (textDelimToken, messageStop) + textDelimToken.length();
    messageStop = jsonString.indexOf (",", messageStart);
    labelConfidenceStr = jsonString.substring (messageStart, messageStop);

    textDelimToken = "\"value\" : \"";
    messageStart = jsonString.indexOf (textDelimToken, messageStop) + textDelimToken.length();
    messageStop = jsonString.indexOf ("\"", messageStart);
    label = jsonString.substring (messageStart, messageStop);

    // System.out.println(entity + " " + label);
    // return jsonString.substring (messageStart, messageStop);
    return label + "," + entity;
  }
  public static String callNER(String userQuery) {
    // String userQuery = "open facebook";
    String processedQuery = userQuery.replace (" ", "%20");
    String url = "https://api.wit.ai/message";
    String key = "OU5YMP53HCXJTM7CWWLQPFXX57YYCPAO";

    String param1 = "17/04/2017";
    String charset = "UTF-8";

    try {
      String versionQuery = String.format("v=%s", URLEncoder.encode(param1, charset));
      String query = String.format("q=%s", URLEncoder.encode(processedQuery, charset));
      URLConnection connection = new URL(url + "?" + versionQuery + "&" + query).openConnection();
      connection.setRequestProperty ("Authorization","Bearer " + key);
      connection.setDoOutput(true);

      BufferedReader response = new BufferedReader(new InputStreamReader(connection.getInputStream()));
      String line, witResponse = "";
      while((line = response.readLine()) != null) {
          witResponse += line;
      }
      System.out.println(witResponse);
      return extractMessage (witResponse);
    } catch (UnsupportedEncodingException e) {
      System.out.println("Error in calling NER module. UnsupportedEncodingException");
    } catch (MalformedURLException e) {
      System.out.println("Error in calling NER module. MalformedURLException");
    } catch (IOException e) {
      System.out.println("Error in calling NER module. IOException");
    }
    return "false";
  }
}
