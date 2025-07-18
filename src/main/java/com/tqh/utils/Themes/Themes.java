/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.tqh.utils.Themes;

import javafx.scene.Scene;

/**
 *
 * @author Wuan Hi Dep Trai
 */
public enum Themes {
    DEFAULT {
        @Override
        public void changeTheme(Scene scene) {
            ThemesManager.setThemeFactory(new DefaultThemesFactory());
            ThemesManager.applyTheme(scene);
        }
    }, LIGHT {
        @Override
        public void changeTheme(Scene scene) {
            ThemesManager.setThemeFactory(new LightThemesFactory());
            ThemesManager.applyTheme(scene);
        }
    }, DARK {
        @Override
        public void changeTheme(Scene scene) {
            ThemesManager.setThemeFactory(new DarkThemesFactory());
            ThemesManager.applyTheme(scene);
        }
    };

    public abstract void changeTheme(Scene scene);
}
