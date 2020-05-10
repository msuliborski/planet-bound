package com.suliborski.planetbound.logic.states;

import com.suliborski.planetbound.logic.GalaxyData;

public class OnSpaceStationState extends StateAdapter {


    public OnSpaceStationState(GalaxyData galaxyData) {
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
        galaxyData.getShip().buyEnergyShield();
        return this;
    }

    @Override
    public IState buyFuel() {
        galaxyData.getShip().buyFuel();
        return this;
    }

    @Override
    public IState buyDrone() {
        galaxyData.getShip().buyDrone();
        return this;
    }

    @Override
    public IState upgradeCargoCapacity() {
        galaxyData.getShip().upgradeCargo();
        return this;
    }

    @Override
    public IState upgradeWeaponSystem() {
        galaxyData.getShip().upgradeWeaponSystem();
        return this;
    }

    @Override
    public IState recruitCrewMember() {
        galaxyData.getShip().upgradeCargo();
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
