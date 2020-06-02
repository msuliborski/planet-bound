package com.suliborski.planetbound.logic.states;

import com.suliborski.planetbound.logic.GalaxyData;
import com.suliborski.planetbound.logic.data.Log;

public class GameWonState extends StateAdapter {

    public GameWonState(GalaxyData galaxyData) {
        super(galaxyData);
        GalaxyData.addLog(new Log("Into GameWon State"));
    }
}