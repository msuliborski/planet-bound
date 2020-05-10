package com.suliborski.planetbound.logic.states;

import com.suliborski.planetbound.logic.Galaxy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AcceptTravelConsequencesState extends StateAdapter {


    public AcceptTravelConsequencesState(Galaxy galaxy) {
        super(galaxy);
    }

    @Override
    public IState acceptTravelConsequences(){
        galaxy.prepareLandable();
        galaxy.prepareEvent();

        if (galaxy.getEvent() == null) {
            int fuelCosts = 3;
            int shieldsCosts = 2;
            int crewCosts = 0;

            if (galaxy.getShip().getCrew() < 4) fuelCosts++;
            shieldsCosts += 2;
            if (galaxy.getShip().getShields() < shieldsCosts) crewCosts++;

            if (galaxy.getShip().getFuel() >= fuelCosts && galaxy.getShip().getShields() >= shieldsCosts && galaxy.getShip().getCrew() >= crewCosts) {
                galaxy.getShip().setFuel(galaxy.getShip().getFuel() - fuelCosts);
                galaxy.getShip().setShields(galaxy.getShip().getShields() - shieldsCosts);
                galaxy.getShip().setCrew(galaxy.getShip().getCrew() - crewCosts);
                return galaxy.getLandable().isPlanet() ? new OnPlanetState(galaxy) : new OnSpaceStationState(galaxy);
            }
            return new GameLostState(galaxy);
        }

        if (galaxy.getEvent().getType().equals("crew-death")) {
            if (galaxy.getShip().getCrew() >= 1) {
                galaxy.getShip().setCrew(galaxy.getShip().getCrew() - 1);
                return galaxy.getLandable().isPlanet() ? new OnPlanetState(galaxy) : new OnSpaceStationState(galaxy);
            }
            return new GameLostState(galaxy);
        }

        if (galaxy.getEvent().getType().equals("salvage-ship")) {
            double type = Math.floor(Math.random() * 4 + 1);
            int amount = (int) Math.floor(Math.random() * 6 + 1);
            if (type == 1) galaxy.getShip().addCargo("red", amount);
            else if (type == 2) galaxy.getShip().addCargo("green", amount);
            else if (type == 3) galaxy.getShip().addCargo("blue", amount);
            else galaxy.getShip().addCargo("black", amount);
            return galaxy.getLandable().isPlanet() ? new OnPlanetState(galaxy) : new OnSpaceStationState(galaxy);
        }
        if (galaxy.getEvent().getType().equals("cargo-loss")) {
            if (galaxy.getShip().getRedCargo() + galaxy.getShip().getGreenCargo() + galaxy.getShip().getBlueCargo() + galaxy.getShip().getBlackCargo() == 0)
                return galaxy.getLandable().isPlanet() ? new OnPlanetState(galaxy) : new OnSpaceStationState(galaxy);

            List<String> cargoIds = new ArrayList<>();
            if (galaxy.getShip().getRedCargo() > 0) cargoIds.add("red");
            if (galaxy.getShip().getGreenCargo() > 0) cargoIds.add("green");
            if (galaxy.getShip().getBlueCargo() > 0) cargoIds.add("blue");
            if (galaxy.getShip().getBlackCargo() > 0) cargoIds.add("black");

            galaxy.getShip().removeCargo(cargoIds.get(new Random().nextInt(cargoIds.size())), (int) Math.floor(Math.random() * 3 + 1));

            return galaxy.getLandable().isPlanet() ? new OnPlanetState(galaxy) : new OnSpaceStationState(galaxy);
        }
        if (galaxy.getEvent().getType().equals("fuel-loss")) {
            if (galaxy.getShip().getFuel() >= 1) {
                galaxy.getShip().setFuel(galaxy.getShip().getFuel() - 1);
                return galaxy.getLandable().isPlanet() ? new OnPlanetState(galaxy) : new OnSpaceStationState(galaxy);
            }
            return new GameLostState(galaxy);
        }
        if (galaxy.getEvent().getType().equals("no-event"))
            return galaxy.getLandable().isPlanet() ? new OnPlanetState(galaxy) : new OnSpaceStationState(galaxy);
        if (galaxy.getEvent().getType().equals("crew-rescue")) {
            if (galaxy.getShip().getCrew() <= 6) galaxy.getShip().setCrew(galaxy.getShip().getCrew() + 1);
            return galaxy.getLandable().isPlanet() ? new OnPlanetState(galaxy) : new OnSpaceStationState(galaxy);
        }
        return this;
    }

}
