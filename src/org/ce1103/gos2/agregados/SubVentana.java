package agregados;


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

public class SubVentana extends SubScene{

	private final static String rutaFuente = "src/agregados/recursosGraficos/fuente.ttf";
	private final static String rutaImagenFondo = "agregados/recursosGraficos/Fondo1.png";
	private static final Dimension miDisplay = Toolkit.getDefaultToolkit().getScreenSize();
	private static final int ancho = (int)miDisplay.getWidth();
	private static final int alto = (int)miDisplay.getHeight();
	private boolean oculto;
	
	
	
	public SubVentana() {
		super(new AnchorPane(), ancho*0.55, alto*0.95);
		this.prefWidth(ancho*0.55);
		this.prefHeight(alto*0.95);

		
		BackgroundImage fondo = new BackgroundImage(new Image(rutaImagenFondo,ancho*0.55,alto*0.95,false,true)
				,BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
		
	
		
		
		
		AnchorPane estaVentana = (AnchorPane) this.getRoot();
		
		estaVentana.setBackground(new Background(fondo));
		
		setLayoutX(2000);
		setLayoutY(0);
		this.setOculto(true);
		
	}

	
	public AnchorPane getPane() {
		return (AnchorPane) this.getRoot();
	}
	
	
	
	
	public boolean getOculto() {
		return this.oculto;
	}
	
	public void setOculto(boolean estado) {
		this.oculto=estado;
	}
	
	
	public void moverSubVentana() {
		TranslateTransition animacionVentana = new TranslateTransition();
		animacionVentana.setDuration(Duration.seconds(1));
		animacionVentana.setNode(this);
		
		if(this.getOculto()==true) {
			animacionVentana.setToX(-1400);
			this.setOculto(false);
			animacionVentana.setToY(10);
			animacionVentana.stop();
		}else {
			animacionVentana.setToX(0);
			animacionVentana.setToY(0);
			this.setOculto(true);
		}
		animacionVentana.play();
	}
	
	

}
