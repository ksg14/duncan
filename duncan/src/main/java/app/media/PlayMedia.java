package media;

import java.lang.Runtime;
import java.io.IOException;

public class PlayMedia {
  public static void indexMediaFiles () {
    String command = "python3 ./media/only_indexing.py";
    try {
        Runtime.getRuntime().exec(command);
    } catch (IOException ioe) {
      System.out.println ("Error in executing the only_indexing.py script");
    }
  }
  public static void main(String [] args) {
    indexMediaFiles ();
    // String command =
    // Runtime.getRuntime().exec(command);

  }
}
