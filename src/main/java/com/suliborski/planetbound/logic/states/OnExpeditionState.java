package com.suliborski.planetbound.logic.states;

import com.suliborski.planetbound.logic.GalaxyData;

public class OnExpeditionState extends StateAdapter {
    
    public OnExpeditionState(GalaxyData galaxyData) {
        super(galaxyData);
    }

    @Override
    public IState moveUp() {
        galaxyData.getExpedition().moveDrone("up");
        if (checkOnDrone())
            if (galaxyData.getShip().getArtifacts() == galaxyData.getArtifactsNeeded()) return new GameWonState(galaxyData);
            else return new OnPlanetState(galaxyData);
        else return this;
    }

    @Override
    public IState moveDown() {
        galaxyData.getExpedition().moveDrone("down");
        if (checkOnDrone())
            if (galaxyData.getShip().getArtifacts() == galaxyData.getArtifactsNeeded()) return new GameWonState(galaxyData);
            else return new OnPlanetState(galaxyData);
        else return this;
    }

    @Override
    public IState moveLeft() {
        galaxyData.getExpedition().moveDrone("left");
        if (checkOnDrone())
            if (galaxyData.getShip().getArtifacts() == galaxyData.getArtifactsNeeded()) return new GameWonState(galaxyData);
            else return new OnPlanetState(galaxyData);
        else return this;
    }

    @Override
    public IState moveRight() {
        galaxyData.getExpedition().moveDrone("right");
        if (checkOnDrone())
            if (galaxyData.getShip().getArtifacts() == galaxyData.getArtifactsNeeded()) return new GameWonState(galaxyData);
            else return new OnPlanetState(galaxyData);
        else return this;
    }

    private boolean checkOnDrone(){
        if (galaxyData.getExpedition().getDrone().isBackWithCargo()) {
            galaxyData.getShip().addResource(galaxyData.getExpedition().getDrone().getCargoType(), galaxyData.getExpedition().getDrone().getCargoType().equals("artifact") ? 1 : (int) Math.floor(Math.random() * 6 + 1));
            return true;
        }

        if (galaxyData.getExpedition().getDrone().getHealth() == 0) {
            galaxyData.getShip().setDroneWorking(false);
            galaxyData.getPlanet().addResource(galaxyData.getExpedition().getResourceType());
            return true;
        }
        return false;
    }
}
