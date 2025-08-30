/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqh.services.question.Decorator;

import com.tqh.services.question.BaseQuestionService;
import java.util.List;

/**
 *
 * @author Wuan Hi Dep Trai
 */
public class LevelQuestionServiceDecorator extends QuestionServiceDecorator {
    private int levelId; 

    public LevelQuestionServiceDecorator(int lvID, BaseQuestionService decorator) {
        super(decorator);
        this.levelId = lvID; 
    }

    @Override
    public String getSQL(List<Object> para) {
        String sql = this.decorator.getSQL(para) + " AND level_id=?"; 
        para.add(levelId); 
        return sql; 
    }
    
}
