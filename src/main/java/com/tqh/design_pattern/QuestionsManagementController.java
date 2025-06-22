/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.tqh.design_pattern;

import com.tqh.pojo.Category;
import com.tqh.services.CategoryService;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.cbcates.setItems(FXCollections.observableArrayList(CategoryService.getCates()));
        }
        catch (SQLException ex) {
            Logger.getLogger(QuestionsManagementController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    
    
    
}
