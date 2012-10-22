package com.wordpress.marleneknoche.view;

import java.net.URL;
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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
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
import com.wordpress.marleneknoche.model.ShootingFigure;
import com.wordpress.marleneknoche.model.TypeOfFigure;
import com.wordpress.marleneknoche.util.Callback;

public class PlayFieldScreen extends Application {

	private final class CallbackImplementation implements Callback {
		private final ShootingFigure shootingFigure;

		private CallbackImplementation(ShootingFigure shootingFigure) {
			this.shootingFigure = shootingFigure;
		}

		@Override
		public void call() {
			double x = shootingFigure.getRectangle().getX();
			double y = shootingFigure.getRectangle().getY();
			Direction direction = shootingFigure.getDirection();
			final Bullet bullet = new Bullet(maze, Color.ANTIQUEWHITE,
					direction, x, y);
			bullet.addCollisionCallback(new Callback() {

				@Override
				public void call() {
					shootingFigure.bulletHasArrived();
				}
			});
			bulletList.add(bullet);
			root.getChildren().add(bullet.getRectangle());
		}
	}

	public Timeline moveEnemy = new Timeline();
	private final List<Enemy> enemyList = new ArrayList<Enemy>();
	private final List<Bullet> bulletList = new ArrayList<Bullet>();
	private static final int ONE_SECOND = 1000;
	private static final int FPS = 30;
	private final Group root = new Group();

	private Media music;
	private MediaPlayer mediaPlayer;

	/**
	 * Mit einer Wahrschnlichkeit von 0.5 wird ein mal pro Sekunde geschossen.
	 */
	private static final double SHOOT_LIKELIHOOD = 0.5;
	private Maze maze;

	@Override
	public void start(final Stage primaryStage) throws Exception {

		primaryStage.setTitle("Knight of Wor");
		primaryStage.setResizable(false);

		URL pathToLevelMusic = getClass().getResource("KoWL.mp3");
		if (pathToLevelMusic != null) {
			music = new Media(pathToLevelMusic.toString());
			mediaPlayer = new MediaPlayer(music);

			mediaPlayer.setVolume(0.5);
			mediaPlayer.play();
		} else {
			System.err.println("Musikdatei 'KoWL.pm3' nicht gefunden!");
		}
		maze = new Maze();
		final Player player = new Player(maze, TypeOfFigure.PLAYER, 130, 510);
		player.setOnShootCallback(new CallbackImplementation(player));

		Enemy enemy1 = new Enemy(maze, TypeOfFigure.BURWOR, 130, 130);
		enemy1.setOnShootCallback(new CallbackImplementation(enemy1));
		Enemy enemy2 = new Enemy(maze, TypeOfFigure.GARWOR, 855, 510);
		enemy2.setOnShootCallback(new CallbackImplementation(enemy2));
		Enemy enemy3 = new Enemy(maze, TypeOfFigure.THORWOR, 855, 130);
		enemy3.setOnShootCallback(new CallbackImplementation(enemy3));
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
				moveAllEnemies();

				moveAllBullets();

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
				if (mediaPlayer != null) {
					mediaPlayer.stop();
				}
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
				if (mediaPlayer != null) {
					mediaPlayer.stop();
				}
				System.exit(0);
			}
		});

	}

	private void moveAllBullets() {
		List<Bullet> bulletsToDelete = new ArrayList<Bullet>();
		for (Bullet b : bulletList) {
			b.move();
			if (!b.isActive()) {
				bulletsToDelete.add(b);
			}
		}
		for (Bullet b : bulletsToDelete) {
			bulletList.remove(b);
			root.getChildren().remove(b.getRectangle());
		}
	}

	private void moveAllEnemies() {
		for (Enemy e : enemyList) {
			e.move();
			int d = (int) (FPS * (1 / SHOOT_LIKELIHOOD));
			int random = new Random().nextInt(d);
			if (random == 0) {
				e.shoot();
			}
		}
	}
}
