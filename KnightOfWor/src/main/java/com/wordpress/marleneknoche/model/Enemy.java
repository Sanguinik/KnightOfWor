package com.wordpress.marleneknoche.model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Enemy {
	
	private String name;
	private int points;
	private Bullet bullet;
	private Boolean alive = true;
	private final Rectangle enemyRectangle;
	private final Maze maze;
	private static final double DISTANCE = 5;
	
	public Enemy(TypeOfEnemy type, Maze maze){
		name = type.name();
		points = TypeOfEnemy.getPoints(type);
		enemyRectangle = new Rectangle(855, 510, 40, 40);
		enemyRectangle.setFill(Color.GREEN);
		this.maze = maze;
		move();
	}
	
	public void move(){
		moveLeft();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		moveLeft();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public int getPoints(){
		return points;
	}
	
	public void shoot(){
		
	}
	
	public String getName(){
		return name;
	}
	
	public Boolean isAlive(){
		return alive;
	}
	
	public void setAlive(Boolean alive){
		this.alive=alive;
	}
	
	public void moveRight() {
		moveX(DISTANCE);
	}

	public void moveLeft() {
		moveX(-DISTANCE);
	}

	public void moveDown() {
		moveY(DISTANCE);
	}

	public void moveUp() {
		moveY(-DISTANCE);
	}
	
	private void moveX(double distance) {
		double x = enemyRectangle.getX();
		CollisionDetector cd = new CollisionDetector();
		for (Rectangle r : maze.getWalls()) {
			if (cd.isCollide(enemyRectangle, r)) {
				if (distance > 0) {
					enemyRectangle.setX(x - 1);
				} else {
					enemyRectangle.setX(x + 1);
				}
				return;
			} else {
				enemyRectangle.setX(x + distance);
			}
		}
	}

	private void moveY(double distance) {
		double y = enemyRectangle.getY();
		CollisionDetector cd = new CollisionDetector();
		for (Rectangle r : maze.getWalls()) {
			if (cd.isCollide(enemyRectangle, r)) {
				if (distance > 0) {
					enemyRectangle.setY(y - 1);
				} else {
					enemyRectangle.setY(y + 1);
				}
				return;
			} else {
				enemyRectangle.setY(y + distance);
			}
		}
	}
	
	public Rectangle getEnemyRectangle() {
		return enemyRectangle;
	}


}
