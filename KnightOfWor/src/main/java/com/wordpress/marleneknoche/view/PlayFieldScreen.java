package com.wordpress.marleneknoche.view;

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
import com.wordpress.marleneknoche.model.Keyboard;
import com.wordpress.marleneknoche.model.Maze;
import com.wordpress.marleneknoche.model.Player;
import com.wordpress.marleneknoche.model.Enemy;
import com.wordpress.marleneknoche.model.TypeOfEnemy;

public class PlayFieldScreen extends Application {
	
	private Timeline moveLeft;
	private Timeline moveUp;
	private Timeline moveDown;
	private Timeline moveRight;
	

	@Override
	public void start(final Stage primaryStage) throws Exception {
		Group root = new Group();
		primaryStage.setTitle("Knight of Wor");
		primaryStage.setResizable(false);

		final Maze maze = new Maze();
		Player player = new Player(maze);
		final Enemy enemy = new Enemy(TypeOfEnemy.BURWOR, maze);
		final Rectangle rect = enemy.getEnemyRectangle();
		Keyboard keyboard = new Keyboard(player);
		Rectangle exit = new Rectangle(400, 300, 100, 100);
		exit.setFill(Color.ROSYBROWN);

		root.getChildren().add(exit);
		root.getChildren().add(player.getPlayerRectangle());
		root.getChildren().add(enemy.getEnemyRectangle());
		root.getChildren().addAll(maze.getWalls());
		
		/*CollisionDetector cd = new CollisionDetector();
		for (Rectangle r : maze.getWalls()) {
			while (!(cd.isCollide(enemy.getEnemyRectangle(), r))){
				final Timeline timeline = new Timeline();
		        timeline.setCycleCount(Timeline.INDEFINITE);
		        timeline.setAutoReverse(false);
				final KeyValue kv = new KeyValue(enemy.getEnemyRectangle().xProperty(), 3);
		        final KeyFrame kf = new KeyFrame(Duration.millis(2000), kv);
		        timeline.getKeyFrames().add(kf);
		        timeline.play();
			}
		}*/
        
		moveLeft = new Timeline();
        moveLeft.setCycleCount(Timeline.INDEFINITE);
        moveLeft.setAutoReverse(false);
        
        EventHandler<ActionEvent> onFinishedL = new EventHandler<ActionEvent>() {
        	int x = 0; //TODO Verschiebung KeyValue-Start (Anpassen, Abhängigkeiten vom Standort)
        	Rectangle r1 = new Rectangle(enemy.getEnemyRectangle().getX(),510, 40, 40);
        	
            public void handle(ActionEvent t) {

                 enemy.getEnemyRectangle().setTranslateX(x);
                
               CollisionDetector cd = new CollisionDetector();
         		for (Rectangle r : maze.getWalls()) {
         			if ((cd.isCollide(r1, r))){
                 
                 x=x+10;//Zurückprallen
                 r1.setX(r1.getX()+10);
                 enemy.getEnemyRectangle().setX(enemy.getEnemyRectangle().getX()+10);
                 moveLeft.stop();
                 moveUp.play();
                 }}
         			x=x-1;
         			r1.setX(r1.getX()-1);
         			enemy.getEnemyRectangle().setX(enemy.getEnemyRectangle().getX()-1);
         			System.out.println("enemy: "+enemy.getEnemyRectangle().getX()+" r1: "+r1.getX());
         			
         		


            }

        };
        
        KeyValue lv = new KeyValue(enemy.getEnemyRectangle().xProperty(), 844);
        KeyFrame lf = new KeyFrame(Duration.millis(30), onFinishedL, lv);
        moveLeft.getKeyFrames().add(lf);
        moveLeft.play();
        
        
        
        moveUp = new Timeline();
        moveUp.setCycleCount(Timeline.INDEFINITE);
        moveUp.setAutoReverse(false);
        
        EventHandler<ActionEvent> onFinishedU = new EventHandler<ActionEvent>() {
        	int y = 0; //TODO Verschiebung KeyValue-Start
        	Rectangle r1 = new Rectangle(enemy.getEnemyRectangle().getX(),enemy.getEnemyRectangle().getY(), 40, 40);
        	
            public void handle(ActionEvent t) {

                 enemy.getEnemyRectangle().setTranslateY(y);
                
               CollisionDetector cd = new CollisionDetector();
         		for (Rectangle r : maze.getWalls()) {
         			if ((cd.isCollide(r1, r))){
                 
                 y=y+10;//Zurückprallen
                 r1.setY(r1.getY()+10);
                 enemy.getEnemyRectangle().setY(enemy.getEnemyRectangle().getY()+10);
                 //moveUp.stop();
                 }}
         			y=y-1;
         			r1.setY(r1.getY()-1);
         			enemy.getEnemyRectangle().setY(enemy.getEnemyRectangle().getY()-1);
         			
         		


            }

        };
        
        KeyValue uv = new KeyValue(enemy.getEnemyRectangle().yProperty(), 509);
        KeyFrame uf = new KeyFrame(Duration.millis(30), onFinishedU, uv);
        moveUp.getKeyFrames().add(uf);
        
        
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
