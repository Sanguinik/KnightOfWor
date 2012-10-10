package com.wordpress.marleneknoche.model;

import java.util.List;

import javafx.scene.shape.Rectangle;

/**
 * The Collision Detecter checks if one rectangle hits an other one.
 * 
 * @author marlene
 * 
 */

public class CollisionDetector {

	public boolean isCollide(Rectangle r1, Rectangle r2) {
		if (r1.intersects(r2.getBoundsInParent())) {
			return true;
		}
		return false;
	}

	public boolean isCollide(List<Rectangle> walls, Rectangle r) {
		for (Rectangle wall : walls) {
			if (isCollide(wall, r)) {
				return true;
			}

		}
		return false;
	}

}
