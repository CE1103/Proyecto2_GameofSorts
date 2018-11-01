package org.ce1103.gos.principalwin;

import java.awt.Dimension;
import java.awt.Toolkit;

import javafx.animation.TranslateTransition;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.util.Duration;

public class NestedWindowForPowerUp extends SubScene{

	private final static String rootFont = "org/ce1103/gos/principalwin/res/fuente.ttf";
	private final static String rootBackgroundImage = "org/ce1103/gos/principalwin/res/backgroundNestWindowForPoweUp.png";
	private static final Dimension displaySettings = Toolkit.getDefaultToolkit().getScreenSize();
	private static final int width = (int)displaySettings.getWidth();
	private static final int height = (int)displaySettings.getHeight();
	private boolean hidden;
	
	public NestedWindowForPowerUp() {
		super(new AnchorPane(), 500, 100);

		BackgroundImage backImage = new BackgroundImage(new Image(rootBackgroundImage,width*0.55,height*0.95,false,true)
				,BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
		
		AnchorPane thisWindow = (AnchorPane) this.getRoot();
		thisWindow.setBackground(new Background(backImage));
		
		setLayoutX(-500);
		setLayoutY(600);
		this.setHidden(true);
		
	}

	public AnchorPane getPane() {
		return (AnchorPane) this.getRoot();
	}
	
	public boolean getHidden() {
		return this.hidden;
	}
	
	public void setHidden(boolean estado) {
		this.hidden=estado;
	}
	
	
	public void moveNestedWindow() {
		TranslateTransition windowAnimation = new TranslateTransition();
		windowAnimation.setDuration(Duration.seconds(0.5));
		windowAnimation.setNode(this);
		
		if(this.getHidden()==true) {
			windowAnimation.setToX(500);
			this.setHidden(false);
			windowAnimation.stop();
		}else {
			windowAnimation.setToX(0);
			windowAnimation.setToY(0);
			this.setHidden(true);
		}
		windowAnimation.play();
	}
	
}
