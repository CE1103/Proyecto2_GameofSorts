package org.ce1103.gos.util;

import org.ce1103.gos.entities.DragonEnemy;
import org.ce1103.gos.entities.EnemyBullet;

import javafx.scene.image.ImageView;

public class DragonList {

	public DragonNode firstNode;
	public int nodes;


	public void addDragon(DragonEnemy dragon) {

		DragonNode newPort = new DragonNode(dragon);
		newPort.next = firstNode;
		firstNode = newPort;
		nodes++;
	}
	

	public void display() {

		DragonNode PN = firstNode;
		while (PN != null) {
			PN.display();		
			System.out.println("\n");


			PN = PN.next;

		}		

	}

	public DragonNode removeDragonNode(DragonEnemy dragon) {

		DragonNode current = firstNode;
		DragonNode previous = firstNode;

		while(current.dragon != dragon) {

			if(current.next == null) {

				return null;

			}else {

				previous = current;
				current = current.next;

			}

		}

		if(current == firstNode) {

			firstNode = firstNode.next;

		}else {

			previous.next = current.next;

		}
		nodes--;
		return current;
	}
	
	
	
	public DragonEnemy findNode(int age) {
		DragonNode PN = firstNode;
		if(firstNode != null) {
			
			while (PN.dragon.getAge() != age) {
				
				if(PN.next == null) {
					return null;
					
				}else {
					PN = PN.next;
				}
			}		
		}else {
			
		}
		return PN.dragon;
	}
	}