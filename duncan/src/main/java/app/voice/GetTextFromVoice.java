package voice;

import java.io.*;
import java.net.*;
import java.nio.channels.FileChannel;
import java.io.FileInputStream;
import java.nio.ByteBuffer;

public class GetTextFromVoice {
  public static String run(String audioFilePath) throws Exception {
      String url = "https://api.wit.ai/speech";
      String key = "OU5YMP53HCXJTM7CWWLQPFXX57YYCPAO";

      String param1 = "20170203";
      String param2 = "command";
      String charset = "UTF-8";

      String query = String.format("v=%s", URLEncoder.encode(param1, charset));

      URLConnection connection = new URL(url + "?" + query).openConnection();
      connection.setRequestProperty ("Authorization","Bearer " + key);
      connection.setRequestProperty("Content-Type", "audio/wav");
      connection.setDoOutput(true);
      OutputStream outputStream = connection.getOutputStream();
      FileInputStream fileStream = new FileInputStream (audioFilePath);
      FileChannel fileChannel = fileStream.getChannel();
       ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

      while((fileChannel.read(byteBuffer)) != -1) {
          byteBuffer.flip();
          byte[] b = new byte[byteBuffer.remaining()];
          byteBuffer.get(b);
          outputStream.write(b);
          byteBuffer.clear();
      }

      BufferedReader response = new BufferedReader(new InputStreamReader(connection.getInputStream()));
      String line, witResponse = "";
      while((line = response.readLine()) != null) {
          witResponse += line;
      }
      return witResponse;
  }
}
