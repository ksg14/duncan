package news;

import java.lang.Runtime;
import java.io.IOException;
import java.io.*;
import java.lang.Object;
import java.lang.*;

public class NewsReader {

  public static String getNews()
  {
    try{
           FileReader fr = new FileReader("./src/main/java/app/news/newsfile.txt");
           BufferedReader br = new BufferedReader(fr);
           String s;
           while((s = br.readLine()) != null) {
               System.out.println(s);
               return s;
              }
            }
           catch(Exception e) {
            System.out.println(e);
          }
          return "false";
         
 }


  public static String readNews (String newsCategory) {
    try {
        System.out.println(newsCategory);
         // create a new array of 2 strings
         String[] cmdArray = new String[3];

         // first argument is the program we want to open
         cmdArray[0] = "python";

         // second argument is a txt file we want to open with notepad
         cmdArray[1] = "./src/main/java/app/news/news.py";

         // third argument is the song to be played
         cmdArray[2] = newsCategory;

         System.out.println(cmdArray);
         // print a message
         System.out.println("Displaying news....");

         // create a process and execute cmdArray and correct environment
         Process process = Runtime.getRuntime().exec(cmdArray, null);

         process.waitFor ();
         BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            System.out.println(in.readLine());  
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
         System.out.println("should be working....");
         //response.close();
        return getNews ();
      } catch (Exception ex) {
         ex.printStackTrace();
      }
      return "false";
  }

}
