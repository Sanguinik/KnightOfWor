package com.wordpress.marleneknoche.model;

import javafx.scene.shape.Rectangle;

public class Figure {

	private final int points;
	private Bullet bullet;
	private Boolean alive = true;
	private final Rectangle rectangle;
	private Maze maze;
	private static final double DISTANCE = 3;
	private static final double RECOIL = 1;

	private final CollisionDetector cd = new CollisionDetector();

	private Direction direction;

	public Figure(Maze maze, TypeOfFigure type, int x, int y) {
		this.setMaze(maze);
		rectangle = new Rectangle(x, y, 40, 40);
		points = TypeOfFigure.getPoints(type);
	}

	public int getPoints() {
		return points;
	}

	public Boolean isAlive() {
		return alive;
	}

	public void setAlive(Boolean alive) {
		this.alive = alive;
	}

	public Rectangle getRectangle() {
		return rectangle;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public Direction getDirection() {
		return direction;
	}

	public Maze getMaze() {
		return maze;
	}

	public void setMaze(Maze maze) {
		this.maze = maze;
	}

	public boolean move() { // returned true, falls Kollision

		double x = rectangle.getX();
		double y = rectangle.getY();

		switch (direction) {
		case UP:

			return moveYDependingOnCollision(y + RECOIL, y - DISTANCE);

		case DOWN:

			return moveYDependingOnCollision(y - RECOIL, y + DISTANCE);

		case LEFT:

			return moveXDependingOnCollision(x + RECOIL, x - DISTANCE);

		case RIGHT:

			return moveXDependingOnCollision(x - RECOIL, x + DISTANCE);

		default:
			return true;

		}
	}

	private boolean moveYDependingOnCollision(double yForCollision,
			double yForNoCollision) {
		for (Rectangle r : getMaze().getWalls()) {
			if (cd.isCollide(rectangle, r)) {
				rectangle.setY(yForCollision);
				return true;
			}
		}
		rectangle.setY(yForNoCollision);
		return false;
	}

	private boolean moveXDependingOnCollision(double xForCollision,
			double xForNoCollision) {
		for (Rectangle r : getMaze().getWalls()) {
			if (cd.isCollide(rectangle, r)) {
				rectangle.setX(xForCollision);
				return true;
			}
		}
		rectangle.setX(xForNoCollision);
		return false;

	}

	public boolean willCollideInFuture(Rectangle current, Direction direction) {
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

		Rectangle futurePosition = new Rectangle(current.getX(), current.getY());
		futurePosition.setX(futurePosition.getX() + moveX);
		futurePosition.setY(futurePosition.getY() + moveY);
		for (Rectangle r : getMaze().getWalls()) {
			if (cd.isCollide(futurePosition, r)) {
				return true;
			}
		}
		return false;
	}

}
