
package com.monopoly.scenes;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class LandingSceneController implements Initializable {
    
    private SceneManager manager;
//    private PlayersManager playersManager;
//    private PlayersController playersController; 
    final private BooleanProperty createPlayerProperty = new SimpleBooleanProperty(this, "Create Player Property");
 

    @FXML
    private Button startGameButton;
    
    @FXML
    private Button createPlayerButton;
    
    @FXML
    private Button exitButton;
    
    private boolean isCreated = true;

    public boolean isIsCreated() {
        return isCreated;
    }

    public void setIsCreated(boolean isCreated) {
        this.isCreated = isCreated;
    }
    
    @FXML
    public void onCreatPlayerButton(ActionEvent event) throws IOException {
        
        createPlayerProperty.set(true);
        setIsCreated(false);
        updateStarGameButtonVisability(isIsCreated());
        
    }

    public BooleanProperty getCreatePlayerProperty() {
        return createPlayerProperty;
    }
    
    @FXML
    public void exitOnAction(){
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
    
    public void updateStarGameButtonVisability(boolean isCreatePlayer) {
        startGameButton.setDisable(isIsCreated());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        updateStarGameButtonVisability(isIsCreated());
    }  

    public void setManager(SceneManager manager) {
        this.manager = manager;
    }
    
    
    
}
