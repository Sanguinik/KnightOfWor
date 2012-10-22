package com.wordpress.marleneknoche.model;

import java.net.URL;

import javafx.scene.media.AudioClip;

import com.wordpress.marleneknoche.util.Callback;

public abstract class ShootingFigure extends Figure {

	private boolean hasBullet;
	private Callback onShootCallback;
	private AudioClip sound;

	public ShootingFigure(Maze maze, double x, double y) {
		super(maze, x, y);
	}

	public void shoot() {
		URL resource = getClass().getResource("fire.mp3");
		if (resource != null) {
			sound = new AudioClip(resource.toString());
		}

		if (!hasBullet) {
			hasBullet = true;
			// schieße
			if (!sound.isPlaying()) {
				sound.play();
			}

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
