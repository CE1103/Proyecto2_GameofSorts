package org.ce1103.gos.util;


public class Node {

	
	public int dragonAge;
	public Node next;
	
	
	public Node(int dragonAge) {
		this.dragonAge = dragonAge;
	}
	
	public void display() {
		
		System.out.println(dragonAge + ".");
		
	}


	
}
