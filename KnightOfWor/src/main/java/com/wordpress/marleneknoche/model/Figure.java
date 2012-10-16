package com.wordpress.marleneknoche.model;

import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public abstract class Figure {

	private static final int HEIGHT = 40;

	private static final int WIDTH = 40;

	private boolean alive = true;
	private final Maze maze;
	protected double distance = 3;
	protected final Group group;
	protected final Rectangle rectangle;
	protected final ImageView imageView;

	private final CollisionDetector cd = new CollisionDetector();

	private Direction direction = Direction.RIGHT;

	public Figure(Maze maze, double x, double y) {
		this.maze = maze;
		rectangle = new Rectangle(x, y, WIDTH, HEIGHT);
		imageView = new ImageView();
		group = new Group();
		group.getChildren().add(rectangle);
		group.getChildren().add(imageView);
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
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

	public void move() {

		if (willCollideInFuture()) {
			onCollision();
		} else {

			double x = rectangle.getX();
			double y = rectangle.getY();

			switch (direction) {
			case UP:

				setY(y - distance);

				break;

			case DOWN:

				setY(y + distance);
				break;

			case LEFT:

				setX(x - distance);
				break;
			case RIGHT:

				setX(x + distance);
				break;

			}
		}

	}

	public abstract void onCollision();

	private void setY(double y) {
		rectangle.setY(y);
		imageView.setY(y);
	}

	private void setX(double x) {
		rectangle.setX(x);
		imageView.setX(x);
	}

	public boolean willCollideInFuture() {
		double moveX = 0.0;
		double moveY = 0.0;

		switch (direction) {
		case UP:
			moveY = -distance;
			break;

		case DOWN:
			moveY = distance;
			break;

		case LEFT:
			moveX = -distance;
			break;

		case RIGHT:
			moveX = distance;
			break;
		}

		double futureX = rectangle.getX() + moveX;
		double futureY = rectangle.getY() + moveY;

		Rectangle futurePosition = new Rectangle(futureX, futureY, WIDTH,
				HEIGHT);

		return cd.isCollide(maze.getWalls(), futurePosition);
	}

	public Group getGroup() {
		return group;
	}

}
