package com.wordpress.marleneknoche.view;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import com.wordpress.marleneknoche.model.Keyboard;
import com.wordpress.marleneknoche.model.Maze;
import com.wordpress.marleneknoche.model.Player;
import com.wordpress.marleneknoche.model.Enemy;
import com.wordpress.marleneknoche.model.TypeOfEnemy;

public class PlayFieldScreen extends Application {

	@Override
	public void start(final Stage primaryStage) throws Exception {
		Group root = new Group();
		primaryStage.setTitle("Knight of Wor");
		primaryStage.setResizable(false);

		Maze maze = new Maze();
		Player player = new Player(maze);
		Enemy enemy = new Enemy(TypeOfEnemy.BURWOR, maze);
		Keyboard keyboard = new Keyboard(player);
		Rectangle exit = new Rectangle(400, 300, 100, 100);
		exit.setFill(Color.ROSYBROWN);

		root.getChildren().add(exit);
		root.getChildren().add(player.getPlayerRectangle());
		root.getChildren().add(enemy.getEnemyRectangle());
		root.getChildren().addAll(maze.getWalls());

		exit.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				GameOver gameOver = new GameOver();
				gameOver.start(primaryStage);

			}

		});
		
	
		Scene scene = new Scene(root, 1024, 740);
		scene.setOnKeyPressed(keyboard);
	

		scene.setFill(Color.BLACK);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		

	}
}
