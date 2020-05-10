package com.suliborski.planetbound.logic;

import com.suliborski.planetbound.logic.data.*;
import com.suliborski.planetbound.logic.states.GalaxyState;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Galaxy {
    Ship ship;

    Landable nextLandable;
    Event nextEvent;

    PlanetBoard planetBoard;

    GalaxyState galaxyState;

    private static final Galaxy instance = new Galaxy();

    private Galaxy(){
        galaxyState = GalaxyState.awaitShipSelection;
    }

    public static Galaxy getInstance() {
        return instance == null ? new Galaxy() : instance;
    }

    public void chooseShip(String t) {
        if (ship == null) {
            if (t.equals("mining"))
                ship = new Ship(t, 6, 53, 18, 1, 6, 53, 18, 9, 0, 0, 0, 0, 0, 6, 6, 6, 6, 0, 3, null);
            else
                ship = new Ship(t, 6, 35, 18, 2, 6, 35, 18, 9, 0, 0, 0, 0, 0, 6, 6, 6, 6, 0, 1, null);
            prepareNextTripLandableAndEvent();
            ship.setCurrentLandable(nextLandable);
            galaxyState = GalaxyState.onPlanetOrSpaceStation;
        }
    }

    private void prepareNextTripLandableAndEvent() {
        double r = Math.random();
        if (r > 0.3) {
            r = Math.floor(Math.random() * 4 + 1);
            if (r == 1) nextLandable = new Planet("red", 1, 0, 1, 0, 0, true);
            else if (r == 2) nextLandable = new Planet("green", 1, 1, 0, 0, 0, true);
            else if (r == 3) nextLandable = new Planet("blue", 0, 1, 1, 1, 1, true);
            else nextLandable = new Planet("black", 0, 0, 1, 1, 0, true);
        } else
            nextLandable = new SpaceStation();

        r = Math.random();
        if (r < 0.125) {
            nextEvent = null;
        } else {
            r = Math.floor(Math.random() * 6 + 1);
            if (r == 1) nextEvent = new Event("crew-death");
            else if (r == 2) nextEvent = new Event("salvage-ship");
            else if (r == 3) nextEvent = new Event("cargo-loss");
            else if (r == 4) nextEvent = new Event("fuel-loss");
            else if (r == 5) nextEvent = new Event("no-event");
            else nextEvent = new Event("crew-rescue");
        }
    }

    public Event tryToGoToNextLandable(){
        prepareNextTripLandableAndEvent();
        if (nextEvent == null)
            galaxyState = GalaxyState.acceptWormholeConsequences;
        else
            galaxyState = GalaxyState.acceptEventConsequences;
        return nextEvent;
    }

    public boolean acceptConsequencesAndArriveOnLandable(Event e){ //true if can accept consequences
        if (e == null) {
            int fuelCosts = 3;
            int shieldsCosts = 2;
            int crewCosts = 0;

            if (ship.getCrew() < 4) fuelCosts++;
            shieldsCosts += 2;
            if (ship.getShields() < shieldsCosts) crewCosts++;

            if (ship.getFuel() >= fuelCosts && ship.getShields() >= shieldsCosts && ship.getCrew() >= crewCosts) {
                ship.setFuel(ship.getFuel() - fuelCosts);
                ship.setShields(ship.getShields() - shieldsCosts);
                ship.setCrew(ship.getCrew() - crewCosts);
                return true;
            }
            return false;
        }

        if (e.getType().equals("crew-death")) {
            if (ship.getCrew() >= 1) {
                ship.setCrew(ship.getCrew() - 1);
                return true;
            }
            return false;
        }

        if (e.getType().equals("salvage-ship")) {
            double type = Math.floor(Math.random() * 4 + 1);
            int amount = (int) Math.floor(Math.random() * 6 + 1);
            if (type == 1) ship.addCargo("red", amount);
            else if (type == 2) ship.addCargo("green", amount);
            else if (type == 3) ship.addCargo("blue", amount);
            else ship.addCargo("black", amount);
            return true;
        }
        if (e.getType().equals("cargo-loss")) {
            if (ship.getRedCargo() + ship.getGreenCargo() + ship.getBlueCargo() + ship.getBlackCargo() == 0) return true;

            List<Integer> cargoIds = new ArrayList<Integer>();
            if (ship.getRedCargo() > 0) cargoIds.add(0);
            if (ship.getGreenCargo() > 0) cargoIds.add(1);
            if (ship.getBlueCargo() > 0) cargoIds.add(2);
            if (ship.getBlackCargo() > 0) cargoIds.add(3);

            int randomCargoId = cargoIds.get(new Random().nextInt(cargoIds.size()));

            if (randomCargoId == 0) ship.removeCargo("red", (int) Math.floor(Math.random() * 3 + 1));
            if (randomCargoId == 1) ship.removeCargo("green", (int) Math.floor(Math.random() * 3 + 1));
            if (randomCargoId == 2) ship.removeCargo("blue", (int) Math.floor(Math.random() * 3 + 1));
            if (randomCargoId == 3) ship.removeCargo("black", (int) Math.floor(Math.random() * 3 + 1));
            return true;
        }
        if (e.getType().equals("fuel-loss")) {
            if (ship.getFuel() >= 1) {
                ship.setFuel(ship.getFuel() - 1);
                return true;
            }
            return false;
        }
        if (e.getType().equals("no-event")) return true;
        if (e.getType().equals("crew-rescue")) {
            if (ship.getCrew() <= 6) ship.setCrew(ship.getCrew() + 1);
            return true;
        }
        return true;
    }

    public boolean buy(String what) {
        if (what.equals("energy-shield")) {
            if (ship.getBlackCargo() > 0 && ship.getGreenCargo() > 0 && ship.getBlueCargo() > 0 && ship.getShields() < ship.getShieldsCapacity()) {
                ship.removeCargo("black", 1);
                ship.removeCargo("green", 1);
                ship.removeCargo("blue", 1);
                ship.setShields(ship.getShields() + 1);
                return true;
            }
        }

        if (what.equals("ammo")) {
            if (ship.getBlackCargo() > 0 && ship.getBlueCargo() > 0 && ship.getWeaponSystems() < ship.getWeaponSystemsCapacity()) {
                ship.removeCargo("black", 1);
                ship.removeCargo("blue", 1);
                ship.setWeaponSystems(ship.getWeaponSystems() + 1);
                return true;
            }
        }

        if (what.equals("fuel")) {
            if (ship.getBlackCargo() > 0 && ship.getGreenCargo() > 0 && ship.getRedCargo() > 0 && ship.getFuel() < ship.getFuelCapacity()) {
                ship.removeCargo("black", 1);
                ship.removeCargo("red", 1);
                ship.removeCargo("blue", 1);
                ship.setFuel(ship.getFuel() + 1);
                return true;
            }
        }
        return false;

        if (what.equals("cargo-upgrade")) {
            if (ship.getBlackCargo() > 2 && ship.getGreenCargo() > 2 && ship.getRedCargo() > 2 && ship.getBlueCargo() > 2 && nextLandable.) {
                ship.removeCargo("black", 1);
                ship.removeCargo("red", 1);
                ship.removeCargo("blue", 1);
                ship.setFuel(ship.getFuel() + 1);
                return true;
            }
        }
        return false;
    }

    public boolean upgradeCargo(){
        return true;
    }


    public void preparePlanetBoard(int id){

    }

    public void move(Minion m, int direction){

    }

    public void attack(Minion m){

    }


}
