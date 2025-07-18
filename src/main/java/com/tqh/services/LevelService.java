/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqh.services;

import com.tqh.pojo.Level;
import com.tqh.utils.JdbcConnector;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Wuan Hi Dep Trai
 */
public class LevelService {

    public List<Level> getLevels() throws SQLException {
        List<Level> levels;
        try (Connection conn = JdbcConnector.getInstance().connect()) {
            Statement stmt = conn.createStatement();
            System.out.println("Connection successful!");
            ResultSet rs = stmt.executeQuery("SELECT * FROM level");
            levels = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String note = rs.getString("note");
                Level c = new Level(id, name, note);
                levels.add(c);
                System.out.println("Added level: " + name);
            }
        }
        System.out.println("Total levels found: " + levels.size());
        return levels;
    }
}
