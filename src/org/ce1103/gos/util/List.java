package org.ce1103.gos.util;

public class List {

	public Node firstNode;
	public int nodes;


	public void addDragon(int dragonAge) {

		Node newPort = new Node(dragonAge);
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

	public Node removeNode(int dragonAge) {

		Node current = firstNode;
		Node previous = firstNode;

		while(current.dragonAge != dragonAge) {

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