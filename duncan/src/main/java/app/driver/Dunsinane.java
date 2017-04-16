package driver;

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
import javafx.scene.input.MouseEvent;

//Driver Dependencies
import voice.VoiceR;
import social.SocialNetwork;
import ner.GetEntity;
import media.PlayMedia;
import weather.ShowWeather;

public class Dunsinane extends Application {
    private Scene scene;
    private HBox panel = new HBox ();
    private HBox weatherPanel = new HBox();
    private VBox vb = new VBox();
    private StackPane stackPane = new StackPane ();
    private TextField userInputField = new TextField ("Say something See something!");
    private TextField t1 = new TextField();
    private TextField t2 = new TextField();
    private TextField t3 = new TextField();
    private Button recordButton;
    private Button textButton;
    VoiceR voiceModule = new VoiceR ();
    boolean startRecord;
    private String str;
    String weatherString[] = new String [3];
    private String lastOpenedFeed = "";

    @Override
    public void start (Stage stage) {
        //Setting TextField width and height
        userInputField.setPrefHeight (64);
        userInputField.setPrefWidth (500);
        weatherString = ShowWeather.showWeather();
        t1.setText(weatherString[0]);
        t2.setText(weatherString[1] + "weather");
        t3.setText(weatherString[2] + "celcius");
        t1.setEditable(false);
        t2.setEditable(false);
        t3.setEditable(false);
        weatherPanel.getChildren().addAll(t1,t2,t3);

        //Get MIC icon
        final String micImagePath = "https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcRP-9pKSMlhW0nRBhRII2SRET6x8n7PENcdYMPX_iN3xDsNFXwGcQ";
        Image micIcon = new Image (micImagePath, 64, 64, false, false, true);

        //Creating Record Button
        recordButton = new Button ("" , new ImageView (micIcon));

        //Creating Text Button
        textButton = new Button ("Go!");

        startRecord = true;
        recordButton.setOnAction (e -> {
          if(startRecord) {
            startRecord = false;
            voiceModule.run ();
          }
          else {
            startRecord = true;
            String userCommand = extractMessage(voiceModule.stop ());
            userInputField.setText (userCommand);
            performTask (userCommand, stage);
          }
        });

        textButton.setOnAction (e -> {
            performTask (userInputField.getText (), stage);
        });
        //
        panel.getChildren ().addAll (userInputField, recordButton, textButton);
        panel.setAlignment(Pos.CENTER);

        vb.getChildren().addAll(panel,weatherPanel);
        vb.setSpacing(20);
        //Set stackPane
        //stackPane.getChildren ().addAll (panel);
        stackPane.getChildren ().add (vb);

        // stackPane.setFitWidth(getWidth());
        // stackPane.setFitHeight(getHeight());

        stackPane.setPadding(new Insets(50,50,50,50));

        //Set Scene
        scene = new Scene (stackPane);
        
        //Stage
        stage.setTitle ("Dunsinane Castle");
        stage.setScene (scene);
        //stage.setResizable(false);

        //Load Stage
        stage.show ();
    }
    private String extractMessage (String jsonString) {
      String textDelimToken = "\"_text\" : \"";
      int messageStart = jsonString.indexOf (textDelimToken) + textDelimToken.length();
      int messageStop = jsonString.indexOf ("\"", messageStart);
      //System.out.println("start-" + messageStart + "stop-" + messageStop);
      return jsonString.substring (messageStart, messageStop);
    }
    private void performTask (String userCommand, Stage stage) {
      //TO-DO extract intents and call modules
      String processedQuery = GetEntity.callNER (userCommand);
      int delimiterIndex = processedQuery.indexOf(",");
      String label = processedQuery.substring(0, delimiterIndex);
      String entity = processedQuery.substring(delimiterIndex + 1);

      System.out.println(label);
      System.out.println(entity);

      //Social
      if(label.equals ("social")) {
        if(entity.equals ("ask")) {
          System.out.println("last opened " + lastOpenedFeed);
          if(lastOpenedFeed.length() > 0)
            SocialNetwork.showBrowser(stage, panel, "https://" + lastOpenedFeed);
          else
            userInputField.setText ("Which feed? Try \"open fb\", \"open twitter\", \"open gmail\", \"open youtube\".");
        }
        else if (entity.length() > 0){
          lastOpenedFeed = entity;
          SocialNetwork.showBrowser(stage, panel, "https://" + entity);
        }
      }
      //Media
      if(label.equals ("media")) {
        //PlayMedia.playSong (entity);
        str = PlayMedia.playSong("");
            if(!str.equals("false"))
              SocialNetwork.showBrowser(stage, panel, str);
            
            //System.out.println(weatherString[0]);
          }
        });
        
        panel.getChildren ().addAll (userInputField, recordButton);
      }
    }
}
