package de.sanguinik.model;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public abstract class EmptyMaze{

	private final List<Rectangle> walls = new ArrayList<Rectangle>();

	public EmptyMaze() {
		createMaze();
	}

	private void createMaze() {
		Group g = new Group();

		// outer walls
		walls.add(new Rectangle(112, 70, 800, 10));
		walls.add(new Rectangle(112, 563, 800, 10));
		walls.add(new Rectangle(112, 70, 10, 493));
		walls.add(new Rectangle(902, 70, 10, 493));

		for (Rectangle r : walls) {
			r.setFill(Color.BLUE);
		}
		g.getChildren().addAll(walls);
	}

	
	public List<Rectangle> getWalls() {
		return walls;
	}
	
	public void addWalls(List<Rectangle> walls){
		this.walls.addAll(walls);
	}
	
}
