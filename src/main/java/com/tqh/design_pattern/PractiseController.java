/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.tqh.design_pattern;

import com.tqh.pojo.Category;
import com.tqh.pojo.Level;
import com.tqh.pojo.Question;
import com.tqh.services.question.BaseQuestionService;
import com.tqh.services.question.Decorator.CategoryQuestionServiceDecorator;
import com.tqh.services.question.Decorator.LevelQuestionServiceDecorator;
import com.tqh.services.question.Decorator.LimitedQuestionServiceDecorator;
import com.tqh.utils.Configs;
import com.tqh.utils.FlyweightFactory;
import com.tqh.utils.MyAlert;
import java.io.ObjectInputFilter;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Wuan Hi Dep Trai
 */
public class PractiseController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField txtNum;

    @FXML
    private Text txtContent;

    @FXML
    private Text txtResult;

    @FXML
    private VBox vboxChoices;

    private List<Question> questions;
    private int currentQuestion;

    @FXML
    private ComboBox<Category> cbCatesSearch;

    @FXML
    private ComboBox<Level> cblevelsSearch;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.cbCatesSearch.setItems(FXCollections.observableList(FlyweightFactory.getCache(Configs.cateService, "categories")));
            this.cblevelsSearch.setItems(FXCollections.observableList(FlyweightFactory.getCache(Configs.levelService, "levels")));
        } catch (SQLException e) {e.printStackTrace();}
    }

    public void handleStart(ActionEvent event) throws SQLException {
        try {
            int num = Integer.parseInt(this.txtNum.getText());
            BaseQuestionService b = Configs.questionService; 
            Category c = this.cbCatesSearch.getSelectionModel().getSelectedItem(); 
            if (c != null)
                b = new CategoryQuestionServiceDecorator(c.getId(), b);
            Level lv = this.cblevelsSearch.getSelectionModel().getSelectedItem(); 
            if (lv != null)
                b = new LevelQuestionServiceDecorator(lv.getId(), b); 
            b = new LimitedQuestionServiceDecorator(num, b);
            questions = b.getList();
            this.currentQuestion = 0; 
            loadQuestion();
        } catch (NumberFormatException ex) {
            MyAlert.getInstance().showMessage("Vui lòng nhập số nguyên!");
        }
    }

    private void loadQuestion() {
        Question q = this.questions.get(this.currentQuestion);
        this.txtContent.setText(q.getContent());
        ToggleGroup tg = new ToggleGroup();
        vboxChoices.getChildren().clear();
        for (var c : q.getChoices()) {
            RadioButton rdb = new RadioButton(c.getContent());
            rdb.setToggleGroup(tg);
            vboxChoices.getChildren().add(rdb);
        }
    }

    public void handleCheck(ActionEvent event) {
        Question q = this.questions.get(this.currentQuestion);
        for (int i = 0; i < q.getChoices().size(); i++){
            if (q.getChoices().get(i).isIs_correct()){
                RadioButton rdb = (RadioButton) this.vboxChoices.getChildren().get(i);
                if (rdb.isSelected()){
                    this.txtResult.setText("Bạn chọn chính xác !");
                    this.txtResult.setStyle("-fx-fill: green");
                }
                else {
                    this.txtResult.setText("Bạn sai rồi !");
                    this.txtResult.setStyle("-fx-fill: red");
                }
            }
        }
        
    }

    public void handleNext(ActionEvent event) {
        if (this.currentQuestion < this.questions.size() - 1) {
            ++this.currentQuestion;
        }
        this.loadQuestion();
    }
}
