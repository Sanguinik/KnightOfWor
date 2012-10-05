package com.wordpress.marleneknoche.model;

import javafx.beans.value.WritableValue;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Enemy extends Figure{
	
	private String name;
	
	public Enemy(Maze maze, TypeOfFigure type, int x, int y){
		super(maze, type, x, y);
		name = type.name();
		rectangle.setFill(Color.GREEN);
		direction = Direction.UP;
	}
	
	
	public void shoot(){
		
	}
	
	public String getName(){
		return name;
	}

}
