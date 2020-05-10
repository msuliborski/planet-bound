package com.suliborski.planetbound.logic.states;

import com.suliborski.planetbound.logic.GalaxyData;
import com.suliborski.planetbound.logic.data.Planet;

public class OnPlanetState extends StateAdapter {


    public OnPlanetState(GalaxyData galaxyData) {
        super(galaxyData);

    }

    @Override
    public IState travelToLandable() {
        return new AcceptTravelConsequencesState(galaxyData);
    }

    @Override
    public IState buyEnergyShield() {
        galaxyData.getShip().buyEnergyShield();
        return this;
    }

    @Override
    public IState buyAmmo() {
        galaxyData.getShip().buyAmmo();
        return this;
    }

    @Override
    public IState buyFuel() {
        galaxyData.getShip().buyFuel();
        return this;
    }

    @Override
    public IState explorePlanet() {
        galaxyData.getPlanetBoard().prepareBoard(((Planet) galaxyData.getLandable()).getType());
        return new OnExpeditionState(galaxyData);
    }
}



