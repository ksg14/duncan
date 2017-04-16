package driver;

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

public class Dunsinane extends Application {
    private Scene scene;
    private HBox panel = new HBox ();
    private StackPane stackPane = new StackPane ();
    private TextField userInputField = new TextField ("Say something See something!");
    private Button recordButton;
    VoiceR voiceModule = new VoiceR ();
    boolean startRecord;

    @Override
    public void start (Stage stage) {
        //Setting TextField width and height
         userInputField.setPrefHeight (64);
         userInputField.setPrefWidth (500);

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
            //TO-DO extract intents and call modules
            SocialNetwork.showBrowser(stage, panel, "http://m.facebook.com");
          }
        });
        //
        panel.getChildren ().addAll (userInputField, recordButton);
        panel.setAlignment(Pos.CENTER);

        //Set stackPane
        stackPane.getChildren ().add (panel);

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
        stage.setResizable(false);

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
