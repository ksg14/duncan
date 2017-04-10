import java.util.Date;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.*;
import java.text.*;
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
 
public class alarmScheduler extends Application
{
    Media media;
    MediaPlayer mediaPlayer;
    String mySound;
    
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
                        Format formatter = new SimpleDateFormat("HH:mm:ss");
                        String inp = "17:46:52";
                        if(formatter.format(date).equals(inp))
                        {
                            mySound = "text.mp3";
                            media = new Media(new File(mySound).toURI().toString());
                            mediaPlayer = new MediaPlayer(media);
                            mediaPlayer.play();
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

