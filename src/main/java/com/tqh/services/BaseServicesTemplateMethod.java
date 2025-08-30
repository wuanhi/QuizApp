/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqh.services;

import com.tqh.pojo.Question;
import com.tqh.utils.JdbcConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.xml.transform.Result;

/**
 *
 * @author Wuan Hi Dep Trai
 */
public abstract class BaseServicesTemplateMethod<T>{
    public abstract PreparedStatement getStm(Connection conn) throws SQLException; 
    public abstract List<T> getResults(ResultSet rs) throws SQLException; 
    
    public List<T> getList() throws SQLException{
        Connection conn = JdbcConnector.getInstance().connect(); 
        PreparedStatement stm = getStm(conn); 
        return this.getResults(stm.executeQuery()); 
    }
}
