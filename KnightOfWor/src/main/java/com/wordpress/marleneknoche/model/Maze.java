package com.wordpress.marleneknoche.model;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * 
 * This class provides the a maze for the game.
 * 
 * @author marlene
 * 
 */
public class Maze {

	private final List<Rectangle> walls = new ArrayList<Rectangle>();

	public Maze() {
		createMaze();
	}

	private void createMaze() {
		Group g = new Group();
		Rectangle r1 = new Rectangle(200, 50, 100, 200);
		Rectangle r2 = new Rectangle(400, 200, 100, 50);

		walls.add(r1);
		walls.add(r2);

		for (Rectangle r : walls) {
			r.setFill(Color.BLUE);
		}
		g.getChildren().addAll(walls);
	}

	public List<Rectangle> getWalls() {
		return walls;
	}

}
