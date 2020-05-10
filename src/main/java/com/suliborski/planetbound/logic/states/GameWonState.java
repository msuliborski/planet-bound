package com.suliborski.planetbound.logic.states;

import com.suliborski.planetbound.logic.Galaxy;

public class GameWonState extends StateAdapter {

    public GameWonState(Galaxy galaxy) {
        super(galaxy);
    }

    @Override
    public IState playAgain(){
        return new ShipSelectionState(galaxy);
    }
}