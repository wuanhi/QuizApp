/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqh.utils;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

/**
 *
 * @author Wuan Hi Dep Trai
 */
public class MyAlert {
    private static MyAlert instance; 
    private final Alert alert; 
    private MyAlert(){
        this.alert = new Alert(Alert.AlertType.INFORMATION); 
        this.alert.setHeaderText("Quiz App");
    }
    public static MyAlert getInstance(){
        if(instance == null)
            instance = new MyAlert(); 
        return instance; 
    }
    public void showMessage(String text){
        this.alert.setContentText(text);
        this.alert.showAndWait();
    }
    
    public Optional<ButtonType> showMessage(String text, Alert.AlertType type){
        this.alert.setContentText(text);
        this.alert.setAlertType(type);
        return this.alert.showAndWait();
    }
}
