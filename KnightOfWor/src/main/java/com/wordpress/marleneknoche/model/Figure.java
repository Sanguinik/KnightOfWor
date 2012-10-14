package com.wordpress.marleneknoche.model;

import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class Figure {

	private static final int HEIGHT = 40;

	private static final int WIDTH = 40;

	private final int points;
	private Bullet bullet;
	private Boolean alive = true;
	private final Rectangle rectangle;
	private Maze maze;
	private static final double DISTANCE = 3;
	private static final double RECOIL = 1;
	protected final Group group;
	protected final ImageView imageView;

	private final CollisionDetector cd = new CollisionDetector();

	private Direction direction;

	public Figure(Maze maze, TypeOfFigure type, double x, double y) {
		this.setMaze(maze);
		rectangle = new Rectangle(x, y, WIDTH, HEIGHT);
		points = TypeOfFigure.getPoints(type);
		imageView = new ImageView();
		group = new Group();
		getGroup().getChildren().add(rectangle);
		getGroup().getChildren().add(imageView);
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
			rectangle.setY(yForCollision);
			imageView.setY(yForCollision);

		} else {
			rectangle.setY(yForNoCollision);
			imageView.setY(yForNoCollision);
		}
	}

	private void moveXDependingOnCollision(double xForCollision,
			double xForNoCollision) {

		boolean collision = cd.isCollide(getMaze().getWalls(), rectangle);

		if (collision) {
			rectangle.setX(xForCollision);
			imageView.setX(xForCollision);
		} else {
			rectangle.setX(xForNoCollision);
			imageView.setX(xForNoCollision);
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

	public Group getGroup() {
		return group;
	}
}
