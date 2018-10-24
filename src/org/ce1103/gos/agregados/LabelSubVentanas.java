package org.ce1103.gos.agregados;

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


public class LabelSubVentanas extends Label{
	
	public final static String rutaFuente = "org/ce1103/gos/agregados/recursosGraficos/fuente.ttf";
	public final static String rutaImagenFondo = "org/ce1103/gos/view/recursosGraficos/textbox for credits.png";
	
	public LabelSubVentanas(String texto) {
		this.setPrefWidth(600);
		this.setPrefHeight(100);

		this.setText(texto);
		this.setWrapText(true);
		this.setFuente();
		this.setAlignment(Pos.CENTER);
		
		
		BackgroundImage imagenFondo = new BackgroundImage(new Image(rutaImagenFondo, 700,150,false,true), BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,null);
		
		
		
		this.setBackground(new Background(imagenFondo));
	}
	
	
	private void setFuente() {
		try {
			this.setFont(Font.loadFont(new FileInputStream(new File(rutaFuente)), 26));
		} catch (FileNotFoundException e) {
			this.setFont(Font.font("Verdana",23));
		}
	}
	
}
