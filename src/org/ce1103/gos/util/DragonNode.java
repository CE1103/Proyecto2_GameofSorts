package org.ce1103.gos.util;

import org.ce1103.gos.entities.DragonEnemy;

public class DragonNode {

	
	public DragonEnemy dragon;
	public DragonNode next;
	
	
	public DragonNode(DragonEnemy dragon) {
		this.dragon = dragon;
	}
	
	public void display() {
		
		System.out.println(dragon.getName() + ".");
		
	}


	
}
