package com.wordpress.marleneknoche.model;

import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public abstract class Figure {

	private static final int HEIGHT = 40;

	private static final int WIDTH = 40;

	private boolean alive = true;
	private final Maze maze;
	private double distance = 3;
	private final Group group;
	private final Rectangle rectangle;
	private final ImageView imageView;

	protected final CollisionDetector cd = new CollisionDetector();

	private Direction direction = Direction.RIGHT;

	private final TypeOfFigure type;

	public Figure(final Maze maze, final TypeOfFigure type, final double x,
			final double y) {
		this.maze = maze;
		this.type = type;
		rectangle = new Rectangle(x, y, WIDTH, HEIGHT);
		imageView = new ImageView();
		group = new Group();
		group.getChildren().add(rectangle);
		group.getChildren().add(imageView);
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(final double distance) {
		this.distance = distance;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(final boolean alive) {
		this.alive = alive;
	}

	public Rectangle getRectangle() {
		return rectangle;
	}

	public void setDirection(final Direction direction) {
		this.direction = direction;
	}

	public Direction getDirection() {
		return direction;
	}

	public void move() {

		if (willCollideWithMazeInFuture()) {
			onCollisionWithMaze();
		} else {

			double x = getRectangle().getX();
			double y = getRectangle().getY();

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

	public abstract void onCollisionWithMaze();

	private void setY(final double y) {
		getRectangle().setY(y);
		getImageView().setY(y);
	}

	private void setX(final double x) {
		getRectangle().setX(x);
		getImageView().setX(x);
	}

	public boolean willCollideWithMazeInFuture() {
		final Rectangle futurePosition = getFuturePosition();

		return cd.isCollide(maze.getWalls(), futurePosition);
	}

	protected Rectangle getFuturePosition() {
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

		final double futureX = getRectangle().getX() + moveX;
		final double futureY = getRectangle().getY() + moveY;

		final Rectangle futurePosition = new Rectangle(futureX, futureY, WIDTH,
				HEIGHT);
		return futurePosition;
	}

	public Maze getMaze() {
		return maze;
	}

	public Group getGroup() {
		return group;
	}

	public ImageView getImageView() {
		return imageView;
	}

	public TypeOfFigure getType() {
		return type;
	}

}
