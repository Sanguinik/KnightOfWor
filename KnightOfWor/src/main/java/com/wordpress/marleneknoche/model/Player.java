package com.wordpress.marleneknoche.model;

import javafx.scene.paint.Color;

public class Player extends Figure {

	private int score = 0;
	private int lives = 4;
	private final int maxLives = 4;

	public Player(Maze maze, TypeOfFigure type, int x, int y) {
		super(maze, type, x, y);
		getRectangle().setFill(Color.ORANGE);
		this.setMaze(maze);
		lives = 4;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
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

}
