/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqh.services.question;

import com.tqh.pojo.Choice;
import com.tqh.pojo.Question;
import com.tqh.utils.JdbcConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Wuan Hi Dep Trai
 */
public class UpdateQuestionService {

    public boolean deleteQuestion(int id) throws SQLException {
        Connection conn = JdbcConnector.getInstance().connect();
        String sql = "DELETE from question WHERE id = ?";
        PreparedStatement stm = conn.prepareCall(sql);
        stm.setInt(1, id);
        return stm.executeUpdate() > 0;
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
            for (Choice c : q.getChoices()) {
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
    
}
