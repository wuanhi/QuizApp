/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqh.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Wuan Hi Dep Trai
 */
public class Question {

    private int id;
    private String content;
    private String hint;
    private String image;
    private Category category;
    private Level level;
    private List<Choice> choices;

    private Question(Builder b) {
        this.id = b.id;
        this.content = b.content;
        this.hint = b.hint;
        this.image = b.image;
        this.category = b.category;
        this.level = b.level;
        this.choices = b.choices;
    }

    public static class Builder {

        private int id;
        private String content;
        private String hint;
        private String image;
        private Category category;
        private Level level;
        private List<Choice> choices = new ArrayList<>();

        public Builder(String content, Category cate, Level lv) throws Exception {
            if (content.isEmpty() || cate == null || lv == null) {
                throw new Exception("Invalid data");
            }
            this.content = content;
            this.category = cate;
            this.level = lv;
        }

        public Builder(int id, String content){
            this.content = content;
            this.id = id; 
        }
        
        public Builder addHint(String h) {
            this.hint = h;
            return this;
        }

        public Builder addImage(String img) {
            this.image = img;
            return this;
        }

        public Builder addChoice(Choice choice) {
            this.choices.add(choice);
            return this;
        }

        public Builder addAllChoice(List<Choice> c ) {
            this.choices.addAll(c);
            return this;
        }
        
        public Question build() {
            return new Question(this);
        }

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

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public List<Choice> getChoices() {
        return choices;
    }

    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }

}
