package com.wordpress.marleneknoche.model;

public class Player {
	
	private int points = 1000;
	private int score = 0;
	private Boolean alive = true;
	private Bullet bullet;
	private int lives = 4;
	private final int maxLives = 4;
	
	public Player(){
		lives=4;
	}
	
	public int getPoints(){
		return points;
	}
	
	public int getScore(){
		return score;
	}
	
	public void setScore(int score){
		this.score=score;
	}
	
	public Boolean isAlive(){
		return alive;
	}
	
	public void setAlive(Boolean alive){
		this.alive=alive;
	}
	
	public void shoot(){
		
	}
	
	public int getLives(){
		return lives;
	}
	
	public void setLives(int lives){
		this.lives=lives;
		if(lives>maxLives){
			this.lives=maxLives;	
		}
	}
	
	public int getMaxLives(){
		return maxLives;
	}
	
	public void move(){
		
	}

}
