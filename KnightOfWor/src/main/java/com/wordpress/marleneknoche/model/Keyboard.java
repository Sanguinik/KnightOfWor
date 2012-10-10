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
			player.setDirection(Direction.UP);
			moveDependingOnCollision();
			break;
		case S:
			player.setDirection(Direction.DOWN);
			moveDependingOnCollision();
			break;
		case A:
			player.setDirection(Direction.LEFT);
			moveDependingOnCollision();
			break;
		case D:
			player.setDirection(Direction.RIGHT);
			moveDependingOnCollision();
			break;
		case SPACE:
			player.shoot();
			break;
		case P:
			break;
		default:
			break;

		}

	}

	private void moveDependingOnCollision() {
		if (!player.willCollideInFuture()) {
			player.move();
		}
	}
}
