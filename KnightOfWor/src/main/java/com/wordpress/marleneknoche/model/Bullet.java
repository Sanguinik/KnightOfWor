package com.wordpress.marleneknoche.model;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Bullet extends Figure {

	private final List<Figure> targets = new ArrayList<Figure>();

	private boolean active = true;

	private final ShootingFigure shooter;

	public Bullet(final Maze maze, final Color color,
			final Direction direction, final double x, final double y,
			final ShootingFigure shooter) {
		super(maze, TypeOfFigure.BULLET, x, y);
		setDirection(direction);
		setDistance(6);
		getRectangle().setFill(color);
		this.shooter = shooter;
	}

	public void setTargets(final List<Figure> newTargets) {
		targets.clear();
		targets.addAll(newTargets);
	}

	/**
	 * We need to override the move method because we need to check if the
	 * bullet has hit a target before we move.
	 */
	@Override
	public void move() {
		Figure target = checkForCollisionWithTargets();

		// When target is null, no target was hit
		if (target == null) {
			super.move();
		} else {
			// When a target was hit, the bullet has to stop the movement.

			active = false;
			shooter.bulletHasHitATarget(target);
		}

	}

	private Figure checkForCollisionWithTargets() {
		Rectangle futurePosition = getFuturePosition();

		for (Figure target : targets) {
			if (cd.isCollide(target.getRectangle(), futurePosition)) {
				return target;
			}
		}

		return null;
	}

	@Override
	public void onCollisionWithMaze() {
		active = false;
		shooter.bulletHasHitTheMaze();
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(final boolean active) {
		this.active = active;
	}

}
