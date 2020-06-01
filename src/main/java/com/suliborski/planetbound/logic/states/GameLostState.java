package com.suliborski.planetbound.logic.states;

import com.suliborski.planetbound.logic.GalaxyData;

public class GameLostState extends StateAdapter {

    public GameLostState(GalaxyData galaxyData) {
        super(galaxyData);
        System.out.println("Into GameLost State");
    }
}
