package com.wordpress.marleneknoche.model;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import com.wordpress.marleneknoche.util.Callback;

public abstract class ShootingFigure extends Figure {

	private boolean hasBullet;
	private Callback onShootCallback;
	
	AudioClip sound = new AudioClip("file:/C:/fire.mp3");
	//Media sound = new Media("file:/c:/fire.mp3");
	//MediaPlayer pl = new MediaPlayer(sound);
	

	public ShootingFigure(Maze maze, double x, double y) {
		super(maze, x, y);
	}

	public void shoot() {
		if (!hasBullet) {
			hasBullet = true;
			// schieße
			if(!sound.isPlaying()){
			sound.play();}
			
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
