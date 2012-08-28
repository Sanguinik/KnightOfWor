package com.wordpress.marleneknoche.model;

public enum TypeOfEnemy {
	
	BURWOR, GARWOR, THORWOR, WORLUK, WIZARD;
	
	public static int getPoints(TypeOfEnemy type){
		if(type==BURWOR)
			return 100;
		if(type==GARWOR)
			return 200;
		if(type==THORWOR)
			return 500;
		if(type==WORLUK)
			return 1000;
		if(type==WIZARD)
			return 2500;
		return 0;
	}

}
