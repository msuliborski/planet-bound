package com.suliborski.planetbound.logic.states;

import com.suliborski.planetbound.logic.Galaxy;

public class OnPlanetState extends StateAdapter {


    public OnPlanetState(Galaxy galaxy) {
        super(galaxy);
    }

    @Override
    public IState travelToLandable() {
        return new AcceptTravelConsequencesState(galaxy);
    }

    @Override
    public IState buyEnergyShield() {
        galaxy.getShip().buyEnergyShield();
        return this;
    }

    @Override
    public IState buyAmmo() {
        galaxy.getShip().buyAmmo();
        return this;
    }

    @Override
    public IState buyFuel() {
        galaxy.getShip().buyFuel();
        return this;
    }

    @Override
    public IState explorePlanet() {
        galaxy.getPlanetBoard().prepareBoard();
        return super.explorePlanet();
    }
}


