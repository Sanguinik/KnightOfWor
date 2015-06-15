package de.sanguinik.model;

import de.sanguinik.view.PlayFieldScreen;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Keyboard implements EventHandler<KeyEvent> {

	private final Player player;
	private final PlayFieldScreen screen;
	private static final String PATH = "/de/sanguinik/model/";
	private static final Image IMAGE_UP = new Image(PATH + "hannes_up.png");
	private static final Image IMAGE_DOWN = new Image(PATH + "hannes_down.png");
	private static final Image IMAGE_LEFT = new Image(PATH + "hannes_left.png");
	private static final Image IMAGE_RIGHT = new Image(PATH
			+ "hannes_right.png");

	public Keyboard(Player player, PlayFieldScreen screen) {
		this.player = player;
		this.screen = screen;
	}

	@Override
	public void handle(KeyEvent e) {
		KeyCode code = e.getCode();

		switch (code) {

		case W:
			if (player.isMovable()) {
				player.setDirection(Direction.UP);
				player.move();
				player.getImageView().setImage(IMAGE_UP);
			}
			break;
		case S:
			if (player.isMovable()) {
				player.setDirection(Direction.DOWN);
				player.move();
				player.getImageView().setImage(IMAGE_DOWN);
			}
			break;
		case A:
			if (player.isMovable()) {
				player.setDirection(Direction.LEFT);
				player.move();
				player.getImageView().setImage(IMAGE_LEFT);
			}
			break;
		case D:
			if (player.isMovable()) {
				player.setDirection(Direction.RIGHT);
				player.move();
				player.getImageView().setImage(IMAGE_RIGHT);
			}
			break;
		case SPACE:
			if (player.isMovable()) {
				player.shoot();
			}
			break;
		case P:
			screen.pauseGame();
			player.toggleMoveable();
			break;
		case M:
			screen.muteMusic();
		default:
			break;

		}

	}
}
