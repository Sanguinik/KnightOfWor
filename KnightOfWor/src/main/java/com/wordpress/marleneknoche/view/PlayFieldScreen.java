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
import com.wordpress.marleneknoche.model.ShootCallback;
import com.wordpress.marleneknoche.model.ShootingFigure;
import com.wordpress.marleneknoche.model.TypeOfFigure;
import com.wordpress.marleneknoche.util.Callback;

public class PlayFieldScreen extends Application {


	private class ShootCallbackImpl implements ShootCallback {
		@Override
		public void shootBullet(final Bullet bullet) {
			bulletList.add(bullet);
			root.getChildren().add(bullet.getRectangle());
		}
	}

	private final Timeline timeline = new Timeline();
	private final List<Enemy> enemyList = new ArrayList<Enemy>();
	private final List<Bullet> bulletList = new ArrayList<Bullet>();
	private static final int ONE_SECOND = 1000;
	private static final int FPS = 30;
	private final Group root = new Group();

	private Media music;
	private MediaPlayer mediaPlayer;

	/**
	 * Mit dieser Wahrschnlichkeit wird ein mal pro Sekunde geschossen.
	 */
	private static final double SHOOT_LIKELIHOOD = 0.7;
	private Maze maze;

	private Player player;

	private Stage primaryStage;

	private Enemy createEnemy(final TypeOfFigure enemyType, final int x,
			final int y) {
		Enemy enemy = new Enemy(maze, enemyType, x, y);
		enemy.addTargets(player);
		enemy.setShootCallback(new ShootCallbackImpl());

		enemyList.add(enemy);
		return enemy;
	}

	@Override
	public void start(final Stage primaryStage) {
		this.primaryStage = primaryStage;
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
		player = new Player(maze, 130, 510);
		player.setShootCallback(new ShootCallbackImpl());


		final Enemy enemy1 = createEnemy(TypeOfFigure.BURWOR, 130, 130);
		final Enemy enemy2 = createEnemy(TypeOfFigure.GARWOR, 855, 510);
		final Enemy enemy3 = createEnemy(TypeOfFigure.THORWOR, 855, 130);

		player.addTargets(enemy1, enemy2, enemy3);

		Keyboard keyboard = new Keyboard(player);

		root.getChildren().add(player.getGroup());
		root.getChildren().add(enemy1.getGroup());
		root.getChildren().add(enemy2.getGroup());
		root.getChildren().add(enemy3.getGroup());
		root.getChildren().addAll(maze.getWalls());

		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.setAutoReverse(false);

		EventHandler<ActionEvent> actionPerFrame = new EventHandler<ActionEvent>() {

			@Override
			public void handle(final ActionEvent t) {

				checkThatPlayerIsStillAlive();

				moveAllEnemies();

				moveAllBullets();

			}

		};

		KeyFrame keyframe = new KeyFrame(Duration.millis(ONE_SECOND / FPS),
				actionPerFrame);
		timeline.getKeyFrames().add(keyframe);
		timeline.play();


		Scene scene = new Scene(root, 1024, 740);
		scene.setOnKeyPressed(keyboard);

		scene.setFill(Color.BLACK);
		primaryStage.setScene(scene);
		primaryStage.show();

		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(final WindowEvent w) {
				timeline.stop();

				if (mediaPlayer != null) {
					mediaPlayer.stop();
				}
				System.exit(0);
			}
		});

	}

	private void checkThatPlayerIsStillAlive() {
		if (!player.isAlive()) {
			gameOver();
		}
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
		List<Enemy> enemiesToDelete = new ArrayList<Enemy>();

		if (enemyList.isEmpty()) {
			gameOver();
		}

		for (Enemy e : enemyList) {
			if (e.isAlive()) {
				e.move();
				int d = (int) (FPS * (1 / SHOOT_LIKELIHOOD));
				int random = new Random().nextInt(d);
				if (random == 0) {
					e.shoot();
				}
			} else {
				enemiesToDelete.add(e);
			}
		}

		for (Enemy e : enemiesToDelete) {
			enemyList.remove(e);
			root.getChildren().remove(e.getGroup());
			player.getTargets().remove(e);
		}
	}

	private void gameOver() {
		final GameOver gameOver = new GameOver();
		gameOver.start(primaryStage);
		timeline.stop();
	}
}
