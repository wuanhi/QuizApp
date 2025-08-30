/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqh.services.question.Decorator;

import com.tqh.pojo.Question;
import com.tqh.services.question.BaseQuestionService;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Wuan Hi Dep Trai
 */
public class LimitedQuestionServiceDecorator extends QuestionServiceDecorator {

    private int numLimited;

    public LimitedQuestionServiceDecorator(int num, BaseQuestionService decorator) {
        super(decorator);
        this.numLimited = num;
    }

    @Override
    public String getSQL(List<Object> para) {
        String sql = this.decorator.getSQL(para) + " ORDER BY rand() LIMIT ?";
        para.add(this.numLimited);
        return sql;
    }

    @Override
    public List<Question> getList() throws SQLException {
        List<Question> listQ = super.getList();
        for (var q : listQ) {
            q.setChoices(this.getChoicesByQuestionsID(q.getId()));
        }
        return listQ;
    }
}
