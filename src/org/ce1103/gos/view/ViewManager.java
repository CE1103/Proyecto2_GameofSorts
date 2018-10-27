package org.ce1103.gos.view;

import java.awt.Dimension;
import java.awt.Toolkit;

import org.ce1103.gos.principalwin.Buttons;
import org.ce1103.gos.principalwin.LabelNestedWindow;
import org.ce1103.gos.principalwin.NestedWindow;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.stage.Stage;

public class ViewManager {
	
	private static final Dimension displaySettings = Toolkit.getDefaultToolkit().getScreenSize();
	private static final int width = (int)displaySettings.getWidth();
	private static final int height = (int)displaySettings.getHeight();
	private static ImageView logo;
	
	private AnchorPane mainPane;
	private Scene mainScene;
	private Stage mainStage;
	
	private NestedWindow nestedWinCredits;
	private NestedWindow nestedWinHelp;
	private NestedWindow nestedWinPlay;
	private NestedWindow nestedWinExit;
	
	private NestedWindow activeNestedWin;
	
	
	public ViewManager() {
		mainPane = new AnchorPane();
		mainScene = new Scene(mainPane,width,height);
		mainStage = new Stage();
		mainStage.setScene(mainScene);
		mainStage.setTitle("GAME OF SORTS");
		this.createPlayButton();
		this.createHelpButton();
		this.createCreditsButton();
		this.createExitButton();
		this.createBackground();
		this.createNestedWindow();
		this.createLogo();

	}
	
	
	private void showNestedWindow(NestedWindow nestedWindow) {
		if(activeNestedWin!=null) {
		}
		nestedWindow.moveNestedWindow();
		activeNestedWin = nestedWindow;
	}
	

	private void createNestedWindow() {
		nestedWinCredits = new NestedWindow();
		mainPane.getChildren().add(nestedWinCredits);
		LabelNestedWindow credito1 = new LabelNestedWindow("Angelo Ortiz Vega - 2017239551");
		credito1.setLayoutX(75);
		credito1.setLayoutY(60);
		nestedWinCredits.getPane().getChildren().add(credito1);
		
		LabelNestedWindow credito2 = new LabelNestedWindow("Iván Solís Ávila - 2018167983");
		credito2.setLayoutX(75);
		credito2.setLayoutY(180);
		nestedWinCredits.getPane().getChildren().add(credito2);
		
		LabelNestedWindow credito3 = new LabelNestedWindow("Jonathan Esquivel Sánchez - 2018167983");
		credito3.setLayoutX(75);
		credito3.setLayoutY(300);
		nestedWinCredits.getPane().getChildren().add(credito3);
		
		LabelNestedWindow credito4 = new LabelNestedWindow("Agustín Venegas Vega - 2018250621");
		credito4.setLayoutX(75);
		credito4.setLayoutY(420);
		nestedWinCredits.getPane().getChildren().add(credito4);
		
		LabelNestedWindow credito5 = new LabelNestedWindow("Oscar Araya Garbanzo - 2018002998");
		credito5.setLayoutX(75);
		credito5.setLayoutY(540);
		nestedWinCredits.getPane().getChildren().add(credito5);
		
		
		nestedWinHelp = new NestedWindow();
		mainPane.getChildren().add(nestedWinHelp);
		
		nestedWinPlay = new NestedWindow();
		mainPane.getChildren().add(nestedWinPlay);
		
		nestedWinExit = new NestedWindow();
		mainPane.getChildren().add(nestedWinExit);
	}
	
	public Stage getMainStage() {
		return mainStage;
	}
	
	
	private void createPlayButton() {
		Buttons playButton = new Buttons("Jugar",80,540, 190,61,"Jugar");
		mainPane.getChildren().add(playButton);
		
		playButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				GameViewManager gameManager = new GameViewManager();
				gameManager.createNewGame(mainStage);
			}
				
		});
		
	
	}
	
	private void createHelpButton() {
		Buttons helpButton = new Buttons("Ayuda",80,620, 190,61,"Ayuda");
		mainPane.getChildren().add(helpButton);
		
		
		helpButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				showNestedWindow(nestedWinHelp);
			}
				
		});
	}
	
	private void createCreditsButton() {
		Buttons creditsButton = new Buttons("Créditos",325,540, 190,61,"Créditos");
		mainPane.getChildren().add(creditsButton);
		
		creditsButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				showNestedWindow(nestedWinCredits);
			}
				
		});
	}
		

		
	private void createExitButton() {
		Buttons exitButton = new Buttons("Salir",325,620, 190,61,"Salir");
		mainPane.getChildren().add(exitButton);


		exitButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				mainStage.close();
			}
				
		});
	}
	
	
	private void createBackground() {
		Image backgroundImage = new Image("org/ce1103/gos/view/graphicResources/background.png",width,height,false,true);
		BackgroundImage background = new BackgroundImage(backgroundImage,BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,null);
		mainPane.setBackground(new Background(background));
	}
	
	private void createLogo() {
		logo = new ImageView("org/ce1103/gos/view/graphicResources/logo.png");
		logo.setLayoutX(-15);
		logo.setLayoutY(-15);
		logo.setFitHeight(229);
		logo.setFitWidth(700);
		logo.setOnMouseEntered(new EventHandler<MouseEvent>() {
		
		@Override
		public void handle(MouseEvent event) {
			logo.setEffect(new DropShadow());
		}
		});
		
		logo.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				logo.setEffect(null);
			}
		});
	
		mainPane.getChildren().add(logo);	
	}
	
}
