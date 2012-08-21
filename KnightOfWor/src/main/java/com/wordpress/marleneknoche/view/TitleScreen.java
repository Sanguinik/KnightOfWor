package com.wordpress.marleneknoche.view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * 
 * @author marlene
 *
 */

public class TitleScreen extends Application {

	private static final double BUTTON_SIZE = 100.0;

	public static void main(String[] args){
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage){
		primaryStage.setTitle("Knight of Wor");
		
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(30);
		grid.setVgap(30);
		
		
		Button newGame = new Button();
		newGame.setText("Neues Spiel");
		newGame.setPrefWidth(BUTTON_SIZE);
		grid.add(newGame, 0, 0);
		
		Button options = new Button();
		options.setText("Optionen");
		options.setPrefWidth(BUTTON_SIZE);
		grid.add(options, 0, 1);
		
		Button highscore = new Button();
		highscore.setText("Highscore");
		highscore.setPrefWidth(BUTTON_SIZE);
		grid.add(highscore, 0, 2);
		
		Button about = new Button();
		about.setText("Credits");
		about.setPrefWidth(BUTTON_SIZE);
		grid.add(about, 0, 3);
		
		Button close = new Button();
		close.setText("Beenden");
		close.setPrefWidth(BUTTON_SIZE);
		grid.add(close, 0, 4);
		
		close.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event){
				System.exit(0);
			}
		});
		
		Scene scene = new Scene(grid, 1024,768);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
