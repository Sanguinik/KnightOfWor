package de.sanguinik.view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Credits extends Application {

	@Override
	public void start(final Stage primaryStage) {
		primaryStage.setTitle("Knight of Wor - Credits");
		primaryStage.setResizable(false);

		GridPane grid = new GridPane();
		grid.setId("creditsGrid");
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(30);
		grid.setVgap(30);

		Label creditsTitle = new Label("CREDITS (2012)");
		grid.add(creditsTitle, 0, 0);

		Label developers = new Label("Entwickelt von:");
		grid.add(developers, 0, 1);

		Label lulu = new Label("Lulu Herrmann");
		grid.add(lulu, 0, 2);

		Label manuel = new Label("Manuel Mauky");
		grid.add(manuel, 0, 3);

		Label marlene = new Label("Marlene Knoche");
		grid.add(marlene, 0, 4);

		Label reason = new Label(
				" Dieses Spiel wurde für Johannes \n zum Geburtstag programmiert! \n In diesem Sinne:\n Alles Gute! :-)");
		grid.add(reason, 0, 5);

		Button okBtn = new Button();
		okBtn.setText("Zurück");
		okBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				TitleScreen title = new TitleScreen();
				title.start(primaryStage);
			}

		});

		grid.add(okBtn, 0, 6);

		Scene scene = new Scene(grid, 1024, 740);
		scene.getStylesheets().add(TitleScreen.class.getResource("controls.css").toExternalForm());
		scene.getStylesheets().add(
				Credits.class.getResource("Credits.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();

	}

}
