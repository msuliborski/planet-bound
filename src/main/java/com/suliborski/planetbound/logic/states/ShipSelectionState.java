package com.suliborski.planetbound.logic.states;

import com.suliborski.planetbound.logic.GalaxyData;
import com.suliborski.planetbound.logic.data.Ship;

public class ShipSelectionState extends StateAdapter {

    public ShipSelectionState(GalaxyData galaxyData) {
        super(galaxyData);
        System.out.println("Into ShipSelection State");
    }

    @Override
    public IState selectShip(String type) {

        if (type.equals("mining"))
//            galaxyData.setShip(new Ship(type, 2, 33, 13, 4, 6, 53, 18, 9, 4, 9, 9, 9, 9, 12, 12, 12, 12, 1, 3, 1, 1,false)); //test ship
            galaxyData.setShip(new Ship(type, 6, 53, 18, 9, 6, 53, 18, 9, 0, 0, 0, 0, 0, 6, 6, 6, 6, 0, 3, 1, 1,true));
        else
            galaxyData.setShip(new Ship(type, 6, 35, 18, 18, 6, 35, 18, 18, 0, 0, 0, 0, 0, 6, 6, 6, 6, 0, 1, 2, 3, true));

        galaxyData.preparePlanet();
        return new OnPlanetState(galaxyData);
    }
}


