package com.wordpress.marleneknoche.view;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class PlayFieldScreen extends Application {

	@Override
	public void start(final Stage primaryStage) throws Exception {
		Group root = new Group();
		primaryStage.setTitle("Knight of Wor");
		primaryStage.setResizable(false);

		final Rectangle rect = new Rectangle(0, 0, 100, 100);
		rect.setFill(Color.BLUE);
		root.getChildren().add(rect);

		Rectangle exit = new Rectangle(400, 300, 100, 100);
		exit.setFill(Color.ROSYBROWN);

		root.getChildren().add(exit);

		exit.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				GameOver gameOver = new GameOver();
				gameOver.start(primaryStage);

			}

		});

		Scene scene = new Scene(root, 1024, 740);
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent ke) {
				if (ke.getCode() == KeyCode.D && rect.getX() < 924) {
					rect.setX(rect.getX() + 5);
				} else {
					if (ke.getCode() == KeyCode.S && rect.getY() < 640) {
						rect.setY(rect.getY() + 5);
					} else {
						if (ke.getCode() == KeyCode.W && rect.getY() != 0) {
							rect.setY(rect.getY() - 5);
						} else {
							if (ke.getCode() == KeyCode.A && rect.getX() != 0) {
								rect.setX(rect.getX() - 5);
							}
						}
					}

				}
			}
		});

		scene.setFill(Color.BLACK);
		primaryStage.setScene(scene);
		primaryStage.show();

	}
}
