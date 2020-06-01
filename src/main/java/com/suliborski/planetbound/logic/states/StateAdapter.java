package com.suliborski.planetbound.logic.states;

import com.suliborski.planetbound.logic.GalaxyData;

import java.io.Serializable;

public class StateAdapter implements IState, Serializable {
    GalaxyData galaxyData;

    public StateAdapter(GalaxyData galaxyData) {
        this.galaxyData = galaxyData;
    }

    @Override
    public IState selectShip(String type) {
        return this;
    }


    @Override
    public IState travelToNextPlanet() {
        return this;
    }

    @Override
    public IState acceptTravelConsequences() {
        return this;
    }


    @Override
    public IState visitSpaceStation() {
        return this;
    }

    @Override
    public IState convertResource(String type, String into) {
        return this;
    }

    @Override
    public IState fullFixEnergyShields() {
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
    public IState leaveSpaceStation() {
        return this;
    }


    @Override
    public IState goOnExpedition() {
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
    public IState produceEnergyShield() {
        return this;
    }

    @Override
    public IState produceAmmo() {
        return this;
    }

    @Override
    public IState produceFuel() {
        return this;
    }


    @Override
    public IState playAgain() {
        return new ShipSelectionState(galaxyData);
    }

    @Override
    public IState exitGame() {
        return this;
    }

}
