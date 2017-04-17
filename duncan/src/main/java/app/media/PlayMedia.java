package media;

import java.lang.Runtime;
import java.io.IOException;
import java.io.*;
import java.lang.Object;
import java.lang.*;

public class PlayMedia {

//getUrl() returns url to the Dunsinane to check for the youtube event.
  public static String getUrl()
  {
    try{
           FileReader fr = new FileReader("./media/urlfile.txt");
           BufferedReader br = new BufferedReader(fr);
           String s;
           while((s = br.readLine()) != null) {
              if((s.substring(0,5).equals("https"))) {
                  return s;
              }
              else
                return "false";
            }
          } catch(Exception e) {
            System.out.println(e);
          }
      return "false";
 }
  public static String playSong (String songName) {
    try {
         // create a new array of 2 strings
         String[] cmdArray = new String[3];

         // first argument is the program we want to open
         cmdArray[0] = "python3";

         // second argument is a txt file we want to open with notepad
         cmdArray[1] = "./media/song_search.py";

         // third argument is the song to be played
         cmdArray[2] = songName;

         // print a message
         System.out.println("Playing....");
         getUrl();

         // create a process and execute cmdArray and correct environment
         Process process = Runtime.getRuntime().exec(cmdArray, null);


        //  BufferedReader response = new BufferedReader(new InputStreamReader(process.getInputStream()));

        //  //BufferedWriter bw = new BufferedWriter(response);
        //  //System.out.println(response.readLine());
        //  //System.out.println(process.getInputStream().readLine());
        // //  String line, outputLine = "";

        // //  while((line = response.readLine()) != null) {
        // //      outputLine += line;
        // //  }

        // System.out.println("Op hai ye " + outputLine);

         process.waitFor ();

        //  playSong ("StartBoy");
         // print another message
         System.out.println("should be working....");
         //response.close();
         return getUrl ();
      } catch (Exception ex) {
         ex.printStackTrace();
      }
      return "false";
  }
  public static void indexMediaFiles () {
    try {
         // create a new array of 2 strings
         String[] cmdArray = new String[2];

         // first argument is the program we want to open
         cmdArray[0] = "python3";

         // second argument is a txt file we want to open with notepad
         cmdArray[1] = "./media/only_indexing.py";

         // print a message
         System.out.println("Indexing....");

         // create a process and execute cmdArray and currect environment
         Process process = Runtime.getRuntime().exec(cmdArray, null);
         process.waitFor ();

         // print another message
         System.out.println("should be working....");
      } catch (Exception ex) {
         ex.printStackTrace();
      }
  }

  // //  public static void main (String [] args) {
  // //    playSong ("starboy");
  //  }
}
