package com.monopoly.scenes;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LandingSceneController {
	@FXML
	private ButtonBar btnBar;
	@FXML
	private Button createBtn;
	@FXML
	private Button playBtn;
	@FXML
	private Button exitBtn;

	// Event Listener on Button[#createBtn].onAction
	@FXML
	public void createPlayers(ActionEvent event) {
		System.out.println("created");
	}
	// Event Listener on Button[#playBtn].onAction
	@FXML
	public void startGame(ActionEvent event) throws IOException {
		System.out.println("started");

	}
	// Event Listener on Button[#exitBtn].onAction
	@FXML
	public void exitGame(ActionEvent event) {
		System.out.println("exited");
	}
}