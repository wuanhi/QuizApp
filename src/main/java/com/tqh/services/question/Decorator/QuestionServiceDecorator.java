/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqh.services.question.Decorator;

import com.tqh.services.question.BaseQuestionService;

/**
 *
 * @author Wuan Hi Dep Trai
 */
public abstract class QuestionServiceDecorator extends BaseQuestionService {
    protected BaseQuestionService decorator; 

    public QuestionServiceDecorator(BaseQuestionService decorator) {
        this.decorator = decorator;
    }

}
