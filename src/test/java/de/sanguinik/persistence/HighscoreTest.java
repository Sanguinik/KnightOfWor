/**
 * 
 */
package de.sanguinik.persistence;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import de.sanguinik.persistence.Highscore;
import de.sanguinik.persistence.HighscoreImpl;

/**
 * @author marlene
 * 
 */
public class HighscoreTest {

	private Highscore highscore;
	private final static String NAME = "Bob";
	private final static int SCORE = 2000;

	@Before
	public void setUp() {
		highscore = new HighscoreImpl();
	}

	@Test
	public void testSaveAndLoadHighscore() {

		assertEquals("", highscore.loadHighscore());
		highscore.saveHighscore(NAME, SCORE);
		assertEquals("Bob: 2000", highscore.loadHighscore());

	}

}
