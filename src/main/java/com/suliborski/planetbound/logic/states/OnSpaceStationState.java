package com.suliborski.planetbound.logic.states;

import com.suliborski.planetbound.logic.GalaxyData;

public class OnSpaceStationState extends StateAdapter {


    public OnSpaceStationState(GalaxyData galaxyData) {
        super(galaxyData);
    }

    @Override
    public IState convertResource(String type, String into) {
        galaxyData.getShip().convertResource(type, into);
        return this;
    }

    @Override
    public IState fullFixEnergyShields() {
        galaxyData.getShip().fullFixEnergyShields();
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
        galaxyData.getShip().recruitCrewMember();
        return this;
    }

    @Override
    public IState leaveSpaceStation() {
        return new OnPlanetState(galaxyData);
    }
}
