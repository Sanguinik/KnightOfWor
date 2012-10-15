package com.wordpress.marleneknoche.model;

import com.wordpress.marleneknoche.view.PlayFieldScreen;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Bullet{
	
	private static final int HEIGHT = 40;

	private static final int WIDTH = 40;
	
	private Color color;
	private String owner;
	private Maze maze;
	private static Rectangle rectangle;
	private Direction direction;
	private static final double DISTANCE = 3;
	private static final double RECOIL = 1;
	private static boolean bulletExists=false;
	
	private final CollisionDetector cd = new CollisionDetector();
	
	public Bullet(Maze maze, Color color, Direction direction, double x, double y){
		this.color=color;
		this.direction=direction;
		this.maze=maze;
		PlayFieldScreen.root.getChildren().remove(rectangle);
		rectangle = new Rectangle(x, y, WIDTH, HEIGHT);
		rectangle.setFill(color);
		PlayFieldScreen.root.getChildren().add(rectangle);
	}
	
	public Maze getMaze() {
		return maze;
	}
	
	public void move() {

		double x = rectangle.getX();
		double y = rectangle.getY();

		switch (direction) {
		case UP:

			moveYDependingOnCollision(y + RECOIL, y - DISTANCE);
			break;

		case DOWN:

			moveYDependingOnCollision(y - RECOIL, y + DISTANCE);
			break;

		case LEFT:

			moveXDependingOnCollision(x + RECOIL, x - DISTANCE);
			break;
		case RIGHT:

			moveXDependingOnCollision(x - RECOIL, x + DISTANCE);
			break;

		}
	}

	private void moveYDependingOnCollision(double yForCollision,
			double yForNoCollision) {

		boolean collision = cd.isCollide(getMaze().getWalls(), rectangle);

		if (collision) {
			PlayFieldScreen.root.getChildren().remove(rectangle);
			bulletExists=false;

		} else {
			rectangle.setY(yForNoCollision);
		
		}
	}

	private void moveXDependingOnCollision(double xForCollision,
			double xForNoCollision) {

		boolean collision = cd.isCollide(getMaze().getWalls(), rectangle);

		if (collision) {
			PlayFieldScreen.root.getChildren().remove(rectangle);
			bulletExists=false;
		
		} else {
			rectangle.setX(xForNoCollision);
			
		}

	}

	public boolean willCollideInFuture() {
		Double moveX = 0.0;
		Double moveY = 0.0;

		switch (direction) {
		case UP:
			moveY = -DISTANCE;
			break;

		case DOWN:
			moveY = DISTANCE;
			break;

		case LEFT:
			moveX = -DISTANCE;
			break;

		case RIGHT:
			moveX = DISTANCE;
			break;
		}

		double futureX = rectangle.getX() + moveX;
		double futureY = rectangle.getY() + moveY;

		Rectangle futurePosition = new Rectangle(futureX, futureY, WIDTH,
				HEIGHT);

		return cd.isCollide(getMaze().getWalls(), futurePosition);
	}

	
	public Rectangle getRectangle(){
		return rectangle;
	}
	
	public void isHitByOtherBullet(){
		
	}

	public static boolean getBulletExists() {
		return bulletExists;
	}

	public static void setBulletExists(boolean bExists) {
		bulletExists = bExists;
	}
	
	

}
