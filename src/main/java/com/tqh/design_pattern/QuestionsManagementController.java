/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.tqh.design_pattern;

import com.tqh.pojo.Category;
import com.tqh.pojo.Levels;
import com.tqh.services.CategoryService;
import com.tqh.services.LevelsService;
import com.tqh.utils.JdbcConnector;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Wuan Hi Dep Trai
 */
public class QuestionsManagementController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML private ComboBox<Category> cbcates; 
    @FXML private ComboBox<Levels> cblevels; 
    @FXML private VBox vboxChoices; 
    private static final CategoryService categoryServices = new CategoryService(); 
    private static final LevelsService levelServices = new LevelsService(); 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.cbcates.setItems(FXCollections.observableArrayList(CategoryService.getCates()));
            this.cblevels.setItems(FXCollections.observableArrayList(LevelsService.getLevels()));
        }
        catch (SQLException ex) {
        }
        
    }    
    
    public void addChoice(ActionEvent event){
        HBox h = new HBox(); 
        h.getStyleClass().add("main"); 
        RadioButton rb = new RadioButton(); 
        TextField txt = new TextField(); 
        h.getChildren().addAll(rb, txt); 
        this.vboxChoices.getChildren().add(h); 
    }
    
}
