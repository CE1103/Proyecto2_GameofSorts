package org.ce1103.gos.view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.ce1103.gos.entities.DragonEnemy;
import org.ce1103.gos.entities.Player;
import org.ce1103.gos.util.BinaryTree;
import org.ce1103.gos.util.BinaryTreeNode;
import org.ce1103.gos.util.KeyListeners;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class GameViewManager {

	public static AnchorPane gamePane;
	public static Scene gameScene;
	private Stage gameStage;
	private BinaryTree dragonList = new BinaryTree();

	private static final Dimension displaySettings = Toolkit.getDefaultToolkit().getScreenSize();
	public static final int width = (int) displaySettings.getWidth();
	private static final int height = (int) displaySettings.getHeight();
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

		for (int j = 1; j < 11; j++) {
			for (int i = 0; i < 10; i++) {
				DragonEnemy d1 = new DragonEnemy(ThreadLocalRandom.current().nextInt(1, 101),
						ThreadLocalRandom.current().nextInt(1, 4));
				dragonList.addNode(d1);
				gamePane.getChildren().add(d1.eDragon);
			}
		}
	}

	public void moveEnemies(BinaryTreeNode dragon) {

		if (dragon != null) {
			moveEnemies(dragon.getLeft());
			dragon.getDragon().eDragon.setLayoutX(dragon.getDragon().eDragon.getLayoutX() - 0.1);
			moveEnemies(dragon.getRight());
		}

	}

	public void binaryTree(BinaryTreeNode dragon, BinaryTreeNode parent, int distance, int cont) {
		if (dragon != null) {
			if (parent == null) {
				dragon.getDragon().eDragon.setLayoutX(900);
				dragon.getDragon().eDragon.setLayoutY(60);
			}
			cont++;
			binaryTree(dragon.getLeft(), dragon, distance, cont);
			binaryTree(dragon.getRight(), dragon, distance, cont);
			moveBinaryTree(dragon, parent, distance, cont);
		}

	}

	public void moveBinaryTree(BinaryTreeNode dragon, BinaryTreeNode parent, double distance, int cont) {
		if (cont >= 5)
			distance = 2 * distance / (3 * (Math.pow(2, cont)));
		if (cont > 2 && cont < 5)
			distance = 2 * distance / (2 * (Math.pow(2, cont)));
		if (cont <= 2)
			distance = 2 * distance / ((Math.pow(2, cont) + Math.pow(2, cont - 1)));

		if (parent != null) {
			if (parent.getLeft() == dragon) {
				dragon.getDragon().eDragon
						.setLayoutX(parent.getDragon().eDragon.getLayoutX() - (distance - (distance / 2)));
				dragon.getDragon().eDragon.setLayoutY(parent.getDragon().eDragon.getLayoutY() + 120);
			} else if (parent.getRight() == dragon) {
				dragon.getDragon().eDragon
						.setLayoutX(parent.getDragon().eDragon.getLayoutX() + (distance - (distance / 2)));
				dragon.getDragon().eDragon.setLayoutY(parent.getDragon().eDragon.getLayoutY() + 120);
			}
		}
	}

	private void createGameLoop() {
		gameTimer = new AnimationTimer() {

			public void handle(long now) {
				Player.moveDragon();
				Player.moveBullet();
				binaryTree(dragonList.getRoot(), null, 1000, 0);
				moveEnemies(dragonList.getRoot());
				collisionEnemies(dragonList.getRoot());
			}

		};
		gameTimer.start();
	}

	private double getDistance(double x1, double x2, double y1, double y2) {

		return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));

	}

	private void collisionEnemies(BinaryTreeNode dragon) {

		if (dragon != null) {
			collisionEnemies(dragon.getLeft());
			collision(dragon);
			collisionEnemies(dragon.getRight());
		}

	}

	public void collision(BinaryTreeNode dragon) {
		if (dragon.getDragon().radiusEnemy + Player.radiusPlayer > getDistance(Player.dragon.getLayoutX() + 40,
				dragon.getDragon().eDragon.getLayoutX() + 25, Player.dragon.getLayoutY() + 40,
				dragon.getDragon().eDragon.getLayoutY() + 25)) {
			gamePane.getChildren().remove(dragon.getDragon().eDragon);
			dragonList.deleteNode(dragon.getDragon().getAge());

		}
		if (Player.bulletExists) {
			if (dragon.getDragon().radiusEnemy + Player.radiusBullet > getDistance(Player.bullet.getLayoutX() + 40,
					dragon.getDragon().eDragon.getLayoutX() + 25, Player.bullet.getLayoutY() + 20,
					dragon.getDragon().eDragon.getLayoutY() + 25)) {
				gamePane.getChildren().remove(Player.bullet);
				Player.bulletExists = false;
				dragon.getDragon().setResistance(dragon.getDragon().getResistance() - 1);
				if (dragon.getDragon().getResistance() == 0) {
					gamePane.getChildren().remove(dragon.getDragon().eDragon);
					dragonList.deleteNode(dragon.getDragon().getAge());
				}
			}
		}
	}

}
