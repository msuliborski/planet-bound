package com.suliborski.planetbound.logic.data;

import com.suliborski.planetbound.logic.states.IState;
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

    private boolean isDroneWorking;

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

    public void buyEnergyShield() {
        if (getBlackCargo() > 0 && getGreenCargo() > 0 && getBlueCargo() > 0 && getShields() < getShieldsCapacity()) {
            removeCargo("black", 1);
            removeCargo("green", 1);
            removeCargo("blue", 1);
            setShields(getShields() + 1);
        }
    }
    
    public void buyAmmo() {
        if (getBlackCargo() > 0 && getBlueCargo() > 0 && getWeaponSystems() < getWeaponSystemsCapacity()) {
            removeCargo("black", 1);
            removeCargo("blue", 1);
            setWeaponSystems(getWeaponSystems() + 1);
        }
    }

    public void buyFuel() {
        if (getBlackCargo() > 0 && getGreenCargo() > 0 && getRedCargo() > 0 && getFuel() < getFuelCapacity()) {
            removeCargo("black", 1);
            removeCargo("red", 1);
            removeCargo("blue", 1);
            setFuel(getFuel() + 1);
        }
    }


    public void upgradeCargo() {
        if (getBlackCargo() > 2 && getGreenCargo() > 2 && getRedCargo() > 2 && getBlueCargo() > 2 && getCargoLevel() < getMaxCargoLevel()) {
            removeCargo("black", 3);
            removeCargo("red", 3);
            removeCargo("blue", 3);
            removeCargo("green", 3);
            setCargoLevel(getCargoLevel() + 1);
        }
    }

    public void recruitCrewMember() {
        if (getBlackCargo() > 0 && getGreenCargo() > 0 && getRedCargo() > 0 && getBlueCargo() > 0 && getCrew() < getCrewCapacity()) {
            removeCargo("black", 1);
            removeCargo("red", 1);
            removeCargo("blue", 1);
            removeCargo("green", 1);
            setCrew(getCrew() + 1);
        }
    }

}
