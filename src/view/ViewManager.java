package view;


import java.awt.Dimension;
import java.awt.Toolkit;

import agregados.Botones;
import agregados.SubVentana;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.stage.Stage;

public class ViewManager {
	
	
	private static final Dimension miDisplay = Toolkit.getDefaultToolkit().getScreenSize();
	private static final int ancho = (int)miDisplay.getWidth();
	private static final int alto = (int)miDisplay.getHeight();
	private static ImageView logo;
	
	private AnchorPane mainPane;
	private Scene mainScene;
	private Stage mainStage;

	
	private SubVentana subVentanaCreditos;
	private SubVentana subVentanaAyuda;
	private SubVentana subVentanaJugar;
	private SubVentana subVentanaSalir;
	
	private String subVentanaActiva;
	
	
	public ViewManager() {
		mainPane = new AnchorPane();
		mainScene = new Scene(mainPane,ancho,alto);
		mainStage = new Stage();
		mainStage.setScene(mainScene);
		mainStage.setTitle("GAME OF SORTS");
		this.crearBotonJugar();
		this.crearBotonAyuda();
		this.crearBotonCreditos();
		this.crearBotonSalir();
		this.crearFondo();
		this.crearSubVentana();
		this.crearLogo();

	}
	
	
	
	

	
	
	
	private void crearSubVentana() {
		subVentanaCreditos = new SubVentana();
		mainPane.getChildren().add(subVentanaCreditos);
		
		subVentanaAyuda = new SubVentana();
		mainPane.getChildren().add(subVentanaAyuda);
		
		subVentanaJugar = new SubVentana();
		mainPane.getChildren().add(subVentanaJugar);
		
		subVentanaSalir = new SubVentana();
		mainPane.getChildren().add(subVentanaSalir);
	}
	
	
	
	
	public Stage getMainStage() {
		return mainStage;
	}
	
	
	
	
	
	
	private void crearBotonJugar() {
		Botones botonJugar = new Botones("Jugar",80,540, 190,61,"Jugar");
		mainPane.getChildren().add(botonJugar);
		
		botonJugar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent evento) {
				subVentanaJugar.moverSubVentana();
				mainPane.getChildren().remove(logo);
			}
				
		});
		
	
	}
	private void crearBotonAyuda() {
		Botones botonAyuda = new Botones("Ayuda",80,620, 190,61,"Ayuda");
		mainPane.getChildren().add(botonAyuda);
		
		
		botonAyuda.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent evento) {
				subVentanaAyuda.moverSubVentana();
				mainPane.getChildren().remove(logo);
			}
				
		});
	}
	
	
	
	private void crearBotonCreditos() {
		Botones botonCreditos = new Botones("Créditos",325,540, 190,61,"Créditos");
		mainPane.getChildren().add(botonCreditos);
		
		botonCreditos.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent evento) {
				subVentanaCreditos.moverSubVentana();
				mainPane.getChildren().remove(logo);
			}
				
		});
	}
		
		
		
		
		
	private void crearBotonSalir() {
		Botones botonSalir = new Botones("Salir",325,620, 190,61,"Salir");
		mainPane.getChildren().add(botonSalir);


		botonSalir.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent evento) {
				subVentanaSalir.moverSubVentana();
				mainPane.getChildren().remove(logo);
			}
				
		});
	}
	
	
	
	
	
	private void crearFondo() {
		Image imagenFondo = new Image("view/recursosGraficos/fondo.png",ancho,alto,false,true);
		BackgroundImage fondo = new BackgroundImage(imagenFondo,BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,null);
		mainPane.setBackground(new Background(fondo));
	}
	
	private void crearLogo() {
		logo = new ImageView("view/recursosGraficos/logo.png");
		logo.setLayoutX(-15);
		logo.setLayoutY(-15);
		logo.setFitHeight(229);
		logo.setFitWidth(700);
		
		logo.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
		public void handle(MouseEvent event) {
			logo.setEffect(new DropShadow());
		}
		});
		
		
		logo.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
		public void handle(MouseEvent event) {
			logo.setEffect(null);
		}
		});
	
		mainPane.getChildren().add(logo);

		
		
	}
	
}
