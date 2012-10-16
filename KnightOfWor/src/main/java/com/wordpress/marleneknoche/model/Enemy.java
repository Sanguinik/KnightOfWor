package com.wordpress.marleneknoche.model;

import java.util.Random;

import javafx.scene.image.Image;

public class Enemy extends ShootingFigure {

	private final String name;
	private final int points;
	private final static String PACKAGE_PATH = "/com/wordpress/marleneknoche/model/";
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

	public Enemy(Maze maze, TypeOfFigure type, double x, double y) {
		super(maze, x, y);
		name = type.name();
		points = type.getPoints();
		setImageByMonster(type);
		imageView.setX(x);
		imageView.setY(y);
	}

	public String getName() {
		return name;
	}

	private void setImageByMonster(TypeOfFigure type) {
		switch (type) {
		case BURWOR:
			imageView.setImage(BURWOR_IMAGE);
			break;
		case GARWOR:
			imageView.setImage(GARWOR_IMAGE);
			break;
		case THORWOR:
			imageView.setImage(THORWOR_IMAGE);
			break;
		case WIZARD:
			imageView.setImage(WIZARD_IMAGE);
			break;
		case WORLUK:
			imageView.setImage(WORLUK_IMAGE);
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
