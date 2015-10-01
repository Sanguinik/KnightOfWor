package de.sanguinik.view;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import de.sanguinik.model.Bullet;
import de.sanguinik.model.Enemy;
import de.sanguinik.model.HighscoreEntry;
import de.sanguinik.model.Keyboard;
import de.sanguinik.model.Maze;
import de.sanguinik.model.Player;
import de.sanguinik.model.ShootCallback;
import de.sanguinik.model.TypeOfFigure;

public class PlayFieldScreen extends Application {

	private class ShootCallbackImpl implements ShootCallback {
		@Override
		public void shootBullet(final Bullet bullet) {
			bulletList.add(bullet);
			root.getChildren().add(bullet.getGroup());
		}
	}

	private final static int START_X_PLAYER_1 = 130;
	private final static int START_Y_PLAYER_1 = 510;
	private final Timeline timeline = new Timeline();
	private final List<Enemy> enemyList = new ArrayList<Enemy>();
	private final List<Bullet> bulletList = new ArrayList<Bullet>();
	private static final int ONE_SECOND = 1000;
	private static final int FPS = 30;
	private final Group root = new Group();
	private boolean gameWasPaused = true;
	private final Label pause = new Label("PAUSE");
	
	private HighscoreEntry entry;

	private Media music;
	private MediaPlayer mediaPlayer;

