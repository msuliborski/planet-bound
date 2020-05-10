package com.suliborski.planetbound.logic.states;

import com.suliborski.planetbound.logic.Galaxy;

public class StateAdapter implements IState {

    Galaxy galaxy;

    public StateAdapter(Galaxy galaxy) {
        this.galaxy = galaxy;
    }

    @Override
    public IState selectShip(String type) {
        return this;
    }

    @Override
    public IState travelToLandable() {
        return this;
    }

    @Override
    public IState acceptTravelConsequences() {
        return this;
    }

    @Override
    public IState buyEnergyShield() {
        return this;
    }

    @Override
    public IState buyAmmo() {
        return this;
    }

    @Override
    public IState buyFuel() {
        return this;
    }

    @Override
    public IState buyDrone() {
        return this;
    }

    @Override
    public IState upgradeCargoCapacity() {
        return this;
    }

    @Override
    public IState upgradeWeaponSystem() {
        return this;
    }

    @Override
    public IState recruitCrewMember() {
        return this;
    }

    @Override
    public IState explorePlanet() {
        return this;
    }

    @Override
    public IState moveUp() {
        return this;
    }

    @Override
    public IState moveDown() {
        return this;
    }

    @Override
    public IState moveLeft() {
        return this;
    }

    @Override
    public IState moveRight() {
        return this;
    }

    @Override
    public IState playAgain() {
        return this;
    }

}
