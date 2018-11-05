package org.ce1103.gos.util;

import static org.junit.jupiter.api.Assertions.*;


import org.ce1103.gos.entities.DragonEnemy;
import org.ce1103.gos.view.ViewManager;
import org.junit.jupiter.api.Test;

import javafx.application.Application;
import javafx.stage.Stage;

class BinaryTreeTest{

	@Test
	void testAddNode() throws Exception{
		
		
		BinaryTree junit = new BinaryTree();
		DragonEnemy de1 = new DragonEnemy(67,2);
		DragonEnemy de2 = new DragonEnemy(32,1);
		DragonEnemy de3 = new DragonEnemy(78,3);		
		de1.age = 50;
		de2.age = 100;
		de3.age = 25;
		junit.addNode(de1);
		junit.addNode(de2);
		junit.addNode(de3);
    	assertEquals(de1, junit.getRoot().getDragon());
    	assertEquals(de2, junit.getRoot().getRight().getDragon());
    	assertEquals(de3, junit.getRoot().getLeft().getDragon());
		
	}

}
