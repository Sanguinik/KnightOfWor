package de.sanguinik.view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Options extends Application {

	@Override
	public void start(final Stage primaryStage) {
		primaryStage.setTitle("Knight of Wor - Optionen");
		primaryStage.setResizable(false);

		GridPane root = new GridPane();
		root.setId("optionsGrid");
		root.setAlignment(Pos.CENTER);
		root.setHgap(30);
		root.setVgap(30);
		
		Label optionHeader = new Label("OPTIONEN");
		root.add(optionHeader,0,0);
		
		Label controls = new Label("Steuerung");
		root.add(controls,0,1);
		
		Label configuration = new Label("Hoch \t W \n"
				+ "Runter \t S \n"
				+ "Links \t A \n"
				+ "Rechts \t D \n"
				+ "Schießen  Leertaste \n"
				+ "Musik an/aus M \n"
				+ "Pause \t P");
		root.add(configuration,0,3);
		
		Button okBtn = new Button();
		okBtn.setText("Zurück");
		okBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				TitleScreen title = new TitleScreen();
				title.start(primaryStage);
			}

		});

		root.add(okBtn,0,4);
		Scene scene = new Scene(root, 1024, 740);
		scene.getStylesheets().add(TitleScreen.class.getResource("controls.css").toExternalForm());
		scene.getStylesheets().add(
				Credits.class.getResource("Options.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
