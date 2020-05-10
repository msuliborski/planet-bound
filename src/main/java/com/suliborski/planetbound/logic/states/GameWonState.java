package com.suliborski.planetbound.logic.states;

import com.suliborski.planetbound.logic.GalaxyData;

public class GameWonState extends StateAdapter {

    public GameWonState(GalaxyData galaxyData) {
        super(galaxyData);
    }

    @Override
    public IState playAgain(){
        return new ShipSelectionState(galaxyData);
    }
}