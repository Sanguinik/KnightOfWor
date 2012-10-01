package com.wordpress.marleneknoche.model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Player {

	private final int points = 1000;
	private int score = 0;
	private Boolean alive = true;
	private Bullet bullet;
	private int lives = 4;
	private final int maxLives = 4;
	private final Rectangle playerRectangle;
	private final Maze maze;
	private static final double DISTANCE = 5;

	public Player(Maze maze) {
		playerRectangle = new Rectangle(130, 510, 40, 40);
		playerRectangle.setFill(Color.ORANGE);
		this.maze = maze;
		lives = 4;
	}

	public int getPoints() {
		return points;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Boolean isAlive() {
		return alive;
	}

	public void setAlive(Boolean alive) {
		this.alive = alive;
	}

	public void shoot() {

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

	private void moveX(double distance) {
		double x = playerRectangle.getX();
		CollisionDetector cd = new CollisionDetector();
		for (Rectangle r : maze.getWalls()) {
			if (cd.isCollide(playerRectangle, r)) {
				if (distance > 0) {
					playerRectangle.setX(x - 1);
				} else {
					playerRectangle.setX(x + 1);
				}
				return;
			} else {
				playerRectangle.setX(x + distance);
			}
		}
	}

	private void moveY(double distance) {
		double y = playerRectangle.getY();
		CollisionDetector cd = new CollisionDetector();
		for (Rectangle r : maze.getWalls()) {
			if (cd.isCollide(playerRectangle, r)) {
				if (distance > 0) {
					playerRectangle.setY(y - 1);
				} else {
					playerRectangle.setY(y + 1);
				}
				return;
			} else {
				playerRectangle.setY(y + distance);
			}
		}
	}

	public void moveRight() {
		moveX(DISTANCE);
	}

	public void moveLeft() {
		moveX(-DISTANCE);
	}

	public void moveDown() {
		moveY(DISTANCE);
	}

	public void moveUp() {
		moveY(-DISTANCE);
	}

	public Rectangle getPlayerRectangle() {
		return playerRectangle;
	}

}
