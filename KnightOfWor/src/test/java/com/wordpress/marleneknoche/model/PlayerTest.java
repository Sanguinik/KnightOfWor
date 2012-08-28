package com.wordpress.marleneknoche.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PlayerTest {
	
	private Player player;
	
	@Before
	public void setUp() throws Exception {
		player = new Player();
	}

	@Test
	public void testPlayer() {
		assertEquals("Am Anfang 4 Leben!", 4, player.getLives());
		assertEquals("Am Anfang Highscore 0!", 0, player.getScore());
		assertEquals("Player 1000 Points!", 1000, player.getPoints());
		assertTrue("Am Anfang lebendig!", player.isAlive());
	}
	
	@Test
	public void testLives(){
		player.setLives(5);
		assertTrue(player.getLives()==player.getMaxLives());
		player.setLives(4);
		assertTrue(player.getLives()==4);
		player.setLives(3);
		assertTrue(player.getLives()==3);
	}

}
