/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqh.utils;

import javafx.scene.control.Alert;

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
}
