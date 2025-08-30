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
public class CategoryQuestionServiceDecorator extends QuestionServiceDecorator {
    private int cateId; 

    public CategoryQuestionServiceDecorator(int cateID, BaseQuestionService decorator) {
        super(decorator);
        this.cateId = cateID; 
    }

    @Override
    public String getSQL(List<Object> para) {
        String sql = this.decorator.getSQL(para) + " AND category_id=?"; 
        para.add(cateId); 
        return sql; 
    }

   
    
}
