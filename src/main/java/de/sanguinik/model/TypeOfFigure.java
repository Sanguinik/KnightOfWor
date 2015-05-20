package de.sanguinik.model;

public enum TypeOfFigure {

	PLAYER(1000),

	BURWOR(100),

	GARWOR(200),

	THORWOR(500),

	WORLUK(1000),

	WIZARD(2500),

	BULLET(0)

	;

	private int points;

	private TypeOfFigure(final int points) {
		this.points = points;
	}

	public int getPoints() {
		return points;
	}

}
