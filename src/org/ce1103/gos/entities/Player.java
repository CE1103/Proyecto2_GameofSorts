package org.ce1103.gos.entities;

import org.ce1103.gos.view.GameViewManager;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;

public class Player {
	
	public static ImageView griffin;
	public static ImageView bullet;
	public static int lifes;
	public ImageView[] playerLifeImage;
	public static final int radiusBullet = 7;
	public static final int radiusPlayer = 20;
	public static final int timeToChangeAnimation = 50;
	public static double currentTimeToChangeAnimation;
	
	
	public static boolean upPressed;
	public static boolean downPressed;
	public static boolean leftPressed;
	public static boolean rightPressed;
	public static boolean shootPressed;
	
	public static boolean bulletExists = false;
	public static boolean obtainedDamage = false;
	
	public final static String BulletRoot = "org/ce1103/gos/view/graphicResources/PlayerBullet.png";
	public final static ImageView GriffinStand = new ImageView("org/ce1103/gos/view/graphicResources/Player1.png");
	public final static ImageView GriffinFlying = new ImageView("org/ce1103/gos/view/graphicResources/Player2.png");
	
	public static void createGriffin() {
		griffin = GriffinStand;
		currentTimeToChangeAnimation = 0;
		griffin.setLayoutX(100);
		griffin.setLayoutY(250);
		griffin.setFitHeight(60);
		griffin.setFitWidth(60);
		lifes=5;
	}
	
	public static void changeAnimation(double positionX, double positionY) {
		if(griffin == GriffinStand) {
			griffin = GriffinFlying;
			griffin.setLayoutX(positionX);
			griffin.setLayoutY(positionY);
			griffin.setFitHeight(55);
			griffin.setFitWidth(55);
		}else{
			griffin = GriffinStand;
			griffin.setLayoutX(positionX);
			griffin.setLayoutY(positionY);
			griffin.setFitHeight(60);
			griffin.setFitWidth(60);
		}
	}
	
	
	public static void shooter() {
		bullet = new ImageView(Player.BulletRoot);
		bullet.relocate(griffin.getLayoutX()+35, griffin.getLayoutY()+25);
		bullet.setFitHeight(14);
		bullet.setFitWidth(14);
		shootPressed = true;
		bulletExists = true;
	}
	
	public static void moveDragon() {
		 
		if((leftPressed && !rightPressed)) {
			if(griffin.getLayoutX() > 0) {
				griffin.setLayoutX(griffin.getLayoutX()-5);
			}
		}
		if(rightPressed && !leftPressed) {
			if(griffin.getLayoutX() < 800) {
				griffin.setLayoutX(griffin.getLayoutX()+5);
			}
		}
		if(downPressed && !upPressed) {
			if(griffin.getLayoutY() < 705) {
				griffin.setLayoutY(griffin.getLayoutY()+5);
			}
		}
		if(upPressed && !downPressed) {
			if(griffin.getLayoutY() > 0) {
				griffin.setLayoutY(griffin.getLayoutY()-5);
			}
		}else {
		}
	}

	public static void moveBullet() {
		if (bulletExists) {
			if(bullet.getLayoutX() > (GameViewManager.width-10)) {
				bulletExists = false;
				GameViewManager.gamePane.getChildren().remove(bullet);
			}
			bullet.setLayoutX(bullet.getLayoutX()+10);
			
		}
	}
}
	

