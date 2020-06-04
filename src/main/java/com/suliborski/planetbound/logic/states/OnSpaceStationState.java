package com.suliborski.planetbound.logic.states;

import com.suliborski.planetbound.logic.GalaxyData;
import com.suliborski.planetbound.logic.data.Log;

public class OnSpaceStationState extends StateAdapter {

    public OnSpaceStationState(GalaxyData galaxyData) {
        super(galaxyData);
        GalaxyData.addLog(new Log("Into OnSpaceStation State"));
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
        if (galaxyData.getPlanet().isCanUpgradeCargo() && galaxyData.getShip().upgradeCargo())
            galaxyData.getPlanet().setCanUpgradeCargo(false);
        return this;
    }

    @Override
    public IState upgradeWeaponSystem() {
        if (galaxyData.getPlanet().isCanUpgradeWeaponSystem() && galaxyData.getShip().upgradeWeaponSystem())
            galaxyData.getPlanet().setCanUpgradeWeaponSystem(false);
        return this;
    }

    @Override
    public IState recruitCrewMember() {
        galaxyData.getShip().recruitCrewMember();
        return this;
    }

    @Override
    public IState leaveSpaceStation() {
        GalaxyData.addLog(new Log("Wants to leave space station"));
        return new OnPlanetState(galaxyData);
    }
}
