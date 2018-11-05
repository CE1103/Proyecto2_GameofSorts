package org.ce1103.gos.entities;

import java.util.concurrent.ThreadLocalRandom;

import org.ce1103.gos.view.GameViewManager;
import javafx.scene.image.ImageView;



public class Player {
	
	public static ImageView griffin;
	public static ImageView bullet;
	
	public static int lifes;
	public static int shields;
	public static int speed;
	public static int bulletSpeed;
	public static int bulletSize;
	
	public static int generalLevel;
	public static int lifeLevel;
	public static int speedLevel;
	public static int bulletSpeedLevel;
	public static int bulletSizeLevel;


	
	public static final int playerRadius = 20;
	public static final int timeToChangeAnimation = 50;
	public static final int timeToBlink = 40;
	public static final int timeToShieldActive = 400;
	public static double bulletRadius = bulletSize/5;
	
	public static double currentTimeToChangeAnimation;
	public static double currentTimeToBlink;
	public static double currentTimeToShieldActive;

	public static boolean upPressed;
	public static boolean downPressed;
	public static boolean leftPressed;
	public static boolean rightPressed;
	public static boolean shootPressed;
	
	public static boolean bulletExists = false;
	public static boolean shieldActive = false;
	public static boolean damaged = false;
	
	public final static String bulletRoot = "org/ce1103/gos/view/graphicResources/PlayerBullet.png";
	public final static ImageView griffinStandRoot = new ImageView("org/ce1103/gos/view/graphicResources/Player1.png");
	public final static ImageView griffinFlyingRoot = new ImageView("org/ce1103/gos/view/graphicResources/Player2.png");
	public final static ImageView griffinBlinkingRoot = new ImageView("org/ce1103/gos/view/graphicResources/Player3.png");
	
	
	public static void createGriffin() {
		Player.griffin = Player.griffinStandRoot;
		Player.griffin.setLayoutX(100);
		Player.griffin.setLayoutY(250);
		Player.griffin.setFitWidth(60);
		Player.griffin.setFitHeight(60);
		
		Player.lifes=5;
		Player.shields=3;
		Player.speed=5;
		Player.bulletSpeed=8;
		Player.bulletSize=25;
		Player.generalLevel=1;
		Player.lifeLevel=0;
		Player.currentTimeToChangeAnimation = 0;
		Player.currentTimeToBlink = 0;
	}
	
	
	public static void changeSpriteAnimation(double positionX, double positionY, String animationType) {
		if(animationType.equals("Normal")) {
			if(Player.griffin == Player.griffinStandRoot) {
				Player.griffin = Player.griffinFlyingRoot;
				Player.griffin.setFitWidth(55);
				Player.griffin.setFitHeight(55);
			}else{
				Player.griffin = Player.griffinStandRoot;
				Player.griffin.setFitWidth(60);
				Player.griffin.setFitHeight(60);
			}
			Player.griffin.setLayoutX(positionX);
			Player.griffin.setLayoutY(positionY);
		}else {
			if(Player.griffin == Player.griffinStandRoot || Player.griffin == Player.griffinFlyingRoot) {
				Player.griffin = Player.griffinBlinkingRoot;
			}else{
				int randomAnimationImage = ThreadLocalRandom.current().nextInt(1, 3);
				if(randomAnimationImage==1) {
					Player.griffin = Player.griffinStandRoot;
				}else {
					Player.griffin = Player.griffinFlyingRoot;
				}
			}
			Player.griffin.setLayoutX(positionX);
			Player.griffin.setLayoutY(positionY);
		}
	}
	

	public static void moveDragonAnimation() {
		if((Player.leftPressed && !Player.rightPressed)) {
			if(Player.griffin.getLayoutX() > 0) {
				Player.griffin.setLayoutX(Player.griffin.getLayoutX()-Player.speed);
			}
		}
		
		if(Player.rightPressed && !Player.leftPressed) {
			if(Player.griffin.getLayoutX() < 800) {
				Player.griffin.setLayoutX(Player.griffin.getLayoutX()+Player.speed);
			}
		}
		
		if(Player.downPressed && !Player.upPressed) {
			if(Player.griffin.getLayoutY() < 705) {
				Player.griffin.setLayoutY(Player.griffin.getLayoutY()+Player.speed);
			}
		}
		
		if(Player.upPressed && !Player.downPressed) {
			if(Player.griffin.getLayoutY() > 0) {
				Player.griffin.setLayoutY(Player.griffin.getLayoutY()-Player.speed);
			}
		}
	}

	
	public static void movePlayerBulletAnimation() {
		if (Player.bulletExists) {
			Player.bullet.setFitWidth(bulletSize);
			Player.bullet.setFitHeight(bulletSize/2);
			if(Player.bullet.getLayoutX() > (GameViewManager.width-10)) {
				Player.bulletExists = false;
				GameViewManager.gamePane.getChildren().remove(Player.bullet);
			}
			Player.bullet.setLayoutX(Player.bullet.getLayoutX()+Player.bulletSpeed);
			
		}
	}
	
}