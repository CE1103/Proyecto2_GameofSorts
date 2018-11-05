package org.ce1103.gos.util;

import org.ce1103.gos.entities.DragonEnemy;
import org.ce1103.gos.entities.EnemyBullet;

public class BulletNode {

	
	public EnemyBullet bullet;
	public BulletNode next;
	
	
	public BulletNode(EnemyBullet bullet) {
		this.bullet = bullet;
	}
	
	public void display() {
		
		//System.out.println(bullet.getName() + ".");
		
	}


	
}
