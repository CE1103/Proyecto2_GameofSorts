package org.ce1103.gos.principalwin;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;


public class Buttons extends Button{
	
	private static final String rootFont = "org/ce1103/gos/agregados/recursosGraficos/fuente.ttf";
	private static final String pressedButtonDisplay = "-fx-background-color: transparent; -fx-background-image: url('org/ce1103/gos/principalwin/res/blue_button01.png');";
	private static final String noPressedButtonDisplay = "-fx-background-color: transparent; -fx-background-image: url('org/ce1103/gos/principalwin/ress/blue_button00.png');";

	public Buttons(String text, double posX, double posY, double width, double height, String action) {
		this.setText(text);
		this.setButtonFont();
		this.setPrefWidth(width);
		this.setPrefHeight(height);
		this.setLayoutX(posX);
		this.setLayoutY(posY);
		this.setStyle(noPressedButtonDisplay);
		startListenersButtons();
	}
	
	
	private void setButtonFont() {
		try {
			this.setFont(Font.loadFont(new FileInputStream(rootFont), 23));
			
		}catch(FileNotFoundException e) {
			this.setFont(Font.font("Verdana",23));
		}
	}

	
	private void setPressedButtonDisplay() {
		this.setStyle(pressedButtonDisplay);
		this.setPrefHeight(55);
		this.setLayoutY(this.getLayoutY()+10);

	}

	
	private void setBotonSinPresionarDisplay() {
		this.setStyle(noPressedButtonDisplay);
		this.setPrefHeight(60);
		this.setLayoutY(this.getLayoutY()-10);

	}

	
	private void startListenersButtons() {
		setOnMousePressed(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent evento) {
				if(evento.getButton().equals(MouseButton.PRIMARY)) {
					setPressedButtonDisplay();
				}
			}
		});
		
		setOnMouseReleased(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent evento) {
				if(evento.getButton().equals(MouseButton.PRIMARY)) {
					setBotonSinPresionarDisplay();
				}
			}
		});
		
		setOnMouseEntered(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent evento) {
				setEffect(new DropShadow());
			}
		});
		
		setOnMouseExited(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent evento) {
				setEffect(null);
			}
		});
		
		
	}
	
}	

