/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.tqh.design_pattern;

import com.tqh.pojo.Category;
import com.tqh.pojo.Choice;
import com.tqh.pojo.Level;
import com.tqh.pojo.Question;
import com.tqh.services.CategoryService;
import com.tqh.services.LevelService;
import com.tqh.services.question.BaseQuestionService;
import com.tqh.services.question.Decorator.CategoryQuestionServiceDecorator;
import com.tqh.services.question.Decorator.KeywordQuestionServiceDecorator;
import com.tqh.services.question.Decorator.LevelQuestionServiceDecorator;
import com.tqh.services.question.QuestionService;
import com.tqh.utils.Configs;
import com.tqh.utils.FlyweightFactory;
import com.tqh.utils.JdbcConnector;
import com.tqh.utils.MyAlert;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
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
    @FXML
    private ComboBox<Category> cbcates;

    @FXML
    private ComboBox<Category> cbCatesSearch;
    
    @FXML
    private ComboBox<Level> cblevels;

    @FXML
    private ComboBox<Level> cblevelsSearch;
    
    @FXML
    private VBox vboxChoice;

    @FXML
    private TextArea txtContent;

    @FXML 
    private ToggleGroup toggleChoice; 
    
    @FXML 
    private TableView<Question> tbQuestions; 
    
    @FXML 
    private TextField txtSearch; 
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.cbcates.setItems(FXCollections.observableList(FlyweightFactory.getCache(Configs.cateService, "categories")));
            this.cbCatesSearch.setItems(FXCollections.observableList(FlyweightFactory.getCache(Configs.cateService, "categories")));
            this.cblevels.setItems(FXCollections.observableList(FlyweightFactory.getCache(Configs.levelService, "levels")));
            this.cblevelsSearch.setItems(FXCollections.observableList(FlyweightFactory.getCache(Configs.levelService, "levels")));
            this.loadColumn();
            this.tbQuestions.setItems(FXCollections.observableList(Configs.questionService.getList()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        this.txtSearch.textProperty().addListener(e -> {
            try {
                BaseQuestionService bs = new KeywordQuestionServiceDecorator(this.txtSearch.getText(), Configs.questionService);
                this.tbQuestions.setItems(FXCollections.observableList(bs.getList()));
            } catch (SQLException ex) {
                Logger.getLogger(QuestionsManagementController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        });
        
        this.cbCatesSearch.getSelectionModel().selectedItemProperty().addListener(e -> {
            try {
                BaseQuestionService bs = new CategoryQuestionServiceDecorator(this.cbCatesSearch.getSelectionModel().getSelectedItem().getId(), Configs.questionService);
                this.tbQuestions.setItems(FXCollections.observableList(bs.getList()));
            } catch (SQLException ex) {
                Logger.getLogger(QuestionsManagementController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        });
        
        this.cblevelsSearch.getSelectionModel().selectedItemProperty().addListener(e -> {
            try {
                BaseQuestionService bs = new LevelQuestionServiceDecorator(this.cblevelsSearch.getSelectionModel().getSelectedItem().getId(), Configs.questionService);
                this.tbQuestions.setItems(FXCollections.observableList(bs.getList()));
            } catch (SQLException ex) {
                Logger.getLogger(QuestionsManagementController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        });
    }

    public void addChoice(ActionEvent event) {
        HBox hbox = new HBox();
        hbox.getStyleClass().add("main");

        RadioButton rdb = new RadioButton();
        rdb.setToggleGroup(toggleChoice);
        TextField txd = new TextField();
        
        hbox.getChildren().addAll(rdb, txd);
        this.vboxChoice.getChildren().add(hbox);
    }

    public void addQuestion(ActionEvent event) {
        try {
            Question.Builder b = new Question.Builder(this.txtContent.getText()
                    , this.cbcates.getSelectionModel().getSelectedItem()
                    ,this.cblevels.getSelectionModel().getSelectedItem());
            for (var i : this.vboxChoice.getChildren()){
                HBox h = (HBox) i; 
                Choice choice = new Choice(((TextField)h.getChildren().get(1)).getText()
                        , ((RadioButton)h.getChildren().get(0)).isSelected());
                b.addChoice(choice); 
            }   
            Configs.updateQuestionService.addQuestion(b.build());
        } catch (SQLException ex) {
            MyAlert.getInstance().showMessage("Thêm không thành công, lý do: " + ex.getMessage());
        }catch (Exception ex) {
            MyAlert.getInstance().showMessage("Dữ liệu không hợp lệ!");
        }
    }
    
    private void loadColumn(){
        TableColumn colID = new TableColumn("Id");
        colID.setCellValueFactory(new PropertyValueFactory("id"));
        colID.setPrefWidth(250);
        
        TableColumn colContent = new TableColumn("Nội dung câu hỏi");
        colContent.setCellValueFactory(new PropertyValueFactory("content"));
        colContent.setPrefWidth(250);
        
        TableColumn colDelete = new TableColumn(); 
        colDelete.setCellFactory(e -> {
            TableCell cell = new TableCell();
            Button btn = new Button("Xóa");
            btn.setStyle("-fx-pref-width: 70; -fx-font-size: 12;");
            btn.setOnAction(eh -> {
                Optional<ButtonType> t = MyAlert.getInstance().showMessage("Nếu xóa câu hỏi sẽ xóa toàn bộ đáp án của câu hỏi đó, Bạn chắc chứ?"
                    ,Alert.AlertType.CONFIRMATION); 
                if (t.isPresent() && t.get().equals(ButtonType.OK)){
                    Question q = (Question) cell.getTableRow().getItem();
                    try {
                        if (true == Configs.updateQuestionService.deleteQuestion(q.getId()) ){
                            this.tbQuestions.getItems().remove(q); 
                            MyAlert.getInstance().showMessage("Xóa câu hỏi thành công !"
                                    , Alert.AlertType.INFORMATION);
                        }
                        else {
                            MyAlert.getInstance().showMessage("Xóa câu hỏi thất bại !"
                                    , Alert.AlertType.INFORMATION);
                            
                        }
                    } catch (SQLException ex) {
                        MyAlert.getInstance().showMessage("Hệ thống bị lỗi, lý do: " + ex.getMessage(), Alert.AlertType.ERROR);
                        System.out.println(ex.getMessage());
                    }
                }
            
            });
            cell.setGraphic(btn);
            return cell; 
        });
        
        
        this.tbQuestions.getColumns().addAll(colID, colContent, colDelete); 
    }
}
