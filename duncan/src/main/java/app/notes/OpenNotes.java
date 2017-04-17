package notes;

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

public class OpenNotes {

   
    public static void saveNoteInFile(TextField notesText)
    {
        try{
            String userNotes = notesText.getText();
            FileWriter fw = new FileWriter("./src/main/java/app/notes/UserNotes.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(userNotes);
            bw.close();
        }
        catch(IOException ie){}
        
    }

    public static void openNote(Stage stage, VBox panel)
    {
        String currentLine;
        TextField notesText = new TextField("");
        notesText.setPrefWidth(500);
        notesText.setPrefHeight(200);

        try{
            FileReader fr = new FileReader("./src/main/java/app/notes/UserNotes.txt");
            BufferedReader br = new BufferedReader(fr);

            while ((currentLine = br.readLine()) != null) {
                    notesText.setText(currentLine);
                }
            br.close();
        }
        catch(IOException ie){}
        
        FlowPane flowPane = new FlowPane();
        Scene scene;
        
        Button saveNotes = new Button("save");
        stage.setTitle("Dunsinane");
        flowPane.getChildren().addAll(notesText,saveNotes,panel);
        flowPane.setVgap(20);
        scene = new Scene(flowPane);
        saveNotes.setOnAction (e -> {
            saveNoteInFile(notesText);
        });
        
        stage.setScene(scene);
        stage.show();
    }
 }  
    
//   public static void showWeather (Stage stage, HBox panel) {
//     try {
//          // create a new array of 2 strings
//          String[] cmdArray = new String[2];

//          // first argument is the program we want to open
//          cmdArray[0] = "python3";

//          // second argument is a txt file we want to open with notepad
//          cmdArray[1] = "weather.py";

//          // print a message
//          System.out.println("Displaying weather....");

//          // create a process and execute cmdArray and correct environment
//          Process process = Runtime.getRuntime().exec(cmdArray, null);
//          process.waitFor ();
         
//       } catch (Exception ex) {
//          ex.printStackTrace();
//       }
      
//       readWeather( stage,  panel);
//   }

