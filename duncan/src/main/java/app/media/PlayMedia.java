package media;

import java.lang.Runtime;
import java.io.IOException;
import java.io.*;

public class PlayMedia {
  public static void playSong (String songName) {
    try {
         // create a new array of 2 strings
         String[] cmdArray = new String[3];

         // first argument is the program we want to open
         cmdArray[0] = "python3";

         // second argument is a txt file we want to open with notepad
         cmdArray[1] = "./media/song_search.py";

         // third argument is the song to be played
         cmdArray[2] = "delhi de sardar boys";

         // print a message
         System.out.println("Playing....");

         // create a process and execute cmdArray and currect environment
         Process process = Runtime.getRuntime().exec(cmdArray, null);
        //  BufferedReader response = new BufferedReader(new InputStreamReader(process.getInputStream ()));
         //
        //  String line, outputLine = "";
        //  while((line = response.readLine()) != null) {
        //      outputLine += line;
        //  }
        //  System.out.println("Op  "+outputLine);

         process.waitFor ();
        //  playSong ("StartBoy");
         // print another message
         System.out.println("should be working....");
      } catch (Exception ex) {
         ex.printStackTrace();
      }
  }
  public static void indexMediaFiles () {
    //String command = "python3 ./media/only_indexing.py";

    // Thread playThread = new Thread(new Runnable() {
    //      @Override
    //      public void run() {
    //          try {
    //              System.out.println("Playing...");
    //              Runtime.getRuntime().exec(command);
    //          } catch (IOException ioe) {
    //            System.out.println ("Error in executing the only_indexing.py script");
    //          }
    //      }
    //  });
    //  playThread.start();

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

  public static void main(String [] args) {
    playSong ("Starboy");
    // indexMediaFiles ();
    // String command =
    // Runtime.getRuntime().exec(command);

    }
  }