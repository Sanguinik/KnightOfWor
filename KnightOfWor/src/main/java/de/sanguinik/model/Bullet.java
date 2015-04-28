package de.sanguinik.model;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public class Bullet extends Figure {

	private final List<Figure> targets = new ArrayList<Figure>();
	private final static String PATH = "/de/sanguinik/model/";

	private boolean active = true;

	private final ShootingFigure shooter;

	public Bullet(final Maze maze, final Direction direction, final double x,
			final double y, final ShootingFigure shooter) {
		super(maze, TypeOfFigure.BULLET, x, y);
		setDirection(direction);
		setDistance(6);
		this.shooter = shooter;
		Image playerBullet = new Image(
				PATH + "note.png");
		Image enemyBullet = new Image(
				PATH + "note_gegner.png");
		if (shooter.getType() == TypeOfFigure.PLAYER) {
			getImageView().setImage(playerBullet);
		} else {
			getImageView().setImage(enemyBullet);
		}
		getImageView().setX(x);
		getImageView().setY(y);
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
