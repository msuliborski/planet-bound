package com.suliborski.planetbound.logic.states;

import com.suliborski.planetbound.logic.Galaxy;
import com.suliborski.planetbound.logic.data.Ship;

public class ShipSelectionState extends StateAdapter {


    public ShipSelectionState(Galaxy galaxy) {
        super(galaxy);
    }

    @Override
    public IState selectShip(String type) {

        if (type.equals("mining"))
            galaxy.setShip(new Ship(type, 6, 53, 18, 1, 6, 53, 18, 9, 0, 0, 0, 0, 0, 6, 6, 6, 6, 0, 3, true));
        else
            galaxy.setShip(new Ship(type, 6, 35, 18, 2, 6, 35, 18, 9, 0, 0, 0, 0, 0, 6, 6, 6, 6, 0, 1, true));

        return new OnPlanetState(galaxy);
    }
}


