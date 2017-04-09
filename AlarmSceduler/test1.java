

 
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.*;
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

import java.io.*;
 
public class test1 extends Application
{
public int count = 0;
public void start(Stage stage) throws IOException 
    {
   
 Timeline timeline = new Timeline();
    DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    Date date = new Date();
   
   
    timeline = new Timeline
    (
      new KeyFrame(Duration.seconds(0),
      new EventHandler<ActionEvent>() 
      {
                public void handle(ActionEvent actionEvent) 
                {
                   Date date = new Date();
                    System.out.println(dateFormat.format(date));
                    String inp = "02:43:31";
                    if(inp == dateFormat.format(date))
                    {
                        //actionEvent
                    }
                    
                }
        }
      ),
      new KeyFrame(Duration.seconds(1))
    );
    timeline.setCycleCount(Animation.INDEFINITE);
    timeline.play();
  
    }
    
}

