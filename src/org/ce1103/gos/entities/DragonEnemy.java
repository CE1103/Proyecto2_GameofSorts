package org.ce1103.gos.entities;

import java.util.concurrent.ThreadLocalRandom;

import javafx.scene.image.ImageView;

public class DragonEnemy {
	
	private String name;
	private int rechargeSpeed;
	private int age;
	private int resistance;
	private DragonEnemy father;
	public boolean alive;
	
	public static ImageView edragon;
	
	public DragonEnemy(int rechargeSpeed, int resistance) {
		setDragonName();
		setAge();
		this.rechargeSpeed = rechargeSpeed;
		this.resistance = resistance;
		
		edragon = new ImageView("org/ce1103/gos/view/recursosGraficos/bat.png");
		edragon.setLayoutX(100);
		edragon.setLayoutY(10);
		edragon.setFitHeight(40);
		edragon.setFitWidth(40);
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
