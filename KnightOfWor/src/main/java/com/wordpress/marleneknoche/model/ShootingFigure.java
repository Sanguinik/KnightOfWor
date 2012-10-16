package com.wordpress.marleneknoche.model;

import com.wordpress.marleneknoche.util.Callback;

public abstract class ShootingFigure extends Figure {

	private boolean hasBullet;
	private Callback onShootCallback;

	public ShootingFigure(Maze maze, double x, double y) {
		super(maze, x, y);
	}

	public void shoot() {
		if (!hasBullet) {
			hasBullet = true;
			// schieße
			onShootCallback.call();
		}
	}

	public void setOnShootCallback(Callback callback) {
		onShootCallback = callback;
	}

	public void bulletHasArrived() {
		hasBullet = false;
	}

}
