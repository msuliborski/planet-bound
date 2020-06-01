package com.suliborski.planetbound.logic.states;

import com.suliborski.planetbound.logic.GalaxyData;

public class GameWonState extends StateAdapter {

    public GameWonState(GalaxyData galaxyData) {
        super(galaxyData);
        System.out.println("Into GameWon State");
    }
}