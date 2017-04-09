import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.*;
import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;
import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.canvas.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.image.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import javafx.scene.shape.*;
import javafx.collections.*;
import javafx.geometry.*;
import javafx.event.*;
import java.io.*;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class scheduleModule extends Application
{
    
    
    public void init()
    {
        System.out.println("Program starts!");
    }

    public void stop(){}

    public void start(Stage stage)
    {
        Timer timer = new Timer();
        TimerTask task = new TimerTask()
        
        {   
            public void run()
            {
                Desktop desktop = Desktop.getDesktop();
                //System.out.println(desktop.getTime());     
               /*String mysound = "text.mp3";
               Media media = new Media(new File(mysound).toURI().toString());
               MediaPlayer mediaPlayer = new MediaPlayer(media);
               mediaPlayer.play();*/ 
            }
        };
        timer.schedule(task,50);
    } 
   
}
 