	/**
	 * Mit dieser Wahrscheinlichkeit wird ein mal pro Sekunde geschossen.
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

		URL pathToLevelMusic = getClass().getResource("KoWLong.mp3");
		if (pathToLevelMusic != null) {
			music = new Media(pathToLevelMusic.toString());
			mediaPlayer = new MediaPlayer(music);

			mediaPlayer.setVolume(0.5);
			mediaPlayer.play();
		} else {
			System.err.println("Musikdatei 'KoWLong.pm3' nicht gefunden!");
		}
		maze = new Maze("level1");
		player = new Player(maze, START_X_PLAYER_1, START_Y_PLAYER_1);
		player.setShootCallback(new ShootCallbackImpl());

		final Enemy enemy1 = createEnemy(TypeOfFigure.BURWOR, 130, 130);
		final Enemy enemy2 = createEnemy(TypeOfFigure.GARWOR, 855, 510);
		final Enemy enemy3 = createEnemy(TypeOfFigure.THORWOR, 855, 130);

		player.addTargets(enemy1, enemy2, enemy3);

		Label score = new Label("Score: " + player.getScore());
		
		Label lives = new Label("Leben: " + player.getLives());
		lives.setLayoutY(40);
		
		Keyboard keyboard = new Keyboard(player, this);

		root.getChildren().add(player.getGroup());
		root.getChildren().add(enemy1.getGroup());
		root.getChildren().add(enemy2.getGroup());
		root.getChildren().add(enemy3.getGroup());
		root.getChildren().addAll(maze.getWalls());
		root.getChildren().add(score);
		root.getChildren().add(lives);

		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.setAutoReverse(false);

		
		
		EventHandler<ActionEvent> actionPerFrame = new EventHandler<ActionEvent>() {

			@Override
			public void handle(final ActionEvent t) {
				
				introSequence();
				
				if(checkThatPlayerIsStillAlive()){

					moveAllEnemies();

					moveAllBullets();
					
					score.setText("Score: " + player.getScore());
					
				}else{
					
					
					if(player.getLives() == 0){

						enterHighscore();
						
					}else{
						timeline.pause();
						player.getRectangle().setX(START_X_PLAYER_1);
						player.getRectangle().setY(START_Y_PLAYER_1);
						player.getImageView().setX(START_X_PLAYER_1);
						player.getImageView().setY(START_Y_PLAYER_1);
						player.setLives(player.getLives() - 1);
						lives.setText("Leben: " + player.getLives());
						player.setInvincible(true);
						player.setAlive(true);
						timeline.play();
						Timer timer = new Timer();
						
						timer.schedule(new TimerTask(){

							@Override
							public void run() {
								Platform.runLater(() -> {
									player.setInvincible(false);
								});
								
							}
						}, 3000);
						
					}
					
				}

			}

		};

		KeyFrame keyframe = new KeyFrame(Duration.millis(ONE_SECOND / FPS),
				actionPerFrame);
		timeline.getKeyFrames().add(keyframe);
		timeline.play();

		Scene scene = new Scene(root, 1024, 740);
		scene.getStylesheets().add(TitleScreen.class.getResource("controls.css").toExternalForm());
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

	private boolean checkThatPlayerIsStillAlive() {
		if (!player.isAlive()) {
			gameWasPaused = true;
			player.toggleMoveable();
			return false;
		}
		return true;
	}
	
	private void enterHighscore(){
		if (mediaPlayer != null) {
			mediaPlayer.stop();
		}
		timeline.stop();
		int finalScore = player.getScore();
		String playerName = "Spieler 1";
		Label playersPoints = new Label("Du hast "+finalScore+ " Punkte!");
		playersPoints.setTextFill(Color.WHITESMOKE);
		Label enterHighscore = new Label("Trage deinen Namen ein! ");
		enterHighscore.setTextFill(Color.WHITESMOKE);
		TextField name = new TextField(playerName);
		Button ok = new Button("Ok");
		VBox highscorePopup = new VBox();
		highscorePopup.setAlignment(Pos.CENTER);
		highscorePopup.getChildren().add(playersPoints);
		highscorePopup.getChildren().add(enterHighscore);
		HBox highscoreBox = new HBox();
		highscoreBox.getChildren().add(name);
		highscoreBox.getChildren().add(ok);
		highscorePopup.getChildren().add(highscoreBox);
		highscorePopup.setLayoutX(root.getScene().getWidth()/2 - 120);
		highscorePopup.setLayoutY(root.getScene().getHeight() - 100);
		root.getChildren().add(highscorePopup);
		
		ok.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				//highscore eintragen
				entry = new HighscoreEntry(name.getText(), finalScore);
				
				//gameover
				gameOver();
			}
		});
		
		
	}
	
	private void introSequence() {
		Label ready = new Label("READY?");
		
		ready.setLayoutX(root.getScene().getWidth()/2);
		ready.setLayoutY(root.getScene().getHeight()/2);
		
		if(gameWasPaused){
			
			timeline.pause();
			root.getChildren().add(ready);
			Timer timer = new Timer();
			
			timer.schedule(new TimerTask(){

				@Override
				public void run() {
					Platform.runLater(() -> {
						ready.setText("START!");
					});
					
				}
			}, 1000);
			
			timer.schedule(new TimerTask(){

				@Override
				public void run() {
					Platform.runLater(() -> {
						
						timeline.play();
						player.toggleMoveable();
						root.getChildren().remove(ready);
					});
				}
				
			}, 2000);
				
			gameWasPaused = false;
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
			root.getChildren().remove(b.getGroup());
		}
	}

	private void moveAllEnemies() {
		List<Enemy> enemiesToDelete = new ArrayList<Enemy>();

		if (enemyList.isEmpty()) {
			enterHighscore();
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
		if (mediaPlayer != null) {
			mediaPlayer.stop();
		}
	}
	
	public void pauseGame(){
		
		if(gameWasPaused){
			root.getChildren().remove(pause);
			timeline.play();
			gameWasPaused = false;
		}else{
			pause.setLayoutX(root.getScene().getWidth()/2);
			pause.setLayoutY(root.getScene().getHeight()/2);
			root.getChildren().add(pause);
			timeline.pause();
			gameWasPaused = true;
		}
	}

	public void muteMusic() {
		if(mediaPlayer.isMute()){
			mediaPlayer.setMute(false);
		}else{
			mediaPlayer.setMute(true);
		}
	}
}
