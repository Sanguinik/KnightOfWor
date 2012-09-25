/**
 * 
 */
package com.wordpress.marleneknoche.persistence;

/**
 * @author marlene
 * 
 */
public class HighscoreImpl implements Highscore {

	private String highscore = "";

	@Override
	public void saveHighscore(String name, int score) {

		highscore = name + ": " + Integer.toString(score);

	}

	@Override
	public String loadHighscore() {
		return highscore;
	}

}
