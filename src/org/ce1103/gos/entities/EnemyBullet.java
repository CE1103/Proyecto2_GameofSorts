package org.ce1103.gos.entities;


import javafx.scene.image.ImageView;

public class EnemyBullet {
	
	private double startingX;
	private double startingY;
	private int angle;
	public final double radiusBullet = 5;

	public ImageView bulletImage;
	
	public EnemyBullet(double startingX, double startingY, int angle) {
		this.startingX = startingX;
		this.startingY = startingY;
		this.angle = angle;
		
		bulletImage = new ImageView("org/ce1103/gos/view/graphicResources/EnemyBullet.png");
		bulletImage.setFitHeight(10);
		bulletImage.setFitWidth(10);
		
	}

	public String getName() {
		return "si";
	}
	
	public double getAngle() {
		return this.angle;
	}

	public void setStartingX(double startingX) {
		this.startingX = startingX;
	}
	
	public double getStartingX() {
		return this.startingX;
	}
	
	public void setStartingY(double startingY) {
		this.startingY = startingY;
	}
	
	public double getStartingY() {
		return this.startingY;
	}
	
	
}