package com.wordpress.marleneknoche.view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class HighscoreScreen extends Application {

	@Override
	public void start(final Stage primaryStage) {

		primaryStage.setTitle("Knight of Wor - Highscore");
		primaryStage.setResizable(false);

		Button okBtn = new Button();
		okBtn.setText("Zurück");
		okBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				TitleScreen title = new TitleScreen();
				title.start(primaryStage);
			}

		});

		StackPane root = new StackPane();
		root.getChildren().add(okBtn);
		Scene scene = new Scene(root, 1024, 740);
		primaryStage.setScene(scene);
		primaryStage.show();

	}

}
