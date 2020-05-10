package com.suliborski.planetbound.logic.states;

import com.suliborski.planetbound.logic.GalaxyData;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AcceptTravelConsequencesState extends StateAdapter {


    public AcceptTravelConsequencesState(GalaxyData galaxyData) {
        super(galaxyData);
    }

    @Override
    public IState acceptTravelConsequences(){
        galaxyData.prepareLandable();
        galaxyData.prepareEvent();

        if (galaxyData.getEvent() == null) {
            int fuelCosts = 3;
            int shieldsCosts = 2;
            int crewCosts = 0;

            if (galaxyData.getShip().getCrew() < 4) fuelCosts++;
            shieldsCosts += 2;
            if (galaxyData.getShip().getShields() < shieldsCosts) crewCosts++;

            if (galaxyData.getShip().getFuel() >= fuelCosts && galaxyData.getShip().getShields() >= shieldsCosts && galaxyData.getShip().getCrew() >= crewCosts) {
                galaxyData.getShip().setFuel(galaxyData.getShip().getFuel() - fuelCosts);
                galaxyData.getShip().setShields(galaxyData.getShip().getShields() - shieldsCosts);
                galaxyData.getShip().setCrew(galaxyData.getShip().getCrew() - crewCosts);
                return galaxyData.getLandable().isPlanet() ? new OnPlanetState(galaxyData) : new OnSpaceStationState(galaxyData);
            }
            return new GameLostState(galaxyData);
        }

        if (galaxyData.getEvent().getType().equals("crew-death")) {
            if (galaxyData.getShip().getCrew() >= 1) {
                galaxyData.getShip().setCrew(galaxyData.getShip().getCrew() - 1);
                return galaxyData.getLandable().isPlanet() ? new OnPlanetState(galaxyData) : new OnSpaceStationState(galaxyData);
            }
            return new GameLostState(galaxyData);
        }

        if (galaxyData.getEvent().getType().equals("salvage-ship")) {
            double type = Math.floor(Math.random() * 4 + 1);
            int amount = (int) Math.floor(Math.random() * 6 + 1);
            if (type == 1) galaxyData.getShip().addCargo("red", amount);
            else if (type == 2) galaxyData.getShip().addCargo("green", amount);
            else if (type == 3) galaxyData.getShip().addCargo("blue", amount);
            else galaxyData.getShip().addCargo("black", amount);
            return galaxyData.getLandable().isPlanet() ? new OnPlanetState(galaxyData) : new OnSpaceStationState(galaxyData);
        }
        if (galaxyData.getEvent().getType().equals("cargo-loss")) {
            if (galaxyData.getShip().getRedCargo() + galaxyData.getShip().getGreenCargo() + galaxyData.getShip().getBlueCargo() + galaxyData.getShip().getBlackCargo() == 0)
                return galaxyData.getLandable().isPlanet() ? new OnPlanetState(galaxyData) : new OnSpaceStationState(galaxyData);

            List<String> cargoIds = new ArrayList<>();
            if (galaxyData.getShip().getRedCargo() > 0) cargoIds.add("red");
            if (galaxyData.getShip().getGreenCargo() > 0) cargoIds.add("green");
            if (galaxyData.getShip().getBlueCargo() > 0) cargoIds.add("blue");
            if (galaxyData.getShip().getBlackCargo() > 0) cargoIds.add("black");

            galaxyData.getShip().removeCargo(cargoIds.get(new Random().nextInt(cargoIds.size())), (int) Math.floor(Math.random() * 3 + 1));

            return galaxyData.getLandable().isPlanet() ? new OnPlanetState(galaxyData) : new OnSpaceStationState(galaxyData);
        }
        if (galaxyData.getEvent().getType().equals("fuel-loss")) {
            if (galaxyData.getShip().getFuel() >= 1) {
                galaxyData.getShip().setFuel(galaxyData.getShip().getFuel() - 1);
                return galaxyData.getLandable().isPlanet() ? new OnPlanetState(galaxyData) : new OnSpaceStationState(galaxyData);
            }
            return new GameLostState(galaxyData);
        }
        if (galaxyData.getEvent().getType().equals("no-event"))
            return galaxyData.getLandable().isPlanet() ? new OnPlanetState(galaxyData) : new OnSpaceStationState(galaxyData);
        if (galaxyData.getEvent().getType().equals("crew-rescue")) {
            if (galaxyData.getShip().getCrew() <= 6) galaxyData.getShip().setCrew(galaxyData.getShip().getCrew() + 1);
            return galaxyData.getLandable().isPlanet() ? new OnPlanetState(galaxyData) : new OnSpaceStationState(galaxyData);
        }
        return this;
    }

}
