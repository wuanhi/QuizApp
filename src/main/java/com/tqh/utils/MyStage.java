/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqh.utils;

import com.tqh.design_pattern.App;
import com.tqh.utils.Themes.ThemesManager;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Wuan Hi Dep Trai
 */
public class MyStage {

    private static MyStage instance;
    private final Stage stage;
    private static Scene scene;

    private MyStage() {
        stage = new Stage();
        stage.setTitle("Quiz App");
    }

    public static MyStage getInstance() {
        if (instance == null) {
            return instance = new MyStage();
        }
        return instance;
    }

    public void showStage(String fxml) throws IOException {
        if (scene == null) {
            scene = new Scene(new FXMLLoader(App.class.getResource(fxml)).load());
        } else {
            scene.setRoot(new FXMLLoader(App.class.getResource(fxml)).load());
        }
        ThemesManager.applyTheme(scene);
        this.stage.setScene(scene);
        this.stage.show();
    }
}
