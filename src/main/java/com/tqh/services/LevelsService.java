/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqh.services;


import com.tqh.pojo.Levels;
import com.tqh.utils.JdbcConnector;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class LevelsService {
    public static List<Levels> getLevels() throws SQLException {
        List<Levels> levels;
        try (Connection conn = JdbcConnector.getInstance().connect()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM level");
            levels = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String note = rs.getString("note"); 
                Levels c = new Levels(id, name, note);
                levels.add(c);
            }
        }
        return levels;
    }
}
