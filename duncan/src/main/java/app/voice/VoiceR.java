package voice;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;

//Dependencies

public class VoiceR {
   private static final int RECORD_TIME = 3000;//3 sec
   final SoundRecordingUtil recorder = new SoundRecordingUtil ();
   final String audioFilePath = "/home/duncan/duncan_voice/audio.wav";
   File wavFile = new File (audioFilePath);

  public void run () {
      //create a separate thread for recording
      Thread recordThread = new Thread(new Runnable() {
           @Override
           public void run() {
               try {
                   System.out.println("Start recording...");
                   recorder.start();
               } catch (LineUnavailableException ex) {
                   ex.printStackTrace();
                   System.exit(-1);
               }
           }
       });
       recordThread.start();
      //  try {
      //      Thread.sleep(RECORD_TIME);
      //  } catch (InterruptedException ex) {
      //      ex.printStackTrace();
      //  }
  }
  public String stop () {
    try {
         recorder.stop();
         recorder.save(wavFile);
         System.out.println("STOPPED");
     } catch (IOException ex) {
         ex.printStackTrace();
     }
     System.out.println("DONE");
     try{
       //Run Voice to Text module
       return GetTextFromVoice.run (audioFilePath);
     }
     catch (Exception e) {
       System.out.println("Exception occured. " + e);
     }
     return ("I did not understand");
  }
}
