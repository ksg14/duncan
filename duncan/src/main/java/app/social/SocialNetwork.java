package social;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import java.util.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.geometry.Pos;
import javafx.scene.layout.FlowPane;
import javafx.geometry.Insets;

public class SocialNetwork extends Region
{
    final WebView browser = new WebView();
    final WebEngine webEngine = browser.getEngine();
    public FlowPane flowPane = new FlowPane();

    public static void showBrowser(Stage stage, HBox panel, String site)
    {
        stage.setTitle("Dunsinane Castle");
        Scene scene = new Scene(new SocialNetwork(site, panel),800,700,Color.web("#666970"));
        stage.setScene(scene);
        //scene.getStylesheets().add("webviewsample/BrowserToolbar.css");        
        stage.show();
    }
    public SocialNetwork(String site, HBox panel)
    {
        //apply the styles
        getStyleClass().add("browser");

        // load the web page
        webEngine.load(site);
        panel.setAlignment(Pos.CENTER);

        panel.setPadding(new Insets(0,50,0,100));
        flowPane.getChildren ().addAll(browser, panel);
        flowPane.setVgap(25);

        //add the web view to the scene
        getChildren().add(flowPane);

    }
    private Node createSpacer()
    {
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        return spacer;
    }

    @Override protected void layoutChildren()
    {
        double w = getWidth();
        double h = getHeight()-100;
        layoutInArea(browser,0,0,w,h,0, HPos.CENTER, VPos.CENTER);
    }

    @Override protected double computePrefWidth(double height)
    {
        return 750;
    }

    @Override protected double computePrefHeight(double width)
    {
        return 500;
    }
}
