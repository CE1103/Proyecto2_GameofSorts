package org.ce1103.gos.gui;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	
    public static String screen1ID = "main";
    public static String screen1File = "Screen1.fxml";
    
    public static String screen2ID = "OptionScene";
    public static String screen2File = "Screen2.fxml";
    public static String screen3ID = "GameScene";
    public static String screen3File = "Screen3.fxml";
    public static String screen4ID = "InfoScene";
    public static String screen4File = "Screen4.fxml";
    public static String screen5ID = "ConnectAppScene";
    public static String screen5File = "Screen5.fxml";
    
    public static String screen6ID = "PauseScene";
    public static String screen6File = "Screen6.fxml";
    
	@Override
    public void start(Stage primaryStage) {

        ScreensController mainContainer = new ScreensController();
        mainContainer.loadScreen(screen1ID, screen1File);
        mainContainer.loadScreen(screen2ID, screen2File);
        mainContainer.loadScreen(screen3ID, screen3File);
        mainContainer.loadScreen(screen4ID, screen4File);
        mainContainer.loadScreen(screen5ID, screen5File);
        mainContainer.loadScreen(screen6ID, screen6File);
        
        mainContainer.setScreen(screen1ID);
        String css = this.getClass().getResource("application.css").toExternalForm(); 
        mainContainer.getStylesheets().add(css);
        
        Group root = new Group();
        root.getChildren().addAll(mainContainer);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        
        primaryStage.setTitle("GameOfSorts");
        primaryStage.setFullScreen(false);
    }

	public static void main(String[] args) {
		
		launch(args);

	}

}
