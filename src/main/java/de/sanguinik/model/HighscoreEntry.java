package de.sanguinik.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class HighscoreEntry {

	private final SimpleStringProperty name;
	
	private final SimpleIntegerProperty score;
	
	public HighscoreEntry(String name, int score){
		this.name = new SimpleStringProperty(name);
		this.score = new SimpleIntegerProperty(score);
	}
	
	public String getName(){
		return name.get();
	}
	
	public int getScore(){
		return score.get();
	}
}
