package com.wordpress.marleneknoche.model;

import javafx.scene.image.Image;

public class Player extends ShootingFigure {

	private int score = 0;
	private int lives = 4;
	private final int maxLives = 4;
	private final int points;

	public Player(Maze maze, TypeOfFigure type, double x, double y) {
		super(maze, x, y);
		points = type.getPoints();
		lives = 4;
		Image image = new Image(
				"/com/wordpress/marleneknoche/model/hannes_right.png");
		imageView.setImage(image);
		imageView.setX(x);
		imageView.setY(y);
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getLives() {
		return lives;
	}

	public void setLives(int lives) {
		this.lives = lives;
		if (lives > maxLives) {
			this.lives = maxLives;
		}
	}

	public int getMaxLives() {
		return maxLives;
	}

	@Override
	public void onCollision() {
		// Der Player macht nichts bei einer kollision.
	}

}
