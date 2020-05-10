package com.suliborski.planetbound.logic.states;

import com.suliborski.planetbound.logic.Galaxy;

public class OnSpaceStationState extends StateAdapter {


    public OnSpaceStationState(Galaxy galaxy) {
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
        galaxy.getShip().buyEnergyShield();
        return this;
    }

    @Override
    public IState buyFuel() {
        galaxy.getShip().buyFuel();
        return this;
    }

    @Override
    public IState buyDrone() {
        galaxy.getShip().buyDrone();
        return this;
    }


    @Override
    public IState upgradeCargoCapacity() {
        galaxy.getShip().upgradeCargo();
        return this;
    }

    @Override
    public IState upgradeWeaponSystem() {
        galaxy.getShip().upgradeWeaponSystem();
        return this;
    }

    @Override
    public IState recruitCrewMember() {
        galaxy.getShip().upgradeCargo();
        return this;
    }
}
