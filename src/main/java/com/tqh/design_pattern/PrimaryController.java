package com.tqh.design_pattern;

import com.tqh.utils.MyAlert;
import com.tqh.utils.MyStage;
import com.tqh.utils.Themes.Themes;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class PrimaryController implements Initializable {
    @FXML ComboBox<Themes> cbThemes; 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.cbThemes.setItems(FXCollections.observableArrayList(Themes.values()));
    }
    
    public void handleChangeThemes(ActionEvent event){
        this.cbThemes.getSelectionModel().getSelectedItem().changeTheme(this.cbThemes.getScene());
    }
    
    public void handleQuestionManagement(ActionEvent event) throws IOException{
        MyStage.getInstance().showStage("questions.fxml");
    }
    
    public void handlePractice(ActionEvent event){
        MyAlert.getInstance().showMessage("Coming soon ... ");
    }
    
    public void handleTest(ActionEvent event){
        MyAlert.getInstance().showMessage("Coming soon ... "); 
    }
    
    public void hanldeRegister(ActionEvent event){
        MyAlert.getInstance().showMessage("Coming soon ... "); 
    }
    
    public void hanldeLogin(ActionEvent event){
        MyAlert.getInstance().showMessage("Coming soon ... "); 
    }

   
    
}
