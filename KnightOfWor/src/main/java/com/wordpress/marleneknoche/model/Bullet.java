package com.wordpress.marleneknoche.model;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;

import com.wordpress.marleneknoche.util.Callback;

public class Bullet extends Figure {

	private String owner;
	private final List<Callback> collisionCallbacks = new ArrayList<Callback>();
	private boolean active = true;

	public Bullet(Maze maze, Color color, Direction direction, double x,
			double y) {
		super(maze, x, y);
		setDirection(direction);
		this.distance = 6;
		rectangle.setFill(color);
	}

	public void isHitByOtherBullet() {

	}

	@Override
	public void onCollision() {
		active = false;
		for (Callback c : collisionCallbacks) {
			c.call();
		}
	}

	public void addCollisionCallback(Callback callback) {
		collisionCallbacks.add(callback);
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
