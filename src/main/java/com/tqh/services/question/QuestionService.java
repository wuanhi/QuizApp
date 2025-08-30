/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqh.services.question;

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
public class QuestionService extends BaseQuestionService {
    @Override
    public String getSQL(List<Object> para) {
        return "SELECT * FROM question WHERE 1=1";
    }
    
//    public List<Question> getQuestions() throws SQLException {
//        List<Question> questions;
//        Connection conn = JdbcConnector.getInstance().connect();
//        PreparedStatement stmt = conn.prepareCall(sql);
//        System.out.println("Connection successful!");
//        ResultSet rs = stmt.executeQuery("SELECT * FROM question");
//        questions = new ArrayList<>();
//        while (rs.next()) {
//            Question q = new Question.Builder(rs.getInt("id"), rs.getString("content")).build();
//            questions.add(q);
//        }
//        return questions;
//    }

//    public List<Question> getQuestions(int num) throws SQLException {
//        List<Question> questions;
//        Connection conn = JdbcConnector.getInstance().connect();
//        PreparedStatement stmt = conn.prepareCall("SELECT * FROM question ORDER BY rand() LIMIT ?");
//        stmt.setInt(1, num);
//        System.out.println("Connection successful!");
//        ResultSet rs = stmt.executeQuery();
//        questions = new ArrayList<>();
//        while (rs.next()) {
//            Question q = new Question.Builder(rs.getInt("id"), rs.getString("content")).addAllChoice(this.getChoicesByQuestionsID(rs.getInt("id"))).build();
//            questions.add(q);
//        }
//
//        return questions;
//    }

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

    
}
