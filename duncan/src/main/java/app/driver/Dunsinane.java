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
    VoiceR voiceModule = new VoiceR ();
    boolean startRecord;
    private String str;
    String weatherString[] = new String [3];

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
            str = PlayMedia.playSong("");
            if(!str.equals("false"))
              SocialNetwork.showBrowser(stage, panel, str);
            
            //System.out.println(weatherString[0]);
          }
        });
        
        panel.getChildren ().addAll (userInputField, recordButton);
        panel.setAlignment(Pos.CENTER);

        vb.getChildren().addAll(panel,weatherPanel);
        vb.setSpacing(20);
        //Set stackPane
        //stackPane.getChildren ().addAll (panel);
        stackPane.getChildren ().add (vb);

        // stackPane.setFitWidth(getWidth());
        // stackPane.setFitHeight(getHeight());

        stackPane.setPadding(new Insets(50,50,50,50));

        // recordButton.setOnAction (e -> {
        // //   VoiceR voiceModule = new VoiceR ();
        // //   voiceModule.run ();
        //     SocialNetwork.showBrowser(stage, panel, "http://m.facebook.com");
        // });

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
}
