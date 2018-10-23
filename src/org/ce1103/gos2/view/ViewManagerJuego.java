package view;

import java.awt.Dimension;
import java.awt.Toolkit;

import javafx.animation.AnimationTimer;
import javafx.event.Event;
import javafx.event.EventHandler;
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
	
	
	
	private boolean abajoPresionado;
	private boolean arribaPresionado;
	private boolean izquierdaPresionado;
	private boolean derechaPresionado;
	private AnimationTimer gameTimer;
	
	
	
	private GridPane gridPane1;
	private GridPane gridPane2;
	private final static String rutaFondo = "view/recursosGraficos/gameBackground.png";
	
	
	public ViewManagerJuego() {
		this.iniciarGameStage();
		this.crearKeyListeners();
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
		crearGameLoop();
		gameStage.show();
	}
	
	
	
	private void crearDragon() {
		dragon = new ImageView("view/recursosGraficos/dragon (1).png");
		dragon.setLayoutX(100);
		dragon.setLayoutY(10);
		dragon.setFitHeight(40);
		dragon.setFitWidth(40);
		gamePane.getChildren().add(dragon);
	}
	
	
	
	
	private void crearGameLoop() {
		gameTimer = new AnimationTimer() {
			
			public void handle(long now) {
				moverDragon();
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
	
	
	private void crearBackground() {
		gridPane1 = new GridPane();
		gridPane2 = new GridPane();
		
		for(int i=0; i<12; i++) {
			ImageView backgroundImage1 = new ImageView(rutaFondo);
			ImageView backgroundImage2 = new ImageView(rutaFondo);

					
			
			
			
		}
	}
	
	
	
	

}
