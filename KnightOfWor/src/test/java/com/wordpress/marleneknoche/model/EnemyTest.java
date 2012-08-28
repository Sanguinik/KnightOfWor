package com.wordpress.marleneknoche.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class EnemyTest {
	
	Enemy enemy;
	
	@Test
	public void testEnemy() {
		enemy = new Enemy(TypeOfEnemy.BURWOR);
		assertEquals(100, enemy.getPoints());
		enemy = new Enemy(TypeOfEnemy.WIZARD);
		assertEquals(2500,enemy.getPoints());
		
	}

}
