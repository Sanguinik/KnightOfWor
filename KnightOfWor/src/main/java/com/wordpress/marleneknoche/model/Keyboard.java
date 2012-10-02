package com.wordpress.marleneknoche.model;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Keyboard implements EventHandler<KeyEvent> {

	private final Player player;


	public Keyboard(Player player) {
		this.player = player;
	}

	@Override
	public void handle(KeyEvent e) {
		KeyCode code = e.getCode();

		switch (code) {

		case W:
			player.moveUp();
			break;
		case S:
			player.moveDown();
			break;
		case A:
			player.moveLeft();
			break;
		case D:
			player.moveRight();
			break;
		case SPACE:
			player.shoot();
			break;
	
		
		}

	}

}
