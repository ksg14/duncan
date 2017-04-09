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


public class openThirdPartyApSceduler extends Application
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
               //TBD : We can schedule it to open facebook or twitter or any other smaller modules.      
               
            }
        };
        timer.schedule(task,50);
    } 
   
}
 