/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqh.services.question;

import com.tqh.pojo.Choice;
import com.tqh.pojo.Question;
import com.tqh.services.BaseServicesTemplateMethod;

import com.tqh.utils.JdbcConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Wuan Hi Dep Trai
 */
public abstract class BaseQuestionService extends BaseServicesTemplateMethod<Question>{

    public abstract String getSQL(List<Object> para);

    @Override
    public List<Question> getResults(ResultSet rs) throws SQLException {
        List<Question> questions;
        questions = new ArrayList<>();
        while (rs.next()) {
            Question q = new Question.Builder(rs.getInt("id"), rs.getString("content")).build();
            questions.add(q);
        }
        return questions;
    }

    @Override
    public PreparedStatement getStm(Connection conn) throws SQLException {
        List<Object> para = new ArrayList<>();
        PreparedStatement stmt = conn.prepareCall(this.getSQL(para));
        for (int i = 0; i < para.size(); i++) {
            stmt.setObject(i + 1, para.get(i));
        }
        return stmt; 
    }

    public List<Choice> getChoicesByQuestionsID(int id) throws SQLException {
        List<Choice> choices;
        Connection conn = JdbcConnector.getInstance().connect();
        PreparedStatement stmt = conn.prepareCall("SELECT * FROM choice WHERE question_id=?");
        stmt.setInt(1, id);
        System.out.println("Connection successful!");
        ResultSet rs = stmt.executeQuery();
        choices = new ArrayList<>();
        while (rs.next()) {
            Choice c = new Choice(rs.getInt("id"), rs.getString("content"), rs.getBoolean("is_correct"));
            choices.add(c);
        }

        return choices;
    }
}
