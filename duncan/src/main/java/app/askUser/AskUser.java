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

//Dependencies
import ner.GetEntity;

public class AskUser {
    static String label = "", phrase = "", entity = "";
    public static void askUser(Stage stage, VBox panel, TextField userInputField)
    {
        TextField userRadioInputField = new TextField("Select an action first!");
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

        flowPane.getChildren().addAll(radioPanel, userRadioInputField, saveUserSelection, panel);
        flowPane.setVgap(20);
        scene = new Scene(flowPane);

        social.setOnAction (e -> {
            userRadioInputField.setText("Enter Url / just the host name.");
        });
        media.setOnAction (e -> {
            userRadioInputField.setText("Enter song name.");
        });
        news.setOnAction (e -> {
            userRadioInputField.setText("Enter news category.");
        });
        notes.setOnAction (e -> {
            userRadioInputField.setText("Press Save to proceed.");
        });
        weather.setOnAction (e -> {
            userRadioInputField.setText("Press Save to proceed.");
        });
        //Configure Button
        saveUserSelection.setOnAction (e -> {
          phrase = userInputField.getText ();
          entity = userRadioInputField.getText ();
            if (social.isSelected ()) {
                label = "social";
              }
            else if (media.isSelected ()) {
                label = "media";
                userRadioInputField.setText("Enter song name.");
              }
            else if (news.isSelected ()) {
                label = "news";
                userRadioInputField.setText("Enter news category.");
              }
            else if (notes.isSelected ()) {
                label = "intent";
                entity = "notes";
              }
            else if (weather.isSelected ()) {
                label = "intent";
                entity = "weather";
              }
            phrase = phrase.replace (" ", "%20");
            System.out.println(label + " " +entity + "%40" + phrase);
            GetEntity.addPhraseToTrainSet (label, entity + "%40" + phrase);
        });


        stage.setScene(scene);
        stage.show();
    }
 }
