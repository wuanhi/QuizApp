/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqh.utils.Themes;

import com.tqh.design_pattern.App;

/**
 *
 * @author Wuan Hi Dep Trai
 */
public class LightThemesFactory implements ThemesFactory {

    @Override
    public String getStyleSheet() {
        return App.class.getResource("lightTheme.css").toExternalForm();
    }
}
