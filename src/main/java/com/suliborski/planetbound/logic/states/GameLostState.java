package com.suliborski.planetbound.logic.states;

import com.suliborski.planetbound.logic.GalaxyData;

public class GameLostState extends StateAdapter {
    public GameLostState(GalaxyData galaxyData) {
        super(galaxyData);
    }

    @Override
    public IState playAgain(){
        return new ShipSelectionState(galaxyData);
    }
}
