package org.ce1103.gos.util;

import org.ce1103.gos.entities.DragonEnemy;

public class Node {

	
	public DragonEnemy dragon;
	public Node next;
	
	
	public Node(DragonEnemy dragon) {
		this.dragon = dragon;
	}
	
	public void display() {
		
		System.out.println(dragon.getName() + ".");
		
	}


	
}
