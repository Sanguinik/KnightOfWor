package com.wordpress.marleneknoche.model;

import javafx.scene.paint.Color;

public class Enemy extends Figure {

	private final String name;

	public Enemy(Maze maze, TypeOfFigure type, int x, int y) {
		super(maze, type, x, y);
		name = type.name();
		getRectangle().setFill(Color.GREEN);
		setDirection(Direction.UP);
	}

	public void shoot() {

	}

	public String getName() {
		return name;
	}

}
