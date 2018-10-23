package agregados;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;


public class Botones extends Button{
	
	private static final String rutaFuente = "src/agregados/recursosGraficos/fuente.ttf";
	private static final String displayBotonPresionado = "-fx-background-color: transparent; -fx-background-image: url('/agregados/recursosGraficos/blue_button01.png');";
	private static final String displayBotonSinPresionar = "-fx-background-color: transparent; -fx-background-image: url('/agregados/recursosGraficos/blue_button00.png');";

	
	public Botones(String texto, double posicionX, double posicionY, double ancho, double alto, String accion) {
		this.setText(texto);
		this.setFuenteBoton();
		this.setPrefWidth(ancho);
		this.setPrefHeight(alto);
		this.setLayoutX(posicionX);
		this.setLayoutY(posicionY);
		this.setStyle(displayBotonSinPresionar);
		iniciarListenersBoton();
	}
	
	

	
	
	
	
	
	private void setFuenteBoton() {
		try {
			this.setFont(Font.loadFont(new FileInputStream(rutaFuente), 23));
			
		}catch(FileNotFoundException e) {
			this.setFont(Font.font("Verdana",23));
		}
	}

	
	private void setBotonPresionadoDisplay() {
		this.setStyle(displayBotonPresionado);
		this.setPrefHeight(55);
		this.setLayoutY(this.getLayoutY()+10);

	}

	
	private void setBotonSinPresionarDisplay() {
		this.setStyle(displayBotonSinPresionar);
		this.setPrefHeight(60);
		this.setLayoutY(this.getLayoutY()-10);

	}

	
	private void iniciarListenersBoton() {
		setOnMousePressed(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent evento) {
				if(evento.getButton().equals(MouseButton.PRIMARY)) {
					setBotonPresionadoDisplay();
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

