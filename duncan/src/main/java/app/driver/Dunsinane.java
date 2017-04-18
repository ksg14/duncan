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
import java.io.*;
import javafx.scene.input.MouseEvent;

//Driver Dependencies
import voice.VoiceR;
import social.SocialNetwork;
import ner.GetEntity;
import media.PlayMedia;
import weather.ShowWeather;
import notes.OpenNotes;
import askUser.AskUser;

public class Dunsinane extends Application {
    private Scene scene;
    private HBox panel = new HBox ();
    private HBox weatherPanel = new HBox();
    private HBox buttonPanel = new HBox();
    private VBox vb = new VBox();
    private StackPane stackPane = new StackPane ();

    private TextField userInputField = new TextField ("Say something See something!");
    private Text t1 = new Text();
    private Text t2 = new Text();
    private Text t3 = new Text();
    private Button recordButton;
    private Button textButton;
    private Button yes = new Button("YES");
    private Button no = new Button("NO");

    VoiceR voiceModule = new VoiceR ();
    boolean startRecord;
    private String str;
    String weatherString[] = new String [3];
    private String lastOpenedFeed = "";
    private String lastLabel = "";
    private String lastEntity = "";
    private String lastPhrase = "";


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

        yes.setOnAction (e -> {
          lastPhrase = userInputField.getText().replace (" ", "%20");
          GetEntity.addPhraseToTrainSet (lastLabel, lastEntity + "%40" + lastPhrase);
            // AskUser.askUser(stage,panel);
            //OpenNotes.openNote(stage, panel);
            //performTask (userInputField.getText (), stage);
        });
        //
        buttonPanel.getChildren().addAll(yes,no);
        buttonPanel.setSpacing(20);
        panel.getChildren ().addAll (userInputField, recordButton, textButton, buttonPanel);
        panel.setAlignment(Pos.CENTER);

        weatherPanel.getChildren().addAll(t1,t2,t3);

        // vb.getChildren().addAll(panel, weatherPanel);
        vb.getChildren().add(panel);

        vb.setSpacing(20);
        //Set stackPane
        //stackPane.getChildren ().addAll (panel);
        stackPane.getChildren ().add (vb);

        //NOtes set on action buttonhandler
        // notes.setOnAction (e -> {
        //     notes.TakeNotes.openNotes(stage);
        // });

        //saving notes
       /* saveNotes.setOnAction(e -> {
            saveNotesInFile();
        });*/

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
      double confidence = 0.0;
      String label = "", entity = "";

      String [] processedQuery = GetEntity.callNER (userCommand);
      if(processedQuery[0].length() > 0)
        confidence = Double.parseDouble(processedQuery[0]);
      if(processedQuery[1].length() > 0)
        label = processedQuery[1];
      if(processedQuery[2].length() > 0)
        entity = processedQuery[2];

      //Persist Label n entity
      lastLabel = label;
      lastEntity = entity;

      System.out.println(confidence);
      System.out.println(label);
      System.out.println(entity);

      //In case of no response
      if(label.length() <= 0 || confidence <= 0.7 || entity.length() <= 0)
      {
        System.out.println("in here");
      }
      //Social
      else if(label.equals ("social")) {
        if(entity.equals ("ask")) {
          System.out.println("last opened " + lastOpenedFeed);
          if(lastOpenedFeed.length() > 0)
            SocialNetwork.showBrowser(stage, panel, "http://www." + lastOpenedFeed + ".com");
          else
            userInputField.setText ("Which feed? Try \"open fb\", \"open twitter\", \"open gmail\", \"open youtube\".");
        }
        else if (entity.length() > 0){
          lastOpenedFeed = entity;
          SocialNetwork.showBrowser(stage, panel, "http://www." + entity + ".com");
        }
      }
      //Media
      else if(label.equals ("media")) {
        //PlayMedia.playSong (entity);
        System.out.println("called " + entity);
        str = PlayMedia.playSong(entity);
        if(!str.equals("false"))
            SocialNetwork.showBrowser(stage, panel, str);
      }
      //weather

      else if(label.equals ("weather")) {
        ShowWeather.showWeather(stage, panel);
      }

      //Notes
      // if(label.equals("notes")){
      //   OpenNotes.takeNote(stage, panel);
      // }
    }

   /* private void saveNotesInFile()
    {
        try{
            String userNotes = notesText.getText();
            //System.out.println(userNotes);
            FileWriter fr = new FileWriter(".src/main/java/appuserNotes.txt");
            BufferedWriter bw = new BufferedWriter(fr);
            bw.write(userNotes);
            bw.close();
        }
        catch(IOException ie){}

    }*/


}
