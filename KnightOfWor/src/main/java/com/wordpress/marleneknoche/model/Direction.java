package com.wordpress.marleneknoche.model;

public enum Direction {
	
	UP, DOWN, LEFT, RIGHT;
	
	public static Direction intToDirection(int index){
		return Direction.values()[index];
	}

}
