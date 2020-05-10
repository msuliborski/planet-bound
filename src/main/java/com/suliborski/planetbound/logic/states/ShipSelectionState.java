package com.suliborski.planetbound.logic.states;

import com.suliborski.planetbound.logic.GalaxyData;
import com.suliborski.planetbound.logic.data.Ship;

public class ShipSelectionState extends StateAdapter {


    public ShipSelectionState(GalaxyData galaxyData) {
        super(galaxyData);
    }

    @Override
    public IState selectShip(String type) {

        if (type.equals("mining"))
            galaxyData.setShip(new Ship(type, 6, 53, 18, 9, 6, 53, 18, 9, 0, 0, 0, 0, 0, 6, 6, 6, 6, 0, 3, 0, 0,true));
        else
            galaxyData.setShip(new Ship(type, 6, 35, 18, 18, 6, 35, 18, 18, 0, 0, 0, 0, 0, 6, 6, 6, 6, 0, 1, 1, 2, true));

        galaxyData.prepareLandable();
        return galaxyData.getLandable().isPlanet() ? new OnPlanetState(galaxyData) : new OnSpaceStationState(galaxyData);
    }
}


