package org.ce1103.gos.util;

import org.ce1103.gos.entities.DragonEnemy;

public class BinaryTreeNode {

	int balanceFactor;
	int height;
	DragonEnemy dragon;
	BinaryTreeNode left;
	BinaryTreeNode right;

	public DragonEnemy getDragon() {
		return dragon;
	}

	public void setDragon(DragonEnemy dragon) {
		this.dragon = dragon;
	}

	public BinaryTreeNode getLeft() {
		return left;
	}

	public void setLeft(BinaryTreeNode left) {
		this.left = left;
	}

	public BinaryTreeNode getRight() {
		return right;
	}

	public void setRight(BinaryTreeNode right) {
		this.right = right;
	}

	public BinaryTreeNode(DragonEnemy dragon) {

		this.dragon = dragon;

	}

	public BinaryTreeNode(DragonEnemy dragon, BinaryTreeNode left, BinaryTreeNode right) {

		this.dragon = dragon;
		this.left = left;
		this.right = right;
		this.height = 0;
	}
}
