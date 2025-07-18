/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqh.utils.Themes;

import javafx.scene.Scene;

/**
 *
 * @author Wuan Hi Dep Trai
 */
public class ThemesManager {

    private static ThemesFactory themeFactory = new DefaultThemesFactory();

    public ThemesManager() {
    }

    public static void setThemeFactory(ThemesFactory aThemeFactory) {
        themeFactory = aThemeFactory;
    }

    public static void applyTheme(Scene scene) {
        scene.getRoot().getStylesheets().clear();
        scene.getRoot().getStylesheets().add(themeFactory.getStyleSheet());
    }
}
