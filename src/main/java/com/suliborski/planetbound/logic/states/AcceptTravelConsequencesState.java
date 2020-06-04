package com.suliborski.planetbound.logic.states;

import com.suliborski.planetbound.logic.GalaxyData;
import com.suliborski.planetbound.logic.data.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AcceptTravelConsequencesState extends StateAdapter {

    public AcceptTravelConsequencesState(GalaxyData galaxyData) {
        super(galaxyData);
        galaxyData.preparePlanet();
        galaxyData.prepareEvent();
        GalaxyData.addLog(new Log("Into AcceptTravelConsequences State"));
    }

    @Override
    public IState acceptTravelConsequences(){
        GalaxyData.addLog(new Log("Consequences of travel accepted"));
        if (galaxyData.getEventType() == null) {
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
                return new OnPlanetState(galaxyData);
            }
            return new GameLostState(galaxyData);
        } else
            galaxyData.getShip().setFuel(galaxyData.getShip().getFuel() - 1);

        switch (galaxyData.getEventType()) {
            case crewDeath:
                if (galaxyData.getShip().getCrew() >= 1) {
                    galaxyData.getShip().setCrew(galaxyData.getShip().getCrew() - 1);
                    return new OnPlanetState(galaxyData);
                }
                return new GameLostState(galaxyData);
            case salvageShip:
                double type = Math.floor(Math.random() * 4 + 1);
                int amount = (int) Math.floor(Math.random() * 6 + 1);
                if (type == 1) galaxyData.getShip().addResource("red", amount);
                else if (type == 2) galaxyData.getShip().addResource("green", amount);
                else if (type == 3) galaxyData.getShip().addResource("blue", amount);
                else galaxyData.getShip().addResource("black", amount);
                return new OnPlanetState(galaxyData);
            case cargoLoss:
                if (galaxyData.getShip().getRedCargo() + galaxyData.getShip().getGreenCargo() + galaxyData.getShip().getBlueCargo() + galaxyData.getShip().getBlackCargo() == 0)
                    return new OnPlanetState(galaxyData);

                List<String> cargoIds = new ArrayList<>();
                if (galaxyData.getShip().getRedCargo() > 0) cargoIds.add("red");
                if (galaxyData.getShip().getGreenCargo() > 0) cargoIds.add("green");
                if (galaxyData.getShip().getBlueCargo() > 0) cargoIds.add("blue");
                if (galaxyData.getShip().getBlackCargo() > 0) cargoIds.add("black");

                galaxyData.getShip().removeResource(cargoIds.get(new Random().nextInt(cargoIds.size())), (int) Math.floor(Math.random() * 3 + 1), false);

                return new OnPlanetState(galaxyData);
            case fuelLoss:
                if (galaxyData.getShip().getFuel() >= 1) {
                    galaxyData.getShip().setFuel(galaxyData.getShip().getFuel() - 1);
                    return new OnPlanetState(galaxyData);
                }
                return new GameLostState(galaxyData);
            case noEvent:
                return new OnPlanetState(galaxyData);
            case crewRescue:
                if (galaxyData.getShip().getCrew() < 6)
                    galaxyData.getShip().setCrew(galaxyData.getShip().getCrew() + 1);
                return new OnPlanetState(galaxyData);
        }
        return this;
    }
}
