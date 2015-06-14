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
//		Group g = new Group();

		//walls.add(new Rectangle(x,y,l,h);
		// outer walls
//		walls.add(new Rectangle(112, 70, 800, 10));
//		walls.add(new Rectangle(112, 563, 800, 10));
//		walls.add(new Rectangle(112, 70, 10, 493));
//		walls.add(new Rectangle(902, 70, 10, 493));
		// inner walls first line - 72 | 83
		walls.add(new Rectangle(400, 70, 10, 83));
		walls.add(new Rectangle(616, 70, 10, 83));
		// inner walls second line -
		walls.add(new Rectangle(184, 153, 144, 10));
		walls.add(new Rectangle(688, 153, 144, 10));
		// third line |
		walls.add(new Rectangle(184, 153, 10, 83));
		walls.add(new Rectangle(472, 153, 10, 83));
		walls.add(new Rectangle(544, 153, 10, 83));
		walls.add(new Rectangle(832, 153, 10, 83));
		// fourth line -
		walls.add(new Rectangle(256, 236, 144, 10));
		walls.add(new Rectangle(616, 236, 144, 10));
		// fifth line |
		walls.add(new Rectangle(256, 236, 10, 166));
		walls.add(new Rectangle(760, 236, 10, 166));
		// sixth line -
		walls.add(new Rectangle(112, 319, 72, 10));
		walls.add(new Rectangle(400, 319, 72, 10));
		walls.add(new Rectangle(544, 319, 72, 10));
		walls.add(new Rectangle(832, 319, 72, 10));
		// seventh line |
		walls.add(new Rectangle(328, 319, 10, 83));
		walls.add(new Rectangle(688, 319, 10, 83));
		// eighth line -
		walls.add(new Rectangle(400, 402, 216, 10));
		// ninth line |
		walls.add(new Rectangle(184, 402, 10, 83));
		walls.add(new Rectangle(832, 402, 10, 83));
		// tenth line -
		walls.add(new Rectangle(400, 485, 72, 10));
		walls.add(new Rectangle(544, 485, 72, 10));
		// eleventh line |
		walls.add(new Rectangle(328, 485, 10, 83));
		walls.add(new Rectangle(688, 485, 10, 83));

		for (Rectangle r : walls) {
			r.setFill(Color.BLUE);
			
		}
		super.getWalls().addAll(walls);
//		g.getChildren().addAll(walls);
	}

	public List<Rectangle> getWalls() {
		return walls;
	}

}
