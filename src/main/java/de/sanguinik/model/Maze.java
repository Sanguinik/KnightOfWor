package de.sanguinik.model;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * 
 * This class provides the a maze for the game.
 * 
 * @author marlene
 * 
 */
public class Maze{

	private List<Rectangle> walls = new ArrayList<Rectangle>();

	public Maze(String levelName) {
		createMaze(levelName);
		
	}

private void createMaze(String levelName) {
		
		double[][] values = loadJSON(levelName);
		
		Group g = new Group();

		for(int i = 0; i<values.length; i++){
			walls.add(new Rectangle(values[i][0], values[i][1], values[i][2], values[i][3]));
		}

		for (Rectangle r : walls) {
			r.setFill(Color.BLUE);
		}
		g.getChildren().addAll(walls);
	}

	
	public List<Rectangle> getWalls() {
		return walls;
	}
	
	public void addWalls(List<Rectangle> walls){
		this.walls.addAll(walls);
	}
	
	private double[][] loadJSON(String levelName){
		
		double[][] result = null;
		
		JSONParser parser = new JSONParser();
		
		try{
			
			Object obj = parser.parse(new FileReader("./src/main/resources/de/sanguinik/model/"+levelName+".json"));
			JSONObject jsonObject = (JSONObject) obj;

			double[][] blockValues = new double[jsonObject.size()][4];
			
			for(int i = 1; i <= jsonObject.size(); i++){
				JSONObject block = (JSONObject) jsonObject.get("Block"+i);
				
				long x = (Long) block.get("x");
				blockValues[i-1][0] = (double) x;

				long y = (Long) block.get("y");
				blockValues[i-1][1] = (double) y;
				
				long width = (Long) block.get("width");
				blockValues[i-1][2] = (double) width;
				
				long height = (Long) block.get("height");
				blockValues[i-1][3] = (double) height;

			}
			
			result = blockValues;
			
		} catch (IOException | ParseException e){
			e.printStackTrace();
		}
		return result;
	}

}
