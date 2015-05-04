package de.sanguinik.view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class HighscoreScreen extends Application {

	@Override
	public void start(final Stage primaryStage) {

		primaryStage.setTitle("Knight of Wor - Highscore");
		primaryStage.setResizable(false);

		GridPane grid = new GridPane();
		grid.setId("highscoreGrid");
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(30);
		grid.setVgap(30);
		
		final Label highscoreTitle = new Label("Highscore");
		highscoreTitle.setFont(new Font("Monospace", 48));
		highscoreTitle.setTextFill(Color.WHITE);
		
		TableView highscoreTable = new TableView();
		
		TableColumn nameCol = new TableColumn("Name");
		nameCol.setMinWidth(150);
		TableColumn scoreCol = new TableColumn("Punkte");
		scoreCol.setMinWidth(150);
		
		highscoreTable.getColumns().addAll(nameCol, scoreCol);
		
		
		
		Button okBtn = new Button();
		okBtn.setText("Zurück");
		okBtn.setAlignment(Pos.CENTER);
		okBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				TitleScreen title = new TitleScreen();
				title.start(primaryStage);
			}

		});

		grid.add(highscoreTitle, 0,0);
		grid.add(highscoreTable, 0,1);
		grid.add(okBtn,0,2);
		StackPane root = new StackPane();
		root.getChildren().add(grid);
		Scene scene = new Scene(root, 1024, 740);
		scene.getStylesheets().add(
				Credits.class.getResource("HighscoreScreen.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();

	}

}
