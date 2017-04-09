import java.io.IOException;
import java.net.URL;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.animation.*;
import javafx.event.*;
import javafx.scene.control.Label;
import javafx.util.Duration;
import java.util.Calendar;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.*;
import java.io.*;

public class test extends Application 
{

    public void start(Stage stage) throws IOException 
    {
        
        stage.setScene(new Scene(new DigitalClock(), 100, 50));
        stage.show();
   }
}

class DigitalClock extends Label 
{

  public DigitalClock() 
  {
    bindToTime();
  }
public void func()
{
    System.out.println("hii");
}
  private void bindToTime() 
  {
    Timeline timeline = new Timeline
    (
      new KeyFrame(Duration.seconds(0),
      new EventHandler<ActionEvent>() 
      {
                public void handle(ActionEvent actionEvent) 
                {
                    Calendar time = Calendar.getInstance();
                    String hourString = StringUtilities.pad(0, ' ', time.get(Calendar.HOUR) == 0 ? "12" : time.get(Calendar.HOUR) + "");
                    String minuteString = StringUtilities.pad(0, '0', time.get(Calendar.MINUTE) + "");
                    String secondString = StringUtilities.pad(0, '0', time.get(Calendar.SECOND) + "");
                    String ampmString = time.get(Calendar.AM_PM) == Calendar.AM ? "AM" : "PM";
                    setText(hourString + ":" + minuteString + ":" + secondString + " " + ampmString);
                    String str = hourString + ":" + minuteString + ":" + secondString + " " + ampmString;
                    String inp = "1:35:00" + "  " + "AM";
                    System.out.println(str);
                    String mysound = "text.mp3";
                    if(str == inp)
                    {
                    func();
                    }
               }
        }
      ),
      new KeyFrame(Duration.seconds(1))
    );
    //timeline.setCycleCount(Animation.INDEFINITE);
    timeline.play();
  }
}

class StringUtilities 
{
  public static String pad(int fieldWidth, char padChar, String s) 
  {
    StringBuilder sb = new StringBuilder();
    for (int i = s.length(); i < fieldWidth; i++) 
    {
      sb.append(padChar);
    }
    sb.append(s);

    return sb.toString();
  }
}