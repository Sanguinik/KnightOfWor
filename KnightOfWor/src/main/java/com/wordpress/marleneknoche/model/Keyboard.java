package com.wordpress.marleneknoche.model;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import com.wordpress.marleneknoche.view.PlayFieldScreen;;

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
			player.changeDirection(Direction.UP);
			player.move();
			break;
		case S:
			player.changeDirection(Direction.DOWN);
			player.move();
			break;
		case A:
			player.changeDirection(Direction.LEFT);
			player.move();
			break;
		case D:
			player.changeDirection(Direction.RIGHT);
			player.move();
			break;
		case SPACE:
			player.shoot();
			break;
		case P:
			
	
		
		}

	}

}
