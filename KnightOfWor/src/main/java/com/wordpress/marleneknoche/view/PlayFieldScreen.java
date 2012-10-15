package com.wordpress.marleneknoche.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

import com.wordpress.marleneknoche.model.Bullet;
import com.wordpress.marleneknoche.model.Direction;
import com.wordpress.marleneknoche.model.Enemy;
import com.wordpress.marleneknoche.model.Keyboard;
import com.wordpress.marleneknoche.model.Maze;
import com.wordpress.marleneknoche.model.Player;
import com.wordpress.marleneknoche.model.TypeOfFigure;

public class PlayFieldScreen extends Application {

	public Timeline moveEnemy = new Timeline();
	private final List<Enemy> enemyList = new ArrayList<Enemy>();
	public final static List<Bullet> bulletList = new ArrayList<Bullet>();
	private static final int ONE_SECOND = 1000;
	private static final int FPS = 30;
    public static Group root = new Group();
	@Override
	public void start(final Stage primaryStage) throws Exception {
		
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
		root.getChildren().add(player.getGroup());
		root.getChildren().add(enemy1.getGroup());
		root.getChildren().add(enemy2.getGroup());
		root.getChildren().add(enemy3.getGroup());
		root.getChildren().addAll(maze.getWalls());

		// moveEnemy = new Timeline();
		moveEnemy.setCycleCount(Timeline.INDEFINITE);
		moveEnemy.setAutoReverse(false);

		EventHandler<ActionEvent> actionPerFrame = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent t) {
				for (Enemy e : enemyList) {
					if (e.willCollideInFuture()) {

						int random = new Random().nextInt(4);

						Direction futureDirection = Direction.values()[random];

						e.setDirection(futureDirection);

					} else {
						e.move();
					}
				}
				
				if (Player.getBullet().willCollideInFuture()) {

					root.getChildren().remove(Player.getBullet().getRectangle());
					Bullet.setBulletExists(false);

				} else {
					Player.getBullet().move();
				}
				
			
					
					
				
			}

		};

		KeyFrame keyframe = new KeyFrame(Duration.millis(ONE_SECOND / FPS),
				actionPerFrame);
		moveEnemy.getKeyFrames().add(keyframe);
		moveEnemy.play();

		exit.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				GameOver gameOver = new GameOver();
				gameOver.start(primaryStage);
				moveEnemy.stop();
			}

		});

		Scene scene = new Scene(root, 1024, 740);
		scene.setOnKeyPressed(keyboard);

		scene.setFill(Color.BLACK);
		primaryStage.setScene(scene);
		primaryStage.show();

		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent w) {
				moveEnemy.stop();
				System.exit(0);
			}
		});

	}
}
