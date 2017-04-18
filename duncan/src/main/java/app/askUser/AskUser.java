package askUser;

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

public class AskUser {

    public static void askUser(Stage stage, HBox panel)
    {

        Button saveUserSelection = new Button("save");
        stage.setTitle("Dunsinane Castle");
        VBox radioPanel = new VBox();
        ToggleGroup group = new ToggleGroup();
        RadioButton social = new RadioButton("social");
        RadioButton media = new RadioButton("media");
        RadioButton news = new RadioButton("news");
        RadioButton notes = new RadioButton("notes");
        RadioButton weather = new RadioButton("weather");
        radioPanel.getChildren().addAll(social,media,news,notes,weather);
        social.setToggleGroup(group);
        media.setToggleGroup(group);
        news.setToggleGroup(group);
        notes.setToggleGroup(group);
        weather.setToggleGroup(group);
        
        //social.setSelected(true);
        FlowPane flowPane = new FlowPane();
        Scene scene;
        radioPanel.setSpacing(20);
        
        flowPane.getChildren().addAll(radioPanel,saveUserSelection,panel);
        flowPane.setVgap(20);
        scene = new Scene(flowPane);

        
        stage.setScene(scene);
        stage.show();
    }
 }  
    

