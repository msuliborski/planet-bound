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
    private int ammo;
    private int crewCapacity;
    private int fuelCapacity;
    private int shieldsCapacity;
    private int ammoCapacity;
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
    private int weaponSystemLevel;
    private int maxWeaponSystemLevel;

    private boolean isDroneWorking;

    public void addResource(String type, int amount) {
        switch (type) {
            case "red": redCargo = Math.min(redCargo + amount, redCargoCapacity); break;
            case "green": greenCargo = Math.min(greenCargo + amount, greenCargoCapacity); break;
            case "blue": blueCargo = Math.min(blueCargo + amount, blueCargoCapacity); break;
            case "black": blackCargo = Math.min(blackCargo + amount, blackCargoCapacity); break;
            case "artifact": artifacts += amount; break;
        }
    }

    public boolean removeResource(String type, int amount, boolean forceRemoval) { //if force true, you must have needed resources
        switch (type) {
            case "red":
                if (forceRemoval && redCargo - amount < 0) return false;
                else redCargo = Math.max(redCargo - amount, 0); return true;
            case "green":
                if (forceRemoval && greenCargo - amount < 0) return false;
                else greenCargo = Math.max(greenCargo - amount, 0); return true;
            case "blue":
                if (forceRemoval && blueCargo - amount < 0) return false;
                else blueCargo = Math.max(blueCargo - amount, 0); return true;
            case "black":
                if (forceRemoval && blackCargo - amount < 0) return false;
                else blackCargo = Math.max(blackCargo - amount, 0); return true;
        }
        return false;
    }

    public void convertResource(String type, String into){
        if (removeResource(type, 1, true))
            addResource(into, 1);
    }

    public void produceEnergyShield() {
        if (getBlackCargo() >= 1 && getGreenCargo() >= 1 && getBlueCargo() >= 1 && getShields() < getShieldsCapacity()) {
            removeResource("black", 1, true);
            removeResource("green", 1, true);
            removeResource("blue", 1, true);
            setShields(getShields() + 1);
        }
    }
    
    public void produceAmmo() {
        if (getBlackCargo() >= 1 && getBlueCargo() >= 1 && getAmmo() < getAmmoCapacity()) {
            removeResource("black", 1, true);
            removeResource("blue", 1, true);
            setAmmo(getAmmo() + 1);
        }
    }

    public void produceFuel() {
        if (getBlackCargo() >= 1 && getGreenCargo() >= 1 && getRedCargo() >= 1 && getFuel() < getFuelCapacity()) {
            removeResource("black", 1, true);
            removeResource("red", 1, true);
            removeResource("blue", 1, true);
            setFuel(getFuel() + 1);
        }
    }

    public void buyDrone() {
        if (getBlackCargo() >= 2 && getGreenCargo() >= 2 && getRedCargo() >= 2 && getBlueCargo() >= 2 && !isDroneWorking()) {
            removeResource("black", 2, true);
            removeResource("red", 2, true);
            removeResource("blue", 2, true);
            removeResource("green", 2, true);
            setDroneWorking(true);
        }
    }

    public void upgradeCargo() {
        if (getBlackCargo() >= 1 && getGreenCargo() >= 1 && getRedCargo() >= 1 && getBlueCargo() >= 1 && getCargoLevel() < getMaxCargoLevel()) {
            removeResource("black", 1, true);
            removeResource("red", 1, true);
            removeResource("blue", 1, true);
            removeResource("green", 1, true);
            setCargoLevel(getCargoLevel() + 1);
        }
    }

    public void upgradeWeaponSystem() {
        if (getBlackCargo() >= 2 && getGreenCargo() >= 2 && getRedCargo() >= 2 && getBlueCargo() >= 2 && getWeaponSystemLevel() < getMaxWeaponSystemLevel()) {
            removeResource("black", 2, true);
            removeResource("red", 2, true);
            removeResource("blue", 2, true);
            removeResource("green", 2, true);
            setCargoLevel(getCargoLevel() + 1);
        }
    }

    public void recruitCrewMember() {
        if (getBlackCargo() >=1  && getGreenCargo() >= 1 && getRedCargo() >= 1 && getBlueCargo() >= 1 && getCrew() < getCrewCapacity()) {
            removeResource("black", 1, true);
            removeResource("red", 1, true);
            removeResource("blue", 1, true);
            removeResource("green", 1, true);
            setCrew(getCrew() + 1);
        }
    }

    public void fullFixEnergyShields() {
        if (getBlackCargo() >= 1 && getGreenCargo() >= 1 && getRedCargo() >= 1 && getBlueCargo() >= 1 && getShields() < getShieldsCapacity()) {
            removeResource("black", 1, true);
            removeResource("red", 1, true);
            removeResource("blue", 1, true);
            removeResource("green", 1, true);
            shields = shieldsCapacity;
        }
    }
}
