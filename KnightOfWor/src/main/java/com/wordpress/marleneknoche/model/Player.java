package com.wordpress.marleneknoche.model;

import com.wordpress.marleneknoche.view.PlayFieldScreen;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Player extends Figure {

	private int score = 0;
	private int lives = 4;
	private final int maxLives = 4;

	public Player(Maze maze, TypeOfFigure type, double x, double y) {
		super(maze, type, x, y);
		this.setMaze(maze); // TODO delete
		lives = 4;
		Image image = new Image(
				"/com/wordpress/marleneknoche/model/hannes_right.jpg");
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

}
