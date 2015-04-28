package de.sanguinik.model;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Keyboard implements EventHandler<KeyEvent> {

	private final Player player;
	private static final String PATH = "/de/sanguinik/model/";
	private static final Image IMAGE_UP = new Image(
			PATH + "hannes_up.png");
	private static final Image IMAGE_DOWN = new Image(
			PATH + "hannes_down.png");
	private static final Image IMAGE_LEFT = new Image(
			PATH + "hannes_left.png");
	private static final Image IMAGE_RIGHT = new Image(
			PATH +"hannes_right.png");

	public Keyboard(Player player) {
		this.player = player;
	}

	@Override
	public void handle(KeyEvent e) {
		KeyCode code = e.getCode();

		switch (code) {

		case W:
			player.setDirection(Direction.UP);
			player.move();
			player.getImageView().setImage(IMAGE_UP);
			break;
		case S:
			player.setDirection(Direction.DOWN);
			player.move();
			player.getImageView().setImage(IMAGE_DOWN);
			break;
		case A:
			player.setDirection(Direction.LEFT);
			player.move();
			player.getImageView().setImage(IMAGE_LEFT);
			break;
		case D:
			player.setDirection(Direction.RIGHT);
			player.move();
			player.getImageView().setImage(IMAGE_RIGHT);
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
