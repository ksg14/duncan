package weather;

import java.lang.Runtime;
import java.io.IOException;
import java.io.*;
import java.lang.Object;
import java.lang.*;
import javafx.scene.control.TextArea;
import javafx.application.*;
import javafx.collections.*;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.canvas.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.text.*;
import javafx.stage.*;
import java.io.*;
import javafx.scene.input.MouseEvent;

public class ShowWeather {

    public static void showOnScene(Stage stage, HBox panel, String [] str)
    {
         FlowPane flowPane = new FlowPane();
        Scene scene;
         Text weatherText = new Text();
        stage.setTitle("Dunsinane Castle");

        weatherText.setText(str[0] + " " + str[1] + " " + str[2]);

        flowPane.getChildren().addAll(weatherText,panel);
        flowPane.setVgap(20);
        scene = new Scene(flowPane);
        stage.setScene(scene);
        //scene.getStylesheets().add("webviewsample/BrowserToolbar.css");
        stage.show();
    }

    public static void readWeather(Stage stage, HBox panel)
    {
        String str[] = new String [3];
        try{
            FileReader fr = new FileReader("./src/main/java/app/weather/weather.txt");    
            BufferedReader br = new BufferedReader(fr);

            String s;
            int i = 0;
            while((s = br.readLine()) != null) {
                str[i] = s;
                i = i + 1;
            }
        }catch(Exception e) {
                System.out.println(e);
            }
        showOnScene(stage, panel, str);
 }

  public static void showWeather (Stage stage, HBox panel) {
    try {
         // create a new array of 2 strings
         String[] cmdArray = new String[2];

         // first argument is the program we want to open
         cmdArray[0] = "python3";

         // second argument is a txt file we want to open with notepad
         cmdArray[1] = "weather.py";

         // print a message
         System.out.println("Displaying weather....");

         // create a process and execute cmdArray and correct environment
         Process process = Runtime.getRuntime().exec(cmdArray, null);
         process.waitFor ();

      } catch (Exception ex) {
         ex.printStackTrace();
      }

      readWeather( stage,  panel);
  }

}
