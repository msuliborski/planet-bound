package com.suliborski.planetbound.logic.states;

import com.suliborski.planetbound.logic.GalaxyData;
import com.suliborski.planetbound.logic.data.Planet;

public class OnExpeditionState extends StateAdapter {


    public OnExpeditionState(GalaxyData galaxyData) {
        super(galaxyData);
        galaxyData.getPlanetBoard().prepareBoard(((Planet) galaxyData.getLandable()).getType());
    }

    @Override
    public IState moveUp() {
        galaxyData.getPlanetBoard().moveDrone("up");
        boolean finishExpedition = checkOnDrone();
        if (finishExpedition && galaxyData.getShip().getArtifacts() >= 5) return new GameWonState(galaxyData);
        if (finishExpedition) return new OnPlanetState(galaxyData);
        else return this;
    }

    @Override
    public IState moveDown() {
        galaxyData.getPlanetBoard().moveDrone("down");
        if (checkOnDrone()) return new OnPlanetState(galaxyData);
        else return this;
    }

    @Override
    public IState moveLeft() {
        galaxyData.getPlanetBoard().moveDrone("left");
        if (checkOnDrone()) return new OnPlanetState(galaxyData);
        else return this;
    }

    @Override
    public IState moveRight() {
        galaxyData.getPlanetBoard().moveDrone("right");
        if (checkOnDrone()) return new OnPlanetState(galaxyData);
        else return this;
    }

    private boolean checkOnDrone(){
        if (galaxyData.getPlanetBoard().getDrone().isBackWithCargo()) {
            galaxyData.getShip().addCargo(galaxyData.getPlanetBoard().getDrone().getCargoType(),
                    galaxyData.getPlanetBoard().getDrone().getCargoType().equals("artifact") ? 1 : (int) Math.floor(Math.random() * 6 + 1));
            ((Planet) galaxyData.getLandable()).setExplorable(false);
            return true;
        }

        if (galaxyData.getPlanetBoard().getDrone().getHealth() == 0) {
            galaxyData.getShip().setDroneWorking(false);
            return true;
        }
        return false;
    }
}
