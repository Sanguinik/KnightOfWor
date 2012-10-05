package com.wordpress.marleneknoche.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.animation.TimelineBuilder;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.GroupBuilder;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SceneBuilder;
import javafx.scene.effect.Lighting;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.RectangleBuilder;
import javafx.stage.Stage;
import javafx.util.Duration;

import com.wordpress.marleneknoche.model.CollisionDetector;
import com.wordpress.marleneknoche.model.Direction;
import com.wordpress.marleneknoche.model.Keyboard;
import com.wordpress.marleneknoche.model.Maze;
import com.wordpress.marleneknoche.model.Player;
import com.wordpress.marleneknoche.model.Enemy;
import com.wordpress.marleneknoche.model.TypeOfFigure;

public class PlayFieldScreen extends Application {
	
	public Timeline moveEnemy = new Timeline();
	private List<Enemy> enemyList= new ArrayList<Enemy>();
	
	
	

	@Override
	public void start(final Stage primaryStage) throws Exception {
		Group root = new Group();
		primaryStage.setTitle("Knight of Wor");
		primaryStage.setResizable(false);

		final Maze maze = new Maze();
		Player player = new Player(maze, TypeOfFigure.PLAYER, 130, 510);
		Enemy enemy1 = new Enemy(maze, TypeOfFigure.BURWOR, 130, 130);
		Enemy enemy2 = new Enemy(maze, TypeOfFigure.GARWOR, 855, 510);
		Enemy enemy3 = new Enemy(maze, TypeOfFigure.THORWOR, 855, 130);
		enemyList.add(enemy1);
		enemyList.add(enemy2);
		enemyList.add(enemy3);
		Keyboard keyboard = new Keyboard(player);
		Rectangle exit = new Rectangle(400, 300, 100, 100);
		exit.setFill(Color.ROSYBROWN);

		root.getChildren().add(exit);
		root.getChildren().add(player.getRectangle());
		root.getChildren().add(enemy1.getRectangle());
		root.getChildren().add(enemy2.getRectangle());
		root.getChildren().add(enemy3.getRectangle());
		root.getChildren().addAll(maze.getWalls());
		
		//moveEnemy = new Timeline();
		moveEnemy.setCycleCount(Timeline.INDEFINITE);
        moveEnemy.setAutoReverse(false);
		
		EventHandler<ActionEvent> onFinished = new EventHandler<ActionEvent>() {
			

			@Override
			public void handle(ActionEvent t) {
				for(Enemy e: enemyList){
				if(e.move()== true){
					Random generator = new Random();
					while(e.move()== true){
					e.changeDirection(Direction.intToDirection(generator.nextInt(4)));
					e.move();
					}
				
				}
				
				e.move();
				
			}}
			
		};
		
		KeyFrame keyframe = new KeyFrame(Duration.millis(30), onFinished);
		moveEnemy.getKeyFrames().add(keyframe);
		moveEnemy.play();
		
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
