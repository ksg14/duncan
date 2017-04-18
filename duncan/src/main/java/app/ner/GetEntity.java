package ner;

import java.io.*;
import java.net.*;
import java.nio.channels.FileChannel;
import java.io.FileInputStream;
import java.nio.ByteBuffer;

public class GetEntity {
  public static void addPhraseToTrainSet (String label, String phrase) {
     try {
          // create a new array of 2 strings
          String[] cmdArray = new String[4];

          // first argument is the shell
          cmdArray[0] = "node";
          // second argument is the script
          cmdArray[1] = "./ner/trainNER.js";
          // third argument is the params
          //  cmdArray[2] = "take%20a%20note";
          cmdArray[2] = label;
          cmdArray[3] = phrase;

          System.out.println(label + " " + phrase);
          // create a process and execute cmdArray and correct environment
          Process process = Runtime.getRuntime().exec(cmdArray, null);
          process.waitFor ();
          System.out.println(label + " " + phrase);
       } catch (Exception ex) {
          ex.printStackTrace();
       }
   }
 public static String [] callNER (String query) {
   String processedResponse [] = new String [3];
   processedResponse[0] = "";
   processedResponse[1] = "";
   processedResponse[2] = "";
    try {

         // create a new array of 2 strings
         String[] cmdArray = new String[3];

         // first argument is the shell
         cmdArray[0] = "node";
         // second argument is the script
         cmdArray[1] = "./ner/getWitResponse.js";
         // third argument is the params
        //  cmdArray[2] = "take%20a%20note";
         System.out.println(query.replace (" ", "%20"));
         cmdArray[2] = query.replace (" ", "%20");

         // create a process and execute cmdArray and correct environment
         Process process = Runtime.getRuntime().exec(cmdArray, null);
         BufferedReader response = new BufferedReader(new InputStreamReader(process.getInputStream()));

         String line;
         int i = 0;
         while((line = response.readLine()) != null) {
             processedResponse[i] = line;
             i++;
         }
         process.waitFor ();
         System.out.println(processedResponse[1] + "- -" + processedResponse[2]);
         return processedResponse;
      } catch (Exception ex) {
         ex.printStackTrace();
      }
    return processedResponse;
  }
  public static String sendGetRequest(String userQuery) {
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
      // return extractMessage (witResponse);
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
