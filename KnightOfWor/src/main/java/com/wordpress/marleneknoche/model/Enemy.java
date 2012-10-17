package com.wordpress.marleneknoche.model;

import java.util.Random;

import javafx.scene.image.Image;

public class Enemy extends ShootingFigure {

	private final String name;
	private static final String PACKAGE_PATH = "/com/wordpress/marleneknoche/model/";
	private static final Image BURWOR_IMAGE = new Image(PACKAGE_PATH
			+ "BURWOR.png");
	private static final Image GARWOR_IMAGE = new Image(PACKAGE_PATH
			+ "GARWOR.png");
	private static final Image THORWOR_IMAGE = new Image(PACKAGE_PATH
			+ "THORWOR.png");
	private static final Image WIZARD_IMAGE = new Image(PACKAGE_PATH
			+ "WIZARD.png");
	private static final Image WORLUK_IMAGE = new Image(PACKAGE_PATH
			+ "WORLUK.png");

	public Enemy(final Maze maze, final TypeOfFigure type, final double x,
			final double y) {
		super(maze, x, y);
		name = type.name();
		setImageByMonster(type);
		getImageView().setX(x);
		getImageView().setY(y);
	}

	public String getName() {
		return name;
	}

	private void setImageByMonster(final TypeOfFigure type) {
		switch (type) {
		case BURWOR:
			getImageView().setImage(BURWOR_IMAGE);
			break;
		case GARWOR:
			getImageView().setImage(GARWOR_IMAGE);
			break;
		case THORWOR:
			getImageView().setImage(THORWOR_IMAGE);
			break;
		case WIZARD:
			getImageView().setImage(WIZARD_IMAGE);
			break;
		case WORLUK:
			getImageView().setImage(WORLUK_IMAGE);
			break;
		default:
			break;
		}
	}

	/**
	 * Enemy wechselt per zufall die Richtung wenn er kollidiert
	 */
	@Override
	public void onCollision() {
		int random = new Random().nextInt(4);

		Direction futureDirection = Direction.values()[random];

		setDirection(futureDirection);
	}

}
