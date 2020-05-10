package com.suliborski.planetbound.logic.states;

import com.suliborski.planetbound.logic.Galaxy;

public class GameLostState extends StateAdapter {
    public GameLostState(Galaxy galaxy) {
        super(galaxy);
    }

    @Override
    public IState playAgain(){
        return new ShipSelectionState(galaxy);
    }
}
