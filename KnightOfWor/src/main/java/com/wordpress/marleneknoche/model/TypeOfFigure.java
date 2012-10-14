package com.wordpress.marleneknoche.model;

public enum TypeOfFigure {

	PLAYER, BURWOR, GARWOR, THORWOR, WORLUK, WIZARD;

	public static int getPoints(TypeOfFigure type) {
		if (type == PLAYER)
			return 1000;
		if (type == BURWOR)
			return 100;
		if (type == GARWOR)
			return 200;
		if (type == THORWOR)
			return 500;
		if (type == WORLUK)
			return 1000;
		if (type == WIZARD)
			return 2500;
		return 0;
	}

}
