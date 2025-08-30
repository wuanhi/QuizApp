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
public class KeywordQuestionServiceDecorator extends QuestionServiceDecorator {
    private String keyword; 
    public KeywordQuestionServiceDecorator(String keyword, BaseQuestionService decorator) {
        super(decorator);
        this.keyword = keyword;
    }

    @Override
    public String getSQL(List<Object> para) {
        String sql = this.decorator.getSQL(para) + " AND content like concat('%', ? , '%')";
        para.add(keyword); 
        return sql; 
    }
}
