package com.tqh.design_pattern;

import com.tqh.utils.MyAlert;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class PrimaryController {
    public void handleQuestionManagement(ActionEvent event) throws IOException{
        Scene scene = new Scene(new FXMLLoader(App.class.getResource("questions.fxml")).load());
        Stage stage = new Stage(); 
        stage.setScene(scene);
        stage.show();
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
