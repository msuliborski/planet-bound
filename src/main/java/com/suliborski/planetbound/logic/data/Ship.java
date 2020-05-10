package com.suliborski.planetbound.logic.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Ship {

    private String type;
    private int crew;
    private int fuel;
    private int shields;
    private int weaponSystems;
    private int crewCapacity;
    private int fuelCapacity;
    private int shieldsCapacity;
    private int weaponSystemsCapacity;
    private int artifacts;
    private int redCargo;
    private int greenCargo;
    private int blueCargo;
    private int blackCargo;
    private int redCargoCapacity;
    private int greenCargoCapacity;
    private int blueCargoCapacity;
    private int blackCargoCapacity;

    private int cargoLevel;
    private int maxCargoLevel;

    private Landable currentLandable;

    Ship(){

    }

    public void addCargo(String type, int amount) {
        if (type.equals("red")) redCargo = Math.min(redCargo + amount, redCargoCapacity);
        else if (type.equals("green")) greenCargo = Math.min(greenCargo + amount, greenCargoCapacity);
        else if (type.equals("blue")) blueCargo = Math.min(blueCargo + amount, blueCargoCapacity);
        else blackCargo = Math.min(blackCargo + amount, blackCargoCapacity);
    }

    public void removeCargo(String type, int amount) {
        if (type.equals("red")) redCargo = Math.max(redCargo - amount, 0);
        else if (type.equals("green")) greenCargo = Math.max(greenCargo - amount, 0);
        else if (type.equals("blue")) blueCargo = Math.max(blueCargo - amount, 0);
        else blackCargo = Math.max(blackCargo - amount, 0);
    }

    void goToLandable(Landable l, Event e) {

    }
    void explore(Planet p) {

    }
    void buy(String type) {

    }

}
