package com.wordpress.marleneknoche.model;

import javafx.scene.shape.Rectangle;

public class Figure {
	
	protected int points;
	protected Bullet bullet;
	protected Boolean alive = true;
	protected Rectangle rectangle;
	protected Maze maze;
	protected static final double DISTANCE = 5;
	
	Direction direction;
	
	public Figure(Maze maze, TypeOfFigure type, int x, int y) {
		this.maze=maze;
		rectangle = new Rectangle(x, y, 40, 40);
		points = TypeOfFigure.getPoints(type);
	}
	
	public int getPoints() {
		return points;
	}
	
	public Boolean isAlive(){
		return alive;
	}

	public void setAlive(Boolean alive){
		this.alive=alive;
	}
	
	public Rectangle getRectangle(){
		return rectangle;
	}
	
	public void changeDirection(Direction direction){
		this.direction=direction;
	}
	
	
	public boolean move(){ //returned true, falls Kollision
		
		double x = rectangle.getX();
		double y = rectangle.getY();
		CollisionDetector cd = new CollisionDetector();
		
		switch(direction)
		{
			case UP:
				for (Rectangle r : maze.getWalls()) {
					if (cd.isCollide(rectangle, r)) {
						rectangle.setY(y + 1);
						return true;
					} else {
						rectangle.setY(y - 5);
					}
				}
				return false;
				
			case DOWN:
				for (Rectangle r : maze.getWalls()) {
					if (cd.isCollide(rectangle, r)) {		
						rectangle.setY(y - 1);
						return true;
					} else {
						rectangle.setY(y + 5);
					}
				}
				return false;
			case LEFT:
				
				for (Rectangle r : maze.getWalls()) {
					if (cd.isCollide(rectangle, r)) {
						rectangle.setX(x + 1);
						return true;
					} else {
						rectangle.setX(x - 5);
					}
				}
				return false;
				
			case RIGHT:
				
				for (Rectangle r : maze.getWalls()) {
					if (cd.isCollide(rectangle, r)) {
						rectangle.setX(x - 1);
						return true;
					} else {
						rectangle.setX(x + 5);
					}
				}
				return false;
				
		}
		return true;
	}

}
