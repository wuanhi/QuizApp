/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqh.services;

import com.tqh.pojo.Level;
import com.tqh.utils.JdbcConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author Wuan Hi Dep Trai
 */
public class LevelService extends BaseServicesTemplateMethod<Level> {

    @Override
    public PreparedStatement getStm(Connection conn) throws SQLException {
        return conn.prepareCall("SELECT * FROM level"); 
    }

    @Override
    public List<Level> getResults(ResultSet rs) throws SQLException {
        List<Level> levels = new ArrayList<>(); 
        while (rs.next()) {
            int id = rs.getInt("id"); 
            String name = rs.getString("name"); 
            String note = rs.getString("note"); 
            Level lv = new Level(id, name, note); 
            levels.add(lv); 
        }
        return levels; 
    }
    
    
}
