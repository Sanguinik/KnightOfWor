package com.wordpress.marleneknoche.model;

import javafx.scene.image.Image;

public class Player extends ShootingFigure {

	private int score = 0;
	private int lives = 4;
	private static final int MAX_LIVES = 4;

	public Player(final Maze maze, final double x, final double y) {
		super(maze, TypeOfFigure.PLAYER, x, y);
		Image image = new Image(
				"/com/wordpress/marleneknoche/model/hannes_right.png");
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
		System.out.println("Oh no, I'm Dead");
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

	@Override
	public void onCollisionWithMaze() {
		// Der Player macht nichts bei einer kollision.
	}

	@Override
	public void bulletHasHitATarget(final Figure target) {
		System.out.println("Hit");
		super.bulletHasHitATarget(target);
		target.setAlive(false);

		int points = target.getType().getPoints();
		score += points;
		System.out.println("new Score:" + score);
	}

}
