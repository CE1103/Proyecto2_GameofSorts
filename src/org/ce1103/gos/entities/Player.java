package org.ce1103.gos.entities;

import org.ce1103.gos.view.GameViewManager;

import javafx.scene.image.ImageView;

public class Player {
	
	public static ImageView dragon;
	public static ImageView bullet;
	
	public static boolean upPressed;
	public static boolean downPressed;
	public static boolean leftPressed;
	public static boolean rightPressed;
	public static boolean shootPressed;
	
	public static boolean bulletExists = false;
	
	public final static String BulletRoot = "org/ce1103/gos/view/graphicResources/dragonBullet.png";
	
	public static void crearDragon() {
		dragon = new ImageView("org/ce1103/gos/view/graphicResources/dragon (1).png");
		dragon.setLayoutX(100);
		dragon.setLayoutY(10);
		dragon.setFitHeight(60);
		dragon.setFitWidth(60);
	}
	
	public static void shooter() {
		bullet = new ImageView(Player.BulletRoot);
		bullet.relocate(dragon.getLayoutX()+35, dragon.getLayoutY()+10);
		shootPressed = true;
		bulletExists = true;
	}
	
	public static void moveDragon() {
		 
		if((leftPressed && !rightPressed)) {
			if(dragon.getLayoutX() > 0) {
				dragon.setLayoutX(dragon.getLayoutX()-5);
			}
		}
		if(rightPressed && !leftPressed) {
			if(dragon.getLayoutX() < 800) {
				dragon.setLayoutX(dragon.getLayoutX()+5);
			}
		}
		if(downPressed && !upPressed) {
			if(dragon.getLayoutY() < 705) {
				dragon.setLayoutY(dragon.getLayoutY()+5);
			}
		}
		if(upPressed && !downPressed) {
			if(dragon.getLayoutY() > 0) {
				dragon.setLayoutY(dragon.getLayoutY()-5);
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
			bullet.setLayoutX(bullet.getLayoutX()+5);
			
		}
	}
		

}
