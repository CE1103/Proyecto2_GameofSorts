package org.ce1103.gos.principalwin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.text.Font;

public class PlayerInfo extends Label {
	private final static String rootFont = "org/ce1103/gos/agregados/recursosGraficos/fuente.ttf";


	public PlayerInfo (String text) {
		this.setPrefWidth(130);
		this.setPrefHeight(50);
		
		Image backImage = new Image("org/ce1103/gos/view/recursosGraficos/minus.png",130,50,false,true);
		BackgroundImage backgroundImage = new BackgroundImage(backImage,BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,null);	

		this.setBackground(new Background(backgroundImage));
		this.setAlignment(Pos.CENTER_LEFT);
		this.setPadding(new Insets(10,10,10,10));
		this.setText(text);
		this.setFont();
	}
	
	private void setFont() {
		try {
			this.setFont(Font.loadFont(new FileInputStream(new File(rootFont)), 15));
		} catch (FileNotFoundException e) {
			this.setFont(Font.font("Verdana", 15));
		}
	}

		
}





