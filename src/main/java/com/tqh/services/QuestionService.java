/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqh.services;

import com.tqh.pojo.Choice;
import com.tqh.pojo.Level;
import com.tqh.pojo.Question;
import com.tqh.utils.JdbcConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Wuan Hi Dep Trai
 */
public class QuestionService {

    public List<Question> getQuestions() throws SQLException {
        List<Question> questions;
        Connection conn = JdbcConnector.getInstance().connect();
        Statement stmt = conn.createStatement();
        System.out.println("Connection successful!");
        ResultSet rs = stmt.executeQuery("SELECT * FROM question");
        questions = new ArrayList<>();
        while (rs.next()) {
            Question q = new Question.Builder(rs.getInt("id"), rs.getString("content")).build();
            questions.add(q);
        }
        return questions;
    }

    public List<Question> getQuestions(int num) throws SQLException {
        List<Question> questions;
        Connection conn = JdbcConnector.getInstance().connect();
        PreparedStatement stmt = conn.prepareCall("SELECT * FROM question ORDER BY rand() LIMIT ?");
        stmt.setInt(1, num);
        System.out.println("Connection successful!");
        ResultSet rs = stmt.executeQuery();
        questions = new ArrayList<>();
        while (rs.next()) {
            Question q = new Question.Builder(rs.getInt("id"), rs.getString("content")).addAllChoice(this.getChoicesByQuestionsID(rs.getInt("id"))).build();
            questions.add(q);
        }

        return questions;
    }

    public List<Question> getQuestions(String kw) throws SQLException {
        List<Question> questions;
        Connection conn = JdbcConnector.getInstance().connect();
        PreparedStatement stmt = conn.prepareCall("SELECT * FROM question WHERE content like concat('%', ? , '%')");
        stmt.setString(1, kw);
        System.out.println("Connection successful!");
        ResultSet rs = stmt.executeQuery();
        questions = new ArrayList<>();
        while (rs.next()) {
            Question q = new Question.Builder(rs.getInt("id"), rs.getString("content")).build();
            questions.add(q);
        }
        return questions;
    }

    public List<Choice> getChoicesByQuestionsID(int id) throws SQLException {
        List<Choice> choices;
        Connection conn = JdbcConnector.getInstance().connect();
        PreparedStatement stmt = conn.prepareCall("SELECT * FROM choice WHERE question_id = ?");
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

    public void addQuestion(Question q) throws SQLException {
        Connection conn = JdbcConnector.getInstance().connect();
        conn.setAutoCommit(false);
        String sql = "INSERT INTO question(content, hint, image, category_id, level_id) VALUES(?, ?, ?, ?, ?)";
        PreparedStatement stm = conn.prepareCall(sql);
        stm.setString(1, q.getContent());
        stm.setString(2, q.getHint());
        stm.setString(3, q.getImage());
        stm.setInt(4, q.getCategory().getId());
        stm.setInt(5, q.getLevel().getId());

        if (stm.executeUpdate() > 0) {
            int question_Id = -1;
            ResultSet rs = stm.getGeneratedKeys();
            if (rs.next()) {
                question_Id = rs.getInt(1);
            }

            sql = "INSERT INTO choice(content, is_correct, question_id) VALUES (?, ?, ?)";
            stm = conn.prepareCall(sql);
            for (var c : q.getChoices()) {
                stm.setString(1, c.getContent());
                stm.setBoolean(2, c.isIs_correct());
                stm.setInt(3, question_Id);
                stm.executeUpdate();
            }
            conn.commit();
        } else {
            conn.rollback();
        }
    }

    public boolean deleteQuestion(int id) throws SQLException {
        Connection conn = JdbcConnector.getInstance().connect();
        String sql = "DELETE from question WHERE id = ?";
        PreparedStatement stm = conn.prepareCall(sql);
        stm.setInt(1, id);
        return stm.executeUpdate() > 0; 
    }
}
