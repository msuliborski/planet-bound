package com.suliborski.planetbound.logic.states;

import com.suliborski.planetbound.logic.Galaxy;
import com.suliborski.planetbound.logic.data.Planet;

public class OnExpeditionState extends StateAdapter {


    public OnExpeditionState(Galaxy galaxy) {
        super(galaxy);
        galaxy.getPlanetBoard().prepareBoard(((Planet)galaxy.getLandable()).getType());
    }

    @Override
    public IState moveUp() {
        galaxy.getPlanetBoard().moveDrone("up");
        if (checkOnDrone()) return new OnPlanetState(galaxy);
        else return this;
    }

    @Override
    public IState moveDown() {
        galaxy.getPlanetBoard().moveDrone("down");
        if (checkOnDrone()) return new OnPlanetState(galaxy);
        else return this;
    }

    @Override
    public IState moveLeft() {
        galaxy.getPlanetBoard().moveDrone("left");
        if (checkOnDrone()) return new OnPlanetState(galaxy);
        else return this;
    }

    @Override
    public IState moveRight() {
        galaxy.getPlanetBoard().moveDrone("right");
        if (checkOnDrone()) return new OnPlanetState(galaxy);
        else return this;
    }

    private boolean checkOnDrone(){
        if (galaxy.getPlanetBoard().getDrone().isBackWithCargo()) {
            galaxy.getShip().addCargo(galaxy.getPlanetBoard().getDrone().getCargoType(),
                    galaxy.getPlanetBoard().getDrone().getCargoType().equals("artifact") ? 1 : (int) Math.floor(Math.random() * 6 + 1));
            ((Planet) galaxy.getLandable()).setExplorable(false);
            return true;
        }

        if (galaxy.getPlanetBoard().getDrone().getHealth() == 0) {
            galaxy.getShip().setDroneWorking(false);
            return true;
        }
        return false;
    }
}
