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
import java.lang.*;
import java.util.*;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class gui extends Application
{
    private TextField text1, text2;
    private Label label1,label2;
    private Button bt;
    private StackPane pane;
    private Scene scene;
    
 
    public void init()
    {
    	System.out.println("Program starts!");
    }
    
    public void stop(){}
    
    public void start(Stage stage)
    {
        stage.setTitle("duncanGUI");
        pane = new StackPane();
        VBox hb = new VBox();
        
        bt = new Button("->");

        label1 = new Label("Your Query");
        label2 = new Label("Your URL");
        text1 = new TextField();
        text1.setEditable(false);  

        text2 = new TextField();
        hb.getChildren().addAll(label1,text1,label2,text2,bt);
        hb.setSpacing(10);
        buttonActionHandler c = new buttonActionHandler();
        bt.setOnAction(c);
        scene = new Scene(hb,400,400);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show(); 
    } 
    private class buttonActionHandler implements EventHandler<ActionEvent>
    {
        public void handle(ActionEvent e)
        {
            browserAPI api = new browserAPI();
            api.browserScript(text2.getText());
        }
    }
}
 