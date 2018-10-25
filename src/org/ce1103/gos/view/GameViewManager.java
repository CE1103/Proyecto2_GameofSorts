package org.ce1103.gos.view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.ce1103.gos.entities.DragonEnemy;
import org.ce1103.gos.entities.Player;
import org.ce1103.gos.util.List;
import org.ce1103.gos.util.Node;
import org.ce1103.gos.util.KeyListeners;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class GameViewManager {

	public static AnchorPane gamePane;
	public static Scene gameScene;
	private Stage gameStage;
	private List dragonList = new List();

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
		createEnemies();
		gamePane.getChildren().add(Player.dragon);
		createGameLoop();
		gameStage.show();
	}

	private void createEnemies() {

		for(int j = 1; j<11; j++) {
			for(int i = 0; i<10 ;i++) {
				DragonEnemy d1 = new DragonEnemy(ThreadLocalRandom.current().nextInt(1, 101),ThreadLocalRandom.current().nextInt(1, 4));
				d1.eDragon.setLayoutX(875 + (i*50));
				d1.eDragon.setLayoutY(j*60);
				dragonList.addDragon(d1);
				gamePane.getChildren().add(d1.eDragon);


			}
		}
	}

	public void moveEnemies() {
		Node dL = dragonList.firstNode;
		while (dL != null) {

			dL.dragon.eDragon.setLayoutX(dL.dragon.eDragon.getLayoutX() - 0.1);
			dL = dL.next;

		}	
	}


	private void createGameLoop() {
		gameTimer = new AnimationTimer() {

			public void handle(long now) {
				Player.moveDragon();
				Player.moveBullet();	
				moveEnemies();
				collisionEnemies();
			}

		};
		gameTimer.start();
	}

	private double getDistance(double x1, double x2, double y1 , double y2) {

		return Math.sqrt(Math.pow(x1-x2, 2)+Math.pow(y1-y2, 2));

	}

	

	private void collisionEnemies() {
		Node dL = dragonList.firstNode;
		while (dL != null) {

			if(dL.dragon.radiusEnemy + Player.radiusPlayer > getDistance(Player.dragon.getLayoutX() + 40, dL.dragon.eDragon.getLayoutX() + 25,
					Player.dragon.getLayoutY() + 40, dL.dragon.eDragon.getLayoutY() + 25)) {
				gamePane.getChildren().remove(dL.dragon.eDragon);
				dragonList.removeNode(dL.dragon);
				System.out.println("colision");
			}
			if(Player.bulletExists){
				if(dL.dragon.radiusEnemy + Player.radiusBullet > getDistance(Player.bullet.getLayoutX() + 40, dL.dragon.eDragon.getLayoutX() + 25,
						Player.bullet.getLayoutY() + 20, dL.dragon.eDragon.getLayoutY() + 25)) {
					gamePane.getChildren().remove(Player.bullet);
					Player.bulletExists = false;
					gamePane.getChildren().remove(dL.dragon.eDragon);
					dragonList.removeNode(dL.dragon);
				}
			}

			dL = dL.next;

		}	
	}


}


