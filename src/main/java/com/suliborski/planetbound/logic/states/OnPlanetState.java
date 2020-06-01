package com.suliborski.planetbound.logic.states;

import com.suliborski.planetbound.logic.GalaxyData;

public class OnPlanetState extends StateAdapter {

    public OnPlanetState(GalaxyData galaxyData) {
        super(galaxyData);
        System.out.println("Into OnPlanet State");
    }

    @Override
    public IState travelToNextPlanet() {
        System.out.println("Wants to travel to next planet");
        return new AcceptTravelConsequencesState(galaxyData);
    }

    @Override
    public IState visitSpaceStation() {
        System.out.println("Wants to visit space station");
        return new OnSpaceStationState(galaxyData);
    }

    @Override
    public IState goOnExpedition() {
        System.out.println("Wants to go on expedition");
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
}



