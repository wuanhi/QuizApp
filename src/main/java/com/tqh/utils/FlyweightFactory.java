/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqh.utils;

import com.tqh.services.BaseServicesTemplateMethod;
import com.tqh.services.question.BaseQuestionService;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Wuan Hi Dep Trai
 */
public class FlyweightFactory {
    public static final HashMap<String, List> cachedData = new HashMap<>();
    public static <E> List<E> getCache(BaseServicesTemplateMethod bs, String key) throws SQLException{
        if (cachedData.containsKey(key) == true){
            return cachedData.get(key); 
        }
        System.out.print(Math.random());
        List ls = bs.getList();
        cachedData.put(key, ls);
        return ls; 
    }
}
