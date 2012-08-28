package com.wordpress.marleneknoche.model;

public class Enemy {
	
	private String name;
	private int points;
	private Bullet bullet;
	private Boolean alive = true;
	
	public Enemy(TypeOfEnemy type){
		name = type.name();
		points = TypeOfEnemy.getPoints(type);
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
	
	public void move(){
		
	}


}
