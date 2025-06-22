/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqh.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Wuan Hi Dep Trai
 */
public class JdbcConnector {
    private static JdbcConnector instance; 
    private static Connection conn; 
    static {
        try { 
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JdbcConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private JdbcConnector() throws SQLException{
        this.conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/quizapp", "root", "Mysqlserver123$");
    }
    public static JdbcConnector getInstance() throws SQLException{
        if(instance == null){
            instance = new JdbcConnector();
        }
        return instance;
    }
    
    public Connection connect(){
        return JdbcConnector.conn; 
    }
    
    public void close() throws SQLException{
        this.conn.close();
    }
}
