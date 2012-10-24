/**
 * 
 */
package com.wordpress.marleneknoche.persistence;

/**
 * @author marlene This is the interface for the Highscore. It gets and sets the
 *         score of a player.
 */
public interface Highscore {
	/**
	 * This method should save someone's highscore.
	 * 
	 * @param name
	 *            The name of the player.
	 * @param score
	 *            The score of the player.
	 */
	void saveHighscore(String name, int score);

	/**
	 * This method should load the actual highscore.
	 * 
	 * @return a String with the current highscore.
	 */
	String loadHighscore();
}
