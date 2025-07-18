/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqh.pojo;

/**
 *
 * @author Wuan Hi Dep Trai
 */
public class Choice {

    private int id;
    private String content;
    private boolean is_correct;

    public Choice(int id, String content, boolean is_correct) {
        this.id = id;
        this.content = content;
        this.is_correct = is_correct;
    }

    public Choice(String content, boolean is_correct) {
        this.content = content;
        this.is_correct = is_correct;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isIs_correct() {
        return is_correct;
    }

    public void setIs_correct(boolean is_correct) {
        this.is_correct = is_correct;
    }

   

}
