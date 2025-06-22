/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqh.pojo;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Wuan Hi Dep Trai
 */
@Getter
@Setter
public class Category {

    private int id;
    private String name;

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("%d - %s\n", this.id, this.name);
    }

}
