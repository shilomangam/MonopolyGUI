package com.monopoly.scenes;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;

import com.monopoly.engine.PlayersManager;
import com.monopoly.exception.DuplicateNameException;
import com.monopoly.exception.EmptyNameException;
import com.monopoly.exception.NoHumanPlayerException;
import com.monopoly.exception.NullPictureException;
import com.monopoly.player.PlayerData;
import com.monopoly.player.PlayerView;

import javafx.animation.FadeTransition;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;

public class UserCreatingSceneController implements Initializable {
	ObservableList<String> iconList = FXCollections.observableArrayList("car", "dog", "iron", "shoe", "thimble",
			"wheelbarrow");
	ObservableList<String> MaleFemalePlayersList = FXCollections.observableArrayList("Male", "Female");
	SceneManager sceneManager;
	@FXML
	private TextField playerNameTextField;

	@FXML
	private CheckBox isHumanCheckBox;

	@FXML
	private Label errorMessageLabel;

	@FXML
	private Button addPlayerButton;

	@FXML
	private Pane playersPane;

	@FXML
	private ChoiceBox MaleFemaleBox;

	@FXML
	private ChoiceBox iconBox;

	@FXML
	private Button continueButton;

	@FXML
	private Button selectImage;

	@FXML
	private Button backBtn;

	private boolean isErrorMessageShown = false;
	private BooleanProperty finishedInit = new SimpleBooleanProperty(this, "Finish Init");
	private Image playerImage = null;
	private PlayersManager playersManager;
	private Image playerIcon = null;

