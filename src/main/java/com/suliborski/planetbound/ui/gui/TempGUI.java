package com.suliborski.planetbound.ui.gui;

import com.suliborski.planetbound.logic.Galaxy;

public class TempGUI {
    public static void main(String[] args){
        Galaxy galaxy = Galaxy.getInstance();

        for (int i = 1; i < 200; i++){
            System.out.println(Math.floor(Math.random() * 4 + 1));
        }
    }
}
