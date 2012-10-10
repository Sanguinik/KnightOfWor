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
			if (player.willCollideInFuture(player.getRectangle(),
					player.getDirection()))
				player.move();
			break;
		case S:
			player.setDirection(Direction.DOWN);
			if (player.willCollideInFuture(player.getRectangle(),
					player.getDirection()))
				player.move();
			break;
		case A:
			player.setDirection(Direction.LEFT);
			if (player.willCollideInFuture(player.getRectangle(),
					player.getDirection()))
				player.move();
			break;
		case D:
			player.setDirection(Direction.RIGHT);
			if (player.willCollideInFuture(player.getRectangle(),
					player.getDirection()))
				player.move();
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
}
