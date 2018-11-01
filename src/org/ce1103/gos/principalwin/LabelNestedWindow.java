package org.ce1103.gos.principalwin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.image.Image;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;


public class LabelNestedWindow extends Label{
	
	public final static String rootFont = "org/ce1103/gos/principalwin/res/fuente.ttf";
	public final static String rootBackgroundImage = "org/ce1103/gos/view/graphicResources/minus.png";
	
	public LabelNestedWindow(String text) {
		this.setPrefWidth(128);
		this.setPrefHeight(128);

		this.setText(text);
		this.setWrapText(true);
		this.setFont();
		this.setAlignment(Pos.CENTER);
		
		BackgroundImage backgroundImage = new BackgroundImage(new Image(rootBackgroundImage, 128,128,false,true), BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,null);
		
		this.setBackground(new Background(backgroundImage));
	}
	
	
	private void setFont() {
		try {
			this.setFont(Font.loadFont(new FileInputStream(new File(rootFont)), 15));
		} catch (FileNotFoundException e) {
			this.setFont(Font.font("Verdana",15));
		}
	}
	
}
