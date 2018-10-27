package org.ce1103.gos.view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.concurrent.ThreadLocalRandom;

import org.ce1103.gos.entities.DragonEnemy;
import org.ce1103.gos.entities.EnemyBullet;
import org.ce1103.gos.entities.Player;
import org.ce1103.gos.util.BulletList;
import org.ce1103.gos.util.BulletNode;
import org.ce1103.gos.util.DragonList;
import org.ce1103.gos.util.DragonNode;
import org.ce1103.gos.util.KeyListeners;
import org.ce1103.gos.util.MusicPlayer;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.stage.Stage;

public class GameViewManager {

	public static AnchorPane gamePane;
	public static Scene gameScene;
	private Stage gameStage;
	
	public static boolean isPaused = false;
	
	public static MusicPlayer musicPlayer;
	
	private DragonList dragonList = new DragonList();
	private BulletList bulletList = new BulletList();

	private static final Dimension displaySettings = Toolkit.getDefaultToolkit().getScreenSize();
	public static final int width = (int)displaySettings.getWidth();
	private static final int height = (int)displaySettings.getHeight();
	
	public static final BackgroundImage gameBackground = new BackgroundImage(new Image("org/ce1103/gos/view/graphicResources/gameBackground.png", width, height, false, true),
			BackgroundRepeat.REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
	
	private Stage menuStage;
	public static AnimationTimer gameTimer;	

	ImageView[] playerLifeImage;
	


	
	
	public GameViewManager() {
		this.startGameStage();
		KeyListeners.createKeyListeners();
	}

	private void startGameStage() {
		gamePane = new AnchorPane();
		gameScene = new Scene(gamePane, width, height);
		gameStage = new Stage();
		gameStage.setScene(gameScene);
		
	}

	public void createNewGame(Stage menuStage) {
		this.menuStage = menuStage;
		gamePane.setBackground(new Background(gameBackground));
	
		this.menuStage.hide();
		Player.createGriffin();
		this.createEnemies();
		MusicPlayer.backgroundMusicPlayer.play();
		MusicPlayer.backgroundMusicPlayer.setVolume(0.8);
		gamePane.getChildren().add(Player.griffin);
		this.createLifesAnimation();
		
		
		createGameLoop();
		gameStage.show();
	}


	private void createLifesAnimation() {
		this.playerLifeImage = new ImageView[Player.lifes];
		for(int i=0; i<playerLifeImage.length; i++) {
			playerLifeImage[i] = new ImageView("org/ce1103/gos/view/graphicResources/PlayerLife.png");
			playerLifeImage[i].setLayoutY(10);
			playerLifeImage[i].setLayoutX(10+(i*30));
			gamePane.getChildren().add(playerLifeImage[i]);
		}
	}
	
	
	private void obtainedDamage(String damageType) {
		int currentLifes = Player.lifes;
		Player.lifes--;
		if(Player.lifes==0) {
			gameStage.close();
			menuStage.show();
			MusicPlayer.backgroundMusicPlayer.stop();
		}
		gamePane.getChildren().remove(playerLifeImage[currentLifes-1]); 
		if(damageType.equals("Collision")) {
			Player.griffin.setLayoutX(5);
			Player.griffin.setLayoutY(height/2-100);
		}
		System.out.println(Player.lifes);
		
	}
	
	
	
	private void createEnemies() {
		for(int j = 1; j<4; j++) {
			for(int i = 0; i<10 ;i++) {
				DragonEnemy d1 = new DragonEnemy(ThreadLocalRandom.current().nextInt(1, 101),ThreadLocalRandom.current().nextInt(1, 4));
				d1.eDragon.setLayoutX(875 + (i*50));
				d1.eDragon.setLayoutY(100 + (j*100));
				dragonList.addDragon(d1);
				gamePane.getChildren().add(d1.eDragon);
			}
		}
	}

	
	public void mainMovement() {
		DragonNode dL = dragonList.firstNode;
		while (dL != null) {
			dL.dragon.eDragon.setLayoutX(dL.dragon.eDragon.getLayoutX() - 0.1);
			dL.dragon.setCurrentShootCharge(dL.dragon.getCurrentShootCharge()+0.07);
			Player.currentTimeToChangeAnimation = Player.currentTimeToChangeAnimation + 0.04;
			if(dL.dragon.getCurrentShootCharge()>dL.dragon.getRechargeSpeed()) {
				dL.dragon.setCurrentShootCharge(0);
				EnemyBullet b1 = new EnemyBullet(dL.dragon.eDragon.getLayoutX(),dL.dragon.eDragon.getLayoutY(),ThreadLocalRandom.current().nextInt(-1, 2));
				bulletList.addBullet(b1);
				b1.eBullet.setLayoutY(dL.dragon.eDragon.getLayoutY());
				b1.eBullet.setLayoutX(dL.dragon.eDragon.getLayoutX());
				gamePane.getChildren().add(b1.eBullet);
			}
			dL = dL.next;
			if(Player.currentTimeToChangeAnimation>Player.timeToChangeAnimation) {
				double playerPositionX = Player.griffin.getLayoutX();
				double playerPositionY = Player.griffin.getLayoutY();

				gamePane.getChildren().remove(Player.griffin);
				Player.changeAnimation(playerPositionX,playerPositionY);
				Player.currentTimeToChangeAnimation=0;
				gamePane.getChildren().add(Player.griffin);
			}
		}	
	}

	
	public void enemyBulletAnimation() {
		BulletNode bL = bulletList.firstNode;
		while(bL != null) {
			bL.bullet.eBullet.setLayoutX(bL.bullet.eBullet.getLayoutX() - 5);
			bL.bullet.eBullet.setLayoutY(bL.bullet.eBullet.getLayoutY()-bL.bullet.getAngle());
			if (bL.bullet.eBullet.getLayoutX()<0) {
				gamePane.getChildren().remove(bL.bullet.eBullet);
				bulletList.removeBulletNode(bL.bullet);
			}
			bL = bL.next;
		}
	}


	private void createGameLoop() {
		gameTimer = new AnimationTimer() {

			public void handle(long now) {
				Player.moveDragon();
				Player.moveBullet();
				mainMovement();
				checkIfEnemiesEscape();
				collisionEnemies();
				colissionBullets();
				enemyBulletAnimation();
			}

		};
		gameTimer.start();
	}
	
	
	private double getDistance(double x1, double x2, double y1 , double y2) {
		return Math.sqrt(Math.pow(x1-x2, 2)+Math.pow(y1-y2, 2));
	}

	

	private void collisionEnemies() {
		DragonNode dL = dragonList.firstNode;
		while (dL != null) {
			if(dL.dragon.radiusEnemy + Player.radiusPlayer > getDistance(Player.griffin.getLayoutX() + 40, dL.dragon.eDragon.getLayoutX() + 25,
					Player.griffin.getLayoutY() + 40, dL.dragon.eDragon.getLayoutY() + 25)) {
				gamePane.getChildren().remove(dL.dragon.eDragon);
				dragonList.removeDragonNode(dL.dragon);
				this.obtainedDamage("Collision");
			}
			if(Player.bulletExists){
				if(dL.dragon.radiusEnemy + Player.radiusBullet > getDistance(Player.bullet.getLayoutX() + 40, dL.dragon.eDragon.getLayoutX() + 25,
						Player.bullet.getLayoutY() + 20, dL.dragon.eDragon.getLayoutY() + 25)) {
					if(dL.dragon.getResistance()>1) {
						dL.dragon.setResistance(dL.dragon.getResistance()-1);
						double positionX = dL.dragon.eDragon.getLayoutX();
						double positionY = dL.dragon.eDragon.getLayoutY();
						gamePane.getChildren().remove(dL.dragon.eDragon);
						if(dL.dragon.getResistance()==2) {
							dL.dragon.eDragon=new ImageView("org/ce1103/gos/view/graphicResources/EnemyDamaged1.png");
						}else{
							dL.dragon.eDragon=new ImageView("org/ce1103/gos/view/graphicResources/EnemyVeryDamaged1.png");
						}
						dL.dragon.eDragon.setLayoutX(positionX);
						dL.dragon.eDragon.setLayoutY(positionY);
						dL.dragon.eDragon.setFitWidth(25);
						dL.dragon.eDragon.setFitHeight(25);
						gamePane.getChildren().add(dL.dragon.eDragon);
					}else{
						gamePane.getChildren().remove(dL.dragon.eDragon);
						dragonList.removeDragonNode(dL.dragon);
						MusicPlayer.enemyKilledSoundPlayer.stop();
						MusicPlayer.enemyKilledSoundPlayer.play();
					}
					gamePane.getChildren().remove(Player.bullet);
					Player.bulletExists = false;
				}
			}
			dL = dL.next;
		}
	
	}
	
	private void colissionBullets() {
		BulletNode bL = bulletList.firstNode;
		while(bL != null) {
			if (bL.bullet.radiusBullet + Player.radiusPlayer > getDistance(Player.griffin.getLayoutX() + 40, bL.bullet.eBullet.getLayoutX() + 10,
					Player.griffin.getLayoutY() + 40, bL.bullet.eBullet.getLayoutY() + 10)) {
				gamePane.getChildren().remove(bL.bullet.eBullet);
				bulletList.removeBulletNode(bL.bullet);
				this.obtainedDamage("Collision");
				
			}
			bL = bL.next;
		}
	}
	
	private void checkIfEnemiesEscape() {
		DragonNode dL = dragonList.firstNode;
		while (dL != null) {
			if(dL.dragon.eDragon.getLayoutX()<0) {
				gamePane.getChildren().remove(dL.dragon.eDragon);
				dragonList.removeDragonNode(dL.dragon);
				this.obtainedDamage("Escape");
			}
			dL = dL.next;
		}
	}


}


