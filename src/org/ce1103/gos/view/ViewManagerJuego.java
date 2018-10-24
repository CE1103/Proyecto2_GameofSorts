package org.ce1103.gos.view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javafx.animation.AnimationTimer;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ViewManagerJuego {
	
	private AnchorPane gamePane;
	private Scene gameScene;
	private Stage gameStage;

	
	
	private static final Dimension miDisplay = Toolkit.getDefaultToolkit().getScreenSize();
	private static final int ancho = (int)miDisplay.getWidth();
	private static final int alto = (int)miDisplay.getHeight();
	
	
	private Stage menuStage;
	private ImageView dragon;
	private ImageView bala;
	
	
	
	private boolean abajoPresionado;
	private boolean arribaPresionado;
	private boolean izquierdaPresionado;
	private boolean derechaPresionado;
	private boolean disparoPresionado;
	private boolean balaExiste = false;
	private AnimationTimer gameTimer;
	
	
	
	private GridPane gridPane1;
	private GridPane gridPane2;
	private final static String rutaFondo = "org/ce1103/gos/view/recursosGraficos/gameBackground.png";
	private final static String rutaBala = "org/ce1103/gos/view/recursosGraficos/balaDragon.png";
	
	
	
	private final static String rutaEnemigos = "org/ce1103/gos/view/recursosGraficos/bat.png";
	
	private ImageView[] enemigos;
	private ImageView[] enemigosPasaron;
	
	Random generarPosicionAleatoria;
	
	
	
	
	public ViewManagerJuego() {
		this.iniciarGameStage();
		this.crearKeyListeners();
		this.generarPosicionAleatoria = new Random();
	}



	private void crearKeyListeners() {
		gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			
			
			public void handle(KeyEvent event) {
				if(event.getCode()== KeyCode.LEFT || event.getCode()== KeyCode.A) {
					izquierdaPresionado=true;
				}else if(event.getCode()== KeyCode.RIGHT || event.getCode()== KeyCode.D) {
					derechaPresionado=true;
				}else if(event.getCode()== KeyCode.UP || event.getCode()== KeyCode.W) {
					arribaPresionado=true;
				}else if(event.getCode()== KeyCode.DOWN || event.getCode()== KeyCode.S) {
					abajoPresionado=true;
				}else if (event.getCode() == KeyCode.SPACE && !balaExiste) {
					bala = new ImageView(rutaBala);
					bala.relocate(dragon.getLayoutX()+35, dragon.getLayoutY()+10);
					gamePane.getChildren().add(bala);
					disparoPresionado = true;
					balaExiste = true;
				}
			
			}	
		});
		
		
		
		gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
			
			
			public void handle(KeyEvent event) {
				if(event.getCode()== KeyCode.LEFT || event.getCode()== KeyCode.A) {
					izquierdaPresionado=false;
				}else if(event.getCode()== KeyCode.RIGHT || event.getCode()== KeyCode.D) {
					derechaPresionado=false;
				}else if(event.getCode()== KeyCode.UP || event.getCode()== KeyCode.W) {
					arribaPresionado=false;
				}else if(event.getCode()== KeyCode.DOWN || event.getCode()== KeyCode.S) {
					abajoPresionado=false;
				}else if (event.getCode() == KeyCode.SPACE){
					disparoPresionado = false;
				}
			
			}	
		});
		
		
		
		
		
	}



	private void iniciarGameStage() {
		gamePane = new AnchorPane();
		gameScene = new Scene(gamePane, ancho, alto);
		gameStage = new Stage();
		gameStage.setScene(gameScene);
		
	}
	
	
	public void crearNuevoJuego(Stage menuStage) {
		this.menuStage = menuStage;
		this.menuStage.hide();
		this.crearDragon();
		crearEnemigos();
		crearGameLoop();
		gameStage.show();
	}
	
	
	private void crearEnemigos() {
		enemigos = new ImageView[100];//
		for(int i=0; i<enemigos.length; i++) {
			enemigos[i] = new ImageView(rutaEnemigos);
			enemigos[i].setFitHeight(25);
			enemigos[i].setFitWidth(25);
			darPosicionEnemigos(enemigos[i]);
			gamePane.getChildren().add(enemigos[i]);
		}
	}
	
	
	private void darPosicionEnemigos(ImageView image) {
		int ejeX=(int)(generarPosicionAleatoria.nextInt(ancho-20));
		while (ejeX<910) {
			ejeX=(int)(generarPosicionAleatoria.nextInt(ancho-20));
		}
		int ejeY=(int)(generarPosicionAleatoria.nextInt(705));
		image.setLayoutX(ejeX);
		image.setLayoutY(ejeY);
	}
	
	
	private void moverEnemigos() {
		for(int i = 0; i<enemigos.length; i++) {
			enemigos[i].setLayoutX(enemigos[i].getLayoutX()-1);
		}
	}
	
	
	private void enemigosPasaron() {
		for(int i=0; i<enemigos.length; i++) {
			if(enemigos[i].getLayoutX()==0) {
				System.out.println("vida menos");

				
			}
		}
	}
	
	
	private void enemigosTocaronJugador() {
		for(int i=0; i<enemigos.length; i++) {
			Rectangle2D posicionEnemigo = new Rectangle2D(enemigos[i].getLayoutX(),enemigos[i].getLayoutY(),30,30);
			Rectangle2D posicionDragon = new Rectangle2D(dragon.getLayoutX(),dragon.getLayoutY(),30,30);
			if(posicionEnemigo.intersects(posicionDragon)){
				System.out.println("tocado");
			}
		}
	}
	
	
	
	
	private void crearDragon() {
		dragon = new ImageView("org/ce1103/gos/view/recursosGraficos/dragon (1).png");
		dragon.setLayoutX(100);
		dragon.setLayoutY(10);
		dragon.setFitHeight(40);
		dragon.setFitWidth(40);
		gamePane.getChildren().add(dragon);
	}
	
	
	
	
	private void crearGameLoop() {
		gameTimer = new AnimationTimer() {
			
			public void handle(long now) {
				moverEnemigos();
				enemigosPasaron();
				moverDragon();
				moverBala();
				enemigosTocaronJugador();
			}

	
		};
		gameTimer.start();
	}
	
	
	
	
	
	private void moverDragon() {
		if(izquierdaPresionado && !derechaPresionado) {
			if(dragon.getLayoutX() > 0) {
				dragon.setLayoutX(dragon.getLayoutX()-5);
			}
		}
		if(derechaPresionado && !izquierdaPresionado) {
			if(dragon.getLayoutX() < 800) {
				dragon.setLayoutX(dragon.getLayoutX()+5);
			}
		}
		if(abajoPresionado && !arribaPresionado) {
			if(dragon.getLayoutY() < 705) {
				dragon.setLayoutY(dragon.getLayoutY()+5);
			}
		}
		if(arribaPresionado && !abajoPresionado) {
			if(dragon.getLayoutY() > 0) {
				dragon.setLayoutY(dragon.getLayoutY()-5);
			}
		}else {
		}
		
		
		
		
	}
	
	private void moverBala() {
		if (balaExiste) {
			if(bala.getLayoutX() > (ancho-10)) {
				balaExiste = false;
				gamePane.getChildren().remove(bala);
			}
			bala.setLayoutX(bala.getLayoutX()+5);
			
		}
		
	}
	
	
	private void crearBackground() {
		gridPane1 = new GridPane();
		gridPane2 = new GridPane();
		
		for(int i=0; i<12; i++) {
			ImageView backgroundImage1 = new ImageView(rutaFondo);
			ImageView backgroundImage2 = new ImageView(rutaFondo);

					
			
			
			
		}
	}
	
	
	
	

}
