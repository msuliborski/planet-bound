package com.suliborski.planetbound.logic.states;

import com.suliborski.planetbound.logic.GalaxyData;

public class OnPlanetState extends StateAdapter {


    public OnPlanetState(GalaxyData galaxyData) {
        super(galaxyData);
    }

    @Override
    public IState travelToNextPlanet() {
        return new AcceptTravelConsequencesState(galaxyData);
    }

    @Override
    public IState visitSpaceStation() {
        return new OnSpaceStationState(galaxyData);
    }


    @Override
    public IState goOnExpedition() {
        if (!galaxyData.getPlanet().getResourceIds().isEmpty() && galaxyData.getShip().getCrew() >= 3) {
            galaxyData.getExpedition().prepareExpedition(galaxyData.getPlanet());
            return new OnExpeditionState(galaxyData);
        } else
            return this;
    }

    @Override
    public IState produceEnergyShield() {
        if (galaxyData.getShip().getCrew() >= 6)
            galaxyData.getShip().produceEnergyShield();
        return this;
    }

    @Override
    public IState produceAmmo() {
        if (galaxyData.getShip().getCrew() >= 6)
            galaxyData.getShip().produceAmmo();
        return this;
    }

    @Override
    public IState produceFuel() {
        if (galaxyData.getShip().getCrew() >= 6)
            galaxyData.getShip().produceFuel();
        return this;
    }

    @Override
    public IState saveGame() {
        galaxyData.saveGame();
        return this;
    }

    @Override
    public IState exitGame() {
        galaxyData.saveGame();
        return null;
    }
}



