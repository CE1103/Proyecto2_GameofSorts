package org.ce1103.gos.view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Random;

import org.ce1103.gos.entities.Player;
import org.ce1103.gos.util.KeyListeners;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class GameViewManager {
	
	public static AnchorPane gamePane;
	public static Scene gameScene;
	private Stage gameStage;
	
	private static final Dimension displaySettings = Toolkit.getDefaultToolkit().getScreenSize();
	public static final int width = (int)displaySettings.getWidth();
	private static final int height = (int)displaySettings.getHeight();
	private Stage menuStage;
	private AnimationTimer gameTimer;	
	
	Random generateRandomPos;
	
	public GameViewManager() {
		this.startGameStage();
		KeyListeners.createKeyListeners();
		this.generateRandomPos = new Random();
	}

	private void startGameStage() {
		gamePane = new AnchorPane();
		gameScene = new Scene(gamePane, width, height);
		gameStage = new Stage();
		gameStage.setScene(gameScene);
	}
	
	public void createNewGame(Stage menuStage) {
		this.menuStage = menuStage;
		this.menuStage.hide();
		Player.crearDragon();
		gamePane.getChildren().add(Player.dragon);
		createGameLoop();
		gameStage.show();
	}
	
	private void createGameLoop() {
		gameTimer = new AnimationTimer() {
			
			public void handle(long now) {
				Player.moveDragon();
				Player.moveBullet();
			}
	
		};
		gameTimer.start();
	}
	

}
