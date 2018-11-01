package org.ce1103.gos.view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.concurrent.ThreadLocalRandom;

import org.ce1103.gos.entities.Boss;
import org.ce1103.gos.entities.DragonEnemy;
import org.ce1103.gos.entities.EnemyBullet;
import org.ce1103.gos.entities.Player;
import org.ce1103.gos.principalwin.Buttons;
import org.ce1103.gos.principalwin.LabelNestedWindow;
import org.ce1103.gos.principalwin.NestedWindow;
import org.ce1103.gos.principalwin.NestedWindowForPowerUp;
import org.ce1103.gos.util.BulletList;
import org.ce1103.gos.util.BulletNode;
import org.ce1103.gos.util.DragonList;
import org.ce1103.gos.util.DragonNode;
import org.ce1103.gos.util.KeyListeners;
import org.ce1103.gos.util.MusicPlayer;
import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.util.Duration;




public class GameViewManager {

	public static AnchorPane gamePane;
	public static Scene gameScene;
	public static Stage menuStage;
	public static Stage gameStage;
	public static MusicPlayer musicPlayer;
	public static AnimationTimer gameTimer;	
	
	
	public static boolean isPaused = false;
	public static boolean choosePowerUpIsActive = false;
	public static boolean bossActive = false;
	
	
	private DragonList dragonList = new DragonList();
	private DragonList movementList = new DragonList();
	private BulletList bulletList = new BulletList();

	
	private static final Dimension displaySettings = Toolkit.getDefaultToolkit().getScreenSize();
	public static final int width = (int)displaySettings.getWidth();
	private static final int height = (int)displaySettings.getHeight();
	public static final BackgroundImage gameBackground = new BackgroundImage(new Image("org/ce1103/gos/view/graphicResources/gameBackground.png", width, height, false, true),BackgroundRepeat.REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
	

	private ImageView[] playerLifeImage;
	public static int points=0;
	public static int stage=1;
	public static int currentEnemies;
	public static int currentEnemiesDefeated=0;
	
	
	public static NestedWindow nestedWindowChoosePowerUp = new NestedWindow();

	public static Boss boss;
	
	public GameViewManager() {
		this.startGameStage();
		KeyListeners.createKeyListeners();
	}

	
	private void startGameStage() {
		GameViewManager.gamePane = new AnchorPane();
		GameViewManager.gameScene = new Scene(gamePane, width, height);
		GameViewManager.gameStage = new Stage();
		GameViewManager.gameStage.setScene(gameScene);
	}

	
	public void createNewGame(Stage menuStage) {
		System.out.println(height/2);
		GameViewManager.menuStage = menuStage;
		GameViewManager.menuStage.hide();

		GameViewManager.gamePane.setBackground(new Background(gameBackground));
		MusicPlayer.backgroundMusicPlayer.play();
		MusicPlayer.backgroundMusicPlayer.setVolume(0.8);
		
		Player.createGriffin();
		gamePane.getChildren().add(Player.griffin);
		
		this.createEnemiesAnimation();
		this.createLifesAnimation();
		this.createPointsContainerAnimation();

		this.createGameLoop();
		GameViewManager.gameStage.show();
	}


	private void createLifesAnimation() {
		if(Player.lifeLevel>=1 || Player.damaged) {
			for(int life=1; life<playerLifeImage.length; life++) {
				GameViewManager.gamePane.getChildren().remove(playerLifeImage[life]);
				Player.damaged=false;
			}
		}
		
		this.playerLifeImage = new ImageView[Player.lifes];
		
		for(int life=0; life<playerLifeImage.length; life++) {
			this.playerLifeImage[life] = new ImageView("org/ce1103/gos/view/graphicResources/PlayerLife.png");
			this.playerLifeImage[life].setLayoutY(10);
			this.playerLifeImage[life].setLayoutX(10+(life*30));
			GameViewManager.gamePane.getChildren().add(playerLifeImage[life]);
		}
	}


	private void obtainDamageAnimation(String damageType) {
		if(!Player.shieldActive) {
			Player.lifes--;
		
			if(Player.lifes==0) {
				GameViewManager.gameStage.close();
				GameViewManager.gameTimer.stop();
				GameViewManager.menuStage.show();
				MusicPlayer.backgroundMusicPlayer.stop();
			}
		
			if(damageType.equals("Collision")) {
				Player.griffin.setLayoutX(5);
				Player.griffin.setLayoutY(height/2-100);
			}
			Player.damaged=true;
			createLifesAnimation();
			removeEnemyBulletAnimation();
			Player.shieldActive=true;
		}
	}
	
	
	private void createEnemiesAnimation() {
		for(int column = 1; column<6; column++) {
			for(int row = 0; row<10 ;row++) {
				DragonEnemy enemy = new DragonEnemy(ThreadLocalRandom.current().nextInt(1, 101),ThreadLocalRandom.current().nextInt(1, 4));
				enemy.enemyImage.setLayoutX(875 + (row*50));
				enemy.enemyImage.setLayoutY(150 + (column*50));
				dragonList.addDragon(enemy);
				movementList.addDragon(enemy);
				GameViewManager.gamePane.getChildren().add(enemy.enemyImage);
				GameViewManager.currentEnemies++;
			}
		}
	}
	
	
	private void createPointsContainerAnimation() {
		LabelNestedWindow pointsLabel = new LabelNestedWindow("PUNTOS: " + Integer.toString(GameViewManager.points));
		pointsLabel.setLayoutX(18);
		pointsLabel.setLayoutY(5);
		gamePane.getChildren().add(pointsLabel);
		
		if(GameViewManager.points%300==0 && GameViewManager.points>=300) {
			this.createLevelUpAnimation();
			Player.generalLevel++;
		}
		if(GameViewManager.currentEnemiesDefeated==GameViewManager.currentEnemies) {
			this.bossAppearsAnimation();
		}
	}
	
	
	private void createLevelUpAnimation() {
		MusicPlayer.levelUpSoundPlayer.stop();
		MusicPlayer.levelUpSoundPlayer.play();
		MusicPlayer.backgroundMusicPlayer.setVolume(0.2);
		GameViewManager.choosePowerUpIsActive = true;
		GameViewManager.gameTimer.stop();
		GameViewManager.gamePane.getChildren().remove(nestedWindowChoosePowerUp);
		GameViewManager.gamePane.getChildren().add(nestedWindowChoosePowerUp);
		GameViewManager.nestedWindowChoosePowerUp.moveNestedWindow();
		
		Player.bulletExists=false;
		Player.shootPressed=false;
		
		Buttons powerUpLifeButton = new Buttons("",180,200, 100,100,"PowerUpLife");
		GameViewManager.nestedWindowChoosePowerUp.getPane().getChildren().add(powerUpLifeButton);
		Buttons powerUpShieldButton = new Buttons("",480,200, 100,100,"PowerUpShield");
		GameViewManager.nestedWindowChoosePowerUp.getPane().getChildren().add(powerUpShieldButton);
		Buttons powerUpExtraSpeedButton = new Buttons("",180,400, 100,100,"PowerUpExtraSpeed");
		GameViewManager.nestedWindowChoosePowerUp.getPane().getChildren().add(powerUpExtraSpeedButton);
		Buttons powerUpSuperFlameButton = new Buttons("",480,400, 100,100,"PowerUpSuperFlame");
		GameViewManager.nestedWindowChoosePowerUp.getPane().getChildren().add(powerUpSuperFlameButton);
		
		powerUpLifeButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				if(!(Player.lifes>4)) {
					GameViewManager.gameTimer.start();
					MusicPlayer.backgroundMusicPlayer.setVolume(0.8);
					GameViewManager.nestedWindowChoosePowerUp.moveNestedWindow();
					GameViewManager.choosePowerUpIsActive = false;
					Player.lifes++;				
					Player.lifeLevel++;				
					createLifesAnimation();	
				}else{
					MusicPlayer.cancelSoundPlayer.stop();
					MusicPlayer.cancelSoundPlayer.play();
				}
			}		
		});
		
		powerUpShieldButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				if(!(Player.shieldActive)) {
					GameViewManager.gameTimer.start();
					MusicPlayer.backgroundMusicPlayer.setVolume(0.8);
					GameViewManager.nestedWindowChoosePowerUp.moveNestedWindow();
					GameViewManager.choosePowerUpIsActive = false;
					Player.shieldActive=true;
				}else {
					MusicPlayer.cancelSoundPlayer.stop();
					MusicPlayer.cancelSoundPlayer.play();
				}
			}	
		});
		
		powerUpSuperFlameButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				if(!(Player.bulletSpeedLevel>2)) {
					GameViewManager.gameTimer.start();
					MusicPlayer.backgroundMusicPlayer.setVolume(0.8);
					GameViewManager.nestedWindowChoosePowerUp.moveNestedWindow();
					GameViewManager.choosePowerUpIsActive = false;
					Player.bulletSpeedLevel++;
					Player.bulletSpeed++;
				}else {
					MusicPlayer.cancelSoundPlayer.stop();
					MusicPlayer.cancelSoundPlayer.play();
				}
			}		
		});
		
		powerUpExtraSpeedButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				if(!(Player.speedLevel>2)) {
					GameViewManager.gameTimer.start();
					MusicPlayer.backgroundMusicPlayer.setVolume(0.8);
					GameViewManager.nestedWindowChoosePowerUp.moveNestedWindow();
					GameViewManager.choosePowerUpIsActive = false;
					Player.speedLevel++;
					Player.speed++;
				}else {
					MusicPlayer.cancelSoundPlayer.stop();
					MusicPlayer.cancelSoundPlayer.play();
				}
			}
		});	
	}
	
	
	public void mainMovementAnimation() {
		DragonNode enemyNode = dragonList.firstNode;
		
		while (enemyNode != null) {
			enemyNode.dragon.enemyImage.setLayoutX(enemyNode.dragon.enemyImage.getLayoutX() - 0.1);
			Player.currentTimeToChangeAnimation = Player.currentTimeToChangeAnimation + 0.04;
			
			
			
			if(Player.shieldActive) {
				Player.currentTimeToShieldActive = Player.currentTimeToShieldActive + 0.04;
			}else {
				enemyNode.dragon.setCurrentShootCharge(enemyNode.dragon.getCurrentShootCharge());
			}

			
			
			if(Player.currentTimeToShieldActive>Player.timeToShieldActive) {
				Player.currentTimeToShieldActive=0;
				Player.shieldActive=false;
			}
			
			if(enemyNode.dragon.getCurrentShootCharge()>enemyNode.dragon.getRechargeSpeed()) {
				enemyNode.dragon.setCurrentShootCharge(0);
				EnemyBullet bullet = new EnemyBullet(enemyNode.dragon.enemyImage.getLayoutX(),enemyNode.dragon.enemyImage.getLayoutY(),ThreadLocalRandom.current().nextInt(-1, 2));
				bullet.bulletImage.setLayoutY(enemyNode.dragon.enemyImage.getLayoutY());
				bullet.bulletImage.setLayoutX(enemyNode.dragon.enemyImage.getLayoutX());
				
				this.bulletList.addBullet(bullet);
				GameViewManager.gamePane.getChildren().add(bullet.bulletImage);
			}
			enemyNode = enemyNode.next;
		}	
	}
	
	
	public void playerAnimation() {
		DragonNode enemyNode = movementList.firstNode;
		
		while (enemyNode != null) {
			
			if(GameViewManager.bossActive) {
				if(boss.bossStand.getLayoutY()<800){
					boss.bossStand.setLayoutY(boss.bossStand.getLayoutY()+0.06);
				}else {
					boss.bossStand.setLayoutY(boss.bossStand.getLayoutY()-1000);
				}
				
				GameViewManager.boss.currentShootCharge+=0.035;

				if(GameViewManager.boss.currentShootCharge>GameViewManager.boss.rechargeSpeed) {
					GameViewManager.boss.currentShootCharge=0;
					for(int i=0; i<9; i++) {
					EnemyBullet bullet = new EnemyBullet(GameViewManager.boss.bossStand.getLayoutX(),GameViewManager.boss.bossStand.getLayoutY(),i-7);
					bullet.bulletImage.setLayoutY(GameViewManager.boss.bossStand.getLayoutY()+180);
					bullet.bulletImage.setLayoutX(GameViewManager.boss.bossStand.getLayoutX());
					
					this.bulletList.addBullet(bullet);
					GameViewManager.gamePane.getChildren().add(bullet.bulletImage);
					}
				}
				
			}
			
			double playerPositionX = Player.griffin.getLayoutX();
			double playerPositionY = Player.griffin.getLayoutY();
			Player.currentTimeToChangeAnimation += 0.03;
			Player.currentTimeToBlink+=0.03;
			
			if(Player.shieldActive && Player.currentTimeToBlink>Player.timeToBlink) {
				Player.currentTimeToBlink=0;
				GameViewManager.gamePane.getChildren().remove(Player.griffin);
				Player.changeSpriteAnimation(playerPositionX,playerPositionY,"Blink");
				GameViewManager.gamePane.getChildren().add(Player.griffin);
			}
			if(Player.currentTimeToChangeAnimation>Player.timeToChangeAnimation) {
				Player.currentTimeToChangeAnimation=0;
				GameViewManager.gamePane.getChildren().remove(Player.griffin);
				Player.changeSpriteAnimation(playerPositionX,playerPositionY,"Normal");
				GameViewManager.gamePane.getChildren().add(Player.griffin);
			}
			enemyNode = enemyNode.next;
		}	
	}
	
	public void removeEnemyBulletAnimation() {
		BulletNode bulletNode = bulletList.firstNode;
		while(bulletNode != null) {
			GameViewManager.gamePane.getChildren().remove(bulletNode.bullet.bulletImage);
			this.bulletList.removeBulletNode(bulletNode.bullet);
			bulletNode = bulletNode.next;
		}
	}

	
	public void enemyBulletAnimation() {
		BulletNode bulletNode = bulletList.firstNode;
		
		while(bulletNode != null) {
			bulletNode.bullet.bulletImage.setLayoutX(bulletNode.bullet.bulletImage.getLayoutX() - 3);
			bulletNode.bullet.bulletImage.setLayoutY(bulletNode.bullet.bulletImage.getLayoutY()-bulletNode.bullet.getAngle());
			
			if (bulletNode.bullet.bulletImage.getLayoutX()<0) {
				GameViewManager.gamePane.getChildren().remove(bulletNode.bullet.bulletImage);
				this.bulletList.removeBulletNode(bulletNode.bullet);
			}
			bulletNode = bulletNode.next;
		}
	}

	
	private double getDistance(double x1, double x2, double y1 , double y2) {
		return Math.sqrt(Math.pow(x1-x2, 2)+Math.pow(y1-y2, 2));
	}


	private void collisionWithEnemyAnimation() {
		DragonNode enemyNode = dragonList.firstNode;
		
		while (enemyNode != null) {
			if(enemyNode.dragon.radiusEnemy + Player.playerRadius > getDistance(Player.griffin.getLayoutX() + 40, enemyNode.dragon.enemyImage.getLayoutX() + 25, Player.griffin.getLayoutY() + 40, enemyNode.dragon.enemyImage.getLayoutY() + 25)  && !Player.shieldActive) {
				GameViewManager.gamePane.getChildren().remove(enemyNode.dragon.enemyImage);
				this.dragonList.removeDragonNode(enemyNode.dragon);
				this.obtainDamageAnimation("Collision");
			}
			
			if(Player.bulletExists){
				if(enemyNode.dragon.radiusEnemy + Player.bulletRadius > getDistance(Player.bullet.getLayoutX() + 40, enemyNode.dragon.enemyImage.getLayoutX() + 25, Player.bullet.getLayoutY() + 20, enemyNode.dragon.enemyImage.getLayoutY() + 25)) {
					if(enemyNode.dragon.getResistance()>1) {
						enemyNode.dragon.setResistance(enemyNode.dragon.getResistance()-1);
						double positionX = enemyNode.dragon.enemyImage.getLayoutX();
						double positionY = enemyNode.dragon.enemyImage.getLayoutY();
						GameViewManager.gamePane.getChildren().remove(enemyNode.dragon.enemyImage);
						if(enemyNode.dragon.getResistance()==2) {
							enemyNode.dragon.enemyImage=new ImageView("org/ce1103/gos/view/graphicResources/EnemyDamaged1.png");
						}else{
							enemyNode.dragon.enemyImage=new ImageView("org/ce1103/gos/view/graphicResources/EnemyVeryDamaged1.png");
						}
						enemyNode.dragon.enemyImage.setLayoutX(positionX);
						enemyNode.dragon.enemyImage.setLayoutY(positionY);
						enemyNode.dragon.enemyImage.setFitWidth(25);
						enemyNode.dragon.enemyImage.setFitHeight(25);
						GameViewManager.gamePane.getChildren().add(enemyNode.dragon.enemyImage);
					}else{
						GameViewManager.gamePane.getChildren().remove(enemyNode.dragon.enemyImage);
						this.dragonList.removeDragonNode(enemyNode.dragon);
						MusicPlayer.enemyKilledSoundPlayer.stop();
						MusicPlayer.enemyKilledSoundPlayer.play();
						this.currentEnemiesDefeated++;
					}
					
					GameViewManager.gamePane.getChildren().remove(Player.bullet);
					GameViewManager.points += 10;
					Player.bulletExists = false;
					this.createPointsContainerAnimation();
					
				}
				if(GameViewManager.bossActive) {
					if(250 + Player.bulletRadius > getDistance(Player.bullet.getLayoutX() + 40, boss.bossStand.getLayoutX() + 300, Player.bullet.getLayoutY() + 20, boss.bossStand.getLayoutY() + 300)) {
						boss.bossLife--;
						System.out.println(boss.bossLife);
						MusicPlayer.enemyKilledSoundPlayer.play();
						GameViewManager.gamePane.getChildren().remove(Player.bullet);
						Player.bulletExists = false;
						
						if(boss.bossLife==0) {
							GameViewManager.gamePane.getChildren().remove(boss.bossStand);
							GameViewManager.bossActive=false;
						}
					}
				}
			}
			enemyNode = enemyNode.next;
		}
	}
	
	
	private void colissionWithEnemyBulletAnimation() {
		BulletNode bulletNode = bulletList.firstNode;
		
		while(bulletNode != null) {
			if (bulletNode.bullet.radiusBullet + Player.playerRadius > getDistance(Player.griffin.getLayoutX() + 40, bulletNode.bullet.bulletImage.getLayoutX() + 10, Player.griffin.getLayoutY() + 40, bulletNode.bullet.bulletImage.getLayoutY() + 10)) {
				GameViewManager.gamePane.getChildren().remove(bulletNode.bullet.bulletImage);
				this.bulletList.removeBulletNode(bulletNode.bullet);
				this.obtainDamageAnimation("Collision");
			}
			bulletNode = bulletNode.next;
		}
	}
	
	
	private void checkIfEnemiesEscapeAnimation() {
		DragonNode enemyNode = dragonList.firstNode;
		
		while (enemyNode != null) {
			if(enemyNode.dragon.enemyImage.getLayoutX()<0) {
				GameViewManager.gamePane.getChildren().remove(enemyNode.dragon.enemyImage);
				this.dragonList.removeDragonNode(enemyNode.dragon);
				this.obtainDamageAnimation("Escape");
			}
			enemyNode = enemyNode.next;
		}
	}
	
	public static void bossAppearsAnimation() {
		MusicPlayer.backgroundMusicPlayer.stop();
		MusicPlayer.enemyKilledSoundPlayer.stop();
		MusicPlayer.bossBackgroundMusicPlayer.play();
		
		GameViewManager.boss = new Boss();
		boss.bossStand.setLayoutX(1100);
		boss.bossStand.setLayoutY(-300);
		boss.bossStand.setFitHeight(300);
		boss.bossStand.setFitWidth(300);
		GameViewManager.gamePane.getChildren().add(boss.bossStand);
		
		
		
		
		bossActive=true;
	}


	private void createGameLoop() {
		GameViewManager.gameTimer = new AnimationTimer() {
			public void handle(long now) {
				Player.moveDragonAnimation();
				Player.movePlayerBulletAnimation();
				mainMovementAnimation();
				checkIfEnemiesEscapeAnimation();
				collisionWithEnemyAnimation();
				colissionWithEnemyBulletAnimation();
				enemyBulletAnimation();
				playerAnimation();
			}
		};
		GameViewManager.gameTimer.start();
	}
}


