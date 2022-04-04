package com.example.lab6;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.io.IOException;

public class Game extends Application
{
	Stage stage;

	public Game(){}

	@Override
	public void start(Stage stage) throws IOException
	{
		this.stage = stage;

		FXMLLoader fxmlLoader = new FXMLLoader(Game.class.getResource("view.fxml"));
		Scene scene = new Scene(fxmlLoader.load());

		Image logo = new Image("logo.png");
		stage.getIcons().add(logo);

		stage.setTitle("Positional Game");
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args)
	{
		launch();
	}
}