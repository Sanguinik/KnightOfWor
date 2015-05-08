package de.sanguinik.model;

import javafx.scene.image.Image;

public class Player extends ShootingFigure {

	private int score = 0;
	private int lives = 4;
	private static final int MAX_LIVES = 4;
	private static final String PATH = "/de/sanguinik/model/";

	public Player(final Maze maze, final double x, final double y) {
		super(maze, TypeOfFigure.PLAYER, x, y);
		Image image = new Image(
				PATH + "hannes_right.png");
		getImageView().setImage(image);
		getImageView().setX(x);
		getImageView().setY(y);
		lives = 4;
	}

	public int getScore() {
		return score;
	}

	public void setScore(final int score) {
		this.score = score;
	}

	@Override
	public void setAlive(final boolean alive) {
		super.setAlive(alive);
	}

	public int getLives() {
		return lives;
	}

	public void setLives(final int lives) {
		this.lives = lives;
		if (lives > MAX_LIVES) {
			this.lives = MAX_LIVES;
		}
	}

	public int getMaxLives() {
		return MAX_LIVES;
	}

	/**
	 * If the player collides with the maze, nothing happens.
	 */
	@Override
	public void onCollisionWithMaze() {
	}

	@Override
	public void bulletHasHitATarget(final Figure target) {
		super.bulletHasHitATarget(target);
		target.setAlive(false);

		int points = target.getType().getPoints();
		score += points;
	}

}