	public void setPlayersManager(PlayersManager playersManager) {
		this.playersManager = playersManager;
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		MaleFemaleBox.setValue("Male");
		MaleFemaleBox.setItems(MaleFemalePlayersList);
		playerNameTextField.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> ov, String t, String t1) {
				onPlayerNameChange();
			}
		});
		iconBox.setValue("car");
		iconBox.setItems(iconList);

		this.finishedInit.addListener((source, oldValue, newValue) -> {
			if (newValue) {
				final PlayersPane gameScene = new PlayersPane(playersManager);
				sceneManager.getPrimaryStage().setScene(gameScene);
			}
		});
		this.playersManager = new PlayersManager();
		selectImage.setDisable(true);
		continueButton.setDisable(true);
		playerImage = new Image("com/monopoly/assets/players-avatar/robot.png");
		finishedInit = new SimpleBooleanProperty(false);
	}

	@FXML
	private void isHumanChange() {
		if (this.isHumanCheckBox.isSelected()) {
			this.selectImage.setDisable(false);
			playerImage = null;
		} else
			this.selectImage.setDisable(true);
	}

	@FXML
	protected void addPlayer(ActionEvent event) {
		String name = getPlayerName();
		boolean isHuman = isPlayerHuman();
		String gender = getGender();
		Image image = getPlayerImage();
		this.enterIcons();
		Image icon = this.getPlayerIcon();
		try {
			PlayerData player = playersManager.addPlayer(name, isHuman, gender, image, icon);
			addPlayerToList(player);
			clearPlayerDetailsFields();
			hideError();
			if (playersManager.isPlayersFullyLoaded()) {
				if (playersManager.isThereHumanPlayer()) {
					continueButton.setDisable(false);
					addPlayerButton.setDisable(true);
					showError("Players are set, Press Continue!");
				}
			}
		} catch (DuplicateNameException | EmptyNameException | NoHumanPlayerException
				| NullPictureException playersManagerException) {
			showError(playersManagerException.getMessage());
		}
	}

	private void deleteIconFromList(String icon) {
		Iterator<String> iterator = iconList.iterator();
		while (iterator.hasNext()) {
			String newIcon = iterator.next();
			if (newIcon.equals(icon))
				iterator.remove();
		}
	}

	private Image getPlayerIcon(){
		return this.playerIcon;
	}
	public void enterIcons() {
		String icon = (String) iconBox.getValue();
		String iconPath = "file:src/com/monopoly/assets/icons/" + icon + ".png";
		Image newIcon = new Image(iconPath);
		playerIcon = newIcon;
	}

	private Image getPlayerImage() {
		return this.playerImage;
	}

	@FXML
	protected void onPlayerNameChange() {
		updateAddPlayerButtonState();
		hideError();
	}

	@FXML
	protected void onContinue(ActionEvent event) {
		finishedInit = new SimpleBooleanProperty(true);
		sceneManager.getPrimaryStage().setScene(sceneManager.getStartScene());

	}

	private void updateAddPlayerButtonState() {
		boolean isEmptyName = getPlayerName().trim().isEmpty();
		boolean disable = (isEmptyName || isErrorMessageShown) && (this.playerImage == null);
		addPlayerButton.setDisable(disable);
	}

	private String getPlayerName() {
		return playerNameTextField.getText();
	}

	private boolean isPlayerHuman() {
		return isHumanCheckBox.isSelected();
	}

	private String getGender() {
		return MaleFemaleBox.getValue().toString();
	}

	private void addPlayerToList(PlayerData player) {
		PlayerView playerView = new PlayerView(player.getName(), player.getGender(), player.isHuman(),
				player.getImage(), player.getIcon());
		HBox thebox = (HBox) playersPane.getChildren().get(0);
		thebox.setPadding(new Insets(20, 20, 20, 20));
		thebox.setSpacing(20);
		thebox.getChildren().add(playerView);
	}

	private void clearPlayerDetailsFields() {
		playerNameTextField.clear();
		isHumanCheckBox.setSelected(false);
		playerNameTextField.requestFocus();
		MaleFemaleBox.setValue("Male");
		selectImage.setDisable(true);
		playerImage = new Image("com/monopoly/assets/players-avatar/robot.png");
		this.deleteIconFromList((String)iconBox.getValue());
		if (!iconList.isEmpty()){
			iconBox.setValue(iconList.get(0));
			iconBox.setItems(iconList);
		}
	}

	private void showError(String message) {
		if (!isErrorMessageShown) {
			isErrorMessageShown = true;
			errorMessageLabel.setText(message);
			FadeTransition animation = new FadeTransition();
			animation.setNode(errorMessageLabel);
			animation.setDuration(Duration.seconds(0.3));
			animation.setFromValue(0.0);
			animation.setToValue(1.0);
			animation.play();
		}
		updateAddPlayerButtonState();
	}

	private void hideError() {
		if (isErrorMessageShown) {
			FadeTransition animation = new FadeTransition();
			animation.setNode(errorMessageLabel);
			animation.setDuration(Duration.seconds(0.3));
			animation.setFromValue(1.0);
			animation.setToValue(0.0);
			animation.play();

			isErrorMessageShown = false;
			errorMessageLabel.textProperty().setValue("");
			updateAddPlayerButtonState();
		}
	}

	@FXML
	private void openPopUpSelection(ActionEvent event) throws IOException {
		final Popup selectImagePopUp = new Popup();
		selectImagePopUp.setX(800);
		selectImagePopUp.setY(800);

		HBox imagesBox = new HBox();
		imagesBox.setPrefSize(800, 800);

		this.createImagesBox(imagesBox, selectImagePopUp);

		selectImagePopUp.getContent().add(imagesBox);
		Button pressedButton = (Button) event.getSource();
		Stage theStage = (Stage) pressedButton.getScene().getWindow();
		selectImagePopUp.show(theStage);
	}

	private void createImagesBox(HBox imagesBox, Popup selectImagePopUp) throws IOException {
		String fullPath = new File("src/com/monopoly/assets/players-avatar").getCanonicalPath().toString();
		File repo = new File(fullPath + "//" + MaleFemaleBox.getValue().toString().toLowerCase());
		File[] fileList = repo.listFiles();
		ArrayList<String> photoStrings = new ArrayList<>();
		for (File f : fileList) {
			String exten = getFileExtension(f);
			if (exten.toLowerCase().equals("png")) {
				photoStrings.add(f.getAbsolutePath());

			}

		}
		for (String str : photoStrings) {
			ImageView photo = new ImageView("file:" + str);
			photo.setOnMouseClicked(new EventHandler<MouseEvent>() {
				public void handle(MouseEvent me) {
					playerImage = ((ImageView) me.getSource()).getImage();
					updateAddPlayerButtonState();
					hideError();
					selectImagePopUp.hide();

				}
			});
			imagesBox.getChildren().add(photo);
		}
	}

	private static String getFileExtension(File file) {
		String fileName = file.getName();
		if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
			return fileName.substring(fileName.lastIndexOf(".") + 1);
		else
			return "";
	}

	public ObservableValue<Boolean> getFinishedInit() {
		return this.finishedInit;
	}

	public void setManager(SceneManager sceneManager) {
		this.sceneManager = sceneManager;

	}

	@FXML
	private void returnToLandingScene(ActionEvent event) {
		this.sceneManager.getPrimaryStage().setScene(this.sceneManager.getStartScene());
	}

	public PlayersManager getPlayersManager() {
		return this.playersManager;
	}
}
