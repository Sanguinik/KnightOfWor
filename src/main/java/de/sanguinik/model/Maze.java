package de.sanguinik.model;

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
public class Maze extends EmptyMaze{

	private List<Rectangle> walls = new ArrayList<Rectangle>();

	public Maze() {
		createMaze();
		walls = super.getWalls();
	}

	private void createMaze() {
		super.getWalls().addAll(walls);
	}

	public List<Rectangle> getWalls() {
		return walls;
	}

}
