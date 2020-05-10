package com.suliborski.planetbound.logic.states;

import com.suliborski.planetbound.logic.data.Event;

public interface IState {

    IState selectShip(String type);
    IState travelToLandable();
    IState acceptTravelConsequences();
    IState buyEnergyShield();
    IState buyAmmo();
    IState buyFuel();
    IState buyDrone();
    IState upgradeCargoCapacity();
    IState upgradeWeaponSystem();
    IState recruitCrewMember();
    IState explorePlanet();
    IState moveUp();
    IState moveDown();
    IState moveLeft();
    IState moveRight();

}
