package com.wordpress.marleneknoche.view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

/**
 * 
 * @author marlene
 * 
 */

public class TitleScreen extends Application {

	private static final double BUTTON_SIZE = 120.0;

	public static void main(String[] args) {
		
		launch(args);
	}

	@Override
	public void start(final Stage primaryStage) {
		primaryStage.setTitle("Knight of Wor");
		primaryStage.setResizable(false);
		
		Media sound = new Media("file:/C:/KoW.mp3");
		final MediaPlayer pl = new MediaPlayer(sound);
        pl.play();
		
		
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(30);
		grid.setVgap(30);

		Button newGame = new Button();
		newGame.setText("Neues Spiel");
		newGame.setPrefWidth(BUTTON_SIZE);
		grid.add(newGame, 0, 0);

		newGame.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				pl.stop();
				PlayFieldScreen psc = new PlayFieldScreen();
				try {
					psc.start(primaryStage);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		});

		Button options = new Button();
		options.setText("Optionen");
		options.setPrefWidth(BUTTON_SIZE);
		grid.add(options, 0, 1);

		options.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				Options optionsGUI = new Options();
				optionsGUI.start(primaryStage);
			}
		});

		Button highscore = new Button();
		highscore.setText("Highscore");
		highscore.setPrefWidth(BUTTON_SIZE);
		grid.add(highscore, 0, 2);

		highscore.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				HighscoreScreen highscoreScreen = new HighscoreScreen();
				highscoreScreen.start(primaryStage);
			}

		});

		Button about = new Button();
		about.setText("Credits");
		about.setPrefWidth(BUTTON_SIZE);
		grid.add(about, 0, 3);

		about.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Credits credits = new Credits();
				credits.start(primaryStage);
			}
		});

		Button close = new Button();
		close.setText("Beenden");
		close.setPrefWidth(BUTTON_SIZE);
		grid.add(close, 0, 4);

		close.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.exit(0);
			}
		});

		Scene scene = new Scene(grid, 1024, 740);
		scene.getStylesheets().add(
				TitleScreen.class.getResource("TitleScreen.css")
						.toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
