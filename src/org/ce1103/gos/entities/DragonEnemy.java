package org.ce1103.gos.entities;

import java.util.concurrent.ThreadLocalRandom;

import org.ce1103.gos.util.DragonNode;

import javafx.scene.image.ImageView;

public class DragonEnemy {
	
	private String name;
	private int rechargeSpeed;
	private double currentShootCharge;
	private double rechargeRandomMovement;
	private double currentRandomMovementCharge;
	private int age;
	private int resistance;
	private DragonEnemy father;
	public boolean alive;
	public final int radiusEnemy = 12;
	
	public ImageView eDragon;
	
	public ImageView enemyFullOfHealth = new ImageView("org/ce1103/gos/view/graphicResources/EnemyFullOfHealth1.png");
	public ImageView enemyDamaged = new ImageView("org/ce1103/gos/view/graphicResources/EnemyDamaged1.png");
	public ImageView enemyVeryDamaged = new ImageView("org/ce1103/gos/view/graphicResources/EnemyVeryDamaged1.png");


	
	public DragonEnemy(int rechargeSpeed, int resistance) {
		setDragonName();
		setAge();
		this.rechargeSpeed = rechargeSpeed;
		this.resistance = resistance;
		
		if(this.resistance==3) {
			eDragon = enemyFullOfHealth;
		}else if(this.resistance==2) {
			eDragon = enemyDamaged;
		}else {
			eDragon = enemyVeryDamaged;

		}
		
		
		eDragon.setFitHeight(25);
		eDragon.setFitWidth(25);
	}
	
	
	
	
	
	
	
	public void setDragonName() {
		String[] randomDragonName = new String[] {"Angelo", "Jonathan", "Ivan", "Oscar", "Agustin"};
		int num = (int) (Math.random() * randomDragonName.length-1);
		setName(randomDragonName[num]);
	}
	
	public String getName() {
		return name;
	}
	
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getRechargeSpeed() {
		return rechargeSpeed;
	}

	public void setRechargeSpeed(int rechargeSpeed) {
		this.rechargeSpeed = rechargeSpeed;
	}
	
	public double getCurrentShootCharge() {
		return currentShootCharge;
	}
	
	public void setCurrentShootCharge(double d) {
		this.currentShootCharge = d;
	}
	
	
	
	public int getAge() {
		return age;
	}
	
	public void setAge() {
		if(getFather() == null) {
			int num = ThreadLocalRandom.current().nextInt(900, 1000 + 1);
			this.age = num;
		}else {
			int num = ThreadLocalRandom.current().nextInt(1, 890 + 1);
			this.age = num;
		}
	}
	
	public int getResistance() {
		return resistance;
	}
	
	public void setResistance(int resistance) {
		this.resistance = resistance;
	}
	
	public DragonEnemy getFather() {
		return father;
	}
	
	public void setFather(DragonEnemy father) {
		this.father = father;
	}
	
	public boolean isAlive() {
		return alive;
	}
	
	
	
}
