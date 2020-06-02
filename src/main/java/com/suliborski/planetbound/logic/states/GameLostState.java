package com.suliborski.planetbound.logic.states;

import com.suliborski.planetbound.logic.GalaxyData;
import com.suliborski.planetbound.logic.data.Log;

public class GameLostState extends StateAdapter {

    public GameLostState(GalaxyData galaxyData) {
        super(galaxyData);
        GalaxyData.addLog(new Log("Into GameLost State"));
    }
}
