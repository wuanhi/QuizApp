/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqh.utils;

import com.tqh.design_pattern.App;
import com.tqh.themes.ThemeManager;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author admin
 */
public class MyStage {
    private static MyStage instance;
    private static Scene scene; 
    private final Stage stage; 
    private MyStage (){
        stage = new Stage();
        stage.setTitle("QuizApp");
    }
    
    public static MyStage getInstance(){
        if (instance == null)
            instance = new MyStage(); 
        return instance; 
    } 
    
    public void showStage(String fxml) throws IOException{
        if(scene == null)
            scene = new Scene(new FXMLLoader(App.class.getResource(fxml)).load());
        else 
            scene.setRoot(new FXMLLoader(App.class.getResource(fxml)).load());
        ThemeManager.applyTheme(scene);
        this.stage.setScene(scene);
        this.stage.show();
    }
}
