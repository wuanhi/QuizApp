/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqh.utils;

import com.tqh.services.CategoryService;
import com.tqh.services.LevelService;
import com.tqh.services.question.BaseQuestionService;
import com.tqh.services.question.QuestionService;
import com.tqh.services.question.UpdateQuestionService;

/**
 *
 * @author Wuan Hi Dep Trai
 */
public class Configs {

    public static final CategoryService cateService = new CategoryService();
    public static final BaseQuestionService questionService = new QuestionService();
    public static final UpdateQuestionService updateQuestionService = new UpdateQuestionService();
    public static final LevelService levelService = new LevelService();
    
}
