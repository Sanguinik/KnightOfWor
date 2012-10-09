package com.wordpress.marleneknoche.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class EnemyTest {

	Enemy enemy;
	Maze maze = new Maze();

	@Test
	public void testEnemy() {
		enemy = new Enemy(maze, TypeOfFigure.BURWOR, 100, 100);
		assertEquals(100, enemy.getPoints());
		enemy = new Enemy(maze, TypeOfFigure.WIZARD, 200, 200);
		assertEquals(2500, enemy.getPoints());

	}

}
