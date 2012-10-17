package com.wordpress.marleneknoche.model;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;

import com.wordpress.marleneknoche.util.Callback;

public class Bullet extends Figure {

	private final List<Callback> collisionCallbacks = new ArrayList<Callback>();
	private boolean active = true;

	public Bullet(final Maze maze, final Color color,
			final Direction direction, final double x, final double y) {
		super(maze, x, y);
		setDirection(direction);
		setDistance(6);
		getRectangle().setFill(color);
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

	public void addCollisionCallback(final Callback callback) {
		collisionCallbacks.add(callback);
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(final boolean active) {
		this.active = active;
	}

}
