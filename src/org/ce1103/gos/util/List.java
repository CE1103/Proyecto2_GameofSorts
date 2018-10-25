package org.ce1103.gos.util;

import org.ce1103.gos.entities.DragonEnemy;

public class List {

	public Node firstNode;
	public int nodes;


	public void addDragon(DragonEnemy dragon) {

		Node newPort = new Node(dragon);
		newPort.next = firstNode;
		firstNode = newPort;
		nodes++;

	}

	public void display() {

		Node PN = firstNode;
		while (PN != null) {
			PN.display();		
			System.out.println("\n");


			PN = PN.next;

		}		

	}

	public Node removeNode(DragonEnemy dragon) {

		Node current = firstNode;
		Node previous = firstNode;

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
}