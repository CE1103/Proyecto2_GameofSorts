package org.ce1103.gos.util;

import org.ce1103.gos.entities.DragonEnemy;
import org.ce1103.gos.entities.EnemyBullet;

public class BulletList {

	public BulletNode firstNode;
	public int nodes;


	public void addBullet(EnemyBullet bullet) {

		BulletNode newPort = new BulletNode(bullet);
		newPort.next = firstNode;
		firstNode = newPort;
		nodes++;
	}
	

	public void display() {
		BulletNode PN = firstNode;
		while (PN != null) {
			PN.display();		
			System.out.println("\n");
			PN = PN.next;

		}		

	}

	
	public BulletNode removeBulletNode(EnemyBullet bullet) {

		BulletNode current = firstNode;
		BulletNode previous = firstNode;

		while(current.bullet != bullet) {

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
}