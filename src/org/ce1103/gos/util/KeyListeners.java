package org.ce1103.gos.util;

import org.ce1103.gos.view.GameViewManager;

import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.ce1103.gos.entities.Player;

public class KeyListeners {
	
	public static void createKeyListeners() {
		GameViewManager.gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			
			public void handle(KeyEvent event) {
				if(event.getCode()== KeyCode.P) {
					if(GameViewManager.isPaused) {
						GameViewManager.gameTimer.start();
						MusicPlayer.pauseSoundPlayer.stop();
						MusicPlayer.pauseSoundPlayer.play();
						MusicPlayer.backgroundMusicPlayer.setVolume(0.8);
						GameViewManager.isPaused=false;
					}else {
						GameViewManager.gameTimer.stop();
						MusicPlayer.pauseSoundPlayer.stop();
						MusicPlayer.pauseSoundPlayer.play();
						MusicPlayer.backgroundMusicPlayer.setVolume(0.2);
						GameViewManager.isPaused=true;
					}
				}if(event.getCode()== KeyCode.LEFT || event.getCode()== KeyCode.A) {
					Player.leftPressed = true;
				}else if(event.getCode()== KeyCode.RIGHT || event.getCode()== KeyCode.D) {
					Player.rightPressed = true;
				}else if(event.getCode()== KeyCode.UP || event.getCode()== KeyCode.W) {
					Player.upPressed = true;
				}else if(event.getCode()== KeyCode.DOWN || event.getCode()== KeyCode.S) {
					Player.downPressed = true;
				}else if (event.getCode() == KeyCode.SPACE && !Player.bulletExists && !GameViewManager.isPaused){
					
					Player.bullet = new ImageView(Player.BulletRoot);
					Player.bullet.relocate(Player.griffin.getLayoutX()+35, Player.griffin.getLayoutY()+25);
					GameViewManager.gamePane.getChildren().add(Player.bullet);
					Player.shootPressed = false;
					Player.bulletExists = true;
					MusicPlayer.shootSoundPlayer.stop();
					MusicPlayer.shootSoundPlayer.play();
					MusicPlayer.shootSoundPlayer.setVolume(0.2);

				}
			}
		});
		
		GameViewManager.gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
			
			
			public void handle(KeyEvent event) {
				if(event.getCode()== KeyCode.LEFT || event.getCode()== KeyCode.A) {
					Player.leftPressed = false;
				}else if(event.getCode()== KeyCode.RIGHT || event.getCode()== KeyCode.D) {
					Player.rightPressed = false;
				}else if(event.getCode()== KeyCode.UP || event.getCode()== KeyCode.W) {
					Player.upPressed = false;
				}else if(event.getCode()== KeyCode.DOWN || event.getCode()== KeyCode.S) {
					Player.downPressed=false;
				}else if (event.getCode() == KeyCode.SPACE){
					Player.shootPressed = false;
				}
			
			}	
		});
		
	}
}
